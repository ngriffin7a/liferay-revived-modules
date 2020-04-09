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

package com.liferay.powwow.provider.zoom.impl;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.powwow.model.PowwowMeeting;
import com.liferay.powwow.model.PowwowMeetingConstants;
import com.liferay.powwow.service.PowwowMeetingLocalServiceUtil;

import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * Example Callback URL: http://localhost:8080/o/powwow/zoom/callback
 *
 * @author Marco Calderon
 */
@Component(
	configurationPid = "com.liferay.powwow.provider.zoom.impl.ZoomConfiguration",
	immediate = true,
	property = {
		"osgi.http.whiteboard.context.path=/",
		"osgi.http.whiteboard.servlet.pattern=/powwow/zoom/callback/*"
	},
	service = Servlet.class
)
public class ZoomCallbackServlet extends HttpServlet {

	@Activate
	public void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(
			ZoomConfiguration.class, properties);
	}

	@Override
	protected void doPost(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		if (!verifyRequest(httpServletRequest)) {
			httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);

			return;
		}

		try {
			String hostId = httpServletRequest.getParameter("host_id");
			String id = httpServletRequest.getParameter("id");
			String status = httpServletRequest.getParameter("status");

			if ((hostId == null) || (id == null) || (status == null)) {
				httpServletResponse.sendError(
					HttpServletResponse.SC_BAD_REQUEST);

				return;
			}

			for (long companyId : PortalUtil.getCompanyIds()) {
				long powwowMeetingId = getPowowwowMeetingId(
					companyId, hostId, id);

				if (powwowMeetingId == 0) {
					continue;
				}

				if (status.equals("ENDED")) {
					PowwowMeetingLocalServiceUtil.updateStatus(
						powwowMeetingId,
						PowwowMeetingConstants.STATUS_COMPLETED);
				}
				else if (status.equals("STARTED")) {
					PowwowMeetingLocalServiceUtil.updateStatus(
						powwowMeetingId,
						PowwowMeetingConstants.STATUS_IN_PROGRESS);
				}
			}

			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		}
		catch (Exception exception) {
			throw new IOException(exception);
		}
	}

	protected long getPowowwowMeetingId(
			long companyId, String zoomHostId, String zoomMeetingId)
		throws Exception {

		SearchContext searchContext = new SearchContext();

		BooleanQuery booleanQuery = new BooleanQueryImpl();

		booleanQuery.addExactTerm("zoomHostId", zoomHostId);
		booleanQuery.addExactTerm("zoomMeetingId", zoomMeetingId);

		BooleanClause booleanClause = BooleanClauseFactoryUtil.create(
			booleanQuery, BooleanClauseOccur.MUST.getName());

		searchContext.setBooleanClauses(new BooleanClause[] {booleanClause});

		searchContext.setCompanyId(companyId);

		Indexer indexer = IndexerRegistryUtil.getIndexer(PowwowMeeting.class);

		Hits hits = indexer.search(searchContext);

		List<Document> documents = hits.toList();

		if (documents.isEmpty()) {
			return 0;
		}

		Document document = documents.get(0);

		return GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK));
	}

	protected boolean verifyRequest(HttpServletRequest httpServletRequest) {
		String authorization = httpServletRequest.getHeader("Authorization");

		if (authorization == null) {
			return false;
		}

		StringTokenizer st = new StringTokenizer(authorization);

		if (!st.hasMoreTokens()) {
			return false;
		}

		String basic = st.nextToken();

		if (!StringUtil.equalsIgnoreCase(
				basic, HttpServletRequest.BASIC_AUTH)) {

			return false;
		}

		String encodedCredentials = st.nextToken();

		String decodedCredentials = new String(
			Base64.decode(encodedCredentials));

		int pos = decodedCredentials.indexOf(CharPool.COLON);

		if (pos == -1) {
			return false;
		}

		String login = GetterUtil.getString(
			decodedCredentials.substring(0, pos));
		String password = decodedCredentials.substring(pos + 1);

		if (login.equals(_configuration.callbackLogin()) &&
			password.equals(_configuration.callbackPassword())) {

			return true;
		}

		return false;
	}

	private static final long serialVersionUID = 1L;

	private volatile ZoomConfiguration _configuration;

}