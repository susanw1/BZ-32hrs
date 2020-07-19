/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Color;
import java.awt.Graphics;

public class Tank extends Aggressor
		implements RadarUser
{
	private int numShots;

	private TimeOut evasionTime, wanderTime;

	private float radarDirection;

	private boolean hunting;

	public Tank()
	{
		visual = FixedVectorDrawing.tank;
		col = Color.green;
		maxTurnSpeed = 0.01f;
		maxHSpeed = 30;
		yViewPoint = 100;
		extremity = 150;
		evasionTime = new TimeOut(0);
		wanderTime = new TimeOut(0);
	}

	public void draw(Graphics g, int w, int h, ViewPoint v)
	{
		visual[0].setColor(col);
		visual[0].draw(g, w, h, v, this, 0f);
		visual[1].setColor(col);
		visual[1].draw(g, w, h, v, this, radarDirection);
	}

	public void move(float deltaT)
	{
		moveAlong(deltaT);

		if (!evasionTime.expired())
		{
		}
		else if (!hunting && wanderTime.expired())
		{
			if (3 / (aggression + 3.5) > Math.random())
			{
				hSpeed = (int) (Math.random() * maxHSpeed);
				hTurnSpeed = (int) ((Math.random() - 0.5) * maxTurnSpeed);
				wanderTime.reset((int) (6000 * Math.random()));
			}
			else
			{
				hunting = true;
			}
		}
		else if (hunting && enemies.size() > 0)
		{
			Entity e = getCurrentEnemy();
			if (e == null)
			{
				hunting = false;
			}
			else
			{
				float eBearing = bearing(e);
				float absBearing = Math.abs(eBearing);

				hTurnSpeed = eBearing;

				if (absBearing > maxTurnSpeed) hTurnSpeed = (eBearing > 0) ? maxTurnSpeed : -maxTurnSpeed;
				if (absBearing < 0.01 && numShots == 0)
				{
					Shell s = new Shell();
					fire(s);
					numShots++;
				}

				if (6 * absBearing < Math.PI)
					hSpeed = (int) (maxHSpeed * (1 - 6 * absBearing / Math.PI));
				else
					hSpeed = 0;
			}
		}
	}

	public void moveAlong(float deltaT)
	{
		radarDirection += deltaT * 0.1;
		super.moveAlong(deltaT);
	}

	public void collide(Entity other)
	{
		if (other instanceof Shell)
		{
			DebrisManager dm = new DebrisManager();
			Debris d;
			d = new DeadTank(FixedVectorDrawing.deadTankSection1);
			dm.addDebris(d);
			turnedToDebris(d);
			d = new DeadTank(FixedVectorDrawing.deadTankSection2);
			dm.addDebris(d);
			turnedToDebris(d);
			d = new DeadTank(FixedVectorDrawing.deadTankSection3);
			dm.addDebris(d);
			turnedToDebris(d);
			d = new DeadTank(FixedVectorDrawing.deadTankSection4);
			dm.addDebris(d);
			turnedToDebris(d);
			killed();
		}
		else
		{
			if (Math.abs(bearing(other)) > Math.PI / 2)
				hSpeed = maxHSpeed;
			else
				hSpeed = -maxHSpeed;
			evasionTime.reset(6000);
			hTurnSpeed = (float) (Math.PI / 100 * (Math.random() - 0.5));
			hunting = false;
		}
	}

	public float getRadarDirection()
	{
		return radarDirection;
	}

	public void shellExploded(Entity e)
	{
		numShots--;
	}

	public float getRadarStrength()
	{
		return 1.0f;
	}

	public int scorePoints()
	{
		return 1000;
	}
}
