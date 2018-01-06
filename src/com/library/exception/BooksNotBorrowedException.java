package com.library.exception;

public class BooksNotBorrowedException extends RuntimeException {
	
	private static final long serialVersionUID = 470712984508655809L;
	
    public BooksNotBorrowedException(final String message) {
        super(message);
    }

    public BooksNotBorrowedException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public BooksNotBorrowedException(final Throwable throwable) {
        super(throwable);
    }
}
