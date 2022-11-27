package AD_Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
enum ActorState {Hit, Stay, Bust}

public class blackjack_withclass
{
	public static void main(String[] args)
	{
		Game blackjack = new Game();

		blackjack.init();

		blackjack.play();

		blackjack.end();
	}
}

class Actor
{
	protected ArrayList<Integer> myCard = new ArrayList<Integer>();
	private ActorState state = ActorState.Hit;
	public void AddCard(int card)
	{
		myCard.add(card);
	}
	ActorState getState()			{ return state; }
	void setState(ActorState state)	{ this.state = state; }
	public int getSum()
	{
		int sum = 0;
		for (int i : myCard)
		{
			sum += i;
		}
		return sum;
	}
	void showCardList()
	{
		for (int i : myCard)
		{
			System.out.printf("%2d ", i);
		}
		System.out.printf("합: %d", getSum());
		System.out.println();
	}
	void reset()
	{
		state = ActorState.Hit;
		myCard.clear();
	}
}
class Player extends Actor
{
	private final String name;
	private int coin = 0;
	private int betCoin = 0;
	Player(String name) 
	{
		this.name = name;
	}
	
	String getName()				{ return this.name; }
	
	int getCoin()					{ return this.coin; }
	void setCoin(int coin)			{ this.coin = coin;	}
	
	int getBetCoin()				{ return this.betCoin; }
	void setBetCoin(int betCoin)	{ this.betCoin = betCoin; }
}
class Dealer extends Actor
{

	Dealer() {}
	void setSum(Integer sum)		{
		int sum1 = sum;
	}
	@Override
	void showCardList()
	{
		System.out.printf("%2d, %s", myCard.get(0), "*");
	}
}

class Game  								//전체 게임 관할
{
	Player p1 = new Player("P1");
	Player p2 = new Player("P2");
	Dealer dealer = new Dealer();
	CardDeck carddeck = new CardDeck();
	int playCount = 0;
	Game() {}
	public void init()
	{
		playCount = 5;
		p1.setCoin(1000);
		p2.setCoin(1000);					//플레이어 돈
											//출력 몇개 해서 꾸미기(타이틀, 룰 설명, 등등)
	}
	public void play()
	{
		for (int i = 0; i<playCount; i++)
		{
			//플레이어, 딜러 리셋 후 라운드 시작
			RoundStart(i+1);
			
			BetCoin();
			
			carddeck.shuffle();				//카드 섞기
			
			// 카드 나눠주기
			giveInitialCard();
			
			// 21 판단
			do {
				displayGame();

				//hit stay묻기
				HitOrStay();

				//판단
			} while (p1.getState() == ActorState.Hit || p2.getState() == ActorState.Hit);
			
			DealerCard();
			
			// 딜러랑 비교, 결과 출력
			Winner(SelectTopPlayers());
		}
	}
	public void end()
	{
		System.out.println("블랙잭 끝");
		System.out.printf("p1 코인: %d p2 코인: %d", p1.getCoin(), p2.getCoin());
		
		//메인 게임이랑 연결하기
	}
	private void giveInitialCard()
	{
		for (int i = 0; i < 2; i++)
		{
			p1.AddCard(carddeck.getCard());
			p2.AddCard(carddeck.getCard());
			dealer.AddCard(carddeck.getCard());
		}
	}
	void RoundStart(int round)
	{
		p1.reset();
		p2.reset();
		dealer.reset();
		System.out.println("-------------------------------------");
		System.out.printf("%d번째 라운드를 시작합니다.\n", round);
	}
	void displayGame()
	{
		System.out.print("p1 카드 : ");
		p1.showCardList();
		System.out.print("p2 카드 : ");
		p2.showCardList();
		System.out.print("딜러 카드: ");
		dealer.showCardList();
		System.out.println();
	}
	void HitOrStay()
	{
		Scanner sc = new Scanner(System.in);
		if (p1.getState() == ActorState.Hit)
		{
			while(true)
			{
				System.out.println("p1, Hit or Stay?(H/S)");
				String input = sc.next();
				if (input.equals("H")||input.equals("h"))
				{
					p1.setState(ActorState.Hit);
					p1.AddCard(carddeck.getCard());
					if (p1.getSum() > 21)
					{
						p1.setState(ActorState.Bust);
						System.out.println("p1 버스트");
					}
					break;
				}
				else if (input.equals("S")||input.equals("s"))
				{
					p1.setState(ActorState.Stay);
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
			while(true)
			{
				System.out.println("p2, Hit or Stay?(H/S)");
				String input = sc.next();
				if (input.equals("H")||input.equals("h"))
				{
					p2.setState(ActorState.Hit);
					p2.AddCard(carddeck.getCard());
					if (p2.getSum() > 21)
					{
						p2.setState(ActorState.Bust);
						System.out.println("p2 버스트");
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
	
	void DealerCard()
	{
		// 17 이상이 될 때까지 카드를 뽑는다.
		while(dealer.getSum() < 17)
		{
			dealer.AddCard(carddeck.getCard());	// 카드 덱에서 하나 뽑아온다.
		}
		
		if (dealer.getSum() > 21)
		{
			dealer.setState(ActorState.Bust);
			System.out.println("딜러 버스트");
		}
		else
		{
			System.out.printf("딜러: %d\n", dealer.getSum());
		}
	}
	
	ArrayList<Player> SelectTopPlayers()
	{
		ArrayList<Player> topPlayers = new ArrayList<Player>();
		int topScore = 0;
		
		if (p1.getState() != ActorState.Bust)
		{
			topScore = p1.getSum();
			topPlayers.add(p1);
		}
		
		if (p2.getState() != ActorState.Bust)
		{
			if (p2.getSum() >= topScore)
			{
				if (p2.getSum() > topScore)
				{
					topPlayers.clear();
				}
				topScore = p2.getSum();
			}
		}
		
		return topPlayers;
	}
	
	void BetCoin()
	{
		Scanner sc = new Scanner(System.in);
		boolean check = true;
		
		while(check)
		{
			System.out.println("p1 보유 코인" + p1.getCoin());
			System.out.print("p1 베팅할 코인: ");
			int betCoin = sc.nextInt();
			if (betCoin > p1.getCoin())
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
			System.out.println("p2 보유 코인" + p2.getCoin());
			System.out.print("p2 베팅할 코인: ");
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
				p.setCoin(p.getCoin() + 2 * p.getBetCoin());
				System.out.println(p.getName() + " 승");
			}
			// 같으면 무승부
			else if (p.getSum() == dealer.getSum())
			{
				p.setCoin(p.getSum() + p.getBetCoin());
				System.out.println(p.getName() + " 무승부");
			}
			else
				System.out.println("딜러 승");
		}
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
