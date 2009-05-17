package kentaro714.documents.domain;

public class User {
	private String name;
	public User(String name) {
		this.name = name;
	}

	public static User currentUser() {
		// FIXIT just returning a fake object.
		return new User("foo");
	}
}
