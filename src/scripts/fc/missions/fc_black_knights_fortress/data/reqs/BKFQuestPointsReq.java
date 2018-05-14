package scripts.fc.missions.fc_black_knights_fortress.data.reqs;

import scripts.fc.framework.quest.QuestMission;
import scripts.fc.framework.requirement.QuestPointRequirement;
import scripts.fc.framework.script.FCMissionScript;
import scripts.fc.missions.fc_ernest_the_chicken.FCErnestTheChicken;
import scripts.fc.missions.fcgoblindiplomacy.FCGoblinDiplomacy;
import scripts.fc.missions.fcromeoandjuliet.FCRomeoAndJuliet;

public class BKFQuestPointsReq extends QuestPointRequirement {

	private static final int REQUIRED_AMT = 12;
	
	
	public BKFQuestPointsReq(FCMissionScript script) {
		super(script, REQUIRED_AMT, generatePreReqMissions(script));
	}
	
	private static QuestMission[] generatePreReqMissions(FCMissionScript script) {
		return new QuestMission[] {
				new FCRomeoAndJuliet(script),
				new FCErnestTheChicken(script),
				new FCGoblinDiplomacy(script)
		};
	}

}
