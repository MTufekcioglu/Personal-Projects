/*
 * Author: Mukerrem Tufekcioglu
 * File: MinkowskiAddFrame.java
 * ---------------------------
 * This program implements the popup window that promps the
 * user to enter values needed for the object that are going to be added.
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.lang.reflect.Field;

public class MinkowskiAddObjectFrame extends JFrame {
	
	private static final int TEXT_FIELD_WIDTH = 16;
	
	public MinkowskiAddObjectFrame() {
		
		super.setTitle("Add Object");
		
		JLabel addLengthLabel = new JLabel("Length:");
		JLabel addLeftPositionAtZeroLabel = new JLabel("Left Position at Time Zero:");
		JLabel addVelocityLabel = new JLabel("Velocity:");
		
		JTextField lengthField = new JTextField(TEXT_FIELD_WIDTH);
		JTextField leftPositionAtZeroField = new JTextField(TEXT_FIELD_WIDTH);
		JTextField velocityField = new JTextField(TEXT_FIELD_WIDTH);
		
		String colors[]={"BLUE","GREEN","RED"};
		JComboBox colorList = new JComboBox(colors);
		
		JLabel addNameLabel = new JLabel("Name: ");
		JTextField nameField = new JTextField(TEXT_FIELD_WIDTH);
		
		JButton addObject = new JButton("Add Object");
		
		addObject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		String lengthString = lengthField.getText();
            		String leftPositionAtZeroString = leftPositionAtZeroField.getText();
            		String velocityString = velocityField.getText();
            		String colorString = "" + colorList.getItemAt(colorList.getSelectedIndex());
            		Color color;
            		try {
            			Field f = Color.class.getField(colorString);
            			color = (Color)f.get(null);
            		} catch (Exception ce) {
            			color = null;
            		}
            		String nameString = nameField.getText();
            		
            		if (formatIsGood(lengthString, leftPositionAtZeroString, velocityString)) {
            			double length = Double.parseDouble(lengthString);
                		double leftPositionAtZero = Double.parseDouble(leftPositionAtZeroString);
                		double velocity = Double.parseDouble(velocityString);
                		String name = nameString;
                		lengthField.setText("");
                		leftPositionAtZeroField.setText("");
                		velocityField.setText("");
                		MinkowskiDatabase.newObject(length, leftPositionAtZero, velocity, color, name);
                		Minkowski.graph.update();
                		dispose();
            		}
            		
            		
            }
        });
		
		JPanel panel = new JPanel();
		panel.add(addLengthLabel);
		panel.add(lengthField);
		panel.add(addLeftPositionAtZeroLabel);
		panel.add(leftPositionAtZeroField);
		panel.add(addVelocityLabel);
		panel.add(velocityField);
		panel.add(colorList);
		panel.add(addNameLabel);
		panel.add(nameField);
		panel.add(addObject);
		
		this.add(panel);
		this.setSize(1450, 60);
		this.setVisible(true);
		
	}
	
	private boolean formatIsGood(String length, String leftPositionAtZero, String velocity) {
		
		return checkLength(length) && checkLeftPositionAtZero(leftPositionAtZero) && checkVelocity(velocity);
		
	}
	
	private boolean checkLength(String length) {
		
		Double num = 0.0;
		
		try {
            num = Double.parseDouble(length);
        } catch (NumberFormatException e) {
        		System.out.println(e);
            return false;
        } catch (NullPointerException e) {
        		System.out.println(e);
        		return false;
        }
		
		if (num > 20 || num < 0) {
			System.out.println("Length out of bounds");
			return false;
		}
		
		return true;
		
	}
	
	private boolean checkLeftPositionAtZero(String leftPositionAtZero) {
		
		Double num = 0.0;
		
		try {
            num = Double.parseDouble(leftPositionAtZero);
        } catch (NumberFormatException e) {
        		System.out.println(e);
            return false;
        } catch (NullPointerException e) {
        		System.out.println(e);
        		return false;
        }
		
		if (num > 10 || num <= -10) {
			System.out.println("Position out of bounds");
			return false;
		}
		
		return true;
		
	}
	
	private boolean checkVelocity(String velocity) {
		
		Double num = 0.0;
		
		try {
            num = Double.parseDouble(velocity);
        } catch (NumberFormatException e) {
        		System.out.println(e);
            return false;
        } catch (NullPointerException e) {
        		System.out.println(e);
        		return false;
        }
		
		if (num >= 1 || num <= -1) {
			System.out.println("Velocity out of bounds");
			return false;
		}
		
		return true;
		
	}
	
}




