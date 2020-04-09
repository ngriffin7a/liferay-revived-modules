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

package com.liferay.powwow.provider.bbb.impl;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Neil Griffin
 */
@ExtendedObjectClassDefinition(category = "collaboration")
@Meta.OCD(
	id = "com.liferay.powwow.provider.bbb.impl.BBBConfiguration",
	localization = "content/Language"
)
public interface BBBConfiguration {

	@Meta.AD(deflt = "", name = "callback-login", required = false)
	public String callbackLogin();

	@Meta.AD(deflt = "", name = "callback-password", required = false)
	public String callbackPassword();

	@Meta.AD(deflt = "3", name = "retry-attempts", required = false)
	public int retryAttempts();

	@Meta.AD(deflt = "30000", name = "retry-interval", required = false)
	public int retryInterval();

}