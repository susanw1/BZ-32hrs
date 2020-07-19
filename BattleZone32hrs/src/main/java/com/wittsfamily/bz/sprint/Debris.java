/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

public abstract class Debris extends Entity
{
	private DebrisManager dm;

	void addDebrisManager(DebrisManager dm)
	{
		this.dm = dm;
	}

	public void collide(Entity other)
	{
	}

	public boolean isLastPieceOfDebris()
	{
		return (dm == null) || dm.getNumberOfDebris() == 1;
	}

	public void remove()
	{
		if (dm != null) dm.removeDebris(this);
	}
}
