package AD_Project;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class board{
    GamePlayer p;
    board(GamePlayer p){
        this.p = p;
    }
    int [][] board = new int[4][4];
    int x =3, y=3;
    int rest,cnt, cnt2;
    String square_up = "��������������";
    String square_bottom = "��������������";
    String square_height_m = "��" + "  M  "+ "��" ;
    String square_height_c = "��" + "  C  "+ "��" ;
    String square_height_last = "��" + "  ?  "+ "��" ;
    String square_height_Player1 = "��" + "  ��  "+ "��" ;
    String square_height_Player2 = "��" + "  ��  "+ "��" ;
    String square_height_2Player = "��" + " �ܡ�  "+ "��" ;
    String square_height = "��" + "     "+ "��" ;

    void getboard() {
        for(int i=0; i<4; i++) {
            System.out.print(square_up);
        }
        System.out.println("");
        // ���ľ��ϴ� �κ�
        System.out.print(square_height_m);
        System.out.print(square_height_c);
        System.out.print(square_height);
        System.out.print(square_height_m);
        System.out.println("");
        for(int i=0; i<4; i++) {
            System.out.print(square_bottom);
        }

        System.out.print("\n" + square_up);
        for(int i =0; i<2; i++) {
            System.out.print("       ");
        }
        System.out.print(square_up);
        System.out.print("\n"+square_height);
        for(int i =0; i<2; i++) {
            System.out.print("       ");
        }
        System.out.print(square_height_c);
        System.out.print("\n"+ square_bottom);
        for(int i =0; i<2; i++) {
            System.out.print("       ");
        }
        System.out.print(square_bottom);
        System.out.print("\n" + square_up);
        for(int i =0; i<2; i++) {
            System.out.print("       ");
        }
        System.out.print(square_up);
        System.out.print("\n"+square_height_c);
        for(int i =0; i<2; i++) {
            System.out.print("       ");
        }
        System.out.print(square_height_c);
        System.out.print("\n"+ square_bottom);
        for(int i =0; i<2; i++) {
            System.out.print("       ");
        }
        System.out.print(square_bottom +"\n");
        for(int i=0; i<4; i++) {
            System.out.print(square_up);
        }
        System.out.println("");
        System.out.print(square_height_m);
        System.out.print(square_height);
        System.out.print(square_height_c);
        System.out.print(square_height_last);
        System.out.println("");
        for(int i=0; i<4; i++) {
            System.out.print(square_bottom);
        }
    }
    void Game(int a) {
        if(cnt == 0) {
            y -= a;
            if(y<0) {
                rest = 0-y;
                y = 0;
                x -= rest;
                cnt++;
                System.out.println("("+x+")"+"("+y+")");
            }
        }else if(cnt ==1) {
            x -= a;
            if(x<0) {
                rest = 0-x;
                x=0;
                y+= rest;
                cnt++;
                System.out.println("("+x+")"+"("+y+")");
            }
        } else if(cnt ==2) {
            y += a;
            if(y>3) {
                rest = y-3;
                y = 3;
                x += rest;
                cnt++;
                System.out.println("("+x+")"+"("+y+")");
                if(x>=3) {
                    System.out.println("��");
                }
            }
        } else {
            x+=a;
            if(x>=3) {
                System.out.println("��");
            }
        }
        cnt2++;
        if(cnt2%2==0) {
            board[p.playerPosition[cnt2%2][0]][p.playerPosition[cnt2%2][1]] -=1 ;
            board[x][y] +=1;
        } else {
            board[p.playerPosition[cnt2%2][0]][p.playerPosition[cnt2%2][1]] -=2 ;
            board[x][y] +=2;
        }
        p.playerPosition[cnt2%2][0] = x;
        p.playerPosition[cnt2%2][1] = y;
    }
}
class GamePlayer{
    String name; // �÷��̾� �̸�
    int dice, Score = 0;
    boolean finish = false; // �÷��̾ ���������� �����ߴ��� Ȯ���ϴ� ����
    boolean isWin; // �÷��̾ �¸��ߴ��� Ȯ���ϴ� ����
    int dicePosition; // �ֻ����� ���� ��ġ�� �����ϴ� ����
    boolean bonus = false; // ���ʽ��� �޾Ҵ��� Ȯ���ϴ� ����
    int [][] playerPosition = new int[2][2];
}
public class MainGame {
    public static void main(String[] args) {

        GamePlayer player1 = new GamePlayer();
        GamePlayer player2 = new GamePlayer();
        Scanner scanner = new Scanner(System.in);


        System.out.println("Player 1�� �̸��� �Է����ּ���: ");
        player1.name = scanner.nextLine();

        System.out.println("Player 2�� �̸��� �Է����ּ���: ");
        player2.name = scanner.nextLine();

        System.out.println("������ �����մϴ�.");


        // ���� ����
        while(!player1.finish || !player2.finish){ // �� �� �� ���� ���������� ������ ������ �ݺ�
            System.out.println("�ֻ����� �����ϴ�.");
            dice();


            if(player1.isWin){ // �÷��̾� 1�� �¸����� ���
                System.out.println(player1.name + "���� �¸��ϼ̽��ϴ�.");
                player1.Score += winnerScoreUp(player1.isWin, player1.bonus);
                player2.Score += winnerScoreUp(player2.isWin, player2.bonus);
            }
            else if(player2.isWin){ // �÷��̾� 2�� �¸����� ���
                System.out.println(player2.name + "���� �¸��ϼ̽��ϴ�.");
                player2.Score += winnerScoreUp(player2.isWin, player2.bonus);
                player1.Score += winnerScoreUp(player1.isWin, player1.bonus);
            } else { // ���º��� ���
                player1.Score += winnerScoreUp(player1.isWin, player1.bonus);
                player2.Score += winnerScoreUp(player2.isWin, player2.bonus);
            }
        }

    }
    public static void dice() {
        int dice = (int) (Math.random() * 3) + 1;
        System.out.println("�ֻ����� �����ּ���.");
        System.out.println("�ֻ����� ���ڴ� " + dice + "�Դϴ�.");
        System.out.println(dice+"ĭ �̵��մϴ�.");
    }
    public static int winnerScoreUp(boolean win, boolean bonus) {
        if (win) { // �̴ϰ��� �¸�
            if (bonus) { // ���ʽ��� �޾��� ���.
                bonus = false;
                return 20;
            } else { // ���ʽ��� ���� ������ ���
                return 10;
            }
        } else{ // �̴ϰ��� �й�
            if (bonus) { // ���ʽ��� �޾��� ���
                bonus = false;
                return 10;
            } else { // ���ʽ��� ���� ������ ���
                return 5;
            }
        }
    }
    public void scoreChange(String p1name, String p2name, int p1score, int p2score) {
        System.out.println(p1name+"��(��)"+p2name+"�� ������ ����Ǿ����ϴ�.");
        System.out.println(p1name+"�� ����: "+p2score);
        System.out.println(p2name+"�� ����: "+p1score);

    }
    public void positionChange(String p1name, String p2name) {
        System.out.println(p1name+"��(��)"+p2name+"�� ��ġ�� ����Ǿ����ϴ�.");
    }

    public void bonusScore(boolean bonus) {
        System.out.println("�̴ϰ��� ���� 2�� Ư���� ȹ���ϼ̽��ϴ�.");
        System.out.println("���� �����ϴ� 1���� �̴ϰ��� ������ 2��� �����մϴ�.");
        bonus = true;
    }
    public void miniGame(){
        System.out.println("�̴ϰ����� �����մϴ�.");
        System.out.println("�̴ϰ����� �������� ����˴ϴ�.");



    }
    public void backward(String playername) {
        System.out.println("1ĭ �ڷ� �̵��մϴ�.");
        System.out.println(playername+"�� ��ġ�� ����Ǿ����ϴ�.");

    }
}