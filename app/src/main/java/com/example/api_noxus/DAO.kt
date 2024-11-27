package com.example.api_noxus

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ChampDAO {
    @Query("SELECT * FROM champ")
    fun getChamps() : LiveData<List<Champ>>

    @Insert
    fun addChamp(champ: Champ)

    @Insert
    fun addMoreChamps(champ: List<Champ>)

    @Delete
    fun deleteChamp(champ: Champ)

    @Query("DELETE FROM champ")
    fun deleteAllChamp()
}

@Dao
interface RoleDAO{
    @Query("SELECT * FROM role")
    fun getRole() : LiveData<List<Role>>

    @Insert
    fun addRole(role: Role)

    @Insert
    fun addMoreRole(role: List<Role>)

    @Delete
    fun deleteRole(role: Role)

    @Query("DELETE FROM role")
    fun deleteAllRole()
}