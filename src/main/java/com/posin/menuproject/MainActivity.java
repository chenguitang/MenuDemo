package com.posin.menuproject;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.posin.menudevices.constant.Dishes;
import com.posin.menudevices.ICallback;
import com.posin.menudevices.IMenuManage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int MAX_SHOW_ITEMS=10;
    private static final boolean IS_CHINESE=true;

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
                    mMenuManage.init(MAX_SHOW_ITEMS, IS_CHINESE, new ICallback() {
                        @Override
                        public void success() throws RemoteException {

                        }

                        @Override
                        public void failure() throws RemoteException {

                        }

                        @Override
                        public IBinder asBinder() {
                            return null;
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_send2:
                try {
                    ArrayList<Dishes> dishes = new ArrayList<>();
                    for (int i=0;i<15;i++){
                        dishes.add(new Dishes("鸡蛋炒饭"+i,1,(20+i),25));
                    }
                    mMenuManage.sendMenu(dishes,1314520.0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_pay:
                try {
                    mMenuManage.pay(520,1314,1228.00,250.0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_clear:
                try {
                    mMenuManage.clearMenu();
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
