package ecommercGesture.infrastructure.exception;

import ecommercGesture.domain.objects.Id;

public class IsNotMemberException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private IsNotMemberException(String message) {
        super(message);
    }

    public static IsNotMemberException withId(Id id) {
        return new IsNotMemberException(String.format("The user ID %d have never sucribed to our membership. Please subscribe before trying to renew your membership", id.getId()));
    }
}
