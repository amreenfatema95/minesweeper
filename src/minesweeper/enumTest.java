package minesweeper;

public class enumTest {
	
	public enum squareValue
	{
		ZERO,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,MINE;
	}
	
	squareValue sq;
	
	//Encodes all possible values of a square on the Minesweeper board.
	public enumTest(String userStr)
	{
		switch(userStr)
		{
		case"#": sq=squareValue.MINE;
				break;
		case"1": sq=squareValue.ONE;
				break;
		case"2": sq=squareValue.TWO;
				break;
		case"3": sq=squareValue.THREE;
				break;
		case"4": sq=squareValue.FOUR;
				break;
		case"5": sq=squareValue.FIVE;
				break;
		case"6": sq=squareValue.SIX;
				break;
		case"7": sq=squareValue.SEVEN;
				break;
		case"8": sq=squareValue.EIGHT;
				break;
		default: sq=squareValue.ZERO;
				break;
		}
		
	}

}
