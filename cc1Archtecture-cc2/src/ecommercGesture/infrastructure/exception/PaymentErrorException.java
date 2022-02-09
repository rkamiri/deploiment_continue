package ecommercGesture.infrastructure.exception;

public class PaymentErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private PaymentErrorException(String message) {
        super(message);
    }

    public static PaymentErrorException error() {
        return new PaymentErrorException(String.format("The payment was unable to be done"));
    }
}
