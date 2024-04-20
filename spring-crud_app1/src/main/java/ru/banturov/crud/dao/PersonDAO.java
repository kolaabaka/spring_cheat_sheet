package ru.banturov.crud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.banturov.crud.models.PersonMapper;

import com.mysql.cj.xdevapi.Statement;

import ru.banturov.crud.models.Person;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT = 4;

	private final JdbcTemplate jdbcTemplate; 
	
	@Autowired
	public PersonDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
    /* JdbcTemplate полностью берёт на себя в классе SpringConfig создавая подключение к БД
     * private static final String URL = "jdbc:mysql://localhost:3306/first_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static java.sql.Connection connection;
    
    
    static {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
         try {
             connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             if (!connection.isClosed()) {
                 System.out.println("DataBASE connected");
             }
         } catch (SQLException e) {
             System.out.println("Problem with SQL db connection");
             e.printStackTrace();
         }
    }*/ 
    
    public List<Person> index() {
    	return jdbcTemplate.query("SELECT * FROM persons;", new PersonMapper()); //Первый аргумент запрос, второй сущность, в которую будет класться результат запроса как раньше в ResultSet
    	/*List<Person> people = new ArrayList<>();
    	String query = "SELECT * FROM persons;"; //Дёргаем все значения из таблицы
    	
    	try {
			java.sql.Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				Person person = new Person();
				person.setId(resultSet.getInt("id"));
				person.setName(resultSet.getString("name"));
				person.setAge(resultSet.getInt("age"));
				person.setEmail(resultSet.getString("email"));
				people.add(person);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return people;*/
    }

    public Person show(int id) {
    	return jdbcTemplate.query("SELECT * FROM persons WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))//поскольку названия столбцов и полей в классе Person совпадает, можно использовать маппер от спринга //new PersonMapper())
    			.stream().findAny().orElse(null);
    	
    	/*Person person = new Person();
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM persons WHERE id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			person.setId(resultSet.getInt("id"));
			person.setName(resultSet.getString("name"));
			person.setEmail(resultSet.getString("email"));
			person.setAge(resultSet.getInt("age"));
			
			return person;
    	} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}*/
    }
    
    public void save(Person person) {
    	//Здесь передаются просто подряд аргументы, без обёртки Object
    	jdbcTemplate.update("INSERT INTO persons VALUES(?, ?, ?, ?)", PEOPLE_COUNT++, person.getName(), person.getAge(), person.getEmail());
    	/*try {
    		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO persons VALUES(1, ?, ?, ?)");
    		preparedStatement.setString(1, person.getName());
    		preparedStatement.setInt(2, person.getAge());
    		preparedStatement.setString(3, person.getEmail());
    		preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error in create new method");
			e.printStackTrace();
		}*/
    }
    
    public void update(int id, Person updatedPerson) {
    	jdbcTemplate.update("UPDATE persons SET name = ?, age = ?, email = ? WHERE id = ?", updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);
    	
    	/*//Person toBeUpdated = show(id); //Не надо делать экзмепляр класса, шок

    	try {
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE persons SET name = ?, age=?, "
					+ "email=? WHERE id=?");
			preparedStatement.setString(1, updatedPerson.getName());
    		preparedStatement.setInt(2, updatedPerson.getAge());
    		preparedStatement.setString(3, updatedPerson.getEmail());
    		preparedStatement.setInt(4, id);
    		preparedStatement.executeUpdate();
    	} catch (SQLException e) {
			e.printStackTrace();
		}*/
    	
    }
    
    public void delete(int id) {
    	jdbcTemplate.update("DELETE FROM persons WHERE id = ?", id);
    	
    	/*//people.removeIf(p -> p.getId() == id);
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM persons WHERE id=?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
    }
}
