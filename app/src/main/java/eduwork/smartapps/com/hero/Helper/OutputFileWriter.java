package eduwork.smartapps.com.hero.Helper;
import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class OutputFileWriter {
    SharedPreferences sharedPreferences ;
    Context context;
    public OutputFileWriter(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences("outputFile", MODE_PRIVATE);
    }
    public String getOutputFile(){
        return sharedPreferences.getString("output","");
    }
    public void setOutPutFile(String outPutFile){
        sharedPreferences.edit().putString("output",outPutFile).apply();
    }


}
