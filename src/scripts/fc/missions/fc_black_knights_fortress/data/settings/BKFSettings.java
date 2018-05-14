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
