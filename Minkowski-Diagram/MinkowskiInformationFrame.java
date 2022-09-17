/*
 * Author: Mukerrem Tufekcioglu
 * File: MinkowskiInformationFrame.java
 * ---------------------------
 * This program allows the user to get specific information about 
 * objects and events in primed and unprimed frames.
 */

import java.awt.*;
import javax.swing.*;

public class MinkowskiInformationFrame extends JFrame {
	
	private static int numComponents = 0;
	
	public MinkowskiInformationFrame() {
		
		super.setTitle("Information");
		
		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);
		
		panel = addEvents(panel);
		panel = addObjects(panel);
		panel = addSignals(panel);
		
		this.add(panel);
		this.setSize(1100, 500);
		this.setVisible(true);
		
		numComponents = 0;
		
	}
	
	private JPanel addEvents(JPanel panel) {
		
		int numEvents = MinkowskiDatabase.getEventList().size();
		if (numEvents == 0) {return panel;}
		
		JLabel eventsLabel = new JLabel("Events");
		panel.add(eventsLabel);
		numComponents++;
		
		for (int i = 0; i < numEvents; i++) {
			
			MinkowskiEvent event = MinkowskiDatabase.getEventList().get(i);
			String name = event.getName();
			String color = event.getColorString();
			double primedTime = event.getTranslatedTime();
			double primedPosition = event.getTranslatedPosition();
			double unprimedTime = event.getOwnTime();
			double unprimedPosition = event.getOwnPosition();
			
			String text = name + " (" + color + ") ->  Primed frame: T = " + primedTime + ", P = " + primedPosition
					+ "  --  Unprimed frame: T = " + unprimedTime + ", P = " + unprimedPosition;
			
			JLabel label = new JLabel(text);
			panel.add(label);
			numComponents++;
			
		}
		
		return panel;
		
	}
	
	private JPanel addObjects(JPanel panel) {
		
		int numObjects = MinkowskiDatabase.getObjectList().size();
		if (numObjects == 0) {return panel;}
		
		JLabel objectsLabel = new JLabel("Objects");
		panel.add(objectsLabel);
		numComponents++;
		
		for (int i = 0; i < numObjects; i++) {
			
			MinkowskiObject object = MinkowskiDatabase.getObjectList().get(i);
			String name = object.getName();
			String color = object.getColor();
			double primedLength = object.getTranslatedLength();
			double primedVelocity = object.getTranslatedVelocity();
			double primedLeftPositionAtZero = object.getTranslatedLeftPositionAtZero();
			double unprimedLength = object.getRestLength();
			double unprimedVelocity = object.getOwnVelocity();
			double unprimedLeftPositionAtZero = object.getOwnLeftPositionAtZero();
			
			String text = name + " (" + color + ") ->  Primed frame: L = " + primedLength + ", V = " + primedVelocity
					+ ", P = " + primedLeftPositionAtZero
					+ "  --  Unprimed frame: L = " + unprimedLength + ", V = " + unprimedVelocity
					+ ", P = " + unprimedLeftPositionAtZero;
			
			JLabel label = new JLabel(text);
			panel.add(label);
			numComponents++;
			
		}
		
		return panel;
		
	}
	
	private JPanel addSignals(JPanel panel) {
		
		int numSignals = MinkowskiDatabase.getSignalList().size();
		if (numSignals == 0) {return panel;}
		
		JLabel signalsLabel = new JLabel("Signals");
		panel.add(signalsLabel);
		numComponents++;
		
		for (int i = 0; i < numSignals; i++) {
			
			MinkowskiSignal signal = MinkowskiDatabase.getSignalList().get(i);
			String name = signal.getName();
			double primedTime = signal.getTranslatedTime();
			double primedPosition = signal.getTranslatedPosition();
			double unprimedTime = signal.getOwnTime();
			double unprimedPosition = signal.getOwnPosition();
			
			String text = name + " ->  Primed frame: T = " + primedTime + ", P = " + primedPosition
					+ "  --  Unprimed frame: T = " + unprimedTime + ", P = " + unprimedPosition;
			
			JLabel label = new JLabel(text);
			panel.add(label);
			numComponents++;
			
		}
		
		return panel;
		
	}
	
}