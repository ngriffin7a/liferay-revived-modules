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

package com.liferay.todo.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.todo.model.TodoEntry;
import com.liferay.todo.service.base.TodoEntryServiceBaseImpl;

import com.liferay.todo.service.permission.TodoEntryPermission;
import com.liferay.todo.service.permission.TodoPermission;
import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the todo entry remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.todo.service.TodoEntryService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Ryan Park
 * @see TodoEntryServiceBaseImpl
 */
@Component(
	property = {
		"json.web.service.context.name=tms",
		"json.web.service.context.path=TodoEntry"
	},
	service = AopService.class
)
public class TodoEntryServiceImpl extends TodoEntryServiceBaseImpl {

	@Override
	public TodoEntry addTodoEntry(
		String title, int priority, long assigneeUserId, int dueDateMonth,
		int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
		boolean neverDue, String portletId, ServiceContext serviceContext)
		throws PortalException {

		TodoPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_ENTRY);

		return todoEntryLocalService.addTodoEntry(
			getUserId(), title, priority, assigneeUserId, dueDateMonth,
			dueDateDay, dueDateYear, dueDateHour, dueDateMinute, neverDue,
			portletId, serviceContext);
	}

	@Override
	public TodoEntry deleteTodoEntry(long todoEntryId)
		throws PortalException {

		TodoEntryPermission.check(
			getPermissionChecker(), todoEntryId, ActionKeys.UPDATE);

		return todoEntryLocalService.deleteTodoEntry(todoEntryId);
	}

	@Override
	public TodoEntry getTodoEntry(long todoEntryId) throws PortalException {
		TodoEntryPermission.check(
			getPermissionChecker(), todoEntryId, ActionKeys.VIEW);

		return todoEntryLocalService.getTodoEntry(todoEntryId);
	}

	@Override
	public TodoEntry updateTodoEntry(
		long todoEntryId, String title, int priority, long assigneeUserId,
		long resolverUserId, int dueDateMonth, int dueDateDay,
		int dueDateYear, int dueDateHour, int dueDateMinute,
		boolean neverDue, int status, String portletId, ServiceContext serviceContext)
		throws PortalException {

		TodoEntryPermission.check(
			getPermissionChecker(), todoEntryId, ActionKeys.UPDATE);

		return todoEntryLocalService.updateTodoEntry(
			todoEntryId, title, priority, assigneeUserId, resolverUserId,
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			neverDue, status, portletId, serviceContext);
	}

	@Override
	public TodoEntry updateTodoEntryStatus(
		long todoEntryId, long resolverUserId, int status,
		String portletId, ServiceContext serviceContext)
		throws PortalException {

		TodoEntryPermission.check(
			getPermissionChecker(), todoEntryId, ActionKeys.UPDATE);

		return todoEntryLocalService.updateTodoEntryStatus(
			todoEntryId, resolverUserId, status, portletId, serviceContext);
	}
}