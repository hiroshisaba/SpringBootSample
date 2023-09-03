CREATE DATABASE spring_sample;

\c spring_sample

CREATE SCHEMA dev;

CREATE USER developer WITH PASSWORD 'dke@keig24' CREATEDB;

GRANT ALL PRIVILEGES ON SCHEMA dev TO developer;