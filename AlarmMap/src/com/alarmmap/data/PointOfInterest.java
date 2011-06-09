package com.alarmmap.data;


// TODO: document this class further
public class PointOfInterest implements Configurable {

	/*
	 * Private fields
	 */
	private CategoryDBManager categoryDB;

	/*
	 * Fields for read-only properties
	 */
	private int id;
	private double lat, lon;
	private String name;
	private String categoryName;

	/*
	 * Getters for read-only properties
	 */
	public int getId() {
		return id;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public String getName() {
		return name;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public Category getCategory() {
		return categoryDB.findByName(categoryName);
	}

	/*
	 * Fields for the read-write setting properties
	 */

	/**
	 * The distance from which the alarm may be triggered. If negative, uses the
	 * category's.
	 */
	private double range;

	/** If null, uses the category's */
	private Boolean vibrate;

	/**
	 * The identifier for the ringtone. If "", doesn't ring. If null, uses the
	 * category's.
	 */
	private String ringtoneUri;

	/**
	 * The message displayed with the alarm. If "", doesn't display any message.
	 * If null, uses the category's.
	 */
	private String message;

	/**
	 * The first hour/minute of a day when the alarm may be triggered.
	 *  0:0 is the first time of the day.
	 *  If negative, uses the category's.
	 *  Must be either both negative or both positive.
	 *  Must be sooner than endH:endM.
	 */
	private int beginH, beginM;

	/**
	 * The last hour/minute of a day when the alarm may be triggered. 
	 *  23:60 indicates the end of the day.
	 *  If negative, uses the category's.
	 *  Must be either both negative or both positive.
	 *  Must be later than beginH:beginM.
	 */
	private int endH, endM;

	/**
	 * If true, uses our own daily schedule settings. If false, uses the
	 * category's.
	 */
	private boolean useSchedule;

	/**
	 * If true, the alarm may be triggered during this day of week. If false, it
	 * won't. Ignored when {@link com.alarmmap.data.PointOfInterest#useSchedule} is
	 * false.
	 */
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
		return (range < 0) ? getCategory().getRange() : range;
	}

	public String getRingtoneUri() {
		return (ringtoneUri == null) ? getCategory().getRingtoneUri()
				: ringtoneUri;
	}

	public boolean willVibrate() {
		return (vibrate == null) ? getCategory().willVibrate() : vibrate;
	}

	public String getMessage() {
		return (message == null) ? getCategory().getMessage() : message;
	}

	public int getBeginHour() {
		return beginH < 0 ? getCategory().getBeginHour() : beginH;
	}

	public int getBeginMinute() {
		return beginM < 0 ? getCategory().getBeginMinute() : beginM;
	}

	public int getEndHour() {
		return endH < 0 ? getCategory().getEndHour() : endH;
	}

	public int getEndMinute() {
		return endM < 0 ? getCategory().getEndMinute() : endM;
	}

	public boolean useOwnSchedule() {
		return useSchedule;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#isOnSunday()
	 */
	public boolean isOnSunday() {
		return useSchedule ? sunday : getCategory().isOnSunday();
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#isOnMonday()
	 */
	public boolean isOnMonday() {
		return useSchedule ? monday : getCategory().isOnMonday();
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#isOnTuesday()
	 */
	public boolean isOnTuesday() {
		return useSchedule ? tuesday : getCategory().isOnTuesday();
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#isOnWednesday()
	 */
	public boolean isOnWednesday() {
		return useSchedule ? wednesday : getCategory().isOnWednesday();
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#isOnThursday()
	 */
	public boolean isOnThursday() {
		return useSchedule ? thursday : getCategory().isOnThursday();
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#isOnFriday()
	 */
	public boolean isOnFriday() {
		return useSchedule ? friday : getCategory().isOnFriday();
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#isOnSaturday()
	 */
	public boolean isOnSaturday() {
		return useSchedule ? saturday : getCategory().isOnSaturday();
	}

	/*
	 * Setters for the read-write setting properties
	 * 
	 * Argument restrictions are defined per method
	 */

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#setRange(double)
	 */
	public void setRange(double range) throws IllegalArgumentException {
		if (range < 0)
			throw new IllegalArgumentException("Cannot use a negative range.");
		
		this.range = range;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#setRingtoneUri(java.lang.String)
	 */
	public void setRingtoneUri(String ringtoneUri) throws IllegalArgumentException {
		if (ringtoneUri == null)
			throw new IllegalArgumentException("Cannot use a null ringtone URI.");
		this.ringtoneUri = ringtoneUri;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#setVibration(boolean)
	 */
	public void setVibration(boolean vibrate) {
		this.vibrate = vibrate;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#setMessage(java.lang.String)
	 */
	public void setMessage(String message) throws IllegalArgumentException {
		if (message == null)
			throw new IllegalArgumentException("Cannot use a null message.");
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#setBeginTime(int, int)
	 */
	public void setBeginTime(int hour, int min) throws IllegalArgumentException, ReversedTimesException {
		if (hour < 0 || min < 0)
			throw new IllegalArgumentException("Cannot use negative times.");
		if ( hour > getEndHour() || (hour == getEndHour() && min >= getEndMinute()) )
			throw new ReversedTimesException("Cannot begin after it ends.");
		this.beginH = hour;
		this.beginM = min;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#setEndTime(int, int)
	 */
	public void setEndTime(int hour, int min) throws IllegalArgumentException {
		if (hour < 0 || min < 0)
			throw new IllegalArgumentException("Cannot use negative times.");
		if ( hour < getBeginHour() || (hour == getBeginHour() && min <= getBeginMinute()) )
			throw new ReversedTimesException("Cannot end before it begins.");
		this.endH = hour;
		this.endM = min;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#useOwnSchedule(boolean)
	 */
	public void useOwnSchedule(boolean useSchedule) {
		this.useSchedule = useSchedule;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#setOnSunday(boolean)
	 */
	public void setOnSunday(boolean active) {
		useSchedule = true;
		sunday = active;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#setOnMonday(boolean)
	 */
	public void setOnMonday(boolean active) {
		useSchedule = true;
		monday = active;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#setOnTuesday(boolean)
	 */
	public void setOnTuesday(boolean active) {
		useSchedule = true;
		tuesday = active;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#setOnWednesday(boolean)
	 */
	public void setOnWednesday(boolean active) {
		useSchedule = true;
		wednesday = active;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#setOnThursday(boolean)
	 */
	public void setOnThursday(boolean active) {
		useSchedule = true;
		thursday = active;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#setOnFriday(boolean)
	 */
	public void setOnFriday(boolean active) {
		useSchedule = true;
		friday = active;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#setOnSaturday(boolean)
	 */
	public void setOnSaturday(boolean active) {
		useSchedule = true;
		saturday = active;
	}

	/*
	 * 'Unsetters' for the read-write setting properties
	 * 
	 * Leaves the value for looking at the configuration
	 */

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#unsetRange()
	 */
	public void unsetRange() {
		range = -1;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#unsetVibration()
	 */
	public void unsetVibration() {
		this.vibrate = null;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#unsetRingtoneUri()
	 */
	public void unsetRingtoneUri() {
		ringtoneUri = null;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#unsetMessage()
	 */
	public void unsetMessage() {
		this.message = null;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#unsetBeginTime()
	 */
	public void unsetBeginTime() {
		this.beginH = -1;
		this.beginM = -1;
	}

	/* (non-Javadoc)
	 * @see com.alarmmap.data.Configurable#unsetEndTime()
	 */
	public void unsetEndTime() {
		this.endH = -1;
		this.endM = -1;
	}

	/**
	 * @param id
	 *            a numeric ID for/from the database
	 * @param lat
	 *            the latitude
	 * @param lon
	 *            the longitude
	 * @param name
	 *            a textual name for the user
	 * @param categoryName
	 *            the string identifier for the POI's category
	 */
	public PointOfInterest(int id, double lat, double lon, String name,
			String categoryName, CategoryDBManager categoryDB) {

		// Initializes the given parameters
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		this.name = name;
		this.categoryName = categoryName;
		this.categoryDB = categoryDB;

		// Initializes the configuration properties
		range = defaultRange;
		ringtoneUri = defaultRingtoneUri;
		message = defaultMessage;
		vibrate = defaultVibration;

		beginH = defaultBeginH;
		beginM = defaultBeginM;
		endH   = defaultEndH;
		endM   = defaultEndM;

		useSchedule = defaultUseSchedule;
		sunday    = defaultOnDayOfWeek;
		monday    = defaultOnDayOfWeek;
		tuesday   = defaultOnDayOfWeek;
		wednesday = defaultOnDayOfWeek;
		thursday  = defaultOnDayOfWeek;
		friday    = defaultOnDayOfWeek;
		saturday  = defaultOnDayOfWeek;
	}

}
