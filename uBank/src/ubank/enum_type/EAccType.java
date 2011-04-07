package ubank.enum_type;

public enum EAccType {
	CREDIT_CARD("信用卡", "cc"), CURRENT_DEPOSIT("活期储蓄卡", "cd"), TIME_DEPOSITS(
			"定期储蓄卡", "td"), NULL("没有", "null");

	private final String mName;
	private final String mId;

	private EAccType(String name, String id) {
		mName = name;
		mId = id;
	}

	public static EAccType getEAccTypeById(String id) {
		for (EAccType a : EAccType.values()) {
			if (a.mId.equals(id)) {
				return a;
			}
		}
		throw new NullPointerException(
				"I can't find Enum AccType,so it is null");
	}

	public static String getAccTypeId(EAccType e) {
		return e.mId;
	}

	public static String getAccTypeName(EAccType e) {
		return e.mName;
	}
}
