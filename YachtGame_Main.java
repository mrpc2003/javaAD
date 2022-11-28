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

	private static int genRandom() { // 1~6 사이의 랜덤한 수를 생성하는 메소드
		int result = 0; // 랜덤한 수를 저장할 변수
		result = (int) (Math.random() * 6) + 1; // Math.random() * 6 -> 0~5 따라서 +1을 통해 1~6까지 범위 달성
		return result; // 랜덤한 수를 반환
	}

	private static void time() throws InterruptedException { // 1초 쉬는 메소드
		TimeUnit.SECONDS.sleep(1); // 1초 쉬기
	}

	private static void rollDiceAll(int[] dice) throws InterruptedException { // 주사위 5개를 던지는 메소드
		for (int i = 0; i < 5; i++) { // 주사위 5개를 던지는 반복문
			dice[i] = genRandom(); // 주사위 5개를 랜덤한 수로 초기화
		}
	}

	private static void printRoll(int[] dice) throws InterruptedException { // 주사위 5개를 출력하는 메소드
		for (int i = 3; i >= 0; i--) { // 3초 카운트 다운
			time(); // 1초 쉬는 메소드
			if (i == 3) { // 3초일 때
				System.out.print("Dice"); // Dice 출력
			} else if (i > 0) { // 2초, 1초일 때
				System.out.print("."); // . 출력
			} else { // 0초일 때
				System.out.println("roll-!"); // roll-! 출력
			}
		}
		time(); // 1초 쉬는 메소드
		System.out.println(Arrays.toString(dice)); // 주사위 5개를 출력
	
	}

	private static int[] reRollDice(int[] dice) throws IOException, InterruptedException { // 주사위를 재배치하는 메소드
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위한 BufferedReader 객체 생성
		String ans; // 재배치 여부를 저장할 변수
		boolean inputLoop = true; // 재배치 여부를 입력받는 반복문을 제어할 변수
		boolean freePath=false; // 무료로 재배치할 수 있는 기회를 제공할 변수
		int diceCount = 0; // 재배치할 주사위의 개수를 저장할 변수
		int rollCount = 2; // 재배치할 수 있는 횟수를 저장할 변수
		int[] diceIdxBox; // 재배치할 주사위의 인덱스를 저장할 배열
		int[] temp; // 재배치할 주사위를 저장할 배열
		while (rollCount > 0) { // 재배치할 수 있는 횟수가 0보다 클 때 반복
			inputLoop = true; // 재배치 여부를 입력받는 반복문을 제어할 변수를 true로 초기화
			System.out.println("다시 던지고 싶은 주사위가 있습니까?(y/n)"); // 재배치 여부를 묻는 문장 출력
			ans = br.readLine(); // 재배치 여부를 입력받음
			if (ans.equals("y") || ans.equals("Y")) { // 재배치를 원할 때
				while (inputLoop) { // 재배치할 주사위의 개수를 입력받는 반복문
					diceCount = 0; // 재배치할 주사위의 개수를 0으로 초기화
					diceIdxBox = new int[0]; // 재배치할 주사위의 인덱스를 저장할 배열을 0으로 초기화
					System.out.println("던지고 싶은 주사위의 번호를 공백으로 구분하여 입력하시오.\n명령어 :1 2 3 4 5 / all / (던지기취소)n "); // 재배치할 주사위의 번호를 입력받는 문장 출력
					ans = br.readLine(); // 재배치할 주사위의 번호를 입력받음
					StringTokenizer st = new StringTokenizer(ans); // 입력받은 재배치할 주사위의 번호를 공백으로 구분하여 토큰화
					while (st.hasMoreTokens()) { // 토큰이 남아있을 때 반복
						if (diceCount > 4) { // 재배치할 주사위의 개수가 5개를 초과할 때
							System.out.println("주사위의 개수를 초과한 입력입니다. 다시 입력해주세요."); // 재배치할 주사위의 개수를 초과한 입력임을 알리는 문장 출력
							inputLoop = true; // 재배치할 주사위의 개수를 입력받는 반복문을 제어할 변수를 true로 초기화
							break; // 재배치할 주사위의 개수를 입력받는 반복문을 종료
						}
						ans = st.nextToken(); // 토큰을 ans에 저장
						if (ans.equals("all")) { // 재배치할 주사위의 번호로 all을 입력받았을 때
							diceIdxBox = new int[0];// 다시 던질 인덱스 초기화 (중간에 All이 나오는 경우, 리롤 차단)
							rollDiceAll(dice);// 주사위를 모두 재배치
							inputLoop = false; // 재배치할 주사위의 개수를 입력받는 반복문을 제어할 변수를 false로 초기화
							break; // 재배치할 주사위의 개수를 입력받는 반복문을 종료
						} else if (ans.equals("n")||ans.equals("N")) { // 재배치를 취소할 때
							System.out.println("주사위 던지기를 취소합니다."); // 주사위 던지기를 취소함을 알리는 문장 출력
							freePath=true; // 무료로 이동할 수 있는 상태임을 알리는 변수를 true로 초기화
							inputLoop=false; // 재배치할 주사위의 개수를 입력받는 반복문을 제어할 변수를 false로 초기화
							break; // 재배치할 주사위의 개수를 입력받는 반복문을 종료
						} else if (ans.length() == 1 && Character.isDigit(ans.charAt(0))) { // 재배치할 주사위의 번호로 1~5의 숫자를 입력받았을 때
							temp = new int[diceIdxBox.length + 1]; // 재배치할 주사위의 번호를 저장할 배열을 재배치할 주사위의 개수 + 1만큼 생성
							System.arraycopy(diceIdxBox, 0, temp, 0, diceIdxBox.length); // 재배치할 주사위의 번호를 저장할 배열을 temp에 복사
							temp[diceIdxBox.length] = Integer.parseInt(ans); // 재배치할 주사위의 번호를 temp에 저장
							diceIdxBox = temp; // 재배치할 주사위의 번호를 저장할 배열을 temp로 초기화
							diceCount++; // 재배치할 주사위의 개수를 1 증가
							inputLoop = false; // 재배치할 주사위의 개수를 입력받는 반복문을 제어할 변수를 false로 초기화
						} else { // 재배치할 주사위의 번호로 1~5의 숫자가 아닌 다른 문자를 입력받았을 때
							System.out.println("잘못된 입력입니다."); // 잘못된 입력임을 알리는 문장 출력
							inputLoop = true; // 재배치할 주사위의 개수를 입력받는 반복문을 제어할 변수를 true로 초기화
							break; // 재배치할 주사위의 개수를 입력받는 반복문을 종료
						} // if end
					} // token End
					if (!inputLoop&&!freePath) {// token에서 문제가 없었다면
						dice = rollSelectedDice(dice, diceIdxBox); // 선택한 주사위를 던짐
						printRoll(dice); // 던진 주사위를 출력
						rollCount--; // 던질 수 있는 횟수를 1 감소
						System.out.println("리롤 가능한 횟수: " + rollCount); // 던질 수 있는 횟수를 출력
					}
					freePath=false; // 무료로 이동할 수 있는 상태임을 알리는 변수를 false로 초기화
				} // loop end
			} // if(y) end
			else if (ans.equals("n") || ans.equals("N")) { // n 또는 N을 입력받았을 때
				rollCount = 0; // 던질 수 있는 횟수를 0으로 초기화
			} else { // n 또는 N이 아닌 다른 문자를 입력받았을 때
				System.out.println("잘못된 입력입니다. 다시 입력해주세요."); // 잘못된 입력임을 알리는 문장 출력
			}
			if (rollCount == 0) { // 던질 수 있는 횟수가 0이 되었을 때
				System.out.println("주사위 던지기를 종료합니다."); // 주사위 던지기를 종료한다는 문장 출력
			}
		} // rollCount end
		return dice; // 던진 주사위를 반환
	}

	private static int[] rollSelectedDice(int[] dice, int[] diceIdx) { // 선택한 주사위를 던지는 메소드
		for (int idx : diceIdx) { // 선택한 주사위의 개수만큼 반복
			dice[idx - 1] = genRandom(); // 선택한 주사위를 던짐
		}
		return dice; // 던진 주사위를 반환
	}

	private static void p1inputScore(int[] dice, ScoreBoard sb) throws IOException { // 플레이어 1이 점수를 넣을 항목을 입력하는 메소드
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위한 BufferedReader 객체 생성
		int trueCount = 0; // 입력받은 점수가 유효한 점수인지 확인하기 위한 변수
		int score = 0; // 입력받은 점수를 저장하기 위한 변수
		int tempInt = 0; // 입력받은 점수를 저장하기 위한 변수
		StringBuilder element; // 입력받은 점수를 저장하기 위한 변수
		String temp; // 입력받은 점수를 저장하기 위한 변수
		boolean inputLoop = true; // 점수를 입력받는 반복문을 제어할 변수
		boolean tfPoint = false; // 점수를 입력받는 반복문을 제어할 변수
		while (inputLoop) {// 여기부터 모든 항목에 대한 분기점을 넣어야하기 때문에 굉장히 길어짐, 노가다 작업 Start
			sb.printScoreBoard(); // 점수판을 출력
			System.out.println("점수를 넣을 항목을 입력하세요. (ex)Aces"); // 점수를 넣을 항목을 입력받는 문장 출력
			element = new StringBuilder(br.readLine()); // 점수를 넣을 항목을 입력받음
			temp=""; // temp를 초기화
			StringTokenizer st = new StringTokenizer(element.toString()); // 입력받은 점수를 공백을 기준으로 분리
			element = new StringBuilder(); // element를 초기화
			while (st.hasMoreTokens()) {// 문자 사이 공백 지우는 작업
				temp += st.nextToken(); // 공백을 제거한 문자를 temp에 저장
				System.out.println(temp); // temp 출력
			}
			for (int i = 0; i < temp.length(); i++) {// 전부 소문자로 변환하는 작업
				if (Character.isLetter(temp.charAt(i)) && temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z') {// 문자&A~Z사이
																											// 체크
					element.append((char) (temp.charAt(i) + 32)); // 대문자를 소문자로 변환
				} else { // 문자&A~Z사이가 아닐 경우
					element.append(temp.charAt(i)); // 그대로 저장
				}
			} // 여기까지, 공백이 없는, 전부 소문자인 문자열 완성
			switch (element.toString()) {
				case "aces":
					if (sb.p1AcesTF) {// 만약 입력받지 않은 상태라면,
						for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
							if (dice[i] == 1) { // 주사위가 1이라면
								trueCount++; // trueCount를 1 증가
							}
						}
						sb.p1Aces = trueCount; // trueCount를 p1Aces에 저장
						sb.p1AcesTF = false;// 입력받은 상태로 전환
						trueCount = 0;// 카운트 초기화
						inputLoop = false;// 스코어 입력 루프 탈출
					} else { // 만약 입력받은 상태라면,
						System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라고 출력
					}
					break;
				case "deuces":  // 위와 동일
					if (sb.p1DeucesTF) {// 만약 입력받지 않은 상태라면,
						for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
							if (dice[i] == 2) { // 주사위가 2이라면
								trueCount++; // trueCount를 1 증가
							}
						}
						sb.p1Deuces = trueCount * 2; // trueCount를 p1Deuces에 저장
						sb.p1DeucesTF = false;// 입력받은 상태로 전환
						trueCount = 0;// 카운트 초기화
						inputLoop = false;// 스코어 입력 루프 탈출
					} else { // 만약 입력받은 상태라면,
						System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라고 출력
					}
					break;
				case "threes":  // 위와 동일
					if (sb.p1ThreesTF) {// 만약 입력받지 않은 상태라면,
						for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
							if (dice[i] == 3) { // 주사위가 3이라면
								trueCount++; // trueCount를 1 증가
							}
						}
						sb.p1Threes = trueCount * 3; // trueCount를 p1Threes에 저장
						sb.p1ThreesTF = false;// 입력받은 상태로 전환
						trueCount = 0;// 카운트 초기화
						inputLoop = false;// 스코어 입력 루프 탈출
					} else { // 만약 입력받은 상태라면,
						System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라고 출력
					}
					break;
				case "fours":  // 위와 동일
					if (sb.p1FoursTF) {// 만약 입력받지 않은 상태라면,
						for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
							if (dice[i] == 4) { // 주사위가 4이라면
								trueCount++; // trueCount를 1 증가
							}
						}
						sb.p1Fours = trueCount * 4; // trueCount를 p1Fours에 저장
						sb.p1FoursTF = false;// 입력받은 상태로 전환
						trueCount = 0;// 카운트 초기화
						inputLoop = false;// 스코어 입력 루프 탈출
					} else { // 만약 입력받은 상태라면,
						System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라고 출력
					}
					break;
				case "fives":  // 위와 동일
					if (sb.p1FivesTF) {// 만약 입력받지 않은 상태라면,
						for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
							if (dice[i] == 5) { // 주사위가 5이라면
								trueCount++; // trueCount를 1 증가
							}
						}
						sb.p1Fives = trueCount * 5; // trueCount를 p1Fives에 저장
						sb.p1FivesTF = false;// 입력받은 상태로 전환
						trueCount = 0;// 카운트 초기화
						inputLoop = false;// 스코어 입력 루프 탈출
					} else { // 만약 입력받은 상태라면,
						System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라고 출력
					}
					break;
				case "sixes":  // 위와 동일
					if (sb.p1SixesTF) {// 만약 입력받지 않은 상태라면,
						for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
							if (dice[i] == 6) { // 주사위가 6이라면
								trueCount++; // trueCount를 1 증가
							}
						}
						sb.p1Sixes = trueCount * 6; // trueCount를 p1Sixes에 저장
						sb.p1SixesTF = false;// 입력받은 상태로 전환
						trueCount = 0;// 카운트 초기화
						inputLoop = false;// 스코어 입력 루프 탈출
					} else { // 만약 입력받은 상태라면,
						System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라고 출력
					}
					break;
				case "choice":  // 위와 동일
					if (sb.p1ChoiceTF) {// 만약 입력받지 않은 상태라면,
						for (int i = 0; i < 5; i++) {// 초이스는 단순한 총합
							sb.p1Choice += dice[i]; // 주사위의 총합을 p1Choice에 저장
						}
						sb.p1ChoiceTF = false;// 입력받은 상태로 전환
						inputLoop = false;// 스코어 입력 루프 탈출
					} else { // 만약 입력받은 상태라면,
						System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라고 출력
					}
					break;
				case "4ofakind":  // 위와 동일
					if (sb.p1FourOfKindTF) {// 만약 입력받지 않은 상태라면,

						tfPoint = false;// 초기화
						for (int i = 0; i < 4; i++) {// 오름차순 정렬
							if (dice[i] - dice[i + 1] < 0) { // 만약 주사위가 오름차순이 아니라면
								tempInt = dice[i + 1]; // tempInt에 주사위를 저장
								dice[i + 1] = dice[i]; // 주사위를 오름차순으로 정렬
								dice[i] = tempInt; // tempInt에 저장된 주사위를 저장
								i = -1; // i를 -1로 초기화
							}
						}
						for (int i = 0; i < 4; i++) { // 4개의 주사위를 비교
							if (dice[i] - dice[i + 1] == 0) {// 일치하는 경우 true점수++
								trueCount++; // trueCount를 1 증가
							} else { // 일치하지 않는 경우
								if (i == 0 || i == 3) {// 주사위 눈에 앞 뒤가 차이가 있을 때,
									tfPoint = true; // 그 차이나는 자리가 (idx) 0과 1 또는 3과 4사이
								}
							}
						}
						if (trueCount >= 3 && tfPoint) { // trueCount가 3이상이고, tfPoint가 true라면
							for (int num : dice) {// 주사위 눈의 값 전부 더하기
								sb.p1FourOfKind += num; // p1FourOfKind에 저장
							}
						} else {// 조건 충족이 안됐을 경우 0입력
							sb.p1FourOfKind = 0; // p1FourOfKind에 0저장
						}
						sb.p1FourOfKindTF = false;// 입력받은 상태로 전환
						trueCount = 0;// 카운트 초기화
						inputLoop = false;// 스코어 입력 루프 탈출
					} else { // 만약 입력받은 상태라면,
						System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라고 출력
					}
					break;
				case "fullhouse":  // 위와 동일
					if (sb.p1FullHouseTF) {// 만약 입력받지 않은 상태라면,
						// 풀하우스 조건
						tfPoint = false; // 초기화
						for (int i = 0; i < 4; i++) {// 오름차순 정렬
							if (dice[i] - dice[i + 1] < 0) { // 만약 주사위가 오름차순이 아니라면
								tempInt = dice[i + 1]; // tempInt에 주사위를 저장
								dice[i + 1] = dice[i]; // 주사위를 오름차순으로 정렬
								dice[i] = tempInt; // tempInt에 저장된 주사위를 저장
								i = -1; // i를 -1로 초기화
							}
						}
						for (int i = 0; i < 4; i++) { // 4개의 주사위를 비교
							if (dice[i] - dice[i + 1] == 0) {// 일치하는 경우 true점수++
								trueCount++; // trueCount를 1 증가
							} else { // 일치하지 않는 경우
								if (i == 1 || i == 2) {// 주사위 눈에 앞 뒤가 차이가 있을 때,
									tfPoint = true; // 그 차이나는 자리가 (idx) 1과 2 또는 2와 3사이
								}
							}
						}
						if (trueCount >= 3 && tfPoint) { // trueCount가 3이상이고, tfPoint가 true라면
							for (int num : dice) {// 주사위 눈의 값 전부 더하기
								sb.p1FullHouse += num; // p1FullHouse에 저장
							}
						} else {// 조건 충족이 안됐을 경우 0입력
							sb.p1FullHouse = 0; // p1FullHouse에 0저장
						}
						sb.p1FullHouseTF = false;// 입력받은 상태로 전환
						trueCount = 0;// 카운트 초기화
						tfPoint = false;// tf포인트 초기화
						inputLoop = false;// 스코어 입력 루프 탈출
					} else { // 만약 입력받은 상태라면,
						System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라고 출력
					}
					break;
				case "smallstraight":  // 위와 동일
					if (sb.p1SmallStraightTF) {// 만약 입력받지 않은 상태라면,
						trueCount = 0; // 카운트 초기화
						for (int i = 0; i < 4; i++) {// 오름차순 정렬
							if (dice[i] - dice[i + 1] < 0) { // 만약 주사위가 오름차순이 아니라면
								tempInt = dice[i + 1]; // tempInt에 주사위를 저장
								dice[i + 1] = dice[i]; // 주사위를 오름차순으로 정렬
								dice[i] = tempInt; // tempInt에 저장된 주사위를 저장
								i = -1; // i를 -1로 초기화
							}
						}
						for (int i = 0; i < 4; i++) { // 4개의 주사위를 비교
							if (dice[i] - dice[i + 1] == 1) { // 일치하는 경우 true점수++
								trueCount++; // trueCount를 1 증가
							}
						}
						if (dice[0] == 5 && trueCount == 4) { // 만약 5,4,3,2,1이라면
							sb.p1SmallStraight = 30; // p1SmallStraight에 30저장
						} else { // 조건 충족이 안됐을 경우 0입력
							sb.p1SmallStraight = 0; // p1SmallStraight에 0저장
						}
						sb.p1SmallStraightTF = false;// 입력받은 상태로 전환
						trueCount = 0;// 카운트 초기화
						inputLoop = false;// 스코어 입력 루프 탈출
					} else { // 만약 입력받은 상태라면,
						System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라고 출력
					}
					break;
				case "largestraight":  // 위와 동일
					if (sb.p1LargeStraightTF) {// 만약 입력받지 않은 상태라면,
						trueCount = 0; // 카운트 초기화
						for (int i = 0; i < 4; i++) {// 오름차순 정렬
							if (dice[i] - dice[i + 1] < 0) { // 만약 주사위가 오름차순이 아니라면
								tempInt = dice[i + 1]; // tempInt에 주사위를 저장
								dice[i + 1] = dice[i]; // 주사위를 오름차순으로 정렬
								dice[i] = tempInt; // tempInt에 저장된 주사위를 저장
								i = -1; // i를 -1로 초기화
							}
						}
						for (int i = 0; i < 4; i++) { // 4개의 주사위를 비교
							if (dice[i] - dice[i + 1] == 1) { // 일치하는 경우 true점수++
								trueCount++; // trueCount를 1 증가
							}
						}
						if (dice[0] == 6 && trueCount == 4) { // 만약 6,5,4,3,2라면
							sb.p1LargeStraight = 30; // p1LargeStraight에 30저장
						} else { // 조건 충족이 안됐을 경우 0입력
							sb.p1LargeStraight = 0; // p1LargeStraight에 0저장
						}
						sb.p1LargeStraightTF = false;// 입력받은 상태로 전환
						trueCount = 0;// 카운트 초기화
						inputLoop = false;// 스코어 입력 루프 탈출
					} else { // 만약 입력받은 상태라면,
						System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라고 출력
					}
					break;
				case "yacht":  // 위와 동일
					if (sb.p1YachtTF) {// 만약 입력받지 않은 상태라면,
						trueCount = 0; // 카운트 초기화
						for (int i = 0; i < 4; i++) {// 오름차순 정렬
							if (dice[i] - dice[i + 1] < 0) { // 만약 주사위가 오름차순이 아니라면
								tempInt = dice[i + 1]; // tempInt에 주사위를 저장
								dice[i + 1] = dice[i]; // 주사위를 오름차순으로 정렬
								dice[i] = tempInt; // tempInt에 저장된 주사위를 저장
								i = -1; // i를 -1로 초기화
							}
						}
						for (int i = 0; i < 4; i++) { // 4개의 주사위를 비교
							if (dice[i] - dice[i + 1] == 0) { // 일치하는 경우 true점수++
								trueCount++; // trueCount를 1 증가
							}
						}
						if (trueCount == 4) { // 만약 4개의 주사위가 일치한다면
							sb.p1Yacht = 50; // p1Yacht에 50저장
						} else { // 조건 충족이 안됐을 경우 0입력
							sb.p1Yacht = 0; // p1Yacht에 0저장
						}
						sb.p1YachtTF = false;// 입력받은 상태로 전환
						trueCount = 0;// 카운트 초기화
						inputLoop = false;// 스코어 입력 루프 탈출
					} else { // 만약 입력받은 상태라면,
						System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라고 출력
					}
					break;
				default:  // 만약 입력받은 값이 없다면,
					System.out.println("입력과 일치하는 항목을 찾을 수 없습니다. 다시 입력해주세요."); // 입력과 일치하는 항목을 찾을 수 없다고 출력
					break;
			}
		} // inputloop end
		sb.totalRefresh(); // totalRefresh() 메소드 호출
		sb.printScoreBoard(); // printScoreBoard() 메소드 호출
		System.out.println("입력되었습니다. 차례가 넘어갑니다."); // 입력되었다고 출력
	}
	private static void p2inputScore(int[] dice, ScoreBoard sb) throws IOException { // 플레이어 2가 점수를 넣을 항목을 입력하는 메소드
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // BufferedReader 객체 생성
		int trueCount = 0; // trueCount 변수 선언 및 초기화
		int score = 0; // score 변수 선언 및 초기화
		int tempInt = 0; // tempInt 변수 선언 및 초기화
		StringBuilder element; // element 변수 선언
		String temp = ""; // temp 변수 선언 및 초기화
		boolean inputLoop = true; // inputLoop 변수 선언 및 초기화
		boolean tfPoint = false; // tfPoint 변수 선언 및 초기화
		while (inputLoop) {// 여기부터 모든 항목에 대한 분기점을 넣어야하기 때문에 굉장히 길어짐, 노가다 작업 Start
			sb.printScoreBoard(); // printScoreBoard() 메소드 호출
			System.out.println("점수를 넣을 항목을 입력하세요. (ex)Aces"); // 점수를 넣을 항목을 입력하라고 출력
			element = new StringBuilder(br.readLine());
			temp="";
			StringTokenizer st = new StringTokenizer(element.toString());
			element = new StringBuilder();
			while (st.hasMoreTokens()) {// 문자 사이 공백 지우는 작업
				temp += st.nextToken();
				System.out.println(temp);
			}
			for (int i = 0; i < temp.length(); i++) {// 전부 소문자로 변환하는 작업
				if (Character.isLetter(temp.charAt(i)) && temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z') {// 문자&A~Z사이
																											// 체크
					element.append((char) (temp.charAt(i) + 32));
				} else {
					element.append(temp.charAt(i));
				}
			} // 여기까지, 공백이 없는, 전부 소문자인 문자열 완성
			switch (element.toString()) {
				case "aces":
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
					}
					break;
				case "deuces":
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
					}
					break;
				case "threes":
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
					}
					break;
				case "fours":
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
					}
					break;
				case "fives":
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
					}
					break;
				case "sixes":
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
					}
					break;
				case "choice":
					if (sb.p2ChoiceTF) {// 만약 입력받지 않은 상태라면,
						for (int i = 0; i < 5; i++) {// 초이스는 단순한 총합
							sb.p2Choice += dice[i];
						}
						sb.p2ChoiceTF = false;// 입력받은 상태로 전환
						inputLoop = false;// 스코어 입력 루프 탈출
					} else {
						System.out.println("이미 입력한 항목입니다.");
					}
					break;
				case "4ofakind":
					if (sb.p2FourOfKindTF) {// 만약 입력받지 않은 상태라면,
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
					}
					break;
				case "fullhouse":
					if (sb.p2FullHouseTF) {// 만약 입력받지 않은 상태라면,
						tfPoint = false;
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
					}
					break;
				case "smallstraight":
					if (sb.p2SmallStraightTF) {// 만약 입력받지 않은 상태라면,
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
					}
					break;
				case "largestraight":
					if (sb.p2LargeStraightTF) {// 만약 입력받지 않은 상태라면,
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
					}
					break;
				case "yacht":
					if (sb.p2YachtTF) {// 만약 입력받지 않은 상태라면,
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
					}
					break;
				default:
					System.out.println("입력과 일치하는 항목을 찾을 수 없습니다. 다시 입력해주세요.");
					break;
			}
		} // inputloop end
		sb.totalRefresh();
		sb.printScoreBoard();
		System.out.println("입력되었습니다. 차례가 넘어갑니다.");
	}

	private static void gameOverCheck(String p1, String p2, ScoreBoard sb) { // 게임 종료 조건 확인
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
