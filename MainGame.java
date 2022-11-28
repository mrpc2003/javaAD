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
    boolean finish = false; // 플레이어가 도착지점에 도착했는지 확인하는 변수
    boolean isWin; // 플레이어가 승리했는지 확인하는 변수
    int dicePosition; // 주사위를 던진 위치를 저장하는 변수
    boolean bonus = false; // 보너스를 받았는지 확인하는 변수
    int [][] playerPosition = new int[2][2];
}
public class MainGame {
    public static void main(String[] args) {

        GamePlayer player1 = new GamePlayer();
        GamePlayer player2 = new GamePlayer();
        Scanner scanner = new Scanner(System.in);


        System.out.println("Player 1의 이름을 입력해주세요: ");
        player1.name = scanner.nextLine();

        System.out.println("Player 2의 이름을 입력해주세요: ");
        player2.name = scanner.nextLine();

        System.out.println("게임을 시작합니다.");


        // 게임 시작
        while(!player1.finish || !player2.finish){ // 둘 중 한 명이 도착지점에 도달할 때까지 반복
            System.out.println("주사위를 굴립니다.");
            dice();


            if(player1.isWin){ // 플레이어 1이 승리했을 경우
                System.out.println(player1.name + "님이 승리하셨습니다.");
                player1.Score += winnerScoreUp(player1.isWin, player1.bonus);
                player2.Score += winnerScoreUp(player2.isWin, player2.bonus);
            }
            else if(player2.isWin){ // 플레이어 2가 승리했을 경우
                System.out.println(player2.name + "님이 승리하셨습니다.");
                player2.Score += winnerScoreUp(player2.isWin, player2.bonus);
                player1.Score += winnerScoreUp(player1.isWin, player1.bonus);
            } else { // 무승부일 경우
                player1.Score += winnerScoreUp(player1.isWin, player1.bonus);
                player2.Score += winnerScoreUp(player2.isWin, player2.bonus);
            }
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
    public void scoreChange(String p1name, String p2name, int p1score, int p2score) {
        System.out.println(p1name+"와(과)"+p2name+"의 점수가 변경되었습니다.");
        System.out.println(p1name+"의 점수: "+p2score);
        System.out.println(p2name+"의 점수: "+p1score);

    }
    public void positionChange(String p1name, String p2name) {
        System.out.println(p1name+"와(과)"+p2name+"의 위치가 변경되었습니다.");
    }

    public void bonusScore(boolean bonus) {
        System.out.println("미니게임 점수 2배 특전을 획득하셨습니다.");
        System.out.println("이후 도착하는 1개의 미니게임 점수가 2배로 증가합니다.");
        bonus = true;
    }
    public void miniGame(){
        System.out.println("미니게임을 시작합니다.");
        System.out.println("미니게임은 랜덤으로 실행됩니다.");



    }
    public void backward(String playername) {
        System.out.println("1칸 뒤로 이동합니다.");
        System.out.println(playername+"의 위치가 변경되었습니다.");

    }
}