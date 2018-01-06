package com.library.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.library.dto.Author;
import com.library.dto.Book;
import com.library.dto.User;
import com.library.exception.BooksNotBorrowedException;
import com.library.exception.BorrowedBookLimitCrossedException;
import com.library.service.BookManagementService;

public class BookManagementServiceImpl implements BookManagementService{
	
	private ConcurrentHashMap<String,List<Book>> titleToBookMap = new ConcurrentHashMap<>();
	
	private ConcurrentHashMap<Author,List<Book>> authorToBookMap = new ConcurrentHashMap<>();
	
	private ConcurrentHashMap<User,List<Book>> userToLendingBook = new ConcurrentHashMap<>();
	
	private static final Integer MAX_BORROWED_BOOK = 5;
	
	public void addBook(List<Book> books) {
		checkTitleAndAdd(books);
		checkAuthorAndAdd(books);
		System.out.println("Books are added successfully");
	}
	
	public List<Book> searchBookByTitle(String title) {
		return titleToBookMap.get(title);
	}
	
	private void checkAuthorAndAdd(List<Book> books) {
		books.forEach(book -> {
		if(authorToBookMap.containsKey(book.getAuthor())) {
			authorToBookMap.get(book.getAuthor()).add(book);
		} else {
			List<Book> bookList = new ArrayList<>();
			bookList.add(book);
			authorToBookMap.put(book.getAuthor(), bookList);
		}});
		
	}

	private void checkTitleAndAdd(List<Book> books) {
		books.forEach(book -> {
		if(titleToBookMap.containsKey(book.getTitle())) {
			titleToBookMap.get(book.getTitle()).add(book);
		} else {
			List<Book> bookList = new ArrayList<>();
			bookList.add(book);
			titleToBookMap.put(book.getTitle(), bookList);
		}
		});
	}

	@Override
	public void lendBook(List<Book> books, User user) {
		List<Book> borrowedBooks = userToLendingBook.get(user);
		if(borrowedBooks == null || borrowedBooks.isEmpty()) {
			borrowedBooks = new ArrayList<>();
			borrowedBooks.addAll(books);
			userToLendingBook.put(user, borrowedBooks);
			borrowedBooks.forEach(borrowedBook -> {titleToBookMap.remove(borrowedBook.getTitle());});
			borrowedBooks.forEach(borrowedBook -> {authorToBookMap.remove(borrowedBook.getAuthor());});
		} else if (borrowedBooks.size() < MAX_BORROWED_BOOK){
			borrowedBooks.addAll(books);
		} else if(borrowedBooks.size() == MAX_BORROWED_BOOK) {
			throw new BorrowedBookLimitCrossedException("Borrowed book limit crossed for this user");
		}
		System.out.println("Lends books to the user "+user.getName() +" successfully");
	}

	@Override
	public void returnBook(List<Book> books, User user) {
		List<Book> borrowedBooks = userToLendingBook.get(user);
		if(borrowedBooks == null || borrowedBooks.isEmpty()) {
			throw new BooksNotBorrowedException("user has not borrowed any books");
		} else if(borrowedBooks.containsAll(books)) {
			borrowedBooks.removeAll(books);
			addBook(books);
		} else {
			throw new BooksNotBorrowedException("Some of the books are not borrowed by the user");
		}
		System.out.println("books are returned from the user "+user.getName() +" successfully and added to the inventory");
	}

	@Override
	public List<Book> searchBookByAuthor(Author author) {
		return authorToBookMap.get(author);
	}


}