package com.gaming.sprites;

import javax.swing.ImageIcon;

public class Sandwich extends Sprite{
	public Sandwich(int x,int speed)
	{
		y=100;
		this.x=x;
		this.speed=speed;
		w=50;
		h=50;
		image =new ImageIcon(Target.class.getResource("sandwich.gif"));
	}
	public void move()
	{
		if(y>800)
		{
			y=0;
		}
		y=y+speed;
	
	}
	

}
