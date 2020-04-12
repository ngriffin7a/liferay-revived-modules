package com.liferay.tasks.web.portlet;

import com.liferay.asset.kernel.exception.AssetTagException;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
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
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.TasksEntryLocalService;
import com.liferay.tasks.service.TasksEntryLocalServiceUtil;
import com.liferay.tasks.service.TasksEntryServiceUtil;
import com.liferay.tasks.web.constants.TasksWebKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.Calendar;

/**
 * @author Neil Griffin
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=tasks-portlet",
		"com.liferay.portlet.display-category=category.collaboration",
		"com.liferay.portlet.footer-portlet-javascript=/tasks/js/main.js",
		"com.liferay.portlet.header-portlet-css=/tasks/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=TasksWeb",
		"javax.portlet.init-param.template-path=/tasks/",
		"javax.portlet.init-param.view-template=/tasks/view.jsp",
		"javax.portlet.name=" + TasksWebKeys.TASKS_PORTLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class TasksPortlet extends MVCPortlet {

	@Override
	protected void include(String path, PortletRequest portletRequest, PortletResponse portletResponse, String lifecycle) throws IOException, PortletException {

		portletRequest.setAttribute("tasksEntryLocalService", _tasksEntryLocalService);

		super.include(path, portletRequest, portletResponse, lifecycle);
	}

	public void deleteTasksEntry(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long tasksEntryId = ParamUtil.getLong(actionRequest, "tasksEntryId");

		TasksEntryServiceUtil.deleteTasksEntry(tasksEntryId);

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
				TasksWebKeys.TASKS_PORTLET);
		}

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
	}

	public void updateTasksEntry(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long tasksEntryId = ParamUtil.getLong(actionRequest, "tasksEntryId");

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
			TasksEntry.class.getName(), actionRequest);

		TasksEntry tasksEntry = null;

		try {
			if (tasksEntryId <= 0) {
				tasksEntry = TasksEntryServiceUtil.addTasksEntry(
					title, priority, assigneeUserId, dueDateMonth, dueDateDay,
					dueDateYear, dueDateHour, dueDateMinute, addDueDate,
					TasksWebKeys.TASKS_PORTLET, serviceContext);
			}
			else {
				tasksEntry = TasksEntryServiceUtil.updateTasksEntry(
					tasksEntryId, title, priority, assigneeUserId,
					resolverUserId, dueDateMonth, dueDateDay, dueDateYear,
					dueDateHour, dueDateMinute, addDueDate, status,
					TasksWebKeys.TASKS_PORTLET, serviceContext);
			}

			Layout layout = themeDisplay.getLayout();

			PortletURL portletURL = PortletURLFactoryUtil.create(
				actionRequest, TasksWebKeys.TASKS_PORTLET, layout.getPlid(),
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter("mvcPath", "/tasks/view_task.jsp");
			portletURL.setParameter(
				"tasksEntryId", String.valueOf(tasksEntry.getTasksEntryId()));
			portletURL.setWindowState(LiferayWindowState.POP_UP);

			actionResponse.sendRedirect(portletURL.toString());
		}
		catch (AssetTagException assetTagException) {
			actionResponse.setRenderParameter(
				"mvcPath", "/tasks/edit_task.jsp");

			actionResponse.setRenderParameters(actionRequest.getParameterMap());

			SessionErrors.add(
				actionRequest, assetTagException.getClass(), assetTagException);
		}
	}

	public void updateTasksEntryStatus(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long tasksEntryId = ParamUtil.getLong(actionRequest, "tasksEntryId");

		long resolverUserId = ParamUtil.getLong(
			actionRequest, "resolverUserId");
		int status = ParamUtil.getInteger(actionRequest, "status");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			TasksEntry.class.getName(), actionRequest);

		TasksEntryServiceUtil.updateTasksEntryStatus(
			tasksEntryId, resolverUserId, status, TasksWebKeys.TASKS_PORTLET, serviceContext);

		Layout layout = themeDisplay.getLayout();

		PortletURL portletURL = PortletURLFactoryUtil.create(
			actionRequest, TasksWebKeys.TASKS_PORTLET, layout.getPlid(),
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/tasks/view_task.jsp");
		portletURL.setParameter("tasksEntryId", String.valueOf(tasksEntryId));
		portletURL.setWindowState(LiferayWindowState.POP_UP);

		actionResponse.sendRedirect(portletURL.toString());
	}

	public void updateTasksEntryViewCount(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long tasksEntryId = ParamUtil.getLong(actionRequest, "tasksEntryId");

		TasksEntry tasksEntry = TasksEntryLocalServiceUtil.fetchTasksEntry(
			tasksEntryId);

		if (tasksEntry == null) {
			return;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		AssetEntryLocalServiceUtil.incrementViewCounter(
			themeDisplay.getUserId(),
			TasksEntry.class.getName(), tasksEntryId);
	}

	@Reference
	private TasksEntryLocalService _tasksEntryLocalService;
}