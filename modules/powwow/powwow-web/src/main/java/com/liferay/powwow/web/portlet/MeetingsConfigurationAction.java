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

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.powwow.web.config.MeetingsConfiguration;
import com.liferay.powwow.web.constants.PowwowKeys;

import java.util.Map;

import javax.portlet.PortletConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Neil Griffin
 */
@Component(
	configurationPid = "com.liferay.powwow.web.config.MeetingsConfiguration",
	immediate = true,
	property = "javax.portlet.name=" + PowwowKeys.POWWOW_MEETINGS,
	service = ConfigurationAction.class
)
public class MeetingsConfigurationAction extends DefaultConfigurationAction {

	@Activate
	public void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(
			MeetingsConfiguration.class, properties);
	}

	@Override
	public void include(
			PortletConfig portletConfig, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		httpServletRequest.setAttribute("configuration", _configuration);

		super.include(portletConfig, httpServletRequest, httpServletResponse);
	}

	private volatile MeetingsConfiguration _configuration;

}