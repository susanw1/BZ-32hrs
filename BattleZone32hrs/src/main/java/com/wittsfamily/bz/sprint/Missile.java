/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Color;
import java.util.Random;

public class Missile extends Aggressor
{
	private Random rand;

	private int jumpHeight;

	private boolean evading;

	private int huntingCount;

	private int zigzagState;

	private int zigzagLevel;

	private TimeOut zigzagTimer, notHitTimer;

	private TimeOut evasionTime;

	public Missile()
	{
		visual = FixedVectorDrawing.missile;
		col = Color.green;
		extremity = 100;
		hSpeed = 70;
		hTurnSpeed = 0.0f;
		maxHSpeed = 70;
		maxTurnSpeed = 0.3f;
		rand = new Random();
		evading = false;
		zigzagState = 0;
		zigzagTimer = new TimeOut(0);
		notHitTimer = new TimeOut(0);
	}

	public void move(float deltaT)
	{
		moveAlong(deltaT);

		if (getY() > jumpHeight)
		{
			evading = false;
		}
		if (!evading)
		{
			if (getY() > 80)
				vSpeed = -30;
			else
				vSpeed = 0;
		}

		if (notHitTimer.expired())
		{
			zigzagLevel = aggression / 6 + 1;
			notHitTimer.reset(15000);
		}

		Entity e = getCurrentEnemy();
		if (e == null)
		{
			blewUp();
		}
		else
		{
			if (zigzagTimer.expired())
			{
				if (zigzagLevel > 0)
				{
					if (zigzagState == 0)
					{
						double rand = Math.random();
						zigzagTimer.reset(300);
						if (rand > 0.4)
						{
							hTurnSpeed = (rand > 0.7) ? maxTurnSpeed : -maxTurnSpeed;
							zigzagState = 1;
						}
					}
					else if (zigzagState == 1)
					{
						zigzagTimer.reset(600);
						hTurnSpeed = -hTurnSpeed;
						zigzagState = 2;
					}
					else if (zigzagState == 2)
					{
						zigzagState = 3;
						huntingCount = 5;
					}
					else if (huntingCount == 0)
					{
						zigzagState = 0;
						zigzagLevel--;
						hTurnSpeed = 0;
					}
				}
			}
			if (huntingCount > 0)
			{
				float eBearing = bearing(e);
				float absBearing = Math.abs(eBearing);

				hTurnSpeed = eBearing;

				if (absBearing > maxTurnSpeed)
				{
					hTurnSpeed = (eBearing > 0) ? maxTurnSpeed : -maxTurnSpeed;
				}
				else
				{
					huntingCount--;
				}
			}
		}
	}

	public void collide(Entity other)
	{
		if (other instanceof Shell)
		{
			blewUp();
		}
		else if (other instanceof Obstacle)
		{
			vSpeed = 30;
			jumpHeight = other.getHeight();
			evading = true;
		}
	}

	private void blewUp()
	{
		DebrisManager dm = new DebrisManager();
		Debris d;
		d = new DeadMissile(FixedVectorDrawing.deadMissileSection1);
		dm.addDebris(d);
		turnedToDebris(d);
		d = new DeadMissile(FixedVectorDrawing.deadMissileSection2);
		dm.addDebris(d);
		turnedToDebris(d);
		d = new DeadMissile(FixedVectorDrawing.deadMissileSection3);
		dm.addDebris(d);
		turnedToDebris(d);
		d = new DeadMissile(FixedVectorDrawing.deadMissileSection4);
		dm.addDebris(d);
		turnedToDebris(d);
		killed();
	}

	public float getRadarStrength()
	{
		return 1f;
	}

	public void shellExploded(Entity e)
	{
	}

	public int scorePoints()
	{
		return 3000;
	}
}
