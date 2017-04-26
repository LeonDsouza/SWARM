/******************************************************************
      1) create placementdb
      2) create 7 schemas under that:
          loader_schema
          user_schema
          job_schema
          role_function_schema
          event_schema
          placement_statistics_schema
          room_allotment

      3) execute the the scripts below

      4) under loader_schema:
          loader

      5) under user_schema:
          userdetails, personal_profile, professional_profile, qualification,
          professional_profile_resume, professional_profile_job

      6) under job_schema:
          criteria, company, job, company_job, feedback, applicant_company

      7) under role_function_schema
        role_master, function_master, role_function_map, ftpc_special_role

      8) under event_schema:
          event_details, aptitude_test, pre_placement_talk_or_other, interview

      9) under placement_statistics_schema
          placement_statistics, quick_stats

     10) under room_allotment
          allotment

*************************************************************************/


CREATE TABLE loader_schema.loader
(
  roll_no text NOT NULL,
  name text,
  branch text,
  gender text,
  dob text,
  mobile text,
  email text,
  ssc_per text,
  hsc_or_dip text,
  hsc_or_dip_per text,
  s1_mark text,
  s1_max text,
  s1_per text,
  s1_res text,
  s1_kt text,
  s2_mark text,
  s2_max text,
  s2_per text,
  s2_res text,
  s2_kt text,
  s3_mark text,
  s3_max text,
  s3_per text,
  s3_res text,
  s3_kt text,
  s4_mark text,
  s4_max text,
  s4_per text,
  s4_res text,
  s4_kt text,
  s5_mark text,
  s5_max text,
  s5_per text,
  s5_res text,
  s5_kt text,
  s6_mark text,
  s6_max text,
  s6_per text,
  s6_res text,
  s6_kt text,
  s7_mark text,
  s7_max text,
  s7_per text,
  s7_res text,
  s7_kt text,
  s8_mark text,
  s8_max text,
  s8_per text,
  s8_res text,
  s8_kt text,
  deg_per text,
  deg_kt text,
  drops text,
  CONSTRAINT loader_pkey PRIMARY KEY (roll_no)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE loader_schema.loader
  OWNER TO postgres;





CREATE TABLE user_schema.userdetails
(
  username text NOT NULL,
  user_password text DEFAULT 'password'::text,
  account_active text,
  current_state text,
  last_login date,
  created_date text,
  created_by text,
  modified_by text,
  modified_date date,
  role_id text,
  notified boolean DEFAULT false,
    CONSTRAINT "UserDetails_pkey" PRIMARY KEY (username),
  CONSTRAINT userdetails_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES role_function_schema.role_master (role_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_schema.userdetails
  OWNER TO postgres;





CREATE TABLE user_schema.personal_profile
(
  username text NOT NULL,
  name text,
  gender text,
  dob text,
  mobile_no text,
  email_id text,
  category text,
  created_date date,
  created_by text,
  modified_date date,
  modified_by text,
  correspondence_address text,
  permanent_address text,
  family_income text,
  CONSTRAINT personal_profile_pkey PRIMARY KEY (username),
  CONSTRAINT fk16399ae32179c1cc FOREIGN KEY (username)
      REFERENCES user_schema.userdetails (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk16399ae35066d9f5 FOREIGN KEY (username)
      REFERENCES user_schema.userdetails (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT personal_profile_username_fkey FOREIGN KEY (username)
      REFERENCES user_schema.userdetails (username) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_schema.personal_profile
  OWNER TO postgres;




CREATE TABLE user_schema.professional_profile
(
  username text NOT NULL,
  branch text,
  status text,
  field_of_interest text,
  achievements text,
  projects text,
  internships text,
  co_curricular_activities text,
  extra_curricular_activities text,
  created_date date,
  created_by text,
  modified_date date,
  modified_by text,
  year text,
  work_experience text,
  CONSTRAINT professional_profile_pkey PRIMARY KEY (username),
  CONSTRAINT fked12774a2179c1cc FOREIGN KEY (username)
      REFERENCES user_schema.userdetails (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fked12774a5066d9f5 FOREIGN KEY (username)
      REFERENCES user_schema.userdetails (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT professional_profile_username_fkey FOREIGN KEY (username)
      REFERENCES user_schema.userdetails (username) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_schema.professional_profile
  OWNER TO postgres;



CREATE TABLE user_schema.qualification
(
  username text NOT NULL,
  ssc_per text,
  hsc_or_dip text,
  hsc_or_dip_per text,
  s1_mark text,
  s1_max text,
  s1_per text,
  s1_res text,
  s1_kt text,
  s2_mark text,
  s2_max text,
  s2_per text,
  s2_res text,
  s2_kt text,
  s3_mark text,
  s3_max text,
  s3_per text,
  s3_res text,
  s3_kt text,
  s4_mark text,
  s4_max text,
  s4_per text,
  s4_res text,
  s4_kt text,
  s5_mark text,
  s5_max text,
  s5_per text,
  s5_res text,
  s5_kt text,
  s6_mark text DEFAULT 0,
  s6_max text DEFAULT 0,
  s6_per text DEFAULT 0,
  s6_res text DEFAULT 0,
  s6_kt text DEFAULT 0,
  s7_mark text DEFAULT 0,
  s7_max text DEFAULT 0,
  s7_per text DEFAULT 0,
  s7_res text DEFAULT 0,
  s7_kt text DEFAULT 0,
  s8_mark text DEFAULT 0,
  s8_max text DEFAULT 0,
  s8_per text DEFAULT 0,
  s8_res text DEFAULT 0,
  s8_kt text DEFAULT 0,
  deg_per text,
  deg_kt text,
  drops text,
  created_date date,
  created_by text,
  modified_date date,
  modified_by text,
  CONSTRAINT qualification_pkey PRIMARY KEY (username),
  CONSTRAINT fk44ff2c562179c1cc FOREIGN KEY (username)
      REFERENCES user_schema.userdetails (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_schema.qualification
  OWNER TO postgres;



CREATE TABLE user_schema.professional_profile_job
(
  username text NOT NULL,
  job_id text NOT NULL,
  CONSTRAINT professional_profile_job_pkey PRIMARY KEY (username, job_id),
  CONSTRAINT professional_profile_job_job_id_fkey FOREIGN KEY (job_id)
      REFERENCES job_schema.job (job_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT professional_profile_job_username_fkey FOREIGN KEY (username)
      REFERENCES user_schema.professional_profile (username) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_schema.professional_profile_job
  OWNER TO postgres;



CREATE TABLE job_schema.company
(
  company_name text,
  company_address text,
  created_date date,
  created_by text,
  modified_date date,
  modified_by text,
  company_id integer NOT NULL,
  criteria_id integer,
  CONSTRAINT company_pkey PRIMARY KEY (company_id),
  CONSTRAINT company_criteria_id_fkey FOREIGN KEY (criteria_id)
      REFERENCES job_schema.criteria (criteria_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT myuniqueconstraint UNIQUE (company_name)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE job_schema.company
  OWNER TO postgres;





CREATE TABLE job_schema.job
(
  job_id text NOT NULL,
  event_id text,
  job_description text,
  job_category text,
  ctc text,
  skills_required text,
  docs_required text,
  drive_date date,
  created_date date,
  created_by text,
  modified_date date,
  modified_by text,
  CONSTRAINT job_pkey PRIMARY KEY (job_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE job_schema.job
  OWNER TO postgres;




CREATE TABLE job_schema.criteria
(
  eligible_branches text,
  year_of_passing text,
  placed_students_allowed text,
  percentage text,
  cgpa text,
  no_of_live_kts_allowed text,
  no_of_dead_kts_allowed text,
  year_gap_allowed text,
  ssc_percentage text,
  hsc_or_dip_percentage text,
  criteria_id integer NOT NULL,
  last_date_to_apply date,
  CONSTRAINT criteria_pkey PRIMARY KEY (criteria_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE job_schema.criteria
  OWNER TO postgres;






CREATE TABLE job_schema.company_job
(
  job_id text NOT NULL,
  company_id integer NOT NULL,
  CONSTRAINT company_job_pkey PRIMARY KEY (job_id, company_id),
  CONSTRAINT company_job_company_id_fkey FOREIGN KEY (company_id)
      REFERENCES job_schema.company (company_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT company_job_job_id_fkey FOREIGN KEY (job_id)
      REFERENCES job_schema.job (job_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE job_schema.company_job
  OWNER TO postgres;





CREATE TABLE role_function_schema.role_master
(
  role_id text NOT NULL,
  role_name text,
  created_by text,
  created_date date,
  modified_by text,
  modified_date date,
  CONSTRAINT role_master_pkey PRIMARY KEY (role_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE role_function_schema.role_master
  OWNER TO postgres;




CREATE TABLE role_function_schema.function_master
(
  function_id text NOT NULL,
  function_name text,
  function_url text,
  created_by text,
  created_date date,
  modified_by text,
  modified_date date,
  CONSTRAINT function_master_pkey PRIMARY KEY (function_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE role_function_schema.function_master
  OWNER TO postgres;



CREATE TABLE role_function_schema.role_function_map
(
  role_id text NOT NULL,
  function_id text NOT NULL,
  CONSTRAINT role_function_map_pkey PRIMARY KEY (role_id, function_id),
  CONSTRAINT role_function_map_function_id_fkey FOREIGN KEY (function_id)
      REFERENCES role_function_schema.function_master (function_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT role_function_map_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES role_function_schema.role_master (role_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE role_function_schema.role_function_map
  OWNER TO postgres;








CREATE TABLE user_schema.professional_profile_resume
(
  username text NOT NULL,
  resume_path text NOT NULL,
  CONSTRAINT professional_profile_resume_pkey PRIMARY KEY (username, resume_path),
  CONSTRAINT professional_profile_resume_username_fkey FOREIGN KEY (username)
      REFERENCES user_schema.personal_profile (username) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_schema.professional_profile_resume
  OWNER TO postgres;



CREATE TABLE job_schema.feedback
(
  username text NOT NULL,
  feedback text,
  company_name text NOT NULL,
created_by text,
  created_date date,
  modified_by text,
  modified_date date,
  
  CONSTRAINT feedback_pkey PRIMARY KEY (username, company_name),
  CONSTRAINT feedback_company_name_fkey FOREIGN KEY (company_name)
      REFERENCES job_schema.company (company_name) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT feedback_username_fkey FOREIGN KEY (username)
      REFERENCES user_schema.userdetails (username) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE job_schema.feedback
  OWNER TO postgres;




CREATE TABLE role_function_schema.ftpc_special_role
(
  username text NOT NULL,
  special_role text NOT NULL,
  CONSTRAINT ftpc_special_role_pkey PRIMARY KEY (username, special_role),
  CONSTRAINT ftpc_special_role_username_fkey FOREIGN KEY (username)
      REFERENCES user_schema.userdetails (username) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE role_function_schema.ftpc_special_role
  OWNER TO postgres;



CREATE TABLE event_schema.event_details
(
  event_id text NOT NULL,
  event_type text,
  company_id integer,
  approved text,
  date date,
  time time without time zone,
  venue text,
  created_by text,
  created_date date,
  modified_by text,
  modified_date date,
  CONSTRAINT event_details_pkey PRIMARY KEY (event_id),
  CONSTRAINT event_details_company_id_fkey FOREIGN KEY (company_id)
      REFERENCES job_schema.company (company_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE event_schema.event_details
  OWNER TO postgres;




CREATE TABLE event_schema.aptitude_test
(
  event_id text NOT NULL,
  test_duration text,
  subjects_to_be_prepared text,
  test_sections text,
  CONSTRAINT aptitude_test_pkey PRIMARY KEY (event_id),
  CONSTRAINT aptitude_test_event_id_fkey FOREIGN KEY (event_id)
      REFERENCES event_schema.event_details (event_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE event_schema.aptitude_test
  OWNER TO postgres;




CREATE TABLE event_schema.pre_placement_talk_or_other
(
  event_id text NOT NULL,
  conducted_by text,
  agenda text,
  CONSTRAINT pre_placement_talk_pkey PRIMARY KEY (event_id),
  CONSTRAINT pre_placement_talk_event_id_fkey FOREIGN KEY (event_id)
      REFERENCES event_schema.event_details (event_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE event_schema.pre_placement_talk_or_other
  OWNER TO postgres;



CREATE TABLE event_schema.interview
(
  event_id text NOT NULL,
  interview_type text,
  CONSTRAINT interview_pkey PRIMARY KEY (event_id),
  CONSTRAINT interview_event_id_fkey FOREIGN KEY (event_id)
      REFERENCES event_schema.event_details (event_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE event_schema.interview
  OWNER TO postgres;





CREATE TABLE placement_statistics_schema.quick_stats
(
  username text NOT NULL,
  branch text NOT NULL,
  company_id integer NOT NULL,
  CONSTRAINT quick_stats_pkey PRIMARY KEY (username, company_id),
  CONSTRAINT quick_stats_company_id_fkey FOREIGN KEY (company_id)
      REFERENCES job_schema.company (company_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT quick_stats_username_fkey FOREIGN KEY (username)
      REFERENCES user_schema.professional_profile (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE placement_statistics_schema.quick_stats
  OWNER TO postgres;







CREATE TABLE placement_statistics_schema.placement_statistics
(
  placment_stats_id text NOT NULL,
  company_id integer NOT NULL,
  year text,
  branch text,
  no_applied integer,
  total_no_of_students integer,
  no_selected integer,  
  no_joined integer,  
  no_dual_placed integer,
  created_by text,
  created_date date,
  modified_by text,
  modified_date date,
  CONSTRAINT placement_statistics_pkey PRIMARY KEY (placment_stats_id, company_id),
  CONSTRAINT placement_statistics_company_id_fkey FOREIGN KEY (company_id)
  REFERENCES job_schema.company (company_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE placement_statistics_schema.placement_statistics
  OWNER TO postgres;
 
 
 
 
 
 
 







CREATE TABLE room_allotment.allotment
(
  allotment_id integer NOT NULL,
  company_name text NOT NULL,
  round_no text,
  room_no text,
  job_description text,
  drive_date date,
  room_path text,
  CONSTRAINT allotment_pkey PRIMARY KEY (allotment_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE room_allotment.allotment
  OWNER TO postgres;





CREATE TABLE job_schema.applicant_company
(
  username text NOT NULL,
  company_id integer NOT NULL,
  company_name text NOT NULL,
  CONSTRAINT user_company_pkey PRIMARY KEY (username, company_id),
  CONSTRAINT user_company_company_id_fkey FOREIGN KEY (company_id)
      REFERENCES job_schema.company (company_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT user_company_username_fkey FOREIGN KEY (username)
      REFERENCES user_schema.userdetails (username) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE job_schema.applicant_company
  OWNER TO postgres;



