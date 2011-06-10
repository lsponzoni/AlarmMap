package com.alarmmap.data;

/**
 * Singleton class that manages the global configuration for points of interest.
 * 
 * @author ggazzi
 */
public class GlobalConfig implements Configurable {

	/*
	 *  Singleton methods/fields
	 */
	
	private GlobalConfig() {
		load();
	}
	protected void finalize() {
		save();
	}
	
	private static GlobalConfig theInstance;
	public static GlobalConfig Instance() {
		if (theInstance == null)
			theInstance = new GlobalConfig();
		return theInstance;
	}
	
	/*
	 *  Loading and saving the configuration 
	 */
	public void load() {
		// TODO implement loading of configuration
		resetFullConfig();
	}
	public void save() { /*TODO implement saving of configuration*/ }
	
	/*
	 *  Configuration fields
	 */
	
	/** The distance from which the alarm may be triggered. Never negative. */
	private double range;

	private boolean vibrate;

	/** The identifier for the ringtone.
	 * If "", doesn't ring. Never null. */
	private String ringtoneUri;

	/** The message displayed with the alarm.
	 * If "", doesn't display any message. Never null */
	private String message;

	/** The first hour/minute of a day when the alarm may be triggered.
	 * 0:0 is the first time of the day.
	 * Must be sooner than endH:endM.
	 * Neither may be negative. */
	private int beginH, beginM;

	/** The last hour/minute of a day when the alarm may be triggered. 
	 * 23:60 indicates the end of the day.
	 * Must be later than beginH:beginM.
	 * Neither may be negative */
	private int endH, endM;

	/** If true, the alarm may be triggered during this day of week. If false, it won't. */
	private boolean sunday, monday, tuesday, wednesday, thursday, friday,
			saturday;

	/*
	 * Default values for the settings
	 */
	public static final double defaultRange = 50;
	public static final boolean defaultVibration = true;
	public static final String defaultRingtoneUri = "";
	public static final String defaultMessage = "";
	public static final int defaultBeginH = 0, defaultBeginM = 0;
	public static final int defaultEndH = 23, defaultEndM = 60;
	public static final boolean defaultOnDayOfWeek = true;
	
	
	/*
	 * Getters for the configuration.
	 */
	
	public int getBeginHour() {
		return beginH;
	}
	public int getBeginMinute() {
		return beginM;
	}
	public int getEndHour() {
		return endH;
	}
	public int getEndMinute() {
		return endM;
	}

	public String getMessage() {
		return message;
	}
	public double getRange() {
		return range;
	}
	public String getRingtoneUri() {
		return ringtoneUri;
	}
	public boolean willVibrate() {
		return vibrate;
	}
	
	public boolean isOnFriday() {
		return friday;
	}
	public boolean isOnMonday() {
		return monday;
	}
	public boolean isOnSaturday() {
		return saturday;
	}
	public boolean isOnSunday() {
		return sunday;
	}
	public boolean isOnThursday() {
		return thursday;
	}
	public boolean isOnTuesday() {
		return tuesday;
	}

	public boolean isOnWednesday() {
		return wednesday;
	}



	/*
	 * Setters for the configuration.
	 */
	
	public void setBeginTime(int hour, int min)
			throws IllegalArgumentException, ReversedTimesException {
		
		if ( hour < 0 || min < 0 )
			throw new IllegalArgumentException("Can't use negative times.");
		if ( hour > 24 || min > 59 )
			throw new IllegalArgumentException("The given time '" + String.valueOf(hour) +":" + String.valueOf(min) + "' is invalid.");
		
		if ( hour > endH || (hour == endH && min >= endM) )
			throw new ReversedTimesException("Begin time would be set after end time.");
		
		beginH = hour;
		beginM = min;
	}

	public void setEndTime(int hour, int min) throws IllegalArgumentException,
			ReversedTimesException {
		
		if ( hour < 0 || min < 0 )
			throw new IllegalArgumentException("Can't use negative times.");
		if ( hour == 24 && min > 0 )
			throw new IllegalArgumentException("The given time '" + String.valueOf(hour) +":" + String.valueOf(min) + "' is invalid.");
		if ( hour > 24 || min > 59 )
			throw new IllegalArgumentException("The given time '" + String.valueOf(hour) +":" + String.valueOf(min) + "' is invalid.");
		
		if ( hour < beginH || (hour == beginH && min <= beginM) )
			throw new ReversedTimesException("End time would be set before begin time.");
		
		endH = hour;
		endM = min;
	}

	
	public void setMessage(String message) throws IllegalArgumentException {
		if (message == null)
			throw new IllegalArgumentException("Cannot use a null message.");

		this.message = message;
	}

	public void setRange(double range) throws IllegalArgumentException {
		if (range < 0)
			throw new IllegalArgumentException("Cannot use a negative range.");
		
		this.range = range;
	}
	public void setRingtoneUri(String ringtoneUri)
			throws IllegalArgumentException {
		if (ringtoneUri == null)
			throw new IllegalArgumentException("Cannot use a null URI.");

		this.ringtoneUri = ringtoneUri;
	}
	public void setVibration(boolean vibrate) {
		this.vibrate = vibrate;
	}
	
	public void setOnFriday(boolean active) {
		friday = active;
	}

	public void setOnMonday(boolean active) {
		monday = active;
	}

	public void setOnSaturday(boolean active) {
		saturday = active;
	}

	public void setOnSunday(boolean active) {
		sunday = active;
	}

	public void setOnThursday(boolean active) {
		thursday = active;
	}

	public void setOnTuesday(boolean active) {
		tuesday = active;
	}

	public void setOnWednesday(boolean active) {
		wednesday = active;
	}


	
	/*
	 * Configuration resetting
	 */
	
	public void resetBeginTime() {
		beginH = defaultBeginH;
		beginM = defaultBeginM;
	}

	public void resetEndTime() {
		endH = defaultEndH;
		endM = defaultEndM;
	}

	
	public void resetMessage() {
		message = defaultMessage;
	}

	public void resetRange() {
		range = defaultRange;
	}

	public void resetRingtoneUri() {
		ringtoneUri = defaultRingtoneUri;
	}

	public void resetVibration() {
		vibrate = defaultVibration;
	}



	public void resetDaysOfWeek() {
		sunday = defaultOnDayOfWeek;
		monday = defaultOnDayOfWeek;
		tuesday = defaultOnDayOfWeek;
		wednesday = defaultOnDayOfWeek;
		thursday = defaultOnDayOfWeek;
		friday = defaultOnDayOfWeek;
		saturday = defaultOnDayOfWeek;
	}
	
	public void resetFullConfig() {
		resetRange();
		resetVibration();
		resetRingtoneUri();
		resetMessage();
		
		resetBeginTime();
		resetEndTime();
		resetDaysOfWeek();
	}
}
