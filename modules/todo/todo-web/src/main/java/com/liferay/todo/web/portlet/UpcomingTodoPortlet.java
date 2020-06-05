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

import com.liferay.todo.service.TodoEntryLocalService;
import com.liferay.todo.web.constants.TodoWebKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=upcoming-todo-portlet",
		"com.liferay.portlet.display-category=category.collaboration",
		"com.liferay.portlet.footer-portlet-javascript=/todo/js/main.js",
		"com.liferay.portlet.header-portlet-css=/todo/css/main.css",
		"javax.portlet.display-name=UpcomingTodoWeb",
		"javax.portlet.init-param.template-path=/upcoming_todo/",
		"javax.portlet.init-param.view-template=/upcoming_todo/view.jsp",
		"javax.portlet.name=" + TodoWebKeys.UPCOMING_TASKS_PORTLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class UpcomingTodoPortlet extends TodoPortlet {

	@Override
	protected TodoEntryLocalService getTodoEntryLocalService() {
		return _todoEntryLocalService;
	}

	@Reference
	private TodoEntryLocalService _todoEntryLocalService;

}