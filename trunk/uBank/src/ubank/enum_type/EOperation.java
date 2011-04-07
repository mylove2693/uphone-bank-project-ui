package ubank.enum_type;

import java.util.ArrayList;
import java.util.List;

public enum EOperation {
	/* 系统 由01开始 */
	GET_ACC_TYPE("0101"), GET_ACC_WITH_NICKNAME("0103"), ADD_ACC("0104"), GET_ACC(
			"0105"), GET_ACC_TYPE_ALL("0106"), GET_ACC_TYPE_ON_CREDITCARD(
			"0107"), GET_COME_HISTORY("0108"), GET_COST("0109"), GET_CREDIT_CARD(
			"0110"), GET_EXTRA_CODE("0111"), GET_LIST_HISTORY("0112"), GET_NET(
			"0113"), GET_NET_ADDRESS("0114"), GET_OPERATOR("0115"), GET_PAYMENT_HISTORY(
			"0116"), GET_BIND_CREDIT_CARD("0117"), GET_PAYMENT_NAME("0118"), GET_PAYMENT_NAME_ON_MANA(
			"0119"), GET_PRE_ACC("0120"), GET_SEL_SERVICE_NAME("0121"), GET_SEL_SERVICE_NAME_BY_USER_ID(
			"0122"), GET_USED_CREDIT_CARD("0123"), LOGIN("0124"), SET_PRE_ACC(
			"0125"), UPDATE_PAYMENT_STATE("0126"),
	/* 系统 */

	/* 查询 由06开始 */
	GET_ACC_INFO("0601"), GET_NICK_NAME("0602"), GET_ORDER_INFO("0603"), ACC_IS_ACTIVE(
			"0604"),
	/* 查询 */

	/* 修改 */
	SET_NICK_NAME("0401"), SET_ORDER_CARD("0402"), SET_BIND("0403"), LOSS_REGISTER(
			"0404"), DELE_ACC("0405"), SET_ACC_ACTIVE("0406"),
	/* 修改 */

	/* 转账 由02开始 */
	GET_TARGET_PHONE_INFO("0201"), TRANSFE_ACC("0202"), GET_LISTQUERY_INFO(
			"0203"), GET_COMEQUERY_INFO("0204"), SET_DETAIL("0205"), GET_PAYMENT_HIS_INFO(
			"0206"), GET_RECHARGE_INFO("0207"), RECHARGE("0208"), GET_PAYMENT_INFO(
			"0209"), PAYMENT("0210"),
	/* 转账 */

	/* 信用卡专用 由03开始 */
	OPEN_CARD("0301"), DESTROY_CARD("0302"), GET_CREDIT_REPAYMENT_INFO("0303"), CREDIT_REPAYMENT(
			"0304"),
	/* 信用卡 */

	/* 助手 由05开始 */
	GET_MONEY_TYPE("0501"), GET_RATE("0502"), GET_EXCHANGE_RESULT("0503");
	/* 助手 */

	private final String mNum;

	private EOperation(String num) {
		mNum = num;
	}

	public static EOperation getEoperation(String num) {
		List<EOperation> list = new ArrayList<EOperation>();

		for (EOperation e : EOperation.values()) {
			if (e.mNum.equals(num)) {
				list.add(e);
			}

		}
		/* 检测是否为空 */
		if (list.size() == 0) {
			throw new NullPointerException(
					"I can't find Enum EOperation,so it is null");
		}

		/* 如果有多个相同操作号，就报错。PS: 没有找到更合适的控制不能重复添加，只能延迟到代码执行的时候才知道 */
		if (list.size() > 1) {
			throw new IllegalArgumentException(
					"It's not client's fault.Operation has been here! don't input again");
		}
		return list.get(0);
	}

	public static String getOperNum(EOperation e) {
		return e.mNum;
	}
}
