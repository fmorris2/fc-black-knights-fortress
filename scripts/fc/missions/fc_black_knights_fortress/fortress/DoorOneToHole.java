package scripts.fc.missions.fc_black_knights_fortress.fortress;

import org.tribot.api.Timing;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.npcs.dialogue.NpcDialogue;
import scripts.webwalker_logic.local.walker_engine.local_pathfinding.Reachable.Direction;

public class DoorOneToHole extends FortressNavigationPoint {

	public DoorOneToHole(RSTile t, Direction d, int id, String action) {
		super(t, d, id, action);
	}
	
	@Override
	public boolean proceedToAndInteract() {
		if(super.proceedToAndInteract() && Timing.waitCondition(FCConditions.IN_DIALOGUE_CONDITION, 5000)) {
			NpcDialogue dialogue = new NpcDialogue("Talk-to", "Fortress Guard", 10, 1);
			dialogue.setIgnoreNpc(true);
			return dialogue.execute();
		}
		
		return false;
	}

}
