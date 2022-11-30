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

		blackjack.init();	// °ÔÀÓ Ã³À½¿¡ ÇÑ¹ø¸¸ ½ÇÇàÇÏ´Â ³»¿ë ½ÇÇà

		blackjack.play();	// ºí·¢Àè °ÔÀÓ 5¹ø µ¹±â

		blackjack.end();	// °ÔÀÓ Á¾·á ÈÄ ¸ŞÀÎ °ÔÀÓ¿¡ ³»¿ë º¸³»±â
	}
}

enum ActorState {Hit, Stay, Bust}	// ÇÃ·¹ÀÌ¾î, µô·¯ÀÇ »óÅÂ



class Actor
{
	protected ArrayList<Integer> myCard = new ArrayList<Integer>();	// ÀÚ½ÅÀÌ °¡Áö°í ÀÖ´Â Ä«µå µ¦
	private ActorState state = ActorState.Hit;	// »óÅÂ Hit·Î ÃÊ±âÈ­

	public void AddCard(int card)
	{
		myCard.add(card);	// ÀÚ½ÅÀÇ Ä«µå µ¦¿¡ Ä«µå Ãß°¡
	}

	ActorState getState()			{ return state; }
	void setState(ActorState state)	{ this.state = state; }

	public int getSum()		// ÀÚ½ÅÀÇ Ä«µå µ¦ Ä«µåµé ÇÕ ±¸ÇÏ±â
	{
		int sum = 0;
		for (int i : myCard)
		{
			sum += i;
		}
		return sum;
	}

	void showCardList()		// Ä«µå º¸¿©ÁÖ±â, Ä«µåÀÇ ¼ıÀÚ ÇÕ º¸¿©ÁÖ±â
	{
		for (int i : myCard)
		{
			System.out.printf("%2d ", i);
		}
		System.out.printf("ÇÕ: %d", getSum());
		System.out.println("");
	}

	void reset()				// »óÅÂ¿Í Ä«µå µ¦ ÃÊ±âÈ­ (ÇÑ ÆÇ ³¡³ª¸é ½ÇÇà)
	{
		state = ActorState.Hit;	// »óÅÂ ÃÊ±âÈ­
		myCard.clear();			// µ¦ ÃÊ±âÈ­
	}
}

class Player extends Actor
{
	private int coin = 0;		// º¸À¯ ÄÚÀÎ
	private int betCoin = 0;	// º£ÆÃÇÒ ÄÚÀÎ
	private String name;		// ÇÃ·¹ÀÌ¾îÀÇ ÀÌ¸§


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
	private int sum = 0;		// µô·¯ Ä«µåÀÇ ÇÕ

	Dealer() {}

	void setSum(Integer sum)		{ this.sum = sum;}

	@Override
	void showCardList()			// µô·¯´Â Ã³À½¿¡ Ä«µå¸¦ ÇÑ Àå¸¸ º¸¿©ÁÖ¹Ç·Î Actor¿¡¼­ ¿À¹ö¶óÀÌµù
	{
		System.out.printf("%2d, %s", myCard.get(0), "*");
	}


}

class Game  								//ÀüÃ¼ °ÔÀÓ °üÇÒ
{
	public static boolean p1win = false;
	public static boolean p2win = false;
	String Bp1name;
	String Bp2name;
<<<<<<< Updated upstream
	Player p1 = new Player(Bp1name);			// ÀÌ¸§°ú ÇÔ²² p1 ÁöÁ¤ (¸ŞÀÎ °ÔÀÓ¿¡¼­ÀÇ ÀÌ¸§ ¹Ş¾Æ¿Àµµ·Ï)
	Player p2 = new Player(Bp2name);			// ÀÌ¸§°ú ÇÔ²² p2 ÁöÁ¤
	Dealer dealer = new Dealer();			// µô·¯ »ı¼º
	CardDeck carddeck = new CardDeck();		// ÀüÃ¼ Ä«µå µ¦ »ı¼º
	int playCount = 0;						// ¸î ¶ó¿îµå µ¹Áö Á¤ÇÏ´Â º¯¼ö
=======
	Player p1 = new Player();				// p1 ì§€ì • (ë©”ì¸ ê²Œì„ì—ì„œì˜ ì´ë¦„ ë°›ì•„ì˜¤ë„ë¡)
	Player p2 = new Player();				// p2 ì§€ì •
	Dealer dealer = new Dealer();			// ë”œëŸ¬ ìƒì„±
	CardDeck carddeck = new CardDeck();		// ì „ì²´ ì¹´ë“œ ë± ìƒì„±
	int playCount = 0;						// ëª‡ ë¼ìš´ë“œ ëŒì§€ ì •í•˜ëŠ” ë³€ìˆ˜
>>>>>>> Stashed changes

