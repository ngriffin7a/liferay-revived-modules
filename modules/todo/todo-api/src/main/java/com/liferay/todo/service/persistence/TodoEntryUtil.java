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

package com.liferay.todo.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.todo.model.TodoEntry;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the todo entry service. This utility wraps <code>com.liferay.todo.service.persistence.impl.TodoEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @see TodoEntryPersistence
 * @generated
 */
public class TodoEntryUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(TodoEntry todoEntry) {
		getPersistence().clearCache(todoEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, TodoEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TodoEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TodoEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TodoEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TodoEntry update(TodoEntry todoEntry) {
		return getPersistence().update(todoEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TodoEntry update(
		TodoEntry todoEntry, ServiceContext serviceContext) {

		return getPersistence().update(todoEntry, serviceContext);
	}

	/**
	 * Returns all the todo entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching todo entries
	 */
	public static List<TodoEntry> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the todo entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries
	 */
	public static List<TodoEntry> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the todo entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByGroupId_First(
			long groupId, OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByGroupId_First(
		long groupId, OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByGroupId_Last(
			long groupId, OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByGroupId_Last(
		long groupId, OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set where groupId = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry[] findByGroupId_PrevAndNext(
			long todoEntryId, long groupId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByGroupId_PrevAndNext(
			todoEntryId, groupId, orderByComparator);
	}

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	 * Returns a range of all the todo entries that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByGroupId(
		long groupId, int start, int end) {

		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByGroupId(
		long groupId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().filterFindByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set of todo entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry[] filterFindByGroupId_PrevAndNext(
			long todoEntryId, long groupId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().filterFindByGroupId_PrevAndNext(
			todoEntryId, groupId, orderByComparator);
	}

	/**
	 * Removes all the todo entries where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of todo entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching todo entries
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching todo entries that the user has permission to view
	 */
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	 * Returns all the todo entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching todo entries
	 */
	public static List<TodoEntry> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	 * Returns a range of all the todo entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries
	 */
	public static List<TodoEntry> findByUserId(
		long userId, int start, int end) {

		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByUserId(
		long userId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the todo entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByUserId(
		long userId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first todo entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByUserId_First(
			long userId, OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first todo entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByUserId_First(
		long userId, OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByUserId_Last(
			long userId, OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByUserId_Last(
		long userId, OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set where userId = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry[] findByUserId_PrevAndNext(
			long todoEntryId, long userId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByUserId_PrevAndNext(
			todoEntryId, userId, orderByComparator);
	}

	/**
	 * Removes all the todo entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	 * Returns the number of todo entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching todo entries
	 */
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	 * Returns all the todo entries where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @return the matching todo entries
	 */
	public static List<TodoEntry> findByAssigneeUserId(long assigneeUserId) {
		return getPersistence().findByAssigneeUserId(assigneeUserId);
	}

	/**
	 * Returns a range of all the todo entries where assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries
	 */
	public static List<TodoEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end) {

		return getPersistence().findByAssigneeUserId(
			assigneeUserId, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries where assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().findByAssigneeUserId(
			assigneeUserId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the todo entries where assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAssigneeUserId(
			assigneeUserId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first todo entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByAssigneeUserId_First(
			long assigneeUserId, OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByAssigneeUserId_First(
			assigneeUserId, orderByComparator);
	}

	/**
	 * Returns the first todo entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByAssigneeUserId_First(
		long assigneeUserId, OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByAssigneeUserId_First(
			assigneeUserId, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByAssigneeUserId_Last(
			long assigneeUserId, OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByAssigneeUserId_Last(
			assigneeUserId, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByAssigneeUserId_Last(
		long assigneeUserId, OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByAssigneeUserId_Last(
			assigneeUserId, orderByComparator);
	}

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry[] findByAssigneeUserId_PrevAndNext(
			long todoEntryId, long assigneeUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByAssigneeUserId_PrevAndNext(
			todoEntryId, assigneeUserId, orderByComparator);
	}

	/**
	 * Removes all the todo entries where assigneeUserId = &#63; from the database.
	 *
	 * @param assigneeUserId the assignee user ID
	 */
	public static void removeByAssigneeUserId(long assigneeUserId) {
		getPersistence().removeByAssigneeUserId(assigneeUserId);
	}

	/**
	 * Returns the number of todo entries where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @return the number of matching todo entries
	 */
	public static int countByAssigneeUserId(long assigneeUserId) {
		return getPersistence().countByAssigneeUserId(assigneeUserId);
	}

	/**
	 * Returns all the todo entries where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @return the matching todo entries
	 */
	public static List<TodoEntry> findByResolverUserId(long resolverUserId) {
		return getPersistence().findByResolverUserId(resolverUserId);
	}

	/**
	 * Returns a range of all the todo entries where resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries
	 */
	public static List<TodoEntry> findByResolverUserId(
		long resolverUserId, int start, int end) {

		return getPersistence().findByResolverUserId(
			resolverUserId, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries where resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByResolverUserId(
		long resolverUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().findByResolverUserId(
			resolverUserId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the todo entries where resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByResolverUserId(
		long resolverUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByResolverUserId(
			resolverUserId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first todo entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByResolverUserId_First(
			long resolverUserId, OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByResolverUserId_First(
			resolverUserId, orderByComparator);
	}

	/**
	 * Returns the first todo entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByResolverUserId_First(
		long resolverUserId, OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByResolverUserId_First(
			resolverUserId, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByResolverUserId_Last(
			long resolverUserId, OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByResolverUserId_Last(
			resolverUserId, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByResolverUserId_Last(
		long resolverUserId, OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByResolverUserId_Last(
			resolverUserId, orderByComparator);
	}

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry[] findByResolverUserId_PrevAndNext(
			long todoEntryId, long resolverUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByResolverUserId_PrevAndNext(
			todoEntryId, resolverUserId, orderByComparator);
	}

	/**
	 * Removes all the todo entries where resolverUserId = &#63; from the database.
	 *
	 * @param resolverUserId the resolver user ID
	 */
	public static void removeByResolverUserId(long resolverUserId) {
		getPersistence().removeByResolverUserId(resolverUserId);
	}

	/**
	 * Returns the number of todo entries where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @return the number of matching todo entries
	 */
	public static int countByResolverUserId(long resolverUserId) {
		return getPersistence().countByResolverUserId(resolverUserId);
	}

	/**
	 * Returns all the todo entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching todo entries
	 */
	public static List<TodoEntry> findByG_U(long groupId, long userId) {
		return getPersistence().findByG_U(groupId, userId);
	}

	/**
	 * Returns a range of all the todo entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries
	 */
	public static List<TodoEntry> findByG_U(
		long groupId, long userId, int start, int end) {

		return getPersistence().findByG_U(groupId, userId, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByG_U(
		long groupId, long userId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().findByG_U(
			groupId, userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the todo entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByG_U(
		long groupId, long userId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_U(
			groupId, userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByG_U_First(
			long groupId, long userId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByG_U_First(
			groupId, userId, orderByComparator);
	}

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByG_U_First(
		long groupId, long userId,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByG_U_First(
			groupId, userId, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByG_U_Last(
			long groupId, long userId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByG_U_Last(
			groupId, userId, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByG_U_Last(
		long groupId, long userId,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByG_U_Last(
			groupId, userId, orderByComparator);
	}

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry[] findByG_U_PrevAndNext(
			long todoEntryId, long groupId, long userId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByG_U_PrevAndNext(
			todoEntryId, groupId, userId, orderByComparator);
	}

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_U(long groupId, long userId) {
		return getPersistence().filterFindByG_U(groupId, userId);
	}

	/**
	 * Returns a range of all the todo entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_U(
		long groupId, long userId, int start, int end) {

		return getPersistence().filterFindByG_U(groupId, userId, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries that the user has permissions to view where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_U(
		long groupId, long userId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().filterFindByG_U(
			groupId, userId, start, end, orderByComparator);
	}

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set of todo entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry[] filterFindByG_U_PrevAndNext(
			long todoEntryId, long groupId, long userId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().filterFindByG_U_PrevAndNext(
			todoEntryId, groupId, userId, orderByComparator);
	}

	/**
	 * Removes all the todo entries where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 */
	public static void removeByG_U(long groupId, long userId) {
		getPersistence().removeByG_U(groupId, userId);
	}

	/**
	 * Returns the number of todo entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching todo entries
	 */
	public static int countByG_U(long groupId, long userId) {
		return getPersistence().countByG_U(groupId, userId);
	}

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching todo entries that the user has permission to view
	 */
	public static int filterCountByG_U(long groupId, long userId) {
		return getPersistence().filterCountByG_U(groupId, userId);
	}

	/**
	 * Returns all the todo entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the matching todo entries
	 */
	public static List<TodoEntry> findByG_A(long groupId, long assigneeUserId) {
		return getPersistence().findByG_A(groupId, assigneeUserId);
	}

	/**
	 * Returns a range of all the todo entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries
	 */
	public static List<TodoEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end) {

		return getPersistence().findByG_A(groupId, assigneeUserId, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().findByG_A(
			groupId, assigneeUserId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the todo entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_A(
			groupId, assigneeUserId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByG_A_First(
			long groupId, long assigneeUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByG_A_First(
			groupId, assigneeUserId, orderByComparator);
	}

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByG_A_First(
		long groupId, long assigneeUserId,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByG_A_First(
			groupId, assigneeUserId, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByG_A_Last(
			long groupId, long assigneeUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByG_A_Last(
			groupId, assigneeUserId, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByG_A_Last(
		long groupId, long assigneeUserId,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByG_A_Last(
			groupId, assigneeUserId, orderByComparator);
	}

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry[] findByG_A_PrevAndNext(
			long todoEntryId, long groupId, long assigneeUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByG_A_PrevAndNext(
			todoEntryId, groupId, assigneeUserId, orderByComparator);
	}

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_A(
		long groupId, long assigneeUserId) {

		return getPersistence().filterFindByG_A(groupId, assigneeUserId);
	}

	/**
	 * Returns a range of all the todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_A(
		long groupId, long assigneeUserId, int start, int end) {

		return getPersistence().filterFindByG_A(
			groupId, assigneeUserId, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries that the user has permissions to view where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_A(
		long groupId, long assigneeUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().filterFindByG_A(
			groupId, assigneeUserId, start, end, orderByComparator);
	}

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set of todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry[] filterFindByG_A_PrevAndNext(
			long todoEntryId, long groupId, long assigneeUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().filterFindByG_A_PrevAndNext(
			todoEntryId, groupId, assigneeUserId, orderByComparator);
	}

	/**
	 * Removes all the todo entries where groupId = &#63; and assigneeUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 */
	public static void removeByG_A(long groupId, long assigneeUserId) {
		getPersistence().removeByG_A(groupId, assigneeUserId);
	}

	/**
	 * Returns the number of todo entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the number of matching todo entries
	 */
	public static int countByG_A(long groupId, long assigneeUserId) {
		return getPersistence().countByG_A(groupId, assigneeUserId);
	}

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the number of matching todo entries that the user has permission to view
	 */
	public static int filterCountByG_A(long groupId, long assigneeUserId) {
		return getPersistence().filterCountByG_A(groupId, assigneeUserId);
	}

	/**
	 * Returns all the todo entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the matching todo entries
	 */
	public static List<TodoEntry> findByG_R(long groupId, long resolverUserId) {
		return getPersistence().findByG_R(groupId, resolverUserId);
	}

	/**
	 * Returns a range of all the todo entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries
	 */
	public static List<TodoEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end) {

		return getPersistence().findByG_R(groupId, resolverUserId, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().findByG_R(
			groupId, resolverUserId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the todo entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_R(
			groupId, resolverUserId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByG_R_First(
			long groupId, long resolverUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByG_R_First(
			groupId, resolverUserId, orderByComparator);
	}

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByG_R_First(
		long groupId, long resolverUserId,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByG_R_First(
			groupId, resolverUserId, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByG_R_Last(
			long groupId, long resolverUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByG_R_Last(
			groupId, resolverUserId, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByG_R_Last(
		long groupId, long resolverUserId,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByG_R_Last(
			groupId, resolverUserId, orderByComparator);
	}

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry[] findByG_R_PrevAndNext(
			long todoEntryId, long groupId, long resolverUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByG_R_PrevAndNext(
			todoEntryId, groupId, resolverUserId, orderByComparator);
	}

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_R(
		long groupId, long resolverUserId) {

		return getPersistence().filterFindByG_R(groupId, resolverUserId);
	}

	/**
	 * Returns a range of all the todo entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_R(
		long groupId, long resolverUserId, int start, int end) {

		return getPersistence().filterFindByG_R(
			groupId, resolverUserId, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries that the user has permissions to view where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_R(
		long groupId, long resolverUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().filterFindByG_R(
			groupId, resolverUserId, start, end, orderByComparator);
	}

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set of todo entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry[] filterFindByG_R_PrevAndNext(
			long todoEntryId, long groupId, long resolverUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().filterFindByG_R_PrevAndNext(
			todoEntryId, groupId, resolverUserId, orderByComparator);
	}

	/**
	 * Removes all the todo entries where groupId = &#63; and resolverUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 */
	public static void removeByG_R(long groupId, long resolverUserId) {
		getPersistence().removeByG_R(groupId, resolverUserId);
	}

	/**
	 * Returns the number of todo entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the number of matching todo entries
	 */
	public static int countByG_R(long groupId, long resolverUserId) {
		return getPersistence().countByG_R(groupId, resolverUserId);
	}

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the number of matching todo entries that the user has permission to view
	 */
	public static int filterCountByG_R(long groupId, long resolverUserId) {
		return getPersistence().filterCountByG_R(groupId, resolverUserId);
	}

	/**
	 * Returns all the todo entries where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching todo entries
	 */
	public static List<TodoEntry> findByU_S(long userId, int status) {
		return getPersistence().findByU_S(userId, status);
	}

	/**
	 * Returns a range of all the todo entries where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries
	 */
	public static List<TodoEntry> findByU_S(
		long userId, int status, int start, int end) {

		return getPersistence().findByU_S(userId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByU_S(
		long userId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().findByU_S(
			userId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the todo entries where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByU_S(
		long userId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByU_S(
			userId, status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first todo entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByU_S_First(
			long userId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByU_S_First(
			userId, status, orderByComparator);
	}

	/**
	 * Returns the first todo entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByU_S_First(
		long userId, int status,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByU_S_First(
			userId, status, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByU_S_Last(
			long userId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByU_S_Last(
			userId, status, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByU_S_Last(
		long userId, int status,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByU_S_Last(
			userId, status, orderByComparator);
	}

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry[] findByU_S_PrevAndNext(
			long todoEntryId, long userId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByU_S_PrevAndNext(
			todoEntryId, userId, status, orderByComparator);
	}

	/**
	 * Returns all the todo entries where userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the matching todo entries
	 */
	public static List<TodoEntry> findByU_S(long userId, int[] statuses) {
		return getPersistence().findByU_S(userId, statuses);
	}

	/**
	 * Returns a range of all the todo entries where userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries
	 */
	public static List<TodoEntry> findByU_S(
		long userId, int[] statuses, int start, int end) {

		return getPersistence().findByU_S(userId, statuses, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries where userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByU_S(
		long userId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().findByU_S(
			userId, statuses, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the todo entries where userId = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByU_S(
		long userId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByU_S(
			userId, statuses, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the todo entries where userId = &#63; and status = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param status the status
	 */
	public static void removeByU_S(long userId, int status) {
		getPersistence().removeByU_S(userId, status);
	}

	/**
	 * Returns the number of todo entries where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching todo entries
	 */
	public static int countByU_S(long userId, int status) {
		return getPersistence().countByU_S(userId, status);
	}

	/**
	 * Returns the number of todo entries where userId = &#63; and status = any &#63;.
	 *
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries
	 */
	public static int countByU_S(long userId, int[] statuses) {
		return getPersistence().countByU_S(userId, statuses);
	}

	/**
	 * Returns all the todo entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the matching todo entries
	 */
	public static List<TodoEntry> findByA_S(long assigneeUserId, int status) {
		return getPersistence().findByA_S(assigneeUserId, status);
	}

	/**
	 * Returns a range of all the todo entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries
	 */
	public static List<TodoEntry> findByA_S(
		long assigneeUserId, int status, int start, int end) {

		return getPersistence().findByA_S(assigneeUserId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByA_S(
		long assigneeUserId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().findByA_S(
			assigneeUserId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the todo entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByA_S(
		long assigneeUserId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByA_S(
			assigneeUserId, status, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first todo entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByA_S_First(
			long assigneeUserId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByA_S_First(
			assigneeUserId, status, orderByComparator);
	}

	/**
	 * Returns the first todo entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByA_S_First(
		long assigneeUserId, int status,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByA_S_First(
			assigneeUserId, status, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByA_S_Last(
			long assigneeUserId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByA_S_Last(
			assigneeUserId, status, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByA_S_Last(
		long assigneeUserId, int status,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByA_S_Last(
			assigneeUserId, status, orderByComparator);
	}

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry[] findByA_S_PrevAndNext(
			long todoEntryId, long assigneeUserId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByA_S_PrevAndNext(
			todoEntryId, assigneeUserId, status, orderByComparator);
	}

	/**
	 * Returns all the todo entries where assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the matching todo entries
	 */
	public static List<TodoEntry> findByA_S(
		long assigneeUserId, int[] statuses) {

		return getPersistence().findByA_S(assigneeUserId, statuses);
	}

	/**
	 * Returns a range of all the todo entries where assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries
	 */
	public static List<TodoEntry> findByA_S(
		long assigneeUserId, int[] statuses, int start, int end) {

		return getPersistence().findByA_S(assigneeUserId, statuses, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries where assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByA_S(
		long assigneeUserId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().findByA_S(
			assigneeUserId, statuses, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the todo entries where assigneeUserId = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByA_S(
		long assigneeUserId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByA_S(
			assigneeUserId, statuses, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Removes all the todo entries where assigneeUserId = &#63; and status = &#63; from the database.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 */
	public static void removeByA_S(long assigneeUserId, int status) {
		getPersistence().removeByA_S(assigneeUserId, status);
	}

	/**
	 * Returns the number of todo entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the number of matching todo entries
	 */
	public static int countByA_S(long assigneeUserId, int status) {
		return getPersistence().countByA_S(assigneeUserId, status);
	}

	/**
	 * Returns the number of todo entries where assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries
	 */
	public static int countByA_S(long assigneeUserId, int[] statuses) {
		return getPersistence().countByA_S(assigneeUserId, statuses);
	}

	/**
	 * Returns all the todo entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching todo entries
	 */
	public static List<TodoEntry> findByG_U_S(
		long groupId, long userId, int status) {

		return getPersistence().findByG_U_S(groupId, userId, status);
	}

	/**
	 * Returns a range of all the todo entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries
	 */
	public static List<TodoEntry> findByG_U_S(
		long groupId, long userId, int status, int start, int end) {

		return getPersistence().findByG_U_S(
			groupId, userId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByG_U_S(
		long groupId, long userId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().findByG_U_S(
			groupId, userId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the todo entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByG_U_S(
		long groupId, long userId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_U_S(
			groupId, userId, status, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByG_U_S_First(
			long groupId, long userId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByG_U_S_First(
			groupId, userId, status, orderByComparator);
	}

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByG_U_S_First(
		long groupId, long userId, int status,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByG_U_S_First(
			groupId, userId, status, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByG_U_S_Last(
			long groupId, long userId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByG_U_S_Last(
			groupId, userId, status, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByG_U_S_Last(
		long groupId, long userId, int status,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByG_U_S_Last(
			groupId, userId, status, orderByComparator);
	}

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry[] findByG_U_S_PrevAndNext(
			long todoEntryId, long groupId, long userId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByG_U_S_PrevAndNext(
			todoEntryId, groupId, userId, status, orderByComparator);
	}

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int status) {

		return getPersistence().filterFindByG_U_S(groupId, userId, status);
	}

	/**
	 * Returns a range of all the todo entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int status, int start, int end) {

		return getPersistence().filterFindByG_U_S(
			groupId, userId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries that the user has permissions to view where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().filterFindByG_U_S(
			groupId, userId, status, start, end, orderByComparator);
	}

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set of todo entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry[] filterFindByG_U_S_PrevAndNext(
			long todoEntryId, long groupId, long userId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().filterFindByG_U_S_PrevAndNext(
			todoEntryId, groupId, userId, status, orderByComparator);
	}

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int[] statuses) {

		return getPersistence().filterFindByG_U_S(groupId, userId, statuses);
	}

	/**
	 * Returns a range of all the todo entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end) {

		return getPersistence().filterFindByG_U_S(
			groupId, userId, statuses, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().filterFindByG_U_S(
			groupId, userId, statuses, start, end, orderByComparator);
	}

	/**
	 * Returns all the todo entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the matching todo entries
	 */
	public static List<TodoEntry> findByG_U_S(
		long groupId, long userId, int[] statuses) {

		return getPersistence().findByG_U_S(groupId, userId, statuses);
	}

	/**
	 * Returns a range of all the todo entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries
	 */
	public static List<TodoEntry> findByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end) {

		return getPersistence().findByG_U_S(
			groupId, userId, statuses, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().findByG_U_S(
			groupId, userId, statuses, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the todo entries where groupId = &#63; and userId = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_U_S(
			groupId, userId, statuses, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Removes all the todo entries where groupId = &#63; and userId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 */
	public static void removeByG_U_S(long groupId, long userId, int status) {
		getPersistence().removeByG_U_S(groupId, userId, status);
	}

	/**
	 * Returns the number of todo entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching todo entries
	 */
	public static int countByG_U_S(long groupId, long userId, int status) {
		return getPersistence().countByG_U_S(groupId, userId, status);
	}

	/**
	 * Returns the number of todo entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries
	 */
	public static int countByG_U_S(long groupId, long userId, int[] statuses) {
		return getPersistence().countByG_U_S(groupId, userId, statuses);
	}

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching todo entries that the user has permission to view
	 */
	public static int filterCountByG_U_S(
		long groupId, long userId, int status) {

		return getPersistence().filterCountByG_U_S(groupId, userId, status);
	}

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries that the user has permission to view
	 */
	public static int filterCountByG_U_S(
		long groupId, long userId, int[] statuses) {

		return getPersistence().filterCountByG_U_S(groupId, userId, statuses);
	}

	/**
	 * Returns all the todo entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the matching todo entries
	 */
	public static List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status) {

		return getPersistence().findByG_A_S(groupId, assigneeUserId, status);
	}

	/**
	 * Returns a range of all the todo entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries
	 */
	public static List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end) {

		return getPersistence().findByG_A_S(
			groupId, assigneeUserId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().findByG_A_S(
			groupId, assigneeUserId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the todo entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_A_S(
			groupId, assigneeUserId, status, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByG_A_S_First(
			long groupId, long assigneeUserId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByG_A_S_First(
			groupId, assigneeUserId, status, orderByComparator);
	}

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByG_A_S_First(
		long groupId, long assigneeUserId, int status,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByG_A_S_First(
			groupId, assigneeUserId, status, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public static TodoEntry findByG_A_S_Last(
			long groupId, long assigneeUserId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByG_A_S_Last(
			groupId, assigneeUserId, status, orderByComparator);
	}

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public static TodoEntry fetchByG_A_S_Last(
		long groupId, long assigneeUserId, int status,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().fetchByG_A_S_Last(
			groupId, assigneeUserId, status, orderByComparator);
	}

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry[] findByG_A_S_PrevAndNext(
			long todoEntryId, long groupId, long assigneeUserId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByG_A_S_PrevAndNext(
			todoEntryId, groupId, assigneeUserId, status, orderByComparator);
	}

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int status) {

		return getPersistence().filterFindByG_A_S(
			groupId, assigneeUserId, status);
	}

	/**
	 * Returns a range of all the todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end) {

		return getPersistence().filterFindByG_A_S(
			groupId, assigneeUserId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries that the user has permissions to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().filterFindByG_A_S(
			groupId, assigneeUserId, status, start, end, orderByComparator);
	}

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set of todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry[] filterFindByG_A_S_PrevAndNext(
			long todoEntryId, long groupId, long assigneeUserId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().filterFindByG_A_S_PrevAndNext(
			todoEntryId, groupId, assigneeUserId, status, orderByComparator);
	}

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int[] statuses) {

		return getPersistence().filterFindByG_A_S(
			groupId, assigneeUserId, statuses);
	}

	/**
	 * Returns a range of all the todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end) {

		return getPersistence().filterFindByG_A_S(
			groupId, assigneeUserId, statuses, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries that the user has permission to view
	 */
	public static List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().filterFindByG_A_S(
			groupId, assigneeUserId, statuses, start, end, orderByComparator);
	}

	/**
	 * Returns all the todo entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the matching todo entries
	 */
	public static List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int[] statuses) {

		return getPersistence().findByG_A_S(groupId, assigneeUserId, statuses);
	}

	/**
	 * Returns a range of all the todo entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of matching todo entries
	 */
	public static List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end) {

		return getPersistence().findByG_A_S(
			groupId, assigneeUserId, statuses, start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().findByG_A_S(
			groupId, assigneeUserId, statuses, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the todo entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching todo entries
	 */
	public static List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_A_S(
			groupId, assigneeUserId, statuses, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Removes all the todo entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 */
	public static void removeByG_A_S(
		long groupId, long assigneeUserId, int status) {

		getPersistence().removeByG_A_S(groupId, assigneeUserId, status);
	}

	/**
	 * Returns the number of todo entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the number of matching todo entries
	 */
	public static int countByG_A_S(
		long groupId, long assigneeUserId, int status) {

		return getPersistence().countByG_A_S(groupId, assigneeUserId, status);
	}

	/**
	 * Returns the number of todo entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries
	 */
	public static int countByG_A_S(
		long groupId, long assigneeUserId, int[] statuses) {

		return getPersistence().countByG_A_S(groupId, assigneeUserId, statuses);
	}

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the number of matching todo entries that the user has permission to view
	 */
	public static int filterCountByG_A_S(
		long groupId, long assigneeUserId, int status) {

		return getPersistence().filterCountByG_A_S(
			groupId, assigneeUserId, status);
	}

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries that the user has permission to view
	 */
	public static int filterCountByG_A_S(
		long groupId, long assigneeUserId, int[] statuses) {

		return getPersistence().filterCountByG_A_S(
			groupId, assigneeUserId, statuses);
	}

	/**
	 * Caches the todo entry in the entity cache if it is enabled.
	 *
	 * @param todoEntry the todo entry
	 */
	public static void cacheResult(TodoEntry todoEntry) {
		getPersistence().cacheResult(todoEntry);
	}

	/**
	 * Caches the todo entries in the entity cache if it is enabled.
	 *
	 * @param todoEntries the todo entries
	 */
	public static void cacheResult(List<TodoEntry> todoEntries) {
		getPersistence().cacheResult(todoEntries);
	}

	/**
	 * Creates a new todo entry with the primary key. Does not add the todo entry to the database.
	 *
	 * @param todoEntryId the primary key for the new todo entry
	 * @return the new todo entry
	 */
	public static TodoEntry create(long todoEntryId) {
		return getPersistence().create(todoEntryId);
	}

	/**
	 * Removes the todo entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param todoEntryId the primary key of the todo entry
	 * @return the todo entry that was removed
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry remove(long todoEntryId)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().remove(todoEntryId);
	}

	public static TodoEntry updateImpl(TodoEntry todoEntry) {
		return getPersistence().updateImpl(todoEntry);
	}

	/**
	 * Returns the todo entry with the primary key or throws a <code>NoSuchTodoEntryException</code> if it could not be found.
	 *
	 * @param todoEntryId the primary key of the todo entry
	 * @return the todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public static TodoEntry findByPrimaryKey(long todoEntryId)
		throws com.liferay.todo.exception.NoSuchTodoEntryException {

		return getPersistence().findByPrimaryKey(todoEntryId);
	}

	/**
	 * Returns the todo entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param todoEntryId the primary key of the todo entry
	 * @return the todo entry, or <code>null</code> if a todo entry with the primary key could not be found
	 */
	public static TodoEntry fetchByPrimaryKey(long todoEntryId) {
		return getPersistence().fetchByPrimaryKey(todoEntryId);
	}

	/**
	 * Returns all the todo entries.
	 *
	 * @return the todo entries
	 */
	public static List<TodoEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the todo entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @return the range of todo entries
	 */
	public static List<TodoEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the todo entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of todo entries
	 */
	public static List<TodoEntry> findAll(
		int start, int end, OrderByComparator<TodoEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the todo entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TodoEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of todo entries
	 * @param end the upper bound of the range of todo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of todo entries
	 */
	public static List<TodoEntry> findAll(
		int start, int end, OrderByComparator<TodoEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the todo entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of todo entries.
	 *
	 * @return the number of todo entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TodoEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TodoEntryPersistence, TodoEntryPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TodoEntryPersistence.class);

		ServiceTracker<TodoEntryPersistence, TodoEntryPersistence>
			serviceTracker =
				new ServiceTracker<TodoEntryPersistence, TodoEntryPersistence>(
					bundle.getBundleContext(), TodoEntryPersistence.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}