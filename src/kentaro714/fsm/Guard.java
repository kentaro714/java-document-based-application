package kentaro714.fsm;

public interface Guard {
	void validForStateChange(Event event) throws GuardException;
}
