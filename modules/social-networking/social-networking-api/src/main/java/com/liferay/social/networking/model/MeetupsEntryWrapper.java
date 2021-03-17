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
 * This class is a wrapper for {@link MeetupsEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsEntry
 * @generated
 */
public class MeetupsEntryWrapper
	extends BaseModelWrapper<MeetupsEntry>
	implements MeetupsEntry, ModelWrapper<MeetupsEntry> {

	public MeetupsEntryWrapper(MeetupsEntry meetupsEntry) {
		super(meetupsEntry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("meetupsEntryId", getMeetupsEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("totalAttendees", getTotalAttendees());
		attributes.put("maxAttendees", getMaxAttendees());
		attributes.put("price", getPrice());
		attributes.put("thumbnailId", getThumbnailId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long meetupsEntryId = (Long)attributes.get("meetupsEntryId");

		if (meetupsEntryId != null) {
			setMeetupsEntryId(meetupsEntryId);
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

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Integer totalAttendees = (Integer)attributes.get("totalAttendees");

		if (totalAttendees != null) {
			setTotalAttendees(totalAttendees);
		}

		Integer maxAttendees = (Integer)attributes.get("maxAttendees");

		if (maxAttendees != null) {
			setMaxAttendees(maxAttendees);
		}

		Double price = (Double)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}

		Long thumbnailId = (Long)attributes.get("thumbnailId");

		if (thumbnailId != null) {
			setThumbnailId(thumbnailId);
		}
	}

	/**
	 * Returns the company ID of this meetups entry.
	 *
	 * @return the company ID of this meetups entry
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this meetups entry.
	 *
	 * @return the create date of this meetups entry
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the description of this meetups entry.
	 *
	 * @return the description of this meetups entry
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the end date of this meetups entry.
	 *
	 * @return the end date of this meetups entry
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the max attendees of this meetups entry.
	 *
	 * @return the max attendees of this meetups entry
	 */
	@Override
	public int getMaxAttendees() {
		return model.getMaxAttendees();
	}

	/**
	 * Returns the meetups entry ID of this meetups entry.
	 *
	 * @return the meetups entry ID of this meetups entry
	 */
	@Override
	public long getMeetupsEntryId() {
		return model.getMeetupsEntryId();
	}

	/**
	 * Returns the modified date of this meetups entry.
	 *
	 * @return the modified date of this meetups entry
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the price of this meetups entry.
	 *
	 * @return the price of this meetups entry
	 */
	@Override
	public double getPrice() {
		return model.getPrice();
	}

	/**
	 * Returns the primary key of this meetups entry.
	 *
	 * @return the primary key of this meetups entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the start date of this meetups entry.
	 *
	 * @return the start date of this meetups entry
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the thumbnail ID of this meetups entry.
	 *
	 * @return the thumbnail ID of this meetups entry
	 */
	@Override
	public long getThumbnailId() {
		return model.getThumbnailId();
	}

	/**
	 * Returns the title of this meetups entry.
	 *
	 * @return the title of this meetups entry
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns the total attendees of this meetups entry.
	 *
	 * @return the total attendees of this meetups entry
	 */
	@Override
	public int getTotalAttendees() {
		return model.getTotalAttendees();
	}

	/**
	 * Returns the user ID of this meetups entry.
	 *
	 * @return the user ID of this meetups entry
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this meetups entry.
	 *
	 * @return the user name of this meetups entry
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this meetups entry.
	 *
	 * @return the user uuid of this meetups entry
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
	 * Sets the company ID of this meetups entry.
	 *
	 * @param companyId the company ID of this meetups entry
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this meetups entry.
	 *
	 * @param createDate the create date of this meetups entry
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this meetups entry.
	 *
	 * @param description the description of this meetups entry
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the end date of this meetups entry.
	 *
	 * @param endDate the end date of this meetups entry
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the max attendees of this meetups entry.
	 *
	 * @param maxAttendees the max attendees of this meetups entry
	 */
	@Override
	public void setMaxAttendees(int maxAttendees) {
		model.setMaxAttendees(maxAttendees);
	}

	/**
	 * Sets the meetups entry ID of this meetups entry.
	 *
	 * @param meetupsEntryId the meetups entry ID of this meetups entry
	 */
	@Override
	public void setMeetupsEntryId(long meetupsEntryId) {
		model.setMeetupsEntryId(meetupsEntryId);
	}

	/**
	 * Sets the modified date of this meetups entry.
	 *
	 * @param modifiedDate the modified date of this meetups entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the price of this meetups entry.
	 *
	 * @param price the price of this meetups entry
	 */
	@Override
	public void setPrice(double price) {
		model.setPrice(price);
	}

	/**
	 * Sets the primary key of this meetups entry.
	 *
	 * @param primaryKey the primary key of this meetups entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the start date of this meetups entry.
	 *
	 * @param startDate the start date of this meetups entry
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the thumbnail ID of this meetups entry.
	 *
	 * @param thumbnailId the thumbnail ID of this meetups entry
	 */
	@Override
	public void setThumbnailId(long thumbnailId) {
		model.setThumbnailId(thumbnailId);
	}

	/**
	 * Sets the title of this meetups entry.
	 *
	 * @param title the title of this meetups entry
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	 * Sets the total attendees of this meetups entry.
	 *
	 * @param totalAttendees the total attendees of this meetups entry
	 */
	@Override
	public void setTotalAttendees(int totalAttendees) {
		model.setTotalAttendees(totalAttendees);
	}

	/**
	 * Sets the user ID of this meetups entry.
	 *
	 * @param userId the user ID of this meetups entry
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this meetups entry.
	 *
	 * @param userName the user name of this meetups entry
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this meetups entry.
	 *
	 * @param userUuid the user uuid of this meetups entry
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected MeetupsEntryWrapper wrap(MeetupsEntry meetupsEntry) {
		return new MeetupsEntryWrapper(meetupsEntry);
	}

}