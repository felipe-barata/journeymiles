ALTER TABLE destinations ADD COLUMN meta VARCHAR(160) NOT NULL;
ALTER TABLE destinations ADD COLUMN description TEXT DEFAULT NULL;
