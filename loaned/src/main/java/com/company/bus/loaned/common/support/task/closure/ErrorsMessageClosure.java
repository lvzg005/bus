package com.company.bus.loaned.common.support.task.closure;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.velocity.VelocityContext;

import com.company.bus.loaned.common.support.TemplateUtils;
import com.company.bus.loaned.common.support.task.context.MessageTaskContext;

/**
 * @Description errors消息资源执行闭包
 * @author 
 * @date 
 * @version 1.0
 */
public class ErrorsMessageClosure extends BaseTaskClosure {

	@Override
	public void execute(Object input) {
		try{
			//获取上下文
			MessageTaskContext context = this.toMessageTaskContext(input);
			//获取ant任务对象
			MatchingTask task = context.getAntTask();
			
			task.log(String.format("message task context: %s", context));
			
			//读取资源文件
			Properties errorsCodeMsgProp = new Properties();
			try {
				errorsCodeMsgProp.load(new FileInputStream(context.getFilePath()));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			task.log(String.format("read errors message resources: %s",errorsCodeMsgProp));
			
			//将参数输入模板生成对应java文件
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("package", context.getTargetPackage());//设置包名
			velocityContext.put("createdate", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));//设置创建日期
			velocityContext.put("prefix", "Error");//设置前缀
			velocityContext.put("errors", errorsCodeMsgProp);//设置映射属性
			TemplateUtils.VelocityHelper.executeTemplateGenerator(
					"template/errorsTemplate.vm" ,
					new StringBuilder()
						.append(context.getTargetProject()).append("/")
						.append(context.getTargetPackage().replace(".", "/")).append("/")
						.append(context.getTargetFileName()).toString()
						, 
					velocityContext
			);
		}catch(Throwable e){
			throw new RuntimeException(e);
		}
	}

}
