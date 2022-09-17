/*
 * Author: Mukerrem Tufekcioglu
 * File: MinkowskiObject.java
 * ---------------------------
 * This class defines what a Minkowski signal is
 * and keeps track of the time and position of the signal.
 */

import acm.graphics.*;
import java.awt.*;

public class MinkowskiSignal {
	
	private static final int SIGNAL_DIRECTIONAL_LENGTH = 8;
	
	private String name;
	
	private Double ownTime;
	private Double ownPosition;
	private Double translatedTime;
	private Double translatedPosition;
	
	private GLine positiveSlopeLine;
	private GLine negativeSlopeLine;
	
	public MinkowskiSignal(double time, double position, String name, Double frameVelocity, 
			double leftPositionOfObjectOfTranslationAtZero) {
	
		this.name = name;
		
		this.translatedPosition = position;
		this.translatedTime = time;
		this.ownPosition = initiatePosition(frameVelocity, position, time, leftPositionOfObjectOfTranslationAtZero);
		this.ownTime = initiateTime(frameVelocity, position, time);
		
		this.positiveSlopeLine = new GLine();
		this.positiveSlopeLine.setColor(Color.PINK);
		this.negativeSlopeLine = new GLine();
		this.negativeSlopeLine.setColor(Color.PINK);
		
	}
	
	public GLine getPositiveSlopeLine() {
		return this.positiveSlopeLine;
	}
	
	public GLine getNegativeSlopeLine() {
		return this.negativeSlopeLine;
	}
	
	public static MinkowskiSignal adjustToScreen(MinkowskiSignal signal, double xGridsSeparation, 
			double yGridsSeparation, double height, double width) {
		
		double screenX = width / 2.0 + signal.translatedPosition * yGridsSeparation;
		double screenLeftX = screenX - SIGNAL_DIRECTIONAL_LENGTH * yGridsSeparation;
		double screenRightX = screenX + SIGNAL_DIRECTIONAL_LENGTH * yGridsSeparation;
		
		double screenY = height / 2.0 - signal.translatedTime * xGridsSeparation;
		double screenHighY = screenY - SIGNAL_DIRECTIONAL_LENGTH * xGridsSeparation;
		double screenLowY = screenY + SIGNAL_DIRECTIONAL_LENGTH * xGridsSeparation;
		
		signal.positiveSlopeLine.setStartPoint(screenLeftX, screenLowY);
		signal.positiveSlopeLine.setEndPoint(screenRightX, screenHighY);
		
		signal.negativeSlopeLine.setStartPoint(screenLeftX, screenHighY);
		signal.negativeSlopeLine.setEndPoint(screenRightX, screenLowY);
		
		return signal;
		
	}
	
	public MinkowskiSignal translate(double frameVelocity, double leftPositionOfObjectOfTranslation) {
		
		this.translatedTime = translateTime(frameVelocity);
		
		this.translatedPosition = translatePosition(frameVelocity, leftPositionOfObjectOfTranslation);
		
		return this;
		
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
	
	public String getName() {
		
		return this.name;
		
	}
	
	public Double getTranslatedPosition() {
		
		return this.translatedPosition;
		
	}
	
	public Double getTranslatedTime() {
		
		return this.translatedTime;
		
	}
	
	public Double getOwnPosition() {
		
		return this.ownPosition;
		
	}
	
	public Double getOwnTime() {
		
		return this.ownTime;
		
	}
	
	
}

