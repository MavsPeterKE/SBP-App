package com.example.peter.smartfarepayer.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.peter.smartfarepayer.models.SaccoData;

@Database(entities = {SaccoData.class},version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {
    public abstract SaccoDataDao getSaccoDaoInstance();

    private static AppRoomDatabase INSTANCE;

    public static AppRoomDatabase getAppRoomDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class, "Sbp_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
