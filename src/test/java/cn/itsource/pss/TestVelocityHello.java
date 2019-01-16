package cn.itsource.pss;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.junit.Test;

public class TestVelocityHello {
	
	@Test
	public void testHello1() throws ResourceNotFoundException, ParseErrorException, Exception{
		//创建一个Velocity核心对象
		VelocityContext velocityContext = new VelocityContext();
		//把后台准备的数据放到VelocityContext里面去
		velocityContext.put("message", "师姐");
		//拿到需要被合并的模板
		Template template = Velocity.getTemplate("template/index.vm", "UTF-8");
		
		StringWriter stringWriter = new StringWriter();
		//开始合并
		template.merge(velocityContext, stringWriter);
		System.out.println(stringWriter);
	}
	
	
	@Test
	public void testHello2() throws ResourceNotFoundException, ParseErrorException, Exception{
		//获取Velocity的核心对象
		VelocityContext context = new VelocityContext();
		//后台准备前端展示的数据
		context.put("message", "世界");
		//拿到需要被合并的模板
		Template template = Velocity.getTemplate("template/index.vm", "UTF-8");
		
		FileWriter writer = new FileWriter(new File("src/main/webapp/index.html"));
		//合并
		template.merge(context, writer);
		//关闭流
		writer.close();
	}
}
