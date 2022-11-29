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

// �迭�� ��� ��Ҹ� 1���� SIZE*SIZE������ ���ڷ� �ʱ�ȭ
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				bingo[i][j] = i * SIZE + j + 1;
			}
		}

// �迭�� ����� ���� �ڼ��´�.
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				x = (int) (Math.random() * SIZE);
				y = (int) (Math.random() * SIZE);

				// bingo[i][j]�� ���Ƿ� ���õ� ��(bingo[x][y])�� �ٲ۴�.
				int tmp = bingo[i][j];
				bingo[i][j] = bingo[x][y];
				bingo[x][y] = tmp;
			}
		}
		// ������ ���
		System.out.println("    # Bingo Board #\n");
		do {
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (bingo[i][j] == 0) {
						if (check[i][j] == 1) {
							System.out.printf("%-5s", "��");
						} else if (check[i][j] == 2) {
							System.out.printf("%-5s", "��");
						}
					} else
						System.out.printf("%-5d", bingo[i][j]);
				}
				System.out.println();
			} // ���� ī��Ʈ
			System.out.println("--------------------------");
			System.out.printf("1~%d�� ���ڸ� �Է��ϼ���. > ", SIZE * SIZE);
			String tmp = scanner.nextLine(); // ȭ�鿡�� �Է¹��� ������ tmp�� ����
			num = Integer.parseInt(tmp); // �Է¹��� ���ڿ�(tmp)�� ���ڷ� ��ȯ

			// �Է¹��� ���ڿ� ���� ���ڰ� ����� ��Ҹ� ã�Ƽ� 0�� ����
			outer: for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (bingo[i][j] == num) {
						bingo[i][j] = 0;
						check[i][j] = player;
						break outer;
						// 2�� �ݺ��� ������
					}
				}
			}
			// P1����üũ
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
			} // P1���� �밢��üũ
			if ((check[0][0] == 1) && (check[1][1] == 1) && (check[2][2] == 1) && (check[3][3] == 1)
					&& (check[4][4] == 1))
				totalCnt1++;
			if ((check[0][4] == 1) && (check[1][3] == 1) && (check[2][2] == 1) && (check[3][1] == 1)
					&& (check[4][0] == 1))
				totalCnt1++;
			// P2����üũ
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
			} // P2���� �밢��üũ
			if ((check[0][0] == 2) && (check[1][1] == 2) && (check[2][2] == 2) && (check[3][3] == 2)
					&& (check[4][4] == 2))
				totalCnt2++;
			if ((check[0][4] == 2) && (check[1][3] == 2) && (check[2][2] == 2) && (check[3][1] == 2)
					&& (check[4][0] == 2))
				totalCnt2++;
			if (totalCnt1 >= 1 || totalCnt2 >= 1) {
				if (totalCnt1 >= 1) {
					System.out.println("    " + p1name + " �¸�!");
					p1isWin = true;
				}
				else if (totalCnt2 >= 1) {
					System.out.println("    " + p2name + " �¸�!");
					p2isWin = true;
				}
				// ���������� ���
				for (int i = 0; i < SIZE; i++) {
					for (int j = 0; j < SIZE; j++) {
						if (bingo[i][j] == 0) {
							if (check[i][j] == 1) {
								System.out.printf("%-5s", "��");
							} else if (check[i][j] == 2) {
								System.out.printf("%-5s", "��");
							}
						} else
							System.out.printf("%-5d", bingo[i][j]);
					}
					System.out.println();
				}
				System.out.println("    *������ �����մϴ�*\n");
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
		System.out.println("------------���º�-----------");
		scanner.close();
	}
}
