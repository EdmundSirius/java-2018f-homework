package hw3;

class Board {
	private int height,width;		//���̵ĸ߶ȺͿ��
	private Creature c[][];			//ÿ�����ӷ�һ������
	Board(int h,int w)				//Ĭ�Ϲ��캯��
	{
		height=h;
		width=w;
		c=new Creature[height][width];
		init();
	}
	public int getHeight() {return height;}
	public int getWidth() {return width;}
	public void testAndSet(Creature c)			//���������겢��������
	{
		if(test(c.getX(),c.getY())==false) this.c[c.getX()][c.getY()]=c;
	}
	public void set(Creature c) {this.c[c.getX()][c.getY()]=c;}					//ֱ�ӷ��ø�������
	public void reset(Creature c) {this.c[c.getX()][c.getY()]=new Creature();}	//����������������ϳ���
	public boolean test(int x,int y)			//����������	
	{
		if(x<0||x>=height||y<0||y>=width) return true;
		else return c[x][y].getExist();
	}
	public void init()							//���̻ص���ʼ״̬
	{
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				c[i][j]=new Creature();
			}
		}
	}
	public char getCh(int x,int y){ return c[x][y].getCh();}	//��ø����������������
	public void showAll(Display disp)			//��ӡ��ǰ���̵����
	{
		for(int j=0;j<width+2;j++)
		{
			//System.out.print("#");
			disp.setLog("#");
		}
		//System.out.print("\n");
		disp.setLog("\n");
		for(int i=0;i<height;i++)
		{
			//System.out.print("#");
			disp.setLog("#");
			for(int j=0;j<width;j++)
			{
				//System.out.print(getCh(i,j));
				disp.setLog(""+getCh(i,j));
			}
			//System.out.print("#\n");
			disp.setLog("#\n");
		}
		for(int j=0;j<width+2;j++)
		{
			//System.out.print("#");
			disp.setLog("#");
		}
		//System.out.print("\n");
		disp.setLog("\n");
		//System.out.print("\n");
		//disp.setLog("\n");
	}
}
