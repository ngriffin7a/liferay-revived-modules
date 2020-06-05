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

package com.liferay.todo.web.asset.model;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.todo.model.TodoEntry;
import com.liferay.todo.service.TodoEntryLocalServiceUtil;
import com.liferay.todo.service.permission.TodoEntryPermission;
import com.liferay.todo.web.constants.TodoWebKeys;

import org.osgi.service.component.annotations.Component;

import java.util.Locale;

/**
 * @author Matthew Kong
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + TodoWebKeys.TASKS_PORTLET,
	service = AssetRendererFactory.class
)
public class TodoEntryAssetRendererFactory
	extends BaseAssetRendererFactory<TodoEntry> {

	public static final String CLASS_NAME = TodoEntry.class.getName();

	public static final String TYPE = "todo";

	@Override
	public String getTypeName(Locale locale) {
		return super.getTypeName(locale);
	}

	@Override
	public AssetRenderer getAssetRenderer(long classPK, int type)
		throws PortalException {

		return new TodoEntryAssetRenderer(
			TodoEntryLocalServiceUtil.getTodoEntry(classPK));
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String getIconCssClass() {
		return "icon-todo";
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		return TodoEntryPermission.contains(
			permissionChecker, classPK, actionId);
	}

}