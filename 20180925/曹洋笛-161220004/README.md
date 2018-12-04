#
# World v3.1 ��
- ��ǰ�汾
- �� **Formation** ����ӷ���  
- �� CharWindow �� GUIWindow ���漰 Formation ��ķ�����ӷ���  
- �Բ��ᱻ�̳е�������� final �ؼ���

## ��ϸ˵���������޸�3.0�Ĳ��ֽ���˵����
### ��һ��Formation
Formation �࣬һ��������ĸ��ࡣ�����������еķ��˺�«�ޣ������󣩣��еķ������֣����˳�������������ͣ���Ϊ��ֱ�ۣ��� Formation �����˷��ʹ����ܵ�Ӱ��ķ����ͱ������£� 
```java
public abstract class Formation<T extends Creature> {
    ...
    /** ���͵� <λ�ã�����> ���� */
    public Map<Point, T> formMap;
    /** ���캯����������Ĺ��캯���е��� */
    protected Formation(FormationType t, int r, int c, int cr, int cc) {
        type = t;
        formRowNum = r;
        formColNum = c;
        pFormCen = new Point(cr, cc);
        formMap = new HashMap<Point, T>();
    }
    /** �õ������ڵ�p���Ķ��� */
    public T getCreature(Point p) {
        return formMap.get(p);
    }
    ...
}
```
��Ȼ���౻�ı��ˣ�8����������Ҳ��Ҫ�����������������Ժ�����Ϊ��������ͬ��
```java
public final class HeYi extends Formation<Monsters> {
    public HeYi() {		
        super(FormationType.HY, 7, 4, 3, 2); // ������ͼ��ռ���й���
        // ���ֵ�λ��
        formMap.put(new Point(0, 3), new Monsters());
        formMap.put(new Point(1, 2), new Monsters());
        formMap.put(new Point(2, 1), new Monsters());
        formMap.put(new Point(3, 0), new Scorpion()); // Ы�Ӿ�
        formMap.put(new Point(4, 1), new Monsters());
        formMap.put(new Point(5, 2), new Monsters());
        formMap.put(new Point(6, 3), new Monsters());
    }
}
```
ͬ������«�����͵ĳ�����ı����£�
```java
public final class ChangShe extends Formation<Brothers> {
    public ChangShe() {		
        super(FormationType.CS, 7, 1, 3, 0); // ������ͼ��ռ���еȹ���
        // ��«�޵�λ��
        formMap.put(new Point(0, 0), new Brothers(0));
        formMap.put(new Point(1, 0), new Brothers(1));
        formMap.put(new Point(2, 0), new Brothers(2));
        formMap.put(new Point(3, 0), new Brothers(3));
        formMap.put(new Point(4, 0), new Brothers(4));
        formMap.put(new Point(5, 0), new Brothers(5));
        formMap.put(new Point(6, 0), new Brothers(6));
    }
}
```

### ������CharWindow
���ȸı���Ƕ����ʵ�������̣�
```java
    public Formation<Brothers> broForm; // ��«�����Ͷ���
    public Formation<Monsters> monForm; // �������Ͷ���
```
�������򷵻�ֵ�ϴ��� Formation ���ͣ�ͨ�����Ĳ����� broForm �� monForm���ķ�������Ҫ��ӷ��ͣ�����ֻѡȡ��һ�����д����Եķ���������ȫ������
```java
    /** ������������Ϊ�������ͽ���ʵ�������������������ͣ��޳����� */
    private Formation<Monsters> setForm(FormationType type) {
        switch (type) {
        case HY: return new HeYi(); 
        case YX: return new YanXing(); 
        case CE: return new ChongE();
        case YL: return new YuLin();
        case FY: return new FangYuan();
        case YY: return new YanYue();
        case FS: return new FengShi();
        default: return new HeYi(); 
        }
    }
```
������һ����Ϊ���ӵģ�
```java
    /** �ж�λ�� p1Cen ������ form1 ��λ�� p2Cen ������ form2 �Ƿ���ͻ */
    private <T1 extends Creature, T2 extends Creature> boolean inCollision(Formation<T1> form1, Point p1Cen, Formation<T2> form2, Point p2Cen) {
        if (p1Cen == null || p2Cen == null) return true;
        if (form1 == null || form2 == null) return false; // ��Ϊ��һ������ͻ
        for (Point p1 : form1.formMap.keySet()) {
            for (Point p2 : form2.formMap.keySet()) { // ��������λ����p1Cen, p2CenΪ����
                if (p1.mov(p1Cen).mov(form1.getFormCen().reverse()).equals(p2.mov(p2Cen).mov(form2.getFormCen().reverse()))) 
                   return true;
            }
        }
        return false;
    }
```
���� inCollision() ������Ҫ���� T1��T2 ��ԭ���ǣ����ô˷���ʱ�������������ͳ�����ͬ����һ�����ã�
```java
    ... inCollision(monForm, pMonCen, broform, pBroCen) ...
```
�����зֱ��� Formation\<Brothers> �� Formation\<Monsters>����Ҫ��ͬ�ġ�T����  
�������͵ķ������У�
```java
    /** �����·������� form �ƶ����� d */
    private <T extends Creature> void movFormation(Formation<T> form, Point pCen, Point d, GroupType type) {...}
```
ʣ�µķ��ͷ����ڴ˱㲻�����ˡ�

