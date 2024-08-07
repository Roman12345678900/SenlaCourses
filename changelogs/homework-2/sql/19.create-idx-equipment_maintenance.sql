CREATE INDEX idx_equipment_maintenance_equipment_id ON equipment_maintenance(equipment_id);

--rollback drop index if exists idx_equipment_maintenance_equipment_id;