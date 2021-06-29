package week4.problem3.solution2;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class SalaryFileMapper extends Mapper<Object, Text, Text, Text> {
    private static final String fileTag = "SD~";
    private static final String DATA_SEPARATOR = ",";
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String values[] = value.toString().split(DATA_SEPARATOR);
        StringBuilder dataStringBuilder = new StringBuilder();
        dataStringBuilder.append(fileTag);
        if(!(values[0]).equalsIgnoreCase("profession")){
            for (int index = 0; index < values.length; index++) {
                if (index != 0) {
                    dataStringBuilder.append(values[index].toString().trim() + DATA_SEPARATOR);
                }
            }
            String dataString = dataStringBuilder.toString();
            if (dataString != null && dataString.length() > 1) {
                dataString = dataString.substring(0, dataString.length() - 1);
            }
            dataStringBuilder = null;
            context.write(new Text(values[0]), new Text(dataString));
        }
    }
}
