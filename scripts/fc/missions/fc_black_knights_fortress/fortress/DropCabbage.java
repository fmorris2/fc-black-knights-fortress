package scripts.fc.missions.fc_black_knights_fortress.fortress;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.types.RSTile;

import scripts.dax_api.walker_engine.local_pathfinding.Reachable.Direction;
import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.EntityInteraction;
import scripts.fc.api.interaction.impl.npcs.dialogue.NpcDialogue;
import scripts.fc.api.interaction.impl.objects.ItemOnObject;
import scripts.fc.missions.fc_black_knights_fortress.data.reqs.BKFItemReqs;
import scripts.fc.missions.fc_black_knights_fortress.data.settings.BKFSettings;

public class DropCabbage extends FortressNavigationPoint {

	public DropCabbage(final RSTile t, final Direction d, final int id, final String action) {
		super(t, d, id, action);
	}
	
	@Override
	public EntityInteraction generateInteraction() {
		return new ItemOnObject("Use", findObject(), BKFItemReqs.CABBAGE);
	}
	
	@Override
	public boolean proceedToAndInteract() {
		if(super.proceedToAndInteract() && Timing.waitCondition(FCConditions.IN_DIALOGUE_CONDITION, 20000)) {
			while(BKFSettings.DROP_CABBAGE_IN_HOLE.isValid()) {
				final NpcDialogue dialogue = new NpcDialogue("Talk-to", "Witch", 10, 0);
				dialogue.setIgnoreNpc(true);
				dialogue.execute();
				General.sleep(100, 400);
			}
			return true;
		}
		
		return false;
	}
}
