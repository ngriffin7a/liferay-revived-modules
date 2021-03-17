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
 * Provides a wrapper for {@link WallEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see WallEntryLocalService
 * @generated
 */
public class WallEntryLocalServiceWrapper
	implements ServiceWrapper<WallEntryLocalService>, WallEntryLocalService {

	public WallEntryLocalServiceWrapper(
		WallEntryLocalService wallEntryLocalService) {

		_wallEntryLocalService = wallEntryLocalService;
	}

	@Override
	public com.liferay.social.networking.model.WallEntry addWallEntry(
			long groupId, long userId, String comments,
			com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _wallEntryLocalService.addWallEntry(
			groupId, userId, comments, themeDisplay);
	}

	/**
	 * Adds the wall entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WallEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wallEntry the wall entry
	 * @return the wall entry that was added
	 */
	@Override
	public com.liferay.social.networking.model.WallEntry addWallEntry(
		com.liferay.social.networking.model.WallEntry wallEntry) {

		return _wallEntryLocalService.addWallEntry(wallEntry);
	}

	/**
	 * Creates a new wall entry with the primary key. Does not add the wall entry to the database.
	 *
	 * @param wallEntryId the primary key for the new wall entry
	 * @return the new wall entry
	 */
	@Override
	public com.liferay.social.networking.model.WallEntry createWallEntry(
		long wallEntryId) {

		return _wallEntryLocalService.createWallEntry(wallEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _wallEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public void deleteWallEntries(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_wallEntryLocalService.deleteWallEntries(groupId);
	}

	/**
	 * Deletes the wall entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WallEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wallEntryId the primary key of the wall entry
	 * @return the wall entry that was removed
	 * @throws PortalException if a wall entry with the primary key could not be found
	 */
	@Override
	public com.liferay.social.networking.model.WallEntry deleteWallEntry(
			long wallEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _wallEntryLocalService.deleteWallEntry(wallEntryId);
	}

	/**
	 * Deletes the wall entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WallEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wallEntry the wall entry
	 * @return the wall entry that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.social.networking.model.WallEntry deleteWallEntry(
			com.liferay.social.networking.model.WallEntry wallEntry)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _wallEntryLocalService.deleteWallEntry(wallEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _wallEntryLocalService.dynamicQuery();
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

		return _wallEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.networking.model.impl.WallEntryModelImpl</code>.
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

		return _wallEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.networking.model.impl.WallEntryModelImpl</code>.
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

		return _wallEntryLocalService.dynamicQuery(
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

		return _wallEntryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _wallEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.social.networking.model.WallEntry fetchWallEntry(
		long wallEntryId) {

		return _wallEntryLocalService.fetchWallEntry(wallEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _wallEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _wallEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _wallEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _wallEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the wall entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.networking.model.impl.WallEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @return the range of wall entries
	 */
	@Override
	public java.util.List<com.liferay.social.networking.model.WallEntry>
		getWallEntries(int start, int end) {

		return _wallEntryLocalService.getWallEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.social.networking.model.WallEntry>
		getWallEntries(long groupId, int start, int end) {

		return _wallEntryLocalService.getWallEntries(groupId, start, end);
	}

	/**
	 * Returns the number of wall entries.
	 *
	 * @return the number of wall entries
	 */
	@Override
	public int getWallEntriesCount() {
		return _wallEntryLocalService.getWallEntriesCount();
	}

	@Override
	public int getWallEntriesCount(long groupId) {
		return _wallEntryLocalService.getWallEntriesCount(groupId);
	}

	/**
	 * Returns the wall entry with the primary key.
	 *
	 * @param wallEntryId the primary key of the wall entry
	 * @return the wall entry
	 * @throws PortalException if a wall entry with the primary key could not be found
	 */
	@Override
	public com.liferay.social.networking.model.WallEntry getWallEntry(
			long wallEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _wallEntryLocalService.getWallEntry(wallEntryId);
	}

	@Override
	public java.util.List<com.liferay.social.networking.model.WallEntry>
		getWallToWallEntries(
			long groupId1, long groupId2, long userId1, long userId2, int start,
			int end) {

		return _wallEntryLocalService.getWallToWallEntries(
			groupId1, groupId2, userId1, userId2, start, end);
	}

	@Override
	public int getWallToWallEntriesCount(
		long groupId1, long groupId2, long userId1, long userId2) {

		return _wallEntryLocalService.getWallToWallEntriesCount(
			groupId1, groupId2, userId1, userId2);
	}

	@Override
	public com.liferay.social.networking.model.WallEntry updateWallEntry(
			long wallEntryId, String comments)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _wallEntryLocalService.updateWallEntry(wallEntryId, comments);
	}

	/**
	 * Updates the wall entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WallEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wallEntry the wall entry
	 * @return the wall entry that was updated
	 */
	@Override
	public com.liferay.social.networking.model.WallEntry updateWallEntry(
		com.liferay.social.networking.model.WallEntry wallEntry) {

		return _wallEntryLocalService.updateWallEntry(wallEntry);
	}

	@Override
	public WallEntryLocalService getWrappedService() {
		return _wallEntryLocalService;
	}

	@Override
	public void setWrappedService(WallEntryLocalService wallEntryLocalService) {
		_wallEntryLocalService = wallEntryLocalService;
	}

	private WallEntryLocalService _wallEntryLocalService;

}