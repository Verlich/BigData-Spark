import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class DBConnector {


    private static final String TABLE_NAME = "new_hbase_reddit";
    private static final String CF1 = "cf1";
    public static void InsertIntoDB(String json) throws IOException {
        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(json);
        if(jsonTree.isJsonObject()){
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            JsonElement name = jsonObject.get("name");
            JsonElement id = jsonObject.get("id");
            JsonElement timeCreated = jsonObject.get("time-created");
            JsonElement locked = jsonObject.get("locked");
            JsonElement comments = jsonObject.get("comments");
            JsonElement is_original_content = jsonObject.get("is_original_content");
            JsonElement num_of_comments = jsonObject.get("num_of_comments");
            JsonElement score = jsonObject.get("score");
            JsonElement upvote_ratio = jsonObject.get("upvote_ratio");

            Configuration config = HBaseConfiguration.create();

            try (Connection connection = ConnectionFactory.createConnection(config);
                 Admin admin = connection.getAdmin())
            {
                Table table = new HTable(connection.getConfiguration(), TABLE_NAME);
                Put p = new Put(Bytes.toBytes(id.getAsString()));
                p.add(CF1.getBytes(), Bytes.toBytes("name"), Bytes.toBytes(name.getAsString()));
                p.add(CF1.getBytes(), Bytes.toBytes("locked"), Bytes.toBytes(locked.getAsString()));
                p.add(CF1.getBytes(), Bytes.toBytes("comments"), Bytes.toBytes(comments.getAsString()));
                p.add(CF1.getBytes(), Bytes.toBytes("is_original_content"), Bytes.toBytes(is_original_content.getAsString()));
                p.add(CF1.getBytes(), Bytes.toBytes("num_of_comments"), Bytes.toBytes(num_of_comments.getAsString()));
                p.add(CF1.getBytes(), Bytes.toBytes("score"), Bytes.toBytes(score.getAsString()));
                p.add(CF1.getBytes(), Bytes.toBytes("upvote_ratio"), Bytes.toBytes(upvote_ratio.getAsString()));
                //java.util.Date time = new java.util.Date((long)timeCreated.getAsDouble()*1000);

                p.add(CF1.getBytes(), Bytes.toBytes("time_created"), Bytes.toBytes(timeCreated.getAsString()));
                table.put(p);

                System.out.println(" Done!");
            }
        }

    }

}
