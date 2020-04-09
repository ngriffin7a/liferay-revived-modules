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

package com.liferay.powwow.invitation.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.powwow.invitation.PowwowInvitationURLFactory;
import com.liferay.powwow.model.PowwowParticipant;
import com.liferay.powwow.service.PowwowMeetingLocalServiceUtil;

import org.osgi.service.component.annotations.Component;

/**
 * @author Neil Griffin
 */
@Component(service = PowwowInvitationURLFactory.class)
public class PowwowInvitationURLFactoryImpl
	implements PowwowInvitationURLFactory {

	@Override
	public String createInvitationURL(
			long powwowMeetingId, PowwowParticipant powwowParticipant,
			String layoutURL)
		throws PortalException {

		StringBundler sb = new StringBundler(9);

		sb.append(layoutURL);
		sb.append(Portal.FRIENDLY_URL_SEPARATOR);
		sb.append("meetings");
		sb.append(StringPool.SLASH);
		sb.append(powwowMeetingId);
		sb.append(StringPool.SLASH);

		long powwowParticipantId = 0;

		if (powwowParticipant != null) {
			powwowParticipantId = powwowParticipant.getPowwowParticipantId();
		}

		sb.append(powwowParticipantId);

		sb.append(StringPool.SLASH);

		String hash = null;

		if (powwowMeetingId > 0) {
			try {
				hash = PowwowMeetingLocalServiceUtil.getHash(powwowMeetingId);
			}
			catch (Exception e) {
				throw new PortalException(e);
			}
		}

		sb.append(hash);

		return sb.toString();
	}

}