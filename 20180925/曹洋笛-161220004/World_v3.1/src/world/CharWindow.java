package world;

import world.creatures.*;
import world.formations.*;
import world.util.*;

/**
 *	�����磬����Ļ���. <br>
 *	�����ն���ʾ��������
 *
 *	@author Mirror
 *	 
 *	@see Creature
 *	@see Formation
 *	@see #broForm
 *	@see #monForm
 *	@see #snake
 *	@see #elder
 *	@see #pBroCen
 *	@see #pMonCen
 *	@see #pEld
 *	@see #pSnk
 *	@see #movGroup(GroupType, Point)
 *	@see #changeFormation(FormationType)
 *	@see #showWorld()
 */
public class CharWindow {

	// ����
	/**	�����«�����Ͷ��� */
	public Formation<Brothers> broForm;
	/**	�����������Ͷ��� */
	public Formation<Monsters> monForm;
	/**	�߾����� */
	public Snake snake = new Snake();
	/**	��үү���� */
	public Elder elder = new Elder();

	// ��λ
	/**	��ͼ���Ͻ� */
	private final Point LU = new Point(0, 0);
	/**	��ͼ���½� */
	private final Point RD = new Point(Range.rowNum - 1, Range.colNum - 1);
	/**	��«���������ĵ� */
	private Point pBroCen; 
	/**	�����������ĵ� */
	private Point pMonCen; 
	/**	��үүλ�� */
	private Point pEld;
	/**	�߾�λ�� */
	private Point pSnk;

	/**	���캯������ʼ�� */
	public CharWindow() {
		initAll();
	}

	/**	��ʼ�����ж�����λ�� */
	public void initAll() {
		broForm = new ChangShe(); // ��«�����ͣ�ֻ���ǳ�����
		monForm = new HeYi(); // �������ͣ���ʼ��Ϊ������
		snake = new Snake();
		elder = new Elder();
		// ��λ��ĳ�ʼ��
		pBroCen = Range.leftCenterP.copy();
		pMonCen = Range.rightCenterP.copy();
		pEld = Range.originElder.copy();
		pSnk = Range.originSnake.copy();
	}
	
	/**	@return ��«������λ�� */
	public Point getBroCen() {
		return pBroCen;
	}

	/**	@return ��������λ�� */
	public Point getMonCen() {
		return pMonCen;
	}

	/**	@return ��үүλ�� */
	public Point getEldPos() {
		return pEld;
	}

	/**	@return �߾�λ�� */
	public Point getSnkPos() {
		return pSnk;
	}
	
	/**	@param type ��������ֵ����Ϊ��������Ϊ�˺�����Ϊ���������ͱ任���裩
	 *	@return �������Ͷ��� */
	private Formation<Monsters> setForm(FormationType type) {
		switch (type) {
		case HY: return new HeYi(); 
		case YX: return new YanXing(); 
		case CE: return new ChongE();
		case YL: return new YuLin();
		case FY: return new FangYuan();
		case YY: return new YanYue();
		case FS: return new FengShi();
		default: return new HeYi(); 
		}
	}

	/**	@param p ��p
	 *	@return {@code true}: p���������ͼ��Χ֮�� */
	private boolean inRange(Point p) {
		if (p == null) return false; // debug
		return p.in(LU, RD);
	}

	/**	@param form ����form
	 *	 @param pCen ����λ�ã����ĵ�pCen
	 *	@return {@code true}: �������������ͼ��Χ֮�� */
	private <T extends Creature> boolean inRange(Formation<T> form, Point pCen) {
		if (form == null) return false; // debug
		for (Point p : form.formMap.keySet()) { // ��������λ����pCenΪ����
			if (!p.mov(pCen).mov(form.getFormCen().reverse()).in(LU, RD))
				return false;
		}
		return true;		
	}

