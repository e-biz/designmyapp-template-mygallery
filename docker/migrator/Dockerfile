FROM debian:jessie

MAINTAINER DesignMyApp <designmyapp@ebusinessinformation.

RUN mkdir -p /dma/migration/

COPY ./migration.sh /dma/migration/migration.sh

CMD ["sh", "/dma/migration/migration.sh"]