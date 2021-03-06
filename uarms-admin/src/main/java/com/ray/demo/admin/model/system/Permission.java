package com.ray.demo.admin.model.system;

import java.io.Serializable;

public class Permission implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column permission.id
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column permission.name
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column permission.url
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	private String url;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column permission.group_name
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	private String groupName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column permission.permission
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	private String permission;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table permission
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column permission.id
	 * @return  the value of permission.id
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column permission.id
	 * @param id  the value for permission.id
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column permission.name
	 * @return  the value of permission.name
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column permission.name
	 * @param name  the value for permission.name
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column permission.url
	 * @return  the value of permission.url
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column permission.url
	 * @param url  the value for permission.url
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column permission.group_name
	 * @return  the value of permission.group_name
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column permission.group_name
	 * @param groupName  the value for permission.group_name
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName == null ? null : groupName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column permission.permission
	 * @return  the value of permission.permission
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column permission.permission
	 * @param permission  the value for permission.permission
	 * @mbggenerated  Mon Aug 08 17:00:34 CST 2016
	 */
	public void setPermission(String permission) {
		this.permission = permission == null ? null : permission.trim();
	}
}