	/**	@param p1 ��p1
	 *	@param p2 ��p2
	 *	@return p1����p2���ͻ
	 */
	private boolean inCollision(Point p1, Point p2) {
		if (p1 == null || p2 == null) return false; // ��Ϊ��һ������ͻ
		else return p1.equals(p2);
	}
	
	/**	@param p ��p
	 *	@param form ����form
	 *	@param pCen ����λ�ã����ĵ�pCen
	 *	@return {@code true}: p��������ͳ�ͻ */
	private <T extends Creature> boolean inCollision(Point p, Formation<T> form, Point pCen) {
		if (pCen == null) return true; // debug
		if (p == null || form == null) return false; // ��Ϊ��һ������ͻ
		for (Point pf : form.formMap.keySet()) { // ��������λ����pCenΪ����
			if (pf.mov(pCen).mov(form.getFormCen().reverse()).equals(p))
				return true;
		}
		return false;
	}

	/**	@param form1 ����1
	 *	@param p1Cen ����1λ�ã����ĵ�p1Cen
	 *	@param form2 ����2
	 *	@param p2Cen ����2λ�ã����ĵ�p2Cen
	 *	@return {@code true}: ����1������2��ͻ */
	private <T1 extends Creature, T2 extends Creature> boolean inCollision(Formation<T1> form1, Point p1Cen, Formation<T2> form2, Point p2Cen) {
		if (p1Cen == null || p2Cen == null) return true; // debug
		if (form1 == null || form2 == null) return false; // ��Ϊ��һ������ͻ
		for (Point p1 : form1.formMap.keySet()) {
			for (Point p2 : form2.formMap.keySet()) { // ��������λ����p1Cen, p2CenΪ����
				if (p1.mov(p1Cen).mov(form1.getFormCen().reverse())
						.equals(p2.mov(p2Cen).mov(form2.getFormCen().reverse()))) 
					return true;
			}
		}
		return false;
	}
	
	/**	@param p ��p
	 *	@param type ��������Ӫ
	 *	@return {@code true}: p�����԰ڷŵ� */
	private boolean checkGround(Point p, GroupType type) {
		if (p == null) return false; // debug
		if (!inRange(p)) {
			System.out.println("������ͼ��Χ");
			return false;
		}
		else if (type != GroupType.Eld && inCollision(p, pEld)) {
			System.out.println("��λ���ѱ���үүռ��");
			return false;
		}
		else if (type != GroupType.Snk && inCollision(p, pSnk)) {
			System.out.println("��λ���ѱ��߾�ռ��");
			return false;
		}
		else if (inCollision(p, broForm, pBroCen)) {
			System.out.println("��λ���ѱ���«��ռ��");
			return false;
		}
		else if (inCollision(p, monForm, pMonCen)) {
			System.out.println("��λ���ѱ�����ռ��");
			return false;
		}
		else return true;
	}

	/**	@param form ����form
	 *	@param pCen �����İڷŵ�pCen
	 *	@param type ��������Ӫ
	 *	@return {@code true}: pCen�����԰ڷŸ����� */
	private <T extends Creature> boolean checkGround(Formation<T> form, Point pCen, GroupType type) {
		if (form == null) return false; // debug
		if (!inRange(form, pCen)) {
			System.out.println("������ͼ��Χ");
			return false;
		}
		else if (inCollision(pEld, form, pCen)) {
			System.out.println("��λ���ѱ���үүռ��");
			return false;
		}
		else if (inCollision(pSnk, form, pCen)) {
			System.out.println("��λ���ѱ��߾�ռ��");
			return false;
		}
		else if (type != GroupType.Bro && inCollision(broForm, pBroCen, form, pCen)) {
			System.out.println("��λ���ѱ���«��ռ��");
			return false;
		}
		else if (type != GroupType.Mon && inCollision(monForm, pMonCen, form, pCen)) {
			System.out.println("��λ���ѱ�����ռ��");
			return false;
		}
		else return true;
	}
	
