package world.formations;

import java.util.HashMap;
import java.util.Map;

import world.creatures.Creature;
import world.util.*;

/**
 *	���ͳ�����.
 *	���������������� 0 ��ʼ
 *
 *	@author Mirror
 *	
 *	@see #type
 *	@see #pFormCen
 *	@see #formMap
 *	@see #isOccupied(Point)
 *	@see #getFormMap()
 */
public abstract class Formation<T extends Creature> {
		
	/**	�������� {@value} */
	private FormationType type;
	
	/**	������ռ���� {@value} */
	private int formRowNum;
	
	/**	������ռ���� {@value} */
	private int formColNum;
	
	/**	�������� {@value} */
	private Point pFormCen;
	
	/**	�����������(����, ʵ��)�Լ��� {@value} */
	public Map<Point, T> formMap;
	
	/**	���캯�����õ�����ֵ */
	protected Formation(FormationType t, int r, int c, int cr, int cc) {
		type = t;
		formRowNum = r;
		formColNum = c;
		pFormCen = new Point(cr, cc);
		formMap = new HashMap<Point, T>();
	}
	
	/**	�õ��������� */
	public FormationType getType() {
		return type;
	}
	
	/**	�õ��������� */
	public int getRowNum() {
		return formRowNum;
	}
	
	/**	�õ��������� */
	public int getColNum() { 
		return formColNum;
	}

	/**	�õ��������� */
	public Point getFormCen() {
		return pFormCen;
	}
	
	/**	�õ������ڵ�p���Ķ��� */
	public T getCreature(Point p) {
		return formMap.get(p);
	}
	
	/**	ĳ���Ƿ�����
	 *	@param p λ��p
	 *	@return {@code true}: ����; {@code false}: ���� */
	public boolean isOccupied(Point p) {
		return (formMap.containsKey(p));
	}
	/**	@see #isOccupied(Point) */
	public boolean isOccupied(int r, int c) {
		return isOccupied(new Point(r, c));
	}
		
	/**	��ӡ����(�ն��ַ���ʾ). ���ǵ����ú����������������ճ����� */
	public void showFormation() {
		for (int i = 0; i < formRowNum; i++) {
			for (int j = 0; j < formColNum; j++) {
				Point p = new Point(i, j);
				if (isOccupied(p))
					System.out.print(getCreature(p).getSymbol() + " ");
				else
					System.out.print("  ");
			}
			System.out.println();
		}
	}
}
