package characters;

//��«����Ϣ��ö������
public enum CalabashInfo {
	//ʵ����
	Red("�ϴ�"),
	Orange("�϶�"),
	Yellow("����"),
	Green("����"),
	Cyan("����"),
	Blue("����"),
	Purple("����");
	//���ݳ�Ա
	private String name;
	
	//���캯��
	private CalabashInfo(String name) {
		this.name=name;
	}
	
}
