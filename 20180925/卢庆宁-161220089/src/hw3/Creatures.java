package hw3;

class Creatures {						//����Ⱥ��
	protected int number;					//��������
	protected Creature[] cs;				//����������
	protected Formation f;
	Creatures(){}							//�յ�Ĭ�Ϲ��캯�����������о���ʵ��
	public int getNumber() {return number;}	//��ȡ��������
	public String getFormationName(){return f.name;}
	public int set(Board b,int x,int y,Formation f) //�������ͷ�������Ⱥ
	{
		if(f.test(b,x,y,number)==false)
		{
			f.set(b,x,y,cs,number);
			this.f=f;
			return 0;
		}
		else return -1;
	}
	public void reset(Board b)				//���õ�ǰ����Ⱥ
	{
		for(int i=0;i<number;i++)
		{
			b.reset(cs[i]);
		}
	}
	public void upset(int i) {}
	public void sort() {};
}

class CalabashBrothers extends Creatures {	//��«��Ⱥ��
	CalabashBrothers(int k)						//Ĭ�Ϲ��캯��
	{
		number=k;
		cs=new CalabashBrother[7];
		for(int i=1;i<=number;i++) 
		{
			cs[i-1]=new CalabashBrother(i,0,0);
		}
	}
	public void upset(int time)					//���Һ�«�޶���
	{	
		java.util.Random random=new java.util.Random();
		for(int j=0;j<time;j++)
		{
			for(int i=0;i<=6;i++)
			{
				int iRandom = random.nextInt(7);
				Creature temp=cs[iRandom];
				cs[iRandom]=cs[i];
				cs[i]=temp;
			}
		}
	}
	public void sort()							//�Ժ�«�޶�������
	{
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<6-i;j++)
			{
				if(cs[j].getNumber()>cs[j+1].getNumber())
				{
					Creature temp=cs[j];
					cs[j]=cs[j+1];
					cs[j+1]=temp;
				}
			}
		}
	}
}

class Monsters extends Creatures {				//����Ⱥ��
	Monsters(int number)							//Ĭ�Ϲ��캯��������������廯ΪЫ�Ӿ�
	{
		this.number=number;
		cs=new Creature[number];
		cs[0]=new ScorpionMonster(0,0);
		for(int i=1;i<number;i++)
		{
			cs[i]=new SmallMonster(0,0);
		}
	}
}
