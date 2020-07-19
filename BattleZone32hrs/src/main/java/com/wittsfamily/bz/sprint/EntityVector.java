/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Graphics;
import java.util.Vector;

public class EntityVector extends Vector
{
	public void addEntity(Entity e)
	{
		addElement(e);
	}

	public Entity entityAt(int n)
	{
		return (Entity) elementAt(n);
	}

	public void move(float deltaT)
	{
		for (int i = 0; i < size(); i++)
			entityAt(i).move(deltaT);
	}

	public void removeTheDead()
	{
		for (int i = 0; i < size(); i++)
			if (entityAt(i).isDead()) removeElementAt(i--);
	}

	public void checkCollisions(EntityVector ev)
	{
		for (int i = 0; i < size(); i++)
		{
			Entity a = entityAt(i);
			int j;
			if (ev != this)
				j = 0;
			else
				j = i + 1;
			if (!a.isDead())
			{
				for (; j < ev.size(); j++)
				{
					Entity b = ev.entityAt(j);
					if (a != b && !b.isDead())
					{
						long dx = a.getX() - b.getX();
						long dz = a.getZ() - b.getZ();
						long d = (int) Math.sqrt(dx * dx + dz * dz);
						if (d < a.getExtremity() + b.getExtremity())
						{
							a.collide(b);
							b.collide(a);
							// System.out.println (a+" collided with "+b);
						}
					}
				}
			}
		}
	}

	public void draw(Graphics g, ViewPoint v, int w, int h)
	{
		for (int i = 0; i < size(); i++)
			entityAt(i).draw(g, w, h, v);
	}
}
