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
 * This class is a wrapper for {@link WallEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WallEntry
 * @generated
 */
public class WallEntryWrapper
	extends BaseModelWrapper<WallEntry>
	implements ModelWrapper<WallEntry>, WallEntry {

	public WallEntryWrapper(WallEntry wallEntry) {
		super(wallEntry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("wallEntryId", getWallEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("comments", getComments());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long wallEntryId = (Long)attributes.get("wallEntryId");

		if (wallEntryId != null) {
			setWallEntryId(wallEntryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}
	}

	/**
	 * Returns the comments of this wall entry.
	 *
	 * @return the comments of this wall entry
	 */
	@Override
	public String getComments() {
		return model.getComments();
	}

	/**
	 * Returns the company ID of this wall entry.
	 *
	 * @return the company ID of this wall entry
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this wall entry.
	 *
	 * @return the create date of this wall entry
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the group ID of this wall entry.
	 *
	 * @return the group ID of this wall entry
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this wall entry.
	 *
	 * @return the modified date of this wall entry
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this wall entry.
	 *
	 * @return the primary key of this wall entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this wall entry.
	 *
	 * @return the user ID of this wall entry
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this wall entry.
	 *
	 * @return the user name of this wall entry
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this wall entry.
	 *
	 * @return the user uuid of this wall entry
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the wall entry ID of this wall entry.
	 *
	 * @return the wall entry ID of this wall entry
	 */
	@Override
	public long getWallEntryId() {
		return model.getWallEntryId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the comments of this wall entry.
	 *
	 * @param comments the comments of this wall entry
	 */
	@Override
	public void setComments(String comments) {
		model.setComments(comments);
	}

	/**
	 * Sets the company ID of this wall entry.
	 *
	 * @param companyId the company ID of this wall entry
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this wall entry.
	 *
	 * @param createDate the create date of this wall entry
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the group ID of this wall entry.
	 *
	 * @param groupId the group ID of this wall entry
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this wall entry.
	 *
	 * @param modifiedDate the modified date of this wall entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this wall entry.
	 *
	 * @param primaryKey the primary key of this wall entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this wall entry.
	 *
	 * @param userId the user ID of this wall entry
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this wall entry.
	 *
	 * @param userName the user name of this wall entry
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this wall entry.
	 *
	 * @param userUuid the user uuid of this wall entry
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the wall entry ID of this wall entry.
	 *
	 * @param wallEntryId the wall entry ID of this wall entry
	 */
	@Override
	public void setWallEntryId(long wallEntryId) {
		model.setWallEntryId(wallEntryId);
	}

	@Override
	protected WallEntryWrapper wrap(WallEntry wallEntry) {
		return new WallEntryWrapper(wallEntry);
	}

}