package scripts.fc.missions.fc_black_knights_fortress.data;

import java.util.Arrays;

import scripts.fc.framework.task.Task;

public enum BKFTasks {
	
	;
	
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
