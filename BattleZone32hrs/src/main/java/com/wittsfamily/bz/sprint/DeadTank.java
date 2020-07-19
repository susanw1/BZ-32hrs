/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Color;

public class DeadTank extends ExplosiveDebris
{
	public DeadTank(VisibleForm[] v)
	{
		super(1.0f);
		visual = v;
		col = Color.green;
	}

	public void move(float deltaT)
	{
		moveAlong(deltaT);
	}
}
