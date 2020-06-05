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

package com.liferay.todo.service.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.comment.CommentManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationManagerUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.todo.exception.TodoEntryDueDateException;
import com.liferay.todo.exception.TodoEntryTitleException;
import com.liferay.todo.model.TodoEntry;
import com.liferay.todo.model.TodoEntryConstants;
import com.liferay.todo.service.base.TodoEntryLocalServiceBaseImpl;

import com.liferay.todo.social.TodoActivityKeys;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * The implementation of the todo entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.todo.service.TodoEntryLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Ryan Park
 * @see TodoEntryLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.todo.model.TodoEntry",
	service = AopService.class
)
public class TodoEntryLocalServiceImpl extends TodoEntryLocalServiceBaseImpl {

	@Override
	public TodoEntry addTodoEntry(
		long userId, String title, int priority, long assigneeUserId,
		int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
		int dueDateMinute, boolean addDueDate, String portletId,
		ServiceContext serviceContext)
		throws PortalException {

		// Todo entry

		User user = userLocalService.getUserById(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		validate(title);

		Date dueDate = null;

		if (addDueDate) {
			dueDate = PortalUtil.getDate(
				dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
				dueDateMinute, user.getTimeZone(),
				TodoEntryDueDateException.class);
		}

		long todoEntryId = counterLocalService.increment();

		TodoEntry todoEntry = todoEntryPersistence.create(todoEntryId);

		todoEntry.setGroupId(groupId);
		todoEntry.setCompanyId(user.getCompanyId());
		todoEntry.setUserId(user.getUserId());
		todoEntry.setUserName(user.getFullName());
		todoEntry.setCreateDate(now);
		todoEntry.setModifiedDate(now);
		todoEntry.setTitle(title);
		todoEntry.setPriority(priority);
		todoEntry.setAssigneeUserId(assigneeUserId);
		todoEntry.setDueDate(dueDate);
		todoEntry.setStatus(TodoEntryConstants.STATUS_OPEN);

		todoEntry = todoEntryPersistence.update(todoEntry);

		// Resources

		resourceLocalService.addModelResources(todoEntry, serviceContext);

		// Asset

		updateAsset(
			userId, todoEntry, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		// Social

		JSONObject extraDataJSONObject = JSONUtil.put(
			"title", todoEntry.getTitle());

		socialActivityLocalService.addActivity(
			userId, groupId, TodoEntry.class.getName(), todoEntryId,
			TodoActivityKeys.ADD_ENTRY, extraDataJSONObject.toString(),
			assigneeUserId);

		// Notifications

		sendNotificationEvent(
			todoEntry, TodoEntryConstants.STATUS_ALL, assigneeUserId,
			portletId, serviceContext);

		return todoEntry;
	}

	@Override
	public TodoEntry deleteTodoEntry(long todoEntryId)
		throws PortalException {

		TodoEntry todoEntry = todoEntryPersistence.findByPrimaryKey(
			todoEntryId);

		return deleteTodoEntry(todoEntry);
	}

	@Override
	public TodoEntry deleteTodoEntry(TodoEntry todoEntry)
		throws PortalException {

		// Todo entry

		todoEntryPersistence.remove(todoEntry);

		// Asset

		assetEntryLocalService.deleteEntry(
			TodoEntry.class.getName(), todoEntry.getTodoEntryId());

		// Comment

		CommentManagerUtil.deleteDiscussion(
			TodoEntry.class.getName(), todoEntry.getTodoEntryId());

		// Social

		socialActivityLocalService.deleteActivities(
			TodoEntry.class.getName(), todoEntry.getTodoEntryId());

		return todoEntry;
	}

	@Override
	public List<TodoEntry> getAssigneeTodoEntries(
		long assigneeUserId, int start, int end) {

		return todoEntryPersistence.findByAssigneeUserId(
			assigneeUserId, start, end);
	}

	@Override
	public int getAssigneeTodoEntriesCount(long assigneeUserId) {
		return todoEntryPersistence.countByAssigneeUserId(assigneeUserId);
	}

	@Override
	public List<TodoEntry> getGroupAssigneeTodoEntries(
		long groupId, long assigneeUserId, int start, int end) {

		return todoEntryPersistence.findByG_A(
			groupId, assigneeUserId, start, end);
	}

	@Override
	public int getGroupAssigneeTodoEntriesCount(
		long groupId, long assigneeUserId) {

		return todoEntryPersistence.countByG_A(groupId, assigneeUserId);
	}

	@Override
	public List<TodoEntry> getGroupResolverTodoEntries(
		long groupId, long resolverUserId, int start, int end) {

		return todoEntryPersistence.findByG_R(
			groupId, resolverUserId, start, end);
	}

	@Override
	public int getGroupResolverTodoEntriesCount(
		long groupId, long resolverUserId) {

		return todoEntryPersistence.countByG_R(groupId, resolverUserId);
	}

	@Override
	public List<TodoEntry> getGroupUserTodoEntries(
		long groupId, long userId, int start, int end) {

		return todoEntryPersistence.findByG_U(groupId, userId, start, end);
	}

	@Override
	public int getGroupUserTodoEntriesCount(long groupId, long userId) {
		return todoEntryPersistence.countByG_U(groupId, userId);
	}

	@Override
	public List<TodoEntry> getResolverTodoEntries(
		long resolverUserId, int start, int end) {

		return todoEntryPersistence.findByResolverUserId(
			resolverUserId, start, end);
	}

	@Override
	public int getResolverTodoEntriesCount(long resolverUserId) {
		return todoEntryPersistence.countByResolverUserId(resolverUserId);
	}

	@Override
	public List<TodoEntry> getTodoEntries(long groupId, int start, int end) {
		return todoEntryPersistence.findByGroupId(groupId, start, end);
	}

	@Override
	public List<TodoEntry> getTodoEntries(
		long groupId, long userId, int priority, long assigneeUserId,
		int status, long[] assetTagIds, long[] notAssetTagIds, int start,
		int end) {

		return todoEntryFinder.findByG_U_P_A_S_T_N(
			groupId, userId, priority, assigneeUserId, status, assetTagIds,
			notAssetTagIds, start, end);
	}

	@Override
	public int getTodoEntriesCount(long groupId) {
		return todoEntryPersistence.countByGroupId(groupId);
	}

	@Override
	public int getTodoEntriesCount(
		long groupId, long userId, int priority, long assigneeUserId,
		int status, long[] tagsEntryIds, long[] notTagsEntryIds) {

		return todoEntryFinder.countByG_U_P_A_S_T_N(
			groupId, userId, priority, assigneeUserId, status, tagsEntryIds,
			notTagsEntryIds);
	}

	@Override
	public TodoEntry getTodoEntry(long todoEntryId) throws PortalException {
		return todoEntryPersistence.findByPrimaryKey(todoEntryId);
	}

	@Override
	public List<TodoEntry> getUserTodoEntries(
		long userId, int start, int end) {

		return todoEntryPersistence.findByUserId(userId, start, end);
	}

	@Override
	public int getUserTodoEntriesCount(long userId) {
		return todoEntryPersistence.countByUserId(userId);
	}

	@Override
	public void updateAsset(
		long userId, TodoEntry todoEntry, long[] assetCategoryIds,
		String[] assetTagNames)
		throws PortalException {

		assetEntryLocalService.updateEntry(
			userId, todoEntry.getGroupId(), TodoEntry.class.getName(),
			todoEntry.getTodoEntryId(), assetCategoryIds, assetTagNames);
	}

	@Override
	public TodoEntry updateTodoEntry(
		long todoEntryId, String title, int priority, long assigneeUserId,
		long resolverUserId, int dueDateMonth, int dueDateDay,
		int dueDateYear, int dueDateHour, int dueDateMinute,
		boolean addDueDate, int status, String portletId, ServiceContext serviceContext)
		throws PortalException {

		// Todo entry

		Date now = new Date();

		TodoEntry todoEntry = todoEntryPersistence.findByPrimaryKey(
			todoEntryId);

		validate(title);

		Date dueDate = null;

		if (addDueDate) {
			User user = userLocalService.getUserById(todoEntry.getUserId());

			dueDate = PortalUtil.getDate(
				dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
				dueDateMinute, user.getTimeZone(),
				TodoEntryDueDateException.class);
		}

		long oldAssigneeUserId = todoEntry.getAssigneeUserId();
		int oldStatus = todoEntry.getStatus();

		todoEntry.setModifiedDate(now);
		todoEntry.setTitle(title);
		todoEntry.setPriority(priority);
		todoEntry.setAssigneeUserId(assigneeUserId);
		todoEntry.setDueDate(dueDate);

		if (status == TodoEntryConstants.STATUS_RESOLVED) {
			todoEntry.setResolverUserId(resolverUserId);
			todoEntry.setFinishDate(now);
		}
		else {
			todoEntry.setResolverUserId(0);
			todoEntry.setFinishDate(null);
		}

		todoEntry.setStatus(status);

		todoEntry = todoEntryPersistence.update(todoEntry);

		// Asset

		updateAsset(
			todoEntry.getUserId(), todoEntry,
			serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		// Social

		addSocialActivity(status, todoEntry, serviceContext);

		// Notifications

		sendNotificationEvent(
			todoEntry, oldStatus, oldAssigneeUserId, portletId, serviceContext);

		return todoEntry;
	}

	@Override
	public TodoEntry updateTodoEntryStatus(
		long todoEntryId, long resolverUserId, int status,
		String portletId, ServiceContext serviceContext)
		throws PortalException {

		// Todo entry

		Date now = new Date();

		TodoEntry todoEntry = todoEntryPersistence.findByPrimaryKey(
			todoEntryId);

		todoEntry.setModifiedDate(now);

		if (status == TodoEntryConstants.STATUS_RESOLVED) {
			todoEntry.setResolverUserId(resolverUserId);
			todoEntry.setFinishDate(now);
		}
		else {
			todoEntry.setResolverUserId(0);
			todoEntry.setFinishDate(null);
		}

		int oldStatus = todoEntry.getStatus();

		todoEntry.setStatus(status);

		todoEntry = todoEntryPersistence.update(todoEntry);

		// Social

		addSocialActivity(status, todoEntry, serviceContext);

		// Notifications

		sendNotificationEvent(
			todoEntry, oldStatus, todoEntry.getAssigneeUserId(),
			portletId, serviceContext);

		return todoEntry;
	}

	protected void addSocialActivity(
		int status, TodoEntry todoEntry, ServiceContext serviceContext)
		throws PortalException {

		int activity = TodoActivityKeys.UPDATE_ENTRY;

		if (status == TodoEntryConstants.STATUS_REOPENED) {
			activity = TodoActivityKeys.REOPEN_ENTRY;
		}
		else if (status == TodoEntryConstants.STATUS_RESOLVED) {
			activity = TodoActivityKeys.RESOLVE_ENTRY;
		}

		JSONObject extraDataJSONObject = JSONUtil.put(
			"title", todoEntry.getTitle());

		socialActivityLocalService.addActivity(
			serviceContext.getUserId(), todoEntry.getGroupId(),
			TodoEntry.class.getName(), todoEntry.getTodoEntryId(), activity,
			extraDataJSONObject.toString(), todoEntry.getAssigneeUserId());
	}

	protected void sendNotificationEvent(
		TodoEntry todoEntry, int oldStatus, long oldAssigneeUserId,
		String portletId, ServiceContext serviceContext)
		throws PortalException {

		HashSet<Long> receiverUserIds = new HashSet<>();

		receiverUserIds.add(oldAssigneeUserId);
		receiverUserIds.add(todoEntry.getUserId());
		receiverUserIds.add(todoEntry.getAssigneeUserId());

		receiverUserIds.remove(serviceContext.getUserId());

		JSONObject notificationEventJSONObject = JSONUtil.put(
			"classPK", todoEntry.getTodoEntryId()
		).put(
			"userId", serviceContext.getUserId()
		);

		for (long receiverUserId : receiverUserIds) {
			if ((receiverUserId == 0) ||
				!UserNotificationManagerUtil.isDeliver(
					receiverUserId, portletId, 0,
					TodoEntryConstants.STATUS_ALL,
					UserNotificationDeliveryConstants.TYPE_WEBSITE)) {

				continue;
			}

			String title = StringPool.BLANK;

			if (oldStatus == TodoEntryConstants.STATUS_ALL) {
				title = "x-assigned-you-a-task";
			}
			else if (todoEntry.getAssigneeUserId() != oldAssigneeUserId) {
				if (receiverUserId == oldAssigneeUserId) {
					title = "x-reassigned-your-task";
				}
				else {
					title = "x-assigned-you-a-task";
				}
			}
			else if (todoEntry.getStatus() != oldStatus) {
				if ((todoEntry.getStatus() !=
					TodoEntryConstants.STATUS_OPEN) &&
					(todoEntry.getStatus() !=
						TodoEntryConstants.STATUS_REOPENED) &&
					(todoEntry.getStatus() !=
						TodoEntryConstants.STATUS_RESOLVED)) {

					return;
				}

				String statusLabel = TodoEntryConstants.getStatusLabel(
					todoEntry.getStatus());

				title = "x-" + statusLabel + "-the-task";
			}
			else {
				title = "x-modified-the-task";
			}

			notificationEventJSONObject.put("title", title);

			userNotificationEventLocalService.sendUserNotificationEvents(
				receiverUserId, portletId,
				UserNotificationDeliveryConstants.TYPE_WEBSITE,
				notificationEventJSONObject);
		}
	}

	protected void validate(String title) throws PortalException {
		if (Validator.isNull(title)) {
			throw new TodoEntryTitleException();
		}
	}
}