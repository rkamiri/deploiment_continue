package ecommercGesture.infrastructure.exception;

public class NegativePriceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private NegativePriceException(String message) {
        super(message);
    }

    public static NegativePriceException withPrice(double price) {
        return new NegativePriceException(String.format("membership price cannot be less than 0 . found : %.2f €.", price));
    }
}
