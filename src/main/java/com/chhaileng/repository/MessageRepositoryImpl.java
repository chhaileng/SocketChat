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
		return messages.add(message);
	}

	public void remove() {
		int count = messages.size();
		if (count <= 25) {
			return;
		}
		messages.remove(0);
		remove();
	}

	@Override
	public List<Message> findAll() {
		remove();
		return messages;
	}
	
}
