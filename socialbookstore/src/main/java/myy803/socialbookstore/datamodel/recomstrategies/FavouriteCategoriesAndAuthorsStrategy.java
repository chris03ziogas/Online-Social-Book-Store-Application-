package myy803.socialbookstore.datamodel.recomstrategies;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Component;

import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.formsdata.BookDto;


@Component
public class FavouriteCategoriesAndAuthorsStrategy extends BaseRecommendationStrategy {
	
	protected List<BookDto> retrieveRecommendedBooks(UserProfile userProfile) {
		List<BookDto> bookDtos = new ArrayList<BookDto>();
		bookDtos.addAll(userProfile.getBooksFromFavouriteAuthors());
		bookDtos.addAll(userProfile.getBooksOfFavouriteCategories());
		bookDtos = removeDuplicates(bookDtos);
		return bookDtos;
	}

	private List<BookDto> removeDuplicates(List<BookDto> bookDtos)
	{
		List<Integer> bookIds = new ArrayList<>();
		List<BookDto> newBookDtos = new ArrayList<>();
		for(BookDto bookDto : bookDtos)
		{
			if(!(bookIds.contains(bookDto.getId())))
			{
				newBookDtos.add(bookDto);
				bookIds.add(bookDto.getId());
			}
		}

		return newBookDtos;	
	}
}
