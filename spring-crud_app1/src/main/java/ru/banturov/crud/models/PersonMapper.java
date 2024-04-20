package ru.banturov.crud.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import ru.banturov.crud.models.Person;

public class PersonMapper implements RowMapper<Person>{

	@Override
	public Person mapRow(ResultSet resultSet, int id) throws SQLException {
		Person person = new Person();
		person.setId(resultSet.getInt("id"));
		person.setName(resultSet.getString("name"));
		person.setEmail(resultSet.getString("email"));
		person.setAge(resultSet.getInt("age"));
		
		return person;
	}

}
