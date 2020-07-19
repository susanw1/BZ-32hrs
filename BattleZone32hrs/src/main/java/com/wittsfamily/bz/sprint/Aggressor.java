/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.
 */

package com.wittsfamily.bz.sprint;

public abstract class Aggressor extends Target
{
	protected EntityVector enemies;

	private int currentEnemy;

	protected int aggression;

	public Aggressor()
	{
		enemies = new EntityVector();
	}

	public abstract void shellExploded(Entity target);

	protected void addEnemy(Target e)
	{
		enemies.addEntity(e);
	}

	public void setAggression(int aggro)
	{
		aggression = aggro;
	}

	protected Target getCurrentEnemy()
	{
		if (currentEnemy < enemies.size() && !enemies.entityAt(currentEnemy).isDead())
			return (Target) enemies.entityAt(currentEnemy);

		while (enemies.size() > 0)
		{
			currentEnemy = (int) (Math.random() * enemies.size());
			if (enemies.entityAt(currentEnemy).isDead())
			{
				enemies.removeElementAt(currentEnemy);
			}
			else
			{
				return (Target) enemies.entityAt(currentEnemy);
			}
		}
		return null;
	}

	public void fire(Shell s)
	{
		int z = (int) (getZ() + getExtremity() * Math.cos(getShapeDirection()));
		int x = (int) (getX() + getExtremity() * Math.sin(getShapeDirection()));
		s.setInitialState(x, 100, z, getShapeDirection(), theCombat);
		s.setShooter(this);
		theCombat.addNewEntity(s);
	}

	public float bearing(Entity e)
	{
		double eBearing = Math.atan2(e.getX() - getX(), e.getZ() - getZ()) - getMotionDirection();
		// System.out.println ("bearing: "+ eBearing);

		if (eBearing > Math.PI)
			eBearing -= 2 * Math.PI;
		else if (eBearing < -Math.PI) eBearing += 2 * Math.PI;
		return (float) eBearing;
	}
}
