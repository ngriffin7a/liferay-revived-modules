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

package com.liferay.todo.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the TodoEntry service. Represents a row in the &quot;TDL_TodoEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ryan Park
 * @see TodoEntryModel
 * @generated
 */
@ImplementationClassName("com.liferay.todo.model.impl.TodoEntryImpl")
@ProviderType
public interface TodoEntry extends PersistedModel, TodoEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.todo.model.impl.TodoEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TodoEntry, Long> TODO_ENTRY_ID_ACCESSOR =
		new Accessor<TodoEntry, Long>() {

			@Override
			public Long get(TodoEntry todoEntry) {
				return todoEntry.getTodoEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TodoEntry> getTypeClass() {
				return TodoEntry.class;
			}

		};

}