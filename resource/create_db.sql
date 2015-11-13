create table bee_tasks(
  id int not null PRIMARY KEY AUTO_INCREMENT,
  title varchar(500) not null,
  content varchar(500) null,
  project_id int not null,
  creator_id int not null,
  assignee_id int null,
  spendtime int not null,
  status int not null,
  start_time varchar(20) null,
  stop_time varchar(20) null,
  create_time varchar(20) not null
);

create table bee_milestones(
  id int not null PRIMARY  key AUTO_INCREMENT,
  title varchar(500) not null,
  content varchar(500) null,
  project_id int not null,
  creator_id int not null,
  status int not null
);

create table bee_users(
  id int not null PRIMARY key AUTO_INCREMENT,
  username varchar(20) not null,
  password varchar(10) not null,
  group int not null
);

create table bee_projects(
  id int not null PRIMARY key AUTO_INCREMENT,
  projectname varchar(50) not null,
  content varchar(500) null,
  create_time varchar(20) not null,
  status int not null
);

create table bee_logs(
  id int not null PRIMARY key AUTO_INCREMENT,
  user_id int not null,
  log varchar(1000)
);
