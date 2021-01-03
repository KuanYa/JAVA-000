

 docker run -p 6379:6379 --name redis-master -v  /c/data/redis/master/redis.conf:/etc/redis/redis.conf  -v /data/redis/master/data:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes





 docker run -p 6380:6380 --name redis-slave -v /data/redis/slave/redis.conf:/etc/redis/redis.conf  -v /data/redis/slave/data:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes





docker cp  C:\data\redis\master  12416362a2b8:/data/redis/





slaveof no one 去掉主从关系