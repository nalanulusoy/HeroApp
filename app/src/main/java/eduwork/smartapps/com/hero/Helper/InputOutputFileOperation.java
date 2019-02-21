package eduwork.smartapps.com.hero.Helper;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
public class InputOutputFileOperation  {
    OutputFileWriter outputFileWriter;
    private String inputFile ="res/raw/input";
    private Context context;
    public InputOutputFileOperation(Context context){
        outputFileWriter=new OutputFileWriter(context);
        this.context=context;
    }
    public String readInputFromFile(){
        InputStream input = this.getClass().getClassLoader().getResourceAsStream(inputFile);
        StringBuilder buf = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(input))){
            String currentLine;
            while((currentLine = br.readLine()) != null){
                buf.append(currentLine);
            }
        } catch(IOException e){
            Log.e("InputFile","Input File Exception");
        }
        return buf.toString();
    }

    public void writeOutputFromFile(String message) {
        outputFileWriter.setOutPutFile(message);



                 try(BufferedWriter bw = new BufferedWriter(new FileWriter(new
                         File(Environment.getDataDirectory() +File.separator+"output.txt")))){
            bw.write(message);
            bw.newLine();
        }
        catch(IOException e){
            Log.e("OutputFile","Output File Exception");
        }
        }

    }




