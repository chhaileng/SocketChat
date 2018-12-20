package com.chhaileng.configuration;

import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Bean;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

@org.springframework.context.annotation.Configuration
public class SocketIOServerConfiguration {

	private static final String SOCKET_IO_HOST = "0.0.0.0";
	private static final int SOCKET_IO_PORT = 1111;
	
	private SocketIOServer server;
	
	@Bean
	public SocketIOServer socketIOServer() {
		Configuration config = new Configuration();
		config.setHostname(SOCKET_IO_HOST);
		config.setPort(SOCKET_IO_PORT);
		
		server = new SocketIOServer(config);
		server.start();
		
		server.addConnectListener(new ConnectListener() {
			@Override
			public void onConnect(SocketIOClient client) {
				System.out.println("Client connected: " + client.getSessionId());
			}
		});
		
		server.addDisconnectListener(new DisconnectListener() {
			@Override
			public void onDisconnect(SocketIOClient client) {
				System.out.println("Client disconnected: " + client.getSessionId());
			}
		});
		
		return server;
	}
	
	@PreDestroy
	public void stopSocketIOServer() {
		this.server.stop();
	}
	
}
