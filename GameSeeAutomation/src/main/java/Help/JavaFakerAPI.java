package Help;

import com.github.javafaker.Faker;

public class JavaFakerAPI {

	Faker faker = new Faker();

	public String getFirstName() {
		//Generating the first name
		String firstName = faker.name().firstName();
		return firstName;
	}

	public String getLastName() {
		//Generating last name
		String lastName = faker.name().lastName();
		return lastName;
	}

	public String getEmailID() {
		//Generating email Id
		String emailId = faker.internet().emailAddress();
		return emailId;
	}

	public String getPassword() {
		//Generating password
		String pwd = faker.internet().password();		
		return pwd;
	}
}