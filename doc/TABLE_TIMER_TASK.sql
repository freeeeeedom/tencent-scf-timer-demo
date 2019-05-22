drop table if exists timer_task;

/*==============================================================*/
/* Table: timer_task                                            */
/*==============================================================*/
create table timer_task
(
   ID                   varchar(36) not null comment '主键',
   TASK_NAME           varchar(64) comment '任务名称',
   TASK_TYPE            varchar(10) comment '任务类型',
   TASK_VAL             varchar(200) comment '任务标识',
   TASK_FUNCTION        varchar(100) comment '任务执行方法',
   TASK_PARAM           text comment '任务执行参数',
   SCF_NAMESPACE        varchar(64) comment '云函数命名空间',
   SCF_NAME             varchar(64) comment '云函数名称',
   TRIGGER_NAME         varchar(36) comment '触发器id',
   CREATE_TIME          datetime comment '创建时间',
   OPERATOR             varchar(36) comment '操作人',
   OPERATION_TIME       datetime comment '操作时间',
   EXT1                 varchar(36) comment '扩展字段',
   EXT2                 varchar(36) comment '扩展字段',
   EXT3                 varchar(36) comment '扩展字段',
   EXT4                 varchar(36) comment '扩展字段',
   primary key (ID)
);