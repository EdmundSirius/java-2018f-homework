package formation;

import cells.*;

public class CrescentMoon extends Formation {
	
	/*0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
	  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
	  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
	  0,0,0,0,0,0,0,0,0,0,0,0,a,0,0
	  0,0,0,0,7,0,0,0,0,a,0,a,0,0,0
	  0,0,0,0,6,0,0,0,a,0,a,0,0,0,0
	  0,0,0,0,5,0,a,a,0,a,0,0,0,0,0
	  0,0,0,8,1,0,y,a,s,a,0,0,0,0,0
	  0,0,0,0,2,0,a,a,0,a,0,0,0,0,0
	  0,0,0,0,3,0,0,0,a,0,a,0,0,0,0
	  0,0,0,0,4,0,0,0,0,a,0,a,0,0,0
	  0,0,0,0,0,0,0,0,0,0,0,0,a,0,0
	  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
	  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
	  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0*/
	
	public CrescentMoon(){
		super();
		name="������";
		int i,j;
		
		//��«��
		for(i=4;i<=10;i++)
			cells[i][4]=new CalabashBrotherCell();
		
		//Сආ�
		for(j=12;j>=9;j--) {
			cells[15-j][j]=new FollowerCell();
			cells[j-1][j]=new FollowerCell();
		}
		for(i=4;i<=10;i+=3)
			cells[i][9]=new FollowerCell();
		cells[5][8]=new FollowerCell();
		cells[9][8]=new FollowerCell();
		for(i=6;i<=8;i++)
			cells[i][7]=new FollowerCell();
		cells[6][6]=new FollowerCell();
		cells[8][6]=new FollowerCell();
		
		//��үү,�߾�,Ы�Ӿ�
		cells[7][3]=new GrandpaCell();
		cells[7][8]=new SerpentCell();
		cells[7][6]=new ScorpionCell();
		
		for(i=0;i<ROWS;i++) {
			for(j=0;j<COLS;j++) {
				if(cells[i][j]==null)
					cells[i][j]=new Cell();
			}
		}
	}
}
