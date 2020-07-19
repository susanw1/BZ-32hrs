/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied 
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public class BZGame extends Panel
		implements Runnable
{
	private Combat combat;

	private Demo d;

	private Image im;

	private Thread t;

	public void paint(Graphics g)
	{
		if (im != null)
		{
			g.drawImage(im, 0, 0, this);
		}
	}

	public void update(Graphics g)
	{
		paint(g);
	}

	public void setImage(Image newIm)
	{
		im = newIm;
	}

	public void start()
	{
		t = new Thread(this);
		t.start();
	}

	public void stop()
	{
		t.stop();
		if (d != null)
		{
			d.stop();
		}
		if (combat != null)
		{
			combat.stop();
		}
	}

	public void run()
	{
		while (true)
		{
			d = new Demo(this);
			d.play();
			d = null;

			combat = new Combat(this);
			combat.play();
			combat = null;
		}
	}

	public boolean mouseDown(Event e, int x, int y)
	{
		if (d != null)
		{
			d.stop();
		}
		else if (combat != null)
		{
			combat.stop();
		}
		// System.out.println ("MouseDown: "
		// +Runtime.getRuntime().freeMemory());
		return true;
	}

	public boolean keyDown(Event e, int key)
	{
		if (combat != null)
		{
			combat.keyDown(key);
		}
		return true;
	}

	public boolean keyUp(Event e, int key)
	{
		if (combat != null)
		{
			combat.keyUp(key);
		}
		return true;
	}
}
