package AD_Project;
import java.util.*;

//class GhostGame_mainGameConnect{
//    static String p1 = "PLAYER1";
//    static String p2 = "PLAYER2";
//    public void setP1name(String p1name){
//        p1 = p1name;
//    }
//    public void setP2name(String p2name){
//        p2 = p2name;
//    }
//    public String getP1Name(){
//        return p1;
//    }
//    public String getP2name(){
//        return p2;
//    }
//}
class Entity{
    int x, y;
    Entity(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class GoodEntity extends Entity {
    GoodEntity(int x, int y) {
        super(x, y);
    }
}
class BadEntity extends Entity {
    BadEntity(int x, int y) {
        super(x, y);
    }
}
class GamePlay {
    Entity[][] entities;
    String ans;
    void explain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("게임의 설명을 들으시겠습니까? y/n");
        ans = sc.next();
        if(ans.equals("y")) {
            System.out.println("이 게임은 심리 게임입니다. 각 플레이어는 4마리의 착한 유령과 나쁜 유령 즉 총 8마리의 유령을 정합니다.");
            System.out.println("이 유령들은 6x6보드 판에 놓아져있습니다. 이 때 왼쪽 위에서부터 오른쪽으로 bad나 good을 입력해 순차적으로 유령의 속성을 정할 수 있습니다.");
            System.out.println("각 턴마다 하나의 유령을 선택해 움직일 수 있고 가고자 하는 곳에 상대방 유령이 있다면 그 유령을 잡을 수 있고, 잡힌 유령은 게임판에서 지워집니다.");
            System.out.println("게임의 승리 조건은 총 3가지 입니다. 첫 번째로 자신의 착한 유령이 상대쪽에 있는 화살표로 가게 되면 승리합니다.");
            System.out.println("두 번째로 자신의 나쁜 유령이 모두 잡힌다면 승리합니다.");
            System.out.println("상대방의 좋은 유령을 모두 잡는다면 승리합니다. \n\n\n");
            System.out.println();
            System.out.println("게임을 진행하시려면 y를 입력해주세요.");
            while(true) {
                String ans2 = sc.next();
                if(ans2.equals("y")) {
                    break;
                } else {
                    System.out.println("잘못 입력하셨습니다. 게임을 진행하시려면 y를 입력해주세요.");
                }
            }
        } else {
            System.out.println("y나 n으로 입력해주십시오.");
            explain();
        }
    }
    void GameSetting() {
        Scanner sc = new Scanner(System	.in);
//        mainGameConnect mg = new mainGameConnect();
        this.entities = new Entity[2][8];
        System.out.printf("%s가 레드팀, %s가 블루팀입니다!", mainGameConnect.getP1Name(), mainGameConnect.getP2Name());
        for(int i = 0;i < 2;i++) {
            System.out.printf("%d 번째 플레이어가 고스트를 정하실 차례입니다. \n", i+1);
            System.out.println("good이나 bad를 입력해 유령의 종류를 알려주세요.");
            System.out.println("다른 플레이어는 보고 있지 말아주세요.");
            int good = 0;
            int bad = 0;
            for(int j = 0; j < 8;) {
                String ans = sc.next();
                if(ans.equals("good")) {
                    this.entities[i][j] = new GoodEntity(j % 4 + 1, i == 0 ? j / 4 : 4 + j / 4);
                    good++;
                    j++;
                    if(good==5) {
                        System.out.println("착한 말의 갯수는 4개 입니다. 다시 입력해주세요");
                        j --;
                        good--;
                        continue;
                    }
                }else if(ans.equals("bad")) {
                    this.entities[i][j] = new BadEntity(j % 4 + 1, i == 0 ? j / 4 : 4 + j / 4);
                    bad++;
                    j++;
                    if(bad==5) {
                        System.out.println("나쁜 말의 갯수는 4개입니다. 다시 입력해주세요");
                        j--;
                        bad--;
                        continue;
                    }
                }else {
                    j++;
                    System.out.println("bad나 good으로만 입력하셔야합니다. 다시 입력해주세요.");
                    j--;
                }
            }
            for(int k=0; k<16; k++) {
                System.out.println("");
            }
        }
        System.out.println("게임 시작!");
    }
}
class Board{
    GamePlay game;
    public static final String RESET = "\u001B[0m";

