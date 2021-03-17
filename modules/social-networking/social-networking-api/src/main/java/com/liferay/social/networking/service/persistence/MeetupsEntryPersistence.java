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
import com.liferay.social.networking.exception.NoSuchMeetupsEntryException;
import com.liferay.social.networking.model.MeetupsEntry;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the meetups entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsEntryUtil
 * @generated
 */
@ProviderType
public interface MeetupsEntryPersistence extends BasePersistence<MeetupsEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MeetupsEntryUtil} to access the meetups entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the meetups entries where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching meetups entries
	 */
	public java.util.List<MeetupsEntry> findByCompanyId(long companyId);

	/**
	 * Returns a range of all the meetups entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of meetups entries
	 * @param end the upper bound of the range of meetups entries (not inclusive)
	 * @return the range of matching meetups entries
	 */
	public java.util.List<MeetupsEntry> findByCompanyId(
		long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the meetups entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of meetups entries
	 * @param end the upper bound of the range of meetups entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching meetups entries
	 */
	public java.util.List<MeetupsEntry> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the meetups entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of meetups entries
	 * @param end the upper bound of the range of meetups entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching meetups entries
	 */
	public java.util.List<MeetupsEntry> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first meetups entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching meetups entry
	 * @throws NoSuchMeetupsEntryException if a matching meetups entry could not be found
	 */
	public MeetupsEntry findByCompanyId_First(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<MeetupsEntry>
				orderByComparator)
		throws NoSuchMeetupsEntryException;

	/**
	 * Returns the first meetups entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching meetups entry, or <code>null</code> if a matching meetups entry could not be found
	 */
	public MeetupsEntry fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsEntry>
			orderByComparator);

	/**
	 * Returns the last meetups entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching meetups entry
	 * @throws NoSuchMeetupsEntryException if a matching meetups entry could not be found
	 */
	public MeetupsEntry findByCompanyId_Last(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<MeetupsEntry>
				orderByComparator)
		throws NoSuchMeetupsEntryException;

	/**
	 * Returns the last meetups entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching meetups entry, or <code>null</code> if a matching meetups entry could not be found
	 */
	public MeetupsEntry fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsEntry>
			orderByComparator);

	/**
	 * Returns the meetups entries before and after the current meetups entry in the ordered set where companyId = &#63;.
	 *
	 * @param meetupsEntryId the primary key of the current meetups entry
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next meetups entry
	 * @throws NoSuchMeetupsEntryException if a meetups entry with the primary key could not be found
	 */
	public MeetupsEntry[] findByCompanyId_PrevAndNext(
			long meetupsEntryId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<MeetupsEntry>
				orderByComparator)
		throws NoSuchMeetupsEntryException;

	/**
	 * Removes all the meetups entries where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public void removeByCompanyId(long companyId);

	/**
	 * Returns the number of meetups entries where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching meetups entries
	 */
	public int countByCompanyId(long companyId);

	/**
	 * Returns all the meetups entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching meetups entries
	 */
	public java.util.List<MeetupsEntry> findByUserId(long userId);

	/**
	 * Returns a range of all the meetups entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of meetups entries
	 * @param end the upper bound of the range of meetups entries (not inclusive)
	 * @return the range of matching meetups entries
	 */
	public java.util.List<MeetupsEntry> findByUserId(
		long userId, int start, int end);

	/**
	 * Returns an ordered range of all the meetups entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of meetups entries
	 * @param end the upper bound of the range of meetups entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching meetups entries
	 */
	public java.util.List<MeetupsEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the meetups entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of meetups entries
	 * @param end the upper bound of the range of meetups entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching meetups entries
	 */
	public java.util.List<MeetupsEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first meetups entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching meetups entry
	 * @throws NoSuchMeetupsEntryException if a matching meetups entry could not be found
	 */
	public MeetupsEntry findByUserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<MeetupsEntry>
				orderByComparator)
		throws NoSuchMeetupsEntryException;

	/**
	 * Returns the first meetups entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching meetups entry, or <code>null</code> if a matching meetups entry could not be found
	 */
	public MeetupsEntry fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsEntry>
			orderByComparator);

	/**
	 * Returns the last meetups entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching meetups entry
	 * @throws NoSuchMeetupsEntryException if a matching meetups entry could not be found
	 */
	public MeetupsEntry findByUserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<MeetupsEntry>
				orderByComparator)
		throws NoSuchMeetupsEntryException;

	/**
	 * Returns the last meetups entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching meetups entry, or <code>null</code> if a matching meetups entry could not be found
	 */
	public MeetupsEntry fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsEntry>
			orderByComparator);

	/**
	 * Returns the meetups entries before and after the current meetups entry in the ordered set where userId = &#63;.
	 *
	 * @param meetupsEntryId the primary key of the current meetups entry
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next meetups entry
	 * @throws NoSuchMeetupsEntryException if a meetups entry with the primary key could not be found
	 */
	public MeetupsEntry[] findByUserId_PrevAndNext(
			long meetupsEntryId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<MeetupsEntry>
				orderByComparator)
		throws NoSuchMeetupsEntryException;

	/**
	 * Removes all the meetups entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByUserId(long userId);

	/**
	 * Returns the number of meetups entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching meetups entries
	 */
	public int countByUserId(long userId);

	/**
	 * Caches the meetups entry in the entity cache if it is enabled.
	 *
	 * @param meetupsEntry the meetups entry
	 */
	public void cacheResult(MeetupsEntry meetupsEntry);

	/**
	 * Caches the meetups entries in the entity cache if it is enabled.
	 *
	 * @param meetupsEntries the meetups entries
	 */
	public void cacheResult(java.util.List<MeetupsEntry> meetupsEntries);

	/**
	 * Creates a new meetups entry with the primary key. Does not add the meetups entry to the database.
	 *
	 * @param meetupsEntryId the primary key for the new meetups entry
	 * @return the new meetups entry
	 */
	public MeetupsEntry create(long meetupsEntryId);

	/**
	 * Removes the meetups entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param meetupsEntryId the primary key of the meetups entry
	 * @return the meetups entry that was removed
	 * @throws NoSuchMeetupsEntryException if a meetups entry with the primary key could not be found
	 */
	public MeetupsEntry remove(long meetupsEntryId)
		throws NoSuchMeetupsEntryException;

	public MeetupsEntry updateImpl(MeetupsEntry meetupsEntry);

	/**
	 * Returns the meetups entry with the primary key or throws a <code>NoSuchMeetupsEntryException</code> if it could not be found.
	 *
	 * @param meetupsEntryId the primary key of the meetups entry
	 * @return the meetups entry
	 * @throws NoSuchMeetupsEntryException if a meetups entry with the primary key could not be found
	 */
	public MeetupsEntry findByPrimaryKey(long meetupsEntryId)
		throws NoSuchMeetupsEntryException;

	/**
	 * Returns the meetups entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param meetupsEntryId the primary key of the meetups entry
	 * @return the meetups entry, or <code>null</code> if a meetups entry with the primary key could not be found
	 */
	public MeetupsEntry fetchByPrimaryKey(long meetupsEntryId);

	/**
	 * Returns all the meetups entries.
	 *
	 * @return the meetups entries
	 */
	public java.util.List<MeetupsEntry> findAll();

	/**
	 * Returns a range of all the meetups entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetups entries
	 * @param end the upper bound of the range of meetups entries (not inclusive)
	 * @return the range of meetups entries
	 */
	public java.util.List<MeetupsEntry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the meetups entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetups entries
	 * @param end the upper bound of the range of meetups entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of meetups entries
	 */
	public java.util.List<MeetupsEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the meetups entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetups entries
	 * @param end the upper bound of the range of meetups entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of meetups entries
	 */
	public java.util.List<MeetupsEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the meetups entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of meetups entries.
	 *
	 * @return the number of meetups entries
	 */
	public int countAll();

}