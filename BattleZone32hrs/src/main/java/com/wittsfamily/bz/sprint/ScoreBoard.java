/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class ScoreBoard
{
	private int score, highest;

	private int lives;

	private int nextBreakPoint;

	private boolean playerDead;

	private Vector highScores;

	public ScoreBoard(int lives)
	{
		this.highest = 0;
		this.highScores = new Vector();
		newGame(lives);
	}

	public void newGame(int lives)
	{
		this.score = 0;
		this.lives = lives;
		this.nextBreakPoint = 25000;
		this.playerDead = false;
	}

	public int currentScore()
	{
		return score;
	}

	public void addToScore(Entity e)
	{
		if (e != null)
		{
			score += e.scorePoints();
			if (score >= nextBreakPoint)
			{
				nextBreakPoint *= 2;
				lives++;
			}
		}
	}

	public void playerTookHit()
	{
		if (!playerDead)
		{
			lives--;
			if (lives == 0)
			{
				playerDead = true;
				highScores.addElement(new Integer(score));
				if (score > highest) highest = score;
			}
		}
	}

	public boolean isDead()
	{
		return playerDead;
	}

	public int getHighestScore()
	{
		return highest;
	}

	public void draw(Graphics g, int w, int h)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, w, h);
		g.setColor(Color.green);
		g.drawString("Lives: " + lives, 0, h / 3);
		g.drawString("Score: " + score, 0, 2 * h / 3);
		g.drawString("Highest: " + highest, 0, h - 2);
	}
}
