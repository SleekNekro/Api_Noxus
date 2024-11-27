package com.example.api_noxus;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Champ.class,Role.class}, version =1)
abstract class AppDatabase_Java extends RoomDatabase {

    private static AppDatabase_Java INSTANCE;

    public static AppDatabase_Java getDatabase(Context context) {
        if (INSTANCE == null){
            INSTANCE =
                    Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase_Java.class, "db"
                    ).build();
        }
        return INSTANCE;
    }
    public abstract ChampDAO getChampDao();
    public abstract RoleDAO getRoleDao();
}
