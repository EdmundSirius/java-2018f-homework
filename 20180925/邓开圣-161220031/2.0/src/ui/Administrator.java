package ui;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import characters.*;
import shapes.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Administrator extends Frame{
	
	// ----------------------Properties--------------------------------
	//�����ڳߴ�
	public static int mainwindow_sizex = 1600;
	public static int mainwindow_sizey = 900;

	//-------------------------Variables----------------------------------------
	private static final long serialVersionUID = 1L;
	//��ȡ��Ļ�ߴ�
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Dimension screen = tk.getScreenSize();
	//����ͼ��ַ
	private static String bkpAddr = "http://i1.073img.com/130109/3848468_100453_1.jpg";
	//����ͼ	
	private static Image bkp;
	

	//��«����Ϣ�������ӳ���
	public static Map <CalabashInfo, Image> boysImg = new HashMap <CalabashInfo, Image>();
	private static Image[] CalabashBoysImgs = null;
	static {
		CalabashBoysImgs = new Image[] {
			new ImageIcon("resources/characters/calabash/red.PNG").getImage(),
			new ImageIcon("resources/characters/calabash/orange.PNG").getImage(),
			new ImageIcon("resources/characters/calabash/yellow.PNG").getImage(),
			new ImageIcon("resources/characters/calabash/green.PNG").getImage(),
			new ImageIcon("resources/characters/calabash/cyan.PNG").getImage(),
			new ImageIcon("resources/characters/calabash/blue.PNG").getImage(),
			new ImageIcon("resources/characters/calabash/purple.PNG").getImage()
		};
		boysImg.put(CalabashInfo.Red, CalabashBoysImgs[0]);
		boysImg.put(CalabashInfo.Orange, CalabashBoysImgs[1]);
		boysImg.put(CalabashInfo.Yellow, CalabashBoysImgs[2]);
		boysImg.put(CalabashInfo.Green, CalabashBoysImgs[3]);
		boysImg.put(CalabashInfo.Cyan, CalabashBoysImgs[4]);
		boysImg.put(CalabashInfo.Blue, CalabashBoysImgs[5]);
		boysImg.put(CalabashInfo.Purple, CalabashBoysImgs[6]);
	}
	//�����������
	public static Random r = new Random();
	//��Ÿ�������
	public Snake snake;	
	public GrandFather grandfather;
	//��ź�«��
	public List <CalabashBoy> calabashes = new ArrayList <CalabashBoy>();
	//Ы�Ӿ���ආ�
	public List <Creature> monsters = new ArrayList <Creature>();
	//������Ϣ������
	public static ShapeInfo[] spInfo_array = ShapeInfo.values();	
	//��«����Ϣ������
	public static CalabashInfo[] cbInfo_array = CalabashInfo.values();	
	//��ͼ����
	Image OffScreenImage = null;
	//��«�����ͺ���������,��ʼ��Ϊ����
	public static ShapeInfo calabash_shape = ShapeInfo.changshe;
	public static ShapeInfo monster_shape = ShapeInfo.changshe;
	//���ƺ�«�����͵������ƶ�
	private int xMove = 0;
	//��дcontainer��update����,�����ݻ�������,��һ���Ի���ԭͼ��
	@Override
	public void update(Graphics g) {
		if(OffScreenImage == null) {
			OffScreenImage = this.createImage(mainwindow_sizex,mainwindow_sizey);
		}
		Graphics gOffScreen = OffScreenImage.getGraphics();
		paint(gOffScreen);
		g.drawImage(OffScreenImage, 0, 0, null);
	}
	//��дwindow��paint����
	@Override
	public void paint(Graphics g) {
		//������ͼ
		//Image bkp = bkps[bkpNum];
		g.drawImage(bkp, 0, 0, this.getWidth(), this.getHeight(), 0, 0, bkp.getWidth(null), bkp.getHeight(null), null);
		
		//�������к�«��
		for(int i=0;i<calabashes.size();i++) {
			calabashes.get(i).draw(g);
		}
		//����Ы�Ӿ���Сආ�
		for(int i=0;i<monsters.size();i++) {
			monsters.get(i).draw(g);
		}
		//�����߾�����үү
		snake.draw(g);
		grandfather.draw(g);
	}	
	//��������
	private void set_shape() {
		
		//��«�޲���
		switch (calabash_shape) {
		case heyi:		break;
		case yanxing:	break;
		case henggui:	break;
		case changshe:
			this.calabashes.get(0).setLoc(new Location(200+xMove,100));
			this.calabashes.get(1).setLoc(new Location(200+xMove,200));
			this.calabashes.get(2).setLoc(new Location(200+xMove,300));
			this.calabashes.get(3).setLoc(new Location(200+xMove,400));
			this.calabashes.get(4).setLoc(new Location(200+xMove,500));
			this.calabashes.get(5).setLoc(new Location(200+xMove,600));
			this.calabashes.get(6).setLoc(new Location(200+xMove,700));
			break;
		case yuling:	break;
		case fangmen:	break;
		case yanyue:	break;
		case fengshi:	break;
			default: break;
		}
		
		monsters.clear();	//ɾ������Ԫ��
		monsters.add(new Crab(new Location(0,0),this));	//���Ы�Ӿ�
		//��������
		switch (monster_shape) {
		case heyi:{
			//���Сආ�
			for(int i=0;i<6;i++) {
				this.monsters.add(new Gangster(new Location(0,0),this));
			}
			this.monsters.get(0).setLoc(new Location(900,400));
			this.monsters.get(1).setLoc(new Location(1000,500));
			this.monsters.get(2).setLoc(new Location(1000,300));
			this.monsters.get(3).setLoc(new Location(1100,600));
			this.monsters.get(4).setLoc(new Location(1100,200));
			this.monsters.get(5).setLoc(new Location(1200,700));
			this.monsters.get(6).setLoc(new Location(1200,100));
			break;			
		}

		case yanxing:{
			for(int i=0;i<5;i++) {
				this.monsters.add(new Gangster(new Location(0,0),this));
			}
			this.monsters.get(0).setLoc(new Location(1300,150));
			this.monsters.get(1).setLoc(new Location(1220,250));
			this.monsters.get(2).setLoc(new Location(1140,350));
			this.monsters.get(3).setLoc(new Location(1060,450));
			this.monsters.get(4).setLoc(new Location(980,550));
			this.monsters.get(5).setLoc(new Location(900,650));
			break;
		}
		case henggui:{
			for(int i=0;i<5;i++) {
				this.monsters.add(new Gangster(new Location(0,0),this));
			}
			this.monsters.get(0).setLoc(new Location(1300,150));
			this.monsters.get(1).setLoc(new Location(1200,250));
			this.monsters.get(2).setLoc(new Location(1300,350));
			this.monsters.get(3).setLoc(new Location(1200,450));
			this.monsters.get(4).setLoc(new Location(1300,550));
			this.monsters.get(5).setLoc(new Location(1200,650));
			break;
		}
		case changshe:{
			for(int i=0;i<5;i++) {
				this.monsters.add(new Gangster(new Location(0,0),this));
			}
			this.monsters.get(0).setLoc(new Location(1280,170));
			this.monsters.get(1).setLoc(new Location(1300,300));
			this.monsters.get(2).setLoc(new Location(1300,400));
			this.monsters.get(3).setLoc(new Location(1300,500));
			this.monsters.get(4).setLoc(new Location(1300,600));
			this.monsters.get(5).setLoc(new Location(1300,700));
			break;
		}

		case yuling:{
			for(int i=0;i<9;i++) {
				this.monsters.add(new Gangster(new Location(0,0),this));
			}
			this.monsters.get(0).setLoc(new Location(1200,250));
			this.monsters.get(1).setLoc(new Location(1260,350));
			this.monsters.get(2).setLoc(new Location(1320,450));
			this.monsters.get(3).setLoc(new Location(1200,450));
			this.monsters.get(4).setLoc(new Location(1080,450));
			this.monsters.get(5).setLoc(new Location(1380,550));
			this.monsters.get(6).setLoc(new Location(1260,550));
			this.monsters.get(7).setLoc(new Location(1140,550));
			this.monsters.get(8).setLoc(new Location(1020,550));
			this.monsters.get(9).setLoc(new Location(1200,650));
			break;
		}
		case fangmen:{
			for(int i=0;i<7;i++) {
				this.monsters.add(new Gangster(new Location(0,0),this));
			}
			this.monsters.get(0).setLoc(new Location(1000,450));
			this.monsters.get(1).setLoc(new Location(1100,350));
			this.monsters.get(2).setLoc(new Location(1100,550));
			this.monsters.get(3).setLoc(new Location(1200,250));
			this.monsters.get(4).setLoc(new Location(1200,650));
			this.monsters.get(5).setLoc(new Location(1300,350));
			this.monsters.get(6).setLoc(new Location(1300,550));
			this.monsters.get(7).setLoc(new Location(1400,450));
			break;
		}
		case yanyue:{
			for(int i=0;i<18;i++) {
				this.monsters.add(new Gangster(new Location(0,0),this));
			}
			this.monsters.get(0).setLoc(new Location(980,440));
			this.monsters.get(1).setLoc(new Location(1000,530));
			this.monsters.get(2).setLoc(new Location(1000,370));
			this.monsters.get(3).setLoc(new Location(1100,450));
			this.monsters.get(4).setLoc(new Location(1100,530));
			this.monsters.get(5).setLoc(new Location(1100,370));
			this.monsters.get(6).setLoc(new Location(1140,290));
			this.monsters.get(7).setLoc(new Location(1140,610));
			this.monsters.get(8).setLoc(new Location(1180,210));
			this.monsters.get(9).setLoc(new Location(1180,690));
			this.monsters.get(10).setLoc(new Location(1200,450));
			this.monsters.get(11).setLoc(new Location(1200,530));
			this.monsters.get(12).setLoc(new Location(1200,370));
			this.monsters.get(13).setLoc(new Location(1240,290));
			this.monsters.get(14).setLoc(new Location(1240,610));
			this.monsters.get(15).setLoc(new Location(1280,210));
			this.monsters.get(16).setLoc(new Location(1280,690));
			this.monsters.get(17).setLoc(new Location(1320,130));
			this.monsters.get(18).setLoc(new Location(1320,770));
			break;
		}
		case fengshi:{
			for(int i=0;i<11;i++) {
				this.monsters.add(new Gangster(new Location(0,0),this));
			}
			this.monsters.get(0).setLoc(new Location(900,450));
			this.monsters.get(1).setLoc(new Location(980,350));
			this.monsters.get(2).setLoc(new Location(980,550));
			this.monsters.get(3).setLoc(new Location(1060,250));
			this.monsters.get(4).setLoc(new Location(1060,650));
			this.monsters.get(5).setLoc(new Location(1140,150));
			this.monsters.get(6).setLoc(new Location(1140,750));
			this.monsters.get(7).setLoc(new Location(1000,450));
			this.monsters.get(8).setLoc(new Location(1100,450));
			this.monsters.get(9).setLoc(new Location(1200,450));
			this.monsters.get(10).setLoc(new Location(1300,450));
			this.monsters.get(11).setLoc(new Location(1400,450));
			break;
		}
		default: break;
		}
	}
	
	//�ı�����
	public void change_shape() {
		int n = r.nextInt(spInfo_array.length);
		monster_shape = spInfo_array[n];
		xMove = r.nextInt(400)-200;			//��«�����ͺ����ƶ�
		//bkpNum = r.nextInt(bkps.length);	//�ı䱳��ͼ
		set_shape();
		setLocGrandfatherSnake();
	}
	//�ı���үү���߾���λ��
	public void setLocGrandfatherSnake() {
		do {
			int fatherX = r.nextInt(mainwindow_sizex/3)+grandfather.getSizex();
			int fatherY = r.nextInt(mainwindow_sizey-2*grandfather.getSizey())+grandfather.getSizey();
			grandfather.setLoc(new Location(fatherX,fatherY));			
		}while(grandfather.hitCalaBash(calabashes) || grandfather.hitOthers(monsters)
				|| grandfather.hitAnother(snake));
		do {
			int snakeX = r.nextInt(mainwindow_sizex/2-snake.getSizex())+mainwindow_sizex/2;
			int snakeY = r.nextInt(mainwindow_sizey-2*snake.getSizey())+snake.getSizey();
			snake.setLoc(new Location(snakeX,snakeY));
		}while(snake.hitCalaBash(this.calabashes) || snake.hitOthers(monsters));
	}
	
	//��Ӽ����¼�����,�̳�KeyAdapter��
	private class KeyMonitor extends KeyAdapter{

		//��д���¼����¼��Ĵ���
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			//���¿ո��ʱ�ı�����
			case KeyEvent.VK_SPACE: 
				change_shape();
				break;
			default:break;
			}
		}
		//��д�ͷż����¼��Ĵ���
		@Override
		public void keyReleased(KeyEvent e) {
			
		}		
	}
	
	//�����ػ��߳�
	private class PaintThread implements Runnable{	
		@Override
		public void run() {
			while(true) {
				repaint();	//repaint()���ȵ���update(),�ٵ���paint()
				try {
					Thread.sleep(50);	//��ͣһ��ʱ��
				} catch (InterruptedException e) {
					e.printStackTrace();
				}					
			}
		}
	}

	
	private void run() {
		//����ҳ���ر���ͼ
		try {
			URL bkpURL = new URL(bkpAddr);
			bkp = tk.getImage(bkpURL);
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}

		//��Ӻ�«��
		for(int i=0;i<cbInfo_array.length;i++) {
			calabashes.add(new CalabashBoy(new Location(0,0),i,this));
		}
		//����߾�
		this.snake = new Snake(new Location(0,0),this);
		//�����үү
		this.grandfather = new GrandFather(new Location(0,0),this);
		this.set_shape();
		this.setLocGrandfatherSnake();
		//���ñ��⡢���ڴ�С���ر��¼�
		this.setTitle("java��«��");
		this.setSize(mainwindow_sizex, mainwindow_sizey);
		this.addWindowListener(new WindowAdapter() {	//���崰�ڹر��¼�
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);		//��ʾ�����˳�
			}
		});
		//����λ�þ���
		int locx = (screen.width - this.getWidth())/2;
		int locy = (screen.height - this.getHeight())/2;
		this.setLocation(locx, locy);
		//��Ӽ����¼�������
		this.addKeyListener(new KeyMonitor());
		//setVisible(true)��÷������,�Է����ֿ�ָ��
		this.setVisible(true);	
		this.setResizable(false);
		//�����ػ��̲߳�����
		new Thread(new PaintThread()).start();
	}
	
	
	public static void main(String[] args) {
		Administrator admin = new Administrator();
		admin.run();
	}

}
