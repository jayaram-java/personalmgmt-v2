FROM java
ADD ./target/personal-mgmt-1.0.jar /personal-mgmt-1.0.jar
ADD ./run.sh /run.sh
RUN chmod a+x /run.sh
EXPOSE 8080:8080
CMD /run.sh
