CREATE INDEX idx_payments_user_id ON payments(user_id);

--rollback drop index if exists idx_payments_user_id;