package com.company.bus.loaned.common.support.task.predicate;

import org.apache.commons.collections.Predicate;

import com.company.bus.loaned.common.support.task.context.MessageTaskContext;

/**
 * @Description 条件基类
 * @author 
 * @date 
 * @version 1.0
 */
public abstract class BaseTaskPredicate implements Predicate {

	/**
	 * @Description 输入obj转为消息资源任务上下文
	 * @param object
	 * @return
	 * 
	 */
	protected MessageTaskContext toMessageTaskContext(Object object){
		return object == null ? new MessageTaskContext() : (MessageTaskContext)object;
	}

}