### ������GUIWindow
��Ϊ CharWindow �ı���GUIWindow Ҳ�ܵ�����΢�Ĳ�����
```java
    /** �ڴ��ڵ� r �� c �з������� form */
    private <T extends Creature> void setFormation(int r, int c, Formation<T> form) {...}
    /** �Ӵ��ڵ� r �� c ���Ƴ����� form */
    private <T extends Creature> void removeFormation(int r, int c, Formation<T> form) {...}
```
## ����
��ͬ�� World v2.0 �� World v3.0 �ķ�Ծ��World v3.1 ����� v3.0 ������������˷��ͣ�����ʹ��������Ͻ������ಿ�ּ���û�иı䡣


#   
# World v3.0 ��
- ��һ�� **GUI** �汾   
  - �˵��������Խ��У����á��ı���������  
  - ��ʼ̬Ϊ�Զ�ģʽ���Բ˵��������������������ֶ�ģʽ
  - ��Ӽ��̼��������Կ���������Ӫ���ƶ�
- �ı��˿�������Ļ��ƣ�  
  - ˦���ƹ� **God**�����������������������硱����  
  - ������ **CharWorld**������Ļ�����  
  - ������ **GUIWorld**������������Ĳ���ʵ�� GUI��  
- ʹ�� Collection �� HashMap �������������д  
  - Formation �е�ÿ����ʹ�� **HashMap<Point, Creature>** ���д洢  
- ���·�װ�� util ���߰�  
  - enum ������Ϊ3�������롰��Ӫ Group �����  
  - Point ��ĺ�������� @Override �� equals() �� hashCode()  
- �����˱��������д�������Ҫ�ĵ㶨λ�Ͳ���Ҫ�Ķ���
- �Կ������͸ı���ƶ��ĺ������е����Լ����ظ�����  
  - �жϺ����Եĺ���������д  
  - �Բ�ͬ��Ӫ�������ƶ��ຯ���ϲ�Ϊһ��
- ������ **javadoc** ��ʽ��д��ע��

## ��ͼ
<img src="img_readme/3.0-class.png" width=100%>

- world    
  - God.java
  - CharWindow.java  
  - GUIWindow.java  
  - creatures  
    - Creature.java
    - Brothers.java  
    - Monsters.java  
    - Scorpion.java  
    - Mascot.java  
    - Elder.java  
    - Snake.java
  - formations  
    - Formation.java
    - HeYi.java
    - YanXing.java
    - ChongE.java
    - ChangShe.java
    - YuLin.java
    - FangYuan.java
    - YanYue.java
    - FengShi.java
  - util
    - Point.java
    - GroupType.java
    - CreatureType.java
    - FormationType.java
    - Range.java
    - WorldSleep.java

## ��ϸ˵��
### ��һ��package world
#### 1. God
����һ��������������ܵģ������������
```java
    public static void main(String[] args) {
        CharWindow cWin = new CharWindow();
        new GUIWindow(cWin);
    }
```
#### 2. CharWorld
����ı��ʡ�  
##### ��1������  
�������Ĵ���Ӫ����«�ޡ����֡���үү���߾����Ķ����λ��  
```java
    public Formation broForm;
    public Formation monForm;
    public Snake snake = new Snake();
    public Elder elder = new Elder();
    private Point pBroCen; 
    private Point pMonCen; 
    private Point pEld;
```
�����Ͳ���Ҫ��ʽ�ظ�����ά�����ŵ�ͼ��Ҳ����˵��ֻ������Ҫ��ӡ��ʱ�����ö����λ�������ɿ���̨�ĵ�ͼ��������� CharWorld.showWorld()���� GUI �ĵ�ͼ��������� GUIWorld.showAtLast()��  
##### ��2������  
��Ҫ�� public ����������  
```java
    /** ���� class Range �����ֵ��ʼ�����ж�����λ��*/
    public void initAll();
    /** ����Ӫ type �����·��ƶ����� d */
    public void movGroup(GroupType type, Point d);
    /** �ı��������͵�������type */
    public void changeFormation(FormationType type);
```
������ private ������Щ�����ж�һ���ƶ������ͱ任�Ƿ���У����磬�Ƿ񳬳���ͼ�߽��Լ��Ƿ���������Ӫ��λ�ò�����ͻ������Щ�ܸ����������ഴ����Ӧ�����Ͷ���ȵȡ�  
  
