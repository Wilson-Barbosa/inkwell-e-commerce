-- When spring runs it will look on this file and run the sql for h2 database.
-- These scripts must be only ran on test-profile and only for the h2 database.
-- I'm running each script on a separate file, because there are lots of tables
-- so it's easier to track and debbug the app's initial state.

-- The h2 schema will be generated automatically using the jpa entities created.

RUNSCRIPT FROM 'src/main/resources/scripts/test_1_add_roles.sql';
RUNSCRIPT FROM 'src/main/resources/scripts/test_2_add_customers.sql';
RUNSCRIPT FROM 'src/main/resources/scripts/test_3_add_admins.sql';
