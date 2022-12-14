package AD_Project;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

class MainGameBoard{
    GamePlayer p;
    static boolean start = false;
    static int [][] board = new int[4][4];
    int x =3, y=3;
    int b1, b2, b3, b4, b5, b6, b7, b8;
    static int cnt ;
    int rest,cnt2=0, no,dice;
    String square_up = "旨收收收收收旬";
    String square_bottom = "曲收收收收收旭";
    String square_height_m = "早" + "  M  "+ "早" ;
    String square_height_c = "早" + "  C  "+ "早" ;
    String square_height_last = "早" + "  <  "+ "早" ;
    String square_height_Player1 = "早" + "  ≒  "+ "早" ;
    String square_height_Player2 = "早" + "  ∞  "+ "早" ;
    String square_height_2Player = "早" + " ≒∞  "+ "早" ;
    String square_height = "早" + "     "+ "早" ;
    MainGameBoard(GamePlayer p){
        this.p = p;
    }
    void getboard() {

        for(int i=0; i<4; i++) {
            System.out.print(square_up);
        }
        System.out.println();
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
        b1 = board[3][0];
        b2 = board[0][0];
        b3 = board[0][3];
        b4 = board[3][2];
        b5 = board[2][0];
        b6 = board[0][1];
        b7 = board[1][3];
        b8 = board[2][3];
        if(cnt ==0 ){
            board[3][3] =3;
        } else if (cnt == 0 && dice == -1) {
            board[3][3] = 3;
            board[3][2] = 0;
            return;
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
                    System.out.println("部");
                    p.isFinish = true;
                }
            }
        } else {
            x += dice;
            if (x >= 3) {
                x =3;
                System.out.println("部");
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
        if(board[3][0] >b1 || board[0][0] > b2 || board[0][3] >b3 ) {
            p.isMiniGame = true;
        } else if(board[0][1] > b6 || board[1][3] >b7 || board[2][0] >b5 || board[2][3] >b8 || board[3][2] >b4){
            p.isChance = true;
        }
        p.playerPosition[1] = y;
        p.playerPosition[0] = x;
    }
}
class GamePlayer{
    public boolean isScoreChange;
    public boolean isBackward;
    public boolean isPositionChange;
    String name; // Ы溯檜橫 檜葷
    int Score = 0; // 薄熱
    boolean isFinish = false; // Ы溯檜橫陛 紫雜雖薄縑 紫雜ц朝雖 ?挫恉炴? 滲熱
    boolean isWin; // Ы溯檜橫陛 蝓葬ц朝雖 ?挫恉炴? 滲熱
    boolean isBonus = false; // 爾傘蝶蒂 嫡懊朝雖 ?挫恉炴? 滲熱
    boolean isTurn = false; // Ы溯檜橫曖 離滔檣雖 ?挫恉炴? 滲熱
    boolean isMiniGame = false; // 嘐棲啪歜擊 ц朝雖 ?挫恉炴? 滲熱
    boolean isChance = false; // Ы溯檜橫陛 晦?蜊奏撣? 鉻懊朝雖 ?挫恉炴? 滲熱
    int [] playerPosition = {3,3}; // Ы溯檜橫曖 嬪纂蒂 盪濰ж朝 滲熱
}
public class MainGame {
    public static void main(String[] args) throws IOException, InterruptedException {
        GamePlayer player1 = new GamePlayer(); // Ы溯檜橫1 偌羹 儅撩
        GamePlayer player2 = new GamePlayer(); // Ы溯檜橫2 偌羹 儅撩
        MainGameBoard b1 = new MainGameBoard(player1);
        MainGameBoard b2 = new MainGameBoard(player2);
        MainGameBoard b; // 啪歜っ 偌羹 儅撩
        Scanner scanner = new Scanner(System.in);
        System.out.print("Player 1曖 檜葷擊 殮溘п輿撮蹂: ");
        player1.name = scanner.nextLine();
        System.out.print("Player 2曖 檜葷擊 殮溘п輿撮蹂: ");
        player2.name = scanner.nextLine();
        System.out.println("啪歜擊 衛濛м棲棻.");
        Thread.sleep(1000); //1蟾 渠晦
        System.out.println(player1.name+"擎(朝) ∞煎 ル衛腌棲棻.");
        time();
        System.out.println(player2.name+"擎(朝) ≒煎 ル衛腌棲棻.");
        time();
        System.out.println("嘐棲啪歜擎 識 4偃陛 氈蝗棲棻.");
        System.out.println();
        System.out.println("1. Yacht Dice");
        time();
        System.out.println("2. 堅蝶お 啪歜");
        time();
        System.out.println("3. 綰楷燮");
        time();
        System.out.println("4. 綵堅啪歜");
        time();
        System.out.println("啪歜っ擊 轎溘м棲棻.");
        time();
        b1.getboard();
        // 啪歜 衛濛
        while(true){ // 萃 醞 и 貲檜 紫雜雖薄縑 紫殖й 陽梱雖 奩犒
            playerTurn(player1, player2,b1);
            if(player1.isFinish){
                break;
            }
            playerTurn(player2, player1,b2);
            if(player2.isFinish){
                break;
            }
        }
    }
    public static void playerTurn(GamePlayer myself, GamePlayer counterPart, MainGameBoard board) throws InterruptedException, IOException {
        System.out.println(myself.name + "曖 欐 殮棲棻.");
        myself.isTurn = true;
        time();
        board.Game(dice());
        board.getboard();
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
                System.out.println("啪歜檜 謙猿腎歷蝗棲棻.");
                time();
                System.out.printf("%s朝 %d, %s朝%d 檜嘎煎... \n", myself.name,myself.Score, counterPart.name,counterPart.Score);
                time();
                System.out.println(myself.name + "曖 蝓葬!");
            } else if (myself.Score < counterPart.Score) {
                counterPart.isWin = true;
                System.out.println("啪歜檜 謙猿腎歷蝗棲棻.");
                time();
                System.out.printf("%s朝 %d, %s朝%d 檜嘎煎... \n", myself.name,myself.Score, counterPart.name,counterPart.Score);
                time();
                System.out.println(counterPart.name + "曖 蝓葬!");
            } else {
                System.out.println("鼠蝓睡!");
            }
        } else {
            int randomscore = (int) (Math.random() * 3) + 3;
            System.out.println("薄熱蒂 ?僱磈蔇懂炴?.");
            System.out.println("?僱磈? 薄熱: "+randomscore);
            myself.Score += randomscore;
            System.out.println(myself.name + "曖 薄熱: " + myself.Score);
            System.out.println(counterPart.name + "曖 薄熱: " + counterPart.Score);
            myself.isTurn = false;
            counterPart.isTurn = true;

        }
    }

    public static int dice() throws InterruptedException, IOException {
        int dice = (int) (Math.random() * 3) + 1;
        System.out.println("Enter蒂 揚楝憮 輿餌嬪蒂 掉葬撮蹂!");
        pause();
        for (int i = 3; i > 0; i--) { // 3蟾 蘋遴お 棻遴
            time(); // 1蟾 蓮朝 詭模萄
            System.out.print("."); // . 轎溘
        }
        time(); // 1蟾 蓮朝 詭模萄
        System.out.println("輿餌嬪曖 璋濠朝 " + dice + "殮棲棻.");
        time();
        System.out.println(dice+"蘊 檜翕м棲棻.");
        return dice;
    }
    public static void winnerScoreUp(GamePlayer player) {
        if (player.isWin) { // 嘐棲啪歜 蝓葬
            if (player.isBonus) { // 爾傘蝶蒂 嫡懊擊 唳辦.
                player.isBonus = false;
                System.out.println("か瞪戲煎 檣п "+player.name + "曖 薄熱陛 20薄 隸陛м棲棻.");
                player.Score += 20;
            } else { // 爾傘蝶蒂 嫡雖 跤ц擊 唳辦
                System.out.println(player.name + "曖 薄熱陛 10薄 隸陛м棲棻.");
                player.Score += 10;
            }
        } else{ // 嘐棲啪歜 ぬ寡
            if (player.isBonus) { // 爾傘蝶蒂 嫡懊擊 唳辦
                player.isBonus = false;
                System.out.println("か瞪戲煎 檣п "+player.name + "曖 薄熱陛 10薄 隸陛м棲棻.");
                player.Score += 10;
            } else { // 爾傘蝶蒂 嫡雖 跤ц擊 唳辦
                System.out.println(player.name + "曖 薄熱陛 5薄 隸陛м棲棻.");
                player.Score += 5;
            }
        }
    }
    public static void calculateMiniGameScore(GamePlayer player1, GamePlayer player2) {
        if(player1.isWin){ // Ы溯檜橫 1檜 蝓葬ц擊 唳辦
            System.out.println(player1.name + "椒檜 蝓葬ж樟蝗棲棻.");
            winnerScoreUp(player1);
            winnerScoreUp(player2);
            player1.isMiniGame = false;
            player2.isMiniGame = false;
            player1.isChance = false;
            player2.isChance = false;
        }
        else if(player2.isWin){ // Ы溯檜橫 2陛 蝓葬ц擊 唳辦
            System.out.println(player2.name + "椒檜 蝓葬ж樟蝗棲棻.");
            winnerScoreUp(player1);
            winnerScoreUp(player2);
            player1.isMiniGame = false;
            player2.isMiniGame = false;
            player1.isChance = false;
            player2.isChance = false;
        } else { // 鼠蝓睡橾 唳辦
            System.out.println("鼠蝓睡殮棲棻.");
            winnerScoreUp(player1);
            winnerScoreUp(player2);
            player1.isMiniGame = false;
            player2.isMiniGame = false;
            player1.isChance = false;
            player2.isChance = false;
        }
    }
    public static void miniGame(GamePlayer p1, GamePlayer p2) throws IOException, InterruptedException {
        System.out.println("嘐棲啪歜擊 衛濛м棲棻.");
        for (int i = 3; i > 0; i--) { // 3蟾 蘋遴お 棻遴
            time(); // 1蟾 蓮朝 詭模萄
            System.out.print("."); // . 轎溘
        }
        time(); // 1蟾 蓮朝 詭模萄
        System.out.println("嘐棲啪歜擎 楠渾戲煎 褒ч腌棲棻.");
        time();
        // 1睡攪 4梱雖 楠渾 璋濠 1偃 儅撩
        int random = (int) (Math.random() * 4) + 1;
        //寡翮曖 牖憮渠煎 陝 璋濠縑 蜃朝 嘐棲啪歜 褒ч
        if(random == 1) {
            System.out.println("Yacht Dice蒂 衛濛м棲棻.");
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
            System.out.println("堅蝶お 啪歜擊 衛濛м棲棻.");
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
            System.out.println("綰楷燮擊 衛濛м棲棻.");
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
            System.out.println("綵堅 啪歜擊 衛濛м棲棻.");
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
        //楠渾戲煎 雙蝶蒂 褒ч
        //楠渾戲煎 碳葬樹 高擊 true煎 滲唳
        int random = (int) (Math.random() * 5) + 1; // 霞辦⑽ ?ご? 僖晦
        if(random == 1){
            myself.isBonus = true;
        }
        else if(random == 2){
            myself.isMiniGame = true;
        }
        else if(random == 3){
            myself.isBackward = true;
        }
        else if(random == 4){
            myself.isScoreChange = true;
        }
        else {
            myself.isPositionChange = true;
        }
        if (myself.isBonus) {
            System.out.println("爾傘蝶 蘋萄蒂 鉻懊蝗棲棻.");
            time();
            bonusScore(myself);
        }
        if (myself.isMiniGame) {
            miniGame(myself, counterPart);
            calculateMiniGameScore(myself, counterPart);
        }
        if (myself.isScoreChange) {
            scoreChange(myself, counterPart);
        }
        if (myself.isPositionChange) {
            positionChange(myself, counterPart, board);
            myself.isChance = false;
            myself.isMiniGame = false;
        }
        if (myself.isBackward) {
            backward(myself, board); // <- 菴煎 鬲擊 陽 雙蝶 嬪纂賊 isChance = true;
        }
    }
    public static void scoreChange(GamePlayer myself, GamePlayer counterpart) throws InterruptedException {
        System.out.println(myself.name+"諦(婁)"+counterpart.name+"曖 薄熱陛 滲唳腎歷蝗棲棻.");
        time();
        //5薄擊 鼻渠寞戲煎睡攪 陛螳褥/邀(鼠濛嬪), 虜擒 5薄爾棻 瞳棻賊, 陛雖堅 氈朝 薄熱 瞪睡蒂 邀

        if (counterpart.Score < 5) {
            myself.Score += counterpart.Score;
            counterpart.Score = 0;
        } else {
            myself.Score += 5;
            counterpart.Score -= 5;
        }
        System.out.println(myself.name+"曖 薄熱: "+myself.Score);
        System.out.println(counterpart.name+"曖 薄熱: "+counterpart.Score);

    }
    public static void positionChange(GamePlayer myself, GamePlayer counterpart, MainGameBoard board) {
        System.out.println(myself.name+"諦(婁)"+counterpart.name+"曖 嬪纂陛 滲唳腎歷蝗棲棻.");
        int x = MainGameBoard.board[counterpart.playerPosition[0]][counterpart.playerPosition[1]];
        MainGameBoard.board[counterpart.playerPosition[0]][counterpart.playerPosition[1]] = MainGameBoard.board[myself.playerPosition[0]][myself.playerPosition[1]];
        MainGameBoard.board[myself.playerPosition[0]][myself.playerPosition[1]] =x;
        board.getboard();
    }
    public static void bonusScore(GamePlayer player) {
        System.out.println("嘐棲啪歜 薄熱 2寡 か瞪擊 ?僱磈牳抻懂炴?.");
        System.out.println("檜?? 紫雜ж朝 1偃曖 嘐棲啪歜 薄熱陛 2寡煎 隸陛м棲棻.");
        player.isBonus = true;
    }
    public static void backward(GamePlayer myself, MainGameBoard board) throws InterruptedException {
        int a,b;
        System.out.println("1蘊 菴煎 檜翕м棲棻.");
        System.out.println(myself.name+"曖 嬪纂陛 滲唳腎歷蝗棲棻.");
        myself.isBackward = false;
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