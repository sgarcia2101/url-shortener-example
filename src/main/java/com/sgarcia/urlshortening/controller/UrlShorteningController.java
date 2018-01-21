package com.sgarcia.urlshortening.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sgarcia.urlshortening.model.dto.UrlDTO;

@Controller
public class UrlShorteningController {

	private static final Logger log = LoggerFactory.getLogger(UrlShorteningController.class);

	@GetMapping("/")
	public String get(Model model) {
		model.addAttribute("urlDto", new UrlDTO());
		return "index";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("urlDto") UrlDTO urlDto, Model model) {
		log.info("form received");
		model.addAttribute("shortedUrl", "example url");
		return "result";
	}

	@GetMapping("/{code}")
	public String get(@PathVariable String code) {
		return code;
	}
}
