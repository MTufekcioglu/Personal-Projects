/*
 * Author: Mukerrem Tufekcioglu
 * File: MinkowskiGraph.java
 * ---------------------------
 * This class represents the canvas on which the Minkowski graph
 * is drawn. This class is responsible for updating (redrawing)
 * the graphs whenever objects and events are added or removed,
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.awt.Color;

public class MinkowskiGraph extends GCanvas implements ComponentListener {

	/* 10 is the number of lines you will have above and below the x-axis.
	Also is the number of lines you will have on the sides of the y-axis.*/
	private static final int GRIDS = 10 * 2 + 1;
	
	private static double frameVelocity = 0;
	private static double leftPositionOfObjectOfTranslation = 0;
	
	private GRect xAxis;
	private GRect yAxis;
	
	private GLine[] xGrids;
	private GLine[] yGrids;
	
	private GLine lightLine1;
	private GLine lightLine2;
	
	public MinkowskiGraph() {
		
		addComponentListener(this);
		xGrids = new GLine[GRIDS];
		yGrids = new GLine[GRIDS];
		
	}
	
	public void update() {
		
		axes();
		grids();
		lightLines();
		events();
		objects();
		signals();
		
	}
	
	private void axes() {
		
		if (xAxis != null) {
			remove(xAxis);
			remove(yAxis);
		}
		
		double xAxisY = getHeight() / 2 - 1;
		double xAxisXf = getWidth();
		double yAxisX = getWidth() / 2 - 1;
		double yAxisYf = getHeight();
		
		xAxis = new GRect(0, xAxisY, xAxisXf, 2);
		add(xAxis);
		yAxis = new GRect(yAxisX, 0, 2, yAxisYf);
		add(yAxis);
		
	}
	
	private void grids() {
		
		if (xGrids[0] != null) {
			for (int i = 0; i < GRIDS; i++) {
				remove(xGrids[i]);
				remove(yGrids[i]);
			}
		}
		
		double xGridsSeparation = getHeight() / (Double.valueOf(GRIDS) + 1.0);
		double yGridsSeparation = getWidth() / (Double.valueOf(GRIDS) + 1.0);
		
		for (int i = 0; i < GRIDS; i++) {
			// xGrids
			double xLineY = xGridsSeparation * (i + 1);
			double xLineXf = getWidth();
			xGrids[i] = new GLine(0, xLineY, xLineXf, xLineY);
			xGrids[i].setColor(Color.LIGHT_GRAY);
			add(xGrids[i]);
			// yGrids
			double yLineX = yGridsSeparation * (i + 1);
			double yLineYf = getHeight();
			yGrids[i] = new GLine(yLineX, 0, yLineX, yLineYf);
			yGrids[i].setColor(Color.LIGHT_GRAY);
			add(yGrids[i]);
		}
		
	}
	
	private void lightLines() {
		
		if (lightLine1 != null) {
			remove(lightLine1);
			remove(lightLine2);
		}
		
		double height = getHeight();
		double width = getWidth();
		
		lightLine1 = new GLine(0, 0, width, height);
		lightLine1.setColor(Color.YELLOW);
		add(lightLine1);
		lightLine2 = new GLine(0, height, width, 0);
		lightLine2.setColor(Color.YELLOW);
		add(lightLine2);
		
	}
	
	private void events() {
		
		double xGridsSeparation = getHeight() / (Double.valueOf(GRIDS) + 1.0);
		double yGridsSeparation = getWidth() / (Double.valueOf(GRIDS) + 1.0);
		double height = getHeight();
		double width = getWidth();
		
		if (MinkowskiDatabase.getEventList().size() > 0) {
			
			add(MinkowskiDatabase.getEventList().get(MinkowskiDatabase.getEventList().size() - 1));
			
			for (int i = 0; i < MinkowskiDatabase.getEventList().size(); i++) {
				remove(MinkowskiDatabase.getEventList().get(i));
				MinkowskiDatabase.getEventList().set(i, MinkowskiDatabase.getEventList().get(i).translate(frameVelocity,
						leftPositionOfObjectOfTranslation));
				MinkowskiDatabase.getEventList().set(i, MinkowskiEvent.adjustToScreen(MinkowskiDatabase.getEventList().get(i), 
						xGridsSeparation, yGridsSeparation,
						height, width));
				add(MinkowskiDatabase.getEventList().get(i));
			}
			
		}
		
	}
	
	private void objects() {
		
		double xGridsSeparation = getHeight() / (Double.valueOf(GRIDS) + 1.0);
		double yGridsSeparation = getWidth() / (Double.valueOf(GRIDS) + 1.0);
		double height = getHeight();
		double width = getWidth();
		
		if (MinkowskiDatabase.getObjectList().size() > 0) {
			
			add(MinkowskiDatabase.getObjectList().get(MinkowskiDatabase.getObjectList().size() - 1).getRightLine());
			add(MinkowskiDatabase.getObjectList().get(MinkowskiDatabase.getObjectList().size() - 1).getLeftLine());
			
			for (int i = 0; i <= MinkowskiDatabase.getObjectList().size() - 1; i++) {
				
				remove(MinkowskiDatabase.getObjectList().get(i).getRightLine());
				remove(MinkowskiDatabase.getObjectList().get(i).getLeftLine());
				
				MinkowskiDatabase.getObjectList().set(i, MinkowskiDatabase.getObjectList().get(i).translate(frameVelocity, 
						leftPositionOfObjectOfTranslation));
				
				MinkowskiDatabase.getObjectList().set(i, MinkowskiObject.adjustToScreen(MinkowskiDatabase.getObjectList().get(i),
						xGridsSeparation, yGridsSeparation, height, width));
				
				add(MinkowskiDatabase.getObjectList().get(i).getLeftLine());
				add(MinkowskiDatabase.getObjectList().get(i).getRightLine());
				
			}
			
		}
		
	}
	
	private void signals() {
		
		double height = getHeight();
		double width = getWidth();
		
		double xGridsSeparation = height / (Double.valueOf(GRIDS) + 1.0);
		double yGridsSeparation = width / (Double.valueOf(GRIDS) + 1.0);
		
		if (MinkowskiDatabase.getSignalList().size() > 0) {
			
			add(MinkowskiDatabase.getSignalList().get(MinkowskiDatabase.getSignalList().size() - 1).getNegativeSlopeLine());
			add(MinkowskiDatabase.getSignalList().get(MinkowskiDatabase.getSignalList().size() - 1).getPositiveSlopeLine());
			
			System.out.println("here it is x = " + MinkowskiDatabase.getSignalList().get(0).getTranslatedPosition() + "y");
			
			for (int i = 0; i <= MinkowskiDatabase.getSignalList().size() - 1; i++) {
				remove(MinkowskiDatabase.getSignalList().get(i).getNegativeSlopeLine());
				remove(MinkowskiDatabase.getSignalList().get(i).getPositiveSlopeLine());
				
				MinkowskiDatabase.getSignalList().set(i, MinkowskiDatabase.getSignalList().get(i).translate(frameVelocity, 
						leftPositionOfObjectOfTranslation));
				
				MinkowskiDatabase.getSignalList().set(i, MinkowskiSignal.adjustToScreen(MinkowskiDatabase.getSignalList().get(i),
						xGridsSeparation, yGridsSeparation, height, width));
				
				add(MinkowskiDatabase.getSignalList().get(i).getPositiveSlopeLine());
				add(MinkowskiDatabase.getSignalList().get(i).getNegativeSlopeLine());
				
			}
			
		}
		
	}
	
	public static void setFrameVelocity(int indexOfObject) {
		
		MinkowskiObject object = MinkowskiDatabase.getObjectList().get(indexOfObject);
		frameVelocity = object.getOwnVelocity();
		leftPositionOfObjectOfTranslation = object.getLeftPositionAtTimeZero();
		System.out.println("Frame velocity is: " + frameVelocity);
		Minkowski.graph.update();
		
	}
	
	public static void setFrameVelocity(Double velocity) {
		
		frameVelocity = velocity;
		leftPositionOfObjectOfTranslation = 0;
		System.out.println("Frame velocity is: " + frameVelocity);
		Minkowski.graph.update();
		
	}
	
	public void removeObject(int index) {
		
		MinkowskiObject object = MinkowskiDatabase.getObjectList().get(index);
		
		remove(object.getRightLine());
		remove(object.getLeftLine());
		
	}
	
	public void removeEvent(int index) {
		
		MinkowskiEvent event = MinkowskiDatabase.getEventList().get(index);
		remove(event);
		
	}
	
	public void removeSignal(int index) {
		
		MinkowskiSignal signal = MinkowskiDatabase.getSignalList().get(index);
		
		remove(signal.getNegativeSlopeLine());
		remove(signal.getPositiveSlopeLine());
		
	}
	
	public static double getFrameVelocity() {
		
		return frameVelocity;
		
	}
	
	public static double getLeftPositionOfObjectOfTranslation() {
		
		return leftPositionOfObjectOfTranslation;
		
	}
	
	/* Implementation of the ComponentListener interface. You need all of the stuff below
	 * to be able to use componentResized(), that's why they are there.*/
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
}