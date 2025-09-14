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
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.services.BookOfferService;

@Controller
public class BookOfferController {

    @Autowired
    private BookOfferService bookOfferService;

    
	@RequestMapping("/user/offers") 
    public String listBookOffers(Model model) {

		String username = getUsername();
    	
		List<BookDto> bookOffersDtos = bookOfferService.listBookOffers(username);
    	model.addAttribute("offers", bookOffersDtos);
    	
    	return "user/offers";
    }
    
    @RequestMapping("/user/show_offer_form") 
    public String showOfferForm(Model model) {
		
    	List<BookCategory> categories = bookOfferService.getAllCategories();
    	model.addAttribute("categories", categories);
    	model.addAttribute("offer", new BookDto());
    	
    	return "user/offer-form";
    }
    
    @RequestMapping("/user/save_offer") 
    public String saveOffer(@ModelAttribute("offer") BookDto bookOfferDto, Model model) {
    	String username = getUsername();
    	bookOfferService.saveOffer(username, bookOfferDto);

		return "redirect:/user/offers";
    }

	@RequestMapping("/user/delete_book_offer")
    public String deleteBookOffer(@RequestParam("selected_offer_id") int bookId, Model model) {
    	
		String username = getUsername();
		bookOfferService.deleteOffer(bookId, username);

    	return "redirect:/user/dashboard";
    }

	private String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
    	String username = authentication.getName();
		return username;
	}

}
