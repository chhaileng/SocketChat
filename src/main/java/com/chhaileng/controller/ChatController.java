package com.chhaileng.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chhaileng.model.Message;
import com.chhaileng.model.User;
import com.chhaileng.service.MessageService;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

@Component
public class ChatController {

	private SocketIONamespace namespace;
	private Map<SocketIOClient, String> users = new HashMap<>();
	
	public SocketIONamespace getNamespace() {
		return namespace;
	}
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	public ChatController(SocketIOServer server) {
		this.namespace = server.addNamespace("/chat");
		
		this.namespace.addConnectListener(onConnectListener);
		this.namespace.addDisconnectListener(onDisconnectListener);
		
		this.namespace.addEventListener("userJoin", User.class, onUserJoinChat);
		this.namespace.addEventListener("sendMessage", Message.class, onUserSendMessage);
		this.namespace.addEventListener("userTyping", User.class, onUserTyping);
		this.namespace.addEventListener("userStopTyping", User.class, onUserStopTyping);
	}
	
	public ConnectListener onConnectListener = new ConnectListener() {	
		@Override
		public void onConnect(SocketIOClient client) {
//			System.out.println("Client " + client.getSessionId() + " connected to /chat namespace.");
		}
	};
	
	public DisconnectListener onDisconnectListener = new DisconnectListener() {	
		@Override
		public void onDisconnect(SocketIOClient client) {
//			System.out.println("Client " + client.getSessionId() + " disconnected from /chat namespace.");
			namespace.getBroadcastOperations().sendEvent("userLeft", users.get(client));
			users.remove(client);
			namespace.getBroadcastOperations().sendEvent("count", users.size());
		}
	};
	
	public DataListener<User> onUserJoinChat = new DataListener<User>() {
		@Override
		public void onData(SocketIOClient client, User user, AckRequest ackSender) throws Exception {
			namespace.getBroadcastOperations().sendEvent("newUser", user);
			users.put(client, user.getUsername());
			namespace.getBroadcastOperations().sendEvent("count", users.size());
		}
	};
	
	public DataListener<Message> onUserSendMessage = new DataListener<Message>() {
		@Override
		public void onData(SocketIOClient client, Message message, AckRequest arg2) throws Exception {
			namespace.getBroadcastOperations().sendEvent("newMessage", client, message);
			messageService.add(message);
		}
	};
	
	public DataListener<User> onUserTyping = new DataListener<User>() {
		@Override
		public void onData(SocketIOClient client, User user, AckRequest arg2) throws Exception {
			namespace.getBroadcastOperations().sendEvent("userTyping", client, user);
		}
	};
	
	public DataListener<User> onUserStopTyping = new DataListener<User>() {
		@Override
		public void onData(SocketIOClient client, User user, AckRequest arg2) throws Exception {
			namespace.getBroadcastOperations().sendEvent("userStopTyping", client, user);
		}
	};
}
