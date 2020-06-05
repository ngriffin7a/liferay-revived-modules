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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for TodoEntry. This utility wraps
 * <code>com.liferay.todo.service.impl.TodoEntryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Ryan Park
 * @see TodoEntryService
 * @generated
 */
public class TodoEntryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.todo.service.impl.TodoEntryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.todo.model.TodoEntry addTodoEntry(
			String title, int priority, long assigneeUserId, int dueDateMonth,
			int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean neverDue, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addTodoEntry(
			title, priority, assigneeUserId, dueDateMonth, dueDateDay,
			dueDateYear, dueDateHour, dueDateMinute, neverDue, portletId,
			serviceContext);
	}

	public static com.liferay.todo.model.TodoEntry deleteTodoEntry(
			long todoEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTodoEntry(todoEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.todo.model.TodoEntry getTodoEntry(
			long todoEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTodoEntry(todoEntryId);
	}

	public static com.liferay.todo.model.TodoEntry updateTodoEntry(
			long todoEntryId, String title, int priority, long assigneeUserId,
			long resolverUserId, int dueDateMonth, int dueDateDay,
			int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean neverDue, int status, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateTodoEntry(
			todoEntryId, title, priority, assigneeUserId, resolverUserId,
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			neverDue, status, portletId, serviceContext);
	}

	public static com.liferay.todo.model.TodoEntry updateTodoEntryStatus(
			long todoEntryId, long resolverUserId, int status, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateTodoEntryStatus(
			todoEntryId, resolverUserId, status, portletId, serviceContext);
	}

	public static TodoEntryService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TodoEntryService, TodoEntryService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TodoEntryService.class);

		ServiceTracker<TodoEntryService, TodoEntryService> serviceTracker =
			new ServiceTracker<TodoEntryService, TodoEntryService>(
				bundle.getBundleContext(), TodoEntryService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}