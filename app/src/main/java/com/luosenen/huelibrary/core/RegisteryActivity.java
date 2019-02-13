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

public class RegisteryActivity extends Activity {
    private EditText account;
    private EditText seat;
    private ProgressBar pb;
    private ImageView registery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registery);
        account = findViewById(R.id.registerAccount);
        seat = findViewById(R.id.seat);
        pb = findViewById(R.id.register_pg);
        registery = findViewById(R.id.register);
        registery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = account.getText().toString().trim();
                String seats = seat.getText().toString().trim();
                if (name == ""||name.equals("")||seats.equals("")||seats==""){
                    pb.setVisibility(View.INVISIBLE);
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
                MyUser user = new MyUser();
                final Info info = new Info();
                user.setUsername(name);
                info.setSeat(seats);
                info.setAccount(name);
                user.signUp(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if (e != null){
                            info.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e ==null){
                                        return;
                                    }
                                }
                            });
                            Toast.makeText(getApplicationContext(),"系统提醒，注册成功",Toast.LENGTH_LONG).show();
                            pb.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        }else {
                            Toast.makeText(getApplicationContext(),"系统提醒，注册失败"+e.toString(),Toast.LENGTH_LONG).show();
                            pb.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
    }
}
