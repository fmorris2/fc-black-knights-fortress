package scripts.fc.missions.fc_black_knights_fortress.fortress;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.tribot.api.General;
import org.tribot.api2007.Game;
import org.tribot.api2007.Options;
import org.tribot.api2007.types.RSTile;

import scripts.dax_api.walker_engine.local_pathfinding.Reachable.Direction;
import scripts.fc.api.wrappers.FCTiming;

public enum FortressNavPoint {
	ENTRANCE(new FortressNavigationPoint(new RSTile(3016,3514,0), Direction.SOUTH, 2337, "Open"), 
			new FortressNavigationPoint(new RSTile(3016,3514,0), Direction.NORTH, 2337, "Open")),
	PUSH_WALL(new FortressNavigationPoint(new RSTile(3016,3517,0), Direction.SOUTH, 2341, "Push"),
			new FortressNavigationPoint(new RSTile(3016,3517,0), Direction.NORTH, 2341, "Push")),
	LADDER_1(new FortressNavigationPoint(new RSTile(3015,3519,0), Direction.SOUTH, 17148, "Climb-up"), 
			new FortressNavigationPoint(new RSTile(3015,3519,1), Direction.SOUTH, 17149, "Climb-down")),
	LADDER_2(new FortressNavigationPoint(new RSTile(3016,3519,1), Direction.SOUTH, 17148, "Climb-up"), 
			new FortressNavigationPoint(new RSTile(3016,3519,2), Direction.SOUTH, 17149, "Climb-down")),
	LADDER_3(new FortressNavigationPoint(new RSTile(3017,3516,2), Direction.WEST, 17149, "Climb-down"), 
			new FortressNavigationPoint(new RSTile(3017,3516,1), Direction.SOUTH, 17148, "Climb-up")),
	DOOR_2(new FortressNavigationPoint(new RSTile(3019,3515,1), Direction.WEST, 14749, "Open"),
			new FortressNavigationPoint(new RSTile(3019,3515,1), Direction.EAST, 14749, "Open")),
	LADDER_4(new FortressNavigationPoint(new RSTile(3023,3513,1), Direction.NORTH, 17148, "Climb-up"), 
			new FortressNavigationPoint(new RSTile(3023,3513,2), Direction.NORTH, 17149, "Climb-down")),
	LADDER_5(new FortressNavigationPoint(new RSTile(3025,3513,2), Direction.NORTH, 17149, "Climb-down"), 
			new FortressNavigationPoint(new RSTile(3025,3513,1), Direction.NORTH, 17148, "Climb-up")),
	DOOR_3(new FortressNavigationPoint(new RSTile(3025,3511,1), Direction.EAST, 2339, "Open"),
			new FortressNavigationPoint(new RSTile(3025,3511,1), Direction.WEST, 2339, "Open")),
	LADDER_6(new FortressNavigationPoint(new RSTile(3021,3510,1), Direction.EAST, 17149, "Climb-down"), 
			new FortressNavigationPoint(new RSTile(3021,3510,0), Direction.EAST, 17148, "Climb-up")),
	GRILL(new FortressNavigationPoint(new RSTile(3026,3507,0), Direction.WEST, 2342, "Listen-at")),
	
	//TO HOLE
	DOOR_1_TO_HOLE(new DoorOneToHole(new RSTile(3020,3515,0), Direction.WEST, 2338, "Open"),
			new DoorOneToHole(new RSTile(3020,3515,0), Direction.EAST, 2338, "Open")),
			
	LADDER_1_TO_HOLE(new FortressNavigationPoint(new RSTile(3022,3518,0), Direction.SOUTH, 17159, "Climb-up"), 
			new FortressNavigationPoint(new RSTile(3022,3518,1), Direction.SOUTH, 17160, "Climb-down")),
			
	PUSH_WALL_TO_HOLE(new FortressNavigationPoint(new RSTile(3030,3510,1), Direction.NORTH, 2341, "Push"),
			new FortressNavigationPoint(new RSTile(3030,3510,1), Direction.SOUTH, 2341, "Push")),
	
	HOLE(new DropCabbage(new RSTile(3031,3507,1), Direction.WEST, 2336, null))
	;
	
	private static final Map<Integer, Set<Integer>> ADJACENCY_MATRIX = new HashMap<>();
	
