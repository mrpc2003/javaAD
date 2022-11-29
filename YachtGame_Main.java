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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위한 BufferedReader
		ScoreBoard sb = new ScoreBoard(); // 점수판
		String p1; // 플레이어1의 이름
		String p2; // 플레이어2의 이름
		String ans = ""; // 플레이어가 입력한 답
		String temp = ""; // 플레이어가 입력한 답을 임시로 저장
		int[] dice = new int[5]; // 주사위 5개를 저장할 배열
		System.out.println("-Yacht Dice-"); // 게임 이름 출력
		p1 = p1player.getP1Name(); // 플레이어1의 이름 가져오기
		p2 = p2player.getP2Name(); // 플레이어2의 이름 가져오기
		System.out.println(p1 + "님과 " + p2 + "님의 Yacht Dice를 시작합니다."); // 플레이어1과 플레이어2의 이름 출력
		for (int i = 3; i > 0; i--) { // 3초 카운트 다운
			time(); // 1초 쉬는 메소드
			System.out.print("."); // . 출력
		}
		time(); // 1초 쉬는 메소드
		System.out.println("game Start!"); // 게임 시작 출력
		time(); // 1초 쉬는 메소드
		while (true) { // 무한 반복
			System.out.println(p1 + "님의 차례입니다. 주사위를 던집니다."); // 플레이어1의 차례 출력
			rollDiceAll(dice); // 주사위 5개를 던지는 메소드
			printRoll(dice); // 주사위 5개를 출력하는 메소드
			time(); // 1초 쉬는 메소드
			dice = reRollDice(dice); // 주사위를 재배치하는 메소드
			p1inputScore(dice, sb); // 플레이어1의 점수를 입력하는 메소드
			time(); // 1초 쉬는 메소드
			System.out.println(p2 + "님의 차례입니다. 주사위를 던집니다."); // 플레이어2의 차례 출력
			rollDiceAll(dice); // 주사위 5개를 던지는 메소드
			printRoll(dice); // 주사위 5개를 출력하는 메소드
			time(); // 1초 쉬는 메소드
			dice = reRollDice(dice); // 주사위를 재배치하는 메소드
			p2inputScore(dice, sb); // 플레이어2의 점수를 입력하는 메소드
			gameOverCheck(p1, p2, sb); // 게임이 끝났는지 확인하는 메소드

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
			System.out.println("다시 던지고 싶은 주사위가 있습니까?(y/n)");
			ans = br.readLine();
			if (ans.equals("y") || ans.equals("Y")) {
				while (inputLoop) {
					diceCount = 0;
					diceIdxBox = new int[0];
					System.out.println("던지고 싶은 주사위의 번호를 공백으로 구분하여 입력하시오.\n명령어 :1 2 3 4 5 / all / (던지기취소)n ");
					ans = br.readLine();
					StringTokenizer st = new StringTokenizer(ans);
					while (st.hasMoreTokens()) {
						if (diceCount > 4) {
							System.out.println("주사위의 개수를 초과한 입력입니다. 다시 입력해주세요.");
							inputLoop = true;
							break;
						}
						ans = st.nextToken();
						if (ans.equals("all")) {
							diceIdxBox = new int[0];// 다시 던질 인덱스 초기화 (중간에 All이 나오는 경우, 리롤 차단)
							dice = rollDiceAll(dice);
							inputLoop = false;
							break;
						} else if (ans.equals("n")||ans.equals("N")) {
							System.out.println("주사위 던지기를 취소합니다.");
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
							System.out.println("잘못된 입력입니다.");
							inputLoop = true;
							break;
						} // if end
					} // token End
					if (!inputLoop&&!freePath) {// token에서 문제가 없었다면
						dice = rollSelectedDice(dice, diceIdxBox);
						printRoll(dice);
						rollCount--;
						System.out.println("리롤 가능한 횟수: " + rollCount);
					}
					freePath=false;
				} // loop end
			} // if(y) end
			else if (ans.equals("n") || ans.equals("N")) {
				rollCount = 0;
				inputLoop = false;
			} else {
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				inputLoop = true;
			}
			if (rollCount == 0) {
				System.out.println("주사위 던지기를 종료합니다.");
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
		while (inputLoop) {// 여기부터 모든 항목에 대한 분기점을 넣어야하기 때문에 굉장히 길어짐, 노가다 작업 Start
			sb.printScoreBoard();
			System.out.println("점수를 넣을 항목을 입력하세요. (ex)Aces");
			element = br.readLine();
			temp="";
			StringTokenizer st = new StringTokenizer(element);
			element="";
			while (st.hasMoreTokens()) {// 문자 사이 공백 지우는 작업
				temp += st.nextToken();
				System.out.println(temp);
			}
			for (int i = 0; i < temp.length(); i++) {// 전부 소문자로 변환하는 작업
				if (Character.isLetter(temp.charAt(i)) && temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z') {// 문자&A~Z사이
					// 체크
					element += (char) (temp.charAt(i) + 32);
				} else {
					element += temp.charAt(i);
				}
			} // 여기까지, 공백이 없는, 전부 소문자인 문자열 완성
			if (element.equals("aces")) {
				if (sb.p1AcesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 1) {
							trueCount++;
						}
					}
					sb.p1Aces = trueCount;
					sb.p1AcesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("deuces")) {
				if (sb.p1DeucesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 2) {
							trueCount++;
						}
					}
					sb.p1Deuces = trueCount * 2;
					sb.p1DeucesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("threes")) {
				if (sb.p1ThreesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 3) {
							trueCount++;
						}
					}
					sb.p1Threes = trueCount * 3;
					sb.p1ThreesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("fours")) {
				if (sb.p1FoursTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 4) {
							trueCount++;
						}
					}
					sb.p1Fours = trueCount * 4;
					sb.p1FoursTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("fives")) {
				if (sb.p1FivesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 5) {
							trueCount++;
						}
					}
					sb.p1Fives = trueCount * 5;
					sb.p1FivesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("sixes")) {
				if (sb.p1SixesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 6) {
							trueCount++;
						}
					}
					sb.p1Sixes = trueCount * 6;
					sb.p1SixesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("choice")) {
				if (sb.p1ChoiceTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 초이스는 단순한 총합
						sb.p1Choice += dice[i];
					}
					sb.p1ChoiceTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("4ofakind")) {
				if (sb.p1FourOfKindTF) {// 만약 입력받지 않은 상태라면,
					// 포오브카인드 조건
					trueCount = 0;// 초기화
					tfPoint = false;// 초기화
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
						if (dice[i] - dice[i + 1] < 0) {
							tempInt = dice[i + 1];
							dice[i + 1] = dice[i];
							dice[i] = tempInt;
							i = -1;
						}
					}
					for (int i = 0; i < 4; i++) {
						if (dice[i] - dice[i + 1] == 0) {// 일치하는 경우 true점수++
							trueCount++;
						} else {
							if (i == 0 || i == 3) {// 주사위 눈에 앞 뒤가 차이가 있을 때,
								tfPoint = true; // 그 차이나는 자리가 (idx) 0과 1 또는 3과 4사이
							}
						}
					}
					if (trueCount >= 3 && tfPoint) {
						for (int num : dice) {// 주사위 눈의 값 전부 더하기
							sb.p1FourOfKind += num;
						}
					} else {// 조건 충족이 안됐을 경우 0입력
						sb.p1FourOfKind = 0;
					}
					sb.p1FourOfKindTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("fullhouse")) {
				if (sb.p1FullHouseTF) {// 만약 입력받지 않은 상태라면,
					// 풀하우스 조건
					tfPoint = false;
					trueCount = 0;
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
						if (dice[i] - dice[i + 1] < 0) {
							tempInt = dice[i + 1];
							dice[i + 1] = dice[i];
							dice[i] = tempInt;
							i = -1;
						}
					}
					for (int i = 0; i < 4; i++) {
						if (dice[i] - dice[i + 1] == 0) {// 일치하는 경우 true점수++
							trueCount++;
						} else {
							if (i == 1 || i == 2) {// 주사위 눈에 앞 뒤가 차이가 있을 때,
								tfPoint = true; // 그 차이나는 자리가 (idx) 1과 2 또는 2와 3사이
							}
						}
					}
					if (trueCount >= 3 && tfPoint) {
						for (int num : dice) {// 주사위 눈의 값 전부 더하기
							sb.p1FullHouse += num;
						}
					} else {// 조건 충족이 안됐을 경우 0입력
						sb.p1FullHouse = 0;
					}
					sb.p1FullHouseTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					tfPoint = false;// tf포인트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("smallstraight")) {
				if (sb.p1SmallStraightTF) {// 만약 입력받지 않은 상태라면,
					trueCount = 0;
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
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
					sb.p1SmallStraightTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("largestraight")) {
				if (sb.p1LargeStraightTF) {// 만약 입력받지 않은 상태라면,
					trueCount = 0;
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
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
					sb.p1LargeStraightTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("yacht")) {
				if (sb.p1YachtTF) {// 만약 입력받지 않은 상태라면,
					trueCount = 0;
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
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
					sb.p1YachtTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else {
				System.out.println("입력과 일치하는 항목을 찾을 수 없습니다. 다시 입력해주세요.");
				inputLoop = true;
			}
		} // inputloop end
		sb.totalRefresh();
		sb.printScoreBoard();
		System.out.println("입력되었습니다. 차례가 넘어갑니다.");
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
		while (inputLoop) {// 여기부터 모든 항목에 대한 분기점을 넣어야하기 때문에 굉장히 길어짐, 노가다 작업 Start
			sb.printScoreBoard();
			System.out.println("점수를 넣을 항목을 입력하세요. (ex)Aces");
			element = br.readLine();
			temp="";
			StringTokenizer st = new StringTokenizer(element);
			element="";
			while (st.hasMoreTokens()) {// 문자 사이 공백 지우는 작업
				temp += st.nextToken();
				System.out.println(temp);
			}
			for (int i = 0; i < temp.length(); i++) {// 전부 소문자로 변환하는 작업
				if (Character.isLetter(temp.charAt(i)) && temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z') {// 문자&A~Z사이
					// 체크
					element += (char) (temp.charAt(i) + 32);
				} else {
					element += temp.charAt(i);
				}
			} // 여기까지, 공백이 없는, 전부 소문자인 문자열 완성
			if (element.equals("aces")) {
				if (sb.p2AcesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 1) {
							trueCount++;
						}
					}
					sb.p2Aces = trueCount;
					sb.p2AcesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("deuces")) {
				if (sb.p2DeucesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 2) {
							trueCount++;
						}
					}
					sb.p2Deuces = trueCount * 2;
					sb.p2DeucesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("threes")) {
				if (sb.p2ThreesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 3) {
							trueCount++;
						}
					}
					sb.p2Threes = trueCount * 3;
					sb.p2ThreesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("fours")) {
				if (sb.p2FoursTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 4) {
							trueCount++;
						}
					}
					sb.p2Fours = trueCount * 4;
					sb.p2FoursTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("fives")) {
				if (sb.p2FivesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 5) {
							trueCount++;
						}
					}
					sb.p2Fives = trueCount * 5;
					sb.p2FivesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("sixes")) {
				if (sb.p2SixesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 6) {
							trueCount++;
						}
					}
					sb.p2Sixes = trueCount * 6;
					sb.p2SixesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("choice")) {
				if (sb.p2ChoiceTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 초이스는 단순한 총합
						sb.p2Choice += dice[i];
					}
					sb.p2ChoiceTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("4ofakind")) {
				if (sb.p2FourOfKindTF) {// 만약 입력받지 않은 상태라면,
					// 포오브카인드 조건
					trueCount = 0;// 초기화
					tfPoint = false;// 초기화
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
						if (dice[i] - dice[i + 1] < 0) {
							tempInt = dice[i + 1];
							dice[i + 1] = dice[i];
							dice[i] = tempInt;
							i = -1;
						}
					}
					for (int i = 0; i < 4; i++) {
						if (dice[i] - dice[i + 1] == 0) {// 일치하는 경우 true점수++
							trueCount++;
						} else {
							if (i == 0 || i == 3) {// 주사위 눈에 앞 뒤가 차이가 있을 때,
								tfPoint = true; // 그 차이나는 자리가 (idx) 0과 1 또는 3과 4사이
							}
						}
					}
					if (trueCount >= 3 && tfPoint) {
						for (int num : dice) {// 주사위 눈의 값 전부 더하기
							sb.p2FourOfKind += num;
						}
					} else {// 조건 충족이 안됐을 경우 0입력
						sb.p2FourOfKind = 0;
					}
					sb.p2FourOfKindTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("fullhouse")) {
				if (sb.p2FullHouseTF) {// 만약 입력받지 않은 상태라면,
					// 풀하우스 조건
					tfPoint = false;
					trueCount = 0;
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
						if (dice[i] - dice[i + 1] < 0) {
							tempInt = dice[i + 1];
							dice[i + 1] = dice[i];
							dice[i] = tempInt;
							i = -1;
						}
					}
					for (int i = 0; i < 4; i++) {
						if (dice[i] - dice[i + 1] == 0) {// 일치하는 경우 true점수++
							trueCount++;
						} else {
							if (i == 1 || i == 2) {// 주사위 눈에 앞 뒤가 차이가 있을 때,
								tfPoint = true; // 그 차이나는 자리가 (idx) 1과 2 또는 2와 3사이
							}
						}
					}
					if (trueCount >= 3 && tfPoint) {
						for (int num : dice) {// 주사위 눈의 값 전부 더하기
							sb.p2FullHouse += num;
						}
					} else {// 조건 충족이 안됐을 경우 0입력
						sb.p2FullHouse = 0;
					}
					sb.p2FullHouseTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					tfPoint = false;// tf포인트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("smallstraight")) {
				if (sb.p2SmallStraightTF) {// 만약 입력받지 않은 상태라면,
					trueCount = 0;
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
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
					sb.p2SmallStraightTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("largestraight")) {
				if (sb.p2LargeStraightTF) {// 만약 입력받지 않은 상태라면,
					trueCount = 0;
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
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
					sb.p2LargeStraightTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else if (element.equals("yacht")) {
				if (sb.p2YachtTF) {// 만약 입력받지 않은 상태라면,
					trueCount = 0;
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
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
					sb.p2YachtTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else {
					System.out.println("이미 입력한 항목입니다.");
					inputLoop = true;
				}
			} else {
				System.out.println("입력과 일치하는 항목을 찾을 수 없습니다. 다시 입력해주세요.");
				inputLoop = true;
			}
		} // inputloop end
		sb.totalRefresh();
		sb.printScoreBoard();
		System.out.println("입력되었습니다. 차례가 넘어갑니다.");
	}

	private static void gameOverCheck(String p1, String p2, ScoreBoard sb) {
		if (sb.gameOverCheck()) {
			System.out.println("게임이 끝났습니다.");
			if (sb.p1Total > sb.p2Total) {
				System.out.println(p1 + "의 승리입니다.");
				System.exit(0);
			} else if (sb.p1Total < sb.p2Total) {
				System.out.println(p2 + "의 승리입니다.");
				System.exit(0);
			} else {
				System.out.println("무승부입니다.");
				System.exit(0);
			}
		}
	}
}
