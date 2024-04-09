package ru.myProject;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testSpring {
	public static void main( String[] args ) {
	
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				SpringConfig.class
				);
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext( 
//												     "applicationContext.xml" 
//				 								     );
		
//		RockMusic musicBufRock = context.getBean("rockMusic", RockMusic.class);
//
//		MusicPlayer music1 = new MusicPlayer(musicBufRock);
//		
//		music1.playMusic(); 
//		 
//		MusicPlayer music = context.getBean( "musicPlayer", MusicPlayer.class );
//		
//		music.playMusic();
		
//		Computer pc = context.getBean("computer", Computer.class);
//		System.out.println(pc.toString());
		
		MusicPlayer music = context.getBean("musicPlayer", MusicPlayer.class);
		System.out.println(music.getName());
		System.out.println(music.getVolume());
		System.out.println(music.playMusic());
		
		
		Music rockMusic1 = context.getBean("rockMusic",RockMusic.class);
		Music rockMusic2 = context.getBean("rockMusic",RockMusic.class);
		
		System.out.println(rockMusic1);
		System.out.println(rockMusic2);
		
		context.close();
	}
}
