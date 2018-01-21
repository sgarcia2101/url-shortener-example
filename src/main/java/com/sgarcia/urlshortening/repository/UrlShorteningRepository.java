package com.sgarcia.urlshortening.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class UrlShorteningRepository {

	@Autowired
	private StringRedisTemplate template;

	public void save(String key, String url, Integer expirationTime) {
		ValueOperations<String, String> ops = this.template.opsForValue();
		if (!this.template.hasKey(key)) {
			ops.set(key, url);
		}
	}

	public String get(String key) {
		ValueOperations<String, String> ops = this.template.opsForValue();
		if (this.template.hasKey(key)) {
			return ops.get(key);
		}
		return null;
	}
}
