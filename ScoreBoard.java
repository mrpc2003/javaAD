package AD_Project;

public class ScoreBoard {
	int p1Aces=0, 					p2Aces=0;
	int p1Deuces=0, 				p2Deuces=0;
	int p1Threes=0, 				p2Threes=0;
	int p1Fours=0,  				p2Fours=0;
	int p1Fives=0,  				p2Fives=0;
	int p1Sixes=0,  				p2Sixes=0;
	int p1Subtotal=0,				p2Subtotal=0;
	int p1Subtotal2=0,				p2Subtotal2=0;
	int p1Choice=0,					p2Choice=0;
	int p1FourOfKind=0,				p2FourOfKind=0;
	int p1FullHouse=0,   			p2FullHouse=0;
	int p1SmallStraight=0,			p2SmallStraight=0;
	int p1LargeStraight=0,			p2LargeStraight=0;
	int p1Yacht=0,					p2Yacht=0;
	int p1Total=0,					p2Total=0;

	String p1AcesS="  ", 			p2AcesS="  ";
	String p1DeucesS="  ", 			p2DeucesS="  ";
	String p1ThreesS="  ", 			p2ThreesS="  ";
	String p1FoursS="  ",  			p2FoursS="  ";
	String p1FivesS="  ",  			p2FivesS="  ";
	String p1SixesS="  ",  			p2SixesS="  ";
	String p1SubtotalS="  ",		p2SubtotalS="  ";
	String p1Subtotal2S="  ",		p2Subtotal2S="  ";
	String p1ChoiceS="  ",			p2ChoiceS="  ";
	String p1FourOfKindS="  ",		p2FourOfKindS="  ";
	String p1FullHouseS="  ",   	p2FullHouseS="  ";
	String p1SmallStraightS="  ",	p2SmallStraightS="  ";
	String p1LargeStraightS="  ",	p2LargeStraightS="  ";
	String p1YachtS="  ",			p2YachtS="  ";
	String p1TotalS="  ",			p2TotalS="  ";

	String p1BarSpace="--",         p2BarSpace="--";

	boolean p1AcesTF=true, 			p2AcesTF=true;
	boolean p1DeucesTF=true, 		p2DeucesTF=true;
	boolean p1ThreesTF=true, 		p2ThreesTF=true;
	boolean p1FoursTF=true,  		p2FoursTF=true;
	boolean p1FivesTF=true,  		p2FivesTF=true;
	boolean p1SixesTF=true,  		p2SixesTF=true;
	boolean p1ChoiceTF=true,		p2ChoiceTF=true;
	boolean p1FourOfKindTF=true,	p2FourOfKindTF=true;
	boolean p1FullHouseTF=true,   	p2FullHouseTF=true;
	boolean p1SmallStraightTF=true,	p2SmallStraightTF=true;
	boolean p1LargeStraightTF=true,	p2LargeStraightTF=true;
	boolean p1YachtTF=true,			p2YachtTF=true;

	String p1AcesTFP=" ", 			p2AcesTFP=" ";
	String p1DeucesTFP=" ", 		p2DeucesTFP=" ";
	String p1ThreesTFP=" ", 		p2ThreesTFP=" ";
	String p1FoursTFP=" ",  		p2FoursTFP=" ";
	String p1FivesTFP=" ",  		p2FivesTFP=" ";
	String p1SixesTFP=" ",  		p2SixesTFP=" ";
	String p1ChoiceTFP=" ",			p2ChoiceTFP=" ";
	String p1FourOfKindTFP=" ",		p2FourOfKindTFP=" ";
	String p1FullHouseTFP=" ",   	p2FullHouseTFP=" ";
	String p1SmallStraightTFP=" ",	p2SmallStraightTFP=" ";
	String p1LargeStraightTFP=" ",	p2LargeStraightTFP=" ";
	String p1YachtTFP=" ",			p2YachtTFP=" ";
	String p1TotalTFP=" ",			p2TotalTFP=" ";

