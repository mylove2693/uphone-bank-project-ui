package ubank.enum_type;

public enum ERateType {
	LENDING_RATE("贷款利率"), DEPOSIT_RATE("存款利率");

	private final String mRateName;

	private ERateType(String name) {
		mRateName = name;
	}

	public static String getRateTypeName(ERateType type) {
		return type.mRateName;
	}

	public static ERateType getRateType(String type) {
		for (ERateType r : ERateType.values()) {
			if (r.mRateName.equals(type)) {
				return r;
			}

		}
		throw new NullPointerException(
				"I can't find Enum ERateType,so it is null");
	}
}
