package com.example.jt.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mobvista.msdk.MobVistaConstans;
import com.mobvista.msdk.MobVistaSDK;
import com.mobvista.msdk.out.MobVistaSDKFactory;

import java.util.Map;

import static com.example.jt.demo.R.color.colorPrimary;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView demo = (TextView)findViewById(R.id.demo);
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load();
            }
        });
        initMobvistaSdk();
    }

    private void load() {
        try {
            Class<?> aClass = Class
                    .forName("com.mobvista.msdk.shell.MVActivity");
            Intent intent = new Intent(getApplicationContext(), aClass);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(MobVistaConstans.PROPERTIES_UNIT_ID, "10000");

            intent.putExtra(MobVistaConstans.PROPERTIES_WALL_STATUS_COLOR, colorPrimary);
            intent.putExtra(MobVistaConstans.PROPERTIES_WALL_TITLE_BACKGROUND_COLOR, colorPrimary);
            intent.putExtra(MobVistaConstans.PROPERTIES_WALL_TAB_BACKGROUND_ID, colorPrimary);

            intent.putExtra(MobVistaConstans.PROPERTIES_WALL_TAB_INDICATE_LINE_BACKGROUND_ID,
                    android.R.color.white);

            String white = "#ffffff";
            intent.putExtra(MobVistaConstans.PROPERTIES_WALL_TAB_UNSELECTED_TEXT_COLOR, white);
            intent.putExtra(MobVistaConstans.PROPERTIES_WALL_TAB_SELECTED_TEXT_COLOR, white);
            getApplicationContext().startActivity(intent);
        } catch (ClassNotFoundException e) {
            Log.e("=========", "", e);
        }
    }

    public static final String MOBVISTA_APP_ID = "90000";
    public static final String MOBVISTA_API_KEY = "88888888888888888888888888888888";
    private void initMobvistaSdk() {
        final MobVistaSDK sdk = MobVistaSDKFactory.getMobVistaSDK();
        final Map<String, String> map = sdk.getMVConfigurationMap(
                MOBVISTA_APP_ID, MOBVISTA_API_KEY);
        // Document says:
        // If you modify applicationId, please add the following attributes, otherwise it will
        // crash.
        // So we add the package name at runtime to avoid crash.
        map.put(MobVistaConstans.PACKAGE_NAME_MANIFEST, getPackageName());
        sdk.init(map, getApplicationContext());
    }

}
