package minesweeper;

import java.util.Arrays;
import java.util.Random;

import minesweeper.enumTest.squareValue;

public class MinesweeperBoard 
{
	public int width;
	public int height;
	public int numberOfMines;
	squareValue [][] board;
	
	public MinesweeperBoard (int userWidth,int userHeight,int userNumberOfMines)
	{
		//Initializing the instance variables. 
		width=userWidth;
		height=userHeight;
		numberOfMines=userNumberOfMines;
		board = new squareValue[width][height];
		
		//Placing the mines randomly.
		Random random = new Random();
		int mineCounter=0;
		while (mineCounter<numberOfMines)
		{
			int i=random.nextInt(width);
			int j=random.nextInt(height);
			while(board[i][j]!=squareValue.MINE)
			{
				board[i][j]=squareValue.MINE;
				mineCounter++;
			}
		}
		
		//Filling the nonMine squares.
		for (int i=0;i<width;i++)
		{
			for (int j=0;j<height;j++)
			{
				if(board[i][j]!=squareValue.MINE)
				{
					board[i][j]=countMinesAround(i,j);
				}
			}
		}
	}

	//Counts the number of Mines around a cell.
	public squareValue countMinesAround(int i,int j)
	{
		int result = 0;
		if((i!=width-1)&&(board[i+1][j]==squareValue.MINE))
		{
			result=result+1;
		}
		if((i!=0)&&(board[i-1][j]==squareValue.MINE))
		{
			result=result+1;
		}
		if((j!=height-1)&&(board[i][j+1]==squareValue.MINE))
		{
			result=result+1;
		}
		if((j!=0)&&(board[i][j-1]==squareValue.MINE))
		{
			result=result+1;
		}
		if((i!=width-1)&&(j!=height-1)&&(board[i+1][j+1]==squareValue.MINE))
		{
			result=result+1;
		}
		if((i!=0)&&(j!=0)&&(board[i-1][j-1]==squareValue.MINE))
		{
			result=result+1;
		}
		if((i!=width-1)&&(j!=0)&&(board[i+1][j-1]==squareValue.MINE))
		{
			result=result+1;
		}
		if((i!=0)&&(j!=height-1)&&(board[i-1][j+1]==squareValue.MINE))
		{
			result=result+1;
		}
		
		enumTest enumResult=new enumTest(result+"");
		return enumResult.sq;
	}
	

}
