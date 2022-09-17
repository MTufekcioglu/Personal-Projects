/*
 * Author: Mukerrem Tufekcioglu
 * File: MinkowskiObject.java
 * ---------------------------
 * This class defines what a Minkowski object is
 * and keeps track of the positions, velocities and times of 
 * the given events.
 */

import acm.graphics.*;
import java.awt.*;

public class MinkowskiObject {
	
	private static final double GRIDS_DIVIDE_INTO_SECTIONS = 11;
	
	private String name;
	private Color color;
	
	private double restLength;
	private double ownLeftPositionAtZero;
	private double ownVelocity;
	
	private double translatedLength;
	private double translatedX1;
	private double translatedX2;
	private double translatedLeftPositionAtZero;
	private double translatedVelocity;
	
	private GLine leftLine;
	private GLine rightLine;
	
	public MinkowskiObject() { }
	
	public MinkowskiObject(double length, double leftPositionAtZero, double velocity, Color color, String name, 
			double frameVelocity, double leftPositionOfObjectOfTranslation) {
	
		this.name = name;
		this.color = color;
		
		this.restLength = initiateLength(length, frameVelocity);
		this.translatedLength = length;
		this.ownLeftPositionAtZero = initiateLeftPositionAtZero(leftPositionAtZero, frameVelocity, 
				leftPositionOfObjectOfTranslation);
		this.translatedLeftPositionAtZero = leftPositionAtZero;
		this.ownVelocity = initiateVelocity(velocity, frameVelocity);
		this.translatedVelocity = velocity;
		
		this.leftLine = new GLine();
		this.leftLine.setColor(color);
		this.leftLine.setLineWidth(3);
		this.rightLine = new GLine();
		this.rightLine.setColor(color);
		this.rightLine.setLineWidth(3);
		
	}
	
	public GLine getLeftLine() {
		return this.leftLine;
	}
	
	public GLine getRightLine() {
		return this.rightLine;
	}
	
	public static MinkowskiObject adjustToScreen(MinkowskiObject object, double xGridsSeparation, 
			double yGridsSeparation, double height, double width) {
		
		object.translatedX1 = object.translatedLeftPositionAtZero + GRIDS_DIVIDE_INTO_SECTIONS * object.translatedVelocity;
		object.translatedX2 = object.translatedLeftPositionAtZero - GRIDS_DIVIDE_INTO_SECTIONS * object.translatedVelocity;
		
		double screenX1 = width / 2.0 + object.translatedX1 * yGridsSeparation;
		double screenX2 = width / 2.0 + object.translatedX2 * yGridsSeparation;
		
		double screenLength = object.translatedLength * yGridsSeparation;
		
		System.out.println("X1: " + screenX1 + ", X2: " + screenX2);
		
		object.leftLine.setStartPoint(screenX1, 0);
		object.leftLine.setEndPoint(screenX2, height);
		
		object.rightLine.setStartPoint(screenX1 + screenLength, 0);
		object.rightLine.setEndPoint(screenX2 + screenLength, height);
		
		return object;
		
	}
	
	public static String getName(MinkowskiObject object) {
		
		return object.name;
		
	}
	
	public MinkowskiObject translate(double frameVelocity, double leftPositionOfObjectOfTranslation) {
		
		this.translatedVelocity = translateVelocity(this.ownVelocity, frameVelocity);
		
		this.translatedLength = translateLength(this.restLength, this.translatedVelocity);
		
		this.translatedLeftPositionAtZero = translateLeftPositionAtZero(leftPositionOfObjectOfTranslation, frameVelocity);
		
		return this;
		
	}
	
	private double translateVelocity(double ownVelocity, double frameVelocity) {
		
		double numerator = ownVelocity - frameVelocity;
		double denominator = 1 - frameVelocity * ownVelocity;
		
		return numerator / denominator;
		
	}
	
	private double translateLength(double restLength, double translatedVelocity) {
		
		double l = 0;
		double lZero = restLength;
		double v = translatedVelocity;
		
		// The length contraction formula
		l = lZero * java.lang.Math.sqrt(1 - v*v);
		
		return l;
		
	}
	
	private double translateLeftPositionAtZero(double leftPositionOfObjectOfTranslation, double frameVelocity) {
		
		double initialLeftPositionOfTranslatedObject = this.ownLeftPositionAtZero;
		double nonTranslatedDistance = initialLeftPositionOfTranslatedObject - leftPositionOfObjectOfTranslation;
		
		double translatedDistance = nonTranslatedDistance * java.lang.Math.sqrt(1 - frameVelocity*frameVelocity);
		
		return translatedDistance;
		
	}
	
	private double initiateLength(double translatedLength, double frameVelocity) {
		
		double ownLength = translatedLength / java.lang.Math.sqrt(1 - frameVelocity * frameVelocity);
		
		return ownLength;
		
	}
	
	private double initiateLeftPositionAtZero(double translatedLeftPositionAtZero,
			double frameVelocity, double leftPositionOfObjectOfTranslation) {
		
		double numerator = translatedLeftPositionAtZero;
		double denominator = java.lang.Math.sqrt(1 - frameVelocity * frameVelocity);
		
		double shiftedLeftPositionAtZero = numerator / denominator;
		
		double nonShiftedLeftPositionAtZero = shiftedLeftPositionAtZero + leftPositionOfObjectOfTranslation;
		
		return nonShiftedLeftPositionAtZero;
		
	}
	
	private double initiateVelocity(double translatedVelocity, double frameVelocity) {
		
		double numerator = translatedVelocity + frameVelocity;
		double denominator = translatedVelocity * frameVelocity + 1;
		
		double ownVelocity = numerator / denominator;
		
		return ownVelocity;
		
	}
	
	public String getName() {
		
		return this.name;
	
	}
	
	public double getOwnVelocity() {
		
		return this.ownVelocity;
		
	}
	
	public double getTranslatedVelocity() {
		
		return this.translatedVelocity;
		
	}
	
	public double getOwnLeftPositionAtZero() {
		
		return this.ownLeftPositionAtZero;
		
	}
	
	public double getTranslatedLeftPositionAtZero() {
		
		return this.translatedLeftPositionAtZero;
		
	}
	
	public double getRestLength() {
		
		return this.restLength;
		
	}
	
	public double getTranslatedLength() {
		
		return this.translatedLength;
		
	}
	
	public double getLeftPositionAtTimeZero() {
		
		return this.ownLeftPositionAtZero;
	
	}
	
	public String getColor() {
		
		int red = this.color.getRed();
		int green = this.color.getGreen();
		int blue = this.color.getBlue();
		
		if (red > green && red > blue) {
			return "red";
		}
		
		if (green > blue) {
			return "green";
		}
		
		return "blue";
		
	}
	
}

