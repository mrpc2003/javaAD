package AD_Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

class mainGameConnect{
	static String p1 = "Player1"; // 플레이어 1 이름
	static String p2 = "Player2"; // 플레이어 2 이름
	public void setP1name(String p1name){
		p1 = p1name;
	} // 플레이어 1 이름 설정
	public void setP2name(String p2name){
		p2 = p2name;
	} // 플레이어 2 이름 설정
	public static String getP1Name(){
		return p1;
	} // 플레이어 1 이름 반환
	public static String getP2Name(){
		return p2;
	} // 플레이어 2 이름 반환
}
class p1player extends mainGameConnect{ // 플레이어 1 클래스
	static boolean p1isWin = false; // 플레이어 1 승리 여부
	public static Boolean p1Result(){
		return p1isWin;
	} // 플레이어 1 승리 여부 반환

}
class p2player extends mainGameConnect{ // 플레이어 2 클래스
	static boolean p2isWin = false; // 플레이어 2 승리 여부
	public static Boolean p2Result(){
		return p2isWin;
	} // 플레이어 2 승리 여부 반환
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
	private static int genRandom() { // 1~6 사이의 랜덤한 정수를 반환하는 메소드
		int result = 0; // 반환할 정수
		result = (int) (Math.random() * 6) + 1; // 1~6 사이의 랜덤한 정수를 반환
		return result; // 반환
	}

	private static void time() throws InterruptedException { // 1초 쉬는 메소드
		TimeUnit.SECONDS.sleep(1); // 1초 쉬기
	}

