/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Color;
import java.awt.Graphics;

public class Radar
{
	private Color[] cols;

	private Color veryBrightGreen = new Color(180, 255, 180);

	private Color veryDarkGreen = new Color(0, 50, 0);

	private Color fairlyDarkGreen = new Color(0, 80, 0);

	private boolean targetInSight, inRange;

	private int quadrant;

	public Radar()
	{
	}

	public void clear(Graphics g, int h)
	{
		g.setColor(Color.black);
		g.fillRect(-h, -h, h * 2, h * 2);
	}

	public void draw(Graphics g, EntityVector entities, ViewPoint me, float deltaT, int h, float zoom)
	{
		float dir, degrees;
		boolean amRadarUser = (me instanceof RadarUser);

		if (amRadarUser)
		{
			degrees = (float) (((RadarUser) me).getRadarDirection() * 180 / Math.PI) % 360;
			dir = (float) (degrees / 180 * Math.PI);
		}
		else
			degrees = dir = 0;
		if (dir > Math.PI) dir -= Math.PI * 2;

		targetInSight = false;
		inRange = false;
		quadrant = 0;

		g.setColor(Color.black);
		g.fillRect(-h, -h, h * 2, h * 2);

		if (amRadarUser)
		{
			g.setColor(veryDarkGreen);
			g.fillArc(-h, -h, h * 2, h * 2, 115 - (int) (degrees), 25);
			g.setColor(fairlyDarkGreen);
			g.fillArc(-h, -h, h * 2, h * 2, 90 - (int) (degrees), 25);
		}
		g.setColor(Color.green);

		for (int i = 0; i < entities.size(); i++)
		{
			g.setColor(Color.green);
			Entity e = entities.entityAt(i);
			float rStrength = e.getRadarStrength();

			double eBearing = Math.atan2(e.getX() - me.getX(), e.getZ() - me.getZ()) - me.getViewingDirection();
			double eb = dir - eBearing;
			if (eb > 2 * Math.PI)
				eb -= Math.PI * 2;
			else if (eb < 0) eb += Math.PI * 2;

			long xd = e.getZ() - me.getZ(), zd = e.getX() - me.getX();
			double eDist = Math.sqrt(xd * xd + zd * zd);
			int bright = Math.max(Math.min(9, (int) (rStrength * (Math.PI * 2 - eb) * 2)), 0);

			g.setColor(GameColours.shadesOfGreen[bright]);
			g.fillRect((int) (eDist * Math.sin(eBearing) * zoom) - 1, (int) (eDist * -Math.cos(eBearing) * zoom) - 1, 2, 2);
			if (e instanceof Target && eDist > 100 && (int) (eBearing * 1591 + 20100) % 10000 < 200)
			{
				targetInSight = true;
			}
			if (e != me && e instanceof Aggressor)
			{
				int offset = (int) (eBearing * 4 / Math.PI + 33) % 8 / 2;
				quadrant |= (1 << offset);
				if (eDist < 8000) inRange = true;
			}
		}
		g.setColor(veryBrightGreen);
		g.drawLine(0, 0, (int) (h * 2 * Math.sin(dir)), -(int) (h * 2 * Math.cos(dir)));

	}

	public boolean isTargetInSight()
	{
		return targetInSight;
	}

	public int getQuadrant()
	{
		return quadrant;
	}

	public boolean getInRange()
	{
		return inRange;
	}
}
