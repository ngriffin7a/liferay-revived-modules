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

package com.liferay.tasks.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.tasks.exception.NoSuchTasksEntryException;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.model.impl.TasksEntryImpl;
import com.liferay.tasks.model.impl.TasksEntryModelImpl;
import com.liferay.tasks.service.persistence.TasksEntryPersistence;
import com.liferay.tasks.service.persistence.impl.constants.TMSPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the tasks entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @generated
 */
@Component(service = TasksEntryPersistence.class)
public class TasksEntryPersistenceImpl
	extends BasePersistenceImpl<TasksEntry> implements TasksEntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TasksEntryUtil</code> to access the tasks entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TasksEntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the tasks entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<TasksEntry> list = null;

		if (useFinderCache) {
			list = (List<TasksEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TasksEntry tasksEntry : list) {
					if (groupId != tasksEntry.getGroupId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<TasksEntry>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByGroupId_First(
			long groupId, OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByGroupId_First(
			groupId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByGroupId_First(
		long groupId, OrderByComparator<TasksEntry> orderByComparator) {

		List<TasksEntry> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByGroupId_Last(
			long groupId, OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByGroupId_Last(groupId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByGroupId_Last(
		long groupId, OrderByComparator<TasksEntry> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByGroupId_PrevAndNext(
			long tasksEntryId, long groupId,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, tasksEntry, groupId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByGroupId_PrevAndNext(
				session, tasksEntry, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByGroupId_PrevAndNext(
		Session session, TasksEntry tasksEntry, long groupId,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(tasksEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks entries where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (TasksEntry tasksEntry :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"tasksEntry.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUserId;
	private FinderPath _finderPathWithoutPaginationFindByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns all the tasks entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByUserId(
		long userId, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the tasks entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByUserId(
		long userId, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUserId;
				finderArgs = new Object[] {userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUserId;
			finderArgs = new Object[] {userId, start, end, orderByComparator};
		}

		List<TasksEntry> list = null;

		if (useFinderCache) {
			list = (List<TasksEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TasksEntry tasksEntry : list) {
					if (userId != tasksEntry.getUserId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<TasksEntry>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first tasks entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByUserId_First(
			long userId, OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByUserId_First(userId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByUserId_First(
		long userId, OrderByComparator<TasksEntry> orderByComparator) {

		List<TasksEntry> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByUserId_Last(
			long userId, OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByUserId_Last(userId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByUserId_Last(
		long userId, OrderByComparator<TasksEntry> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where userId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByUserId_PrevAndNext(
			long tasksEntryId, long userId,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, tasksEntry, userId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByUserId_PrevAndNext(
				session, tasksEntry, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByUserId_PrevAndNext(
		Session session, TasksEntry tasksEntry, long userId,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(tasksEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (TasksEntry tasksEntry :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"tasksEntry.userId = ?";

	private FinderPath _finderPathWithPaginationFindByAssigneeUserId;
	private FinderPath _finderPathWithoutPaginationFindByAssigneeUserId;
	private FinderPath _finderPathCountByAssigneeUserId;

	/**
	 * Returns all the tasks entries where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByAssigneeUserId(long assigneeUserId) {
		return findByAssigneeUserId(
			assigneeUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end) {

		return findByAssigneeUserId(assigneeUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {

		return findByAssigneeUserId(
			assigneeUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the tasks entries where assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByAssigneeUserId;
				finderArgs = new Object[] {assigneeUserId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByAssigneeUserId;
			finderArgs = new Object[] {
				assigneeUserId, start, end, orderByComparator
			};
		}

		List<TasksEntry> list = null;

		if (useFinderCache) {
			list = (List<TasksEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TasksEntry tasksEntry : list) {
					if (assigneeUserId != tasksEntry.getAssigneeUserId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_ASSIGNEEUSERID_ASSIGNEEUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				list = (List<TasksEntry>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first tasks entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByAssigneeUserId_First(
			long assigneeUserId,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByAssigneeUserId_First(
			assigneeUserId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByAssigneeUserId_First(
		long assigneeUserId, OrderByComparator<TasksEntry> orderByComparator) {

		List<TasksEntry> list = findByAssigneeUserId(
			assigneeUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByAssigneeUserId_Last(
			long assigneeUserId,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByAssigneeUserId_Last(
			assigneeUserId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByAssigneeUserId_Last(
		long assigneeUserId, OrderByComparator<TasksEntry> orderByComparator) {

		int count = countByAssigneeUserId(assigneeUserId);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByAssigneeUserId(
			assigneeUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByAssigneeUserId_PrevAndNext(
			long tasksEntryId, long assigneeUserId,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByAssigneeUserId_PrevAndNext(
				session, tasksEntry, assigneeUserId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByAssigneeUserId_PrevAndNext(
				session, tasksEntry, assigneeUserId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByAssigneeUserId_PrevAndNext(
		Session session, TasksEntry tasksEntry, long assigneeUserId,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_ASSIGNEEUSERID_ASSIGNEEUSERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assigneeUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(tasksEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks entries where assigneeUserId = &#63; from the database.
	 *
	 * @param assigneeUserId the assignee user ID
	 */
	@Override
	public void removeByAssigneeUserId(long assigneeUserId) {
		for (TasksEntry tasksEntry :
				findByAssigneeUserId(
					assigneeUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByAssigneeUserId(long assigneeUserId) {
		FinderPath finderPath = _finderPathCountByAssigneeUserId;

		Object[] finderArgs = new Object[] {assigneeUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_ASSIGNEEUSERID_ASSIGNEEUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ASSIGNEEUSERID_ASSIGNEEUSERID_2 =
		"tasksEntry.assigneeUserId = ?";

	private FinderPath _finderPathWithPaginationFindByResolverUserId;
	private FinderPath _finderPathWithoutPaginationFindByResolverUserId;
	private FinderPath _finderPathCountByResolverUserId;

	/**
	 * Returns all the tasks entries where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByResolverUserId(long resolverUserId) {
		return findByResolverUserId(
			resolverUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByResolverUserId(
		long resolverUserId, int start, int end) {

		return findByResolverUserId(resolverUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByResolverUserId(
		long resolverUserId, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {

		return findByResolverUserId(
			resolverUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the tasks entries where resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByResolverUserId(
		long resolverUserId, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByResolverUserId;
				finderArgs = new Object[] {resolverUserId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByResolverUserId;
			finderArgs = new Object[] {
				resolverUserId, start, end, orderByComparator
			};
		}

		List<TasksEntry> list = null;

		if (useFinderCache) {
			list = (List<TasksEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TasksEntry tasksEntry : list) {
					if (resolverUserId != tasksEntry.getResolverUserId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_RESOLVERUSERID_RESOLVERUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resolverUserId);

				list = (List<TasksEntry>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first tasks entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByResolverUserId_First(
			long resolverUserId,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByResolverUserId_First(
			resolverUserId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resolverUserId=");
		msg.append(resolverUserId);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByResolverUserId_First(
		long resolverUserId, OrderByComparator<TasksEntry> orderByComparator) {

		List<TasksEntry> list = findByResolverUserId(
			resolverUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByResolverUserId_Last(
			long resolverUserId,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByResolverUserId_Last(
			resolverUserId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resolverUserId=");
		msg.append(resolverUserId);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByResolverUserId_Last(
		long resolverUserId, OrderByComparator<TasksEntry> orderByComparator) {

		int count = countByResolverUserId(resolverUserId);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByResolverUserId(
			resolverUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByResolverUserId_PrevAndNext(
			long tasksEntryId, long resolverUserId,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByResolverUserId_PrevAndNext(
				session, tasksEntry, resolverUserId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByResolverUserId_PrevAndNext(
				session, tasksEntry, resolverUserId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByResolverUserId_PrevAndNext(
		Session session, TasksEntry tasksEntry, long resolverUserId,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_RESOLVERUSERID_RESOLVERUSERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resolverUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(tasksEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks entries where resolverUserId = &#63; from the database.
	 *
	 * @param resolverUserId the resolver user ID
	 */
	@Override
	public void removeByResolverUserId(long resolverUserId) {
		for (TasksEntry tasksEntry :
				findByResolverUserId(
					resolverUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByResolverUserId(long resolverUserId) {
		FinderPath finderPath = _finderPathCountByResolverUserId;

		Object[] finderArgs = new Object[] {resolverUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_RESOLVERUSERID_RESOLVERUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resolverUserId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_RESOLVERUSERID_RESOLVERUSERID_2 =
		"tasksEntry.resolverUserId = ?";

	private FinderPath _finderPathWithPaginationFindByG_U;
	private FinderPath _finderPathWithoutPaginationFindByG_U;
	private FinderPath _finderPathCountByG_U;

	/**
	 * Returns all the tasks entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U(long groupId, long userId) {
		return findByG_U(
			groupId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U(
		long groupId, long userId, int start, int end) {

		return findByG_U(groupId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U(
		long groupId, long userId, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {

		return findByG_U(groupId, userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U(
		long groupId, long userId, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByG_U;
				finderArgs = new Object[] {groupId, userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByG_U;
			finderArgs = new Object[] {
				groupId, userId, start, end, orderByComparator
			};
		}

		List<TasksEntry> list = null;

		if (useFinderCache) {
			list = (List<TasksEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TasksEntry tasksEntry : list) {
					if ((groupId != tasksEntry.getGroupId()) ||
						(userId != tasksEntry.getUserId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				list = (List<TasksEntry>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_U_First(
			long groupId, long userId,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByG_U_First(
			groupId, userId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_U_First(
		long groupId, long userId,
		OrderByComparator<TasksEntry> orderByComparator) {

		List<TasksEntry> list = findByG_U(
			groupId, userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_U_Last(
			long groupId, long userId,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByG_U_Last(
			groupId, userId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_U_Last(
		long groupId, long userId,
		OrderByComparator<TasksEntry> orderByComparator) {

		int count = countByG_U(groupId, userId);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByG_U(
			groupId, userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByG_U_PrevAndNext(
			long tasksEntryId, long groupId, long userId,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByG_U_PrevAndNext(
				session, tasksEntry, groupId, userId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByG_U_PrevAndNext(
				session, tasksEntry, groupId, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByG_U_PrevAndNext(
		Session session, TasksEntry tasksEntry, long groupId, long userId,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(tasksEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks entries where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 */
	@Override
	public void removeByG_U(long groupId, long userId) {
		for (TasksEntry tasksEntry :
				findByG_U(
					groupId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByG_U(long groupId, long userId) {
		FinderPath finderPath = _finderPathCountByG_U;

		Object[] finderArgs = new Object[] {groupId, userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_U_GROUPID_2 =
		"tasksEntry.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_U_USERID_2 =
		"tasksEntry.userId = ?";

	private FinderPath _finderPathWithPaginationFindByG_A;
	private FinderPath _finderPathWithoutPaginationFindByG_A;
	private FinderPath _finderPathCountByG_A;

	/**
	 * Returns all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A(long groupId, long assigneeUserId) {
		return findByG_A(
			groupId, assigneeUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end) {

		return findByG_A(groupId, assigneeUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {

		return findByG_A(
			groupId, assigneeUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByG_A;
				finderArgs = new Object[] {groupId, assigneeUserId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByG_A;
			finderArgs = new Object[] {
				groupId, assigneeUserId, start, end, orderByComparator
			};
		}

		List<TasksEntry> list = null;

		if (useFinderCache) {
			list = (List<TasksEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TasksEntry tasksEntry : list) {
					if ((groupId != tasksEntry.getGroupId()) ||
						(assigneeUserId != tasksEntry.getAssigneeUserId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_A_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

				list = (List<TasksEntry>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_A_First(
			long groupId, long assigneeUserId,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByG_A_First(
			groupId, assigneeUserId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_A_First(
		long groupId, long assigneeUserId,
		OrderByComparator<TasksEntry> orderByComparator) {

		List<TasksEntry> list = findByG_A(
			groupId, assigneeUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_A_Last(
			long groupId, long assigneeUserId,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByG_A_Last(
			groupId, assigneeUserId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_A_Last(
		long groupId, long assigneeUserId,
		OrderByComparator<TasksEntry> orderByComparator) {

		int count = countByG_A(groupId, assigneeUserId);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByG_A(
			groupId, assigneeUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByG_A_PrevAndNext(
			long tasksEntryId, long groupId, long assigneeUserId,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByG_A_PrevAndNext(
				session, tasksEntry, groupId, assigneeUserId, orderByComparator,
				true);

			array[1] = tasksEntry;

			array[2] = getByG_A_PrevAndNext(
				session, tasksEntry, groupId, assigneeUserId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByG_A_PrevAndNext(
		Session session, TasksEntry tasksEntry, long groupId,
		long assigneeUserId, OrderByComparator<TasksEntry> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(assigneeUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(tasksEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks entries where groupId = &#63; and assigneeUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 */
	@Override
	public void removeByG_A(long groupId, long assigneeUserId) {
		for (TasksEntry tasksEntry :
				findByG_A(
					groupId, assigneeUserId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByG_A(long groupId, long assigneeUserId) {
		FinderPath finderPath = _finderPathCountByG_A;

		Object[] finderArgs = new Object[] {groupId, assigneeUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_A_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_A_GROUPID_2 =
		"tasksEntry.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_A_ASSIGNEEUSERID_2 =
		"tasksEntry.assigneeUserId = ?";

	private FinderPath _finderPathWithPaginationFindByG_R;
	private FinderPath _finderPathWithoutPaginationFindByG_R;
	private FinderPath _finderPathCountByG_R;

	/**
	 * Returns all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_R(long groupId, long resolverUserId) {
		return findByG_R(
			groupId, resolverUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end) {

		return findByG_R(groupId, resolverUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {

		return findByG_R(
			groupId, resolverUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByG_R;
				finderArgs = new Object[] {groupId, resolverUserId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByG_R;
			finderArgs = new Object[] {
				groupId, resolverUserId, start, end, orderByComparator
			};
		}

		List<TasksEntry> list = null;

		if (useFinderCache) {
			list = (List<TasksEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TasksEntry tasksEntry : list) {
					if ((groupId != tasksEntry.getGroupId()) ||
						(resolverUserId != tasksEntry.getResolverUserId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_R_GROUPID_2);

			query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(resolverUserId);

				list = (List<TasksEntry>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_R_First(
			long groupId, long resolverUserId,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByG_R_First(
			groupId, resolverUserId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", resolverUserId=");
		msg.append(resolverUserId);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_R_First(
		long groupId, long resolverUserId,
		OrderByComparator<TasksEntry> orderByComparator) {

		List<TasksEntry> list = findByG_R(
			groupId, resolverUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_R_Last(
			long groupId, long resolverUserId,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByG_R_Last(
			groupId, resolverUserId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", resolverUserId=");
		msg.append(resolverUserId);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_R_Last(
		long groupId, long resolverUserId,
		OrderByComparator<TasksEntry> orderByComparator) {

		int count = countByG_R(groupId, resolverUserId);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByG_R(
			groupId, resolverUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByG_R_PrevAndNext(
			long tasksEntryId, long groupId, long resolverUserId,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByG_R_PrevAndNext(
				session, tasksEntry, groupId, resolverUserId, orderByComparator,
				true);

			array[1] = tasksEntry;

			array[2] = getByG_R_PrevAndNext(
				session, tasksEntry, groupId, resolverUserId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByG_R_PrevAndNext(
		Session session, TasksEntry tasksEntry, long groupId,
		long resolverUserId, OrderByComparator<TasksEntry> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_R_GROUPID_2);

		query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(resolverUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(tasksEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks entries where groupId = &#63; and resolverUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 */
	@Override
	public void removeByG_R(long groupId, long resolverUserId) {
		for (TasksEntry tasksEntry :
				findByG_R(
					groupId, resolverUserId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByG_R(long groupId, long resolverUserId) {
		FinderPath finderPath = _finderPathCountByG_R;

		Object[] finderArgs = new Object[] {groupId, resolverUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_R_GROUPID_2);

			query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(resolverUserId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_R_GROUPID_2 =
		"tasksEntry.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_R_RESOLVERUSERID_2 =
		"tasksEntry.resolverUserId = ?";

	private FinderPath _finderPathWithPaginationFindByU_S;
	private FinderPath _finderPathWithoutPaginationFindByU_S;
	private FinderPath _finderPathCountByU_S;
	private FinderPath _finderPathWithPaginationCountByU_S;

	/**
	 * Returns all the tasks entries where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByU_S(long userId, int status) {
		return findByU_S(
			userId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByU_S(
		long userId, int status, int start, int end) {

		return findByU_S(userId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByU_S(
		long userId, int status, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {

		return findByU_S(userId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the tasks entries where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByU_S(
		long userId, int status, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByU_S;
				finderArgs = new Object[] {userId, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByU_S;
			finderArgs = new Object[] {
				userId, status, start, end, orderByComparator
			};
		}

		List<TasksEntry> list = null;

		if (useFinderCache) {
			list = (List<TasksEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TasksEntry tasksEntry : list) {
					if ((userId != tasksEntry.getUserId()) ||
						(status != tasksEntry.getStatus())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_S_USERID_2);

			query.append(_FINDER_COLUMN_U_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(status);

				list = (List<TasksEntry>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first tasks entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByU_S_First(
			long userId, int status,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByU_S_First(
			userId, status, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByU_S_First(
		long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator) {

		List<TasksEntry> list = findByU_S(
			userId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByU_S_Last(
			long userId, int status,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByU_S_Last(
			userId, status, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByU_S_Last(
		long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator) {

		int count = countByU_S(userId, status);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByU_S(
			userId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByU_S_PrevAndNext(
			long tasksEntryId, long userId, int status,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByU_S_PrevAndNext(
				session, tasksEntry, userId, status, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByU_S_PrevAndNext(
				session, tasksEntry, userId, status, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByU_S_PrevAndNext(
		Session session, TasksEntry tasksEntry, long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_U_S_USERID_2);

		query.append(_FINDER_COLUMN_U_S_STATUS_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(tasksEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tasks entries where userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByU_S(long userId, int[] statuses) {
		return findByU_S(
			userId, statuses, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByU_S(
		long userId, int[] statuses, int start, int end) {

		return findByU_S(userId, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByU_S(
		long userId, int[] statuses, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {

		return findByU_S(userId, statuses, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the tasks entries where userId = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByU_S(
		long userId, int[] statuses, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator,
		boolean useFinderCache) {

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.sortedUnique(statuses);
		}

		if (statuses.length == 1) {
			return findByU_S(
				userId, statuses[0], start, end, orderByComparator);
		}

		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderArgs = new Object[] {userId, StringUtil.merge(statuses)};
			}
		}
		else if (useFinderCache) {
			finderArgs = new Object[] {
				userId, StringUtil.merge(statuses), start, end,
				orderByComparator
			};
		}

		List<TasksEntry> list = null;

		if (useFinderCache) {
			list = (List<TasksEntry>)finderCache.getResult(
				_finderPathWithPaginationFindByU_S, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TasksEntry tasksEntry : list) {
					if ((userId != tasksEntry.getUserId()) ||
						!ArrayUtil.contains(statuses, tasksEntry.getStatus())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_S_USERID_2);

			if (statuses.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_U_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(
				removeConjunction(query.stringAt(query.index() - 1)),
				query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<TasksEntry>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(
						_finderPathWithPaginationFindByU_S, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathWithPaginationFindByU_S, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the tasks entries where userId = &#63; and status = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param status the status
	 */
	@Override
	public void removeByU_S(long userId, int status) {
		for (TasksEntry tasksEntry :
				findByU_S(
					userId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByU_S(long userId, int status) {
		FinderPath finderPath = _finderPathCountByU_S;

		Object[] finderArgs = new Object[] {userId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_S_USERID_2);

			query.append(_FINDER_COLUMN_U_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of tasks entries where userId = &#63; and status = any &#63;.
	 *
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByU_S(long userId, int[] statuses) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.sortedUnique(statuses);
		}

		Object[] finderArgs = new Object[] {userId, StringUtil.merge(statuses)};

		Long count = (Long)finderCache.getResult(
			_finderPathWithPaginationCountByU_S, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_S_USERID_2);

			if (statuses.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_U_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(
				removeConjunction(query.stringAt(query.index() - 1)),
				query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathWithPaginationCountByU_S, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathWithPaginationCountByU_S, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_U_S_USERID_2 =
		"tasksEntry.userId = ? AND ";

	private static final String _FINDER_COLUMN_U_S_STATUS_2 =
		"tasksEntry.status = ?";

	private static final String _FINDER_COLUMN_U_S_STATUS_7 =
		"tasksEntry.status IN (";

	private FinderPath _finderPathWithPaginationFindByA_S;
	private FinderPath _finderPathWithoutPaginationFindByA_S;
	private FinderPath _finderPathCountByA_S;
	private FinderPath _finderPathWithPaginationCountByA_S;

	/**
	 * Returns all the tasks entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByA_S(long assigneeUserId, int status) {
		return findByA_S(
			assigneeUserId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByA_S(
		long assigneeUserId, int status, int start, int end) {

		return findByA_S(assigneeUserId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByA_S(
		long assigneeUserId, int status, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {

		return findByA_S(
			assigneeUserId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the tasks entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByA_S(
		long assigneeUserId, int status, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByA_S;
				finderArgs = new Object[] {assigneeUserId, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByA_S;
			finderArgs = new Object[] {
				assigneeUserId, status, start, end, orderByComparator
			};
		}

		List<TasksEntry> list = null;

		if (useFinderCache) {
			list = (List<TasksEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TasksEntry tasksEntry : list) {
					if ((assigneeUserId != tasksEntry.getAssigneeUserId()) ||
						(status != tasksEntry.getStatus())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_A_S_ASSIGNEEUSERID_2);

			query.append(_FINDER_COLUMN_A_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				qPos.add(status);

				list = (List<TasksEntry>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first tasks entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByA_S_First(
			long assigneeUserId, int status,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByA_S_First(
			assigneeUserId, status, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByA_S_First(
		long assigneeUserId, int status,
		OrderByComparator<TasksEntry> orderByComparator) {

		List<TasksEntry> list = findByA_S(
			assigneeUserId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByA_S_Last(
			long assigneeUserId, int status,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByA_S_Last(
			assigneeUserId, status, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByA_S_Last(
		long assigneeUserId, int status,
		OrderByComparator<TasksEntry> orderByComparator) {

		int count = countByA_S(assigneeUserId, status);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByA_S(
			assigneeUserId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByA_S_PrevAndNext(
			long tasksEntryId, long assigneeUserId, int status,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByA_S_PrevAndNext(
				session, tasksEntry, assigneeUserId, status, orderByComparator,
				true);

			array[1] = tasksEntry;

			array[2] = getByA_S_PrevAndNext(
				session, tasksEntry, assigneeUserId, status, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByA_S_PrevAndNext(
		Session session, TasksEntry tasksEntry, long assigneeUserId, int status,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_A_S_ASSIGNEEUSERID_2);

		query.append(_FINDER_COLUMN_A_S_STATUS_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assigneeUserId);

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(tasksEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tasks entries where assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByA_S(long assigneeUserId, int[] statuses) {
		return findByA_S(
			assigneeUserId, statuses, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the tasks entries where assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByA_S(
		long assigneeUserId, int[] statuses, int start, int end) {

		return findByA_S(assigneeUserId, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByA_S(
		long assigneeUserId, int[] statuses, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {

		return findByA_S(
			assigneeUserId, statuses, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the tasks entries where assigneeUserId = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByA_S(
		long assigneeUserId, int[] statuses, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator,
		boolean useFinderCache) {

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.sortedUnique(statuses);
		}

		if (statuses.length == 1) {
			return findByA_S(
				assigneeUserId, statuses[0], start, end, orderByComparator);
		}

		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderArgs = new Object[] {
					assigneeUserId, StringUtil.merge(statuses)
				};
			}
		}
		else if (useFinderCache) {
			finderArgs = new Object[] {
				assigneeUserId, StringUtil.merge(statuses), start, end,
				orderByComparator
			};
		}

		List<TasksEntry> list = null;

		if (useFinderCache) {
			list = (List<TasksEntry>)finderCache.getResult(
				_finderPathWithPaginationFindByA_S, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TasksEntry tasksEntry : list) {
					if ((assigneeUserId != tasksEntry.getAssigneeUserId()) ||
						!ArrayUtil.contains(statuses, tasksEntry.getStatus())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_A_S_ASSIGNEEUSERID_2);

			if (statuses.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_A_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(
				removeConjunction(query.stringAt(query.index() - 1)),
				query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				list = (List<TasksEntry>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(
						_finderPathWithPaginationFindByA_S, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathWithPaginationFindByA_S, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the tasks entries where assigneeUserId = &#63; and status = &#63; from the database.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 */
	@Override
	public void removeByA_S(long assigneeUserId, int status) {
		for (TasksEntry tasksEntry :
				findByA_S(
					assigneeUserId, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByA_S(long assigneeUserId, int status) {
		FinderPath finderPath = _finderPathCountByA_S;

		Object[] finderArgs = new Object[] {assigneeUserId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_A_S_ASSIGNEEUSERID_2);

			query.append(_FINDER_COLUMN_A_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of tasks entries where assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByA_S(long assigneeUserId, int[] statuses) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.sortedUnique(statuses);
		}

		Object[] finderArgs = new Object[] {
			assigneeUserId, StringUtil.merge(statuses)
		};

		Long count = (Long)finderCache.getResult(
			_finderPathWithPaginationCountByA_S, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_A_S_ASSIGNEEUSERID_2);

			if (statuses.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_A_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(
				removeConjunction(query.stringAt(query.index() - 1)),
				query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathWithPaginationCountByA_S, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathWithPaginationCountByA_S, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_A_S_ASSIGNEEUSERID_2 =
		"tasksEntry.assigneeUserId = ? AND ";

	private static final String _FINDER_COLUMN_A_S_STATUS_2 =
		"tasksEntry.status = ?";

	private static final String _FINDER_COLUMN_A_S_STATUS_7 =
		"tasksEntry.status IN (";

	private FinderPath _finderPathWithPaginationFindByG_U_S;
	private FinderPath _finderPathWithoutPaginationFindByG_U_S;
	private FinderPath _finderPathCountByG_U_S;
	private FinderPath _finderPathWithPaginationCountByG_U_S;

	/**
	 * Returns all the tasks entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U_S(long groupId, long userId, int status) {
		return findByG_U_S(
			groupId, userId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the tasks entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U_S(
		long groupId, long userId, int status, int start, int end) {

		return findByG_U_S(groupId, userId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U_S(
		long groupId, long userId, int status, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {

		return findByG_U_S(
			groupId, userId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U_S(
		long groupId, long userId, int status, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByG_U_S;
				finderArgs = new Object[] {groupId, userId, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByG_U_S;
			finderArgs = new Object[] {
				groupId, userId, status, start, end, orderByComparator
			};
		}

		List<TasksEntry> list = null;

		if (useFinderCache) {
			list = (List<TasksEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TasksEntry tasksEntry : list) {
					if ((groupId != tasksEntry.getGroupId()) ||
						(userId != tasksEntry.getUserId()) ||
						(status != tasksEntry.getStatus())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_S_USERID_2);

			query.append(_FINDER_COLUMN_G_U_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(status);

				list = (List<TasksEntry>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_U_S_First(
			long groupId, long userId, int status,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByG_U_S_First(
			groupId, userId, status, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_U_S_First(
		long groupId, long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator) {

		List<TasksEntry> list = findByG_U_S(
			groupId, userId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_U_S_Last(
			long groupId, long userId, int status,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByG_U_S_Last(
			groupId, userId, status, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_U_S_Last(
		long groupId, long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator) {

		int count = countByG_U_S(groupId, userId, status);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByG_U_S(
			groupId, userId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByG_U_S_PrevAndNext(
			long tasksEntryId, long groupId, long userId, int status,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByG_U_S_PrevAndNext(
				session, tasksEntry, groupId, userId, status, orderByComparator,
				true);

			array[1] = tasksEntry;

			array[2] = getByG_U_S_PrevAndNext(
				session, tasksEntry, groupId, userId, status, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByG_U_S_PrevAndNext(
		Session session, TasksEntry tasksEntry, long groupId, long userId,
		int status, OrderByComparator<TasksEntry> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_S_USERID_2);

		query.append(_FINDER_COLUMN_G_U_S_STATUS_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(tasksEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tasks entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U_S(
		long groupId, long userId, int[] statuses) {

		return findByG_U_S(
			groupId, userId, statuses, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the tasks entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end) {

		return findByG_U_S(groupId, userId, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {

		return findByG_U_S(
			groupId, userId, statuses, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and userId = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator,
		boolean useFinderCache) {

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.sortedUnique(statuses);
		}

		if (statuses.length == 1) {
			return findByG_U_S(
				groupId, userId, statuses[0], start, end, orderByComparator);
		}

		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderArgs = new Object[] {
					groupId, userId, StringUtil.merge(statuses)
				};
			}
		}
		else if (useFinderCache) {
			finderArgs = new Object[] {
				groupId, userId, StringUtil.merge(statuses), start, end,
				orderByComparator
			};
		}

		List<TasksEntry> list = null;

		if (useFinderCache) {
			list = (List<TasksEntry>)finderCache.getResult(
				_finderPathWithPaginationFindByG_U_S, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TasksEntry tasksEntry : list) {
					if ((groupId != tasksEntry.getGroupId()) ||
						(userId != tasksEntry.getUserId()) ||
						!ArrayUtil.contains(statuses, tasksEntry.getStatus())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_S_USERID_2);

			if (statuses.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_U_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(
				removeConjunction(query.stringAt(query.index() - 1)),
				query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				list = (List<TasksEntry>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(
						_finderPathWithPaginationFindByG_U_S, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathWithPaginationFindByG_U_S, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the tasks entries where groupId = &#63; and userId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 */
	@Override
	public void removeByG_U_S(long groupId, long userId, int status) {
		for (TasksEntry tasksEntry :
				findByG_U_S(
					groupId, userId, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByG_U_S(long groupId, long userId, int status) {
		FinderPath finderPath = _finderPathCountByG_U_S;

		Object[] finderArgs = new Object[] {groupId, userId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_S_USERID_2);

			query.append(_FINDER_COLUMN_G_U_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of tasks entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByG_U_S(long groupId, long userId, int[] statuses) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.sortedUnique(statuses);
		}

		Object[] finderArgs = new Object[] {
			groupId, userId, StringUtil.merge(statuses)
		};

		Long count = (Long)finderCache.getResult(
			_finderPathWithPaginationCountByG_U_S, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_S_USERID_2);

			if (statuses.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_U_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(
				removeConjunction(query.stringAt(query.index() - 1)),
				query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathWithPaginationCountByG_U_S, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathWithPaginationCountByG_U_S, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_U_S_GROUPID_2 =
		"tasksEntry.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_U_S_USERID_2 =
		"tasksEntry.userId = ? AND ";

	private static final String _FINDER_COLUMN_G_U_S_STATUS_2 =
		"tasksEntry.status = ?";

	private static final String _FINDER_COLUMN_G_U_S_STATUS_7 =
		"tasksEntry.status IN (";

	private FinderPath _finderPathWithPaginationFindByG_A_S;
	private FinderPath _finderPathWithoutPaginationFindByG_A_S;
	private FinderPath _finderPathCountByG_A_S;
	private FinderPath _finderPathWithPaginationCountByG_A_S;

	/**
	 * Returns all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status) {

		return findByG_A_S(
			groupId, assigneeUserId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end) {

		return findByG_A_S(groupId, assigneeUserId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {

		return findByG_A_S(
			groupId, assigneeUserId, status, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByG_A_S;
				finderArgs = new Object[] {groupId, assigneeUserId, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByG_A_S;
			finderArgs = new Object[] {
				groupId, assigneeUserId, status, start, end, orderByComparator
			};
		}

		List<TasksEntry> list = null;

		if (useFinderCache) {
			list = (List<TasksEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TasksEntry tasksEntry : list) {
					if ((groupId != tasksEntry.getGroupId()) ||
						(assigneeUserId != tasksEntry.getAssigneeUserId()) ||
						(status != tasksEntry.getStatus())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

			query.append(_FINDER_COLUMN_G_A_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

				qPos.add(status);

				list = (List<TasksEntry>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_A_S_First(
			long groupId, long assigneeUserId, int status,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByG_A_S_First(
			groupId, assigneeUserId, status, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_A_S_First(
		long groupId, long assigneeUserId, int status,
		OrderByComparator<TasksEntry> orderByComparator) {

		List<TasksEntry> list = findByG_A_S(
			groupId, assigneeUserId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_A_S_Last(
			long groupId, long assigneeUserId, int status,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByG_A_S_Last(
			groupId, assigneeUserId, status, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_A_S_Last(
		long groupId, long assigneeUserId, int status,
		OrderByComparator<TasksEntry> orderByComparator) {

		int count = countByG_A_S(groupId, assigneeUserId, status);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByG_A_S(
			groupId, assigneeUserId, status, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByG_A_S_PrevAndNext(
			long tasksEntryId, long groupId, long assigneeUserId, int status,
			OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByG_A_S_PrevAndNext(
				session, tasksEntry, groupId, assigneeUserId, status,
				orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByG_A_S_PrevAndNext(
				session, tasksEntry, groupId, assigneeUserId, status,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByG_A_S_PrevAndNext(
		Session session, TasksEntry tasksEntry, long groupId,
		long assigneeUserId, int status,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

		query.append(_FINDER_COLUMN_G_A_S_STATUS_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(assigneeUserId);

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(tasksEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A_S(
		long groupId, long assigneeUserId, int[] statuses) {

		return findByG_A_S(
			groupId, assigneeUserId, statuses, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end) {

		return findByG_A_S(groupId, assigneeUserId, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {

		return findByG_A_S(
			groupId, assigneeUserId, statuses, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator,
		boolean useFinderCache) {

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.sortedUnique(statuses);
		}

		if (statuses.length == 1) {
			return findByG_A_S(
				groupId, assigneeUserId, statuses[0], start, end,
				orderByComparator);
		}

		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderArgs = new Object[] {
					groupId, assigneeUserId, StringUtil.merge(statuses)
				};
			}
		}
		else if (useFinderCache) {
			finderArgs = new Object[] {
				groupId, assigneeUserId, StringUtil.merge(statuses), start, end,
				orderByComparator
			};
		}

		List<TasksEntry> list = null;

		if (useFinderCache) {
			list = (List<TasksEntry>)finderCache.getResult(
				_finderPathWithPaginationFindByG_A_S, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TasksEntry tasksEntry : list) {
					if ((groupId != tasksEntry.getGroupId()) ||
						(assigneeUserId != tasksEntry.getAssigneeUserId()) ||
						!ArrayUtil.contains(statuses, tasksEntry.getStatus())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

			if (statuses.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_A_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(
				removeConjunction(query.stringAt(query.index() - 1)),
				query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

				list = (List<TasksEntry>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(
						_finderPathWithPaginationFindByG_A_S, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathWithPaginationFindByG_A_S, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 */
	@Override
	public void removeByG_A_S(long groupId, long assigneeUserId, int status) {
		for (TasksEntry tasksEntry :
				findByG_A_S(
					groupId, assigneeUserId, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByG_A_S(long groupId, long assigneeUserId, int status) {
		FinderPath finderPath = _finderPathCountByG_A_S;

		Object[] finderArgs = new Object[] {groupId, assigneeUserId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

			query.append(_FINDER_COLUMN_G_A_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByG_A_S(long groupId, long assigneeUserId, int[] statuses) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.sortedUnique(statuses);
		}

		Object[] finderArgs = new Object[] {
			groupId, assigneeUserId, StringUtil.merge(statuses)
		};

		Long count = (Long)finderCache.getResult(
			_finderPathWithPaginationCountByG_A_S, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

			if (statuses.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_A_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(
				removeConjunction(query.stringAt(query.index() - 1)),
				query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathWithPaginationCountByG_A_S, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathWithPaginationCountByG_A_S, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_A_S_GROUPID_2 =
		"tasksEntry.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2 =
		"tasksEntry.assigneeUserId = ? AND ";

	private static final String _FINDER_COLUMN_G_A_S_STATUS_2 =
		"tasksEntry.status = ?";

	private static final String _FINDER_COLUMN_G_A_S_STATUS_7 =
		"tasksEntry.status IN (";

	public TasksEntryPersistenceImpl() {
		setModelClass(TasksEntry.class);

		setModelImplClass(TasksEntryImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the tasks entry in the entity cache if it is enabled.
	 *
	 * @param tasksEntry the tasks entry
	 */
	@Override
	public void cacheResult(TasksEntry tasksEntry) {
		entityCache.putResult(
			entityCacheEnabled, TasksEntryImpl.class,
			tasksEntry.getPrimaryKey(), tasksEntry);

		tasksEntry.resetOriginalValues();
	}

	/**
	 * Caches the tasks entries in the entity cache if it is enabled.
	 *
	 * @param tasksEntries the tasks entries
	 */
	@Override
	public void cacheResult(List<TasksEntry> tasksEntries) {
		for (TasksEntry tasksEntry : tasksEntries) {
			if (entityCache.getResult(
					entityCacheEnabled, TasksEntryImpl.class,
					tasksEntry.getPrimaryKey()) == null) {

				cacheResult(tasksEntry);
			}
			else {
				tasksEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all tasks entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TasksEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the tasks entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TasksEntry tasksEntry) {
		entityCache.removeResult(
			entityCacheEnabled, TasksEntryImpl.class,
			tasksEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TasksEntry> tasksEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TasksEntry tasksEntry : tasksEntries) {
			entityCache.removeResult(
				entityCacheEnabled, TasksEntryImpl.class,
				tasksEntry.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, TasksEntryImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new tasks entry with the primary key. Does not add the tasks entry to the database.
	 *
	 * @param tasksEntryId the primary key for the new tasks entry
	 * @return the new tasks entry
	 */
	@Override
	public TasksEntry create(long tasksEntryId) {
		TasksEntry tasksEntry = new TasksEntryImpl();

		tasksEntry.setNew(true);
		tasksEntry.setPrimaryKey(tasksEntryId);

		tasksEntry.setCompanyId(CompanyThreadLocal.getCompanyId());

		return tasksEntry;
	}

	/**
	 * Removes the tasks entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tasksEntryId the primary key of the tasks entry
	 * @return the tasks entry that was removed
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry remove(long tasksEntryId)
		throws NoSuchTasksEntryException {

		return remove((Serializable)tasksEntryId);
	}

	/**
	 * Removes the tasks entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the tasks entry
	 * @return the tasks entry that was removed
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry remove(Serializable primaryKey)
		throws NoSuchTasksEntryException {

		Session session = null;

		try {
			session = openSession();

			TasksEntry tasksEntry = (TasksEntry)session.get(
				TasksEntryImpl.class, primaryKey);

			if (tasksEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTasksEntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(tasksEntry);
		}
		catch (NoSuchTasksEntryException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected TasksEntry removeImpl(TasksEntry tasksEntry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(tasksEntry)) {
				tasksEntry = (TasksEntry)session.get(
					TasksEntryImpl.class, tasksEntry.getPrimaryKeyObj());
			}

			if (tasksEntry != null) {
				session.delete(tasksEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (tasksEntry != null) {
			clearCache(tasksEntry);
		}

		return tasksEntry;
	}

	@Override
	public TasksEntry updateImpl(TasksEntry tasksEntry) {
		boolean isNew = tasksEntry.isNew();

		if (!(tasksEntry instanceof TasksEntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(tasksEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(tasksEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in tasksEntry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TasksEntry implementation " +
					tasksEntry.getClass());
		}

		TasksEntryModelImpl tasksEntryModelImpl =
			(TasksEntryModelImpl)tasksEntry;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (tasksEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				tasksEntry.setCreateDate(now);
			}
			else {
				tasksEntry.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!tasksEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				tasksEntry.setModifiedDate(now);
			}
			else {
				tasksEntry.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (tasksEntry.isNew()) {
				session.save(tasksEntry);

				tasksEntry.setNew(false);
			}
			else {
				tasksEntry = (TasksEntry)session.merge(tasksEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {tasksEntryModelImpl.getGroupId()};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			args = new Object[] {tasksEntryModelImpl.getUserId()};

			finderCache.removeResult(_finderPathCountByUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUserId, args);

			args = new Object[] {tasksEntryModelImpl.getAssigneeUserId()};

			finderCache.removeResult(_finderPathCountByAssigneeUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByAssigneeUserId, args);

			args = new Object[] {tasksEntryModelImpl.getResolverUserId()};

			finderCache.removeResult(_finderPathCountByResolverUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByResolverUserId, args);

			args = new Object[] {
				tasksEntryModelImpl.getGroupId(),
				tasksEntryModelImpl.getUserId()
			};

			finderCache.removeResult(_finderPathCountByG_U, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByG_U, args);

			args = new Object[] {
				tasksEntryModelImpl.getGroupId(),
				tasksEntryModelImpl.getAssigneeUserId()
			};

			finderCache.removeResult(_finderPathCountByG_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByG_A, args);

			args = new Object[] {
				tasksEntryModelImpl.getGroupId(),
				tasksEntryModelImpl.getResolverUserId()
			};

			finderCache.removeResult(_finderPathCountByG_R, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByG_R, args);

			args = new Object[] {
				tasksEntryModelImpl.getUserId(), tasksEntryModelImpl.getStatus()
			};

			finderCache.removeResult(_finderPathCountByU_S, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByU_S, args);

			args = new Object[] {
				tasksEntryModelImpl.getAssigneeUserId(),
				tasksEntryModelImpl.getStatus()
			};

			finderCache.removeResult(_finderPathCountByA_S, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByA_S, args);

			args = new Object[] {
				tasksEntryModelImpl.getGroupId(),
				tasksEntryModelImpl.getUserId(), tasksEntryModelImpl.getStatus()
			};

			finderCache.removeResult(_finderPathCountByG_U_S, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByG_U_S, args);

			args = new Object[] {
				tasksEntryModelImpl.getGroupId(),
				tasksEntryModelImpl.getAssigneeUserId(),
				tasksEntryModelImpl.getStatus()
			};

			finderCache.removeResult(_finderPathCountByG_A_S, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByG_A_S, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((tasksEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					tasksEntryModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {tasksEntryModelImpl.getGroupId()};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUserId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					tasksEntryModelImpl.getOriginalUserId()
				};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);

				args = new Object[] {tasksEntryModelImpl.getUserId()};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByAssigneeUserId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					tasksEntryModelImpl.getOriginalAssigneeUserId()
				};

				finderCache.removeResult(
					_finderPathCountByAssigneeUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAssigneeUserId, args);

				args = new Object[] {tasksEntryModelImpl.getAssigneeUserId()};

				finderCache.removeResult(
					_finderPathCountByAssigneeUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAssigneeUserId, args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByResolverUserId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					tasksEntryModelImpl.getOriginalResolverUserId()
				};

				finderCache.removeResult(
					_finderPathCountByResolverUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByResolverUserId, args);

				args = new Object[] {tasksEntryModelImpl.getResolverUserId()};

				finderCache.removeResult(
					_finderPathCountByResolverUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByResolverUserId, args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByG_U.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					tasksEntryModelImpl.getOriginalGroupId(),
					tasksEntryModelImpl.getOriginalUserId()
				};

				finderCache.removeResult(_finderPathCountByG_U, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_U, args);

				args = new Object[] {
					tasksEntryModelImpl.getGroupId(),
					tasksEntryModelImpl.getUserId()
				};

				finderCache.removeResult(_finderPathCountByG_U, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_U, args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByG_A.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					tasksEntryModelImpl.getOriginalGroupId(),
					tasksEntryModelImpl.getOriginalAssigneeUserId()
				};

				finderCache.removeResult(_finderPathCountByG_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_A, args);

				args = new Object[] {
					tasksEntryModelImpl.getGroupId(),
					tasksEntryModelImpl.getAssigneeUserId()
				};

				finderCache.removeResult(_finderPathCountByG_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_A, args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByG_R.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					tasksEntryModelImpl.getOriginalGroupId(),
					tasksEntryModelImpl.getOriginalResolverUserId()
				};

				finderCache.removeResult(_finderPathCountByG_R, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_R, args);

				args = new Object[] {
					tasksEntryModelImpl.getGroupId(),
					tasksEntryModelImpl.getResolverUserId()
				};

				finderCache.removeResult(_finderPathCountByG_R, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_R, args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByU_S.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					tasksEntryModelImpl.getOriginalUserId(),
					tasksEntryModelImpl.getOriginalStatus()
				};

				finderCache.removeResult(_finderPathCountByU_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByU_S, args);

				args = new Object[] {
					tasksEntryModelImpl.getUserId(),
					tasksEntryModelImpl.getStatus()
				};

				finderCache.removeResult(_finderPathCountByU_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByU_S, args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByA_S.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					tasksEntryModelImpl.getOriginalAssigneeUserId(),
					tasksEntryModelImpl.getOriginalStatus()
				};

				finderCache.removeResult(_finderPathCountByA_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByA_S, args);

				args = new Object[] {
					tasksEntryModelImpl.getAssigneeUserId(),
					tasksEntryModelImpl.getStatus()
				};

				finderCache.removeResult(_finderPathCountByA_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByA_S, args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByG_U_S.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					tasksEntryModelImpl.getOriginalGroupId(),
					tasksEntryModelImpl.getOriginalUserId(),
					tasksEntryModelImpl.getOriginalStatus()
				};

				finderCache.removeResult(_finderPathCountByG_U_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_U_S, args);

				args = new Object[] {
					tasksEntryModelImpl.getGroupId(),
					tasksEntryModelImpl.getUserId(),
					tasksEntryModelImpl.getStatus()
				};

				finderCache.removeResult(_finderPathCountByG_U_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_U_S, args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByG_A_S.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					tasksEntryModelImpl.getOriginalGroupId(),
					tasksEntryModelImpl.getOriginalAssigneeUserId(),
					tasksEntryModelImpl.getOriginalStatus()
				};

				finderCache.removeResult(_finderPathCountByG_A_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_A_S, args);

				args = new Object[] {
					tasksEntryModelImpl.getGroupId(),
					tasksEntryModelImpl.getAssigneeUserId(),
					tasksEntryModelImpl.getStatus()
				};

				finderCache.removeResult(_finderPathCountByG_A_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_A_S, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, TasksEntryImpl.class,
			tasksEntry.getPrimaryKey(), tasksEntry, false);

		tasksEntry.resetOriginalValues();

		return tasksEntry;
	}

	/**
	 * Returns the tasks entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the tasks entry
	 * @return the tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTasksEntryException {

		TasksEntry tasksEntry = fetchByPrimaryKey(primaryKey);

		if (tasksEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTasksEntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return tasksEntry;
	}

	/**
	 * Returns the tasks entry with the primary key or throws a <code>NoSuchTasksEntryException</code> if it could not be found.
	 *
	 * @param tasksEntryId the primary key of the tasks entry
	 * @return the tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry findByPrimaryKey(long tasksEntryId)
		throws NoSuchTasksEntryException {

		return findByPrimaryKey((Serializable)tasksEntryId);
	}

	/**
	 * Returns the tasks entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param tasksEntryId the primary key of the tasks entry
	 * @return the tasks entry, or <code>null</code> if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry fetchByPrimaryKey(long tasksEntryId) {
		return fetchByPrimaryKey((Serializable)tasksEntryId);
	}

	/**
	 * Returns all the tasks entries.
	 *
	 * @return the tasks entries
	 */
	@Override
	public List<TasksEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of tasks entries
	 */
	@Override
	public List<TasksEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of tasks entries
	 */
	@Override
	public List<TasksEntry> findAll(
		int start, int end, OrderByComparator<TasksEntry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the tasks entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TasksEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of tasks entries
	 */
	@Override
	public List<TasksEntry> findAll(
		int start, int end, OrderByComparator<TasksEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<TasksEntry> list = null;

		if (useFinderCache) {
			list = (List<TasksEntry>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TASKSENTRY);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TASKSENTRY;

				sql = sql.concat(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				list = (List<TasksEntry>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the tasks entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TasksEntry tasksEntry : findAll()) {
			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries.
	 *
	 * @return the number of tasks entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TASKSENTRY);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "tasksEntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_TASKSENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TasksEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the tasks entry persistence.
	 */
	@Activate
	public void activate() {
		TasksEntryModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		TasksEntryModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()},
			TasksEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] {Long.class.getName()},
			TasksEntryModelImpl.USERID_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByAssigneeUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssigneeUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByAssigneeUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssigneeUserId",
			new String[] {Long.class.getName()},
			TasksEntryModelImpl.ASSIGNEEUSERID_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByAssigneeUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAssigneeUserId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByResolverUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByResolverUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByResolverUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByResolverUserId",
			new String[] {Long.class.getName()},
			TasksEntryModelImpl.RESOLVERUSERID_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByResolverUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByResolverUserId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByG_U = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByG_U = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U",
			new String[] {Long.class.getName(), Long.class.getName()},
			TasksEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TasksEntryModelImpl.USERID_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByG_U = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByG_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_A",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByG_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_A",
			new String[] {Long.class.getName(), Long.class.getName()},
			TasksEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TasksEntryModelImpl.ASSIGNEEUSERID_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByG_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_A",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByG_R = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_R",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByG_R = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_R",
			new String[] {Long.class.getName(), Long.class.getName()},
			TasksEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TasksEntryModelImpl.RESOLVERUSERID_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByG_R = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_R",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByU_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByU_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			TasksEntryModelImpl.USERID_COLUMN_BITMASK |
			TasksEntryModelImpl.STATUS_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByU_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_S",
			new String[] {Long.class.getName(), Integer.class.getName()});

		_finderPathWithPaginationCountByU_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByU_S",
			new String[] {Long.class.getName(), Integer.class.getName()});

		_finderPathWithPaginationFindByA_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByA_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByA_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByA_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			TasksEntryModelImpl.ASSIGNEEUSERID_COLUMN_BITMASK |
			TasksEntryModelImpl.STATUS_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByA_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByA_S",
			new String[] {Long.class.getName(), Integer.class.getName()});

		_finderPathWithPaginationCountByA_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByA_S",
			new String[] {Long.class.getName(), Integer.class.getName()});

		_finderPathWithPaginationFindByG_U_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByG_U_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			TasksEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TasksEntryModelImpl.USERID_COLUMN_BITMASK |
			TasksEntryModelImpl.STATUS_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByG_U_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

		_finderPathWithPaginationCountByG_U_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

		_finderPathWithPaginationFindByG_A_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_A_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByG_A_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_A_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			TasksEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TasksEntryModelImpl.ASSIGNEEUSERID_COLUMN_BITMASK |
			TasksEntryModelImpl.STATUS_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByG_A_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_A_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

		_finderPathWithPaginationCountByG_A_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_A_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(TasksEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = TMSPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.liferay.tasks.model.TasksEntry"),
			true);
	}

	@Override
	@Reference(
		target = TMSPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = TMSPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_TASKSENTRY =
		"SELECT tasksEntry FROM TasksEntry tasksEntry";

	private static final String _SQL_SELECT_TASKSENTRY_WHERE =
		"SELECT tasksEntry FROM TasksEntry tasksEntry WHERE ";

	private static final String _SQL_COUNT_TASKSENTRY =
		"SELECT COUNT(tasksEntry) FROM TasksEntry tasksEntry";

	private static final String _SQL_COUNT_TASKSENTRY_WHERE =
		"SELECT COUNT(tasksEntry) FROM TasksEntry tasksEntry WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "tasksEntry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TasksEntry exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No TasksEntry exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		TasksEntryPersistenceImpl.class);

	static {
		try {
			Class.forName(TMSPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException classNotFoundException) {
			throw new ExceptionInInitializerError(classNotFoundException);
		}
	}

}