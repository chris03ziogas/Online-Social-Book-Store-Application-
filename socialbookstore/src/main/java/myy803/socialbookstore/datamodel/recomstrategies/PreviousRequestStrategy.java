package myy803.socialbookstore.datamodel.recomstrategies;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

import myy803.socialbookstore.datamodel.Book;
import myy803.socialbookstore.datamodel.BookAuthor;
import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.formsdata.BookDto;


@Component
public class PreviousRequestStrategy extends BaseRecommendationStrategy{ 

    protected List<BookDto> retrieveRecommendedBooks(UserProfile userProfile) 
    {
       List<Book> books = userProfile.getRequestedBooks();
       List<BookAuthor> authors = new ArrayList<>();
       List<BookCategory> categories = new ArrayList<>();
       List<BookDto> bookDtos = new ArrayList<>();

       for(Book book : books)
       {
            authors.addAll(book.getBookAuthors());
            categories.add(book.getCategory());     
       }

       bookDtos.addAll(getBooksFromAuthors(authors));
       bookDtos.addAll(getBooksFromCategories(categories));
	   bookDtos = removeDuplicates(bookDtos);

       return bookDtos ;

    }

    private List<BookDto>  getBooksFromAuthors(List<BookAuthor> authors) {
		ArrayList<BookDto> bookDtos = new ArrayList<BookDto>();
		List<BookAuthor> bookAuthors = authors;
		for(BookAuthor favouriteAuthor : bookAuthors) {
			List<Book> books = favouriteAuthor.getBooks();
			for(Book book : books)
				bookDtos.add(book.buildDto());
		}
		return bookDtos;
	}

    private List<BookDto> getBooksFromCategories(List<BookCategory> categories) {
		ArrayList<BookDto> bookDtos = new ArrayList<BookDto>();
		List<BookCategory> bookCategories = categories;
		for(BookCategory category: bookCategories) {
			List<Book> books = category.getBookOffers();
			for(Book book : books)
				bookDtos.add(book.buildDto());
		}
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
