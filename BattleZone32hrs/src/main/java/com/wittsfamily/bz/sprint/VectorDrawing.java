/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Graphics;

public abstract class VectorDrawing extends VisibleForm
{
	protected int[] xC, yC, zC;

	protected int[] vecS, vecE;

	protected int[] xP, yP;

	public abstract void init(int x, int y, int z, int[][] ptInfo);

	public void draw(Graphics g, int w, int h, ViewPoint v, Entity e, float spin)
	{
		g.setColor(col);

		float realA = e.getShapeDirection() - v.getViewingDirection();
		float elev = e.getElevationDirection();

		float xPosT, yPosT, zPosT;

		float xT = e.getX() - v.getX();
		yPosT = e.getY() - v.getYViewPoint() + yPos;
		float zT = e.getZ() - v.getZ();

		zPosT = zT * (float) Math.cos(v.getViewingDirection()) + xT * (float) Math.sin(v.getViewingDirection()) + zPos
				* (float) Math.cos(realA) - xPos * (float) Math.sin(realA);
		xPosT = xT * (float) Math.cos(v.getViewingDirection()) - zT * (float) Math.sin(v.getViewingDirection()) + xPos + xPos
				* (float) Math.cos(realA) + zPos * (float) Math.sin(realA);

		realA += spin;

		if (zPosT > 0)
		{
			boolean drawable = true;
			for (int i = 0; i < xC.length; i++)
			{
				float xA = xC[i], yA = yC[i], zA = zC[i];

				float xB = xA, yB = yA, zB = zA;

				float xR, yR, zR;

				if (e.elevationUser)
				{
					xB = xA;
					yB = yA * (float) Math.cos(elev) + zA * (float) Math.sin(elev);
					zB = zA * (float) Math.cos(elev) - yA * (float) Math.sin(elev);
				}

				xR = xB * (float) Math.cos(realA) + zB * (float) Math.sin(realA) + xPosT;
				yR = yB + yPosT;
				zR = zB * (float) Math.cos(realA) - xB * (float) Math.sin(realA) + zPosT;
				xP[i] = (int) (w * xR / zR) + w / 2;
				yP[i] = h / 2 - (int) (w * yR / zR);
				if (zR < 1 || Math.abs(xP[i]) > 32000) drawable = false;
			}

			if (drawable)
			{
				for (int i = 0; i < vecS.length; i++)
				{
					g.drawLine(xP[vecS[i]], yP[vecS[i]], xP[vecE[i]], yP[vecE[i]]);
				}
			}
		}
	}
}
