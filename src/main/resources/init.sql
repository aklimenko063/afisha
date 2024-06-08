CREATE SCHEMA IF NOT EXISTS application;

CREATE TABLE IF NOT EXISTS application.event_type (id serial primary key, name varchar(100) NOT NULL);

CREATE TABLE IF NOT EXISTS application.place (id serial primary key,
								name varchar(100) NOT NULL, 
								address varchar(100) NOT NULL, 
								city varchar(100) NOT NULL);

CREATE TABLE IF NOT EXISTS application.event (id serial primary key,
					name varchar(100) NOT NULL, 
					event_type_id int REFERENCES application.event_type(id) NOT NULL, 
					event_date timestamp NOT NULL, 
					place_id int REFERENCES application.place(id) NOT NULL);
					
CREATE TABLE IF NOT EXISTS application.ticket (id serial primary key,
					event_id int REFERENCES application.event(id) NOT NULL, 
					client_email varchar(100),
					price numeric(15,2),
					is_selled bool default FALSE);
					
INSERT INTO application.event_type (name) values
('museum'), ('cinema'), ('theather');

CREATE OR REPLACE VIEW application.report_view AS
(select
   e.name as event_name,
   et.name as event_type_name,
   COALESCE(SUM(CASE WHEN t.is_selled THEN 1 ELSE 0 END), 0) as sold_tickets_count,
   COALESCE(SUM(CASE WHEN t.is_selled THEN t.price ELSE 0 END), 0) as sold_tickets_total_cost
from
   application.event e
   join application.event_type et ON e.event_type_id = et.id
   left join application.ticket t ON e.id = t.event_id
group by e.name, et.name
order by e.name);