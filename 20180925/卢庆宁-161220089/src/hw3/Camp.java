package hw3;

class Camp {						//��Ӫ�࣬����һ��boss������С��
	public Creature boss;
	public Creatures soldiers;
}
class Good extends Camp				//������Ӫ��������үү���߸���«��
{
	Good(int x_boss,int y_boss)
	{
		boss=new GrandFather(x_boss, y_boss);
		soldiers=new CalabashBrothers(7);
	}
}
class Bad extends Camp				//������Ӫ�������߾�����������
{
	Bad(int x_boss,int y_boss,int number)
	{
		boss=new SnakeMonster(x_boss,y_boss);
		soldiers=new Monsters(number);
	}
}
