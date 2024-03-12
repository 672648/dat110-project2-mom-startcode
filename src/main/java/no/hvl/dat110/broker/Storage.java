package no.hvl.dat110.broker;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import no.hvl.dat110.common.TODO;
import no.hvl.dat110.common.Logger;
import no.hvl.dat110.messagetransport.Connection;

public class Storage {

	// data structure for managing subscriptions
	// maps from a topic to set of subscribed users
	protected ConcurrentHashMap<String, Set<String>> subscriptions;
	
	// data structure for managing currently connected clients
	// maps from user to corresponding client session object
	
	protected ConcurrentHashMap<String, ClientSession> clients;

	public Storage() {
		subscriptions = new ConcurrentHashMap<String, Set<String>>();
		clients = new ConcurrentHashMap<String, ClientSession>();
	}

	public Collection<ClientSession> getSessions() {
		return clients.values();
	}

	public Set<String> getTopics() {

		return subscriptions.keySet();

	}

	// get the session object for a given user
	// session object can be used to send a message to the user
	
	public ClientSession getSession(String user) {

		ClientSession session = clients.get(user);

		return session;
	}

	public Set<String> getSubscribers(String topic) {

		return (subscriptions.get(topic));

	}

	public void addClientSession(String user, Connection connection) {

		// TODO: add corresponding client session to the storage
		// See ClientSession class
		
		ClientSession clientSession = new ClientSession(user, connection);
		clients.put(user, clientSession);
		
		//throw new UnsupportedOperationException(TODO.method());
		
	}

	public void removeClientSession(String user) {

		// TODO: disconnet the client (user) 
		// and remove client session for user from the storage
		ClientSession session = clients.get(user);
		session.disconnect();
		clients.remove(user);
		
		//throw new UnsupportedOperationException(TODO.method());
		
	}

	public void createTopic(String topic) {

		// TODO: create topic in the storage
		if(subscriptions.containsKey(topic)) {
			System.out.println("Topic eksisterer allerede");
			return;
		}
		subscriptions.put(topic, ConcurrentHashMap.newKeySet());
		
		//throw new UnsupportedOperationException(TODO.method());
	
	}

	public void deleteTopic(String topic) {

		// TODO: delete topic from the storage
		if(subscriptions.containsKey(topic)) {
			subscriptions.remove(topic);
		}

		//throw new UnsupportedOperationException(TODO.method());
		
	}

	public void addSubscriber(String user, String topic) {

		// TODO: add the user as subscriber to the topic
		
		if(subscriptions.containsKey(topic)) {
			Set<String> subscribers = subscriptions.get(topic);
			subscribers.add(user);
		}
		
		else {
	        // If the topic doesn't exist, create a new set of subscribers and add the user
	        Set<String> subscribers = new HashSet<>();
	        subscribers.add(user);
	        subscriptions.put(topic, subscribers);
	    }
		
		//throw new UnsupportedOperationException(TODO.method());
		
	}

	public void removeSubscriber(String user, String topic) {

		// TODO: remove the user as subscriber to the topic
		
		if(subscriptions.containsKey(topic) && subscriptions.get(topic).contains(user)) {
			Set<String> subscribers = subscriptions.get(topic);
			subscribers.remove(user);
		}

		//throw new UnsupportedOperationException(TODO.method());
	}
}