	static {
		ADJACENCY_MATRIX.put(ENTRANCE.ordinal(), new HashSet<>(Arrays.asList(PUSH_WALL.ordinal(),DOOR_1_TO_HOLE.ordinal())));
		ADJACENCY_MATRIX.put(PUSH_WALL.ordinal(), new HashSet<>(Arrays.asList(ENTRANCE.ordinal(),LADDER_1.ordinal())));
		ADJACENCY_MATRIX.put(LADDER_1.ordinal(), new HashSet<>(Arrays.asList(PUSH_WALL.ordinal(),LADDER_2.ordinal())));
		ADJACENCY_MATRIX.put(LADDER_2.ordinal(), new HashSet<>(Arrays.asList(LADDER_1.ordinal(),LADDER_3.ordinal())));
		ADJACENCY_MATRIX.put(LADDER_3.ordinal(), new HashSet<>(Arrays.asList(LADDER_2.ordinal(), DOOR_2.ordinal())));
		ADJACENCY_MATRIX.put(DOOR_2.ordinal(), new HashSet<>(Arrays.asList(LADDER_3.ordinal(),LADDER_4.ordinal())));
		ADJACENCY_MATRIX.put(LADDER_4.ordinal(), new HashSet<>(Arrays.asList(DOOR_2.ordinal(),LADDER_5.ordinal())));
		ADJACENCY_MATRIX.put(LADDER_5.ordinal(), new HashSet<>(Arrays.asList(LADDER_4.ordinal(),DOOR_3.ordinal())));
		ADJACENCY_MATRIX.put(DOOR_3.ordinal(), new HashSet<>(Arrays.asList(LADDER_5.ordinal(),LADDER_6.ordinal())));
		ADJACENCY_MATRIX.put(LADDER_6.ordinal(), new HashSet<>(Arrays.asList(DOOR_3.ordinal(),GRILL.ordinal())));
		ADJACENCY_MATRIX.put(GRILL.ordinal(), new HashSet<>(Arrays.asList(LADDER_6.ordinal())));
		ADJACENCY_MATRIX.put(DOOR_1_TO_HOLE.ordinal(), new HashSet<>(Arrays.asList(ENTRANCE.ordinal(), LADDER_1_TO_HOLE.ordinal())));
		ADJACENCY_MATRIX.put(LADDER_1_TO_HOLE.ordinal(), new HashSet<>(Arrays.asList(DOOR_1_TO_HOLE.ordinal(), PUSH_WALL_TO_HOLE.ordinal())));
		ADJACENCY_MATRIX.put(PUSH_WALL_TO_HOLE.ordinal(), new HashSet<>(Arrays.asList(LADDER_1_TO_HOLE.ordinal(), HOLE.ordinal())));
		ADJACENCY_MATRIX.put(HOLE.ordinal(), new HashSet<>(Arrays.asList(PUSH_WALL_TO_HOLE.ordinal())));
	}
	
	public final FortressNavigationPoint FORWARD_NAV_POINT, BACKWARD_NAV_POINT;
	
	FortressNavPoint(final FortressNavigationPoint p){
		FORWARD_NAV_POINT = BACKWARD_NAV_POINT = p;
	}
	
	FortressNavPoint(final FortressNavigationPoint forward, final FortressNavigationPoint backward, 
			final RSTile afterInteractionTileForward, final RSTile afterInteractionTileBackward) {
		FORWARD_NAV_POINT = forward;
		BACKWARD_NAV_POINT = backward;
	}
	
	FortressNavPoint(final FortressNavigationPoint forward, final FortressNavigationPoint backward) {
		FORWARD_NAV_POINT = forward;
		BACKWARD_NAV_POINT = backward;
	}
	
	private static FortressNavPoint getCurrentNavPointPlayerIsAt() {
		for(final FortressNavPoint p : values()) {
			if(p.FORWARD_NAV_POINT.isPlayerAt() || p.BACKWARD_NAV_POINT.isPlayerAt())
				return p;
		}
		
		return ENTRANCE;
	}
	
	public static boolean isPlayerInFortress() {
		for(final FortressNavPoint p : values()) {
			if(p == ENTRANCE) {
				if(p.BACKWARD_NAV_POINT.isPlayerAt())
					return true;
			}
			else if(p.FORWARD_NAV_POINT.isPlayerAt() || p.BACKWARD_NAV_POINT.isPlayerAt())
				return true;
		}
		
		return false;
	}
	
	public static boolean navigateOutOfFortress() {
		if(FortressNavPoint.ENTRANCE.navigateTo() && FortressNavPoint.ENTRANCE.BACKWARD_NAV_POINT.isPlayerAt()) {
			if(FortressNavPoint.ENTRANCE.BACKWARD_NAV_POINT.proceedToAndInteract()) {
				return FCTiming.waitCondition(() -> !FortressNavPoint.isPlayerInFortress(), 4000);
			}
		}
		return false;
	}
	
