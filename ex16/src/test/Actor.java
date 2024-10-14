package test;

import java.util.concurrent.ConcurrentHashMap;

public abstract class Actor {

	private static ConcurrentHashMap<Integer, Actor> map=new ConcurrentHashMap<>();	

	public static Actor get(int id) {
		return map.get(id);
	}
	
	
	public Actor(int id) {
		map.put(id, this);		
	}
	
	abstract void addMessage(String msg);
	
	abstract void close();
}
