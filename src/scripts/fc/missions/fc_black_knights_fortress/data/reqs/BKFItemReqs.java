package scripts.fc.missions.fc_black_knights_fortress.data.reqs;

import scripts.fc.framework.quest.InvBankBool;
import scripts.fc.framework.quest.InvBankBool.TYPE;
import scripts.fc.framework.quest.Order;
import scripts.fc.framework.quest.SettingBool;
import scripts.fc.framework.requirement.item.ItemRequirement;
import scripts.fc.framework.requirement.item.ReqItem;
import scripts.fc.framework.requirement.item.SingleReqItem;
import scripts.fc.framework.script.FCMissionScript;
import scripts.fc.missions.fc_black_knights_fortress.FCBlackKnightsFortress;

public class BKFItemReqs extends ItemRequirement {
	
	public static final int TROUT = 333, IRON_CHAIN = 1101, BRONZE_MED = 1139, CABBAGE = 1965;
	
	public BKFItemReqs(FCMissionScript script) {
		super(script);
	}

	@Override
	public ReqItem[] getReqItems() {
		return new ReqItem[] {
			new SingleReqItem(TROUT, 8, true, true)
				.when(new SettingBool(FCBlackKnightsFortress.SETTING, 3, true, Order.BEFORE_EQUALS)
					.and(new InvBankBool(TROUT, 3, TYPE.NOT_IN_EITHER, true))),
					
			new SingleReqItem(IRON_CHAIN, 1, true, true)
				.when(new SettingBool(FCBlackKnightsFortress.SETTING, 3, true, Order.BEFORE_EQUALS)),
				
			new SingleReqItem(BRONZE_MED, 1, true, true)
				.when(new SettingBool(FCBlackKnightsFortress.SETTING, 3, true, Order.BEFORE_EQUALS)),

			new SingleReqItem(CABBAGE, 1, true, true)
				.when(new SettingBool(FCBlackKnightsFortress.SETTING, 3, true, Order.BEFORE))
		};
	}

}
