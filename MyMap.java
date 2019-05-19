import java.util.*;
/**
 * Class MyMap uses an ArrayList of LinkedLists(that have a Pair) to mimic a HashTable. 
 * Using the HashTable, class MyMap has inner class Pair that acts as the <key, value> pair.
 * @author Mark Goldstein
 */
public class MyMap {

	//Class MyMap's data members
	private ArrayList<LinkedList<Pair>> theTable;
	private int size ;
	private final int LIMIT = 1000;
	//Constructor that initialized the arraylist and adds a new link given the max size.
	public MyMap() {
		theTable = new ArrayList<>();
		for (int i = 0; i < LIMIT; i++) {
			theTable.add(new LinkedList<>());
		}
		size = 0;
	}

	/**
	 * Inner class Pair acts as the Key,Value pair-object for the table to store in the map's arraylist.
	 * @author Mark
	 */
	public static class Pair{

		private String key;
		private int value;

		//Constructor, simply setting the Pair's values via passed key and value.
		public Pair(String key, int val) {
			this.key = key;
			this.value = val;
		}

		//Returns the private key
		String getKey() {
			return this.key;
		}
		//Returns the private  value
		Integer getValue() {
			return this.value;
		}
		/**
		 * Method setValue(Integer value) reassigns the value of the given pair object.
		 * @param value
		 * @return the old value that was overridden.
		 */
		Integer setValue(Integer value) {
			Integer temp = value;
			this.value = value;
			return temp;// returning the deleted value
		}
	}

	/**
	 * Method put(String key, Integer value) inserts the given key-value pair into the 
	 *  map given that: If key already exists in the map, the current value is overridden
	 *  by the newly passed value. Otherwise, given the key is not already in use, 
	 *  insert the passed key-value pair into the map.
	 * @param key
	 * @param value
	 */
	void put (String key, Integer value) {
		if(theTable.size() <1)
			return;
		int bucket = keyHashCode(key) % LIMIT;
		if(containsKey(key)){

			for (Pair p : theTable.get(bucket)) {
				if(p.getKey().equals(key))
					p.setValue(value);
			}
		}else{
			if(theTable.get(bucket) == null)
				theTable.add(bucket,new LinkedList<Pair>());
			theTable.get(bucket).add(new Pair(key, value));
			size++;
		}
	}

	/**
	 * Method containsKey(String key) takes a key and goes through the table, checking
	 * if the given key is found. If the key exists, returns true. Otherwise, returns false.
	 * @param key
	 * @return flag weather the map contains the given key
	 */
	boolean containsKey(String key) {
		if(theTable.size() <1)
			return false;
		int bucket = keyHashCode(key) %LIMIT;
		if(theTable.size() > bucket) {
			// We iterate though the bucket that has a linked list of pairs.
			for(Pair p : theTable.get(bucket)) {
				if(p.getKey().equals(key))
					return true;
			}
		}
		return false;
	}
	/**
	 * Method keyHashCode(String key) generates a hashCode given a string as a key.
	 * @param key
	 * @return and integer indicating the hashCode(prehash).
	 */
	public int keyHashCode(String key) {
		int code = 0;
		for (int i = 0; i <key.length(); i++) {
			code += key.charAt(i);
		}
		if(code <=0) // Handles the case of the empty string
			return - (code -'a');
		return code -'a' ;
	}

	/**
	 * Method containsValue()  goes the the LinkedList of Pairs in our table
	 * and returns true if, and only if value already exists in the map.
	 * @param value
	 * @return flag indicating if the table has a value.
	 */

	boolean containsValue(Integer value){
		// We iterate though the bucket that has a linked list of pairs.
		for (LinkedList<Pair> link : theTable) {
			for (Pair p : link) {
				if(p.getValue() == value)
					return true;
			}
		}
		return false;
	}

	/**
	 * Method remove( Object key) returns a flag indicating weather a key is in the map,
	 *  and removes it if the key is found. Otherwise the method returns false.
	 * @param key
	 * @return boolean indicating if the object was successfully removed.
	 */
	boolean remove (Object key){
		if(key instanceof String) {
			String temp = (String) key;
			if(containsKey(temp)) {
				int bucket = keyHashCode(temp) %LIMIT;
				for (Pair p : theTable.get(bucket)) {
					if(p.getKey().equals(temp))
						theTable.get(bucket).remove(p);
					size--;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Method get(String key) takes a key of the map and returns the associated value.
	 * Checks the bounds of the table, finds the key by using the bucket that would be
	 * assigned if the key was successfully added.
	 * If the key does not exist, returns null.
	 * @param key
	 * @return
	 */
	Integer get(String key){
		if(theTable.size() <0)
			return -1;
		int bucket = keyHashCode(key) %LIMIT;
		String temp = (String) key;
		if(containsKey(temp)) {
			for(Pair p : theTable.get(bucket)) {
				if(p.getKey().equals(key))
					return p.getValue();
			}
		}
		return null;
	}

	/**
	 * Method entrySet() makes a HashSet of Pairs from the Linked list on our table.
	 * 
	 * @return a set of all the pairs in the map.
	 */
	Set<Pair> entrySet(){
		HashSet<Pair> set = new HashSet<>();
		for (LinkedList<Pair> link : theTable) {
			for (Pair p : link) {
				set.add(p);
			}
		}
		return set;
	}

	/**
	 * Method values()  goes though the LinkList of pairs in the table and gets the values.
	 * @return a list (which is a collection), in this case of Integers, of the associated values.
	 */
	Collection<Integer> values(){
		ArrayList<Integer> list = new ArrayList<>();
		for (LinkedList<Pair> link : theTable) {
			for (Pair p : link) {
				list.add(p.getValue());
			}
		}
		return list;
	}

	/**
	 * Method keySet()  goes though the LinkList of pairs in the table and gets the keys.
	 * @return a set , in this case of Strings, of the associated keys.
	 */
	Set<String> keySet(){
		HashSet<String> set = new HashSet<>();
		for (LinkedList<Pair> link : theTable) {
			for (Pair p : link) {
				set.add(p.getKey());
			}
		}
		return set;	
	}
	int size(){
		return size;
	}
	//Uses boolean resolution to check if the map is empty.
	boolean isEmpty(){
		return size == 0;
	}
}
