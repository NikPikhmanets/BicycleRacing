INSERT INTO users(username, first_name, last_name, password, enabled)
values ('admin', 'admin first', 'admin last', '$2a$10$xyxQC/hJMr7NdU4/AHyFouoNofrYglWWCAtx4Vt5DS.Q3kY/or69C', true);

INSERT INTO user_roles(user_id, role)
values (1, 'ROLE_ADMIN');

INSERT INTO users(username, first_name, last_name, password, enabled)
values ('user', 'user first last', '', '$2a$10$xyxQC/hJMr7NdU4/AHyFouoNofrYglWWCAtx4Vt5DS.Q3kY/or69C', true);
-- pass for test 123

INSERT INTO user_roles(user_id, role)
values (2, 'ROLE_USER');