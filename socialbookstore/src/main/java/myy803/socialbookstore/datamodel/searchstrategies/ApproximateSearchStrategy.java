package myy803.socialbookstore.datamodel.searchstrategies;


import java.util.List;

import org.springframework.stereotype.Component;


import myy803.socialbookstore.datamodel.Book;

import myy803.socialbookstore.formsdata.SearchDto;


@Component
public class ApproximateSearchStrategy extends BaseSearchStrategy {

	protected List<Book> makeInitialListOfBooks(SearchDto searchDto) {
		List<Book> books = bookMapper.findByTitleStartingWith(searchDto.getTitle());
		return books;
	}

	protected boolean checkIfAuthorsMatch(SearchDto searchDto, Book book) {
		boolean authorsMatch;
		authorsMatch = book.authorsListIncludes(searchDto.getAuthors());
		return authorsMatch;
	}
}
