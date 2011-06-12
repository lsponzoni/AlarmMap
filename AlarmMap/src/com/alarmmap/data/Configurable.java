package com.alarmmap.data;


/**
 * Contains configuration for a point of interest. 
 * 
 * The methods defined on the interface are accessors
 * for the required properties.
 * 
 * @author ggazzi
 */
public interface Configurable {

	/*
	 * Getters
	 */
	/** @return The distance from which the alarm may be triggered. */
	public abstract double getRange();
	/** @return The identifier for the ringtone. If "", doesn't ring. */
	public abstract String getRingtoneUri();
	public abstract boolean willVibrate();
	/**
	 * @return The message displayed with the alarm. If "", must not display any
	 *         message.
	 */
	public abstract String getMessage();

	/**
	 * The first hour of a day when the alarm may be triggered. 0 is the first
	 * hour of the day.
	 */
	public abstract int getBeginHour();
	/**
	 * The first minute of a day when the alarm may be triggered. 0 is the first
	 * minute of an hour.
	 */
	public abstract int getBeginMinute();
	/**
	 * The last hour of a day when the alarm may be triggered. 23 is the last
	 * hour of the day.
	 */
	public abstract int getEndHour();
	/**
	 * The last minute of a day when the alarm may be triggered. 59 is the first
	 * minute of an hour, except on 23:60 when it indicates the end of the day.
	 */
	public abstract int getEndMinute();

	public abstract boolean isOnSunday();
	public abstract boolean isOnMonday();
	public abstract boolean isOnTuesday();
	public abstract boolean isOnWednesday();
	public abstract boolean isOnThursday();
	public abstract boolean isOnFriday();
	public abstract boolean isOnSaturday();

	
	/*
	 * Setters
	 */
	/**
	 * @param range
	 *            The distance from which the alarm may be triggered.
	 * @throws IllegalArgumentException
	 * 			Won't handle negative ranges.
	 */
	public abstract void setRange(double range) throws IllegalArgumentException;
	/**
	 * @param ringtoneUri
	 *            The identifier for the ringtone. If "", doesn't ring.
	 * @throws IllegalArgumentException
	 * 			Won't handle null URIs.
	 */
	public abstract void setRingtoneUri(String ringtoneUri) throws IllegalArgumentException;
	public abstract void setVibration(boolean vibrate);
	/**
	 * @param message
	 *            The message displayed with the alarm. If "", will display no
	 *            message.
	 * @throws IllegalArgumentException
	 * 			Won't handle null messages.
	 */
	public abstract void setMessage(String message) throws IllegalArgumentException;

	/**
	 * Sets the first hour/minute of a day when the alarm may be triggered.
	 * 0:0 is the first time of the day.
	 * @throws IllegalArgumentException
	 * 			Won't handle negative times, times later than 24:00 or minutes greater than 59.
	 * @throws ReversedTimesException
	 * 			Won't handle times later than the end time.
	 */
	public abstract void setBeginTime(int hour, int min) throws IllegalArgumentException, ReversedTimesException;
	/**
	 * Sets the last hour/minute of a day when the alarm may be triggered. 
	 * 24:00 is the last time of the day.
	 * @throws IllegalArgumentException
	 * 			Won't handle negative times, times later than 24:00 or minutes greater than 59.
	 * @throws ReversedTimesException
	 * 			Won't handle times earlier than the begin time.
	 */
	public abstract void setEndTime(int hour, int min) throws IllegalArgumentException, ReversedTimesException;
	
	public abstract void setOnSunday(boolean active);
	public abstract void setOnMonday(boolean active);
	public abstract void setOnTuesday(boolean active);
	public abstract void setOnWednesday(boolean active);
	public abstract void setOnThursday(boolean active);
	public abstract void setOnFriday(boolean active);
	public abstract void setOnSaturday(boolean active);

	
	/*
	 * Resetters
	 */
	
	public abstract void resetRange();
	public abstract void resetVibration();
	public abstract void resetRingtoneUri();
	public abstract void resetMessage();
	
	public abstract void resetBeginTime();
	public abstract void resetEndTime();

	/** Semantics vary. */
	public void resetDaysOfWeek();

	/** Resets all config options. Same as calling all individual resetters. */ 
	public void resetFullConfig();

}