    public static final String FONT_RED = "\u001B[31m";

    public static final String FONT_BLUE = "\u001B[34m";

    String Ghost = ":  o  ";
    String Ghost_blue = ":  "+FONT_BLUE+"o  "+RESET;
    String Ghost_red = ":  "+FONT_RED+"o  "+RESET;
    String noGhost = ":     ";
    String border = "-------------------------------------";
    Board(GamePlay game) {
        this.game = game;
    }
    void getBoard() {
        System.out.println(border);
        int [][] board = new int [7][7];
        for(int i = 0;i < 2;i++) {
            for(int j = 0; j < 8;j++) {
                if(i==0) {
                    board[game.entities[i][j].y][game.entities[i][j].x] = 1;

                } else{
                    board[game.entities[i][j].y][game.entities[i][j].x] = 2;
                }
            }
        }
        for(int i =0; i < 6; i++) {
            if(i== 0 || i==5)
                System.out.print(":  <- ");
            for(int j =0; j < 6; j++) {
                if(i==0 && j==0 || i==0 && j==5 || i==5&& j==5 || i==5 && j==0)
                    continue;
                if(board[i][j] == 1){
                    System.out.print(Ghost_blue);
                } else if(board[i][j] == 2){
                    System.out.print(Ghost_red);
                } else{
                    System.out.print(noGhost);
                }
            }
            if(i== 0 || i==5)
                System.out.print(":  -> ");
            System.out.print(": \n");
            System.out.println(border);
        }
    }
}
class Ghost_move {
    String move;
    GamePlay game;
    static int cnt;
    int blue_bad_cnt,red_bad_cnt,red_good_cnt, blue_good_cnt;

    Ghost_move(GamePlay game) {
        this.game = game;
    }

    void Check(int c) {
        for (int i = 0; i < 8; i++) {
            if (game.entities[cnt % 2][c - 1].x == game.entities[(cnt + 1) % 2][i].x && game.entities[cnt % 2][c - 1].y == game.entities[(cnt + 1) % 2][i].y) {
                if (game.entities[(cnt + 1) % 2][i] instanceof GoodEntity) {
                    if ((cnt+1) % 2 == 0) {
                        blue_good_cnt++;
                        System.out.println("착한 유령이었습니다!" + blue_good_cnt + "마리 잡았습니다.");
                    } else {
                        red_good_cnt++;
                        System.out.println("착한 유령이었습니다!" + red_good_cnt + "마리 잡았습니다.");
                    }
                } else {
                    if ((cnt+1) % 2 == 0) {
                        blue_bad_cnt++;
                        System.out.println("나쁜 유령이었습니다!" + blue_bad_cnt + "마리 잡았습니다.");
                    } else {
                        red_bad_cnt++;
                        System.out.println("나쁜 유령이었습니다!" + red_bad_cnt + "마리 잡았습니다.");
                    }
                }
                game.entities[(cnt + 1) % 2][i] = new GoodEntity(6, 6);
                break;
            } else if(game.entities[cnt % 2][c - 1].x == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c - 1].y == game.entities[cnt % 2][i].y && i != c-1) {
                System.out.println("다른 말이 있습니다. 다시 선택해주세요.");
            }
        }
    }

