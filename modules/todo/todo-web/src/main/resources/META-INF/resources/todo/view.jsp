<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", tabs1Default);
String tabs2 = ParamUtil.getString(request, "tabs2", "open");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);

portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("tabs2", tabs2);
%>

<%@ include file="/todo/tabs1.jspf" %>

<div class="control-wrapper">
	<c:if test="<%= TodoPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), ActionKeys.ADD_ENTRY) %>">
		<portlet:renderURL var="addkURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/todo/edit_todo.jsp" />
		</portlet:renderURL>

		<%
		String taglibOnClick = "Liferay.Todo.displayPopup('" + addkURL.toString() + "', '" + LanguageUtil.get(request, "add-todo") + "');";
		%>

		<liferay-ui:icon
			icon="plus"
			label="<%= true %>"
			markupView="lexicon"
			message="add-todo"
			onClick="<%= taglibOnClick %>"
			url="javascript:;"
		/>
	</c:if>

	<c:if test="<%= TodoPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="com.liferay.todo"
			modelResourceDescription="<%= HtmlUtil.escape(themeDisplay.getScopeGroupName()) %>"
			resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			icon="lock"
			label="<%= true %>"
			markupView="lexicon"
			message="permissions"
			method="get"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<liferay-ui:icon
		cssClass="filter-todo"
		icon="tag"
		label="<%= true %>"
		markupView="lexicon"
		message="filter"
		onClick="Liferay.Todo.toggleTodoFilter()"
		url="javascript:;"
	/>

	<div style="clear: both;"><!-- --></div>
</div>

<div class="filter-wrapper hide">
	<%@ include file="/todo/view_todos_filter.jspf" %>
</div>

<div class="list-wrapper">
	<liferay-util:include page="/todo/view_todos.jsp" servletContext="<%= application %>" />
</div>

<div class="todo-options">
	<table>
		<tr>
			<td>
				<input name="all-todo" onclick="Liferay.Todo.updateTodoList(null, this.checked);" type="checkbox" <%= tabs2.equals("all") ? "checked" : StringPool.BLANK %> />
			</td>
			<td>
				<liferay-ui:message key="show-completed-todo" />
			</td>
		</tr>
	</table>
</div>

<aui:script use="liferay-todo">
	Liferay.Todo.init(
		{
			baseActionURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.ACTION_PHASE) %>',
			currentTab: '<%= HtmlUtil.escape(tabs1) %>',
			namespace: '<portlet:namespace />',
			todoListURL: '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/todo/view_todos.jsp" /></portlet:renderURL>'
		}
	);
</aui:script>