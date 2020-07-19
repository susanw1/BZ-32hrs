/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Color;
import java.awt.Graphics;

public class Combat extends PlaySession
{
	private EntityVector entities;

	private EntityVector protoEntities;

	private EntityVector debris;

	private EntityVector obstacles;

	private Radar theRadar;

	private Scenery theScenery;

	private ScoreBoard scoreBoard;

	private MessageBoard messageBoard;

	private Graphics fullDisplayGC, viewGC, radarGC, scoreBoardGC, messageBoardGC;

	private int radarHeight, viewHeight;

	private GamePlay gamePlay;

	public Combat(BZGame bzg)
	{
		super(bzg);
		scoreBoard = new ScoreBoard(3);
		messageBoard = new MessageBoard(this);

		entities = new EntityVector();
		protoEntities = new EntityVector();
		debris = new EntityVector();
		obstacles = new EntityVector();
		theRadar = new Radar();
		theScenery = new LineDrawRandomScenery(bzg);
		gamePlay = new GamePlay(this);

		includeNewEntities();
	}

	public void addNewEntity(Entity e)
	{
		protoEntities.addEntity(e);
	}

	public void addNewGlobalEnemy(Target baddie)
	{
		for (int i = 0; i < entities.size(); i++)
		{
			Entity e = entities.entityAt(i);
			if (e instanceof Aggressor) ((Aggressor) e).addEnemy(baddie);
		}
	}

	private void includeNewEntities()
	{
		for (int i = 0; i < protoEntities.size(); i++)
		{
			Entity e = protoEntities.entityAt(i);
			if (e.isReal())
			{
				if (e instanceof Debris)
					debris.addEntity(e);
				else if (e instanceof Obstacle)
					obstacles.addEntity(e);
				else
					entities.addEntity(e);
				protoEntities.removeElementAt(i--);
			}
			// System.out.println(e.toString());
		}
	}

	public void render(Graphics g, int w, int h)
	{
		if (g != fullDisplayGC)
		{
			fullDisplayGC = g;
			radarHeight = h / 5;
			viewHeight = h - radarHeight;
			viewGC = fullDisplayGC.create(0, h - viewHeight, w, viewHeight);
			radarGC = fullDisplayGC.create((w - radarHeight) / 2, 0, radarHeight, radarHeight);
			radarGC.translate(radarHeight / 2, radarHeight / 2);
			theRadar.clear(radarGC, radarHeight);
			scoreBoardGC = fullDisplayGC.create((w + radarHeight) / 2, 0, (w - radarHeight) / 2, radarHeight);
			messageBoardGC = fullDisplayGC.create(0, 0, (w - radarHeight) / 2, radarHeight);
			theScenery.setScenery(w, h - radarHeight);
		}
		ViewPoint myView = (ViewPoint) gamePlay.getLocalPlayer();

		entities.move(deltaT);
		debris.move(deltaT);

		entities.checkCollisions(obstacles);
		entities.checkCollisions(entities);

		entities.removeTheDead();
		debris.removeTheDead();
		entities.removeTheDead();

		includeNewEntities();

		viewGC.setColor(Color.black);
		viewGC.fillRect(0, 0, w, viewHeight);

		theRadar.draw(radarGC, entities, myView, deltaT, radarHeight, 0.01f);
		theScenery.draw(viewGC, myView);

		entities.draw(viewGC, myView, w, viewHeight);
		debris.draw(viewGC, myView, w, viewHeight);
		obstacles.draw(viewGC, myView, w, viewHeight);

		scoreBoard.draw(scoreBoardGC, w - radarHeight / 2, radarHeight);
		messageBoard.draw(messageBoardGC, w - radarHeight / 2, radarHeight);
		drawGameDisplay();
	}

	private void drawGameDisplay()
	{
		fullDisplayGC.setColor(Color.green);
		fullDisplayGC.drawLine(0, radarHeight, w, radarHeight);
		fullDisplayGC.drawLine((w - radarHeight) / 2, 0, (w - radarHeight) / 2, radarHeight);
		fullDisplayGC.drawLine((w + radarHeight) / 2, 0, (w + radarHeight) / 2, radarHeight);

		boolean targetInSight = theRadar.isTargetInSight();

		if (targetInSight)
			viewGC.setColor(GameColours.shadesOfGreen[8]);
		else
			viewGC.setColor(GameColours.shadesOfGreen[4]);

		int w2 = w / 2, h2 = viewHeight / 2;
		int len = w / 16;

		viewGC.drawLine(w2, h2 - 3 * len, w2, h2 - 2 * len);
		viewGC.drawLine(w2, h2 + 3 * len, w2, h2 + 2 * len);
		viewGC.drawLine(w2 - 2 * len, h2 - 2 * len, w2 + 2 * len, h2 - 2 * len);
		viewGC.drawLine(w2 - 2 * len, h2 + 2 * len, w2 + 2 * len, h2 + 2 * len);
		if (targetInSight)
		{
			viewGC.drawLine(w2 - 2 * len, h2 - 2 * len, w2 - len, h2 - len);
			viewGC.drawLine(w2 + 2 * len, h2 + 2 * len, w2 + len, h2 + len);
			viewGC.drawLine(w2 - 2 * len, h2 + 2 * len, w2 - len, h2 + len);
			viewGC.drawLine(w2 + 2 * len, h2 - 2 * len, w2 + len, h2 - len);
		}
		else
		{
			viewGC.drawLine(w2 - 2 * len, h2 - 2 * len, w2 - 2 * len, h2 - len);
			viewGC.drawLine(w2 + 2 * len, h2 + 2 * len, w2 + 2 * len, h2 + len);
			viewGC.drawLine(w2 - 2 * len, h2 + 2 * len, w2 - 2 * len, h2 + len);
			viewGC.drawLine(w2 + 2 * len, h2 - 2 * len, w2 + 2 * len, h2 - len);
		}
	}

	public ScoreBoard getScoreBoard()
	{
		return scoreBoard;
	}

	public Radar getRadar()
	{
		return theRadar;
	}

	public GamePlay getGamePlay()
	{
		return gamePlay;
	}

	public void keyDown(int key)
	{
		Player me = gamePlay.getLocalPlayer();

		switch (key)
		{
		case 'p':
			me.setCtrl(Player.FR);
			break;
		case 'l':
			me.setCtrl(Player.BR);
			break;
		case 'q':
			me.setCtrl(Player.FL);
			break;
		case 'a':
			me.setCtrl(Player.BL);
			break;
		case '\n':
			me.setCtrl(Player.FIRE);
			break;
		}
		// System.out.println ("keydown: "+key);
	}

	public void keyUp(int key)
	{
		Player me = gamePlay.getLocalPlayer();
		switch (key)
		{
		case 'p':
			me.unsetCtrl(Player.FR);
			break;
		case 'l':
			me.unsetCtrl(Player.BR);
			break;
		case 'q':
			me.unsetCtrl(Player.FL);
			break;
		case 'a':
			me.unsetCtrl(Player.BL);
			break;
		}
		// System.out.println ("keyup: "+key);
	}
}
