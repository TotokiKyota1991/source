
import java.io.IOException;

public class PortfolioMonsterGame {
	public static int pHp = 300;
	public static int pMp = 250;
	public static int yakusou = 2;
	public static int posyon = 2;
	public static int kisekiKakera = 1;
	public static boolean mapBoss = false;
	public static void main(String[] args) throws IOException {
		boolean live = true;
		int monsterOutbreak;
		int square = 0;
		int dice;
		int command;

		System.out.println("//// Tekito033 〜勇者トトキーのぼうけん〜////\n");
		timeLag500();
		System.out.println(" ----------------------------------------------------------------");
		System.out.println("| ダンジョンをすすみボスをたおそう！！                           |");
		System.out.println("| トトキーのHPが0になったらゲームオーバー！！                    |");
		System.out.println(" ----------------------------------------------------------------");
		timeLag1000();
		while(true) {
			sugoroku(square);
			System.out.println("トトキー/HP:" + pHp + "/MP:" + pMp);
			timeLag500();
			while(true) {
				System.out.print("\n(１)すすむ(２)どうぐ -->");
				command = Character.digit(System.in.read(), 10);
				System.in.skip(256);
				if(command == 1 || command == 2) {
					if(command == 2) {
						douguSousa();
						continue;
					}
					break;
				}
			}
			dice = (int)((Math.random() * 3) + 1);
			square += dice;
			if(square >= 50) {
				bossLines();
				bossBattle(args);
				if(mapBoss) {
					System.out.println("\n《トトキーはボスをたおした！！》\n《ゲームクリア！！》");
					timeLag1000();
				}
				break;
			}
			monsterOutbreak = (int)(Math.random() * 3);
			if(monsterOutbreak != 1) {
				System.out.println("《" + dice + "にちこうていすすんだ》");
				timeLag500();
			}
			if(monsterOutbreak == 1) {
				live = battle(args);
			}
			if(live == false) {
				break;
			}
		}
		if(!mapBoss) {
			System.out.println("\n======= ゲームオーバー =======");
		}
	}

