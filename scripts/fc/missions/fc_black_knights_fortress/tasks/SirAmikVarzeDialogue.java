package scripts.fc.missions.fc_black_knights_fortress.tasks;

import java.util.function.BooleanSupplier;

import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.interaction.EntityInteraction;
import scripts.fc.api.interaction.impl.npcs.dialogue.NpcDialogue;
import scripts.fc.framework.task.BasicInteractionTask;

public abstract class SirAmikVarzeDialogue extends BasicInteractionTask {
	private static final long serialVersionUID = 1L;
	
	protected abstract int[] getOptions();

	@Override
	protected Positionable getPosition() {
		return new RSTile(2959, 3338, 2);
	}

	@Override
	protected int getRadius() {
		return 7;
	}

	@Override
	protected EntityInteraction getInteraction() {
		return new NpcDialogue("Talk-to", "Sir Amik Varze", 10, getOptions());
	}

	@Override
	protected BooleanSupplier getWaitCondition() {
		return null;
	}

	@Override
	protected int getWaitTimeout() {
		return 0;
	}
}
