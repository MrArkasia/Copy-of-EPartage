package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdminService;
import utilities.CryptPassword;
import domain.Admin;
import domain.Status;
import domain.Student;

@Controller
@RequestMapping("/login_staff")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	// Constructors -----------------------------------------------------------

	public AdminController() {
		super();
	}

	// Index 
	
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		System.out.println("Controller : /login_staff --- Action : /index");
		ModelAndView result;
		result = new ModelAndView("login_staff/index");
		return result;
	}

	// Connection ------------------------------------------------------------------

		
		
		// List waiting ------------------------------------------------------------------
		
		@RequestMapping(value = "/listWaiting")
		public ModelAndView login (
				HttpSession session,
				Model model) {
		
			System.out.println("Controller : /AdminController --- Action : /listWaiting");
			
			Map<String, List<Student>> mapStudents = new HashMap<String, List<Student>>();
			
			List <Student> listStudents = adminService.findByStatus(Status.W);
			
			mapStudents.put("listStudents", listStudents);
			
			model.addAttribute("admin", session.getAttribute("adminSession"));
			return new ModelAndView("login_staff/listWaiting", mapStudents);
		}
			
}
