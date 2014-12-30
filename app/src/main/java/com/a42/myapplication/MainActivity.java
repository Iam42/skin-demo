package com.a42.myapplication;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Method;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private AssetManager mAssetManager;
    private Resources mResources;
    private Resources.Theme mTheme;

    private ImageView mImageView;
    private TextView mTextView;
    private View mBg;
    private Button button1;
    private Button button2;

    private String skinName = "skin.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadResources();
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.iv_test);
        mTextView = (TextView) findViewById(R.id.tv_test);
        mBg = findViewById(R.id.view_bg);
        button1 = (Button) findViewById(R.id.btn_skin1);
        button2 = (Button) findViewById(R.id.btn_skin2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        initView();
    }

    private void initView() {
        mImageView.setImageDrawable(getSkinResources().getDrawable(R.drawable.test));
        mTextView.setTextColor(getSkinResources().getColor(R.color.test_color));
        mBg.setBackgroundColor(getSkinResources().getColor(R.color.test_bg));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void loadResources() {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, getFilesDir() + "/" + skinName);
            mAssetManager = assetManager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Resources superRes = super.getResources();
        mResources = new Resources(mAssetManager, superRes.getDisplayMetrics(),
                superRes.getConfiguration());
        mTheme = mResources.newTheme();
        mTheme.setTo(super.getTheme());
    }


    public AssetManager getSkinAssets() {
        return mAssetManager;
    }

    public Resources getSkinResources() {
        return mResources;
    }

    @Override
    public AssetManager getAssets() {
        return super.getAssets();
    }

    @Override
    public Resources getResources() {
        return super.getResources();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_skin1:
                skinName = "skin.apk";
                break;

            case R.id.btn_skin2:
                skinName = "skin2.apk";
                break;
        }
        loadResources();
        initView();
    }
}
