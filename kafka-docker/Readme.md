# kafka在docker下安装
在docker下安装kafka环境，然后用kafka-producer生产数据，kafka-consumer消费数据
docker-compose内容参考docker-compose-cluster.yml，配置了一个zookeeper和三个kafka
运行命令`docker-compose -f docker-compose-cluster.yml up -d`,即可以看到运行的kafka集群

> consumer和producer两个java微服务我都是在window10中运行的，而kafka集群是在linux系统中运行
> 需要在windows10的hosts下增加配置，将地址进行映射，例如 
> 192.168.3.5 kafka0

## 参考
https://blog.csdn.net/boling_cavalry/article/details/85528519