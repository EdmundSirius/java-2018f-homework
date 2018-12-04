package confrontation;

import java.util.Scanner;

public class Organisms {
	public String name;
	public int x,y;
	public Organisms() {
		
	}
	public Organisms(String n) {
		this.name=n;
	}
	public String getName() {
		return name;
	}
	public void setXY(String[][] space,int x,int y) {
		space[x][y]=this.name;
		this.x=x;
		this.y=y;
	} 
}

class Grandpa extends Organisms{
	public Grandpa() {
		super("үү");
	}
	public void cheerUp() {
		
	}
}
class CalabashBrothers extends Organisms{
	public CalabashBrothers(String c) {
		this.name=c;
	}
	public void selectFormation(Formation formation) {
	
	}
}
class Sneak extends Organisms{
	public Sneak() {
		super("  �߾�   ");
	}
}
class Sorpio extends Organisms{
	public Sorpio() {
		super(" Ы�Ӿ� ");
	}
	public void selectFormation(Formation formation,Sorpio sorpio,Lackeys[] lackeys,String[][] space) {
		Scanner in=new Scanner(System.in);
		System.out.println("Ы�Ӿ�����ѡ������");
		System.out.println("1������");
		System.out.println("2������");
		System.out.println("3������");
		System.out.println("4������");
		System.out.println("5������");
		System.out.println("6������");
		System.out.println("7������");
		System.out.println("8����ʸ");
		int key=in.nextInt();
		switch(key) {
		case 1:formation.craneFormation(sorpio, lackeys, space);break;
		case 2:formation.echelonFormation(sorpio, lackeys, space);break;
		case 3:formation.yokeFormation(sorpio, lackeys, space);break;
		case 4:formation.sneakFormation(sorpio, lackeys, space);break;
		case 5:formation.fishFormation(sorpio, lackeys, space);break;
		case 6:formation.squareFormation(sorpio, lackeys, space);break;
		case 7:formation.moonFormation(sorpio, lackeys, space);break;
		case 8:formation.shapeFormation(sorpio, lackeys, space);break;
		}
		in.close();
	}
}
class Lackeys extends Organisms{
	public Lackeys() {
		super(" Сආ� ");
	}
}