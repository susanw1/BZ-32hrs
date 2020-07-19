/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Color;
import java.awt.Graphics;

public class Demo extends PlaySession
{
	String msg = "Click to start: Q and A control left track, " + "P and L for right track, <return> to fire";

	float x;

	public Demo(BZGame bzg)
	{
		super(bzg);
		x = 0;
	}

	public void render(Graphics g, int w, int h)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, w, h);

		g.setColor(Color.green);
		g.drawString(msg, (int) x, 30);
		x = (x + deltaT * 2) % w;
	}
}
