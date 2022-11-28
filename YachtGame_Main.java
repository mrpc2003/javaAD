package AD_Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

class mainGameConnect{
	static String p1 = "Player1";
	static String p2 = "Player2";
	public void setP1name(String p1name){
		p1 = p1name;
	}
	public void setP2name(String p2name){
		p2 = p2name;
	}
	public static String getP1Name(){
		return p1;
	}
	public static String getP2Name(){
		return p2;
	}
}
class p1player extends mainGameConnect{
	boolean result;
	p1player(boolean isWin){
		this.result = isWin;
	}

}
class p2player extends mainGameConnect{
	boolean result;
	p2player(boolean isWin){
		this.result = isWin;
	}
}

public class YachtGame_Main {

	public static void main(String[] args) throws InterruptedException, IOException {
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

	private static int genRandom() { // 1~6 ������ ������ ���� �����ϴ� �޼ҵ�
		int result = 0; // ������ ���� ������ ����
		result = (int) (Math.random() * 6) + 1; // Math.random() * 6 -> 0~5 ���� +1�� ���� 1~6���� ���� �޼�
		return result; // ������ ���� ��ȯ
	}

	private static void time() throws InterruptedException { // 1�� ���� �޼ҵ�
		TimeUnit.SECONDS.sleep(1); // 1�� ����
	}

	private static void rollDiceAll(int[] dice) throws InterruptedException { // �ֻ��� 5���� ������ �޼ҵ�
		for (int i = 0; i < 5; i++) { // �ֻ��� 5���� ������ �ݺ���
			dice[i] = genRandom(); // �ֻ��� 5���� ������ ���� �ʱ�ȭ
		}
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
		String ans; // ���ġ ���θ� ������ ����
		boolean inputLoop = true; // ���ġ ���θ� �Է¹޴� �ݺ����� ������ ����
		boolean freePath=false; // ����� ���ġ�� �� �ִ� ��ȸ�� ������ ����
		int diceCount = 0; // ���ġ�� �ֻ����� ������ ������ ����
		int rollCount = 2; // ���ġ�� �� �ִ� Ƚ���� ������ ����
		int[] diceIdxBox; // ���ġ�� �ֻ����� �ε����� ������ �迭
		int[] temp; // ���ġ�� �ֻ����� ������ �迭
		while (rollCount > 0) { // ���ġ�� �� �ִ� Ƚ���� 0���� Ŭ �� �ݺ�
			inputLoop = true; // ���ġ ���θ� �Է¹޴� �ݺ����� ������ ������ true�� �ʱ�ȭ
			System.out.println("�ٽ� ������ ���� �ֻ����� �ֽ��ϱ�?(y/n)"); // ���ġ ���θ� ���� ���� ���
			ans = br.readLine(); // ���ġ ���θ� �Է¹���
			if (ans.equals("y") || ans.equals("Y")) { // ���ġ�� ���� ��
				while (inputLoop) { // ���ġ�� �ֻ����� ������ �Է¹޴� �ݺ���
					diceCount = 0; // ���ġ�� �ֻ����� ������ 0���� �ʱ�ȭ
					diceIdxBox = new int[0]; // ���ġ�� �ֻ����� �ε����� ������ �迭�� 0���� �ʱ�ȭ
					System.out.println("������ ���� �ֻ����� ��ȣ�� �������� �����Ͽ� �Է��Ͻÿ�.\n��ɾ� :1 2 3 4 5 / all / (���������)n "); // ���ġ�� �ֻ����� ��ȣ�� �Է¹޴� ���� ���
					ans = br.readLine(); // ���ġ�� �ֻ����� ��ȣ�� �Է¹���
					StringTokenizer st = new StringTokenizer(ans); // �Է¹��� ���ġ�� �ֻ����� ��ȣ�� �������� �����Ͽ� ��ūȭ
					while (st.hasMoreTokens()) { // ��ū�� �������� �� �ݺ�
						if (diceCount > 4) { // ���ġ�� �ֻ����� ������ 5���� �ʰ��� ��
							System.out.println("�ֻ����� ������ �ʰ��� �Է��Դϴ�. �ٽ� �Է����ּ���."); // ���ġ�� �ֻ����� ������ �ʰ��� �Է����� �˸��� ���� ���
							inputLoop = true; // ���ġ�� �ֻ����� ������ �Է¹޴� �ݺ����� ������ ������ true�� �ʱ�ȭ
							break; // ���ġ�� �ֻ����� ������ �Է¹޴� �ݺ����� ����
						}
						ans = st.nextToken(); // ��ū�� ans�� ����
						if (ans.equals("all")) { // ���ġ�� �ֻ����� ��ȣ�� all�� �Է¹޾��� ��
							diceIdxBox = new int[0];// �ٽ� ���� �ε��� �ʱ�ȭ (�߰��� All�� ������ ���, ���� ����)
							rollDiceAll(dice);// �ֻ����� ��� ���ġ
							inputLoop = false; // ���ġ�� �ֻ����� ������ �Է¹޴� �ݺ����� ������ ������ false�� �ʱ�ȭ
							break; // ���ġ�� �ֻ����� ������ �Է¹޴� �ݺ����� ����
						} else if (ans.equals("n")||ans.equals("N")) { // ���ġ�� ����� ��
							System.out.println("�ֻ��� �����⸦ ����մϴ�."); // �ֻ��� �����⸦ ������� �˸��� ���� ���
							freePath=true; // ����� �̵��� �� �ִ� �������� �˸��� ������ true�� �ʱ�ȭ
							inputLoop=false; // ���ġ�� �ֻ����� ������ �Է¹޴� �ݺ����� ������ ������ false�� �ʱ�ȭ
							break; // ���ġ�� �ֻ����� ������ �Է¹޴� �ݺ����� ����
						} else if (ans.length() == 1 && Character.isDigit(ans.charAt(0))) { // ���ġ�� �ֻ����� ��ȣ�� 1~5�� ���ڸ� �Է¹޾��� ��
							temp = new int[diceIdxBox.length + 1]; // ���ġ�� �ֻ����� ��ȣ�� ������ �迭�� ���ġ�� �ֻ����� ���� + 1��ŭ ����
							System.arraycopy(diceIdxBox, 0, temp, 0, diceIdxBox.length); // ���ġ�� �ֻ����� ��ȣ�� ������ �迭�� temp�� ����
							temp[diceIdxBox.length] = Integer.parseInt(ans); // ���ġ�� �ֻ����� ��ȣ�� temp�� ����
							diceIdxBox = temp; // ���ġ�� �ֻ����� ��ȣ�� ������ �迭�� temp�� �ʱ�ȭ
							diceCount++; // ���ġ�� �ֻ����� ������ 1 ����
							inputLoop = false; // ���ġ�� �ֻ����� ������ �Է¹޴� �ݺ����� ������ ������ false�� �ʱ�ȭ
						} else { // ���ġ�� �ֻ����� ��ȣ�� 1~5�� ���ڰ� �ƴ� �ٸ� ���ڸ� �Է¹޾��� ��
							System.out.println("�߸��� �Է��Դϴ�."); // �߸��� �Է����� �˸��� ���� ���
							inputLoop = true; // ���ġ�� �ֻ����� ������ �Է¹޴� �ݺ����� ������ ������ true�� �ʱ�ȭ
							break; // ���ġ�� �ֻ����� ������ �Է¹޴� �ݺ����� ����
						} // if end
					} // token End
					if (!inputLoop&&!freePath) {// token���� ������ �����ٸ�
						dice = rollSelectedDice(dice, diceIdxBox); // ������ �ֻ����� ����
						printRoll(dice); // ���� �ֻ����� ���
						rollCount--; // ���� �� �ִ� Ƚ���� 1 ����
						System.out.println("���� ������ Ƚ��: " + rollCount); // ���� �� �ִ� Ƚ���� ���
					}
					freePath=false; // ����� �̵��� �� �ִ� �������� �˸��� ������ false�� �ʱ�ȭ
				} // loop end
			} // if(y) end
			else if (ans.equals("n") || ans.equals("N")) { // n �Ǵ� N�� �Է¹޾��� ��
				rollCount = 0; // ���� �� �ִ� Ƚ���� 0���� �ʱ�ȭ
			} else { // n �Ǵ� N�� �ƴ� �ٸ� ���ڸ� �Է¹޾��� ��
				System.out.println("�߸��� �Է��Դϴ�. �ٽ� �Է����ּ���."); // �߸��� �Է����� �˸��� ���� ���
			}
			if (rollCount == 0) { // ���� �� �ִ� Ƚ���� 0�� �Ǿ��� ��
				System.out.println("�ֻ��� �����⸦ �����մϴ�."); // �ֻ��� �����⸦ �����Ѵٴ� ���� ���
			}
		} // rollCount end
		return dice; // ���� �ֻ����� ��ȯ
	}

	private static int[] rollSelectedDice(int[] dice, int[] diceIdx) { // ������ �ֻ����� ������ �޼ҵ�
		for (int idx : diceIdx) { // ������ �ֻ����� ������ŭ �ݺ�
			dice[idx - 1] = genRandom(); // ������ �ֻ����� ����
		}
		return dice; // ���� �ֻ����� ��ȯ
	}

	private static void p1inputScore(int[] dice, ScoreBoard sb) throws IOException { // �÷��̾� 1�� ������ ���� �׸��� �Է��ϴ� �޼ҵ�
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // �Է��� �ޱ� ���� BufferedReader ��ü ����
		int trueCount = 0; // �Է¹��� ������ ��ȿ�� �������� Ȯ���ϱ� ���� ����
		int score = 0; // �Է¹��� ������ �����ϱ� ���� ����
		int tempInt = 0; // �Է¹��� ������ �����ϱ� ���� ����
		StringBuilder element; // �Է¹��� ������ �����ϱ� ���� ����
		String temp; // �Է¹��� ������ �����ϱ� ���� ����
		boolean inputLoop = true; // ������ �Է¹޴� �ݺ����� ������ ����
		boolean tfPoint = false; // ������ �Է¹޴� �ݺ����� ������ ����
		while (inputLoop) {// ������� ��� �׸� ���� �б����� �־���ϱ� ������ ������ �����, �밡�� �۾� Start
			sb.printScoreBoard(); // �������� ���
			System.out.println("������ ���� �׸��� �Է��ϼ���. (ex)Aces"); // ������ ���� �׸��� �Է¹޴� ���� ���
			element = new StringBuilder(br.readLine()); // ������ ���� �׸��� �Է¹���
			temp=""; // temp�� �ʱ�ȭ
			StringTokenizer st = new StringTokenizer(element.toString()); // �Է¹��� ������ ������ �������� �и�
			element = new StringBuilder(); // element�� �ʱ�ȭ
			while (st.hasMoreTokens()) {// ���� ���� ���� ����� �۾�
				temp += st.nextToken(); // ������ ������ ���ڸ� temp�� ����
				System.out.println(temp); // temp ���
			}
			for (int i = 0; i < temp.length(); i++) {// ���� �ҹ��ڷ� ��ȯ�ϴ� �۾�
				if (Character.isLetter(temp.charAt(i)) && temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z') {// ����&A~Z����
																											// üũ
					element.append((char) (temp.charAt(i) + 32)); // �빮�ڸ� �ҹ��ڷ� ��ȯ
				} else { // ����&A~Z���̰� �ƴ� ���
					element.append(temp.charAt(i)); // �״�� ����
				}
			} // �������, ������ ����, ���� �ҹ����� ���ڿ� �ϼ�
			switch (element.toString()) {
				case "aces":
					if (sb.p1AcesTF) {// ���� �Է¹��� ���� ���¶��,
						for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
							if (dice[i] == 1) { // �ֻ����� 1�̶��
								trueCount++; // trueCount�� 1 ����
							}
						}
						sb.p1Aces = trueCount; // trueCount�� p1Aces�� ����
						sb.p1AcesTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else { // ���� �Է¹��� ���¶��,
						System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ���
					}
					break;
				case "deuces":  // ���� ����
					if (sb.p1DeucesTF) {// ���� �Է¹��� ���� ���¶��,
						for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
							if (dice[i] == 2) { // �ֻ����� 2�̶��
								trueCount++; // trueCount�� 1 ����
							}
						}
						sb.p1Deuces = trueCount * 2; // trueCount�� p1Deuces�� ����
						sb.p1DeucesTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else { // ���� �Է¹��� ���¶��,
						System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ���
					}
					break;
				case "threes":  // ���� ����
					if (sb.p1ThreesTF) {// ���� �Է¹��� ���� ���¶��,
						for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
							if (dice[i] == 3) { // �ֻ����� 3�̶��
								trueCount++; // trueCount�� 1 ����
							}
						}
						sb.p1Threes = trueCount * 3; // trueCount�� p1Threes�� ����
						sb.p1ThreesTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else { // ���� �Է¹��� ���¶��,
						System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ���
					}
					break;
				case "fours":  // ���� ����
					if (sb.p1FoursTF) {// ���� �Է¹��� ���� ���¶��,
						for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
							if (dice[i] == 4) { // �ֻ����� 4�̶��
								trueCount++; // trueCount�� 1 ����
							}
						}
						sb.p1Fours = trueCount * 4; // trueCount�� p1Fours�� ����
						sb.p1FoursTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else { // ���� �Է¹��� ���¶��,
						System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ���
					}
					break;
				case "fives":  // ���� ����
					if (sb.p1FivesTF) {// ���� �Է¹��� ���� ���¶��,
						for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
							if (dice[i] == 5) { // �ֻ����� 5�̶��
								trueCount++; // trueCount�� 1 ����
							}
						}
						sb.p1Fives = trueCount * 5; // trueCount�� p1Fives�� ����
						sb.p1FivesTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else { // ���� �Է¹��� ���¶��,
						System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ���
					}
					break;
				case "sixes":  // ���� ����
					if (sb.p1SixesTF) {// ���� �Է¹��� ���� ���¶��,
						for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
							if (dice[i] == 6) { // �ֻ����� 6�̶��
								trueCount++; // trueCount�� 1 ����
							}
						}
						sb.p1Sixes = trueCount * 6; // trueCount�� p1Sixes�� ����
						sb.p1SixesTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else { // ���� �Է¹��� ���¶��,
						System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ���
					}
					break;
				case "choice":  // ���� ����
					if (sb.p1ChoiceTF) {// ���� �Է¹��� ���� ���¶��,
						for (int i = 0; i < 5; i++) {// ���̽��� �ܼ��� ����
							sb.p1Choice += dice[i]; // �ֻ����� ������ p1Choice�� ����
						}
						sb.p1ChoiceTF = false;// �Է¹��� ���·� ��ȯ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else { // ���� �Է¹��� ���¶��,
						System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ���
					}
					break;
				case "4ofakind":  // ���� ����
					if (sb.p1FourOfKindTF) {// ���� �Է¹��� ���� ���¶��,

						tfPoint = false;// �ʱ�ȭ
						for (int i = 0; i < 4; i++) {// �������� ����
							if (dice[i] - dice[i + 1] < 0) { // ���� �ֻ����� ���������� �ƴ϶��
								tempInt = dice[i + 1]; // tempInt�� �ֻ����� ����
								dice[i + 1] = dice[i]; // �ֻ����� ������������ ����
								dice[i] = tempInt; // tempInt�� ����� �ֻ����� ����
								i = -1; // i�� -1�� �ʱ�ȭ
							}
						}
						for (int i = 0; i < 4; i++) { // 4���� �ֻ����� ��
							if (dice[i] - dice[i + 1] == 0) {// ��ġ�ϴ� ��� true����++
								trueCount++; // trueCount�� 1 ����
							} else { // ��ġ���� �ʴ� ���
								if (i == 0 || i == 3) {// �ֻ��� ���� �� �ڰ� ���̰� ���� ��,
									tfPoint = true; // �� ���̳��� �ڸ��� (idx) 0�� 1 �Ǵ� 3�� 4����
								}
							}
						}
						if (trueCount >= 3 && tfPoint) { // trueCount�� 3�̻��̰�, tfPoint�� true���
							for (int num : dice) {// �ֻ��� ���� �� ���� ���ϱ�
								sb.p1FourOfKind += num; // p1FourOfKind�� ����
							}
						} else {// ���� ������ �ȵ��� ��� 0�Է�
							sb.p1FourOfKind = 0; // p1FourOfKind�� 0����
						}
						sb.p1FourOfKindTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else { // ���� �Է¹��� ���¶��,
						System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ���
					}
					break;
				case "fullhouse":  // ���� ����
					if (sb.p1FullHouseTF) {// ���� �Է¹��� ���� ���¶��,
						// Ǯ�Ͽ콺 ����
						tfPoint = false; // �ʱ�ȭ
						for (int i = 0; i < 4; i++) {// �������� ����
							if (dice[i] - dice[i + 1] < 0) { // ���� �ֻ����� ���������� �ƴ϶��
								tempInt = dice[i + 1]; // tempInt�� �ֻ����� ����
								dice[i + 1] = dice[i]; // �ֻ����� ������������ ����
								dice[i] = tempInt; // tempInt�� ����� �ֻ����� ����
								i = -1; // i�� -1�� �ʱ�ȭ
							}
						}
						for (int i = 0; i < 4; i++) { // 4���� �ֻ����� ��
							if (dice[i] - dice[i + 1] == 0) {// ��ġ�ϴ� ��� true����++
								trueCount++; // trueCount�� 1 ����
							} else { // ��ġ���� �ʴ� ���
								if (i == 1 || i == 2) {// �ֻ��� ���� �� �ڰ� ���̰� ���� ��,
									tfPoint = true; // �� ���̳��� �ڸ��� (idx) 1�� 2 �Ǵ� 2�� 3����
								}
							}
						}
						if (trueCount >= 3 && tfPoint) { // trueCount�� 3�̻��̰�, tfPoint�� true���
							for (int num : dice) {// �ֻ��� ���� �� ���� ���ϱ�
								sb.p1FullHouse += num; // p1FullHouse�� ����
							}
						} else {// ���� ������ �ȵ��� ��� 0�Է�
							sb.p1FullHouse = 0; // p1FullHouse�� 0����
						}
						sb.p1FullHouseTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						tfPoint = false;// tf����Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else { // ���� �Է¹��� ���¶��,
						System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ���
					}
					break;
				case "smallstraight":  // ���� ����
					if (sb.p1SmallStraightTF) {// ���� �Է¹��� ���� ���¶��,
						trueCount = 0; // ī��Ʈ �ʱ�ȭ
						for (int i = 0; i < 4; i++) {// �������� ����
							if (dice[i] - dice[i + 1] < 0) { // ���� �ֻ����� ���������� �ƴ϶��
								tempInt = dice[i + 1]; // tempInt�� �ֻ����� ����
								dice[i + 1] = dice[i]; // �ֻ����� ������������ ����
								dice[i] = tempInt; // tempInt�� ����� �ֻ����� ����
								i = -1; // i�� -1�� �ʱ�ȭ
							}
						}
						for (int i = 0; i < 4; i++) { // 4���� �ֻ����� ��
							if (dice[i] - dice[i + 1] == 1) { // ��ġ�ϴ� ��� true����++
								trueCount++; // trueCount�� 1 ����
							}
						}
						if (dice[0] == 5 && trueCount == 4) { // ���� 5,4,3,2,1�̶��
							sb.p1SmallStraight = 30; // p1SmallStraight�� 30����
						} else { // ���� ������ �ȵ��� ��� 0�Է�
							sb.p1SmallStraight = 0; // p1SmallStraight�� 0����
						}
						sb.p1SmallStraightTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else { // ���� �Է¹��� ���¶��,
						System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ���
					}
					break;
				case "largestraight":  // ���� ����
					if (sb.p1LargeStraightTF) {// ���� �Է¹��� ���� ���¶��,
						trueCount = 0; // ī��Ʈ �ʱ�ȭ
						for (int i = 0; i < 4; i++) {// �������� ����
							if (dice[i] - dice[i + 1] < 0) { // ���� �ֻ����� ���������� �ƴ϶��
								tempInt = dice[i + 1]; // tempInt�� �ֻ����� ����
								dice[i + 1] = dice[i]; // �ֻ����� ������������ ����
								dice[i] = tempInt; // tempInt�� ����� �ֻ����� ����
								i = -1; // i�� -1�� �ʱ�ȭ
							}
						}
						for (int i = 0; i < 4; i++) { // 4���� �ֻ����� ��
							if (dice[i] - dice[i + 1] == 1) { // ��ġ�ϴ� ��� true����++
								trueCount++; // trueCount�� 1 ����
							}
						}
						if (dice[0] == 6 && trueCount == 4) { // ���� 6,5,4,3,2���
							sb.p1LargeStraight = 30; // p1LargeStraight�� 30����
						} else { // ���� ������ �ȵ��� ��� 0�Է�
							sb.p1LargeStraight = 0; // p1LargeStraight�� 0����
						}
						sb.p1LargeStraightTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else { // ���� �Է¹��� ���¶��,
						System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ���
					}
					break;
				case "yacht":  // ���� ����
					if (sb.p1YachtTF) {// ���� �Է¹��� ���� ���¶��,
						trueCount = 0; // ī��Ʈ �ʱ�ȭ
						for (int i = 0; i < 4; i++) {// �������� ����
							if (dice[i] - dice[i + 1] < 0) { // ���� �ֻ����� ���������� �ƴ϶��
								tempInt = dice[i + 1]; // tempInt�� �ֻ����� ����
								dice[i + 1] = dice[i]; // �ֻ����� ������������ ����
								dice[i] = tempInt; // tempInt�� ����� �ֻ����� ����
								i = -1; // i�� -1�� �ʱ�ȭ
							}
						}
						for (int i = 0; i < 4; i++) { // 4���� �ֻ����� ��
							if (dice[i] - dice[i + 1] == 0) { // ��ġ�ϴ� ��� true����++
								trueCount++; // trueCount�� 1 ����
							}
						}
						if (trueCount == 4) { // ���� 4���� �ֻ����� ��ġ�Ѵٸ�
							sb.p1Yacht = 50; // p1Yacht�� 50����
						} else { // ���� ������ �ȵ��� ��� 0�Է�
							sb.p1Yacht = 0; // p1Yacht�� 0����
						}
						sb.p1YachtTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else { // ���� �Է¹��� ���¶��,
						System.out.println("�̹� �Է��� �׸��Դϴ�."); // �̹� �Է��� �׸��̶�� ���
					}
					break;
				default:  // ���� �Է¹��� ���� ���ٸ�,
					System.out.println("�Է°� ��ġ�ϴ� �׸��� ã�� �� �����ϴ�. �ٽ� �Է����ּ���."); // �Է°� ��ġ�ϴ� �׸��� ã�� �� ���ٰ� ���
					break;
			}
		} // inputloop end
		sb.totalRefresh(); // totalRefresh() �޼ҵ� ȣ��
		sb.printScoreBoard(); // printScoreBoard() �޼ҵ� ȣ��
		System.out.println("�ԷµǾ����ϴ�. ���ʰ� �Ѿ�ϴ�."); // �ԷµǾ��ٰ� ���
	}
	private static void p2inputScore(int[] dice, ScoreBoard sb) throws IOException { // �÷��̾� 2�� ������ ���� �׸��� �Է��ϴ� �޼ҵ�
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // BufferedReader ��ü ����
		int trueCount = 0; // trueCount ���� ���� �� �ʱ�ȭ
		int score = 0; // score ���� ���� �� �ʱ�ȭ
		int tempInt = 0; // tempInt ���� ���� �� �ʱ�ȭ
		StringBuilder element; // element ���� ����
		String temp = ""; // temp ���� ���� �� �ʱ�ȭ
		boolean inputLoop = true; // inputLoop ���� ���� �� �ʱ�ȭ
		boolean tfPoint = false; // tfPoint ���� ���� �� �ʱ�ȭ
		while (inputLoop) {// ������� ��� �׸� ���� �б����� �־���ϱ� ������ ������ �����, �밡�� �۾� Start
			sb.printScoreBoard(); // printScoreBoard() �޼ҵ� ȣ��
			System.out.println("������ ���� �׸��� �Է��ϼ���. (ex)Aces"); // ������ ���� �׸��� �Է��϶�� ���
			element = new StringBuilder(br.readLine());
			temp="";
			StringTokenizer st = new StringTokenizer(element.toString());
			element = new StringBuilder();
			while (st.hasMoreTokens()) {// ���� ���� ���� ����� �۾�
				temp += st.nextToken();
				System.out.println(temp);
			}
			for (int i = 0; i < temp.length(); i++) {// ���� �ҹ��ڷ� ��ȯ�ϴ� �۾�
				if (Character.isLetter(temp.charAt(i)) && temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z') {// ����&A~Z����
																											// üũ
					element.append((char) (temp.charAt(i) + 32));
				} else {
					element.append(temp.charAt(i));
				}
			} // �������, ������ ����, ���� �ҹ����� ���ڿ� �ϼ�
			switch (element.toString()) {
				case "aces":
					if (sb.p2AcesTF) {// ���� �Է¹��� ���� ���¶��,
						for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
							if (dice[i] == 1) {
								trueCount++;
							}
						}
						sb.p2Aces = trueCount;
						sb.p2AcesTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else {
						System.out.println("�̹� �Է��� �׸��Դϴ�.");
					}
					break;
				case "deuces":
					if (sb.p2DeucesTF) {// ���� �Է¹��� ���� ���¶��,
						for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
							if (dice[i] == 2) {
								trueCount++;
							}
						}
						sb.p2Deuces = trueCount * 2;
						sb.p2DeucesTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else {
						System.out.println("�̹� �Է��� �׸��Դϴ�.");
					}
					break;
				case "threes":
					if (sb.p2ThreesTF) {// ���� �Է¹��� ���� ���¶��,
						for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
							if (dice[i] == 3) {
								trueCount++;
							}
						}
						sb.p2Threes = trueCount * 3;
						sb.p2ThreesTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else {
						System.out.println("�̹� �Է��� �׸��Դϴ�.");
					}
					break;
				case "fours":
					if (sb.p2FoursTF) {// ���� �Է¹��� ���� ���¶��,
						for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
							if (dice[i] == 4) {
								trueCount++;
							}
						}
						sb.p2Fours = trueCount * 4;
						sb.p2FoursTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else {
						System.out.println("�̹� �Է��� �׸��Դϴ�.");
					}
					break;
				case "fives":
					if (sb.p2FivesTF) {// ���� �Է¹��� ���� ���¶��,
						for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
							if (dice[i] == 5) {
								trueCount++;
							}
						}
						sb.p2Fives = trueCount * 5;
						sb.p2FivesTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else {
						System.out.println("�̹� �Է��� �׸��Դϴ�.");
					}
					break;
				case "sixes":
					if (sb.p2SixesTF) {// ���� �Է¹��� ���� ���¶��,
						for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
							if (dice[i] == 6) {
								trueCount++;
							}
						}
						sb.p2Sixes = trueCount * 6;
						sb.p2SixesTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else {
						System.out.println("�̹� �Է��� �׸��Դϴ�.");
					}
					break;
				case "choice":
					if (sb.p2ChoiceTF) {// ���� �Է¹��� ���� ���¶��,
						for (int i = 0; i < 5; i++) {// ���̽��� �ܼ��� ����
							sb.p2Choice += dice[i];
						}
						sb.p2ChoiceTF = false;// �Է¹��� ���·� ��ȯ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else {
						System.out.println("�̹� �Է��� �׸��Դϴ�.");
					}
					break;
				case "4ofakind":
					if (sb.p2FourOfKindTF) {// ���� �Է¹��� ���� ���¶��,
						tfPoint = false;// �ʱ�ȭ
						for (int i = 0; i < 4; i++) {// �������� ����
							if (dice[i] - dice[i + 1] < 0) {
								tempInt = dice[i + 1];
								dice[i + 1] = dice[i];
								dice[i] = tempInt;
								i = -1;
							}
						}
						for (int i = 0; i < 4; i++) {
							if (dice[i] - dice[i + 1] == 0) {// ��ġ�ϴ� ��� true����++
								trueCount++;
							} else {
								if (i == 0 || i == 3) {// �ֻ��� ���� �� �ڰ� ���̰� ���� ��,
									tfPoint = true; // �� ���̳��� �ڸ��� (idx) 0�� 1 �Ǵ� 3�� 4����
								}
							}
						}
						if (trueCount >= 3 && tfPoint) {
							for (int num : dice) {// �ֻ��� ���� �� ���� ���ϱ�
								sb.p2FourOfKind += num;
							}
						} else {// ���� ������ �ȵ��� ��� 0�Է�
							sb.p2FourOfKind = 0;
						}
						sb.p2FourOfKindTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else {
						System.out.println("�̹� �Է��� �׸��Դϴ�.");
					}
					break;
				case "fullhouse":
					if (sb.p2FullHouseTF) {// ���� �Է¹��� ���� ���¶��,
						tfPoint = false;
						for (int i = 0; i < 4; i++) {// �������� ����
							if (dice[i] - dice[i + 1] < 0) {
								tempInt = dice[i + 1];
								dice[i + 1] = dice[i];
								dice[i] = tempInt;
								i = -1;
							}
						}
						for (int i = 0; i < 4; i++) {
							if (dice[i] - dice[i + 1] == 0) {// ��ġ�ϴ� ��� true����++
								trueCount++;
							} else {
								if (i == 1 || i == 2) {// �ֻ��� ���� �� �ڰ� ���̰� ���� ��,
									tfPoint = true; // �� ���̳��� �ڸ��� (idx) 1�� 2 �Ǵ� 2�� 3����
								}
							}
						}
						if (trueCount >= 3 && tfPoint) {
							for (int num : dice) {// �ֻ��� ���� �� ���� ���ϱ�
								sb.p2FullHouse += num;
							}
						} else {// ���� ������ �ȵ��� ��� 0�Է�
							sb.p2FullHouse = 0;
						}
						sb.p2FullHouseTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						tfPoint = false;// tf����Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else {
						System.out.println("�̹� �Է��� �׸��Դϴ�.");
					}
					break;
				case "smallstraight":
					if (sb.p2SmallStraightTF) {// ���� �Է¹��� ���� ���¶��,
						for (int i = 0; i < 4; i++) {// �������� ����
							if (dice[i] - dice[i + 1] < 0) {
								tempInt = dice[i + 1];
								dice[i + 1] = dice[i];
								dice[i] = tempInt;
								i = -1;
							}
						}
						for (int i = 0; i < 4; i++) {
							if (dice[i] - dice[i + 1] == 1) {
								trueCount++;
							}
						}
						if (dice[0] == 5 && trueCount == 4) {
							sb.p2SmallStraight = 30;
						} else {
							sb.p2SmallStraight = 0;
						}
						sb.p2SmallStraightTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else {
						System.out.println("�̹� �Է��� �׸��Դϴ�.");
					}
					break;
				case "largestraight":
					if (sb.p2LargeStraightTF) {// ���� �Է¹��� ���� ���¶��,
						for (int i = 0; i < 4; i++) {// �������� ����
							if (dice[i] - dice[i + 1] < 0) {
								tempInt = dice[i + 1];
								dice[i + 1] = dice[i];
								dice[i] = tempInt;
								i = -1;
							}
						}
						for (int i = 0; i < 4; i++) {
							if (dice[i] - dice[i + 1] == 1) {
								trueCount++;
							}
						}
						if (dice[0] == 6 && trueCount == 4) {
							sb.p2LargeStraight = 30;
						} else {
							sb.p2LargeStraight = 0;
						}
						sb.p2LargeStraightTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else {
						System.out.println("�̹� �Է��� �׸��Դϴ�.");
					}
					break;
				case "yacht":
					if (sb.p2YachtTF) {// ���� �Է¹��� ���� ���¶��,
						for (int i = 0; i < 4; i++) {// �������� ����
							if (dice[i] - dice[i + 1] < 0) {
								tempInt = dice[i + 1];
								dice[i + 1] = dice[i];
								dice[i] = tempInt;
								i = -1;
							}
						}
						for (int i = 0; i < 4; i++) {
							if (dice[i] - dice[i + 1] == 0) {
								trueCount++;
							}
						}
						if (trueCount == 4) {
							sb.p2Yacht = 50;
						} else {
							sb.p2Yacht = 0;
						}
						sb.p2YachtTF = false;// �Է¹��� ���·� ��ȯ
						trueCount = 0;// ī��Ʈ �ʱ�ȭ
						inputLoop = false;// ���ھ� �Է� ���� Ż��
					} else {
						System.out.println("�̹� �Է��� �׸��Դϴ�.");
					}
					break;
				default:
					System.out.println("�Է°� ��ġ�ϴ� �׸��� ã�� �� �����ϴ�. �ٽ� �Է����ּ���.");
					break;
			}
		} // inputloop end
		sb.totalRefresh();
		sb.printScoreBoard();
		System.out.println("�ԷµǾ����ϴ�. ���ʰ� �Ѿ�ϴ�.");
	}

	private static void gameOverCheck(String p1, String p2, ScoreBoard sb) { // ���� ���� ���� Ȯ��
		if (sb.gameOverCheck()) {
			System.out.println("������ �������ϴ�.");
			if (sb.p1Total > sb.p2Total) {
				System.out.println(p1 + "�� �¸��Դϴ�.");
				System.exit(0);
			} else if (sb.p1Total < sb.p2Total) {
				System.out.println(p2 + "�� �¸��Դϴ�.");
				System.exit(0);
			} else {
				System.out.println("���º��Դϴ�.");
				System.exit(0);
			}
		}
	}
}
