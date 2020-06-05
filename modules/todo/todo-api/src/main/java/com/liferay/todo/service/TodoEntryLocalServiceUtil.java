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

package com.liferay.todo.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for TodoEntry. This utility wraps
 * <code>com.liferay.todo.service.impl.TodoEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ryan Park
 * @see TodoEntryLocalService
 * @generated
 */
public class TodoEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.todo.service.impl.TodoEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.todo.model.TodoEntry addTodoEntry(
			long userId, String title, int priority, long assigneeUserId,
			int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
			int dueDateMinute, boolean addDueDate, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addTodoEntry(
			userId, title, priority, assigneeUserId, dueDateMonth, dueDateDay,
			dueDateYear, dueDateHour, dueDateMinute, addDueDate, portletId,
			serviceContext);
	}

	/**
	 * Adds the todo entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param todoEntry the todo entry
	 * @return the todo entry that was added
	 */
	public static com.liferay.todo.model.TodoEntry addTodoEntry(
		com.liferay.todo.model.TodoEntry todoEntry) {

		return getService().addTodoEntry(todoEntry);
	}

	/**
	 * Creates a new todo entry with the primary key. Does not add the todo entry to the database.
	 *
	 * @param todoEntryId the primary key for the new todo entry
	 * @return the new todo entry
	 */
	public static com.liferay.todo.model.TodoEntry createTodoEntry(
		long todoEntryId) {

		return getService().createTodoEntry(todoEntryId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the todo entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param todoEntryId the primary key of the todo entry
	 * @return the todo entry that was removed
	 * @throws PortalException if a todo entry with the primary key could not be found
	 */
	public static com.liferay.todo.model.TodoEntry deleteTodoEntry(
			long todoEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTodoEntry(todoEntryId);
	}

	/**
	 * Deletes the todo entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param todoEntry the todo entry
	 * @return the todo entry that was removed
	 * @throws PortalException
	 */
	public static com.liferay.todo.model.TodoEntry deleteTodoEntry(
			com.liferay.todo.model.TodoEntry todoEntry)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTodoEntry(todoEntry);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.todo.model.impl.TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.todo.model.impl.TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.todo.model.TodoEntry fetchTodoEntry(
		long todoEntryId) {

		return getService().fetchTodoEntry(todoEntryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<com.liferay.todo.model.TodoEntry>
		getAssigneeTodoEntries(long assigneeUserId, int start, int end) {

		return getService().getAssigneeTodoEntries(assigneeUserId, start, end);
	}

	public static int getAssigneeTodoEntriesCount(long assigneeUserId) {
		return getService().getAssigneeTodoEntriesCount(assigneeUserId);
	}

	public static java.util.List<com.liferay.todo.model.TodoEntry>
		getGroupAssigneeTodoEntries(
			long groupId, long assigneeUserId, int start, int end) {

		return getService().getGroupAssigneeTodoEntries(
			groupId, assigneeUserId, start, end);
	}

	public static int getGroupAssigneeTodoEntriesCount(
		long groupId, long assigneeUserId) {

		return getService().getGroupAssigneeTodoEntriesCount(
			groupId, assigneeUserId);
	}

	public static java.util.List<com.liferay.todo.model.TodoEntry>
		getGroupResolverTodoEntries(
			long groupId, long resolverUserId, int start, int end) {

		return getService().getGroupResolverTodoEntries(
			groupId, resolverUserId, start, end);
	}

	public static int getGroupResolverTodoEntriesCount(
		long groupId, long resolverUserId) {

		return getService().getGroupResolverTodoEntriesCount(
			groupId, resolverUserId);
	}

	public static java.util.List<com.liferay.todo.model.TodoEntry>
		getGroupUserTodoEntries(long groupId, long userId, int start, int end) {

		return getService().getGroupUserTodoEntries(
			groupId, userId, start, end);
	}

	public static int getGroupUserTodoEntriesCount(long groupId, long userId) {
		return getService().getGroupUserTodoEntriesCount(groupId, userId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.util.List<com.liferay.todo.model.TodoEntry>
		getResolverTodoEntries(long resolverUserId, int start, int end) {

		return getService().getResolverTodoEntries(resolverUserId, start, end);
	}

	public static int getResolverTodoEntriesCount(long resolverUserId) {
		return getService().getResolverTodoEntriesCount(resolverUserId);
	}

	/**
	 * Returns a range of all the todo entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.todo.model.impl.TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of todo entries
	 */
	public static java.util.List<com.liferay.todo.model.TodoEntry>
		getTodoEntries(int start, int end) {

		return getService().getTodoEntries(start, end);
	}

	public static java.util.List<com.liferay.todo.model.TodoEntry>
		getTodoEntries(long groupId, int start, int end) {

		return getService().getTodoEntries(groupId, start, end);
	}

	public static java.util.List<com.liferay.todo.model.TodoEntry>
		getTodoEntries(
			long groupId, long userId, int priority, long assigneeUserId,
			int status, long[] assetTagIds, long[] notAssetTagIds, int start,
			int end) {

		return getService().getTodoEntries(
			groupId, userId, priority, assigneeUserId, status, assetTagIds,
			notAssetTagIds, start, end);
	}

	/**
	 * Returns the number of todo entries.
	 *
	 * @return the number of todo entries
	 */
	public static int getTodoEntriesCount() {
		return getService().getTodoEntriesCount();
	}

	public static int getTodoEntriesCount(long groupId) {
		return getService().getTodoEntriesCount(groupId);
	}

	public static int getTodoEntriesCount(
		long groupId, long userId, int priority, long assigneeUserId,
		int status, long[] tagsEntryIds, long[] notTagsEntryIds) {

		return getService().getTodoEntriesCount(
			groupId, userId, priority, assigneeUserId, status, tagsEntryIds,
			notTagsEntryIds);
	}

	/**
	 * Returns the todo entry with the primary key.
	 *
	 * @param todoEntryId the primary key of the todo entry
	 * @return the todo entry
	 * @throws PortalException if a todo entry with the primary key could not be found
	 */
	public static com.liferay.todo.model.TodoEntry getTodoEntry(
			long todoEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTodoEntry(todoEntryId);
	}

	public static java.util.List<com.liferay.todo.model.TodoEntry>
		getUserTodoEntries(long userId, int start, int end) {

		return getService().getUserTodoEntries(userId, start, end);
	}

	public static int getUserTodoEntriesCount(long userId) {
		return getService().getUserTodoEntriesCount(userId);
	}

	public static void updateAsset(
			long userId, com.liferay.todo.model.TodoEntry todoEntry,
			long[] assetCategoryIds, String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateAsset(
			userId, todoEntry, assetCategoryIds, assetTagNames);
	}

	public static com.liferay.todo.model.TodoEntry updateTodoEntry(
			long todoEntryId, String title, int priority, long assigneeUserId,
			long resolverUserId, int dueDateMonth, int dueDateDay,
			int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean addDueDate, int status, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateTodoEntry(
			todoEntryId, title, priority, assigneeUserId, resolverUserId,
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			addDueDate, status, portletId, serviceContext);
	}

	/**
	 * Updates the todo entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param todoEntry the todo entry
	 * @return the todo entry that was updated
	 */
	public static com.liferay.todo.model.TodoEntry updateTodoEntry(
		com.liferay.todo.model.TodoEntry todoEntry) {

		return getService().updateTodoEntry(todoEntry);
	}

	public static com.liferay.todo.model.TodoEntry updateTodoEntryStatus(
			long todoEntryId, long resolverUserId, int status, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateTodoEntryStatus(
			todoEntryId, resolverUserId, status, portletId, serviceContext);
	}

	public static TodoEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TodoEntryLocalService, TodoEntryLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TodoEntryLocalService.class);

		ServiceTracker<TodoEntryLocalService, TodoEntryLocalService>
			serviceTracker =
				new ServiceTracker
					<TodoEntryLocalService, TodoEntryLocalService>(
						bundle.getBundleContext(), TodoEntryLocalService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}