	/**	�����·����ƶ�����
	 *	@param form ΪbroForm/monForm
	 *	@param pCen ΪpBroCen/pMonCen
	 *	@param d ���λ��(d.row(), d.col())
	 *	@param type ���ƶ�����Ӫ */
	private <T extends Creature> void movFormation(Formation<T> form, Point pCen, Point d, GroupType type) {
		if (form == null || pCen == null) return; // debug
		if (d == null || d.equals(new Point(0, 0)))
			System.out.println("����δ�ƶ�");
		else if (checkGround(form, pCen.mov(d), type)) // �����ƶ�
			pCen.reset(pCen.mov(d));
	}

	/**	�����·����ƶ�������
	 *	@param p ΪpEld/pSnk
	 *	@param d ���λ��(d.row(), d.col())
	 *	@param type ���ƶ�����Ӫ */
	private void movPoint(Point p, Point d, GroupType type) {
		if (p == null) return; // debug
		if (d == null || d.equals(new Point(0, 0)))
			System.out.println("������δ�ƶ�");
		else if (checkGround(p.mov(d), type)) // �����ƶ�
			p.reset(p.mov(d));
	}
	
	/**	����Ӫtype�����·��ƶ�
	 *	@param type ������Ӫ
	 *	@param d ���λ��(d.row(), d.col()) */
	public void movGroup(GroupType type, Point d) {
		switch (type) {
		case Bro: movFormation(broForm, pBroCen, d, type); return;
		case Mon: movFormation(monForm, pMonCen, d, type); return;
		case Eld: movPoint(pEld, d, type); return;
		case Snk: movPoint(pSnk, d, type); return;
		default: return;
		}
	}

	/**	�ı��������͵�������type */
	public void changeFormation(FormationType type) {
		if (type == monForm.getType())
			System.out.println("����δ�ı�");
		else if (checkGround(setForm(type), pMonCen, GroupType.Mon)) // ���Ըı�
			monForm = setForm(type);
	}
	
	/**	��ʾ������ */
	public void showWorld() {
		// ��������Χ����ʾ
		if (!inRange(broForm, pBroCen) || !inRange(monForm, pMonCen) || !inRange(pEld) || !inRange(pSnk)) {
			System.out.println("�޷���ʾ��ͼ");
			return;
		}
		 // ��ʼ����ʾ�ĵ�ͼ
		char[][] cMap = new char[Range.rowNum][Range.colNum];
		for (int i = 0; i < Range.rowNum; i++) { 
			for (int j = 0; j < Range.colNum; j++)
				cMap[i][j] = ' '; 
		}
		// �ֱ�Ѻ�«�ޡ����֡������ӷ���ȥ
		for (Point bp : broForm.formMap.keySet())
			cMap[bp.row() + pBroCen.row() - broForm.getFormCen().row()]
				[bp.col() + pBroCen.col() - broForm.getFormCen().col()] 
						= broForm.getCreature(bp).getSymbol();
		for (Point mp : monForm.formMap.keySet())
			cMap[mp.row() + pMonCen.row() - monForm.getFormCen().row()]
				[mp.col() + pMonCen.col() - monForm.getFormCen().col()] 
						= monForm.getCreature(mp).getSymbol();
		cMap[pEld.row()][pEld.col()] = elder.getSymbol();
		cMap[pSnk.row()][pSnk.col()] = snake.getSymbol();
		// ��ʼ��ӡ��ͼ
		char boundary = '#';
		// ��һ�У���ͼ�߽�
		for (int j = 0; j < Range.colNum + 2; j++)
			System.out.print(boundary + " ");
		System.out.println();
		// ��ͼ����
		for (int i = 0; i < Range.rowNum; i++) {
			System.out.print(boundary);
			for (int j = 0; j < Range.colNum; j++)
				System.out.print(" " + cMap[i][j]);
			System.out.println(" " + boundary);
		}
		// ���һ�У���ͼ�߽�
		for (int j = 0; j < Range.colNum + 2; j++)
			System.out.print(boundary + " ");
		System.out.println();
	}
}
