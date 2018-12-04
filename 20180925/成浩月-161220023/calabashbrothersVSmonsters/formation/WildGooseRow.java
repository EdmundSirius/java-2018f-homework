package formation;

import cells.*;

public class WildGooseRow extends Formation {
	
	/*0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
	  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
	  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
	  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
	  0,0,0,0,1,0,0,0,0,0,0,0,a,0,0
	  0,0,0,0,2,0,0,0,0,0,0,a,0,0,0
	  0,0,0,0,3,0,0,0,0,0,a,0,0,0,0
	  0,0,0,8,4,0,0,0,0,y,s,0,0,0,0
	  0,0,0,0,5,0,0,0,a,0,0,0,0,0,0
	  0,0,0,0,6,0,0,a,0,0,0,0,0,0,0
	  0,0,0,0,7,0,a,0,0,0,0,0,0,0,0
	  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
	  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
	  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
	  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0*/
	
	public WildGooseRow(){
		super();
		name="������";
		int i,j;
		
		//��Ӻ�«��
		for(i=4;i<=10;i++)
			cells[i][4]=new CalabashBrotherCell();
		
		//���Сආ�
		for(i=4;i<=6;i++)
			cells[i][16-i]=new FollowerCell();
		for(i=8;i<=10;i++)
			cells[i][16-i]=new FollowerCell();
		
		//�����үү,�߾�,Ы�Ӿ�
		cells[7][3]=new GrandpaCell();
		cells[7][9]=new ScorpionCell();
		cells[7][10]=new SerpentCell();
		
		for(i=0;i<ROWS;i++) {
			for(j=0;j<COLS;j++) {
				if(cells[i][j]==null)
					cells[i][j]=new Cell();
			}
		}
	}
}
