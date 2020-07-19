/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public abstract class PlaySession
{
	protected static float strobe = 60;

	protected long startTime;

	protected long lastTime;

	private static long thisTime;

	protected float deltaT, instantFrameRate, frameRate;

	private boolean keepGoing;

	protected BZGame display;

	protected Image im;

	protected int w, h;

	public PlaySession(BZGame com)
	{
		lastTime = thisTime = System.currentTimeMillis();
		display = com;
		keepGoing = true;
	}

	public void gameStrobe()
	{
		int delay = 0;

		instantFrameRate = thisTime - lastTime;

		frameRate = (frameRate * 2 + instantFrameRate) / 3;

		if (frameRate > 120) // places limits on the frameRate
			frameRate = 120;
		else if (frameRate < 30)
		{
			delay = 30 - (int) frameRate;
			frameRate = 30;
		}
		deltaT = frameRate / strobe;

		// System.out.println (deltaT);

		lastTime = thisTime;
		try
		{
			Thread.sleep(10 + delay);
		}
		catch (InterruptedException e)
		{
		}
		thisTime = System.currentTimeMillis();
	}

	public void play()
	{
		Graphics dispGC = display.getGraphics();
		Graphics imGC = null;
		while (keepGoing)
		{
			synchronized (this)
			{
				Dimension ds = display.size();
				if (ds.width != w || ds.height != h)
				{
					im = display.createImage(ds.width, ds.height);
					imGC = im.getGraphics();
					display.setImage(im);
					w = im.getWidth(display);
					h = im.getHeight(display);
					System.out.println("New image: " + ds + w + " " + h);
				}
				render(imGC, w, h);
				display.update(dispGC);
			}
			gameStrobe();
		}
	}

	public synchronized void stop()
	{
		keepGoing = false;
	}

	public static long now()
	{
		return thisTime;
	}

	public abstract void render(Graphics g, int w, int h);
}
