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

package com.liferay.powwow.provider.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.powwow.model.PowwowMeeting;
import com.liferay.powwow.provider.PowwowServiceProvider;
import com.liferay.powwow.provider.PowwowServiceProviderRegistry;
import com.liferay.powwow.service.PowwowMeetingLocalServiceUtil;
import com.liferay.powwow.service.impl.PowwowMeetingLocalServiceImpl;

import java.util.Collection;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Neil Griffin
 */
@Component(service = PowwowServiceProviderRegistry.class)
public class PowwowServiceProviderRegistryImpl
	implements PowwowServiceProviderRegistry {

	@Activate
	public void activate() {
	}

	@Override
	public PowwowServiceProvider lookupByMeetingId(long powwowMeetingId)
		throws PortalException {

		PowwowMeeting powwowMeeting =
			PowwowMeetingLocalServiceUtil.getPowwowMeeting(powwowMeetingId);

		if (powwowMeeting == null) {
			return null;
		}

		return lookupByType(powwowMeeting.getProviderType());
	}

	public PowwowServiceProvider lookupByType(String providerType) {
		Collection<ServiceReference<PowwowServiceProvider>> serviceReferences =
			null;

		try {
			Bundle bundle = FrameworkUtil.getBundle(
				PowwowMeetingLocalServiceImpl.class);

			BundleContext bundleContext = bundle.getBundleContext();

			serviceReferences = bundleContext.getServiceReferences(
				PowwowServiceProvider.class,
				"(provider.type=" + providerType + ")");

			for (ServiceReference<PowwowServiceProvider> serviceReference :
					serviceReferences) {

				return bundleContext.getService(serviceReference);
			}
		}
		catch (InvalidSyntaxException e) {
			_log.error(e.getMessage(), e);
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PowwowServiceProviderRegistry.class);

}