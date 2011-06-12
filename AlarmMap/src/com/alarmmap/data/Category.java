package com.alarmmap.data;

import java.util.ArrayList;
import java.util.List;

// TODO: implement and clean up
public class Category implements Configurable {

	/*
	 * Private fields
	 */
	
	GlobalConfig conf;
	
	/* 
	 * Fields for the category's properties
	 */
	private int id;
	private String name;
	private List<Integer> pointIds;
	
	/*
	 * Getters/setters for the category's properties
	 */
	public int getId() {
		return id;
	}
	public List<Integer> getPointIds() {
		return pointIds;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		// TODO Look up on DB, ensure name is not already used
		this.name = name;
	}
	
	/*
	 * Fields for the read-write setting properties
	 */
	/** The distance from which the alarm may be triggered.
	 * If negative, uses the category's. */
	private double range;
	/** If null, uses the category's */
	private Boolean vibrate;
	/** The identifier for the ringtone.
	 * If "", doesn't ring.
	 * If null, uses the category's. */
	private String ringtoneUri;
	/** The message displayed with the alarm.
	 * If "", doesn't display any message.
	 * If null, uses the category's. */
	private String message;
	/** The first hour/minute of a day when the alarm may be triggered.
	 * 0:0 is the first time of the day.
	 * If negative, uses the category's.
	 * Must be either both negative or both positive.
	 * Must be sooner than endH:endM. */
	private int beginH, beginM;
	/** The last hour/minute of a day when the alarm may be triggered. 
	 * 24:00 indicates the end of the day.
	 * If negative, uses the category's.
	 * Must be either both negative or both positive.
	 * Must be later than beginH:beginM. */
	private int endH, endM;
	/** If true, uses our own daily schedule settings.
	 * If false, uses the category's. */
	private boolean useSchedule;
	/** If true, the alarm may be triggered during this day of week. If false, it won't.
	 * Ignored when {@link com.alarmmap.data.PointOfInterest#useSchedule} is false. */
	private boolean sunday, monday, tuesday, wednesday, thursday, friday,
			saturday;

	/*
	 * Default values for the read-write setting properties
	 */
	public static final double defaultRange = -1;
	public static final Boolean defaultVibration = null;
	public static final String defaultRingtoneUri = null;
	public static final String defaultMessage = null;
	public static final int defaultBeginH = -1, defaultBeginM = -1;
	public static final int defaultEndH = -1, defaultEndM = -1;
	public static final boolean defaultUseSchedule = false;
	public static final boolean defaultOnDayOfWeek = false;

	/*
	 * Getters for the read-write setting properties
	 * 
	 * Conventions: - double, int: if negative, looks at the configuration -
	 * Object: if null, looks at the configuration - sunday..saturday: if
	 * useSchedule is false, looks at the configuration
	 * 
	 * @see com.alarmmap.data.Configurable
	 */

	public double getRange() {
		return (range < 0) ? conf.getRange() : range;
	}
	public String getRingtoneUri() {
		return (ringtoneUri == null) ? conf.getRingtoneUri()
				: ringtoneUri;
	}
	public boolean willVibrate() {
		return (vibrate == null) ? conf.willVibrate() : vibrate;
	}
	public String getMessage() {
		return (message == null) ? conf.getMessage() : message;
	}

	public int getBeginHour() {
		return beginH < 0 ? conf.getBeginHour() : beginH;
	}
	public int getBeginMinute() {
		return beginM < 0 ? conf.getBeginMinute() : beginM;
	}
	public int getEndHour() {
		return endH < 0 ? conf.getEndHour() : endH;
	}
	public int getEndMinute() {
		return endM < 0 ? conf.getEndMinute() : endM;
	}

	public boolean useOwnSchedule() {
		return useSchedule;
	}

	public boolean isOnSunday() {
		return useSchedule ? sunday : conf.isOnSunday();
	}
	public boolean isOnMonday() {
		return useSchedule ? monday : conf.isOnMonday();
	}
	public boolean isOnTuesday() {
		return useSchedule ? tuesday : conf.isOnTuesday();
	}
	public boolean isOnWednesday() {
		return useSchedule ? wednesday : conf.isOnWednesday();
	}
	public boolean isOnThursday() {
		return useSchedule ? thursday : conf.isOnThursday();
	}
	public boolean isOnFriday() {
		return useSchedule ? friday : conf.isOnFriday();
	}
	public boolean isOnSaturday() {
		return useSchedule ? saturday : conf.isOnSaturday();
	}

