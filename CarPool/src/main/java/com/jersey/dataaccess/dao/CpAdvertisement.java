package main.java.com.jersey.dataaccess.dao;

import java.util.Date;

public class CpAdvertisement {
 
	private Integer addNo;
	private String addType;
	private String city;
	private String fromCity;
	private String toCity;
	private String fromLocal;
	private String toLocal;
	private Date dateTime;
	private String viaRoute1;
	private String viaRoute2;
	private String viaRoute3;
	private String vehicleNo;
	private Integer seatsAvailable;
	private Integer userId;
	private Integer femaleOnly;
	private String vehicleType;
	private String flag;
	private String status; // active, cancelled, upcoming
	private Integer deTourTime;
	private String tripType; // round/one way
	private String comment;
	private String timeScheduleFlexi;

	/**
	 * @return the addNo
	 */
	public Integer getAddNo() {
		return addNo;
	}
	/**
	 * @param addNo the addNo to set
	 */
	public void setAddNo(Integer addNo) {
		this.addNo = addNo;
	}
	/**
	 * @return the addType
	 */
	public String getAddType() {
		return addType;
	}
	/**
	 * @param addType the addType to set
	 */
	public void setAddType(String addType) {
		this.addType = addType;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the fromCity
	 */
	public String getFromCity() {
		return fromCity;
	}
	/**
	 * @param fromCity the fromCity to set
	 */
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	/**
	 * @return the toCity
	 */
	public String getToCity() {
		return toCity;
	}
	/**
	 * @param toCity the toCity to set
	 */
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	/**
	 * @return the fromLocal
	 */
	public String getFromLocal() {
		return fromLocal;
	}
	/**
	 * @param fromLocal the fromLocal to set
	 */
	public void setFromLocal(String fromLocal) {
		this.fromLocal = fromLocal;
	}
	/**
	 * @return the toLocal
	 */
	public String getToLocal() {
		return toLocal;
	}
	/**
	 * @param toLocal the toLocal to set
	 */
	public void setToLocal(String toLocal) {
		this.toLocal = toLocal;
	}
	/**
	 * @return the dateTime
	 */
	public Date getDateTime() {
		return dateTime;
	}
	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	/**
	 * @return the viaRoute1
	 */
	public String getViaRoute1() {
		return viaRoute1;
	}
	/**
	 * @param viaRoute1 the viaRoute1 to set
	 */
	public void setViaRoute1(String viaRoute1) {
		this.viaRoute1 = viaRoute1;
	}
	/**
	 * @return the viaRoute2
	 */
	public String getViaRoute2() {
		return viaRoute2;
	}
	/**
	 * @param viaRoute2 the viaRoute2 to set
	 */
	public void setViaRoute2(String viaRoute2) {
		this.viaRoute2 = viaRoute2;
	}
	/**
	 * @return the viaRoute3
	 */
	public String getViaRoute3() {
		return viaRoute3;
	}
	/**
	 * @param viaRoute3 the viaRoute3 to set
	 */
	public void setViaRoute3(String viaRoute3) {
		this.viaRoute3 = viaRoute3;
	}
	/**
	 * @return the vehicleNo
	 */
	public String getVehicleNo() {
		return vehicleNo;
	}
	/**
	 * @param vehicleNo the vehicleNo to set
	 */
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public CpAdvertisement() {
		super();
	}
	/**
	 * @return the seatsAvailable
	 */
	public Integer getSeatsAvailable() {
		return seatsAvailable;
	}
	/**
	 * @param seatsAvailable the seatsAvailable to set
	 */
	public void setSeatsAvailable(Integer seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	
	/**
	 * @return the femaleOnly
	 */
	public Integer getFemaleOnly() {
		return femaleOnly;
	}
	/**
	 * @param femaleOnly the femaleOnly to set
	 */
	public void setFemaleOnly(Integer femaleOnly) {
		this.femaleOnly = femaleOnly;
	}
	/**
	 * @return the vehicleType
	 */
	public String getVehicleType() {
		return vehicleType;
	}
	/**
	 * @param vehicleType the vehicleType to set
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the deTourTime
	 */
	public Integer getDeTourTime() {
		return deTourTime;
	}
	/**
	 * @param deTourTime the deTourTime to set
	 */
	public void setDeTourTime(Integer deTourTime) {
		this.deTourTime = deTourTime;
	}
	/**
	 * @return the tripType
	 */
	public String getTripType() {
		return tripType;
	}
	/**
	 * @param tripType the tripType to set
	 */
	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
	/**
	 * @return the timeScheduleFlexi
	 */
	public String getTimeScheduleFlexi() {
		return timeScheduleFlexi;
	}
	/**
	 * @param timeScheduleFlexi the timeScheduleFlexi to set
	 */
	public void setTimeScheduleFlexi(String timeScheduleFlexi) {
		this.timeScheduleFlexi = timeScheduleFlexi;
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
