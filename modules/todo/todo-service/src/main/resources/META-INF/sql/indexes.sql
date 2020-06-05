create index IX_3913BE9E on TDL_TodoEntry (assigneeUserId, status);
create index IX_A48C470A on TDL_TodoEntry (groupId, assigneeUserId, status);
create index IX_DAB3F35B on TDL_TodoEntry (groupId, resolverUserId);
create index IX_CDD4001B on TDL_TodoEntry (groupId, userId, status);
create index IX_3D263EEF on TDL_TodoEntry (resolverUserId);
create index IX_30464BAF on TDL_TodoEntry (userId, status);

create index IX_2E9208BC on TDL_TodoEntry (assigneeUserId, status);
create index IX_D81C7EAC on TDL_TodoEntry (groupId, assigneeUserId, status);
create index IX_94FEECFD on TDL_TodoEntry (groupId, resolverUserId);
create index IX_881EF9BD on TDL_TodoEntry (groupId, userId, status);
create index IX_6467870D on TDL_TodoEntry (resolverUserId);
create index IX_578793CD on TDL_TodoEntry (userId, status);