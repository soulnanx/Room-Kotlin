package au.com.vector.study.vector.data.migration

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

object Migration_1_2 : Migration(1, 2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Person ADD COLUMN gender TEXT NOT NULL DEFAULT 'n/a'")
    }
}