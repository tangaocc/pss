package cn.itsource.pss.web.action;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itsource.pss.service.IEmployeeService;

@Controller
@Scope("prototype")
public class ImportAction extends BaseAction {
	//接收前端传递过来的附件
	private File upload;
	
	@Autowired
	private IEmployeeService employeeService;
	
	
	/**
	 * 跳转jsp与上传附件都是用的同一个方法
	 */
	@Override
	public String execute() throws Exception {
		if(upload!=null){
			//解析file文件，转为Employee数据
			List<String[]> datas = employeeService.importExcel(upload);
			employeeService.saveExcelData(datas);
			putContext("count", datas.size());
		}
		return SUCCESS;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	

	
}
