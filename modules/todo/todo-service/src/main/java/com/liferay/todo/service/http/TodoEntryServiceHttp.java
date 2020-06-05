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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.todo.service.TodoEntryServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>TodoEntryServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Ryan Park
 * @see TodoEntryServiceSoap
 * @generated
 */
public class TodoEntryServiceHttp {

	public static com.liferay.todo.model.TodoEntry addTodoEntry(
			HttpPrincipal httpPrincipal, String title, int priority,
			long assigneeUserId, int dueDateMonth, int dueDateDay,
			int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean neverDue, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TodoEntryServiceUtil.class, "addTodoEntry",
				_addTodoEntryParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, title, priority, assigneeUserId, dueDateMonth,
				dueDateDay, dueDateYear, dueDateHour, dueDateMinute, neverDue,
				portletId, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.todo.model.TodoEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.todo.model.TodoEntry deleteTodoEntry(
			HttpPrincipal httpPrincipal, long todoEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TodoEntryServiceUtil.class, "deleteTodoEntry",
				_deleteTodoEntryParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, todoEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.todo.model.TodoEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.todo.model.TodoEntry getTodoEntry(
			HttpPrincipal httpPrincipal, long todoEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TodoEntryServiceUtil.class, "getTodoEntry",
				_getTodoEntryParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, todoEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.todo.model.TodoEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.todo.model.TodoEntry updateTodoEntry(
			HttpPrincipal httpPrincipal, long todoEntryId, String title,
			int priority, long assigneeUserId, long resolverUserId,
			int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
			int dueDateMinute, boolean neverDue, int status, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TodoEntryServiceUtil.class, "updateTodoEntry",
				_updateTodoEntryParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, todoEntryId, title, priority, assigneeUserId,
				resolverUserId, dueDateMonth, dueDateDay, dueDateYear,
				dueDateHour, dueDateMinute, neverDue, status, portletId,
				serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.todo.model.TodoEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.todo.model.TodoEntry updateTodoEntryStatus(
			HttpPrincipal httpPrincipal, long todoEntryId, long resolverUserId,
			int status, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TodoEntryServiceUtil.class, "updateTodoEntryStatus",
				_updateTodoEntryStatusParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, todoEntryId, resolverUserId, status, portletId,
				serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.todo.model.TodoEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TodoEntryServiceHttp.class);

	private static final Class<?>[] _addTodoEntryParameterTypes0 = new Class[] {
		String.class, int.class, long.class, int.class, int.class, int.class,
		int.class, int.class, boolean.class, String.class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _deleteTodoEntryParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _getTodoEntryParameterTypes2 = new Class[] {
		long.class
	};
	private static final Class<?>[] _updateTodoEntryParameterTypes3 =
		new Class[] {
			long.class, String.class, int.class, long.class, long.class,
			int.class, int.class, int.class, int.class, int.class,
			boolean.class, int.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateTodoEntryStatusParameterTypes4 =
		new Class[] {
			long.class, long.class, int.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};

}