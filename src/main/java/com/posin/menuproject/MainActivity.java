package com.posin.menuproject;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.posin.menudevices.Dishes;
import com.posin.menudevices.IMenuManage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private IMenuManage mMenuManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                try {
//                    mMenuManage.setMenu("黄焖鸡鸭狗1354654...");
//                    LinkedHashMap<String, String> menuMaps = new LinkedHashMap<>();
//
//                    menuMaps.put("greetty1", "lajdfljdaf");
//                    menuMaps.put("greetty2", "哈哈哈哈");
//                    menuMaps.put("greetty3", "7777777777777");
//                    menuMaps.put("greetty4", "测试hashMap，....");
//                    mMenuManage.setMenuMap(menuMaps);


                    mMenuManage.sendDish(new Dishes("清蒸罗非鱼",88.88));


                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_send2:
                try {

                    ArrayList<String> list = new ArrayList<>();
                    list.add("Greetty");
                    list.add("陈贵堂");
                    list.add("拉到就发了点击");
                    list.add("我是逗比");
                    String returnName = mMenuManage.setListMenuList(list);
                    Log.e(TAG, "return name is: " + returnName);

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 绑定服务
     */
    public void bindService() {
        Intent intent = new Intent();
        intent.setAction("com.posin.menudevices.menuservices");
        intent.setPackage("com.posin.menudevices");
        bindService(intent, conn, BIND_AUTO_CREATE);

    }

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMenuManage = IMenuManage.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMenuManage = null;
        }

    };

}