#### 3. GUIWorld extends JFrame
������ı����ϣ�����һ��ÿ�����Ƥ��  
##### ��1������  
�����˲˵����Լ��˵��  
�����˲˵�/�����¼�������  
�����˷�ͼƬ�õı�ǩ ����  
```java
    ...
    /** GUI �����ԭ�� ���� char ���� */
    private CharWindow cWin;
    /** �˵��¼����� */
    private MenuMonitor menuMonitor; // MenuMonitor ���ڲ���
    /** �����¼����� */
    private KeyMonitor keyMonitor; // KeyMonitor ���ڲ���
    /** ���屳��ͼƬ���ڱ�ǩ */
    private JLabel ground;
    /** ����һȺ��ǩ�Է�������ͼƬ */
    private JLabel[][] cell;
    ...
```
##### ��2������  
���캯�������������Զ�ģʽ **autoWorld()**�������ڸոյ����ͻ�����Զ�ģʽ��  
��Ҫ�ķ����У�
```java
    /** ���GUI���� */
    public void removeAtFirst();
    /** ����char��������ӻ���GUI���� */
    public void showAtLast();
    /** �Զ�ģʽ */
    public void autoWorld();
```
�������Ȼ������ƶ��Ȳ���Ȼ�������»���������ˢ�´��ڵķ�ʽ��  
##### ��3���ڲ���
```java
    /** �����˵��� */
    class MenuMonitor implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == menuItReset)
                handleReset();
            else if (e.getSource() == menuItHY)
                handleMon(FormationType.HY);
            else if (e.getSource() == menuItYX)
                handleMon(FormationType.YX);
            else if (e.getSource() == menuItCE)
                handleMon(FormationType.CE);
            else if (e.getSource() == menuItYL)
                handleMon(FormationType.YL);
            else if (e.getSource() == menuItFY)
                handleMon(FormationType.FY);
            else if (e.getSource() == menuItYY)
                handleMon(FormationType.YY);
            else if (e.getSource() == menuItFS)
                handleMon(FormationType.FS);
        }
    }
    /** �˵�������ʼ��->�����á� */
    public void handleReset() {
        isAuto = false;     // ȡ���Զ�ģʽ
        removeAtFirst();    // ���
        cWin.initAll();     // ����
        cWin.showWorld();   // �ػ�����̨
        showAtLast();       // �ػ� GUI
    }
    /** �˵�������ʼ��->�����͡� */
    public void handleMon(FormationType type) {
        isAuto = false;             // ȡ���Զ�ģʽ
        nextForm = type;            // ���� sleep �������ӳ�����
        removeAtFirst();            // ���
        cWin.changeFormation(type); // ������
        cWin.showWorld();           // �ػ�����̨
        showAtLast();               // �ػ� GUI
    }
```
```java
    /** ���������� */
    class KeyMonitor implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyPressed(KeyEvent e) {}
        @Override
        public void keyReleased(KeyEvent e) {
            if (isAuto) return; // �Զ�ģʽ��֧��ʹ�ü���
            removeAtFirst();
            switch (e.getKeyCode()) {
            // �ƶ���«�ޣ�WDSA
            case KeyEvent.VK_W: cWin.movGroup(GroupType.Bro, new Point(-1, 0)); break;
            case KeyEvent.VK_D: cWin.movGroup(GroupType.Bro, new Point(0, 1)); break;
            case KeyEvent.VK_S: cWin.movGroup(GroupType.Bro, new Point(1, 0)); break;
            case KeyEvent.VK_A: cWin.movGroup(GroupType.Bro, new Point(0, -1)); break;
            // �ƶ����֣�UP/RIGHT/DOWN/LEFT
            case KeyEvent.VK_UP: cWin.movGroup(GroupType.Mon, new Point(-1, 0)); break;
            case KeyEvent.VK_RIGHT: cWin.movGroup(GroupType.Mon, new Point(0, 1)); break;
            case KeyEvent.VK_DOWN: cWin.movGroup(GroupType.Mon, new Point(1, 0)); break;
            case KeyEvent.VK_LEFT: cWin.movGroup(GroupType.Mon, new Point(0, -1)); break;
            // �ƶ���үү��THGF
            case KeyEvent.VK_T: cWin.movGroup(GroupType.Eld, new Point(-1, 0)); break;
            case KeyEvent.VK_H: cWin.movGroup(GroupType.Eld, new Point(0, 1)); break;
            case KeyEvent.VK_G: cWin.movGroup(GroupType.Eld, new Point(1, 0)); break;
            case KeyEvent.VK_F: cWin.movGroup(GroupType.Eld, new Point(0, -1)); break;
            // �ƶ��߾���ILKJ
            case KeyEvent.VK_I: cWin.movGroup(GroupType.Snk, new Point(-1, 0)); break;
            case KeyEvent.VK_L: cWin.movGroup(GroupType.Snk, new Point(0, 1)); break;
            case KeyEvent.VK_K: cWin.movGroup(GroupType.Snk, new Point(1, 0)); break;
            case KeyEvent.VK_J: cWin.movGroup(GroupType.Snk, new Point(0, -1)); break;
            }
            cWin.showWorld();
            showAtLast();
        }
    }
```

