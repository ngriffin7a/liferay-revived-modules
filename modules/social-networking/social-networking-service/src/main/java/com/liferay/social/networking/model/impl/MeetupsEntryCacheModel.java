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

package com.liferay.social.networking.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.social.networking.model.MeetupsEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MeetupsEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MeetupsEntryCacheModel
	implements CacheModel<MeetupsEntry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MeetupsEntryCacheModel)) {
			return false;
		}

		MeetupsEntryCacheModel meetupsEntryCacheModel =
			(MeetupsEntryCacheModel)object;

		if (meetupsEntryId == meetupsEntryCacheModel.meetupsEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, meetupsEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{meetupsEntryId=");
		sb.append(meetupsEntryId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", totalAttendees=");
		sb.append(totalAttendees);
		sb.append(", maxAttendees=");
		sb.append(maxAttendees);
		sb.append(", price=");
		sb.append(price);
		sb.append(", thumbnailId=");
		sb.append(thumbnailId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MeetupsEntry toEntityModel() {
		MeetupsEntryImpl meetupsEntryImpl = new MeetupsEntryImpl();

		meetupsEntryImpl.setMeetupsEntryId(meetupsEntryId);
		meetupsEntryImpl.setCompanyId(companyId);
		meetupsEntryImpl.setUserId(userId);

		if (userName == null) {
			meetupsEntryImpl.setUserName("");
		}
		else {
			meetupsEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			meetupsEntryImpl.setCreateDate(null);
		}
		else {
			meetupsEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			meetupsEntryImpl.setModifiedDate(null);
		}
		else {
			meetupsEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			meetupsEntryImpl.setTitle("");
		}
		else {
			meetupsEntryImpl.setTitle(title);
		}

		if (description == null) {
			meetupsEntryImpl.setDescription("");
		}
		else {
			meetupsEntryImpl.setDescription(description);
		}

		if (startDate == Long.MIN_VALUE) {
			meetupsEntryImpl.setStartDate(null);
		}
		else {
			meetupsEntryImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			meetupsEntryImpl.setEndDate(null);
		}
		else {
			meetupsEntryImpl.setEndDate(new Date(endDate));
		}

		meetupsEntryImpl.setTotalAttendees(totalAttendees);
		meetupsEntryImpl.setMaxAttendees(maxAttendees);
		meetupsEntryImpl.setPrice(price);
		meetupsEntryImpl.setThumbnailId(thumbnailId);

		meetupsEntryImpl.resetOriginalValues();

		return meetupsEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		meetupsEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();

		totalAttendees = objectInput.readInt();

		maxAttendees = objectInput.readInt();

		price = objectInput.readDouble();

		thumbnailId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(meetupsEntryId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		objectOutput.writeInt(totalAttendees);

		objectOutput.writeInt(maxAttendees);

		objectOutput.writeDouble(price);

		objectOutput.writeLong(thumbnailId);
	}

	public long meetupsEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public String description;
	public long startDate;
	public long endDate;
	public int totalAttendees;
	public int maxAttendees;
	public double price;
	public long thumbnailId;

}