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

package com.liferay.powwow.web.config;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Neil Griffin
 */
@ExtendedObjectClassDefinition(category = "collaboration")
@Meta.OCD(
	id = "com.liferay.powwow.web.config.MeetingsConfiguration",
	localization = "content/Language"
)
public interface MeetingsConfiguration {

	@Meta.AD(
		deflt = "com/liferay/powwow/dependencies/meeting_scheduled_notification_body.tmpl",
		name = "invitation-email-body", required = false
	)
	public String invitationEmailBody();

	@Meta.AD(
		deflt = "com/liferay/powwow/dependencies/meeting_scheduled_notification_subject.tmpl",
		name = "invitation-email-subject", required = false
	)
	public String invitationEmailSubject();

	@Meta.AD(deflt = "", name = "invitation-group-name", required = false)
	public String invitationGroupName();

	@Meta.AD(
		deflt = "", name = "invitation-layout-friendly-url", required = false
	)
	public String invitationLayoutFriendlyURL();

	@Meta.AD(
		deflt = "false", name = "invitation-layout-private", required = false
	)
	public boolean invitationLayoutPrivate();

	@Meta.AD(deflt = "bbb|zoom", name = "provider-types", required = false)
	public String[] providerTypes();

	@Meta.AD(deflt = "1", name = "sync-interval", required = false)
	public int syncInterval();

}