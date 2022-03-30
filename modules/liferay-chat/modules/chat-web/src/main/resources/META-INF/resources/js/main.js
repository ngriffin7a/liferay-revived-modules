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

/* global SWFObject */

AUI().use(
	'anim-color',
	'anim-easing',
	'aui-base',
	'aui-datatype',
	'aui-live-search-deprecated',
	'liferay-poller',
	'node-focusmanager',
	'stylesheet',
	'swfobject',
	function(A) {
		var KeyMap = A.Event.KeyMap;
		var Lang = A.Lang;
		var LString = Lang.String;

		var Config = A.config;

		var Notification = Config.win.Notification;

		var windowId = Liferay.Util.randomInt();

		var now = Date.now;

		var DOC = Config.doc;

		var ENTER = 'ENTER';

		var ESC = 'ESC';

		var NOTIFICATIONS_LIST = [];

		var NOTIFICATIONS_PERMISSION_DEFAULT = 'default';

		var NOTIFICATIONS_PERMISSION_GRANTED = 'granted';

		var STR_NEW_MESSAGE = Liferay.Language.get('new-message-from-x');

		Liferay.namespace('Chat');

		A.one(DOC.documentElement).toggleClass(
			'desktop-notifications',
			!!Notification
		);

		Liferay.Chat.Util = {
			formatTime: function(time) {
				var instance = this;

				time = instance._convertToClientTimestamp(time);

				time = new Date(time);

				return A.DataType.Date.format(time, {
					format: '%X',
					locale: themeDisplay.getLanguageId()
				});
			},

			getCurrentTimestamp: function() {
				var instance = this;

				return now() - instance._getOffset();
			},

			getDefaultColor: function() {
				var instance = this;

				var defaultColor = instance._defaultColor;

				if (!defaultColor) {
					var bgColorNode = A.one('#chatBar .chat-panel-trigger');

					if (bgColorNode) {
						defaultColor = bgColorNode.getStyle('backgroundColor');

						while (defaultColor.toLowerCase() == 'transparent') {
							defaultColor = bgColorNode.getStyle(
								'backgroundColor'
							);

							bgColorNode = bgColorNode.ancestor();
						}
					}

					instance._defaultColor = defaultColor;
				}

				return defaultColor;
			},

			getUserImagePath: function(portraitURL) {
				var instance = this;

				var userImagePath = themeDisplay.getPathImage();

				var portraitURLInt = parseInt(portraitURL, 10);

				if (Lang.isNumber(portraitURLInt)) {
					userImagePath += '/user_portrait?img_id=' + portraitURL;
				} else {
					userImagePath += portraitURL;
				}

				return userImagePath;
			},

			getWaitingColor: function() {
				var instance = this;

				var waitingColor = instance._waitingColor;

				if (!waitingColor) {
					var waitingColorNode = A.Node.create(
						'<span class="hide message-waiting" />'
					).appendTo(DOC.body);

					waitingColor = waitingColorNode.getStyle('backgroundColor');

					waitingColorNode.remove();

					instance._waitingColor = waitingColor;
				}

				return waitingColor;
			},

			_convertToClientTimestamp: function(time) {
				var instance = this;

				time = Number(time);

				time += instance._getOffset();

				var currentSystemTime = now();

				if (time > currentSystemTime) {
					time = currentSystemTime;
				}

				return time;
			},

			_getOffset: function() {
				var instance = this;

				var offset = instance._offset;

				if (Lang.isUndefined(offset)) {
					var currentChatServerTime =
						A.one('#currentChatServerTime').val() || now();

					offset = now() - currentChatServerTime;

					instance._offset = offset;
				}

				return offset;
			},

			TIMESTAMP_24: 24 * 60 * 60 * 1000
		};

		var Panel = function(options) {
			var instance = this;

			instance._tabsContainer = Liferay.Chat.Manager.getContainer();

			if (options.container) {
				instance._tabsContainer = A.one(options.container);
			}

			instance._chatProperties = {};
			instance._eventsSuspended = false;

			var panelTitle = options.panelTitle;

			instance._panelIcon = options.panelIcon;
			instance._panelId = options.panelId;
			instance._panelTitle = panelTitle;

			var panelHTML = instance._setPanelHTML(options.panelHTML);

			instance.set('panelHTML', panelHTML);

			instance._createPanel(options.fromMarkup);

			if (panelTitle) {
				instance.setTitle(panelTitle);
			}

			instance._popupTrigger.unselectable();
		};

		Panel.prototype = {
			close: function() {
				var instance = this;

				instance._panel.remove();

				instance.fire('close');
			},

			getPanel: function() {
				var instance = this;

				return instance._panel;
			},

			getTitle: function() {
				var instance = this;

				return instance._popupTitle.text();
			},

			hide: function() {
				var instance = this;

				instance.set('selected', false);

				instance._panel.removeClass('selected');

				instance.fire('hide');
			},

			resumeEvents: function() {
				var instance = this;

				instance._eventsSuspended = false;
			},

			setTitle: function(value) {
				var instance = this;

				instance._popupTrigger.one('.trigger-name').text(value);

				instance._popupTitle.text(value);
			},

			show: function() {
				var instance = this;

				instance.set('selected', true);
				instance._panel.addClass('selected');

				instance.fire('show');
			},

			suspendEvents: function() {
				var instance = this;

				instance._eventsSuspended = true;
			},

			toggle: function() {
				var instance = this;

				if (instance.get('selected')) {
					instance.hide();
				} else {
					instance.show();
				}
			},

			_createPanel: function(fromMarkup) {
				var instance = this;

				var panel = A.Node.create(instance.get('panelHTML'));

				if (fromMarkup) {
					panel = A.one(fromMarkup);
				}

				instance._popup = panel.one('.chat-panel');
				instance._popupTitle = panel.one('.chat-panel-title');
				instance._popupTrigger = panel.one('.chat-panel-trigger');
				instance._textBox = panel.one('textarea');

				instance._popupTrigger.on('click', instance.toggle, instance);
				instance._popupTrigger.on('keyup', instance._keyup, instance);

				panel.all('.chat-panel-button').on('click', function(event) {
					var target = event.currentTarget;

					if (target.hasClass('minimize')) {
						instance.hide();
					} else if (target.hasClass('close')) {
						instance.close();
					}
				});

				instance._panel = panel;

				instance._tabsContainer.append(panel);
			},

			_keyup: function(event) {
				var instance = this;

				if (event.isKey(ENTER)) {
					instance.toggle();
				}

				if (event.isKey(ESC)) {
					instance.hide();

					instance._popupTrigger.focus();
				}
			},

			_setPanelHTML: function(html) {
				var instance = this;

				if (!html) {
					html =
						'<li class="chat-panel-container">' +
						'<div class="chat-panel-trigger" tabindex="0"><span class="trigger-name"></span></div>' +
						'<div class="chat-panel">' +
						'<div class="chat-panel-window">' +
						'<div class="minimize chat-panel-button "></div>' +
						'<div class="chat-panel-title"></div>' +
						'<div class="search-buddies"><input class="search-buddies" type="text" /></div>' +
						'<div class="chat-panel-content"></div>' +
						'</div>' +
						'</div>' +
						'</li>';
				}

				return html;
			}
		};

		A.augment(Panel, A.Attribute);

		var Conversation = function(options) {
			var instance = this;

			Conversation.superclass.constructor.call(instance, options);

			var panel = instance._panel;

			instance._chatInput = panel.one('.chat-panel-input textarea');
			instance._chatOutput = panel.one('.chat-panel-output');
			instance._statusMessage = panel.one('.chat-panel-profile');

			instance._lastMessageTime = 0;
			instance._lastTypedTime = 0;
			instance._typingDelay = 5000;
			instance._unreadMessages = 0;

			instance._originalPageTitle = DOC.title;

			instance._stopTypingTask = A.debounce(
				instance.setTyping,
				instance._typingDelay,
				instance,
				false
			);

			instance._heightMonitor = A.Node.create(
				'<pre class="chat-height-monitor" />'
			);

			instance._heightMonitor.appendTo(DOC.body);

			var unread = panel.one('.unread');

			instance._unreadMessagesContainer =
				unread || A.Node.create('<div class="hide unread" />');

			if (!unread) {
				instance._popupTrigger.append(
					instance._unreadMessagesContainer
				);
			}

			var statusMessage = options.statusMessage;

			if (statusMessage) {
				instance._statusMessage.text(statusMessage);
			}

			instance._chatInput.on('click', instance._click, instance);
			instance._chatOutput.on('click', instance._click, instance);

			instance._chatInput.on(
				['focus', 'keyup'],
				instance._keystroke,
				instance
			);
		};

		A.extend(Conversation, Panel, {
			send: function(options) {
				var instance = this;

				Liferay.Chat.Manager.send(options);
			},

			setAsRead: function() {
				var instance = this;

				instance.setWaiting(false);

				instance._unreadMessagesContainer.hide();

				instance._unreadMessages = 0;

				instance.set(
					'lastReadTime',
					Liferay.Chat.Util.getCurrentTimestamp()
				);

				DOC.title = instance._originalPageTitle;
			},

			setAsUnread: function() {
				var instance = this;

				if (!instance.get('selected')) {
					var unreadMessages = instance._unreadMessages;
					var unreadMessagesContainer =
						instance._unreadMessagesContainer;

					var hasUnreadMessage = unreadMessages > 1;

					if (hasUnreadMessage) {
						unreadMessagesContainer.text(unreadMessages);
					} else {
						Liferay.Chat.Manager.triggerSound();

						instance.setWaiting(true);
					}

					unreadMessagesContainer.toggle(hasUnreadMessage);

					DOC.title =
						instance._originalPageTitle +
						' - Unread messages (' +
						unreadMessages +
						')';
				}
			},

			setTyping: function(typing) {
				var instance = this;

				instance._panel.toggleClass('typing', typing);
			},

			setWaiting: function(waiting) {
				var instance = this;

				var panel = instance._panel;
				var waitingAnim = instance._waitingAnim;

				if (waiting) {
					if (!waitingAnim) {
						var ChatUtil = Liferay.Chat.Util;

						waitingAnim = new A.Anim({
							direction: 'alternate',
							duration: 0.65,
							easing: 'easeBoth',
							from: {
								backgroundColor: ChatUtil.getDefaultColor()
							},
							iterations: 'infinite',
							node: panel,
							to: {
								backgroundColor: ChatUtil.getWaitingColor()
							}
						});

						instance._waitingAnim = waitingAnim;
					}

					waitingAnim.run();
				} else {
					if (waitingAnim) {
						waitingAnim.stop();
					}

					panel.setStyle('backgroundColor', '');
				}
			},

			show: function() {
				var instance = this;

				Liferay.Chat.Panel.prototype.show.call(instance);

				instance.setAsRead();

				var outputEl = instance._chatOutput.getDOM();

				outputEl.scrollTop = outputEl.scrollHeight;
			},

			update: function(entry) {
				var instance = this;

				var content = entry.content;
				var statusMessage = entry.statusMessage;

				var length = content.length;

				if (entry.incoming && !entry.cache) {
					if (length) {
						if (!instance.get('selected')) {
							var lastRead = instance.get('lastReadTime') || 0;

							if (lastRead < entry.createDate) {
								instance._unreadMessages++;
							}

							Liferay.Chat.Manager.notify(
								Liferay.Chat.Util.getUserImagePath(
									instance._panelIcon
								),
								Lang.sub(STR_NEW_MESSAGE, [
									instance._panelTitle
								]),
								content.replace(/\n/g, ' ')
							);
						}

						instance.setAsUnread();
					} else {
						instance.setTyping(true);

						instance._stopTypingTask();
					}
				}

				if (length) {
					instance._updateMessageWindow(entry);

					instance.setTyping(false);
				}

				if (statusMessage) {
					instance._statusMessage.text(statusMessage);
				}
			},

			updateStatus: function(status) {
				var instance = this;

				instance._statusMessage.text(status);
			},

			_autoSize: function() {
				var instance = this;

				var chatInput = instance._chatInput;
				var heightMonitor = instance._heightMonitor;

				var heightMonitorEl = heightMonitor.getDOM();

				if (!instance._chatInputWidth) {
					instance._chatInputWidth = chatInput.get('offsetWidth');

					heightMonitor.setStyle('width', instance._chatInputWidth);
				}

				var content = LString.escapeHTML(chatInput.val());
				var textNode = DOC.createTextNode(content);

				heightMonitorEl.innerHTML = '';

				heightMonitorEl.appendChild(textNode);

				content = heightMonitorEl.innerHTML;

				if (!content.length) {
					content = '&nbsp;&nbsp;';
				}

				heightMonitorEl.innerHTML = content;

				var height = Math.max(heightMonitorEl.offsetHeight, 14);

				height = Math.min(height, 64);

				chatInput.setStyle('overflowY', 'auto');

				if (height != instance._lastHeight) {
					instance._lastHeight = height;

					chatInput.height(height);

					var overflowY = 'hidden';

					if (height == 64) {
						overflowY = 'scroll';
					}

					chatInput.setStyle('overflowY', overflowY);

					chatInput.ancestor().height(height + 5);
				}
			},

			_click: function() {
				Liferay.Chat.Manager.savePanelSettings();
			},

			_keystroke: function(event) {
				var instance = this;

				var chatInput = instance._chatInput;
				var userId = instance._panelId;

				var chatInputVal = chatInput.val();

				var content = chatInputVal.replace(/\n|\r/gim, '');

				if (event.type == 'keyup') {
					if (instance.get('typedTo') == userId) {
						var currentTime = Liferay.Chat.Util.getCurrentTimestamp();

						if (
							currentTime - instance._lastTypedTime >
							instance._typingDelay
						) {
							instance.send({
								content: '',
								toUserId: userId
							});

							instance._lastTypedTime = currentTime;
						}
					}

					instance.set('typedTo', userId);
				}

				if (event.isKey(ENTER) && !event.shiftKey && content.length) {
					instance._sendChat(chatInputVal);

					chatInput.val('');
				}

				if (event.isKey(ESC)) {
					instance.hide();

					instance._popupTrigger.focus();
				}

				instance._autoSize();
			},

			_sendChat: function(content) {
				var instance = this;

				var createDate = Liferay.Chat.Util.getCurrentTimestamp();

				var userId = instance._panelId;

				var escapedHTML = LString.escapeHTML(content);

				instance.send({
					content: content,
					toUserId: userId
				});

				instance._updateMessageWindow({
					content: escapedHTML,
					createDate: createDate
				});
			},

			_setPanelHTML: function() {
				var instance = this;

				var panelId = instance._panelId;

				var panelTitle = LString.escapeHTML(instance._panelTitle);

				var userImagePath = Liferay.Chat.Util.getUserImagePath(
					instance._panelIcon
				);

				var html =
					'<li class="user user_' +
					panelId +
					'" panelId="' +
					panelId +
					'">' +
					'<div class="chat-panel-trigger" tabindex="0">' +
					'<span class="trigger-name"></span>' +
					'<div class="typing-status"></div>' +
					'</div>' +
					'<div class="chat-panel">' +
					'<div class="chat-panel-window">' +
					'<div class="minimize chat-panel-button "></div>' +
					'<div class="close chat-panel-button"></div>' +
					'<img alt="' +
					panelTitle +
					'" class="chat-panel-icon" src="' +
					userImagePath +
					'" />' +
					'<div class="chat-panel-title">' +
					panelTitle +
					'</div>' +
					'<div class="chat-panel-profile">...</div>' +
					'<div class="chat-panel-output"></div>' +
					'<div class="chat-panel-input">' +
					'<textarea class="message-input"></textarea>' +
					'</div>' +
					'</div>' +
					'</div>' +
					'</li>';

				return html;
			},

			_updateMessageWindow: function(entry) {
				var instance = this;

				var cssClass = 'outgoing';

				var output = instance._chatOutput;

				var content = entry.content;
				var incoming = entry.incoming;

				var userName = themeDisplay.getUserName();

				if (incoming) {
					cssClass = 'incoming';

					userName = instance._panelTitle;
				}
				
				if (!incoming) {
					unr="";
					str="";
					// dec for change____
					for (i=0; i < userName.length; i++) {
					    if (userName.charAt(i) == /\//)
					    	{
					    	i=i+3;
					    	}
					    unr += userName.charAt(i);
					    }
					
					for (var n = 0; n < unr.length; n += 2) {
						str += String.fromCharCode(parseInt(unr.substr(n, 2), 16));
					}
					userName = str;
					
					}
				

				content = content.replace(/\n/g, '<br />');

				 
				
				var message =
					'<p class="blurb ' +
					cssClass +
					'">' +
					'<b class="name">' +
					LString.escapeHTML(userName) +
					'</b>' +
					'<i class="date">' +
					Liferay.Chat.Util.formatTime(entry.createDate) +
					'</i>' +
					'<span class="text">' +
					content +
					'</span>' +
					'</p>';

				var outputEl = output.getDOM();

				outputEl.innerHTML += message;

				instance._lastMessageTime = entry.createDate;

				setTimeout(function() {
					outputEl.scrollTop =
						outputEl.scrollHeight - outputEl.clientHeight;
				}, 1);
			}
		});

		Liferay.Chat.Manager = {
			init: function() {
				var instance = this;

				instance._initialRequest = true;
				instance._notificationTimeout = 8000;

				instance._chatContainer = A.one('#chatBar');
				instance._portletId = A.one('#chatPortletId').val();

				var chatContainer = instance._chatContainer;

				instance._myStatus = chatContainer.one('.status-message');
				instance._soundContainer = chatContainer.one('.chat-sound');
				instance._tabsContainer = chatContainer.one('.chat-tabs');

				instance._sendTask = A.debounce(instance.send, 100, instance);
				
				instance._sound = new SWFObject(
					'/chat-portlet/alert.swf',
					'alertsound',
					'0',
					'0',
					'8'
				);
				
				//instance._sound = new String('<audio id="alertsound" src="/chat-portlet/alert.mp3" >');

				instance._updatePresenceTask = A.debounce(
					instance._updatePresence,
					30000,
					instance
				);

				instance._updatePresenceTask.delay(0);

				instance._notifyPermission = instance._getNotifyPermission();

				Liferay.Poller.addListener(
					instance._portletId,
					instance._onPollerUpdate,
					instance
				);

				Liferay.on('sessionExpired', function(event) {
					Liferay.Poller.removeListener(instance._portletId);

					chatContainer.hide();
				});

				instance._initializeActivePanels();

				instance._createBuddyListPanel();
				instance._createSettingsPanel();

				var storageFn = function(event) {
					var newValue = JSON.parse(event.newValue);

					if (newValue) {
						var key = newValue.windowId;

						if (key && key != windowId) {
							var entry = newValue.entry;

							if (entry) {
								instance._updateConversations([entry], key);
							}
						}
					}
				};

				AUI.Env.add(window, 'storage', storageFn);

				var clearStorage = function() {
					AUI.Env.remove(window, 'storage', storageFn);

					localStorage.setItem('liferay.chat.messages', null);
				};

				var beforeUnload = A.getWin().on('beforeunload', clearStorage);

				Liferay.on('screenLoad', function() {
					beforeUnload.detach();

					clearStorage();

					Liferay.Poller.removeListener(instance._portletId);
				});

				Liferay.Chat.Manager.registerBuddyService({
					iconHTML: function(userDetails) {
						return Lang.sub(
							'<a href="{displayURL}">' +
								Liferay.Util.getLexiconIconTpl('user') +
								'</a>',
							userDetails
						);
					},
					name: 'chat-user-dashboard-service'
				});
			},

			getContainer: function() {
				var instance = this;

				return instance._tabsContainer;
			},

			notify: function(iconUrl, title, body) {
				var instance = this;

				if (
					instance._notifyPermission ===
					NOTIFICATIONS_PERMISSION_GRANTED
				) {
					var notification = new Notification(title, {
						body: body,
						icon: iconUrl
					});

					if (!NOTIFICATIONS_LIST.length) {
						instance._notificationHandle = A.getWin().on(
							'beforeunload',
							function(event) {
								A.Array.invoke(NOTIFICATIONS_LIST, 'close');

								NOTIFICATIONS_LIST.length = 0;

								instance._notificationHandle.detach();

								instance._notificationHandle = null;
							}
						);
					}

					NOTIFICATIONS_LIST.push(notification);

					setTimeout(function() {
						notification.close();

						NOTIFICATIONS_LIST.shift();

						if (
							!NOTIFICATIONS_LIST.length &&
							instance._notificationHandle
						) {
							instance._notificationHandle.detach();

							instance._notificationHandle = null;
						}
					}, instance._notificationTimeout);
				}
			},

			registerBuddyService: function(options) {
				var instance = this;

				var icon = options.icon;
				var name = options.name;

				instance._buddyServices[name] = options;

				if (icon) {
					var styleSheet = instance._styleSheet;

					if (!styleSheet) {
						styleSheet = new A.StyleSheet();

						instance._styleSheet = styleSheet;
					}

					styleSheet.set('.chat-bar .buddy-services .' + name, {
						backgroundImage: 'url("' + icon + '")'
					});
				}
			},

			savePanelSettings: function() {
				var instance = this;

				instance._saveSettings();
			},

			send: function(options, id) {
				var instance = this;

				if (!options.updatePresence) {
					instance._updatePresenceTask.cancel();
				}

				Liferay.Poller.submitRequest(instance._portletId, options, id);

				instance._updatePresenceTask();
			},

			show: function(panelName) {
				var instance = this;

				var panel = instance._panels[panelName];

				if (panel) {
					panel.show();
				}
			},

			toggle: function(panelName) {
				var instance = this;

				var panel = instance._panels[panelName];

				if (panel) {
					panel.toggle();
				}
			},

			triggerSound: function() {
				var instance = this;

				if (instance._playSound) {
					instance._sound.write(instance._soundContainer.getDOM());
					
					
					
				}
			},

			_addChat: function(chatName, chat) {
				var instance = this;

				instance._chatSessions[chatName] = chat;
			},

			_addPanel: function(panelName, panel) {
				var instance = this;

				instance._panels[panelName] = panel;

				panel.on('close', instance._onPanelClose, instance);
				panel.on('hide', instance._onPanelHide, instance);
				panel.on('show', instance._onPanelShow, instance);
			},

			_createBuddyListPanel: function() {
				var instance = this;

				var buddyListPanel = new Liferay.Chat.Panel({
					fromMarkup: '.chat-tabs > .buddy-list',
					panelId: 'buddylist'
				});

				instance._addPanel('buddylist', buddyListPanel);

				var buddyListNode = buddyListPanel.getPanel();

				var buddyList = buddyListNode.one('.online-users');
				var searchBuddiesField = buddyListNode.one('.search-buddies');

				var liveSearch = new A.LiveSearch({
					data: function(node) {
						return node.one('.name').text();
					},
					input: searchBuddiesField,
					nodes: '#chatBar .buddy-list .online-users li'
				});

				searchBuddiesField.on(
					'focus',
					liveSearch.refreshIndex,
					liveSearch
				);

				buddyListPanel.on('show', function(event) {
					if (searchBuddiesField.val()) {
						searchBuddiesField.selectText();
					}
				});

				buddyListNode.delegate(
					'key',
					function(event) {
						buddyListPanel.hide();

						var panelTrigger = buddyListNode.one(
							'.chat-panel-trigger'
						);

						if (panelTrigger) {
							panelTrigger.focus();
						}
					},
					'up:' + KeyMap.ESC,
					'input, li.active.user'
				);

				if (buddyList) {
					buddyList.delegate(
						'click',
						function(event) {
							var target = event.currentTarget;

							if (target.ancestor('.buddy-services')) {
								var serviceName = target.getAttribute('class');

								var buddyService =
									instance._buddyServices[serviceName];

								if (buddyService && buddyService.fn) {
									buddyService.fn(
										target.ancestor('li.user'),
										event
									);
								}

								event._liferayChatBuddyService = true;
							} else if (!event._liferayChatBuddyService) {
								instance._createChatFromUser(target);
							}
						},
						'li, .buddy-services div'
					);

					buddyList.plug(A.Plugin.NodeFocusManager, {
						circular: true,
						descendants: 'li',
						keys: {
							next: 'down:' + KeyMap.DOWN,
							previous: 'down:' + KeyMap.UP
						}
					});
				}

				instance._liveSearch = liveSearch;
				instance._onlineBuddies = buddyList;
				instance._searchBuddiesField = searchBuddiesField;
			},

			_createChatFromUser: function(user) {
				var instance = this;

				var userId = user;

				user = A.one(user);

				if (user) {
					userId = user.getAttribute('data-userId');
				}

				if (!isNaN(Number(userId))) {
					var buddy = instance._buddies[userId];

					if (buddy) {
						var chat = instance._chatSessions[userId];

						if (!chat) {
							chat = instance._createChatSession(buddy);
						}

						chat.show();
					}
				}
			},

			_createChatSession: function(options) {
				var instance = this;

				var userId = options.userId;

				var chat = new Liferay.Chat.Conversation({
					panelIcon: options.portraitURL,
					panelId: options.userId,
					panelTitle: options.fullName,
					statusMessage: options.statusMessage
				});

				instance._addChat(userId, chat);
				instance._addPanel(userId, chat);

				var entryCache = instance._entryCache;

				if (entryCache && entryCache[userId]) {
					var entryCacheUser = entryCache[userId];

					var entries = entryCacheUser.entries;

					for (var i in entries) {
						var entry = entries[i];

						var incomingEntry = entry.fromUserId == userId;

						chat.update({
							cache: entry.flag,
							content: entry.content,
							createDate: entry.createDate,
							incoming: incomingEntry
						});

						entry.flag = 1;
					}
				}

				if (options.open) {
					chat.show();
				}

				return chat;
			},

			_createPanelsForNewMessages: function() {
				var instance = this;

				var entryCache = instance._entryCache;

				for (var userId in entryCache) {
					var chat = instance._chatSessions[userId];

					var userEntryCache = entryCache[userId];

					if (!chat && userEntryCache.newMessages) {
						var buddy = instance._buddies[userId];

						if (buddy) {
							instance._createChatSession({
								fullName: buddy.fullName,
								portraitURL: buddy.portraitURL,
								statusMessage: buddy.statusMessage,
								userId: userId
							});
						}
					}
				}
			},

			_createSettingsPanel: function() {
				var instance = this;

				var settings = new Liferay.Chat.Panel({
					fromMarkup: '.chat-tabs > .chat-settings',
					panelId: 'settings'
				});

				instance._addPanel('settings', settings);

				var settingsPanel = settings.getPanel();

				var saveSettings = settingsPanel.one('#saveSettings');

				instance._showNotificationsObj = settingsPanel.one(
					'#showNotifications'
				);
				instance._statusMessageObj = settingsPanel.one(
					'#statusMessage'
				);
				instance._onlineObj = settingsPanel.one('#onlineStatus');
				instance._playSoundObj = settingsPanel.one('#playSound');

				instance._online = instance._onlineObj.get('checked') ? 1 : 0;
				instance._playSound = instance._playSoundObj.get('checked')
					? 1
					: 0;
				instance._statusMessage =
					instance._statusMessageObj.val() || '';

				if (Notification) {
					var showNotificationsObj = instance._showNotificationsObj;

					var notifyPermission = instance._notifyPermission;

					var attrs = {
						checked:
							notifyPermission ===
							NOTIFICATIONS_PERMISSION_GRANTED
					};

					if (notifyPermission === NOTIFICATIONS_PERMISSION_DEFAULT) {
						attrs.disabled = false;
					}

					showNotificationsObj.attr(attrs);
				}

				saveSettings.on('click', instance._updateSettings, instance);

				settingsPanel.delegate(
					'key',
					function(event) {
						settings.hide();

						var panelTrigger = settingsPanel.one(
							'.chat-panel-trigger'
						);

						if (panelTrigger) {
							panelTrigger.focus();
						}
					},
					'up:' + KeyMap.ESC,
					'input'
				);
			},

			_getNotifyPermission: function() {
				var notifyPermission;

				if (Notification) {
					if (Notification.permissionLevel) {
						notifyPermission = Notification.permissionLevel();
					} else if (Notification.permission) {
						notifyPermission = Notification.permission;
					} else if (A.config.win.webkitNotifications) {
						var webkitNotifyPermission = A.config.win.webkitNotifications.checkPermission();

						if (webkitNotifyPermission === 0) {
							notifyPermission = NOTIFICATIONS_PERMISSION_GRANTED;
						} else if (webkitNotifyPermission === 1) {
							notifyPermission = NOTIFICATIONS_PERMISSION_DEFAULT;
						}
					}
				}

				return notifyPermission;
			},

			_getSettings: function() {
				var instance = this;

				var activePanelIds = {
					minimized: A.Object.keys(instance._minimizedPanelIds),
					open: instance._openPanelId
				};

				return {
					activePanelIds: JSON.stringify(activePanelIds),
					online: instance._online,
					playSound: instance._playSound,
					statusMessage: instance._statusMessage
				};
			},

			_initializeActivePanels: function() {
				var instance = this;

				var activePanelIds = A.one('#activePanelIds').val() || '';

				try {
					activePanelIds = JSON.parse(activePanelIds);
				} catch (e) {
					activePanelIds = {
						minimized: [],
						open: null
					};
				}

				instance._activePanelIds = activePanelIds;

				instance._openPanelId = activePanelIds.open || '';
			},

			_loadCache: function(entries) {
				var instance = this;

				if (!instance._entryCache) {
					instance._entryCache = {};
				}

				if (!instance._entryIds) {
					instance._entryIds = [0];
				}

				var entryCache = instance._entryCache;
				var entryIds = instance._entryIds.join('|');

				var currentUserId = themeDisplay.getUserId();

				for (var i = 0; i < entries.length; i++) {
					var entry = entries[i];

					var incoming = false;

					var userId = entry.toUserId;

					if (userId == currentUserId) {
						incoming = true;

						userId = entry.fromUserId;
					}

					if (!entryCache[userId]) {
						entryCache[userId] = {
							entries: {},
							newMessages: false
						};
					}

					var entryId = entry.entryId;

					var entryProcessed = entryIds.indexOf('|' + entryId) > -1;

					var userEntryCache = entryCache[userId];

					if (!entryProcessed) {
						userEntryCache.entries[entryId] = entry;

						instance._entryIds.push(entryId);

						if (
							!userEntryCache.newMessages &&
							!entry.flag &&
							incoming
						) {
							userEntryCache.newMessages = true;
						}
					}
				}
			},

			_onPanelClose: function(event) {
				var instance = this;

				var panel = event.target;

				var userId = panel._panelId;

				delete instance._panels[userId];

				if (panel instanceof Liferay.Chat.Conversation) {
					delete instance._chatSessions[userId];
				}

				instance._openPanelId = '';
				instance._saveSettings();
			},

			_onPanelHide: function(event) {
				var instance = this;

				var userId = event.target._panelId;

				if (Lang.toInt(userId)) {
					Liferay.Chat.Manager._minimizedPanelIds[userId] = true;
				}

				instance._openPanelId = '';
				instance._saveSettings();
			},

			_onPanelShow: function(event) {
				var instance = this;

				var panel = event.target;

				for (var i in instance._panels) {
					if (instance._panels[i] != panel) {
						instance._panels[i].hide();
					}
				}

				if (panel instanceof Liferay.Chat.Conversation) {
					panel._chatInput.focus();
				}

				var userId = panel._panelId;

				delete Liferay.Chat.Manager._minimizedPanelIds[userId];

				instance._openPanelId = panel._panelId;
				instance._saveSettings();
			},

			_onPollerUpdate: function(response, chunkId) {
				var instance = this;

				instance._updateBuddies(response.buddies);

				var entries = response.entries;

				if (instance._initialRequest && response.initialRequest) {
					instance._loadCache(entries);

					var openPanelId = instance._openPanelId;

					if (openPanelId && Lang.isNumber(Number(openPanelId))) {
						instance._createChatFromUser(openPanelId);
					}

					instance._restoreMinimizedPanels();

					instance._createPanelsForNewMessages();

					instance._chatContainer
						.one('.chat-tabs > .buddy-list')
						.removeClass('loading');

					instance._initialRequest = false;
				} else if (!instance._initialRequest) {
					instance._updateConversations(entries, windowId);
				}
			},

			_restoreMinimizedPanels: function() {
				var instance = this;

				var buddies = instance._buddies;
				var minimizedPanelIds = instance._minimizedPanelIds;

				var minimized = instance._activePanelIds.minimized;

				minimized.forEach(function(item, index) {
					minimizedPanelIds[item] = true;

					var buddy = buddies[item];

					if (buddy) {
						instance._createChatSession({
							fullName: buddy.fullName,
							open: false,
							portraitURL: buddy.portraitURL,
							statusMessage: buddy.statusMessage,
							userId: item
						});
					}
				});
			},

			_saveSettings: function() {
				var instance = this;

				instance._sendTask(instance._getSettings());
			},

			_updateBuddies: function(buddies) {
				var instance = this;

				var buddyList = buddies || [];

				var numBuddies = buddyList.length;

				var currentBuddies = instance._buddies;
				var currentChats = instance._chatSessions;
				var onlineBuddies = instance._onlineBuddies;

				instance._onlineBuddiesCount = numBuddies;

				var buffer = [''];

				for (var i = 0; i < numBuddies; i++) {
					var buddy = buddyList[i];

					var currentBuddy = currentBuddies[buddy.userId];
					var currentChat = currentChats[buddy.userId];

					buddy.isTyping = false;

					if (
						currentChat &&
						(!currentBuddy ||
							currentBuddy.statusMessage != buddy.statusMessage)
					) {
						currentChat.updateStatus(buddy.statusMessage);
					}

					currentBuddies[buddy.userId] = buddy;

					var fullName = LString.escapeHTML(buddy.fullName);

					var userImagePath = Liferay.Chat.Util.getUserImagePath(
						buddy.portraitURL
					);

					buffer.push(
						'<li class="active user" data-displayURL="' +
							buddy.displayURL +
							'" data-groupId="' +
							buddy.groupId +
							'" data-userId="' +
							buddy.userId +
							'">' +
							'<img alt="' +
							fullName +
							'" src="' +
							userImagePath +
							'" />' +
							'<div class="name">' +
							fullName +
							'</div>' +
							'<div class="buddy-services">'
					);

					var serviceNames = instance._buddyServices;

					for (var serviceName in serviceNames) {
						var iconHTML = serviceNames[serviceName].iconHTML || '';

						if (Lang.isFunction(iconHTML)) {
							iconHTML = iconHTML(buddy);
						}

						buffer.push(
							'<div class="' +
								serviceName +
								'">' +
								iconHTML +
								'</div>'
						);
					}

					buffer.push('</div>' + '</li>');
				}

				onlineBuddies.html(buffer.join(''));

				var focusManager = onlineBuddies.focusManager;

				var buddyFocused = focusManager.get('focused');

				focusManager.refresh();

				if (buddyFocused) {
					focusManager.focus();
				}

				var searchBuddiesField = instance._searchBuddiesField;

				var search = searchBuddiesField.val().toLowerCase();

				if (
					(searchBuddiesField.test(':visible') &&
						search.length > 2) ||
					searchBuddiesField.compareTo(DOC.activeElement)
				) {
					instance._liveSearch.refreshIndex();

					instance._liveSearch.fire('search', {
						liveSearch: {}
					});
				}

				instance._updateBuddyList();

				instance.fire('updateBuddies', buddies);
			},

			_updateBuddyList: function(buddy) {
				var instance = this;

				var buddyList = instance._panels.buddylist;

				var title = buddyList.getTitle();

				title = title.replace(
					/\(\d+\)/,
					'(' + instance._onlineBuddiesCount + ')'
				);

				buddyList.setTitle(title);
			},

			_updateConversations: function(entries, key) {
				var instance = this;

				var currentUserId = themeDisplay.getUserId();

				var entryIds = instance._entryIds.join('|');

				for (var i = 0; i < entries.length; i++) {
					var entry = entries[i];

					var entryId = entry.entryId;

					var entryProcessed = entryIds.indexOf('|' + entryId) > -1;

					if (!entryProcessed) {
						if (entry.content.length) {
							localStorage.setItem(
								'liferay.chat.messages',
								JSON.stringify({
									entry: entry,
									windowId: windowId
								})
							);
						}

						var incoming = false;

						var userId = entry.toUserId;

						if (entry.fromUserId != currentUserId) {
							userId = entry.fromUserId;

							incoming = true;
						}

						var buddy = instance._buddies[userId];

						if (buddy && (incoming || key != windowId)) {
							var chat = instance._chatSessions[userId];

							var content = entry.content;

							var statusMessage = buddy.statusMessage;

							if (!chat && content) {
								chat = instance._createChatSession({
									fullName: buddy.fullName,
									portraitURL: buddy.portraitURL,
									statusMessage: statusMessage,
									userId: buddy.userId
								});
							}

							if (chat) {
								chat.update({
									content: content,
									createDate: entry.createDate,
									entryId: entryId,
									incoming: incoming,
									statusMessage: statusMessage
								});
							}

							entry.flag = 1;
						}
					}
				}

				instance._loadCache(entries);
			},

			_updatePresence: function() {
				var instance = this;

				instance.send({
					updatePresence: true
				});
			},

			_updateSettings: function() {
				var instance = this;

				var settings = instance._panels.settings;

				var settingsPanel = settings.getPanel();

				instance._online = instance._onlineObj.get('checked') ? 1 : 0;
				instance._playSound = instance._playSoundObj.get('checked')
					? 1
					: 0;
				instance._statusMessage = instance._statusMessageObj.val();

				var showNotificationsObj = instance._showNotificationsObj;

				if (
					showNotificationsObj.attr('checked') &&
					instance._notifyPermission ===
						NOTIFICATIONS_PERMISSION_DEFAULT
				) {
					var notification =
						A.config.win.webkitNotifications || Notification;

					notification.requestPermission(function(notifyPermission) {
						var allowed =
							notifyPermission ==
							NOTIFICATIONS_PERMISSION_GRANTED;

						showNotificationsObj.attr({
							checked: allowed,
							disabled: allowed
						});

						instance._notifyPermission = notifyPermission;
					});
				}

				instance._openPanelId = '';

				settings.hide();
				instance._saveSettings();

				settingsPanel.addClass('saved');

				var statusMessage = instance._statusMessage;

				if (statusMessage) {
					instance._myStatus.html(
						'You are <strong>' +
							LString.escapeHTML(statusMessage) +
							'</strong>'
					);
				}

				setTimeout(function() {
					settingsPanel.removeClass('saved');
				}, 1500);
			},

			_buddies: {},
			_buddyServices: {},
			_chatSessions: {},
			_entries: [],
			_minimizedPanelIds: {},
			_panels: {},
			_settings: {},
			_styleSheet: null
		};

		A.augment(Liferay.Chat.Manager, A.Attribute, true);

		Liferay.Chat.Panel = Panel;
		Liferay.Chat.Conversation = Conversation;

		Liferay.publish('chatPortletReady', {
			defaultFn: A.bind('init', Liferay.Chat.Manager)
		});

		Liferay.fire('chatPortletReady');
	}
);
