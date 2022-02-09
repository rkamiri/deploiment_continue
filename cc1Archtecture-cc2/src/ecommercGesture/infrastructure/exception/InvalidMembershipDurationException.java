package ecommercGesture.infrastructure.exception;

public class InvalidMembershipDurationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private InvalidMembershipDurationException(String message) {
        super(message);
    }

    public static InvalidMembershipDurationException withDay(int day) {
        return new InvalidMembershipDurationException(String.format("membership duration cannot be 0 day or less. found : %d days.", day));
    }
}
