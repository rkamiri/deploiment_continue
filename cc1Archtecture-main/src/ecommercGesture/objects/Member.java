package ecommercGesture.objects;

import java.time.LocalDate;

public class Member {
	
	private final Id id;
	private final User user;
	private LocalDate startMembership;
	private LocalDate endMembership;
	
	private Member(Id id, User user, LocalDate startMembership, LocalDate endMembership) {
		this.id = id;
		this.user = user;
		this.startMembership = startMembership;
		this.endMembership = endMembership;
	}
	
    public static Member of(Id id, User user, LocalDate startMembership, LocalDate endMembership) {
        return new Member(id, user, startMembership, endMembership);
    }
	
	public Id getId() {
		return id;
	}
	
	public User getUser() {
		return user;
	}

	public LocalDate getStartMembership() {
		return startMembership;
	}

	public LocalDate getEndMembership() {
		return endMembership;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", user=" + user + ", startMembership=" + startMembership + ", endMembership="
				+ endMembership + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endMembership == null) ? 0 : endMembership.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((startMembership == null) ? 0 : startMembership.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (endMembership == null) {
			if (other.endMembership != null)
				return false;
		} else if (!endMembership.equals(other.endMembership))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (startMembership == null) {
			if (other.startMembership != null)
				return false;
		} else if (!startMembership.equals(other.startMembership))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
