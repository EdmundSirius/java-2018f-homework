package beings;

import java.awt.Color;

//�ı���ԭ�Ƚ�ö�����Ͷ�����CalabashBrother���е�����(�����Ļ���«�޴���������ȻҪ����һ��index�Ĳ���)
//���ں�«�޴���������봫��һ����ö��������Ϊ�����Դ������ƺ�«�޵�����
public enum CalabashAttributes {
	FIRST("����",Color.RED,"/c1.jpg"),SECOND("����",Color.ORANGE,"/c2.jpg"),THIRD("����",Color.YELLOW,"/c3.jpg"),FOURTH("����",Color.GREEN,"/c4.jpg"),FIFTH("����",Color.CYAN,"/c5.jpg"),SIXTH("����",Color.BLUE,"/c6.jpg"),SEVENTH("����",new Color(160,32,240),"/c7.jpg");
	String name;
	Color color;
	String path;
	CalabashAttributes(String name,Color color,String path){
		this.name=name;
		this.color=color;
		this.path=path;
	}
}
