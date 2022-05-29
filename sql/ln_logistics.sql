-- auto-generated definition
create table ln_logistics
(
    id           bigint auto_increment comment 'ID'
        primary key,
    apartment_id varchar(30)  default ''                null comment '楼栋ID',
    unit_id      varchar(30)  default ''                null comment '单元',
    room_id      bigint       default 0                 null comment '房间ID',
    room_img     varchar(300) default ''                null comment '门牌图片',
    status       char         default '0'               null comment '状态（0送达 1待送）',
    user_name    varchar(30)  default ''                null comment '租户名',
    user_phone   varchar(30)  default ''                null comment '租户手机',
    create_by    varchar(64)  default ''                null comment '创建者',
    create_time  datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by    varchar(64)  default ''                null comment '更新者',
    update_time  datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    remark       varchar(500) default ''                null comment '备注'
)
    comment '物流信息表';

create index logisticsidx
    on ln_logistics (apartment_id, room_id, status, create_by, create_time, update_by, update_time);

