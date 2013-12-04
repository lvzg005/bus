package com.company.bus.loaned.common.support.task.closure;

import org.apache.commons.collections.Closure;

import com.company.bus.loaned.common.support.task.context.MessageTaskContext;

/**
 * @Description 执行闭包基类
 * @author 
 * @date 
 * @version 1.0
 */
public abstract class BaseTaskClosure implements Closure {
	/**
	 * @Description 输入obj转为资源任务上下文
	 * @param object
	 * @return
	 * @author 
	 */
	protected MessageTaskContext toMessageTaskContext(Object object){
		return object == null ? new MessageTaskContext() : (MessageTaskContext)object;
	}
}