	private static int[] rollDiceAll(int[] dice) throws InterruptedException { // 주사위 5개를 던지는 메소드
		for (int i = 0; i < 5; i++) { // 주사위 5개를 던지는 반복문
			dice[i] = genRandom(); // 주사위 5개를 던지는 메소드
		}
		return dice; // 주사위 5개를 반환
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
		String ans; // 재배치할 주사위를 입력받을 변수
		boolean inputLoop = true; // 재배치할 주사위를 입력받는 반복문을 제어할 변수
		boolean freePath=false; // 무료로 재배치할 수 있는지 확인하는 변수
		int diceCount = 0; // 재배치할 주사위의 개수를 저장할 변수
		int rollCount = 2; // 재배치할 수 있는 횟수를 저장할 변수
		int[] diceIdxBox; // 재배치할 주사위의 인덱스를 저장할 배열
		int[] temp; // 재배치할 주사위를 저장할 배열
		while (rollCount > 0) { // 재배치할 수 있는 횟수가 0보다 클 때 반복
			inputLoop = true; // 재배치할 주사위를 입력받는 반복문을 제어할 변수를 true로 초기화
			System.out.println("다시 던지고 싶은 주사위가 있습니까?(y/n)"); // 재배치할 주사위가 있는지 물어보는 출력문
			ans = br.readLine(); // 재배치할 주사위가 있는지 입력받음
			if (ans.equals("y") || ans.equals("Y")) { // 재배치할 주사위가 있을 때
				while (inputLoop) { // 재배치할 주사위를 입력받는 반복문
					diceCount = 0; // 재배치할 주사위의 개수를 저장할 변수를 0으로 초기화
					diceIdxBox = new int[0]; // 재배치할 주사위의 인덱스를 저장할 배열을 0으로 초기화
					System.out.println("던지고 싶은 주사위의 번호를 공백으로 구분하여 입력하시오.\n명령어 :1 2 3 4 5 / all / (던지기취소)n "); // 재배치할 주사위의 번호를 입력받는 출력문
					ans = br.readLine(); // 재배치할 주사위의 번호를 입력받음
					StringTokenizer st = new StringTokenizer(ans); // 입력받은 재배치할 주사위의 번호를 공백으로 구분하여 토큰화
					while (st.hasMoreTokens()) { // 토큰이 남아있을 때 반복
						if (diceCount > 4) { // 재배치할 주사위의 개수가 5개를 초과할 때
							System.out.println("주사위의 개수를 초과한 입력입니다. 다시 입력해주세요."); // 재배치할 주사위의 개수를 초과한 입력이라는 출력문
							inputLoop = true; // 재배치할 주사위를 입력받는 반복문을 제어할 변수를 true로 초기화
							break; // 반복문을 빠져나감
						}
						ans = st.nextToken(); // 토큰을 ans에 저장
						if (ans.equals("all")) { // 재배치할 주사위의 번호를 all로 입력받았을 때
							diceIdxBox = new int[0];// 다시 던질 인덱스 초기화 (중간에 All이 나오는 경우, 리롤 차단)
							dice = rollDiceAll(dice); // 주사위를 모두 재배치
							inputLoop = false; // 재배치할 주사위를 입력받는 반복문을 제어할 변수를 false로 초기화
							break; // 반복문을 빠져나감
						} else if (ans.equals("n")||ans.equals("N")) { // 재배치할 주사위의 번호를 n으로 입력받았을 때
							System.out.println("주사위 던지기를 취소합니다."); // 주사위 던지기를 취소한다는 출력문
							freePath=true; // 무료로 이동할 수 있는 상태를 true로 초기화
							inputLoop=false; // 재배치할 주사위를 입력받는 반복문을 제어할 변수를 false로 초기화
							break; // 반복문을 빠져나감
						} else if (ans.length() == 1 && Character.isDigit(ans.charAt(0))) { // 재배치할 주사위의 번호를 1자리 숫자로 입력받았을 때
							temp = new int[diceIdxBox.length + 1]; // 재배치할 주사위의 번호를 저장할 배열을 생성
							System.arraycopy(diceIdxBox, 0, temp, 0, diceIdxBox.length); // 재배치할 주사위의 번호를 저장할 배열에 재배치할 주사위의 번호를 저장
							temp[diceIdxBox.length] = Integer.parseInt(ans); // 재배치할 주사위의 번호를 저장할 배열에 재배치할 주사위의 번호를 저장
							diceIdxBox = temp; // 재배치할 주사위의 번호를 저장할 배열을 재배치할 주사위의 번호를 저장할 배열로 초기화
							diceCount++; // 재배치할 주사위의 개수를 1 증가
							inputLoop = false; // 재배치할 주사위를 입력받는 반복문을 제어할 변수를 false로 초기화
						} else { // 재배치할 주사위의 번호를 1자리 숫자가 아닌 다른 문자로 입력받았을 때
							System.out.println("잘못된 입력입니다."); // 잘못된 입력이라는 출력문
							inputLoop = true; // 재배치할 주사위를 입력받는 반복문을 제어할 변수를 true로 초기화
							break; // 반복문을 빠져나감
						} // if end
					} // token End
					if (!inputLoop&&!freePath) {// token에서 문제가 없었다면 = 재배치할 주사위를 입력받는 반복문을 제어할 변수가 false이고 무료로 이동할 수 있는 상태가 false일 때
						dice = rollSelectedDice(dice, diceIdxBox); // 선택한 주사위를 던진다.
						printRoll(dice); // 던진 주사위를 출력한다.
						rollCount--; // 던질 수 있는 횟수를 1 감소
						System.out.println("리롤 가능한 횟수: " + rollCount); // 던질 수 있는 횟수를 출력
					}
					freePath=false; // 무료로 이동할 수 있는 상태를 false로 초기화
				} // loop end
			} // if(y) end
			else if (ans.equals("n") || ans.equals("N")) { // 주사위를 재배치하지 않을 때
				rollCount = 0; // 던질 수 있는 횟수를 0으로 초기화
				inputLoop = false; // 재배치할 주사위를 입력받는 반복문을 제어할 변수를 false로 초기화
			} else { // 주사위를 재배치할지 말지를 입력받는 반복문을 제어할 변수가 false일 때
				System.out.println("잘못된 입력입니다. 다시 입력해주세요."); // 잘못된 입력이라는 출력문
				inputLoop = true; // 주사위를 재배치할지 말지를 입력받는 반복문을 제어할 변수를 true로 초기화
			}
			if (rollCount == 0) { // 던질 수 있는 횟수가 0일 때
				System.out.println("주사위 던지기를 종료합니다."); // 주사위 던지기를 종료한다는 출력문
			}
		} // rollCount end
		return dice; // 던진 주사위를 반환
	}

	private static int[] rollSelectedDice(int[] dice, int[] diceIdx) { // 선택한 주사위를 던지는 메소드
		for (int idx : diceIdx) { // 선택한 주사위의 개수만큼 반복
			dice[idx - 1] = genRandom(); // 선택한 주사위를 던진다.
		}
		return dice; // 던진 주사위를 반환
	}

