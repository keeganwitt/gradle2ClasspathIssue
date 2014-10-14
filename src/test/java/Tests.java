import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Cluster;
import org.junit.Test;


public class Tests {
  @Test
  public void theTest() throws Exception {
    Configuration conf = new Configuration();
    Cluster cluster = new Cluster(conf);
  }

}
