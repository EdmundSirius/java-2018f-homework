import java.util.*;
class Monsters extends Creature{
    public Monsters(String c) {
        super(c);
    }
}
class Snake extends Creature {
    public Snake() {
        super("*�߾�*");
    }

    public void changeLine(Line line, Monsters[] monsters, String[][] ground, int num) {
        switch (num) {
            case 1: {
                System.out.println("����");
                line.Heyi(monsters, ground);
                break;
            }
            case 2: {
                System.out.println("����");
                line.Yanxing(monsters, ground);
                break;
            }
            case 3: {
                System.out.println("����");
                line.Chonge(monsters, ground);
                break;
            }
            case 4: {
                System.out.println("����");
                line.Yulin(monsters, ground);
                break;
            }
            case 5: {
                System.out.println("����");
                line.Fangyuan(monsters, ground);
                break;
            }
            case 6: {
                System.out.println("����");
                line.Yanyue(monsters, ground);
                break;
            }
            case 7: {
                System.out.println("��ʸ");
                line.Fengshi(monsters, ground);
                break;
            }
        }

    }
}


