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
        System.out.println("������ ������ �����ðڽ��ϱ�? y/n");
        ans = sc.next();
        if(ans.equals("y")) {
            System.out.println("�� ������ �ɸ� �����Դϴ�. �� �÷��̾�� 4������ ���� ���ɰ� ���� ���� �� �� 8������ ������ ���մϴ�.");
            System.out.println("�� ���ɵ��� 6x6���� �ǿ� �������ֽ��ϴ�. �� �� ���� ���������� ���������� bad�� good�� �Է��� ���������� ������ �Ӽ��� ���� �� �ֽ��ϴ�.");
            System.out.println("�� �ϸ��� �ϳ��� ������ ������ ������ �� �ְ� ������ �ϴ� ���� ���� ������ �ִٸ� �� ������ ���� �� �ְ�, ���� ������ �����ǿ��� �������ϴ�.");
            System.out.println("������ �¸� ������ �� 3���� �Դϴ�. ù ��°�� �ڽ��� ���� ������ ����ʿ� �ִ� ȭ��ǥ�� ���� �Ǹ� �¸��մϴ�.");
            System.out.println("�� ��°�� �ڽ��� ���� ������ ��� �����ٸ� �¸��մϴ�.");
            System.out.println("������ ���� ������ ��� ��´ٸ� �¸��մϴ�. \n\n\n");
            System.out.println();
            System.out.println("������ �����Ͻ÷��� y�� �Է����ּ���.");
            while(true) {
                String ans2 = sc.next();
                if(ans2.equals("y")) {
                    break;
                } else {
                    System.out.println("�߸� �Է��ϼ̽��ϴ�. ������ �����Ͻ÷��� y�� �Է����ּ���.");
                }
            }
        } else {
            System.out.println("y�� n���� �Է����ֽʽÿ�.");
            explain();
        }
    }
    void GameSetting() {
        Scanner sc = new Scanner(System	.in);
//        mainGameConnect mg = new mainGameConnect();
        this.entities = new Entity[2][8];
        System.out.printf("%s�� ������, %s�� ������Դϴ�!", mainGameConnect.getP1Name(), mainGameConnect.getP2Name());
        for(int i = 0;i < 2;i++) {
            System.out.printf("%d ��° �÷��̾ ��Ʈ�� ���Ͻ� �����Դϴ�. \n", i+1);
            System.out.println("good�̳� bad�� �Է��� ������ ������ �˷��ּ���.");
            System.out.println("�ٸ� �÷��̾�� ���� ���� �����ּ���.");
            int good = 0;
            int bad = 0;
            for(int j = 0; j < 8;) {
                String ans = sc.next();
                if(ans.equals("good")) {
                    this.entities[i][j] = new GoodEntity(j % 4 + 1, i == 0 ? j / 4 : 4 + j / 4);
                    good++;
                    j++;
                    if(good==5) {
                        System.out.println("���� ���� ������ 4�� �Դϴ�. �ٽ� �Է����ּ���");
                        j --;
                        good--;
                        continue;
                    }
                }else if(ans.equals("bad")) {
                    this.entities[i][j] = new BadEntity(j % 4 + 1, i == 0 ? j / 4 : 4 + j / 4);
                    bad++;
                    j++;
                    if(bad==5) {
                        System.out.println("���� ���� ������ 4���Դϴ�. �ٽ� �Է����ּ���");
                        j--;
                        bad--;
                        continue;
                    }
                }else {
                    j++;
                    System.out.println("bad�� good���θ� �Է��ϼž��մϴ�. �ٽ� �Է����ּ���.");
                    j--;
                }
            }
            for(int k=0; k<16; k++) {
                System.out.println("");
            }
        }
        System.out.println("���� ����!");
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
                        System.out.println("���� �����̾����ϴ�!" + blue_good_cnt + "���� ��ҽ��ϴ�.");
                    } else {
                        red_good_cnt++;
                        System.out.println("���� �����̾����ϴ�!" + red_good_cnt + "���� ��ҽ��ϴ�.");
                    }
                } else {
                    if ((cnt+1) % 2 == 0) {
                        blue_bad_cnt++;
                        System.out.println("���� �����̾����ϴ�!" + blue_bad_cnt + "���� ��ҽ��ϴ�.");
                    } else {
                        red_bad_cnt++;
                        System.out.println("���� �����̾����ϴ�!" + red_bad_cnt + "���� ��ҽ��ϴ�.");
                    }
                }
                game.entities[(cnt + 1) % 2][i] = new GoodEntity(6, 6);
                break;
            } else if(game.entities[cnt % 2][c - 1].x == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c - 1].y == game.entities[cnt % 2][i].y && i != c-1) {
                System.out.println("�ٸ� ���� �ֽ��ϴ�. �ٽ� �������ּ���.");
            }
        }
    }

    void Move() {
        Scanner sc = new Scanner(System.in);
        cnt++;
        int c;
        boolean ans = false;
        System.out.print("� ������ �������� ���ڸ� �Է��ϼ��� : ");
        try{
            c = sc.nextInt();
            if(c<1 || c>8){
                System.out.println("���� �����Դϴ�. �ٽ� ������ ����ּ���.");
                cnt--;
                return;
            }
        } catch(InputMismatchException e) {
            System.out.println("�߸� �Է��ϼ̽��ϴ�. ���ڸ� �Է����ּ���.");
            cnt--;
            return;
        }
        if (game.entities[cnt % 2][c - 1].x == 6) {
            System.out.println("�̹� ���� ���Դϴ�. �ٽ� �������ּ���.");
            cnt--;
        } else {
            System.out.print("wasd�� ������ ������ ������ �Է����ּ��� : ");
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
                        System.out.println("�� �� ���� ���Դϴ�. �ٽ� �Է����ּ���.");
                        cnt--;
                    }else if(ans){
                        System.out.println("�ٸ� ���� �ֽ��ϴ�.");
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
                        System.out.println("�� �� ���� ���Դϴ�. �ٽ� �Է����ּ���.");
                        cnt--;
                    }else if(ans){
                        System.out.println("�ٸ� ���� �ֽ��ϴ�.");
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
                        System.out.println("�� �� ���� ���Դϴ�. �ٽ� �Է����ּ���.");
                        cnt--;
                    } else if(ans) {
                        System.out.println("�ٸ� ���� �ֽ��ϴ�.");
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
                        System.out.println("�� �� ���� ���Դϴ�. �ٽ� �Է����ּ���.");
                        cnt--;
                    } else if(ans) {
                        System.out.println("�ٸ� ���� �ֽ��ϴ�. �ٽ� �Է����ּ���.");
                        cnt--;
                    } else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity)
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x + 1, game.entities[cnt % 2][c - 1].y);
                        else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x + 1, game.entities[cnt % 2][c - 1].y);
                        Check(c);
                    }
                } else {
                    System.out.println("wasd�θ� �Է��ϼž��մϴ�. �ٽ� �Է����ּ���.");
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
                        System.out.println("�� �� ���� ���Դϴ�. �ٽ� �Է����ּ���.");
                        cnt--;
                    } else if(ans) {
                        System.out.println("�ٸ� ���� �ֽ��ϴ�.");
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
                        System.out.println("�� �� ���� ���Դϴ�. �ٽ� �Է����ּ���.");
                        cnt--;
                    } else if(ans) {
                        System.out.println("�ٸ� ���� �ֽ��ϴ�.");
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
                        System.out.println("�� �� ���� �� �Դϴ�. �ٽ� �Է����ּ���.");
                        cnt--;
                    } else if(ans) {
                        System.out.println("�ٸ� ���� �ֽ��ϴ�.");
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
                        System.out.println("�� �� ���� �� �Դϴ�. �ٽ� �Է����ּ���.");
                        cnt--;
                    } else if(ans) {
                        System.out.println("�ٸ� ���� �ֽ��ϴ�.");
                        cnt--;
                    } else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity)
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x + 1, game.entities[cnt % 2][c - 1].y);
                        else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x + 1, game.entities[cnt % 2][c - 1].y);
                        Check(c);
                    }
                } else {
                    System.out.println("wasd�θ� �Է��ϼž� �մϴ�. �ٽ� �Է����ּ���.");
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
                    System.out.println("������ �¸�!");
                    cnt = false;
                    result = true;
                    // p1
                    break;

                } else if (g.red_good_cnt == 4 ||g.blue_bad_cnt==4 ||(game.entities[1][i] instanceof GoodEntity&&game.entities[0][i].x == 0 && game.entities[0][i].y == 5) || (game.entities[0][i] instanceof GoodEntity&&game.entities[0][i].x == 5 && game.entities[0][i].y == 5)) {
                    System.out.println("����� �¸�!");
                    cnt = false;
                    result = false;
                    // p2
                    break;
                }
            }
        }
    }
}