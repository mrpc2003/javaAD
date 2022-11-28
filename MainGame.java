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
    String square_up = "┏━━━━━┓";
    String square_bottom = "┗━━━━━┛";
    String square_height_m = "┃" + "  M  "+ "┃" ;
    String square_height_c = "┃" + "  C  "+ "┃" ;
    String square_height_last = "┃" + "  ?  "+ "┃" ;
    String square_height_Player1 = "┃" + "  ●  "+ "┃" ;
    String square_height_Player2 = "┃" + "  ○  "+ "┃" ;
    String square_height_2Player = "┃" + " ●○  "+ "┃" ;
    String square_height = "┃" + "     "+ "┃" ;

    void getboard() {
        for(int i=0; i<4; i++) {
            System.out.print(square_up);
        }
        System.out.println("");
        // 고쳐야하는 부분
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
                    System.out.println("끝");
                }
            }
        } else {
            x+=a;
            if(x>=3) {
                System.out.println("끝");
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
    String name; // 플레이어 이름
    int dice, Score = 0;
    boolean isFinish = false; // 플레이어가 도착지점에 도착했는지 확인하는 변수
    boolean isWin; // 플레이어가 승리했는지 확인하는 변수
    int dicePosition; // 주사위를 던진 위치를 저장하는 변수
    boolean isBonus = false; // 보너스를 받았는지 확인하는 변수
    boolean isTurn = false; // 플레이어의 차례인지 확인하는 변수
    boolean isMiniGame = false; // 미니게임을 했는지 확인하는 변수
    boolean isChance = false; // 플레이어가 기회카드를 뽑았는지 확인하는 변수

    int [][] playerPosition = new int[2][2];
}

public class MainGame {
    public static void main(String[] args) throws IOException, InterruptedException {

        GamePlayer player1 = new GamePlayer();
        GamePlayer player2 = new GamePlayer();
        GamePlayer myself = new GamePlayer();


        Scanner scanner = new Scanner(System.in);
        int MiniGameCount = -1;

        System.out.println("Player 1의 이름을 입력해주세요: ");
        player1.name = scanner.nextLine();

        System.out.println("Player 2의 이름을 입력해주세요: ");
        player2.name = scanner.nextLine();

        System.out.println("게임을 시작합니다.");


        // 게임 시작
        while(!player1.isFinish || !player2.isFinish){ // 둘 중 한 명이 도착지점에 도달할 때까지 반복

            System.out.println("게임판을 출력합니다.");
            if (isMiniGame) {
                miniGame(MiniGameCount++);
            } else if(isChance){
                chance(MiniGameCount++, player1.Score, player2.Score, player1.name, player2.name, player1.isTurn, player2.isTurn,  player1.isBonus,  player2.isBonus);

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
    public static void dice() {
        int dice = (int) (Math.random() * 3) + 1;
        System.out.println("주사위를 굴려주세요.");
        System.out.println("주사위의 숫자는 " + dice + "입니다.");
        System.out.println(dice+"칸 이동합니다.");
    }
    public static int winnerScoreUp(boolean win, boolean bonus) {
        if (win) { // 미니게임 승리
            if (bonus) { // 보너스를 받았을 경우.
                bonus = false;
                return 20;
            } else { // 보너스를 받지 못했을 경우
                return 10;
            }
        } else{ // 미니게임 패배
            if (bonus) { // 보너스를 받았을 경우
                bonus = false;
                return 10;
            } else { // 보너스를 받지 못했을 경우
                return 5;
            }
        }
    }

    public static void chance(int MiniGameCount,int p1Score, int p2Score, String p1name, String p2name, boolean p1turn, boolean p2turn, boolean p1isBonus, boolean p2isBonus) throws IOException, InterruptedException {
        boolean isScoreChange = false, isPositionChange = false, isBonus = false, isMiniGame = false, isBackward = false;
        if (isBonus){
            System.out.println("보너스 카드를 뽑았습니다.");
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
        System.out.println(myself+"와(과)"+counterpart+"의 점수가 변경되었습니다.");
        //5점을 상대방으로부터 가져옴/줌(무작위), 만약 5점보다 적다면, 가지고 있는 점수 전부를 줌

        if (counterpartscore < 5) {
            myselfscore += counterpartscore;
            counterpartscore = 0;
        } else {
            myselfscore += 5;
            counterpartscore -= 5;
        }
        System.out.println(myself+"의 점수: "+myselfscore);
        System.out.println(counterpart+"의 점수: "+counterpartscore);

    }
    public static void positionChange(String myself, String counterpart) {
        System.out.println(myself+"와(과)"+counterpart+"의 위치가 변경되었습니다.");
    }
    public static void playerTurn(String myself) {
        System.out.println(myself+"의 차례입니다.");
        System.out.println("주사위를 굴립니다.");
        dice();
    }

    public void calculateMiniGameScore(GamePlayer player1, GamePlayer player2) {
        if(player1.isWin){ // 플레이어 1이 승리했을 경우
            System.out.println(player1.name + "님이 승리하셨습니다.");
            player1.Score += winnerScoreUp(player1.isWin, player1.isBonus);
            player2.Score += winnerScoreUp(player2.isWin, player2.isBonus);
        }
        else if(player2.isWin){ // 플레이어 2가 승리했을 경우
            System.out.println(player2.name + "님이 승리하셨습니다.");
            player2.Score += winnerScoreUp(player2.isWin, player2.isBonus);
            player1.Score += winnerScoreUp(player1.isWin, player1.isBonus);
        } else { // 무승부일 경우
            player1.Score += winnerScoreUp(player1.isWin, player1.isBonus);
            player2.Score += winnerScoreUp(player2.isWin, player2.isBonus);
        }
    }

    public static void bonusScore(boolean bonus) {
        System.out.println("미니게임 점수 2배 특전을 획득하셨습니다.");
        System.out.println("이후 도착하는 1개의 미니게임 점수가 2배로 증가합니다.");
        bonus = true;
    }
    public static void miniGame(int MiniGameCount) throws IOException, InterruptedException {

        System.out.println("미니게임을 시작합니다.");
        System.out.println("미니게임은 랜덤으로 실행됩니다.");

        // 4개의 미니게임 중 랜덤으로 선택
        if(MiniGameCount == 0){
            System.out.println("미니게임은 총 4개가 있습니다.");
            System.out.println();
            System.out.println("1. Yacht Dice");
            System.out.println("2. 고스트 게임");
            System.out.println("3. 블랙잭");
            System.out.println("4. 빙고게임");
        }

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
            System.out.println("Yacht Dice 를 시작합니다.");
            YachtGame_Main.start();
        }
        else if(miniGame[MiniGameCount]==2){
            System.out.println("고스트 게임을 시작합니다.");
        }
        else if(miniGame[MiniGameCount]==3){
            System.out.println("블랙잭을 시작합니다.");
        }
        else if(miniGame[MiniGameCount]==4){
            System.out.println("빙고게임을 시작합니다.");
        }
    }
    public static void backward(String myself) {

        System.out.println("1칸 뒤로 이동합니다.");
        System.out.println(myself+"의 위치가 변경되었습니다.");

    }



}