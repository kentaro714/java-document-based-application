package kentaro714.fsm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State {

	private Map<Event, List<Action>> actions = new HashMap<Event, List<Action>>();
	private Map<Event, State> transitions = new HashMap<Event, State>();
	private Map<Event, List<Guard>> guards = new HashMap<Event, List<Guard>>();
	private String name;
	// FIXME not used yet...
	private State superState;
	
	public State(String name) {
		this.name = name;
	}

	public State fire(Event event) throws GuardException {
		if(guards.containsKey(event)) {
			for (Guard guard : guards.get(event)) {
				guard.validForStateChange(event);
			}
		}
		
		if(actions.containsKey(event)) {
			for (Action action : actions.get(event)) {
				action.execute();
			}
		}
		if(transitions.containsKey(event)) {
			return transitions.get(event);
		} else {
			return this;
		}
	}

	public void registerAction(Event event, Action action) {
		if(!actions.containsKey(event)) {
			actions.put(event, new ArrayList<Action>());
		}
		List<Action> actionList = actions.get(event);
		actionList.add(action);
	}
	
	public void registerTransition(Event event, State to) {
		if(transitions.containsKey(event)) {
			// FIXME message
			// FIXME support guard
			throw new IllegalStateException();
		}
		transitions.put(event, to);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getSuperState() {
		return superState;
	}

	public void setSuperState(State superState) {
		this.superState = superState;
	}
	
}
