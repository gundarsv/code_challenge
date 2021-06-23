FROM ubuntu:latest

RUN apt-get update \
	&& apt-get install -y python3-pip
COPY requirements.txt .
RUN pip3 install -r requirements.txt

CMD ["/scripts/run_tests.sh"]