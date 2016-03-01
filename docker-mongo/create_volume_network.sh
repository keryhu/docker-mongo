 #!/bin/sh
 #创建mongo volume
 docker volume create --name=mongo-data
 # 休息2s
 sleep 2
 #此时将备份的单独数据 /home/keryhu/dbdata/mongodb  拷贝到 /var/lib/docker/volumes/mongo-data/_data 下
 sudo -i
 cp -r /home/keryhu/dbdata/mongodb/*   /var/lib/docker/volumes/mongo-data/_data

exit