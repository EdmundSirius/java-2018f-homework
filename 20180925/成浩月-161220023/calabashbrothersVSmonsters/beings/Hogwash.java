package beings;

import java.awt.Toolkit;
/*import java.io.*;
import javax.imageio.*;*/

public class Hogwash extends Follower implements Battle {

	public Hogwash() {
		super("����");
		// TODO �Զ����ɵĹ��캯�����
		/*this.imagepath=this.imagepath+"\\hogwash.jpg";
		try {
			image=ImageIO.read(new File(imagepath));
		}catch(IOException e) {
			System.out.println("error "+e);
		}*/
		/*java.net.URL imgURL = this.getClass().getResource("/beings/additions/hogwash.jpg");
		try {
			image = ImageIO.read(new File(imgURL.getFile()));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}*/
		image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/additions/hogwash.jpg"));
	}

	@Override
	public void behave() {
		// TODO �Զ����ɵķ������
		
	}

}
