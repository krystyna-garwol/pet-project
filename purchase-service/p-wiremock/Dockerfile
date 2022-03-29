FROM wiremock/wiremock

ADD build/libs/p-wiremock-*-all.jar /data/service.jar

CMD ["java", "-jar", "/data/service.jar", "--port=9000"]