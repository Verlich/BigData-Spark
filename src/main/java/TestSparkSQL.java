
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

public class TestSparkSQL implements Serializable {

    public static void main( String[] args )
    {
        String warehouseLocation = new File("/user/hive/warehouse").getAbsolutePath();
        System.out.println(warehouseLocation);
        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark Hive Example")
                .config("spark.sql.warehouse.dir", warehouseLocation)
                .enableHiveSupport()
                .master("local")
                .config("hive.metastore.uris", "thrift://localhost:9083")
                .getOrCreate();

        System.out.println(spark.sql("SELECT * from new_hive_reddit").count());
    }
}

