CREATE INDEX idx_roles_users_user_id ON roles_users(user_id);
CREATE INDEX idx_roles_users_role_id ON roles_users(role_id);

--rollback drop index if exists idx_roles_users_user_id;
--rollback drop index if exists idx_roles_users_role_id;