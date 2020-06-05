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

package com.liferay.todo.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TodoEntryService}.
 *
 * @author Ryan Park
 * @see TodoEntryService
 * @generated
 */
public class TodoEntryServiceWrapper
	implements ServiceWrapper<TodoEntryService>, TodoEntryService {

	public TodoEntryServiceWrapper(TodoEntryService todoEntryService) {
		_todoEntryService = todoEntryService;
	}

	@Override
	public com.liferay.todo.model.TodoEntry addTodoEntry(
			String title, int priority, long assigneeUserId, int dueDateMonth,
			int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean neverDue, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _todoEntryService.addTodoEntry(
			title, priority, assigneeUserId, dueDateMonth, dueDateDay,
			dueDateYear, dueDateHour, dueDateMinute, neverDue, portletId,
			serviceContext);
	}

	@Override
	public com.liferay.todo.model.TodoEntry deleteTodoEntry(long todoEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _todoEntryService.deleteTodoEntry(todoEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _todoEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.todo.model.TodoEntry getTodoEntry(long todoEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _todoEntryService.getTodoEntry(todoEntryId);
	}

	@Override
	public com.liferay.todo.model.TodoEntry updateTodoEntry(
			long todoEntryId, String title, int priority, long assigneeUserId,
			long resolverUserId, int dueDateMonth, int dueDateDay,
			int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean neverDue, int status, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _todoEntryService.updateTodoEntry(
			todoEntryId, title, priority, assigneeUserId, resolverUserId,
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			neverDue, status, portletId, serviceContext);
	}

	@Override
	public com.liferay.todo.model.TodoEntry updateTodoEntryStatus(
			long todoEntryId, long resolverUserId, int status, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _todoEntryService.updateTodoEntryStatus(
			todoEntryId, resolverUserId, status, portletId, serviceContext);
	}

	@Override
	public TodoEntryService getWrappedService() {
		return _todoEntryService;
	}

	@Override
	public void setWrappedService(TodoEntryService todoEntryService) {
		_todoEntryService = todoEntryService;
	}

	private TodoEntryService _todoEntryService;

}