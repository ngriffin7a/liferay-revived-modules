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

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.powwow.web.constants.PowwowKeys;

import org.osgi.service.component.annotations.Component;

/**
 * @author Neil Griffin
 */
@Component(
	immediate = true,
	property = "panel.category.key=" + PanelCategoryKeys.CONTROL_PANEL_CONFIGURATION,
	service = PanelApp.class
)
public class MeetingsControlPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return PowwowKeys.POWWOW_ADMIN;
	}

}