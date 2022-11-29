package AD_Project;

import java.util.Scanner;
import java.util.Stack;

public class BM {
	public static boolean p1isWin = false;
	public static boolean p2isWin = false;


	public static void start(String p1name, String p2name) {
		final int SIZE = 5;
		int x = 0, y = 0, num = 0;
		int totalCnt1 = 0;
		int totalCnt2 = 0;
		int cnt = 0;
		int player = 1;
		int bingoCnt = SIZE * SIZE;


		int[][] check = new int[SIZE][SIZE];
		int[][] bingo = new int[SIZE][SIZE];
		Scanner scanner = new Scanner(System.in);

// 배열의 모든 요소를 1부터 SIZE*SIZE까지의 숫자로 초기화
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				bingo[i][j] = i * SIZE + j + 1;
			}
		}

// 배열에 저장된 값을 뒤섞는다.
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				x = (int) (Math.random() * SIZE);
				y = (int) (Math.random() * SIZE);

				// bingo[i][j]와 임의로 선택된 값(bingo[x][y])을 바꾼다.
				int tmp = bingo[i][j];
				bingo[i][j] = bingo[x][y];
				bingo[x][y] = tmp;
			}
		}
		// 빙고판 출력
		System.out.println("    # Bingo Board #\n");
		do {
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (bingo[i][j] == 0) {
						if (check[i][j] == 1) {
							System.out.printf("%-5s", "☆");
						} else if (check[i][j] == 2) {
							System.out.printf("%-5s", "★");
						}
					} else
						System.out.printf("%-5d", bingo[i][j]);
				}
				System.out.println();
			} // 빙고 카운트
			System.out.println("--------------------------");
			System.out.printf("1~%d의 숫자를 입력하세요. > ", SIZE * SIZE);
			String tmp = scanner.nextLine(); // 화면에서 입력받은 내용을 tmp에 저장
			num = Integer.parseInt(tmp); // 입력받은 문자열(tmp)를 숫자로 변환

			// 입력받은 숫자와 같은 숫자가 저장된 요소를 찾아서 0을 저장
			outer: for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (bingo[i][j] == num) {
						bingo[i][j] = 0;
						check[i][j] = player;
						break outer;
						// 2중 반복문 나가기
					}
				}
			}
			// P1빙고체크
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (check[i][j] == 1)
						cnt++;
				}
				if (cnt == 5)
					totalCnt1++;
				cnt = 0;

				for (int j = 0; j < SIZE; j++) {
					if (check[j][i] == 1)
						cnt++;
				}
				if (cnt == 5)
					totalCnt1++;
				cnt = 0;
			} // P1빙고 대각선체크
			if ((check[0][0] == 1) && (check[1][1] == 1) && (check[2][2] == 1) && (check[3][3] == 1)
					&& (check[4][4] == 1))
				totalCnt1++;
			if ((check[0][4] == 1) && (check[1][3] == 1) && (check[2][2] == 1) && (check[3][1] == 1)
					&& (check[4][0] == 1))
				totalCnt1++;
			// P2빙고체크
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (check[i][j] == 2)
						cnt++;
				}
				if (cnt == 5)
					totalCnt2++;
				cnt = 0;

				for (int j = 0; j < SIZE; j++) {
					if (check[j][i] == 2)
						cnt++;
				}
				if (cnt == 5)
					totalCnt2++;
				cnt = 0;
			} // P2빙고 대각선체크
			if ((check[0][0] == 2) && (check[1][1] == 2) && (check[2][2] == 2) && (check[3][3] == 2)
					&& (check[4][4] == 2))
				totalCnt2++;
			if ((check[0][4] == 2) && (check[1][3] == 2) && (check[2][2] == 2) && (check[3][1] == 2)
					&& (check[4][0] == 2))
				totalCnt2++;
			if (totalCnt1 >= 1 || totalCnt2 >= 1) {
				if (totalCnt1 >= 1) {
					System.out.println("    " + p1name + " 승리!");
					p1isWin = true;
				}
				else if (totalCnt2 >= 1) {
					System.out.println("    " + p2name + " 승리!");
					p2isWin = true;
				}
				// 최종빙고판 출력
				for (int i = 0; i < SIZE; i++) {
					for (int j = 0; j < SIZE; j++) {
						if (bingo[i][j] == 0) {
							if (check[i][j] == 1) {
								System.out.printf("%-5s", "☆");
							} else if (check[i][j] == 2) {
								System.out.printf("%-5s", "★");
							}
						} else
							System.out.printf("%-5d", bingo[i][j]);
					}
					System.out.println();
				}
				System.out.println("    *게임을 종료합니다*\n");
				break;
			}

			if (player == 1) {
				player = 2;
			} else if (player == 2) {
				player = 1;
			}

			if (num >= 1 || num <= 25)
				bingoCnt--;

		} while (bingoCnt >= 0);
		System.out.println("------------무승부-----------");
		scanner.close();
	}
}
