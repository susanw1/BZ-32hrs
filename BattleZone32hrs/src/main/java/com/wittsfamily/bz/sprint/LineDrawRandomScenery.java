/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

public class LineDrawRandomScenery extends Scenery
{
	private Image im;

	private int w, h;

	private int fullW;

	private double viewWidth = Math.atan(0.5);

	public LineDrawRandomScenery(Component display)
	{
		super(display);
	}

	public void setScenery(int displayW, int displayH)
	{
		this.w = displayW;
		this.h = displayH / 4;

		fullW = (int) (w * Math.PI / viewWidth);
		im = display.createImage(fullW, h);
		Graphics g = im.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, fullW, h);
		g.setColor(GameColours.shadesOfGreen[4]);

		int x = 0;
		int lastX = x;
		double a, b, c, d;

		int v = 0;

		while (x < fullW)
		{
			lastX = x;
			b = h / 3 * Math.random();
			d = h - h / 2 * Math.random();
			a = x;
			c = a + Math.random() * w / 7 + w / 20;
			g.drawLine((int) a, h - (int) b, (int) c, h - (int) d);
			if (v == 0 && c - a < w / 12 && d > 4 * h / 5)
			{
				v = (int) c + w / 60;
				g.drawLine((int) c, h - (int) d, (int) c + w / 60, h - (int) d + h / 25);
				g.drawLine((int) c + w / 30, h - (int) d, (int) c + w / 60, h - (int) d + h / 25);

				c += w / 30;
			}
			a = c;
			b = d;
			c = a + (Math.random() - 0.5) * w / 12;
			d = h / 2 * Math.random();
			g.drawLine((int) a, h - (int) b, (int) c, h - (int) d);
			c = a + Math.random() * w / 7;
			d = h / 3 * Math.random();
			g.drawLine((int) a, h - (int) b, (int) c, h - (int) d);
			x = (int) c;
		}
		fullW = lastX;
	}

	public void draw(Graphics g, ViewPoint myView)
	{
		float view = (float) (myView.getViewingDirection() / (2 * Math.PI));

		int left = (int) ((fullW * (view + 10)) % fullW);
		g.drawImage(im, -left, h, display);
		if (left + w > fullW)
		{
			g.drawImage(im, -left + fullW, h, display);
		}
	}
}
