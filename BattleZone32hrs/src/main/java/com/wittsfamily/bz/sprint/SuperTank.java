/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Color;

public class SuperTank extends Tank
{
	public SuperTank()
	{
		visual = FixedVectorDrawing.superTank;
		col = Color.red;
		maxTurnSpeed = 0.025f;
		maxHSpeed = 40;
		yViewPoint = 100;
		extremity = 140;
	}

	public int scorePoints()
	{
		return 3000;
	}
}
