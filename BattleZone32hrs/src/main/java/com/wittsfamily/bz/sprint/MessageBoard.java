/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Color;
import java.awt.Graphics;

public class MessageBoard
{
	private static final int front = 1, right = 2, rear = 4, left = 8;

	private Combat theCombat;

	public MessageBoard(Combat c)
	{
		theCombat = c;
	}

	public void draw(Graphics g, int w, int h)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, w, h);
		g.setColor(Color.green);

		if (theCombat.getGamePlay().getLocalPlayer().getObstructed()) g.drawString("Motion obstructed by object", 0, h / 3);

		if (theCombat.getRadar().getInRange()) g.drawString("Enemy in range", 0, 2 * h / 3);

		int quadrant = theCombat.getRadar().getQuadrant();

		if (quadrant > 0)
		{
			StringBuffer message = new StringBuffer("Enemy to ");
			if ((quadrant & front) != 0) message.append("front ");
			if ((quadrant & right) != 0) message.append("right ");
			if ((quadrant & rear) != 0) message.append("rear ");
			if ((quadrant & left) != 0) message.append("left ");
			g.drawString(new String(message), 0, h - 2);
		}
	}
}
