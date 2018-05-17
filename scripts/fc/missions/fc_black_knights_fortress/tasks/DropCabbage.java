package scripts.fc.missions.fc_black_knights_fortress.tasks;

import org.tribot.api.General;

import scripts.fc.api.interaction.impl.npcs.dialogue.DialogueThread;
import scripts.fc.api.items.FCItem;
import scripts.fc.api.utils.FoodUtils;
import scripts.fc.api.wrappers.FCTiming;
import scripts.fc.framework.equipment.EquipmentSet;
import scripts.fc.framework.task.EquipmentRequiredTask;
import scripts.fc.framework.task.ItemsRequiredTask;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fc_black_knights_fortress.FCBlackKnightsFortress;
import scripts.fc.missions.fc_black_knights_fortress.data.reqs.BKFItemReqs;
import scripts.fc.missions.fc_black_knights_fortress.data.settings.BKFSettings;
import scripts.fc.missions.fc_black_knights_fortress.fortress.FortressNavPoint;

public class DropCabbage extends Task implements ItemsRequiredTask, EquipmentRequiredTask {
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
		General.println("Navigating to cauldron sabotage point");
		if(DialogueThread.areDialogueInterfacesUp()) {
			DialogueThread.doClickToContinue();
		}
		else if(FortressNavPoint.HOLE.navigateTo()) {
			if(FortressNavPoint.HOLE.FORWARD_NAV_POINT.proceedToAndInteract())
				FCTiming.waitCondition(() -> !BKFSettings.DROP_CABBAGE_IN_HOLE.isValid(), 5000);
		}
		
		return true;
	}

	@Override
	public boolean shouldExecute() {
		return BKFSettings.DROP_CABBAGE_IN_HOLE.isValid();
	}

	@Override
	public String getStatus() {
		return "Sabotage Cauldron";
	}
}
