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

package com.liferay.todo.web.portlet;

import com.liferay.asset.kernel.exception.AssetTagException;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.todo.model.TodoEntry;
import com.liferay.todo.service.TodoEntryLocalService;
import com.liferay.todo.service.TodoEntryServiceUtil;
import com.liferay.todo.web.constants.TodoWebKeys;

import java.io.IOException;

import java.util.Calendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=todo-portlet",
		"com.liferay.portlet.display-category=category.collaboration",
		"com.liferay.portlet.footer-portlet-javascript=/todo/js/main.js",
		"com.liferay.portlet.header-portlet-css=/todo/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=TodoWeb",
		"javax.portlet.init-param.template-path=/todo/",
		"javax.portlet.init-param.view-template=/todo/view.jsp",
		"javax.portlet.name=" + TodoWebKeys.TASKS_PORTLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class TodoPortlet extends MVCPortlet {

	public void deleteTodoEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long todoEntryId = ParamUtil.getLong(actionRequest, "todoEntryId");

		TodoEntryLocalService todoEntryLocalService =
			getTodoEntryLocalService();

		todoEntryLocalService.deleteTodoEntry(todoEntryId);

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
		else {
			JSONObject jsonObject = JSONUtil.put("success", Boolean.TRUE);

			ServletResponseUtil.write(
				PortalUtil.getHttpServletResponse(actionResponse),
				jsonObject.toString());
		}
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		if (!callActionMethod(actionRequest, actionResponse)) {
			return;
		}

		if (SessionErrors.isEmpty(actionRequest)) {
			SessionMessages.add(
				actionRequest,
				PortalUtil.getPortletId(actionRequest) +
					SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
				TodoWebKeys.TASKS_PORTLET);
		}

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
	}

	public void updateTodoEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long todoEntryId = ParamUtil.getLong(actionRequest, "todoEntryId");

		String title = ParamUtil.getString(actionRequest, "title");
		int priority = ParamUtil.getInteger(actionRequest, "priority");
		long assigneeUserId = ParamUtil.getLong(
			actionRequest, "assigneeUserId");
		long resolverUserId = ParamUtil.getLong(
			actionRequest, "resolverUserId");

		int dueDateMonth = ParamUtil.getInteger(actionRequest, "dueDateMonth");
		int dueDateDay = ParamUtil.getInteger(actionRequest, "dueDateDay");
		int dueDateYear = ParamUtil.getInteger(actionRequest, "dueDateYear");
		int dueDateHour = ParamUtil.getInteger(actionRequest, "dueDateHour");
		int dueDateMinute = ParamUtil.getInteger(
			actionRequest, "dueDateMinute");
		int dueDateAmPm = ParamUtil.getInteger(actionRequest, "dueDateAmPm");

		if (dueDateAmPm == Calendar.PM) {
			dueDateHour += 12;
		}

		boolean addDueDate = ParamUtil.getBoolean(actionRequest, "addDueDate");
		int status = ParamUtil.getInteger(actionRequest, "status");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			TodoEntry.class.getName(), actionRequest);

		TodoEntry todoEntry = null;

		try {
			if (todoEntryId <= 0) {
				todoEntry = TodoEntryServiceUtil.addTodoEntry(
					title, priority, assigneeUserId, dueDateMonth, dueDateDay,
					dueDateYear, dueDateHour, dueDateMinute, addDueDate,
					TodoWebKeys.TASKS_PORTLET, serviceContext);
			}
			else {
				todoEntry = TodoEntryServiceUtil.updateTodoEntry(
					todoEntryId, title, priority, assigneeUserId,
					resolverUserId, dueDateMonth, dueDateDay, dueDateYear,
					dueDateHour, dueDateMinute, addDueDate, status,
					TodoWebKeys.TASKS_PORTLET, serviceContext);
			}

			Layout layout = themeDisplay.getLayout();

			PortletURL portletURL = PortletURLFactoryUtil.create(
				actionRequest, TodoWebKeys.TASKS_PORTLET, layout.getPlid(),
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter("mvcPath", "/todo/view_todo.jsp");
			portletURL.setParameter(
				"todoEntryId", String.valueOf(todoEntry.getTodoEntryId()));
			portletURL.setWindowState(LiferayWindowState.POP_UP);

			actionResponse.sendRedirect(portletURL.toString());
		}
		catch (AssetTagException assetTagException) {
			actionResponse.setRenderParameter(
				"mvcPath", "/todo/edit_todo.jsp");

			actionResponse.setRenderParameters(actionRequest.getParameterMap());

			SessionErrors.add(
				actionRequest, assetTagException.getClass(), assetTagException);
		}
	}

	public void updateTodoEntryStatus(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long todoEntryId = ParamUtil.getLong(actionRequest, "todoEntryId");

		long resolverUserId = ParamUtil.getLong(
			actionRequest, "resolverUserId");
		int status = ParamUtil.getInteger(actionRequest, "status");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			TodoEntry.class.getName(), actionRequest);

		TodoEntryServiceUtil.updateTodoEntryStatus(
			todoEntryId, resolverUserId, status, TodoWebKeys.TASKS_PORTLET,
			serviceContext);

		Layout layout = themeDisplay.getLayout();

		PortletURL portletURL = PortletURLFactoryUtil.create(
			actionRequest, TodoWebKeys.TASKS_PORTLET, layout.getPlid(),
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/todo/view_todo.jsp");
		portletURL.setParameter("todoEntryId", String.valueOf(todoEntryId));
		portletURL.setWindowState(LiferayWindowState.POP_UP);

		actionResponse.sendRedirect(portletURL.toString());
	}

	public void updateTodoEntryViewCount(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long todoEntryId = ParamUtil.getLong(actionRequest, "todoEntryId");

		TodoEntry todoEntry = getTodoEntryLocalService().fetchTodoEntry(
			todoEntryId);

		if (todoEntry == null) {
			return;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		AssetEntryLocalServiceUtil.incrementViewCounter(
			themeDisplay.getUserId(), TodoEntry.class.getName(), todoEntryId);
	}

	protected TodoEntryLocalService getTodoEntryLocalService() {
		return _todoEntryLocalService;
	}

	@Override
	protected void include(
			String path, PortletRequest portletRequest,
			PortletResponse portletResponse, String lifecycle)
		throws IOException, PortletException {

		portletRequest.setAttribute(
			"todoEntryLocalService", getTodoEntryLocalService());

		super.include(path, portletRequest, portletResponse, lifecycle);
	}

	@Reference
	private TodoEntryLocalService _todoEntryLocalService;

}