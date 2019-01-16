package cn.itsource.pss;

import java.io.File;
import java.io.FileWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.junit.Test;

public class TestCreateCoder {
	
	//最基本的SRC路径
	private static final String SRC = "src/main/java/";
	//最基本的TEST路径
	private static final String TEST = "src/test/java/";
	//最基本的WEBAPP路径
	private static final String WEBAPP = "src/main/webapp/";
	//公共的包 
	private static final String PACKAGE = "cn/itsource/pss/";
	//准备的模板信息
	private static final String[] templates = {"domain_input.jsp","domain.js","domain.jsp","DomainAction.java",
			"DomainQuery.java","DomainRepository.java","DomainServiceImpl.java",
			"DomainServiceTest.java","IDomainService.java"};
	//拼接的路径
	private static final String[] PATHS = {WEBAPP+"WEB-INF/views/domain/",WEBAPP+"assets/model/",WEBAPP+"WEB-INF/views/domain/",
			SRC+PACKAGE+"web/action/",SRC+PACKAGE+"query/",SRC+PACKAGE+"repository/",
			SRC+PACKAGE+"service/impl/",TEST+PACKAGE+"service/",SRC+PACKAGE+"service/"};
	
	//要生成的模型
	private static final String[] DOMAINS = {"Depot","ProductStock","StockIncomeBill","StockIncomeBillItem"};
	private static final String[] DOMAINNAMES = {"仓库","即时库存","入库单","入库单明细"};
	
	//定义一个开关，如果开关为false，那就不能创建文件，如果开关为true，它是允许去创建文件的(前提：该文件没有)
	private boolean flag = true;
	
	
	@Test
	public void testCreateCode() throws ResourceNotFoundException, ParseErrorException, Exception{
		
		//创建VelocityContext对象
		VelocityContext velocityContext = new VelocityContext();
		//开始合并
		for (int i = 0; i < DOMAINS.length; i++) {
			//大写名字
			String upperCase = DOMAINS[i];
			//小写名字
			String lowerCase = upperCase.substring(0, 1).toLowerCase()+upperCase.substring(1);//department  employee
			//准备数据
			velocityContext.put("upperCase", upperCase);
			velocityContext.put("lowerCase", lowerCase);
			velocityContext.put("domainName", DOMAINNAMES[i]);
			
			for (int j = 0; j < PATHS.length; j++) {
				String path = PATHS[j]+templates[j];
				path = path.replaceAll("domain", lowerCase).replaceAll("Domain", upperCase);
				File file = new File(path);
				//判断父路径是否存在，如果不存在，直接进行一个创建
				if(!file.getParentFile().exists()){
					file.getParentFile().mkdirs();
				}
				//如果文件存在，就不再覆盖之前的文件
				if(!flag ||  file.exists()){
					continue;//结束本次循环
				}
				System.out.println(file);
				//获取要合并的模板
				Template template = Velocity.getTemplate("template/"+templates[j], "UTF-8");
				FileWriter writer = new FileWriter(path);
				template.merge(velocityContext, writer);
				writer.close();
			}
		}
		
		
	}
}


