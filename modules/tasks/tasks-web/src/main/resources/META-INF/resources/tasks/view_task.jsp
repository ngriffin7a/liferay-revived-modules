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
long tasksEntryId = ParamUtil.getLong(request, "tasksEntryId");

TasksEntry tasksEntry = tasksEntryLocalService.fetchTasksEntry(tasksEntryId);
%>

<c:choose>
	<c:when test="<%= tasksEntry == null %>">
		<span class="alert alert-danger"><liferay-ui:message key="task-could-not-be-found" /></span>
	</c:when>
	<c:otherwise>

		<%
		tasksEntry = tasksEntry.toEscapedModel();
		%>

		<liferay-ui:header
			title="<%= HtmlUtil.unescape(tasksEntry.getTitle()) %>"
		/>

		<div class="task-data-container">
			<div class="task-data">
				<c:choose>
					<c:when test="<%= tasksEntry.getAssigneeUserId() > 0 %>">

						<%
						String taglibAssigneeDisplayURL = LanguageUtil.get(request, "unknown-user");

						User assigneeUser = UserLocalServiceUtil.fetchUser(tasksEntry.getAssigneeUserId());

						if (assigneeUser != null) {
							taglibAssigneeDisplayURL = "<a href=\"" + assigneeUser.getDisplayURL(themeDisplay) + "\">" + PortalUtil.getUserName(tasksEntry.getAssigneeUserId(), "N/A") + "</a>";
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

			<div class="task-data">

				<%
				String taglibReporterDisplayURL = LanguageUtil.get(request, "unknown-user");

				User reporterUser = UserLocalServiceUtil.fetchUser(tasksEntry.getUserId());

				if (reporterUser != null) {
					taglibReporterDisplayURL = "<a href=\"" + reporterUser.getDisplayURL(themeDisplay) + "\">" + PortalUtil.getUserName(tasksEntry.getUserId(), "N/A") + "</a>";
				}
				%>

				<liferay-ui:icon
					icon="user"
					label="<%= true %>"
					markupView="lexicon"
					message='<%= LanguageUtil.format(request, "created-by-x", taglibReporterDisplayURL, false) %>'
				/>
			</div>

			<div class="last task-data">
				<liferay-ui:icon
					icon="calendar"
					label="<%= true %>"
					markupView="lexicon"
					message='<%= LanguageUtil.format(request, "modified-on-x", dateFormatDateTime.format(tasksEntry.getModifiedDate()), false) %>'
				/>
			</div>
		</div>

		<table class="lfr-table task-data-table">
			<tr>
				<td class="lfr-label">
					<liferay-ui:message key="status" />
				</td>
				<td>
					<div class="status task-data">
						<liferay-ui:message key="<%= TasksEntryConstants.getStatusLabel(tasksEntry.getStatus()) %>" />
					</div>
				</td>
			</tr>
			<tr>
				<td class="lfr-label">
					<liferay-ui:message key="priority" />
				</td>
				<td>
					<div class="task-data <%= TasksEntryConstants.getPriorityLabel(tasksEntry.getPriority()) %>">
						<liferay-ui:message key="<%= TasksEntryConstants.getPriorityLabel(tasksEntry.getPriority()) %>" />
					</div>
				</td>
			</tr>

			<c:if test="<%= tasksEntry.getDueDate() != null %>">
				<tr>
					<td class="lfr-label">
						<liferay-ui:message key="due-date" />
					</td>
					<td>
						<div class="due-date task-data">
							<%= dateFormatDateTime.format(tasksEntry.getDueDate()) %>
						</div>
					</td>
				</tr>
			</c:if>

			<tr>
				<td class="lfr-label">
					<liferay-ui:message key="priority" />
				</td>
				<td>
					<div class="task-data <%= TasksEntryConstants.getPriorityLabel(tasksEntry.getPriority()) %>">
						<i class="icon-circle"></i>

						<liferay-ui:message key="<%= TasksEntryConstants.getPriorityLabel(tasksEntry.getPriority()) %>" />
					</div>
				</td>
			</tr>

			<c:if test="<%= tasksEntry.getDueDate() != null %>">
				<tr>
					<td class="lfr-label">
						<liferay-ui:message key="due-date" />
					</td>
					<td>
						<div class="due-date task-data">
							<liferay-ui:icon
								icon="calendar"
								label="<%= true %>"
								markupView="lexicon"
								message="<%= dateFormatDateTime.format(tasksEntry.getDueDate()) %>"
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
						className="<%= TasksEntry.class.getName() %>"
						classPK="<%= tasksEntry.getTasksEntryId() %>"
					/>
				</td>
			</tr>
		</table>

		<aui:button-row>
			<c:if test="<%= TasksEntryPermission.contains(permissionChecker, tasksEntry, ActionKeys.UPDATE) %>">

				<%
				boolean resolved = tasksEntry.getStatus() == TasksEntryConstants.STATUS_RESOLVED;
				%>

				<portlet:actionURL name="updateTasksEntryStatus" var="updateTasksEntryStatusURL">
					<portlet:param name="tasksEntryId" value="<%= String.valueOf(tasksEntry.getTasksEntryId()) %>" />
					<portlet:param name="resolverUserId" value="<%= String.valueOf(user.getUserId()) %>" />
					<portlet:param name="status" value="<%= String.valueOf(resolved ? TasksEntryConstants.STATUS_REOPENED : TasksEntryConstants.STATUS_RESOLVED) %>" />
				</portlet:actionURL>

				<aui:button cssClass="btn-primary" onClick="<%= updateTasksEntryStatusURL %>" value='<%= resolved ? "reopen" : "resolve" %>' />

				<portlet:renderURL var="editTasksEntryURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="mvcPath" value="/tasks/edit_task.jsp" />
					<portlet:param name="tasksEntryId" value="<%= String.valueOf(tasksEntry.getTasksEntryId()) %>" />
				</portlet:renderURL>

				<aui:button onClick="<%= editTasksEntryURL %>" value="edit" />

				<aui:button name="deleteTasksEntry" value="delete" />

				<aui:script use="aui-io-deprecated">
					var <portlet:namespace />deleteTasksEntry = document.querySelector('#<portlet:namespace />deleteTasksEntry');

					if (<portlet:namespace />deleteTasksEntry) {
						<portlet:namespace />deleteTasksEntry.addEventListener(
							'click',
							function(event) {
								if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
									A.io.request(
										'<portlet:actionURL name="deleteTasksEntry" />',
										{
											after: {
												success: function() {
													Liferay.Util.getWindow('<portlet:namespace />Dialog').hide();
												}
											},
											data: {
												<portlet:namespace />tasksEntryId: <%= tasksEntry.getTasksEntryId() %>
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

		<%@ include file="/tasks/view_comments.jspf" %>
	</c:otherwise>
</c:choose>