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

package com.liferay.powwow.web.view;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shinn Lok
 */
@Component(service = SearchContainerViewState.class)
public class SearchContainerViewState {

	public static Sort[] getPowwowMeetingSorts(
		String orderByCol, String orderByType) {

		if (Validator.isNull(orderByCol) || Validator.isNull(orderByType)) {
			return SortFactoryUtil.getDefaultSorts();
		}

		boolean reverse = true;

		if (orderByType.equals("asc")) {
			reverse = false;
		}

		if (orderByCol.equals("created-by")) {
			return new Sort[] {
				SortFactoryUtil.create("creatorName", Sort.STRING_TYPE, reverse)
			};
		}
		else if (orderByCol.equals("date")) {
			return new Sort[] {
				SortFactoryUtil.create("startTime", Sort.LONG_TYPE, reverse)
			};
		}
		else if (orderByCol.equals("name")) {
			return new Sort[] {
				SortFactoryUtil.create(Field.NAME, Sort.STRING_TYPE, reverse)
			};
		}

		return SortFactoryUtil.getDefaultSorts();
	}

}