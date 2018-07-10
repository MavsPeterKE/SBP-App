package com.example.peter.smartfarepayer.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.peter.smartfarepayer.models.SaccoData;

import java.util.List;

@Dao
public interface SaccoDataDao {

    @Query("select * from sacco_data_table")
    LiveData<List<SaccoData>> getSaccoData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertSaccoData(SaccoData saccoData);

    @Delete
    void delete(SaccoData saccoData);

    @Query("DELETE FROM sacco_data_table")
    void deleteAll();

    @Query("SELECT * from sacco_data_table ORDER BY saco_name ASC")
    List<SaccoData> getAllSaccos();
}
