package myy803.socialbookstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.RecommendationsDto;
import myy803.socialbookstore.formsdata.SearchDto;
import myy803.socialbookstore.services.SearchAndRecommendService;

@Controller
public class SearchAndRecommendController {

    @Autowired
    private SearchAndRecommendService searchAndRecommendService;

    
	@RequestMapping("/user/search")
    public String showSearchForm(Model model) {
    	SearchDto searchDto = new SearchDto(); 
    	model.addAttribute("searchDto", searchDto);
    	
    	return "user/search_form";
    }
    
    @RequestMapping("/user/search_offer")
    public String search(@ModelAttribute("searchDto") SearchDto searchDto, Model model) {
    	    	
		List<BookDto> bookDtos = searchAndRecommendService.search(searchDto);
    	model.addAttribute("books", bookDtos);
    	
		return "user/search_results";
    }
    
    @RequestMapping("/user/recom")
    public String showRecommendationsForm(Model model) {
    	
		RecommendationsDto recomDto = new RecommendationsDto();
    	
    	model.addAttribute("recomDto", recomDto);
    	
    	return "user/recommendation_form";
    }
    
    @RequestMapping("/user/recommend_offers")
    public String recommend(@ModelAttribute("recomDto") RecommendationsDto recomDto, Model model) {
    	
		String username = getUsername();

    	List<BookDto> bookDtos = searchAndRecommendService.recommend(username, recomDto);

    	model.addAttribute("books", bookDtos);
    	
    	return "user/search_results";
    }

    private String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
    	String username = authentication.getName();
		return username;
	}

}
