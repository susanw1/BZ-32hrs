/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Color;

public class Shell extends Entity
{
	private TimeOut life;

	private Aggressor myShooter;

	public Shell()
	{
		visual = FixedVectorDrawing.shell;
		col = Color.green;
		yViewPoint = 100;
		hSpeed = 100;
		explosive = true;
		extremity = 30;
		life = new TimeOut(4000);
	}

	public void setShooter(Aggressor e)
	{
		myShooter = e;
	}

	public void move(float deltaT)
	{
		moveAlong(deltaT);
		if (life.expired()) terminate(null);
	}

	public void collide(Entity target)
	{
		if (!(target instanceof Shell))
		{
			if (BattleZone.boomEffect != null)
			{
				BattleZone.boomEffect.play();
			}
			terminate(target);
		}
	}

	private void terminate(Entity target)
	{
		DebrisManager dm = new DebrisManager();
		for (int i = 0; i < 5; i++)
		{
			DeadShell ds = new DeadShell(0.4f);
			dm.addDebris(ds);
			turnedToDebris(ds);
		}
		killed();

		if (myShooter != null) myShooter.shellExploded(target);
	}

	public float getRadarStrength()
	{
		return 0.1f;
	}

}
