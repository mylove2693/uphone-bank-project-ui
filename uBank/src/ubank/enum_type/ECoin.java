package ubank.enum_type;

public enum ECoin {
	DOLLAR("美元"), RMB("人民币"), Euro("欧元"), ye("日元");

	private final String mName;

	private ECoin(String name) {
		mName = name;
	}

	public static ECoin getCoin(String name) {

		for (ECoin a : ECoin.values()) {
			if (a.mName.equals(name)) {
				return a;
			}
		}
		throw new NullPointerException("I can't find Enum Coin,so it is null");
	}

	public static String getCoinName(ECoin a) {
		return a.mName;
	}
}
