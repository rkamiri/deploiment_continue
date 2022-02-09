package ecommercGesture.exposition.memberDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BillingInformationsDTO {
	
    @NotNull
    @NotBlank
	public String cardNumber;
    
    @NotNull
    @NotBlank
	public String expirationDate;
    
    @NotNull
    @NotBlank
	public String secretPictogram;
    
    @NotNull
    @NotBlank
	public String ownerName;

}
