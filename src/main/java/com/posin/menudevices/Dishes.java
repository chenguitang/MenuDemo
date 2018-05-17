package com.posin.menudevices;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Greetty on 2018/5/17.
 * <p>
 * 菜品实体类
 */
@SuppressLint("ParcelCreator")
public class Dishes implements Parcelable {

    private String name;
    private double prices;

    public Dishes(String name, double prices) {
        this.name = name;
        this.prices = prices;
    }

    //从Parcel中读出之前写进的数据
    protected Dishes(Parcel in) {
        this.name = in.readString();
        this.prices = in.readDouble();
    }

    public static final Creator<Dishes> CREATOR = new Creator<Dishes>() {
        //反序列化，把我们通过writeToParcel方法写进的数据再读出来
        @Override
        public Dishes createFromParcel(Parcel in) {
            return new Dishes(in);
        }

        @Override
        public Dishes[] newArray(int size) {
            return new Dishes[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    //实现 Parcelable 接口所需重写的方法1，一般不用管
    @Override
    public int describeContents() {
        return 0;
    }

    //实现 Parcelable 接口所需重写的方法2，将对象的每个字段写入到Parcel中
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(prices);
    }

    @Override
    public String toString() {
        return "Dishes{" +
                "name='" + name + '\'' +
                ", prices=" + prices +
                '}';
    }
}
