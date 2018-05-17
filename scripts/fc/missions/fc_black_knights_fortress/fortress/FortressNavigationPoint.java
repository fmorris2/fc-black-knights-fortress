package scripts.fc.missions.fc_black_knights_fortress.fortress;

import org.tribot.api2007.Objects;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.EntityInteraction;
import scripts.fc.api.interaction.impl.objects.ClickObject;
import scripts.fc.api.travel.Travel;
import scripts.webwalker_logic.local.walker_engine.local_pathfinding.Reachable.Direction;

public class FortressNavigationPoint {
	private static final int DISTANCE_THRESHOLD = 20;
	
	private final RSTile OBJECT_TILE, INTERACT_FROM_TILE;
	private final int OBJECT_ID;
	
	private String action;

	public FortressNavigationPoint(RSTile t, Direction d, int id, String action) {
		OBJECT_TILE = t;
		INTERACT_FROM_TILE = d.getPointingTile(t);
		OBJECT_ID = id;
		this.action = action;
	}
	
	public boolean proceedTo() {
		if(Player.getPosition().distanceTo(OBJECT_TILE) > DISTANCE_THRESHOLD) {
			Travel.webWalkTo(OBJECT_TILE, FCConditions.withinDistanceOfTile(OBJECT_TILE, DISTANCE_THRESHOLD));
		}
		
		return Player.getPosition().distanceTo(OBJECT_TILE) <= DISTANCE_THRESHOLD;
	}
	
	public boolean proceedToAndInteract() {
		if(proceedTo())
			return generateInteraction().execute();
		
		return false;
	}
	
	public boolean isPlayerAt() {
		RSTile playerPos = Player.getPosition();
		
		if(playerPos.getPlane() != OBJECT_TILE.getPlane())
			return false;
		
		if(playerPos.distanceTo(OBJECT_TILE) > DISTANCE_THRESHOLD)
			return false;
		
		return PathFinding.canReach(INTERACT_FROM_TILE, false);
	}
	
	public RSObject findObject() {
		RSObject[] objs = Objects.getAt(OBJECT_TILE, Filters.Objects.idEquals(OBJECT_ID));
		
		return objs.length > 0 ? objs[0] : null;
	}
	
	public EntityInteraction generateInteraction() {
		return new ClickObject(action, findObject());
	}
}
