package com.sgarcia.urlshortening.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sgarcia.urlshortening.model.dto.UrlDTO;
import com.sgarcia.urlshortening.service.UrlShorteningService;

@Controller
public class UrlShorteningController {

	private static final Logger log = LoggerFactory.getLogger(UrlShorteningController.class);

	@Autowired
	private UrlShorteningService urlShorteningService;

	@GetMapping("/")
	public String get(Model model) {
		model.addAttribute("urlDto", new UrlDTO());
		return "index";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("urlDto") UrlDTO urlDto, Model model) {
		log.info("form received");
		String shortedUrl = urlShorteningService.save(urlDto);
		model.addAttribute("shortedUrl", shortedUrl);
		return "result";
	}

	@GetMapping("/{key}")
	public String get(@PathVariable String key, Model model) {
		String url = urlShorteningService.get(key);
		if (url == null) {
			model.addAttribute("urlDto", new UrlDTO());
			return "redirect:/";
		}
		return "redirect:" + url;
	}
}
