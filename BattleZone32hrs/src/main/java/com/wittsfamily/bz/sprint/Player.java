/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

public class Player extends Tank
{
	static final int FR = 1, FL = 2, BR = 4, BL = 8, FIRE = 16;

	private int ctrls;

	private int numShots;

	private boolean forwardJammed, backwardJammed;

	public Player()
	{
		forwardJammed = false;
		backwardJammed = false;
		maxHSpeed = 30;
		maxTurnSpeed = 0.1f;
		extremity = 150;
	}

	public synchronized void setCtrl(int newCtrl)
	{
		ctrls |= newCtrl;
	}

	public synchronized void unsetCtrl(int newCtrl)
	{
		ctrls &= ~newCtrl;
	}

	public synchronized void move(float deltaT)
	{
		int dir = 0;
		hSpeed = 0;
		if ((ctrls & (FR | FL)) != 0 && !forwardJammed) hSpeed += maxHSpeed;
		if ((ctrls & (BR | BL)) != 0 && !backwardJammed) hSpeed -= maxHSpeed;
		if ((ctrls & BR) != 0) dir++;
		if ((ctrls & FR) != 0) dir--;
		if ((ctrls & BL) != 0) dir--;
		if ((ctrls & FL) != 0) dir++;
		// if (hSpeed != 0 || dir != 0) System.out.println
		// ("Speed="+hSpeed+", dir="+dir);
		hTurnSpeed = maxTurnSpeed / 2 * dir;

		moveAlong(deltaT);
		if ((ctrls & FIRE) != 0)
		{
			if (numShots < 30)
			{
				Shell s = new Shell();
				fire(s);
				numShots++;
			}
			ctrls &= ~FIRE;
		}
		forwardJammed = backwardJammed = false;
	}

	public void shellExploded(Entity e)
	{
		numShots--;
		theCombat.getScoreBoard().addToScore(e);
	}

	public void collide(Entity other)
	{
		if (other instanceof Shell || other instanceof Missile)
		{
			DeadPlayer d = new DeadPlayer();
			d.setInitialState(getX(), getY(), getZ(), getViewingDirection(), theCombat);
			theCombat.addNewEntity(d);
			killed();
		}
		if (Math.abs(bearing(other)) > Math.PI / 2)
			backwardJammed = true;
		else
			forwardJammed = true;
	}

	public boolean getObstructed()
	{
		return forwardJammed || backwardJammed;
	}
}
