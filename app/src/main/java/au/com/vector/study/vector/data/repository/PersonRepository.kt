package au.com.vector.study.vector.data.repository

import au.com.vector.study.vector.data.AppDatabase
import au.com.vector.study.vector.data.entity.Person
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class PersonRepository (private val db : AppDatabase){
    suspend fun getPeople() : List<Person>{
        return GlobalScope.async {
            db.personDao().getAll()
        }.await()
    }

    suspend fun add(person: Person) : Long{
        return GlobalScope.async {
            db.personDao().create(person)
        }.await()
    }
}