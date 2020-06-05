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

long priority = BeanParamUtil.getLong(todoEntry, request, "priority", TodoEntryConstants.PRIORITY_NORMAL);
long assigneeUserId = BeanParamUtil.getLong(todoEntry, request, "assigneeUserId");

boolean addDueDate = false;
String dueDateHideClass = "hide";
String dueDateToggleText = LanguageUtil.get(request, "add-due-date");

if ((todoEntry != null) && (todoEntry.getDueDate() != null)) {
	addDueDate = true;
	dueDateHideClass = StringPool.BLANK;
	dueDateToggleText = LanguageUtil.get(request, "remove-due-date");
}

String dueDateControlGroupCssClass = renderResponse.getNamespace() + "dueDateControlGroup";

String dueDateWrapperCssClass = dueDateControlGroupCssClass + StringPool.SPACE + dueDateHideClass;
%>

<c:choose>
	<c:when test="<%= (todoEntry == null) && (todoEntryId > 0) %>">
		<span class="alert alert-danger"><liferay-ui:message key="todo-could-not-be-found" /></span>
	</c:when>
	<c:otherwise>
		<portlet:actionURL name="updateTodoEntry" var="updateTodoEntryURL" />

		<aui:form action="<%= updateTodoEntryURL %>" cssClass="container-fluid-1280" method="post" name="fm1">
			<aui:input name="mvcPath" type="hidden" value="/todo/edit_todo.jsp" />
			<aui:input name="todoEntryId" type="hidden" value="<%= todoEntryId %>" />
			<aui:input name="userId" type="hidden" value="<%= user.getUserId() %>" />
			<aui:input name="resolverUserId" type="hidden" value="<%= user.getUserId() %>" />

			<liferay-asset:asset-tags-error />

			<aui:model-context bean="<%= todoEntry %>" model="<%= TodoEntry.class %>" />

			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<aui:input cssClass="input-todo-description" label="description" name="title">
						<aui:validator name="required" />
					</aui:input>

					<aui:select label="assignee" name="assigneeUserId">
						<c:choose>
							<c:when test="<%= group.isUser() %>">
								<aui:option label="<%= HtmlUtil.escape(user.getFullName()) %>" selected="<%= assigneeUserId == 0 %>" value="<%= user.getUserId() %>" />

								<optgroup label="<liferay-ui:message key="contacts" />">
							</c:when>
							<c:otherwise>
								<aui:option label="unassigned" selected="<%= assigneeUserId == 0 %>" value="0" />

								<aui:option label="<%= HtmlUtil.escape(user.getFullName()) %>" selected="<%= assigneeUserId == user.getUserId() %>" value="<%= user.getUserId() %>" />

								<c:if test="<%= (todoEntry != null) && (assigneeUserId > 0) && (assigneeUserId != user.getUserId()) %>">
									<aui:option label='<%= PortalUtil.getUserName(assigneeUserId, "N/A") %>' selected="<%= true %>" />
								</c:if>

								<optgroup label="<liferay-ui:message key="members" />">
							</c:otherwise>
						</c:choose>

						<%
						List<User> users = null;

						if (group.isUser()) {
							users = UserLocalServiceUtil.getSocialUsers(group.getClassPK(), SocialRelationConstants.TYPE_BI_CONNECTION, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new UserFirstNameComparator(true));
						}
						else {
							LinkedHashMap userParams = new LinkedHashMap();

							userParams.put("inherit", Boolean.TRUE);
							userParams.put("usersGroups", Long.valueOf(themeDisplay.getScopeGroupId()));

							users = UserLocalServiceUtil.search(company.getCompanyId(), StringPool.BLANK, WorkflowConstants.STATUS_APPROVED, userParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new UserFirstNameComparator(true));
						}

						for (User curUser : users) {
							long curUserId = curUser.getUserId();

							if (curUserId == user.getUserId()) {
								continue;
							}
						%>

							<aui:option label="<%= HtmlUtil.escape(curUser.getFullName()) %>" selected="<%= assigneeUserId == curUserId %>" value="<%= curUserId %>" />

						<%
						}
						%>

						</optgroup>
					</aui:select>

					<aui:select name="priority" value="<%= priority %>">
						<aui:option label="high" value="1" />
						<aui:option label="normal" value="2" />
						<aui:option label="low" value="3" />
					</aui:select>

					<aui:field-wrapper>
						<label class="control-label"><liferay-ui:message key="due-date" /></label>

						<aui:a cssClass="field-content" href="javascript:;" id="toggleDueDate" label="<%= dueDateToggleText %>" onClick='<%= renderResponse.getNamespace() + "displayInputDate();" %>' />
					</aui:field-wrapper>

					<aui:input id="addDueDate" name="addDueDate" type="hidden" value="<%= addDueDate %>" />

					<aui:input label="" name="dueDate" wrapperCssClass="<%= dueDateWrapperCssClass %>" />

					<c:if test="<%= todoEntry != null %>">
						<aui:select name="status">

							<%
							for (int curStatus : TodoEntryConstants.STATUSES) {
							%>

								<aui:option label="<%= TodoEntryConstants.getStatusLabel(curStatus) %>" selected="<%= todoEntry.getStatus() == curStatus %>" value="<%= curStatus %>" />

							<%
							}
							%>

						</aui:select>
					</c:if>

					<aui:input name="tags" type="assetTags" />
				</aui:fieldset>
			</aui:fieldset-group>

			<aui:button-row>
				<aui:button type="submit" />

				<c:if test="<%= todoEntryId > 0 %>">
					<portlet:renderURL var="viewURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
						<portlet:param name="mvcPath" value="/todo/view_todo.jsp" />
						<portlet:param name="todoEntryId" value="<%= String.valueOf(todoEntry.getTodoEntryId()) %>" />
					</portlet:renderURL>

					<aui:button onClick="<%= viewURL %>" value="cancel" />
				</c:if>
			</aui:button-row>
		</aui:form>
	</c:otherwise>
</c:choose>

<aui:script>
	var <portlet:namespace />displayInputDate = Liferay.lazyLoad(
		'metal-dom/src/dom',
		function(dom) {
			var form = document.querySelector('#<portlet:namespace />fm1');

			if (form) {
				var addDueDate = form.querySelector('#<portlet:namespace />addDueDate');
				var dueDateToggle = form.querySelector('#<portlet:namespace />toggleDueDate');

				if (addDueDate && dueDateToggle) {
					var addDueDateVal = 'true';

					var dueDateLabel = '<liferay-ui:message key="remove-due-date" />';

					if (addDueDate.value == 'true') {
						addDueDateVal = 'false';

						dueDateLabel = '<liferay-ui:message key="add-due-date" />';
					}

					addDueDate.value = addDueDateVal;

					dueDateToggle.textContent = dueDateLabel;
				}

				var dueDate = document.querySelector('.<%= dueDateControlGroupCssClass %>');

				dom.toggleClasses(dueDate, 'hide');
			}
		}
	);
</aui:script>