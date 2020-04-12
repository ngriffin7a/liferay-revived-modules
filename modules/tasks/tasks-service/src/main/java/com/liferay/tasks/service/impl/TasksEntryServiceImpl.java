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

package com.liferay.tasks.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.base.TasksEntryServiceBaseImpl;

import com.liferay.tasks.service.permission.TasksEntryPermission;
import com.liferay.tasks.service.permission.TasksPermission;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the tasks entry remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.tasks.service.TasksEntryService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Ryan Park
 * @see TasksEntryServiceBaseImpl
 */
@Component(
	property = {
		"json.web.service.context.name=tms",
		"json.web.service.context.path=TasksEntry"
	},
	service = AopService.class
)
public class TasksEntryServiceImpl extends TasksEntryServiceBaseImpl {

	@Override
	public TasksEntry addTasksEntry(
		String title, int priority, long assigneeUserId, int dueDateMonth,
		int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
		boolean neverDue, String portletId, ServiceContext serviceContext)
		throws PortalException {

		TasksPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_ENTRY);

		return tasksEntryLocalService.addTasksEntry(
			getUserId(), title, priority, assigneeUserId, dueDateMonth,
			dueDateDay, dueDateYear, dueDateHour, dueDateMinute, neverDue,
			portletId, serviceContext);
	}

	@Override
	public TasksEntry deleteTasksEntry(long tasksEntryId)
		throws PortalException {

		TasksEntryPermission.check(
			getPermissionChecker(), tasksEntryId, ActionKeys.UPDATE);

		return tasksEntryLocalService.deleteTasksEntry(tasksEntryId);
	}

	@Override
	public TasksEntry getTasksEntry(long tasksEntryId) throws PortalException {
		TasksEntryPermission.check(
			getPermissionChecker(), tasksEntryId, ActionKeys.VIEW);

		return tasksEntryLocalService.getTasksEntry(tasksEntryId);
	}

	@Override
	public TasksEntry updateTasksEntry(
		long tasksEntryId, String title, int priority, long assigneeUserId,
		long resolverUserId, int dueDateMonth, int dueDateDay,
		int dueDateYear, int dueDateHour, int dueDateMinute,
		boolean neverDue, int status, String portletId, ServiceContext serviceContext)
		throws PortalException {

		TasksEntryPermission.check(
			getPermissionChecker(), tasksEntryId, ActionKeys.UPDATE);

		return tasksEntryLocalService.updateTasksEntry(
			tasksEntryId, title, priority, assigneeUserId, resolverUserId,
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			neverDue, status, portletId, serviceContext);
	}

	@Override
	public TasksEntry updateTasksEntryStatus(
		long tasksEntryId, long resolverUserId, int status,
		String portletId, ServiceContext serviceContext)
		throws PortalException {

		TasksEntryPermission.check(
			getPermissionChecker(), tasksEntryId, ActionKeys.UPDATE);

		return tasksEntryLocalService.updateTasksEntryStatus(
			tasksEntryId, resolverUserId, status, portletId, serviceContext);
	}

}