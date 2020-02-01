import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.auth.Credentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;
public class ApplicationMaster {
    public static void main(String[] args) throws IOException {

        //To do run all the servers and configurations together
        // Current order:
        /*
            Make sure you update maven
            Make sure you have jdk 1.8
            Make sure you install python3
            Configure python3 interpeter
            Install pip3
            pip3 install kafka
            pip3 install praw
            Download kafka and put it in the home directory
            Rename your downloaded kafka to kafka
            Run the zookeeper.sh command
            Run the Kafka-server.sh command
            Optional: Run kafka-console-consumer for real time logging
         */
        /*
        TO DO
        Read the data sent from the python producer into spark streaming
        Insert the data into Hbase
        Compute the data and save in Hbase
        Draw the analyzed data
         */
    }
}
