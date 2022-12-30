import java.util.Objects;

public class Person implements Comparable<Person>{
	String id;
	String firstName;
	String lastName;

	public Person(String id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return "Person [" + id + " " + firstName + " " + lastName + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(Person o) {
		for (int i = 0 ; i < id.length() ; i++) {
			if ((int) id.charAt(i) > (int) o.id.charAt(i))
				return 1;
			else if ((int) id.charAt(i) < (int) o.id.charAt(i))
				return -1;
		}
		return 0;
	}
	
}
