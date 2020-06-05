/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.todo.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.todo.model.TodoEntry;
import com.liferay.todo.service.TodoEntryLocalServiceUtil;

/**
 * @author Ryan Park
 */
public class TodoEntryPermission {

	public static void check(
			PermissionChecker permissionChecker, long todoEntryId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, todoEntryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, TodoEntry todoEntry,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, todoEntry, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long todoEntryId,
			String actionId)
		throws PortalException {

		return contains(
			permissionChecker,
			TodoEntryLocalServiceUtil.getTodoEntry(todoEntryId), actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, TodoEntry todoEntry,
		String actionId) {

		if (permissionChecker.getUserId() == todoEntry.getAssigneeUserId()) {
			return true;
		}

		if (permissionChecker.hasOwnerPermission(
				todoEntry.getCompanyId(), TodoEntry.class.getName(),
				todoEntry.getTodoEntryId(), todoEntry.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			todoEntry.getGroupId(), TodoEntry.class.getName(),
			todoEntry.getTodoEntryId(), actionId);
	}

}