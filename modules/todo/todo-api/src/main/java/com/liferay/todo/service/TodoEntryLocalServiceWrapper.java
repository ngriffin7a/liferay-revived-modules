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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TodoEntryLocalService}.
 *
 * @author Ryan Park
 * @see TodoEntryLocalService
 * @generated
 */
public class TodoEntryLocalServiceWrapper
	implements ServiceWrapper<TodoEntryLocalService>, TodoEntryLocalService {

	public TodoEntryLocalServiceWrapper(
		TodoEntryLocalService todoEntryLocalService) {

		_todoEntryLocalService = todoEntryLocalService;
	}

	@Override
	public com.liferay.todo.model.TodoEntry addTodoEntry(
			long userId, String title, int priority, long assigneeUserId,
			int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
			int dueDateMinute, boolean addDueDate, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _todoEntryLocalService.addTodoEntry(
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
	@Override
	public com.liferay.todo.model.TodoEntry addTodoEntry(
		com.liferay.todo.model.TodoEntry todoEntry) {

		return _todoEntryLocalService.addTodoEntry(todoEntry);
	}

	/**
	 * Creates a new todo entry with the primary key. Does not add the todo entry to the database.
	 *
	 * @param todoEntryId the primary key for the new todo entry
	 * @return the new todo entry
	 */
	@Override
	public com.liferay.todo.model.TodoEntry createTodoEntry(long todoEntryId) {
		return _todoEntryLocalService.createTodoEntry(todoEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _todoEntryLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the todo entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param todoEntryId the primary key of the todo entry
	 * @return the todo entry that was removed
	 * @throws PortalException if a todo entry with the primary key could not be found
	 */
	@Override
	public com.liferay.todo.model.TodoEntry deleteTodoEntry(long todoEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _todoEntryLocalService.deleteTodoEntry(todoEntryId);
	}

	/**
	 * Deletes the todo entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param todoEntry the todo entry
	 * @return the todo entry that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.todo.model.TodoEntry deleteTodoEntry(
			com.liferay.todo.model.TodoEntry todoEntry)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _todoEntryLocalService.deleteTodoEntry(todoEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _todoEntryLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _todoEntryLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _todoEntryLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _todoEntryLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _todoEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _todoEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.todo.model.TodoEntry fetchTodoEntry(long todoEntryId) {
		return _todoEntryLocalService.fetchTodoEntry(todoEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _todoEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.todo.model.TodoEntry>
		getAssigneeTodoEntries(long assigneeUserId, int start, int end) {

		return _todoEntryLocalService.getAssigneeTodoEntries(
			assigneeUserId, start, end);
	}

	@Override
	public int getAssigneeTodoEntriesCount(long assigneeUserId) {
		return _todoEntryLocalService.getAssigneeTodoEntriesCount(
			assigneeUserId);
	}

	@Override
	public java.util.List<com.liferay.todo.model.TodoEntry>
		getGroupAssigneeTodoEntries(
			long groupId, long assigneeUserId, int start, int end) {

		return _todoEntryLocalService.getGroupAssigneeTodoEntries(
			groupId, assigneeUserId, start, end);
	}

	@Override
	public int getGroupAssigneeTodoEntriesCount(
		long groupId, long assigneeUserId) {

		return _todoEntryLocalService.getGroupAssigneeTodoEntriesCount(
			groupId, assigneeUserId);
	}

	@Override
	public java.util.List<com.liferay.todo.model.TodoEntry>
		getGroupResolverTodoEntries(
			long groupId, long resolverUserId, int start, int end) {

		return _todoEntryLocalService.getGroupResolverTodoEntries(
			groupId, resolverUserId, start, end);
	}

	@Override
	public int getGroupResolverTodoEntriesCount(
		long groupId, long resolverUserId) {

		return _todoEntryLocalService.getGroupResolverTodoEntriesCount(
			groupId, resolverUserId);
	}

	@Override
	public java.util.List<com.liferay.todo.model.TodoEntry>
		getGroupUserTodoEntries(long groupId, long userId, int start, int end) {

		return _todoEntryLocalService.getGroupUserTodoEntries(
			groupId, userId, start, end);
	}

	@Override
	public int getGroupUserTodoEntriesCount(long groupId, long userId) {
		return _todoEntryLocalService.getGroupUserTodoEntriesCount(
			groupId, userId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _todoEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _todoEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _todoEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.todo.model.TodoEntry>
		getResolverTodoEntries(long resolverUserId, int start, int end) {

		return _todoEntryLocalService.getResolverTodoEntries(
			resolverUserId, start, end);
	}

	@Override
	public int getResolverTodoEntriesCount(long resolverUserId) {
		return _todoEntryLocalService.getResolverTodoEntriesCount(
			resolverUserId);
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
	@Override
	public java.util.List<com.liferay.todo.model.TodoEntry> getTodoEntries(
		int start, int end) {

		return _todoEntryLocalService.getTodoEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.todo.model.TodoEntry> getTodoEntries(
		long groupId, int start, int end) {

		return _todoEntryLocalService.getTodoEntries(groupId, start, end);
	}

	@Override
	public java.util.List<com.liferay.todo.model.TodoEntry> getTodoEntries(
		long groupId, long userId, int priority, long assigneeUserId,
		int status, long[] assetTagIds, long[] notAssetTagIds, int start,
		int end) {

		return _todoEntryLocalService.getTodoEntries(
			groupId, userId, priority, assigneeUserId, status, assetTagIds,
			notAssetTagIds, start, end);
	}

	/**
	 * Returns the number of todo entries.
	 *
	 * @return the number of todo entries
	 */
	@Override
	public int getTodoEntriesCount() {
		return _todoEntryLocalService.getTodoEntriesCount();
	}

	@Override
	public int getTodoEntriesCount(long groupId) {
		return _todoEntryLocalService.getTodoEntriesCount(groupId);
	}

	@Override
	public int getTodoEntriesCount(
		long groupId, long userId, int priority, long assigneeUserId,
		int status, long[] tagsEntryIds, long[] notTagsEntryIds) {

		return _todoEntryLocalService.getTodoEntriesCount(
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
	@Override
	public com.liferay.todo.model.TodoEntry getTodoEntry(long todoEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _todoEntryLocalService.getTodoEntry(todoEntryId);
	}

	@Override
	public java.util.List<com.liferay.todo.model.TodoEntry> getUserTodoEntries(
		long userId, int start, int end) {

		return _todoEntryLocalService.getUserTodoEntries(userId, start, end);
	}

	@Override
	public int getUserTodoEntriesCount(long userId) {
		return _todoEntryLocalService.getUserTodoEntriesCount(userId);
	}

	@Override
	public void updateAsset(
			long userId, com.liferay.todo.model.TodoEntry todoEntry,
			long[] assetCategoryIds, String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException {

		_todoEntryLocalService.updateAsset(
			userId, todoEntry, assetCategoryIds, assetTagNames);
	}

	@Override
	public com.liferay.todo.model.TodoEntry updateTodoEntry(
			long todoEntryId, String title, int priority, long assigneeUserId,
			long resolverUserId, int dueDateMonth, int dueDateDay,
			int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean addDueDate, int status, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _todoEntryLocalService.updateTodoEntry(
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
	@Override
	public com.liferay.todo.model.TodoEntry updateTodoEntry(
		com.liferay.todo.model.TodoEntry todoEntry) {

		return _todoEntryLocalService.updateTodoEntry(todoEntry);
	}

	@Override
	public com.liferay.todo.model.TodoEntry updateTodoEntryStatus(
			long todoEntryId, long resolverUserId, int status, String portletId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _todoEntryLocalService.updateTodoEntryStatus(
			todoEntryId, resolverUserId, status, portletId, serviceContext);
	}

	@Override
	public TodoEntryLocalService getWrappedService() {
		return _todoEntryLocalService;
	}

	@Override
	public void setWrappedService(TodoEntryLocalService todoEntryLocalService) {
		_todoEntryLocalService = todoEntryLocalService;
	}

	private TodoEntryLocalService _todoEntryLocalService;

}