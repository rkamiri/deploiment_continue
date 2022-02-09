package ecommercGesture.exposition.memberDTO;

import java.time.LocalDate;

public class MembershipDTO {
	
    public int id;
    public int userId;
    public double price;
    public LocalDate startMembership;
    public LocalDate endMembership;
    public boolean automaticMembershipRenew;
    
    public static MembershipDTO of(int id, int userId, double price,LocalDate startMembership, LocalDate endMembership, boolean automaticMembershipRenew) {
    	return new MembershipDTO(id, userId, price, startMembership, endMembership, automaticMembershipRenew);
    }
   

    private MembershipDTO(int id, int userId, double price, LocalDate startMembership, LocalDate endMembership, boolean automaticMembershipRenew) {
        this.id = id;
        this.userId = userId;
        this.price = price;
        this.startMembership = startMembership;
        this.endMembership = endMembership;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", price='" + price + '\'' +
                ", startMembership='" + startMembership + '\'' +
                ", endMembership=" + endMembership + '\'' +
                ", automaticMembershipRenew='" + automaticMembershipRenew +
                '}';
    }

	

}
