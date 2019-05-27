drop table if exists timer_task;

/*==============================================================*/
/* Table: timer_task                                            */
/*==============================================================*/
CREATE TABLE `timer_task` (
  `ID` varchar(36) DEFAULT NULL COMMENT '主键',
  `TASK_NAME` varchar(64) DEFAULT NULL COMMENT '任务名称',
  `TASK_TYPE` varchar(10) DEFAULT NULL COMMENT '任务类型',
  `TASK_VAL` text COMMENT '任务标识',
  `TASK_FUNCTION` text COMMENT '任务执行方法名',
  `TASK_PARAM` text COMMENT '任务执行参数',
  `SCF_NAMESPACE` varchar(64) DEFAULT NULL COMMENT '云函数命名空间',
  `SCF_NAME` varchar(64) DEFAULT NULL COMMENT '云函数名称',
  `TRIGGER_NAME` varchar(36) DEFAULT NULL COMMENT '触发器id',
  `IS_ASYNC` varchar(255) DEFAULT NULL COMMENT '是否异步 0否 1是',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `OPERATOR` varchar(36) DEFAULT NULL COMMENT '操作人',
  `OPERATION_TIME` datetime DEFAULT NULL COMMENT '操作时间',
  `EXT1` varchar(36) DEFAULT NULL COMMENT '扩展字段',
  `EXT2` varchar(36) DEFAULT NULL COMMENT '扩展字段',
  `EXT3` varchar(36) DEFAULT NULL COMMENT '扩展字段',
  `EXT4` varchar(36) DEFAULT NULL COMMENT '扩展字段'
)
