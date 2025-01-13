# 基础镜像
FROM  openjdk:8-jre
# 挂载目录
VOLUME /home/red
# 创建目录
RUN mkdir -p /home/red
# 指定路径
WORKDIR /home/red
# 复制jar文件到路径
COPY ./target/view.jar /home/red/view.jar
# 启动网关服务
ENTRYPOINT ["java","-jar","view.jar"]