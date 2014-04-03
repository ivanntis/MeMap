package com.gizmoideas.mobile.memap.model.pojo;

import com.google.android.gms.maps.model.LatLng;

public class Zone {
	private int radio;
	private double latitude;
	private double longitude;
	private String name;
	
	
	public Zone(){
		
	}
	public Zone(int radio, double latitude, double longitude, String name) {
		this.radio = radio;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
	}
	public Zone(int radio, LatLng position, String name) {
		this.radio = radio;
		this.latitude = position.latitude;
		this.longitude = position.longitude;
		this.name = name;
	}
	public int getRadio() {
		return radio;
	}
	public void setRadio(int radio) {
		this.radio = radio;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public LatLng position(){
		return new LatLng(getLatitude(),getLongitude());
	}
	

}
