/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

public class GamePlay
{
	private Combat theCombat;

	private static final int MAX_AGGRESSORS = 1;

	private int MAX_SAUCERS = 10;

	private int MAX_OBJECTS = 30;

	private int numSaucers;

	private int numAggressors;

	private Player localPlayer;

	private int aggro;

	public GamePlay(Combat c)
	{
		theCombat = c;
		makeLocalPlayer(new Player());

		for (int i = 0; i < MAX_OBJECTS / 2; i++)
		{
			Entity e = new Block();
			theCombat.addNewEntity(e);
			e.setInitialState(fullFieldPos(), 0, fullFieldPos(), 0, theCombat);
			e = new Pyramid();
			theCombat.addNewEntity(e);
			e.setInitialState(fullFieldPos(), 0, fullFieldPos(), 0, theCombat);
		}

		for (int i = 0; i < MAX_AGGRESSORS; i++)
		{
			makeTank(new Tank());
		}
	}

	public void entityDied(Entity e)
	{
		ScoreBoard sb = theCombat.getScoreBoard();
		if (e instanceof Player)
		{
			sb.playerTookHit();
		}
		else if (e instanceof Debris)
		{
			Debris d = (Debris) e;

			if (d.isLastPieceOfDebris())
			{
				if (d instanceof DeadPlayer)
				{
					if (!sb.isDead())
					{
						makeLocalPlayer(new Player());
					}
				}
				else if (e instanceof DeadTank || e instanceof DeadMissile)
				{
					float r = (float) Math.random();
					if (sb.currentScore() >= 5000 && (r > 0.8 || (r > 0.3 && e instanceof DeadMissile)))
						makeMissile(new Missile());
					else
					{
						if (sb.currentScore() >= 25000 && Math.random() > 0.5)
							makeTank(new SuperTank());
						else
							makeTank(new Tank());
					}
					numAggressors++;
					if (numSaucers < MAX_SAUCERS && sb.currentScore() >= 5000)
					{
						makeSaucer(new Saucer());
						numSaucers++;
					}
				}
			}
			d.remove();
		}
		else if (e instanceof Saucer)
		{
			numSaucers--;
		}
	}

	public Player getLocalPlayer()
	{
		return localPlayer;
	}

	private void makeLocalPlayer(Player localPlayer)
	{
		this.localPlayer = localPlayer;
		localPlayer.setInitialState(0, 0, 0, 0, theCombat);
		theCombat.addNewEntity(localPlayer);
		theCombat.addNewGlobalEnemy(localPlayer);
	}

	private void makeTank(Aggressor tank)
	{
		theCombat.addNewEntity(tank);
		tank.addEnemy(localPlayer);
		if (aggro < 8)
		{
			tank.setInitialState(fullFieldPos() * aggro / 8, 0, Math.abs(fullFieldPos()) / 2 + 1000, (float) Math.random() * 6,
					theCombat);
		}
		else
		{
			tank.setInitialState(fullFieldPos(), 0, fullFieldPos(), (float) Math.random() * 6, theCombat);
		}
		tank.setAggression(aggro++);
	}

	private void makeMissile(Missile missile)
	{
		theCombat.addNewEntity(missile);
		missile.addEnemy(localPlayer);
		int xInit = Math.min(fullFieldPos() * aggro / 100, 10000);
		int zInit = 4000;
		float angle = -localPlayer.getViewingDirection();
		int x = (int) (xInit * Math.cos(angle) - zInit * Math.sin(angle)) + localPlayer.getX();
		int z = (int) (zInit * Math.cos(angle) + xInit * Math.sin(angle)) + localPlayer.getZ();
		missile.setInitialState(x, 1000, z, 3 + (float) (Math.random() - 0.5), theCombat);
		missile.setAggression(aggro++);
	}

	private void makeSaucer(Saucer s)
	{
		s.setInitialState(fullFieldPos(), 40, fullFieldPos(), 0f, theCombat);
		theCombat.addNewEntity(s);
	}

	private int fullFieldPos()
	{
		return (int) ((Math.random() * 20000) - 10000);
	}
}
