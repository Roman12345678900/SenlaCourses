CREATE INDEX idx_reviews_class_id ON reviews(class_id);
CREATE INDEX idx_reviews_user_id ON reviews(user_id);

--rollback drop index if exists idx_reviews_class_id;
--rollback drop index if exists idx_reviews_user_id;