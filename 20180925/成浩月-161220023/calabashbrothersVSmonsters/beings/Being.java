package beings;

import java.awt.*;
/*import java.io.*;
import java.net.*;*/

//import javax.swing.ImageIcon;

public abstract class Being implements Behave {
	/*��������ֱ�Ӵ�������*/
	//ÿһ�֡����ڡ����������ֵ�,Ҳ�ж�Ӧ��ͼƬ��·��
	String name;
	//String imagepath;
	public Image image;
	public Being(String name){
		/*this.name=name;
		try {
			File directory = new File("");
			imagepath=directory.getCanonicalPath()+"\\beings\\additions";
		}catch(IOException e) {
			System.out.println("error "+e);
		}*/
		/*java.net.URL imgURL = this.getClass().getResource("pic/1.jpg");
		Icon startIcon = new ImageIcon(imgURL);*/
	}
}
