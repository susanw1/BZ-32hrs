/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.applet.Applet;
import java.awt.Event;
import java.awt.Frame;

public class BattleZoneFrame extends Frame
{
	private BZGame bzGame;

	private boolean standalone;

	public BattleZoneFrame(boolean standalone)
	{
		this.standalone = standalone;
		setSize(800, 600);
		bzGame = new BZGame();
		add("Center", bzGame);
		show();
		bzGame.start();

		BattleZone.boomEffect = Applet.newAudioClip(getClass().getResource("/com/wittsfamily/bz/sprint/BoomEffect.au"));
	}

	public boolean handleEvent(Event e)
	{
		if (e.id == Event.WINDOW_DESTROY)
		{
			hide();
			bzGame.stop();
			dispose();
			if (standalone)
			{
				System.exit(0);
			}
		}
		return super.handleEvent(e);
	}

	public static void main(String arg[])
	{
		new BattleZoneFrame(true);
	}
}
