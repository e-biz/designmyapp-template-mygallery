FROM designmyapp/mygallery:v3.0.0

MAINTAINER DesignMyApp <designmyapp@ebusinessinformation.fr>

RUN apt-get update \
    	&& apt-get install -y debconf-utils \
    	&& echo 'mysql-server mysql-server/root_password password root' | debconf-set-selections \
        && echo 'mysql-server mysql-server/root_password_again password root' | debconf-set-selections \
    	&& apt-get install -y mysql-server \
	&& apt-get install -y zip sshpass

COPY dump.sh /dma/migration/
COPY mysql-config /mysql-config
COPY startup.sh /startup.sh

CMD ["sh", "/startup.sh"]