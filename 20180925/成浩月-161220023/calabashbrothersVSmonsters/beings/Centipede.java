package beings;

import java.awt.Toolkit;
/*import java.io.*;
import javax.imageio.*;*/

public class Centipede extends Follower implements Battle {

	public Centipede() {
		super("��򼾫");
		// TODO �Զ����ɵĹ��캯�����
		/*this.imagepath=this.imagepath+"\\centipede.jpg";
		try {
			image=ImageIO.read(new File(imagepath));
		}catch(IOException e) {
			System.out.println("error "+e);
		}*/
		/*java.net.URL imgURL = this.getClass().getResource("/beings/additions/centipede.jpg");
		try {
			image = ImageIO.read(new File(imgURL.getFile()));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}*/
		image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/additions/centipede.jpg"));
	}

	@Override
	public void behave() {
		// TODO �Զ����ɵķ������
		
	}

}
