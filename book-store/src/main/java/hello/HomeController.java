package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomeController{

	
	@RequestMapping("/ui-elements")
	public String uiElements(){
		return "ui-elements";
	}


	@RequestMapping("/chart")
	public String charts(){
		return "chart";
	}


	@RequestMapping("/tab-panel")
	public String tabPanel(){
		return "tab-panel";
	}


	@RequestMapping("/table")
	public String table(){
		return "tabel";
	}


	@RequestMapping("/form")
	public String form(){
		return "form";
	}

	
	
	@RequestMapping("/book/new")
	public String bookNew(){
		return "new-book";
	}
	
}