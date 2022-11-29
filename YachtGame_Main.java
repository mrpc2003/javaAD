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
	static boolean p1isWin = false;
	public static Boolean p1Result(){
		return p1isWin;
	}

}
class p2player extends mainGameConnect{
	static boolean p2isWin = false;
	public static Boolean p2Result(){
		return p2isWin;
	}
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
	private static int genRandom() {
		int result = 0;
		result = (int) (Math.random() * 6) + 1;
		return result;
	}

	private static void time() throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
	}

	private static int[] rollDiceAll(int[] dice) throws InterruptedException {
		for (int i = 0; i < 5; i++) {
			dice[i] = genRandom();
		}
		return dice;
	}

	private static void printRoll(int[] dice) throws InterruptedException {
		for (int i = 3; i >= 0; i--) {
			time();
			if (i == 3) {
				System.out.print("Dice");
			} else if (i > 0) {
				System.out.print(".");
			} else {
				System.out.println("roll-!");
			}
		}
		time();
		System.out.println(Arrays.toString(dice));

	}

	private static int[] reRollDice(int[] dice) throws IOException, InterruptedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String ans;
		boolean inputLoop = true;
		boolean freePath=false;
		int diceCount = 0;
		int rollCount = 2;
		int[] diceIdxBox;
		int[] temp;
		while (rollCount > 0) {
			inputLoop = true;
			System.out.println("�ٽ� ������ ���� �ֻ����� �ֽ��ϱ�?(y/n)");
			ans = br.readLine();
			if (ans.equals("y") || ans.equals("Y")) {
				while (inputLoop) {
					diceCount = 0;
					diceIdxBox = new int[0];
					System.out.println("������ ���� �ֻ����� ��ȣ�� �������� �����Ͽ� �Է��Ͻÿ�.\n��ɾ� :1 2 3 4 5 / all / (���������)n ");
					ans = br.readLine();
					StringTokenizer st = new StringTokenizer(ans);
					while (st.hasMoreTokens()) {
						if (diceCount > 4) {
							System.out.println("�ֻ����� ������ �ʰ��� �Է��Դϴ�. �ٽ� �Է����ּ���.");
							inputLoop = true;
							break;
						}
						ans = st.nextToken();
						if (ans.equals("all")) {
							diceIdxBox = new int[0];// �ٽ� ���� �ε��� �ʱ�ȭ (�߰��� All�� ������ ���, ���� ����)
							dice = rollDiceAll(dice);
							inputLoop = false;
							break;
						} else if (ans.equals("n")||ans.equals("N")) {
							System.out.println("�ֻ��� �����⸦ ����մϴ�.");
							freePath=true;
							inputLoop=false;
							break;
						} else if (ans.length() == 1 && Character.isDigit(ans.charAt(0))) {
							temp = new int[diceIdxBox.length + 1];
							System.arraycopy(diceIdxBox, 0, temp, 0, diceIdxBox.length);
							temp[diceIdxBox.length] = Integer.parseInt(ans);
							diceIdxBox = temp;
							diceCount++;
							inputLoop = false;
						} else {
							System.out.println("�߸��� �Է��Դϴ�.");
							inputLoop = true;
							break;
						} // if end
					} // token End
					if (!inputLoop&&!freePath) {// token���� ������ �����ٸ�
						dice = rollSelectedDice(dice, diceIdxBox);
						printRoll(dice);
						rollCount--;
						System.out.println("���� ������ Ƚ��: " + rollCount);
					}
					freePath=false;
				} // loop end
			} // if(y) end
			else if (ans.equals("n") || ans.equals("N")) {
				rollCount = 0;
				inputLoop = false;
			} else {
				System.out.println("�߸��� �Է��Դϴ�. �ٽ� �Է����ּ���.");
				inputLoop = true;
			}
			if (rollCount == 0) {
				System.out.println("�ֻ��� �����⸦ �����մϴ�.");
			}
		} // rollCount end
		return dice;
	}

	private static int[] rollSelectedDice(int[] dice, int[] diceIdx) {
		for (int i = 0; i < diceIdx.length; i++) {
			dice[diceIdx[i] - 1] = genRandom();
		}
		return dice;
	}

	private static void p1inputScore(int[] dice, ScoreBoard sb) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int trueCount = 0;
		int score = 0;
		int tempInt = 0;
		String element;
		String temp;
		boolean inputLoop = true;
		boolean tfPoint = false;
		while (inputLoop) {// ������� ��� �׸� ���� �б����� �־���ϱ� ������ ������ �����, �밡�� �۾� Start
			sb.printScoreBoard();
			System.out.println("������ ���� �׸��� �Է��ϼ���. (ex)Aces");
			element = br.readLine();
			temp="";
			StringTokenizer st = new StringTokenizer(element);
			element="";
			while (st.hasMoreTokens()) {// ���� ���� ���� ����� �۾�
				temp += st.nextToken();
				System.out.println(temp);
			}
			for (int i = 0; i < temp.length(); i++) {// ���� �ҹ��ڷ� ��ȯ�ϴ� �۾�
				if (Character.isLetter(temp.charAt(i)) && temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z') {// ����&A~Z����
					// üũ
					element += (char) (temp.charAt(i) + 32);
				} else {
					element += temp.charAt(i);
				}
			} // �������, ������ ����, ���� �ҹ����� ���ڿ� �ϼ�
			if (element.equals("aces")) {
				if (sb.p1AcesTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
						if (dice[i] == 1) {
							trueCount++;
						}
					}
					sb.p1Aces = trueCount;
					sb.p1AcesTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else {
					System.out.println("�̹� �Է��� �׸��Դϴ�.");
					inputLoop = true;
				}
			} else if (element.equals("deuces")) {
				if (sb.p1DeucesTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
						if (dice[i] == 2) {
							trueCount++;
						}
					}
					sb.p1Deuces = trueCount * 2;
					sb.p1DeucesTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else {
					System.out.println("�̹� �Է��� �׸��Դϴ�.");
					inputLoop = true;
				}
			} else if (element.equals("threes")) {
				if (sb.p1ThreesTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
						if (dice[i] == 3) {
							trueCount++;
						}
					}
					sb.p1Threes = trueCount * 3;
					sb.p1ThreesTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else {
					System.out.println("�̹� �Է��� �׸��Դϴ�.");
					inputLoop = true;
				}
			} else if (element.equals("fours")) {
				if (sb.p1FoursTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
						if (dice[i] == 4) {
							trueCount++;
						}
					}
					sb.p1Fours = trueCount * 4;
					sb.p1FoursTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else {
					System.out.println("�̹� �Է��� �׸��Դϴ�.");
					inputLoop = true;
				}
			} else if (element.equals("fives")) {
				if (sb.p1FivesTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
						if (dice[i] == 5) {
							trueCount++;
						}
					}
					sb.p1Fives = trueCount * 5;
					sb.p1FivesTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else {
					System.out.println("�̹� �Է��� �׸��Դϴ�.");
					inputLoop = true;
				}
			} else if (element.equals("sixes")) {
				if (sb.p1SixesTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// �ֻ������� 1�� �� ������ Ȯ��
						if (dice[i] == 6) {
							trueCount++;
						}
					}
					sb.p1Sixes = trueCount * 6;
					sb.p1SixesTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else {
					System.out.println("�̹� �Է��� �׸��Դϴ�.");
					inputLoop = true;
				}
			} else if (element.equals("choice")) {
				if (sb.p1ChoiceTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// ���̽��� �ܼ��� ����
						sb.p1Choice += dice[i];
					}
					sb.p1ChoiceTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else {
					System.out.println("�̹� �Է��� �׸��Դϴ�.");
					inputLoop = true;
				}
			} else if (element.equals("4ofakind")) {
				if (sb.p1FourOfKindTF) {// ���� �Է¹��� ���� ���¶��,
					// ������ī�ε� ����
					trueCount = 0;// �ʱ�ȭ
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
							sb.p1FourOfKind += num;
						}
					} else {// ���� ������ �ȵ��� ��� 0�Է�
						sb.p1FourOfKind = 0;
					}
					sb.p1FourOfKindTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else {
					System.out.println("�̹� �Է��� �׸��Դϴ�.");
					inputLoop = true;
				}
			} else if (element.equals("fullhouse")) {
				if (sb.p1FullHouseTF) {// ���� �Է¹��� ���� ���¶��,
					// Ǯ�Ͽ콺 ����
					tfPoint = false;
					trueCount = 0;
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
							sb.p1FullHouse += num;
						}
					} else {// ���� ������ �ȵ��� ��� 0�Է�
						sb.p1FullHouse = 0;
					}
					sb.p1FullHouseTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					tfPoint = false;// tf����Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else {
					System.out.println("�̹� �Է��� �׸��Դϴ�.");
					inputLoop = true;
				}
			} else if (element.equals("smallstraight")) {
				if (sb.p1SmallStraightTF) {// ���� �Է¹��� ���� ���¶��,
					trueCount = 0;
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
						sb.p1SmallStraight = 30;
					} else {
						sb.p1SmallStraight = 0;
					}
					sb.p1SmallStraightTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else {
					System.out.println("�̹� �Է��� �׸��Դϴ�.");
					inputLoop = true;
				}
			} else if (element.equals("largestraight")) {
				if (sb.p1LargeStraightTF) {// ���� �Է¹��� ���� ���¶��,
					trueCount = 0;
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
						sb.p1LargeStraight = 30;
					} else {
						sb.p1LargeStraight = 0;
					}
					sb.p1LargeStraightTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else {
					System.out.println("�̹� �Է��� �׸��Դϴ�.");
					inputLoop = true;
				}
			} else if (element.equals("yacht")) {
				if (sb.p1YachtTF) {// ���� �Է¹��� ���� ���¶��,
					trueCount = 0;
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
						sb.p1Yacht = 50;
					} else {
						sb.p1Yacht = 0;
					}
					sb.p1YachtTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else {
					System.out.println("�̹� �Է��� �׸��Դϴ�.");
					inputLoop = true;
				}
			} else {
				System.out.println("�Է°� ��ġ�ϴ� �׸��� ã�� �� �����ϴ�. �ٽ� �Է����ּ���.");
				inputLoop = true;
			}
		} // inputloop end
		sb.totalRefresh();
		sb.printScoreBoard();
		System.out.println("�ԷµǾ����ϴ�. ���ʰ� �Ѿ�ϴ�.");
	}

	private static void p2inputScore(int[] dice, ScoreBoard sb) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int trueCount = 0;
		int score = 0;
		int tempInt = 0;
		String element;
		String temp = "";
		boolean inputLoop = true;
		boolean tfPoint = false;
		while (inputLoop) {// ������� ��� �׸� ���� �б����� �־���ϱ� ������ ������ �����, �밡�� �۾� Start
			sb.printScoreBoard();
			System.out.println("������ ���� �׸��� �Է��ϼ���. (ex)Aces");
			element = br.readLine();
			temp="";
			StringTokenizer st = new StringTokenizer(element);
			element="";
			while (st.hasMoreTokens()) {// ���� ���� ���� ����� �۾�
				temp += st.nextToken();
				System.out.println(temp);
			}
			for (int i = 0; i < temp.length(); i++) {// ���� �ҹ��ڷ� ��ȯ�ϴ� �۾�
				if (Character.isLetter(temp.charAt(i)) && temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z') {// ����&A~Z����
					// üũ
					element += (char) (temp.charAt(i) + 32);
				} else {
					element += temp.charAt(i);
				}
			} // �������, ������ ����, ���� �ҹ����� ���ڿ� �ϼ�
			if (element.equals("aces")) {
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
					inputLoop = true;
				}
			} else if (element.equals("deuces")) {
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
					inputLoop = true;
				}
			} else if (element.equals("threes")) {
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
					inputLoop = true;
				}
			} else if (element.equals("fours")) {
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
					inputLoop = true;
				}
			} else if (element.equals("fives")) {
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
					inputLoop = true;
				}
			} else if (element.equals("sixes")) {
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
					inputLoop = true;
				}
			} else if (element.equals("choice")) {
				if (sb.p2ChoiceTF) {// ���� �Է¹��� ���� ���¶��,
					for (int i = 0; i < 5; i++) {// ���̽��� �ܼ��� ����
						sb.p2Choice += dice[i];
					}
					sb.p2ChoiceTF = false;// �Է¹��� ���·� ��ȯ
					trueCount = 0;// ī��Ʈ �ʱ�ȭ
					inputLoop = false;// ���ھ� �Է� ���� Ż��
				} else {
					System.out.println("�̹� �Է��� �׸��Դϴ�.");
					inputLoop = true;
				}
			} else if (element.equals("4ofakind")) {
				if (sb.p2FourOfKindTF) {// ���� �Է¹��� ���� ���¶��,
					// ������ī�ε� ����
					trueCount = 0;// �ʱ�ȭ
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
					inputLoop = true;
				}
			} else if (element.equals("fullhouse")) {
				if (sb.p2FullHouseTF) {// ���� �Է¹��� ���� ���¶��,
					// Ǯ�Ͽ콺 ����
					tfPoint = false;
					trueCount = 0;
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
					inputLoop = true;
				}
			} else if (element.equals("smallstraight")) {
				if (sb.p2SmallStraightTF) {// ���� �Է¹��� ���� ���¶��,
					trueCount = 0;
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
					inputLoop = true;
				}
			} else if (element.equals("largestraight")) {
				if (sb.p2LargeStraightTF) {// ���� �Է¹��� ���� ���¶��,
					trueCount = 0;
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
					inputLoop = true;
				}
			} else if (element.equals("yacht")) {
				if (sb.p2YachtTF) {// ���� �Է¹��� ���� ���¶��,
					trueCount = 0;
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
					inputLoop = true;
				}
			} else {
				System.out.println("�Է°� ��ġ�ϴ� �׸��� ã�� �� �����ϴ�. �ٽ� �Է����ּ���.");
				inputLoop = true;
			}
		} // inputloop end
		sb.totalRefresh();
		sb.printScoreBoard();
		System.out.println("�ԷµǾ����ϴ�. ���ʰ� �Ѿ�ϴ�.");
	}

	private static void gameOverCheck(String p1, String p2, ScoreBoard sb) {
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
