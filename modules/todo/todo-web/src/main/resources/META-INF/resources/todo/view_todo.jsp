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
long todoEntryId = ParamUtil.getLong(request, "todoEntryId");

TodoEntry todoEntry = todoEntryLocalService.fetchTodoEntry(todoEntryId);
%>

<c:choose>
	<c:when test="<%= todoEntry == null %>">
		<span class="alert alert-danger"><liferay-ui:message key="todo-could-not-be-found" /></span>
	</c:when>
	<c:otherwise>

		<%
		todoEntry = todoEntry.toEscapedModel();
		%>

		<liferay-ui:header
			title="<%= HtmlUtil.unescape(todoEntry.getTitle()) %>"
		/>

		<div class="todo-data-container">
			<div class="todo-data">
				<c:choose>
					<c:when test="<%= todoEntry.getAssigneeUserId() > 0 %>">

						<%
						String taglibAssigneeDisplayURL = LanguageUtil.get(request, "unknown-user");

						User assigneeUser = UserLocalServiceUtil.fetchUser(todoEntry.getAssigneeUserId());

						if (assigneeUser != null) {
							taglibAssigneeDisplayURL = "<a href=\"" + assigneeUser.getDisplayURL(themeDisplay) + "\">" + PortalUtil.getUserName(todoEntry.getAssigneeUserId(), "N/A") + "</a>";
						}
						%>

						<liferay-ui:icon
							icon="user"
							label="<%= true %>"
							markupView="lexicon"
							message='<%= LanguageUtil.format(request, "assigned-to-x", taglibAssigneeDisplayURL, false) %>'
						/>
					</c:when>
					<c:otherwise>
						<liferay-ui:icon
							icon="upload"
							label="<%= true %>"
							markupView="lexicon"
							message="unassigned"
						/>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="todo-data">

				<%
				String taglibReporterDisplayURL = LanguageUtil.get(request, "unknown-user");

				User reporterUser = UserLocalServiceUtil.fetchUser(todoEntry.getUserId());

				if (reporterUser != null) {
					taglibReporterDisplayURL = "<a href=\"" + reporterUser.getDisplayURL(themeDisplay) + "\">" + PortalUtil.getUserName(todoEntry.getUserId(), "N/A") + "</a>";
				}
				%>

				<liferay-ui:icon
					icon="user"
					label="<%= true %>"
					markupView="lexicon"
					message='<%= LanguageUtil.format(request, "created-by-x", taglibReporterDisplayURL, false) %>'
				/>
			</div>

			<div class="last todo-data">
				<liferay-ui:icon
					icon="calendar"
					label="<%= true %>"
					markupView="lexicon"
					message='<%= LanguageUtil.format(request, "modified-on-x", dateFormatDateTime.format(todoEntry.getModifiedDate()), false) %>'
				/>
			</div>
		</div>

		<table class="lfr-table todo-data-table">
			<tr>
				<td class="lfr-label">
					<liferay-ui:message key="status" />
				</td>
				<td>
					<div class="status todo-data">
						<liferay-ui:message key="<%= TodoEntryConstants.getStatusLabel(todoEntry.getStatus()) %>" />
					</div>
				</td>
			</tr>
			<tr>
				<td class="lfr-label">
					<liferay-ui:message key="priority" />
				</td>
				<td>
					<div class="todo-data <%= TodoEntryConstants.getPriorityLabel(todoEntry.getPriority()) %>">
						<liferay-ui:message key="<%= TodoEntryConstants.getPriorityLabel(todoEntry.getPriority()) %>" />
					</div>
				</td>
			</tr>

			<c:if test="<%= todoEntry.getDueDate() != null %>">
				<tr>
					<td class="lfr-label">
						<liferay-ui:message key="due-date" />
					</td>
					<td>
						<div class="due-date todo-data">
							<%= dateFormatDateTime.format(todoEntry.getDueDate()) %>
						</div>
					</td>
				</tr>
			</c:if>

			<tr>
				<td class="lfr-label">
					<liferay-ui:message key="priority" />
				</td>
				<td>
					<div class="todo-data <%= TodoEntryConstants.getPriorityLabel(todoEntry.getPriority()) %>">
						<i class="icon-circle"></i>

						<liferay-ui:message key="<%= TodoEntryConstants.getPriorityLabel(todoEntry.getPriority()) %>" />
					</div>
				</td>
			</tr>

			<c:if test="<%= todoEntry.getDueDate() != null %>">
				<tr>
					<td class="lfr-label">
						<liferay-ui:message key="due-date" />
					</td>
					<td>
						<div class="due-date todo-data">
							<liferay-ui:icon
								icon="calendar"
								label="<%= true %>"
								markupView="lexicon"
								message="<%= dateFormatDateTime.format(todoEntry.getDueDate()) %>"
							/>
						</div>
					</td>
				</tr>
			</c:if>

			<tr>
				<td colspan="2">
					<br />
				</td>
			</tr>
			<tr>
				<td class="lfr-label">
					<liferay-ui:message key="tags" />
				</td>
				<td>
					<liferay-asset:asset-tags-summary
						className="<%= TodoEntry.class.getName() %>"
						classPK="<%= todoEntry.getTodoEntryId() %>"
					/>
				</td>
			</tr>
		</table>

		<aui:button-row>
			<c:if test="<%= TodoEntryPermission.contains(permissionChecker, todoEntry, ActionKeys.UPDATE) %>">

				<%
				boolean resolved = todoEntry.getStatus() == TodoEntryConstants.STATUS_RESOLVED;
				%>

				<portlet:actionURL name="updateTodoEntryStatus" var="updateTodoEntryStatusURL">
					<portlet:param name="todoEntryId" value="<%= String.valueOf(todoEntry.getTodoEntryId()) %>" />
					<portlet:param name="resolverUserId" value="<%= String.valueOf(user.getUserId()) %>" />
					<portlet:param name="status" value="<%= String.valueOf(resolved ? TodoEntryConstants.STATUS_REOPENED : TodoEntryConstants.STATUS_RESOLVED) %>" />
				</portlet:actionURL>

				<aui:button cssClass="btn-primary" onClick="<%= updateTodoEntryStatusURL %>" value='<%= resolved ? "reopen" : "resolve" %>' />

				<portlet:renderURL var="editTodoEntryURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="mvcPath" value="/todo/edit_todo.jsp" />
					<portlet:param name="todoEntryId" value="<%= String.valueOf(todoEntry.getTodoEntryId()) %>" />
				</portlet:renderURL>

				<aui:button onClick="<%= editTodoEntryURL %>" value="edit" />

				<aui:button name="deleteTodoEntry" value="delete" />

				<aui:script use="aui-io-deprecated">
					var <portlet:namespace />deleteTodoEntry = document.querySelector('#<portlet:namespace />deleteTodoEntry');

					if (<portlet:namespace />deleteTodoEntry) {
						<portlet:namespace />deleteTodoEntry.addEventListener(
							'click',
							function(event) {
								if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
									A.io.request(
										'<portlet:actionURL name="deleteTodoEntry" />',
										{
											after: {
												success: function() {
													Liferay.Util.getWindow('<portlet:namespace />Dialog').hide();
												}
											},
											data: {
												<portlet:namespace />todoEntryId: <%= todoEntry.getTodoEntryId() %>
											}
										}
									);
								}
							}
						);
					}
				</aui:script>
			</c:if>
		</aui:button-row>

		<liferay-ui:tabs
			names="comments"
		/>

		<%@ include file="/todo/view_comments.jspf" %>
	</c:otherwise>
</c:choose>