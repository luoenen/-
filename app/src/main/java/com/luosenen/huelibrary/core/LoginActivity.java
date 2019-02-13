package com.luosenen.huelibrary.core;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.luosenen.huelibrary.R;
import com.luosenen.huelibrary.info.Info;
import com.luosenen.huelibrary.user.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends Activity {

    private EditText input_account;
    private ImageView login;
    private ImageView toRegistery;
    private ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toRegistery = findViewById(R.id.toRegister);
        toRegistery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisteryActivity.class));
                finish();
            }
        });
        input_account = findViewById(R.id.account);
        login =findViewById(R.id.login);
        pg = findViewById(R.id.login_pg);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg.setVisibility(View.VISIBLE);
                final String name = input_account.getText().toString().trim();
                if (name == ""||name.equals("")){
                    pg.setVisibility(View.INVISIBLE);
                    new AlertDialog.Builder(getApplicationContext())
                            .setTitle("用户系统提示：")
                            .setMessage("学号座位输入不合法请重新输入！")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .create().show();
                    return;
                }

                MyUser myUser = new MyUser();
                myUser.login(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if(e!=null){
                            Toast.makeText(getApplicationContext(),"系统提醒，登录成功",Toast.LENGTH_SHORT).show();
                            pg.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(getApplicationContext(),"系统提醒，登录出错"+e.toString(),Toast.LENGTH_LONG).show();
                            pg.setVisibility(View.INVISIBLE);
                        }
                    }
                });

            }
        });

    }
    }

