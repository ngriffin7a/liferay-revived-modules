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

import com.liferay.powwow.model.PowwowMeetingConstants;
import com.liferay.powwow.model.PowwowServer;
import com.liferay.powwow.provider.PowwowServiceProvider;
import com.liferay.powwow.service.PowwowMeetingLocalServiceUtil;
import com.liferay.powwow.service.PowwowServerLocalServiceUtil;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.osgi.service.component.annotations.Component;

/**
 * @author Neil Griffin
 */
@Component(service = PowwowServerUtil.class)
public class PowwowServerUtil {

	public long getPowwowServerId(PowwowServiceProvider powwowServiceProvider) {
		PowwowServerLocalServiceUtil.checkPowwowServers(powwowServiceProvider);

		List<PowwowServer> powwowServers =
			PowwowServerLocalServiceUtil.getPowwowServers(
				powwowServiceProvider.getPowwowServiceProviderKey(), true);

		if (powwowServers.isEmpty()) {
			return PowwowMeetingConstants.POWWOW_SERVER_ID_DEFAULT;
		}

		TreeMap<Integer, Long> powwowServerIds = new TreeMap<>();

		for (PowwowServer powwowServer : powwowServers) {
			int count = PowwowMeetingLocalServiceUtil.getPowwowMeetingsCount(
				powwowServer.getPowwowServerId(),
				PowwowMeetingConstants.STATUS_IN_PROGRESS);

			powwowServerIds.put(count, powwowServer.getPowwowServerId());
		}

		Map.Entry<Integer, Long> entry = powwowServerIds.firstEntry();

		return entry.getValue();
	}

}