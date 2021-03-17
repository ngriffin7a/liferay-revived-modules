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

package com.liferay.social.networking.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MeetupsEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsEntryLocalService
 * @generated
 */
public class MeetupsEntryLocalServiceWrapper
	implements MeetupsEntryLocalService,
			   ServiceWrapper<MeetupsEntryLocalService> {

	public MeetupsEntryLocalServiceWrapper(
		MeetupsEntryLocalService meetupsEntryLocalService) {

		_meetupsEntryLocalService = meetupsEntryLocalService;
	}

	@Override
	public com.liferay.social.networking.model.MeetupsEntry addMeetupsEntry(
			long userId, String title, String description, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute,
			int totalAttendees, int maxAttendees, double price,
			byte[] thumbnail)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _meetupsEntryLocalService.addMeetupsEntry(
			userId, title, description, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, totalAttendees,
			maxAttendees, price, thumbnail);
	}

	/**
	 * Adds the meetups entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MeetupsEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param meetupsEntry the meetups entry
	 * @return the meetups entry that was added
	 */
	@Override
	public com.liferay.social.networking.model.MeetupsEntry addMeetupsEntry(
		com.liferay.social.networking.model.MeetupsEntry meetupsEntry) {

		return _meetupsEntryLocalService.addMeetupsEntry(meetupsEntry);
	}

	/**
	 * Creates a new meetups entry with the primary key. Does not add the meetups entry to the database.
	 *
	 * @param meetupsEntryId the primary key for the new meetups entry
	 * @return the new meetups entry
	 */
	@Override
	public com.liferay.social.networking.model.MeetupsEntry createMeetupsEntry(
		long meetupsEntryId) {

		return _meetupsEntryLocalService.createMeetupsEntry(meetupsEntryId);
	}

	/**
	 * Deletes the meetups entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MeetupsEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param meetupsEntryId the primary key of the meetups entry
	 * @return the meetups entry that was removed
	 * @throws PortalException if a meetups entry with the primary key could not be found
	 */
	@Override
	public com.liferay.social.networking.model.MeetupsEntry deleteMeetupsEntry(
			long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _meetupsEntryLocalService.deleteMeetupsEntry(meetupsEntryId);
	}

	/**
	 * Deletes the meetups entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MeetupsEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param meetupsEntry the meetups entry
	 * @return the meetups entry that was removed
	 */
	@Override
	public com.liferay.social.networking.model.MeetupsEntry deleteMeetupsEntry(
		com.liferay.social.networking.model.MeetupsEntry meetupsEntry) {

		return _meetupsEntryLocalService.deleteMeetupsEntry(meetupsEntry);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _meetupsEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _meetupsEntryLocalService.dynamicQuery();
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

		return _meetupsEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.networking.model.impl.MeetupsEntryModelImpl</code>.
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

		return _meetupsEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.networking.model.impl.MeetupsEntryModelImpl</code>.
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

		return _meetupsEntryLocalService.dynamicQuery(
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

		return _meetupsEntryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _meetupsEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.social.networking.model.MeetupsEntry fetchMeetupsEntry(
		long meetupsEntryId) {

		return _meetupsEntryLocalService.fetchMeetupsEntry(meetupsEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _meetupsEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _meetupsEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the meetups entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.networking.model.impl.MeetupsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetups entries
	 * @param end the upper bound of the range of meetups entries (not inclusive)
	 * @return the range of meetups entries
	 */
	@Override
	public java.util.List<com.liferay.social.networking.model.MeetupsEntry>
		getMeetupsEntries(int start, int end) {

		return _meetupsEntryLocalService.getMeetupsEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.social.networking.model.MeetupsEntry>
		getMeetupsEntriesByCompany(long companyId) {

		return _meetupsEntryLocalService.getMeetupsEntriesByCompany(companyId);
	}

	@Override
	public java.util.List<com.liferay.social.networking.model.MeetupsEntry>
		getMeetupsEntriesByUser(long userId) {

		return _meetupsEntryLocalService.getMeetupsEntriesByUser(userId);
	}

	/**
	 * Returns the number of meetups entries.
	 *
	 * @return the number of meetups entries
	 */
	@Override
	public int getMeetupsEntriesCount() {
		return _meetupsEntryLocalService.getMeetupsEntriesCount();
	}

	/**
	 * Returns the meetups entry with the primary key.
	 *
	 * @param meetupsEntryId the primary key of the meetups entry
	 * @return the meetups entry
	 * @throws PortalException if a meetups entry with the primary key could not be found
	 */
	@Override
	public com.liferay.social.networking.model.MeetupsEntry getMeetupsEntry(
			long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _meetupsEntryLocalService.getMeetupsEntry(meetupsEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _meetupsEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _meetupsEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.social.networking.model.MeetupsEntry updateMeetupsEntry(
			long userId, long meetupsEntryId, String title, String description,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int endDateMonth,
			int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
			int totalAttendees, int maxAttendees, double price,
			byte[] thumbnail)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _meetupsEntryLocalService.updateMeetupsEntry(
			userId, meetupsEntryId, title, description, startDateMonth,
			startDateDay, startDateYear, startDateHour, startDateMinute,
			endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
			totalAttendees, maxAttendees, price, thumbnail);
	}

	/**
	 * Updates the meetups entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MeetupsEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param meetupsEntry the meetups entry
	 * @return the meetups entry that was updated
	 */
	@Override
	public com.liferay.social.networking.model.MeetupsEntry updateMeetupsEntry(
		com.liferay.social.networking.model.MeetupsEntry meetupsEntry) {

		return _meetupsEntryLocalService.updateMeetupsEntry(meetupsEntry);
	}

	@Override
	public MeetupsEntryLocalService getWrappedService() {
		return _meetupsEntryLocalService;
	}

	@Override
	public void setWrappedService(
		MeetupsEntryLocalService meetupsEntryLocalService) {

		_meetupsEntryLocalService = meetupsEntryLocalService;
	}

	private MeetupsEntryLocalService _meetupsEntryLocalService;

}