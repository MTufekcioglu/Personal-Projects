/*
 * Author: Mukerrem Tufekcioglu
 * File: Minkowski.java
 * ---------------------
 * This program implements the viewer for the interactive Minkowski Diagram.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class Minkowski extends Program {
	
	public static MinkowskiGraph graph;
	
	public void run() {
		
		setTitle("Minkowski Diagram");
		
		graph = new MinkowskiGraph();
		add(graph);
		
		JButton addEventButton = new JButton("Add Event");
		add(addEventButton, EAST);
		
		JButton addObjectButton = new JButton("Add Object");
		add(addObjectButton, EAST);
		
		JButton signalButton = new JButton("Signal");
		add(signalButton, EAST);
		
		JButton translateButton = new JButton("Translate");
		add(translateButton, EAST);
		
		JButton informationButton = new JButton("Information");
		add(informationButton, EAST);
		
		JButton removeButton = new JButton("Remove");
		add(removeButton, EAST);
		
		addActionListeners();
		
	}
	
	public static void main(String[] args) {

		(new Minkowski()).start(args);

	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("Add Event")) {
			MinkowskiAddEventFrame frame = new MinkowskiAddEventFrame();
		}
		
		if (e.getActionCommand().equals("Add Object")) {
			MinkowskiAddObjectFrame frame = new MinkowskiAddObjectFrame();
		}
		
		if (e.getActionCommand().equals("Signal")) {
			MinkowskiSignalFrame frame = new MinkowskiSignalFrame();
		}
		
		if (e.getActionCommand().equals("Translate")) {
			MinkowskiTranslateFrame frame = new MinkowskiTranslateFrame();
		}
		
		if (e.getActionCommand().equals("Information")) {
			MinkowskiInformationFrame frame = new MinkowskiInformationFrame();
		}
		
		if (e.getActionCommand().equals("Remove")) {
			MinkowskiRemoveFrame frame = new MinkowskiRemoveFrame();
		}
		
	}

}

