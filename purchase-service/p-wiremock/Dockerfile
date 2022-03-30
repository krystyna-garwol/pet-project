FROM wiremock/wiremock

ADD build/libs/p-wiremock-*-all.jar /data/service.jar
ADD mappings /data/mappings

CMD ["java", "-jar", "/data/service.jar", "--port=9000", "--max-request-journal-entries=10", "--root-dir=/data"]