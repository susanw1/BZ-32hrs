/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Button;
import java.awt.Event;
import java.awt.Frame;

public class BattleZone extends Applet
{
	private Button startButton;

	Frame theFrame;

	public static AudioClip boomEffect;

	public void init()
	{
		// GameDefaults.loadSounds(this);
		boomEffect = getAudioClip(getCodeBase(), "com/wittsfamily/bz/sprint/BoomEffect.au");
		startButton = new Button("Start game");
		add(startButton);
	}

	public boolean action(Event e, Object ob)
	{
		if (e.target == startButton && (theFrame == null || !theFrame.isVisible()))
		{
			theFrame = new BattleZoneFrame(false);
			return true;
		}
		return false;
	}
}
