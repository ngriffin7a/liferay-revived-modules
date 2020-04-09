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

package com.liferay.powwow.web.portlet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.powwow.model.PowwowParticipant;
import com.liferay.powwow.service.PowwowParticipantLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shinn Lok
 */
@Component(service = PowwowPortletUtil.class)
public class PowwowPortletUtil {

	public List<PowwowParticipant> getPowwowParticipants(
			ActionRequest actionRequest)
		throws Exception {

		List<PowwowParticipant> powwowParticipants = new ArrayList<>();

		long powwowMeetingId = ParamUtil.getLong(
			actionRequest, "powwowMeetingId");

		String participantsJSON = ParamUtil.getString(
			actionRequest, "participantsJSON");

		JSONArray participantsJSONArray = JSONFactoryUtil.createJSONArray(
			participantsJSON);

		for (int i = 0; i < participantsJSONArray.length(); i++) {
			JSONObject participantJSONObject =
				participantsJSONArray.getJSONObject(i);

			String name = participantJSONObject.getString("name");
			String emailAddress = participantJSONObject.getString(
				"emailAddress");

			if (Validator.isNull(name) && Validator.isNull(emailAddress)) {
				continue;
			}

			long participantUserId = participantJSONObject.getLong(
				"participantUserId");
			int type = participantJSONObject.getInt("type");

			PowwowParticipant powwowParticipant = _getPowwowParticipant(
				powwowMeetingId, name, participantUserId, emailAddress, type);

			powwowParticipants.add(powwowParticipant);
		}

		return powwowParticipants;
	}

	private PowwowParticipant _getPowwowParticipant(
		long powwowMeetingId, String name, long participantUserId,
		String emailAddress, int type) {

		PowwowParticipant powwowParticipant = null;

		if (powwowMeetingId > 0) {
			if (participantUserId > 0) {
				powwowParticipant =
					PowwowParticipantLocalServiceUtil.fetchPowwowParticipant(
						powwowMeetingId, participantUserId);
			}
			else {
				powwowParticipant =
					PowwowParticipantLocalServiceUtil.fetchPowwowParticipant(
						powwowMeetingId, emailAddress);
			}
		}

		if (powwowParticipant == null) {
			powwowParticipant =
				PowwowParticipantLocalServiceUtil.createPowwowParticipant(0);
		}

		powwowParticipant.setName(name);
		powwowParticipant.setParticipantUserId(participantUserId);
		powwowParticipant.setEmailAddress(emailAddress);
		powwowParticipant.setType(type);

		return powwowParticipant;
	}

}