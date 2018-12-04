package characters;
import ui.Administrator;
import java.awt.*;
import shapes.Location;;

public class CalabashBoy extends Creature{
	// -----------------------------------Variables---------------------------------
	public int rank;	
	private CalabashInfo cbInfo;
	private Image img;	//��«�޸�������
	
	
	//----------------------------------Properties-------------------------------------
	public static int boysizex = 80, boysizey = 80;
	


	public int getRank() {
		return rank;
	}
	//���캯��
	public CalabashBoy(Location l, int rank, Administrator ad){
		this.loc = l;
		this.admin = ad;
		this.sizex = boysizex;	//��д�����еĳ�Ա����
		this.sizey = boysizey;
		this.rank = rank;
		this.cbInfo = Administrator.cbInfo_array[rank];
		this.img = Administrator.boysImg.get(cbInfo);
	}

	public void draw(Graphics g) {
		g.drawImage(img, loc.locx, loc.locy, loc.locx+sizex, loc.locy+sizey, 0, 0, img.getWidth(null), img.getHeight(null), null);
		
	}
	
	
	
}



