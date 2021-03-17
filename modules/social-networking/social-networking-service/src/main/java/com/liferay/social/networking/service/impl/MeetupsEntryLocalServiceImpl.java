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

package com.liferay.social.networking.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.social.networking.exception.MeetupsEntryEndDateException;
import com.liferay.social.networking.exception.MeetupsEntryStartDateException;
import com.liferay.social.networking.model.MeetupsEntry;
import com.liferay.social.networking.service.base.MeetupsEntryLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the meetups entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.social.networking.service.MeetupsEntryLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsEntryLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.social.networking.model.MeetupsEntry",
	service = AopService.class
)
public class MeetupsEntryLocalServiceImpl
	extends MeetupsEntryLocalServiceBaseImpl {

	@Override
	public MeetupsEntry addMeetupsEntry(
			long userId, String title, String description, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute,
			int totalAttendees, int maxAttendees, double price,
			byte[] thumbnail)
		throws PortalException {

		User user = userLocalService.getUserById(userId);

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, user.getTimeZone(),
			MeetupsEntryStartDateException.class);

		Date endDate = PortalUtil.getDate(
			endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
			user.getTimeZone(), MeetupsEntryEndDateException.class);

		long meetupsEntryId = counterLocalService.increment();

		MeetupsEntry meetupsEntry = meetupsEntryPersistence.create(
			meetupsEntryId);

		meetupsEntry.setCompanyId(user.getCompanyId());
		meetupsEntry.setUserId(user.getUserId());
		meetupsEntry.setUserName(user.getFullName());
		meetupsEntry.setTitle(title);
		meetupsEntry.setDescription(description);
		meetupsEntry.setStartDate(startDate);
		meetupsEntry.setEndDate(endDate);
		meetupsEntry.setTotalAttendees(totalAttendees);
		meetupsEntry.setMaxAttendees(maxAttendees);
		meetupsEntry.setPrice(price);

		if (ArrayUtil.isNotEmpty(thumbnail)) {
			meetupsEntry.setThumbnailId(counterLocalService.increment());
		}

		meetupsEntryPersistence.update(meetupsEntry);

		if (ArrayUtil.isNotEmpty(thumbnail)) {
			imageLocalService.updateImage(
				meetupsEntry.getThumbnailId(), thumbnail);
		}

		return meetupsEntry;
	}

	@Override
	public MeetupsEntry deleteMeetupsEntry(long meetupsEntryId)
		throws PortalException {

		MeetupsEntry meetupsEntry = meetupsEntryPersistence.findByPrimaryKey(
			meetupsEntryId);

		meetupsEntryPersistence.remove(meetupsEntry);

		meetupsRegistrationPersistence.removeByMeetupsEntryId(meetupsEntryId);

		imageLocalService.deleteImage(meetupsEntry.getThumbnailId());

		return meetupsEntry;
	}

	@Override
	public List<MeetupsEntry> getMeetupsEntriesByCompany(long companyId) {
		return meetupsEntryPersistence.findByCompanyId(companyId);
	}

	@Override
	public List<MeetupsEntry> getMeetupsEntriesByUser(long userId) {
		return meetupsEntryPersistence.findByUserId(userId);
	}

	@Override
	public MeetupsEntry updateMeetupsEntry(
			long userId, long meetupsEntryId, String title, String description,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int endDateMonth,
			int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
			int totalAttendees, int maxAttendees, double price,
			byte[] thumbnail)
		throws PortalException {

		User user = userLocalService.getUserById(userId);

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, user.getTimeZone(),
			MeetupsEntryStartDateException.class);

		Date endDate = PortalUtil.getDate(
			endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
			user.getTimeZone(), MeetupsEntryEndDateException.class);

		MeetupsEntry meetupsEntry = meetupsEntryPersistence.findByPrimaryKey(
			meetupsEntryId);

		meetupsEntry.setTitle(title);
		meetupsEntry.setDescription(description);
		meetupsEntry.setStartDate(startDate);
		meetupsEntry.setEndDate(endDate);
		meetupsEntry.setTotalAttendees(totalAttendees);
		meetupsEntry.setMaxAttendees(maxAttendees);
		meetupsEntry.setPrice(price);

		if ((thumbnail != null) && (thumbnail.length > 0) &&
			(meetupsEntry.getThumbnailId() == 0)) {

			meetupsEntry.setThumbnailId(counterLocalService.increment());
		}

		meetupsEntryPersistence.update(meetupsEntry);

		if (ArrayUtil.isNotEmpty(thumbnail)) {
			imageLocalService.updateImage(
				meetupsEntry.getThumbnailId(), thumbnail);
		}

		return meetupsEntry;
	}

}