package eduwork.smartapps.com.hero.Helper;

import android.content.Context;

public class MessageWriter {
    InputOutputFileOperation inputOutputFileOperation;
    private Context context;

    public MessageWriter(Context context){
        inputOutputFileOperation=new InputOutputFileOperation(context);
        this.context=context;
    }

    public void writeMessage(String message ) {
        inputOutputFileOperation.writeOutputFromFile(message);
    }

}
