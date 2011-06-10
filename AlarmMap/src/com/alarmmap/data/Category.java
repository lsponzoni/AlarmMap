package com.alarmmap.data;

// TODO: implement and clean up
public class Category implements Configurable {

	/*
	 * Read-write settings:
	 */
	
	/** The distance from which the alarm may be triggered. If negative, uses the category's. */
	private double range;
	/** @return The distance from which the alarm may be triggered. */
	public double getRange() 
	{ return range; }
	/** @param range The distance from which the alarm may be triggered. */
	public void setRange(double range)
	{ this.range = range; }
	/** Makes the point 'inherit' the range from its category. */
	public void resetRange()
	{ range = -1; }
	
	/** The identifier for the ringtone. If "", doesn't ring. If null, uses the category's. */
	private String ringtoneUri;
	/** @return The identifier for the ringtone. If "", doesn't ring. */
	public String getRingtoneUri() 
	{ return ringtoneUri; }
	/** @param ringtoneUri The identifier for the ringtone. If "", doesn't ring. */
	public void setRingtoneUri(String ringtoneUri)
	{ this.ringtoneUri = ringtoneUri; }
	/** Makes the point 'inherit' the ringtone from its category. */
	public void resetRingtoneUri()
	{ ringtoneUri = null; }
	
	
	/** If null, uses the category's. */
	private Boolean vibrate;
	public boolean willVibrate() 
	{ return vibrate; }
	public void setVibration(boolean vibrate)
	{ this.vibrate = vibrate; }
	/** Makes the point 'inherit' the vibration from its category. */
	public void resetVibration()
	{ this.vibrate = null; }
	
	/** The message displayed with the alarm. If "", doesn't display any message. If null, uses the global. */
	private String message;
	/** @return The message displayed with the alarm. If "", must not display any message.*/
	public String getMessage() 
	{ return message; }
	/** @param message The message displayed with the alarm. If "", will display no message. */
	public void setMessage(String message)
	{ this.message = message; }
	/** Makes the point 'inherit' the message from its category. */
	public void resetMessage()
	{ this.message = null; }
	
	/**  The first hour/minute of a day when the alarm may be triggered.
	 * 0:0 is the first time of the day. If negative, uses the global.*/
	private int beginH, beginM;
	/**  The first hour of a day when the alarm may be triggered. 0 is the first hour of the day.*/
	public int getBeginHour()
	{ return beginH; }
	/**  The first minute of a day when the alarm may be triggered. 0 is the first minute of an hour.*/
	public int getBeginMinute()
	{ return beginM; }
	/** Sets the first hour/minute of a day when the alarm may be triggered.
	 * 0:0 is the first time of the day.*/
	public void setBeginTime(int hour, int min)
	{ this.beginH = hour; this.beginM = min; }
	/** Makes the point 'inherit' the vibration from its category. */
	public void resetBeginTime()
	{ this.beginH = -1; this.beginM = -1; }
	
	/** The last hour/minute of a day when the alarm may be triggered.
	 * 23:60 indicates the end of the day. If negative, uses the global. */
	private int endH, endM;
	/**  The last hour of a day when the alarm may be triggered. 23 is the last hour of the day.*/
	public int getEndHour()
	{ return endH; }
	/**  The last minute of a day when the alarm may be triggered. 
	 * 59 is the first minute of an hour, except on 23:60 when it indicates 
	 * the end of the day.*/
	public int getEndMinute()
	{ return endM; }
	/** Sets the last hour/minute of a day when the alarm may be triggered.
	 * 23:60 is the last time of the day.*/
	public void setEndTime(int hour, int min)
	{ this.endH = hour; this.endM = min; }
	/** Makes the point 'inherit' the vibration from its category. */
	public void resetEndTime()
	{ this.endH = -1; this.endM = -1; }
	
	/** If true, uses the schedule settings. If false, uses the global. */
	private boolean useSchedule;
	public boolean useOwnSchedule()
	{ return useSchedule; }
	/** @param useSchedule If true, will use the schedule settings. If false, uses the global. */
	public void useOwnSchedule(boolean useSchedule)
	{ this.useSchedule = useSchedule; }
	
	/** If true, the alarm may be triggered during this day of week. If false, it won't. */
	private boolean sunday, monday, tuesday, wednesday, thursday, friday, saturday;
	
	public boolean isOnSunday() 
	{ return sunday; }
	public void setOnSunday(boolean sunday) 
	{ this.sunday = sunday; }

	public boolean isOnMonday() 
	{ return monday; }
	public void setOnMonday(boolean monday) 
	{ this.monday = monday; }

	public boolean isOnTuesday() 
	{ return tuesday; }
	public void setOnTuesday(boolean tuestay) 
	{ this.tuesday = tuestay; }

	public boolean isOnWednesday() 
	{ return wednesday; }
	public void setOnWednesday(boolean wednesday) 
	{ this.wednesday = wednesday; }

	public boolean isOnThursday() 
	{ return thursday; }
	public void setOnThursday(boolean thursday) 
	{ this.thursday = thursday; }

	public boolean isOnFriday() 
	{ return friday; }
	public void setOnFriday(boolean friday) 
	{ this.friday = friday; }

	public boolean isOnSaturday() 
	{ return saturday; }
	public void setOnSaturday(boolean saturday) 
	{ this.saturday = saturday; }
	public void resetFullConfig() {
	}
	public void resetDaysOfWeek() {
	}
}
