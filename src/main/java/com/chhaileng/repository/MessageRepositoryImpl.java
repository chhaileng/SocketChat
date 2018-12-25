package com.chhaileng.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.chhaileng.model.Message;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

	private List<Message> messages = new ArrayList<>();
	
	@Override
	public boolean add(Message message) {
		clearOldMessagesIfMoreThan(25);
		return messages.add(message);
	}

	public void clearOldMessagesIfMoreThan(int limit) {
		int count = messages.size();
		if (count <= limit-1) {
			return;
		}
		messages.remove(0);
		clearOldMessagesIfMoreThan(limit);
	}

	@Override
	public List<Message> findAll() {
		return messages;
	}
	
}