	private static void p1inputScore(int[] dice, ScoreBoard sb) throws IOException { // 플레이어1이 점수판에 점수를 입력하는 메소드
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위한 BufferedReader 객체 생성
		int trueCount = 0; // 주사위의 개수를 세기 위한 변수
		int score = 0; // 점수를 저장하기 위한 변수
		int tempInt = 0; // 임시로 주사위의 개수를 저장하기 위한 변수
		String element; // 점수판의 요소를 저장하기 위한 변수
		String temp; // 임시로 점수판의 요소를 저장하기 위한 변수
		boolean inputLoop = true; // 점수를 입력받는 반복문을 제어할 변수
		boolean tfPoint = false; // 점수판에 점수를 입력할 수 있는지를 판단하기 위한 변수
		while (inputLoop) { // 점수를 입력받는 반복문
			sb.printScoreBoard(); // 점수판을 출력
			System.out.println("점수를 넣을 항목을 입력하세요. (ex)Aces"); // 점수를 넣을 항목을 입력받는 출력문
			element = br.readLine(); // 점수를 넣을 항목을 입력받는다.
			temp=""; // 임시로 점수판의 요소를 저장하기 위한 변수를 초기화
			StringTokenizer st = new StringTokenizer(element); // 입력받은 점수를 넣을 항목을 공백을 기준으로 분리하기 위한 StringTokenizer 객체 생성
			element=""; // 점수판의 요소를 저장하기 위한 변수를 초기화
			while (st.hasMoreTokens()) {// 문자 사이 공백 지우는 작업
				temp += st.nextToken(); // 공백을 제거한 문자를 임시로 저장
				System.out.println(temp); // 임시로 저장한 문자를 출력
			}
			for (int i = 0; i < temp.length(); i++) {// 전부 소문자로 변환하는 작업
				if (Character.isLetter(temp.charAt(i)) && temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z') {// 문자&A~Z사이
					// 체크
					element += (char) (temp.charAt(i) + 32); // 소문자로 변환하여 저장
				} else { // 문자가 아니거나 A~Z가 아닌 경우
					element += temp.charAt(i); // 그대로 저장
				}
			} // 여기까지, 공백이 없는, 전부 소문자인 문자열 완성
			if (element.equals("aces")) { // Aces인 경우
				if (sb.p1AcesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 1) { // 주사위의 값이 1인 경우
							trueCount++; // 주사위의 개수를 1 증가
						}
					}
					sb.p1Aces = trueCount; // 점수판에 주사위의 개수를 저장
					sb.p1AcesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 이미 입력받은 상태라면
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프를 계속 진행
				}
			} else if (element.equals("deuces")) { // Deuces인 경우
				if (sb.p1DeucesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 2) { // 주사위의 값이 2인 경우
							trueCount++; // 주사위의 개수를 1 증가
						}
					}
					sb.p1Deuces = trueCount * 2; // 점수판에 주사위의 개수를 저장
					sb.p1DeucesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 이미 입력받은 상태라면
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프를 계속 진행
				}
			} else if (element.equals("threes")) { // Threes인 경우
				if (sb.p1ThreesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 3) { // 주사위의 값이 3인 경우
							trueCount++; // 주사위의 개수를 1 증가
						}
					}
					sb.p1Threes = trueCount * 3; // 점수판에 주사위의 개수를 저장
					sb.p1ThreesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 이미 입력받은 상태라면
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프를 계속 진행
				}
			} else if (element.equals("fours")) { // Fours인 경우
				if (sb.p1FoursTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 4) { // 주사위의 값이 4인 경우
							trueCount++; // 주사위의 개수를 1 증가
						}
					}
					sb.p1Fours = trueCount * 4; // 점수판에 주사위의 개수를 저장
					sb.p1FoursTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 이미 입력받은 상태라면
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프를 계속 진행
				}
			} else if (element.equals("fives")) { // Fives인 경우
				if (sb.p1FivesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 5) { // 주사위의 값이 5인 경우
							trueCount++; // 주사위의 개수를 1 증가
						}
					}
					sb.p1Fives = trueCount * 5; // 점수판에 주사위의 개수를 저장
					sb.p1FivesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 이미 입력받은 상태라면
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프를 계속 진행
				}
			} else if (element.equals("sixes")) { // Sixes인 경우
				if (sb.p1SixesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 6) { // 주사위의 값이 6인 경우
							trueCount++; // 주사위의 개수를 1 증가
						}
					}
					sb.p1Sixes = trueCount * 6; // 점수판에 주사위의 개수를 저장
					sb.p1SixesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 이미 입력받은 상태라면
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프를 계속 진행
				}
			} else if (element.equals("choice")) { // Choice인 경우
				if (sb.p1ChoiceTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 초이스는 단순한 총합
						sb.p1Choice += dice[i]; // 점수판에 주사위의 총합을 저장
					}
					sb.p1ChoiceTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 이미 입력받은 상태라면
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프를 계속 진행
				}
			} else if (element.equals("4ofakind")) { // 4 of a kind인 경우
				if (sb.p1FourOfKindTF) {// 만약 입력받지 않은 상태라면,
					trueCount = 0;// 초기화
					tfPoint = false;// 초기화
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
						if (dice[i] - dice[i + 1] < 0) { // 오름차순이 아닌 경우
							tempInt = dice[i + 1]; // 임시 변수에 값 저장
							dice[i + 1] = dice[i]; // 값 교환
							dice[i] = tempInt; // 임시 변수에 저장된 값 저장
							i = -1; // i를 -1로 초기화
						}
					}
					for (int i = 0; i < 4; i++) { // 4개의 주사위가 같은지 확인
						if (dice[i] - dice[i + 1] == 0) {// 일치하는 경우 true점수++
							trueCount++; // trueCount를 1 증가
						} else { // 일치하지 않는 경우
							if (i == 0 || i == 3) {// 주사위 눈에 앞 뒤가 차이가 있을 때,
								tfPoint = true; // 그 차이나는 자리가 (idx) 0과 1 또는 3과 4사이
							}
						}
					}
					if (trueCount >= 3 && tfPoint) { // 4개의 주사위가 같고, 앞 뒤가 차이가 나는 경우
						for (int num : dice) {// 주사위 눈의 값 전부 더하기
							sb.p1FourOfKind += num; // 점수판에 저장
						}
					} else {// 조건 충족이 안됐을 경우 0입력
						sb.p1FourOfKind = 0; // 점수판에 0저장
					}
					sb.p1FourOfKindTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 이미 입력받은 상태라면
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프를 계속 진행
				}
			} else if (element.equals("fullhouse")) { // full house인 경우
				if (sb.p1FullHouseTF) {// 만약 입력받지 않은 상태라면,
					tfPoint = false; // 초기화
					trueCount = 0; // 초기화
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
						if (dice[i] - dice[i + 1] < 0) { // 오름차순이 아닌 경우
							tempInt = dice[i + 1]; // 임시 변수에 값 저장
							dice[i + 1] = dice[i]; // 값 교환
							dice[i] = tempInt; // 임시 변수에 저장된 값 저장
							i = -1; // i를 -1로 초기화
						}
					}
					for (int i = 0; i < 4; i++) { // 4개의 주사위가 같은지 확인
						if (dice[i] - dice[i + 1] == 0) {// 일치하는 경우 true점수++
							trueCount++; // trueCount를 1 증가
						} else { // 일치하지 않는 경우
							if (i == 1 || i == 2) {// 주사위 눈에 앞 뒤가 차이가 있을 때,
								tfPoint = true; // 그 차이나는 자리가 (idx) 1과 2 또는 2와 3사이
							}
						}
					}
					if (trueCount >= 3 && tfPoint) { // 4개의 주사위가 같고, 앞 뒤가 차이가 나는 경우
						for (int num : dice) {// 주사위 눈의 값 전부 더하기
							sb.p1FullHouse += num; // 점수판에 저장
						}
					} else {// 조건 충족이 안됐을 경우 0입력
						sb.p1FullHouse = 0; // 점수판에 0저장
					}
					sb.p1FullHouseTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					tfPoint = false;// tf포인트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 이미 입력받은 상태라면
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프를 계속 진행
				}
			} else if (element.equals("smallstraight")) { // small straight인 경우
				if (sb.p1SmallStraightTF) {// 만약 입력받지 않은 상태라면,
					trueCount = 0; // 초기화
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
						if (dice[i] - dice[i + 1] < 0) { // 오름차순이 아닌 경우
							tempInt = dice[i + 1]; // 임시 변수에 값 저장
							dice[i + 1] = dice[i]; // 값 교환
							dice[i] = tempInt; // 임시 변수에 저장된 값 저장
							i = -1; // i를 -1로 초기화
						}
					}
					for (int i = 0; i < 4; i++) { // 4개의 주사위가 같은지 확인
						if (dice[i] - dice[i + 1] == 1) { // 일치하는 경우 true점수++
							trueCount++; // trueCount를 1 증가
						}
					}
					if (dice[0] == 5 && trueCount == 4) { // 5,4,3,2,1인 경우
						sb.p1SmallStraight = 30; // 점수판에 30저장
					} else { // 조건 충족이 안됐을 경우 0입력
						sb.p1SmallStraight = 0; // 점수판에 0저장
					}
					sb.p1SmallStraightTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 이미 입력받은 상태라면
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프를 계속 진행
				}
			} else if (element.equals("largestraight")) { // large straight인 경우
				if (sb.p1LargeStraightTF) {// 만약 입력받지 않은 상태라면,
					trueCount = 0; // 초기화
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
						if (dice[i] - dice[i + 1] < 0) { // 오름차순이 아닌 경우
							tempInt = dice[i + 1]; // 임시 변수에 값 저장
							dice[i + 1] = dice[i]; // 값 교환
							dice[i] = tempInt; // 임시 변수에 저장된 값 저장
							i = -1; // i를 -1로 초기화
						}
					}
					for (int i = 0; i < 4; i++) { // 4개의 주사위가 같은지 확인
						if (dice[i] - dice[i + 1] == 1) { // 일치하는 경우 true점수++
							trueCount++; // trueCount를 1 증가
						}
					}
					if (dice[0] == 6 && trueCount == 4) { // 6,5,4,3,2인 경우
						sb.p1LargeStraight = 30; // 점수판에 30저장
					} else { // 조건 충족이 안됐을 경우 0입력
						sb.p1LargeStraight = 0; // 점수판에 0저장
					}
					sb.p1LargeStraightTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 이미 입력받은 상태라면
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프를 계속 진행
				}
			} else if (element.equals("yacht")) { // yacht인 경우
				if (sb.p1YachtTF) {// 만약 입력받지 않은 상태라면,
					trueCount = 0; // 초기화
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
						if (dice[i] - dice[i + 1] < 0) { // 오름차순이 아닌 경우
							tempInt = dice[i + 1]; // 임시 변수에 값 저장
							dice[i + 1] = dice[i]; // 값 교환
							dice[i] = tempInt; // 임시 변수에 저장된 값 저장
							i = -1; // i를 -1로 초기화
						}
					}
					for (int i = 0; i < 4; i++) { // 4개의 주사위가 같은지 확인
						if (dice[i] - dice[i + 1] == 0) { // 일치하는 경우 true점수++
							trueCount++; // trueCount를 1 증가
						}
					}
					if (trueCount == 4) { // 4개의 주사위가 같은 경우
						sb.p1Yacht = 50; // 점수판에 50저장
					} else { // 조건 충족이 안됐을 경우 0입력
						sb.p1Yacht = 0; // 점수판에 0저장
					}
					sb.p1YachtTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 이미 입력받은 상태라면
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프를 계속 진행
				}
			} else { // 입력한 값이 없는 경우
				System.out.println("입력과 일치하는 항목을 찾을 수 없습니다. 다시 입력해주세요."); // 입력과 일치하는 항목을 찾을 수 없다는 출력문
				inputLoop = true; // 스코어 입력 루프를 계속 진행
			}
		} // inputloop end
		sb.totalRefresh(); // 점수판 총점 갱신
		sb.printScoreBoard(); // 점수판 출력
		System.out.println("입력되었습니다. 차례가 넘어갑니다."); // 입력되었다는 출력문
	}

	private static void p2inputScore(int[] dice, ScoreBoard sb) throws IOException { // 플레이어2의 스코어 입력 메소드
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 버퍼리더 생성
		int trueCount = 0; // trueCount 변수 생성
		int score = 0; // score 변수 생성
		int tempInt = 0; // tempInt 변수 생성
		String element; // element 변수 생성
		String temp = ""; // temp 변수 생성
		boolean inputLoop = true; // inputLoop 변수 생성
		boolean tfPoint = false; // tfPoint 변수 생성
		while (inputLoop) { // 스코어 입력 루프
			sb.printScoreBoard(); // 점수판 출력
			System.out.println("점수를 넣을 항목을 입력하세요. (ex)Aces"); // 점수를 넣을 항목을 입력하라는 출력문
			element = br.readLine(); // 입력받은 값을 element에 저장
			temp=""; // temp 초기화
			StringTokenizer st = new StringTokenizer(element); // element를 공백을 기준으로 토큰화
			element=""; // element 초기화
			while (st.hasMoreTokens()) {// 문자 사이 공백 지우는 작업
				temp += st.nextToken(); // temp에 토큰화된 문자열을 저장
				System.out.println(temp); // temp 출력
			}
			for (int i = 0; i < temp.length(); i++) {// 전부 소문자로 변환하는 작업
				if (Character.isLetter(temp.charAt(i)) && temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z') {// 문자&A~Z사이
					// 체크
					element += (char) (temp.charAt(i) + 32); // 소문자로 변환
				} else { // 그 외
					element += temp.charAt(i); // 그대로 저장
				}
			} // 여기까지, 공백이 없는, 전부 소문자인 문자열 완성
			if (element.equals("aces")) { // aces인 경우
				if (sb.p2AcesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 1) { // 주사위가 1이라면
							trueCount++; // trueCount 증가
						}
					}
					sb.p2Aces = trueCount; // trueCount를 p2Aces에 저장
					sb.p2AcesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 만약 입력받은 상태라면,
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프 반복
				}
			} else if (element.equals("deuces")) { // deuces인 경우
				if (sb.p2DeucesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 2가 몇 개인지 확인
						if (dice[i] == 2) { // 주사위가 2라면
							trueCount++; // trueCount 증가
						}
					}
					sb.p2Deuces = trueCount * 2; // trueCount를 p2Deuces에 저장
					sb.p2DeucesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 만약 입력받은 상태라면,
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프 반복
				}
			} else if (element.equals("threes")) { // threes인 경우
				if (sb.p2ThreesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 3이 몇 개인지 확인
						if (dice[i] == 3) { // 주사위가 3라면
							trueCount++; // trueCount 증가
						}
					}
					sb.p2Threes = trueCount * 3; // trueCount를 p2Threes에 저장
					sb.p2ThreesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 만약 입력받은 상태라면,
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프 반복
				}
			} else if (element.equals("fours")) { // fours인 경우
				if (sb.p2FoursTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 4가 몇 개인지 확인
						if (dice[i] == 4) { // 주사위가 4라면
							trueCount++; // trueCount 증가
						}
					}
					sb.p2Fours = trueCount * 4; // trueCount를 p2Fours에 저장
					sb.p2FoursTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 만약 입력받은 상태라면,
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프 반복
				}
			} else if (element.equals("fives")) { // fives인 경우
				if (sb.p2FivesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 5) { // 주사위가 5라면
							trueCount++; // trueCount 증가
						}
					}
					sb.p2Fives = trueCount * 5; // trueCount를 p2Fives에 저장
					sb.p2FivesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 만약 입력받은 상태라면,
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프 반복
				}
			} else if (element.equals("sixes")) { // sixes인 경우
				if (sb.p2SixesTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 주사위에서 1이 몇 개인지 확인
						if (dice[i] == 6) { // 주사위가 6라면
							trueCount++; // trueCount 증가
						}
					}
					sb.p2Sixes = trueCount * 6; // trueCount를 p2Sixes에 저장
					sb.p2SixesTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 만약 입력받은 상태라면,
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프 반복
				}
			} else if (element.equals("choice")) { // choice인 경우
				if (sb.p2ChoiceTF) {// 만약 입력받지 않은 상태라면,
					for (int i = 0; i < 5; i++) {// 초이스는 단순한 총합
						sb.p2Choice += dice[i]; // 주사위의 총합을 p2Choice에 저장
					}
					sb.p2ChoiceTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 만약 입력받은 상태라면,
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프 반복
				}
			} else if (element.equals("4ofakind")) { // 4ofakind인 경우
				if (sb.p2FourOfKindTF) {// 만약 입력받지 않은 상태라면,
					// 포오브카인드 조건
					trueCount = 0;// 초기화
					tfPoint = false;// 초기화
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
						if (dice[i] - dice[i + 1] < 0) { // 만약 주사위가 오름차순이 아니라면
							tempInt = dice[i + 1]; // tempInt에 주사위를 저장
							dice[i + 1] = dice[i]; // 주사위에 주사위를 저장
							dice[i] = tempInt; // 주사위에 tempInt를 저장
							i = -1; // i를 -1로 초기화
						}
					}
					for (int i = 0; i < 4; i++) { // 4개의 주사위를 확인
						if (dice[i] - dice[i + 1] == 0) {// 일치하는 경우 true점수++
							trueCount++; // trueCount 증가
						} else { // 일치하지 않는 경우
							if (i == 0 || i == 3) {// 주사위 눈에 앞 뒤가 차이가 있을 때,
								tfPoint = true; // 그 차이나는 자리가 (idx) 0과 1 또는 3과 4사이
							}
						}
					}
					if (trueCount >= 3 && tfPoint) { // trueCount가 3이상이고, tfPoint가 true라면
						for (int num : dice) {// 주사위 눈의 값 전부 더하기
							sb.p2FourOfKind += num; // p2FourOfKind에 저장
						}
					} else {// 조건 충족이 안됐을 경우 0입력
						sb.p2FourOfKind = 0; // p2FourOfKind에 0저장
					}
					sb.p2FourOfKindTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 만약 입력받은 상태라면,
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프 반복
				}
			} else if (element.equals("fullhouse")) { // fullhouse인 경우
				if (sb.p2FullHouseTF) {// 만약 입력받지 않은 상태라면,
					tfPoint = false; // 초기화
					trueCount = 0; // 초기화
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
						if (dice[i] - dice[i + 1] < 0) { // 만약 주사위가 오름차순이 아니라면
							tempInt = dice[i + 1]; // tempInt에 주사위를 저장
							dice[i + 1] = dice[i]; // 주사위에 주사위를 저장
							dice[i] = tempInt; // 주사위에 tempInt를 저장
							i = -1; // i를 -1로 초기화
						}
					}
					for (int i = 0; i < 4; i++) { // 4개의 주사위를 확인
						if (dice[i] - dice[i + 1] == 0) {// 일치하는 경우 true점수++
							trueCount++; // trueCount 증가
						} else { // 일치하지 않는 경우
							if (i == 1 || i == 2) {// 주사위 눈에 앞 뒤가 차이가 있을 때,
								tfPoint = true; // 그 차이나는 자리가 (idx) 1과 2 또는 2와 3사이
							}
						}
					}
					if (trueCount >= 3 && tfPoint) { // trueCount가 3이상이고, tfPoint가 true라면
						for (int num : dice) {// 주사위 눈의 값 전부 더하기
							sb.p2FullHouse += num; // p2FullHouse에 저장
						}
					} else {// 조건 충족이 안됐을 경우 0입력
						sb.p2FullHouse = 0; // p2FullHouse에 0저장
					}
					sb.p2FullHouseTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					tfPoint = false;// tf포인트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 만약 입력받은 상태라면,
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프 반복
				}
			} else if (element.equals("smallstraight")) { // smallstraight인 경우
				if (sb.p2SmallStraightTF) {// 만약 입력받지 않은 상태라면,
					trueCount = 0; // 초기화
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
						if (dice[i] - dice[i + 1] < 0) { // 만약 주사위가 오름차순이 아니라면
							tempInt = dice[i + 1]; // tempInt에 주사위를 저장
							dice[i + 1] = dice[i]; // 주사위에 주사위를 저장
							dice[i] = tempInt; // 주사위에 tempInt를 저장
							i = -1; // i를 -1로 초기화
						}
					}
					for (int i = 0; i < 4; i++) { // 4개의 주사위를 확인
						if (dice[i] - dice[i + 1] == 1) { // 차이가 1인 경우 trueCount++
							trueCount++; // trueCount 증가
						}
					}
					if (dice[0] == 5 && trueCount == 4) { // 만약 5가 있고, trueCount가 4라면
						sb.p2SmallStraight = 30; // p2SmallStraight에 30저장
					} else { // 조건 충족이 안됐을 경우 0입력
						sb.p2SmallStraight = 0; // p2SmallStraight에 0저장
					}
					sb.p2SmallStraightTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 만약 입력받은 상태라면,
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프 반복
				}
			} else if (element.equals("largestraight")) { // largestraight인 경우
				if (sb.p2LargeStraightTF) {// 만약 입력받지 않은 상태라면,
					trueCount = 0; // 초기화
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
						if (dice[i] - dice[i + 1] < 0) { // 만약 주사위가 오름차순이 아니라면
							tempInt = dice[i + 1]; // tempInt에 주사위를 저장
							dice[i + 1] = dice[i]; // 주사위에 주사위를 저장
							dice[i] = tempInt; // 주사위에 tempInt를 저장
							i = -1; // i를 -1로 초기화
						}
					}
					for (int i = 0; i < 4; i++) { // 4개의 주사위를 확인
						if (dice[i] - dice[i + 1] == 1) { // 차이가 1인 경우 trueCount++
							trueCount++; // trueCount 증가
						}
					}
					if (dice[0] == 6 && trueCount == 4) { // 만약 6이 있고, trueCount가 4라면
						sb.p2LargeStraight = 30; // p2LargeStraight에 30저장
					} else { // 조건 충족이 안됐을 경우 0입력
						sb.p2LargeStraight = 0; // p2LargeStraight에 0저장
					}
					sb.p2LargeStraightTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 만약 입력받은 상태라면,
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프 반복
				}
			} else if (element.equals("yacht")) { // yacht인 경우
				if (sb.p2YachtTF) {// 만약 입력받지 않은 상태라면,
					trueCount = 0; // 초기화
					for (int i = 0; i < 4; i++) {// 오름차순 정렬
						if (dice[i] - dice[i + 1] < 0) { // 만약 주사위가 오름차순이 아니라면
							tempInt = dice[i + 1]; // tempInt에 주사위를 저장
							dice[i + 1] = dice[i]; // 주사위에 주사위를 저장
							dice[i] = tempInt; // 주사위에 tempInt를 저장
							i = -1; // i를 -1로 초기화
						}
					}
					for (int i = 0; i < 4; i++) { // 4개의 주사위를 확인
						if (dice[i] - dice[i + 1] == 0) { // 차이가 0인 경우 trueCount++
							trueCount++; // trueCount 증가
						}
					}
					if (trueCount == 4) { // 만약 trueCount가 4라면
						sb.p2Yacht = 50; // p2Yacht에 50저장
					} else { // 조건 충족이 안됐을 경우 0입력
						sb.p2Yacht = 0; // p2Yacht에 0저장
					}
					sb.p2YachtTF = false;// 입력받은 상태로 전환
					trueCount = 0;// 카운트 초기화
					inputLoop = false;// 스코어 입력 루프 탈출
				} else { // 만약 입력받은 상태라면,
					System.out.println("이미 입력한 항목입니다."); // 이미 입력한 항목이라는 출력문
					inputLoop = true; // 스코어 입력 루프 반복
				}
			} else { // 만약 입력받은 상태라면,
				System.out.println("입력과 일치하는 항목을 찾을 수 없습니다. 다시 입력해주세요."); // 입력과 일치하는 항목을 찾을 수 없다는 출력문
				inputLoop = true; // 스코어 입력 루프 반복
			}
		} // inputloop end
		sb.totalRefresh(); // totalRefresh() 메소드 호출
		sb.printScoreBoard(); // printScoreBoard() 메소드 호출
		System.out.println("입력되었습니다. 차례가 넘어갑니다."); // 입력되었다는 출력문
	}

	private static void gameOverCheck(String p1, String p2, ScoreBoard sb) { // gameOverCheck() 메소드
		if (sb.gameOverCheck()) { // 만약 gameOverCheck() 메소드가 true를 반환한다면
			System.out.println("게임이 끝났습니다."); // 게임이 끝났다는 출력문
			if (sb.p1Total > sb.p2Total) { // 만약 p1Total이 p2Total보다 크다면
				System.out.println(p1 + "의 승리입니다."); // p1의 승리라는 출력문
				System.exit(0); // 프로그램 종료
			} else if (sb.p1Total < sb.p2Total) { // 만약 p1Total이 p2Total보다 작다면
				System.out.println(p2 + "의 승리입니다."); // p2의 승리라는 출력문
				System.exit(0); // 프로그램 종료
			} else { // 만약 두 점수가 같다면
				System.out.println("무승부입니다."); // 무승부라는 출력문
				System.exit(0); // 프로그램 종료
			}
		}
	}
}
