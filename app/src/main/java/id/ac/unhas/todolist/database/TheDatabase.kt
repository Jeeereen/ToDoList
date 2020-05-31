package id.ac.unhas.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.unhas.todolist.ToDo

@Database(entities = [ToDo::class], exportSchema = false, version = 1)
abstract class TheDatabase : RoomDatabase() {
    abstract fun todoDao(): TheDAO

    companion object{
        private const val DB_NAME = "TODO_DB"
        private var instance: TheDatabase? = null

        fun getInstance(context: Context): TheDatabase? {
            if (instance == null) {
                synchronized(TheDatabase::class) {
                    instance = Room.databaseBuilder(context, TheDatabase::class.java,
                        DB_NAME
                    ).build()
                }
            }

            return instance
        }
    }

}