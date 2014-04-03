package com.gizmoideas.mobile.memap.model.pojo;

import com.gizmoideas.mobile.memap.util.DataNotable;

public class Note implements Comparable<Note> {
	
	private int id;
	private String name;
	private String message;
	private long createDate;
	private Zone zone;
	private int status;
	private String  audioMessage;
	public static int lastId=0;
	
	
	
	public void newNote() {
		lastId++;
		this.id=lastId;
		this.status=DataNotable.NEW_NOTIFICATION;
	}



	public Note(String name, String message, long createDate, Zone zone) {		
		this.name = name;
		this.message = message;
		this.createDate = createDate;
		this.zone = zone;
		newNote();
	}
	
	
	public Note(long createDate, Zone zone, String audioMessage) {
		this.createDate = createDate;
		this.zone = zone;
		this.audioMessage = createDate+"_"+id+".mp3";
		newNote();
	}


	
	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public String getAudioMessage() {
		return audioMessage;
	}


	public void setAudioMessage(String audioMessage) {
		this.audioMessage = audioMessage;
	}


	@Override
	public String toString() {
		return getId()+"";
	}


	@Override
	public int compareTo(Note idNote) {
		if(idNote.getId()==this.id){
			return 0;
		}else if(idNote.getId()<this.id){
			return -1;
		}else{
			return 1;
		}
		
	}



	
	
	

}
