/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.social.networking.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MeetupsRegistration}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsRegistration
 * @generated
 */
public class MeetupsRegistrationWrapper
	extends BaseModelWrapper<MeetupsRegistration>
	implements MeetupsRegistration, ModelWrapper<MeetupsRegistration> {

	public MeetupsRegistrationWrapper(MeetupsRegistration meetupsRegistration) {
		super(meetupsRegistration);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("meetupsRegistrationId", getMeetupsRegistrationId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("meetupsEntryId", getMeetupsEntryId());
		attributes.put("comments", getComments());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long meetupsRegistrationId = (Long)attributes.get(
			"meetupsRegistrationId");

		if (meetupsRegistrationId != null) {
			setMeetupsRegistrationId(meetupsRegistrationId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long meetupsEntryId = (Long)attributes.get("meetupsEntryId");

		if (meetupsEntryId != null) {
			setMeetupsEntryId(meetupsEntryId);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	/**
	 * Returns the comments of this meetups registration.
	 *
	 * @return the comments of this meetups registration
	 */
	@Override
	public String getComments() {
		return model.getComments();
	}

	/**
	 * Returns the company ID of this meetups registration.
	 *
	 * @return the company ID of this meetups registration
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this meetups registration.
	 *
	 * @return the create date of this meetups registration
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the meetups entry ID of this meetups registration.
	 *
	 * @return the meetups entry ID of this meetups registration
	 */
	@Override
	public long getMeetupsEntryId() {
		return model.getMeetupsEntryId();
	}

	/**
	 * Returns the meetups registration ID of this meetups registration.
	 *
	 * @return the meetups registration ID of this meetups registration
	 */
	@Override
	public long getMeetupsRegistrationId() {
		return model.getMeetupsRegistrationId();
	}

	/**
	 * Returns the modified date of this meetups registration.
	 *
	 * @return the modified date of this meetups registration
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this meetups registration.
	 *
	 * @return the primary key of this meetups registration
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the status of this meetups registration.
	 *
	 * @return the status of this meetups registration
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the user ID of this meetups registration.
	 *
	 * @return the user ID of this meetups registration
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this meetups registration.
	 *
	 * @return the user name of this meetups registration
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this meetups registration.
	 *
	 * @return the user uuid of this meetups registration
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the comments of this meetups registration.
	 *
	 * @param comments the comments of this meetups registration
	 */
	@Override
	public void setComments(String comments) {
		model.setComments(comments);
	}

	/**
	 * Sets the company ID of this meetups registration.
	 *
	 * @param companyId the company ID of this meetups registration
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this meetups registration.
	 *
	 * @param createDate the create date of this meetups registration
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the meetups entry ID of this meetups registration.
	 *
	 * @param meetupsEntryId the meetups entry ID of this meetups registration
	 */
	@Override
	public void setMeetupsEntryId(long meetupsEntryId) {
		model.setMeetupsEntryId(meetupsEntryId);
	}

	/**
	 * Sets the meetups registration ID of this meetups registration.
	 *
	 * @param meetupsRegistrationId the meetups registration ID of this meetups registration
	 */
	@Override
	public void setMeetupsRegistrationId(long meetupsRegistrationId) {
		model.setMeetupsRegistrationId(meetupsRegistrationId);
	}

	/**
	 * Sets the modified date of this meetups registration.
	 *
	 * @param modifiedDate the modified date of this meetups registration
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this meetups registration.
	 *
	 * @param primaryKey the primary key of this meetups registration
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the status of this meetups registration.
	 *
	 * @param status the status of this meetups registration
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the user ID of this meetups registration.
	 *
	 * @param userId the user ID of this meetups registration
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this meetups registration.
	 *
	 * @param userName the user name of this meetups registration
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this meetups registration.
	 *
	 * @param userUuid the user uuid of this meetups registration
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected MeetupsRegistrationWrapper wrap(
		MeetupsRegistration meetupsRegistration) {

		return new MeetupsRegistrationWrapper(meetupsRegistration);
	}

}