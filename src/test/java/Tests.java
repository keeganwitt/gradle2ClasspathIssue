import java.io.IOException;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Shell;
import org.junit.Test;


public class Tests {
  static {
    if (System.getProperty("os.name").contains("Windows")) {
      try {
        setFinalStatic(Shell.class.getField("osType"), Shell.OSType.OS_TYPE_LINUX);
        setFinalStatic(Shell.class.getField("WINDOWS"), false);
        setFinalStatic(Shell.class.getField("TOKEN_SEPARATOR_REGEX"), "[ \t\n\r\f]");
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  static void setFinalStatic(Field field, Object newValue) throws Exception {
    field.setAccessible(true);
    Field modifiersField = Field.class.getDeclaredField("modifiers");
    modifiersField.setAccessible(true);
    modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
    field.set(null, newValue);
  }

  @Test
  public void theTest() throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "someJob");
    FileInputFormat.addInputPath(job, new Path("input"));
    FileOutputFormat.setOutputPath(job, new Path("build/output"));
    job.submit();
  }

}
