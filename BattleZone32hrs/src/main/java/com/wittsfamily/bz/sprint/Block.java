/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Color;

public class Block extends Obstacle
{
	public Block()
	{
		visual = FixedVectorDrawing.block;
		col = Color.green;
		yViewPoint = 100;
		extremity = 140;
	}

	public void move(float deltaT)
	{
	}

	public void collide(Entity other)
	{
	}
}
