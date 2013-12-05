package com.company.bus.loaned.cache.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.bus.loaned.cache.service.SampleCache;
import com.company.bus.loaned.common.comm.ECache;
import com.company.bus.loaned.dbaccess.dao.SampleDao;
import com.company.bus.loaned.dbaccess.entity.SampleAccount;

/**
 * @Description 样例缓存实现
 * @author 
 * @date 2013-4-10
 * @version 1.0
 */
@Component
public class SampleCacheImpl implements SampleCache {

	@Autowired
	private SampleDao sampleDao;
	
	@Override
	public SampleAccount selectSample(String id) {
		// 获取缓存Sample
		SampleAccount sample = ECache.CACHE_SAMPLE.getCacheEntity(id);
		if(sample == null){
			sample = sampleDao.accQueryById(id);
			if(sample != null){
				ECache.CACHE_SAMPLE.putCacheEntity(id, sample);
			}	
		}else{
			log.debug("Get result from cache with key : {}", new Object[]{id});
		}
		return sample;
	}
	
	private transient Logger log = LoggerFactory.getLogger(this.getClass());
}
