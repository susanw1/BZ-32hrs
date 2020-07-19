/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

public abstract class ExplosiveDebris extends Debris
{
	private DebrisManager dm;

	public ExplosiveDebris(float force)
	{
		hSpeed = (int) (Math.random() * 120 * force);
		spinSpeed = (float) (Math.random() * 0.2);

		vTurnSpeed = (float) (Math.random() * 0.4);
		vSpeed = (int) (Math.random() * 180 * force);
		elevationUser = true;

	}

	public void moveAlong(float deltaT)
	{
		vSpeed -= deltaT * 10;
		if (getY() < 0) killed();
		super.moveAlong(deltaT);
	}
}
