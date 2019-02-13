package com.luosenen.huelibrary.core;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.luosenen.huelibrary.R;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private  FragmentOneActivity fragment1;
    private FragmentTwoActivity fragment2;
    private FragmentThreeActivity fragment3;
    private Fragment[] fragments;
    private int lastfragment;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        look();
    }
    private void initFragment()
    {

        fragment1 = new FragmentOneActivity();
        fragment2 = new FragmentTwoActivity();
        fragment3 = new FragmentThreeActivity();
        fragments = new Fragment[]{fragment1,fragment2,fragment3};
        lastfragment=0;
        getSupportFragmentManager().beginTransaction().replace(R.id.mainview,fragment1).show(fragment1).commit();
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(changeFragment);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId())
            {
                case R.id.navigation_search:
                {
                    if(lastfragment!=0)
                    {
                        switchFragment(lastfragment,0);
                        lastfragment=0;

                    }

                    return true;
                }
                case R.id.navigation_select:
                {
                    if(lastfragment!=1)
                    {
                        switchFragment(lastfragment,1);
                        lastfragment=1;

                    }

                    return true;
                }
                case R.id.navigation_book:
                {
                    if(lastfragment!=2)
                    {
                        switchFragment(lastfragment,2);
                        lastfragment=2;

                    }

                    return true;
                }



            }


            return false;
        }
    };

    private void switchFragment(int lastfragment,int index)
    {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);
        if(fragments[index].isAdded()==false)
        {
            transaction.add(R.id.mainview,fragments[index]);


        }
        transaction.show(fragments[index]).commitAllowingStateLoss();


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void look(){
        int i = checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
        if (i!= PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(),"不开启权限会导致程序无法运行，定位失败，无法上架您的物品",Toast.LENGTH_SHORT).show();
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.READ_PHONE_STATE,Manifest.permission.ACCESS_FINE_LOCATION
                    ,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_EXTERNAL_STORAGE
                    ,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.WRITE_CONTACTS
                    ,Manifest.permission.GET_ACCOUNTS,Manifest.permission.READ_CONTACTS},1);
            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1){
            if (grantResults[1]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(),"手机权限获取成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),"手机权限获取失败",Toast.LENGTH_SHORT).show();
            }
        }
    }


}
