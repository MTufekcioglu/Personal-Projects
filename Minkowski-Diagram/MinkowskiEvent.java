/*
 * Author: Mukerrem Tufekcioglu
 * File: MinkowskiEvent.java
 * ---------------------------
 * This class defines what a Minkowski event is
 * and keeps track of the positions and times of 
 * the given events.
 */


import java.awt.*;
import acm.graphics.*;

public class MinkowskiEvent extends GOval {
	
	private static final Double RADIUS = 6.0;
	
	private String name;
	private Color color;
	
	private Double ownTime;
	private Double ownPosition;
	private Double translatedTime;
	private Double translatedPosition;
	private Double adjustedTime;
	private Double adjustedPosition;
	
	public MinkowskiEvent() { super(0,0); }
	
	public MinkowskiEvent(Double position, Double time, Color color, String name, 
			Double frameVelocity, double leftPositionOfObjectOfTranslationAtZero) {
		
		super(2 * RADIUS, 2 * RADIUS);
		this.ownPosition = initiatePosition(frameVelocity, position, time, leftPositionOfObjectOfTranslationAtZero);
		this.ownTime = initiateTime(frameVelocity, position, time);
		this.translatedPosition = position;
		this.translatedTime = time;
		this.adjustedPosition = 0.0;
		this.adjustedTime = 0.0;
		this.color = color;
		this.setColor(color);
		this.setFilled(true);
		this.name = name;
		
	}
	
	public static MinkowskiEvent adjustToScreen(MinkowskiEvent event, double xGridsSeparation, 
			double yGridsSeparation, double height, double width) {
		
		event.adjustedTime = height / 2 - event.translatedTime * xGridsSeparation - RADIUS;
		event.adjustedPosition = width / 2 + event.translatedPosition * yGridsSeparation - RADIUS;
		
		event.setLocation(event.adjustedPosition, event.adjustedTime);
		
		return event;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}
	
	public MinkowskiEvent translate(double frameVelocity, double leftPositionOfObjectOfTranslation) {
		
		this.translatedTime = translateTime(frameVelocity);
		
		this.translatedPosition = translatePosition(frameVelocity, leftPositionOfObjectOfTranslation);
		
		return this;
		
	}
	
	private double initiatePosition (double frameVelocity, double translatedPosition, 
			double translatedTime, double leftPositionOfObjectOfTranslationAtZero) {
		
		double numerator = translatedPosition + frameVelocity * translatedTime;
		double denominator = java.lang.Math.sqrt(1 - frameVelocity * frameVelocity);
		
		double shiftedPosition = numerator / denominator;
		
		double nonShiftedEventPosition = shiftedPosition + leftPositionOfObjectOfTranslationAtZero;
		
		return nonShiftedEventPosition;
		
	}
	
	private double initiateTime (double frameVelocity, double translatedPosition, 
			double translatedTime) {
		
		double numerator = translatedTime + frameVelocity * translatedPosition;
		double denominator = java.lang.Math.sqrt(1 - frameVelocity * frameVelocity);
		
		double nonTranslatedTime = numerator / denominator;
		
		return nonTranslatedTime;
		
	}
	
	private double translateTime(double frameVelocity) {
		
		double numerator = this.ownTime - frameVelocity * this.ownPosition;
		double denominator = java.lang.Math.sqrt(1 - frameVelocity * frameVelocity);
		
		double translatedTime = numerator / denominator;
		
		return translatedTime;
		
	}
	
	private double translatePosition(double frameVelocity, double leftPositionOfObjectOfTranslationAtZero) {
		
		double shiftedEventPosition = this.ownPosition - leftPositionOfObjectOfTranslationAtZero;
		
		double numerator = shiftedEventPosition - frameVelocity * this.ownTime;
		double denominator = java.lang.Math.sqrt(1 - frameVelocity * frameVelocity);
		
		double translatedPosition = numerator / denominator;
		
		return translatedPosition;
		
	}
	
	public double getTranslatedTime() {
		
		return this.translatedTime;
		
	}
	
	public double getTranslatedPosition() {
		
		return this.translatedPosition;
		
	}
	
	public double getOwnTime() {
		
		return this.ownTime;
		
	}
	
	public double getOwnPosition() {
		
		return this.ownPosition;
		
	}
	
	public String getColorString() {
			
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
