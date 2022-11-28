package AD_Project;
import java.io.BufferedReader;
import java.io.IOException;
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
    boolean isFinish = false; // �÷��̾ ���������� �����ߴ��� Ȯ���ϴ� ����
    boolean isWin; // �÷��̾ �¸��ߴ��� Ȯ���ϴ� ����
    int dicePosition; // �ֻ����� ���� ��ġ�� �����ϴ� ����
    boolean isBonus = false; // ���ʽ��� �޾Ҵ��� Ȯ���ϴ� ����
    boolean isTurn = false; // �÷��̾��� �������� Ȯ���ϴ� ����
    boolean isMiniGame = false; // �̴ϰ����� �ߴ��� Ȯ���ϴ� ����
    boolean isChance = false; // �÷��̾ ��ȸī�带 �̾Ҵ��� Ȯ���ϴ� ����

    int [][] playerPosition = new int[2][2];
}

public class MainGame {
    public static void main(String[] args) throws IOException, InterruptedException {

        GamePlayer player1 = new GamePlayer();
        GamePlayer player2 = new GamePlayer();
        GamePlayer myself = new GamePlayer();


        Scanner scanner = new Scanner(System.in);
        int MiniGameCount = -1;

        System.out.println("Player 1�� �̸��� �Է����ּ���: ");
        player1.name = scanner.nextLine();

        System.out.println("Player 2�� �̸��� �Է����ּ���: ");
        player2.name = scanner.nextLine();

        System.out.println("������ �����մϴ�.");


        // ���� ����
        while(!player1.isFinish || !player2.isFinish){ // �� �� �� ���� ���������� ������ ������ �ݺ�

            System.out.println("�������� ����մϴ�.");
            if (isMiniGame) {
                miniGame(MiniGameCount++);
            } else if(isChance){
                chance(MiniGameCount++, player1.Score, player2.Score, player1.name, player2.name, player1.isTurn, player2.isTurn,  player1.isBonus,  player2.isBonus);

            }  else {
                //�������� 3~5�� ���� ȹ��
                int randomscore = (int) (Math.random() * 3) + 3;
                System.out.println("������ ȹ���߽��ϴ�.");
                if (player1.isTurn) {
                    System.out.println("ȹ���� ����: "+randomscore);
                    player1.Score += randomscore;
                    System.out.println(player1.name + "�� ����: " + player1.Score);
                    player1.isTurn = false;
                    player2.isTurn = true;
                } else if (player2.isTurn) {
                    System.out.println("ȹ���� ����: "+randomscore);
                    player2.Score += randomscore;
                    System.out.println(player2.name + "�� ����: " + player2.Score);
                    player1.isTurn = true;
                    player2.isTurn = false;
                }
            }
        }
        if (player1.Score > player2.Score) {
            player1.isWin = true;
            System.out.println(player1.name + "�� �¸�!");
        } else if (player1.Score < player2.Score) {
            player2.isWin = true;
            System.out.println(player2.name + "�� �¸�!");
        } else {
            System.out.println("���º�!");
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

    public static void chance(int MiniGameCount,int p1Score, int p2Score, String p1name, String p2name, boolean p1turn, boolean p2turn, boolean p1isBonus, boolean p2isBonus) throws IOException, InterruptedException {
        boolean isScoreChange = false, isPositionChange = false, isBonus = false, isMiniGame = false, isBackward = false;
        if (isBonus){
            System.out.println("���ʽ� ī�带 �̾ҽ��ϴ�.");
            if (p1turn){
                bonusScore(p1isBonus);
            } else if (p2turn){
                bonusScore(p2isBonus);
            }

        } if (isMiniGame){
            miniGame(MiniGameCount);
        } if (isScoreChange){
            scoreChange(p1name, p2name, p1Score, p2Score);
        } if (isPositionChange){
            positionChange(p1name, p2name);
        } if (isBackward) {
            if (p1turn){
                backward(p1name);
            } else if (p2turn){
                backward(p2name);
            }
        }
    }
    public static void scoreChange(String myself, String counterpart, int myselfscore, int counterpartscore) {
        System.out.println(myself+"��(��)"+counterpart+"�� ������ ����Ǿ����ϴ�.");
        //5���� �������κ��� ������/��(������), ���� 5������ ���ٸ�, ������ �ִ� ���� ���θ� ��

        if (counterpartscore < 5) {
            myselfscore += counterpartscore;
            counterpartscore = 0;
        } else {
            myselfscore += 5;
            counterpartscore -= 5;
        }
        System.out.println(myself+"�� ����: "+myselfscore);
        System.out.println(counterpart+"�� ����: "+counterpartscore);

    }
    public static void positionChange(String myself, String counterpart) {
        System.out.println(myself+"��(��)"+counterpart+"�� ��ġ�� ����Ǿ����ϴ�.");
    }
    public static void playerTurn(String myself) {
        System.out.println(myself+"�� �����Դϴ�.");
        System.out.println("�ֻ����� �����ϴ�.");
        dice();
    }

    public void calculateMiniGameScore(GamePlayer player1, GamePlayer player2) {
        if(player1.isWin){ // �÷��̾� 1�� �¸����� ���
            System.out.println(player1.name + "���� �¸��ϼ̽��ϴ�.");
            player1.Score += winnerScoreUp(player1.isWin, player1.isBonus);
            player2.Score += winnerScoreUp(player2.isWin, player2.isBonus);
        }
        else if(player2.isWin){ // �÷��̾� 2�� �¸����� ���
            System.out.println(player2.name + "���� �¸��ϼ̽��ϴ�.");
            player2.Score += winnerScoreUp(player2.isWin, player2.isBonus);
            player1.Score += winnerScoreUp(player1.isWin, player1.isBonus);
        } else { // ���º��� ���
            player1.Score += winnerScoreUp(player1.isWin, player1.isBonus);
            player2.Score += winnerScoreUp(player2.isWin, player2.isBonus);
        }
    }

    public static void bonusScore(boolean bonus) {
        System.out.println("�̴ϰ��� ���� 2�� Ư���� ȹ���ϼ̽��ϴ�.");
        System.out.println("���� �����ϴ� 1���� �̴ϰ��� ������ 2��� �����մϴ�.");
        bonus = true;
    }
    public static void miniGame(int MiniGameCount) throws IOException, InterruptedException {

        System.out.println("�̴ϰ����� �����մϴ�.");
        System.out.println("�̴ϰ����� �������� ����˴ϴ�.");

        // 4���� �̴ϰ��� �� �������� ����
        if(MiniGameCount == 0){
            System.out.println("�̴ϰ����� �� 4���� �ֽ��ϴ�.");
            System.out.println();
            System.out.println("1. Yacht Dice");
            System.out.println("2. ��Ʈ ����");
            System.out.println("3. ����");
            System.out.println("4. �������");
        }

        //4���� ���Ұ� ���� �迭 ����
        int[] miniGame = new int[4];
        //�迭�� 1~4������ ���ڸ� �������� �ֱ�
        for(int i=0; i<miniGame.length; i++) {
            miniGame[i] = (int)(Math.random()*4)+1;
            for(int j=0; j<i; j++) {
                if(miniGame[i]==miniGame[j]) {
                    i--;
                    break;
                }
            }
        }
        //�迭�� ������� �� ���ڿ� �´� �̴ϰ��� ����

        if(miniGame[MiniGameCount]==1) {
            System.out.println("Yacht Dice �� �����մϴ�.");
            YachtGame_Main.start();
        }
        else if(miniGame[MiniGameCount]==2){
            System.out.println("��Ʈ ������ �����մϴ�.");
        }
        else if(miniGame[MiniGameCount]==3){
            System.out.println("������ �����մϴ�.");
        }
        else if(miniGame[MiniGameCount]==4){
            System.out.println("��������� �����մϴ�.");
        }
    }
    public static void backward(String myself) {

        System.out.println("1ĭ �ڷ� �̵��մϴ�.");
        System.out.println(myself+"�� ��ġ�� ����Ǿ����ϴ�.");

    }



}