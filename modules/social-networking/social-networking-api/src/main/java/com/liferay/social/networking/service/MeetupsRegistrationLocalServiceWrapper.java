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
 * Provides a wrapper for {@link MeetupsRegistrationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsRegistrationLocalService
 * @generated
 */
public class MeetupsRegistrationLocalServiceWrapper
	implements MeetupsRegistrationLocalService,
			   ServiceWrapper<MeetupsRegistrationLocalService> {

	public MeetupsRegistrationLocalServiceWrapper(
		MeetupsRegistrationLocalService meetupsRegistrationLocalService) {

		_meetupsRegistrationLocalService = meetupsRegistrationLocalService;
	}

	/**
	 * Adds the meetups registration to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MeetupsRegistrationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param meetupsRegistration the meetups registration
	 * @return the meetups registration that was added
	 */
	@Override
	public com.liferay.social.networking.model.MeetupsRegistration
		addMeetupsRegistration(
			com.liferay.social.networking.model.MeetupsRegistration
				meetupsRegistration) {

		return _meetupsRegistrationLocalService.addMeetupsRegistration(
			meetupsRegistration);
	}

	/**
	 * Creates a new meetups registration with the primary key. Does not add the meetups registration to the database.
	 *
	 * @param meetupsRegistrationId the primary key for the new meetups registration
	 * @return the new meetups registration
	 */
	@Override
	public com.liferay.social.networking.model.MeetupsRegistration
		createMeetupsRegistration(long meetupsRegistrationId) {

		return _meetupsRegistrationLocalService.createMeetupsRegistration(
			meetupsRegistrationId);
	}

	/**
	 * Deletes the meetups registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MeetupsRegistrationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param meetupsRegistrationId the primary key of the meetups registration
	 * @return the meetups registration that was removed
	 * @throws PortalException if a meetups registration with the primary key could not be found
	 */
	@Override
	public com.liferay.social.networking.model.MeetupsRegistration
			deleteMeetupsRegistration(long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _meetupsRegistrationLocalService.deleteMeetupsRegistration(
			meetupsRegistrationId);
	}

	/**
	 * Deletes the meetups registration from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MeetupsRegistrationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param meetupsRegistration the meetups registration
	 * @return the meetups registration that was removed
	 */
	@Override
	public com.liferay.social.networking.model.MeetupsRegistration
		deleteMeetupsRegistration(
			com.liferay.social.networking.model.MeetupsRegistration
				meetupsRegistration) {

		return _meetupsRegistrationLocalService.deleteMeetupsRegistration(
			meetupsRegistration);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _meetupsRegistrationLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _meetupsRegistrationLocalService.dynamicQuery();
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

		return _meetupsRegistrationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.networking.model.impl.MeetupsRegistrationModelImpl</code>.
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

		return _meetupsRegistrationLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.networking.model.impl.MeetupsRegistrationModelImpl</code>.
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

		return _meetupsRegistrationLocalService.dynamicQuery(
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

		return _meetupsRegistrationLocalService.dynamicQueryCount(dynamicQuery);
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

		return _meetupsRegistrationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.social.networking.model.MeetupsRegistration
		fetchMeetupsRegistration(long meetupsRegistrationId) {

		return _meetupsRegistrationLocalService.fetchMeetupsRegistration(
			meetupsRegistrationId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _meetupsRegistrationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _meetupsRegistrationLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the meetups registration with the primary key.
	 *
	 * @param meetupsRegistrationId the primary key of the meetups registration
	 * @return the meetups registration
	 * @throws PortalException if a meetups registration with the primary key could not be found
	 */
	@Override
	public com.liferay.social.networking.model.MeetupsRegistration
			getMeetupsRegistration(long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _meetupsRegistrationLocalService.getMeetupsRegistration(
			meetupsRegistrationId);
	}

	@Override
	public com.liferay.social.networking.model.MeetupsRegistration
			getMeetupsRegistration(long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _meetupsRegistrationLocalService.getMeetupsRegistration(
			userId, meetupsEntryId);
	}

	/**
	 * Returns a range of all the meetups registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.networking.model.impl.MeetupsRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetups registrations
	 * @param end the upper bound of the range of meetups registrations (not inclusive)
	 * @return the range of meetups registrations
	 */
	@Override
	public java.util.List
		<com.liferay.social.networking.model.MeetupsRegistration>
			getMeetupsRegistrations(int start, int end) {

		return _meetupsRegistrationLocalService.getMeetupsRegistrations(
			start, end);
	}

	@Override
	public java.util.List
		<com.liferay.social.networking.model.MeetupsRegistration>
			getMeetupsRegistrations(
				long meetupsEntryId, int status, int start, int end) {

		return _meetupsRegistrationLocalService.getMeetupsRegistrations(
			meetupsEntryId, status, start, end);
	}

	/**
	 * Returns the number of meetups registrations.
	 *
	 * @return the number of meetups registrations
	 */
	@Override
	public int getMeetupsRegistrationsCount() {
		return _meetupsRegistrationLocalService.getMeetupsRegistrationsCount();
	}

	@Override
	public int getMeetupsRegistrationsCount(long meetupsEntryId, int status) {
		return _meetupsRegistrationLocalService.getMeetupsRegistrationsCount(
			meetupsEntryId, status);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _meetupsRegistrationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _meetupsRegistrationLocalService.getPersistedModel(
			primaryKeyObj);
	}

	@Override
	public com.liferay.social.networking.model.MeetupsRegistration
			updateMeetupsRegistration(
				long userId, long meetupsEntryId, int status, String comments)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _meetupsRegistrationLocalService.updateMeetupsRegistration(
			userId, meetupsEntryId, status, comments);
	}

	/**
	 * Updates the meetups registration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MeetupsRegistrationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param meetupsRegistration the meetups registration
	 * @return the meetups registration that was updated
	 */
	@Override
	public com.liferay.social.networking.model.MeetupsRegistration
		updateMeetupsRegistration(
			com.liferay.social.networking.model.MeetupsRegistration
				meetupsRegistration) {

		return _meetupsRegistrationLocalService.updateMeetupsRegistration(
			meetupsRegistration);
	}

	@Override
	public MeetupsRegistrationLocalService getWrappedService() {
		return _meetupsRegistrationLocalService;
	}

	@Override
	public void setWrappedService(
		MeetupsRegistrationLocalService meetupsRegistrationLocalService) {

		_meetupsRegistrationLocalService = meetupsRegistrationLocalService;
	}

	private MeetupsRegistrationLocalService _meetupsRegistrationLocalService;

}