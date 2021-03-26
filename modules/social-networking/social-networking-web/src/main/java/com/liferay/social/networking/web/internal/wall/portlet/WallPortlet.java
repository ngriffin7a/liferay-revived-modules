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

package com.liferay.social.networking.web.internal.wall.portlet;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.permission.UserPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.social.kernel.model.SocialRelationConstants;
import com.liferay.social.kernel.service.SocialRelationLocalService;
import com.liferay.social.networking.constants.SocialNetworkingPortletKeys;
import com.liferay.social.networking.model.WallEntry;
import com.liferay.social.networking.service.WallEntryLocalService;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=social-networking-portlet-wall",
		"com.liferay.portlet.display-category=category.social",
		"com.liferay.portlet.icon=/icons/wall.png",
		"javax.portlet.display-name=Wall", "javax.portlet.expiration-cache=0",
		"javax.portlet.info.short-title=Wall", "javax.portlet.info.title=Wall",
		"javax.portlet.init-param.clear-request-parameters=true",
		"javax.portlet.init-param.view-template=/wall/view.jsp",
		"javax.portlet.keywords=Wall",
		"javax.portlet.name=" + SocialNetworkingPortletKeys.WALL,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class WallPortlet extends MVCPortlet {

	public void addWallEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		Group group = _groupLocalService.getGroup(
			themeDisplay.getScopeGroupId());

		User user = themeDisplay.getUser();

		if (group.isUser()) {
			user = _userLocalService.getUserById(group.getClassPK());
		}

		if ((themeDisplay.getUserId() != user.getUserId()) &&
			!_socialRelationLocalService.hasRelation(
				themeDisplay.getUserId(), user.getUserId(),
				SocialRelationConstants.TYPE_BI_CONNECTION)) {

			return;
		}

		String comments = ParamUtil.getString(actionRequest, "comments");

		_wallEntryLocalService.addWallEntry(
			themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), comments,
			themeDisplay);
	}

	public void deleteWallEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		long wallEntryId = ParamUtil.getLong(actionRequest, "wallEntryId");

		WallEntry wallEntry = null;

		try {
			wallEntry = _wallEntryLocalService.getWallEntry(wallEntryId);
		}
		catch (Exception e) {
			return;
		}

		if (wallEntry.getGroupId() != themeDisplay.getScopeGroupId()) {
			return;
		}

		Group group = _groupLocalService.getGroup(
			themeDisplay.getScopeGroupId());

		User user = null;

		if (group.isUser()) {
			user = _userLocalService.getUserById(group.getClassPK());
		}
		else {
			return;
		}

		if (!UserPermissionUtil.contains(
				themeDisplay.getPermissionChecker(), user.getUserId(),
				ActionKeys.UPDATE)) {

			return;
		}

		try {
			_wallEntryLocalService.deleteWallEntry(wallEntryId);
		}
		catch (Exception e) {
		}
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(unbind = "-")
	protected void setSocialRelationLocalService(
		SocialRelationLocalService socialRelationLocalService) {

		_socialRelationLocalService = socialRelationLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	@Reference(unbind = "-")
	protected void setWallEntryLocalService(
		WallEntryLocalService wallEntryLocalService) {

		_wallEntryLocalService = wallEntryLocalService;
	}

	private GroupLocalService _groupLocalService;
	private SocialRelationLocalService _socialRelationLocalService;
	private UserLocalService _userLocalService;
	private WallEntryLocalService _wallEntryLocalService;

}