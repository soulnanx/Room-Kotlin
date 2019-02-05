package au.com.vector.study.vector.ui.person

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import au.com.vector.study.vector.App
import au.com.vector.study.vector.R
import au.com.vector.study.vector.data.AppDatabase
import au.com.vector.study.vector.data.entity.Person
import kotlinx.android.synthetic.main.activity_all_people.*
import java.lang.Exception

class AllPeople : AppCompatActivity() {

    private lateinit var viewModel: PersonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_people)
        App.applicationContext().setConnectivityListener(::showInternetStatus)
        setViewModel()
        setEvents()
    }

    private fun setEvents() {
        activity_all_people_add_btn.setOnClickListener { navigateToAddPerson()}
        activity_all_people_swipe.setOnRefreshListener {
            activity_all_people_swipe.isRefreshing = true
            getPeople()
        }
    }

    private fun showInternetStatus(hasConnectivity : Boolean){
        Toast.makeText(baseContext, "has connection = $hasConnectivity", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        getPeople()
    }

    private fun getPeople() {

        viewModel.findAll(
            ::setList,
            ::showError
        )
    }

    private fun navigateToAddPerson() {
        val intent = AddPerson.newIntent(this@AllPeople)
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            AddPerson.CREATED -> getPeople()
        }
    }

    private fun showError(exception: Exception) {
        Snackbar.make(
            activity_all_people_main_view,
            exception.toString(),
            Snackbar.LENGTH_SHORT
        ).show()
        activity_all_people_swipe.isRefreshing = false
    }

    private fun setViewModel() {
        viewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
    }

    private fun setList(people: List<Person>) {
        with(activity_all_people_people_rv){
            adapter = PeopleAdapter(context, people, ::onClick)
            layoutManager = LinearLayoutManager(context)
            activity_all_people_swipe.isRefreshing = false
        }
    }

    private fun onClick(person: Person) {
        Snackbar.make(activity_all_people_main_view, person.description, Snackbar.LENGTH_INDEFINITE).show()
    }
}