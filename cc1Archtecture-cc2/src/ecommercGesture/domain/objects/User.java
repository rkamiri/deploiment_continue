package ecommercGesture.domain.objects;

import java.util.Objects;

public class User {
	
	private final Id id;
	private final String name;
	private final String lastName;
	private String userName;
	private String password;

	private User(Id id, String name, String lastName, String userName, String password) {
		this.id = id;
		this.name = Objects.requireNonNull(name,"User name must be not null");
		this.lastName = Objects.requireNonNull(lastName,"User lastname must be not null");
		this.userName = Objects.requireNonNull(userName,"UserName must be not null");
		this.password = Objects.requireNonNull(password,"User password must be not null");
	}

    public static User of(Id id, String name, String lastname, String userName, String password) {
        return new User(id, name, lastname, userName, password);
    }

	public Id getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void changeUserName(String userName) {
		this.userName = Objects.requireNonNull(userName,"UserName must be not null");
	}

	public String getPassword() {
		return password;
	}

	public void changePassword(String password) {
		this.password = Objects.requireNonNull(password,"User password must be not null");
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
}
