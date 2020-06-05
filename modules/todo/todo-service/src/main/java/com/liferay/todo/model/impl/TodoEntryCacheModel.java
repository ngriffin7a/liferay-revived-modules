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

package com.liferay.todo.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.todo.model.TodoEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TodoEntry in entity cache.
 *
 * @author Ryan Park
 * @generated
 */
public class TodoEntryCacheModel
	implements CacheModel<TodoEntry>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TodoEntryCacheModel)) {
			return false;
		}

		TodoEntryCacheModel todoEntryCacheModel = (TodoEntryCacheModel)obj;

		if (todoEntryId == todoEntryCacheModel.todoEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, todoEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{todoEntryId=");
		sb.append(todoEntryId);
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
		sb.append(", title=");
		sb.append(title);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", assigneeUserId=");
		sb.append(assigneeUserId);
		sb.append(", resolverUserId=");
		sb.append(resolverUserId);
		sb.append(", dueDate=");
		sb.append(dueDate);
		sb.append(", finishDate=");
		sb.append(finishDate);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TodoEntry toEntityModel() {
		TodoEntryImpl todoEntryImpl = new TodoEntryImpl();

		todoEntryImpl.setTodoEntryId(todoEntryId);
		todoEntryImpl.setGroupId(groupId);
		todoEntryImpl.setCompanyId(companyId);
		todoEntryImpl.setUserId(userId);

		if (userName == null) {
			todoEntryImpl.setUserName("");
		}
		else {
			todoEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			todoEntryImpl.setCreateDate(null);
		}
		else {
			todoEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			todoEntryImpl.setModifiedDate(null);
		}
		else {
			todoEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			todoEntryImpl.setTitle("");
		}
		else {
			todoEntryImpl.setTitle(title);
		}

		todoEntryImpl.setPriority(priority);
		todoEntryImpl.setAssigneeUserId(assigneeUserId);
		todoEntryImpl.setResolverUserId(resolverUserId);

		if (dueDate == Long.MIN_VALUE) {
			todoEntryImpl.setDueDate(null);
		}
		else {
			todoEntryImpl.setDueDate(new Date(dueDate));
		}

		if (finishDate == Long.MIN_VALUE) {
			todoEntryImpl.setFinishDate(null);
		}
		else {
			todoEntryImpl.setFinishDate(new Date(finishDate));
		}

		todoEntryImpl.setStatus(status);

		todoEntryImpl.resetOriginalValues();

		return todoEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		todoEntryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		title = objectInput.readUTF();

		priority = objectInput.readInt();

		assigneeUserId = objectInput.readLong();

		resolverUserId = objectInput.readLong();
		dueDate = objectInput.readLong();
		finishDate = objectInput.readLong();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(todoEntryId);

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

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeInt(priority);

		objectOutput.writeLong(assigneeUserId);

		objectOutput.writeLong(resolverUserId);
		objectOutput.writeLong(dueDate);
		objectOutput.writeLong(finishDate);

		objectOutput.writeInt(status);
	}

	public long todoEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public int priority;
	public long assigneeUserId;
	public long resolverUserId;
	public long dueDate;
	public long finishDate;
	public int status;

}