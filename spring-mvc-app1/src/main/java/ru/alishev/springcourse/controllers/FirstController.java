package ru.alishev.springcourse.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

	
	@GetMapping("/hello")
	public String helloPage(@RequestParam(value = "name", required = false) String name, //required делает так, что страница не падает, если нет параметров
							@RequestParam(value = "surname", required = false) String surname, 
							Model model) { //HttpServletRequest можно через экземляр этого класса
				
		//System.out.println("Hello " + name + " " + surname);
		
		model.addAttribute("message", "Hello " + name + " " + surname);
		
		return "first/hello";
	}
	
	@GetMapping("/goodbye")
	public String goodbyePage() {
		return "first/goodbye";
	}
	

	@GetMapping("/calculator")
	public String calcPage(HttpServletRequest request, Model model) {
		String a = request.getParameter("a");
		String b = request.getParameter("b");
		String action = request.getParameter("action");
		if(a == null || b == null || action == null) { 
			model.addAttribute("message", "Something go WRONG");
		}else
		{
			int result = 0;
			switch(action) {
				case "plus":
					result = Integer.valueOf(a) + Integer.valueOf(b);
					break;
				case "minus":
					result = Integer.valueOf(a) - Integer.valueOf(b);
					break;
				case "mult":
					result = Integer.valueOf(a) * Integer.valueOf(b);
					break;
				case "div":
					result = Integer.valueOf(a) / Integer.valueOf(b);
					break;
			}
		
			model.addAttribute("message", a + " " + " " + action + " " + b + " = " + result);
		}	
		
		return "first/calculator";
	}
}
