package com.chhaileng.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chhaileng.model.Message;
import com.chhaileng.service.MessageService;

@RestController
public class MessageRestController {

	@Autowired
	private MessageService messageService;
	
	@GetMapping("/api/messages")
	public List<Message> findAll() {
		return messageService.findAll();
	}
	
}
