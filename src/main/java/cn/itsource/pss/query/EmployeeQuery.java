package cn.itsource.pss.query;

import org.apache.commons.lang.StringUtils;

import cn.itsource.pss.domain.Employee;

/**
 * 接收前端传递过来的参数
 * 
 * @author Administrator
 *
 */
public class EmployeeQuery extends BaseQuery {

	private String username;
	private String password;
	private Long departmentId;

	public EmployeeQuery() {
		super(Employee.class);
	}

	/**
	 * 添加条件
	 */
	@Override
	public void addCondition() {
		if (StringUtils.isNotBlank(username)) {
			super.addWhere("o.username like ?", "%" + username + "%");
		}
		if (StringUtils.isNotBlank(password)) {
			super.addWhere("o.password=?", password);
		}
		if (departmentId != null && departmentId != -1) {// 如果id不为-1，就证明它要去查询具体的部门
			super.addWhere("o.department.id=?", departmentId);
		}
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "EmployeeQuery [username=" + username + ", password=" + password + ", departmentId=" + departmentId
				+ "]";
	}

}
