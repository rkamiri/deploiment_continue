package kernel;

import ecommercGesture.domain.objects.Id;

public final class NoSuchEntityException extends RuntimeException {

    public NoSuchEntityException(String message) {
        super(message);
    }

    public static NoSuchEntityException withId(Id id) { 
    	return new NoSuchEntityException(String.format("No entity found with ID %d.", id.getId()));
    }
    
    public static NoSuchEntityException withIdAndElem(Id id, String element) { 
		return new NoSuchEntityException(String.format("No "+ element +" found with ID %d.", id.getId()));
    }
}