	public LinkedList<FortressNavPoint> generatePathToDest(final FortressNavPoint dest) {
		if(this == dest)
			return new LinkedList<>();
			
		final Set<FortressNavPoint> alreadyChecked = new HashSet<>();
		final LinkedList<FortressNavPoint> path = new LinkedList<>();
		return generatePathToDest(dest, path, alreadyChecked);
	}
	
	private static boolean isBackwardStep(final FortressNavPoint start, final FortressNavPoint end) {
		if(start == ENTRANCE)
			return false;
		
		final LinkedList<FortressNavPoint> fromStart = start.generatePathToDest(ENTRANCE);
		final LinkedList<FortressNavPoint> fromEnd = end.generatePathToDest(ENTRANCE);
		
		return fromStart.size() >= fromEnd.size();
	}
	
	private LinkedList<FortressNavPoint> generatePathToDest(final FortressNavPoint dest, final LinkedList<FortressNavPoint> path, final Set<FortressNavPoint> alreadyChecked) {
		final Set<Integer> connectionSet = ADJACENCY_MATRIX.get(dest.ordinal());
		if(connectionSet.contains(this.ordinal())) {
			path.add(dest);
			return path;
		}
		
		for(final int connectionIndex : connectionSet) {
			if(alreadyChecked.contains(values()[connectionIndex])) {
				continue;
			}
			alreadyChecked.add(values()[connectionIndex]);
			final Queue<FortressNavPoint> connection = generatePathToDest(values()[connectionIndex], path, alreadyChecked);
			if(connection != null) {
				path.add(dest);
				return path;
			}
		}
		
		return new LinkedList<>(); //no path found, or we're at destination
	}
	
	//navigates to forward interactable object of this point
	public boolean navigateTo() {
		final FortressNavPoint currentlyAt = getCurrentNavPointPlayerIsAt();
		General.println("Player is currently at: " + currentlyAt.toString());
		
		if(currentlyAt == this)
			return true;
		
		final LinkedList<FortressNavPoint> path = currentlyAt.generatePathToDest(this);
		
		if(path == null)
			return false;
		
		if(isBackwardStep(currentlyAt, path.peek()))
			path.addFirst(currentlyAt);
		
		navigateThroughPath(path);
		
		return this.FORWARD_NAV_POINT.isPlayerAt() || this.BACKWARD_NAV_POINT.isPlayerAt();
	}
	
	private void navigateThroughPath(final LinkedList<FortressNavPoint> path) {
		final int FAILURE_THRESH = 5;
		int failures = 0;
		while(!path.isEmpty() || failures > FAILURE_THRESH) {
			checkRun();
			final FortressNavPoint nextToInteractWith = getNextInteract(path);
			General.println("[FC Fortress Navigator]: Path: " + path.toString());
			General.println("[FC Fortress Navigator]: Next to interact with: " + nextToInteractWith);
			if(!path.contains(nextToInteractWith) && isPlayerInFortress()) {
				path.clear();
				path.addAll(getCurrentNavPointPlayerIsAt().generatePathToDest(this));
				continue;
			}
			//boolean isBackwardStep = isBackwardStep(getCurrentNavPointPlayerIsAt(), nextToInteractWith);
			final FortressNavigationPoint toInteractWith = nextToInteractWith.BACKWARD_NAV_POINT.isPlayerAt() 
					? nextToInteractWith.BACKWARD_NAV_POINT : nextToInteractWith.FORWARD_NAV_POINT;
			
			final boolean interactResult = nextToInteractWith == GRILL || nextToInteractWith == HOLE ?
					toInteractWith.proceedTo() : toInteractWith.proceedToAndInteract();
			if(interactResult) {
				if(nextToInteractWith == GRILL || nextToInteractWith == HOLE)
					break;
				
				if(path.size() >= 1 && path.getLast() == ENTRANCE 
						&& FCTiming.waitCondition(() -> !isPlayerInFortress(), 5000)) {
					break;
				}
				
				if(FCTiming.waitCondition(() -> getNextInteract(path) != nextToInteractWith, 5000)) {
					while(!path.isEmpty()) {
						final FortressNavPoint removed = path.removeFirst();
						if(removed == nextToInteractWith)
							break;
					}
				}
			}
			else
				failures++;
		}
	}
	
	private void checkRun() {
		if(!Game.isRunOn() && Game.getRunEnergy() > 30)
			Options.setRunEnabled(true);
	}
	
	private FortressNavPoint getNextInteract(final LinkedList<FortressNavPoint> path) {
		for(int i = path.size() - 1; i >= 0; i--) {
			final FortressNavPoint point = path.get(i);
			if(point.BACKWARD_NAV_POINT.isPlayerAt() || point.FORWARD_NAV_POINT.isPlayerAt())
				return point;
		}
		
		return ENTRANCE;
	}
}
