package com.chhaileng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chhaileng.model.Message;
import com.chhaileng.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Override
	public boolean add(Message message) {
		return messageRepository.add(message);
	}

	@Override
	public List<Message> findAll() {
		return messageRepository.findAll();
	}

}
