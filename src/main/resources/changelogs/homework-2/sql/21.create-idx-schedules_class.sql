CREATE INDEX idx_schedules_class_id ON schedules(class_id);
CREATE INDEX idx_schedules_schedule_id ON schedules(schedule_id);

--rollback drop index if exists idx_schedules_class_id;
--rollback drop index if exists idx_schedules_schedule_id;