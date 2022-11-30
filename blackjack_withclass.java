package AD_Project;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class blackjack_withclass
{
	public static void Bstart(String p1name, String p2name) throws InterruptedException, IOException
	{
		String p1_name = p1name;
		String p2_name = p2name;
		Game blackjack = new Game(p1_name, p2_name);

		blackjack.init();	// 게임 처음에 한번만 실행하는 내용 실행

		blackjack.play();	// 블랙잭 게임 5번 돌기

		blackjack.end();	// 게임 종료 후 메인 게임에 내용 보내기
	}
}

enum ActorState {Hit, Stay, Bust}	// 플레이어, 딜러의 상태



class Actor
{
	protected ArrayList<Integer> myCard = new ArrayList<Integer>();	// 자신이 가지고 있는 카드 덱
	private ActorState state = ActorState.Hit;	// 상태 Hit로 초기화

	public void AddCard(int card)
	{
		myCard.add(card);	// 자신의 카드 덱에 카드 추가
	}

	ActorState getState()			{ return state; }
	void setState(ActorState state)	{ this.state = state; }

	public int getSum()		// 자신의 카드 덱 카드들 합 구하기
	{
		int sum = 0;
		for (int i : myCard)
		{
			sum += i;
		}
		return sum;
	}

	void showCardList()		// 카드 보여주기, 카드의 숫자 합 보여주기
	{
		for (int i : myCard)
		{
			System.out.printf("%2d ", i);
		}
		System.out.printf("합: %d", getSum());
		System.out.println("");
	}

	void reset()				// 상태와 카드 덱 초기화 (한 판 끝나면 실행)
	{
		state = ActorState.Hit;	// 상태 초기화
		myCard.clear();			// 덱 초기화
	}
}

class Player extends Actor
{
	private int coin = 0;		// 보유 코인
	private int betCoin = 0;	// 베팅할 코인
	private String name;		// 플레이어의 이름


	Player()	{}

	String getName()				{ return this.name; }
	void setName(String name)		{ this.name = name; }
	int getCoin()					{ return this.coin; }
	void setCoin(int coin)			{ this.coin = coin;	}

	int getBetCoin()				{ return this.betCoin; }
	void setBetCoin(int betCoin)	{ this.betCoin = betCoin; }

}

class Dealer extends Actor
{
	private int sum = 0;		// 딜러 카드의 합

	Dealer() {}

	void setSum(Integer sum)		{ this.sum = sum;}

	@Override
	void showCardList()			// 딜러는 처음에 카드를 한 장만 보여주므로 Actor에서 오버라이딩
	{
		System.out.printf("%2d, %s", myCard.get(0), "*");
	}


}

class Game  								//전체 게임 관할
{
	public static boolean p1win = false;
	public static boolean p2win = false;
	String Bp1name;
	String Bp2name;

	Player p1 = new Player();				// p1 吏??젙 (硫붿씤 寃뚯엫?뿉?꽌?쓽 ?씠由? 諛쏆븘?삤?룄濡?)
	Player p2 = new Player();				// p2 吏??젙
	Dealer dealer = new Dealer();			// ?뵜?윭 ?깮?꽦
	CardDeck carddeck = new CardDeck();		// ?쟾泥? 移대뱶 ?뜳 ?깮?꽦
	int playCount = 0;						// 紐? ?씪?슫?뱶 ?룎吏? ?젙?븯?뒗 蹂??닔

	Game(String p1name, String p2name)
	{
		this.Bp1name = p1name;
		this.Bp2name = p2name;
	}

	// ----------------------------------------------- 게임 실행 부분 ->
	public void init() throws InterruptedException, IOException
	{
		p1.setName(Bp1name);				// ?씠由? ?깮?꽦
		p2.setName(Bp2name);
		playCount = 5;
		p1.setCoin(1000);
		p2.setCoin(1000);					//플레이어 돈

		MiniGameStart();
		//출력 몇개 해서 꾸미기(타이틀, 룰 설명, 등등)
	}
	public void play()
	{
		checkcoin :while(true) {
			for (int i = 0; i < playCount; i++) {
				//플레이어, 딜러 리셋 후 라운드 시작
				RoundStart(i + 1);

				// 돈 걸기
				BetCoin();

				// 카드 섞기
				carddeck.shuffle();

				// 처음에 카드 나눠주기
				giveInitialCard();

				// 21 판단
				while (true) {
					// 카드들과 합 보여주기(이 안에 showcardlist있음)
					displayGame();

					//hit stay묻기
					HitOrStay();

					// 대답이 hit인지 stay인지 판단
					if (p1.getState() != ActorState.Hit && p2.getState() != ActorState.Hit)
						break;
				}

				DealerCard();    // 플레이어 선택이 끝난 후 딜러 룰대로 카드 뽑은 후 버스트 판단

				// 딜러랑 비교, 결과 출력
				Winner(SelectTopPlayers());

				if (p1.getCoin() == 0 || p2.getCoin() == 0)		// 만약 한 라운드가 끝나고 보유 코인이 0이면 게임 바로 종료
					break checkcoin;
			}
		}
	}

