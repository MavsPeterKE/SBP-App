package com.example.peter.smartfarepayer.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.peter.smartfarepayer.models.SaccoData;

import java.util.List;

public class SaccoDataRepository {
    SaccoDataDao saccoDataDao;
    LiveData<List<SaccoData>>allSaccoData;

    public SaccoDataRepository(Application application) {
        AppRoomDatabase appRoomDatabase = AppRoomDatabase.getAppRoomDatabase(application);
        saccoDataDao = appRoomDatabase.getSaccoDaoInstance();
        allSaccoData = saccoDataDao.getSaccoData();
    }

    public LiveData<List<SaccoData>> getAllSaccoData() {
        return allSaccoData;
    }

    public long insertSaccoData(SaccoData saccoData){
        new insertAsyncTask(saccoDataDao).execute(saccoData);
        return 1;
    }

    private static class insertAsyncTask extends AsyncTask<SaccoData, Void, Void> {

        private SaccoDataDao mAsyncTaskSaccoDao;

        insertAsyncTask(SaccoDataDao dao) {
            mAsyncTaskSaccoDao = dao;
        }

        @Override
        protected Void doInBackground(final SaccoData... params) {
            mAsyncTaskSaccoDao.insertSaccoData(params[0]);
            return null;
        }

        protected long onPostExecute(long result) {
            //super.onPostExecute(aVoid);
            Log.e("onPostExecute: ",""+result );
            return result;
        }
    }
}
