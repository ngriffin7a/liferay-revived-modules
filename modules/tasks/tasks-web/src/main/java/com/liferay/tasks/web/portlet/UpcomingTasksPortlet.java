package com.liferay.tasks.web.portlet;

import com.liferay.tasks.service.TasksEntryLocalService;
import com.liferay.tasks.web.constants.TasksWebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.Portlet;

/**
 * @author Neil Griffin
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=upcoming-tasks-portlet",
		"com.liferay.portlet.display-category=category.collaboration",
		"com.liferay.portlet.footer-portlet-javascript=/tasks/js/main.js",
		"com.liferay.portlet.header-portlet-css=/tasks/css/main.css",
		"javax.portlet.display-name=UpcomingTasksWeb",
		"javax.portlet.init-param.template-path=/upcoming_tasks/",
		"javax.portlet.init-param.view-template=/upcoming_tasks/view.jsp",
		"javax.portlet.name=" + TasksWebKeys.UPCOMING_TASKS_PORTLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class UpcomingTasksPortlet extends TasksPortlet {

	@Override
	protected TasksEntryLocalService getTasksEntryLocalService() {
		return _tasksEntryLocalService;
	}

	@Reference
	private TasksEntryLocalService _tasksEntryLocalService;
}