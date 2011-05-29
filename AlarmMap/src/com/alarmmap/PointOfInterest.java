package com.alarmmap;

public class PointOfInterest {

	/*
	 * Read-only properties:
	 */
	private int id;
	private double lat, lon;
	private String name;
	private String categoryName;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the latitude
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * @return the longitude
	 */
	public double getLon() {
		return lon;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the category's name
	 */
	public String getCategoryName() {
		return categoryName;
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
			String categoryName) {
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		this.name = name;
		this.categoryName = categoryName;
	}

}
