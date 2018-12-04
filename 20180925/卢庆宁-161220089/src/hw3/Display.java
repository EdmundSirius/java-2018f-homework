package hw3;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display{
	int height;							//�߶�
	int width;							//���
	int badNumber=0;					//�������������˲���δ֪
	int badSetCount=0;					//��¼�Ѿ��ںõĹ������
	public static final int sizeOfPic=50;			//��ͼ�Ĵ�С
	private JFrame frame;				//�����塱
	private JPanel panel;				//��������
	private JLabel good0;				//����ΪͼƬ��ǩ
	private JLabel[] good1;
	private JLabel bad0;
	private JLabel[] bad1;
	private JLabel backGround;			//������ͬ������ͼƬ��ǩʵ��
	private JButton button;				//��ť
	private int buttonCount=0;			//��ť��������ͳ��
	//private JTextArea textArea;		//�ı���
	private JTextPane textPane;			//���ı���
	public int getButtonCount() {return buttonCount;}
	public void setButtonCount(int i) {buttonCount=i;}
	public Display(Board b,int num) 
	{
		initSize(b,num);
		initFrame();
        initPanel();
        initLabels(); 
        initButton();
        initTextArea();
        initBackGround();
        frame.setContentPane(panel);
        frame.setVisible(true);       
	}
	public void initSize(Board b,int num)
	{
		width=sizeOfPic*b.getWidth()+2*sizeOfPic+6*sizeOfPic;
		height=sizeOfPic*b.getHeight()+4*sizeOfPic;
		badNumber=num;
	}
	public void initFrame()
	{
		frame = new JFrame("��«��");
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
        frame.setResizable(false);
	}
	public void initPanel()
	{
		panel=new JPanel();
		panel.setSize(width, height);
        panel.setLayout(null);
	}
	public void initLabels() 
	{
        good0=new JLabel();
        good0.setIcon(new ImageIcon("pic/0.jpg"));
        panel.add(good0);
        good1 = new JLabel[7]; 
        for (int i = 0; i < 7; i++) 
        { 
        	 good1[i] = new JLabel(); 
        	 good1[i].setIcon(new ImageIcon("pic/"+String.valueOf(i+1)+".jpg"));
        	 panel.add(good1[i]);
        }
        bad0=new JLabel(); 
        bad0.setIcon(new ImageIcon("pic/snake.jpg"));
        panel.add(bad0);
        bad1 = new JLabel[badNumber]; 
        bad1[0]=new JLabel(); 
        bad1[0].setIcon(new ImageIcon("pic/scorpion.jpg"));
        panel.add(bad1[0]);
        for (int i = 1; i < badNumber; i++) 
        { 
        	 bad1[i] = new JLabel();
        	 bad1[i].setIcon(new ImageIcon("pic/small.jpg"));
        	 panel.add(bad1[i]);
        }
	}
	public void initButton()
	{
		button = new JButton("����");
        button.setBounds(width/2-50, height-100, 100, 50);
        button.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                buttonCount++;
            }
        });
        panel.add(button);
	}
	public void initTextArea()
	{
        /*textArea = new JTextArea();
        textArea.setBounds(width-5*sizeOfPic, 0, 5*sizeOfPic, height);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("����", Font.PLAIN, 15));   
        textArea.setEditable(false);*/
		
		textPane = new JTextPane(); 
        textPane.setBounds(width-5*sizeOfPic, 0, 5*sizeOfPic, height);
        textPane.setEditable(false); 
        
        JScrollPane scrollPane = new JScrollPane(
                //textArea,
                textPane,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.setBounds(width-5*sizeOfPic, 0, 5*sizeOfPic, height);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Log"));
        panel.add(scrollPane);
	}
	public void initBackGround()
	{
		backGround=new JLabel(new ImageIcon("pic/background.jpg"));
        backGround.setBounds(0,0,width,height);
        frame.getLayeredPane().add(backGround, new Integer(Integer.MIN_VALUE));
        panel.add(backGround); 
	}
	public void change(Board b)					//�ı�����ͼƬ��ǩ��λ�ã���ˢ��frame
	{
		badSetCount=0;
		for(int i=0;i<b.getHeight();i++)
		{
			for(int j=0;j<b.getWidth();j++)
			{
				setLabel(b.getCh(i, j),i,j);
			}
		}
		frame.validate();
	}
	public void setLabel(char ch,int i,int j)	//����Ӧ��ͼƬ��ǩ���뵽��Ӧλ��
	{
		switch(ch)
		{
		case '0':good0.setBounds(sizeOfPic*j+sizeOfPic, sizeOfPic*i+sizeOfPic,sizeOfPic,sizeOfPic);break;
		case '1':good1[0].setBounds(sizeOfPic*j+sizeOfPic, sizeOfPic*i+sizeOfPic,sizeOfPic,sizeOfPic);break;
		case '2':good1[1].setBounds(sizeOfPic*j+sizeOfPic, sizeOfPic*i+sizeOfPic,sizeOfPic,sizeOfPic);break;
		case '3':good1[2].setBounds(sizeOfPic*j+sizeOfPic, sizeOfPic*i+sizeOfPic,sizeOfPic,sizeOfPic);break;
		case '4':good1[3].setBounds(sizeOfPic*j+sizeOfPic, sizeOfPic*i+sizeOfPic,sizeOfPic,sizeOfPic);break;
		case '5':good1[4].setBounds(sizeOfPic*j+sizeOfPic, sizeOfPic*i+sizeOfPic,sizeOfPic,sizeOfPic);break;
		case '6':good1[5].setBounds(sizeOfPic*j+sizeOfPic, sizeOfPic*i+sizeOfPic,sizeOfPic,sizeOfPic);break;
		case '7':good1[6].setBounds(sizeOfPic*j+sizeOfPic, sizeOfPic*i+sizeOfPic,sizeOfPic,sizeOfPic);break;
		case 'S':bad0.setBounds(sizeOfPic*j+sizeOfPic, sizeOfPic*i+sizeOfPic,sizeOfPic,sizeOfPic);break;
		case '$':bad1[0].setBounds(sizeOfPic*j+sizeOfPic, sizeOfPic*i+sizeOfPic,sizeOfPic,sizeOfPic);break;
		case 'o':bad1[(badSetCount++)+1].setBounds(sizeOfPic*j+sizeOfPic, sizeOfPic*i+sizeOfPic,sizeOfPic,sizeOfPic);break;
		default:break;
		}
	}
	public void setLog(String str)				//���ı����д�ӡlog
	{
        SimpleAttributeSet attrset = new SimpleAttributeSet(); 
        StyleConstants.setFontSize(attrset,15); 
        StyleConstants.setFontFamily(attrset,"����");
        
        Document docs = textPane.getDocument(); 
        try { 
            docs.insertString(docs.getLength(), str, attrset); 
        } catch (BadLocationException e) { 
            e.printStackTrace(); 
        }

        //textArea.append(str);
	}
}
