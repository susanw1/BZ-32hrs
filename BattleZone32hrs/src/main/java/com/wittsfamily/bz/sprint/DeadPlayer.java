/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Color;
import java.awt.Graphics;

public class DeadPlayer extends Debris
{
	private TimeOut time;

	private int phase;

	public DeadPlayer()
	{
		visual = FixedVectorDrawing.deadPlayer;
		time = new TimeOut(150);
		col = Color.white;
	}

	public void move(float deltaT)
	{
		if (time.expired())
		{
			if (phase < visual.length - 2)
			{
				phase++;
				time.reset(150);
			}
			else if (phase < visual.length - 1)
			{
				phase++;
				time.reset(2000);
			}
			else
				killed();
		}
	}

	public void draw(Graphics g, int w, int h, ViewPoint v)
	{
		visual[phase].draw(g, w, h, v, this, 0f);
	}
}
