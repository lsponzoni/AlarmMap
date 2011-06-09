/**
 * 
 */
package com.alarmmap.data;

/**
 *  Exception issued when, by setting a begin/end time,
 *  those would be reversed (i.e. begin is later than end).
 * @author ggazzi
 *
 */
// TODO check warning on line below
public class ReversedTimesException extends IllegalArgumentException {
	private static final long serialVersionUID = 6015327307584713312L;

	public ReversedTimesException() {
	}

	public ReversedTimesException(String detailMessage) {
		super(detailMessage);
	}

	public ReversedTimesException(Throwable cause) {
		super(cause);
	}

	public ReversedTimesException(String message, Throwable cause) {
		super(message, cause);
	}

}
