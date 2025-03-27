--liquibase formatted sql
-- changeset author:ddl-script-v2

ALTER TABLE notifications RENAME TO notification;
