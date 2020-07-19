/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Color;

public class DeadShell extends ExplosiveDebris
{
	public DeadShell(float force)
	{
		super(force);
		visual = FixedVectorDrawing.deadShell;
		col = Color.gray;
		yViewPoint = 100;
		explosive = true;
		extremity = 30;
	}

	public void move(float deltaT)
	{
		moveAlong(deltaT);
	}
}
