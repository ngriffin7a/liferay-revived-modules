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

package com.liferay.todo.web.social;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ClassResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.social.kernel.model.BaseSocialActivityInterpreter;
import com.liferay.social.kernel.model.SocialActivity;
import com.liferay.social.kernel.model.SocialActivityInterpreter;
import com.liferay.todo.model.TodoEntry;
import com.liferay.todo.service.TodoEntryLocalServiceUtil;
import com.liferay.todo.service.permission.TodoEntryPermission;
import com.liferay.todo.social.TodoActivityKeys;
import com.liferay.todo.web.constants.TodoWebKeys;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ryan Park
 */
@Component(
	property = "javax.portlet.name=" + TodoWebKeys.TASKS_PORTLET,
	service = SocialActivityInterpreter.class
)
public class TodoActivityInterpreter extends BaseSocialActivityInterpreter {

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		TodoEntry todoEntry = TodoEntryLocalServiceUtil.getTodoEntry(
			activity.getClassPK());

		return getJSONValue(
			activity.getExtraData(), "title", todoEntry.getTitle());
	}

	@Override
	protected String getLink(
		SocialActivity activity, ServiceContext serviceContext) {

		return StringPool.BLANK;
	}

	@Override
	protected ResourceBundleLoader getResourceBundleLoader() {
		return _resourceBundleLoader;
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		long userId = activity.getUserId();
		long receiverUserId = activity.getReceiverUserId();

		int activityType = activity.getType();

		if (activityType == TodoActivityKeys.RESOLVE_ENTRY) {
			TodoEntry todoEntry = TodoEntryLocalServiceUtil.getTodoEntry(
				activity.getClassPK());

			userId = todoEntry.getResolverUserId();
			receiverUserId = todoEntry.getUserId();
		}

		String creatorUserName = getUserName(userId, serviceContext);
		String receiverUserName = getUserName(receiverUserId, serviceContext);

		return new Object[] {creatorUserName, receiverUserName};
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		long userId = activity.getUserId();
		long receiverUserId = activity.getReceiverUserId();

		int activityType = activity.getType();

		if (activityType == TodoActivityKeys.ADD_ENTRY) {
			if ((userId != receiverUserId) && (receiverUserId != 0)) {
				return "activity-todo-add-entry-for";
			}

			return "activity-todo-add-entry";
		}
		else if (activityType == TodoActivityKeys.REOPEN_ENTRY) {
			if ((userId != receiverUserId) && (receiverUserId != 0)) {
				return "activity-todo-reopen-entry-for";
			}

			return "activity-todo-reopen-entry";
		}
		else if (activityType == TodoActivityKeys.RESOLVE_ENTRY) {
			if ((userId != receiverUserId) && (receiverUserId != 0)) {
				return "activity-todo-resolve-entry-for";
			}

			return "activity-todo-resolve-entry";
		}
		else if (activityType == TodoActivityKeys.UPDATE_ENTRY) {
			if ((userId != receiverUserId) && (receiverUserId != 0)) {
				return "activity-todo-update-entry-for";
			}

			return "activity-todo-update-entry";
		}

		return StringPool.BLANK;
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

		TodoEntry todoEntry = TodoEntryLocalServiceUtil.fetchTodoEntry(
			activity.getClassPK());

		if (todoEntry == null) {
			return false;
		}

		return TodoEntryPermission.contains(
			permissionChecker, todoEntry, ActionKeys.VIEW);
	}

	private static final String[] _CLASS_NAMES = {TodoEntry.class.getName()};

	private final ResourceBundleLoader _resourceBundleLoader =
		new ClassResourceBundleLoader(
			"content.Language", TodoActivityInterpreter.class);

}