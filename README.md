# BigData-Spark
The idea of this project was to get used to using Kafka, Spark, Hbase and graphing the findings using Jupyter to do some analysis on each new submission coming from reddit.

To start the project please install Kafka and move it to the home directory.
(Note make sure the file is renamed to Kafka, this will be useful for starting up the zookeeper/kafka server using the scripts provided.)

First tier component is Kafka Producer which is written in Python.
Before running run the following:
1)Zookeeper-server.sh
2)Kafka-server.sh

it is Recommended to have Python3 installed and use pip3 to install the following modules before running the producer:
Kafka
Praw

The data source we are using to get the data is Reddit using the Praw Python module, then we stream this data on Kafka on localhost onto topic final-lab-topic.

Second tier component, we have two Kafka consumers currently that listen onto this topic:
1) Java Consumer: reads the streamed data using kafka and spark, then add them to the database using spark SQL.
This must be a different machine and must have the following installed:
A) Hbase
B) Hadoop 2.x+
3) Spark
The dependancies are all included in the pom-xml file, you can use Intellj Ide to download any missing ones.

2)Python Consumer, currently reads the data on the same machine, it is built for future implemention for some machine learning model to run on the streamed data.

Install pyspark before running this one.

3)Data visualization: Currently reads the data from the json file produced by the python producer, in the future it will read the data directly from Hbase that is hosted on another machine.

Install Jupiyter notebook, pandas, plotly, matplotlib before running this one.
