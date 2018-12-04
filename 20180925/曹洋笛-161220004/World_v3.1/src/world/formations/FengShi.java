package world.formations;

import world.creatures.Monsters;
import world.creatures.Scorpion;
import world.util.*;

/**
 *	 ��ʸ��row*col = 7 * 6.
 *	
 *	 		0	1	2	3	4	5
 *	 
 *	0		O	O	&	O	O	O
 *	1		O	&	O	O	O	O
 *	2		&	O	O	O	O	O
 *	3		&	&	&	&	&	&
 *	4		&	O	O	O	O	O
 *	5		O	&	O	O	O	O
 *	6		O	O	&	O	O	O
 *	
 *	@author Mirror
 */
public class FengShi extends Formation<Monsters> {
	
	public FengShi() {		
		super(FormationType.FS, 7, 6, 3, 2); // ������ͼ��ռ���й���
		// ���ֵ�λ��
		formMap.put(new Point(0, 2), new Monsters());
		formMap.put(new Point(1, 1), new Monsters());
		formMap.put(new Point(2, 0), new Monsters());
		formMap.put(new Point(3, 0), new Scorpion()); // Ы�Ӿ�
		formMap.put(new Point(3, 1), new Monsters());
		formMap.put(new Point(3, 2), new Monsters());
		formMap.put(new Point(3, 3), new Monsters());
		formMap.put(new Point(3, 4), new Monsters());
		formMap.put(new Point(3, 5), new Monsters());
		formMap.put(new Point(4, 0), new Monsters());
		formMap.put(new Point(5, 1), new Monsters());
		formMap.put(new Point(6, 2), new Monsters());
	}
}
