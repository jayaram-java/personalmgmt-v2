package com.company.Personalmgmt.dto;

public class KeyNotesDto implements Comparable<KeyNotesDto> {

	private Long id;

	private String name;

	private String link;

	private String parent;

	private int serialId;

	private String createdDate;

	private int sequenceOrder;

	@Override
	public String toString() {
		return "KeyNotesDto [id=" + id + ", name=" + name + ", link=" + link + ", parent=" + parent + ", serialId="
				+ serialId + ", createdDate=" + createdDate + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public int getSerialId() {
		return serialId;
	}

	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getSequenceOrder() {
		return sequenceOrder;
	}

	public void setSequenceOrder(int sequenceOrder) {
		this.sequenceOrder = sequenceOrder;
	}

	@Override
	public int compareTo(KeyNotesDto order) {
		
		// this > order = +
		// this < order = -
		//this == order = 0
		
		if(this.getSequenceOrder() > order.getSequenceOrder()) {
			return 1;
		}else {
			return -1;
		}

	}

}
