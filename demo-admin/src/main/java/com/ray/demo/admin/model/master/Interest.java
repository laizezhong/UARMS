package com.ray.demo.admin.model.master;

import java.io.Serializable;
import java.util.Date;

public class Interest implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column interest.id
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column interest.interest_date
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	private Date interestDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column interest.half_year
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	private Double halfYear;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column interest.one_year
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	private Double oneYear;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column interest.three_year
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	private Double threeYear;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column interest.five_year
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	private Double fiveYear;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column interest.five_year_above
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	private Double fiveYearAbove;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column interest.delete_flag
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	private Integer deleteFlag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column interest.notes
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	private String notes;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column interest.creator
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	private Long creator;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column interest.create_time
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column interest.modifier
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	private Long modifier;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column interest.modify_time
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	private Date modifyTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table interest
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column interest.id
	 * @return  the value of interest.id
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column interest.id
	 * @param id  the value for interest.id
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column interest.interest_date
	 * @return  the value of interest.interest_date
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public Date getInterestDate() {
		return interestDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column interest.interest_date
	 * @param interestDate  the value for interest.interest_date
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public void setInterestDate(Date interestDate) {
		this.interestDate = interestDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column interest.half_year
	 * @return  the value of interest.half_year
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public Double getHalfYear() {
		return halfYear;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column interest.half_year
	 * @param halfYear  the value for interest.half_year
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public void setHalfYear(Double halfYear) {
		this.halfYear = halfYear;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column interest.one_year
	 * @return  the value of interest.one_year
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public Double getOneYear() {
		return oneYear;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column interest.one_year
	 * @param oneYear  the value for interest.one_year
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public void setOneYear(Double oneYear) {
		this.oneYear = oneYear;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column interest.three_year
	 * @return  the value of interest.three_year
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public Double getThreeYear() {
		return threeYear;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column interest.three_year
	 * @param threeYear  the value for interest.three_year
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public void setThreeYear(Double threeYear) {
		this.threeYear = threeYear;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column interest.five_year
	 * @return  the value of interest.five_year
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public Double getFiveYear() {
		return fiveYear;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column interest.five_year
	 * @param fiveYear  the value for interest.five_year
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public void setFiveYear(Double fiveYear) {
		this.fiveYear = fiveYear;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column interest.five_year_above
	 * @return  the value of interest.five_year_above
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public Double getFiveYearAbove() {
		return fiveYearAbove;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column interest.five_year_above
	 * @param fiveYearAbove  the value for interest.five_year_above
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public void setFiveYearAbove(Double fiveYearAbove) {
		this.fiveYearAbove = fiveYearAbove;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column interest.delete_flag
	 * @return  the value of interest.delete_flag
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column interest.delete_flag
	 * @param deleteFlag  the value for interest.delete_flag
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column interest.notes
	 * @return  the value of interest.notes
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column interest.notes
	 * @param notes  the value for interest.notes
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public void setNotes(String notes) {
		this.notes = notes == null ? null : notes.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column interest.creator
	 * @return  the value of interest.creator
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public Long getCreator() {
		return creator;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column interest.creator
	 * @param creator  the value for interest.creator
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public void setCreator(Long creator) {
		this.creator = creator;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column interest.create_time
	 * @return  the value of interest.create_time
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column interest.create_time
	 * @param createTime  the value for interest.create_time
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column interest.modifier
	 * @return  the value of interest.modifier
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public Long getModifier() {
		return modifier;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column interest.modifier
	 * @param modifier  the value for interest.modifier
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public void setModifier(Long modifier) {
		this.modifier = modifier;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column interest.modify_time
	 * @return  the value of interest.modify_time
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column interest.modify_time
	 * @param modifyTime  the value for interest.modify_time
	 * @mbggenerated  Mon Aug 29 09:30:58 CST 2016
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}