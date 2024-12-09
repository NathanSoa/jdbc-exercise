-- Create the person table
CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    birthdate DATE NOT NULL
);

-- Create the task table
CREATE TABLE task (
  id SERIAL PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  description TEXT,
  assignee_id INTEGER REFERENCES person(id),
  reporter_id INTEGER REFERENCES person(id),
  status VARCHAR(20) NOT NULL
);