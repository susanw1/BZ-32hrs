/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Color;
import java.awt.Graphics;

public abstract class VisibleForm
{
	protected int xPos, yPos, zPos;

	protected Color col;

	abstract public void draw(Graphics g, int w, int h, ViewPoint v, Entity e, float spin);

	abstract public int getHeight();

	public void setColor(Color c)
	{
		col = c;
	}

	public void setOffset(int x, int y, int z)
	{
		xPos = x;
		yPos = y;
		zPos = z;
	}
}
