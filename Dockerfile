FROM bellsoft/liberica-openjdk-alpine:17-cds

#Work directory
WORKDIR /home/workdirectory
# adding files

RUN apk add curl jq

ADD target/docker-resources ./

ADD runner.sh    runner.sh

ENTRYPOINT sh runner.sh




#selenium.grid.hubHost
#192.168.122.1