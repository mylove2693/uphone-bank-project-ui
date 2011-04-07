package ubank.enum_type;

public enum EAccState {

	BIND("bind"), UNBIND("unbind"), ACTIVE("active"), UNACTIVE("unactive"), LOSS(
			"loss"), UNLOSS("unloss"), ORDER("order"), UNORDER("unorder");

	private final String mName;

	private EAccState(String name) {
		mName = name;
	}

	public static EAccState getAccState(String name) {

		for (EAccState a : EAccState.values()) {
			if (a.mName.equals(name)) {
				return a;
			}
		}
		throw new NullPointerException(
				"I can't find Enum AccState,so it is null");
	}

	public static String getStateName(EAccState a) {
		return a.mName;
	}
}
