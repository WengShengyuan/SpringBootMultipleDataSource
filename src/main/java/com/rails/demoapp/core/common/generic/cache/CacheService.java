package com.rails.demoapp.core.common.generic.cache;

public interface CacheService {

	Object put(String key, Object obj) throws Exception;
	
	Object get(String key) throws Exception;
	
}
