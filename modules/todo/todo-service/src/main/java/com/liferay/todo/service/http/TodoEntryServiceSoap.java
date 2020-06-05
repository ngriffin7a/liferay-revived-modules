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

package com.liferay.todo.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.todo.service.TodoEntryServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>TodoEntryServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.todo.model.TodoEntrySoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.todo.model.TodoEntry</code>, that is translated to a
 * <code>com.liferay.todo.model.TodoEntrySoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Ryan Park
 * @see TodoEntryServiceHttp
 * @generated
 */
public class TodoEntryServiceSoap {

	public static com.liferay.todo.model.TodoEntrySoap addTodoEntry(
			String title, int priority, long assigneeUserId, int dueDateMonth,
			int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean neverDue, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.todo.model.TodoEntry returnValue =
				TodoEntryServiceUtil.addTodoEntry(
					title, priority, assigneeUserId, dueDateMonth, dueDateDay,
					dueDateYear, dueDateHour, dueDateMinute, neverDue,
					portletId, serviceContext);

			return com.liferay.todo.model.TodoEntrySoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.todo.model.TodoEntrySoap deleteTodoEntry(
			long todoEntryId)
		throws RemoteException {

		try {
			com.liferay.todo.model.TodoEntry returnValue =
				TodoEntryServiceUtil.deleteTodoEntry(todoEntryId);

			return com.liferay.todo.model.TodoEntrySoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.todo.model.TodoEntrySoap getTodoEntry(
			long todoEntryId)
		throws RemoteException {

		try {
			com.liferay.todo.model.TodoEntry returnValue =
				TodoEntryServiceUtil.getTodoEntry(todoEntryId);

			return com.liferay.todo.model.TodoEntrySoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.todo.model.TodoEntrySoap updateTodoEntry(
			long todoEntryId, String title, int priority, long assigneeUserId,
			long resolverUserId, int dueDateMonth, int dueDateDay,
			int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean neverDue, int status, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.todo.model.TodoEntry returnValue =
				TodoEntryServiceUtil.updateTodoEntry(
					todoEntryId, title, priority, assigneeUserId,
					resolverUserId, dueDateMonth, dueDateDay, dueDateYear,
					dueDateHour, dueDateMinute, neverDue, status, portletId,
					serviceContext);

			return com.liferay.todo.model.TodoEntrySoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.todo.model.TodoEntrySoap updateTodoEntryStatus(
			long todoEntryId, long resolverUserId, int status, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.todo.model.TodoEntry returnValue =
				TodoEntryServiceUtil.updateTodoEntryStatus(
					todoEntryId, resolverUserId, status, portletId,
					serviceContext);

			return com.liferay.todo.model.TodoEntrySoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TodoEntryServiceSoap.class);

}