package com.posin.menuproject;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.posin.menudevices.IMenuManage;

public class MainActivity extends AppCompatActivity {

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
                    mMenuManage.setMenu("黄焖鸡鸭狗1354654...");
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
