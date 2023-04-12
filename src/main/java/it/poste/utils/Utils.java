package it.poste.utils;

public class Utils {

	public static String paddingZero(int length, String number) {

		return String.format("%" + length + "s", number).replace(" ", "0");

	}

	public static String paddingZero(int length, int number) {

		return String.format("%0" + length + "d", number);

	}

	public static String paddingZero(int length, long number) {

		return String.format("%0" + length + "d", number);

	}

	public static String paddingBlank(int length) {

		return String.format("%" + length + "s", "");
	}

	public static String paddingBlank(int length, String string) {

		return String.format("%1$-" + length + "s", string);
	}

	public static String calculateItemWithCheckDigit(String aCodiceRaccomandata) {
		return aCodiceRaccomandata + computeCheckDigit(aCodiceRaccomandata);
	}

	public static int computeCheckDigit(String aCodiceRaccomandata) {
		if (aCodiceRaccomandata == null || aCodiceRaccomandata.length() < 11) {
			return -1;
		}
		int sumOdd = 0;
		int sumEven = 0;
		for (int i = 0; i < 11; i++) {
			char theChar = aCodiceRaccomandata.charAt(i);
			if (!Character.isDigit(theChar))
				return -1;
			if ((i % 2) != 0) {
				sumOdd += Character.digit(theChar, 10);
			} else {
				sumEven += Character.digit(theChar, 10);
			}
		}
		int totale = 0;
		String sumTotalAsString = String.valueOf(sumEven + sumOdd * 11);
		for (int i = 0; i < sumTotalAsString.length(); i++) {
			totale += Character.digit(sumTotalAsString.charAt(i), 10);
		}

		int digit = totale % 10;
		return digit;
	}

}
