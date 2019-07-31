##### 1.maven使用DockerFile构建镜像

```dockerfile
mvn package docker:build  #首次构建需要时间
```

构建好的镜像通过docker images查看

#### 2.运行镜像

```dockerfile
docker run -p 8001:8080 -dti <imagesName> /bin/bash #启动一个 bash 终端，允许用户进行交互
#-t 让Docker分配一个伪终端（pseudo-tty）并绑定到容器的标准输入上
#-i 让容器的标准输入保持打开
#-d 让Docker容器在后台以守护态（Daemonized）形式运行
```

启动成功使用docker ps 查看运行的容器

#### 3.删除镜像/容器

```
docker rm <contaninerId>/<containerName>  #删除容器，一般删除之前要通过stop中断容器
docker rmi <imageId>/<imageName>  #删除镜像
```

#### 4.导出/导入容器

```dockerfile
docker export <containerId>  >**.tar #导出容器快照到本地文件
cat **.tar | docker import - <imagesName>:<version> # 从容器快照文件中再导入为镜像
```

**Tip**

运行导入的镜像必须带command，否则启动会报错：Error response from daemon: No command specified

必须像下列输入命令

```
docker run -d -p 9999:9093 sso:latest java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
```

