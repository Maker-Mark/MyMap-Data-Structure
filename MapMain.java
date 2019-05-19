
import java.util.*;
/**
 * Class MapMain demonstrates the usage of MyMap, a class that mimics the data structure based
 * off of a hash table. 
 * @author Mark Goldstein, class MyMap developed with help of Matthew Yarmolinsky.
 *
 */
public class MapMain {

	public static void main(String[] args) {
		MyMap map = new MyMap();
		System.out.println("Is the map empty? " + map.isEmpty());
		//Demonstrating the putting of strings as keys and integers as values.

		map.put("apple", 12);
		map.put("curve", 3);
		map.put("passing",7);
		map.put("sithlord", 96);

		//Using the entrySet to get a entry of a Pair. 
		//Each Pair has a key and a value.
		System.out.println("Current Map:");
		System.out.println("______________");
		for (MyMap.Pair p: map.entrySet()) 
			System.out.println(p.getKey()+" = " + p.getValue());
		System.out.println("______________");

		System.out.println("Is the map empty? " + map.isEmpty());
		System.out.println("Key \"apple\" currently has the value of: "+ map.get("apple"));
		System.out.println("Removing \"apple\" was successful? "+ map.remove("apple"));
		System.out.println("Can we remove \"apple \" again? "+map.remove("apple"));
		System.out.println("The value of \"passing\" via the map.get:" +map.get("passing"));
		map.put("apple", 13);
		System.out.println("Now,  \"apple\" has a value of "+ map.get("apple"));
		System.out.println("Is the map empty? " +map.isEmpty());
		System.out.println("The map is " + map.size() + " elements large.");

		map.put("pedantic", 9);
		map.put("apricot", 99);
		System.out.println("Using the .values method:");
		System.out.println("______________");
		for (Integer i : map.values()) 
			System.out.println(i);
		System.out.println("______________");
		System.out.println("Demo of the keyset being used alone:");
		System.out.println("______________");
		for (String str: map.keySet()) 
			System.out.println(str);
		System.out.println("______________");
		System.out.println();
		System.out.println(map.containsKey("apple"));
		System.out.println(map.containsKey("poll"));
		System.out.println("Does the map contain the value 2? " +map.containsValue(2));
		System.out.println("How about 13?(should be true) " +map.containsValue(13));

		System.out.println("Final Map:");
		System.out.println("______________");
		for (MyMap.Pair p: map.entrySet()) 
			System.out.println(p.getKey()+" = " + p.getValue());
		System.out.println("______________");

	}

}
