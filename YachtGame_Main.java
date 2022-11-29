package AD_Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

class mainGameConnect{
	static String p1 = "Player1"; // �÷��̾� 1 �̸�
	static String p2 = "Player2"; // �÷��̾� 2 �̸�
	public void setP1name(String p1name){
		p1 = p1name;
	} // �÷��̾� 1 �̸� ����
	public void setP2name(String p2name){
		p2 = p2name;
	} // �÷��̾� 2 �̸� ����
	public static String getP1Name(){
		return p1;
	} // �÷��̾� 1 �̸� ��ȯ
	public static String getP2Name(){
		return p2;
	} // �÷��̾� 2 �̸� ��ȯ
}
class p1player extends mainGameConnect{ // �÷��̾� 1 Ŭ����
	static boolean p1isWin = false; // �÷��̾� 1 �¸� ����
	public static Boolean p1Result(){
		return p1isWin;
	} // �÷��̾� 1 �¸� ���� ��ȯ

}
class p2player extends mainGameConnect{ // �÷��̾� 2 Ŭ����
	static boolean p2isWin = false; // �÷��̾� 2 �¸� ����
	public static Boolean p2Result(){
		return p2isWin;
	} // �÷��̾� 2 �¸� ���� ��ȯ
}

public class YachtGame_Main {
	public static void start() throws InterruptedException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // �Է��� �ޱ� ���� BufferedReader
		ScoreBoard sb = new ScoreBoard(); // ������
		String p1; // �÷��̾�1�� �̸�
		String p2; // �÷��̾�2�� �̸�
		String ans = ""; // �÷��̾ �Է��� ��
		String temp = ""; // �÷��̾ �Է��� ���� �ӽ÷� ����
		int[] dice = new int[5]; // �ֻ��� 5���� ������ �迭
		System.out.println("-Yacht Dice-"); // ���� �̸� ���
		p1 = p1player.getP1Name(); // �÷��̾�1�� �̸� ��������
		p2 = p2player.getP2Name(); // �÷��̾�2�� �̸� ��������
		System.out.println(p1 + "�԰� " + p2 + "���� Yacht Dice�� �����մϴ�."); // �÷��̾�1�� �÷��̾�2�� �̸� ���
		for (int i = 3; i > 0; i--) { // 3�� ī��Ʈ �ٿ�
			time(); // 1�� ���� �޼ҵ�
			System.out.print("."); // . ���
		}
		time(); // 1�� ���� �޼ҵ�
		System.out.println("game Start!"); // ���� ���� ���
		time(); // 1�� ���� �޼ҵ�
		while (true) { // ���� �ݺ�
			System.out.println(p1 + "���� �����Դϴ�. �ֻ����� �����ϴ�."); // �÷��̾�1�� ���� ���
			rollDiceAll(dice); // �ֻ��� 5���� ������ �޼ҵ�
			printRoll(dice); // �ֻ��� 5���� ����ϴ� �޼ҵ�
			time(); // 1�� ���� �޼ҵ�
			dice = reRollDice(dice); // �ֻ����� ���ġ�ϴ� �޼ҵ�
			p1inputScore(dice, sb); // �÷��̾�1�� ������ �Է��ϴ� �޼ҵ�
			time(); // 1�� ���� �޼ҵ�
			System.out.println(p2 + "���� �����Դϴ�. �ֻ����� �����ϴ�."); // �÷��̾�2�� ���� ���
			rollDiceAll(dice); // �ֻ��� 5���� ������ �޼ҵ�
			printRoll(dice); // �ֻ��� 5���� ����ϴ� �޼ҵ�
			time(); // 1�� ���� �޼ҵ�
			dice = reRollDice(dice); // �ֻ����� ���ġ�ϴ� �޼ҵ�
			p2inputScore(dice, sb); // �÷��̾�2�� ������ �Է��ϴ� �޼ҵ�
			gameOverCheck(p1, p2, sb); // ������ �������� Ȯ���ϴ� �޼ҵ�

		}
	}
	private static int genRandom() { // 1~6 ������ ������ ������ ��ȯ�ϴ� �޼ҵ�
		int result = 0; // ��ȯ�� ����
		result = (int) (Math.random() * 6) + 1; // 1~6 ������ ������ ������ ��ȯ
		return result; // ��ȯ
	}

	private static void time() throws InterruptedException { // 1�� ���� �޼ҵ�
		TimeUnit.SECONDS.sleep(1); // 1�� ����
	}

	private static int[] rollDiceAll(int[] dice) throws InterruptedException { // �ֻ��� 5���� ������ �޼ҵ�
		for (int i = 0; i < 5; i++) { // �ֻ��� 5���� ������ �ݺ���
			dice[i] = genRandom(); // �ֻ��� 5���� ������ �޼ҵ�
		}
		return dice; // �ֻ��� 5���� ��ȯ
	}

	private static void printRoll(int[] dice) throws InterruptedException { // �ֻ��� 5���� ����ϴ� �޼ҵ�
		for (int i = 3; i >= 0; i--) { // 3�� ī��Ʈ �ٿ�
			time(); // 1�� ���� �޼ҵ�
			if (i == 3) { // 3���� ��
				System.out.print("Dice"); // Dice ���
			} else if (i > 0) { // 2��, 1���� ��
				System.out.print("."); // . ���
			} else { // 0���� ��
				System.out.println("roll-!"); // roll-! ���
			}
		}
		time(); // 1�� ���� �޼ҵ�
		System.out.println(Arrays.toString(dice)); // �ֻ��� 5���� ���

	}

	private static int[] reRollDice(int[] dice) throws IOException, InterruptedException { // �ֻ����� ���ġ�ϴ� �޼ҵ�
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // �Է��� �ޱ� ���� BufferedReader ��ü ����
		String ans; // ���ġ�� �ֻ����� �Է¹��� ����
		boolean inputLoop = true; // ���ġ�� �ֻ����� �Է¹޴� �ݺ����� ������ ����
		boolean freePath=false; // ����� ���ġ�� �� �ִ��� Ȯ���ϴ� ����
		int diceCount = 0; // ���ġ�� �ֻ����� ������ ������ ����
		int rollCount = 2; // ���ġ�� �� �ִ� Ƚ���� ������ ����
		int[] diceIdxBox; // ���ġ�� �ֻ����� �ε����� ������ �迭
		int[] temp; // ���ġ�� �ֻ����� ������ �迭
		while (rollCount > 0) { // ���ġ�� �� �ִ� Ƚ���� 0���� Ŭ �� �ݺ�
			inputLoop = true; // ���ġ�� �ֻ����� �Է¹޴� �ݺ����� ������ ������ true�� �ʱ�ȭ
			System.out.println("�ٽ� ������ ���� �ֻ����� �ֽ��ϱ�?(y/n)"); // ���ġ�� �ֻ����� �ִ��� ����� ��¹�
			ans = br.readLine(); // ���ġ�� �ֻ����� �ִ��� �Է¹���
			if (ans.equals("y") || ans.equals("Y")) { // ���ġ�� �ֻ����� ���� ��
				while (inputLoop) { // ���ġ�� �ֻ����� �Է¹޴� �ݺ���
					diceCount = 0; // ���ġ�� �ֻ����� ������ ������ ������ 0���� �ʱ�ȭ
					diceIdxBox = new int[0]; // ���ġ�� �ֻ����� �ε����� ������ �迭�� 0���� �ʱ�ȭ
					System.out.println("������ ���� �ֻ����� ��ȣ�� �������� �����Ͽ� �Է��Ͻÿ�.\n��ɾ� :1 2 3 4 5 / all / (���������)n "); // ���ġ�� �ֻ����� ��ȣ�� �Է¹޴� ��¹�
					ans = br.readLine(); // ���ġ�� �ֻ����� ��ȣ�� �Է¹���
					StringTokenizer st = new StringTokenizer(ans); // �Է¹��� ���ġ�� �ֻ����� ��ȣ�� �������� �����Ͽ� ��ūȭ
					while (st.hasMoreTokens()) { // ��ū�� �������� �� �ݺ�
						if (diceCount > 4) { // ���ġ�� �ֻ����� ������ 5���� �ʰ��� ��
							System.out.println("�ֻ����� ������ �ʰ��� �Է��Դϴ�. �ٽ� �Է����ּ���."); // ���ġ�� �ֻ����� ������ �ʰ��� �Է��̶�� ��¹�
							inputLoop = true; // ���ġ�� �ֻ����� �Է¹޴� �ݺ����� ������ ������ true�� �ʱ�ȭ
							break; // �ݺ����� ��������
						}
						ans = st.nextToken(); // ��ū�� ans�� ����
						if (ans.equals("all")) { // ���ġ�� �ֻ����� ��ȣ�� all�� �Է¹޾��� ��
							diceIdxBox = new int[0];// �ٽ� ���� �ε��� �ʱ�ȭ (�߰��� All�� ������ ���, ���� ����)
							dice = rollDiceAll(dice); // �ֻ����� ��� ���ġ
							inputLoop = false; // ���ġ�� �ֻ����� �Է¹޴� �ݺ����� ������ ������ false�� �ʱ�ȭ
							break; // �ݺ����� ��������
						} else if (ans.equals("n")||ans.equals("N")) { // ���ġ�� �ֻ����� ��ȣ�� n���� �Է¹޾��� ��
							System.out.println("�ֻ��� �����⸦ ����մϴ�."); // �ֻ��� �����⸦ ����Ѵٴ� ��¹�
							freePath=true; // ����� �̵��� �� �ִ� ���¸� true�� �ʱ�ȭ
							inputLoop=false; // ���ġ�� �ֻ����� �Է¹޴� �ݺ����� ������ ������ false�� �ʱ�ȭ
							break; // �ݺ����� ��������
						} else if (ans.length() == 1 && Character.isDigit(ans.charAt(0))) { // ���ġ�� �ֻ����� ��ȣ�� 1�ڸ� ���ڷ� �Է¹޾��� ��
							temp = new int[diceIdxBox.length + 1]; // ���ġ�� �ֻ����� ��ȣ�� ������ �迭�� ����
							System.arraycopy(diceIdxBox, 0, temp, 0, diceIdxBox.length); // ���ġ�� �ֻ����� ��ȣ�� ������ �迭�� ���ġ�� �ֻ����� ��ȣ�� ����
							temp[diceIdxBox.length] = Integer.parseInt(ans); // ���ġ�� �ֻ����� ��ȣ�� ������ �迭�� ���ġ�� �ֻ����� ��ȣ�� ����
							diceIdxBox = temp; // ���ġ�� �ֻ����� ��ȣ�� ������ �迭�� ���ġ�� �ֻ����� ��ȣ�� ������ �迭�� �ʱ�ȭ
							diceCount++; // ���ġ�� �ֻ����� ������ 1 ����
							inputLoop = false; // ���ġ�� �ֻ����� �Է¹޴� �ݺ����� ������ ������ false�� �ʱ�ȭ
						} else { // ���ġ�� �ֻ����� ��ȣ�� 1�ڸ� ���ڰ� �ƴ� �ٸ� ���ڷ� �Է¹޾��� ��
							System.out.println("�߸��� �Է��Դϴ�."); // �߸��� �Է��̶�� ��¹�
							inputLoop = true; // ���ġ�� �ֻ����� �Է¹޴� �ݺ����� ������ ������ true�� �ʱ�ȭ
							break; // �ݺ����� ��������
						} // if end
					} // token End
					if (!inputLoop&&!freePath) {// token���� ������ �����ٸ� = ���ġ�� �ֻ����� �Է¹޴� �ݺ����� ������ ������ false�̰� ����� �̵��� �� �ִ� ���°� false�� ��
						dice = rollSelectedDice(dice, diceIdxBox); // ������ �ֻ����� ������.
						printRoll(dice); // ���� �ֻ����� ����Ѵ�.
						rollCount--; // ���� �� �ִ� Ƚ���� 1 ����
						System.out.println("���� ������ Ƚ��: " + rollCount); // ���� �� �ִ� Ƚ���� ���
					}
					freePath=false; // ����� �̵��� �� �ִ� ���¸� false�� �ʱ�ȭ
				} // loop end
			} // if(y) end
			else if (ans.equals("n") || ans.equals("N")) { // �ֻ����� ���ġ���� ���� ��
				rollCount = 0; // ���� �� �ִ� Ƚ���� 0���� �ʱ�ȭ
				inputLoop = false; // ���ġ�� �ֻ����� �Է¹޴� �ݺ����� ������ ������ false�� �ʱ�ȭ
			} else { // �ֻ����� ���ġ���� ������ �Է¹޴� �ݺ����� ������ ������ false�� ��
				System.out.println("�߸��� �Է��Դϴ�. �ٽ� �Է����ּ���."); // �߸��� �Է��̶�� ��¹�
				inputLoop = true; // �ֻ����� ���ġ���� ������ �Է¹޴� �ݺ����� ������ ������ true�� �ʱ�ȭ
			}
			if (rollCount == 0) { // ���� �� �ִ� Ƚ���� 0�� ��
				System.out.println("�ֻ��� �����⸦ �����մϴ�."); // �ֻ��� �����⸦ �����Ѵٴ� ��¹�
			}
		} // rollCount end
		return dice; // ���� �ֻ����� ��ȯ
	}

	private static int[] rollSelectedDice(int[] dice, int[] diceIdx) { // ������ �ֻ����� ������ �޼ҵ�
		for (int idx : diceIdx) { // ������ �ֻ����� ������ŭ �ݺ�
			dice[idx - 1] = genRandom(); // ������ �ֻ����� ������.
		}
		return dice; // ���� �ֻ����� ��ȯ
	}

	private static void p1inputScore(int[] dice, ScoreBoard sb) throws IOException { // �÷��̾�1�� �����ǿ� ������ �Է��ϴ� �޼ҵ�
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // �Է��� �ޱ� ���� BufferedReader ��ü ����
		int trueCount = 0; // �ֻ����� ������ ���� ���� ����
		int score = 0; // ������ �����ϱ� ���� ����
		int tempInt = 0; // �ӽ÷� �ֻ����� ������ �����ϱ� ���� ����
		String element; // �������� ��Ҹ� �����ϱ� ���� ����
		String temp; // �ӽ÷� �������� ��Ҹ� �����ϱ� ���� ����
		boolean inputLoop = true; // ������ �Է¹޴� �ݺ����� ������ ����
		boolean tfPoint = false; // �����ǿ� ������ �Է��� �� �ִ����� �Ǵ��ϱ� ���� ����
		while (inputLoop) { // ������ �Է¹޴� �ݺ���
			sb.printScoreBoard(); // �������� ���
			System.out.println("������ ���� �׸��� �Է��ϼ���. (ex)Aces"); // ������ ���� �׸��� �Է¹޴� ��¹�
			element = br.readLine(); // ������ ���� �׸��� �Է¹޴´�.
			temp=""; // �ӽ÷� �������� ��Ҹ� �����ϱ� ���� ������ �ʱ�ȭ
			StringTokenizer st = new StringTokenizer(element); // �Է¹��� ������ ���� �׸��� ������ �������� �и��ϱ� ���� StringTokenizer ��ü ����
			element=""; // �������� ��Ҹ� �����ϱ� ���� ������ �ʱ�ȭ
			while (st.hasMoreTokens()) {// ���� ���� ���� ����� �۾�
				temp += st.nextToken(); // ������ ������ ���ڸ� �ӽ÷� ����
				System.out.println(temp); // �ӽ÷� ������ ���ڸ� ���
			}
			for (int i = 0; i < temp.length(); i++) {// ���� �ҹ��ڷ� ��ȯ�ϴ� �۾�
				if (Character.isLetter(temp.charAt(i)) && temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z') {// ����&A~Z����
					// üũ
					element += (char) (temp.charAt(i) + 32); // �ҹ��ڷ� ��ȯ�Ͽ� ����
				} else { // ���ڰ� �ƴϰų� A~Z�� �ƴ� ���
					element += temp.charAt(i); // �״�� ����
				}
			} // �������, ������ ����, ���� �ҹ����� ���ڿ� �ϼ�
			if (element.equals("aces")) { // Aces�� ���
				if (sb.p1AcesTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
						if (dice[i] == 1) { // �ֻ����� ���� 1�� ���
							trueCount++; // �ֻ����� ������ 1 ����
						}
					}
					sb.p1Aces = trueCount; // �����ǿ� �ֻ����� ������ ����
					sb.p1AcesTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // �̹� �Է¹��� ���¶��
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ������ ��� ����
				}
			} else if (element.equals("deuces")) { // Deuces�� ���
				if (sb.p1DeucesTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
						if (dice[i] == 2) { // �ֻ����� ���� 2�� ���
							trueCount++; // �ֻ����� ������ 1 ����
						}
					}
					sb.p1Deuces = trueCount * 2; // �����ǿ� �ֻ����� ������ ����
					sb.p1DeucesTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // �̹� �Է¹��� ���¶��
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ������ ��� ����
				}
			} else if (element.equals("threes")) { // Threes�� ���
				if (sb.p1ThreesTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
						if (dice[i] == 3) { // �ֻ����� ���� 3�� ���
							trueCount++; // �ֻ����� ������ 1 ����
						}
					}
					sb.p1Threes = trueCount * 3; // �����ǿ� �ֻ����� ������ ����
					sb.p1ThreesTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // �̹� �Է¹��� ���¶��
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ������ ��� ����
				}
			} else if (element.equals("fours")) { // Fours�� ���
				if (sb.p1FoursTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
						if (dice[i] == 4) { // �ֻ����� ���� 4�� ���
							trueCount++; // �ֻ����� ������ 1 ����
						}
					}
					sb.p1Fours = trueCount * 4; // �����ǿ� �ֻ����� ������ ����
					sb.p1FoursTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // �̹� �Է¹��� ���¶��
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ������ ��� ����
				}
			} else if (element.equals("fives")) { // Fives�� ���
				if (sb.p1FivesTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
						if (dice[i] == 5) { // �ֻ����� ���� 5�� ���
							trueCount++; // �ֻ����� ������ 1 ����
						}
					}
					sb.p1Fives = trueCount * 5; // �����ǿ� �ֻ����� ������ ����
					sb.p1FivesTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // �̹� �Է¹��� ���¶��
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ������ ��� ����
				}
			} else if (element.equals("sixes")) { // Sixes�� ���
				if (sb.p1SixesTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
						if (dice[i] == 6) { // �ֻ����� ���� 6�� ���
							trueCount++; // �ֻ����� ������ 1 ����
						}
					}
					sb.p1Sixes = trueCount * 6; // �����ǿ� �ֻ����� ������ ����
					sb.p1SixesTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // �̹� �Է¹��� ���¶��
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ������ ��� ����
				}
			} else if (element.equals("choice")) { // Choice�� ���
				if (sb.p1ChoiceTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// ���̽��� �ܼ��� ����
						sb.p1Choice += dice[i]; // �����ǿ� �ֻ����� ������ ����
					}
					sb.p1ChoiceTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // �̹� �Է¹��� ���¶��
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ������ ��� ����
				}
			} else if (element.equals("4ofakind")) { // 4 of a kind�� ���
				if (sb.p1FourOfKindTF) {// ���� �Է¹��� ���� ���¶��,
					trueCount = 0;// �ʱ�ȭ
					tfPoint = false;// �ʱ�ȭ
					for (int i = 0; i < 4; i++) {// �������� ����
						if (dice[i] - dice[i + 1] < 0) { // ���������� �ƴ� ���
							tempInt = dice[i + 1]; // �ӽ� ������ �� ����
							dice[i + 1] = dice[i]; // �� ��ȯ
							dice[i] = tempInt; // �ӽ� ������ ����� �� ����
							i = -1; // i�� -1�� �ʱ�ȭ
						}
					}
					for (int i = 0; i < 4; i++) { // 4���� �ֻ����� ������ Ȯ��
						if (dice[i] - dice[i + 1] == 0) {// ��ġ�ϴ� ��� true����++
							trueCount++; // trueCount�� 1 ����
						} else { // ��ġ���� �ʴ� ���
							if (i == 0 || i == 3) {// �ֻ��� ���� �� �ڰ� ���̰� ���� ��,
								tfPoint = true; // �� ���̳��� �ڸ��� (idx) 0�� 1 �Ǵ� 3�� 4����
							}
						}
					}
					if (trueCount >= 3 && tfPoint) { // 4���� �ֻ����� ����, �� �ڰ� ���̰� ���� ���
						for (int num : dice) {// �ֻ��� ���� �� ���� ���ϱ�
							sb.p1FourOfKind += num; // �����ǿ� ����
						}
					} else {// ���� ������ �ȵ��� ��� 0�Է�
						sb.p1FourOfKind = 0; // �����ǿ� 0����
					}
					sb.p1FourOfKindTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // �̹� �Է¹��� ���¶��
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ������ ��� ����
				}
			} else if (element.equals("fullhouse")) { // full house�� ���
				if (sb.p1FullHouseTF) {// ���� �Է¹��� ���� ���¶��,
					tfPoint = false; // �ʱ�ȭ
					trueCount = 0; // �ʱ�ȭ
					for (int i = 0; i < 4; i++) {// �������� ����
						if (dice[i] - dice[i + 1] < 0) { // ���������� �ƴ� ���
							tempInt = dice[i + 1]; // �ӽ� ������ �� ����
							dice[i + 1] = dice[i]; // �� ��ȯ
							dice[i] = tempInt; // �ӽ� ������ ����� �� ����
							i = -1; // i�� -1�� �ʱ�ȭ
						}
					}
					for (int i = 0; i < 4; i++) { // 4���� �ֻ����� ������ Ȯ��
						if (dice[i] - dice[i + 1] == 0) {// ��ġ�ϴ� ��� true����++
							trueCount++; // trueCount�� 1 ����
						} else { // ��ġ���� �ʴ� ���
							if (i == 1 || i == 2) {// �ֻ��� ���� �� �ڰ� ���̰� ���� ��,
								tfPoint = true; // �� ���̳��� �ڸ��� (idx) 1�� 2 �Ǵ� 2�� 3����
							}
						}
					}
					if (trueCount >= 3 && tfPoint) { // 4���� �ֻ����� ����, �� �ڰ� ���̰� ���� ���
						for (int num : dice) {// �ֻ��� ���� �� ���� ���ϱ�
							sb.p1FullHouse += num; // �����ǿ� ����
						}
					} else {// ���� ������ �ȵ��� ��� 0�Է�
						sb.p1FullHouse = 0; // �����ǿ� 0����
					}
					sb.p1FullHouseTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					tfPoint = false;// tf����Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // �̹� �Է¹��� ���¶��
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ������ ��� ����
				}
			} else if (element.equals("smallstraight")) { // small straight�� ���
				if (sb.p1SmallStraightTF) {// ���� �Է¹��� ���� ���¶��,
					trueCount = 0; // �ʱ�ȭ
					for (int i = 0; i < 4; i++) {// �������� ����
						if (dice[i] - dice[i + 1] < 0) { // ���������� �ƴ� ���
							tempInt = dice[i + 1]; // �ӽ� ������ �� ����
							dice[i + 1] = dice[i]; // �� ��ȯ
							dice[i] = tempInt; // �ӽ� ������ ����� �� ����
							i = -1; // i�� -1�� �ʱ�ȭ
						}
					}
					for (int i = 0; i < 4; i++) { // 4���� �ֻ����� ������ Ȯ��
						if (dice[i] - dice[i + 1] == 1) { // ��ġ�ϴ� ��� true����++
							trueCount++; // trueCount�� 1 ����
						}
					}
					if (dice[0] == 5 && trueCount == 4) { // 5,4,3,2,1�� ���
						sb.p1SmallStraight = 30; // �����ǿ� 30����
					} else { // ���� ������ �ȵ��� ��� 0�Է�
						sb.p1SmallStraight = 0; // �����ǿ� 0����
					}
					sb.p1SmallStraightTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // �̹� �Է¹��� ���¶��
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ������ ��� ����
				}
			} else if (element.equals("largestraight")) { // large straight�� ���
				if (sb.p1LargeStraightTF) {// ���� �Է¹��� ���� ���¶��,
					trueCount = 0; // �ʱ�ȭ
					for (int i = 0; i < 4; i++) {// �������� ����
						if (dice[i] - dice[i + 1] < 0) { // ���������� �ƴ� ���
							tempInt = dice[i + 1]; // �ӽ� ������ �� ����
							dice[i + 1] = dice[i]; // �� ��ȯ
							dice[i] = tempInt; // �ӽ� ������ ����� �� ����
							i = -1; // i�� -1�� �ʱ�ȭ
						}
					}
					for (int i = 0; i < 4; i++) { // 4���� �ֻ����� ������ Ȯ��
						if (dice[i] - dice[i + 1] == 1) { // ��ġ�ϴ� ��� true����++
							trueCount++; // trueCount�� 1 ����
						}
					}
					if (dice[0] == 6 && trueCount == 4) { // 6,5,4,3,2�� ���
						sb.p1LargeStraight = 30; // �����ǿ� 30����
					} else { // ���� ������ �ȵ��� ��� 0�Է�
						sb.p1LargeStraight = 0; // �����ǿ� 0����
					}
					sb.p1LargeStraightTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // �̹� �Է¹��� ���¶��
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ������ ��� ����
				}
			} else if (element.equals("yacht")) { // yacht�� ���
				if (sb.p1YachtTF) {// ���� �Է¹��� ���� ���¶��,
					trueCount = 0; // �ʱ�ȭ
					for (int i = 0; i < 4; i++) {// �������� ����
						if (dice[i] - dice[i + 1] < 0) { // ���������� �ƴ� ���
							tempInt = dice[i + 1]; // �ӽ� ������ �� ����
							dice[i + 1] = dice[i]; // �� ��ȯ
							dice[i] = tempInt; // �ӽ� ������ ����� �� ����
							i = -1; // i�� -1�� �ʱ�ȭ
						}
					}
					for (int i = 0; i < 4; i++) { // 4���� �ֻ����� ������ Ȯ��
						if (dice[i] - dice[i + 1] == 0) { // ��ġ�ϴ� ��� true����++
							trueCount++; // trueCount�� 1 ����
						}
					}
					if (trueCount == 4) { // 4���� �ֻ����� ���� ���
						sb.p1Yacht = 50; // �����ǿ� 50����
					} else { // ���� ������ �ȵ��� ��� 0�Է�
						sb.p1Yacht = 0; // �����ǿ� 0����
					}
					sb.p1YachtTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // �̹� �Է¹��� ���¶��
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ������ ��� ����
				}
			} else { // �Է��� ���� ���� ���
				System.out.println("�Է°� ��ġ�ϴ� �׸��� ã�� �� �����ϴ�. �ٽ� �Է����ּ���."); // �Է°� ��ġ�ϴ� �׸��� ã�� �� ���ٴ� ��¹�
				inputLoop = true; // ���ھ� �Է� ������ ��� ����
			}
		} // inputloop end
		sb.totalRefresh(); // ������ ���� ����
		sb.printScoreBoard(); // ������ ���
		System.out.println("�ԷµǾ����ϴ�. ���ʰ� �Ѿ�ϴ�."); // �ԷµǾ��ٴ� ��¹�
	}

	private static void p2inputScore(int[] dice, ScoreBoard sb) throws IOException { // �÷��̾�2�� ���ھ� �Է� �޼ҵ�
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // ���۸��� ����
		int trueCount = 0; // trueCount ���� ����
		int score = 0; // score ���� ����
		int tempInt = 0; // tempInt ���� ����
		String element; // element ���� ����
		String temp = ""; // temp ���� ����
		boolean inputLoop = true; // inputLoop ���� ����
		boolean tfPoint = false; // tfPoint ���� ����
		while (inputLoop) { // ���ھ� �Է� ����
			sb.printScoreBoard(); // ������ ���
			System.out.println("������ ���� �׸��� �Է��ϼ���. (ex)Aces"); // ������ ���� �׸��� �Է��϶�� ��¹�
			element = br.readLine(); // �Է¹��� ���� element�� ����
			temp=""; // temp �ʱ�ȭ
			StringTokenizer st = new StringTokenizer(element); // element�� ������ �������� ��ūȭ
			element=""; // element �ʱ�ȭ
			while (st.hasMoreTokens()) {// ���� ���� ���� ����� �۾�
				temp += st.nextToken(); // temp�� ��ūȭ�� ���ڿ��� ����
				System.out.println(temp); // temp ���
			}
			for (int i = 0; i < temp.length(); i++) {// ���� �ҹ��ڷ� ��ȯ�ϴ� �۾�
				if (Character.isLetter(temp.charAt(i)) && temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z') {// ����&A~Z����
					// üũ
					element += (char) (temp.charAt(i) + 32); // �ҹ��ڷ� ��ȯ
				} else { // �� ��
					element += temp.charAt(i); // �״�� ����
				}
			} // �������, ������ ����, ���� �ҹ����� ���ڿ� �ϼ�
			if (element.equals("aces")) { // aces�� ���
				if (sb.p2AcesTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
						if (dice[i] == 1) { // �ֻ����� 1�̶��
							trueCount++; // trueCount ����
						}
					}
					sb.p2Aces = trueCount; // trueCount�� p2Aces�� ����
					sb.p2AcesTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // ���� �Է¹��� ���¶��,
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ���� �ݺ�
				}
			} else if (element.equals("deuces")) { // deuces�� ���
				if (sb.p2DeucesTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 2�� �� ������ Ȯ��
						if (dice[i] == 2) { // �ֻ����� 2���
							trueCount++; // trueCount ����
						}
					}
					sb.p2Deuces = trueCount * 2; // trueCount�� p2Deuces�� ����
					sb.p2DeucesTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // ���� �Է¹��� ���¶��,
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ���� �ݺ�
				}
			} else if (element.equals("threes")) { // threes�� ���
				if (sb.p2ThreesTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 3�� �� ������ Ȯ��
						if (dice[i] == 3) { // �ֻ����� 3���
							trueCount++; // trueCount ����
						}
					}
					sb.p2Threes = trueCount * 3; // trueCount�� p2Threes�� ����
					sb.p2ThreesTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // ���� �Է¹��� ���¶��,
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ���� �ݺ�
				}
			} else if (element.equals("fours")) { // fours�� ���
				if (sb.p2FoursTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 4�� �� ������ Ȯ��
						if (dice[i] == 4) { // �ֻ����� 4���
							trueCount++; // trueCount ����
						}
					}
					sb.p2Fours = trueCount * 4; // trueCount�� p2Fours�� ����
					sb.p2FoursTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // ���� �Է¹��� ���¶��,
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ���� �ݺ�
				}
			} else if (element.equals("fives")) { // fives�� ���
				if (sb.p2FivesTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
						if (dice[i] == 5) { // �ֻ����� 5���
							trueCount++; // trueCount ����
						}
					}
					sb.p2Fives = trueCount * 5; // trueCount�� p2Fives�� ����
					sb.p2FivesTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // ���� �Է¹��� ���¶��,
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ���� �ݺ�
				}
			} else if (element.equals("sixes")) { // sixes�� ���
				if (sb.p2SixesTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
						if (dice[i] == 6) { // �ֻ����� 6���
							trueCount++; // trueCount ����
						}
					}
					sb.p2Sixes = trueCount * 6; // trueCount�� p2Sixes�� ����
					sb.p2SixesTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // ���� �Է¹��� ���¶��,
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ���� �ݺ�
				}
			} else if (element.equals("choice")) { // choice�� ���
				if (sb.p2ChoiceTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// ���̽��� �ܼ��� ����
						sb.p2Choice += dice[i]; // �ֻ����� ������ p2Choice�� ����
					}
					sb.p2ChoiceTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // ���� �Է¹��� ���¶��,
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ���� �ݺ�
				}
			} else if (element.equals("4ofakind")) { // 4ofakind�� ���
				if (sb.p2FourOfKindTF) {// ���� �Է¹��� ���� ���¶��,
					// ������ī�ε� ����
					trueCount = 0;// �ʱ�ȭ
					tfPoint = false;// �ʱ�ȭ
					for (int i = 0; i < 4; i++) {// �������� ����
						if (dice[i] - dice[i + 1] < 0) { // ���� �ֻ����� ���������� �ƴ϶��
							tempInt = dice[i + 1]; // tempInt�� �ֻ����� ����
							dice[i + 1] = dice[i]; // �ֻ����� �ֻ����� ����
							dice[i] = tempInt; // �ֻ����� tempInt�� ����
							i = -1; // i�� -1�� �ʱ�ȭ
						}
					}
					for (int i = 0; i < 4; i++) { // 4���� �ֻ����� Ȯ��
						if (dice[i] - dice[i + 1] == 0) {// ��ġ�ϴ� ��� true����++
							trueCount++; // trueCount ����
						} else { // ��ġ���� �ʴ� ���
							if (i == 0 || i == 3) {// �ֻ��� ���� �� �ڰ� ���̰� ���� ��,
								tfPoint = true; // �� ���̳��� �ڸ��� (idx) 0�� 1 �Ǵ� 3�� 4����
							}
						}
					}
					if (trueCount >= 3 && tfPoint) { // trueCount�� 3�̻��̰�, tfPoint�� true���
						for (int num : dice) {// �ֻ��� ���� �� ���� ���ϱ�
							sb.p2FourOfKind += num; // p2FourOfKind�� ����
						}
					} else {// ���� ������ �ȵ��� ��� 0�Է�
						sb.p2FourOfKind = 0; // p2FourOfKind�� 0����
					}
					sb.p2FourOfKindTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // ���� �Է¹��� ���¶��,
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ���� �ݺ�
				}
			} else if (element.equals("fullhouse")) { // fullhouse�� ���
				if (sb.p2FullHouseTF) {// ���� �Է¹��� ���� ���¶��,
					tfPoint = false; // �ʱ�ȭ
					trueCount = 0; // �ʱ�ȭ
					for (int i = 0; i < 4; i++) {// �������� ����
						if (dice[i] - dice[i + 1] < 0) { // ���� �ֻ����� ���������� �ƴ϶��
							tempInt = dice[i + 1]; // tempInt�� �ֻ����� ����
							dice[i + 1] = dice[i]; // �ֻ����� �ֻ����� ����
							dice[i] = tempInt; // �ֻ����� tempInt�� ����
							i = -1; // i�� -1�� �ʱ�ȭ
						}
					}
					for (int i = 0; i < 4; i++) { // 4���� �ֻ����� Ȯ��
						if (dice[i] - dice[i + 1] == 0) {// ��ġ�ϴ� ��� true����++
							trueCount++; // trueCount ����
						} else { // ��ġ���� �ʴ� ���
							if (i == 1 || i == 2) {// �ֻ��� ���� �� �ڰ� ���̰� ���� ��,
								tfPoint = true; // �� ���̳��� �ڸ��� (idx) 1�� 2 �Ǵ� 2�� 3����
							}
						}
					}
					if (trueCount >= 3 && tfPoint) { // trueCount�� 3�̻��̰�, tfPoint�� true���
						for (int num : dice) {// �ֻ��� ���� �� ���� ���ϱ�
							sb.p2FullHouse += num; // p2FullHouse�� ����
						}
					} else {// ���� ������ �ȵ��� ��� 0�Է�
						sb.p2FullHouse = 0; // p2FullHouse�� 0����
					}
					sb.p2FullHouseTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					tfPoint = false;// tf����Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // ���� �Է¹��� ���¶��,
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ���� �ݺ�
				}
			} else if (element.equals("smallstraight")) { // smallstraight�� ���
				if (sb.p2SmallStraightTF) {// ���� �Է¹��� ���� ���¶��,
					trueCount = 0; // �ʱ�ȭ
					for (int i = 0; i < 4; i++) {// �������� ����
						if (dice[i] - dice[i + 1] < 0) { // ���� �ֻ����� ���������� �ƴ϶��
							tempInt = dice[i + 1]; // tempInt�� �ֻ����� ����
							dice[i + 1] = dice[i]; // �ֻ����� �ֻ����� ����
							dice[i] = tempInt; // �ֻ����� tempInt�� ����
							i = -1; // i�� -1�� �ʱ�ȭ
						}
					}
					for (int i = 0; i < 4; i++) { // 4���� �ֻ����� Ȯ��
						if (dice[i] - dice[i + 1] == 1) { // ���̰� 1�� ��� trueCount++
							trueCount++; // trueCount ����
						}
					}
					if (dice[0] == 5 && trueCount == 4) { // ���� 5�� �ְ�, trueCount�� 4���
						sb.p2SmallStraight = 30; // p2SmallStraight�� 30����
					} else { // ���� ������ �ȵ��� ��� 0�Է�
						sb.p2SmallStraight = 0; // p2SmallStraight�� 0����
					}
					sb.p2SmallStraightTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // ���� �Է¹��� ���¶��,
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ���� �ݺ�
				}
			} else if (element.equals("largestraight")) { // largestraight�� ���
				if (sb.p2LargeStraightTF) {// ���� �Է¹��� ���� ���¶��,
					trueCount = 0; // �ʱ�ȭ
					for (int i = 0; i < 4; i++) {// �������� ����
						if (dice[i] - dice[i + 1] < 0) { // ���� �ֻ����� ���������� �ƴ϶��
							tempInt = dice[i + 1]; // tempInt�� �ֻ����� ����
							dice[i + 1] = dice[i]; // �ֻ����� �ֻ����� ����
							dice[i] = tempInt; // �ֻ����� tempInt�� ����
							i = -1; // i�� -1�� �ʱ�ȭ
						}
					}
					for (int i = 0; i < 4; i++) { // 4���� �ֻ����� Ȯ��
						if (dice[i] - dice[i + 1] == 1) { // ���̰� 1�� ��� trueCount++
							trueCount++; // trueCount ����
						}
					}
					if (dice[0] == 6 && trueCount == 4) { // ���� 6�� �ְ�, trueCount�� 4���
						sb.p2LargeStraight = 30; // p2LargeStraight�� 30����
					} else { // ���� ������ �ȵ��� ��� 0�Է�
						sb.p2LargeStraight = 0; // p2LargeStraight�� 0����
					}
					sb.p2LargeStraightTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // ���� �Է¹��� ���¶��,
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ���� �ݺ�
				}
			} else if (element.equals("yacht")) { // yacht�� ���
				if (sb.p2YachtTF) {// ���� �Է¹��� ���� ���¶��,
					trueCount = 0; // �ʱ�ȭ
					for (int i = 0; i < 4; i++) {// �������� ����
						if (dice[i] - dice[i + 1] < 0) { // ���� �ֻ����� ���������� �ƴ϶��
							tempInt = dice[i + 1]; // tempInt�� �ֻ����� ����
							dice[i + 1] = dice[i]; // �ֻ����� �ֻ����� ����
							dice[i] = tempInt; // �ֻ����� tempInt�� ����
							i = -1; // i�� -1�� �ʱ�ȭ
						}
					}
					for (int i = 0; i < 4; i++) { // 4���� �ֻ����� Ȯ��
						if (dice[i] - dice[i + 1] == 0) { // ���̰� 0�� ��� trueCount++
							trueCount++; // trueCount ����
						}
					}
					if (trueCount == 4) { // ���� trueCount�� 4���
						sb.p2Yacht = 50; // p2Yacht�� 50����
					} else { // ���� ������ �ȵ��� ��� 0�Է�
						sb.p2Yacht = 0; // p2Yacht�� 0����
					}
					sb.p2YachtTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else { // ���� �Է¹��� ���¶��,
					System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ��¹�
					inputLoop = true; // ���ھ� �Է� ���� �ݺ�
				}
			} else { // ���� �Է¹��� ���¶��,
				System.out.println("�Է°� ��ġ�ϴ� �׸��� ã�� �� �����ϴ�. �ٽ� �Է����ּ���."); // �Է°� ��ġ�ϴ� �׸��� ã�� �� ���ٴ� ��¹�
				inputLoop = true; // ���ھ� �Է� ���� �ݺ�
			}
		} // inputloop end
		sb.totalRefresh(); // totalRefresh() �޼ҵ� ȣ��
		sb.printScoreBoard(); // printScoreBoard() �޼ҵ� ȣ��
		System.out.println("�ԷµǾ����ϴ�. ���ʰ� �Ѿ�ϴ�."); // �ԷµǾ��ٴ� ��¹�
	}

	private static void gameOverCheck(String p1, String p2, ScoreBoard sb) { // gameOverCheck() �޼ҵ�
		if (sb.gameOverCheck()) { // ���� gameOverCheck() �޼ҵ尡 true�� ��ȯ�Ѵٸ�
			System.out.println("������ �������ϴ�."); // ������ �����ٴ� ��¹�
			if (sb.p1Total > sb.p2Total) { // ���� p1Total�� p2Total���� ũ�ٸ�
				System.out.println(p1 + "�� �¸��Դϴ�."); // p1�� �¸���� ��¹�
				System.exit(0); // ���α׷� ����
			} else if (sb.p1Total < sb.p2Total) { // ���� p1Total�� p2Total���� �۴ٸ�
				System.out.println(p2 + "�� �¸��Դϴ�."); // p2�� �¸���� ��¹�
				System.exit(0); // ���α׷� ����
			} else { // ���� �� ������ ���ٸ�
				System.out.println("���º��Դϴ�."); // ���ºζ�� ��¹�
				System.exit(0); // ���α׷� ����
			}
		}
	}
}
