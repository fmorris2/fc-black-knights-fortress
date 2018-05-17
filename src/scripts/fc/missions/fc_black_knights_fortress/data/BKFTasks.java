package scripts.fc.missions.fc_black_knights_fortress.data;

import java.util.Arrays;

import scripts.fc.framework.task.Task;
import scripts.fc.missions.fc_black_knights_fortress.tasks.DropCabbage;
import scripts.fc.missions.fc_black_knights_fortress.tasks.ListenAtGrill;
import scripts.fc.missions.fc_black_knights_fortress.tasks.StartQuest;
import scripts.fc.missions.fc_black_knights_fortress.tasks.TurnInQuest;

public enum BKFTasks {
	START_QUEST(new StartQuest()),
	LISTEN_AT_GRILL(new ListenAtGrill()),
	DROP_CABBAGE(new DropCabbage()),
	TURN_IN_QUEST(new TurnInQuest());
	
	public final Task TASK;
	
	BKFTasks(Task t)
	{
		TASK = t;
	}
	
	public static Task[] getTasks()
	{
		return Arrays.stream(values())
				.map(ks -> ks.TASK)
				.toArray(Task[]::new);
	}
}
