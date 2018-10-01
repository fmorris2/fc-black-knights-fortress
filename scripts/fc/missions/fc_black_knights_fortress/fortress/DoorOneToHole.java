package scripts.fc.missions.fc_black_knights_fortress.fortress;

import org.tribot.api.Timing;
import org.tribot.api2007.types.RSTile;

import scripts.dax_api.walker_engine.local_pathfinding.Reachable.Direction;
import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.npcs.dialogue.NpcDialogue;

public class DoorOneToHole extends FortressNavigationPoint {

	public DoorOneToHole(final RSTile t, final Direction d, final int id, final String action) {
		super(t, d, id, action);
	}
	
	@Override
	public boolean proceedToAndInteract() {
		if(super.proceedToAndInteract() && Timing.waitCondition(FCConditions.IN_DIALOGUE_CONDITION, 5000)) {
			final NpcDialogue dialogue = new NpcDialogue("Talk-to", "Fortress Guard", 10, 1);
			dialogue.setIgnoreNpc(true);
			return dialogue.execute();
		}
		
		return false;
	}

}
