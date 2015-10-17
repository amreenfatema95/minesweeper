package minesweeper;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import minesweeper.enumTest.squareValue;

public class MinesweeperGUI extends JFrame
{
	 JToolBar toolbar;
	 JPanel topPanel;
	 JPanel centerPanel;
	 JPanel bottomPanel;
	 JButton[][] buttons;
	 ImageIcon newImage;
	 JButton newButton;
	 MinesweeperBoard newBoard = new MinesweeperBoard(9,9,10);
	 JMenuBar menubar;
	 JMenu settings;
	 JCheckBoxMenuItem easy;
	 JCheckBoxMenuItem medium;
	 JCheckBoxMenuItem hard;
	 ButtonGroup difficultyLevel;
	 MyButtonMouseListener[][] ml;
	 int frameWidth=250;
	 int frameHeight=318;
	 int position;
	public MinesweeperGUI()
	{
		createWindow();
	}
	
	public void createWindow()
	{
		//creating a window.
		setSize(frameWidth,frameHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Minesweeper");
		
		//setting a borderlayout within the window.
		setLayout(new BorderLayout());
		
		//creating a top Panel.
		topPanel= new JPanel(new BorderLayout());
		add(topPanel,BorderLayout.NORTH);
		
		//adding a non floatable toolbar to the topPanel.
		toolbar = new JToolBar();
		topPanel.add(toolbar,BorderLayout.EAST);
		toolbar.setFloatable(false);
		
		//Adding a 'New' Button with an image to the toolbar.
		newImage = new ImageIcon("new_icon.png");
		JButton newButton = new JButton("New",newImage);
		newButton.addActionListener(new MyNewBoardActionListener());
		toolbar.add(newButton,BorderLayout.LINE_START);
		
		//adding a menubar to the top Panel.
		menubar = new JMenuBar();
		topPanel.add(menubar,BorderLayout.WEST);
		
		// adding a settings menu item to the menu bar.
		settings = new JMenu("Settings");
		menubar.add(settings);
		
		//adding menuItems to the settings menu.
		easy =new JCheckBoxMenuItem("Easy");
		easy.addActionListener(new MySettingsActionListener());
		settings.add(easy);
		medium = new JCheckBoxMenuItem("Medium");
		medium.addActionListener(new MySettingsActionListener());
		settings.add(medium);
		hard = new JCheckBoxMenuItem("Hard");
		hard.addActionListener(new MySettingsActionListener());
		settings.add(hard);
		
		//creating a group for the three menu items.
		difficultyLevel = new ButtonGroup();
		difficultyLevel.add(easy);
		difficultyLevel.add(medium);
		difficultyLevel.add(hard);
		
		//Creating a bottom panel to store the minesweeper board.
		bottomPanel = new JPanel();
		add(bottomPanel,BorderLayout.CENTER);
	}

	//Creating a mouseListener for the buttons on the grid.
	class MyButtonMouseListener extends MouseAdapter
	{
		int x;
		int y;
		public MyButtonMouseListener(int i,int j)
		{
			x=i;
			y=j;
			position = newBoard.board[x][y].ordinal();
		}
		public void mouseClicked(MouseEvent e)
		{
			//rightClicks
			if (e.getModifiers()==MouseEvent.BUTTON3_MASK)
			{
				if(buttons[x][y].getText()=="F")
				{
					buttons[x][y].setText("");
				}
				else if (buttons[x][y].getText()=="")
				{
					buttons[x][y].setText("F");
				}
			}
			
			//left Clicks
			if (e.getModifiers()==MouseEvent.BUTTON1_MASK)
			{
				leftClickAction(x,y);
			}
		}
	}
	
	//Creating an ActionListener for the menu items on the settings menu.
	class MySettingsActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			 String level=e.getActionCommand();
			if (level=="Easy")
			{
				frameWidth=250;
				frameHeight=318;
				newBoard = new MinesweeperBoard(9,9,10);
			}
			else if (level=="Medium")
			{
				frameWidth=400;
				frameHeight=400;
				newBoard = new MinesweeperBoard(16,16,40);
			}
			else if (level=="Hard")
			{
				frameWidth=450;
				frameHeight=600;
				newBoard = new MinesweeperBoard(30,16,99);
			}
		}
	}

	//Creating an ActionListener for a newly created board.
	class MyNewBoardActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//Creating a button with a mouse Listener in the bottom panel for each square on the minesweeper board.
			bottomPanel.removeAll();
			setSize(frameWidth, frameHeight);
			bottomPanel.setLayout(new GridLayout(newBoard.width,newBoard.height));
			buttons = new JButton[newBoard.width][newBoard.height];
			ml = new MyButtonMouseListener[newBoard.width][newBoard.height];
				for (int i=0;i<newBoard.width;i++)
				{
					for (int j=0;j<newBoard.height;j++)
					{
						buttons[i][j]=new JButton();
						bottomPanel.add(buttons[i][j]);
						setVisible(true);
						ml[i][j] = new MyButtonMouseListener(i,j);
						buttons[i][j].addMouseListener(ml[i][j]);
					}
				}
			}
	}

	//counts the number of flags around a given button.
	public int countFlagsAround(int i,int j)
	{
		int result = 0;
		if((i!=buttons.length-1)&&(buttons[i+1][j].getText()=="F"))
		{
			result=result+1;
		}
		if((i!=0)&&(buttons[i-1][j].getText()=="F"))
		{
			result=result+1;
		}
		if((j!=buttons[0].length-1)&&(buttons[i][j+1].getText()=="F"))
		{
			result=result+1;
		}
		if((j!=0)&&(buttons[i][j-1].getText()=="F"))
		{
			result=result+1;
		}
		if((i!=buttons.length-1)&&(j!=buttons[0].length-1)&&(buttons[i+1][j+1].getText()=="F"))
		{
			result=result+1;
		}
		if((i!=0)&&(j!=0)&&(buttons[i-1][j-1].getText()=="F"))
		{
			result=result+1;
		}
		if((i!=buttons.length-1)&&(j!=0)&&(buttons[i+1][j-1].getText()=="F"))
		{
			result=result+1;
		}
		if((i!=0)&&(j!=buttons[0].length-1)&&(buttons[i-1][j+1].getText()=="F"))
		{
			result=result+1;
		}
		return result;
	}
	
	//Details the set of actions to be executed when a button is left clicked.
	public void leftClickAction(int x, int y)
	{
		position = newBoard.board[x][y].ordinal();
		if(buttons[x][y].getText()!="F")
		{
			buttons[x][y].setEnabled(false);
			if (position==0)
			{
				buttons[x][y].setText("");
			}
			else if (position==9)
			{
				JOptionPane.showMessageDialog(bottomPanel, "Game Over!", null, JOptionPane.INFORMATION_MESSAGE);
				for(int i =0;i<newBoard.width;i++)
				{
					for(int j=0;j<newBoard.height;j++)
					{
						buttons[i][j].setEnabled(false);
						buttons[i][j].removeMouseListener(ml[i][j]);
					}
				}
			}
			else
			{
				buttons[x][y].setText(position+"");
			}
		}
	}
}
