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

package com.liferay.todo.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.todo.exception.NoSuchTodoEntryException;
import com.liferay.todo.model.TodoEntry;
import com.liferay.todo.model.impl.TodoEntryImpl;
import com.liferay.todo.model.impl.TodoEntryModelImpl;
import com.liferay.todo.service.persistence.TodoEntryPersistence;
import com.liferay.todo.service.persistence.impl.constants.TDLPersistenceConstants;

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
 * The persistence implementation for the todo entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @generated
 */
@Component(service = TodoEntryPersistence.class)
public class TodoEntryPersistenceImpl
	extends BasePersistenceImpl<TodoEntry> implements TodoEntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TodoEntryUtil</code> to access the todo entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TodoEntryImpl.class.getName();

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
	 * Returns all the todo entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching todo entries
	 */
	@Override
	public List<TodoEntry> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TodoEntry> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
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
	@Override
	public List<TodoEntry> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
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
	@Override
	public List<TodoEntry> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
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

		List<TodoEntry> list = null;

		if (useFinderCache) {
			list = (List<TodoEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TodoEntry todoEntry : list) {
					if (groupId != todoEntry.getGroupId()) {
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

			query.append(_SQL_SELECT_TODOENTRY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<TodoEntry>)QueryUtil.list(
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
	 * Returns the first todo entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry findByGroupId_First(
			long groupId, OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByGroupId_First(groupId, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByGroupId_First(
		long groupId, OrderByComparator<TodoEntry> orderByComparator) {

		List<TodoEntry> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry findByGroupId_Last(
			long groupId, OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByGroupId_Last(groupId, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByGroupId_Last(
		long groupId, OrderByComparator<TodoEntry> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<TodoEntry> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry[] findByGroupId_PrevAndNext(
			long todoEntryId, long groupId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = findByPrimaryKey(todoEntryId);

		Session session = null;

		try {
			session = openSession();

			TodoEntry[] array = new TodoEntryImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, todoEntry, groupId, orderByComparator, true);

			array[1] = todoEntry;

			array[2] = getByGroupId_PrevAndNext(
				session, todoEntry, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TodoEntry getByGroupId_PrevAndNext(
		Session session, TodoEntry todoEntry, long groupId,
		OrderByComparator<TodoEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TODOENTRY_WHERE);

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
			query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(todoEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TodoEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching todo entries that the user has permission to view
	 */
	@Override
	public List<TodoEntry> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TodoEntry> filterFindByGroupId(
		long groupId, int start, int end) {

		return filterFindByGroupId(groupId, start, end, null);
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
	@Override
	public List<TodoEntry> filterFindByGroupId(
		long groupId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TODOENTRY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TodoEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TodoEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<TodoEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public TodoEntry[] filterFindByGroupId_PrevAndNext(
			long todoEntryId, long groupId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(
				todoEntryId, groupId, orderByComparator);
		}

		TodoEntry todoEntry = findByPrimaryKey(todoEntryId);

		Session session = null;

		try {
			session = openSession();

			TodoEntry[] array = new TodoEntryImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(
				session, todoEntry, groupId, orderByComparator, true);

			array[1] = todoEntry;

			array[2] = filterGetByGroupId_PrevAndNext(
				session, todoEntry, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TodoEntry filterGetByGroupId_PrevAndNext(
		Session session, TodoEntry todoEntry, long groupId,
		OrderByComparator<TodoEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TODOENTRY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TodoEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TodoEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(todoEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TodoEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the todo entries where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (TodoEntry todoEntry :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(todoEntry);
		}
	}

	/**
	 * Returns the number of todo entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching todo entries
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TODOENTRY_WHERE);

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

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching todo entries that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_TODOENTRY_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"todoEntry.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUserId;
	private FinderPath _finderPathWithoutPaginationFindByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns all the todo entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching todo entries
	 */
	@Override
	public List<TodoEntry> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TodoEntry> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
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
	@Override
	public List<TodoEntry> findByUserId(
		long userId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
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
	@Override
	public List<TodoEntry> findByUserId(
		long userId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
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

		List<TodoEntry> list = null;

		if (useFinderCache) {
			list = (List<TodoEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TodoEntry todoEntry : list) {
					if (userId != todoEntry.getUserId()) {
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

			query.append(_SQL_SELECT_TODOENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<TodoEntry>)QueryUtil.list(
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
	 * Returns the first todo entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry findByUserId_First(
			long userId, OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByUserId_First(userId, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the first todo entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByUserId_First(
		long userId, OrderByComparator<TodoEntry> orderByComparator) {

		List<TodoEntry> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last todo entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry findByUserId_Last(
			long userId, OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByUserId_Last(userId, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the last todo entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByUserId_Last(
		long userId, OrderByComparator<TodoEntry> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<TodoEntry> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry[] findByUserId_PrevAndNext(
			long todoEntryId, long userId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = findByPrimaryKey(todoEntryId);

		Session session = null;

		try {
			session = openSession();

			TodoEntry[] array = new TodoEntryImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, todoEntry, userId, orderByComparator, true);

			array[1] = todoEntry;

			array[2] = getByUserId_PrevAndNext(
				session, todoEntry, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TodoEntry getByUserId_PrevAndNext(
		Session session, TodoEntry todoEntry, long userId,
		OrderByComparator<TodoEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TODOENTRY_WHERE);

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
			query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(todoEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TodoEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the todo entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (TodoEntry todoEntry :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(todoEntry);
		}
	}

	/**
	 * Returns the number of todo entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching todo entries
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TODOENTRY_WHERE);

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
		"todoEntry.userId = ?";

	private FinderPath _finderPathWithPaginationFindByAssigneeUserId;
	private FinderPath _finderPathWithoutPaginationFindByAssigneeUserId;
	private FinderPath _finderPathCountByAssigneeUserId;

	/**
	 * Returns all the todo entries where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @return the matching todo entries
	 */
	@Override
	public List<TodoEntry> findByAssigneeUserId(long assigneeUserId) {
		return findByAssigneeUserId(
			assigneeUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TodoEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end) {

		return findByAssigneeUserId(assigneeUserId, start, end, null);
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
	@Override
	public List<TodoEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return findByAssigneeUserId(
			assigneeUserId, start, end, orderByComparator, true);
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
	@Override
	public List<TodoEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
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

		List<TodoEntry> list = null;

		if (useFinderCache) {
			list = (List<TodoEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TodoEntry todoEntry : list) {
					if (assigneeUserId != todoEntry.getAssigneeUserId()) {
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

			query.append(_SQL_SELECT_TODOENTRY_WHERE);

			query.append(_FINDER_COLUMN_ASSIGNEEUSERID_ASSIGNEEUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				list = (List<TodoEntry>)QueryUtil.list(
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
	 * Returns the first todo entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry findByAssigneeUserId_First(
			long assigneeUserId, OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByAssigneeUserId_First(
			assigneeUserId, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the first todo entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByAssigneeUserId_First(
		long assigneeUserId, OrderByComparator<TodoEntry> orderByComparator) {

		List<TodoEntry> list = findByAssigneeUserId(
			assigneeUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last todo entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry findByAssigneeUserId_Last(
			long assigneeUserId, OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByAssigneeUserId_Last(
			assigneeUserId, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the last todo entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByAssigneeUserId_Last(
		long assigneeUserId, OrderByComparator<TodoEntry> orderByComparator) {

		int count = countByAssigneeUserId(assigneeUserId);

		if (count == 0) {
			return null;
		}

		List<TodoEntry> list = findByAssigneeUserId(
			assigneeUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry[] findByAssigneeUserId_PrevAndNext(
			long todoEntryId, long assigneeUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = findByPrimaryKey(todoEntryId);

		Session session = null;

		try {
			session = openSession();

			TodoEntry[] array = new TodoEntryImpl[3];

			array[0] = getByAssigneeUserId_PrevAndNext(
				session, todoEntry, assigneeUserId, orderByComparator, true);

			array[1] = todoEntry;

			array[2] = getByAssigneeUserId_PrevAndNext(
				session, todoEntry, assigneeUserId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TodoEntry getByAssigneeUserId_PrevAndNext(
		Session session, TodoEntry todoEntry, long assigneeUserId,
		OrderByComparator<TodoEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TODOENTRY_WHERE);

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
			query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assigneeUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(todoEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TodoEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the todo entries where assigneeUserId = &#63; from the database.
	 *
	 * @param assigneeUserId the assignee user ID
	 */
	@Override
	public void removeByAssigneeUserId(long assigneeUserId) {
		for (TodoEntry todoEntry :
				findByAssigneeUserId(
					assigneeUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(todoEntry);
		}
	}

	/**
	 * Returns the number of todo entries where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @return the number of matching todo entries
	 */
	@Override
	public int countByAssigneeUserId(long assigneeUserId) {
		FinderPath finderPath = _finderPathCountByAssigneeUserId;

		Object[] finderArgs = new Object[] {assigneeUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TODOENTRY_WHERE);

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
		"todoEntry.assigneeUserId = ?";

	private FinderPath _finderPathWithPaginationFindByResolverUserId;
	private FinderPath _finderPathWithoutPaginationFindByResolverUserId;
	private FinderPath _finderPathCountByResolverUserId;

	/**
	 * Returns all the todo entries where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @return the matching todo entries
	 */
	@Override
	public List<TodoEntry> findByResolverUserId(long resolverUserId) {
		return findByResolverUserId(
			resolverUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TodoEntry> findByResolverUserId(
		long resolverUserId, int start, int end) {

		return findByResolverUserId(resolverUserId, start, end, null);
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
	@Override
	public List<TodoEntry> findByResolverUserId(
		long resolverUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return findByResolverUserId(
			resolverUserId, start, end, orderByComparator, true);
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
	@Override
	public List<TodoEntry> findByResolverUserId(
		long resolverUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
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

		List<TodoEntry> list = null;

		if (useFinderCache) {
			list = (List<TodoEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TodoEntry todoEntry : list) {
					if (resolverUserId != todoEntry.getResolverUserId()) {
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

			query.append(_SQL_SELECT_TODOENTRY_WHERE);

			query.append(_FINDER_COLUMN_RESOLVERUSERID_RESOLVERUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resolverUserId);

				list = (List<TodoEntry>)QueryUtil.list(
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
	 * Returns the first todo entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry findByResolverUserId_First(
			long resolverUserId, OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByResolverUserId_First(
			resolverUserId, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resolverUserId=");
		msg.append(resolverUserId);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the first todo entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByResolverUserId_First(
		long resolverUserId, OrderByComparator<TodoEntry> orderByComparator) {

		List<TodoEntry> list = findByResolverUserId(
			resolverUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last todo entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry findByResolverUserId_Last(
			long resolverUserId, OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByResolverUserId_Last(
			resolverUserId, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resolverUserId=");
		msg.append(resolverUserId);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the last todo entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByResolverUserId_Last(
		long resolverUserId, OrderByComparator<TodoEntry> orderByComparator) {

		int count = countByResolverUserId(resolverUserId);

		if (count == 0) {
			return null;
		}

		List<TodoEntry> list = findByResolverUserId(
			resolverUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry[] findByResolverUserId_PrevAndNext(
			long todoEntryId, long resolverUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = findByPrimaryKey(todoEntryId);

		Session session = null;

		try {
			session = openSession();

			TodoEntry[] array = new TodoEntryImpl[3];

			array[0] = getByResolverUserId_PrevAndNext(
				session, todoEntry, resolverUserId, orderByComparator, true);

			array[1] = todoEntry;

			array[2] = getByResolverUserId_PrevAndNext(
				session, todoEntry, resolverUserId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TodoEntry getByResolverUserId_PrevAndNext(
		Session session, TodoEntry todoEntry, long resolverUserId,
		OrderByComparator<TodoEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TODOENTRY_WHERE);

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
			query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resolverUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(todoEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TodoEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the todo entries where resolverUserId = &#63; from the database.
	 *
	 * @param resolverUserId the resolver user ID
	 */
	@Override
	public void removeByResolverUserId(long resolverUserId) {
		for (TodoEntry todoEntry :
				findByResolverUserId(
					resolverUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(todoEntry);
		}
	}

	/**
	 * Returns the number of todo entries where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @return the number of matching todo entries
	 */
	@Override
	public int countByResolverUserId(long resolverUserId) {
		FinderPath finderPath = _finderPathCountByResolverUserId;

		Object[] finderArgs = new Object[] {resolverUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TODOENTRY_WHERE);

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
		"todoEntry.resolverUserId = ?";

	private FinderPath _finderPathWithPaginationFindByG_U;
	private FinderPath _finderPathWithoutPaginationFindByG_U;
	private FinderPath _finderPathCountByG_U;

	/**
	 * Returns all the todo entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching todo entries
	 */
	@Override
	public List<TodoEntry> findByG_U(long groupId, long userId) {
		return findByG_U(
			groupId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TodoEntry> findByG_U(
		long groupId, long userId, int start, int end) {

		return findByG_U(groupId, userId, start, end, null);
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
	@Override
	public List<TodoEntry> findByG_U(
		long groupId, long userId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return findByG_U(groupId, userId, start, end, orderByComparator, true);
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
	@Override
	public List<TodoEntry> findByG_U(
		long groupId, long userId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
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

		List<TodoEntry> list = null;

		if (useFinderCache) {
			list = (List<TodoEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TodoEntry todoEntry : list) {
					if ((groupId != todoEntry.getGroupId()) ||
						(userId != todoEntry.getUserId())) {

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

			query.append(_SQL_SELECT_TODOENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				list = (List<TodoEntry>)QueryUtil.list(
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
	 * Returns the first todo entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry findByG_U_First(
			long groupId, long userId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByG_U_First(
			groupId, userId, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByG_U_First(
		long groupId, long userId,
		OrderByComparator<TodoEntry> orderByComparator) {

		List<TodoEntry> list = findByG_U(
			groupId, userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry findByG_U_Last(
			long groupId, long userId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByG_U_Last(
			groupId, userId, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByG_U_Last(
		long groupId, long userId,
		OrderByComparator<TodoEntry> orderByComparator) {

		int count = countByG_U(groupId, userId);

		if (count == 0) {
			return null;
		}

		List<TodoEntry> list = findByG_U(
			groupId, userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry[] findByG_U_PrevAndNext(
			long todoEntryId, long groupId, long userId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = findByPrimaryKey(todoEntryId);

		Session session = null;

		try {
			session = openSession();

			TodoEntry[] array = new TodoEntryImpl[3];

			array[0] = getByG_U_PrevAndNext(
				session, todoEntry, groupId, userId, orderByComparator, true);

			array[1] = todoEntry;

			array[2] = getByG_U_PrevAndNext(
				session, todoEntry, groupId, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TodoEntry getByG_U_PrevAndNext(
		Session session, TodoEntry todoEntry, long groupId, long userId,
		OrderByComparator<TodoEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TODOENTRY_WHERE);

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
			query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(todoEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TodoEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching todo entries that the user has permission to view
	 */
	@Override
	public List<TodoEntry> filterFindByG_U(long groupId, long userId) {
		return filterFindByG_U(
			groupId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TodoEntry> filterFindByG_U(
		long groupId, long userId, int start, int end) {

		return filterFindByG_U(groupId, userId, start, end, null);
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
	@Override
	public List<TodoEntry> filterFindByG_U(
		long groupId, long userId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_U(groupId, userId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TODOENTRY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TodoEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TodoEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

			return (List<TodoEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public TodoEntry[] filterFindByG_U_PrevAndNext(
			long todoEntryId, long groupId, long userId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_U_PrevAndNext(
				todoEntryId, groupId, userId, orderByComparator);
		}

		TodoEntry todoEntry = findByPrimaryKey(todoEntryId);

		Session session = null;

		try {
			session = openSession();

			TodoEntry[] array = new TodoEntryImpl[3];

			array[0] = filterGetByG_U_PrevAndNext(
				session, todoEntry, groupId, userId, orderByComparator, true);

			array[1] = todoEntry;

			array[2] = filterGetByG_U_PrevAndNext(
				session, todoEntry, groupId, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TodoEntry filterGetByG_U_PrevAndNext(
		Session session, TodoEntry todoEntry, long groupId, long userId,
		OrderByComparator<TodoEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TODOENTRY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TodoEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TodoEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(todoEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TodoEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the todo entries where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 */
	@Override
	public void removeByG_U(long groupId, long userId) {
		for (TodoEntry todoEntry :
				findByG_U(
					groupId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(todoEntry);
		}
	}

	/**
	 * Returns the number of todo entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching todo entries
	 */
	@Override
	public int countByG_U(long groupId, long userId) {
		FinderPath finderPath = _finderPathCountByG_U;

		Object[] finderArgs = new Object[] {groupId, userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TODOENTRY_WHERE);

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

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching todo entries that the user has permission to view
	 */
	@Override
	public int filterCountByG_U(long groupId, long userId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_U(groupId, userId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_TODOENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_U_GROUPID_2 =
		"todoEntry.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_U_USERID_2 =
		"todoEntry.userId = ?";

	private FinderPath _finderPathWithPaginationFindByG_A;
	private FinderPath _finderPathWithoutPaginationFindByG_A;
	private FinderPath _finderPathCountByG_A;

	/**
	 * Returns all the todo entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the matching todo entries
	 */
	@Override
	public List<TodoEntry> findByG_A(long groupId, long assigneeUserId) {
		return findByG_A(
			groupId, assigneeUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<TodoEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end) {

		return findByG_A(groupId, assigneeUserId, start, end, null);
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
	@Override
	public List<TodoEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return findByG_A(
			groupId, assigneeUserId, start, end, orderByComparator, true);
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
	@Override
	public List<TodoEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
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

		List<TodoEntry> list = null;

		if (useFinderCache) {
			list = (List<TodoEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TodoEntry todoEntry : list) {
					if ((groupId != todoEntry.getGroupId()) ||
						(assigneeUserId != todoEntry.getAssigneeUserId())) {

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

			query.append(_SQL_SELECT_TODOENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_A_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

				list = (List<TodoEntry>)QueryUtil.list(
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
	 * Returns the first todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry findByG_A_First(
			long groupId, long assigneeUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByG_A_First(
			groupId, assigneeUserId, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByG_A_First(
		long groupId, long assigneeUserId,
		OrderByComparator<TodoEntry> orderByComparator) {

		List<TodoEntry> list = findByG_A(
			groupId, assigneeUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry findByG_A_Last(
			long groupId, long assigneeUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByG_A_Last(
			groupId, assigneeUserId, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByG_A_Last(
		long groupId, long assigneeUserId,
		OrderByComparator<TodoEntry> orderByComparator) {

		int count = countByG_A(groupId, assigneeUserId);

		if (count == 0) {
			return null;
		}

		List<TodoEntry> list = findByG_A(
			groupId, assigneeUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry[] findByG_A_PrevAndNext(
			long todoEntryId, long groupId, long assigneeUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = findByPrimaryKey(todoEntryId);

		Session session = null;

		try {
			session = openSession();

			TodoEntry[] array = new TodoEntryImpl[3];

			array[0] = getByG_A_PrevAndNext(
				session, todoEntry, groupId, assigneeUserId, orderByComparator,
				true);

			array[1] = todoEntry;

			array[2] = getByG_A_PrevAndNext(
				session, todoEntry, groupId, assigneeUserId, orderByComparator,
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

	protected TodoEntry getByG_A_PrevAndNext(
		Session session, TodoEntry todoEntry, long groupId, long assigneeUserId,
		OrderByComparator<TodoEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TODOENTRY_WHERE);

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
			query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(todoEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TodoEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the matching todo entries that the user has permission to view
	 */
	@Override
	public List<TodoEntry> filterFindByG_A(long groupId, long assigneeUserId) {
		return filterFindByG_A(
			groupId, assigneeUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<TodoEntry> filterFindByG_A(
		long groupId, long assigneeUserId, int start, int end) {

		return filterFindByG_A(groupId, assigneeUserId, start, end, null);
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
	@Override
	public List<TodoEntry> filterFindByG_A(
		long groupId, long assigneeUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A(
				groupId, assigneeUserId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TODOENTRY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TodoEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TodoEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(assigneeUserId);

			return (List<TodoEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public TodoEntry[] filterFindByG_A_PrevAndNext(
			long todoEntryId, long groupId, long assigneeUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A_PrevAndNext(
				todoEntryId, groupId, assigneeUserId, orderByComparator);
		}

		TodoEntry todoEntry = findByPrimaryKey(todoEntryId);

		Session session = null;

		try {
			session = openSession();

			TodoEntry[] array = new TodoEntryImpl[3];

			array[0] = filterGetByG_A_PrevAndNext(
				session, todoEntry, groupId, assigneeUserId, orderByComparator,
				true);

			array[1] = todoEntry;

			array[2] = filterGetByG_A_PrevAndNext(
				session, todoEntry, groupId, assigneeUserId, orderByComparator,
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

	protected TodoEntry filterGetByG_A_PrevAndNext(
		Session session, TodoEntry todoEntry, long groupId, long assigneeUserId,
		OrderByComparator<TodoEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TODOENTRY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TodoEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TodoEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(assigneeUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(todoEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TodoEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the todo entries where groupId = &#63; and assigneeUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 */
	@Override
	public void removeByG_A(long groupId, long assigneeUserId) {
		for (TodoEntry todoEntry :
				findByG_A(
					groupId, assigneeUserId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(todoEntry);
		}
	}

	/**
	 * Returns the number of todo entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the number of matching todo entries
	 */
	@Override
	public int countByG_A(long groupId, long assigneeUserId) {
		FinderPath finderPath = _finderPathCountByG_A;

		Object[] finderArgs = new Object[] {groupId, assigneeUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TODOENTRY_WHERE);

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

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the number of matching todo entries that the user has permission to view
	 */
	@Override
	public int filterCountByG_A(long groupId, long assigneeUserId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_A(groupId, assigneeUserId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_TODOENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(assigneeUserId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_A_GROUPID_2 =
		"todoEntry.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_A_ASSIGNEEUSERID_2 =
		"todoEntry.assigneeUserId = ?";

	private FinderPath _finderPathWithPaginationFindByG_R;
	private FinderPath _finderPathWithoutPaginationFindByG_R;
	private FinderPath _finderPathCountByG_R;

	/**
	 * Returns all the todo entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the matching todo entries
	 */
	@Override
	public List<TodoEntry> findByG_R(long groupId, long resolverUserId) {
		return findByG_R(
			groupId, resolverUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<TodoEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end) {

		return findByG_R(groupId, resolverUserId, start, end, null);
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
	@Override
	public List<TodoEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return findByG_R(
			groupId, resolverUserId, start, end, orderByComparator, true);
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
	@Override
	public List<TodoEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
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

		List<TodoEntry> list = null;

		if (useFinderCache) {
			list = (List<TodoEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TodoEntry todoEntry : list) {
					if ((groupId != todoEntry.getGroupId()) ||
						(resolverUserId != todoEntry.getResolverUserId())) {

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

			query.append(_SQL_SELECT_TODOENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_R_GROUPID_2);

			query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(resolverUserId);

				list = (List<TodoEntry>)QueryUtil.list(
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
	 * Returns the first todo entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry findByG_R_First(
			long groupId, long resolverUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByG_R_First(
			groupId, resolverUserId, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", resolverUserId=");
		msg.append(resolverUserId);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the first todo entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByG_R_First(
		long groupId, long resolverUserId,
		OrderByComparator<TodoEntry> orderByComparator) {

		List<TodoEntry> list = findByG_R(
			groupId, resolverUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry findByG_R_Last(
			long groupId, long resolverUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByG_R_Last(
			groupId, resolverUserId, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", resolverUserId=");
		msg.append(resolverUserId);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the last todo entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByG_R_Last(
		long groupId, long resolverUserId,
		OrderByComparator<TodoEntry> orderByComparator) {

		int count = countByG_R(groupId, resolverUserId);

		if (count == 0) {
			return null;
		}

		List<TodoEntry> list = findByG_R(
			groupId, resolverUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry[] findByG_R_PrevAndNext(
			long todoEntryId, long groupId, long resolverUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = findByPrimaryKey(todoEntryId);

		Session session = null;

		try {
			session = openSession();

			TodoEntry[] array = new TodoEntryImpl[3];

			array[0] = getByG_R_PrevAndNext(
				session, todoEntry, groupId, resolverUserId, orderByComparator,
				true);

			array[1] = todoEntry;

			array[2] = getByG_R_PrevAndNext(
				session, todoEntry, groupId, resolverUserId, orderByComparator,
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

	protected TodoEntry getByG_R_PrevAndNext(
		Session session, TodoEntry todoEntry, long groupId, long resolverUserId,
		OrderByComparator<TodoEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TODOENTRY_WHERE);

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
			query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(todoEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TodoEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the matching todo entries that the user has permission to view
	 */
	@Override
	public List<TodoEntry> filterFindByG_R(long groupId, long resolverUserId) {
		return filterFindByG_R(
			groupId, resolverUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<TodoEntry> filterFindByG_R(
		long groupId, long resolverUserId, int start, int end) {

		return filterFindByG_R(groupId, resolverUserId, start, end, null);
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
	@Override
	public List<TodoEntry> filterFindByG_R(
		long groupId, long resolverUserId, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_R(
				groupId, resolverUserId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TODOENTRY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_R_GROUPID_2);

		query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TodoEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TodoEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(resolverUserId);

			return (List<TodoEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public TodoEntry[] filterFindByG_R_PrevAndNext(
			long todoEntryId, long groupId, long resolverUserId,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_R_PrevAndNext(
				todoEntryId, groupId, resolverUserId, orderByComparator);
		}

		TodoEntry todoEntry = findByPrimaryKey(todoEntryId);

		Session session = null;

		try {
			session = openSession();

			TodoEntry[] array = new TodoEntryImpl[3];

			array[0] = filterGetByG_R_PrevAndNext(
				session, todoEntry, groupId, resolverUserId, orderByComparator,
				true);

			array[1] = todoEntry;

			array[2] = filterGetByG_R_PrevAndNext(
				session, todoEntry, groupId, resolverUserId, orderByComparator,
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

	protected TodoEntry filterGetByG_R_PrevAndNext(
		Session session, TodoEntry todoEntry, long groupId, long resolverUserId,
		OrderByComparator<TodoEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TODOENTRY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_R_GROUPID_2);

		query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TodoEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TodoEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(resolverUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(todoEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TodoEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the todo entries where groupId = &#63; and resolverUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 */
	@Override
	public void removeByG_R(long groupId, long resolverUserId) {
		for (TodoEntry todoEntry :
				findByG_R(
					groupId, resolverUserId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(todoEntry);
		}
	}

	/**
	 * Returns the number of todo entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the number of matching todo entries
	 */
	@Override
	public int countByG_R(long groupId, long resolverUserId) {
		FinderPath finderPath = _finderPathCountByG_R;

		Object[] finderArgs = new Object[] {groupId, resolverUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TODOENTRY_WHERE);

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

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the number of matching todo entries that the user has permission to view
	 */
	@Override
	public int filterCountByG_R(long groupId, long resolverUserId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_R(groupId, resolverUserId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_TODOENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_R_GROUPID_2);

		query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(resolverUserId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_R_GROUPID_2 =
		"todoEntry.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_R_RESOLVERUSERID_2 =
		"todoEntry.resolverUserId = ?";

	private FinderPath _finderPathWithPaginationFindByU_S;
	private FinderPath _finderPathWithoutPaginationFindByU_S;
	private FinderPath _finderPathCountByU_S;
	private FinderPath _finderPathWithPaginationCountByU_S;

	/**
	 * Returns all the todo entries where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching todo entries
	 */
	@Override
	public List<TodoEntry> findByU_S(long userId, int status) {
		return findByU_S(
			userId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TodoEntry> findByU_S(
		long userId, int status, int start, int end) {

		return findByU_S(userId, status, start, end, null);
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
	@Override
	public List<TodoEntry> findByU_S(
		long userId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return findByU_S(userId, status, start, end, orderByComparator, true);
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
	@Override
	public List<TodoEntry> findByU_S(
		long userId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
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

		List<TodoEntry> list = null;

		if (useFinderCache) {
			list = (List<TodoEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TodoEntry todoEntry : list) {
					if ((userId != todoEntry.getUserId()) ||
						(status != todoEntry.getStatus())) {

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

			query.append(_SQL_SELECT_TODOENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_S_USERID_2);

			query.append(_FINDER_COLUMN_U_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(status);

				list = (List<TodoEntry>)QueryUtil.list(
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
	 * Returns the first todo entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry findByU_S_First(
			long userId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByU_S_First(
			userId, status, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the first todo entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByU_S_First(
		long userId, int status,
		OrderByComparator<TodoEntry> orderByComparator) {

		List<TodoEntry> list = findByU_S(
			userId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry findByU_S_Last(
			long userId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByU_S_Last(
			userId, status, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the last todo entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByU_S_Last(
		long userId, int status,
		OrderByComparator<TodoEntry> orderByComparator) {

		int count = countByU_S(userId, status);

		if (count == 0) {
			return null;
		}

		List<TodoEntry> list = findByU_S(
			userId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry[] findByU_S_PrevAndNext(
			long todoEntryId, long userId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = findByPrimaryKey(todoEntryId);

		Session session = null;

		try {
			session = openSession();

			TodoEntry[] array = new TodoEntryImpl[3];

			array[0] = getByU_S_PrevAndNext(
				session, todoEntry, userId, status, orderByComparator, true);

			array[1] = todoEntry;

			array[2] = getByU_S_PrevAndNext(
				session, todoEntry, userId, status, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TodoEntry getByU_S_PrevAndNext(
		Session session, TodoEntry todoEntry, long userId, int status,
		OrderByComparator<TodoEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TODOENTRY_WHERE);

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
			query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(todoEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TodoEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
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
	@Override
	public List<TodoEntry> findByU_S(long userId, int[] statuses) {
		return findByU_S(
			userId, statuses, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TodoEntry> findByU_S(
		long userId, int[] statuses, int start, int end) {

		return findByU_S(userId, statuses, start, end, null);
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
	@Override
	public List<TodoEntry> findByU_S(
		long userId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return findByU_S(userId, statuses, start, end, orderByComparator, true);
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
	@Override
	public List<TodoEntry> findByU_S(
		long userId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
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

		List<TodoEntry> list = null;

		if (useFinderCache) {
			list = (List<TodoEntry>)finderCache.getResult(
				_finderPathWithPaginationFindByU_S, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TodoEntry todoEntry : list) {
					if ((userId != todoEntry.getUserId()) ||
						!ArrayUtil.contains(statuses, todoEntry.getStatus())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TODOENTRY_WHERE);

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
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<TodoEntry>)QueryUtil.list(
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
	 * Removes all the todo entries where userId = &#63; and status = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param status the status
	 */
	@Override
	public void removeByU_S(long userId, int status) {
		for (TodoEntry todoEntry :
				findByU_S(
					userId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(todoEntry);
		}
	}

	/**
	 * Returns the number of todo entries where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching todo entries
	 */
	@Override
	public int countByU_S(long userId, int status) {
		FinderPath finderPath = _finderPathCountByU_S;

		Object[] finderArgs = new Object[] {userId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TODOENTRY_WHERE);

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
	 * Returns the number of todo entries where userId = &#63; and status = any &#63;.
	 *
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries
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

			query.append(_SQL_COUNT_TODOENTRY_WHERE);

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
		"todoEntry.userId = ? AND ";

	private static final String _FINDER_COLUMN_U_S_STATUS_2 =
		"todoEntry.status = ?";

	private static final String _FINDER_COLUMN_U_S_STATUS_7 =
		"todoEntry.status IN (";

	private FinderPath _finderPathWithPaginationFindByA_S;
	private FinderPath _finderPathWithoutPaginationFindByA_S;
	private FinderPath _finderPathCountByA_S;
	private FinderPath _finderPathWithPaginationCountByA_S;

	/**
	 * Returns all the todo entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the matching todo entries
	 */
	@Override
	public List<TodoEntry> findByA_S(long assigneeUserId, int status) {
		return findByA_S(
			assigneeUserId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TodoEntry> findByA_S(
		long assigneeUserId, int status, int start, int end) {

		return findByA_S(assigneeUserId, status, start, end, null);
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
	@Override
	public List<TodoEntry> findByA_S(
		long assigneeUserId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return findByA_S(
			assigneeUserId, status, start, end, orderByComparator, true);
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
	@Override
	public List<TodoEntry> findByA_S(
		long assigneeUserId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
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

		List<TodoEntry> list = null;

		if (useFinderCache) {
			list = (List<TodoEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TodoEntry todoEntry : list) {
					if ((assigneeUserId != todoEntry.getAssigneeUserId()) ||
						(status != todoEntry.getStatus())) {

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

			query.append(_SQL_SELECT_TODOENTRY_WHERE);

			query.append(_FINDER_COLUMN_A_S_ASSIGNEEUSERID_2);

			query.append(_FINDER_COLUMN_A_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				qPos.add(status);

				list = (List<TodoEntry>)QueryUtil.list(
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
	 * Returns the first todo entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry findByA_S_First(
			long assigneeUserId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByA_S_First(
			assigneeUserId, status, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the first todo entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByA_S_First(
		long assigneeUserId, int status,
		OrderByComparator<TodoEntry> orderByComparator) {

		List<TodoEntry> list = findByA_S(
			assigneeUserId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry findByA_S_Last(
			long assigneeUserId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByA_S_Last(
			assigneeUserId, status, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchTodoEntryException(msg.toString());
	}

	/**
	 * Returns the last todo entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching todo entry, or <code>null</code> if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry fetchByA_S_Last(
		long assigneeUserId, int status,
		OrderByComparator<TodoEntry> orderByComparator) {

		int count = countByA_S(assigneeUserId, status);

		if (count == 0) {
			return null;
		}

		List<TodoEntry> list = findByA_S(
			assigneeUserId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry[] findByA_S_PrevAndNext(
			long todoEntryId, long assigneeUserId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = findByPrimaryKey(todoEntryId);

		Session session = null;

		try {
			session = openSession();

			TodoEntry[] array = new TodoEntryImpl[3];

			array[0] = getByA_S_PrevAndNext(
				session, todoEntry, assigneeUserId, status, orderByComparator,
				true);

			array[1] = todoEntry;

			array[2] = getByA_S_PrevAndNext(
				session, todoEntry, assigneeUserId, status, orderByComparator,
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

	protected TodoEntry getByA_S_PrevAndNext(
		Session session, TodoEntry todoEntry, long assigneeUserId, int status,
		OrderByComparator<TodoEntry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TODOENTRY_WHERE);

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
			query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(todoEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TodoEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
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
	@Override
	public List<TodoEntry> findByA_S(long assigneeUserId, int[] statuses) {
		return findByA_S(
			assigneeUserId, statuses, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<TodoEntry> findByA_S(
		long assigneeUserId, int[] statuses, int start, int end) {

		return findByA_S(assigneeUserId, statuses, start, end, null);
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
	@Override
	public List<TodoEntry> findByA_S(
		long assigneeUserId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return findByA_S(
			assigneeUserId, statuses, start, end, orderByComparator, true);
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
	@Override
	public List<TodoEntry> findByA_S(
		long assigneeUserId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
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

		List<TodoEntry> list = null;

		if (useFinderCache) {
			list = (List<TodoEntry>)finderCache.getResult(
				_finderPathWithPaginationFindByA_S, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TodoEntry todoEntry : list) {
					if ((assigneeUserId != todoEntry.getAssigneeUserId()) ||
						!ArrayUtil.contains(statuses, todoEntry.getStatus())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TODOENTRY_WHERE);

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
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				list = (List<TodoEntry>)QueryUtil.list(
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
	 * Removes all the todo entries where assigneeUserId = &#63; and status = &#63; from the database.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 */
	@Override
	public void removeByA_S(long assigneeUserId, int status) {
		for (TodoEntry todoEntry :
				findByA_S(
					assigneeUserId, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(todoEntry);
		}
	}

	/**
	 * Returns the number of todo entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the number of matching todo entries
	 */
	@Override
	public int countByA_S(long assigneeUserId, int status) {
		FinderPath finderPath = _finderPathCountByA_S;

		Object[] finderArgs = new Object[] {assigneeUserId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TODOENTRY_WHERE);

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
	 * Returns the number of todo entries where assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries
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

			query.append(_SQL_COUNT_TODOENTRY_WHERE);

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
		"todoEntry.assigneeUserId = ? AND ";

	private static final String _FINDER_COLUMN_A_S_STATUS_2 =
		"todoEntry.status = ?";

	private static final String _FINDER_COLUMN_A_S_STATUS_7 =
		"todoEntry.status IN (";

	private FinderPath _finderPathWithPaginationFindByG_U_S;
	private FinderPath _finderPathWithoutPaginationFindByG_U_S;
	private FinderPath _finderPathCountByG_U_S;
	private FinderPath _finderPathWithPaginationCountByG_U_S;

	/**
	 * Returns all the todo entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching todo entries
	 */
	@Override
	public List<TodoEntry> findByG_U_S(long groupId, long userId, int status) {
		return findByG_U_S(
			groupId, userId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<TodoEntry> findByG_U_S(
		long groupId, long userId, int status, int start, int end) {

		return findByG_U_S(groupId, userId, status, start, end, null);
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
	@Override
	public List<TodoEntry> findByG_U_S(
		long groupId, long userId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return findByG_U_S(
			groupId, userId, status, start, end, orderByComparator, true);
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
	@Override
	public List<TodoEntry> findByG_U_S(
		long groupId, long userId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
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

		List<TodoEntry> list = null;

		if (useFinderCache) {
			list = (List<TodoEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TodoEntry todoEntry : list) {
					if ((groupId != todoEntry.getGroupId()) ||
						(userId != todoEntry.getUserId()) ||
						(status != todoEntry.getStatus())) {

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

			query.append(_SQL_SELECT_TODOENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_S_USERID_2);

			query.append(_FINDER_COLUMN_G_U_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
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

				list = (List<TodoEntry>)QueryUtil.list(
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
	 * Returns the first todo entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry findByG_U_S_First(
			long groupId, long userId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByG_U_S_First(
			groupId, userId, status, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
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

		throw new NoSuchTodoEntryException(msg.toString());
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
	@Override
	public TodoEntry fetchByG_U_S_First(
		long groupId, long userId, int status,
		OrderByComparator<TodoEntry> orderByComparator) {

		List<TodoEntry> list = findByG_U_S(
			groupId, userId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry findByG_U_S_Last(
			long groupId, long userId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByG_U_S_Last(
			groupId, userId, status, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
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

		throw new NoSuchTodoEntryException(msg.toString());
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
	@Override
	public TodoEntry fetchByG_U_S_Last(
		long groupId, long userId, int status,
		OrderByComparator<TodoEntry> orderByComparator) {

		int count = countByG_U_S(groupId, userId, status);

		if (count == 0) {
			return null;
		}

		List<TodoEntry> list = findByG_U_S(
			groupId, userId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry[] findByG_U_S_PrevAndNext(
			long todoEntryId, long groupId, long userId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = findByPrimaryKey(todoEntryId);

		Session session = null;

		try {
			session = openSession();

			TodoEntry[] array = new TodoEntryImpl[3];

			array[0] = getByG_U_S_PrevAndNext(
				session, todoEntry, groupId, userId, status, orderByComparator,
				true);

			array[1] = todoEntry;

			array[2] = getByG_U_S_PrevAndNext(
				session, todoEntry, groupId, userId, status, orderByComparator,
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

	protected TodoEntry getByG_U_S_PrevAndNext(
		Session session, TodoEntry todoEntry, long groupId, long userId,
		int status, OrderByComparator<TodoEntry> orderByComparator,
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

		query.append(_SQL_SELECT_TODOENTRY_WHERE);

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
			query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(todoEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TodoEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching todo entries that the user has permission to view
	 */
	@Override
	public List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int status) {

		return filterFindByG_U_S(
			groupId, userId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int status, int start, int end) {

		return filterFindByG_U_S(groupId, userId, status, start, end, null);
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
	@Override
	public List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_U_S(
				groupId, userId, status, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TODOENTRY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_S_USERID_2);

		query.append(_FINDER_COLUMN_G_U_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TodoEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TodoEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

			qPos.add(status);

			return (List<TodoEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public TodoEntry[] filterFindByG_U_S_PrevAndNext(
			long todoEntryId, long groupId, long userId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_U_S_PrevAndNext(
				todoEntryId, groupId, userId, status, orderByComparator);
		}

		TodoEntry todoEntry = findByPrimaryKey(todoEntryId);

		Session session = null;

		try {
			session = openSession();

			TodoEntry[] array = new TodoEntryImpl[3];

			array[0] = filterGetByG_U_S_PrevAndNext(
				session, todoEntry, groupId, userId, status, orderByComparator,
				true);

			array[1] = todoEntry;

			array[2] = filterGetByG_U_S_PrevAndNext(
				session, todoEntry, groupId, userId, status, orderByComparator,
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

	protected TodoEntry filterGetByG_U_S_PrevAndNext(
		Session session, TodoEntry todoEntry, long groupId, long userId,
		int status, OrderByComparator<TodoEntry> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				7 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TODOENTRY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_S_USERID_2);

		query.append(_FINDER_COLUMN_G_U_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TodoEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TodoEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(todoEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TodoEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the matching todo entries that the user has permission to view
	 */
	@Override
	public List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int[] statuses) {

		return filterFindByG_U_S(
			groupId, userId, statuses, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end) {

		return filterFindByG_U_S(groupId, userId, statuses, start, end, null);
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
	@Override
	public List<TodoEntry> filterFindByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_U_S(
				groupId, userId, statuses, start, end, orderByComparator);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.sortedUnique(statuses);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TODOENTRY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

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

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TodoEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TodoEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

			return (List<TodoEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public List<TodoEntry> findByG_U_S(
		long groupId, long userId, int[] statuses) {

		return findByG_U_S(
			groupId, userId, statuses, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<TodoEntry> findByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end) {

		return findByG_U_S(groupId, userId, statuses, start, end, null);
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
	@Override
	public List<TodoEntry> findByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return findByG_U_S(
			groupId, userId, statuses, start, end, orderByComparator, true);
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
	@Override
	public List<TodoEntry> findByG_U_S(
		long groupId, long userId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
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

		List<TodoEntry> list = null;

		if (useFinderCache) {
			list = (List<TodoEntry>)finderCache.getResult(
				_finderPathWithPaginationFindByG_U_S, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TodoEntry todoEntry : list) {
					if ((groupId != todoEntry.getGroupId()) ||
						(userId != todoEntry.getUserId()) ||
						!ArrayUtil.contains(statuses, todoEntry.getStatus())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TODOENTRY_WHERE);

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
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				list = (List<TodoEntry>)QueryUtil.list(
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
	 * Removes all the todo entries where groupId = &#63; and userId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 */
	@Override
	public void removeByG_U_S(long groupId, long userId, int status) {
		for (TodoEntry todoEntry :
				findByG_U_S(
					groupId, userId, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(todoEntry);
		}
	}

	/**
	 * Returns the number of todo entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching todo entries
	 */
	@Override
	public int countByG_U_S(long groupId, long userId, int status) {
		FinderPath finderPath = _finderPathCountByG_U_S;

		Object[] finderArgs = new Object[] {groupId, userId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TODOENTRY_WHERE);

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
	 * Returns the number of todo entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries
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

			query.append(_SQL_COUNT_TODOENTRY_WHERE);

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

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching todo entries that the user has permission to view
	 */
	@Override
	public int filterCountByG_U_S(long groupId, long userId, int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_U_S(groupId, userId, status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_TODOENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_S_USERID_2);

		query.append(_FINDER_COLUMN_G_U_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

			qPos.add(status);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries that the user has permission to view
	 */
	@Override
	public int filterCountByG_U_S(long groupId, long userId, int[] statuses) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_U_S(groupId, userId, statuses);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.sortedUnique(statuses);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_TODOENTRY_WHERE);

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

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_U_S_GROUPID_2 =
		"todoEntry.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_U_S_USERID_2 =
		"todoEntry.userId = ? AND ";

	private static final String _FINDER_COLUMN_G_U_S_STATUS_2 =
		"todoEntry.status = ?";

	private static final String _FINDER_COLUMN_G_U_S_STATUS_7 =
		"todoEntry.status IN (";

	private FinderPath _finderPathWithPaginationFindByG_A_S;
	private FinderPath _finderPathWithoutPaginationFindByG_A_S;
	private FinderPath _finderPathCountByG_A_S;
	private FinderPath _finderPathWithPaginationCountByG_A_S;

	/**
	 * Returns all the todo entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the matching todo entries
	 */
	@Override
	public List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status) {

		return findByG_A_S(
			groupId, assigneeUserId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end) {

		return findByG_A_S(groupId, assigneeUserId, status, start, end, null);
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
	@Override
	public List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return findByG_A_S(
			groupId, assigneeUserId, status, start, end, orderByComparator,
			true);
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
	@Override
	public List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
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

		List<TodoEntry> list = null;

		if (useFinderCache) {
			list = (List<TodoEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TodoEntry todoEntry : list) {
					if ((groupId != todoEntry.getGroupId()) ||
						(assigneeUserId != todoEntry.getAssigneeUserId()) ||
						(status != todoEntry.getStatus())) {

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

			query.append(_SQL_SELECT_TODOENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

			query.append(_FINDER_COLUMN_G_A_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
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

				list = (List<TodoEntry>)QueryUtil.list(
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
	 * Returns the first todo entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching todo entry
	 * @throws NoSuchTodoEntryException if a matching todo entry could not be found
	 */
	@Override
	public TodoEntry findByG_A_S_First(
			long groupId, long assigneeUserId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByG_A_S_First(
			groupId, assigneeUserId, status, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
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

		throw new NoSuchTodoEntryException(msg.toString());
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
	@Override
	public TodoEntry fetchByG_A_S_First(
		long groupId, long assigneeUserId, int status,
		OrderByComparator<TodoEntry> orderByComparator) {

		List<TodoEntry> list = findByG_A_S(
			groupId, assigneeUserId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry findByG_A_S_Last(
			long groupId, long assigneeUserId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByG_A_S_Last(
			groupId, assigneeUserId, status, orderByComparator);

		if (todoEntry != null) {
			return todoEntry;
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

		throw new NoSuchTodoEntryException(msg.toString());
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
	@Override
	public TodoEntry fetchByG_A_S_Last(
		long groupId, long assigneeUserId, int status,
		OrderByComparator<TodoEntry> orderByComparator) {

		int count = countByG_A_S(groupId, assigneeUserId, status);

		if (count == 0) {
			return null;
		}

		List<TodoEntry> list = findByG_A_S(
			groupId, assigneeUserId, status, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TodoEntry[] findByG_A_S_PrevAndNext(
			long todoEntryId, long groupId, long assigneeUserId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = findByPrimaryKey(todoEntryId);

		Session session = null;

		try {
			session = openSession();

			TodoEntry[] array = new TodoEntryImpl[3];

			array[0] = getByG_A_S_PrevAndNext(
				session, todoEntry, groupId, assigneeUserId, status,
				orderByComparator, true);

			array[1] = todoEntry;

			array[2] = getByG_A_S_PrevAndNext(
				session, todoEntry, groupId, assigneeUserId, status,
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

	protected TodoEntry getByG_A_S_PrevAndNext(
		Session session, TodoEntry todoEntry, long groupId, long assigneeUserId,
		int status, OrderByComparator<TodoEntry> orderByComparator,
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

		query.append(_SQL_SELECT_TODOENTRY_WHERE);

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
			query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(todoEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TodoEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the matching todo entries that the user has permission to view
	 */
	@Override
	public List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int status) {

		return filterFindByG_A_S(
			groupId, assigneeUserId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end) {

		return filterFindByG_A_S(
			groupId, assigneeUserId, status, start, end, null);
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
	@Override
	public List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A_S(
				groupId, assigneeUserId, status, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TODOENTRY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

		query.append(_FINDER_COLUMN_G_A_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TodoEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TodoEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(assigneeUserId);

			qPos.add(status);

			return (List<TodoEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public TodoEntry[] filterFindByG_A_S_PrevAndNext(
			long todoEntryId, long groupId, long assigneeUserId, int status,
			OrderByComparator<TodoEntry> orderByComparator)
		throws NoSuchTodoEntryException {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A_S_PrevAndNext(
				todoEntryId, groupId, assigneeUserId, status,
				orderByComparator);
		}

		TodoEntry todoEntry = findByPrimaryKey(todoEntryId);

		Session session = null;

		try {
			session = openSession();

			TodoEntry[] array = new TodoEntryImpl[3];

			array[0] = filterGetByG_A_S_PrevAndNext(
				session, todoEntry, groupId, assigneeUserId, status,
				orderByComparator, true);

			array[1] = todoEntry;

			array[2] = filterGetByG_A_S_PrevAndNext(
				session, todoEntry, groupId, assigneeUserId, status,
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

	protected TodoEntry filterGetByG_A_S_PrevAndNext(
		Session session, TodoEntry todoEntry, long groupId, long assigneeUserId,
		int status, OrderByComparator<TodoEntry> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				7 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TODOENTRY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

		query.append(_FINDER_COLUMN_G_A_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TodoEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TodoEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(assigneeUserId);

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(todoEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<TodoEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the matching todo entries that the user has permission to view
	 */
	@Override
	public List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int[] statuses) {

		return filterFindByG_A_S(
			groupId, assigneeUserId, statuses, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end) {

		return filterFindByG_A_S(
			groupId, assigneeUserId, statuses, start, end, null);
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
	@Override
	public List<TodoEntry> filterFindByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A_S(
				groupId, assigneeUserId, statuses, start, end,
				orderByComparator);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.sortedUnique(statuses);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TODOENTRY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

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

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TodoEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TodoEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TodoEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(assigneeUserId);

			return (List<TodoEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int[] statuses) {

		return findByG_A_S(
			groupId, assigneeUserId, statuses, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end) {

		return findByG_A_S(groupId, assigneeUserId, statuses, start, end, null);
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
	@Override
	public List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator) {

		return findByG_A_S(
			groupId, assigneeUserId, statuses, start, end, orderByComparator,
			true);
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
	@Override
	public List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int[] statuses, int start, int end,
		OrderByComparator<TodoEntry> orderByComparator,
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

		List<TodoEntry> list = null;

		if (useFinderCache) {
			list = (List<TodoEntry>)finderCache.getResult(
				_finderPathWithPaginationFindByG_A_S, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TodoEntry todoEntry : list) {
					if ((groupId != todoEntry.getGroupId()) ||
						(assigneeUserId != todoEntry.getAssigneeUserId()) ||
						!ArrayUtil.contains(statuses, todoEntry.getStatus())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TODOENTRY_WHERE);

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
				query.append(TodoEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

				list = (List<TodoEntry>)QueryUtil.list(
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
	 * Removes all the todo entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 */
	@Override
	public void removeByG_A_S(long groupId, long assigneeUserId, int status) {
		for (TodoEntry todoEntry :
				findByG_A_S(
					groupId, assigneeUserId, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(todoEntry);
		}
	}

	/**
	 * Returns the number of todo entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the number of matching todo entries
	 */
	@Override
	public int countByG_A_S(long groupId, long assigneeUserId, int status) {
		FinderPath finderPath = _finderPathCountByG_A_S;

		Object[] finderArgs = new Object[] {groupId, assigneeUserId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TODOENTRY_WHERE);

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
	 * Returns the number of todo entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries
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

			query.append(_SQL_COUNT_TODOENTRY_WHERE);

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

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the number of matching todo entries that the user has permission to view
	 */
	@Override
	public int filterCountByG_A_S(
		long groupId, long assigneeUserId, int status) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_A_S(groupId, assigneeUserId, status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_TODOENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

		query.append(_FINDER_COLUMN_G_A_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(assigneeUserId);

			qPos.add(status);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of todo entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the number of matching todo entries that the user has permission to view
	 */
	@Override
	public int filterCountByG_A_S(
		long groupId, long assigneeUserId, int[] statuses) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_A_S(groupId, assigneeUserId, statuses);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.sortedUnique(statuses);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_TODOENTRY_WHERE);

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

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), TodoEntry.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(assigneeUserId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_A_S_GROUPID_2 =
		"todoEntry.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2 =
		"todoEntry.assigneeUserId = ? AND ";

	private static final String _FINDER_COLUMN_G_A_S_STATUS_2 =
		"todoEntry.status = ?";

	private static final String _FINDER_COLUMN_G_A_S_STATUS_7 =
		"todoEntry.status IN (";

	public TodoEntryPersistenceImpl() {
		setModelClass(TodoEntry.class);

		setModelImplClass(TodoEntryImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the todo entry in the entity cache if it is enabled.
	 *
	 * @param todoEntry the todo entry
	 */
	@Override
	public void cacheResult(TodoEntry todoEntry) {
		entityCache.putResult(
			entityCacheEnabled, TodoEntryImpl.class, todoEntry.getPrimaryKey(),
			todoEntry);

		todoEntry.resetOriginalValues();
	}

	/**
	 * Caches the todo entries in the entity cache if it is enabled.
	 *
	 * @param todoEntries the todo entries
	 */
	@Override
	public void cacheResult(List<TodoEntry> todoEntries) {
		for (TodoEntry todoEntry : todoEntries) {
			if (entityCache.getResult(
					entityCacheEnabled, TodoEntryImpl.class,
					todoEntry.getPrimaryKey()) == null) {

				cacheResult(todoEntry);
			}
			else {
				todoEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all todo entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TodoEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the todo entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TodoEntry todoEntry) {
		entityCache.removeResult(
			entityCacheEnabled, TodoEntryImpl.class, todoEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TodoEntry> todoEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TodoEntry todoEntry : todoEntries) {
			entityCache.removeResult(
				entityCacheEnabled, TodoEntryImpl.class,
				todoEntry.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, TodoEntryImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new todo entry with the primary key. Does not add the todo entry to the database.
	 *
	 * @param todoEntryId the primary key for the new todo entry
	 * @return the new todo entry
	 */
	@Override
	public TodoEntry create(long todoEntryId) {
		TodoEntry todoEntry = new TodoEntryImpl();

		todoEntry.setNew(true);
		todoEntry.setPrimaryKey(todoEntryId);

		todoEntry.setCompanyId(CompanyThreadLocal.getCompanyId());

		return todoEntry;
	}

	/**
	 * Removes the todo entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param todoEntryId the primary key of the todo entry
	 * @return the todo entry that was removed
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	@Override
	public TodoEntry remove(long todoEntryId) throws NoSuchTodoEntryException {
		return remove((Serializable)todoEntryId);
	}

	/**
	 * Removes the todo entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the todo entry
	 * @return the todo entry that was removed
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	@Override
	public TodoEntry remove(Serializable primaryKey)
		throws NoSuchTodoEntryException {

		Session session = null;

		try {
			session = openSession();

			TodoEntry todoEntry = (TodoEntry)session.get(
				TodoEntryImpl.class, primaryKey);

			if (todoEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTodoEntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(todoEntry);
		}
		catch (NoSuchTodoEntryException noSuchEntityException) {
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
	protected TodoEntry removeImpl(TodoEntry todoEntry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(todoEntry)) {
				todoEntry = (TodoEntry)session.get(
					TodoEntryImpl.class, todoEntry.getPrimaryKeyObj());
			}

			if (todoEntry != null) {
				session.delete(todoEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (todoEntry != null) {
			clearCache(todoEntry);
		}

		return todoEntry;
	}

	@Override
	public TodoEntry updateImpl(TodoEntry todoEntry) {
		boolean isNew = todoEntry.isNew();

		if (!(todoEntry instanceof TodoEntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(todoEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(todoEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in todoEntry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TodoEntry implementation " +
					todoEntry.getClass());
		}

		TodoEntryModelImpl todoEntryModelImpl = (TodoEntryModelImpl)todoEntry;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (todoEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				todoEntry.setCreateDate(now);
			}
			else {
				todoEntry.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!todoEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				todoEntry.setModifiedDate(now);
			}
			else {
				todoEntry.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (todoEntry.isNew()) {
				session.save(todoEntry);

				todoEntry.setNew(false);
			}
			else {
				todoEntry = (TodoEntry)session.merge(todoEntry);
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
			Object[] args = new Object[] {todoEntryModelImpl.getGroupId()};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			args = new Object[] {todoEntryModelImpl.getUserId()};

			finderCache.removeResult(_finderPathCountByUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUserId, args);

			args = new Object[] {todoEntryModelImpl.getAssigneeUserId()};

			finderCache.removeResult(_finderPathCountByAssigneeUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByAssigneeUserId, args);

			args = new Object[] {todoEntryModelImpl.getResolverUserId()};

			finderCache.removeResult(_finderPathCountByResolverUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByResolverUserId, args);

			args = new Object[] {
				todoEntryModelImpl.getGroupId(), todoEntryModelImpl.getUserId()
			};

			finderCache.removeResult(_finderPathCountByG_U, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByG_U, args);

			args = new Object[] {
				todoEntryModelImpl.getGroupId(),
				todoEntryModelImpl.getAssigneeUserId()
			};

			finderCache.removeResult(_finderPathCountByG_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByG_A, args);

			args = new Object[] {
				todoEntryModelImpl.getGroupId(),
				todoEntryModelImpl.getResolverUserId()
			};

			finderCache.removeResult(_finderPathCountByG_R, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByG_R, args);

			args = new Object[] {
				todoEntryModelImpl.getUserId(), todoEntryModelImpl.getStatus()
			};

			finderCache.removeResult(_finderPathCountByU_S, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByU_S, args);

			args = new Object[] {
				todoEntryModelImpl.getAssigneeUserId(),
				todoEntryModelImpl.getStatus()
			};

			finderCache.removeResult(_finderPathCountByA_S, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByA_S, args);

			args = new Object[] {
				todoEntryModelImpl.getGroupId(), todoEntryModelImpl.getUserId(),
				todoEntryModelImpl.getStatus()
			};

			finderCache.removeResult(_finderPathCountByG_U_S, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByG_U_S, args);

			args = new Object[] {
				todoEntryModelImpl.getGroupId(),
				todoEntryModelImpl.getAssigneeUserId(),
				todoEntryModelImpl.getStatus()
			};

			finderCache.removeResult(_finderPathCountByG_A_S, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByG_A_S, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((todoEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					todoEntryModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {todoEntryModelImpl.getGroupId()};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}

			if ((todoEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUserId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					todoEntryModelImpl.getOriginalUserId()
				};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);

				args = new Object[] {todoEntryModelImpl.getUserId()};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);
			}

			if ((todoEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByAssigneeUserId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					todoEntryModelImpl.getOriginalAssigneeUserId()
				};

				finderCache.removeResult(
					_finderPathCountByAssigneeUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAssigneeUserId, args);

				args = new Object[] {todoEntryModelImpl.getAssigneeUserId()};

				finderCache.removeResult(
					_finderPathCountByAssigneeUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAssigneeUserId, args);
			}

			if ((todoEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByResolverUserId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					todoEntryModelImpl.getOriginalResolverUserId()
				};

				finderCache.removeResult(
					_finderPathCountByResolverUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByResolverUserId, args);

				args = new Object[] {todoEntryModelImpl.getResolverUserId()};

				finderCache.removeResult(
					_finderPathCountByResolverUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByResolverUserId, args);
			}

			if ((todoEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByG_U.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					todoEntryModelImpl.getOriginalGroupId(),
					todoEntryModelImpl.getOriginalUserId()
				};

				finderCache.removeResult(_finderPathCountByG_U, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_U, args);

				args = new Object[] {
					todoEntryModelImpl.getGroupId(),
					todoEntryModelImpl.getUserId()
				};

				finderCache.removeResult(_finderPathCountByG_U, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_U, args);
			}

			if ((todoEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByG_A.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					todoEntryModelImpl.getOriginalGroupId(),
					todoEntryModelImpl.getOriginalAssigneeUserId()
				};

				finderCache.removeResult(_finderPathCountByG_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_A, args);

				args = new Object[] {
					todoEntryModelImpl.getGroupId(),
					todoEntryModelImpl.getAssigneeUserId()
				};

				finderCache.removeResult(_finderPathCountByG_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_A, args);
			}

			if ((todoEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByG_R.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					todoEntryModelImpl.getOriginalGroupId(),
					todoEntryModelImpl.getOriginalResolverUserId()
				};

				finderCache.removeResult(_finderPathCountByG_R, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_R, args);

				args = new Object[] {
					todoEntryModelImpl.getGroupId(),
					todoEntryModelImpl.getResolverUserId()
				};

				finderCache.removeResult(_finderPathCountByG_R, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_R, args);
			}

			if ((todoEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByU_S.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					todoEntryModelImpl.getOriginalUserId(),
					todoEntryModelImpl.getOriginalStatus()
				};

				finderCache.removeResult(_finderPathCountByU_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByU_S, args);

				args = new Object[] {
					todoEntryModelImpl.getUserId(),
					todoEntryModelImpl.getStatus()
				};

				finderCache.removeResult(_finderPathCountByU_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByU_S, args);
			}

			if ((todoEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByA_S.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					todoEntryModelImpl.getOriginalAssigneeUserId(),
					todoEntryModelImpl.getOriginalStatus()
				};

				finderCache.removeResult(_finderPathCountByA_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByA_S, args);

				args = new Object[] {
					todoEntryModelImpl.getAssigneeUserId(),
					todoEntryModelImpl.getStatus()
				};

				finderCache.removeResult(_finderPathCountByA_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByA_S, args);
			}

			if ((todoEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByG_U_S.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					todoEntryModelImpl.getOriginalGroupId(),
					todoEntryModelImpl.getOriginalUserId(),
					todoEntryModelImpl.getOriginalStatus()
				};

				finderCache.removeResult(_finderPathCountByG_U_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_U_S, args);

				args = new Object[] {
					todoEntryModelImpl.getGroupId(),
					todoEntryModelImpl.getUserId(),
					todoEntryModelImpl.getStatus()
				};

				finderCache.removeResult(_finderPathCountByG_U_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_U_S, args);
			}

			if ((todoEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByG_A_S.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					todoEntryModelImpl.getOriginalGroupId(),
					todoEntryModelImpl.getOriginalAssigneeUserId(),
					todoEntryModelImpl.getOriginalStatus()
				};

				finderCache.removeResult(_finderPathCountByG_A_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_A_S, args);

				args = new Object[] {
					todoEntryModelImpl.getGroupId(),
					todoEntryModelImpl.getAssigneeUserId(),
					todoEntryModelImpl.getStatus()
				};

				finderCache.removeResult(_finderPathCountByG_A_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_A_S, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, TodoEntryImpl.class, todoEntry.getPrimaryKey(),
			todoEntry, false);

		todoEntry.resetOriginalValues();

		return todoEntry;
	}

	/**
	 * Returns the todo entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the todo entry
	 * @return the todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	@Override
	public TodoEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTodoEntryException {

		TodoEntry todoEntry = fetchByPrimaryKey(primaryKey);

		if (todoEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTodoEntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return todoEntry;
	}

	/**
	 * Returns the todo entry with the primary key or throws a <code>NoSuchTodoEntryException</code> if it could not be found.
	 *
	 * @param todoEntryId the primary key of the todo entry
	 * @return the todo entry
	 * @throws NoSuchTodoEntryException if a todo entry with the primary key could not be found
	 */
	@Override
	public TodoEntry findByPrimaryKey(long todoEntryId)
		throws NoSuchTodoEntryException {

		return findByPrimaryKey((Serializable)todoEntryId);
	}

	/**
	 * Returns the todo entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param todoEntryId the primary key of the todo entry
	 * @return the todo entry, or <code>null</code> if a todo entry with the primary key could not be found
	 */
	@Override
	public TodoEntry fetchByPrimaryKey(long todoEntryId) {
		return fetchByPrimaryKey((Serializable)todoEntryId);
	}

	/**
	 * Returns all the todo entries.
	 *
	 * @return the todo entries
	 */
	@Override
	public List<TodoEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TodoEntry> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<TodoEntry> findAll(
		int start, int end, OrderByComparator<TodoEntry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<TodoEntry> findAll(
		int start, int end, OrderByComparator<TodoEntry> orderByComparator,
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

		List<TodoEntry> list = null;

		if (useFinderCache) {
			list = (List<TodoEntry>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TODOENTRY);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TODOENTRY;

				sql = sql.concat(TodoEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				list = (List<TodoEntry>)QueryUtil.list(
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
	 * Removes all the todo entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TodoEntry todoEntry : findAll()) {
			remove(todoEntry);
		}
	}

	/**
	 * Returns the number of todo entries.
	 *
	 * @return the number of todo entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TODOENTRY);

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
		return "todoEntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_TODOENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TodoEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the todo entry persistence.
	 */
	@Activate
	public void activate() {
		TodoEntryModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		TodoEntryModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()},
			TodoEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TodoEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TodoEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TodoEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] {Long.class.getName()},
			TodoEntryModelImpl.USERID_COLUMN_BITMASK |
			TodoEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TodoEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TodoEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByAssigneeUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssigneeUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByAssigneeUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssigneeUserId",
			new String[] {Long.class.getName()},
			TodoEntryModelImpl.ASSIGNEEUSERID_COLUMN_BITMASK |
			TodoEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TodoEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TodoEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByAssigneeUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAssigneeUserId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByResolverUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByResolverUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByResolverUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByResolverUserId",
			new String[] {Long.class.getName()},
			TodoEntryModelImpl.RESOLVERUSERID_COLUMN_BITMASK |
			TodoEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TodoEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TodoEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByResolverUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByResolverUserId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByG_U = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByG_U = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U",
			new String[] {Long.class.getName(), Long.class.getName()},
			TodoEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TodoEntryModelImpl.USERID_COLUMN_BITMASK |
			TodoEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TodoEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TodoEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByG_U = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByG_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_A",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByG_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_A",
			new String[] {Long.class.getName(), Long.class.getName()},
			TodoEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TodoEntryModelImpl.ASSIGNEEUSERID_COLUMN_BITMASK |
			TodoEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TodoEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TodoEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByG_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_A",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByG_R = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_R",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByG_R = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_R",
			new String[] {Long.class.getName(), Long.class.getName()},
			TodoEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TodoEntryModelImpl.RESOLVERUSERID_COLUMN_BITMASK |
			TodoEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TodoEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TodoEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByG_R = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_R",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByU_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByU_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			TodoEntryModelImpl.USERID_COLUMN_BITMASK |
			TodoEntryModelImpl.STATUS_COLUMN_BITMASK |
			TodoEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TodoEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TodoEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByU_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_S",
			new String[] {Long.class.getName(), Integer.class.getName()});

		_finderPathWithPaginationCountByU_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByU_S",
			new String[] {Long.class.getName(), Integer.class.getName()});

		_finderPathWithPaginationFindByA_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByA_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByA_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByA_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			TodoEntryModelImpl.ASSIGNEEUSERID_COLUMN_BITMASK |
			TodoEntryModelImpl.STATUS_COLUMN_BITMASK |
			TodoEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TodoEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TodoEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByA_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByA_S",
			new String[] {Long.class.getName(), Integer.class.getName()});

		_finderPathWithPaginationCountByA_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByA_S",
			new String[] {Long.class.getName(), Integer.class.getName()});

		_finderPathWithPaginationFindByG_U_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByG_U_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			TodoEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TodoEntryModelImpl.USERID_COLUMN_BITMASK |
			TodoEntryModelImpl.STATUS_COLUMN_BITMASK |
			TodoEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TodoEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TodoEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

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
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_A_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByG_A_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TodoEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_A_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			TodoEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TodoEntryModelImpl.ASSIGNEEUSERID_COLUMN_BITMASK |
			TodoEntryModelImpl.STATUS_COLUMN_BITMASK |
			TodoEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TodoEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TodoEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

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
		entityCache.removeCache(TodoEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = TDLPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.liferay.todo.model.TodoEntry"),
			true);
	}

	@Override
	@Reference(
		target = TDLPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = TDLPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static final String _SQL_SELECT_TODOENTRY =
		"SELECT todoEntry FROM TodoEntry todoEntry";

	private static final String _SQL_SELECT_TODOENTRY_WHERE =
		"SELECT todoEntry FROM TodoEntry todoEntry WHERE ";

	private static final String _SQL_COUNT_TODOENTRY =
		"SELECT COUNT(todoEntry) FROM TodoEntry todoEntry";

	private static final String _SQL_COUNT_TODOENTRY_WHERE =
		"SELECT COUNT(todoEntry) FROM TodoEntry todoEntry WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"todoEntry.todoEntryId";

	private static final String _FILTER_SQL_SELECT_TODOENTRY_WHERE =
		"SELECT DISTINCT {todoEntry.*} FROM TDL_TodoEntry todoEntry WHERE ";

	private static final String
		_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {TDL_TodoEntry.*} FROM (SELECT DISTINCT todoEntry.todoEntryId FROM TDL_TodoEntry todoEntry WHERE ";

	private static final String
		_FILTER_SQL_SELECT_TODOENTRY_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN TDL_TodoEntry ON TEMP_TABLE.todoEntryId = TDL_TodoEntry.todoEntryId";

	private static final String _FILTER_SQL_COUNT_TODOENTRY_WHERE =
		"SELECT COUNT(DISTINCT todoEntry.todoEntryId) AS COUNT_VALUE FROM TDL_TodoEntry todoEntry WHERE ";

	private static final String _FILTER_ENTITY_ALIAS = "todoEntry";

	private static final String _FILTER_ENTITY_TABLE = "TDL_TodoEntry";

	private static final String _ORDER_BY_ENTITY_ALIAS = "todoEntry.";

	private static final String _ORDER_BY_ENTITY_TABLE = "TDL_TodoEntry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TodoEntry exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No TodoEntry exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		TodoEntryPersistenceImpl.class);

	static {
		try {
			Class.forName(TDLPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException classNotFoundException) {
			throw new ExceptionInInitializerError(classNotFoundException);
		}
	}

}