package beings;

import java.awt.Toolkit;
/*import java.io.*;
import javax.imageio.*;*/


public class CalabashBrother extends Good implements Battle{
	public CalabashAttributes attributes;
	public CalabashBrother(CalabashAttributes attributes) {
		super(attributes.name);
		// TODO �Զ����ɵĹ��캯�����
		this.attributes=attributes;
		//java.net.URL imgURL = this.getClass().getResource("/beings/additions"+attributes.path);
		//System.out.println(imgURL.toString());
		/*File file=new File("");
		try {
			System.out.println(file.getCanonicalPath());
			System.out.println("1");
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}*/
		/*System.out.println(this.getClass().getResource(""));
		try {
			image = ImageIO.read(new File(imgURL.getFile()));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}*/
		image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/additions"+attributes.path));
	}
	@Override
	public void behave() {
		// TODO �Զ����ɵķ������
		System.out.println("��Ѽ");
	}

}
