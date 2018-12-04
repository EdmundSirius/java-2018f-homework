//References:
//https://blog.csdn.net/incyanggan/article/details/9819005
//https://www.cnblogs.com/frankliiu-java/archive/2010/12/07/1898721.html
//https://blog.csdn.net/liboom/article/details/68488279
//https://www.cnblogs.com/opangle/p/4082692.html
//https://blog.csdn.net/ytuglt/article/details/47312543
//http://maven.outofmemory.cn/org.fusesource.jansi/jansi/1.11/
//https://jingyan.baidu.com/article/ca41422fc76c4a1eae99ed9f.html
//https://blog.csdn.net/u010889616/article/details/51477037
//https://blog.csdn.net/nvliba/article/details/53520611
//https://blog.csdn.net/magi1201/article/details/42212537
//http://blog.51cto.com/dongdong1314/79385

package code;
import java.io.*;
import java.util.*;
//��ӡ��Ϣ�Ľӿ�
interface printinfo{
	void printout();
}
//��ö�����Ͷ����߸���«��
enum Calabash implements printinfo{
	//ʵ����
	Red("��ɫ","�ϴ�",1),
	Orange("��ɫ","�϶�",2),
	Yellow("��ɫ","����",3),
	Green("��ɫ","����",4),
	Cyan("��ɫ","����",5),
	Blue("��ɫ","����",6),
	Purple("��ɫ","����",7);
	//���ݳ�Ա
	private String color;
	private String name;
	private int rank;
	//��Ա����
	private Calabash(String color,String name,int rank) {
		this.color=color;
		this.name=name;
		this.rank=rank;
	}
	public String getname() {
		return this.name;
	}
	public String getcolor() {
		return this.color;
	}
	public int getrank() {
		return this.rank;
	}
	
	@ Override
	public void printout() {
		System.out.print(this.rank);
	}
	
}
//��ȱ(��������)
class Blank implements printinfo{
	private char sym='-';
	@ Override
	public void printout() {
		System.out.print(sym);
	}
}
//Сආ���(��������)
class Monster implements printinfo{
	private char sym='v';
	@ Override
	public void printout() {
		System.out.print(sym);
	}
}
//�������������ҽ���һ������
//�߾�
class Snack implements printinfo{
	public static Snack snack=new Snack();
	private char sym;
	private Snack() {
		this.sym='&';
	}
	@ Override
	public void printout() {
		System.out.print(sym);
	}
	
}
//Ы�Ӿ�
class Crab implements printinfo{
	public static Crab crab=new Crab();
	private char sym;
	private Crab() {
		this.sym='%';
	}
	@ Override
	public void printout() {
		System.out.print(sym);
	}
}
//��үү
class Elder implements printinfo{
	public static Elder elder=new Elder();
	private char sym;
	private Elder() {
		this.sym='o';
	}
	@ Override
	public void printout() {
		System.out.print(sym);
	}
}