### ������package world.creatures  
#### Creature
�������������Ŀ������ CharWindow �Լ� GUIWindow�кϲ��������Բ�ͬ�������ͽ��в����Ķ�����Ƶĺ����ϲ�Ϊһ�������� CreatureTyp e������ͬ��ִ�в�ͬ�Ĳ�����
```java
    /** �������� */
    protected CreatureType type;
```

### ������package world.formations
#### Formation
��һ�汾��ά��������ͼ Creature[][] creatureMap �����˷ѿռ䣨û������ĵط�Ҳ�п�ָ�룩����̭��ȡ����֮���� HashMap��������¼����������ġ���λ��->�������͡���Ϣ��  
```java
    public Map<Point, Creature> formMap;
    protected Formation(...) {
        ...
        formMap = new HashMap<Point, Creature>();
    }
```
��������࣬�������͵Ĺ��캯�������˸ı䡣�Գ�����Ϊ����
```java
    public ChongE() {		
        super(FormationType.CE, 6, 2, 3, 0); // ������ͼ��ռ���й���		
        // ���ֵ�λ��
        formMap.put(new Point(1, 0), new Monsters());
        formMap.put(new Point(3, 0), new Scorpion()); // Ы�Ӿ�
        formMap.put(new Point(5, 0), new Monsters());
        formMap.put(new Point(0, 1), new Monsters());
        formMap.put(new Point(2, 1), new Monsters());
        formMap.put(new Point(4, 1), new Monsters());
    }
```

### ���ģ�package world.util
#### 1. Point
���ʹ�� Map<Point, Creature> formMap ������ formMap.containsKey(point) ��ԶΪ�ٵ������ԭ������Ϊ�ڱȽ����� Point ����ļ�ֵʱ������ Object �� equals() �����������±Ƚϵ��ǵ�ַ����ֵ��������д�� equals() ������  
```java
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Point) {
            Point p = (Point) obj;
            return ((this.pRow == p.pRow) && (this.pCol == p.pCol));
        }
        return false;
    }
```
������ʱ���� formMap.containsKey(point) ��Ȼ��ԶΪ�٣�ԭ������ʹ�õ��� Hash ��Object �����˶���ĵ�ַ���� HashCode����������д�ĳ����������е�ֵ���� HashCode��  
```java
    @Override
    public int hashCode() {
        return Objects.hash(pRow, pCol);
    }
```  
#### 2. Range
�����������ͼ�ϵ���ೣ����������������������Լ��Ĵ���Ӫ�ĳ�ʼλ�õȡ�

#### 3. WorldSleep
�����˷��� worldSleep(int time)������ GUIWorld ���Զ�ģʽ��ʱ������

#### 4. GroupType
�Ĵ���Ӫ��  
�����Ӫ�����ַ�ʽ�ǡ�������Ч�ĵ�Ԫ���������ƶ���үүʱ����«�ޡ����ֺ��߾��������ƶ���������үү�������������γ�һ������Ӫ�����߸���«��һ���ƶ���������ͬһ������Ӫ����
```java
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
```
#### 5. CreatureType & FormationType
CreatureType���������ϸ��𻮷֣�ֻҪ���ڲ�ͬ��Ϊ��ͬ�����ࡣ  
������Bro1, Bro2, Bro3, Bro4, Bro5, Bro6, Bro7, Scorp, Mons, Snk, Eld.  
FormationType��  
������HY, YX, CE, CS, YL, FY, YY, FS.  

