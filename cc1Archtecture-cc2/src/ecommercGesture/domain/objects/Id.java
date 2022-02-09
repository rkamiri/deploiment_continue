package ecommercGesture.domain.objects;

public class Id {
	
	private final int id;

	private Id(int id) {
		this.id = id;
	}
	
    public static Id of(int id) {
        return new Id(id);
    }

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Id other = (Id) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
