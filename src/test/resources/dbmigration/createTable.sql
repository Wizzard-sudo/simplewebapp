CREATE TABLE employee (
  employee_id SERIAL,
  first_name varchar(20) NOT NULL,
  last_name varchar(20) NOT NULL,
  departament_id INT NOT NULL,
  job_title varchar(20) NOT NULL,
  gender varchar(20) NOT NULL,
  date_of_birth DATE NOT NULL,
  PRIMARY KEY (employee_id)
);
