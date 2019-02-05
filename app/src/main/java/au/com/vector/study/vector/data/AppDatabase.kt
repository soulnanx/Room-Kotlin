package au.com.vector.study.vector.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.arch.persistence.room.migration.Migration
import au.com.vector.study.vector.data.dao.PersonDao
import au.com.vector.study.vector.data.entity.Person
import android.arch.persistence.room.Room
import android.content.Context
import android.net.ConnectivityManager
import au.com.vector.study.vector.data.migration.Migration_1_2

@Database(entities = [Person::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase constructor() : RoomDatabase() {

    companion object {

        private const val DB_NAME = "app"
        @Volatile
        private var instance: AppDatabase? = null

        @Synchronized
        internal fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                instance = create(context)
            }
            return instance
        }

        private fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .addMigrations(Migration_1_2)
                .build()
        }

    }

    abstract fun personDao() : PersonDao
}