	public void end()
	{
		blackjackResult();

		//메인 게임이랑 연결하기(승패에 따라 돈 지급)
	}

// --------------------------------------------------  <- 게임 실행 부분

	private void giveInitialCard()
	{
		for (int i = 0; i < 2; i++)
		{
			p1.AddCard(carddeck.getCard());		// 초기 카드들 배분
			p2.AddCard(carddeck.getCard());
			dealer.AddCard(carddeck.getCard());
		}
	}

	void MiniGameStart() throws InterruptedException, IOException
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("블랙잭 게임에 오신 것을 환영합니다!");
		second();
		System.out.println("룰 설명을 들으시겠습니까? (Y/N)");
		while(true)
		{
			String rule = sc.next();
			if (rule.equals("Y") || rule.equals("y"))
			{
				System.out.println("각 플레이어에게 주어진 코인은 1000코인입니다.\n"
						+ "인원은 플레이어 둘과 딜러로 구성되어 있습니다.\n"
						+ "카드를 한 장 더 받으려면 Hit, 더 받지 않으려면 Stay를 고르세요.\n"
						+ "카드의 합은 21을 넘기지 않고 딜러의 합보다 커야 합니다.\n"
						+ "21이 넘어가면 버스트, 즉 패배하니까 카드를 더 뽑을지 조심해서 선택하세요.\n"
						+ "살아있는 플레이어 중에서는 카드의 합이 큰 사람이 승리합니다.\n"
						+ "총 5번의 라운드로 이루어져 있고, 5라운드 후 코인의 수가 많은 사람이 최종승리합니다.\n");
				System.out.println("게임을 시작하려면 Enter를 눌러주세요.");
				enter();
				System.out.println("게임을 시작합니다.");
				break;
			}
			else if (rule.equals("N") || rule.equals("n"))
			{
				System.out.println("게임을 시작합니다.");
				break;
			}
			else
			{
				System.out.println("Y 혹은 N을 선택해주세요");
			}
		}
	}

	void RoundStart(int round)
	{
		p1.reset();			//초기화
		p2.reset();
		dealer.reset();
		System.out.println("-------------------------------------");
		System.out.printf("%d번째 라운드를 시작합니다.\n", round);		// 라운드 표시
	}

	void displayGame()
	{
		System.out.printf("%s 카드 : ", p1.getName());
		p1.showCardList();			// 카드들 나열, 카드의 합 보여주기
		System.out.printf("%s 카드 : ", p2.getName());
		p2.showCardList();			// "
		System.out.print("딜러 카드: ");
		dealer.showCardList();		// 딜러는 오버라이딩해서 첫 카드만 보여줌
		System.out.println();
	}

	void HitOrStay()	// hit stay 결정하는 메소드
	{
		Scanner sc = new Scanner(System.in);

		if (p1.getState() == ActorState.Hit)	//저번 입력 hit인지 확인(처음 상태는 hit이므로 실행됨)
		{
			while(true)
			{
				System.out.printf("%s, Hit or Stay?(H/S)\n", p1.getName());
				String input = sc.next();
				if (input.equals("H")||input.equals("h"))	// 입력이 hit면
				{
					p1.setState(ActorState.Hit);			// 상태 hit로 바꾸고
					p1.AddCard(carddeck.getCard());			// 카드 더하고
					if (p1.getSum() > 21)					// 합이 21이 넘으면
					{
						p1.setState(ActorState.Bust);		// 상태 bust
						System.out.printf("%s 버스트\n", p1.getName());
					}
					break;
				}
				else if (input.equals("S")||input.equals("s"))	// 입력이 stay면
				{
					p1.setState(ActorState.Stay);				// 상태 stay
					break;
				}
				else
				{
					System.out.println("잘못 입력했습니다. 다시 입력하세요.");
				}
			}
		}

		if (p2.getState() == ActorState.Hit)
		{
			while(true)		// p1 코드와 같음(나중에 시간이 있으면 둘이 메소드 하나로 합치기)
			{
				System.out.printf("%s, Hit or Stay?(H/S)\n", p2.getName());
				String input = sc.next();
				if (input.equals("H")||input.equals("h"))
				{
					p2.setState(ActorState.Hit);
					p2.AddCard(carddeck.getCard());
					if (p2.getSum() > 21)
					{
						p2.setState(ActorState.Bust);
						System.out.printf("%s 버스트\n", p2.getName());
					}
					break;
				}
				else if (input.equals("S")||input.equals("s"))
				{
					p2.setState(ActorState.Stay);
					break;
				}
				else
				{
					System.out.println("잘못 입력했습니다. 다시 입력하세요.");
				}
			}
		}
	}

	void DealerCard()	// 딜러 카드 메소드
	{
		// 17 이상이 될 때까지 카드를 뽑는다.
		while(dealer.getSum() < 17)
		{
			dealer.AddCard(carddeck.getCard());	// 카드 덱에서 하나 뽑아온다.
		}

		if (dealer.getSum() > 21)
		{
			dealer.setState(ActorState.Bust);					// 21 이상이면 버스트
			System.out.println("딜러 버스트");
		}
		else
		{
			System.out.printf("딜러: %d\n", dealer.getSum());		// 21 이하면 출력
		}
	}

	ArrayList<Player> SelectTopPlayers()	// 승자 판단 메소드
	{
		ArrayList<Player> topPlayers = new ArrayList<Player>();	// 가장 높은 플레이어 담는 리스트
		int topScore = 0;										// 카드 합 중 최대인 수 담아놓는 변수

		if (p1.getState() != ActorState.Bust)	// 버스트가 아니면
		{
			topScore = p1.getSum();				// 최대 점수에 담고
			topPlayers.add(p1);					// 리스트에 추가
		}

		if (p2.getState() != ActorState.Bust)
		{
			if (p2.getSum() >= topScore)		// p1보다 p2의 합이 크거나 같으면
			{
				if (p2.getSum() > topScore)		// 만약 크면
				{
					topPlayers.clear();			// 대체하기
				}
				topScore = p2.getSum();			// 같으면 topScore는 그대로일 것이고
				topPlayers.add(p2);				// topPlayers에 p2 추가
			}
		}

		return topPlayers;
	}

	void BetCoin()	// 코인 베팅하는 메소드
	{
		Scanner sc = new Scanner(System.in);
		boolean check = true;

		while(check)
		{
			System.out.printf("%s 보유 코인: %d\n", p1.getName(), p1.getCoin());
			System.out.printf("%s 베팅할 코인: ", p1.getName());
			while (!sc.hasNextInt())
			{
				sc.next();
				System.out.println("숫자를 입력해주세요.");
			}
			int betCoin = sc.nextInt();
			if (betCoin > p1.getCoin())			// 보유 코인 이상 베팅 불가
			{
				System.out.println("코인이 부족합니다.");
			}
			else
			{
				p1.setBetCoin(betCoin);
				p1.setCoin(p1.getCoin() - betCoin);
				check = false;
			}
		}

		check = true;

		while(check)
		{
			System.out.printf("%s 보유 코인: %d\n", p2.getName(), p2.getCoin());
			System.out.printf("%s 베팅할 코인: ", p2.getName());
			while (!sc.hasNextInt())					// 베팅코인이 숫자가 아니면 다시 입력하라고 지시
			{
				sc.next();
				System.out.println("숫자를 입력해주세요.");
			}
			int betCoin = sc.nextInt();
			if (betCoin > p2.getCoin())
			{
				System.out.println("코인이 부족합니다.");
			}
			else
			{
				p2.setBetCoin(betCoin);
				p2.setCoin(p2.getCoin() - betCoin);
				check = false;
			}
		}
	}
	void Winner(ArrayList<Player> topPlayers)
	{
		if (topPlayers.size() == 0)
		{
			System.out.println("딜러 승");
			return;
		}

		for (Player p : topPlayers)
		{
			// 딜러 버스트이거나 플레이어보다 적으면 플레이어 승
			if (dealer.getState() == ActorState.Bust || p.getSum() > dealer.getSum())
			{
				p.setCoin(p.getCoin() + (2 * p.getBetCoin()));
				System.out.println(p.getName() + " 승");
			}
			// 같으면 무승부
			else if (p.getSum() == dealer.getSum())
			{
				p.setCoin(p.getCoin() + p.getBetCoin());
				System.out.println(p.getName() + " 무승부");
			}
			else
				System.out.println("딜러 승");
		}
	}

	void blackjackResult()
	{


		System.out.println("");
		System.out.println("블랙잭 끝");
		System.out.print("최종 결과: ");
		System.out.printf("%s 코인: %d, %s 코인: %d\n",p1.getName(), p1.getCoin(), p2.getName(), p2.getCoin());

		if (p1.getCoin() > p2.getCoin())
		{
			System.out.printf("%s 최종 승리", p1.getName());
			p1win = true;
		}
		else if (p1.getCoin() < p2.getCoin())
		{
			System.out.printf("%s 최종 승리", p2.getName());
			p2win = true;
		}
		else
		{
			System.out.println("무승부");
		}
	}

	public static void second() throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
	}
	public static void enter() throws IOException{
		System.in.read();
	}
}


class CardDeck
{
	ArrayList<Integer> cards = new ArrayList<Integer>();

	public int getCard()
	{
		int firstcard = cards.get(0);
		cards.remove(0);
		return firstcard;
	}

	void shuffle()
	{
		int[] card = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
		for (int i = 0; i < 4; i++)
		{
			for (Integer j : card)
			{
				cards.add(j);
			}
		}
		Collections.shuffle(cards);
	}
}


