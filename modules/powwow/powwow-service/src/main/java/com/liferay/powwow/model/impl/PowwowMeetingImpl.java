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

package com.liferay.powwow.model.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;

import java.io.Serializable;

import java.util.Map;

/**
 * The extended model implementation for the PowwowMeeting service. Represents a row in the &quot;PowwowMeeting&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.powwow.model.PowwowMeeting</code> interface.
 * </p>
 *
 * @author Shinn Lok
 */
public class PowwowMeetingImpl extends PowwowMeetingBaseImpl {

	public PowwowMeetingImpl() {
	}

	@Override
	public Map<String, Serializable> getProviderTypeMetadataMap() {
		if (_providerTypeMetadataMap != null) {
			return _providerTypeMetadataMap;
		}

		_providerTypeMetadataMap =
			(Map<String, Serializable>)JSONFactoryUtil.deserialize(
				getProviderTypeMetadata());

		return _providerTypeMetadataMap;
	}

	private Map<String, Serializable> _providerTypeMetadataMap;

}