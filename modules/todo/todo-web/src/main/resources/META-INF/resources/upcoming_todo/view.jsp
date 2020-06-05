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

<div class="todo-entries-container">
	<ul class="todo-entries">

		<%
		List<TodoEntry> todoEntries = todoEntryLocalService.getTodoEntries(0, user.getUserId(), 0, 0, TodoEntryConstants.STATUS_OPEN, new long[0], new long[0], 0, 10);

		for (TodoEntry todoEntry : todoEntries) {
			String todoHREF = null;

			if (TodoEntryPermission.contains(permissionChecker, todoEntry, ActionKeys.UPDATE)) {
				LiferayPortletURL liferayPortletURL = liferayPortletResponse.createLiferayPortletURL(TodoWebKeys.TASKS_PORTLET, PortletRequest.RENDER_PHASE);

				liferayPortletURL.setParameter("mvcPath", "/todo/view_todo.jsp");
				liferayPortletURL.setParameter("todoEntryId", String.valueOf(todoEntry.getTodoEntryId()));
				liferayPortletURL.setWindowState(LiferayWindowState.POP_UP);

				todoHREF = liferayPortletURL.toString();
			}

			String cssClass = "todo-title";

			if (todoEntry.getPriority() == 1) {
				cssClass = cssClass.concat(" high");
			}
			else if (todoEntry.getPriority() == 2) {
				cssClass = cssClass.concat(" normal");
			}
			else {
				cssClass = cssClass.concat(" low");
			}
		%>

			<li class="<%= cssClass %>">
				<c:choose>
					<c:when test="<%= Validator.isNotNull(todoHREF) %>">
						<a href="javascript:;" onClick="Liferay.Todo.openTodo('<%= todoHREF %>');">
							<i class="icon-circle"></i>

							<%= HtmlUtil.escape(todoEntry.getTitle()) %>
						</a>
					</c:when>
					<c:otherwise>
						<span>
							<i class="icon-circle"></i>

							<%= HtmlUtil.escape(todoEntry.getTitle()) %>
						</span>
					</c:otherwise>
				</c:choose>
			</li>

		<%
		}
		%>

	</ul>

	<div class="view-all-todo">

		<%
		long todoPlid = PortalUtil.getPlidFromPortletId(group.getGroupId(), TodoWebKeys.TASKS_PORTLET);

		PortletURL portletURL = null;

		if (todoPlid != 0) {
			portletURL = PortletURLFactoryUtil.create(request, TodoWebKeys.TASKS_PORTLET, todoPlid, PortletRequest.RENDER_PHASE);
		}
		%>

		<c:if test="<%= portletURL != null %>">
			<a href="<%= portletURL %>"><liferay-ui:message key="view-all-todo" /></a>
		</c:if>
	</div>
</div>

<aui:script use="liferay-todo">
	Liferay.Todo.initUpcomingTodo(
		{
			upcomingTodoListURL: '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/upcoming_todo/view.jsp" /></portlet:renderURL>'
		}
	);
</aui:script>