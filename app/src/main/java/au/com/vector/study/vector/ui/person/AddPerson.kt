package au.com.vector.study.vector.ui.person

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import au.com.vector.study.vector.R
import au.com.vector.study.vector.data.entity.Person
import kotlinx.android.synthetic.main.activity_add_person.*
import java.lang.Exception

class AddPerson: AppCompatActivity() {

    private lateinit var viewModel: PersonViewModel

    companion object {
        const val CREATED = 201

        fun newIntent(context: Context) : Intent {
            return Intent(context, AddPerson::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person)
        setViewModel()
        setEvents()
    }

    private fun setViewModel() {
        viewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
    }

    private fun setEvents() {
        activity_add_person_create_btn.setOnClickListener { tryToPerson() }
    }

    private fun tryToPerson() {
        try {
            viewModel.create(buildPerson(), ::onSuccess, ::onFailure)
        } catch (exception : Exception){
            onFailure(exception)
        }
    }

    private fun onSuccess(id : Long) {
        setResult(AddPerson.CREATED)
        finish()
    }

    private fun onFailure(exception: Exception){
        Snackbar.make(
            activity_add_person_main_view,
            exception.toString(),
            Snackbar.LENGTH_INDEFINITE
        ).show()
    }

    private fun buildPerson(): Person {
        val nameValue = activity_add_person_name_edt.text.toString()
        val descriptionValue = activity_add_person_description_edt.text.toString()
        return when {
            nameValue.isNullOrEmpty() ->
                throw Exception("you have to fill the person name!")
            descriptionValue.isNullOrEmpty() ->
                throw Exception("you have to fill the description name!")
            else -> Person(
                        name = nameValue,
                        description = descriptionValue
                    )
        }
    }
}