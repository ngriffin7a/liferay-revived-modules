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
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.powwow.model.PowwowServer;
import com.liferay.powwow.provider.PowwowServiceProviderRegistry;
import com.liferay.powwow.service.PowwowServerLocalServiceUtil;
import com.liferay.powwow.web.config.MeetingsConfiguration;
import com.liferay.powwow.web.constants.PowwowKeys;

import java.io.IOException;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(
	configurationPid = "com.liferay.powwow.web.config.MeetingsConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.icon=/icons/icon.png",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.control-panel-entry-category=configuration",
		"javax.portlet.init-param.template-path=/admin/",
		"javax.portlet.init-param.view-template=/admin/view.jsp",
		"javax.portlet.name=" + PowwowKeys.POWWOW_ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator"
	},
	service = Portlet.class
)
public class MeetingsAdminPortlet extends MVCPortlet {

	@Activate
	public void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(
			MeetingsConfiguration.class, properties);
	}

	public void deletePowwowServer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long powwowServerId = ParamUtil.getLong(
			actionRequest, "powwowServerId");

		PowwowServerLocalServiceUtil.deletePowwowServer(powwowServerId);
	}

	public void updatePowwowServer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long powwowServerId = ParamUtil.getLong(
			actionRequest, "powwowServerId");

		String name = ParamUtil.getString(actionRequest, "name");
		String providerType = ParamUtil.getString(
			actionRequest, "providerType");
		String url = ParamUtil.getString(actionRequest, "url");
		String apiKey = ParamUtil.getString(actionRequest, "apiKey");
		String secret = ParamUtil.getString(actionRequest, "secret");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			PowwowServer.class.getName(), actionRequest);

		if (powwowServerId <= 0) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			PowwowServerLocalServiceUtil.addPowwowServer(
				themeDisplay.getUserId(), name, providerType, url, apiKey,
				secret, serviceContext);
		}
		else {
			PowwowServerLocalServiceUtil.updatePowwowServer(
				powwowServerId, name, providerType, url, apiKey, secret,
				serviceContext);
		}
	}

	@Override
	protected void include(
			String path, PortletRequest portletRequest,
			PortletResponse portletResponse, String lifecycle)
		throws IOException, PortletException {

		portletRequest.setAttribute("configuration", _configuration);
		portletRequest.setAttribute(
			"powwowServiceProviderRegistry", _powwowServiceProviderRegistry);

		super.include(path, portletRequest, portletResponse, lifecycle);
	}

	private volatile MeetingsConfiguration _configuration;

	@Reference
	private PowwowServiceProviderRegistry _powwowServiceProviderRegistry;

}