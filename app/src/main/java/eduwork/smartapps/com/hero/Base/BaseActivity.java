package eduwork.smartapps.com.hero.Base;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity   extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
    }



    public Activity getActivity(){return  this;}
    protected abstract int getLayoutResource();

    public  boolean AskPermision() {
        boolean durum=true;
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            durum=false;
        }if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=PackageManager.PERMISSION_GRANTED) {
            durum=false;
        }
        return durum;
    }

    public void Want_To_Permision(){
        int smsOkumaIzni = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);

        int bellegeYazmaIzni = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        List<String> izinler = new ArrayList<String>();

        if(bellegeYazmaIzni != PackageManager.PERMISSION_GRANTED){

            izinler.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        } if(smsOkumaIzni != PackageManager.PERMISSION_GRANTED){
            izinler.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if(!izinler.isEmpty()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(izinler.toArray(new String[izinler.size()]), 200);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch ( requestCode ) {
            case 200: {
                for( int i = 0; i < permissions.length; i++ ) {
                    if( grantResults[i] == PackageManager.PERMISSION_GRANTED ) {
                        Log.e( "Permissions", "İzin Verildi: " + permissions[i] );

                    } else if( grantResults[i] == PackageManager.PERMISSION_DENIED ) {
                        Log.e( "Permissions", "İzin Reddedildi: " + permissions[i] );

                    }
                }
            }
            break;
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }


}
