package com.dialogdemo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
public class MainActivity extends Activity {
    String[] singel_list = {"男", "女"};
    String[] multi_list = {"篮球", "足球","桌球","排球"};
    String[] item_list = {"项目经理", "攻城狮","程序猿"};
    NotificationManager manger;
    int notification_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manger= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        initEvent();
    }

    private void initEvent() {
        findViewById(R.id.dialog_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog1();
            }
        });
        findViewById(R.id.dialog_btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog2();
            }
        });
        findViewById(R.id.dialog_btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog3();
            }
        });
        findViewById(R.id.dialog_btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog4();
            }
        });
        findViewById(R.id.dialog_btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog5();
            }
        });
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnsend();
            }
        });
        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btncancel();
            }
        });
    }

    private void showDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认对话框");//设置标题
        builder.setIcon(R.mipmap.ic_launcher);//设置图标
        builder.setMessage("确认对话框提示内容");//设置内容
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "点击了确定按钮", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();//获取一个DIALOG
        dialog.show();//显示对话框
    }

    private void showDialog2() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("单选对话框");//设置标题
        builder.setIcon(R.mipmap.ic_launcher);//设置图标
        builder.setSingleChoiceItems(singel_list, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = singel_list[which];
                Toast.makeText(MainActivity.this, "性别为：" + str, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });//设置单选
        AlertDialog dialog = builder.create();//获取一个DIALOG
        dialog.show();//显示对话框
    }
    private void showDialog3() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("多选对话框");//设置标题
        builder.setIcon(R.mipmap.ic_launcher);//设置图标
        builder.setMultiChoiceItems(multi_list, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "我喜欢" + multi_list[which], Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "我不喜欢：" + multi_list[which], Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog dialog = builder.create();//获取一个DIALOG
        dialog.show();//显示对话框
    }
    private void showDialog4() {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("部门列表");//设置标题
        builder.setIcon(R.mipmap.ic_launcher);//设置图标
        builder.setItems(item_list, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "我不喜欢：" + item_list[which], Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();//获取一个DIALOG
        dialog.show();//显示对话框
    }
    private void showDialog5() {
        LayoutInflater inflater=LayoutInflater.from(this);
        View view=inflater.inflate(R.layout.dialog_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("自定义对话框");//设置标题
        builder.setIcon(R.mipmap.ic_launcher);//设置图标
        builder.setView(view);
        Button btn= (Button) view.findViewById(R.id.accpet);
        final EditText editText= (EditText) view.findViewById(R.id.edt_text);
         final AlertDialog dialog = builder.create();//获取一个DIALOG
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,editText.getText().toString() , Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();//显示对话框
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void btnsend() {
        //构造NOTIFICATION并发送到通知栏
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pendin=PendingIntent.getActivity(this, 0, intent,0);
        Notification.Builder builder=new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("hello");//手机状态栏的提示
        builder.setWhen(System.currentTimeMillis());//设置时间
        builder.setContentTitle("通知栏通知");//通知栏通知
        builder.setContentText("我来自NOTIFICATIONDEMO");//通知内容
        builder.setContentIntent(pendin);//点击后的意图
//        builder.setDefaults(Notification.DEFAULT_SOUND);//设置提示声音
//        builder.setDefaults(Notification.DEFAULT_LIGHTS);//设置指示灯
//        builder.setDefaults(Notification.DEFAULT_VIBRATE);//设置震动
        builder.setDefaults(Notification.DEFAULT_ALL);//设置震动
        Notification notification = builder.build();//4.1以上要用这个
        manger.notify(notification_id,notification);
    }

    private void btncancel() {
        manger.cancel(notification_id);
    }

}
