# mvn package -Dmaven.test.skip=true
# mv ../../../ruoyi-admin/target/ruoyi-admin.jar .
# docker build -t registry.cn-shanghai.aliyuncs.com/publicimg/lnjyadminapi:v1.0.0 .
# docker push registry.cn-shanghai.aliyuncs.com/publicimg/lnjyadminapi:v1.0.0
FROM java:8
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone
COPY ./ruoyi-admin.jar /workspace/app.jar
CMD java -jar /workspace/app.jar
