package scripts.fc.missions.fc_black_knights_fortress.data.settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import scripts.fc.framework.quest.Order;
import scripts.fc.framework.quest.QuestBool;
import scripts.fc.framework.quest.QuestState;
import scripts.fc.framework.quest.SettingBool;
import scripts.fc.missions.fc_black_knights_fortress.FCBlackKnightsFortress;

public enum BKFSettings {
	
	START_QUEST
	(
		new QuestState
		(
			new SettingBool(FCBlackKnightsFortress.SETTING, 0, true, Order.EQUALS)
		)
	),
	
	LISTEN_AT_GRILL
	(
		new QuestState
		(
			new SettingBool(FCBlackKnightsFortress.SETTING, 1, true, Order.EQUALS)
		)
	),
	
	DROP_CABBAGE_IN_HOLE
	(
		new QuestState
		(
			new SettingBool(FCBlackKnightsFortress.SETTING, 2, true, Order.EQUALS)
		)
	),
	
	TURN_IN_QUEST
	(
		new QuestState
		(
			new SettingBool(FCBlackKnightsFortress.SETTING, 3, true, Order.EQUALS)
		)
	),
	
	QUEST_COMPLETE
	(
		new QuestState
		(
			new SettingBool(FCBlackKnightsFortress.SETTING, 4, true, Order.EQUALS)
		)
	);

	private QuestState[] states;

	BKFSettings(QuestState... states) {
		this.states = states;
	}

	public boolean isValid() {
		return Arrays.stream(states).allMatch(s -> s.validate());
	}

	public QuestBool[] getBools() {
		List<QuestBool> bools = new ArrayList<>();
		Arrays.stream(states).forEach(
				s -> Arrays.stream(s.getBools()).forEach(b -> bools.add(b)));
		return bools.toArray(new QuestBool[bools.size()]);
	}
}
