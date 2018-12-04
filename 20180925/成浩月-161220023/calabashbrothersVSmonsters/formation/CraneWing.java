package formation;

import cells.*;

public class CraneWing extends Formation {
	
	//1-7�����«��,8������үү,a����Сආ�,s�����߾�,y����Ы�Ӿ�
	/*0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
	0,0,0,0,0,0,0,8,0,0,0,0,0,0,0
	0,0,0,0,0,0,0,7,0,0,0,0,0,0,0
	0,0,0,0,0,0,0,6,0,0,0,0,0,0,0
	0,0,0,0,0,0,0,5,0,0,0,0,0,0,0
	0,0,0,0,0,0,0,4,0,0,0,0,0,0,0
	0,0,0,0,0,0,0,3,0,0,0,0,0,0,0
	0,0,0,0,0,0,0,2,0,0,0,0,0,0,0
	0,0,a,0,0,0,0,1,0,0,0,0,a,0,0
	0,0,0,a,0,0,0,0,0,0,0,a,0,0,0
	0,0,0,0,a,0,0,0,0,0,a,0,0,0,0
	0,0,0,0,0,a,0,0,0,a,0,0,0,0,0
	0,0,0,0,0,0,a,s,a,0,0,0,0,0,0
	0,0,0,0,0,0,0,y,0,0,0,0,0,0,0
	0,0,0,0,0,0,0,0,0,0,0,0,0,0,0*/
	
	public CraneWing(){
		super();
		name="������";
		int i,j;

		//��«��
		for(i=2;i<=8;i++)
			cells[i][7]=new CalabashBrotherCell();
		
		//Сආ�
		for(i=8;i<=12;i++) {
			cells[i][i-6]=new FollowerCell();
			cells[i][20-i]=new FollowerCell();
		}
		
		//��үү,�߾�,Ы�Ӿ�
		cells[1][7]=new GrandpaCell();
		cells[12][7]=new SerpentCell();
		cells[13][7]=new ScorpionCell();
		
		for(i=0;i<ROWS;i++) {
			for(j=0;j<COLS;j++) {
				if(cells[i][j]==null)
					cells[i][j]=new Cell();
			}
		}
	}
}
