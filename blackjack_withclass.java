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

		blackjack.init();	// ���� ó���� �ѹ��� �����ϴ� ���� ����

		blackjack.play();	// ���� ���� 5�� ����

		blackjack.end();	// ���� ���� �� ���� ���ӿ� ���� ������
	}
}

enum ActorState {Hit, Stay, Bust}	// �÷��̾�, ������ ����



class Actor
{
	protected ArrayList<Integer> myCard = new ArrayList<Integer>();	// �ڽ��� ������ �ִ� ī�� ��
	private ActorState state = ActorState.Hit;	// ���� Hit�� �ʱ�ȭ

	public void AddCard(int card)
	{
		myCard.add(card);	// �ڽ��� ī�� ���� ī�� �߰�
	}

	ActorState getState()			{ return state; }
	void setState(ActorState state)	{ this.state = state; }

	public int getSum()		// �ڽ��� ī�� �� ī��� �� ���ϱ�
	{
		int sum = 0;
		for (int i : myCard)
		{
			sum += i;
		}
		return sum;
	}

	void showCardList()		// ī�� �����ֱ�, ī���� ���� �� �����ֱ�
	{
		for (int i : myCard)
		{
			System.out.printf("%2d ", i);
		}
		System.out.printf("��: %d", getSum());
		System.out.println("");
	}

	void reset()				// ���¿� ī�� �� �ʱ�ȭ (�� �� ������ ����)
	{
		state = ActorState.Hit;	// ���� �ʱ�ȭ
		myCard.clear();			// �� �ʱ�ȭ
	}
}

class Player extends Actor
{
	private int coin = 0;		// ���� ����
	private int betCoin = 0;	// ������ ����
	private String name;		// �÷��̾��� �̸�


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
	private int sum = 0;		// ���� ī���� ��

	Dealer() {}

	void setSum(Integer sum)		{ this.sum = sum;}

	@Override
	void showCardList()			// ������ ó���� ī�带 �� �常 �����ֹǷ� Actor���� �������̵�
	{
		System.out.printf("%2d, %s", myCard.get(0), "*");
	}


}

class Game  								//��ü ���� ����
{
	public static boolean p1win = false;
	public static boolean p2win = false;
	String Bp1name;
	String Bp2name;

	Player p1 = new Player();				// p1 �??�� (메인 게임?��?��?�� ?���? 받아?��?���?)
	Player p2 = new Player();				// p2 �??��
	Dealer dealer = new Dealer();			// ?��?�� ?��?��
	CardDeck carddeck = new CardDeck();		// ?���? 카드 ?�� ?��?��
	int playCount = 0;						// �? ?��?��?�� ?���? ?��?��?�� �??��

	Game(String p1name, String p2name)
	{
		this.Bp1name = p1name;
		this.Bp2name = p2name;
	}

	// ----------------------------------------------- ���� ���� �κ� ->
	public void init() throws InterruptedException, IOException
	{
		p1.setName(Bp1name);				// ?���? ?��?��
		p2.setName(Bp2name);
		playCount = 5;
		p1.setCoin(1000);
		p2.setCoin(1000);					//�÷��̾� ��

		MiniGameStart();
		//��� � �ؼ� �ٹ̱�(Ÿ��Ʋ, �� ����, ���)
	}
	public void play()
	{
		checkcoin :while(true) {
			for (int i = 0; i < playCount; i++) {
				//�÷��̾�, ���� ���� �� ���� ����
				RoundStart(i + 1);

				// �� �ɱ�
				BetCoin();

				// ī�� ����
				carddeck.shuffle();

				// ó���� ī�� �����ֱ�
				giveInitialCard();

				// 21 �Ǵ�
				while (true) {
					// ī���� �� �����ֱ�(�� �ȿ� showcardlist����)
					displayGame();

					//hit stay����
					HitOrStay();

					// ����� hit���� stay���� �Ǵ�
					if (p1.getState() != ActorState.Hit && p2.getState() != ActorState.Hit)
						break;
				}

				DealerCard();    // �÷��̾� ������ ���� �� ���� ���� ī�� ���� �� ����Ʈ �Ǵ�

				// ������ ��, ��� ���
				Winner(SelectTopPlayers());

				if (p1.getCoin() == 0 || p2.getCoin() == 0)		// ���� �� ���尡 ������ ���� ������ 0�̸� ���� �ٷ� ����
					break checkcoin;
			}
		}
	}

