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
    String square_height_last = "��" + "  <  "+ "��" ;
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
        if(board[3][0] !=0 || board[0][0] != 0 || board[0][3] !=0 ) {
            if(p.isMiniGame){
                return;
            }
            p.isMiniGame = true;
        } else if(board[0][1] != 0 || board[1][3] !=0 || board[2][0] !=0|| board[2][3] !=0|| board[3][2] !=0){
            if(p.isChance){
                return;
            }
            p.isChance = true;
        }
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
                    p.isFinish = true;

                }
            }
        } else {
            x += dice;
            if (x >= 3) {
                x =3;
                System.out.println("��");
                p.isFinish = true;

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

        p.playerPosition[1] = y;
        p.playerPosition[0] = x;
    }
}
class GamePlayer{
    public boolean isScoreChange;
    public boolean isBackward;
    public boolean isPositionChange;
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
        MainGameBoard b1 = new MainGameBoard(player1);
        MainGameBoard b2 = new MainGameBoard(player2);

        MainGameBoard b; // ������ ��ü ����
        Scanner scanner = new Scanner(System.in);

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
        System.out.println("�̴ϰ����� �� 4���� �ֽ��ϴ�.");
        System.out.println();
        System.out.println("1. Yacht Dice");
        time();
        System.out.println("2. ��Ʈ ����");
        time();
        System.out.println("3. ����");
        time();
        System.out.println("4. �������");
        time();
        System.out.println("�������� ����մϴ�.");
        time();
        b1.getboard();


