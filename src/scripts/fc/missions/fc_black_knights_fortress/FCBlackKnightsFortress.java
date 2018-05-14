package scripts.fc.missions.fc_black_knights_fortress;

import java.util.Arrays;
import java.util.LinkedList;

import scripts.fc.api.items.FCItem;
import scripts.fc.framework.quest.QuestScriptManager;
import scripts.fc.framework.requirement.Requirement;
import scripts.fc.framework.script.FCMissionScript;
import scripts.fc.framework.task.Task;
import scripts.fc.framework.threads.FCFoodThread;
import scripts.fc.missions.fc_black_knights_fortress.data.BKFTasks;
import scripts.fc.missions.fc_black_knights_fortress.data.reqs.BKFItemReqs;
import scripts.fc.missions.fc_black_knights_fortress.data.reqs.BKFQuestPointsReq;
import scripts.fc.missions.fc_black_knights_fortress.data.settings.BKFSettings;

public class FCBlackKnightsFortress extends QuestScriptManager
{
	private static final long serialVersionUID = 1L;
	public static final String QUEST_NAME = "Black Knight's Fortress";
	public static final int SETTING = 130;
	private final FCFoodThread FOOD_THREAD;
	
	public FCBlackKnightsFortress(FCMissionScript fcScript)
	{
		super(fcScript);
		FOOD_THREAD = new FCFoodThread(50, 65, new FCItem(1, false, BKFItemReqs.TROUT));
		FOOD_THREAD.start();
	}

	@Override
	public boolean canStart()
	{
		return true;
	}

	@Override
	public boolean hasReachedEndingCondition()
	{
		return BKFSettings.QUEST_COMPLETE.isValid();
	}

	@Override
	public String getMissionName()
	{
		return "FC Black Knight's Fortress";
	}

	@Override
	public String getEndingMessage()
	{
		return "FC Black Knight's Fortress has ended";
	}

	@Override
	public String[] getMissionSpecificPaint()
	{
		return new String[]{};
	}

	@Override
	public void resetStatistics()
	{}

	@Override
	public Requirement[] getRequirements()
	{
		return new Requirement[]{new BKFQuestPointsReq(missionScript), new BKFItemReqs(missionScript)};
	}

	@Override
	public LinkedList<Task> getTaskList()
	{
		return new LinkedList<>(Arrays.asList(BKFTasks.getTasks()));
	}
	
	public String toString()
	{
		return QUEST_NAME;
	}

	@Override
	public int getQuestPointReward() {
		return 3;
	}
}
