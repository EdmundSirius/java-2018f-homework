package hw3;

class Creature {							//��������
	protected boolean exist;					//�������
	protected String name;						//����
	protected int x;							//������
	protected int y;							//������
	protected char ch;							//�ַ���ʾ
	Creature(){exist=false;ch=' ';}								//Ĭ�Ϲ��죬�����Ϊfalse
	public boolean getExist() {return exist;}					//����ĸ�����������ֵ
	public String getName() {return name;}
	public int getX() {return x;}
	public int getY() {return y;}
	public char getCh() {return ch;}
	public int getNumber() {return 0;}
	public void set(int x,int y) {exist=true;this.x=x;this.y=y;}//��������
}

class CalabashBrother extends Creature {	//��«����
	private int number;
	CalabashBrother(int i,int x,int y)
	{
		set(x,y);
		ch=(char)(i + '0');
		number=i;
		switch(i)
		{
		case 1:name="����";break;
		case 2:name="����";break;
		case 3:name="����";break;
		case 4:name="����";break;
		case 5:name="����";break;
		case 6:name="����";break;
		case 7:name="����";break;
		}
	}
	public int getNumber() {return number;}
}

class GrandFather extends Creature {	//үү��
	GrandFather(int x,int y)
	{
		set(x,y);
		this.name="үү";
		this.ch='0';
	}
	public void cheer() {};
}

class ScorpionMonster extends Creature {//Ы�Ӿ���
	ScorpionMonster(int x,int y)
	{
		set(x,y);
		this.name="Ы�Ӿ�";
		this.ch='$';
	}
}

class SnakeMonster extends Creature {	//�߾���
	SnakeMonster(int x,int y)
	{
		set(x,y);
		this.name="�߾�";
		this.ch='S';
	}
	public void cheer() {};
}

class SmallMonster extends Creature {	//Сආ���
	SmallMonster(int x,int y)
	{
		set(x,y);
		this.name="Сආ�";
		this.ch='o';
	}
	public void cheer() {};
}
