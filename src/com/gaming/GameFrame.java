package com.gaming;
import java.awt.*;

//import javax.swing.JFrame;
import javax.swing.*;

public class GameFrame extends JFrame{
	public GameFrame()
	{
		Board board=new Board();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Game Dev in Java");
		setSize(4000,820);
		setResizable(false);
//		add(board);
//		 setLocationRelativeTo(null);
//	     setVisible(true);
//		
		
		 JPanel contentPanel = new JPanel(); 
         contentPanel.setLayout(new BorderLayout()); 
         
         JTextArea textArea = new JTextArea(10, 40); 
         textArea.setWrapStyleWord(true); 
         textArea.setLineWrap(false); // Disable line wrapping for horizontal scrolling 

         // Create a JScrollPane with a horizontal scrollbar 
         JScrollPane scrollPane = new JScrollPane(board, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 

         // Create a horizontal scrollbar 
         JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar(); 

         // Add a button to simulate content updates 
//         JButton addButton = new JButton("Add Text"); 
//         addButton.addActionListener(new ActionListener() { 
//             @Override
//             public void actionPerformed(ActionEvent e) { 
//                 // Append text horizontally to the JTextArea 
//                 textArea.append("JScrollBar Example. "); 
//             } 
//         }); 

         contentPanel.add(scrollPane, BorderLayout.CENTER); 
//         contentPanel.add(addButton, BorderLayout.SOUTH); 

         // Add the content panel to the frame 
        add(contentPanel); 
        setLocationRelativeTo(null);
        setVisible(true);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameFrame();
		
	}

}
