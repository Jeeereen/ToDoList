package id.ac.unhas.todolist.database

import android.app.Application
import androidx.lifecycle.LiveData
import id.ac.unhas.todolist.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Penyimpanan
class TheRepository(application: Application){
    private val todoDao: TheDAO?
    private var todos: LiveData<List<ToDo>>? = null

    init{
        val db =
            TheDatabase.getInstance(
                application.applicationContext
            )
        todoDao = db?.todoDao()
        todos = todoDao?.getTodos()
    }

    fun getTodos(): LiveData<List<ToDo>>?{
        return todos
    }

    fun insert(todo: ToDo) = runBlocking {
        this.launch(Dispatchers.IO){
            todoDao?.insertTodo(todo)
        }
    }

    fun update(todo: ToDo) = runBlocking {
        this.launch(Dispatchers.IO){
            todoDao?.updateTodo(todo)
        }
    }

    fun delete(todo: ToDo) = runBlocking {
        this.launch(Dispatchers.IO){
            todoDao?.deleteTodo(todo)
        }
    }

}