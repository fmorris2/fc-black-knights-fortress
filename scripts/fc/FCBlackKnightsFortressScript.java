package scripts.fc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Ending;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.interfaces.Starting;

import scripts.fc.framework.mission.Mission;
import scripts.fc.framework.paint.FCPaintable;
import scripts.fc.framework.script.FCMissionScript;
import scripts.fc.missions.fc_black_knights_fortress.FCBlackKnightsFortress;

@ScriptManifest(
	authors     = { 
	    "Final Calibur",
	}, 
	category    = "Quests", 
	name        = "FC Black Knight's Fortress", 
	version     = 0.1, 
	description = "Completes The Black Knights Fortress. Start anywhere. Make sure to have enough GP to buy materials from GE.", 
	gameMode    = 1)
public class FCBlackKnightsFortressScript extends FCMissionScript implements FCPaintable, Painting, Starting, Ending
{
	private static final long serialVersionUID = 1L;

	@Override
	protected Queue<Mission> getMissions()
	{
		return new LinkedList<>(Arrays.asList(new FCBlackKnightsFortress(this)));
	}

	@Override
	protected String[] scriptSpecificPaint()
	{
		return new String[]{};
	}
}