	/*
	 * Setters for the read-write setting properties
	 * 
	 * Argument restrictions are defined per method
	 */

	public void setRange(double range) throws IllegalArgumentException {
		if (range < 0)
			throw new IllegalArgumentException("Cannot use a negative range.");
		
		this.range = range;
	}
	public void setRingtoneUri(String ringtoneUri) throws IllegalArgumentException {
		if (ringtoneUri == null)
			throw new IllegalArgumentException("Cannot use a null ringtone URI.");
		this.ringtoneUri = ringtoneUri;
	}
	public void setVibration(boolean vibrate) {
		this.vibrate = vibrate;
	}
	public void setMessage(String message) throws IllegalArgumentException {
		if (message == null)
			throw new IllegalArgumentException("Cannot use a null message.");
		this.message = message;
	}

	public void setBeginTime(int hour, int min) throws IllegalArgumentException, ReversedTimesException {
		if (hour < 0 || min < 0)
			throw new IllegalArgumentException("Cannot use negative times.");
		if ( hour > getEndHour() || (hour == getEndHour() && min >= getEndMinute()) )
			throw new ReversedTimesException("Cannot begin after it ends.");
		this.beginH = hour;
		this.beginM = min;
	}
	public void setEndTime(int hour, int min) throws IllegalArgumentException {
		
		if (hour == 24 && min > 0)
			throw new IllegalArgumentException(
					"The given time '" + String.valueOf(hour) +":" + String.valueOf(min) + "' is invalid.");
		if (hour > 24 || min > 59)
			throw new IllegalArgumentException(
					"The given time '" + String.valueOf(hour) +":" + String.valueOf(min) + "' is invalid.");

		if (hour < 0 || min < 0)
			throw new IllegalArgumentException("Cannot use negative times.");
		if ( hour < getBeginHour() || (hour == getBeginHour() && min <= getBeginMinute()) )
			throw new ReversedTimesException("Cannot end before it begins.");
		
		this.endH = hour;
		this.endM = min;
		
	}

	public void useOwnSchedule(boolean useSchedule) {
		this.useSchedule = useSchedule;
	}
	
	public void setOnSunday(boolean active) {
		useSchedule = true;
		sunday = active;
	}
	public void setOnMonday(boolean active) {
		useSchedule = true;
		monday = active;
	}
	public void setOnTuesday(boolean active) {
		useSchedule = true;
		tuesday = active;
	}
	public void setOnWednesday(boolean active) {
		useSchedule = true;
		wednesday = active;
	}
	public void setOnThursday(boolean active) {
		useSchedule = true;
		thursday = active;
	}
	public void setOnFriday(boolean active) {
		useSchedule = true;
		friday = active;
	}
	public void setOnSaturday(boolean active) {
		useSchedule = true;
		saturday = active;
	}

	/*
	 * 'Unsetters' for the read-write setting properties
	 * 
	 * Leaves the value for looking at the configuration
	 */

	public void resetRange() {
		range = defaultRange;
	}
	public void resetVibration() {
		this.vibrate = defaultVibration;
	}
	public void resetRingtoneUri() {
		ringtoneUri = defaultRingtoneUri;
	}
	public void resetMessage() {
		this.message = defaultMessage;
	}

	public void resetBeginTime() {
		this.beginH = defaultBeginH;
		this.beginM = defaultBeginM;
	}
	public void resetEndTime() {
		this.endH = defaultEndH;
		this.endM = defaultEndM;
	}

	/** Semantics vary. Here: same as useOwnSchedule(false) */
	public void resetDaysOfWeek() {
		useOwnSchedule(false);
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


	/* 
	 * Constructors/Destructors
	 */
	public Category(int id, String name, List<Integer> pointIds, GlobalConfig conf) {
		this.id = id;
		this.name = name;
		this.pointIds = pointIds;
		this.conf = conf;
		resetFullConfig();
	}
	public Category(int id, String name, List<Integer> pointIds) {
		this(id, name, pointIds, GlobalConfig.Instance());
	}
	public Category(int id, String name) {
		this(id, name, new ArrayList<Integer>(), GlobalConfig.Instance());
	}
	
	/** Saves the Category on the DB
	 * TODO implement, really */
	public void saveOnDB() { }
}