	Game(String p1name, String p2name)
	{
		this.Bp1name = p1name;
		this.Bp2name = p2name;
	}

	// ----------------------------------------------- °ÔÀÓ ½ÇÇà ºÎºĞ ->
	public void init() throws InterruptedException, IOException
	{
		p1.setName(Bp1name);				// ì´ë¦„ ìƒì„±
		p2.setName(Bp2name);
		playCount = 5;
		p1.setCoin(1000);
<<<<<<< Updated upstream
		p2.setCoin(1000);					//ÇÃ·¹ÀÌ¾î µ·
=======
		p2.setCoin(1000);					// í”Œë ˆì´ì–´ ëˆ
>>>>>>> Stashed changes
		MiniGameStart();
		//Ãâ·Â ¸î°³ ÇØ¼­ ²Ù¹Ì±â(Å¸ÀÌÆ², ·ê ¼³¸í, µîµî)
	}
	public void play()
	{
		checkcoin :while(true) {
			for (int i = 0; i < playCount; i++) {
				//ÇÃ·¹ÀÌ¾î, µô·¯ ¸®¼Â ÈÄ ¶ó¿îµå ½ÃÀÛ
				RoundStart(i + 1);

				// µ· °É±â
				BetCoin();

				// Ä«µå ¼¯±â
				carddeck.shuffle();

				// Ã³À½¿¡ Ä«µå ³ª´²ÁÖ±â
				giveInitialCard();

				// 21 ÆÇ´Ü
				while (true) {
					// Ä«µåµé°ú ÇÕ º¸¿©ÁÖ±â(ÀÌ ¾È¿¡ showcardlistÀÖÀ½)
					displayGame();

					//hit stay¹¯±â
					HitOrStay();

					// ´ë´äÀÌ hitÀÎÁö stayÀÎÁö ÆÇ´Ü
					if (p1.getState() != ActorState.Hit && p2.getState() != ActorState.Hit)
						break;
				}

				DealerCard();    // ÇÃ·¹ÀÌ¾î ¼±ÅÃÀÌ ³¡³­ ÈÄ µô·¯ ·ê´ë·Î Ä«µå »ÌÀº ÈÄ ¹ö½ºÆ® ÆÇ´Ü

				// µô·¯¶û ºñ±³, °á°ú Ãâ·Â
				Winner(SelectTopPlayers());

				if (p1.getCoin() == 0 || p2.getCoin() == 0)		// ¸¸¾à ÇÑ ¶ó¿îµå°¡ ³¡³ª°í º¸À¯ ÄÚÀÎÀÌ 0ÀÌ¸é °ÔÀÓ ¹Ù·Î Á¾·á
					break checkcoin;
			}
		}
	}

	public void end()
	{
		blackjackResult();

		//¸ŞÀÎ °ÔÀÓÀÌ¶û ¿¬°áÇÏ±â(½ÂÆĞ¿¡ µû¶ó µ· Áö±Ş)
	}

// --------------------------------------------------  <- °ÔÀÓ ½ÇÇà ºÎºĞ

	private void giveInitialCard()
	{
		for (int i = 0; i < 2; i++)
		{
			p1.AddCard(carddeck.getCard());		// ÃÊ±â Ä«µåµé ¹èºĞ
			p2.AddCard(carddeck.getCard());
			dealer.AddCard(carddeck.getCard());
		}
	}

