package com.example.api_noxus;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Champ.class,Role.class}, version =1, exportSchema = false)
public abstract class AppDatabase_java extends RoomDatabase {

    private static AppDatabase_java INSTANCE;

    public static AppDatabase_java getDatabase(Context context) {
        if (INSTANCE == null){
            INSTANCE =
                    Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase_java.class, "db"
                    ).build();
        }
        return INSTANCE;
    }
    public abstract ChampDAO getChampDao();
    public abstract RoleDAO getRoleDao();
}
