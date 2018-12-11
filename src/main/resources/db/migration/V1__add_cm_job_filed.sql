alter table cm_job add JOB_TYPE varchar(128) comment 'JOB类型' DEFAULT 'kettle';
alter table cm_job add FOLDER_NAME varchar(128) comment '文件夹名称';
alter table cm_job_chlog add JOB_TYPE varchar(128) comment 'JOB类型' DEFAULT 'kettle';
alter table cm_job_chlog add FOLDER_NAME varchar(128) comment '文件夹名称';