## ��ʾ
<img src="img_readme/3.0-0.gif" width=100%>


#   
# World v2.0 ��
- ���һ������̨�汾  
- ��1.0���ʹ��package����������
- ��1.0��ȶ������������·�װ
- �ı��˿�������Ļ��ƣ�  
  - �����ͼ WorldMap  
  -  �� Mud ��Ϸ Observer  

## ����˵��
- ABCDEFG����«���ϴ�����  
- Y����үү��������  
- &��Ы�Ӿ�  
- o��Сආ�  
- S���߾���������  

## ��ͼ
<img src="img_readme/2.0-class.jpg" width=100%>

## Point
�����ͣ�����Ϊ�к��У��ܹ����м򵥵���ʾ���С��ж��Ƿ���ȡ��ƶ��Ȳ�����  

## Type
enum ���ͣ�Ϊ�����͵����֣�  
HY("������"), YX("������"), CE("������"), CS("������"), YL("������"),
FY("������"), YY("������"), FS("��ʸ��");  

## Creature
�����У����� String name����ӡ���� char symbol  
������Ϊ��ʽ�Ĳ�ͬ�����̳�Ϊ3�ࣺ  
- ��«�ޣ�Brothers  
  - �ڲ��� enum Members���涨��7����«�޵����֣����У���ɫ  
- ���֣�Monsters  
  - �ӡ�Сආ����м̳г������족Ы�Ӿ���Scorpion
- �����ӣ�Mascot
  - ������үү�� Elder �롱�߾��� Snake �̳�  

## Formation
�����԰�����  
- �������� Types type  
- �������� int formRangeRow  
- �������� int formRangeCol  
- ����ͼ Creature[][] creatureMap ��û���˵�λ��Ϊ null��
- ���͵����ĵ� Point formCenter ��������λ��  

�䷽��������  
- Point center() // �õ�������������  
- Types getType() // �õ���������  
- int getRowNum() // �õ���������  
- int getColNum() // �õ���������  
- boolean isEmpty(int r, int c) // ĳ���Ƿ�����  
- char[][] getFormMap() // �õ����ʹ�ӡЧ��  

������ǰ�������:  
�������� HeYi�������� YanXing�������� ChongE�������� ChangShe�������� YuLin�������� FangYuan�������� YanYue����ʸ�� FengShi��  
���ǵĲ�֮ͬ�����ڹ��캯����

## WorldMap
������ Formation �̶������統�С�  
WorldMap �ڶ����˵�ͼ�������� rangeRow, rangeCol ֮�󣬽��������������͵Ķ����Լ��������ڵ�ͼ��Ӧ���ڵ�λ�ã�Point ���ͣ�ָʾ���������ĵ����꣩��  
�ƶ����͵Ĳ���ֻ��Ҫ�ı�����͵����ĵ㶨λ��  
�ı��������͵Ĳ���ֻ��Ҫ�ı��������������õĶ�������������Ϊ��������ԭ���� monForm = yxForm ��Ϊ monForm = ylForm������ monForm �� Formation ���ͣ�yxForm/ylForm �� YanXing/YuLin ���ͣ���  
����ӡ�����ͼ���� showWorld() �Ĺ��̣����ǰѸ���������ʽ���ս����������ͼ�ϣ�������ǣ�  
- ����һ������Ϊ rangeRow, rangeCol �ĵ�ͼ char[][] map  
- ��ʼ����ʹ map ��ÿ��Ԫ�ض�Ϊ ' '���ո�  
- ͨ�� Formation ����Ϊ char[][] getFormMap()���õ���«�޺��������;ֲ�����ʾͼ  
- ͨ����«�����ͺ��������͵����ĵ㶨λ�����������ֲ�Сͼ�����ݸ��ǵ������ͼ map ��  
- ͨ����������үү���߾��Ķ�λ�㣬����үү���߾��ķ��Ÿ��ǵ������ͼ map ��  
- ��ӡ�����ͼ

## Observer
main �������ڵ��ࡣ
�γ�һ��������mud��Ϸ��Ч�������Թ�ʹ��������ѡ���Կ��� WorldMap ����Ϊ��

## ��ʾ
<img src="img_readme/2.0-0.png" width=20%>
<img src="img_readme/2.0-1.png" width=39%>
<img src="img_readme/2.0-2.png" width=20%>
<img src="img_readme/2.0-3.png" width=20%>
<img src="img_readme/2.0-4.png" width=38%>
<img src="img_readme/2.0-5.png" width=20%>
<img src="img_readme/2.0-6.png" width=20%>
