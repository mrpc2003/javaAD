package AD_Project;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.TimeUnit;

class MainGameBoard{
    GamePlayer p ;
    static boolean start = false;
    static int [][] board = new int[4][4];
    int x =3, y=3;
    static int cnt ;
    int rest,cnt2=0, dice;
    String square_up = "��������������";
    String square_bottom = "��������������";
    String square_height_m = "��" + "  M  "+ "��" ;
    String square_height_c = "��" + "  C  "+ "��" ;
    String square_height_last = "��" + "  ?  "+ "��" ;
    String square_height_Player1 = "��" + "  ��  "+ "��" ;
    String square_height_Player2 = "��" + "  ��  "+ "��" ;
    String square_height_2Player = "��" + " �ܡ�  "+ "��" ;
    String square_height = "��" + "     "+ "��" ;
    MainGameBoard(GamePlayer p){
        this.p = p;
    }

    void getboard() {
//        p = new GamePlayer();

        for(int i=0; i<4; i++) {
            System.out.print(square_up);
        }
        System.out.println();
        // ���ľ��ϴ� �κ�

        System.out.print(board[0][0]==1 ? square_height_Player1 : board[0][0]==2? square_height_Player2 :board[0][0]==3? square_height_2Player :square_height_m);
        System.out.print(board[0][1]==1 ? square_height_Player1 : board[0][1]==2? square_height_Player2 :board[0][1]==3? square_height_2Player :square_height_c);
        System.out.print(board[0][2]==1 ? square_height_Player1 : board[0][2]==2? square_height_Player2 :board[0][2]==3? square_height_2Player :square_height);
        System.out.println(board[0][3]==1 ? square_height_Player1 : board[0][3]==2? square_height_Player2 :board[0][3]==3? square_height_2Player :square_height_m);

        for(int i=0; i<4; i++) {
            System.out.print(square_bottom);
        }

        System.out.print("\n" + square_up);
        for(int i =0; i<2; i++) {
            System.out.print("       ");
        }
        System.out.println(square_up);
        System.out.print(board[1][0]==1 ? square_height_Player1 : board[1][0]==2? square_height_Player2 :board[1][0]==3? square_height_2Player :square_height);
        for(int i =0; i<2; i++) {
            System.out.print("       ");
        }

        System.out.println(board[1][3]==1 ? square_height_Player1 : board[1][3]==2? square_height_Player2 :board[1][3]==3? square_height_2Player :square_height_c);
        System.out.print(square_bottom);
        for(int i =0; i<2; i++) {
            System.out.print("       ");
        }
        System.out.println(square_bottom);
        System.out.print(square_up);
        for(int i =0; i<2; i++) {
            System.out.print("       ");
        }
        System.out.println(square_up);
        System.out.print(board[2][0]==1 ? square_height_Player1 : board[2][0]==2? square_height_Player2 :board[2][0]==3? square_height_2Player :square_height_c);
        for(int i =0; i<2; i++) {
            System.out.print("       ");
        }
        System.out.println(board[2][3]==1 ? square_height_Player1 : board[2][3]==2? square_height_Player2 :board[2][3]==3? square_height_2Player :square_height_c);
        System.out.print(square_bottom);
        for(int i =0; i<2; i++) {
            System.out.print("       ");
        }
        System.out.println(square_bottom);
        for(int i=0; i<4; i++) {
            System.out.print(square_up);
        }
        System.out.println("");
        System.out.print(board[3][0]==1 ? square_height_Player1 : (board[3][0]==2? square_height_Player2 :(board[3][0]==3? square_height_2Player :square_height_m)));
        System.out.print(board[3][1]==1 ? square_height_Player1 : (board[3][1]==2? square_height_Player2 :(board[3][1]==3? square_height_2Player :square_height)));
        System.out.print(board[3][2]==1 ? square_height_Player1 : (board[3][2]==2? square_height_Player2 :(board[3][2]==3? square_height_2Player :square_height_c)));
        System.out.println(board[3][3]==1 ? square_height_Player1 : (board[3][3]==2? square_height_Player2 :(board[3][3]==3? square_height_2Player :square_height_last)));

        for(int i=0; i<4; i++) {
            System.out.print(square_bottom);
        }
        System.out.println();
    }
    void Game(int dice) {

        if(cnt ==0){
            board[3][3] =3;

        } else{
            x = p.playerPosition[0];
            y= p.playerPosition[1];
        }
        if (cnt2 == 0) {
            y -= dice;
            if (y < 0) {
                rest = -y;
                y = 0;
                x -= rest;
                cnt2++;
            }
        } else if (cnt2 == 1) {
            x -= dice;
            if (x < 0) {
                rest = -x;
                x = 0;
                y += rest;
                cnt2++;
            }
        } else if (cnt2 == 2) {
            y += dice;
            if (y > 3) {
                rest = y - 3;
                y = 3;
                x += rest;
                cnt2++;
                if (x >= 3) {
                    System.out.println("��");
                }
            }
        } else {
            x += dice;
            if (x >= 3) {
                x =3;
                System.out.println("��");
            }
        }
        cnt++;
        if (cnt % 2 == 0) {
            board[p.playerPosition[0]][p.playerPosition[1]] -= 1;
            board[x][y] += 1;
        } else {
            board[p.playerPosition[0]][p.playerPosition[1]] -= 2;
            board[x][y] += 2;
        }

        if(board[3][0] !=0 || board[0][0] != 0 || board[0][3] !=0 ) {
            start = true;
        }

        p.playerPosition[1] = y;
        p.playerPosition[0] = x;
    }
}
class GamePlayer{
    String name; // �÷��̾� �̸�
    int dice, Score = 0; // �ֻ��� ��, ����
    boolean isFinish = false; // �÷��̾ ���������� �����ߴ��� Ȯ���ϴ� ����
    boolean isWin; // �÷��̾ �¸��ߴ��� Ȯ���ϴ� ����
    int dicePosition; // �ֻ����� ���� ��ġ�� �����ϴ� ����
    boolean isBonus = false; // ���ʽ��� �޾Ҵ��� Ȯ���ϴ� ����
    boolean isTurn = false; // �÷��̾��� �������� Ȯ���ϴ� ����
    boolean isMiniGame = false; // �̴ϰ����� �ߴ��� Ȯ���ϴ� ����
    boolean isChance = false; // �÷��̾ ��ȸī�带 �̾Ҵ��� Ȯ���ϴ� ����

