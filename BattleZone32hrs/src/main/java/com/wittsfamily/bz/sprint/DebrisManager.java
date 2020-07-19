/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

public class DebrisManager
{
	private int numberOfDebris;

	public void addDebris(Debris d)
	{
		d.addDebrisManager(this);
		numberOfDebris++;
	}

	public void removeDebris(Debris d)
	{
		d.addDebrisManager(null);
		numberOfDebris--;
	}

	public int getNumberOfDebris()
	{
		return numberOfDebris;
	}
}
