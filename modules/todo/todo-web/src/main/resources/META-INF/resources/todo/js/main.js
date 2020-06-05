AUI().add(
	'liferay-todo',
	function(A) {
		Liferay.Todo = {
			init: function(param) {
				var instance = this;

				instance._setupFilter();
				instance._setupTagsPopup();
				instance._setupProgressBar();

				instance._baseActionURL = param.baseActionURL;
				instance._currentTab = param.currentTab;
				instance._namespace = param.namespace;
				instance._todoListURL = param.todoListURL;
			},

			clearFilters: function() {
				var instance = this;

				A.all('.todo-portlet .asset-tag-filter .asset-tag.selected').toggleClass('selected');

				var groupFilter = A.one('.todo-portlet .group-filter select');

				if (groupFilter) {
					groupFilter.set('value', 0);
				}

				instance.updateTodoList();
			},

			displayPopup: function(url, title) {
				var instance = this;

				Liferay.Util.openWindow(
					{
						dialog: {
							after: {
								destroy: function(event) {
									instance.updateTodoList();
								}
							},
							centered: true,
							constrain: true,
							cssClass: 'todo-dialog',
							destroyOnHide: true,
							modal: true,
							plugins: [Liferay.WidgetZIndex],
							width: 800
						},
						id: instance._namespace + 'Dialog',
						title: title,
						uri: url
					}
				);
			},

			initUpcomingTodo: function(param) {
				var instance = this;

				instance._upcomingTodoListURL = param.upcomingTodoListURL;
			},

			openTodo: function(href, todoEntryId) {
				var instance = this;

				instance.displayPopup(href, Liferay.Language.get('model.resource.com.liferay.todo.model.TodoEntry'));

				instance._updateViewCount(todoEntryId);
			},

			toggleCommentForm: function() {
				var comment = A.one('.todo-dialog .add-comment');

				var control = comment.one('.control');
				var form = comment.one('.form');

				form.toggle();
				control.toggle();
			},

			toggleTodoFilter: function() {
				A.one('.todo-portlet .filter-wrapper').toggle();
			},

			updateTodoList: function(url, showAll) {
				var instance = this;

				instance._todoList = A.one('.todo-portlet .list-wrapper');

				if (!instance._todoList) {
					instance._todoList = A.one('.upcoming-todo-portlet .todo-entries-container');

					if (!url) {
						url = instance._upcomingTodoListURL;
					}
				}

				if (!instance._todoList.io) {
					instance._todoList.plug(
						A.Plugin.IO,
						{autoLoad: false}
					);
				}

				if (!url) {
					url = instance._todoListURL;

					var data = {};

					if (!showAll) {
						showAll = A.one('.todo-portlet input[name="all-todo"]').get('checked');
					}

					data[instance._namespace + 'assetTagIds'] = instance._getAssetTagIds();
					data[instance._namespace + 'groupId'] = instance._getGroupId();
					data[instance._namespace + 'tabs1'] = instance._currentTab;
					data[instance._namespace + 'tabs2'] = showAll ? 'all' : 'open';

					instance._todoList.io.set('data', data);
				}

				instance._todoList.io.set('uri', url);

				instance._todoList.io.start();
			},

			_getAssetTagIds: function() {
				var assetTagIds = [];

				A.all('.todo-portlet .asset-tag-filter .asset-tag.selected').each(
					function(assetTag, index, collection) {
						assetTagIds.push(assetTag.attr('data-assetTagId'));
					}
				);

				return assetTagIds.join(',');
			},

			_getGroupId: function() {
				var groupSelect = A.one('.todo-portlet .group-filter select');

				if (!groupSelect) {
					return 0;
				}

				return groupSelect.get('value');
			},

			_setupFilter: function() {
				var instance = this;

				A.one('.todo-portlet .asset-tag-filter').delegate(
					'click',
					function(event) {
						var assetTag = event.currentTarget;

						if (assetTag.hasClass('icon-check')) {
							assetTag.removeClass('icon-check');
							assetTag.addClass('icon-check-empty');
						}
						else {
							assetTag.removeClass('icon-check-empty');
							assetTag.addClass('icon-check');
						}

						assetTag.toggleClass('selected');

						instance.updateTodoList();
					},
					'.asset-tag'
				);

				A.all('.todo-portlet .group-filter select').on(
					'change',
					function(event) {
						instance.updateTodoList();
					}
				);
			},

			_setupProgressBar: function() {
				var instance = this;

				var portlet = A.one('.todo-portlet .list-wrapper');

				portlet.delegate(
					'mouseover',
					function(event) {
						event = event.currentTarget;

						event.one('.current').hide();
						event.one('.progress-picker').show();
					},
					'.progress-wrapper'
				);

				portlet.delegate(
					'mouseout',
					function(event) {
						event = event.currentTarget;

						event.one('.current').show();
						event.one('.progress-picker').hide();
					},
					'.progress-wrapper'
				);

				portlet.delegate(
					'mouseover',
					function(event) {
						event = event.currentTarget;

						var str = event.getAttribute('class');

						var pos = str.substring(str.indexOf('progress-') + 9);

						var completedText = Liferay.Language.get('complete');

						if (pos !== '100') {
							completedText = Liferay.Language.get(pos + '-percent-complete');
						}

						var container = event.ancestor('.progress-wrapper');

						container.one('.new-progress').setStyle('width', pos + '%');
						container.one('.progress-indicator').set('text', completedText);
					},
					'.progress-selector a'
				);

				portlet.delegate(
					'click',
					function(event) {
						event.halt();

						var href = event.currentTarget.getAttribute('href');

						instance.updateTodoList(href);
					},
					'.progress-selector a'
				);
			},

			_setupTagsPopup: function() {
				var container = A.one('.todo-portlet');

				container.delegate(
					'mouseover',
					function(event) {
						event.currentTarget.one('.tags').show();
					},
					'.tags-wrapper'
				);

				container.delegate(
					'mouseout',
					function(event) {
						event.currentTarget.one('.tags').hide();
					},
					'.tags-wrapper'
				);
			},

			_updateViewCount: function(todoEntryId) {
				var instance = this;

				var portletURL = new Liferay.PortletURL.createURL(instance._baseActionURL);

				portletURL.setParameter('javax.portlet.action', 'updateTodoEntryViewCount');
				portletURL.setParameter('todoEntryId', todoEntryId);
				portletURL.setWindowState('normal');

				A.io.request(portletURL.toString());
			}
		};
	},
	'',
	{
		requires: ['aui-base', 'aui-io-plugin-deprecated', 'aui-modal', 'liferay-util-window', 'liferay-widget-zindex']
	}
);