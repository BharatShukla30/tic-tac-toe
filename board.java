package tictactoe;

public class board {
	public int[] layout=new int[]{0,0,0,0,0,0,0,0,0};
	
	void setLayout(int i,int val)
	{
		i=i-1;
		layout[i]=val;
	}
	int getLayout(int i)
	{
		i=i-1;
		return layout[i];
	}
}
