package controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.Status;
import domain.User;

/**
 * Class managing subscription page actions
 * 
 * @author
 */
@Controller
@RequestMapping("/subscription")
public class SubscriptionController {

	@Autowired
	UserService userService;

	// Constructors -----------------------------------------------------------

	public SubscriptionController() {
		super();
	}

	@RequestMapping(value = "/subscribe", method = RequestMethod.GET)
	public ModelAndView subscribe(Model model) {
		System.out
				.println("Controller : /SubscriptionController --- Action : /subscribe");
		User user = new User();
		ModelAndView result;
		result = new ModelAndView("subscription/subscribe");
		model.addAttribute("user", user);
		return result;
	}

	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	public ModelAndView subscribePost(@Valid @ModelAttribute User user,
			BindingResult bindingResult) {
		System.out
				.println("Controller : /SubscriptionController --- Action : /subscribePost");

		ModelAndView result;
		result = new ModelAndView("subscription/subscribe");

		if (bindingResult.hasErrors()) {
			//System.out.println("Erreurs   :" + bindingResult.getAllErrors());
			return result;
		} else {
			user.setInscriptAppDate(new Date());
			user.setStatus(Status.W);
			userService.save(user);
			result = new ModelAndView("welcome/index");
			return result;
		}
		
	}

	@ModelAttribute("months")
	public int[] getMonths() {
		int[] days = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		return days;
	}

	@ModelAttribute("days")
	public int[] getDays() {
		int[] days = new int[31];
		for (int i = 0; i < days.length; i++) {
			days[i] = i + 1;
		}
		return days;
	}

	@ModelAttribute("years")
	public int[] getYears() {
		// Calendar cal = Calendar.getInstance();
		// cal.setTime(new Date());
		int currentYear = Calendar.getInstance().get(Calendar.YEAR) + 1;
		int[] years = new int[currentYear - 1900];

		int i = 1900;
		for (int j = 0; j < years.length; j++) {
			years[j] = i;
			i++;
		}
		return years;
	}

}
