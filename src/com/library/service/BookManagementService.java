package com.library.service;

import java.util.List;

import com.library.dto.Author;
import com.library.dto.Book;
import com.library.dto.User;

public interface BookManagementService {
	
	void addBook(List<Book> books);
	
	void lendBook(List<Book> books, User user);
	
	void returnBook(List<Book> books, User user);
	
	List<Book> searchBookByTitle(String title);
	
	List<Book> searchBookByAuthor(Author author);
	
	
}
