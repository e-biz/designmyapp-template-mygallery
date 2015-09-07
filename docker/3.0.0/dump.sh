#!/bin/bash

if [ -z "$MYGALLERY_CONTAINER_NAME" ]
  then
    echo "The env variable MYGALLERY_CONTAINER_NAME is not defined"
    exit
fi

mkdir -p /dma/migration/data/bundle/$MYGALLERY_CONTAINER_NAME && 
cd /data &&
zip -r /dma/migration/data/bundle/$MYGALLERY_CONTAINER_NAME/bundle.zip images thumbnail

exit
