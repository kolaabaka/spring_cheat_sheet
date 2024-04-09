package ru.alishev.springcourse.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringMvcDispathecherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() { //На данный момент неизвестно зачем нужен этот метод
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() { //Возрващает класс, который создаёт контекст спринга вместо .xml с бинами
		return new Class[] {SpringConfig.class};
	}

	@Override
	protected String[] getServletMappings() { //Говорит, что сервлет обрабатывет все запросы от пользователся
		return new String[] {"/"};
	}

}

