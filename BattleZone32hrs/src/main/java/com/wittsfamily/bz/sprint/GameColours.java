/*
Copyright Susan Witts 1996. All rights reserved.
This source code remains the property of Susan Witts, and should not be copied
or used for any purpose without prior written permission.

 */

package com.wittsfamily.bz.sprint;

import java.awt.Color;

public class GameColours
{
	public static Color[] shadesOfGreen;

	public static final Color tankGreen = Color.green;

	static
	{
		shadesOfGreen = new Color[10];
		for (int i = 0; i < 10; i++)
		{
			shadesOfGreen[i] = new Color(10 * i, 20 * (i + 1) + 50, 10 * i);
		}
	}
}
