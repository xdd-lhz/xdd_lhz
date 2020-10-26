# xdd_lhz
毕业设计
## 启动部署

进入club_manage
编译
```
mvn clean

mavpackage
```

启动项目日志重定向到标准输出springlog.out文件
```
nohup java -jar target/club_manager-0.0.1-SNAPSHOT.jar >springlog.out 2>&1 &
```
查看日志
```
tail -f springlog.out
```
