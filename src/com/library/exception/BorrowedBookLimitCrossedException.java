package com.library.exception;

public class BorrowedBookLimitCrossedException extends RuntimeException {
	
	private static final long serialVersionUID = 470712984508655809L;
	
    public BorrowedBookLimitCrossedException(final String message) {
        super(message);
    }

    public BorrowedBookLimitCrossedException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public BorrowedBookLimitCrossedException(final Throwable throwable) {
        super(throwable);
    }
}
