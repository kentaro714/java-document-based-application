package kentaro714.fsm;

public class FiniteStateMachine {
	private State currentState;
	
	public FiniteStateMachine(State initialState) {
		this.currentState = initialState;
	}
	public void fire(Event event) throws GuardException {
		currentState = currentState.fire(event);
	}
	public static FiniteStateMachine newInstance(State initialState) {
		return new FiniteStateMachine(initialState);
	}
	public State current() {
		return currentState;
	}
}
