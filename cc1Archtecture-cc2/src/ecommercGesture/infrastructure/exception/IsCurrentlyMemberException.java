package ecommercGesture.infrastructure.exception;

import ecommercGesture.domain.objects.Id;

public class IsCurrentlyMemberException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private IsCurrentlyMemberException(String message) {
        super(message);
    }

    public static IsCurrentlyMemberException withId(Id id, String endDate) {
        return new IsCurrentlyMemberException(String.format("The user with ID %d is currently a member. renew your membership the "+ endDate +" if automatic renew is disabled", id.getId()));
    }
}
