package ru.myProject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



//@Component
//@Scope("singleton")
public class RockMusic implements Music{

	
	public void doMyInit() {
		System.out.println( "I initialized" );
	}

	public void doMyDesctruct() {
		System.out.println( "I destroyed" );
	}
	
	@Override
	public String getSong() {
		return "Rammstein";
	}

}
