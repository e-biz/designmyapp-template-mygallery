#!/bin/bash

DATA_DIR=/data

for image in $(ls $DATA_DIR/images)
do
    echo "insert into image(full_size_path, thumbnail_path) values ('$DATA_DIR/images/$image', '$DATA_DIR/thumbnail/$image');" >> /mysql-config/ENTRIES.sql
done
