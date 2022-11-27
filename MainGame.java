package AD_Project;
import java.util.Scanner;

class GamePlayer{
    String name; // 플레이어 이름
    int dice, Score = 0;
    boolean finish = false; // 플레이어가 도착지점에 도착했는지 확인하는 변수
    boolean isWin; // 플레이어가 승리했는지 확인하는 변수
    int dicePosition; // 주사위를 던진 위치를 저장하는 변수
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
                player1.Score += winnerScoreUp(player1.isWin);
                player2.Score += winnerScoreUp(player2.isWin);
            }
            else if(player2.isWin){ // 플레이어 2가 승리했을 경우
                System.out.println(player2.name + "님이 승리하셨습니다.");
                player2.Score += winnerScoreUp(player2.isWin);
                player1.Score += winnerScoreUp(player1.isWin);
            }
        }


    }
    public static void dice() {
        int dice = (int) (Math.random() * 3) + 1;
        System.out.println("주사위를 굴려주세요.");
        System.out.println("주사위의 숫자는 " + dice + "입니다.");
        System.out.println(dice+"칸 이동합니다.");
    }
    public static int winnerScoreUp(boolean win) {
        if (win) { // 미니게임 승리
            return 10;
        } else{ // 미니게임 패배
            return 5;
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

    public void bonusScore(){
        System.out.println("미니게임 점수 2배 특전을 획득하셨습니다.");
        System.out.println("이후 도착하는 1개의 미니게임 점수가 2배로 증가합니다.");

    }
    public void miniGame(){
        System.out.println("미니게임을 시작합니다.");


    }
    public void backward(){
        System.out.println("1칸 뒤로 이동합니다.");
    }
}
