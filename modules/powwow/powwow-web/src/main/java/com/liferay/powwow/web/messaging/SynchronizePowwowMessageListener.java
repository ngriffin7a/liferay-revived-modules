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

package com.liferay.powwow.web.messaging;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.powwow.provider.PowwowServiceProvider;
import com.liferay.powwow.provider.PowwowServiceProviderRegistry;
import com.liferay.powwow.service.PowwowMeetingLocalService;
import com.liferay.powwow.service.PowwowServerLocalService;
import com.liferay.powwow.web.config.MeetingsConfiguration;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(
	configurationPid = "com.liferay.powwow.web.config.MeetingsConfiguration",
	immediate = true, service = SynchronizePowwowMessageListener.class
)
public class SynchronizePowwowMessageListener extends BaseMessageListener {

	@Activate
	public void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(
			MeetingsConfiguration.class, properties);

		Class<?> clazz = getClass();

		String className = clazz.getName();

		Trigger trigger = _triggerFactory.createTrigger(
			className, className, null, null, _configuration.syncInterval(),
			TimeUnit.MINUTE);

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
			className, trigger);

		_schedulerEngineHelper.register(
			this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	public void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		try {
			_powwowMeetingLocalService.checkPowwowMeetings();

			for (String providerType : _configuration.providerTypes()) {
				PowwowServiceProvider powwowServiceProvider =
					_powwowServiceProviderRegistry.lookupByType(providerType);

				_powwowServerLocalService.checkPowwowServers(
					powwowServiceProvider);
			}
		}
		catch (PortalException e) {
			_log.error(e.getMessage());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SynchronizePowwowMessageListener.class);

	private volatile MeetingsConfiguration _configuration;

	@Reference
	private PowwowMeetingLocalService _powwowMeetingLocalService;

	@Reference
	private PowwowServerLocalService _powwowServerLocalService;

	@Reference
	private PowwowServiceProviderRegistry _powwowServiceProviderRegistry;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;

}