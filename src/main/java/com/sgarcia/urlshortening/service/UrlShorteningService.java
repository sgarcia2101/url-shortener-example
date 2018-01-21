package com.sgarcia.urlshortening.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgarcia.urlshortening.model.dto.UrlDTO;
import com.sgarcia.urlshortening.repository.UrlShorteningRepository;

@Service
public class UrlShorteningService {

	private static final int DEFAULT_EXPIRATION_TIME = 30;

	@Autowired
	UrlShorteningRepository urlShorteningRepository;

	public String save(UrlDTO urlDto) {
		// Get alias
		String alias = urlDto.getAlias() != null && !urlDto.getAlias().isEmpty() ? urlDto.getAlias() : UUID.randomUUID().toString();

		// Get expiration time
		Integer expirationTime = urlDto.getExpirationTime() != null ? urlDto.getExpirationTime() : DEFAULT_EXPIRATION_TIME;

		// Save url info
		urlShorteningRepository.save(alias, urlDto.getUrl(), expirationTime);
		
		return alias;
	}

	public String get(String key) {
		// Return value retrieved by key
		return urlShorteningRepository.get(key);
	}
}
