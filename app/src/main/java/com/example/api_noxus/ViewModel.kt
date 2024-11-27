package com.example.api_noxus

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors


class ViewModel (app: Application) : AndroidViewModel(app){
    private val appDatabase : AppDatabase_Java = AppDatabase_Java.getDatabase(
        getApplication()
    )
    private val champDAO : ChampDAO = appDatabase.champDao
    private val roleDAO : RoleDAO = appDatabase.roleDao
    val champs : LiveData<List<Champ>>
        get() = champDAO.getChamps()


    suspend fun reload(){
        val executor = Executors.newSingleThreadExecutor()
        val rChamp = RetrofitServiceFactory.makeRetrofitService().listChamps("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InF4dXVvZ2JpY3J1dmJhdGdxam91Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE1MDc5NjgsImV4cCI6MjA0NzA4Mzk2OH0.HwsEVV1d6b_0pMHBTyYt88cgEd7MUyb9XrFNexqJBEY")
        val rRole = RetrofitServiceFactory.makeRetrofitService().listRoles("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InF4dXVvZ2JpY3J1dmJhdGdxam91Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE1MDc5NjgsImV4cCI6MjA0NzA4Mzk2OH0.HwsEVV1d6b_0pMHBTyYt88cgEd7MUyb9XrFNexqJBEY")

        executor.execute{
            champDAO.deleteAllChamp()
            champDAO.addMoreChamps(rChamp)
            roleDAO.deleteAllRole()
            roleDAO.addMoreRole(rRole)
        }
    }
}