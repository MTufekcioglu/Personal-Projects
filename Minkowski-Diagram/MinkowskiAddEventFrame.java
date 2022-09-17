/*
 * Author: Mukerrem Tufekcioglu
 * File: MinkowskiAddFrame.java
 * ---------------------------
 * This program implements the popup window that promps the
 * user to enter values for the events that are going to be added.
 */

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.lang.reflect.Field;

public class MinkowskiAddEventFrame extends JFrame {
	
	private static final int TEXT_FIELD_WIDTH = 16;
	
	public MinkowskiAddEventFrame() {
		
		super.setTitle("Add Event");
		JLabel addTimeLabel = new JLabel("Time:");
		JLabel addPositionLabel = new JLabel("Position:");
		JTextField timeField = new JTextField(TEXT_FIELD_WIDTH);
		JTextField positionField = new JTextField(TEXT_FIELD_WIDTH);
		JButton addEvent = new JButton("Add Event");
		
		String colors[]={"BLUE","GREEN","RED"};
		JComboBox colorList = new JComboBox(colors);
		
		JLabel addNameLabel = new JLabel("Name: ");
		JTextField nameField = new JTextField(TEXT_FIELD_WIDTH);
		
		addEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		String timeString = timeField.getText();
            		String positionString = positionField.getText();
            		String colorString = "" + colorList.getItemAt(colorList.getSelectedIndex());
            		String nameString = nameField.getText();
            		
            		if (formatIsGood(timeString, positionString)) {
            			
            			Color color;
            			
            			try {
                			Field f = Color.class.getField(colorString);
                			color = (Color)f.get(null);
                		} catch (Exception ce) {
                			color = null;
                		}
            			
            			double time = Double.parseDouble(timeString);
            			double position = Double.parseDouble(positionString);
            			String name = nameString;
            			
            			timeField.setText("");
                		positionField.setText("");
                		
                		MinkowskiDatabase.newEvent(time, position, color, name);
                		Minkowski.graph.update();
                		dispose();
            		}
            }
        });
		
		JPanel panel = new JPanel();
		panel.add(addTimeLabel);
		panel.add(timeField);
		panel.add(addPositionLabel);
		panel.add(positionField);
		panel.add(colorList);
		panel.add(addNameLabel);
		panel.add(nameField);
		panel.add(addEvent);
		
		this.add(panel);
		this.setSize(1000, 60);
		this.setVisible(true);
		
	}
	
	private boolean formatIsGood(String time, String position){ 
		
		return checkTime(time) && checkPosition(position);
		
	}
	
	private boolean checkTime(String time) {
		
		Double num = 0.0;
		
		try {
            num = Double.parseDouble(time);
        } catch (NumberFormatException e) {
        		System.out.println(e);
            return false;
        } catch (NullPointerException e) {
        		System.out.println(e);
        		return false;
        }
		
		if (num > 10 || num < -10) {
			return false;
		}
		
		return true;
		
	}

	private boolean checkPosition(String position) {
		
		Double num = 0.0;
		
		try {
            num = Double.parseDouble(position);
        } catch (NumberFormatException e) {
        		System.out.println(e);
            return false;
        } catch (NullPointerException e) {
        		System.out.println(e);
    			return false;
        }
		
		if (num > 10 || num < -10) {
			return false;
		}
		
		return true;
		
	}
	
}