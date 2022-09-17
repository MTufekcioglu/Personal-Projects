/*
 * Author: Mukerrem Tufekcioglu
 * File: MinkowskiSignalFrame.java
 * ---------------------------
 * This program implements the popup window that promps the
 * user to point to a position for signaling to be emulated.
 */

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class MinkowskiSignalFrame extends JFrame {
	
	private static final int TEXT_FIELD_WIDTH = 16;
	
	public MinkowskiSignalFrame() {
		
		super.setTitle("Signal");
		
		JLabel addTimeLabel = new JLabel("Time:");
		JLabel addPositionLabel = new JLabel("Position:");
		JLabel addNameLabel = new JLabel("Name: ");
		JTextField timeField = new JTextField(TEXT_FIELD_WIDTH);
		JTextField positionField = new JTextField(TEXT_FIELD_WIDTH);
		JTextField nameField = new JTextField(TEXT_FIELD_WIDTH);
		JButton addSignal = new JButton("Add Signal");
		
		addSignal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		String timeString = timeField.getText();
            		String positionString = positionField.getText();
            		String nameString = nameField.getText();
            		
            		if (formatIsGood(timeString, positionString)) {
            			
            			double time = Double.parseDouble(timeString);
            			double position = Double.parseDouble(positionString);
            			String name = nameString;
            			
            			timeField.setText("");
                		positionField.setText("");
                		nameField.setText("");
                		
                		MinkowskiDatabase.newSignal(time, position, name);
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
		panel.add(addNameLabel);
		panel.add(nameField);
		panel.add(addSignal);
		
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