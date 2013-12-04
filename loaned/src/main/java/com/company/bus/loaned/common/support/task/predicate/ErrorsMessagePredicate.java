package com.company.bus.loaned.common.support.task.predicate;

import com.company.bus.loaned.common.support.task._enum.MessageType;

/**
 * @Description error消息条件
 * @author 
 * @date 
 * @version 1.0
 */
public class ErrorsMessagePredicate extends BaseTaskPredicate {

	@Override
	public boolean evaluate(Object object) {
		String messageType = this.toMessageTaskContext(object).getMessageType();
		return MessageType.ERRORS_TYPE.getCode().equals(messageType);
	}

}
