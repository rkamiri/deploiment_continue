package ecommercGesture.infrastructure.exception;

import ecommercGesture.domain.objects.Id;

public class IsAlreadyMemberException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private IsAlreadyMemberException(String message) {
        super(message);
    }

    public static IsAlreadyMemberException withId(Id id) {
        return new IsAlreadyMemberException(String.format("The user with ID %d have already sucribed to our membership. Please renew instead of subscribe", id.getId()));
    }
}
