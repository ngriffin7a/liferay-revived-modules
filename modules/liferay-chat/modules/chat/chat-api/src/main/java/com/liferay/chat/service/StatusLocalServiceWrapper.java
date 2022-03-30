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

package com.liferay.chat.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link StatusLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see StatusLocalService
 * @generated
 */
@ProviderType
public class StatusLocalServiceWrapper
	implements StatusLocalService, ServiceWrapper<StatusLocalService> {

	public StatusLocalServiceWrapper(StatusLocalService statusLocalService) {
		_statusLocalService = statusLocalService;
	}

	/**
	 * Adds the status to the database. Also notifies the appropriate model listeners.
	 *
	 * @param status the status
	 * @return the status that was added
	 */
	@Override
	public com.liferay.chat.model.Status addStatus(
		com.liferay.chat.model.Status status) {

		return _statusLocalService.addStatus(status);
	}

	/**
	 * Creates a new status with the primary key. Does not add the status to the database.
	 *
	 * @param statusId the primary key for the new status
	 * @return the new status
	 */
	@Override
	public com.liferay.chat.model.Status createStatus(long statusId) {
		return _statusLocalService.createStatus(statusId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _statusLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param statusId the primary key of the status
	 * @return the status that was removed
	 * @throws PortalException if a status with the primary key could not be found
	 */
	@Override
	public com.liferay.chat.model.Status deleteStatus(long statusId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _statusLocalService.deleteStatus(statusId);
	}

	/**
	 * Deletes the status from the database. Also notifies the appropriate model listeners.
	 *
	 * @param status the status
	 * @return the status that was removed
	 */
	@Override
	public com.liferay.chat.model.Status deleteStatus(
		com.liferay.chat.model.Status status) {

		return _statusLocalService.deleteStatus(status);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _statusLocalService.dynamicQuery();
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

		return _statusLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.chat.model.impl.StatusModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _statusLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.chat.model.impl.StatusModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _statusLocalService.dynamicQuery(
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

		return _statusLocalService.dynamicQueryCount(dynamicQuery);
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

		return _statusLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.chat.model.Status fetchStatus(long statusId) {
		return _statusLocalService.fetchStatus(statusId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _statusLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<Object[]> getAllStatuses(
		long companyId, long userId, long modifiedDate, int start, int end) {

		return _statusLocalService.getAllStatuses(
			companyId, userId, modifiedDate, start, end);
	}

	@Override
	public java.util.List<Object[]> getGroupStatuses(
		long userId, long modifiedDate, String[] groupNames, int start,
		int end) {

		return _statusLocalService.getGroupStatuses(
			userId, modifiedDate, groupNames, start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _statusLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _statusLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _statusLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<Object[]> getSocialStatuses(
		long userId, int type, long modifiedDate, int start, int end) {

		return _statusLocalService.getSocialStatuses(
			userId, type, modifiedDate, start, end);
	}

	@Override
	public java.util.List<Object[]> getSocialStatuses(
		long userId, int[] types, long modifiedDate, int start, int end) {

		return _statusLocalService.getSocialStatuses(
			userId, types, modifiedDate, start, end);
	}

	/**
	 * Returns the status with the primary key.
	 *
	 * @param statusId the primary key of the status
	 * @return the status
	 * @throws PortalException if a status with the primary key could not be found
	 */
	@Override
	public com.liferay.chat.model.Status getStatus(long statusId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _statusLocalService.getStatus(statusId);
	}

	/**
	 * Returns a range of all the statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.chat.model.impl.StatusModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @return the range of statuses
	 */
	@Override
	public java.util.List<com.liferay.chat.model.Status> getStatuses(
		int start, int end) {

		return _statusLocalService.getStatuses(start, end);
	}

	/**
	 * Returns the number of statuses.
	 *
	 * @return the number of statuses
	 */
	@Override
	public int getStatusesCount() {
		return _statusLocalService.getStatusesCount();
	}

	@Override
	public com.liferay.chat.model.Status getUserStatus(long userId) {
		return _statusLocalService.getUserStatus(userId);
	}

	@Override
	public com.liferay.chat.model.Status updateStatus(
		long userId, long modifiedDate) {

		return _statusLocalService.updateStatus(userId, modifiedDate);
	}

	@Override
	public com.liferay.chat.model.Status updateStatus(
		long userId, long modifiedDate, int online, int awake,
		String activePanelIds, String message, int playSound) {

		return _statusLocalService.updateStatus(
			userId, modifiedDate, online, awake, activePanelIds, message,
			playSound);
	}

	/**
	 * Updates the status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param status the status
	 * @return the status that was updated
	 */
	@Override
	public com.liferay.chat.model.Status updateStatus(
		com.liferay.chat.model.Status status) {

		return _statusLocalService.updateStatus(status);
	}

	@Override
	public StatusLocalService getWrappedService() {
		return _statusLocalService;
	}

	@Override
	public void setWrappedService(StatusLocalService statusLocalService) {
		_statusLocalService = statusLocalService;
	}

	private StatusLocalService _statusLocalService;

}