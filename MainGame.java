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
    String square_up = "┏━━━━━┓";
    String square_bottom = "┗━━━━━┛";
    String square_height_m = "┃" + "  M  "+ "┃" ;
    String square_height_c = "┃" + "  C  "+ "┃" ;
    String square_height_last = "┃" + "  ?  "+ "┃" ;
    String square_height_Player1 = "┃" + "  ●  "+ "┃" ;
    String square_height_Player2 = "┃" + "  ○  "+ "┃" ;
    String square_height_2Player = "┃" + " ●○  "+ "┃" ;
    String square_height = "┃" + "     "+ "┃" ;
    MainGameBoard(GamePlayer p){
        this.p = p;
    }

    void getboard() {
//        p = new GamePlayer();

        for(int i=0; i<4; i++) {
            System.out.print(square_up);
        }
        System.out.println();
        // 고쳐야하는 부분

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
                    System.out.println("끝");
                }
            }
        } else {
            x += dice;
            if (x >= 3) {
                x =3;
                System.out.println("끝");
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
    String name; // 플레이어 이름
    int dice, Score = 0; // 주사위 값, 점수
    boolean isFinish = false; // 플레이어가 도착지점에 도착했는지 확인하는 변수
    boolean isWin; // 플레이어가 승리했는지 확인하는 변수
    int dicePosition; // 주사위를 던진 위치를 저장하는 변수
    boolean isBonus = false; // 보너스를 받았는지 확인하는 변수
    boolean isTurn = false; // 플레이어의 차례인지 확인하는 변수
    boolean isMiniGame = false; // 미니게임을 했는지 확인하는 변수
    boolean isChance = false; // 플레이어가 기회카드를 뽑았는지 확인하는 변수

    int [] playerPosition = {3,3}; // 플레이어의 위치를 저장하는 변수

}

public class MainGame {
    public static void main(String[] args) throws IOException, InterruptedException {

        GamePlayer player1 = new GamePlayer(); // 플레이어1 객체 생성
        GamePlayer player2 = new GamePlayer(); // 플레이어2 객체 생성
        MainGameBoard b; // 게임판 객체 생성
        Scanner scanner = new Scanner(System.in);
        int MiniGameCount = -1;

        System.out.print("Player 1의 이름을 입력해주세요: ");
        player1.name = scanner.nextLine();

        System.out.print("Player 2의 이름을 입력해주세요: ");
        player2.name = scanner.nextLine();
        System.out.println("게임을 시작합니다.");
        Thread.sleep(1000); //1초 대기

        System.out.println(player1.name+"은(는) ○로 표시됩니다.");
        time();
        System.out.println(player2.name+"은(는) ●로 표시됩니다.");
        time();
        System.out.println("게임판을 출력합니다.");
        MainGameBoard b1 = new MainGameBoard(player1);
        MainGameBoard b2 = new MainGameBoard(player2);
        // 게임 시작
        while(!player1.isFinish || !player2.isFinish){ // 둘 중 한 명이 도착지점에 도달할 때까지 반복
            System.out.println(player1.name+"의 턴!");
            player1.dice = dice();
            b1.Game(player1.dice);
            b1.getboard();

            System.out.println(player2.name+"의 턴!");
            player2.dice = dice();
            b2.Game(player2.dice);
            b2.getboard();
            
//            if ()   // 메소드로 묶기
            if (player1.isMiniGame||player2.isMiniGame) {
                miniGame(MiniGameCount++, player1, player2);
            } else if(isChance){
                chance(MiniGameCount++, player1, player2);
            }  else {
                //랜덤으로 3~5점 점수 획득
                int randomscore = (int) (Math.random() * 3) + 3;
                System.out.println("점수를 획득했습니다.");
                if (player1.isTurn) {
                    System.out.println("획득한 점수: "+randomscore);
                    player1.Score += randomscore;
                    System.out.println(player1.name + "의 점수: " + player1.Score);
                    player1.isTurn = false;
                    player2.isTurn = true;
                } else if (player2.isTurn) {
                    System.out.println("획득한 점수: "+randomscore);
                    player2.Score += randomscore;
                    System.out.println(player2.name + "의 점수: " + player2.Score);
                    player1.isTurn = true;
                    player2.isTurn = false;
                }
            }
        }



        if (player1.Score > player2.Score) {
            player1.isWin = true;
            System.out.println(player1.name + "의 승리!");
        } else if (player1.Score < player2.Score) {
            player2.isWin = true;
            System.out.println(player2.name + "의 승리!");
        } else {
            System.out.println("무승부!");
        }
    }
    public static int dice() throws InterruptedException {
        int dice = (int) (Math.random() * 3) + 1;
        System.out.println("주사위를 굴립니다.");
        for (int i = 3; i > 0; i--) { // 3초 카운트 다운
            time(); // 1초 쉬는 메소드
            System.out.print("."); // . 출력
        }
        time(); // 1초 쉬는 메소드
        System.out.println("주사위의 숫자는 " + dice + "입니다.");
        time();
        System.out.println(dice+"칸 이동합니다.");
        return dice;
    }
    public static void winnerScoreUp(GamePlayer player) {
        if (player.isWin) { // 미니게임 승리
            if (player.isBonus) { // 보너스를 받았을 경우.
                player.isBonus = false;
                player.Score += 20;
            } else { // 보너스를 받지 못했을 경우
                player.Score += 10;
            }
        } else{ // 미니게임 패배
            if (player.isBonus) { // 보너스를 받았을 경우
                player.isBonus = false;
                player.Score += 10;
            } else { // 보너스를 받지 못했을 경우
                player.Score += 5;
            }
        }
    }

    public static void chance(int MiniGameCount,GamePlayer p1, GamePlayer p2) throws IOException, InterruptedException {
        boolean isScoreChange = false, isPositionChange = false, isBonus = false, isMiniGame = false, isBackward = false;
        if (p1.isBonus){
            System.out.println("보너스 카드를 뽑았습니다.");
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
        System.out.println(myself.name+"와(과)"+counterpart.name+"의 점수가 변경되었습니다.");
        time();
        //5점을 상대방으로부터 가져옴/줌(무작위), 만약 5점보다 적다면, 가지고 있는 점수 전부를 줌

        if (counterpart.Score < 5) {
            myself.Score += counterpart.Score;
            counterpart.Score = 0;
        } else {
            myself.Score += 5;
            counterpart.Score -= 5;
        }
        System.out.println(myself.name+"의 점수: "+myself.Score);
        System.out.println(counterpart.name+"의 점수: "+counterpart.Score);

    }
    public static void positionChange(GamePlayer myself, GamePlayer counterpart) {
        System.out.println(myself.name+"와(과)"+counterpart.name+"의 위치가 변경되었습니다.");
    }
    public static void playerTurn(GamePlayer myself) throws InterruptedException {
        System.out.println(myself.name+"의 차례입니다.");
        time();
        System.out.println("주사위를 굴립니다.");
        dice();
    }

    public void calculateMiniGameScore(GamePlayer player1, GamePlayer player2) {
        if(player1.isWin){ // 플레이어 1이 승리했을 경우
            System.out.println(player1.name + "님이 승리하셨습니다.");
            winnerScoreUp(player1);
            winnerScoreUp(player2);
        }
        else if(player2.isWin){ // 플레이어 2가 승리했을 경우
            System.out.println(player2.name + "님이 승리하셨습니다.");
            winnerScoreUp(player1);
            winnerScoreUp(player2);
        } else { // 무승부일 경우
            winnerScoreUp(player1);
            winnerScoreUp(player2);
        }
    }

    public static void bonusScore(GamePlayer player) {
        System.out.println("미니게임 점수 2배 특전을 획득하셨습니다.");
        System.out.println("이후 도착하는 1개의 미니게임 점수가 2배로 증가합니다.");
        player.isBonus = true;
    }
    public static void miniGame(int MiniGameCount, GamePlayer p1, GamePlayer p2) throws IOException, InterruptedException {

        System.out.println("미니게임을 시작합니다.");
        for (int i = 3; i > 0; i--) { // 3초 카운트 다운
            time(); // 1초 쉬는 메소드
            System.out.print("."); // . 출력
        }


        // 4개의 미니게임 중 랜덤으로 선택
        if(MiniGameCount == 0){
            System.out.println("미니게임은 총 4개가 있습니다.");
            System.out.println();
            System.out.println("1. Yacht Dice");
            time();
            System.out.println("2. 고스트 게임");
            time();
            System.out.println("3. 블랙잭");
            time();
            System.out.println("4. 빙고게임");
        }

        time(); // 1초 쉬는 메소드
        System.out.println("미니게임은 랜덤으로 실행됩니다.");
        time();

        //4개의 원소가 들어가는 배열 생성
        int[] miniGame = new int[4];
        //배열에 1~4까지의 숫자를 랜덤으로 넣기
        for(int i=0; i<miniGame.length; i++) {
            miniGame[i] = (int)(Math.random()*4)+1;
            for(int j=0; j<i; j++) {
                if(miniGame[i]==miniGame[j]) {
                    i--;
                    break;
                }
            }
        }
        //배열의 순서대로 각 숫자에 맞는 미니게임 실행
        if(miniGame[MiniGameCount]==1) {
            System.out.println("Yacht Dice를 시작합니다.");
            time();
            YachtGame_Main.start(p1.name, p2.name);
            if (p1player.p1isWin) {
                System.out.println(p1.name + "님이 승리하셨습니다.");
                p1.isWin = true;
            } else if (p2player.p2isWin) {
                System.out.println(p2.name + "님이 승리하셨습니다.");
                p2.isWin = true;
            } else {
                System.out.println("무승부입니다.");
            }
        }
        else if(miniGame[MiniGameCount]==2){
            System.out.println("고스트 게임을 시작합니다.");
            time();
            oriented.start(p1.name, p2.name);
        }
        else if(miniGame[MiniGameCount]==3){
            System.out.println("블랙잭을 시작합니다.");
            time();
            blackjack_withclass.Bstart(p1.name, p2.name);
        }
        else if(miniGame[MiniGameCount]==4){
            System.out.println("빙고 게임을 시작합니다.");
            time();
            BM.start(p1.name, p2.name);
            if (BM.p1isWin) {
                System.out.println(p1.name + "님이 승리하셨습니다.");
                p1.isWin = true;

            } else if (BM.p2isWin) {
                System.out.println(p2.name + "님이 승리하셨습니다.");
                p2.isWin = true;
            } else {
                System.out.println("무승부입니다.");
            }
        }
    }
    public static void backward(GamePlayer myself) throws InterruptedException {
        System.out.println("1칸 뒤로 이동합니다.");
        System.out.println(myself.name+"의 위치가 변경되었습니다.");
        time();
    }
    public static void time() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }
}