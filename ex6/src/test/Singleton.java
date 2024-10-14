package test;


import java.util.concurrent.ConcurrentHashMap;

public class Singleton {

	public static ConcurrentHashMap<String, Object> holderSingleObject = new ConcurrentHashMap<>();

	public static <V> V getInstance(Class<V> c) throws Exception {
		if (!holderSingleObject.containsKey(c.getName())) {
            synchronized (holderSingleObject)
			{
				if (!holderSingleObject.containsKey(c.getName()))
					holderSingleObject.put(c.getName(),c.newInstance());
			}
		}

		return (V) holderSingleObject.get(c.getName());
	}


}