        // ���� ����
        while(!player1.isFinish || !player2.isFinish){ // �� �� �� ���� ���������� ������ ������ �ݺ�
            playerTurn(player1, player2,b1);
            playerTurn(player2, player1,b2);

        }

    }
    public static void playerTurn(GamePlayer myself, GamePlayer counterPart, MainGameBoard board) throws InterruptedException, IOException {
        System.out.println(myself.name+"�� �� �Դϴ�.");
        myself.isTurn = true;
        time();
        board.Game(dice());
        board.getboard();

        //�̺κ� �����ؾ���
        loop:
        if (myself.isChance) {
            chance(myself, counterPart, board);
            break loop;
        } else if (myself.isMiniGame) {
            miniGame(myself, counterPart);
            calculateMiniGameScore(myself, counterPart);
        } else if (myself.isFinish){
            if (myself.Score > counterPart.Score) {
                myself.isWin = true;
                System.out.println(myself.name + "�� �¸�!");
            } else if (myself.Score < counterPart.Score) {
                counterPart.isWin = true;
                System.out.println(counterPart.name + "�� �¸�!");
            } else {
                System.out.println("���º�!");
            }
        } else {
            int randomscore = (int) (Math.random() * 3) + 3;
            System.out.println("������ ȹ���߽��ϴ�.");

            System.out.println("ȹ���� ����: "+randomscore);
            myself.Score += randomscore;
            System.out.println(myself.name + "�� ����: " + myself.Score);
            System.out.println(counterPart.name + "�� ����: " + counterPart.Score);
            myself.isTurn = false;
            counterPart.isTurn = true;

        }
    }

    public static int dice() throws InterruptedException, IOException {
        int dice = (int) (Math.random() * 3) + 1;
        System.out.println("Enter�� ������ �ֻ����� ��������!");
        pause();
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
                System.out.println("Ư������ ���� "+player.name + "�� ������ 20�� �����մϴ�.");
                player.Score += 20;
            } else { // ���ʽ��� ���� ������ ���
                System.out.println(player.name + "�� ������ 10�� �����մϴ�.");
                player.Score += 10;
            }
        } else{ // �̴ϰ��� �й�
            if (player.isBonus) { // ���ʽ��� �޾��� ���
                player.isBonus = false;
                System.out.println("Ư������ ���� "+player.name + "�� ������ 10�� �����մϴ�.");
                player.Score += 10;
            } else { // ���ʽ��� ���� ������ ���
                System.out.println(player.name + "�� ������ 5�� �����մϴ�.");
                player.Score += 5;
            }
        }
    }
    public static void calculateMiniGameScore(GamePlayer player1, GamePlayer player2) {
        if(player1.isWin){ // �÷��̾� 1�� �¸����� ���
            System.out.println(player1.name + "���� �¸��ϼ̽��ϴ�.");
            winnerScoreUp(player1);
            winnerScoreUp(player2);
            player1.isMiniGame = false;
            player2.isMiniGame = false;
            player1.isChance = false;
            player2.isChance = false;
        }
        else if(player2.isWin){ // �÷��̾� 2�� �¸����� ���
            System.out.println(player2.name + "���� �¸��ϼ̽��ϴ�.");
            winnerScoreUp(player1);
            winnerScoreUp(player2);
            player1.isMiniGame = false;
            player2.isMiniGame = false;
            player1.isChance = false;
            player2.isChance = false;
        } else { // ���º��� ���
            System.out.println("���º��Դϴ�.");
            winnerScoreUp(player1);
            winnerScoreUp(player2);
            player1.isMiniGame = false;
            player2.isMiniGame = false;
            player1.isChance = false;
            player2.isChance = false;
        }
    }
    public static void miniGame(GamePlayer p1, GamePlayer p2) throws IOException, InterruptedException {

        System.out.println("�̴ϰ����� �����մϴ�.");
        for (int i = 3; i > 0; i--) { // 3�� ī��Ʈ �ٿ�
            time(); // 1�� ���� �޼ҵ�
            System.out.print("."); // . ���
        }
        time(); // 1�� ���� �޼ҵ�
        System.out.println("�̴ϰ����� �������� ����˴ϴ�.");
        time();

        // 1���� 4���� ���� ���� 1�� ����
        int random = (int) (Math.random() * 4) + 1;


        //�迭�� ������� �� ���ڿ� �´� �̴ϰ��� ����
        if(random == 1) {
            System.out.println("Yacht Dice�� �����մϴ�.");
            time();
            YachtGame_Main.start(p1.name, p2.name);
            if (p1player.p1isWin) {
                p1.isWin = true;
                time();
            } else if (p2player.p2isWin) {
                p2.isWin = true;
                time();
            } else {
                time();
            }
        }
        if(random==2){
            System.out.println("��Ʈ ������ �����մϴ�.");
            time();
            oriented.start(p1.name, p2.name);
            if (oriented.result) {
                p1.isWin = true;
                time();
            }  else {
                p2.isWin = true;
                time();
            }
        }
        if(random==3){
            System.out.println("������ �����մϴ�.");
            time();
            blackjack_withclass.Bstart(p1.name, p2.name);
            if (Game.p1win) {
                p1.isWin = true;
                time();

            } else if (Game.p2win) {
                p2.isWin = true;
                time();
            } else {
                time();
            }
        }
        if(random==4){
            System.out.println("���� ������ �����մϴ�.");
            time();
            BM.start(p1.name, p2.name);
            if (BM.p1isWin) {
                p1.isWin = true;
                time();

            } else if (BM.p2isWin) {
                p2.isWin = true;
                time();
            } else {
                time();
            }
        }

    }
    public static void chance(GamePlayer myself, GamePlayer counterPart, MainGameBoard board) throws IOException, InterruptedException {
        myself.isChance = false;

        //�������� ������ ����
        //�������� �Ҹ��� ���� true�� ����
        int random = (int) (Math.random() * 5) + 1;
        if(random == 1){
            myself.isScoreChange = true;
        }
        else if(random == 2){
            myself.isPositionChange = true;
        }
        else if(random == 3){
            myself.isBonus = true;
        }
        else if(random == 4){
            myself.isMiniGame = true;
        }
        else {
            myself.isBackward = true;
        }
        if (myself.isBonus) {
            System.out.println("���ʽ� ī�带 �̾ҽ��ϴ�.");
            time();
            bonusScore(myself);
        }
        if (myself.isMiniGame) {
            miniGame(myself, counterPart);
        }
        if (myself.isScoreChange) {
            scoreChange(myself, counterPart);
            myself.isMiniGame = false;
            myself.isChance = false;
        }
        if (myself.isPositionChange) {
            positionChange(myself, counterPart, board);
            myself.isChance = false;
            myself.isMiniGame = false;
        }
        if (myself.isBackward) {
            backward(myself, board);
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
    public static void positionChange(GamePlayer myself, GamePlayer counterpart, MainGameBoard board) {
        System.out.println(myself.name+"��(��)"+counterpart.name+"�� ��ġ�� ����Ǿ����ϴ�.");
        int x = myself.playerPosition[0];
        int y = myself.playerPosition[1];
        myself.playerPosition[0] = counterpart.playerPosition[0];
        myself.playerPosition[1] = counterpart.playerPosition[1];
        counterpart.playerPosition[0] = x;
        counterpart.playerPosition[1] = y;
        board.getboard();
    }
    public static void bonusScore(GamePlayer player) {
        System.out.println("�̴ϰ��� ���� 2�� Ư���� ȹ���ϼ̽��ϴ�.");
        System.out.println("���� �����ϴ� 1���� �̴ϰ��� ������ 2��� �����մϴ�.");
        player.isBonus = true;
    }
    public static void backward(GamePlayer myself, MainGameBoard board) throws InterruptedException {
        int a,b;
        System.out.println("1ĭ �ڷ� �̵��մϴ�.");
        System.out.println(myself.name+"�� ��ġ�� ����Ǿ����ϴ�.");

        time();
        MainGameBoard.cnt--;
        board.Game(-1);
        board.getboard();

    }
    public static void time() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }
    public static void pause() throws IOException
    {
        System.in.read();
    }
}