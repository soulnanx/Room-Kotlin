package au.com.vector.study.vector.ui.person

import android.arch.lifecycle.ViewModel
import android.arch.persistence.room.Room
import au.com.vector.study.vector.App
import au.com.vector.study.vector.data.AppDatabase
import au.com.vector.study.vector.data.entity.Person
import au.com.vector.study.vector.data.repository.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class PersonViewModel : ViewModel() {
    private val db = AppDatabase.getInstance(App.applicationContext())!! //warning!!!
    private val repository = PersonRepository(db)

    fun create(
        person: Person,
        success: (Long) -> Any,
        failure: (Exception) -> Any
    ) {

        GlobalScope.launch(Dispatchers.Main + SupervisorJob()) {
            try {
                val idReturn = repository.add(person)
                success(idReturn)
            } catch (e: Exception) {
                failure(e)
            }
        }
    }

    fun findAll(
        success: (List<Person>) -> Any,
        failure: (Exception) -> Any
    ) {
        GlobalScope.launch(Dispatchers.Main + SupervisorJob()) {
            try {
                val people = repository.getPeople()
                success(people)
            } catch (e: Exception) {
                failure(e)
            }
        }
    }

}