	void MiniGameStart() throws InterruptedException, IOException
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("ºí·¢Àè °ÔÀÓ¿¡ ¿À½Å °ÍÀ» È¯¿µÇÕ´Ï´Ù!");
		second();
		System.out.println("·ê ¼³¸íÀ» µéÀ¸½Ã°Ú½À´Ï±î? (Y/N)");
		while(true)
		{
			String rule = sc.next();
			if (rule.equals("Y") || rule.equals("y"))
			{
				System.out.println("°¢ ÇÃ·¹ÀÌ¾î¿¡°Ô ÁÖ¾îÁø ÄÚÀÎÀº 1000ÄÚÀÎÀÔ´Ï´Ù.\n"
						+ "ÀÎ¿øÀº ÇÃ·¹ÀÌ¾î µÑ°ú µô·¯·Î ±¸¼ºµÇ¾î ÀÖ½À´Ï´Ù.\n"
						+ "Ä«µå¸¦ ÇÑ Àå ´õ ¹ŞÀ¸·Á¸é Hit, ´õ ¹ŞÁö ¾ÊÀ¸·Á¸é Stay¸¦ °í¸£¼¼¿ä.\n"
						+ "Ä«µåÀÇ ÇÕÀº 21À» ³Ñ±âÁö ¾Ê°í µô·¯ÀÇ ÇÕº¸´Ù Ä¿¾ß ÇÕ´Ï´Ù.\n"
						+ "21ÀÌ ³Ñ¾î°¡¸é ¹ö½ºÆ®, Áï ÆĞ¹èÇÏ´Ï±î Ä«µå¸¦ ´õ »ÌÀ»Áö Á¶½ÉÇØ¼­ ¼±ÅÃÇÏ¼¼¿ä.\n"
						+ "»ì¾ÆÀÖ´Â ÇÃ·¹ÀÌ¾î Áß¿¡¼­´Â Ä«µåÀÇ ÇÕÀÌ Å« »ç¶÷ÀÌ ½Â¸®ÇÕ´Ï´Ù.\n"
						+ "ÃÑ 5¹øÀÇ ¶ó¿îµå·Î ÀÌ·ç¾îÁ® ÀÖ°í, 5¶ó¿îµå ÈÄ ÄÚÀÎÀÇ ¼ö°¡ ¸¹Àº »ç¶÷ÀÌ ÃÖÁ¾½Â¸®ÇÕ´Ï´Ù.\n");
				System.out.println("°ÔÀÓÀ» ½ÃÀÛÇÏ·Á¸é Enter¸¦ ´­·¯ÁÖ¼¼¿ä.");
				enter();
				System.out.println("°ÔÀÓÀ» ½ÃÀÛÇÕ´Ï´Ù.");
				break;
			}
			else if (rule.equals("N") || rule.equals("n"))
			{
				System.out.println("°ÔÀÓÀ» ½ÃÀÛÇÕ´Ï´Ù.");
				break;
			}
			else
			{
				System.out.println("Y È¤Àº NÀ» ¼±ÅÃÇØÁÖ¼¼¿ä");
			}
		}
	}

	void RoundStart(int round)
	{
		p1.reset();			//ÃÊ±âÈ­
		p2.reset();
		dealer.reset();
		System.out.println("-------------------------------------");
		System.out.printf("%d¹øÂ° ¶ó¿îµå¸¦ ½ÃÀÛÇÕ´Ï´Ù.\n", round);		// ¶ó¿îµå Ç¥½Ã
	}

	void displayGame()
	{
		System.out.printf("%s Ä«µå : ", p1.getName());
		p1.showCardList();			// Ä«µåµé ³ª¿­, Ä«µåÀÇ ÇÕ º¸¿©ÁÖ±â
		System.out.printf("%s Ä«µå : ", p2.getName());
		p2.showCardList();			// "
		System.out.print("µô·¯ Ä«µå: ");
		dealer.showCardList();		// µô·¯´Â ¿À¹ö¶óÀÌµùÇØ¼­ Ã¹ Ä«µå¸¸ º¸¿©ÁÜ
		System.out.println();
	}

	void HitOrStay()	// hit stay °áÁ¤ÇÏ´Â ¸Ş¼Òµå
	{
		Scanner sc = new Scanner(System.in);

		if (p1.getState() == ActorState.Hit)	//Àú¹ø ÀÔ·Â hitÀÎÁö È®ÀÎ(Ã³À½ »óÅÂ´Â hitÀÌ¹Ç·Î ½ÇÇàµÊ)
		{
			while(true)
			{
				System.out.printf("%s, Hit or Stay?(H/S)\n", p1.getName());
				String input = sc.next();
				if (input.equals("H")||input.equals("h"))	// ÀÔ·ÂÀÌ hit¸é
				{
					p1.setState(ActorState.Hit);			// »óÅÂ hit·Î ¹Ù²Ù°í
					p1.AddCard(carddeck.getCard());			// Ä«µå ´õÇÏ°í
					if (p1.getSum() > 21)					// ÇÕÀÌ 21ÀÌ ³ÑÀ¸¸é
					{
						p1.setState(ActorState.Bust);		// »óÅÂ bust
						System.out.printf("%s ¹ö½ºÆ®\n", p1.getName());
					}
					break;
				}
				else if (input.equals("S")||input.equals("s"))	// ÀÔ·ÂÀÌ stay¸é
				{
					p1.setState(ActorState.Stay);				// »óÅÂ stay
					break;
				}
				else
				{
					System.out.println("Àß¸ø ÀÔ·ÂÇß½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
				}
			}
		}

		if (p2.getState() == ActorState.Hit)
		{
			while(true)		// p1 ÄÚµå¿Í °°À½(³ªÁß¿¡ ½Ã°£ÀÌ ÀÖÀ¸¸é µÑÀÌ ¸Ş¼Òµå ÇÏ³ª·Î ÇÕÄ¡±â)
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
						System.out.printf("%s ¹ö½ºÆ®\n", p2.getName());
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
					System.out.println("Àß¸ø ÀÔ·ÂÇß½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
				}
			}
		}
	}

	void DealerCard()	// µô·¯ Ä«µå ¸Ş¼Òµå
	{
		// 17 ÀÌ»óÀÌ µÉ ¶§±îÁö Ä«µå¸¦ »Ì´Â´Ù.
		while(dealer.getSum() < 17)
		{
			dealer.AddCard(carddeck.getCard());	// Ä«µå µ¦¿¡¼­ ÇÏ³ª »Ì¾Æ¿Â´Ù.
		}

		if (dealer.getSum() > 21)
		{
			dealer.setState(ActorState.Bust);					// 21 ÀÌ»óÀÌ¸é ¹ö½ºÆ®
			System.out.println("µô·¯ ¹ö½ºÆ®");
		}
		else
		{
			System.out.printf("µô·¯: %d\n", dealer.getSum());		// 21 ÀÌÇÏ¸é Ãâ·Â
		}
	}

	ArrayList<Player> SelectTopPlayers()	// ½ÂÀÚ ÆÇ´Ü ¸Ş¼Òµå
	{
		ArrayList<Player> topPlayers = new ArrayList<Player>();	// °¡Àå ³ôÀº ÇÃ·¹ÀÌ¾î ´ã´Â ¸®½ºÆ®
		int topScore = 0;										// Ä«µå ÇÕ Áß ÃÖ´ëÀÎ ¼ö ´ã¾Æ³õ´Â º¯¼ö

		if (p1.getState() != ActorState.Bust)	// ¹ö½ºÆ®°¡ ¾Æ´Ï¸é
		{
			topScore = p1.getSum();				// ÃÖ´ë Á¡¼ö¿¡ ´ã°í
			topPlayers.add(p1);					// ¸®½ºÆ®¿¡ Ãß°¡
		}

		if (p2.getState() != ActorState.Bust)
		{
			if (p2.getSum() >= topScore)		// p1º¸´Ù p2ÀÇ ÇÕÀÌ Å©°Å³ª °°À¸¸é
			{
				if (p2.getSum() > topScore)		// ¸¸¾à Å©¸é
				{
					topPlayers.clear();			// ´ëÃ¼ÇÏ±â
				}
				topScore = p2.getSum();			// °°À¸¸é topScore´Â ±×´ë·ÎÀÏ °ÍÀÌ°í
				topPlayers.add(p2);				// topPlayers¿¡ p2 Ãß°¡
			}
		}

		return topPlayers;
	}

	void BetCoin()	// ÄÚÀÎ º£ÆÃÇÏ´Â ¸Ş¼Òµå
	{
		Scanner sc = new Scanner(System.in);
		boolean check = true;

		while(check)
		{
			System.out.printf("%s º¸À¯ ÄÚÀÎ: %d\n", p1.getName(), p1.getCoin());
			System.out.printf("%s º£ÆÃÇÒ ÄÚÀÎ: ", p1.getName());
			while (!sc.hasNextInt())
			{
				sc.next();
				System.out.println("¼ıÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
			}
			int betCoin = sc.nextInt();
			if (betCoin > p1.getCoin())			// º¸À¯ ÄÚÀÎ ÀÌ»ó º£ÆÃ ºÒ°¡
			{
				System.out.println("ÄÚÀÎÀÌ ºÎÁ·ÇÕ´Ï´Ù.");
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
			System.out.printf("%s º¸À¯ ÄÚÀÎ: %d\n", p2.getName(), p2.getCoin());
			System.out.printf("%s º£ÆÃÇÒ ÄÚÀÎ: ", p2.getName());
			while (!sc.hasNextInt())					// º£ÆÃÄÚÀÎÀÌ ¼ıÀÚ°¡ ¾Æ´Ï¸é ´Ù½Ã ÀÔ·ÂÇÏ¶ó°í Áö½Ã
			{
				sc.next();
				System.out.println("¼ıÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
			}
			int betCoin = sc.nextInt();
			if (betCoin > p2.getCoin())
			{
				System.out.println("ÄÚÀÎÀÌ ºÎÁ·ÇÕ´Ï´Ù.");
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
			System.out.println("µô·¯ ½Â");
			return;
		}

		for (Player p : topPlayers)
		{
			// µô·¯ ¹ö½ºÆ®ÀÌ°Å³ª ÇÃ·¹ÀÌ¾îº¸´Ù ÀûÀ¸¸é ÇÃ·¹ÀÌ¾î ½Â
			if (dealer.getState() == ActorState.Bust || p.getSum() > dealer.getSum())
			{
				p.setCoin(p.getCoin() + (2 * p.getBetCoin()));
				System.out.println(p.getName() + " ½Â");
			}
			// °°À¸¸é ¹«½ÂºÎ
			else if (p.getSum() == dealer.getSum())
			{
				p.setCoin(p.getCoin() + p.getBetCoin());
				System.out.println(p.getName() + " ¹«½ÂºÎ");
			}
			else
				System.out.println("µô·¯ ½Â");
		}
	}

	void blackjackResult()
	{


		System.out.println("");
		System.out.println("ºí·¢Àè ³¡");
		System.out.print("ÃÖÁ¾ °á°ú: ");
		System.out.printf("%s ÄÚÀÎ: %d, %s ÄÚÀÎ: %d\n",p1.getName(), p1.getCoin(), p2.getName(), p2.getCoin());

		if (p1.getCoin() > p2.getCoin())
		{
			System.out.printf("%s ÃÖÁ¾ ½Â¸®", p1.getName());
			p1win = true;
		}
		else if (p1.getCoin() < p2.getCoin())
		{
			System.out.printf("%s ÃÖÁ¾ ½Â¸®", p2.getName());
			p2win = true;
		}
		else
		{
			System.out.println("¹«½ÂºÎ");
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


