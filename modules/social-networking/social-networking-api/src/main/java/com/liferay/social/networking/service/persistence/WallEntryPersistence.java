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

package com.liferay.social.networking.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.social.networking.exception.NoSuchWallEntryException;
import com.liferay.social.networking.model.WallEntry;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the wall entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WallEntryUtil
 * @generated
 */
@ProviderType
public interface WallEntryPersistence extends BasePersistence<WallEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WallEntryUtil} to access the wall entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the wall entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching wall entries
	 */
	public java.util.List<WallEntry> findByGroupId(long groupId);

	/**
	 * Returns a range of all the wall entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WallEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @return the range of matching wall entries
	 */
	public java.util.List<WallEntry> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the wall entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WallEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching wall entries
	 */
	public java.util.List<WallEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the wall entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WallEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching wall entries
	 */
	public java.util.List<WallEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first wall entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wall entry
	 * @throws NoSuchWallEntryException if a matching wall entry could not be found
	 */
	public WallEntry findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
				orderByComparator)
		throws NoSuchWallEntryException;

	/**
	 * Returns the first wall entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wall entry, or <code>null</code> if a matching wall entry could not be found
	 */
	public WallEntry fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
			orderByComparator);

	/**
	 * Returns the last wall entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wall entry
	 * @throws NoSuchWallEntryException if a matching wall entry could not be found
	 */
	public WallEntry findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
				orderByComparator)
		throws NoSuchWallEntryException;

	/**
	 * Returns the last wall entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wall entry, or <code>null</code> if a matching wall entry could not be found
	 */
	public WallEntry fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
			orderByComparator);

	/**
	 * Returns the wall entries before and after the current wall entry in the ordered set where groupId = &#63;.
	 *
	 * @param wallEntryId the primary key of the current wall entry
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next wall entry
	 * @throws NoSuchWallEntryException if a wall entry with the primary key could not be found
	 */
	public WallEntry[] findByGroupId_PrevAndNext(
			long wallEntryId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
				orderByComparator)
		throws NoSuchWallEntryException;

	/**
	 * Removes all the wall entries where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of wall entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching wall entries
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns all the wall entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching wall entries
	 */
	public java.util.List<WallEntry> findByUserId(long userId);

	/**
	 * Returns a range of all the wall entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WallEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @return the range of matching wall entries
	 */
	public java.util.List<WallEntry> findByUserId(
		long userId, int start, int end);

	/**
	 * Returns an ordered range of all the wall entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WallEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching wall entries
	 */
	public java.util.List<WallEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the wall entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WallEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching wall entries
	 */
	public java.util.List<WallEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first wall entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wall entry
	 * @throws NoSuchWallEntryException if a matching wall entry could not be found
	 */
	public WallEntry findByUserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
				orderByComparator)
		throws NoSuchWallEntryException;

	/**
	 * Returns the first wall entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wall entry, or <code>null</code> if a matching wall entry could not be found
	 */
	public WallEntry fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
			orderByComparator);

	/**
	 * Returns the last wall entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wall entry
	 * @throws NoSuchWallEntryException if a matching wall entry could not be found
	 */
	public WallEntry findByUserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
				orderByComparator)
		throws NoSuchWallEntryException;

	/**
	 * Returns the last wall entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wall entry, or <code>null</code> if a matching wall entry could not be found
	 */
	public WallEntry fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
			orderByComparator);

	/**
	 * Returns the wall entries before and after the current wall entry in the ordered set where userId = &#63;.
	 *
	 * @param wallEntryId the primary key of the current wall entry
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next wall entry
	 * @throws NoSuchWallEntryException if a wall entry with the primary key could not be found
	 */
	public WallEntry[] findByUserId_PrevAndNext(
			long wallEntryId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
				orderByComparator)
		throws NoSuchWallEntryException;

	/**
	 * Removes all the wall entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByUserId(long userId);

	/**
	 * Returns the number of wall entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching wall entries
	 */
	public int countByUserId(long userId);

	/**
	 * Returns all the wall entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching wall entries
	 */
	public java.util.List<WallEntry> findByG_U(long groupId, long userId);

	/**
	 * Returns a range of all the wall entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WallEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @return the range of matching wall entries
	 */
	public java.util.List<WallEntry> findByG_U(
		long groupId, long userId, int start, int end);

	/**
	 * Returns an ordered range of all the wall entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WallEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching wall entries
	 */
	public java.util.List<WallEntry> findByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the wall entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WallEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching wall entries
	 */
	public java.util.List<WallEntry> findByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wall entry
	 * @throws NoSuchWallEntryException if a matching wall entry could not be found
	 */
	public WallEntry findByG_U_First(
			long groupId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
				orderByComparator)
		throws NoSuchWallEntryException;

	/**
	 * Returns the first wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wall entry, or <code>null</code> if a matching wall entry could not be found
	 */
	public WallEntry fetchByG_U_First(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
			orderByComparator);

	/**
	 * Returns the last wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wall entry
	 * @throws NoSuchWallEntryException if a matching wall entry could not be found
	 */
	public WallEntry findByG_U_Last(
			long groupId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
				orderByComparator)
		throws NoSuchWallEntryException;

	/**
	 * Returns the last wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wall entry, or <code>null</code> if a matching wall entry could not be found
	 */
	public WallEntry fetchByG_U_Last(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
			orderByComparator);

	/**
	 * Returns the wall entries before and after the current wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param wallEntryId the primary key of the current wall entry
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next wall entry
	 * @throws NoSuchWallEntryException if a wall entry with the primary key could not be found
	 */
	public WallEntry[] findByG_U_PrevAndNext(
			long wallEntryId, long groupId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
				orderByComparator)
		throws NoSuchWallEntryException;

	/**
	 * Removes all the wall entries where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 */
	public void removeByG_U(long groupId, long userId);

	/**
	 * Returns the number of wall entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching wall entries
	 */
	public int countByG_U(long groupId, long userId);

	/**
	 * Caches the wall entry in the entity cache if it is enabled.
	 *
	 * @param wallEntry the wall entry
	 */
	public void cacheResult(WallEntry wallEntry);

	/**
	 * Caches the wall entries in the entity cache if it is enabled.
	 *
	 * @param wallEntries the wall entries
	 */
	public void cacheResult(java.util.List<WallEntry> wallEntries);

	/**
	 * Creates a new wall entry with the primary key. Does not add the wall entry to the database.
	 *
	 * @param wallEntryId the primary key for the new wall entry
	 * @return the new wall entry
	 */
	public WallEntry create(long wallEntryId);

	/**
	 * Removes the wall entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param wallEntryId the primary key of the wall entry
	 * @return the wall entry that was removed
	 * @throws NoSuchWallEntryException if a wall entry with the primary key could not be found
	 */
	public WallEntry remove(long wallEntryId) throws NoSuchWallEntryException;

	public WallEntry updateImpl(WallEntry wallEntry);

	/**
	 * Returns the wall entry with the primary key or throws a <code>NoSuchWallEntryException</code> if it could not be found.
	 *
	 * @param wallEntryId the primary key of the wall entry
	 * @return the wall entry
	 * @throws NoSuchWallEntryException if a wall entry with the primary key could not be found
	 */
	public WallEntry findByPrimaryKey(long wallEntryId)
		throws NoSuchWallEntryException;

	/**
	 * Returns the wall entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param wallEntryId the primary key of the wall entry
	 * @return the wall entry, or <code>null</code> if a wall entry with the primary key could not be found
	 */
	public WallEntry fetchByPrimaryKey(long wallEntryId);

	/**
	 * Returns all the wall entries.
	 *
	 * @return the wall entries
	 */
	public java.util.List<WallEntry> findAll();

	/**
	 * Returns a range of all the wall entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WallEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @return the range of wall entries
	 */
	public java.util.List<WallEntry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the wall entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WallEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of wall entries
	 */
	public java.util.List<WallEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the wall entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WallEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of wall entries
	 */
	public java.util.List<WallEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WallEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the wall entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of wall entries.
	 *
	 * @return the number of wall entries
	 */
	public int countAll();

}