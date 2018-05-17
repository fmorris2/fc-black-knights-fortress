package scripts.fc.missions.fc_black_knights_fortress.tasks;

import org.tribot.api.General;

import scripts.fc.missions.fc_black_knights_fortress.data.settings.BKFSettings;
import scripts.fc.missions.fc_black_knights_fortress.fortress.FortressNavPoint;

public class TurnInQuest extends SirAmikVarzeDialogue {
	private static final long serialVersionUID = 1L;

	@Override
	protected int[] getOptions() {
		return new int[]{};
	}
	
	@Override
	public boolean execute() {
		if(FortressNavPoint.isPlayerInFortress()) {
			General.println("Navigating out of fortress...");
			return FortressNavPoint.navigateOutOfFortress();
		}
		return super.execute();
	}

	@Override
	public boolean shouldExecute() {
		return BKFSettings.TURN_IN_QUEST.isValid();
	}

	@Override
	public String getStatus() {
		return "Turn in quest";
	}

}
