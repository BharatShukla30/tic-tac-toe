
package tictactoe;
import java.util.*;
import java.lang.Math.*;
public class start {
	
	static int isEnd(board obj)
	{
		int[][] winCon=new int[][]{{1,2,3},{4,5,6},{7,8,9},{1,4,7},
									{2,5,8},{3,6,9},{3,5,7},{1,5,9}};
		for(int i=0,j=0;i<8;i++)											// Check for any player's win
		{
			j=0;
			if((obj.getLayout(winCon[i][j])==obj.getLayout(winCon[i][j+1]) && obj.getLayout(winCon[i][j+1])==obj.getLayout(winCon[i][j+2])) && obj.getLayout(winCon[i][j])!=0)
			{
				return obj.getLayout(winCon[i][j]);
			}
			
		}
		int iter=1;
		while(iter<10 && obj.getLayout(iter)!=0)		// Check for ties condition
		{
			//champ=100;
			iter++;
		}
		if(iter==10)
		{
			return 0;
		}
		return 69;
	}

	static int minimax(board lay,int depth,boolean isMax)
	{
		Map< Integer,Integer> tally = new HashMap< Integer,Integer>(); 
		tally.put(1, -1);
		tally.put(2, 1);
		tally.put(0, 0);
		int result=isEnd(lay);
		//System.out.println(result);
		if(result!=69)
		{
				return tally.get(result);
		}
		if(isMax)
		{
			int topScore=-100;
			for(int i=1;i<=9;i++)
			{
				//AI turn
				if(lay.getLayout(i)==0)
				{
					lay.setLayout(i, 2);
					int score=minimax(lay,depth+1,false);
					lay.setLayout(i, 0);
					if(score>topScore)
						{
						
						topScore=score;
						System.out.println("Max:"+topScore+"index:"+i);
						}
				}
			}
			return topScore;
		}
		else
		{
			int topScore=100;
			for(int i=1;i<=9;i++)
			{
				//human turn
				if(lay.getLayout(i)==0)
				{
					lay.setLayout(i, 1);
					int score=minimax(lay,depth+1,true);
					lay.setLayout(i, 0);
					if(score<topScore)
						{
						
						topScore=score;
						System.out.println("Min:"+topScore+"index:"+i);
						}
				}
			}
			return topScore;
		}
	
	}
	
	static void startAi(board lay)
	{
		
		int bestMove =0;
		int topScore=-100;
		
		for(int i=1;i<=9;i++)
		{
			
			//minimax calling
			if(lay.getLayout(i)==0)
			{
				lay.setLayout(i, 2);
				int score=minimax(lay,0,false);
				lay.setLayout(i, 0);
				if(score>topScore)
					{
					topScore=score;
					System.out.println("Final:"+topScore+"index:"+i);
					bestMove=i;
					}
			}
		}
		lay.setLayout(bestMove, 2);
	}
		
	static void showBoard(board lay){
		for(int i=1,j=0;j<3;j++)
		{
			int k=1;
			while(k<=3)
			{
				System.out.print(lay.getLayout(i));
				i++;
				k++;
			}
			System.out.print("\n");
		}
	}

	public static void main(String[] args )
	{
		Scanner input=new Scanner(System.in);
		int turn=1 ;												// Counter to see whose turn is it
		boolean isActive=true,firstMove=true;						// Check if game is still active
		board lay=new board();
		int result = 0;
		while(isActive)
		{
			if(turn==1)											// Player's turn 
			{
				System.out.println("Enter grid number (from 1-9):");
				int move=input.nextInt();
				boolean isLegal=false;
				if(lay.getLayout(move)==0)
					isLegal=true;
				if(isLegal==true)
				{
					lay.setLayout(move,1);
					showBoard(lay);
					turn=0;
				}
				else
				{
					System.out.println("This is a preoccupied place. Try again.");
				}
			}
			else												// Computer's turn
			{
				startAi(lay);
				System.out.println("Now the AI's turn:");
				showBoard(lay);
				turn=1;
				
			}
			result=isEnd(lay);							// Checking if the game has ended
			if(result!=69)
				{System.out.println("Winner is player "+result);isActive=false;}
		}
		
		input.close();
	}
	
}






