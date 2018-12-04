package world.formations;

import world.creatures.Monsters;
import world.creatures.Scorpion;
import world.util.*;

/**
 *	 ������row*col = 5 * 5.
 *	
 *	 		0	1	2	3	4
 *	 
 *	0		O	O	&	O	O
 *	1		O	&	O	&	O
 *	2		&	O	O	O	&
 *	3		O	&	O	&	O
 *	4		O	O	&	O	O
 *	
 *	@author Mirror
 */
public class FangYuan extends Formation<Monsters> {
	
	public FangYuan() {		
		super(FormationType.FY, 5, 5, 2, 2); // ������ͼ��ռ���й���
		// ���ֵ�λ��
		formMap.put(new Point(0, 2), new Monsters());
		formMap.put(new Point(1, 1), new Monsters());
		formMap.put(new Point(1, 3), new Monsters());
		formMap.put(new Point(2, 0), new Scorpion()); // Ы�Ӿ�
		formMap.put(new Point(2, 4), new Monsters());
		formMap.put(new Point(3, 1), new Monsters());
		formMap.put(new Point(3, 3), new Monsters());
		formMap.put(new Point(4, 2), new Monsters());
	}
}
