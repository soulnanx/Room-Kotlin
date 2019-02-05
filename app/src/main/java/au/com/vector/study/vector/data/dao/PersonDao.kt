package au.com.vector.study.vector.data.dao

import android.arch.persistence.room.*
import au.com.vector.study.vector.data.entity.Person

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(person: Person): Long

    @Update
    fun update(person: Person): Int

    @Delete
    fun delete(vararg person: Person): Int

    @Query("SELECT * FROM Person WHERE name LIKE :name ORDER BY name")
    fun personByName(name: String = "%"): List<Person>

    @Query("SELECT * FROM Person WHERE id = :id")
    fun personById(id: Long): Person

    @Query("SELECT * FROM Person")
    fun getAll(): List<Person>

}