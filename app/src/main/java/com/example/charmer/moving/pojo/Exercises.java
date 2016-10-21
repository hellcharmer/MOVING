package com.example.charmer.moving.pojo;

import java.util.Date;

public class Exercises {

    @Override
	public String toString() {
		return "Exercises [activeState=" + activeState + ", activityTime="
				+ activityTime + ", cost=" + cost + ", currentNumber="
				+ currentNumber + ", exerciseCode=" + exerciseCode
				+ ", exerciseId=" + exerciseId + ", exerciseIntroduce="
				+ exerciseIntroduce + ", exerciseTheme=" + exerciseTheme
				+ ", exerciseTitle=" + exerciseTitle + ", exerciseType="
				+ exerciseType + ", groupMembers=" + groupMembers
				+ ", participator=" + participator + ", paymentMethod="
				+ paymentMethod + ", place=" + place + ", publisherId="
				+ publisherId + ", releaseTime=" + releaseTime
				+ ", totalNumber=" + totalNumber + "]";
	}
	private Integer exerciseId;
    private Integer publisherId;
    private String exerciseTitle;
    private String exerciseType;
    private String exerciseTheme;
    private String exerciseIntroduce;
    private String place;
    private Date activityTime;
    private Double cost;
    private String paymentMethod;
    private Integer currentNumber;
    private Integer totalNumber;
    private Date releaseTime;
    private String participator;
    private String groupMembers;
    private String exerciseCode;
    private String activeState;
    
    
    

	public Integer getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(Integer exerciseId) {
		this.exerciseId = exerciseId;
	}

	public Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public String getExerciseTitle() {
		return exerciseTitle;
	}

	public void setExerciseTitle(String exerciseTitle) {
		this.exerciseTitle = exerciseTitle;
	}

	public String getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}

	public String getExerciseTheme() {
		return exerciseTheme;
	}

	public void setExerciseTheme(String exerciseTheme) {
		this.exerciseTheme = exerciseTheme;
	}

	public String getExerciseIntroduce() {
		return exerciseIntroduce;
	}

	public void setExerciseIntroduce(String exerciseIntroduce) {
		this.exerciseIntroduce = exerciseIntroduce;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	

	public Date getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Integer getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(Integer currentNumber) {
		this.currentNumber = currentNumber;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getParticipator() {
		return participator;
	}

	public void setParticipator(String participator) {
		this.participator = participator;
	}

	public String getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(String groupMembers) {
		this.groupMembers = groupMembers;
	}

	public String getExerciseCode() {
		return exerciseCode;
	}

	public void setExerciseCode(String exerciseCode) {
		this.exerciseCode = exerciseCode;
	}

	public String getActiveState() {
		return activeState;
	}

	public void setActiveState(String activeState) {
		this.activeState = activeState;
	}

	public Exercises() {
    }

	public Exercises( Integer publisherId,String exerciseTitle, String exerciseType,
			String exerciseTheme, String place, Double cost, String paymentMethod,
			Integer currentNumber, Integer totalNumber, Date activityTime) {
		super();
		this.publisherId = publisherId;
		this.exerciseTitle = exerciseTitle;
		this.exerciseType = exerciseType;
		this.exerciseTheme = exerciseTheme;
		this.place = place;
		this.cost = cost;
		this.paymentMethod = paymentMethod;
		this.currentNumber = currentNumber;
		this.totalNumber = totalNumber;
		this.activityTime = activityTime;
	}
	public Exercises(Integer exerciseId, Integer publisherId,String exerciseTitle, String exerciseType,
			String exerciseTheme, String place, Double cost, String paymentMethod,
			Integer currentNumber, Integer totalNumber, Date activityTime) {
		super();
		
		this.exerciseId = exerciseId;
		this.publisherId = publisherId;
		this.exerciseTitle = exerciseTitle;
		this.exerciseType = exerciseType;
		this.exerciseTheme = exerciseTheme;
		this.place = place;
		this.cost = cost;
		this.paymentMethod = paymentMethod;
		this.currentNumber = currentNumber;
		this.totalNumber = totalNumber;
		this.activityTime = activityTime;
	}

	public Exercises(Integer publisherId, String exerciseTitle, String exerciseType, String exerciseTheme, String exerciseIntroduce, String place, Date activityTime, Double cost, String paymentMethod, Integer totalNumber, Date releaseTime, String exerciseCode) {
		this.publisherId = publisherId;
		this.exerciseTitle = exerciseTitle;
		this.exerciseType = exerciseType;
		this.exerciseTheme = exerciseTheme;
		this.exerciseIntroduce = exerciseIntroduce;
		this.place = place;
		this.activityTime = activityTime;
		this.cost = cost;
		this.paymentMethod = paymentMethod;
		this.totalNumber = totalNumber;
		this.releaseTime = releaseTime;
		this.exerciseCode = exerciseCode;
	}
}