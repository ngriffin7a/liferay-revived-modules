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
import com.liferay.social.networking.model.WallEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WallEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class WallEntryCacheModel
	implements CacheModel<WallEntry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof WallEntryCacheModel)) {
			return false;
		}

		WallEntryCacheModel wallEntryCacheModel = (WallEntryCacheModel)object;

		if (wallEntryId == wallEntryCacheModel.wallEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, wallEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{wallEntryId=");
		sb.append(wallEntryId);
		sb.append(", groupId=");
		sb.append(groupId);
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
		sb.append(", comments=");
		sb.append(comments);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WallEntry toEntityModel() {
		WallEntryImpl wallEntryImpl = new WallEntryImpl();

		wallEntryImpl.setWallEntryId(wallEntryId);
		wallEntryImpl.setGroupId(groupId);
		wallEntryImpl.setCompanyId(companyId);
		wallEntryImpl.setUserId(userId);

		if (userName == null) {
			wallEntryImpl.setUserName("");
		}
		else {
			wallEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			wallEntryImpl.setCreateDate(null);
		}
		else {
			wallEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			wallEntryImpl.setModifiedDate(null);
		}
		else {
			wallEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (comments == null) {
			wallEntryImpl.setComments("");
		}
		else {
			wallEntryImpl.setComments(comments);
		}

		wallEntryImpl.resetOriginalValues();

		return wallEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		wallEntryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		comments = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(wallEntryId);

		objectOutput.writeLong(groupId);

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

		if (comments == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(comments);
		}
	}

	public long wallEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String comments;

}