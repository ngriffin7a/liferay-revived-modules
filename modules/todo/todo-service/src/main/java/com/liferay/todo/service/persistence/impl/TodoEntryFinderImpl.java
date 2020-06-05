/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.todo.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.todo.model.TodoEntry;
import com.liferay.todo.model.TodoEntryConstants;
import com.liferay.todo.model.impl.TodoEntryImpl;
import com.liferay.todo.service.persistence.TodoEntryFinder;
import com.liferay.todo.service.persistence.TodoEntryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Iterator;
import java.util.List;

/**
 * @author Ryan Park
 */
@Component(service = TodoEntryFinder.class)
public class TodoEntryFinderImpl
	extends TodoEntryFinderBaseImpl implements TodoEntryFinder {

	public static final String COUNT_BY_G_U_P_A_S_T_N =
		TodoEntryFinder.class.getName() + ".countByG_U_P_A_S_T_N";

	public static final String FIND_BY_G_U_P_A_S_T_N =
		TodoEntryFinder.class.getName() + ".findByG_U_P_A_S_T_N";

	public static final String JOIN_BY_ASSET_TAGS =
		TodoEntryFinder.class.getName() + ".joinByAssetTags";

	public static final String JOIN_BY_NOT_ASSET_TAGS =
		TodoEntryFinder.class.getName() + ".joinByNotAssetTags";

	@Override
	public int countByG_U_P_A_S_T_N(
		long groupId, long userId, int priority, long assigneeUserId,
		int status, long[] assetTagIds, long[] notAssetTagIds) {

		if ((priority <= 0) && (assetTagIds.length == 0) &&
			(notAssetTagIds.length == 0)) {

			if ((userId > 0) && (assigneeUserId <= 0)) {
				return countByG_U_S(groupId, userId, status);
			}

			if ((userId <= 0) && (assigneeUserId > 0)) {
				return countByG_A_S(groupId, assigneeUserId, status);
			}
		}

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), COUNT_BY_G_U_P_A_S_T_N);

			sql = StringUtil.replace(
				sql, "[$JOIN$]", getJoin(assetTagIds, notAssetTagIds));
			sql = StringUtil.replace(
				sql, "[$ASSET_TAG_TAG_IDS$]",
				getAssetTagTagIds(assetTagIds, notAssetTagIds));

			if (assetTagIds.length > 0) {
				sql = StringUtil.replaceLast(sql, "WHERE", StringPool.BLANK);
			}

			sql = StringUtil.replace(sql, "[$GROUP_ID$]", getGroupId(groupId));
			sql = StringUtil.replace(sql, "[$USER_ID$]", getUserId(userId));
			sql = StringUtil.replace(
				sql, "[$PRIORITY$]", getPriority(priority));
			sql = StringUtil.replace(
				sql, "[$ASSIGNEE_USER_ID$]", getAssigneeUserId(assigneeUserId));

			int[] statuses = getStatuses(status);

			sql = StringUtil.replace(sql, "[$STATUS$]", getStatus(statuses));

			sql = StringUtil.replaceLast(sql, "AND", StringPool.BLANK);

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if ((assetTagIds.length > 0) || (notAssetTagIds.length > 0)) {
				queryPos.add(
					PortalUtil.getClassNameId(TodoEntry.class.getName()));
				queryPos.add(assetTagIds);
				queryPos.add(notAssetTagIds);
			}

			if (groupId > 0) {
				queryPos.add(groupId);
			}

			if (userId > 0) {
				queryPos.add(userId);
			}

			if (priority > 0) {
				queryPos.add(priority);
			}

			if (assigneeUserId > 0) {
				queryPos.add(assigneeUserId);
			}

			queryPos.add(statuses);

			Iterator<Long> itr = sqlQuery.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<TodoEntry> findByG_U_P_A_S_T_N(
		long groupId, long userId, int priority, long assigneeUserId,
		int status, long[] assetTagIds, long[] notAssetTagIds, int start,
		int end) {

		if ((priority <= 0) && (assetTagIds.length == 0) &&
			(notAssetTagIds.length == 0)) {

			if ((userId > 0) && (assigneeUserId <= 0)) {
				return findByG_U_S(groupId, userId, status, start, end);
			}

			if ((userId <= 0) && (assigneeUserId > 0)) {
				return findByG_A_S(groupId, assigneeUserId, status, start, end);
			}
		}

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_G_U_P_A_S_T_N);

			sql = StringUtil.replace(
				sql, "[$JOIN$]", getJoin(assetTagIds, notAssetTagIds));
			sql = StringUtil.replace(
				sql, "[$ASSET_TAG_TAG_IDS$]",
				getAssetTagTagIds(assetTagIds, notAssetTagIds));

			if (assetTagIds.length > 0) {
				sql = StringUtil.replaceLast(sql, "WHERE", StringPool.BLANK);
			}

			sql = StringUtil.replace(sql, "[$GROUP_ID$]", getGroupId(groupId));
			sql = StringUtil.replace(sql, "[$USER_ID$]", getUserId(userId));
			sql = StringUtil.replace(
				sql, "[$PRIORITY$]", getPriority(priority));
			sql = StringUtil.replace(
				sql, "[$ASSIGNEE_USER_ID$]", getAssigneeUserId(assigneeUserId));

			int[] statuses = getStatuses(status);

			sql = StringUtil.replace(sql, "[$STATUS$]", getStatus(statuses));

			sql = StringUtil.replaceLast(sql, "AND", StringPool.BLANK);

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addEntity("TDL_TodoEntry", TodoEntryImpl.class);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if ((assetTagIds.length > 0) || (notAssetTagIds.length > 0)) {
				queryPos.add(
					PortalUtil.getClassNameId(TodoEntry.class.getName()));
				queryPos.add(assetTagIds);
				queryPos.add(notAssetTagIds);
			}

			if (groupId > 0) {
				queryPos.add(groupId);
			}

			if (userId > 0) {
				queryPos.add(userId);
			}

			if (priority > 0) {
				queryPos.add(priority);
			}

			if (assigneeUserId > 0) {
				queryPos.add(assigneeUserId);
			}

			queryPos.add(statuses);

			return (List<TodoEntry>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected int countByG_A_S(long groupId, long assigneeUserId, int status) {
		if (status != TodoEntryConstants.STATUS_ALL) {
			if (groupId > 0) {
				return TodoEntryUtil.countByG_A_S(
					groupId, assigneeUserId, getStatuses(status));
			}

			return TodoEntryUtil.countByA_S(
				assigneeUserId, getStatuses(status));
		}

		if (groupId > 0) {
			return TodoEntryUtil.countByG_A(groupId, assigneeUserId);
		}

		return TodoEntryUtil.countByAssigneeUserId(assigneeUserId);
	}

	protected int countByG_U_S(long groupId, long userId, int status) {
		if (status != TodoEntryConstants.STATUS_ALL) {
			if (groupId > 0) {
				return TodoEntryUtil.countByG_U_S(
					groupId, userId, getStatuses(status));
			}

			return TodoEntryUtil.countByU_S(userId, getStatuses(status));
		}

		if (groupId > 0) {
			return TodoEntryUtil.countByG_U(groupId, userId);
		}

		return TodoEntryUtil.countByUserId(userId);
	}

	protected List<TodoEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end) {

		if (status != TodoEntryConstants.STATUS_ALL) {
			if (groupId > 0) {
				return TodoEntryUtil.findByG_A_S(
					groupId, assigneeUserId, getStatuses(status), start, end);
			}

			return TodoEntryUtil.findByA_S(
				assigneeUserId, getStatuses(status), start, end);
		}

		if (groupId > 0) {
			return TodoEntryUtil.findByG_A(
				groupId, assigneeUserId, start, end);
		}

		return TodoEntryUtil.findByAssigneeUserId(assigneeUserId, start, end);
	}

	protected List<TodoEntry> findByG_U_S(
		long groupId, long userId, int status, int start, int end) {

		if (status != TodoEntryConstants.STATUS_ALL) {
			if (groupId > 0) {
				return TodoEntryUtil.findByG_U_S(
					groupId, userId, getStatuses(status), start, end);
			}

			return TodoEntryUtil.findByU_S(
				userId, getStatuses(status), start, end);
		}

		if (groupId > 0) {
			return TodoEntryUtil.findByG_U(groupId, userId, start, end);
		}

		return TodoEntryUtil.findByUserId(userId, start, end);
	}

	protected String getAssetTagTagIds(
		long[] assetTagIds, boolean equalsOperator) {

		StringBundler sb = new StringBundler((assetTagIds.length * 4) + 1);

		sb.append(" (");

		for (int i = 0; i < assetTagIds.length; i++) {
			sb.append("AssetEntries_AssetTags.tagId ");

			if (equalsOperator) {
				sb.append(StringPool.EQUAL);
			}
			else {
				sb.append(StringPool.NOT_EQUAL);
			}

			sb.append(" ? ");

			if ((i + 1) != assetTagIds.length) {
				if (equalsOperator) {
					sb.append("OR ");
				}
				else {
					sb.append("AND ");
				}
			}
		}

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected String getAssetTagTagIds(
		long[] assetTagIds, long[] notAssetTagIds) {

		if ((assetTagIds != null) && (assetTagIds.length > 0)) {
			return getAssetTagTagIds(assetTagIds, true);
		}

		if ((notAssetTagIds != null) && (notAssetTagIds.length > 0)) {
			return getAssetTagTagIds(notAssetTagIds, false);
		}

		return StringPool.BLANK;
	}

	protected String getAssigneeUserId(long assigneeUserId) {
		if (assigneeUserId > 0) {
			return "TDL_TodoEntry.assigneeUserId = ? AND";
		}

		return StringPool.BLANK;
	}

	protected String getGroupId(long groupId) {
		if (groupId > 0) {
			return "TDL_TodoEntry.groupId = ? AND";
		}

		return StringPool.BLANK;
	}

	protected String getJoin(long[] assetTagIds, long[] notAssetTagIds) {
		if ((assetTagIds != null) && (assetTagIds.length > 0)) {
			return _customSQL.get(getClass(), JOIN_BY_ASSET_TAGS);
		}

		if ((notAssetTagIds != null) && (notAssetTagIds.length > 0)) {
			return _customSQL.get(getClass(), JOIN_BY_NOT_ASSET_TAGS);
		}

		return StringPool.BLANK;
	}

	protected String getPriority(int priority) {
		if (priority > 0) {
			return "TDL_TodoEntry.priority = ? AND";
		}

		return StringPool.BLANK;
	}

	protected String getStatus(int[] statuses) {
		if (statuses.length == 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler((statuses.length * 2) + 1);

		sb.append(" (");

		for (int i = 0; i < statuses.length; i++) {
			sb.append("TDL_TodoEntry.status = ? ");
			sb.append("OR ");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(") AND ");

		return sb.toString();
	}

	protected int[] getStatuses(int status) {
		if (status == TodoEntryConstants.STATUS_ALL) {
			return new int[0];
		}

		if (status == TodoEntryConstants.STATUS_RESOLVED) {
			return _RESOLVED_STATUS_ARRAY;
		}

		return _OPEN_STATUSES_ARRAY;
	}

	protected String getUserId(long userId) {
		if (userId > 0) {
			return "TDL_TodoEntry.userId = ? AND";
		}

		return StringPool.BLANK;
	}

	private static final int[] _OPEN_STATUSES_ARRAY = {
		TodoEntryConstants.STATUS_OPEN,
		TodoEntryConstants.STATUS_PERCENT_TWENTY,
		TodoEntryConstants.STATUS_PERCENT_SIXTY,
		TodoEntryConstants.STATUS_PERCENT_FORTY,
		TodoEntryConstants.STATUS_PERCENT_EIGHTY,
		TodoEntryConstants.STATUS_REOPENED
	};

	private static final int[] _RESOLVED_STATUS_ARRAY = {
		TodoEntryConstants.STATUS_RESOLVED
	};

	@Reference
	private CustomSQL _customSQL;

}