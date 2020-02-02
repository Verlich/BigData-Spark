from pyspark.streaming.kafka import KafkaUtils
from pyspark import SparkContext
from pyspark.streaming import StreamingContext
import os

os.environ['PYSPARK_PYTHON'] = 'python3'
os.environ['PYSPARK_SUBMIT_ARGS'] = '--jars spark-streaming-kafka-0-8-assembly_2.11-2.4.4.jar pyspark-shell'

sc = SparkContext(appName="spark_consumer_kafka")
ssc = StreamingContext(sc, 2)
kafkaParams = {"metadata.broker.list": "localhost:9092"}

data = KafkaUtils.createDirectStream(ssc, ['final-lab-topic'], kafkaParams)

lines = data.map(lambda x: x[1])
lines.pprint()
ssc.start()
ssc.awaitTermination()