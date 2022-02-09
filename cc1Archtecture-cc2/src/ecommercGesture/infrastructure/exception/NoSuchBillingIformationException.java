package ecommercGesture.infrastructure.exception;

import ecommercGesture.domain.objects.Id;

public class NoSuchBillingIformationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private NoSuchBillingIformationException(String message) {
        super(message);
    }

    public static NoSuchBillingIformationException withId(Id id) {
        return new NoSuchBillingIformationException(String.format("No billing information found for user with ID %d.", id.getId()));
    }
}
