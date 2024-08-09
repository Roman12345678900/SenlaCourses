CREATE INDEX idx_trainer_schedules_user_id ON trainer_schedules(user_id);

--rollback drop index if exists idx_trainer_schedules_user_id;
