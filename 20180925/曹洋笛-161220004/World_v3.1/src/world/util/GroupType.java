package world.util;

/**	
 *	��Ӫ���ͣ�����ͬ�ƶ��Ļ�����λ��
 * 
 *	@author Mirror
 */
public enum GroupType {

	Bro("��«��"),
	Mon("����"),
	Eld("��үү"),
	Snk("�߾�");

	public String name;
	private GroupType(String name) { // ���췽��
		this.name = name;
	}
}
