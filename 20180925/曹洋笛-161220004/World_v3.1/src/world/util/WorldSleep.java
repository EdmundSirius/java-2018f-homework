package world.util;

/**	
 *	��GUI���磨�Զ�ģʽ����Ĭһ����ٽ���ˢ��
 *	
 *	@author Mirror
 */
public class WorldSleep {

	public static void worldSleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
