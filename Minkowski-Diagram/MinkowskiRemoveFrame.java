/*
 * Author: Mukerrem Tufekcioglu
 * File: MinkowskiRemoveFrame.java
 * ---------------------------
 * This program implements the popup window that promps the
 * user to choose objects or events that they want to remove.
 */

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MinkowskiRemoveFrame extends JFrame {
	
	public MinkowskiRemoveFrame() {
		
		super.setTitle("Remove");
		
		String[] objectNameArray = MinkowskiDatabase.objectStringList();
		System.out.println(objectNameArray.length);
		JComboBox objectList = new JComboBox(objectNameArray);
		JButton removeObjectButton = new JButton("Remove Object");
		JButton removeAllObjectsButton = new JButton("Remove All Objects");
		
		removeObjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = objectList.getItemAt(objectList.getSelectedIndex());
				if (objectRemovalPossible(obj)) {
					System.out.println("The object _" + obj + "_ was chosen.");
					ArrayList<MinkowskiObject> objectList = MinkowskiDatabase.getObjectList();
					int length = objectList.size();
					for (int i = 0; i < length; i++) {
						String objectName = MinkowskiObject.getName(objectList.get(i));
						if (objectName == obj) {
							MinkowskiDatabase.removeObject(i);
							dispose();
						}
					}
				} else {
					System.out.println("No object chosen");
				}
			}
		});
		
		removeAllObjectsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MinkowskiDatabase.getObjectList().size() != 0) {
					MinkowskiDatabase.removeAllObjects();
					dispose();
				}
			}
		});
		
		String[] eventNameArray = MinkowskiDatabase.eventStringList();
		System.out.println(eventNameArray.length);
		JComboBox eventList = new JComboBox(eventNameArray);
		JButton removeEventButton = new JButton("Remove Event");
		JButton removeAllEventsButton = new JButton("Remove All Events");
		
		removeEventButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object event = eventList.getItemAt(eventList.getSelectedIndex());
				if (eventRemovalPossible(event)) {
					System.out.println("The event _" + event + "_ was chosen.");
					ArrayList<MinkowskiEvent> eventList = MinkowskiDatabase.getEventList();
					int length = eventList.size();
					for (int i = 0; i < length; i++) {
						String eventName = eventList.get(i).getName();
						if (eventName == event) {
							MinkowskiDatabase.removeEvent(i);
							dispose();
						}
					}
				} else {
					System.out.println("No object chosen");
				}
			}
		});
		
		removeAllEventsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MinkowskiDatabase.getEventList().size() != 0) {
					MinkowskiDatabase.removeAllEvents();
					dispose();
				}
			}
		});
		
		String[] signalNameArray = MinkowskiDatabase.signalStringList();
		System.out.println(signalNameArray.length);
		JComboBox signalList = new JComboBox(signalNameArray);
		JButton removeSignalButton = new JButton("Remove Signal");
		JButton removeAllSignalsButton = new JButton("Remove All Signals");
		
		removeSignalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object signal = signalList.getItemAt(signalList.getSelectedIndex());
				if (signalRemovalPossible(signal)) {
					System.out.println("The signal _" + signal + "_ was chosen.");
					ArrayList<MinkowskiSignal> signalList = MinkowskiDatabase.getSignalList();
					int length = signalList.size();
					for (int i = 0; i < length; i++) {
						String signalName = signalList.get(i).getName();
						if (signalName == signal) {
							MinkowskiDatabase.removeSignal(i);
							dispose();
						}
					}
				} else {
					System.out.println("No signal chosen");
				}
			}
		});
		
		removeAllSignalsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MinkowskiDatabase.getSignalList().size() != 0) {
					MinkowskiDatabase.removeAllSignals();
					dispose();
				}
			}
		});
		
		JPanel panel = new JPanel();
		
		panel.add(objectList);
		panel.add(removeObjectButton);
		panel.add(removeAllObjectsButton);
		panel.add(eventList);
		panel.add(removeEventButton);
		panel.add(removeAllEventsButton);
		panel.add(signalList);
		panel.add(removeSignalButton);
		panel.add(removeAllSignalsButton);
		this.add(panel);
		this.setSize(1500, 60);
		this.setVisible(true);
		
	}
	
	private boolean objectRemovalPossible(Object obj) {
		
		return obj != null;
	
	}
	
	private boolean eventRemovalPossible(Object event) {
		
		return event != null;
		
	}
	
	private boolean signalRemovalPossible(Object signal) {
		
		return signal != null;
		
	}
	
}




