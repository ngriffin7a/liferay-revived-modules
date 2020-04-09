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

package com.liferay.powwow.search.impl;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.QueryTermImpl;
import com.liferay.portal.kernel.search.generic.TermQueryImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.powwow.model.PowwowMeeting;
import com.liferay.powwow.model.PowwowParticipant;
import com.liferay.powwow.provider.PowwowServiceProvider;
import com.liferay.powwow.provider.PowwowServiceProviderRegistry;
import com.liferay.powwow.service.PowwowMeetingLocalServiceUtil;
import com.liferay.powwow.service.PowwowParticipantLocalServiceUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Calderon
 */
@Component(immediate = true, service = Indexer.class)
public class PowwowMeetingIndexer extends BaseIndexer<PowwowMeeting> {

	@Override
	public String getClassName() {
		return PowwowMeeting.class.getName();
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
		throws Exception {

		super.postProcessSearchQuery(
			searchQuery, fullQueryBooleanFilter, searchContext);

		BooleanQuery participantTypeBooleanQuery = new BooleanQueryImpl();

		String[] powwowParticipantKeys = GetterUtil.getStringValues(
			searchContext.getAttribute("powwowParticipantKeys"));

		for (String powwowParticipantKey : powwowParticipantKeys) {
			participantTypeBooleanQuery.add(
				new TermQueryImpl(
					new QueryTermImpl(
						"powwowParticipantKeys", powwowParticipantKey)),
				BooleanClauseOccur.SHOULD);
		}

		long userId = GetterUtil.getLong(
			searchContext.getAttribute(Field.USER_ID));

		if (userId > 0) {
			participantTypeBooleanQuery.addTerm(Field.USER_ID, userId);
		}

		searchQuery.add(participantTypeBooleanQuery, BooleanClauseOccur.MUST);

		int[] statuses = (int[])searchContext.getAttribute("statuses");

		if (statuses.length > 0) {
			BooleanQuery statusesQuery = new BooleanQueryImpl();

			for (int status : statuses) {
				statusesQuery.add(
					new TermQueryImpl(
						new QueryTermImpl("status", String.valueOf(status))),
					BooleanClauseOccur.SHOULD);
			}

			searchQuery.add(statusesQuery, BooleanClauseOccur.MUST);
		}
	}

	protected void addSearchUserId(
		BooleanQuery contextQuery, SearchContext searchContext) {
	}

	@Override
	protected void doDelete(PowwowMeeting powwowMeeting) throws Exception {
		deleteDocument(
			powwowMeeting.getCompanyId(), powwowMeeting.getPowwowMeetingId());
	}

	@Override
	protected Document doGetDocument(PowwowMeeting powwowMeeting)
		throws Exception {

		Document document = getBaseModelDocument(_CLASS_NAME, powwowMeeting);

		document.addText(Field.DESCRIPTION, powwowMeeting.getDescription());
		document.addKeyword(Field.NAME, powwowMeeting.getName());

		String powwowMeetingCreatorName = StringPool.BLANK;

		User powwowMeetingCreatorUser = UserLocalServiceUtil.fetchUser(
			powwowMeeting.getUserId());

		if (powwowMeetingCreatorUser != null) {
			powwowMeetingCreatorName = powwowMeetingCreatorUser.getFullName();
		}

		document.addKeyword("creatorName", powwowMeetingCreatorName);

		List<PowwowParticipant> powwowParticipants =
			PowwowParticipantLocalServiceUtil.getPowwowParticipants(
				powwowMeeting.getPowwowMeetingId());

		String[] powwowParticipantKeys = new String[powwowParticipants.size()];

		for (int i = 0; i < powwowParticipants.size(); i++) {
			PowwowParticipant powwowParticipant = powwowParticipants.get(i);

			if (powwowParticipant.getParticipantUserId() > 0) {
				powwowParticipantKeys[i] =
					powwowParticipant.getParticipantUserId() +
						StringPool.UNDERLINE + powwowParticipant.getType();
			}
		}

		document.addKeyword("powwowParticipantKeys", powwowParticipantKeys);

		document.addText("providerType", powwowMeeting.getProviderType());

		PowwowServiceProvider powwowServiceProvider =
			powwowServiceProviderRegistry.lookupByType(
				powwowMeeting.getProviderType());

		if (powwowServiceProvider != null) {
			Map<String, String> indexFields =
				powwowServiceProvider.getIndexFields(
					powwowMeeting.getPowwowMeetingId());

			for (Map.Entry<String, String> entry : indexFields.entrySet()) {
				document.addKeyword(entry.getKey(), entry.getValue());
			}

			Date date = powwowMeeting.getModifiedDate();

			long startTime = date.getTime();

			CalendarBooking calendarBooking =
				CalendarBookingLocalServiceUtil.fetchCalendarBooking(
					powwowMeeting.getCalendarBookingId());

			if (calendarBooking != null) {
				startTime = calendarBooking.getStartTime();
			}

			document.addKeyword("startTime", startTime);

			document.addNumber("status", powwowMeeting.getStatus());

			return document;
		}

		_log.error(
			"Unable to determine service associated with provider.type=" +
				powwowMeeting.getProviderType());

		return null;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		Summary summary = createSummary(
			document, Field.TITLE, Field.DESCRIPTION);

		summary.setMaxContentLength(200);

		return summary;
	}

	@Override
	protected void doReindex(PowwowMeeting powwowMeeting) throws Exception {
		IndexWriterHelperUtil.updateDocument(
			getSearchEngineId(), powwowMeeting.getCompanyId(),
			getDocument(powwowMeeting), false);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		doReindex(PowwowMeetingLocalServiceUtil.getPowwowMeeting(classPK));
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexPowwowMeetings(companyId);
	}

	protected void reindexPowwowMeetings(long companyId)
		throws PortalException {

		final Collection<Document> documents = new ArrayList<>();

		IndexWriterHelperUtil.updateDocuments(
			getSearchEngineId(), companyId, documents, false);
	}

	@Reference
	protected PowwowServiceProviderRegistry powwowServiceProviderRegistry;

	private static final String _CLASS_NAME = PowwowMeeting.class.getName();

	private static final Log _log = LogFactoryUtil.getLog(
		PowwowMeetingIndexer.class);

}