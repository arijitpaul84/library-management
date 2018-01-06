package com.library.main;

import java.util.ArrayList;
import java.util.List;

import com.library.dto.Author;
import com.library.dto.Book;
import com.library.dto.Genre;
import com.library.dto.User;
import com.library.service.BookManagementService;
import com.library.service.UserManagementService;
import com.library.service.impl.BookManagementServiceImpl;
import com.library.service.impl.UserManagementServiceImpl;

public class LibraryManagement {
	
	private BookManagementService bookManagemenntService = new BookManagementServiceImpl();
	
	private UserManagementService userManagemenntService = new UserManagementServiceImpl();

	public static void main(String[] args) {
		
		LibraryManagement libraryManagement = new LibraryManagement();
		libraryManagement.addBooks();
		libraryManagement.addUsers();
		libraryManagement.lendBook();
		libraryManagement.returnBook();

	}
	
	 void addBooks() {
		List<Book> bookList = getBooks();
		
		bookManagemenntService.addBook(bookList);
	}

	private List<Book> getBooks() {
		Author author = new Author();
		author.setAuthorName("ABC");
		author.setId("Author1");
		Book book1 = new Book()
				.withId("ID1")
				.withAuthor(author)
				.withTitle("Title1")
				.withGenre(Genre.ACTION);
		
		Book book2 = new Book()
				.withId("ID2")
				.withAuthor(author)
				.withTitle("Title2")
				.withGenre(Genre.ACTION);
		
		Book book3 = new Book()
				.withId("ID3")
				.withAuthor(author)
				.withTitle("Title3")
				.withGenre(Genre.ACTION);
		
		List<Book> bookList = new ArrayList<>();
		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book3);
		return bookList;
	}
	
	 void addUsers() {
		User user1 = new User();
		user1.setId("user1");
		user1.setName("User A");
		user1.setAddress("Address");
		
		userManagemenntService.addUser(user1);
		
	}
	
	 void lendBook() {
		 Author author = new Author();
			author.setAuthorName("ABC");
			author.setId("Author1");
		 List<Book> books = bookManagemenntService.searchBookByAuthor(author);
		 bookManagemenntService.lendBook(books, userManagemenntService.getUser("user1"));
	}
	 
	 void returnBook() {
		 Author author = new Author();
			author.setAuthorName("ABC");
			author.setId("Author1");
		 bookManagemenntService.returnBook(getBooks(), userManagemenntService.getUser("user1"));
	}

}