    void Move() {
        Scanner sc = new Scanner(System.in);
        cnt++;
        int c;
        boolean ans = false;
        System.out.print("어떤 유령을 조종할지 숫자를 입력하세요 : ");
        try{
            c = sc.nextInt();
            if(c<1 || c>8){
                System.out.println("없는 유령입니다. 다시 유령을 골라주세요.");
                cnt--;
                return;
            }
        } catch(InputMismatchException e) {
            System.out.println("잘못 입력하셨습니다. 숫자를 입력해주세요.");
            cnt--;
            return;
        }
        if (game.entities[cnt % 2][c - 1].x == 6) {
            System.out.println("이미 먹힌 말입니다. 다시 선택해주세요.");
            cnt--;
        } else {
            System.out.print("wasd로 유령이 움직일 방향을 입력해주세요 : ");
            move = sc.next();
            if (cnt % 2 == 0) {
                if (move.equals("w")) {
                    for(int i= 0; i<8; i++) {
                        if(game.entities[cnt % 2][c - 1].x == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c-1].y+1 == game.entities[cnt % 2][i].y && i != c-1) {
                            ans = true;
                            break;
                        }
                    }
                    if (game.entities[cnt % 2][c - 1].y + 1 == 6) {
                        System.out.println("갈 수 없는 곳입니다. 다시 입력해주세요.");
                        cnt--;
                    }else if(ans){
                        System.out.println("다른 말이 있습니다.");
                        cnt--;
                    }else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity) {
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x, game.entities[cnt % 2][c - 1].y + 1);
                        } else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x, game.entities[cnt % 2][c - 1].y + 1);
                        Check(c);
                    }
                } else if (move.equals("a")) {
                    for(int i= 0; i<8; i++) {
                        if(game.entities[cnt % 2][c - 1].x-1 == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c - 1].y == game.entities[cnt % 2][i].y && i != c-1) {
                            ans = true;
                            break;
                        }
                    }
                    if (game.entities[cnt % 2][c - 1].x -1 == -1) {
                        System.out.println("갈 수 없는 곳입니다. 다시 입력해주세요.");
                        cnt--;
                    }else if(ans){
                        System.out.println("다른 말이 있습니다.");
                        cnt--;
                    } else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity)
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x - 1, game.entities[cnt % 2][c - 1].y);
                        else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x - 1, game.entities[cnt % 2][c - 1].y);
                        Check(c);
                    }
                } else if (move.equals("s")) {
                    for(int i= 0; i<8; i++) {
                        if(game.entities[cnt % 2][c - 1].x == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c - 1].y-1 == game.entities[cnt % 2][i].y && i != c-1) {
                            ans = true;
                            break;
                        }
                    }
                    if (game.entities[cnt % 2][c - 1].y - 1 == -1) {
                        System.out.println("갈 수 없는 곳입니다. 다시 입력해주세요.");
                        cnt--;
                    } else if(ans) {
                        System.out.println("다른 말이 있습니다.");
                        cnt--;
                    }else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity)
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x, game.entities[cnt % 2][c - 1].y - 1);
                        else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x, game.entities[cnt % 2][c - 1].y - 1);
                        Check(c);
                    }
                } else if (move.equals("d")) {
                    for(int i= 0; i<8; i++) {
                        if(game.entities[cnt % 2][c - 1].x+1 == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c - 1].y == game.entities[cnt % 2][i].y && i != c-1) {
                            ans = true;
                            break;
                        }
                    }
                    if (game.entities[cnt % 2][c - 1].x + 1 == 6) {
                        System.out.println("갈 수 없는 곳입니다. 다시 입력해주세요.");
                        cnt--;
                    } else if(ans) {
                        System.out.println("다른 말이 있습니다. 다시 입력해주세요.");
                        cnt--;
                    } else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity)
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x + 1, game.entities[cnt % 2][c - 1].y);
                        else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x + 1, game.entities[cnt % 2][c - 1].y);
                        Check(c);
                    }
                } else {
                    System.out.println("wasd로만 입력하셔야합니다. 다시 입력해주세요.");
                    cnt--;
                }
            } else {
                if (move.equals("w")) {
                    for(int i= 0; i<8; i++) {
                        if(game.entities[cnt % 2][c - 1].x == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c - 1].y-1 == game.entities[cnt % 2][i].y && i != c-1) {
                            ans = true;
                            break;
                        }
                    }
                    if (game.entities[cnt % 2][c - 1].y - 1 == -1) {
                        System.out.println("갈 수 없는 곳입니다. 다시 입력해주세요.");
                        cnt--;
                    } else if(ans) {
                        System.out.println("다른 말이 있습니다.");
                        cnt--;
                    } else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity)
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x, game.entities[cnt % 2][c - 1].y - 1);
                        else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x, game.entities[cnt % 2][c - 1].y - 1);
                        Check(c);
                    }
                } else if (move.equals("a")) {
                    for(int i= 0; i<8; i++) {
                        if(game.entities[cnt % 2][c - 1].x-1 == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c - 1].y == game.entities[cnt % 2][i].y && i != c-1) {
                            ans = true;
                            break;
                        }
                    }
                    if (game.entities[cnt % 2][c - 1].x - 1 == -1) {
                        System.out.println("갈 수 없는 곳입니다. 다시 입력해주세요.");
                        cnt--;
                    } else if(ans) {
                        System.out.println("다른 말이 있습니다.");
                        cnt--;
                    } else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity)
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x - 1, game.entities[cnt % 2][c - 1].y);
                        else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x - 1, game.entities[cnt % 2][c - 1].y);
                        Check(c);
                    }
                } else if (move.equals("s")) {
                    for(int i= 0; i<8; i++) {
                        if(game.entities[cnt % 2][c - 1].x == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c - 1].y+1 == game.entities[cnt % 2][i].y && i != c-1) {
                            ans = true;
                            break;
                        }
                    }
                    if (game.entities[cnt % 2][c - 1].y + 1 == 6) {
                        System.out.println("갈 수 없는 곳 입니다. 다시 입력해주세요.");
                        cnt--;
                    } else if(ans) {
                        System.out.println("다른 말이 있습니다.");
                        cnt--;
                    } else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity) {
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x, game.entities[cnt % 2][c - 1].y + 1);
                        } else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x, game.entities[cnt % 2][c - 1].y + 1);
                        Check(c);
                    }
                } else if (move.equals("d")) {
                    for(int i= 0; i<8; i++) {
                        if(game.entities[cnt % 2][c - 1].x+1 == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c - 1].y == game.entities[cnt % 2][i].y && i != c-1) {
                            ans = true;
                            break;
                        }
                    }
                    if (game.entities[cnt % 2][c - 1].x + 1 == 6) {
                        System.out.println("갈 수 없는 곳 입니다. 다시 입력해주세요.");
                        cnt--;
                    } else if(ans) {
                        System.out.println("다른 말이 있습니다.");
                        cnt--;
                    } else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity)
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x + 1, game.entities[cnt % 2][c - 1].y);
                        else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x + 1, game.entities[cnt % 2][c - 1].y);
                        Check(c);
                    }
                } else {
                    System.out.println("wasd로만 입력하셔야 합니다. 다시 입력해주세요.");
                    cnt--;
                }
            }
        }
    }
}

