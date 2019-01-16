package cn.itsource.pss.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.Department;
import cn.itsource.pss.domain.Employee;
import cn.itsource.pss.page.PageList;
import cn.itsource.pss.query.BaseQuery;
import cn.itsource.pss.repository.DepartmentRepository;
import cn.itsource.pss.repository.EmployeeRepository;
import cn.itsource.pss.service.IDepartmentService;
import cn.itsource.pss.service.IEmployeeService;

//当前bean交给spring进行管理
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Long> implements IEmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private IDepartmentService departmentService;
	/**
	 * 验证用户名是否存在
	 * 		1.拿到当前用户名去数据库中进行查询
	 * 		2.如果查询的总数>0,那证明就已经存在了
	 * 		3.如果查询的总数=0,那证明该用户名是不存在的
	 * 
	 * 返回的结果值：  true表示用户名没重复，false表示用户名已重复了
	 * 
	 * 		判断用户名是否存在：
	 * 		判断新增还是修改，我们可以通过employee的主键id进行一个判断
	 * 				1. 新增
						  验证用户名是否存在
							1.拿到当前用户名去数据库中进行查询
							2.如果查询的总数>0,那证明就已经存在了
							3.如果查询的总数=0,那证明该用户名是不存在的
					
	 * 				2. 修改
	 * 					 通过id去把对应的employee对象查询出来
	 * 						1.username与employee对应的 username进行一个标胶
	 * 							如果相等：让它过，证明用户名是没有修改的
	 * 							如果不相等： 又要去数据库进行查询判断
	 * 					 
	 * 
	 * 	
	 * 
	 * @return
	 */
	@Override
	public boolean checkUsername(Long id,String username) {
		
		String jpql = "select count(o) from Employee o where username=?";
		if(id == null){//证明是新增
			List list = employeeRepository.findByJpql(jpql, username);
			Long count = (Long)list.get(0);
			return count == 0;
			
		}else{//证明是修改
			//通过id查询到对应的employee对象
			Employee employee = super.queryOne(id);
			if(username.equals(employee.getUsername())){
				return true;// 应该直接放行    true:代表是没重复， false：代表重复了
			}else{
				List list = employeeRepository.findByJpql(jpql, username);
				Long count = (Long)list.get(0);
				return count == 0;
			}
		}
	}


	/**
	 * 登录的流程：
	 * 		1.先判断用户名在数据库中是否存在
	 * 			1.1 存在， 则判断密码
	 * 			1.2 不存在，直接抛出异常，提示前端用户，用户名不存在
	 * 		2.判断密码的时候
	 * 			2.1 相等， 直接返回对象
	 * 			2.2不相等，抛出异常，提示用户密码错误
	 */
	@Override
	public Employee checkUsernameAndPassword(String username, String password) {
		String jpql = "select o from Employee o where o.username=?";
		List<Employee> employees = employeeRepository.findByJpql(jpql, username);
		if(employees==null || employees.size()==0){
			throw new RuntimeException("用户名不存在！！！");
		}
		Employee employee = employees.get(0);
		if(employee.getPassword().equals(password)){
			return employee;	
		}else{
			throw new RuntimeException("密码异常!!");
		}
	}


	@Override
	public InputStream downloadExcel(BaseQuery baseQuery) throws IOException {
		baseQuery.setPageSize(Integer.MAX_VALUE);
		PageList<Employee> pageList = super.findPageByQuery(baseQuery);
		String[] head = {"ID","用户名","密码","email","年龄","部门"};
		List<Employee> datas = pageList.getData();
		//准备一个集合，该集合专门是用来封装我们对象数据的 
		List<String[]> data = new ArrayList<String[]>();
		for (Employee employee : datas) {
			String[] strs = new String[head.length];
			strs[0] = employee.getId().toString();
			strs[1] = employee.getUsername();
			strs[2] = employee.getPassword();
			strs[3] = employee.getEmail();
			strs[4] = employee.getAge() == null ? null : employee.getAge().toString();
			strs[5] = employee.getDepartment()==null ? null : employee.getDepartment().getName();
			data.add(strs);
		}
		InputStream inputStream = super.data2Excel(data, head);
		return inputStream;
	}


	@Override
	public void saveExcelData(List<String[]> datas) {
	
		//定义一个集合
		Map<String,Department> map = new HashMap<String,Department>();
		for (String[] strs : datas) {
			Employee employee = new Employee();
			employee.setUsername(strs[0]);
			employee.setPassword(strs[1]);
			employee.setEmail(strs[2]);
			employee.setAge(strs[3] == null ? null : Integer.valueOf(strs[3]));
			String deptName = strs[4];
			if(StringUtils.isNotBlank(deptName)){
				Department department = null;
				if(!map.containsKey(deptName)){
					department = departmentService.findDepartmentByName(deptName);
					map.put(deptName, department);
				}else{
					department = map.get(deptName);
				}
				employee.setDepartment(department);
			}
			employeeRepository.save(employee);
		}
	}

}
