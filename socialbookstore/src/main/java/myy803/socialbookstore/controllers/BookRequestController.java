package myy803.socialbookstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.UserProfileDto;
import myy803.socialbookstore.services.BookRequestService;

@Controller
public class BookRequestController {

    @Autowired
    private BookRequestService bookRequestService;

    @RequestMapping("/user/request_book")
    public String request(@RequestParam("selected_book_id") int bookId, Model model) {
    	String username = getUsername();
        
    	bookRequestService.request(username, bookId);

    	return "redirect:/user/dashboard";
    }
    
    @RequestMapping("/user/requests")
    public String showUserBookRequests(Model model) {
    	String username = getUsername();

		List <BookDto> requests = bookRequestService.getRequests(username);
    	model.addAttribute("requests", requests);
    	
    	return "/user/book_requests";
    }

    @RequestMapping("/user/book_requesting_users")
    public String showRequestingUsersForBookOffer(@RequestParam("selected_offer_id") int bookId, Model model) {
    	    	
    	List<UserProfileDto> requestingUsers = bookRequestService.getRequestingUsersForBookOffer(bookId);
    	
    	model.addAttribute("requesting_users", requestingUsers);
    	model.addAttribute("book_id", bookId);
   
    	return "/user/requesting_users";
    }

	@RequestMapping("/user/accept_request")
    public String acceptRequest(@RequestParam("selected_user") String username, @RequestParam("book_id") int bookId, Model model) {
    	/*
    	 * TODO - have to implement this user story
    	 */
    	System.err.println("Selected user: " + username + " for book id: " + bookId);
    	
    	return "redirect:/user/dashboard";
    }
    
    @RequestMapping("/user/delete_book_request")
    public String deleteBookRequest(@RequestParam("selected_request_id") int bookId, Model model) {
    	
		String username = getUsername();
		bookRequestService.deleteRequest(bookId, username);
    	System.err.println("Delete Book Request for book id: " + bookId);

    	return "redirect:/user/dashboard";
    }

    private String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
    	String username = authentication.getName();
		return username;
	}

}
