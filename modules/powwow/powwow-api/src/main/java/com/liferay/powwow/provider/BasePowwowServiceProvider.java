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

package com.liferay.powwow.provider;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.powwow.model.PowwowMeeting;
import com.liferay.powwow.model.PowwowMeetingConstants;
import com.liferay.powwow.model.PowwowServer;
import com.liferay.powwow.service.PowwowMeetingLocalService;
import com.liferay.powwow.service.PowwowServerLocalService;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * @author Marco Calderon
 */
public abstract class BasePowwowServiceProvider
	implements PowwowServiceProvider {

	@Override
	public Map<String, Serializable> addPowwowMeeting(
			long userId, long powwowServerId, long powwowMeetingId, String name,
			Map<String, String> options, String welcomeMessage)
		throws PortalException {

		UserLocalService userLocalService = getUserLocalService();
		PowwowServerLocalService powwowServerLocalService =
			getPowwowServerLocalService();

		return addPowwowMeeting(
			userLocalService.getUser(userId),
			powwowServerLocalService.getPowwowServer(powwowServerId),
			powwowMeetingId, name, options, welcomeMessage);
	}

	@Override
	public PowwowMeeting deletePowwowMeeting(long powwowMeetingId)
		throws PortalException {

		PowwowMeetingLocalService powwowMeetingLocalService =
			getPowwowMeetingLocalService();

		PowwowMeeting powwowMeeting =
			powwowMeetingLocalService.getPowwowMeeting(powwowMeetingId);

		PowwowServerLocalService powwowServerLocalService =
			getPowwowServerLocalService();

		PowwowServer powwowServer = powwowServerLocalService.getPowwowServer(
			powwowMeeting.getPowwowServerId());

		if (!deletePowwowMeeting(powwowServer, powwowMeeting)) {
			return null;
		}

		return powwowMeeting;
	}

	@Override
	public PowwowMeeting endPowwowMeeting(long powwowMeetingId)
		throws PortalException {

		PowwowMeetingLocalService powwowMeetingLocalService =
			getPowwowMeetingLocalService();

		PowwowMeeting powwowMeeting =
			powwowMeetingLocalService.fetchPowwowMeeting(powwowMeetingId);

		if (powwowMeeting == null) {
			return null;
		}

		PowwowServerLocalService powwowServerLocalService =
			getPowwowServerLocalService();

		PowwowServer powwowServer = powwowServerLocalService.fetchPowwowServer(
			powwowMeeting.getPowwowServerId());

		if (powwowServer == null) {
			return null;
		}

		if (endPowwowMeeting(powwowServer, powwowMeeting)) {
			powwowMeetingLocalService.updateStatus(
				powwowMeetingId, PowwowMeetingConstants.STATUS_COMPLETED);
		}

		return powwowMeeting;
	}

	@Override
	public Map<String, String> getIndexFields(long powwowMeetingId)
		throws PortalException {

		PowwowMeetingLocalService powwowMeetingLocalService =
			getPowwowMeetingLocalService();

		return getIndexFields(
			powwowMeetingLocalService.getPowwowMeeting(powwowMeetingId));
	}

	@Override
	public long getJoinByPhoneAccessCode(long powwowMeetingId) {
		PowwowMeetingLocalService powwowMeetingLocalService =
			getPowwowMeetingLocalService();

		PowwowMeeting powwowMeeting =
			powwowMeetingLocalService.fetchPowwowMeeting(powwowMeetingId);

		if (powwowMeeting == null) {
			return 0;
		}

		return getJoinByPhoneAccessCode(powwowMeeting);
	}

	@Override
	public String getJoinByPhoneAccessCodeLabel() {
		return "access-code";
	}

	@Override
	public List<String> getJoinByPhoneDefaultNumbers() {
		return null;
	}

	@Override
	public Map<String, List<String>> getJoinByPhoneInternationalNumbers() {
		return null;
	}

	@Override
	public String getJoinPowwowMeetingURL(
		long powwowMeetingId, String name, int type) {

		PowwowMeetingLocalService powwowMeetingLocalService =
			getPowwowMeetingLocalService();

		PowwowMeeting powwowMeeting =
			powwowMeetingLocalService.fetchPowwowMeeting(powwowMeetingId);

		if (powwowMeeting == null) {
			return StringPool.BLANK;
		}

		PowwowServerLocalService powwowServerLocalService =
			getPowwowServerLocalService();

		PowwowServer powwowServer = powwowServerLocalService.fetchPowwowServer(
			powwowMeeting.getPowwowServerId());

		if (powwowServer == null) {
			return StringPool.BLANK;
		}

		return getJoinPowwowMeetingURL(powwowServer, powwowMeeting, name, type);
	}

	@Override
	public boolean getOptionAutoStartVideo(long powwowMeetingId) {
		PowwowMeetingLocalService powwowMeetingLocalService =
			getPowwowMeetingLocalService();

		PowwowMeeting powwowMeeting =
			powwowMeetingLocalService.fetchPowwowMeeting(powwowMeetingId);

		return getOptionAutoStartVideo(powwowMeeting);
	}

	@Override
	public String getOptionPassword(long powwowMeetingId) {
		PowwowMeetingLocalService powwowMeetingLocalService =
			getPowwowMeetingLocalService();

		PowwowMeeting powwowMeeting =
			powwowMeetingLocalService.fetchPowwowMeeting(powwowMeetingId);

		return getOptionPassword(powwowMeeting);
	}

	@Override
	public boolean isFieldAPIKeyRequired() {
		return false;
	}

	@Override
	public boolean isFieldSecretRequired() {
		return false;
	}

	@Override
	public boolean isFieldURLRequired() {
		return false;
	}

	@Override
	public boolean isPowwowMeetingCreated(long powwowMeetingId)
		throws PortalException {

		PowwowMeetingLocalService powwowMeetingLocalService =
			getPowwowMeetingLocalService();

		PowwowMeeting powwowMeeting =
			powwowMeetingLocalService.getPowwowMeeting(powwowMeetingId);

		if (powwowMeeting.getPowwowServerId() <= 0) {
			return false;
		}

		PowwowServerLocalService powwowServerLocalService =
			getPowwowServerLocalService();

		PowwowServer powwowServer = powwowServerLocalService.getPowwowServer(
			powwowMeeting.getPowwowServerId());

		return isPowwowMeetingCreated(powwowServer, powwowMeeting);
	}

	@Override
	public boolean isPowwowMeetingRunning(long powwowMeetingId)
		throws PortalException {

		PowwowMeetingLocalService powwowMeetingLocalService =
			getPowwowMeetingLocalService();

		PowwowMeeting powwowMeeting =
			powwowMeetingLocalService.getPowwowMeeting(powwowMeetingId);

		PowwowServerLocalService powwowServerLocalService =
			getPowwowServerLocalService();

		PowwowServer powwowServer = powwowServerLocalService.getPowwowServer(
			powwowMeeting.getPowwowServerId());

		return isPowwowMeetingRunning(powwowServer, powwowMeeting);
	}

	@Override
	public boolean isSupportsJoinByPhone() {
		return false;
	}

	@Override
	public boolean isSupportsOptionAutoStartVideo() {
		return false;
	}

	@Override
	public boolean isSupportsOptionPassword() {
		return false;
	}

	@Override
	public boolean isSupportsPresettingParticipantName() {
		return false;
	}

	public String sendRequest(
		Http.Options options, int retryAttempts, int retryInterval) {

		for (int i = 0; i < retryAttempts; i++) {
			try {
				return HttpUtil.URLtoString(options);
			}
			catch (Exception exception) {
				try {
					Thread.sleep(retryInterval);
				}
				catch (InterruptedException interruptedException) {
				}
			}
		}

		throw new SystemException(
			"Unable to complete request to " + options.getLocation());
	}

	@Override
	public Map<String, Serializable> updatePowwowMeeting(
			long powwowMeetingId, String name, long userId,
			Map<String, String> options, String welcomeMessage)
		throws PortalException {

		PowwowMeetingLocalService powwowMeetingLocalService =
			getPowwowMeetingLocalService();

		PowwowMeeting powwowMeeting =
			powwowMeetingLocalService.getPowwowMeeting(powwowMeetingId);

		PowwowServerLocalService powwowServerLocalService =
			getPowwowServerLocalService();

		PowwowServer powwowServer = powwowServerLocalService.getPowwowServer(
			powwowMeeting.getPowwowServerId());

		UserLocalService userLocalService = getUserLocalService();

		return updatePowwowMeeting(
			powwowServer, powwowMeeting, name, userLocalService.getUser(userId),
			options, welcomeMessage);
	}

	protected abstract Map<String, Serializable> addPowwowMeeting(
		User creator, PowwowServer powwowServer, long powwowMeetingId,
		String name, Map<String, String> options, String welcomeMessage);

	protected abstract boolean deletePowwowMeeting(
		PowwowServer powwowServer, PowwowMeeting powwowMeeting);

	protected abstract boolean endPowwowMeeting(
		PowwowServer powwowServer, PowwowMeeting powwowMeeting);

	protected abstract Map<String, String> getIndexFields(
		PowwowMeeting powwowMeeting);

	protected abstract long getJoinByPhoneAccessCode(
		PowwowMeeting powwowMeeting);

	protected abstract String getJoinPowwowMeetingURL(
		PowwowServer powwowServer, PowwowMeeting powwowMeeting, String name,
		int type);

	protected abstract boolean getOptionAutoStartVideo(
		PowwowMeeting powwowMeeting);

	protected abstract String getOptionPassword(PowwowMeeting powwowMeeting);

	protected abstract PowwowMeetingLocalService getPowwowMeetingLocalService();

	protected abstract PowwowServerLocalService getPowwowServerLocalService();

	protected abstract UserLocalService getUserLocalService();

	protected abstract boolean isPowwowMeetingCreated(
		PowwowServer powwowServer, PowwowMeeting powwowMeeting);

	protected abstract boolean isPowwowMeetingRunning(
		PowwowServer powwowServer, PowwowMeeting powwowMeeting);

	protected abstract Map<String, Serializable> updatePowwowMeeting(
		PowwowServer powwowServer, PowwowMeeting powwowMeeting, String name,
		User creator, Map<String, String> options, String welcomeMessage);

}