class Oriented {
    public static void start(String Player1, String Player2) {
//        mainGameConnect mg = new mainGameConnect();
        mainGameConnect.setP1name(Player1);
        mainGameConnect.setP2name(Player2);
        Scanner sc = new Scanner(System.in);
        GamePlay game = new GamePlay();
        Board b = new Board(game);
        Ghost_move g = new Ghost_move(game);
        game.explain();
        game.GameSetting();
        b.getBoard();
        boolean result;
        boolean cnt = true;
        while(cnt) {
            g.Move();
            b.getBoard();
            for (int i = 0; i < 8; i++) {
                if (g.blue_good_cnt == 4 || g.red_bad_cnt==4 ||(game.entities[1][i] instanceof GoodEntity &&game.entities[1][i].x == 0 && game.entities[1][i].y == 0) || (game.entities[1][i] instanceof GoodEntity&&game.entities[1][i].x == 5 && game.entities[1][i].y == 0)) {
                    System.out.println("레드팀 승리!");
                    cnt = false;
                    result = true;
                    // p1
                    break;

                } else if (g.red_good_cnt == 4 ||g.blue_bad_cnt==4 ||(game.entities[1][i] instanceof GoodEntity&&game.entities[0][i].x == 0 && game.entities[0][i].y == 5) || (game.entities[0][i] instanceof GoodEntity&&game.entities[0][i].x == 5 && game.entities[0][i].y == 5)) {
                    System.out.println("블루팀 승리!");
                    cnt = false;
                    result = false;
                    // p2
                    break;
                }
            }
        }
    }
}