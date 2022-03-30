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

package com.liferay.chat.service.persistence.impl;

import com.liferay.chat.exception.NoSuchEntryException;
import com.liferay.chat.model.Entry;
import com.liferay.chat.model.impl.EntryImpl;
import com.liferay.chat.model.impl.EntryModelImpl;
import com.liferay.chat.service.persistence.EntryPersistence;
import com.liferay.chat.service.persistence.impl.constants.ChatPersistenceConstants;
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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = EntryPersistence.class)
@ProviderType
public class EntryPersistenceImpl
	extends BasePersistenceImpl<Entry> implements EntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>EntryUtil</code> to access the entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		EntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCreateDate;
	private FinderPath _finderPathWithoutPaginationFindByCreateDate;
	private FinderPath _finderPathCountByCreateDate;

	/**
	 * Returns all the entries where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @return the matching entries
	 */
	@Override
	public List<Entry> findByCreateDate(long createDate) {
		return findByCreateDate(
			createDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 */
	@Override
	public List<Entry> findByCreateDate(long createDate, int start, int end) {
		return findByCreateDate(createDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findByCreateDate(long, int, int, OrderByComparator)}
	 * @param createDate the create date
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching entries
	 */
	@Deprecated
	@Override
	public List<Entry> findByCreateDate(
		long createDate, int start, int end,
		OrderByComparator<Entry> orderByComparator, boolean useFinderCache) {

		return findByCreateDate(createDate, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the entries where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 */
	@Override
	public List<Entry> findByCreateDate(
		long createDate, int start, int end,
		OrderByComparator<Entry> orderByComparator) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCreateDate;
			finderArgs = new Object[] {createDate};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCreateDate;
			finderArgs = new Object[] {
				createDate, start, end, orderByComparator
			};
		}

		List<Entry> list = (List<Entry>)finderCache.getResult(
			finderPath, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((createDate != entry.getCreateDate())) {
					list = null;

					break;
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

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first entry in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws NoSuchEntryException if a matching entry could not be found
	 */
	@Override
	public Entry findByCreateDate_First(
			long createDate, OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = fetchByCreateDate_First(createDate, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 */
	@Override
	public Entry fetchByCreateDate_First(
		long createDate, OrderByComparator<Entry> orderByComparator) {

		List<Entry> list = findByCreateDate(
			createDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws NoSuchEntryException if a matching entry could not be found
	 */
	@Override
	public Entry findByCreateDate_Last(
			long createDate, OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = fetchByCreateDate_Last(createDate, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 */
	@Override
	public Entry fetchByCreateDate_Last(
		long createDate, OrderByComparator<Entry> orderByComparator) {

		int count = countByCreateDate(createDate);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByCreateDate(
			createDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where createDate = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws NoSuchEntryException if a entry with the primary key could not be found
	 */
	@Override
	public Entry[] findByCreateDate_PrevAndNext(
			long entryId, long createDate,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByCreateDate_PrevAndNext(
				session, entry, createDate, orderByComparator, true);

			array[1] = entry;

			array[2] = getByCreateDate_PrevAndNext(
				session, entry, createDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByCreateDate_PrevAndNext(
		Session session, Entry entry, long createDate,
		OrderByComparator<Entry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_2);

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
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(createDate);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(entry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where createDate = &#63; from the database.
	 *
	 * @param createDate the create date
	 */
	@Override
	public void removeByCreateDate(long createDate) {
		for (Entry entry :
				findByCreateDate(
					createDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @return the number of matching entries
	 */
	@Override
	public int countByCreateDate(long createDate) {
		FinderPath finderPath = _finderPathCountByCreateDate;

		Object[] finderArgs = new Object[] {createDate};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CREATEDATE_CREATEDATE_2 =
		"entry.createDate = ?";

	private FinderPath _finderPathWithPaginationFindByFromUserId;
	private FinderPath _finderPathWithoutPaginationFindByFromUserId;
	private FinderPath _finderPathCountByFromUserId;

	/**
	 * Returns all the entries where fromUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @return the matching entries
	 */
	@Override
	public List<Entry> findByFromUserId(long fromUserId) {
		return findByFromUserId(
			fromUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries where fromUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param fromUserId the from user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 */
	@Override
	public List<Entry> findByFromUserId(long fromUserId, int start, int end) {
		return findByFromUserId(fromUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where fromUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findByFromUserId(long, int, int, OrderByComparator)}
	 * @param fromUserId the from user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching entries
	 */
	@Deprecated
	@Override
	public List<Entry> findByFromUserId(
		long fromUserId, int start, int end,
		OrderByComparator<Entry> orderByComparator, boolean useFinderCache) {

		return findByFromUserId(fromUserId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the entries where fromUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param fromUserId the from user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 */
	@Override
	public List<Entry> findByFromUserId(
		long fromUserId, int start, int end,
		OrderByComparator<Entry> orderByComparator) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByFromUserId;
			finderArgs = new Object[] {fromUserId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByFromUserId;
			finderArgs = new Object[] {
				fromUserId, start, end, orderByComparator
			};
		}

		List<Entry> list = (List<Entry>)finderCache.getResult(
			finderPath, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((fromUserId != entry.getFromUserId())) {
					list = null;

					break;
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

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_FROMUSERID_FROMUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first entry in the ordered set where fromUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws NoSuchEntryException if a matching entry could not be found
	 */
	@Override
	public Entry findByFromUserId_First(
			long fromUserId, OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = fetchByFromUserId_First(fromUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fromUserId=");
		msg.append(fromUserId);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where fromUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 */
	@Override
	public Entry fetchByFromUserId_First(
		long fromUserId, OrderByComparator<Entry> orderByComparator) {

		List<Entry> list = findByFromUserId(
			fromUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where fromUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws NoSuchEntryException if a matching entry could not be found
	 */
	@Override
	public Entry findByFromUserId_Last(
			long fromUserId, OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = fetchByFromUserId_Last(fromUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fromUserId=");
		msg.append(fromUserId);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where fromUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 */
	@Override
	public Entry fetchByFromUserId_Last(
		long fromUserId, OrderByComparator<Entry> orderByComparator) {

		int count = countByFromUserId(fromUserId);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByFromUserId(
			fromUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where fromUserId = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws NoSuchEntryException if a entry with the primary key could not be found
	 */
	@Override
	public Entry[] findByFromUserId_PrevAndNext(
			long entryId, long fromUserId,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByFromUserId_PrevAndNext(
				session, entry, fromUserId, orderByComparator, true);

			array[1] = entry;

			array[2] = getByFromUserId_PrevAndNext(
				session, entry, fromUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByFromUserId_PrevAndNext(
		Session session, Entry entry, long fromUserId,
		OrderByComparator<Entry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_FROMUSERID_FROMUSERID_2);

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
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(fromUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(entry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where fromUserId = &#63; from the database.
	 *
	 * @param fromUserId the from user ID
	 */
	@Override
	public void removeByFromUserId(long fromUserId) {
		for (Entry entry :
				findByFromUserId(
					fromUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where fromUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @return the number of matching entries
	 */
	@Override
	public int countByFromUserId(long fromUserId) {
		FinderPath finderPath = _finderPathCountByFromUserId;

		Object[] finderArgs = new Object[] {fromUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_FROMUSERID_FROMUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_FROMUSERID_FROMUSERID_2 =
		"entry.fromUserId = ?";

	private FinderPath _finderPathWithPaginationFindByToUserId;
	private FinderPath _finderPathWithoutPaginationFindByToUserId;
	private FinderPath _finderPathCountByToUserId;

	/**
	 * Returns all the entries where toUserId = &#63;.
	 *
	 * @param toUserId the to user ID
	 * @return the matching entries
	 */
	@Override
	public List<Entry> findByToUserId(long toUserId) {
		return findByToUserId(
			toUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries where toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 */
	@Override
	public List<Entry> findByToUserId(long toUserId, int start, int end) {
		return findByToUserId(toUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findByToUserId(long, int, int, OrderByComparator)}
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching entries
	 */
	@Deprecated
	@Override
	public List<Entry> findByToUserId(
		long toUserId, int start, int end,
		OrderByComparator<Entry> orderByComparator, boolean useFinderCache) {

		return findByToUserId(toUserId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the entries where toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 */
	@Override
	public List<Entry> findByToUserId(
		long toUserId, int start, int end,
		OrderByComparator<Entry> orderByComparator) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByToUserId;
			finderArgs = new Object[] {toUserId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByToUserId;
			finderArgs = new Object[] {toUserId, start, end, orderByComparator};
		}

		List<Entry> list = (List<Entry>)finderCache.getResult(
			finderPath, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((toUserId != entry.getToUserId())) {
					list = null;

					break;
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

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_TOUSERID_TOUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(toUserId);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first entry in the ordered set where toUserId = &#63;.
	 *
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws NoSuchEntryException if a matching entry could not be found
	 */
	@Override
	public Entry findByToUserId_First(
			long toUserId, OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = fetchByToUserId_First(toUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("toUserId=");
		msg.append(toUserId);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where toUserId = &#63;.
	 *
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 */
	@Override
	public Entry fetchByToUserId_First(
		long toUserId, OrderByComparator<Entry> orderByComparator) {

		List<Entry> list = findByToUserId(toUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where toUserId = &#63;.
	 *
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws NoSuchEntryException if a matching entry could not be found
	 */
	@Override
	public Entry findByToUserId_Last(
			long toUserId, OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = fetchByToUserId_Last(toUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("toUserId=");
		msg.append(toUserId);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where toUserId = &#63;.
	 *
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 */
	@Override
	public Entry fetchByToUserId_Last(
		long toUserId, OrderByComparator<Entry> orderByComparator) {

		int count = countByToUserId(toUserId);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByToUserId(
			toUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where toUserId = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws NoSuchEntryException if a entry with the primary key could not be found
	 */
	@Override
	public Entry[] findByToUserId_PrevAndNext(
			long entryId, long toUserId,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByToUserId_PrevAndNext(
				session, entry, toUserId, orderByComparator, true);

			array[1] = entry;

			array[2] = getByToUserId_PrevAndNext(
				session, entry, toUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByToUserId_PrevAndNext(
		Session session, Entry entry, long toUserId,
		OrderByComparator<Entry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_TOUSERID_TOUSERID_2);

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
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(toUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(entry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where toUserId = &#63; from the database.
	 *
	 * @param toUserId the to user ID
	 */
	@Override
	public void removeByToUserId(long toUserId) {
		for (Entry entry :
				findByToUserId(
					toUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where toUserId = &#63;.
	 *
	 * @param toUserId the to user ID
	 * @return the number of matching entries
	 */
	@Override
	public int countByToUserId(long toUserId) {
		FinderPath finderPath = _finderPathCountByToUserId;

		Object[] finderArgs = new Object[] {toUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_TOUSERID_TOUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(toUserId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_TOUSERID_TOUSERID_2 =
		"entry.toUserId = ?";

	private FinderPath _finderPathWithPaginationFindByC_F;
	private FinderPath _finderPathWithoutPaginationFindByC_F;
	private FinderPath _finderPathCountByC_F;

	/**
	 * Returns all the entries where createDate = &#63; and fromUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @return the matching entries
	 */
	@Override
	public List<Entry> findByC_F(long createDate, long fromUserId) {
		return findByC_F(
			createDate, fromUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries where createDate = &#63; and fromUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 */
	@Override
	public List<Entry> findByC_F(
		long createDate, long fromUserId, int start, int end) {

		return findByC_F(createDate, fromUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where createDate = &#63; and fromUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findByC_F(long,long, int, int, OrderByComparator)}
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching entries
	 */
	@Deprecated
	@Override
	public List<Entry> findByC_F(
		long createDate, long fromUserId, int start, int end,
		OrderByComparator<Entry> orderByComparator, boolean useFinderCache) {

		return findByC_F(createDate, fromUserId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the entries where createDate = &#63; and fromUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 */
	@Override
	public List<Entry> findByC_F(
		long createDate, long fromUserId, int start, int end,
		OrderByComparator<Entry> orderByComparator) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByC_F;
			finderArgs = new Object[] {createDate, fromUserId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByC_F;
			finderArgs = new Object[] {
				createDate, fromUserId, start, end, orderByComparator
			};
		}

		List<Entry> list = (List<Entry>)finderCache.getResult(
			finderPath, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((createDate != entry.getCreateDate()) ||
					(fromUserId != entry.getFromUserId())) {

					list = null;

					break;
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

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_F_CREATEDATE_2);

			query.append(_FINDER_COLUMN_C_F_FROMUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(fromUserId);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws NoSuchEntryException if a matching entry could not be found
	 */
	@Override
	public Entry findByC_F_First(
			long createDate, long fromUserId,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = fetchByC_F_First(
			createDate, fromUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", fromUserId=");
		msg.append(fromUserId);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 */
	@Override
	public Entry fetchByC_F_First(
		long createDate, long fromUserId,
		OrderByComparator<Entry> orderByComparator) {

		List<Entry> list = findByC_F(
			createDate, fromUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws NoSuchEntryException if a matching entry could not be found
	 */
	@Override
	public Entry findByC_F_Last(
			long createDate, long fromUserId,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = fetchByC_F_Last(
			createDate, fromUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", fromUserId=");
		msg.append(fromUserId);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 */
	@Override
	public Entry fetchByC_F_Last(
		long createDate, long fromUserId,
		OrderByComparator<Entry> orderByComparator) {

		int count = countByC_F(createDate, fromUserId);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByC_F(
			createDate, fromUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws NoSuchEntryException if a entry with the primary key could not be found
	 */
	@Override
	public Entry[] findByC_F_PrevAndNext(
			long entryId, long createDate, long fromUserId,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByC_F_PrevAndNext(
				session, entry, createDate, fromUserId, orderByComparator,
				true);

			array[1] = entry;

			array[2] = getByC_F_PrevAndNext(
				session, entry, createDate, fromUserId, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByC_F_PrevAndNext(
		Session session, Entry entry, long createDate, long fromUserId,
		OrderByComparator<Entry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_C_F_CREATEDATE_2);

		query.append(_FINDER_COLUMN_C_F_FROMUSERID_2);

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
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(createDate);

		qPos.add(fromUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(entry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where createDate = &#63; and fromUserId = &#63; from the database.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 */
	@Override
	public void removeByC_F(long createDate, long fromUserId) {
		for (Entry entry :
				findByC_F(
					createDate, fromUserId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where createDate = &#63; and fromUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @return the number of matching entries
	 */
	@Override
	public int countByC_F(long createDate, long fromUserId) {
		FinderPath finderPath = _finderPathCountByC_F;

		Object[] finderArgs = new Object[] {createDate, fromUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_F_CREATEDATE_2);

			query.append(_FINDER_COLUMN_C_F_FROMUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(fromUserId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_F_CREATEDATE_2 =
		"entry.createDate = ? AND ";

	private static final String _FINDER_COLUMN_C_F_FROMUSERID_2 =
		"entry.fromUserId = ?";

	private FinderPath _finderPathWithPaginationFindByC_T;
	private FinderPath _finderPathWithoutPaginationFindByC_T;
	private FinderPath _finderPathCountByC_T;

	/**
	 * Returns all the entries where createDate = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @return the matching entries
	 */
	@Override
	public List<Entry> findByC_T(long createDate, long toUserId) {
		return findByC_T(
			createDate, toUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries where createDate = &#63; and toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 */
	@Override
	public List<Entry> findByC_T(
		long createDate, long toUserId, int start, int end) {

		return findByC_T(createDate, toUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where createDate = &#63; and toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findByC_T(long,long, int, int, OrderByComparator)}
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching entries
	 */
	@Deprecated
	@Override
	public List<Entry> findByC_T(
		long createDate, long toUserId, int start, int end,
		OrderByComparator<Entry> orderByComparator, boolean useFinderCache) {

		return findByC_T(createDate, toUserId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the entries where createDate = &#63; and toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 */
	@Override
	public List<Entry> findByC_T(
		long createDate, long toUserId, int start, int end,
		OrderByComparator<Entry> orderByComparator) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByC_T;
			finderArgs = new Object[] {createDate, toUserId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByC_T;
			finderArgs = new Object[] {
				createDate, toUserId, start, end, orderByComparator
			};
		}

		List<Entry> list = (List<Entry>)finderCache.getResult(
			finderPath, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((createDate != entry.getCreateDate()) ||
					(toUserId != entry.getToUserId())) {

					list = null;

					break;
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

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_T_CREATEDATE_2);

			query.append(_FINDER_COLUMN_C_T_TOUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(toUserId);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws NoSuchEntryException if a matching entry could not be found
	 */
	@Override
	public Entry findByC_T_First(
			long createDate, long toUserId,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = fetchByC_T_First(createDate, toUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", toUserId=");
		msg.append(toUserId);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 */
	@Override
	public Entry fetchByC_T_First(
		long createDate, long toUserId,
		OrderByComparator<Entry> orderByComparator) {

		List<Entry> list = findByC_T(
			createDate, toUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws NoSuchEntryException if a matching entry could not be found
	 */
	@Override
	public Entry findByC_T_Last(
			long createDate, long toUserId,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = fetchByC_T_Last(createDate, toUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", toUserId=");
		msg.append(toUserId);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 */
	@Override
	public Entry fetchByC_T_Last(
		long createDate, long toUserId,
		OrderByComparator<Entry> orderByComparator) {

		int count = countByC_T(createDate, toUserId);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByC_T(
			createDate, toUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws NoSuchEntryException if a entry with the primary key could not be found
	 */
	@Override
	public Entry[] findByC_T_PrevAndNext(
			long entryId, long createDate, long toUserId,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByC_T_PrevAndNext(
				session, entry, createDate, toUserId, orderByComparator, true);

			array[1] = entry;

			array[2] = getByC_T_PrevAndNext(
				session, entry, createDate, toUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByC_T_PrevAndNext(
		Session session, Entry entry, long createDate, long toUserId,
		OrderByComparator<Entry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_C_T_CREATEDATE_2);

		query.append(_FINDER_COLUMN_C_T_TOUSERID_2);

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
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(createDate);

		qPos.add(toUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(entry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where createDate = &#63; and toUserId = &#63; from the database.
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 */
	@Override
	public void removeByC_T(long createDate, long toUserId) {
		for (Entry entry :
				findByC_T(
					createDate, toUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where createDate = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @return the number of matching entries
	 */
	@Override
	public int countByC_T(long createDate, long toUserId) {
		FinderPath finderPath = _finderPathCountByC_T;

		Object[] finderArgs = new Object[] {createDate, toUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_T_CREATEDATE_2);

			query.append(_FINDER_COLUMN_C_T_TOUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(toUserId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_T_CREATEDATE_2 =
		"entry.createDate = ? AND ";

	private static final String _FINDER_COLUMN_C_T_TOUSERID_2 =
		"entry.toUserId = ?";

	private FinderPath _finderPathWithPaginationFindByF_T;
	private FinderPath _finderPathWithoutPaginationFindByF_T;
	private FinderPath _finderPathCountByF_T;

	/**
	 * Returns all the entries where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @return the matching entries
	 */
	@Override
	public List<Entry> findByF_T(long fromUserId, long toUserId) {
		return findByF_T(
			fromUserId, toUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 */
	@Override
	public List<Entry> findByF_T(
		long fromUserId, long toUserId, int start, int end) {

		return findByF_T(fromUserId, toUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findByF_T(long,long, int, int, OrderByComparator)}
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching entries
	 */
	@Deprecated
	@Override
	public List<Entry> findByF_T(
		long fromUserId, long toUserId, int start, int end,
		OrderByComparator<Entry> orderByComparator, boolean useFinderCache) {

		return findByF_T(fromUserId, toUserId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the entries where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 */
	@Override
	public List<Entry> findByF_T(
		long fromUserId, long toUserId, int start, int end,
		OrderByComparator<Entry> orderByComparator) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByF_T;
			finderArgs = new Object[] {fromUserId, toUserId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByF_T;
			finderArgs = new Object[] {
				fromUserId, toUserId, start, end, orderByComparator
			};
		}

		List<Entry> list = (List<Entry>)finderCache.getResult(
			finderPath, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((fromUserId != entry.getFromUserId()) ||
					(toUserId != entry.getToUserId())) {

					list = null;

					break;
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

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_F_T_FROMUSERID_2);

			query.append(_FINDER_COLUMN_F_T_TOUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				qPos.add(toUserId);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first entry in the ordered set where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws NoSuchEntryException if a matching entry could not be found
	 */
	@Override
	public Entry findByF_T_First(
			long fromUserId, long toUserId,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = fetchByF_T_First(fromUserId, toUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fromUserId=");
		msg.append(fromUserId);

		msg.append(", toUserId=");
		msg.append(toUserId);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 */
	@Override
	public Entry fetchByF_T_First(
		long fromUserId, long toUserId,
		OrderByComparator<Entry> orderByComparator) {

		List<Entry> list = findByF_T(
			fromUserId, toUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws NoSuchEntryException if a matching entry could not be found
	 */
	@Override
	public Entry findByF_T_Last(
			long fromUserId, long toUserId,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = fetchByF_T_Last(fromUserId, toUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fromUserId=");
		msg.append(fromUserId);

		msg.append(", toUserId=");
		msg.append(toUserId);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 */
	@Override
	public Entry fetchByF_T_Last(
		long fromUserId, long toUserId,
		OrderByComparator<Entry> orderByComparator) {

		int count = countByF_T(fromUserId, toUserId);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByF_T(
			fromUserId, toUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws NoSuchEntryException if a entry with the primary key could not be found
	 */
	@Override
	public Entry[] findByF_T_PrevAndNext(
			long entryId, long fromUserId, long toUserId,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByF_T_PrevAndNext(
				session, entry, fromUserId, toUserId, orderByComparator, true);

			array[1] = entry;

			array[2] = getByF_T_PrevAndNext(
				session, entry, fromUserId, toUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByF_T_PrevAndNext(
		Session session, Entry entry, long fromUserId, long toUserId,
		OrderByComparator<Entry> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_F_T_FROMUSERID_2);

		query.append(_FINDER_COLUMN_F_T_TOUSERID_2);

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
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(fromUserId);

		qPos.add(toUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(entry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where fromUserId = &#63; and toUserId = &#63; from the database.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 */
	@Override
	public void removeByF_T(long fromUserId, long toUserId) {
		for (Entry entry :
				findByF_T(
					fromUserId, toUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @return the number of matching entries
	 */
	@Override
	public int countByF_T(long fromUserId, long toUserId) {
		FinderPath finderPath = _finderPathCountByF_T;

		Object[] finderArgs = new Object[] {fromUserId, toUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_F_T_FROMUSERID_2);

			query.append(_FINDER_COLUMN_F_T_TOUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				qPos.add(toUserId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_F_T_FROMUSERID_2 =
		"entry.fromUserId = ? AND ";

	private static final String _FINDER_COLUMN_F_T_TOUSERID_2 =
		"entry.toUserId = ?";

	private FinderPath _finderPathWithPaginationFindByC_F_T;
	private FinderPath _finderPathWithoutPaginationFindByC_F_T;
	private FinderPath _finderPathCountByC_F_T;

	/**
	 * Returns all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @return the matching entries
	 */
	@Override
	public List<Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId) {

		return findByC_F_T(
			createDate, fromUserId, toUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 */
	@Override
	public List<Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId, int start, int end) {

		return findByC_F_T(createDate, fromUserId, toUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findByC_F_T(long,long,long, int, int, OrderByComparator)}
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching entries
	 */
	@Deprecated
	@Override
	public List<Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId, int start, int end,
		OrderByComparator<Entry> orderByComparator, boolean useFinderCache) {

		return findByC_F_T(
			createDate, fromUserId, toUserId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 */
	@Override
	public List<Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId, int start, int end,
		OrderByComparator<Entry> orderByComparator) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByC_F_T;
			finderArgs = new Object[] {createDate, fromUserId, toUserId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByC_F_T;
			finderArgs = new Object[] {
				createDate, fromUserId, toUserId, start, end, orderByComparator
			};
		}

		List<Entry> list = (List<Entry>)finderCache.getResult(
			finderPath, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((createDate != entry.getCreateDate()) ||
					(fromUserId != entry.getFromUserId()) ||
					(toUserId != entry.getToUserId())) {

					list = null;

					break;
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

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_F_T_CREATEDATE_2);

			query.append(_FINDER_COLUMN_C_F_T_FROMUSERID_2);

			query.append(_FINDER_COLUMN_C_F_T_TOUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(fromUserId);

				qPos.add(toUserId);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws NoSuchEntryException if a matching entry could not be found
	 */
	@Override
	public Entry findByC_F_T_First(
			long createDate, long fromUserId, long toUserId,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = fetchByC_F_T_First(
			createDate, fromUserId, toUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", fromUserId=");
		msg.append(fromUserId);

		msg.append(", toUserId=");
		msg.append(toUserId);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 */
	@Override
	public Entry fetchByC_F_T_First(
		long createDate, long fromUserId, long toUserId,
		OrderByComparator<Entry> orderByComparator) {

		List<Entry> list = findByC_F_T(
			createDate, fromUserId, toUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws NoSuchEntryException if a matching entry could not be found
	 */
	@Override
	public Entry findByC_F_T_Last(
			long createDate, long fromUserId, long toUserId,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = fetchByC_F_T_Last(
			createDate, fromUserId, toUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", fromUserId=");
		msg.append(fromUserId);

		msg.append(", toUserId=");
		msg.append(toUserId);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 */
	@Override
	public Entry fetchByC_F_T_Last(
		long createDate, long fromUserId, long toUserId,
		OrderByComparator<Entry> orderByComparator) {

		int count = countByC_F_T(createDate, fromUserId, toUserId);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByC_F_T(
			createDate, fromUserId, toUserId, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws NoSuchEntryException if a entry with the primary key could not be found
	 */
	@Override
	public Entry[] findByC_F_T_PrevAndNext(
			long entryId, long createDate, long fromUserId, long toUserId,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByC_F_T_PrevAndNext(
				session, entry, createDate, fromUserId, toUserId,
				orderByComparator, true);

			array[1] = entry;

			array[2] = getByC_F_T_PrevAndNext(
				session, entry, createDate, fromUserId, toUserId,
				orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByC_F_T_PrevAndNext(
		Session session, Entry entry, long createDate, long fromUserId,
		long toUserId, OrderByComparator<Entry> orderByComparator,
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

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_C_F_T_CREATEDATE_2);

		query.append(_FINDER_COLUMN_C_F_T_FROMUSERID_2);

		query.append(_FINDER_COLUMN_C_F_T_TOUSERID_2);

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
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(createDate);

		qPos.add(fromUserId);

		qPos.add(toUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(entry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63; from the database.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 */
	@Override
	public void removeByC_F_T(long createDate, long fromUserId, long toUserId) {
		for (Entry entry :
				findByC_F_T(
					createDate, fromUserId, toUserId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @return the number of matching entries
	 */
	@Override
	public int countByC_F_T(long createDate, long fromUserId, long toUserId) {
		FinderPath finderPath = _finderPathCountByC_F_T;

		Object[] finderArgs = new Object[] {createDate, fromUserId, toUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_F_T_CREATEDATE_2);

			query.append(_FINDER_COLUMN_C_F_T_FROMUSERID_2);

			query.append(_FINDER_COLUMN_C_F_T_TOUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(fromUserId);

				qPos.add(toUserId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_F_T_CREATEDATE_2 =
		"entry.createDate = ? AND ";

	private static final String _FINDER_COLUMN_C_F_T_FROMUSERID_2 =
		"entry.fromUserId = ? AND ";

	private static final String _FINDER_COLUMN_C_F_T_TOUSERID_2 =
		"entry.toUserId = ?";

	private FinderPath _finderPathWithPaginationFindByF_T_C;
	private FinderPath _finderPathWithoutPaginationFindByF_T_C;
	private FinderPath _finderPathCountByF_T_C;

	/**
	 * Returns all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @return the matching entries
	 */
	@Override
	public List<Entry> findByF_T_C(
		long fromUserId, long toUserId, String content) {

		return findByF_T_C(
			fromUserId, toUserId, content, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 */
	@Override
	public List<Entry> findByF_T_C(
		long fromUserId, long toUserId, String content, int start, int end) {

		return findByF_T_C(fromUserId, toUserId, content, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findByF_T_C(long,long,String, int, int, OrderByComparator)}
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching entries
	 */
	@Deprecated
	@Override
	public List<Entry> findByF_T_C(
		long fromUserId, long toUserId, String content, int start, int end,
		OrderByComparator<Entry> orderByComparator, boolean useFinderCache) {

		return findByF_T_C(
			fromUserId, toUserId, content, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 */
	@Override
	public List<Entry> findByF_T_C(
		long fromUserId, long toUserId, String content, int start, int end,
		OrderByComparator<Entry> orderByComparator) {

		content = Objects.toString(content, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByF_T_C;
			finderArgs = new Object[] {fromUserId, toUserId, content};
		}
		else {
			finderPath = _finderPathWithPaginationFindByF_T_C;
			finderArgs = new Object[] {
				fromUserId, toUserId, content, start, end, orderByComparator
			};
		}

		List<Entry> list = (List<Entry>)finderCache.getResult(
			finderPath, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((fromUserId != entry.getFromUserId()) ||
					(toUserId != entry.getToUserId()) ||
					!content.equals(entry.getContent())) {

					list = null;

					break;
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

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_F_T_C_FROMUSERID_2);

			query.append(_FINDER_COLUMN_F_T_C_TOUSERID_2);

			boolean bindContent = false;

			if (content.isEmpty()) {
				query.append(_FINDER_COLUMN_F_T_C_CONTENT_3);
			}
			else {
				bindContent = true;

				query.append(_FINDER_COLUMN_F_T_C_CONTENT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				qPos.add(toUserId);

				if (bindContent) {
					qPos.add(content);
				}

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws NoSuchEntryException if a matching entry could not be found
	 */
	@Override
	public Entry findByF_T_C_First(
			long fromUserId, long toUserId, String content,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = fetchByF_T_C_First(
			fromUserId, toUserId, content, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fromUserId=");
		msg.append(fromUserId);

		msg.append(", toUserId=");
		msg.append(toUserId);

		msg.append(", content=");
		msg.append(content);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 */
	@Override
	public Entry fetchByF_T_C_First(
		long fromUserId, long toUserId, String content,
		OrderByComparator<Entry> orderByComparator) {

		List<Entry> list = findByF_T_C(
			fromUserId, toUserId, content, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws NoSuchEntryException if a matching entry could not be found
	 */
	@Override
	public Entry findByF_T_C_Last(
			long fromUserId, long toUserId, String content,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		Entry entry = fetchByF_T_C_Last(
			fromUserId, toUserId, content, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fromUserId=");
		msg.append(fromUserId);

		msg.append(", toUserId=");
		msg.append(toUserId);

		msg.append(", content=");
		msg.append(content);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 */
	@Override
	public Entry fetchByF_T_C_Last(
		long fromUserId, long toUserId, String content,
		OrderByComparator<Entry> orderByComparator) {

		int count = countByF_T_C(fromUserId, toUserId, content);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByF_T_C(
			fromUserId, toUserId, content, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws NoSuchEntryException if a entry with the primary key could not be found
	 */
	@Override
	public Entry[] findByF_T_C_PrevAndNext(
			long entryId, long fromUserId, long toUserId, String content,
			OrderByComparator<Entry> orderByComparator)
		throws NoSuchEntryException {

		content = Objects.toString(content, "");

		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByF_T_C_PrevAndNext(
				session, entry, fromUserId, toUserId, content,
				orderByComparator, true);

			array[1] = entry;

			array[2] = getByF_T_C_PrevAndNext(
				session, entry, fromUserId, toUserId, content,
				orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByF_T_C_PrevAndNext(
		Session session, Entry entry, long fromUserId, long toUserId,
		String content, OrderByComparator<Entry> orderByComparator,
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

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_F_T_C_FROMUSERID_2);

		query.append(_FINDER_COLUMN_F_T_C_TOUSERID_2);

		boolean bindContent = false;

		if (content.isEmpty()) {
			query.append(_FINDER_COLUMN_F_T_C_CONTENT_3);
		}
		else {
			bindContent = true;

			query.append(_FINDER_COLUMN_F_T_C_CONTENT_2);
		}

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
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(fromUserId);

		qPos.add(toUserId);

		if (bindContent) {
			qPos.add(content);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(entry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63; from the database.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 */
	@Override
	public void removeByF_T_C(long fromUserId, long toUserId, String content) {
		for (Entry entry :
				findByF_T_C(
					fromUserId, toUserId, content, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @return the number of matching entries
	 */
	@Override
	public int countByF_T_C(long fromUserId, long toUserId, String content) {
		content = Objects.toString(content, "");

		FinderPath finderPath = _finderPathCountByF_T_C;

		Object[] finderArgs = new Object[] {fromUserId, toUserId, content};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_F_T_C_FROMUSERID_2);

			query.append(_FINDER_COLUMN_F_T_C_TOUSERID_2);

			boolean bindContent = false;

			if (content.isEmpty()) {
				query.append(_FINDER_COLUMN_F_T_C_CONTENT_3);
			}
			else {
				bindContent = true;

				query.append(_FINDER_COLUMN_F_T_C_CONTENT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				qPos.add(toUserId);

				if (bindContent) {
					qPos.add(content);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_F_T_C_FROMUSERID_2 =
		"entry.fromUserId = ? AND ";

	private static final String _FINDER_COLUMN_F_T_C_TOUSERID_2 =
		"entry.toUserId = ? AND ";

	private static final String _FINDER_COLUMN_F_T_C_CONTENT_2 =
		"entry.content = ?";

	private static final String _FINDER_COLUMN_F_T_C_CONTENT_3 =
		"(entry.content IS NULL OR entry.content = '')";

	public EntryPersistenceImpl() {
		setModelClass(Entry.class);

		setModelImplClass(EntryImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the entry in the entity cache if it is enabled.
	 *
	 * @param entry the entry
	 */
	@Override
	public void cacheResult(Entry entry) {
		entityCache.putResult(
			entityCacheEnabled, EntryImpl.class, entry.getPrimaryKey(), entry);

		entry.resetOriginalValues();
	}

	/**
	 * Caches the entries in the entity cache if it is enabled.
	 *
	 * @param entries the entries
	 */
	@Override
	public void cacheResult(List<Entry> entries) {
		for (Entry entry : entries) {
			if (entityCache.getResult(
					entityCacheEnabled, EntryImpl.class,
					entry.getPrimaryKey()) == null) {

				cacheResult(entry);
			}
			else {
				entry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Entry entry) {
		entityCache.removeResult(
			entityCacheEnabled, EntryImpl.class, entry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Entry> entries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Entry entry : entries) {
			entityCache.removeResult(
				entityCacheEnabled, EntryImpl.class, entry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new entry with the primary key. Does not add the entry to the database.
	 *
	 * @param entryId the primary key for the new entry
	 * @return the new entry
	 */
	@Override
	public Entry create(long entryId) {
		Entry entry = new EntryImpl();

		entry.setNew(true);
		entry.setPrimaryKey(entryId);

		return entry;
	}

	/**
	 * Removes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the entry
	 * @return the entry that was removed
	 * @throws NoSuchEntryException if a entry with the primary key could not be found
	 */
	@Override
	public Entry remove(long entryId) throws NoSuchEntryException {
		return remove((Serializable)entryId);
	}

	/**
	 * Removes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the entry
	 * @return the entry that was removed
	 * @throws NoSuchEntryException if a entry with the primary key could not be found
	 */
	@Override
	public Entry remove(Serializable primaryKey) throws NoSuchEntryException {
		Session session = null;

		try {
			session = openSession();

			Entry entry = (Entry)session.get(EntryImpl.class, primaryKey);

			if (entry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(entry);
		}
		catch (NoSuchEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Entry removeImpl(Entry entry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(entry)) {
				entry = (Entry)session.get(
					EntryImpl.class, entry.getPrimaryKeyObj());
			}

			if (entry != null) {
				session.delete(entry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (entry != null) {
			clearCache(entry);
		}

		return entry;
	}

	@Override
	public Entry updateImpl(Entry entry) {
		boolean isNew = entry.isNew();

		if (!(entry instanceof EntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(entry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(entry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in entry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Entry implementation " +
					entry.getClass());
		}

		EntryModelImpl entryModelImpl = (EntryModelImpl)entry;

		Session session = null;

		try {
			session = openSession();

			if (entry.isNew()) {
				session.save(entry);

				entry.setNew(false);
			}
			else {
				entry = (Entry)session.merge(entry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {entryModelImpl.getCreateDate()};

			finderCache.removeResult(_finderPathCountByCreateDate, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCreateDate, args);

			args = new Object[] {entryModelImpl.getFromUserId()};

			finderCache.removeResult(_finderPathCountByFromUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByFromUserId, args);

			args = new Object[] {entryModelImpl.getToUserId()};

			finderCache.removeResult(_finderPathCountByToUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByToUserId, args);

			args = new Object[] {
				entryModelImpl.getCreateDate(), entryModelImpl.getFromUserId()
			};

			finderCache.removeResult(_finderPathCountByC_F, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_F, args);

			args = new Object[] {
				entryModelImpl.getCreateDate(), entryModelImpl.getToUserId()
			};

			finderCache.removeResult(_finderPathCountByC_T, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_T, args);

			args = new Object[] {
				entryModelImpl.getFromUserId(), entryModelImpl.getToUserId()
			};

			finderCache.removeResult(_finderPathCountByF_T, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByF_T, args);

			args = new Object[] {
				entryModelImpl.getCreateDate(), entryModelImpl.getFromUserId(),
				entryModelImpl.getToUserId()
			};

			finderCache.removeResult(_finderPathCountByC_F_T, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_F_T, args);

			args = new Object[] {
				entryModelImpl.getFromUserId(), entryModelImpl.getToUserId(),
				entryModelImpl.getContent()
			};

			finderCache.removeResult(_finderPathCountByF_T_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByF_T_C, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((entryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCreateDate.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					entryModelImpl.getOriginalCreateDate()
				};

				finderCache.removeResult(_finderPathCountByCreateDate, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCreateDate, args);

				args = new Object[] {entryModelImpl.getCreateDate()};

				finderCache.removeResult(_finderPathCountByCreateDate, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCreateDate, args);
			}

			if ((entryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByFromUserId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					entryModelImpl.getOriginalFromUserId()
				};

				finderCache.removeResult(_finderPathCountByFromUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByFromUserId, args);

				args = new Object[] {entryModelImpl.getFromUserId()};

				finderCache.removeResult(_finderPathCountByFromUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByFromUserId, args);
			}

			if ((entryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByToUserId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					entryModelImpl.getOriginalToUserId()
				};

				finderCache.removeResult(_finderPathCountByToUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByToUserId, args);

				args = new Object[] {entryModelImpl.getToUserId()};

				finderCache.removeResult(_finderPathCountByToUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByToUserId, args);
			}

			if ((entryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_F.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					entryModelImpl.getOriginalCreateDate(),
					entryModelImpl.getOriginalFromUserId()
				};

				finderCache.removeResult(_finderPathCountByC_F, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_F, args);

				args = new Object[] {
					entryModelImpl.getCreateDate(),
					entryModelImpl.getFromUserId()
				};

				finderCache.removeResult(_finderPathCountByC_F, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_F, args);
			}

			if ((entryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_T.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					entryModelImpl.getOriginalCreateDate(),
					entryModelImpl.getOriginalToUserId()
				};

				finderCache.removeResult(_finderPathCountByC_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_T, args);

				args = new Object[] {
					entryModelImpl.getCreateDate(), entryModelImpl.getToUserId()
				};

				finderCache.removeResult(_finderPathCountByC_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_T, args);
			}

			if ((entryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByF_T.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					entryModelImpl.getOriginalFromUserId(),
					entryModelImpl.getOriginalToUserId()
				};

				finderCache.removeResult(_finderPathCountByF_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByF_T, args);

				args = new Object[] {
					entryModelImpl.getFromUserId(), entryModelImpl.getToUserId()
				};

				finderCache.removeResult(_finderPathCountByF_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByF_T, args);
			}

			if ((entryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_F_T.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					entryModelImpl.getOriginalCreateDate(),
					entryModelImpl.getOriginalFromUserId(),
					entryModelImpl.getOriginalToUserId()
				};

				finderCache.removeResult(_finderPathCountByC_F_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_F_T, args);

				args = new Object[] {
					entryModelImpl.getCreateDate(),
					entryModelImpl.getFromUserId(), entryModelImpl.getToUserId()
				};

				finderCache.removeResult(_finderPathCountByC_F_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_F_T, args);
			}

			if ((entryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByF_T_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					entryModelImpl.getOriginalFromUserId(),
					entryModelImpl.getOriginalToUserId(),
					entryModelImpl.getOriginalContent()
				};

				finderCache.removeResult(_finderPathCountByF_T_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByF_T_C, args);

				args = new Object[] {
					entryModelImpl.getFromUserId(),
					entryModelImpl.getToUserId(), entryModelImpl.getContent()
				};

				finderCache.removeResult(_finderPathCountByF_T_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByF_T_C, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, EntryImpl.class, entry.getPrimaryKey(), entry,
			false);

		entry.resetOriginalValues();

		return entry;
	}

	/**
	 * Returns the entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the entry
	 * @return the entry
	 * @throws NoSuchEntryException if a entry with the primary key could not be found
	 */
	@Override
	public Entry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntryException {

		Entry entry = fetchByPrimaryKey(primaryKey);

		if (entry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return entry;
	}

	/**
	 * Returns the entry with the primary key or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param entryId the primary key of the entry
	 * @return the entry
	 * @throws NoSuchEntryException if a entry with the primary key could not be found
	 */
	@Override
	public Entry findByPrimaryKey(long entryId) throws NoSuchEntryException {
		return findByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns the entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entryId the primary key of the entry
	 * @return the entry, or <code>null</code> if a entry with the primary key could not be found
	 */
	@Override
	public Entry fetchByPrimaryKey(long entryId) {
		return fetchByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns all the entries.
	 *
	 * @return the entries
	 */
	@Override
	public List<Entry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of entries
	 */
	@Override
	public List<Entry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findAll(int, int, OrderByComparator)}
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of entries
	 */
	@Deprecated
	@Override
	public List<Entry> findAll(
		int start, int end, OrderByComparator<Entry> orderByComparator,
		boolean useFinderCache) {

		return findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of entries
	 */
	@Override
	public List<Entry> findAll(
		int start, int end, OrderByComparator<Entry> orderByComparator) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Entry> list = (List<Entry>)finderCache.getResult(
			finderPath, finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ENTRY);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ENTRY;

				if (pagination) {
					sql = sql.concat(EntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Entry entry : findAll()) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries.
	 *
	 * @return the number of entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ENTRY);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
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
		return "entryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the entry persistence.
	 */
	@Activate
	public void activate() {
		EntryModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		EntryModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByCreateDate = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCreateDate",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCreateDate = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCreateDate",
			new String[] {Long.class.getName()},
			EntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByCreateDate = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCreateDate",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByFromUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFromUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByFromUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFromUserId",
			new String[] {Long.class.getName()},
			EntryModelImpl.FROMUSERID_COLUMN_BITMASK |
			EntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByFromUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFromUserId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByToUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByToUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByToUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByToUserId",
			new String[] {Long.class.getName()},
			EntryModelImpl.TOUSERID_COLUMN_BITMASK |
			EntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByToUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByToUserId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByC_F = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_F",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_F = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_F",
			new String[] {Long.class.getName(), Long.class.getName()},
			EntryModelImpl.CREATEDATE_COLUMN_BITMASK |
			EntryModelImpl.FROMUSERID_COLUMN_BITMASK);

		_finderPathCountByC_F = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_F",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByC_T = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_T = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_T",
			new String[] {Long.class.getName(), Long.class.getName()},
			EntryModelImpl.CREATEDATE_COLUMN_BITMASK |
			EntryModelImpl.TOUSERID_COLUMN_BITMASK);

		_finderPathCountByC_T = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_T",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByF_T = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByF_T = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_T",
			new String[] {Long.class.getName(), Long.class.getName()},
			EntryModelImpl.FROMUSERID_COLUMN_BITMASK |
			EntryModelImpl.TOUSERID_COLUMN_BITMASK |
			EntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByF_T = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_T",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByC_F_T = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_F_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_F_T = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_F_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			EntryModelImpl.CREATEDATE_COLUMN_BITMASK |
			EntryModelImpl.FROMUSERID_COLUMN_BITMASK |
			EntryModelImpl.TOUSERID_COLUMN_BITMASK);

		_finderPathCountByC_F_T = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_F_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

		_finderPathWithPaginationFindByF_T_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_T_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByF_T_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_T_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			EntryModelImpl.FROMUSERID_COLUMN_BITMASK |
			EntryModelImpl.TOUSERID_COLUMN_BITMASK |
			EntryModelImpl.CONTENT_COLUMN_BITMASK |
			EntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByF_T_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_T_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(EntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = ChatPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.liferay.chat.model.Entry"),
			true);
	}

	@Override
	@Reference(
		target = ChatPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = ChatPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static final String _SQL_SELECT_ENTRY =
		"SELECT entry FROM Entry entry";

	private static final String _SQL_SELECT_ENTRY_WHERE =
		"SELECT entry FROM Entry entry WHERE ";

	private static final String _SQL_COUNT_ENTRY =
		"SELECT COUNT(entry) FROM Entry entry";

	private static final String _SQL_COUNT_ENTRY_WHERE =
		"SELECT COUNT(entry) FROM Entry entry WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "entry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Entry exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Entry exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		EntryPersistenceImpl.class);

	static {
		try {
			Class.forName(ChatPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException cnfe) {
			throw new ExceptionInInitializerError(cnfe);
		}
	}

}