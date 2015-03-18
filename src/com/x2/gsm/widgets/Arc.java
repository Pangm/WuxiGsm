package com.x2.gsm.widgets;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.utils.ScreenPosition;

public class Arc implements Control {
	private int liveCnt = 0;
	private boolean isVisible = false;
	private ScreenPosition currentPos;
	private ScreenPosition lastPos;
	private PApplet context;
	
	public Arc(PApplet context, ScreenPosition currentPos, ScreenPosition lastPos) {
		this.liveCnt = 20;
		this.context = context;
		this.isVisible = true;
		this.currentPos = currentPos;
		this.lastPos = lastPos;
	}

	@Override
	public boolean overEvent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsDisplay(boolean isDisplay) {
		// TODO Auto-generated method stub
		this.isVisible = isDisplay;
	}
}
