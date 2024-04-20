package ru.banturov.crud.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Person {
    private int id;
    
    @NotEmpty(message = "NOT EMPTY") //Аннотация от хибернейт валидатора
    @Size(min = 2, max = 15, message = "WRONG SIZE")
    private String name;
    
    @Min(value = 0, message = "WRONG VALUE")
    private int age;
    
    @NotEmpty(message = "NOT EMPTY")
    @Email(message = "NOT VALID EMAIl")
    private String email;
    
    public Person() {
    	
    }
    
    public Person(int id, String name, int age, String  email) {
        this.id = id;
        this.name = name;
        this.setAge(age);
        this.setEmail(email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}