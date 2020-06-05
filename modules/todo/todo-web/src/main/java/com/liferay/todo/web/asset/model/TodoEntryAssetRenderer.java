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

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.todo.model.TodoEntry;
import com.liferay.todo.service.permission.TodoEntryPermission;
import com.liferay.todo.web.constants.TodoWebKeys;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Matthew Kong
 */
public class TodoEntryAssetRenderer extends BaseJSPAssetRenderer<TodoEntry> {

	public TodoEntryAssetRenderer(TodoEntry entry) {
		_entry = entry;
	}

	@Override
	public TodoEntry getAssetObject() {
		return _entry;
	}

	@Override
	public String getClassName() {
		return TodoEntry.class.getName();
	}

	@Override
	public long getClassPK() {
		return _entry.getTodoEntryId();
	}

	@Override
	public long getGroupId() {
		return _entry.getGroupId();
	}

	@Override
	public String getJspPath(
		HttpServletRequest httpServletRequest, String template) {

		if (template.equals(TEMPLATE_ABSTRACT) ||
			template.equals(TEMPLATE_FULL_CONTENT)) {

			return "/todo/asset/" + template + ".jsp";
		}

		return null;
	}

	@Override
	public int getStatus() {
		return _entry.getStatus();
	}

	@Override
	public String getSummary(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		return _entry.getTitle();
	}

	@Override
	public String getTitle(Locale locale) {
		return _entry.getTitle();
	}

	@Override
	public String getURLViewInContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		String noSuchEntryRedirect) {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)liferayPortletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			User user = themeDisplay.getUser();

			long portletPlid = PortalUtil.getPlidFromPortletId(
				user.getGroupId(), true, TodoWebKeys.TASKS_PORTLET);

			PortletURL portletURL = PortletURLFactoryUtil.create(
				liferayPortletRequest, TodoWebKeys.TASKS_PORTLET, portletPlid,
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter("mvcPath", "/todo/view.jsp");

			return portletURL.toString();
		}
		catch (Exception exception) {
		}

		return null;
	}

	@Override
	public long getUserId() {
		return _entry.getUserId();
	}

	@Override
	public String getUserName() {
		return _entry.getUserName();
	}

	@Override
	public String getUuid() {
		return null;
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) {
		return TodoEntryPermission.contains(
			permissionChecker, _entry, ActionKeys.VIEW);
	}

	@Override
	public boolean include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String template)
		throws Exception {

		httpServletRequest.setAttribute(TodoWebKeys.TASKS_ENTRY, _entry);

		return super.include(httpServletRequest, httpServletResponse, template);
	}

	private final TodoEntry _entry;

}