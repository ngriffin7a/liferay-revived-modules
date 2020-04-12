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

package com.liferay.tasks.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for TasksEntry. This utility wraps
 * <code>com.liferay.tasks.service.impl.TasksEntryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Ryan Park
 * @see TasksEntryService
 * @generated
 */
public class TasksEntryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.tasks.service.impl.TasksEntryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.tasks.model.TasksEntry addTasksEntry(
			String title, int priority, long assigneeUserId, int dueDateMonth,
			int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean neverDue, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addTasksEntry(
			title, priority, assigneeUserId, dueDateMonth, dueDateDay,
			dueDateYear, dueDateHour, dueDateMinute, neverDue, portletId,
			serviceContext);
	}

	public static com.liferay.tasks.model.TasksEntry deleteTasksEntry(
			long tasksEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTasksEntry(tasksEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.tasks.model.TasksEntry getTasksEntry(
			long tasksEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTasksEntry(tasksEntryId);
	}

	public static com.liferay.tasks.model.TasksEntry updateTasksEntry(
			long tasksEntryId, String title, int priority, long assigneeUserId,
			long resolverUserId, int dueDateMonth, int dueDateDay,
			int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean neverDue, int status, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateTasksEntry(
			tasksEntryId, title, priority, assigneeUserId, resolverUserId,
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			neverDue, status, portletId, serviceContext);
	}

	public static com.liferay.tasks.model.TasksEntry updateTasksEntryStatus(
			long tasksEntryId, long resolverUserId, int status,
			String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateTasksEntryStatus(
			tasksEntryId, resolverUserId, status, portletId, serviceContext);
	}

	public static TasksEntryService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TasksEntryService, TasksEntryService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TasksEntryService.class);

		ServiceTracker<TasksEntryService, TasksEntryService> serviceTracker =
			new ServiceTracker<TasksEntryService, TasksEntryService>(
				bundle.getBundleContext(), TasksEntryService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}