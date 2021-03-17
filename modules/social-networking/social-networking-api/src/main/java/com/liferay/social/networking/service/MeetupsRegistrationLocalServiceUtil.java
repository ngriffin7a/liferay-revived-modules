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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for MeetupsRegistration. This utility wraps
 * <code>com.liferay.social.networking.service.impl.MeetupsRegistrationLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsRegistrationLocalService
 * @generated
 */
public class MeetupsRegistrationLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.social.networking.service.impl.MeetupsRegistrationLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static com.liferay.social.networking.model.MeetupsRegistration
		addMeetupsRegistration(
			com.liferay.social.networking.model.MeetupsRegistration
				meetupsRegistration) {

		return getService().addMeetupsRegistration(meetupsRegistration);
	}

	/**
	 * Creates a new meetups registration with the primary key. Does not add the meetups registration to the database.
	 *
	 * @param meetupsRegistrationId the primary key for the new meetups registration
	 * @return the new meetups registration
	 */
	public static com.liferay.social.networking.model.MeetupsRegistration
		createMeetupsRegistration(long meetupsRegistrationId) {

		return getService().createMeetupsRegistration(meetupsRegistrationId);
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
	public static com.liferay.social.networking.model.MeetupsRegistration
			deleteMeetupsRegistration(long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteMeetupsRegistration(meetupsRegistrationId);
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
	public static com.liferay.social.networking.model.MeetupsRegistration
		deleteMeetupsRegistration(
			com.liferay.social.networking.model.MeetupsRegistration
				meetupsRegistration) {

		return getService().deleteMeetupsRegistration(meetupsRegistration);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.social.networking.model.MeetupsRegistration
		fetchMeetupsRegistration(long meetupsRegistrationId) {

		return getService().fetchMeetupsRegistration(meetupsRegistrationId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the meetups registration with the primary key.
	 *
	 * @param meetupsRegistrationId the primary key of the meetups registration
	 * @return the meetups registration
	 * @throws PortalException if a meetups registration with the primary key could not be found
	 */
	public static com.liferay.social.networking.model.MeetupsRegistration
			getMeetupsRegistration(long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getMeetupsRegistration(meetupsRegistrationId);
	}

	public static com.liferay.social.networking.model.MeetupsRegistration
			getMeetupsRegistration(long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getMeetupsRegistration(userId, meetupsEntryId);
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
	public static java.util.List
		<com.liferay.social.networking.model.MeetupsRegistration>
			getMeetupsRegistrations(int start, int end) {

		return getService().getMeetupsRegistrations(start, end);
	}

	public static java.util.List
		<com.liferay.social.networking.model.MeetupsRegistration>
			getMeetupsRegistrations(
				long meetupsEntryId, int status, int start, int end) {

		return getService().getMeetupsRegistrations(
			meetupsEntryId, status, start, end);
	}

	/**
	 * Returns the number of meetups registrations.
	 *
	 * @return the number of meetups registrations
	 */
	public static int getMeetupsRegistrationsCount() {
		return getService().getMeetupsRegistrationsCount();
	}

	public static int getMeetupsRegistrationsCount(
		long meetupsEntryId, int status) {

		return getService().getMeetupsRegistrationsCount(
			meetupsEntryId, status);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static com.liferay.social.networking.model.MeetupsRegistration
			updateMeetupsRegistration(
				long userId, long meetupsEntryId, int status, String comments)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateMeetupsRegistration(
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
	public static com.liferay.social.networking.model.MeetupsRegistration
		updateMeetupsRegistration(
			com.liferay.social.networking.model.MeetupsRegistration
				meetupsRegistration) {

		return getService().updateMeetupsRegistration(meetupsRegistration);
	}

	public static MeetupsRegistrationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<MeetupsRegistrationLocalService, MeetupsRegistrationLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			MeetupsRegistrationLocalService.class);

		ServiceTracker
			<MeetupsRegistrationLocalService, MeetupsRegistrationLocalService>
				serviceTracker =
					new ServiceTracker
						<MeetupsRegistrationLocalService,
						 MeetupsRegistrationLocalService>(
							 bundle.getBundleContext(),
							 MeetupsRegistrationLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}