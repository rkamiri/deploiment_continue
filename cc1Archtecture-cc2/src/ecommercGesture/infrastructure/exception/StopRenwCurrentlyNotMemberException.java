package ecommercGesture.infrastructure.exception;

public class StopRenwCurrentlyNotMemberException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	private StopRenwCurrentlyNotMemberException(String message) {
        super(message);
    }

    public static StopRenwCurrentlyNotMemberException error() {
        return new StopRenwCurrentlyNotMemberException(String.format("You cant stop a membership that is already finished"));
    }
}