    int [] playerPosition = {3,3}; // �÷��̾��� ��ġ�� �����ϴ� ����

}

public class MainGame {
    public static void main(String[] args) throws IOException, InterruptedException {

        GamePlayer player1 = new GamePlayer(); // �÷��̾�1 ��ü ����
        GamePlayer player2 = new GamePlayer(); // �÷��̾�2 ��ü ����
        MainGameBoard b; // ������ ��ü ����
        Scanner scanner = new Scanner(System.in);
        int MiniGameCount = -1;

        System.out.print("Player 1�� �̸��� �Է����ּ���: ");
        player1.name = scanner.nextLine();

        System.out.print("Player 2�� �̸��� �Է����ּ���: ");
        player2.name = scanner.nextLine();
        System.out.println("������ �����մϴ�.");
        Thread.sleep(1000); //1�� ���

        System.out.println(player1.name+"��(��) �۷� ǥ�õ˴ϴ�.");
        time();
        System.out.println(player2.name+"��(��) �ܷ� ǥ�õ˴ϴ�.");
        time();
        System.out.println("�������� ����մϴ�.");
        MainGameBoard b1 = new MainGameBoard(player1);
        MainGameBoard b2 = new MainGameBoard(player2);
        // ���� ����
        while(!player1.isFinish || !player2.isFinish){ // �� �� �� ���� ���������� ������ ������ �ݺ�
            System.out.println(player1.name+"�� ��!");
            player1.dice = dice();
            b1.Game(player1.dice);
            b1.getboard();

            System.out.println(player2.name+"�� ��!");
            player2.dice = dice();
            b2.Game(player2.dice);
            b2.getboard();
            
//            if ()   // �޼ҵ�� ����
            if (player1.isMiniGame||player2.isMiniGame) {
                miniGame(MiniGameCount++, player1, player2);
            } else if(isChance){
                chance(MiniGameCount++, player1, player2);
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
    public static int dice() throws InterruptedException {
        int dice = (int) (Math.random() * 3) + 1;
        System.out.println("�ֻ����� �����ϴ�.");
        for (int i = 3; i > 0; i--) { // 3�� ī��Ʈ �ٿ�
            time(); // 1�� ���� �޼ҵ�
            System.out.print("."); // . ���
        }
        time(); // 1�� ���� �޼ҵ�
        System.out.println("�ֻ����� ���ڴ� " + dice + "�Դϴ�.");
        time();
        System.out.println(dice+"ĭ �̵��մϴ�.");
        return dice;
    }
    public static void winnerScoreUp(GamePlayer player) {
        if (player.isWin) { // �̴ϰ��� �¸�
            if (player.isBonus) { // ���ʽ��� �޾��� ���.
                player.isBonus = false;
                player.Score += 20;
            } else { // ���ʽ��� ���� ������ ���
                player.Score += 10;
            }
        } else{ // �̴ϰ��� �й�
            if (player.isBonus) { // ���ʽ��� �޾��� ���
                player.isBonus = false;
                player.Score += 10;
            } else { // ���ʽ��� ���� ������ ���
                player.Score += 5;
            }
        }
    }

    public static void chance(int MiniGameCount,GamePlayer p1, GamePlayer p2) throws IOException, InterruptedException {
        boolean isScoreChange = false, isPositionChange = false, isBonus = false, isMiniGame = false, isBackward = false;
        if (p1.isBonus){
            System.out.println("���ʽ� ī�带 �̾ҽ��ϴ�.");
            time();
            if (p1.isTurn){
                bonusScore(p1.isBonus);
            } else if (p2.isTurn){
                bonusScore(p2.isBonus);
            }

        } if (isMiniGame){
            miniGame(MiniGameCount, p1, p2);
        } if (isScoreChange){
            scoreChange(p1, p2);
        } if (isPositionChange){
            positionChange(p1, p2);
        } if (isBackward) {
            if (p1.isTurn){
                backward(p1);
            } else if (p2.isTurn){
                backward(p2);
            }
        }
    }
    public static void scoreChange(GamePlayer myself, GamePlayer counterpart) throws InterruptedException {
        System.out.println(myself.name+"��(��)"+counterpart.name+"�� ������ ����Ǿ����ϴ�.");
        time();
        //5���� �������κ��� ������/��(������), ���� 5������ ���ٸ�, ������ �ִ� ���� ���θ� ��

        if (counterpart.Score < 5) {
            myself.Score += counterpart.Score;
            counterpart.Score = 0;
        } else {
            myself.Score += 5;
            counterpart.Score -= 5;
        }
        System.out.println(myself.name+"�� ����: "+myself.Score);
        System.out.println(counterpart.name+"�� ����: "+counterpart.Score);

    }
    public static void positionChange(GamePlayer myself, GamePlayer counterpart) {
        System.out.println(myself.name+"��(��)"+counterpart.name+"�� ��ġ�� ����Ǿ����ϴ�.");
    }
    public static void playerTurn(GamePlayer myself) throws InterruptedException {
        System.out.println(myself.name+"�� �����Դϴ�.");
        time();
        System.out.println("�ֻ����� �����ϴ�.");
        dice();
    }

    public void calculateMiniGameScore(GamePlayer player1, GamePlayer player2) {
        if(player1.isWin){ // �÷��̾� 1�� �¸����� ���
            System.out.println(player1.name + "���� �¸��ϼ̽��ϴ�.");
            winnerScoreUp(player1);
            winnerScoreUp(player2);
        }
        else if(player2.isWin){ // �÷��̾� 2�� �¸����� ���
            System.out.println(player2.name + "���� �¸��ϼ̽��ϴ�.");
            winnerScoreUp(player1);
            winnerScoreUp(player2);
        } else { // ���º��� ���
            winnerScoreUp(player1);
            winnerScoreUp(player2);
        }
    }

    public static void bonusScore(GamePlayer player) {
        System.out.println("�̴ϰ��� ���� 2�� Ư���� ȹ���ϼ̽��ϴ�.");
        System.out.println("���� �����ϴ� 1���� �̴ϰ��� ������ 2��� �����մϴ�.");
        player.isBonus = true;
    }
    public static void miniGame(int MiniGameCount, GamePlayer p1, GamePlayer p2) throws IOException, InterruptedException {

        System.out.println("�̴ϰ����� �����մϴ�.");
        for (int i = 3; i > 0; i--) { // 3�� ī��Ʈ �ٿ�
            time(); // 1�� ���� �޼ҵ�
            System.out.print("."); // . ���
        }


        // 4���� �̴ϰ��� �� �������� ����
        if(MiniGameCount == 0){
            System.out.println("�̴ϰ����� �� 4���� �ֽ��ϴ�.");
            System.out.println();
            System.out.println("1. Yacht Dice");
            time();
            System.out.println("2. ��Ʈ ����");
            time();
            System.out.println("3. ����");
            time();
            System.out.println("4. �������");
        }

        time(); // 1�� ���� �޼ҵ�
        System.out.println("�̴ϰ����� �������� ����˴ϴ�.");
        time();

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
            System.out.println("Yacht Dice�� �����մϴ�.");
            time();
            YachtGame_Main.start(p1.name, p2.name);
            if (p1player.p1isWin) {
                System.out.println(p1.name + "���� �¸��ϼ̽��ϴ�.");
                p1.isWin = true;
            } else if (p2player.p2isWin) {
                System.out.println(p2.name + "���� �¸��ϼ̽��ϴ�.");
                p2.isWin = true;
            } else {
                System.out.println("���º��Դϴ�.");
            }
        }
        else if(miniGame[MiniGameCount]==2){
            System.out.println("��Ʈ ������ �����մϴ�.");
            time();
            oriented.start(p1.name, p2.name);
        }
        else if(miniGame[MiniGameCount]==3){
            System.out.println("������ �����մϴ�.");
            time();
            blackjack_withclass.Bstart(p1.name, p2.name);
        }
        else if(miniGame[MiniGameCount]==4){
            System.out.println("���� ������ �����մϴ�.");
            time();
            BM.start(p1.name, p2.name);
            if (BM.p1isWin) {
                System.out.println(p1.name + "���� �¸��ϼ̽��ϴ�.");
                p1.isWin = true;

            } else if (BM.p2isWin) {
                System.out.println(p2.name + "���� �¸��ϼ̽��ϴ�.");
                p2.isWin = true;
            } else {
                System.out.println("���º��Դϴ�.");
            }
        }
    }
    public static void backward(GamePlayer myself) throws InterruptedException {
        System.out.println("1ĭ �ڷ� �̵��մϴ�.");
        System.out.println(myself.name+"�� ��ġ�� ����Ǿ����ϴ�.");
        time();
    }
    public static void time() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }
}