package com.gaming.sprites;

import javax.swing.ImageIcon;

public class Player extends Sprite{
	public Player()
	{
		w=150;
		h=200;
		x=2;
		y=400;
		image=new ImageIcon(Player.class.getResource("player.gif"));
	}
	public void move()
	{
		x=x+speed;
	}
	public boolean outOfScreen()
	{
		return x>3000;
	}
}
