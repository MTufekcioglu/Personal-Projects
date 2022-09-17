/*
 * Author: Mukerrem Tufekcioglu
 * File: MinkowskiDatabase.java
 * ---------------------------
 * This class handles the creation, modification  
 * and destruction of events and objects.
 */

import java.awt.*;
import java.util.ArrayList;

public class MinkowskiDatabase {
	
	private static ArrayList<MinkowskiEvent> events = new ArrayList<MinkowskiEvent>();
	private static ArrayList<MinkowskiObject> objects = new ArrayList<MinkowskiObject>();
	private static ArrayList<MinkowskiSignal> signals = new ArrayList<MinkowskiSignal>();
	
	public static void newEvent(double time, double position, Color color, String name) 
	{
		double frameVelocity = MinkowskiGraph.getFrameVelocity();
		double leftPositionOfObjectOfTranslationAtZero = MinkowskiGraph.getLeftPositionOfObjectOfTranslation();
		MinkowskiEvent event = new MinkowskiEvent(position, time, color, name, 
				frameVelocity, leftPositionOfObjectOfTranslationAtZero);
		events.add(event);	
		System.out.println("Event _" + name + "_ created");
	}
	
	public static void newObject(double length, double leftPositionAtZero, double velocity, Color color, String name) 
	{	
		double frameVelocity = MinkowskiGraph.getFrameVelocity();
		double leftPositionOfObjectOfTranslationAtZero = MinkowskiGraph.getLeftPositionOfObjectOfTranslation();
		MinkowskiObject object = new MinkowskiObject(length, leftPositionAtZero, velocity, color, name, 
				frameVelocity, leftPositionOfObjectOfTranslationAtZero);
		objects.add(object);	
		System.out.println("Object _" + name + "_ created");
	}
	
	public static void newSignal (double time, double position, String name) 
	{
		double frameVelocity = MinkowskiGraph.getFrameVelocity();
		double leftPositionOfObjectOfTranslationAtZero = MinkowskiGraph.getLeftPositionOfObjectOfTranslation();
		MinkowskiSignal signal = new MinkowskiSignal(time, position, name, 
				frameVelocity, leftPositionOfObjectOfTranslationAtZero);
		signals.add(signal);
		System.out.println("Signal _" + name + "_ created at x = _" + position + "_ and y = _" + time + "_" );
	}
	
	public static ArrayList<MinkowskiEvent> getEventList() 
	{	
		return events;	
	}
	
	public static ArrayList<MinkowskiObject> getObjectList()
	{
		return objects;
	}
	
	public static ArrayList<MinkowskiSignal> getSignalList()
	{
		return signals;
	}
	
	public static String[] objectStringList() {
		
		int length = objects.size();
		String objectArray[] = new String[length];
		
		for (int i = 0; i < length; i++) {
			
			objectArray[i] = MinkowskiObject.getName(objects.get(i));
			
		}
		
		return objectArray;
		
	}
	
	public static String[] eventStringList() {
		
		int length = events.size();
		String eventArray[] = new String[length];
		
		for (int i = 0; i < length; i++) {
			
			eventArray[i] = events.get(i).getName();
			
		}
		
		return eventArray;
		
	}
	
	public static String[] signalStringList() {
		
		int length = signals.size();
		String signalArray[] = new String[length];
		
		for (int i = 0; i < length; i++) {
			
			signalArray[i] = signals.get(i).getName();
			
		}
		
		return signalArray;
		
	}
	
	public static void removeObject(int index) {
		
		Minkowski.graph.removeObject(index);
		objects.remove(index);
		
	}
	
	public static void removeAllObjects() {
		
		while (objects.size() != 0) {
			
			Minkowski.graph.removeObject(0);
			objects.remove(0);
			
		}
		
	}
	
	public static void removeEvent(int index) {
		
		Minkowski.graph.removeEvent(index);
		events.remove(index);
		
	}
	
	public static void removeAllEvents() {
		
		while (events.size() != 0) {
			
			Minkowski.graph.removeEvent(0);
			events.remove(0);
			
		}
		
	}
	
	public static void removeSignal(int index) {
		
		Minkowski.graph.removeSignal(index);
		signals.remove(index);
		
	}
	
	public static void removeAllSignals() {
		
		while (signals.size() != 0) {
			
			Minkowski.graph.removeSignal(0);
			signals.remove(0);
			
		}
		
	}
	
}






