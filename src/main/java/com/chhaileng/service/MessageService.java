package com.chhaileng.service;

import java.util.List;

import com.chhaileng.model.Message;

public interface MessageService {
	boolean add(Message message);
	List<Message> findAll();
}
