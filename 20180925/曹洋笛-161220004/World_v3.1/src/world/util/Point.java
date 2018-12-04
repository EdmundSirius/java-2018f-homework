package world.util;

import java.util.Objects;

/**
 *	�����ж�λ�ĵ�����
 *	
 *	@author Mirror
 *
 *	@see #pRow
 *	@see #pCol
 *	@see #row()
 *	@see #col()
 *	@see #equals(Object)
 *	@see #hashCode()
 *	@see #reset(Point)
 *	@see #copy()
 *	@see #reverse()
 *	@see #mov(Point)
 */
public class Point {

	/**	�ж�λ */
	private int pRow;
	/**	�ж�λ */
	private int pCol;

	/**	���캯�� */
	public Point(int r, int c) {
		pRow = r;
		pCol = c;
	}
	
	/**	�õ����������� */
	public int row() {
		return pRow;
	}

	/**	�õ����������� */
	public int col() {
		return pCol;
	}

	/**	��д Object �� {@code Object.equals(key, k)} ����.
	 *	���� {@code Formation.formMap} Ѱ�Ҽ�ֵ�� {@code formMap.containsKey(point)} ʹ�õ���
	 *	�� Object �� {@code Object.equals(key, k)} ����������ʹ����HashCode��
	 *	�����Ҫ��д�Ա�֤�Ƚϵ���ֵ���ǵ�ַ
	 *
	 *	@param obj ��˵�Ƚ�����λ�ñȽ�
	 *	@return boolean �Ƿ����  */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Point) {
			Point p = (Point) obj;
			return ((this.pRow == p.pRow) && (this.pCol == p.pCol));
		}
		return false;
	}
	
	/**	��д Object �� {@code Object.hashCode()} ����.
	 *	���� Map ���ͣ�HashMap������д equals ��ͬʱҲҪ��д hashCode��
	 *	��Ϊ hashCode ��ֵ�Ǹ��ݵ�ַ���ü���Ķ����Ǹ���ֵ */
	@Override
	public int hashCode() {
		return Objects.hash(pRow, pCol);
	}

	/**	@param pLU ĳ������Χ�����Ͻ���������
	 *	@param pRD ĳ������Χ�����½���������
	 *	@return boolean �˵��Ƿ��ڸó�����Χ�� */
	public boolean in(Point pLU, Point pRD) {
		return ((pRow >= pLU.row()) && (pRow <= pRD.row()) && (pCol >= pLU.col()) && (pCol <= pRD.col()));
	}

	/**	Ϊ�˵�������������Ϊ(r, c) */
	public void reset(Point p) {
		pRow = p.pRow;
		pCol = p.pCol;
	}
	
	/**	�õ�һ�����Ƶĵ� */
	public Point copy() {
		return new Point(pRow, pCol);
	}

	/**	�õ�һ������ĵ� */
	public Point reverse() {
		return new Point(-pRow, -pCol);
	}
	
	/**	�����·����ƶ��˵㣬���λ��(d.row(), d.col()) */
	public Point mov(Point d) {
		return new Point(pRow + d.pRow, pCol + d.pCol);
	}
	
	/**	��ӡ�˵� */
	public void show() {
		System.out.print(pRow);
		System.out.print(", ");
		System.out.println(pCol);
	}
}
