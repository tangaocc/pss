package cn.itsource.pss.service.impl;

import org.springframework.stereotype.Service;

import cn.itsource.pss.service.IWorkService;

@Service("quartzJob")
public class WorkServiceImpl implements IWorkService {

	@Override
	public void work() {
		System.out.println("该工作了。。。。");
	}

}
