package scripts.fc.missions.fc_black_knights_fortress.tasks;

import scripts.fc.api.items.FCItem;
import scripts.fc.framework.equipment.EquipmentSet;
import scripts.fc.framework.task.EquipmentRequiredTask;
import scripts.fc.framework.task.FutureTaskPreparer;
import scripts.fc.framework.task.ItemsRequiredTask;
import scripts.fc.missions.fc_black_knights_fortress.FCBlackKnightsFortress;
import scripts.fc.missions.fc_black_knights_fortress.data.BKFTasks;
import scripts.fc.missions.fc_black_knights_fortress.data.settings.BKFSettings;

public class StartQuest extends SirAmikVarzeDialogue implements EquipmentRequiredTask, FutureTaskPreparer, ItemsRequiredTask {
	private static final long serialVersionUID = 1L;

	@Override
	protected int[] getOptions() {
		return new int[]{0,0,0};
	}

	@Override
	public boolean shouldExecute() {
		return BKFSettings.START_QUEST.isValid();
	}

	@Override
	public String getStatus() {
		return "Start quest";
	}

	@Override
	public EquipmentSet getNeededEquipmentSet() {
		return FCBlackKnightsFortress.EQUIPMENT;
	}

	@Override
	public ItemsRequiredTask[] getFutureTasks() {
		return new ItemsRequiredTask[]{
				(ItemsRequiredTask)BKFTasks.LISTEN_AT_GRILL.TASK, 
				(ItemsRequiredTask)BKFTasks.DROP_CABBAGE.TASK
		};
	}

	@Override
	public FCItem[] getRequiredItems() {
		return new FCItem[0];
	}

}
