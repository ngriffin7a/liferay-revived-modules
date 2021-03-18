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

package com.liferay.social.networking.service.impl;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.social.networking.model.WallEntry;
import com.liferay.social.networking.service.base.WallEntryLocalServiceBaseImpl;

import com.liferay.social.networking.wall.social.WallActivityKeys;
import org.osgi.service.component.annotations.Component;

import javax.mail.internet.InternetAddress;
import java.util.List;

/**
 * The implementation of the wall entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.social.networking.service.WallEntryLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WallEntryLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.social.networking.model.WallEntry",
	service = AopService.class
)
public class WallEntryLocalServiceImpl extends WallEntryLocalServiceBaseImpl {

	@Override
	public WallEntry addWallEntry(
			long groupId, long userId, String comments,
			ThemeDisplay themeDisplay)
		throws PortalException {

		// Wall entry

		Group group = groupLocalService.getGroup(groupId);
		User user = userLocalService.getUserById(userId);

		long wallEntryId = counterLocalService.increment();

		WallEntry wallEntry = wallEntryPersistence.create(wallEntryId);

		wallEntry.setGroupId(groupId);
		wallEntry.setCompanyId(user.getCompanyId());
		wallEntry.setUserId(user.getUserId());
		wallEntry.setUserName(user.getFullName());
		wallEntry.setComments(comments);

		wallEntryPersistence.update(wallEntry);

		// Email

		try {
			sendEmail(wallEntry, themeDisplay);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}

		// Social

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("comments", wallEntry.getComments());

		if (userId != group.getClassPK()) {
			socialActivityLocalService.addActivity(
				userId, groupId, WallEntry.class.getName(), wallEntryId,
				WallActivityKeys.ADD_ENTRY, extraDataJSONObject.toString(),
				group.getClassPK());
		}

		return wallEntry;
	}

	@Override
	public void deleteWallEntries(long groupId) throws PortalException {
		List<WallEntry> wallEntries = wallEntryPersistence.findByGroupId(
			groupId);

		for (WallEntry wallEntry : wallEntries) {
			deleteWallEntry(wallEntry);
		}
	}

	@Override
	public WallEntry deleteWallEntry(long wallEntryId) throws PortalException {
		WallEntry wallEntry = wallEntryPersistence.findByPrimaryKey(
			wallEntryId);

		return deleteWallEntry(wallEntry);
	}

	@Override
	public WallEntry deleteWallEntry(WallEntry wallEntry)
		throws PortalException {

		// Entry

		wallEntryPersistence.remove(wallEntry);

		// Social

		socialActivityLocalService.deleteActivities(
			WallEntry.class.getName(), wallEntry.getWallEntryId());

		return wallEntry;
	}

	@Override
	public List<WallEntry> getWallEntries(long groupId, int start, int end) {
		return wallEntryPersistence.findByGroupId(groupId, start, end);
	}

	@Override
	public int getWallEntriesCount(long groupId) {
		return wallEntryPersistence.countByGroupId(groupId);
	}

	@Override
	public WallEntry getWallEntry(long wallEntryId) throws PortalException {
		return wallEntryPersistence.findByPrimaryKey(wallEntryId);
	}

	@Override
	public List<WallEntry> getWallToWallEntries(
		long groupId1, long groupId2, long userId1, long userId2, int start,
		int end) {

		return wallEntryFinder.findByG1_G2_U1_U2(
			groupId1, groupId2, userId1, userId2, start, end);
	}

	@Override
	public int getWallToWallEntriesCount(
		long groupId1, long groupId2, long userId1, long userId2) {

		return wallEntryFinder.countByG1_G2_U1_U2(
			groupId1, groupId2, userId1, userId2);
	}

	@Override
	public WallEntry updateWallEntry(long wallEntryId, String comments)
		throws PortalException {

		WallEntry wallEntry = wallEntryPersistence.findByPrimaryKey(
			wallEntryId);

		wallEntry.setComments(comments);

		wallEntryPersistence.update(wallEntry);

		return wallEntry;
	}

	protected void sendEmail(WallEntry wallEntry, ThemeDisplay themeDisplay)
		throws Exception {

		long companyId = wallEntry.getCompanyId();

		String wallEntryURL = PortalUtil.getLayoutURL(themeDisplay);

		Group group = groupLocalService.getGroup(wallEntry.getGroupId());

		User user = userLocalService.getUserById(group.getClassPK());

		User wallEntryUser = userLocalService.getUserById(
			wallEntry.getUserId());

		String fromName = PrefsPropsUtil.getString(
			companyId, PropsKeys.ADMIN_EMAIL_FROM_NAME);
		String fromAddress = PrefsPropsUtil.getString(
			companyId, PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);

		String toName = user.getFullName();
		String toAddress = user.getEmailAddress();

		String subject = StringUtil.read(
			getClassLoader(),
			"com/liferay/social/networking/wall/dependencies" +
				"/wall_entry_added_subject.tmpl");
		String body = StringUtil.read(
			getClassLoader(),
			"com/liferay/social/networking/wall/dependencies" +
				"/wall_entry_added_body.tmpl");

		subject = StringUtil.replace(
			subject,
			new String[] {
				"[$FROM_ADDRESS$]", "[$FROM_NAME$]", "[$TO_ADDRESS$]",
				"[$TO_NAME$]", "[$WALL_ENTRY_URL$]",
				"[$WALL_ENTRY_USER_ADDRESS$]", "[$WALL_ENTRY_USER_NAME$]"
			},
			new String[] {
				fromAddress, fromName, toAddress, toName, wallEntryURL,
				wallEntryUser.getEmailAddress(), wallEntryUser.getFullName()
			});

		body = StringUtil.replace(
			body,
			new String[] {
				"[$FROM_ADDRESS$]", "[$FROM_NAME$]", "[$TO_ADDRESS$]",
				"[$TO_NAME$]", "[$WALL_ENTRY_URL$]",
				"[$WALL_ENTRY_USER_ADDRESS$]", "[$WALL_ENTRY_USER_NAME$]"
			},
			new String[] {
				fromAddress, fromName, toAddress, toName, wallEntryURL,
				wallEntryUser.getEmailAddress(), wallEntryUser.getFullName()
			});

		InternetAddress from = new InternetAddress(fromAddress, fromName);

		InternetAddress to = new InternetAddress(toAddress, toName);

		MailMessage mailMessage = new MailMessage(
			from, to, subject, body, true);

		MailServiceUtil.sendEmail(mailMessage);
	}

}