class BattleField {

    String[][] battleground=new String[Battle.N][Battle.N];
    Snake snake=new Snake();
    Monsters[] monsters=new Monsters[20];
    HuluBrothers[] huluwa=new HuluBrothers[7];
    Grandpa grandpa=new Grandpa();
    Line line=new Line();
    public BattleField(){
        huluwa[0]=new HuluBrothers("*�ϴ�*");
        huluwa[1]=new HuluBrothers("*�϶�*");
        huluwa[2]=new HuluBrothers("*����*");
        huluwa[3]=new HuluBrothers("*����*");
        huluwa[4]=new HuluBrothers("*����*");
        huluwa[5]=new HuluBrothers("*����*");
        huluwa[6]=new HuluBrothers("*����*");
        monsters[0]=new Monsters("Ы�Ӿ�");
        for(int i=1;i<20;i++) {
            monsters[i]=new Monsters("Сආ�");
        }
    }
    public void setupField(int num){
        line.Changshe(huluwa,battleground);
        snake.changeLine(line,monsters,battleground,num);
        grandpa.movetoline(battleground,(Battle.N)/2,0);
        snake.movetoline(battleground,(Battle.N)/2,(Battle.N-1));
    }
    public void cleanField(){
        int i,j;
        for (i=0;i<battleground.length;i++){
            for (j=0;j<battleground[i].length;j++){
                battleground[i][j]=null;
            }
        }
    }
    public void printField() {
        int i,j;
        for (i=0;i<battleground.length;i++){
            for (j=0;j<battleground[i].length;j++){
                if(battleground[i][j]!=null){
                    System.out.print(battleground[i][j]);
                }
                else System.out.print("******");
            }
            System.out.println("");
         }
    }
}