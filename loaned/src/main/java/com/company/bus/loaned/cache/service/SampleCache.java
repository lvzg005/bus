package com.company.bus.loaned.cache.service;

import com.company.bus.loaned.dbaccess.entity.SampleAccount;

/**
 * @Description 样例缓存
 * @author 
 * @date 
 * @version 1.0
 */
public interface SampleCache {
	SampleAccount selectSample(String id);
}
