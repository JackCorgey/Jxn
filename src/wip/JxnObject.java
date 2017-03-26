package wip;
import java.util.HashMap;
import java.util.Set;

public class JxnObject {
	
	public enum Type {
		Object, Numeric, String, Array;
	}
	
	private String val;
	Type type;
	private HashMap<String, JxnObject> dict = null;
	
	public JxnObject(String _val, Type _type) {
		val = _val;
		if(Type.Object == (type = _type) ) {
			dict = new HashMap<String,JxnObject>();
		}
	}
	
	/* 
	 * adds a jxDict object to HashMap dict
	 */
	public JxnObject add(String key, JxnObject jxDict) {
		if(dict == null) {
			String msg = String.format("Cannot add Object to type %s", type);
			throw new RuntimeException(msg);
		}
		
		if( !dict.containsKey(key) ) {
			dict.put(key, jxDict);
		}
		
		return jxDict;
			
	}
	
	public JxnObject get(String key) {
		if(dict != null) {
			if( dict.containsKey(key) ) {
				return dict.get(key);
			}
		}
		return null;
	}
	
	public JxnObject get(String keys, boolean deepFetch) {
		if(deepFetch == false) {
			return get(keys);
		}
		
		JxnObject obj = this; 
		String[] _keys = keys.split("=>");
		for(String key : _keys) {
			obj = obj.get(key);
		}
		
		return obj;
	}
	
	public <T> T value() {
		if(type == Type.Numeric) {
			Integer num = Integer.parseInt(val);
			return (T) num;
		}
	    return (T) val;
	}
	
	public Type type() {
		return type;
	}
	
	public HashMap<String,JxnObject> getDict() {
		return dict;
	}
	
	public void print(JxnObject _dict) {
		for( String key : keySet() ) {
			System.out.println(key);
		}
	}
	
	private String[] keySet() {
		return (String[]) dict.keySet().toArray();
	}

}