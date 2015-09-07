#!/bin/bash

service mysql start
mysql -uroot -proot < /mysql-config/SCHEMA.sql

if [ -n "$MYGALLERY_REMOTE_BUNDLE_HOST" ] && [ -n "$MYGALLERY_REMOTE_BUNDLE_PORT" ] && [ -n "$DMA_SECURITY_TOKEN" ]; then 
    echo "remote host = '$MYGALLERY_REMOTE_BUNDLE_HOST'"
    cd /tmp
    sshpass -p "$DMA_SECURITY_TOKEN" scp -oStrictHostKeyChecking=no -P "$MYGALLERY_REMOTE_BUNDLE_PORT" data@$MYGALLERY_REMOTE_BUNDLE_HOST:bundle/mygallery-container-v3.0.0/bundleMigrated.zip .
#    wget --no-check-certificate --user=$DMA_APPID --password=$DMA_SECURITY_TOKEN $MYGALLERY_REMOTE_BUNDLE_URL/bundle/mygallery-container-v3.0.0/bundleMigrated.zip
    unzip -o -d /data bundleMigrated.zip
    rm bundleMigrated.zip
elif [ -f /dma/migration/data/bundle/mygallery-container-v3.0.0/bundleMigrated.zip ]; then
    unzip -o -d /data /dma/migration/data/bundle/mygallery-container-v3.0.0/bundleMigrated.zip
fi

/bin/sh /mysql-config/generate-entries.sh
mysql -uroot -proot mygallery-db < /mysql-config/ENTRIES.sql

$CATALINA_HOME/bin/catalina.sh run
