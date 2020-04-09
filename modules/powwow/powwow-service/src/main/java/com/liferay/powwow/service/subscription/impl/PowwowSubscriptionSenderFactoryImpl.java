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

package com.liferay.powwow.service.subscription.impl;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.powwow.invitation.PowwowInvitationURLFactory;
import com.liferay.powwow.model.PowwowMeeting;
import com.liferay.powwow.provider.PowwowServiceProvider;
import com.liferay.powwow.provider.PowwowServiceProviderRegistry;
import com.liferay.powwow.subscription.PowwowSubscriptionSender;
import com.liferay.powwow.subscription.PowwowSubscriptionSenderFactory;

import java.text.Format;

import java.util.TimeZone;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(service = PowwowSubscriptionSenderFactory.class)
public class PowwowSubscriptionSenderFactoryImpl
	implements PowwowSubscriptionSenderFactory {

	@Override
	public PowwowSubscriptionSender createPowwowSubscriptionSender(
			String portletId, PowwowMeeting powwowMeeting, String emailSubject,
			String emailBody, String layoutURL, ServiceContext serviceContext)
		throws PortalException {

		PowwowSubscriptionSender powwowSubscriptionSender =
			new PowwowSubscriptionSenderImpl();

		if (powwowMeeting.getCalendarBookingId() > 0) {
			try {
				String calendarBookingString =
					calendarBookingLocalService.exportCalendarBooking(
						powwowMeeting.getCalendarBookingId(),
						CalendarUtil.ICAL_EXTENSION);

				powwowSubscriptionSender.addFileAttachment(
					FileUtil.createTempFile(calendarBookingString.getBytes()),
					"invite.ics");
			}
			catch (Exception e) {
				throw new PortalException(e);
			}
		}

		powwowSubscriptionSender.setCompanyId(powwowMeeting.getCompanyId());

		String startDateString = StringPool.BLANK;
		String startTimeString = StringPool.BLANK;
		String timeZoneDisplayName = StringPool.BLANK;

		if (powwowMeeting.getCalendarBookingId() > 0) {
			CalendarBooking calendarBooking =
				calendarBookingLocalService.getCalendarBooking(
					powwowMeeting.getCalendarBookingId());

			User user = userLocalService.getUser(powwowMeeting.getUserId());

			Format format = FastDateFormatFactoryUtil.getSimpleDateFormat(
				"EEEE, dd MMMMM yyyy", user.getLocale(), user.getTimeZone());

			startDateString = format.format(calendarBooking.getStartTime());

			Format timeFormatDate = FastDateFormatFactoryUtil.getTime(
				user.getLocale(), user.getTimeZone());

			startTimeString = timeFormatDate.format(
				calendarBooking.getStartTime());

			TimeZone timeZone = user.getTimeZone();

			timeZoneDisplayName = timeZone.getDisplayName();
		}

		PowwowServiceProvider powwowServiceProvider =
			powwowServiceProviderRegistry.lookupByType(
				powwowMeeting.getProviderType());

		String invitationURL = powwowInvitationURLFactory.createInvitationURL(
			powwowMeeting.getPowwowMeetingId(), null, layoutURL);

		powwowSubscriptionSender.setContextAttributes(
			"[$MEETING_DATE$]", startDateString, "[$MEETING_DESCRIPTION$]",
			powwowMeeting.getDescription(),
			"[$MEETING_JOIN_BY_PHONE_ACCESS_CODE$]",
			powwowServiceProvider.getJoinByPhoneAccessCode(
				powwowMeeting.getPowwowMeetingId()),
			"[$MEETING_JOIN_BY_PHONE_ACCESS_CODE_LABEL$]",
			LanguageUtil.get(
				serviceContext.getLocale(),
				powwowServiceProvider.getJoinByPhoneAccessCodeLabel()),
			"[$MEETING_NAME$]", powwowMeeting.getName(), "[$MEETING_PASSWORD$]",
			powwowServiceProvider.getOptionPassword(
				powwowMeeting.getPowwowMeetingId()),
			"[$MEETING_TIME$]", startTimeString, "[$MEETING_TIME_ZONE$]",
			timeZoneDisplayName, "[$MEETING_URL$]", invitationURL);

		powwowSubscriptionSender.setContextCreatorUserPrefix("MEETING");

		String fromName = PrefsPropsUtil.getString(
			powwowMeeting.getCompanyId(), PropsKeys.ADMIN_EMAIL_FROM_NAME);
		String fromAddress = PrefsPropsUtil.getString(
			powwowMeeting.getCompanyId(), PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);

		powwowSubscriptionSender.setFrom(fromAddress, fromName);

		powwowSubscriptionSender.setHtmlFormat(true);
		powwowSubscriptionSender.setBody(emailBody);
		powwowSubscriptionSender.setSubject(emailSubject);
		powwowSubscriptionSender.setMailId(
			"powwowMeeting", powwowMeeting.getPowwowMeetingId());
		powwowSubscriptionSender.setPortletId(portletId);
		powwowSubscriptionSender.setReplyToAddress(fromAddress);
		powwowSubscriptionSender.setScopeGroupId(powwowMeeting.getGroupId());
		powwowSubscriptionSender.setServiceContext(serviceContext);
		powwowSubscriptionSender.setCurrentUserId(powwowMeeting.getUserId());

		return powwowSubscriptionSender;
	}

	@Reference
	protected CalendarBookingLocalService calendarBookingLocalService;

	@Reference
	protected PowwowInvitationURLFactory powwowInvitationURLFactory;

	@Reference
	protected PowwowServiceProviderRegistry powwowServiceProviderRegistry;

	@Reference
	protected UserLocalService userLocalService;

}