	void printScoreBoard() { //점수판 출력
		System.out.println("┏------------------------------------┓\n"
				+"|                  <1p>       <2p>   |\n"
				+"| Aces          :   "+p1Aces+p1AcesS+p1AcesTFP+"  ||   "+p2Aces+p2AcesS+p2AcesTFP+"  |\n"
				+"| Deuces        :   "+p1Deuces+p1DeucesS+p1DeucesTFP+"  ||   "+p2Deuces+p2DeucesS+p2DeucesTFP+"  |\n"
				+"| Threes        :   "+p1Threes+p1ThreesS+p1ThreesTFP+"  ||   "+p2Threes+p2ThreesS+p2ThreesTFP+"  |\n"
				+"| Fours         :   "+p1Fours+p1FoursS+p1FoursTFP+"  ||   "+p2Fours+p2FoursS+p2FoursTFP+"  |\n"
				+"| Fives         :   "+p1Fives+p1FivesS+p1FivesTFP+"  ||   "+p2Fives+p2FivesS+p2FivesTFP+"  |\n"
				+"| Sixes         :   "+p1Sixes+p1SixesS+p1SixesTFP+"  ||   "+p2Sixes+p2SixesS+p2SixesTFP+"  |\n"
				+"| +35Quest      :   "+p1Subtotal+"/63"+p1SubtotalS+"||   "+p2Subtotal+"/63"+p2SubtotalS+"|\n"
				+"┗-(Subtotal)-------<"+p1Subtotal2+">--"+p1BarSpace+"----<"+p2Subtotal2+">"+p2BarSpace+"--┛\n"
				+"┏------------------------------------┓\n"
				+"| Choice        :   "+p1Choice+p1ChoiceS+p1ChoiceTFP+"  ||   "+p2Choice+p2ChoiceS+p2ChoiceTFP+"  |\n"
				+"| 4 of a kind   :   "+p1FourOfKind+p1FourOfKindS+p1FourOfKindTFP+"  ||   "+p2FourOfKind+p2FourOfKindS+p2FourOfKindTFP+"  |\n"
				+"| Full House    :   "+p1FullHouse+p1FullHouseS+p1FullHouseTFP+"  ||   "+p2FullHouse+p2FullHouseS+p2FullHouseTFP+"  |\n"
				+"| SmallStraight :   "+p1SmallStraight+p1SmallStraightS+p1SmallStraightTFP+"  ||   "+p2SmallStraight+p2SmallStraightS+p2SmallStraightTFP+"  |\n"
				+"| LargeStraight :   "+p1LargeStraight+p1LargeStraightS+p1LargeStraightTFP+"  ||   "+p2LargeStraight+p2LargeStraightS+p2LargeStraightTFP+"  |\n"
				+"| Yacht         :   "+p1Yacht+p1YachtS+p1YachtTFP+"  ||   "+p2Yacht+p2YachtS+p2YachtTFP+"  |\n"
				+"| total         :   "+p1Total+p1TotalS+"   ||   "+p2Total+p2TotalS+"   |\n"
				+"┗------------------------------------┛\n");
	}
	void totalRefresh() { //점수판 초기화
		p1Subtotal=p1Aces+p1Deuces+p1Threes+p1Fours+p1Fives+p1Sixes;
		if(p1Subtotal>=35) {
			p1Subtotal2=p1Subtotal+35;
		} else {
			p1Subtotal2=p1Subtotal;
		}
		p2Subtotal=p2Aces+p2Deuces+p2Threes+p2Fours+p2Fives+p2Sixes;
		if(p2Subtotal>=35) {
			p2Subtotal2=p2Subtotal+35;
		} else {
			p2Subtotal2=p2Subtotal;
		}

		p1Total=p1Subtotal2+p1Choice+p1FourOfKind+p1FullHouse+p1SmallStraight+p1LargeStraight+p1Yacht;
		p2Total=p2Subtotal2+p2Choice+p2FourOfKind+p2FullHouse+p2SmallStraight+p2LargeStraight+p2Yacht;

		if(p1Aces>=10) {p1AcesS=" ";}					if(p2Aces>=10) {p2AcesS=" ";}
		if(p1Deuces>=10) {p1DeucesS=" ";}				if(p2Deuces>=10) {p2DeucesS=" ";}
		if(p1Threes>=10) {p1ThreesS=" ";}				if(p2Threes>=10) {p2ThreesS=" ";}
		if(p1Fours>=10) {p1FoursS=" ";}					if(p2Fours>=10) {p2FoursS=" ";}
		if(p1Fives>=10) {p1FivesS=" ";}					if(p2Fives>=10) {p2FivesS=" ";}
		if(p1Sixes>=10) {p1SixesS=" ";}					if(p2Sixes>=10) {p2SixesS=" ";}
		if(p1Subtotal>=10) {p1SubtotalS=" ";}			if(p2Subtotal>=10) {p2SubtotalS=" ";}
		if(p1Choice>=10) {p1ChoiceS=" ";}				if(p2Choice>=10) {p2ChoiceS=" ";}
		if(p1FourOfKind>=10) {p1FourOfKindS=" ";}		if(p2FourOfKind>=10) {p2FourOfKindS=" ";}
		if(p1FullHouse>=10) {p1FullHouseS=" ";}			if(p2FullHouse>=10) {p2FullHouseS=" ";}
		if(p1SmallStraight>=10) {p1SmallStraightS=" ";}	if(p2SmallStraight>=10) {p2SmallStraightS=" ";}
		if(p1LargeStraight>=10) {p1LargeStraightS=" ";}	if(p2LargeStraight>=10) {p2LargeStraightS=" ";}
		if(p1Yacht>=10) {p1YachtS=" ";}					if(p2Yacht>=10) {p2YachtS=" ";}
		if(p1Total>=10) {p1TotalS=" ";}					if(p2Total>=10) {p2TotalS=" ";}

		if(p1Subtotal>=100) {p1SubtotalS="";}			if(p2Subtotal>=100) {p2SubtotalS="";}
		if(p1Total>=100) {p1TotalS="";}					if(p2Total>=100) {p2TotalS="";}

		if(p1Subtotal>=100) {p1BarSpace="";}			else if(p1Subtotal>=10) {p1BarSpace="-";}
		if(p2Subtotal>=100) {p2BarSpace="";}			else if(p2Subtotal>=10) {p2BarSpace="-";}

		if(!p1AcesTF) {p1AcesTFP="■";}					if(!p2AcesTF) {p2AcesTFP="■";}
		if(!p1DeucesTF) {p1DeucesTFP="■";}				if(!p2DeucesTF) {p2DeucesTFP="■";}
		if(!p1ThreesTF) {p1ThreesTFP="■";}				if(!p2ThreesTF) {p2ThreesTFP="■";}
		if(!p1FoursTF) {p1FoursTFP="■";}				if(!p2FoursTF) {p2FoursTFP="■";}
		if(!p1FivesTF) {p1FivesTFP="■";}				if(!p2FivesTF) {p2FivesTFP="■";}
		if(!p1SixesTF) {p1SixesTFP="■";}				if(!p2SixesTF) {p2SixesTFP="■";}
		if(!p1ChoiceTF) {p1ChoiceTFP="■";}				if(!p2ChoiceTF) {p2ChoiceTFP="■";}
		if(!p1FourOfKindTF) {p1FourOfKindTFP="■";}		if(!p2FourOfKindTF) {p2FourOfKindTFP="■";}
		if(!p1FullHouseTF) {p1FullHouseTFP="■";}		if(!p2FullHouseTF) {p2FullHouseTFP="■";}
		if(!p1SmallStraightTF) {p1SmallStraightTFP="■";}if(!p2SmallStraightTF) {p2SmallStraightTFP="■";}
		if(!p1LargeStraightTF) {p1LargeStraightTFP="■";}if(!p2LargeStraightTF) {p2LargeStraightTFP="■";}
		if(!p1YachtTF) {p1YachtTFP="■";}				if(!p2YachtTF) {p2YachtTFP="■";}
	}

	boolean gameOverCheck() { //checks if the game is over
		return !(p2AcesTF || p2DeucesTF || p2ThreesTF || p2FoursTF || p2FivesTF || p2SixesTF ||
				p2ChoiceTF || p2FourOfKindTF || p2FullHouseTF || p2SmallStraightTF || p2LargeStraightTF || p2YachtTF);
	}
}