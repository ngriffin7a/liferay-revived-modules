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

package com.liferay.tasks.web.asset.model;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.TasksEntryLocalServiceUtil;
import com.liferay.tasks.service.permission.TasksEntryPermission;
import com.liferay.tasks.web.constants.TasksWebKeys;

import org.osgi.service.component.annotations.Component;

/**
 * @author Matthew Kong
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + TasksWebKeys.TASKS_PORTLET,
	service = AssetRendererFactory.class
)
public class TasksEntryAssetRendererFactory
	extends BaseAssetRendererFactory<TasksEntry> {

	public static final String CLASS_NAME = TasksEntry.class.getName();

	public static final String TYPE = "tasks";

	@Override
	public AssetRenderer getAssetRenderer(long classPK, int type)
		throws PortalException {

		return new TasksEntryAssetRenderer(
			TasksEntryLocalServiceUtil.getTasksEntry(classPK));
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String getIconCssClass() {
		return "icon-tasks";
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		return TasksEntryPermission.contains(
			permissionChecker, classPK, actionId);
	}

}