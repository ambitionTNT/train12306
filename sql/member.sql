drop table if exists `member`;
create table  `member` (
    `id` bigint not null comment 'id',
    `mobile` varchar(11) comment '手机号',
    primary key (`id`),
    unique key `mobile_unique` (`mobile`)
)engine=innodb default charset=utf8m64 comment='会员';