package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.GroupService;
import domain.Group;

@Controller
@RequestMapping("/login_staff/group")
public class AdminGroupController {
	
	
	@Autowired
	GroupService groupService;


// Constructors ---------------------------------------------------------------

	public AdminGroupController() {
		super();
	}


// ADD GROUP ------------------------------------------------------------------
	
	@RequestMapping(value = "/addGroup", method = RequestMethod.GET)
	public ModelAndView addGroup(HttpSession session, Model model) {
		System.out.println("Controller : /AdminGroupController --- Action : /addGroup GET");
		model.addAttribute("group", new Group());
		model.addAttribute("admin", session.getAttribute("adminSession"));
		return new ModelAndView("/login_staff/group/addGroup");
	}
	
	@RequestMapping(value = "/addGroup", method = RequestMethod.POST)
	public ModelAndView addGroupForm(HttpSession session, Model model, @ModelAttribute Group group){
		System.out.println("Controller : /AdminGroupController --- Action : /addGroup POST");
		groupService.save(group);
		model.addAttribute("admin", session.getAttribute("adminSession"));
		return new ModelAndView("/login_staff/group/addGroup");
	}
	
// LIST GROUP -----------------------------------------------------------------
	
	@RequestMapping(value = "/listGroup")
	public ModelAndView listWaiting(HttpSession session, Model model) {
		System.out.println("Controller : /AdminGroupController --- Action : /listgroup");
		Map<String, List<Group>> mapGroup = new HashMap<String, List<Group>>();
		List<Group> listGroups = (List<Group>) groupService.findAll();
		mapGroup.put("listGroups", listGroups);
		model.addAttribute("admin", session.getAttribute("adminSession"));
		return new ModelAndView("login_staff/group/listGroup", mapGroup);
	}
	
// MANAGEMENT GROUP ---------------------------------------------------------------
	
	@RequestMapping(value = "/managementGroup")
	public ModelAndView managementGroup(@RequestParam(value="name") String name , 
			@RequestParam String action, 
			HttpSession session, 
			Model model) {
		
		Map<String, List<Group>> mapGroup = new HashMap<String, List<Group>>();
		List<Group> listGroup = (List<Group>) groupService.findAll();
		
		Map<String, Group> mapGroupModify = new HashMap<String, Group>();
		
		mapGroup.put("listGroups", listGroup);
		
		model.addAttribute("admin", session.getAttribute("adminSession"));
		
		Group group = groupService.findGroupByName(name);
		
		if (action.equals("Supprimer")) {
			groupService.delete(group);
		} 
		else if (action.equals("Modifier")) {
//			groupService.modify(group, name);
			mapGroupModify.put("group", group);
			System.out.println("coucouuuuuu" + group.getName());
			return new ModelAndView("login_staff/group/modifyGroup", mapGroupModify);
		} 
		else {
			System.out.println("Error action not exist");
		}
		
		return new ModelAndView("login_staff/group/listGroup", mapGroup);
	}
	
	
// MODIFY GROUP ---------------------------------------------------------------
//
//	@RequestMapping(value = "/modifyGroup")
//	public ModelAndView modifyGroup(@RequestParam(value="name") String name , 
//			@ModelAttribute("group") Group group, HttpSession session, Model model) {
		

	@RequestMapping(value = "/modifyGroup")
	public ModelAndView modifyGroup(@RequestParam(value="name") String name , 
			@RequestParam (value = "name") String newName, @RequestParam (value = "description") String newDescription, HttpSession session, Model model) {
		model.addAttribute("admin", session.getAttribute("adminSession"));
		
		Map<String, List<Group>> mapGroup = new HashMap<String, List<Group>>();
		
		
		Map<String, Group> mapGroupModify = new HashMap<String, Group>();
		Group group = groupService.findGroupByName(newName);
		if (group != null) {
			group.setName(name);
			mapGroupModify.put("group", group);
			return new ModelAndView("login_staff/group/modifyGroup", mapGroupModify);
			// FAIRE MESSAGE ERREUR
		}
		System.out.println(name + "  testtttttt");
		newName = newName.replace(name, "");
		group = new Group();
		group.setName(newName);
		group.setDescription(newDescription);
		System.out.println(group.getName());	
		groupService.modify(group, name);
		
		mapGroup.put("listGroups", (List<Group>) groupService.findAll());
		
		return new ModelAndView("login_staff/group/listGroup", mapGroup);
	}
}
