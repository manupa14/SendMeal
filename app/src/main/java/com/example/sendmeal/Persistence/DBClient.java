package com.example.sendmeal.Persistence;


import android.content.Context;

import androidx.room.Room;

public class DBClient {

    private static DBClient ourInstance = null;
    private AppDataBase appDataBase;


    private DBClient(Context ctx) {
        appDataBase = Room.databaseBuilder(ctx, AppDataBase.class, "app-db").fallbackToDestructiveMigration().build();
    }

    public static DBClient getInstance(Context ctx) {
        if (ourInstance == null){
            ourInstance = new DBClient(ctx);
        }
        return ourInstance;
    }

    public AppDataBase getAppDataBase(){
        return appDataBase;
    }

}
