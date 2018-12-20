package com.chhaileng.repository;

import java.util.List;

import com.chhaileng.model.Message;

public interface MessageRepository {
	boolean add(Message message);
	List<Message> findAll();
}
