package com.company.bus.loaned.common.support.task;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.NOPClosure;
import org.apache.commons.collections.functors.SwitchClosure;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.MatchingTask;

import com.company.bus.loaned.common.support.task.closure.ErrorsMessageClosure;
import com.company.bus.loaned.common.support.task.context.MessageTaskContext;
import com.company.bus.loaned.common.support.task.predicate.ErrorsMessagePredicate;

/**
 * @Description 通过消息码属性文件生成消息码类任务
 * @author 
 * @date 
 * @version 1.0
 */
public class CodeGeneratorTask extends MatchingTask{

	@Override
	public void execute() throws BuildException {
		//校验输入
		try{
			if(!MESSAGE_TYPES.contains(StringUtils.defaultString(messageType))){
				throw new BuildException(String.format("%s was an invalid type of message resource.", messageType));
			}
			Validate.notEmpty(filePath, String.format("The validated String[filePath] is empty"));
			Validate.notEmpty(targetFileName, String.format("The validated String[targetFileName] is empty"));
			Validate.notEmpty(targetPackage, String.format("The validated String[targetPackage] is empty"));
			Validate.notEmpty(targetProject, String.format("The validated String[targetProject] is empty"));
		}catch(Throwable t){
			throw new BuildException(t);
		}
		
		//初始化MessageTaskContext
		MessageTaskContext context = new MessageTaskContext();
		context.setMessageType(messageType);
		context.setFilePath(filePath);
		context.setTargetFileName(targetFileName);
		context.setTargetPackage(targetPackage);
		context.setTargetProject(targetProject);
		context.setAntTask(this);
		
		
		//条件判断执行
		Closure codeGenerator = new SwitchClosure(
				new Predicate[]{
					new ErrorsMessagePredicate()	
				}, 
				new Closure[]{
					new ErrorsMessageClosure()	
				}, 
				NOPClosure.getInstance());
		codeGenerator.execute(context);
	}
	
	
	private final String MESSAGE_TYPES = "errors|dicts";
	//生成消息类型, e.g.:errors/dicts/....
	private String messageType;
	//properties文件路径
	private String filePath;
	//目标java文件名称
	private String targetFileName;
	//目标生成包
	private String targetPackage;
	//目标项目, e.g.:core-psfp/src/main/java
	private String targetProject;
	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @param targetFileName the targetFileName to set
	 */
	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}
	/**
	 * @param targetPackage the targetPackage to set
	 */
	public void setTargetPackage(String targetPackage) {
		this.targetPackage = targetPackage;
	}
	/**
	 * @param targetProject the targetProject to set
	 */
	public void setTargetProject(String targetProject) {
		this.targetProject = targetProject;
	}
	
	
	
}
