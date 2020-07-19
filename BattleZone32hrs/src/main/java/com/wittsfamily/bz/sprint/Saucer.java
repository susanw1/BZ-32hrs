/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

public class Saucer extends Target
{
	TimeOut action, longLife;

	public Saucer()
	{
		visual = FixedVectorDrawing.saucer;
		col = GameColours.shadesOfGreen[6];
		extremity = 200;
		spinSpeed = 0.1f;
		maxHSpeed = 50;
		action = new TimeOut(5000);
		longLife = new TimeOut(300000);
	}

	public void move(float deltaT)
	{
		moveAlong(deltaT);
		if (action.expired())
		{
			hSpeed = (int) (Math.random() * maxHSpeed);
			hMotionDirection = (float) (Math.random() * Math.PI * 2);
			action.reset((int) (Math.random() * 10000 + 5000));
		}
		if (longLife.expired())
		{
			DeadSaucer ds = new DeadSaucer();
			turnedToDebris(ds);
			killed();
		}
	}

	public void collide(Entity other)
	{
		if (other instanceof Shell)
		{
			DeadSaucer ds = new DeadSaucer();
			turnedToDebris(ds);
			killed();
		}
		else
			hSpeed = -hSpeed;
	}

	public float getRadarStrength()
	{
		return 0.1f;
	}

	public int scorePoints()
	{
		return 5000;
	}
}
