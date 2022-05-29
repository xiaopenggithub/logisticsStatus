<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">物流状态流转小程序 v1.0.0</h1>


## 简介

1. 关于配送小程序
> 主要特征：状态流转。
> 涉及到类似特征的场景都可以改进、扩展相关功能

**比如**
- 订单：新建待付款、已经付款待配送、配送中、收货、评价
- 审批：新提交待审批、上一级审批...最终审批

使用演示：https://www.bilibili.com/video/BV11A4y1f7gV/  
部署演示：https://www.bilibili.com/video/BV1NY4y1z7sp/

## 部署说明
> 源码地址：https://github.com/xiaopenggithub/logisticsStatus

基于Docker构建镜像，上传阿里云镜像仓库，Docker-compose运行
环境准备：需要提交安装Docker、Docker-compose、Mysql5.7、Redis
1. 前端
> /logisticsStatus/logisticsApp/Dockerfile   
```
# 构建镜像
docker build -t registry.cn-shanghai.aliyuncs.com/publicimg/lnjyadminh5:v1.0.0 .
# 上传镜像
docker push registry.cn-shanghai.aliyuncs.com/publicimg/lnjyadminh5:v1.0.0
``` 
2. 后台
> /logisticsStatus/deploy/dockerfile/front/Dockerfile   
```
# 复制编译好的文件到相应地方
mv ../../../ruoyi-ui/dist .
# 构建镜像
docker build -t registry.cn-shanghai.aliyuncs.com/publicimg/lnjyadminweb:v1.0.0 .
# 上传镜像
docker push registry.cn-shanghai.aliyuncs.com/publicimg/lnjyadminweb:v1.0.0
```   
3. 接口
> /logisticsStatus/deploy/dockerfile/api/Dockerfile   
```
# 打包
mvn package -Dmaven.test.skip=true
# 移动到相应位置
mv ../../../ruoyi-admin/target/ruoyi-admin.jar .
# 构建镜像
docker build -t registry.cn-shanghai.aliyuncs.com/publicimg/lnjyadminapi:v1.0.0 .
# 上传镜像
docker push registry.cn-shanghai.aliyuncs.com/publicimg/lnjyadminapi:v1.0.0
```

4. 运行
> /logisticsStatus/deploy/docker-compose.yaml

```
version: "3.5"

networks:
  network:
    ipam:
      driver: default
      config:
        - subnet: '177.217.0.0/16'

services:
  lnjyweb:
    image: registry.cn-shanghai.aliyuncs.com/publicimg/lnjyadminweb:v1.0.0
    container_name: lnjy_admin
    restart: always
    ports:
      - '8077:8077'
    volumes:
      - /etc/localtime:/etc/localtime:ro
      - ./logs/nginx:/var/log/nginx
    depends_on:
      - lnjyapi
    networks:
      network:
        ipv4_address: 177.217.0.11

  lnjyapi:
    image: registry.cn-shanghai.aliyuncs.com/publicimg/lnjyadminapi:v1.0.0
    container_name: lnjy_api
    restart: always
    ports:
      - '8099:8080'
    volumes:
      - /etc/localtime:/etc/localtime:ro
      - ./logs/backend:/workspace/logs
      - ./data/backend/media:/home/user/Desktop/zll/system
    networks:
      network:
        ipv4_address: 177.217.0.12
  lnjyh5:
    image: registry.cn-shanghai.aliyuncs.com/publicimg/lnjyadminh5:v1.0.0
    container_name: lnjy_h5
    restart: always
    ports:
      - '8078:80'
    volumes:
      - /etc/localtime:/etc/localtime:ro
      - ./logs/nginx:/var/log/nginx
    depends_on:
      - lnjyapi
    networks:
      network:
        ipv4_address: 177.217.0.13
```

## 演示图
![1653779627105](https://user-images.githubusercontent.com/20268389/170845703-89f55f29-bafb-416e-8202-2951a953ce3c.jpg)
![1653779803410](https://user-images.githubusercontent.com/20268389/170845708-f46eb06f-99bf-4f26-863b-20affadedeb5.jpg)
![1653779904629](https://user-images.githubusercontent.com/20268389/170845710-c77f027d-0cbe-4539-9661-466c29d35f2c.jpg)

## 系统开发基于 http://ruoyi.vip/ 