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

package com.liferay.social.networking.web.internal.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.social.networking.constants.SocialNetworkingPortletKeys;
import com.liferay.social.networking.web.internal.configuration.SocialNetworkingServiceConfigurationValues;

/**
 * @author Zsolt Berentey
 */
public class WallUtil {

	public static String getWallLayoutFriendlyURL(User user)
		throws PortalException {

		String wallLayoutFriendlyURL =
			SocialNetworkingServiceConfigurationValues.WALL_LAYOUT_FRIENDLY_URL;

		if (Validator.isNull(wallLayoutFriendlyURL)) {
			Group group = user.getGroup();

			long plid = LayoutLocalServiceUtil.getDefaultPlid(
				group.getGroupId(), false, SocialNetworkingPortletKeys.WALL);

			if (plid != 0) {
				Layout layout = LayoutLocalServiceUtil.getLayout(plid);

				wallLayoutFriendlyURL = layout.getFriendlyURL();
			}
		}

		return wallLayoutFriendlyURL;
	}

	public static String getWallLink(
			User user, String wallLayoutFriendlyURL, String screenNameOrUserId,
			ThemeDisplay themeDisplay)
		throws Exception {

		if (Validator.isNull(wallLayoutFriendlyURL)) {
			return null;
		}

		StringBundler sb = new StringBundler(7);

		sb.append(themeDisplay.getPortalURL());
		sb.append(themeDisplay.getPathFriendlyURLPublic());
		sb.append(StringPool.SLASH);
		sb.append(HtmlUtil.escapeURL(user.getScreenName()));
		sb.append(wallLayoutFriendlyURL);
		sb.append("/-/wall/");
		sb.append(screenNameOrUserId);

		return sb.toString();
	}

}