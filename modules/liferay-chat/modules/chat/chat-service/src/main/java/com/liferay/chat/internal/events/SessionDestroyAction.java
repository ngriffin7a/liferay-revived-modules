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

package com.liferay.chat.internal.events;

import com.liferay.chat.internal.jabber.JabberUtil;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.SessionAction;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;

/**
 * @author Bruno Farache
 */
@Component(
	enabled = false, immediate = true,
	property = "key=servlet.session.destroy.events",
	service = LifecycleAction.class
)
public class SessionDestroyAction extends SessionAction {

	@Override
	public void run(HttpSession session) {
		Long userId = (Long)session.getAttribute(WebKeys.USER_ID);

		JabberUtil.disconnect(userId);
	}

}