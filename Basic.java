package basic;
import java.util.*;
//class Entity{
//    int x, y;
//    Entity(int x, int y){
//        this.x = x;
//        this.y = y;
//    }
//}
class board{
	int [][] board = new int[4][4];
	int x =3, y=3;
	int rest,cnt, cnt2;
	String square_up = "┏━━━━━┓";
	String square_bottom = "┗━━━━━┛";
	String square_height_m = "┃" + "  M  "+ "┃" ;
	String square_height_c = "┃" + "  C  "+ "┃" ;
	String square_height_last = "┃" + "  ❮  "+ "┃" ;
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
			board[x][y] +=1;
		} else {
			board[x][y] +=2;
		}
	}
	
}
public class Basic{
	public static void main(String [] args) {
		board b = new board();
		Scanner sc = new Scanner(System.in);
		while(true) {
//		b.getboard();
		b.Game(sc.nextInt());
		}
	}
}
