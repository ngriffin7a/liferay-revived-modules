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

package com.liferay.todo.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.todo.service.http.TodoEntryServiceSoap}.
 *
 * @author Ryan Park
 * @generated
 */
public class TodoEntrySoap implements Serializable {

	public static TodoEntrySoap toSoapModel(TodoEntry model) {
		TodoEntrySoap soapModel = new TodoEntrySoap();

		soapModel.setTodoEntryId(model.getTodoEntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setPriority(model.getPriority());
		soapModel.setAssigneeUserId(model.getAssigneeUserId());
		soapModel.setResolverUserId(model.getResolverUserId());
		soapModel.setDueDate(model.getDueDate());
		soapModel.setFinishDate(model.getFinishDate());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static TodoEntrySoap[] toSoapModels(TodoEntry[] models) {
		TodoEntrySoap[] soapModels = new TodoEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TodoEntrySoap[][] toSoapModels(TodoEntry[][] models) {
		TodoEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TodoEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new TodoEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TodoEntrySoap[] toSoapModels(List<TodoEntry> models) {
		List<TodoEntrySoap> soapModels = new ArrayList<TodoEntrySoap>(
			models.size());

		for (TodoEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TodoEntrySoap[soapModels.size()]);
	}

	public TodoEntrySoap() {
	}

	public long getPrimaryKey() {
		return _todoEntryId;
	}

	public void setPrimaryKey(long pk) {
		setTodoEntryId(pk);
	}

	public long getTodoEntryId() {
		return _todoEntryId;
	}

	public void setTodoEntryId(long todoEntryId) {
		_todoEntryId = todoEntryId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public int getPriority() {
		return _priority;
	}

	public void setPriority(int priority) {
		_priority = priority;
	}

	public long getAssigneeUserId() {
		return _assigneeUserId;
	}

	public void setAssigneeUserId(long assigneeUserId) {
		_assigneeUserId = assigneeUserId;
	}

	public long getResolverUserId() {
		return _resolverUserId;
	}

	public void setResolverUserId(long resolverUserId) {
		_resolverUserId = resolverUserId;
	}

	public Date getDueDate() {
		return _dueDate;
	}

	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;
	}

	public Date getFinishDate() {
		return _finishDate;
	}

	public void setFinishDate(Date finishDate) {
		_finishDate = finishDate;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _todoEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private int _priority;
	private long _assigneeUserId;
	private long _resolverUserId;
	private Date _dueDate;
	private Date _finishDate;
	private int _status;

}