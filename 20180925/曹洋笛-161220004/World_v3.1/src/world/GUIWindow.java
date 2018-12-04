package world;


import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import world.creatures.Creature;
import world.formations.*;
import world.util.*;

/**
 *	�����磬���������. <br>
 *	��ȫ����������{@code CharWindow}���Ա�֤ͬ�� <br>
 *	����GUI��ʾ��������
 *
 *	@author Mirror
 *	
 *	@see CharWindow
 *	@see #cWin
 *	@see #menuBar
 *	@see #ground
 *	@see #cell
 *	@see #removeAtFirst()
 *	@see #showAtLast()
 */
public class GUIWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	/**	����λ�� {@value} */
	private static final int winLocationXY = 10;
	
	/**	���ڳ� {@value} */
	private static final int winWidth = 814;
	
	/**	���ڿ� {@value} */
	private static final int winHeight = 822;
	
	/**	ͼƬ�� {@value} */
	private static final int groundWidth = 800;
	
	/**	ͼƬ�� {@value} */
	private static final int groundHeight = 760;	
	
	/**	ͼƬ�߾ࣨ�� {@value} */
	private static final int marginWidth = 40;	
	
	/**	ͼƬ�߾ࣨ�ߣ� {@value} */
	private static final int marginHeight = 40 + 25;	
	
	/**	������ {@value} */
	private static final int span = 40;
	
	/**	����˵��� */
	private JMenuBar menuBar;
	
	/**	���塰��ʼ���˵����˵���Ϊ�����á� */
	private JMenu menuStart; 
	/**	����ʼ���˵�������á� */
	private JMenuItem menuItReset;
	
	/**	���塰���͡��˵����˵���Ϊ���ֵ����� */
	private JMenu menuFormation;
	/**	�����͡��˵�����ֵ����� */
	private JMenuItem menuItHY, menuItYX, menuItCE, 
						menuItYL, menuItFY, menuItYY, menuItFS;
	
	/**	�˵��¼����� */
	private MenuMonitor menuMonitor;
	
	/**	��һ�����ͣ������ڴ�ϳ�ʼ���Զ�ģʽ֮�� */
	private FormationType nextForm;
	
	/**	�����¼����� */
	private KeyMonitor keyMonitor;
	
	/**	���屳��ͼƬ���ڱ�ǩ */
	private JLabel ground;

	/**	����һȺ�ձ�ǩ�Է������� */
	private JLabel[][] cell;
	
	/**	�Ƿ�ֹͣ�Զ�ģʽ */
	private boolean isAuto;
	
	/**	GUI�����ԭ�� ���� char���� */
	private CharWindow cWin;
	
	/**	���캯������char����Ϊ����������GUI���細�� */
	public GUIWindow(CharWindow w) {
		super("��«�޴�ս����"); // ���ñ���
		// ���ò˵���
		menuBar = new JMenuBar();
		menuStart = new JMenu("��ʼ");
		menuItReset = new JMenuItem("����");
		menuFormation = new JMenu("����");
		menuItHY = new JMenuItem("������");
		menuItYX = new JMenuItem("������");
		menuItCE = new JMenuItem("������");
		menuItYL = new JMenuItem("������");
		menuItFY = new JMenuItem("������");
		menuItYY = new JMenuItem("������");
		menuItFS = new JMenuItem("��ʸ��");
		menuStart.add(menuItReset);
		menuFormation.add(menuItHY);
		menuFormation.add(menuItYX);
		menuFormation.add(menuItCE);
		menuFormation.add(menuItYL);
		menuFormation.add(menuItFY);
		menuFormation.add(menuItYY);
		menuFormation.add(menuItFS);
		menuBar.add(menuStart);
		menuBar.add(menuFormation);
		this.add(menuBar, BorderLayout.NORTH); // ��Ӳ˵���
		this.setSize(winWidth, winHeight);
		this.setLocation(winLocationXY, winLocationXY); // ����λ��
		this.setResizable(false); // ���ڴ�С���ɸ���
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// ��ʼ�����б�ǩ
		ground = null;
		cell = new JLabel[Range.rowNum][Range.colNum];
		for (int i = 0; i < Range.rowNum; i++) {
			for (int j = 0; j < Range.colNum; j++)
				cell[i][j] = null; 
		}
		// ���ü��������
		keyMonitor = new KeyMonitor();
		this.addKeyListener(keyMonitor);
		// ���ò˵������
		menuMonitor = new MenuMonitor();
		menuItReset.addActionListener(menuMonitor);
		menuItHY.addActionListener(menuMonitor);
		menuItYX.addActionListener(menuMonitor);
		menuItCE.addActionListener(menuMonitor);
		menuItYL.addActionListener(menuMonitor);
		menuItFY.addActionListener(menuMonitor);
		menuItYY.addActionListener(menuMonitor);
		menuItFS.addActionListener(menuMonitor);
		// ���� char ���磬��ʼ����
		cWin = w; 
		nextForm = FormationType.HY;
		autoWorld();
	}
	
	/**	�ڴ�������ӵ�ͼ */
	private void setGround() {
		ImageIcon groundIcon = new ImageIcon("img\\BackGround.png");
		groundIcon.setImage(groundIcon.getImage().getScaledInstance(groundWidth, groundHeight,Image.SCALE_DEFAULT));
		ground = new JLabel(groundIcon, JLabel.CENTER);
		this.add(ground);
	}

	/**	�Ӵ��������ߵ�ͼ */
	private void removeGround() {
		if (ground != null)
			this.getContentPane().remove(ground);
	}

	/**	�ڴ����Ϸ����� */
	private void setCreature(int r, int c, CreatureType t) {
		ImageIcon creatureIcon = new ImageIcon("img\\" + t.imgName + ".png");
		creatureIcon.setImage(creatureIcon.getImage().getScaledInstance(span, span,Image.SCALE_DEFAULT));
		cell[r][c] = new JLabel(creatureIcon, JLabel.CENTER);
		cell[r][c].setSize(span, span);
		cell[r][c].setLocation(marginWidth + c * span, marginHeight + r * span);
		this.add(cell[r][c]);
	}

	/**	�Ӵ������������� */
	private void removeCreature(int r, int c) {
		if (cell[r][c] != null)
			this.getContentPane().remove(cell[r][c]);
	}
	
	/**	�ڴ����Ϸ�����.
	 *	��������Ϊform����������Ϊ(r, c) */
	private <T extends Creature> void setFormation(int r, int c, Formation<T> form) {
		// ��������������(����, ʵ��)��
		for (Point p : form.formMap.keySet())
			setCreature(p.row() + r - form.getFormCen().row(),
					p.col() + c - form.getFormCen().col(), form.getCreature(p).getType());
		
	}

	/**	�Ӵ�������������.
	 *	��������Ϊform����������Ϊ(r, c) */
	private <T extends Creature> void removeFormation(int r, int c, Formation<T> form) {
		// ��������������(����, ʵ��)��
		for (Point p : form.formMap.keySet())
			removeCreature(p.row() + r - form.getFormCen().row(),
					p.col() + c - form.getFormCen().col());
		
	}

	/**	���GUI���� */
	public void removeAtFirst() {
		removeGround();
		removeFormation(cWin.getBroCen().row(), cWin.getBroCen().col(), cWin.broForm);
		removeFormation(cWin.getMonCen().row(), cWin.getMonCen().col(), cWin.monForm);
		removeCreature(cWin.getEldPos().row(), cWin.getEldPos().col());
		removeCreature(cWin.getSnkPos().row(), cWin.getSnkPos().col());
	}

	/**	����char��������ӻ���GUI���� */
	public void showAtLast() {
		setFormation(cWin.getBroCen().row(), cWin.getBroCen().col(), cWin.broForm);
		setFormation(cWin.getMonCen().row(), cWin.getMonCen().col(), cWin.monForm);
		setCreature(cWin.getEldPos().row(), cWin.getEldPos().col(), cWin.elder.getType());
		setCreature(cWin.getSnkPos().row(), cWin.getSnkPos().col(), cWin.snake.getType());
		setGround();
		this.setVisible(true);
	}
	
	/**	���������� */
	class KeyMonitor implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyPressed(KeyEvent e) {
		}
		@Override
		public void keyReleased(KeyEvent e) {
			if (isAuto) return; // �Զ�ģʽ��֧��ʹ�ü���
			removeAtFirst();
			switch (e.getKeyCode()) {
			// �ƶ���«�ޣ�WDSA
			case KeyEvent.VK_W: cWin.movGroup(GroupType.Bro, new Point(-1, 0)); break;
			case KeyEvent.VK_D: cWin.movGroup(GroupType.Bro, new Point(0, 1)); break;
			case KeyEvent.VK_S: cWin.movGroup(GroupType.Bro, new Point(1, 0)); break;
			case KeyEvent.VK_A: cWin.movGroup(GroupType.Bro, new Point(0, -1)); break;
			// �ƶ����֣�UP/RIGHT/DOWN/LEFT
			case KeyEvent.VK_UP: cWin.movGroup(GroupType.Mon, new Point(-1, 0)); break;
			case KeyEvent.VK_RIGHT: cWin.movGroup(GroupType.Mon, new Point(0, 1)); break;
			case KeyEvent.VK_DOWN: cWin.movGroup(GroupType.Mon, new Point(1, 0)); break;
			case KeyEvent.VK_LEFT: cWin.movGroup(GroupType.Mon, new Point(0, -1)); break;
			// �ƶ���үү��THGF
			case KeyEvent.VK_T: cWin.movGroup(GroupType.Eld, new Point(-1, 0)); break;
			case KeyEvent.VK_H: cWin.movGroup(GroupType.Eld, new Point(0, 1)); break;
			case KeyEvent.VK_G: cWin.movGroup(GroupType.Eld, new Point(1, 0)); break;
			case KeyEvent.VK_F: cWin.movGroup(GroupType.Eld, new Point(0, -1)); break;
			// �ƶ��߾���ILKJ
			case KeyEvent.VK_I: cWin.movGroup(GroupType.Snk, new Point(-1, 0)); break;
			case KeyEvent.VK_L: cWin.movGroup(GroupType.Snk, new Point(0, 1)); break;
			case KeyEvent.VK_K: cWin.movGroup(GroupType.Snk, new Point(1, 0)); break;
			case KeyEvent.VK_J: cWin.movGroup(GroupType.Snk, new Point(0, -1)); break;
			}
			cWin.showWorld();
			showAtLast();
		}
	}
	
	/**	�����˵��� */
	class MenuMonitor implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == menuItReset)
				handleReset();
			else if (e.getSource() == menuItHY)
				handleMon(FormationType.HY);
			else if (e.getSource() == menuItYX)
				handleMon(FormationType.YX);
			else if (e.getSource() == menuItCE)
				handleMon(FormationType.CE);
			else if (e.getSource() == menuItYL)
				handleMon(FormationType.YL);
			else if (e.getSource() == menuItFY)
				handleMon(FormationType.FY);
			else if (e.getSource() == menuItYY)
				handleMon(FormationType.YY);
			else if (e.getSource() == menuItFS)
				handleMon(FormationType.FS);
		}
	}
	
	/**	�˵�������ʼ��->�����á� */
	public void handleReset() {
		isAuto = false;
		removeAtFirst();
		cWin.initAll();
		cWin.showWorld();
		showAtLast();
	}
	
	/**	�˵�������ʼ��->�����͡� */
	public void handleMon(FormationType type) {
		isAuto = false;
		nextForm = type;
		removeAtFirst();
		cWin.changeFormation(type);
		cWin.showWorld();
		showAtLast();
	}

	/**	�Զ�ģʽ */
	public void autoWorld() {
		isAuto = true;
		while (isAuto) {
			// 1�غϣ����ְں�����
			removeAtFirst();
			cWin.initAll();
			cWin.showWorld();
			showAtLast();
			if (!isAuto) break;
			// 2�غϣ����ְ�������
			WorldSleep.worldSleep(500);
			removeAtFirst();
			cWin.changeFormation(FormationType.YX);
			cWin.movGroup(GroupType.Bro, new Point(0, 1));
			cWin.movGroup(GroupType.Eld, new Point(0, -2));
			cWin.movGroup(GroupType.Snk, new Point(0, 2));
			cWin.showWorld();
			showAtLast();
			if (!isAuto) break;
			// 3�غϣ����ְڳ�����
			WorldSleep.worldSleep(500);
			removeAtFirst();
			cWin.changeFormation(FormationType.CE);
			cWin.movGroup(GroupType.Bro, new Point(1, 0));
			cWin.movGroup(GroupType.Eld, new Point(-2, 1));
			cWin.movGroup(GroupType.Snk, new Point(2, -1));
			cWin.showWorld();
			showAtLast();
			if (!isAuto) break;
			// 4�غϣ����ְ�������
			WorldSleep.worldSleep(500);
			removeAtFirst();
			cWin.changeFormation(FormationType.YL);
			cWin.movGroup(GroupType.Bro, new Point(0, -1));
			cWin.movGroup(GroupType.Eld, new Point(2, 1));
			cWin.movGroup(GroupType.Snk, new Point(-2, -1));
			cWin.showWorld();
			showAtLast();
			if (!isAuto) break;
			// 5�غϣ����ְڷ�����
			WorldSleep.worldSleep(500);
			removeAtFirst();
			cWin.changeFormation(FormationType.FY);
			cWin.movGroup(GroupType.Bro, new Point(-1, 0));
			cWin.movGroup(GroupType.Eld, new Point(0, -2));
			cWin.movGroup(GroupType.Snk, new Point(0, 2));
			cWin.showWorld();
			showAtLast();
			if (!isAuto) break;
			// 6�غϣ����ְ�������
			WorldSleep.worldSleep(500);
			removeAtFirst();
			cWin.changeFormation(FormationType.YY);
			cWin.movGroup(GroupType.Bro, new Point(0, 2));
			cWin.movGroup(GroupType.Eld, new Point(-2, 1));
			cWin.movGroup(GroupType.Snk, new Point(2, -1));
			cWin.showWorld();
			showAtLast();
			if (!isAuto) break;
			// 7�غϣ����ְڷ�ʸ��
			WorldSleep.worldSleep(500);
			removeAtFirst();
			cWin.changeFormation(FormationType.FS);
			cWin.movGroup(GroupType.Bro, new Point(0, -2));
			cWin.movGroup(GroupType.Eld, new Point(2, 1));
			cWin.movGroup(GroupType.Snk, new Point(-2, -1));
			cWin.showWorld();
			showAtLast();
			WorldSleep.worldSleep(500);
		}
		removeAtFirst();
		cWin.initAll();
		cWin.changeFormation(nextForm);
		cWin.showWorld();
		showAtLast();
	}
}
