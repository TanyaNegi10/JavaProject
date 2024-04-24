package com.gaming.sprites;

import javax.swing.ImageIcon;

public class Win extends Sprite{
	public Win()
	{
		y=500/2;
		x=2250;
		w=250;
		h=250;
		image =new ImageIcon(Target.class.getResource("win.gif"));
	}
	
}
