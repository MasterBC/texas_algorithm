package com.github.esrrhs.texas_algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestUtil
{
	public static void main(String[] args)
	{
		TexasAlgorithmUtil.load();

		String card = "方2,梅3,黑2,黑4,鬼";

		List<Byte> tmp1 = TexasAlgorithmUtil.strToPokes(card);
		ArrayList<Poke> tmp = GenUtil.toArray(GenUtil.genCardBind(tmp1));

		ArrayList<Poke> pickedCards = new ArrayList<>();
		TexasCardUtil.fiveFromFive(tmp, pickedCards);

		List<Byte> guiTrans = new ArrayList<>();
		System.out.println(TexasAlgorithmUtil.getMax(card, guiTrans));
		System.out.println(TexasAlgorithmUtil.pokesToStr(guiTrans));

		System.out.println(TexasAlgorithmUtil.getMax("方2,方3,方4,鬼,鬼,黑6,红6", guiTrans));
		System.out.println(TexasAlgorithmUtil.pokesToStr(guiTrans));

		System.out.println(TexasAlgorithmUtil.getMax("黑A,方J,黑8,红8,鬼", guiTrans));
		System.out.println(TexasAlgorithmUtil.pokesToStr(guiTrans));

		card = "方2,鬼,黑2,黑4,黑5,鬼";
		System.out.println(TexasAlgorithmUtil.getMax(card, guiTrans));
		System.out.println(TexasAlgorithmUtil.pokesToStr(guiTrans));

		String cards = "方4,方A,黑2,黑A,黑3,黑5,黑6";
		String cards1 = "红8,方A,方2,黑8,黑3,黑5,黑7";
		System.out.println(TexasAlgorithmUtil.getWinPosition(cards));
		System.out.println(TexasAlgorithmUtil.getWinProbability(cards));
		System.out.println(TexasAlgorithmUtil.keyToStr(TexasAlgorithmUtil.getWinMax(cards)));
		System.out.println(TexasAlgorithmUtil.getWinType(cards));

		System.out.println(TexasAlgorithmUtil.getWinPosition(cards1));
		System.out.println(TexasAlgorithmUtil.getWinProbability(cards1));
		System.out.println(TexasAlgorithmUtil.keyToStr(TexasAlgorithmUtil.getWinMax(cards1)));
		System.out.println(TexasAlgorithmUtil.getWinType(cards1));

		System.out.println(TexasAlgorithmUtil.compare(cards, cards1));

		String cards2 = "红8,方A,方2,黑8,黑5,黑7";
		System.out.println(TexasAlgorithmUtil.getWinPosition(cards2));
		System.out.println(TexasAlgorithmUtil.getWinProbability(cards2));
		System.out.println(TexasAlgorithmUtil.keyToStr(TexasAlgorithmUtil.getWinMax(cards2)));
		System.out.println(TexasAlgorithmUtil.getWinType(cards2));

		String cards3 = "红8,方A,方2,黑5,黑7";
		System.out.println(TexasAlgorithmUtil.getWinPosition(cards3));
		System.out.println(TexasAlgorithmUtil.getWinProbability(cards3));
		System.out.println(TexasAlgorithmUtil.keyToStr(TexasAlgorithmUtil.getWinMax(cards3)));
		System.out.println(TexasAlgorithmUtil.getWinType(cards3));

		System.out.println(TexasAlgorithmUtil.getMax("方4,方2", "黑2,黑A,方3,黑5,黑6", null));
		System.out.println(TexasAlgorithmUtil.getMax("方4,方2", "黑2,黑A,黑7,黑5,黑6", null));
		System.out.println(TexasAlgorithmUtil.getMax("黑2,黑3", "方2,方A,黑7,黑5,黑6", null));
		System.out.println(TexasAlgorithmUtil.getMax("黑2,黑3", "方2,方A,黑7,黑5", null));
		System.out.println(TexasAlgorithmUtil.getMax("黑2,黑3", "方2,黑7,黑5", null));

		TexasAlgorithmUtil.loadProbility();
		System.out.println(TexasAlgorithmUtil.getHandProbability("方3,方A", "黑2,黑4,黑5,黑K"));
		System.out.println(TexasAlgorithmUtil.getHandProbability("方2,方3", ""));
		System.out.println(TexasAlgorithmUtil.getHandProbability("方3,方A", "黑2,黑4,黑5,黑K,方A"));

		compare("hand4/texas_hand_方3方10.txt");
	}

	public static void compare(String file)
	{
		int total = 0;
		int diff1 = 0;
		int diff2 = 0;
		try
		{
			File file1 = new File(file);
			if (!file1.exists())
			{
				return;
			}
			FileInputStream inputStream = new FileInputStream(file);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			String str = null;
			while ((str = bufferedReader.readLine()) != null)
			{
				String[] params = str.split(" ");
				long key = Long.parseLong(params[0]);
				float probility = Float.parseFloat(params[1]);
				String card = params[2];

				float p = TexasAlgorithmUtil.getHandProbability(key / 100000000, key % 100000000);

				if (p - probility > 0.1 || probility - p > 0.1)
				{
					System.out.println("diff " + (p - probility) + " " + card + " " + p + " " + probility);
					diff1++;
				}
				if (p - probility > 0.2 || probility - p > 0.2)
				{
					System.out.println("diff " + (p - probility) + " " + card + " " + p + " " + probility);
					diff2++;
				}

				total++;
			}
			System.out.println("diff>0.1 = %" + diff1 * 100 / total);
			System.out.println("diff>0.1 = " + diff1);
			System.out.println("diff>0.2 = %" + diff2 * 100 / total);
			System.out.println("diff>0.2 = " + diff2);
			System.out.println("total " + total);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
