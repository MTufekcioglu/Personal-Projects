/*
 * Author: Mukerrem Tufekcioglu
 * File: MinkowskiTranslateFrame.java
 * ---------------------------
 * This program implements the popup window that promps the
 * user to choose an object thus its velocity to translate the graph.
 */

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MinkowskiTranslateFrame extends JFrame {
	
	private static final int TEXT_FIELD_WIDTH = 16;
	
	public MinkowskiTranslateFrame() {
		
		String[] objectNameArray = MinkowskiDatabase.objectStringList();
		System.out.println(objectNameArray.length);
		JComboBox objectList = new JComboBox(objectNameArray);
		JButton translateByObjectButton = new JButton("Translate using Object");
		
		translateByObjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = objectList.getItemAt(objectList.getSelectedIndex());
				if (objectTranslationPossible(obj)) {
					System.out.println("The object _" + obj + "_ was chosen.");
					ArrayList<MinkowskiObject> objectList = MinkowskiDatabase.getObjectList();
					int length = objectList.size();
					for (int i = 0; i < length; i++) {
						String objectName = MinkowskiObject.getName(objectList.get(i));
						if (objectName == obj) {
							MinkowskiGraph.setFrameVelocity(i);
							//MinkowskiDatabase.removeAllSignals();
							dispose();
						}
					}
				} else {
					System.out.println("No object chosen");
				}
			}
		});
		
		JLabel enterVelocityLabel = new JLabel("Set frame velocity to: ");
		JTextField velocityField = new JTextField(TEXT_FIELD_WIDTH);
		JButton translateByVelocityButton = new JButton("Translate using Velocity");
		
		translateByVelocityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String velocityString = velocityField.getText();
				
				if (formatIsGood(velocityString)) {
					Double velocity = Double.parseDouble(velocityString);
					MinkowskiGraph.setFrameVelocity(velocity);
					//MinkowskiDatabase.removeAllSignals();
					dispose();
				}
				
			}
		});
		
		JPanel panel = new JPanel();
		
		panel.add(objectList);
		panel.add(translateByObjectButton);
		panel.add(enterVelocityLabel);
		panel.add(velocityField);
		panel.add(translateByVelocityButton);
		this.add(panel);
		this.setSize(1100, 60);
		this.setVisible(true);
		
	}
	
	private boolean objectTranslationPossible(Object obj) {
		
		return obj != null;
	
	}
	
	private boolean formatIsGood(String velocity) {
		
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




