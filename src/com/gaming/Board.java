package com.gaming;

import java.awt.*;
//import java.awt.Font;
//import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.gaming.sprites.Player;
import com.gaming.sprites.Sandwich;
import com.gaming.sprites.Sprite;
import com.gaming.sprites.Target;
import com.gaming.sprites.Win;

public class Board extends JPanel{
	Timer timer;
	BufferedImage backgroundImage;
	Win win=new Win();
	Player player;
	Target target[]=new Target[4];
	Sandwich sandwich[]=new Sandwich[4];
	int cnt=0;
	boolean[] collided = new boolean[4];
	public Board()
	{
//		setSize(1500,820);
		setPreferredSize(new Dimension(3300, 820));
		loadBackgroundImage();
		player=new Player();
		
		loadTarget();
		gameLoop();
		
		bindEvents();
		setFocusable(true);
		
	}
	public Dimension getPreferredSize() {
        return new Dimension(3300, 820);
    }
	private void gameOver(Graphics pen)
	{
		if(cnt==4 )
		{
			pen.setFont(new Font("times",Font.BOLD,30));
			pen.setColor(Color.RED);
			pen.drawString("Game Win",2000,900/2);
			pen.drawString("All Apples Collected", 2000,800/2);
			win.draw(pen);
			pen.clearRect(player.x,player.y,player.w,player.h);
//			timer.stop();
			
		}
		for(int i=0;i<target.length;i++)
		{
			if(isCollide(target[i]) )
			{
				if(!collided[i])
				{
					cnt++;
					collided[i] = true; 
					
				}
				target[i].w=0;
				target[i].h=0;
				
//				timer.stop();

			}
		}

		for(int i=0;i<sandwich.length;i++)
		{
			if(isCollideS(sandwich[i]))
			{
				pen.setFont(new Font("times",Font.BOLD,30));
				pen.setColor(Color.RED);
				pen.drawString("Game Over",player.x+30,700/2);
				pen.drawString("You got only "+Integer.toString(cnt)+"apple 😢", player.x+30,800/2);
				
//				pen.clearRect(player.x,player.y,player.w+60,player.h+50);
				timer.stop();
			}
		}
	}
	private boolean isCollideS(Sandwich target)
	{
		int xDistance=Math.abs(player.x-target.x);
		int yDistance=Math.abs(player.y-target.y);
		int maxH=Math.max(player.h, target.h);
		int maxW=Math.max(player.w, target.w);
		return xDistance<=maxW-30 && yDistance<=maxH-150;
	}
	private boolean isCollide(Target target)
	{
		int xDistance=Math.abs(player.x-target.x);
		int yDistance=Math.abs(player.y-target.y);
		int maxH=Math.max(player.h, target.h);
		int maxW=Math.max(player.w, target.w);
		return xDistance<=maxW-40 && yDistance<=maxH-70;
	}
	
	private void bindEvents()
	{
		addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_RIGHT)
				{
					player.speed=5;				
				}
				else if(e.getKeyCode()==KeyEvent.VK_LEFT)
				{
					player.speed=-5;				
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				player.speed=0;
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	private void loadTarget()
	{
		int x=300;
		int gap=380;
		int speed=3;
		
//	    int i= 0;
//	    int j = 0;
//
//	    for (int k = 0; k < 10; k++) {
//	        if (k % 2 == 0 && i < target.length) {
//	        	target[i++]=new Target(x,speed);
//	        	x=x+gap;
//				speed=speed+5;
//	        } else if (j < sandwich.length) {
//	        	sandwich[j++]=new Sandwich(x,speed);
//	        	x=x+gap;
//				speed=speed+5;
//	        }
//	    }
		
		  ArrayList<Integer> indices = new ArrayList<>();
		    for (int i = 0; i < target.length + sandwich.length; i++) {
		        indices.add(i);
		    }
		    Collections.shuffle(indices);

		    // Iterate through shuffled indices and assign targets and sandwiches alternatively
		    int targetIndex = 0;
		    int sandwichIndex = 0;
		    for (int k : indices) {
		        if (k % 2 == 0 && targetIndex < target.length) {
		            target[targetIndex++] = new Target(x, speed);
		        } else if (sandwichIndex < sandwich.length) {
		            sandwich[sandwichIndex++] = new Sandwich(x, speed);
		        }
		        x += gap;
		        speed += 2;
		    }
	}
	
	private void gameLoop()
	{
		timer=new Timer(60,(e)->repaint());
		timer.start();
	}
	
	private void loadBackgroundImage()
	{
		try {
			backgroundImage=ImageIO.read(Board.class.getResource("game-bg.jpg"));
		}
		catch(IOException e)
		{
			System.out.println("Background image not found....");
			System.exit(1);
			e.printStackTrace();
		}
	}
	private void loadwin()
	{
		try {
			backgroundImage=ImageIO.read(Board.class.getResource("win.gif"));
		}
		catch(IOException e)
		{
			System.out.println("Background image not found....");
			System.exit(1);
			e.printStackTrace();
		}
	}
	private void printTarget(Graphics pen, Object[] targets, Object[] sandwiches)
	{
		 int numTargets = targets.length;
		    int numSandwiches = sandwiches.length;
		    
		    Random rand = new Random();
	        int r = rand.nextInt(10);
		    
		    int targetIndex = 0;
		    int sandwichIndex = 0;

		    for (int k = 0; k < 8; k++) {
		        if (k % 2 == 0 && sandwichIndex < numSandwiches) {
		        	((Sprite) sandwiches[sandwichIndex]).draw(pen);
		        	((Sandwich) sandwiches[sandwichIndex]).move();
		        	sandwichIndex++;
		        } else if (targetIndex < numTargets) {
		        	((Sprite) targets[targetIndex]).draw(pen);
		        	((Target) targets[targetIndex]).move();
		        	targetIndex++;
		        }
		    }
		
	}
	public void paintComponent(Graphics pen) {
		super.paintComponent(pen);   //clean up
		//all printing logic will be here
		
		 // Get the current size of the board
	    int width = getWidth();
	    int height = getHeight();

	    // Draw the background image using the current size of the board
	    pen.drawImage(backgroundImage, 0, 0, width, height, null);

//		pen.drawImage(backgroundImage,0,0,1500,820,null);
		player.draw(pen);
		player.move();
		printTarget(pen,target,sandwich);

		gameOver(pen);
	}
}
