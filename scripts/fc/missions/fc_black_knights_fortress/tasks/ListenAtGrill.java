package scripts.fc.missions.fc_black_knights_fortress.tasks;

import org.tribot.api.General;

import scripts.fc.api.interaction.impl.npcs.dialogue.DialogueThread;
import scripts.fc.api.items.FCItem;
import scripts.fc.api.utils.FoodUtils;
import scripts.fc.api.wrappers.FCTiming;
import scripts.fc.framework.equipment.EquipmentSet;
import scripts.fc.framework.task.EquipmentRequiredTask;
import scripts.fc.framework.task.FutureTaskPreparer;
import scripts.fc.framework.task.ItemsRequiredTask;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fc_black_knights_fortress.FCBlackKnightsFortress;
import scripts.fc.missions.fc_black_knights_fortress.data.BKFTasks;
import scripts.fc.missions.fc_black_knights_fortress.data.reqs.BKFItemReqs;
import scripts.fc.missions.fc_black_knights_fortress.data.settings.BKFSettings;
import scripts.fc.missions.fc_black_knights_fortress.fortress.FortressNavPoint;

public class ListenAtGrill extends Task implements ItemsRequiredTask, EquipmentRequiredTask, FutureTaskPreparer {
	private static final long serialVersionUID = 1L;

	@Override
	public EquipmentSet getNeededEquipmentSet() {
		return FCBlackKnightsFortress.EQUIPMENT;
	}

	@Override
	public FCItem[] getRequiredItems() {
		return new FCItem[]{
			new FCItem(1, false, BKFItemReqs.CABBAGE),
			FoodUtils.generateOptionalFoodFCItemToWithdraw(BKFItemReqs.TROUT, BKFItemReqs.MIN_TROUT_AMT, BKFItemReqs.OPTIMAL_TROUT_AMT)
		};
	}

	@Override
	public boolean execute() {
		General.println("Navigating to grill");
		if(DialogueThread.areDialogueInterfacesUp()) {
			DialogueThread.doClickToContinue();
		}
		else if(FortressNavPoint.GRILL.navigateTo()) {
			General.println("GenerateInteraction and execute");
			if(FortressNavPoint.GRILL.FORWARD_NAV_POINT.generateInteraction().execute()) {
				General.println("Interact with grill");
				FCTiming.waitCondition(() -> DialogueThread.areDialogueInterfacesUp(), 5000);
			
			}
		}
		
		return true;
	}

	@Override
	public boolean shouldExecute() {
		return BKFSettings.LISTEN_AT_GRILL.isValid();
	}

	@Override
	public String getStatus() {
		return "Listen at Grill";
	}

	@Override
	public ItemsRequiredTask[] getFutureTasks() {
		return new ItemsRequiredTask[] {
			(ItemsRequiredTask)BKFTasks.DROP_CABBAGE.TASK
		};
	}

}
