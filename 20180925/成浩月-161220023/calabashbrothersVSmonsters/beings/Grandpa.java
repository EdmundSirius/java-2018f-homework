package beings;

import java.awt.Toolkit;
/*import java.io.*;
import javax.imageio.ImageIO;*/

public class Grandpa extends Good implements CheerUp{
	
	public Grandpa() {
		super("үү");
		// TODO �Զ����ɵĹ��캯�����
		/*this.imagepath=this.imagepath+"\\grandpa.jpg";
		try {
			image=ImageIO.read(new File(imagepath));
		}catch(IOException e) {
			System.out.println("error "+e);
		}*/
		/*java.net.URL imgURL = this.getClass().getResource("/beings/additions/grandpa.jpg");
		try {
			image = ImageIO.read(new File(imgURL.getFile()));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}*/
		image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/additions/grandpa.jpg"));
	}

	@Override
	public void behave() {
		// TODO �Զ����ɵķ������
		System.out.println("���Ͱ�С������");
	}

}