	public static boolean battle(String[] args) throws IOException {

//tekito26のソースコードを流用
//変数設定
//モンスター用
		int monsterKazu = (int)(Math.random() * 9) + 1;
		int monsterKill = 0;
		int monsterIncidence;
		String[] monsterName = {
				"スライム", "メタルキングスライム", "がいこつナイト", "のらいぬ", "チワワライオン",
				"かまいたち", "がんせきおに"
				};
		String[] monsterNumber  =new String[monsterKazu];
		int[] monsterHp = new int[monsterKazu];
		int[] monsterPw = new int[monsterKazu];
		int[] monsterDf = new int[monsterKazu];
		boolean[] monsterLive = new boolean[monsterKazu];
		int suraimuAlphabet = 0;
		int mksAlphabet = 0;
		int jkoAlphabet = 0;
		int nriAlphabet = 0;
		int hostAlphabet = 0;
		int hangureAlphabet = 0;
		int yakuzaAlphabet = 0;
		for(int i = 0; i < monsterNumber.length; i++) {
			monsterIncidence = (int)(Math.random() * 15);
			switch(monsterIncidence) {
				case 0 :
					monsterNumber[i] = monsterName[1] + (char)('A' + mksAlphabet);
					monsterHp[i] = (int)(Math.random() * 101) + 100;
					monsterPw[i] = (int)(Math.random() * 51) + 50;
					monsterDf[i] = (int)(Math.random() * 11) + 15;
					mksAlphabet++;
					break;
				case 1 :
					monsterNumber[i] = monsterName[2] + (char)('A' + jkoAlphabet);
					monsterHp[i] = (int)(Math.random() * 21) + 15;
					monsterPw[i] = (int)(Math.random() * 101) + 50;
					monsterDf[i] = (int)(Math.random() * 11);
					jkoAlphabet++;
					break;
				case 2 :
				case 3 :
					monsterNumber[i] = monsterName[3] + (char)('A' + nriAlphabet);
					monsterHp[i] = (int)(Math.random() * 4) + 4;
					monsterPw[i] = (int)(Math.random() * 2) + 2;
					monsterDf[i] = (int)(Math.random() * 2) + 1;
					nriAlphabet++;
					break;
				case 4 :
				case 5 :
				case 6 :
					monsterNumber[i] = monsterName[4] + (char)('A' + hostAlphabet);
					monsterHp[i] = (int)(Math.random() * 4) + 10;
					monsterPw[i] = (int)(Math.random() * 21) + 10;
					monsterDf[i] = (int)(Math.random() * 2) + 5;
					hostAlphabet++;
					break;
				case 7 :
				case 8 :
				case 9 :
					monsterNumber[i] = monsterName[5] + (char)('A' + hangureAlphabet);
					monsterHp[i] = (int)(Math.random() * 4) + 2;
					monsterPw[i] = (int)(Math.random() * 21) + 30;
					monsterDf[i] = (int)(Math.random() * 2) + 1;
					hangureAlphabet++;
					break;
				case 10 :
				case 11 :
					monsterNumber[i] = monsterName[6] + (char)('A' + yakuzaAlphabet);
					monsterHp[i] = (int)(Math.random() * 31) + 20;
					monsterPw[i] = (int)(Math.random() * 10) + 10;
					monsterDf[i] = (int)(Math.random() * 21) + 20;
					yakuzaAlphabet++;
					break;
				default :
					monsterNumber[i] = monsterName[0] + (char)('A' + suraimuAlphabet);
					monsterHp[i] = (int)(Math.random() * 11) + 10;
					monsterPw[i] = (int)(Math.random() * 4) + 3;
					monsterDf[i] = (int)(Math.random() * 4) + 2;
					suraimuAlphabet++;
			}
			monsterLive[i] = true;
		}
//プレイヤー用
		boolean fireBolt;
		boolean horobiMado;
		int playerDp;
		int selectCmmand;
		boolean attackPattern;
		boolean defensePattern;
		boolean escapePattern;
		boolean magicPattern;
		int target = 0;
		int kougekiryoku;
		int escapeLuck;
		int kaishin;
		long stratT;

//処理開始
		System.out.println("\n////////////////////////////////////////////////");
		System.out.println("////////////////////////////////////////////////");
		System.out.println("////////////////////////////////////////////////");
		System.out.println("////////////////////////////////////////////////");
		stratT = System.currentTimeMillis();
		while(true) {
			if((System.currentTimeMillis() - stratT) >= 1000){
				break;
			}
		}
		System.out.println("\n\n<<モンスターが" + monsterKazu + "たいあらわれた！>>");
		while(true) {
			playerDp = 1;
			attackPattern = false;
			defensePattern = false;
			escapePattern = false;
			magicPattern = false;
			fireBolt =false;
			horobiMado = false;
			System.out.println("-------------------------------------");
			for(int i = 0; i < monsterNumber.length; i++) {
				if(monsterLive[i]) {
					System.out.println("(" + (i + 1) + ") " + monsterNumber[i] + " : HP " + monsterHp[i]);
				}
			}
			System.out.println("-------------------------------------\n");
			System.out.println("トトキー/HP:" + pHp + "/MP:" + pMp + "/ぼうぎょ:" + playerDp + "/ちから:きぶんしだい");
			while(true) {
				System.out.println("《コマンドをせんたく》");
				System.out.println("(１)こうげき(２)まほう");
				System.out.println("(３)ぼうぎょ(４)どうぐ");
				System.out.print("(５)にげる                    -->");
				selectCmmand = Character.digit(System.in.read(), 10);
				System.in.skip(256);
				if(selectCmmand == 4) {
					boolean taked = douguSousa();
					if(taked)break;
				}
				if(selectCmmand == 1) {
					attackPattern = true;
					break;
				}else if(selectCmmand == 2) {
					magicPattern = true;
					break;
				}else if(selectCmmand == 3) {
					defensePattern = true;
					break;
				}else if(selectCmmand == 5) {
					escapePattern = true;
					break;
				}
			}
			stratT = System.currentTimeMillis();
			while(true) {
				if((System.currentTimeMillis() - stratT) >= 500){
					break;
				}
			}
//プレイヤーの魔法ブロック
			if(magicPattern) {
				System.out.print("■まほうをつかう");
				while(true) {
					System.out.println("《じゅもん》");
					System.out.println("(１)ファイヤーボルト/MP50          -->");
					System.out.println("(２)ほろびのまどう/MP150〜200      -->");
					System.out.print("(５)もどる                         -->");
					selectCmmand = Character.digit(System.in.read(), 10);
					System.in.skip(256);
					if(selectCmmand == 5) {
						break;
					}
					if(selectCmmand == 1 && pMp >= 50) {
						System.out.println("\n《ほのおのいなづまがふりそそぐ！！》");
						stratT = System.currentTimeMillis();
						while(true) {
							if((System.currentTimeMillis() - stratT) >= 1000){
								break;
							}
						}
						pMp -= 50;
						fireBolt = true;
						break;
					}else if(selectCmmand == 1 && pMp < 50){
						System.out.println("/// MPがたりない！ ///");
					}
					if(selectCmmand == 2 && pMp >= 150) {
						while(true) {
							while(true) {
								System.out.print("■こうげきするモンスターのばんごう -> ");
								target = Character.digit(System.in.read(), 10);
								System.in.skip(256);
								if(target > 0 && target <= monsterKazu) {
									break;
								}
							}
							if(monsterLive[target - 1] == false) {
								continue;
							}
							horobiMado = true;
							break;
						}
					}else if(selectCmmand == 2 && pMp < 150) {
						System.out.println("/// MPがたりない！ ///");
					}
					if(horobiMado) {
						break;
					}
				}
				if(magicPattern && selectCmmand == 5) {
					magicPattern = false;
					continue;
				}
				if(fireBolt) {
					int tyousei = 0;
					int atari;
					while(true) {
						for(int i = 0; i < monsterNumber.length; i++) {
							if(monsterLive[i]) {
								atari = (int)(Math.random() * 2);
								if(atari == 0) {
									kougekiryoku = (int)(Math.random() * 36) + 65;
									System.out.println(monsterNumber[i] + "にちょくげき!!");
									tyousei++;
									if(kougekiryoku > monsterDf[i]) {
										monsterHp[i]  -= kougekiryoku - monsterDf[i];
										System.out.println(">" + (kougekiryoku - monsterDf[i]) + "ポイントのダメージをあたえた！");
									}else {
										System.out.println("かたすぎてはがたたない！！");
									}
									stratT = System.currentTimeMillis();
									while(true) {
										if((System.currentTimeMillis() - stratT) >= 500){
											break;
										}
									}
									if(monsterHp[i] <= 0) {
										monsterLive[i] = false;
										System.out.println(monsterNumber[i] + "をたおした！！");
										monsterKill++;
										stratT = System.currentTimeMillis();
										while(true) {
											if((System.currentTimeMillis() - stratT) >= 500){
												break;
											}
										}
									}
									if(monsterKazu == monsterKill) {
										System.out.println("\n《モンスターをせんめつした！！》");
										stratT = System.currentTimeMillis();
										while(true) {
											if((System.currentTimeMillis() - stratT) >= 500){
												break;
											}
										}
										break;
									}
								}
							}
						}
						if(tyousei > 0) {
							break;
						}
					}
					if(monsterKazu == monsterKill) {
						break;
					}
				}
				if (horobiMado) {
					pMp = 0;
					System.out.println("\n/// じくうがゆがむ /// ");
					stratT = System.currentTimeMillis();
					while(true) {
						if((System.currentTimeMillis() - stratT) >= 1000){
							break;
						}
					}
					monsterLive[target - 1] = false;
					System.out.println("《" + monsterNumber[target - 1] + "はすなのようにくちはてた！！》 ");
					while(true) {
						if((System.currentTimeMillis() - stratT) >= 1000){
							break;
						}
					}
					monsterKill++;
					if(monsterKazu == monsterKill) {
						System.out.println("《モンスターをせんめつした！！》");
						while(true) {
							if((System.currentTimeMillis() - stratT) >= 500){
								break;
							}
						}
						break;
					}
				}
			}
//プレイヤーのこうげきブロック
			if(attackPattern) {
				while(true) {								//入力ミス対策
					while(true) {
						System.out.print("■こうげきするモンスターのばんごう -> ");
						target = Character.digit(System.in.read(), 10);
						System.in.skip(256);
						if(target > 0 && target <= monsterKazu) {
							break;
						}
					}
					if(monsterLive[target - 1] == false) {
						continue;
					}
					break;
				}
				stratT = System.currentTimeMillis();
				while(true) {
					if((System.currentTimeMillis() - stratT) >= 500){
						break;
					}
				}
				kougekiryoku = (int)(Math.random() * 36) + 15;
				kaishin = (int)(Math.random() * 5);
				if(kaishin == 0) {
					kougekiryoku *= 2;
					System.out.println("/// かいしんのいちげき/// ");
				}
				System.out.println(monsterNumber[target - 1] + "をこうげき!!");
				stratT = System.currentTimeMillis();
				while(true) {
					if((System.currentTimeMillis() - stratT) >= 500){
						break;
					}
				}
				if(kougekiryoku > monsterDf[target - 1]) {
				monsterHp[target - 1]  -= kougekiryoku - monsterDf[target - 1];
				System.out.println(">" + (kougekiryoku - monsterDf[target - 1]) + "ポイントのダメージをあたえた！");
				}else {
					System.out.println("かたすぎてはがたたない！！");
				}
				if(monsterHp[target - 1] <= 0) {
					monsterLive[target - 1] = false;
					System.out.println();
					System.out.println(monsterNumber[target - 1] + "をたおした！！");
					monsterKill++;
				}
				if(monsterKazu == monsterKill) {
					System.out.println("《モンスターをせんめつした！！》");
					break;
				}
			}
			if(defensePattern) {
				stratT = System.currentTimeMillis();
				while(true) {
					if((System.currentTimeMillis() - stratT) >= 500){
						break;
					}
				}
//プレイヤーの防御ブロック
				System.out.println("■トトキーはまもるをつかった");
				playerDp = (int)(Math.random() * 9) + 1;
				System.out.println("《ぼうぎょが" + (playerDp - 1) + "あがった》");
			}
			if(escapePattern) {
				stratT = System.currentTimeMillis();
				while(true) {
					if((System.currentTimeMillis() - stratT) >= 500){
						break;
					}
				}
//プレイヤーの逃走ブロック
				System.out.println("■トトキーはにげだした");
				escapeLuck = (int)(Math.random() * 2);
				stratT = System.currentTimeMillis();
				while(true) {
					if((System.currentTimeMillis() - stratT) >= 500){
						break;
					}
				}
				if(escapeLuck == 0) {
					System.out.println("《だっしゅつせいこう！！》");
					break;
				}else {
					System.out.println("《しかしまわりこまれてしまった！！》");
				}
			}
//モンスターのこうげきターン
			System.out.println("\n《モンスターのこうげき》");
			for(int i = 0; i < monsterNumber.length; i++) {
				if(monsterLive[i]) {
					System.out.println(monsterNumber[i] + "のこうげき");
					stratT = System.currentTimeMillis();
					while(true) {
						if((System.currentTimeMillis() - stratT) >= 1000){
							break;
						}
					}
					if(monsterPw[i] > playerDp) {
						System.out.println((monsterPw[i] - playerDp) + "のダメージ");
						pHp -= monsterPw[i] - playerDp;
					}else {
						System.out.println("トトキーはガードした");
					}
					stratT = System.currentTimeMillis();
					while(true) {
						if((System.currentTimeMillis() - stratT) >= 500){
							break;
						}
					}
				}
				if(pHp <= 0 && kisekiKakera > 0) {
					kisekimethod();
				}
				if(pHp <= 0) {
					System.out.println("\nゆうしゃトトキーはしんでしまった");
					stratT = System.currentTimeMillis();
					while(true) {
						if((System.currentTimeMillis() - stratT) >= 500){
							break;
						}
					}
					break;
				}
			}
			if(pHp <= 0) {
				break;
			}
		}
//戦闘終了の表示
		System.out.println("\n\n//// せんとうしゅうりょう ////");
		stratT = System.currentTimeMillis();
		while(true) {
			if((System.currentTimeMillis() - stratT) >= 500){
				break;
			}
		}
		if(pHp <= 0) {
//負け
			return false;
		}else {
//勝ち
			if(monsterKazu == monsterKill) {
				doropItem(monsterKazu);
			}
			return true;
		}
	}
	public static void timeLag500() {
		long stratT = System.currentTimeMillis();
		while(true) {
			if((System.currentTimeMillis() - stratT) >= 500){
				break;
			}
		}
	}
	public static void timeLag1000() {
		long stratT = System.currentTimeMillis();
		while(true) {
			if((System.currentTimeMillis() - stratT) >= 1000){
				break;
			}
		}
	}
	public static void sugoroku(int square) {
		System.out.println("\n 《ダンジョンマップ》");
		for(int i = 1; i <= 50; i++ ){
			if(i ==square){
				switch(square) {
				case 10:
				case 20:
				case 30:
				case 40:
					System.out.print(" ■ ");
					break;
				default :
					System.out.print("■");
				}
				if(i % 10 == 0 && i != 0)
			System.out.println();
			continue;
			}
			switch(i){
				case 10:
				case 20:
				case 30:
				case 40:
					System.out.print(" ▽ ");
					break;
				case 50:
					System.out.print(" ★ ");
					break;
				default:
					System.out.print("□");
					break;
			}
			if(i % 10 == 0 && i != 0)
			System.out.println();
		}
		System.out.println();

	}
	public static void bossLines() {
		System.out.println(" なぞのこえ");
		System.out.println(" ----------------------------------------------------------------");
		System.out.println("| …………………………………………                               |");
		System.out.println("| ………………………………                                       |");
		System.out.println(" ----------------------------------------------------------------");
		timeLag1000();
		System.out.println(" なぞのこえ");
		System.out.println(" ----------------------------------------------------------------");
		System.out.println("| …………………………………………                               |");
		System.out.println("| …………………………まっていたぞ……                           |");
		System.out.println(" ----------------------------------------------------------------");
		timeLag1000();
		System.out.println(" くびなしジェネラル");
		System.out.println(" ----------------------------------------------------------------");
		System.out.println("| ゆうしゃトトキー                                               |");
		System.out.println("| わたしをたおしてみよ！！！                                     |");
		System.out.println(" ----------------------------------------------------------------");
		timeLag1000();
		timeLag1000();
	}
//ボスバトル専用メソッド
	public static boolean bossBattle(String[] args) throws IOException {

		//tekito26のソースコードを流用
		//変数設定
		//モンスター用
				int monsterKazu = 3;
				int monsterKill = 0;
				int monsterIncidence;
				String[] monsterName = {"くびなしジェネラル", "がいこつポーン", "がいこつナイト"};
				String[] monsterNumber  =new String[monsterKazu];
				int[] monsterHp = new int[monsterKazu];
				int[] monsterPw = new int[monsterKazu];
				int[] monsterDf = new int[monsterKazu];
				boolean[] monsterLive = new boolean[monsterKazu];
				for(int i = 0; i < monsterNumber.length; i++) {
					monsterIncidence = i;
					switch(monsterIncidence) {
						case 0 :
							monsterNumber[i] = monsterName[0];
							monsterHp[i] =500;
							monsterPw[i] =(int)(Math.random() * 50) + 150;
							monsterDf[i] = (int)(Math.random() * 11);
							break;
						case 1 :
							monsterNumber[i] = monsterName[1];
							monsterHp[i] = (int)(Math.random() * 21) + 100;
							monsterPw[i] = (int)(Math.random() * 41) + 50;
							monsterDf[i] = (int)(Math.random() * 11) + 100;
							break;
						case 2 :
							monsterNumber[i] = monsterName[2];
							monsterHp[i] = (int)(Math.random() * 21) + 15;
							monsterPw[i] = (int)(Math.random() * 101) + 50;
							monsterDf[i] = (int)(Math.random() * 11);
							break;
					}
					monsterLive[i] = true;
				}
		//プレイヤー用
				boolean fireBolt;
				boolean horobiMado;
				int playerDp;
				int selectCmmand;
				boolean attackPattern;
				boolean defensePattern;
				boolean magicPattern;
				int target = 0;
				int kougekiryoku;
				int kaishin;
				long stratT;

		//処理開始
				System.out.println("\n////////////////////////////////////////////////");
				System.out.println("////////////////////////////////////////////////");
				System.out.println("////////////////////////////////////////////////");
				System.out.println("////////////////////////////////////////////////");
				System.out.println("////////////////////////////////////////////////");
				System.out.println("////////////////////////////////////////////////");
				System.out.println("////////////////////////////////////////////////");
				System.out.println("////////////////////////////////////////////////");
				stratT = System.currentTimeMillis();
				while(true) {
					if((System.currentTimeMillis() - stratT) >= 1500){
						break;
					}
				}
				System.out.println("\n\n<< くびなしジェネラルがあらわれた！！！>>");
				while(true) {
					playerDp = 1;
					attackPattern = false;
					defensePattern = false;
					magicPattern = false;
					fireBolt =false;
					horobiMado = false;
					System.out.println("-------------------------------------");
					for(int i = 0; i < monsterNumber.length; i++) {
						if(monsterLive[i]) {
							System.out.println("(" + (i + 1) + ") " + monsterNumber[i] + " : HP " + monsterHp[i]);
						}
					}
					System.out.println("-------------------------------------\n");
					System.out.println("トトキー/HP:" + pHp + "/MP:" + pMp + "/ぼうぎょ:" + playerDp + "/ちから:きぶんしだい");
					while(true) {
						System.out.println("《コマンドをせんたく》");
						System.out.println("(１)こうげき(２)まほう");
						System.out.print("(３)だいぼうぎょ(４)どうぐ  -->");
						selectCmmand = Character.digit(System.in.read(), 10);
						System.in.skip(256);
						if(selectCmmand == 4) {
							Boolean taked = false;
							taked = douguSousa();
							if(taked)break;
						}
						if(selectCmmand == 1) {
							attackPattern = true;
							break;
						}else if(selectCmmand == 2) {
							magicPattern = true;
							break;
						}else if(selectCmmand == 3) {
							defensePattern = true;
							break;
						}
					}
					stratT = System.currentTimeMillis();
					while(true) {
						if((System.currentTimeMillis() - stratT) >= 500){
							break;
						}
					}
		//プレイヤーの魔法ブロック
					if(magicPattern) {
						System.out.print("■まほうをつかう");
						while(true) {
							System.out.println("《じゅもん》");
							System.out.println("(１)ファイヤーボルト/MP50          -->");
							System.out.println("(２)ほろびのまどう/MP150〜200      -->");
							System.out.print("(５)もどる                         -->");
							selectCmmand = Character.digit(System.in.read(), 10);
							System.in.skip(256);
							if(selectCmmand == 5) {
								break;
							}
							if(selectCmmand == 1 && pMp >= 50) {
								System.out.println("\n《ほのおのいなづまがふりそそぐ！！》");
								stratT = System.currentTimeMillis();
								while(true) {
									if((System.currentTimeMillis() - stratT) >= 1000){
										break;
									}
								}
								pMp -= 50;
								fireBolt = true;
								break;
							}else if(selectCmmand == 1 && pMp < 50){
								System.out.println("/// MPがたりない！ ///");
							}
							if(selectCmmand == 2 && pMp >= 150) {
								while(true) {
									while(true) {
										System.out.print("■こうげきするモンスターのばんごう -> ");
										target = Character.digit(System.in.read(), 10);
										System.in.skip(256);
										if(target > 0 && target <= monsterKazu) {
											break;
										}
									}
									if(monsterLive[target - 1] == false) {
										continue;
									}
									horobiMado = true;
									break;
								}
							}else if(selectCmmand == 2 && pMp < 150) {
								System.out.println("/// MPがたりない！ ///");
							}
							if(horobiMado) {
								break;
							}
						}
						if(magicPattern && selectCmmand == 5) {
							magicPattern = false;
							continue;
						}
						if(fireBolt) {
							int tyousei = 0;
							int atari;
							while(true) {
								for(int i = 0; i < monsterNumber.length; i++) {
									if(monsterLive[i]) {
										atari = (int)(Math.random() * 2);
										if(atari == 0) {
											kougekiryoku = (int)(Math.random() * 36) + 65;
											System.out.println(monsterNumber[i] + "にちょくげき!!");
											tyousei++;
											if(kougekiryoku > monsterDf[i]) {
												monsterHp[i]  -= kougekiryoku - monsterDf[i];
												System.out.println(">" + (kougekiryoku - monsterDf[i]) + "ポイントのダメージをあたえた！");
											}else {
												System.out.println("かたすぎてはがたたない！！");
											}
											stratT = System.currentTimeMillis();
											while(true) {
												if((System.currentTimeMillis() - stratT) >= 500){
													break;
												}
											}
											if(monsterHp[i] <= 0) {
												monsterLive[i] = false;
												System.out.println(monsterNumber[i] + "をたおした！！");
												monsterKill++;
												stratT = System.currentTimeMillis();
												while(true) {
													if((System.currentTimeMillis() - stratT) >= 500){
														break;
													}
												}
											}
											if(monsterKazu == monsterKill) {
												System.out.println("\n《モンスターをせんめつした！！》");
												stratT = System.currentTimeMillis();
												while(true) {
													if((System.currentTimeMillis() - stratT) >= 500){
														break;
													}
												}
												break;
											}
										}
									}
								}
								if(tyousei > 0) {
									break;
								}
							}
							if(monsterKazu == monsterKill) {
								break;
							}
						}
						if (horobiMado) {
							pMp = 0;
							System.out.println("\n/// じくうがゆがむ /// ");
							stratT = System.currentTimeMillis();
							while(true) {
								if((System.currentTimeMillis() - stratT) >= 1000){
									break;
								}
							}
							monsterLive[target - 1] = false;
							System.out.println("《" + monsterNumber[target - 1] + "はすなのようにくちはてた！！》 ");
							while(true) {
								if((System.currentTimeMillis() - stratT) >= 1000){
									break;
								}
							}
							monsterKill++;
							if(monsterKazu == monsterKill) {
								System.out.println("《モンスターをせんめつした！！》");
								while(true) {
									if((System.currentTimeMillis() - stratT) >= 500){
										break;
									}
								}
								break;
							}
						}
					}
		//プレイヤーのこうげきブロック
					if(attackPattern) {
						while(true) {								//入力ミス対策
							while(true) {
								System.out.print("■こうげきするモンスターのばんごう -> ");
								target = Character.digit(System.in.read(), 10);
								System.in.skip(256);
								if(target > 0 && target <= monsterKazu) {
									break;
								}
							}
							if(monsterLive[target - 1] == false) {
								continue;
							}
							break;
						}
						stratT = System.currentTimeMillis();
						while(true) {
							if((System.currentTimeMillis() - stratT) >= 500){
								break;
							}
						}
						kougekiryoku = (int)(Math.random() * 36) + 15;
						kaishin = (int)(Math.random() * 5);
						if(kaishin == 0) {
							kougekiryoku *= 2;
							System.out.println("/// かいしんのいちげき/// ");
						}
						System.out.println(monsterNumber[target - 1] + "をこうげき!!");
						stratT = System.currentTimeMillis();
						while(true) {
							if((System.currentTimeMillis() - stratT) >= 500){
								break;
							}
						}
						if(kougekiryoku > monsterDf[target - 1]) {
						monsterHp[target - 1]  -= kougekiryoku - monsterDf[target - 1];
						System.out.println(">" + (kougekiryoku - monsterDf[target - 1]) + "ポイントのダメージをあたえた！");
						}else {
							System.out.println("かたすぎてはがたたない！！");
						}
						if(monsterHp[target - 1] <= 0) {
							monsterLive[target - 1] = false;
							System.out.println();
							System.out.println(monsterNumber[target - 1] + "をたおした！！");
							monsterKill++;
						}
						if(monsterKazu == monsterKill) {
							System.out.println("《モンスターをせんめつした！！》");
							break;
						}
					}
					if(defensePattern) {
						stratT = System.currentTimeMillis();
						while(true) {
							if((System.currentTimeMillis() - stratT) >= 500){
								break;
							}
						}
		//プレイヤーの防御ブロック
						System.out.println("■トトキーはだいぼうぎょをつかった");
						playerDp = (int)(Math.random() * 11) + 30;
						System.out.println("《ぼうぎょが" + (playerDp - 1) + "あがった》");
					}
		//モンスターのこうげきターン
					System.out.println("\n《ダンジョンボスのこうげき》");
					for(int i = 0; i < monsterNumber.length; i++) {
						if(monsterLive[i]) {
							System.out.println(monsterNumber[i] + "のこうげき");
							stratT = System.currentTimeMillis();
							while(true) {
								if((System.currentTimeMillis() - stratT) >= 1000){
									break;
								}
							}
							if(monsterPw[i] > playerDp) {
								System.out.println((monsterPw[i] - playerDp) + "のダメージ");
								pHp -= monsterPw[i] - playerDp;
							}else {
								System.out.println("トトキーはガードした");
							}
							stratT = System.currentTimeMillis();
							while(true) {
								if((System.currentTimeMillis() - stratT) >= 500){
									break;
								}
							}
						}
						if(pHp <= 0 && kisekiKakera > 0) {
							kisekimethod();
						}
						if(pHp <= 0) {
							System.out.println("\nゆうしゃトトキーはしんでしまった");
							stratT = System.currentTimeMillis();
							while(true) {
								if((System.currentTimeMillis() - stratT) >= 500){
									break;
								}
							}
							break;
						}
					}
					if(pHp <= 0) {
						break;
					}
				}
		//戦闘終了の表示
				System.out.println("\n\n//// せんとうしゅうりょう ////");
				stratT = System.currentTimeMillis();
				while(true) {
					if((System.currentTimeMillis() - stratT) >= 500){
						break;
					}
				}
				if(pHp <= 0) {
		//負け
					return false;
				}else {
		//勝ち
					mapBoss =true;
					return true;
				}
			}
	public static boolean douguSousa() throws IOException {
		boolean taked = false;
		int command;
		while(true) {
			System.out.println("《どうぐ》");
			System.out.println("トトキー/HP:" + pHp + "/MP:" + pMp);
			timeLag500();
			System.out.println("（０）もどる");
			System.out.println("（１）やくそう : " + yakusou + " こ");
			System.out.println("（２）ポーション : " + posyon + " こ");
			System.out.print("（３）きせきのかけら : " + kisekiKakera + " こ     -->");
			command = Character.digit( System.in.read(), 10);
			System.in.skip(256);
			if(command == 0) {
				break;
			}
			if(command == 1 && yakusou > 0) {
				taked = true;
				yakusou -= 1;
				if(pHp <= 150) {
					pHp += 150;
				}else {
					pHp = 300;
				}
				System.out.println("《HPが１５０かいふくした》");
				timeLag1000();
			}else if(command == 1 && yakusou == 0) {
				System.out.println("やくそうがない");
				timeLag1000();
			}
			if(command == 2 && posyon > 0) {
				taked = true;
				posyon -= 1;
				if(pMp <= 50) {
					pMp += 150;
				}else {
					pMp = 250;
				}
				System.out.println("《MPが１５０かいふくした》");
				timeLag1000();
			}else if(command == 2 && posyon == 0) {
				System.out.println("ポーションがない");
				timeLag1000();
			}
			if(command == 3) {
				System.out.println("この石はなにかをひめている……");
				timeLag1000();
			}
		}
		return taked;
	}
	public static void doropItem(int monsterNum) {
		int yakusouDorop;
		int posyonDorop;
		int kisekinokakeraDorop;
		int yakusocnt = 0;
		int posyoncnt = 0;
		int kisekicnt = 0;
		for(int i = 0; i < monsterNum; i++) {
			yakusouDorop = (int)(Math.random() * 4);
			posyonDorop = (int)(Math.random() * 4);
			kisekinokakeraDorop = (int)(Math.random() * 10);
			if(yakusouDorop == 0) {
				yakusocnt++;
			}
			if(posyonDorop == 0) {
				posyoncnt++;
			}
			if(kisekinokakeraDorop == 0) {
				kisekicnt++;
			}
		}
		if(yakusocnt > 0) {
			yakusou += yakusocnt;
			System.out.println("《 やくそうを " + yakusocnt + " こゲットした》");
		}
		if(posyoncnt > 0) {
			posyon += posyoncnt;
			System.out.println("《 ポーション を " + posyoncnt + " こゲットした》");
		}
		if(kisekicnt > 0) {
			kisekiKakera += kisekicnt;
			System.out.println("《 きせきのかけら を " + kisekicnt + " こゲットした》");
		}
		if(yakusocnt > 0 || posyoncnt > 0 || kisekicnt > 0) {
			timeLag1000();
			timeLag500();
		}
	}
	public static void kisekimethod() {
		kisekiKakera--;
		pHp = 300;
		System.out.println("\nゆうしゃトトキーはしんでしまった");
		timeLag1000();
		timeLag1000();
		System.out.println("////　きせきのひかりがさしこむ　////");
		timeLag1000();
		timeLag1000();
		System.out.println("《 ゆうしゃトトキーはふっかつした！！ 》");
		timeLag1000();
		timeLag1000();
	}
	public static void name() {

	}
}