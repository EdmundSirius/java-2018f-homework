package world.util;

/**	
 *	�������ϸ��𻮷�. <br>
 *	ֻҪ���ڲ�ͬ��Ϊ��ͬ�����࣬���в�ͬ��enum
 *
 *	@author Mirror
 */
public enum CreatureType {
	Bro1("��«��", "�ϴ�", "bro1"),
	Bro2("��«��", "�϶�", "bro2"),
	Bro3("��«��", "����", "bro3"),
	Bro4("��«��", "����", "bro4"),
	Bro5("��«��", "����", "bro5"),
	Bro6("��«��", "����", "bro6"),
	Bro7("��«��", "����", "bro7"),
	Scorp("����", "Ы�Ӿ�", "scorp"),
	Mons("����", "Сආ�", "mons"),
	Snk("������", "�߾�", "snk"),
	Eld("������", "��үү", "eld");
	
	public String rough; // ���������
	public String detail; // ϸ�������
	public String imgName; // ��ӦͼƬ��
	
	/**	enum �Ĺ��캯��
	 *	@param rou ���������
	 *	@param det ϸ�������
	 *	@param nam ��ӦͼƬ�� */
	private CreatureType(String rou, String det, String nam) {
		this.rough = rou;
		this.detail = det;
		this.imgName = nam;
	}
}
