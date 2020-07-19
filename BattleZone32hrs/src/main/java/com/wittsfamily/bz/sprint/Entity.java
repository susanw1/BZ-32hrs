/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Entity
		implements ViewPoint
{
	protected VisibleForm[] visual;

	private int xPos, yPos, zPos;

	protected int yViewPoint; // relative to yPos

	protected int hSpeed, vSpeed;

	protected int maxHSpeed;

	protected float hTurnSpeed, vTurnSpeed, maxTurnSpeed, spinSpeed;

	protected int extremity;

	protected int height;

	protected boolean elevationUser;

	protected Color col;

	protected float hMotionDirection, hRelSpinDirection, hRelViewingDirection;

	protected float elevationDirection;

	protected int hardness, armour;

	protected boolean explosive, real;

	private boolean dead;

	protected Combat theCombat;

	protected Entity()
	{
		dead = false;
		real = false;
	}

	protected void setInitialState(int x, int y, int z, float a, Combat c)
	{
		if (!real)
		{
			this.xPos = x;
			this.yPos = y;
			this.zPos = z;
			this.hMotionDirection = a;
			this.real = true;
			theCombat = c;
		}
	}

	protected int getExtremity()
	{
		return extremity;
	}

	public float getRadarStrength()
	{
		return 0.0f;
	}

	public abstract void move(float deltaT);

	public abstract void collide(Entity e);

	public void moveAlong(float deltaT)
	{
		zPos += hSpeed * Math.cos(hMotionDirection) * deltaT;
		xPos += hSpeed * Math.sin(hMotionDirection) * deltaT;
		yPos += vSpeed * deltaT;
		hMotionDirection += hTurnSpeed * deltaT;
		hRelSpinDirection += spinSpeed * deltaT;
		elevationDirection += vTurnSpeed * deltaT;

		if (hMotionDirection > Math.PI) hMotionDirection -= 2 * Math.PI;
		if (hMotionDirection < -Math.PI) hMotionDirection += 2 * Math.PI;
		if (hRelSpinDirection > Math.PI) hRelSpinDirection -= 2 * Math.PI;
		if (elevationDirection < -Math.PI) elevationDirection += 2 * Math.PI;

	}

	public void draw(Graphics g, int w, int h, ViewPoint v)
	{
		visual[0].setColor(col);
		visual[0].draw(g, w, h, v, this, 0);
	}

	public void turnedToDebris(Debris d)
	{
		d.setInitialState(xPos, yPos, zPos, (float) (Math.random() * 7), theCombat);
		theCombat.addNewEntity(d);
	}

	public void killed()
	{
		dead = true;
		theCombat.getGamePlay().entityDied(this);
	}

	public boolean isDead()
	{
		return dead;
	}

	public boolean isReal()
	{
		return real;
	}

	public int getX()
	{
		return xPos;
	}

	public int getYViewPoint()
	{
		return yViewPoint + yPos;
	}

	public int getY()
	{
		return yPos;
	}

	public int getZ()
	{
		return zPos;
	}

	public float getMotionDirection()
	{
		return hMotionDirection;
	}

	public float getShapeDirection()
	{
		return hMotionDirection + hRelSpinDirection;
	}

	public float getViewingDirection()
	{
		return hMotionDirection + hRelSpinDirection + hRelViewingDirection;
	}

	public float getElevationDirection()
	{
		return elevationDirection;
	}

	public int getHeight()
	{
		for (int i = 0; i < visual.length; i++)
		{
			if (height != 0)
			{
				return height + yPos;
			}
			else
			{
				if (visual != null)
				{
					height = visual[i].getHeight();
					return height + yPos;
				}
			}
		}
		return yPos;
	}

	public int scorePoints()
	{
		return 0;
	}
}
