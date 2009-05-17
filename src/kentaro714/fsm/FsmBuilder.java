package kentaro714.fsm;

public class FsmBuilder {

	public static StateBuilder state(String name) {
		return state(new State(name));
	}

	public static StateBuilder state(State state) {
		return new StateBuilder(state);
	}

	public static EventBuilder event(String name) {
		return new EventBuilder(new Event(name));
	}

	public static class EventBuilder extends FsmBuilder {
		private Event currentEvent;
		
		public EventBuilder(Event event) {
			currentEvent = event;
		}

		public Event build() {
			return currentEvent;
		}
	}
	
	public static class StateBuilder extends FsmBuilder {
		private State currentState;
		private Event currentEvent;
		
		public StateBuilder(State state) {
			this.currentState = state;
		}

		public State build() {
			return currentState;
		}

		public StateBuilder on(Event event) {
			this.currentEvent = event;
			return this;
		}

		public StateBuilder action(Action action) {
			currentState.registerAction(currentEvent, action);
			return this;
		}

		public StateBuilder to(State state) {
			currentState.registerTransition(currentEvent, state);
			return this;
		}
	}
}