	public void end()
	{
		blackjackResult();

		//���� �����̶� �����ϱ�(���п� ���� �� ����)
	}

// --------------------------------------------------  <- ���� ���� �κ�

	private void giveInitialCard()
	{
		for (int i = 0; i < 2; i++)
		{
			p1.AddCard(carddeck.getCard());		// �ʱ� ī��� ���
			p2.AddCard(carddeck.getCard());
			dealer.AddCard(carddeck.getCard());
		}
	}

	void MiniGameStart() throws InterruptedException, IOException
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("���� ���ӿ� ���� ���� ȯ���մϴ�!");
		second();
		System.out.println("�� ������ �����ðڽ��ϱ�? (Y/N)");
		while(true)
		{
			String rule = sc.next();
			if (rule.equals("Y") || rule.equals("y"))
			{
				System.out.println("�� �÷��̾�� �־��� ������ 1000�����Դϴ�.\n"
						+ "�ο��� �÷��̾� �Ѱ� ������ �����Ǿ� �ֽ��ϴ�.\n"
						+ "ī�带 �� �� �� �������� Hit, �� ���� �������� Stay�� ������.\n"
						+ "ī���� ���� 21�� �ѱ��� �ʰ� ������ �պ��� Ŀ�� �մϴ�.\n"
						+ "21�� �Ѿ�� ����Ʈ, �� �й��ϴϱ� ī�带 �� ������ �����ؼ� �����ϼ���.\n"
						+ "����ִ� �÷��̾� �߿����� ī���� ���� ū ����� �¸��մϴ�.\n"
						+ "�� 5���� ����� �̷���� �ְ�, 5���� �� ������ ���� ���� ����� �����¸��մϴ�.\n");
				System.out.println("������ �����Ϸ��� Enter�� �����ּ���.");
				enter();
				System.out.println("������ �����մϴ�.");
				break;
			}
			else if (rule.equals("N") || rule.equals("n"))
			{
				System.out.println("������ �����մϴ�.");
				break;
			}
			else
			{
				System.out.println("Y Ȥ�� N�� �������ּ���");
			}
		}
	}

	void RoundStart(int round)
	{
		p1.reset();			//�ʱ�ȭ
		p2.reset();
		dealer.reset();
		System.out.println("-------------------------------------");
		System.out.printf("%d��° ���带 �����մϴ�.\n", round);		// ���� ǥ��
	}

	void displayGame()
	{
		System.out.printf("%s ī�� : ", p1.getName());
		p1.showCardList();			// ī��� ����, ī���� �� �����ֱ�
		System.out.printf("%s ī�� : ", p2.getName());
		p2.showCardList();			// "
		System.out.print("���� ī��: ");
		dealer.showCardList();		// ������ �������̵��ؼ� ù ī�常 ������
		System.out.println();
	}

	void HitOrStay()	// hit stay �����ϴ� �޼ҵ�
	{
		Scanner sc = new Scanner(System.in);

		if (p1.getState() == ActorState.Hit)	//���� �Է� hit���� Ȯ��(ó�� ���´� hit�̹Ƿ� �����)
		{
			while(true)
			{
				System.out.printf("%s, Hit or Stay?(H/S)\n", p1.getName());
				String input = sc.next();
				if (input.equals("H")||input.equals("h"))	// �Է��� hit��
				{
					p1.setState(ActorState.Hit);			// ���� hit�� �ٲٰ�
					p1.AddCard(carddeck.getCard());			// ī�� ���ϰ�
					if (p1.getSum() > 21)					// ���� 21�� ������
					{
						p1.setState(ActorState.Bust);		// ���� bust
						System.out.printf("%s ����Ʈ\n", p1.getName());
					}
					break;
				}
				else if (input.equals("S")||input.equals("s"))	// �Է��� stay��
				{
					p1.setState(ActorState.Stay);				// ���� stay
					break;
				}
				else
				{
					System.out.println("�߸� �Է��߽��ϴ�. �ٽ� �Է��ϼ���.");
				}
			}
		}

		if (p2.getState() == ActorState.Hit)
		{
			while(true)		// p1 �ڵ�� ����(���߿� �ð��� ������ ���� �޼ҵ� �ϳ��� ��ġ��)
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
						System.out.printf("%s ����Ʈ\n", p2.getName());
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
					System.out.println("�߸� �Է��߽��ϴ�. �ٽ� �Է��ϼ���.");
				}
			}
		}
	}

	void DealerCard()	// ���� ī�� �޼ҵ�
	{
		// 17 �̻��� �� ������ ī�带 �̴´�.
		while(dealer.getSum() < 17)
		{
			dealer.AddCard(carddeck.getCard());	// ī�� ������ �ϳ� �̾ƿ´�.
		}

		if (dealer.getSum() > 21)
		{
			dealer.setState(ActorState.Bust);					// 21 �̻��̸� ����Ʈ
			System.out.println("���� ����Ʈ");
		}
		else
		{
			System.out.printf("����: %d\n", dealer.getSum());		// 21 ���ϸ� ���
		}
	}

	ArrayList<Player> SelectTopPlayers()	// ���� �Ǵ� �޼ҵ�
	{
		ArrayList<Player> topPlayers = new ArrayList<Player>();	// ���� ���� �÷��̾� ��� ����Ʈ
		int topScore = 0;										// ī�� �� �� �ִ��� �� ��Ƴ��� ����

		if (p1.getState() != ActorState.Bust)	// ����Ʈ�� �ƴϸ�
		{
			topScore = p1.getSum();				// �ִ� ������ ���
			topPlayers.add(p1);					// ����Ʈ�� �߰�
		}

		if (p2.getState() != ActorState.Bust)
		{
			if (p2.getSum() >= topScore)		// p1���� p2�� ���� ũ�ų� ������
			{
				if (p2.getSum() > topScore)		// ���� ũ��
				{
					topPlayers.clear();			// ��ü�ϱ�
				}
				topScore = p2.getSum();			// ������ topScore�� �״���� ���̰�
				topPlayers.add(p2);				// topPlayers�� p2 �߰�
			}
		}

		return topPlayers;
	}

	void BetCoin()	// ���� �����ϴ� �޼ҵ�
	{
		Scanner sc = new Scanner(System.in);
		boolean check = true;

		while(check)
		{
			System.out.printf("%s ���� ����: %d\n", p1.getName(), p1.getCoin());
			System.out.printf("%s ������ ����: ", p1.getName());
			while (!sc.hasNextInt())
			{
				sc.next();
				System.out.println("���ڸ� �Է����ּ���.");
			}
			int betCoin = sc.nextInt();
			if (betCoin > p1.getCoin())			// ���� ���� �̻� ���� �Ұ�
			{
				System.out.println("������ �����մϴ�.");
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
			System.out.printf("%s ���� ����: %d\n", p2.getName(), p2.getCoin());
			System.out.printf("%s ������ ����: ", p2.getName());
			while (!sc.hasNextInt())					// ���������� ���ڰ� �ƴϸ� �ٽ� �Է��϶�� ����
			{
				sc.next();
				System.out.println("���ڸ� �Է����ּ���.");
			}
			int betCoin = sc.nextInt();
			if (betCoin > p2.getCoin())
			{
				System.out.println("������ �����մϴ�.");
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
			System.out.println("���� ��");
			return;
		}

		for (Player p : topPlayers)
		{
			// ���� ����Ʈ�̰ų� �÷��̾�� ������ �÷��̾� ��
			if (dealer.getState() == ActorState.Bust || p.getSum() > dealer.getSum())
			{
				p.setCoin(p.getCoin() + (2 * p.getBetCoin()));
				System.out.println(p.getName() + " ��");
			}
			// ������ ���º�
			else if (p.getSum() == dealer.getSum())
			{
				p.setCoin(p.getCoin() + p.getBetCoin());
				System.out.println(p.getName() + " ���º�");
			}
			else
				System.out.println("���� ��");
		}
	}

	void blackjackResult()
	{


		System.out.println("");
		System.out.println("���� ��");
		System.out.print("���� ���: ");
		System.out.printf("%s ����: %d, %s ����: %d\n",p1.getName(), p1.getCoin(), p2.getName(), p2.getCoin());

		if (p1.getCoin() > p2.getCoin())
		{
			System.out.printf("%s ���� �¸�", p1.getName());
			p1win = true;
		}
		else if (p1.getCoin() < p2.getCoin())
		{
			System.out.printf("%s ���� �¸�", p2.getName());
			p2win = true;
		}
		else
		{
			System.out.println("���º�");
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


