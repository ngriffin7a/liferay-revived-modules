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

long[] assetTagIds = StringUtil.split(ParamUtil.getString(request, "assetTagIds"), 0L);

long groupId = ParamUtil.getLong(request, "groupId");

if (group.isRegularSite()) {
	groupId = group.getGroupId();
}

long userId = 0;

if (tabs1.equals("i-have-created")) {
	userId = user.getUserId();
}

long assigneeUserId = 0;

if (tabs1.equals("assigned-to-me")) {
	assigneeUserId = user.getUserId();
}

int status = TodoEntryConstants.STATUS_ALL;

if (tabs2.equals("open")) {
	status = TodoEntryConstants.STATUS_OPEN;
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);

portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("tabs2", tabs2);

PortletURL todoListURL = renderResponse.createRenderURL();

todoListURL.setWindowState(LiferayWindowState.EXCLUSIVE);

todoListURL.setParameter("mvcPath", "/todo/view_todo.jsp");
todoListURL.setParameter("tabs1", tabs1);
todoListURL.setParameter("tabs2", tabs2);
%>

<liferay-ui:search-container
	emptyResultsMessage="no-todos-were-found"
	headerNames="description,due, "
	iteratorURL="<%= portletURL %>"
	total="<%= todoEntryLocalService.getTodoEntriesCount(groupId, userId, 0, assigneeUserId, status, assetTagIds, new long[0]) %>"
>
	<liferay-ui:search-container-results
		results="<%= todoEntryLocalService.getTodoEntries(groupId, userId, 0, assigneeUserId, status, assetTagIds, new long[0], searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.todo.model.TodoEntry"
		escapedModel="<%= true %>"
		keyProperty="todoEntryId"
		modelVar="todoEntry"
	>

		<%
		String rowHREF = null;

		if (TodoEntryPermission.contains(permissionChecker, todoEntry, ActionKeys.UPDATE)) {
			PortletURL rowURL = renderResponse.createRenderURL();

			rowURL.setWindowState(LiferayWindowState.POP_UP);

			rowURL.setParameter("mvcPath", "/todo/view_todo.jsp");
			rowURL.setParameter("todoEntryId", String.valueOf(todoEntry.getTodoEntryId()));

			rowHREF = rowURL.toString();
		}
		%>

		<liferay-ui:search-container-column-text
			name="description"
		>

			<%
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

			<div class="result-title">
				<c:choose>
					<c:when test="<%= Validator.isNotNull(rowHREF) %>">
						<a class="<%= cssClass %>" href="javascript:;" onClick="Liferay.Todo.openTodo('<%= rowHREF %>', <%= todoEntry.getTodoEntryId() %>);">
							<i class="icon-circle"></i>

							<%= todoEntry.getTitle() %>
						</a>
					</c:when>
					<c:otherwise>
						<span class="<%= cssClass %>">
							<i class="icon-circle"></i>

							<%= todoEntry.getTitle() %>
						</span>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="result-data">
				<c:if test="<%= group.isUser() %>">

					<%
					Group curGroup = GroupLocalServiceUtil.fetchGroup(todoEntry.getGroupId());
					%>

					<c:if test="<%= (curGroup != null) && curGroup.isRegularSite() %>">
						<span><liferay-ui:message key="site" />: <%= HtmlUtil.escape(curGroup.getDescriptiveName(locale)) %></span>
					</c:if>
				</c:if>

				<c:if test='<%= !tabs1.equals("assigned-to-me") %>'>
					<span><liferay-ui:message key="assignee" />:
						<c:choose>
							<c:when test="<%= todoEntry.getAssigneeUserId() > 0 %>">
								<%= PortalUtil.getUserName(todoEntry.getAssigneeUserId(), "N/A") %>
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="unassigned" />
							</c:otherwise>
						</c:choose>
					</span>
				</c:if>

				<c:if test='<%= !tabs1.equals("i-have-created") %>'>
					<span><liferay-ui:message key="reporter" />: <%= PortalUtil.getUserName(todoEntry.getUserId(), "N/A") %></span>
				</c:if>
			</div>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="due"
		>
			<c:choose>
				<c:when test="<%= TodoEntryPermission.contains(permissionChecker, todoEntry, ActionKeys.UPDATE) %>">

					<%
					int curStatus = todoEntry.getStatus();

					int width = 0;

					if (curStatus == TodoEntryConstants.STATUS_PERCENT_TWENTY) {
						width = 20;
					}
					else if (curStatus == TodoEntryConstants.STATUS_PERCENT_FORTY) {
						width = 40;
					}
					else if (curStatus == TodoEntryConstants.STATUS_PERCENT_SIXTY) {
						width = 60;
					}
					else if (curStatus == TodoEntryConstants.STATUS_PERCENT_EIGHTY) {
						width = 80;
					}
					else if (curStatus == TodoEntryConstants.STATUS_RESOLVED) {
						width = 100;
					}
					%>

					<div class="progress-wrapper">
						<div class="current">
							<div class="progress" style="width: <%= width %>%;">
								<!-- -->
							</div>

							<c:if test="<%= todoEntry.getDueDate() != null %>">
								<div class="due-date <%= (DateUtil.compareTo(new Date(), todoEntry.getDueDate()) >= 0) ? "past-due" : StringPool.BLANK %>">
									<%= dateFormatDateTime.format(todoEntry.getDueDate()) %>
								</div>
							</c:if>
						</div>

						<div class="hide progress-picker">
							<div class="new-progress"><!-- --></div>
							<div class="progress-indicator"></div>
							<div class="progress-selector">

								<%
								for (int i = TodoEntryConstants.STATUS_PERCENT_TWENTY; i <= TodoEntryConstants.STATUS_RESOLVED; i++) {
								%>

									<portlet:actionURL name="updateTodoEntryStatus" var="statusURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
										<portlet:param name="redirect" value="<%= todoListURL.toString() %>" />
										<portlet:param name="todoEntryId" value="<%= String.valueOf(todoEntry.getTodoEntryId()) %>" />
										<portlet:param name="resolverUserId" value="<%= String.valueOf(user.getUserId()) %>" />
										<portlet:param name="status" value="<%= String.valueOf(i) %>" />
									</portlet:actionURL>

									<a class="progress-<%= (i - 1) * 20 %>" href="<%= statusURL %>"><!-- --></a>

								<%
								}
								%>

							</div>
						</div>
					</div>
				</c:when>
				<c:when test="<%= todoEntry.getDueDate() != null %>">
					<%= dateFormatDateTime.format(todoEntry.getDueDate()) %>
				</c:when>
			</c:choose>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="<%= StringPool.BLANK %>"
		>

			<%
			List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(TodoEntry.class.getName(), todoEntry.getTodoEntryId());
			%>

			<c:if test="<%= !assetTags.isEmpty() %>">
				<div class="tags-wrapper">
					<i class="icon-tag"></i>

					<div class="hide tags">
						<%= ListUtil.toString(assetTags, AssetTag.NAME_ACCESSOR) %>
					</div>
				</div>
			</c:if>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>