/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

public class DeadSaucer extends Debris
{
	private TimeOut life;

	private float colourCode;

	public DeadSaucer()
	{
		visual = FixedVectorDrawing.saucer;
		colourCode = 99;
		life = new TimeOut(5000);
		col = GameColours.shadesOfGreen[(int) (colourCode / 10)];
		hSpeed = 0;
		spinSpeed = 0.2f;
	}

	public void move(float deltaT)
	{
		moveAlong(deltaT);
		colourCode -= deltaT;
		col = GameColours.shadesOfGreen[(int) (colourCode / 10)];
		if (life.expired() || colourCode < 0) killed();
	}
}
