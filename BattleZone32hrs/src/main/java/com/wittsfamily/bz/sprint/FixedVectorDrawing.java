/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

public class FixedVectorDrawing extends VectorDrawing
{
	private final static int[][] tankPts =
	{
			{ 125, 125, 125, 125, 75, 75, 75, -125, -125, -125, -125, -75, -75, -75, -5, -5, 5, 5, -5, -5, 5, 5, 0, 0 },
			{ 40, 40, 0, 0, 80, 80, 140, 40, 40, 0, 0, 80, 80, 140, 115, 105, 105, 115, 115, 105, 105, 115, 140, 160 },
			{ 200, -200, 150, -150, 150, -150, -150, 200, -200, 150, -150, 150, -150, -150, 0, 0, 0, 0, 200, 200, 200, 200, -150,
					-150 },
			{ 0, 1, 8, 7, 2, 3, 10, 9, 4, 5, 12, 11, 0, 1, 7, 8, 0, 1, 7, 8, 4, 5, 11, 12, 6, 14, 15, 16, 17, 18, 19, 20, 21, 14,
					15, 16, 17, 22 },
			{ 1, 8, 7, 0, 3, 10, 9, 2, 5, 12, 11, 4, 2, 3, 9, 10, 4, 5, 11, 12, 6, 6, 13, 13, 13, 15, 16, 17, 14, 19, 20, 21, 18,
					18, 19, 20, 21, 23 } }, tankRadarPts =
	{
	{ -10, -10, 10, 10 },
	{ 0, 15, 0, 15 },
	{ 0, 0, 0, 0 },
	{ 0, 1, 3, 2 },
	{ 1, 3, 2, 0 } }, superTankPts =
	{
			{ 125, 125, 125, 125, 75, 60, 60, -125, -125, -125, -125, -75, -60, -60, -5, -5, 5, 5, -5, -5, 5, 5, 0, 0, 0 },
			{ 30, 30, 0, 0, 80, 75, 140, 30, 30, 0, 0, 80, 75, 140, 115, 105, 105, 115, 115, 105, 105, 115, 140, 160, 39 },
			{ 200, -200, 150, -150, -150, -120, -110, 200, -200, 150, -150, -150, -120, -110, 0, 0, 0, 0, 200, 200, 200, 200, -110,
					-110, 100 },
			{ 0, 1, 8, 7, 2, 3, 10, 9, 0, 1, 7, 8, 0, 1, 7, 8, 4, 5, 6, 24, 5, 6, 12, 13, 24, 14, 15, 16, 17, 18, 19, 20, 21, 14,
					15, 16, 17, 22 },
			{ 1, 8, 7, 0, 3, 10, 9, 2, 2, 3, 9, 10, 4, 4, 11, 11, 11, 6, 24, 5, 12, 13, 13, 24, 12, 15, 16, 17, 14, 19, 20, 21, 18,
					18, 19, 20, 21, 23 } }, superTankRadarPts =
	{
	{ -10, -10, 10, 10 },
	{ 0, 15, 0, 15 },
	{ 0, 0, 0, 0 },
	{ 0, 1, 3, 2 },
	{ 1, 3, 2, 0 } }, saucerPts =
	{
	{ 300, 212, 0, -212, -300, -212, 0, 212, 0, 0 },
	{ 60, 60, 60, 60, 60, 60, 60, 60, 0, 120 },
	{ 0, -212, -300, -212, 0, 212, 300, 212, 0, 0 },
	{ 0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7 },
	{ 1, 2, 3, 4, 5, 6, 7, 0, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9 } }, shellPts =
	{
	{ 0, -10, 10, 10, -10 },
	{ 0, -10, -10, 10, 10 },
	{ 30, -30, -30, -30, -30 },
	{ 0, 0, 0, 0, 1, 2, 3, 4 },
	{ 1, 2, 3, 4, 2, 3, 4, 1 } }, missilePts =
	{
	{ 0, -30, 30, 45, 30, -30, -45 },
	{ 40, 80, 80, 40, 0, 0, 40 },
	{ 0, -200, -200, -200, -200, -200, -200 },
	{ 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6 },
	{ 1, 2, 3, 4, 5, 6, 2, 3, 4, 5, 6, 1 }, }, blockPts =
	{
	{ -100, 100, 100, -100, -100, 100, 100, -100 },
	{ 0, 0, 200, 200, 0, 0, 200, 200 },
	{ -100, -100, -100, -100, 100, 100, 100, 100 },
	{ 0, 1, 2, 3, 0, 1, 2, 3, 4, 5, 6, 7 },
	{ 1, 2, 3, 0, 4, 5, 6, 7, 5, 6, 7, 4 } }, pyramidPts =
	{
	{ -100, 100, 100, -100, 0 },
	{ 0, 0, 0, 0, 200 },
	{ -100, -100, 100, 100, 0 },
	{ 0, 1, 2, 3, 0, 1, 2, 3 },
	{ 1, 2, 3, 0, 4, 4, 4, 4 } }, deadPlayerPts1 =
	{
	{ 0, 200, 300, 280, 220, 350, -240, -310 },
	{ 0, -40, 250, 500, -240, -140, -160, 80 },
	{ 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 0, 1, 1, 1, 2, 0, 0 },
	{ 1, 2, 4, 5, 3, 6, 7 } }, deadPlayerPts2 =
	{
	{ 0, 200, 300, 280, 220, 350, -240, -310, 510, 810, -510, -640, -420, },
	{ 0, -40, 250, 500, -240, -140, -160, 80, 100, 630, -310, 310, 940, },
	{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
	{ 0, 1, 1, 1, 2, 0, 0, 5, 3, 6, 7, 7 },
	{ 1, 2, 4, 5, 3, 6, 7, 8, 9, 10, 11, 12 } }, deadPlayerPts3 =
	{
	{ 0, 200, 300, 280, 220, 350, -240, -310, 510, 810, -510, -640, -420, 400, 1000, 1100, -100, 240, -700, -1230 },
	{ 0, -40, 250, 500, -240, -140, -160, 80, 100, 630, -310, 310, 940, 1000, 200, -300, -400, -960, -920, 630 },
	{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 0, 1, 1, 1, 2, 0, 0, 5, 3, 6, 7, 7, 3, 9, 8, 4, 16, 10, 11 },
	{ 1, 2, 4, 5, 3, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 } }, deadTankSection1Pts =
	{ tankPts[0], tankPts[1], tankPts[2],
	{ 0, 1, 3, 2, 1, 8, 10, 3, 10, 9, 8, 9 },
	{ 1, 3, 2, 0, 8, 10, 3, 1, 9, 2, 9, 0 } }, deadTankSection2Pts =
	{ tankPts[0], tankPts[1], tankPts[2],
	{ 1, 8, 9, 0, 8, 7, 7, 4, 1, 12, 11, 8, 7, 0 },
	{ 8, 9, 0, 1, 7, 9, 0, 1, 12, 11, 4, 12, 11, 4 } }, deadTankSection3Pts =
	{ tankPts[0], tankPts[1], tankPts[2],
	{ 6, 13, 12, 5, 4, 4, 11, 11, 4, 4, 5, 12, 22 },
	{ 13, 12, 5, 6, 5, 6, 12, 13, 11, 1, 1, 1, 23 } }, deadTankSection4Pts =
	{ tankPts[0], tankPts[1], tankPts[2],
	{ 14, 15, 16, 17, 18, 19, 20, 21, 14, 15, 16, 17 },
	{ 15, 16, 17, 14, 19, 20, 21, 18, 18, 19, 20, 21 } }, deadShellSectionPts =
	{ shellPts[0], shellPts[1], shellPts[2],
	{ 0, 1, 2 },
	{ 1, 2, 0 } }, deadMissileSection1Pts =
	{ missilePts[0], missilePts[1], missilePts[2],
	{ 0, 0, 0, 1, 2, 3 },
	{ 1, 2, 3, 2, 3, 1 } }, deadMissileSection2Pts =
	{ missilePts[0], missilePts[1], missilePts[2],
	{ 0, 0, 0, 3, 4, 5 },
	{ 3, 4, 5, 4, 5, 3 } }, deadMissileSection3Pts =
	{ missilePts[0], missilePts[1], missilePts[2],
	{ 0, 0, 0, 5, 6, 1 },
	{ 5, 6, 1, 6, 1, 5 } }, deadMissileSection4Pts =
	{ missilePts[0], missilePts[1], missilePts[2],
	{ 0, 0, 0, 1, 2, 3 },
	{ 1, 2, 3, 2, 3, 1 } };

	public static FixedVectorDrawing[] tank = new FixedVectorDrawing[2], superTank = new FixedVectorDrawing[2],
			saucer = new FixedVectorDrawing[1], shell = new FixedVectorDrawing[1], missile = new FixedVectorDrawing[1],
			block = new FixedVectorDrawing[1], pyramid = new FixedVectorDrawing[1], deadPlayer = new FixedVectorDrawing[3],
			deadShell = new FixedVectorDrawing[1], deadTankSection1 = new FixedVectorDrawing[1],
			deadTankSection2 = new FixedVectorDrawing[1], deadTankSection3 = new FixedVectorDrawing[1],
			deadTankSection4 = new FixedVectorDrawing[1], deadMissileSection1 = new FixedVectorDrawing[1],
			deadMissileSection2 = new FixedVectorDrawing[1], deadMissileSection3 = new FixedVectorDrawing[1],
			deadMissileSection4 = new FixedVectorDrawing[1];

	static
	{
		tank[0] = new FixedVectorDrawing();
		tank[1] = new FixedVectorDrawing();
		tank[0].init(0, 0, 0, tankPts);
		tank[1].init(0, 160, -150, tankRadarPts);
		superTank[0] = new FixedVectorDrawing();
		superTank[1] = new FixedVectorDrawing();
		superTank[0].init(0, 0, 0, superTankPts);
		superTank[1].init(0, 160, -110, superTankRadarPts);
		saucer[0] = new FixedVectorDrawing();
		saucer[0].init(0, 0, 0, saucerPts);
		missile[0] = new FixedVectorDrawing();
		missile[0].init(0, 0, 0, missilePts);
		shell[0] = new FixedVectorDrawing();
		shell[0].init(0, 0, 0, shellPts);
		block[0] = new FixedVectorDrawing();
		block[0].init(0, 0, 0, blockPts);
		pyramid[0] = new FixedVectorDrawing();
		pyramid[0].init(0, 0, 0, pyramidPts);
		deadPlayer[0] = new FixedVectorDrawing();
		deadPlayer[1] = new FixedVectorDrawing();
		deadPlayer[2] = new FixedVectorDrawing();
		deadPlayer[0].init(0, 100, 3000, deadPlayerPts1);
		deadPlayer[1].init(0, 100, 3000, deadPlayerPts2);
		deadPlayer[2].init(0, 100, 3000, deadPlayerPts3);
		deadTankSection1[0] = new FixedVectorDrawing();
		deadTankSection1[0].init(0, 0, 0, deadTankSection1Pts);
		deadTankSection2[0] = new FixedVectorDrawing();
		deadTankSection2[0].init(0, 0, 0, deadTankSection2Pts);
		deadTankSection3[0] = new FixedVectorDrawing();
		deadTankSection3[0].init(0, 0, 0, deadTankSection3Pts);
		deadTankSection4[0] = new FixedVectorDrawing();
		deadTankSection4[0].init(0, 0, 0, deadTankSection4Pts);
		deadShell[0] = new FixedVectorDrawing();
		deadShell[0].init(0, 0, 0, deadShellSectionPts);
		deadMissileSection1[0] = new FixedVectorDrawing();
		deadMissileSection1[0].init(0, 0, 0, deadMissileSection1Pts);
		deadMissileSection2[0] = new FixedVectorDrawing();
		deadMissileSection2[0].init(0, 0, 0, deadMissileSection2Pts);
		deadMissileSection3[0] = new FixedVectorDrawing();
		deadMissileSection3[0].init(0, 0, 0, deadMissileSection3Pts);
		deadMissileSection4[0] = new FixedVectorDrawing();
		deadMissileSection4[0].init(0, 0, 0, deadMissileSection4Pts);
	}

	public void init(int x, int y, int z, int[][] ptInfo)
	{
		xPos = x;
		yPos = y;
		zPos = z;
		xC = ptInfo[0];
		yC = ptInfo[1];
		zC = ptInfo[2];
		vecS = ptInfo[3];
		vecE = ptInfo[4];
		xP = new int[xC.length];
		yP = new int[xC.length];
	}

	public int getHeight()
	{
		int hMax = 0;
		for (int i = 0; i < yC.length; i++)
		{
			if (yC[i] > hMax) hMax = yC[i];
		}
		return hMax;
	}
}