public class HW3 {
	//��Χ�Ĵ�С
	public static final int size=20;
	//���͵���������
	public static final int num_shapes=7;
	//ÿ�����͵�����
	public static final String[] shape_names=
		{"����","����","���","����","����","����","��ʸ"};
	public static int shape_code;
	//����һ��Object���͵Ķ�ά����
	public static final Object [][] grid=new Object[size] [size];
	//�洢��������
	public static char [][][] shapes=new char [num_shapes][size/2][size];
	// ��ȡ��ǰ·��	
	private static final String wd=System.getProperty("user.dir");
	//�洢���͵������ļ���
	private static final String filename=wd+"\\shape_list.txt";
	//��gridȫ����ʼ��ΪBlank	
	public static void cleargrid() {

		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				Blank b=new Blank();
				grid[i][j]=b;
			}
		}
	}
	//���ݴ�����������÷�������
	public static void setgrid(int shape_code) {
		//�ַ������鿽��
		char [][] get_shape=shapes[shape_code].clone();
		//��һ��ľ�����������������Ӫ
		for(int i=0;i<size/2;i++) {
			for(int j=0;j<size;j++) {
				//System.out.print(get_shape[i][j]);
				switch(get_shape[i][j]) {
				case '-':	//��ȱ
					Blank b=new Blank();
					grid[i][j]=b;
					break;
				case 'v':	//Сආ�
					Monster m=new Monster();
					grid[i][j]=m;
					break;
				case '%':	//Ы�Ӿ�
					grid[i][j]=Crab.crab;
					break;
				default:
						break;
				}
			}
			//System.out.println("");
		}
		//���ú�«��
		int cala_row=size/2;
		for(Calabash c:Calabash.values()) {
			grid[cala_row][9]=c;
			cala_row++;
		}
		
		Random r=new Random();
		//�����߾�
		int snack_x,snack_y;
		do {
			snack_x=r.nextInt(size/2);
			snack_y=r.nextInt(size);
		}while(!(grid[snack_x][snack_y] instanceof Blank));
		grid[snack_x][snack_y]=Snack.snack;
		
		//System.out.println("");
		//printgrid();
		//������үү
		int elder_x,elder_y;
		do {
			elder_x=r.nextInt(size/2)+size/2;
			elder_y=r.nextInt(size);
		}while(!(grid[elder_x][elder_y] instanceof Blank));
		grid[elder_x][elder_y]=Elder.elder;
	}
	//ɨ�貢��ӡ��������
	public static void printgrid(int shape_code) {
		System.out.println("��ǰ����: "+shape_names[shape_code]);
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				//���������ӿ�ת��
				((printinfo) grid[i][j]).printout();
			}
			System.out.println("");
		}
	}

	//��ȡ�ļ������������ô洢��shapes������
	public static void read_shapelist() {
		try {
			File file=new File(filename);
			FileInputStream inputStream=new FileInputStream(file);
			BufferedReader bf=new BufferedReader(new InputStreamReader(inputStream));
			String tmp=null;
			
			int count_shapes=0,count_rows=0;
			do {
				tmp=bf.readLine();
				if(tmp==null)
					break;
				if(!tmp.equals("")) {
					shapes[count_shapes][count_rows]=tmp.toCharArray();
					/*for(int i=0;i<size;i++) {
						System.out.print(shapes[count_shapes][count_rows][i]);
					}
					System.out.println("");*/
					
					count_rows++;					
				}
				else {
					count_rows=0;
					count_shapes++;
					//System.out.println("");
				}
			}while(tmp!=null);
			
			bf.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		//���Դ���:���shapes��ά����
		/*for(int i=0;i<shapes.length;i++) {
			for(int j=0;j<shapes[i].length;j++) {
				for(int k=0;k<shapes[i][j].length;k++) {
					System.out.print(shapes[i][j][k]);
				}
				System.out.println("");
			}
			System.out.println("");
		}*/
	}
	//���ر任�����������ļ��е����
	//���뵱ǰ���͵ı��,��֤��һ�������뵱ǰ��ͬ
	public static int change_shape_code(int current) {
		Random r=new Random();
		int get;
		do {
			get=r.nextInt(num_shapes);
		}while(get==current);
		return get;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(filename);
		read_shapelist();
		Random r=new Random();
		shape_code=r.nextInt(num_shapes);
		cleargrid();
		setgrid(shape_code);
		printgrid(shape_code);
		//��try...catch��䲶��IO�쳣
		try {
			//ʹ�� System.in ���� BufferedReader 
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

			do {
				System.out.println("����change�任1������,����������n�任n������,����end����...");
				//�ӿ���̨�����ַ���
				String str=br.readLine();
				if(str.equals("change")) {
					cleargrid();
					shape_code=change_shape_code(shape_code);
					setgrid(shape_code);
					printgrid(shape_code);
				}
				else if(str.equals("end")) {
					break;
				}
				else {
					//�����try...catch��䲶���ַ���ת���ֵ��쳣
					try {
						//�ַ���ת����
						int x=Integer.parseInt(str);
						for(int i=0;i<x;i++) {
							cleargrid();
							shape_code=change_shape_code(shape_code);
							setgrid(shape_code);
							printgrid(shape_code);
						}
					} catch (NumberFormatException e) {
						System.out.println("ָ���޷�ʶ��,����������...");						
					}
				}
			}while(true);

			System.out.println("����");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
