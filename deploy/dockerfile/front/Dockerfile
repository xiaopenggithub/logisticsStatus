# mv ../../../ruoyi-ui/dist .
# docker build -t registry.cn-shanghai.aliyuncs.com/publicimg/lnjyadminweb:v1.0.0 .
# docker push registry.cn-shanghai.aliyuncs.com/publicimg/lnjyadminweb:v1.0.0
FROM nginx:alpine
LABEL MAINTAINER="zan@361018729@qq.com"
COPY ./frontendnginxprod.conf /etc/nginx/conf.d/default.conf
#COPY ../../../ruoyi-ui/dist /usr/share/nginx/html
COPY ./dist /usr/share/nginx/html
