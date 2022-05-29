-- auto-generated definition
create table ln_logistic_items
(
    id              bigint auto_increment comment 'ID'
        primary key,
    logistics_id    bigint       default 0                 null comment '收货快递ID',
    apartment_id    varchar(30)  default ''                null comment '楼栋ID',
    unit_id         varchar(30)  default ''                null comment '单元',
    room_id         bigint       default 0                 null comment '房间ID',
    img             varchar(300) default ''                null comment '物品面单图片',
    status          char         default '0'               null comment '状态（0送达 1待送）',
    courier_company varchar(30)  default ''                null comment '快递公司',
    courier_number  varchar(30)  default ''                null comment '快递单号',
    create_by       varchar(64)  default ''                null comment '创建者',
    create_time     datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by       varchar(64)  default ''                null comment '更新者',
    update_time     datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    remark          varchar(500) default ''                null comment '备注'
)
    comment '快件信息表';

create index itemidx
    on ln_logistic_items (apartment_id, room_id, status, create_time);

