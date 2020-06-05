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

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.todo.exception.NoSuchTodoEntryException;
import com.liferay.todo.model.TodoEntry;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the todo entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @see TodoEntryUtil
 * @generated
 */
@ProviderType
public interface TodoEntryPersistence extends BasePersistence<TodoEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TodoEntryUtil} to access the todo entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the todo entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching todo entries
	 */
	public java.util.List<TodoEntry> findByGroupId(long groupId);

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
	public java.util.List<TodoEntry> findByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<TodoEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set where groupId = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public TodoEntry[] findByGroupId_PrevAndNext(
			long todoEntryId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching todo entries that the user has permission to view
	 */
	public java.util.List<TodoEntry> filterFindByGroupId(long groupId);

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
	public java.util.List<TodoEntry> filterFindByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<TodoEntry> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set of todo entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public TodoEntry[] filterFindByGroupId_PrevAndNext(
			long todoEntryId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Removes all the todo entries where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of todo entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching todo entries
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching todo entries that the user has permission to view
	 */
	public int filterCountByGroupId(long groupId);

	/**
	 * Returns all the todo entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching todo entries
	 */
	public java.util.List<TodoEntry> findByUserId(long userId);

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
	public java.util.List<TodoEntry> findByUserId(
		long userId, int start, int end);

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
	public java.util.List<TodoEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first todo entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByUserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the first todo entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

	/**
	 * Returns the last todo entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByUserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the last todo entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set where userId = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public TodoEntry[] findByUserId_PrevAndNext(
			long todoEntryId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Removes all the todo entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByUserId(long userId);

	/**
	 * Returns the number of todo entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching todo entries
	 */
	public int countByUserId(long userId);

	/**
	 * Returns all the todo entries where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @return the matching todo entries
	 */
	public java.util.List<TodoEntry> findByAssigneeUserId(long assigneeUserId);

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
	public java.util.List<TodoEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end);

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
	public java.util.List<TodoEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first todo entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByAssigneeUserId_First(
			long assigneeUserId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the first todo entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByAssigneeUserId_First(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

	/**
	 * Returns the last todo entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByAssigneeUserId_Last(
			long assigneeUserId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the last todo entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByAssigneeUserId_Last(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public TodoEntry[] findByAssigneeUserId_PrevAndNext(
			long todoEntryId, long assigneeUserId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Removes all the todo entries where assigneeUserId = &#63; from the database.
	 *
	 * @param assigneeUserId the assignee user ID
	 */
	public void removeByAssigneeUserId(long assigneeUserId);

	/**
	 * Returns the number of todo entries where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @return the number of matching todo entries
	 */
	public int countByAssigneeUserId(long assigneeUserId);

	/**
	 * Returns all the todo entries where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @return the matching todo entries
	 */
	public java.util.List<TodoEntry> findByResolverUserId(long resolverUserId);

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
	public java.util.List<TodoEntry> findByResolverUserId(
		long resolverUserId, int start, int end);

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
	public java.util.List<TodoEntry> findByResolverUserId(
		long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findByResolverUserId(
		long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first todo entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByResolverUserId_First(
			long resolverUserId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the first todo entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByResolverUserId_First(
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

	/**
	 * Returns the last todo entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByResolverUserId_Last(
			long resolverUserId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the last todo entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByResolverUserId_Last(
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

	/**
	 * Returns the todo entries before and after the current todo entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param todoEntryId the primary key of the current todo entry
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public TodoEntry[] findByResolverUserId_PrevAndNext(
			long todoEntryId, long resolverUserId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Removes all the todo entries where resolverUserId = &#63; from the database.
	 *
	 * @param resolverUserId the resolver user ID
	 */
	public void removeByResolverUserId(long resolverUserId);

	/**
	 * Returns the number of todo entries where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @return the number of matching todo entries
	 */
	public int countByResolverUserId(long resolverUserId);

	/**
	 * Returns all the todo entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching todo entries
	 */
	public java.util.List<TodoEntry> findByG_U(long groupId, long userId);

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
	public java.util.List<TodoEntry> findByG_U(
		long groupId, long userId, int start, int end);

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
	public java.util.List<TodoEntry> findByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByG_U_First(
			long groupId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByG_U_First(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByG_U_Last(
			long groupId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByG_U_Last(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public TodoEntry[] findByG_U_PrevAndNext(
			long todoEntryId, long groupId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching todo entries that the user has permission to view
	 */
	public java.util.List<TodoEntry> filterFindByG_U(long groupId, long userId);

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
	public java.util.List<TodoEntry> filterFindByG_U(
		long groupId, long userId, int start, int end);

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
	public java.util.List<TodoEntry> filterFindByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public TodoEntry[] filterFindByG_U_PrevAndNext(
			long todoEntryId, long groupId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Removes all the todo entries where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 */
	public void removeByG_U(long groupId, long userId);

	/**
	 * Returns the number of todo entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching todo entries
	 */
	public int countByG_U(long groupId, long userId);

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching todo entries that the user has permission to view
	 */
	public int filterCountByG_U(long groupId, long userId);

	/**
	 * Returns all the todo entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the matching todo entries
	 */
	public java.util.List<TodoEntry> findByG_A(
		long groupId, long assigneeUserId);

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
	public java.util.List<TodoEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end);

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
	public java.util.List<TodoEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByG_A_First(
			long groupId, long assigneeUserId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByG_A_First(
		long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByG_A_Last(
			long groupId, long assigneeUserId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByG_A_Last(
		long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public TodoEntry[] findByG_A_PrevAndNext(
			long todoEntryId, long groupId, long assigneeUserId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the matching todo entries that the user has permission to view
	 */
	public java.util.List<TodoEntry> filterFindByG_A(
		long groupId, long assigneeUserId);

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
	public java.util.List<TodoEntry> filterFindByG_A(
		long groupId, long assigneeUserId, int start, int end);

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
	public java.util.List<TodoEntry> filterFindByG_A(
		long groupId, long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public TodoEntry[] filterFindByG_A_PrevAndNext(
			long todoEntryId, long groupId, long assigneeUserId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Removes all the todo entries where groupId = &#63; and assigneeUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 */
	public void removeByG_A(long groupId, long assigneeUserId);

	/**
	 * Returns the number of todo entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the number of matching todo entries
	 */
	public int countByG_A(long groupId, long assigneeUserId);

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the number of matching todo entries that the user has permission to view
	 */
	public int filterCountByG_A(long groupId, long assigneeUserId);

	/**
	 * Returns all the todo entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the matching todo entries
	 */
	public java.util.List<TodoEntry> findByG_R(
		long groupId, long resolverUserId);

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
	public java.util.List<TodoEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end);

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
	public java.util.List<TodoEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByG_R_First(
			long groupId, long resolverUserId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByG_R_First(
		long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByG_R_Last(
			long groupId, long resolverUserId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByG_R_Last(
		long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public TodoEntry[] findByG_R_PrevAndNext(
			long todoEntryId, long groupId, long resolverUserId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the matching todo entries that the user has permission to view
	 */
	public java.util.List<TodoEntry> filterFindByG_R(
		long groupId, long resolverUserId);

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
	public java.util.List<TodoEntry> filterFindByG_R(
		long groupId, long resolverUserId, int start, int end);

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
	public java.util.List<TodoEntry> filterFindByG_R(
		long groupId, long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public TodoEntry[] filterFindByG_R_PrevAndNext(
			long todoEntryId, long groupId, long resolverUserId,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Removes all the todo entries where groupId = &#63; and resolverUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 */
	public void removeByG_R(long groupId, long resolverUserId);

	/**
	 * Returns the number of todo entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the number of matching todo entries
	 */
	public int countByG_R(long groupId, long resolverUserId);

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the number of matching todo entries that the user has permission to view
	 */
	public int filterCountByG_R(long groupId, long resolverUserId);

	/**
	 * Returns all the todo entries where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching todo entries
	 */
	public java.util.List<TodoEntry> findByU_S(long userId, int status);

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
	public java.util.List<TodoEntry> findByU_S(
		long userId, int status, int start, int end);

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
	public java.util.List<TodoEntry> findByU_S(
		long userId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findByU_S(
		long userId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first todo entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByU_S_First(
			long userId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the first todo entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByU_S_First(
		long userId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

	/**
	 * Returns the last todo entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByU_S_Last(
			long userId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the last todo entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByU_S_Last(
		long userId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public TodoEntry[] findByU_S_PrevAndNext(
			long todoEntryId, long userId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

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
	public java.util.List<TodoEntry> findByU_S(long userId, int[] statuses);

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
	public java.util.List<TodoEntry> findByU_S(
		long userId, int[] statuses, int start, int end);

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
	public java.util.List<TodoEntry> findByU_S(
		long userId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findByU_S(
		long userId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the todo entries where userId = &#63; and status = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param status the status
	 */
	public void removeByU_S(long userId, int status);

	/**
	 * Returns the number of todo entries where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching todo entries
	 */
	public int countByU_S(long userId, int status);

	/**
	 * Returns the number of todo entries where userId = &#63; and status = any &#63;.
	 *
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries
	 */
	public int countByU_S(long userId, int[] statuses);

	/**
	 * Returns all the todo entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the matching todo entries
	 */
	public java.util.List<TodoEntry> findByA_S(long assigneeUserId, int status);

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
	public java.util.List<TodoEntry> findByA_S(
		long assigneeUserId, int status, int start, int end);

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
	public java.util.List<TodoEntry> findByA_S(
		long assigneeUserId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findByA_S(
		long assigneeUserId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first todo entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByA_S_First(
			long assigneeUserId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the first todo entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByA_S_First(
		long assigneeUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

	/**
	 * Returns the last todo entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	public TodoEntry findByA_S_Last(
			long assigneeUserId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the last todo entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByA_S_Last(
		long assigneeUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public TodoEntry[] findByA_S_PrevAndNext(
			long todoEntryId, long assigneeUserId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

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
	public java.util.List<TodoEntry> findByA_S(
		long assigneeUserId, int[] statuses);

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
	public java.util.List<TodoEntry> findByA_S(
		long assigneeUserId, int[] statuses, int start, int end);

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
	public java.util.List<TodoEntry> findByA_S(
		long assigneeUserId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findByA_S(
		long assigneeUserId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the todo entries where assigneeUserId = &#63; and status = &#63; from the database.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 */
	public void removeByA_S(long assigneeUserId, int status);

	/**
	 * Returns the number of todo entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the number of matching todo entries
	 */
	public int countByA_S(long assigneeUserId, int status);

	/**
	 * Returns the number of todo entries where assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries
	 */
	public int countByA_S(long assigneeUserId, int[] statuses);

	/**
	 * Returns all the todo entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching todo entries
	 */
	public java.util.List<TodoEntry> findByG_U_S(
		long groupId, long userId, int status);

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
	public java.util.List<TodoEntry> findByG_U_S(
		long groupId, long userId, int status, int start, int end);

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
	public java.util.List<TodoEntry> findByG_U_S(
		long groupId, long userId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findByG_U_S(
		long groupId, long userId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator,
		boolean useFinderCache);

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
	public TodoEntry findByG_U_S_First(
			long groupId, long userId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByG_U_S_First(
		long groupId, long userId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public TodoEntry findByG_U_S_Last(
			long groupId, long userId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByG_U_S_Last(
		long groupId, long userId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public TodoEntry[] findByG_U_S_PrevAndNext(
			long todoEntryId, long groupId, long userId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching todo entries that the user has permission to view
	 */
	public java.util.List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int status);

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
	public java.util.List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int status, int start, int end);

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
	public java.util.List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public TodoEntry[] filterFindByG_U_S_PrevAndNext(
			long todoEntryId, long groupId, long userId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the matching todo entries that the user has permission to view
	 */
	public java.util.List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int[] statuses);

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
	public java.util.List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end);

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
	public java.util.List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findByG_U_S(
		long groupId, long userId, int[] statuses);

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
	public java.util.List<TodoEntry> findByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end);

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
	public java.util.List<TodoEntry> findByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the todo entries where groupId = &#63; and userId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 */
	public void removeByG_U_S(long groupId, long userId, int status);

	/**
	 * Returns the number of todo entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching todo entries
	 */
	public int countByG_U_S(long groupId, long userId, int status);

	/**
	 * Returns the number of todo entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries
	 */
	public int countByG_U_S(long groupId, long userId, int[] statuses);

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching todo entries that the user has permission to view
	 */
	public int filterCountByG_U_S(long groupId, long userId, int status);

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries that the user has permission to view
	 */
	public int filterCountByG_U_S(long groupId, long userId, int[] statuses);

	/**
	 * Returns all the todo entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the matching todo entries
	 */
	public java.util.List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status);

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
	public java.util.List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end);

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
	public java.util.List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator,
		boolean useFinderCache);

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
	public TodoEntry findByG_A_S_First(
			long groupId, long assigneeUserId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByG_A_S_First(
		long groupId, long assigneeUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public TodoEntry findByG_A_S_Last(
			long groupId, long assigneeUserId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	public TodoEntry fetchByG_A_S_Last(
		long groupId, long assigneeUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public TodoEntry[] findByG_A_S_PrevAndNext(
			long todoEntryId, long groupId, long assigneeUserId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the matching todo entries that the user has permission to view
	 */
	public java.util.List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int status);

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
	public java.util.List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end);

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
	public java.util.List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public TodoEntry[] filterFindByG_A_S_PrevAndNext(
			long todoEntryId, long groupId, long assigneeUserId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
				orderByComparator)
		throws NoSuchTodoEntryException;

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the matching todo entries that the user has permission to view
	 */
	public java.util.List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int[] statuses);

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
	public java.util.List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end);

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
	public java.util.List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int[] statuses);

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
	public java.util.List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end);

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
	public java.util.List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the todo entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 */
	public void removeByG_A_S(long groupId, long assigneeUserId, int status);

	/**
	 * Returns the number of todo entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the number of matching todo entries
	 */
	public int countByG_A_S(long groupId, long assigneeUserId, int status);

	/**
	 * Returns the number of todo entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries
	 */
	public int countByG_A_S(long groupId, long assigneeUserId, int[] statuses);

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the number of matching todo entries that the user has permission to view
	 */
	public int filterCountByG_A_S(
		long groupId, long assigneeUserId, int status);

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries that the user has permission to view
	 */
	public int filterCountByG_A_S(
		long groupId, long assigneeUserId, int[] statuses);

	/**
	 * Caches the todo entry in the entity cache if it is enabled.
	 *
	 * @param todoEntry the todo entry
	 */
	public void cacheResult(TodoEntry todoEntry);

	/**
	 * Caches the todo entries in the entity cache if it is enabled.
	 *
	 * @param todoEntries the todo entries
	 */
	public void cacheResult(java.util.List<TodoEntry> todoEntries);

	/**
	 * Creates a new todo entry with the primary key. Does not add the todo entry to the database.
	 *
	 * @param todoEntryId the primary key for the new todo entry
	 * @return the new todo entry
	 */
	public TodoEntry create(long todoEntryId);

	/**
	 * Removes the todo entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param todoEntryId the primary key of the todo entry
	 * @return the todo entry that was removed
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public TodoEntry remove(long todoEntryId) throws NoSuchTodoEntryException;

	public TodoEntry updateImpl(TodoEntry todoEntry);

	/**
	 * Returns the todo entry with the primary key or throws a <code>NoSuchTodoEntryException</code> if it could not be found.
	 *
	 * @param todoEntryId the primary key of the todo entry
	 * @return the todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	public TodoEntry findByPrimaryKey(long todoEntryId)
		throws NoSuchTodoEntryException;

	/**
	 * Returns the todo entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param todoEntryId the primary key of the todo entry
	 * @return the todo entry, or <code>null</code> if a todo entry with the primary key could not be found
	 */
	public TodoEntry fetchByPrimaryKey(long todoEntryId);

	/**
	 * Returns all the todo entries.
	 *
	 * @return the todo entries
	 */
	public java.util.List<TodoEntry> findAll();

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
	public java.util.List<TodoEntry> findAll(int start, int end);

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
	public java.util.List<TodoEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator);

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
	public java.util.List<TodoEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TodoEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the todo entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of todo entries.
	 *
	 * @return the number of todo entries
	 */
	public int countAll();

}