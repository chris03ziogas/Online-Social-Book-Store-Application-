package myy803.socialbookstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.formsdata.UserProfileDto;
import myy803.socialbookstore.services.UserProfileService;


@Controller
public class UserProfileController {

	@Autowired
	private UserProfileService userProfileService;

    @RequestMapping("/user/dashboard")
    public String getUserMainMenu(){
       
    	return "user/dashboard";
    }
    
    @RequestMapping("/user/profile")
    public String retrieveProfile(Model model){

		String username = getUsername();

    	List <BookCategory> categories = userProfileService.getAllCategories();
    	model.addAttribute("categories", categories);
		
		UserProfileDto userProfileDto = userProfileService.retrieveProfile(username);
    	model.addAttribute("profile", userProfileDto);
    	
    	return "user/profile";
    }
    
    @RequestMapping("/user/save_profile")
    public String saveProfile(@ModelAttribute("profile") UserProfileDto userProfileDto, Model theModel) {
    	System.err.println(userProfileDto.toString());
    	    	
		userProfileService.saveProfile(userProfileDto);
    	
    	return "user/dashboard";
    }

	private String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
    	String username = authentication.getName();
		return username;
	}
    

}
