package ecommercGesture.exposition.memberDTO;

import java.time.LocalDate;
import java.util.List;

import ecommercGesture.exposition.userDTO.UserDTO;

@SuppressWarnings("all")
public class MembershipsDTO {

	public final List<MembershipDTO> memberShips;
	
    public static MembershipsDTO of(List<MembershipDTO> memberShips) {
    	return new MembershipsDTO(memberShips);
    }

    private MembershipsDTO(List<MembershipDTO> memberShips) {
        this.memberShips = memberShips;
    }
}
