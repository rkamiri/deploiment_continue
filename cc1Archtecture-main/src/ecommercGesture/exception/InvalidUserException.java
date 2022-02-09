package ecommercGesture.exception;

import ecommercGesture.objects.Id;

public class InvalidUserException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InvalidUserException(String message) {
        super(message);
    }

    public static InvalidUserException withId(Id id) {
        return new InvalidUserException(String.format("Invalid information found for user with ID %d.", id.getId()));
    }

}
