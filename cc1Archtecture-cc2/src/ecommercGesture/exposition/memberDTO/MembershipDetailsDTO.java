package ecommercGesture.exposition.memberDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class MembershipDetailsDTO {
	
    @NotNull
    @PositiveOrZero
	public double applictionPrice;
    
    @NotNull
    @Positive
	public int membershipDuration;

}
