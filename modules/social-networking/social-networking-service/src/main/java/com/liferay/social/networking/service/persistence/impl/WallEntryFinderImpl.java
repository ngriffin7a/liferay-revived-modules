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

package com.liferay.social.networking.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.social.networking.model.WallEntry;
import com.liferay.social.networking.model.impl.WallEntryImpl;
import com.liferay.social.networking.service.persistence.WallEntryFinder;
import com.liferay.util.dao.orm.CustomSQLUtil;
import org.osgi.service.component.annotations.Component;

import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(service=WallEntryFinder.class)
public class WallEntryFinderImpl
	extends WallEntryFinderBaseImpl implements WallEntryFinder {

	public static final String COUNT_BY_G1_G2_U1_U2 =
		WallEntryFinder.class.getName() + ".countByG1_G2_U1_U2";

	public static final String FIND_BY_G1_G2_U1_U2 =
		WallEntryFinder.class.getName() + ".findByG1_G2_U1_U2";

	@Override
	public int countByG1_G2_U1_U2(
		long groupId1, long groupId2, long userId1, long userId2) {

		Session session = null;

		try {
			session = openSession();

			// String sql = CustomSQLUtil.get(getClass(), COUNT_BY_G1_G2_U1_U2);
			String sql = CustomSQLUtil.get(COUNT_BY_G1_G2_U1_U2);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId1);
			qPos.add(groupId2);
			qPos.add(userId1);
			qPos.add(userId2);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<WallEntry> findByG1_G2_U1_U2(
		long groupId1, long groupId2, long userId1, long userId2, int start,
		int end) {

		Session session = null;

		try {
			session = openSession();

			// String sql = CustomSQLUtil.get(getClass(), FIND_BY_G1_G2_U1_U2);
			String sql = CustomSQLUtil.get(FIND_BY_G1_G2_U1_U2);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("SN_WallEntry", WallEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId1);
			qPos.add(groupId2);
			qPos.add(userId1);
			qPos.add(userId2);

			return (List<WallEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}