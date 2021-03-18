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
import com.liferay.social.networking.model.MeetupsRegistration;

import com.liferay.social.networking.service.base.MeetupsRegistrationLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * The implementation of the meetups registration local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.social.networking.service.MeetupsRegistrationLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsRegistrationLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.social.networking.model.MeetupsRegistration",
	service = AopService.class
)
public class MeetupsRegistrationLocalServiceImpl
	extends MeetupsRegistrationLocalServiceBaseImpl {

	@Override
	public MeetupsRegistration getMeetupsRegistration(
			long userId, long meetupsEntryId)
		throws PortalException {

		return meetupsRegistrationPersistence.findByU_ME(
			userId, meetupsEntryId);
	}

	@Override
	public List<MeetupsRegistration> getMeetupsRegistrations(
		long meetupsEntryId, int status, int start, int end) {

		return meetupsRegistrationPersistence.findByME_S(
			meetupsEntryId, status, start, end);
	}

	@Override
	public int getMeetupsRegistrationsCount(long meetupsEntryId, int status) {
		return meetupsRegistrationPersistence.countByME_S(
			meetupsEntryId, status);
	}

	@Override
	public MeetupsRegistration updateMeetupsRegistration(
			long userId, long meetupsEntryId, int status, String comments)
		throws PortalException {

		User user = userLocalService.getUserById(userId);

		MeetupsRegistration meetupsRegistration =
			meetupsRegistrationPersistence.fetchByU_ME(userId, meetupsEntryId);

		if (meetupsRegistration == null) {
			long meetupsRegistrationId = counterLocalService.increment();

			meetupsRegistration = meetupsRegistrationPersistence.create(
				meetupsRegistrationId);

			meetupsRegistration.setCompanyId(user.getCompanyId());
			meetupsRegistration.setUserId(user.getUserId());
			meetupsRegistration.setUserName(user.getFullName());
			meetupsRegistration.setMeetupsEntryId(meetupsEntryId);
		}

		meetupsRegistration.setComments(comments);
		meetupsRegistration.setStatus(status);

		meetupsRegistrationPersistence.update(meetupsRegistration);

		return meetupsRegistration;
	}

}