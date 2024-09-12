CREATE INDEX idx_user_profiles_user_id ON profiles(user_id);

--rollback drop index if exists idx_user_profiles_user_id;