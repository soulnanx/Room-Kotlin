package au.com.vector.study.vector.ui.person

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import au.com.vector.study.vector.R
import au.com.vector.study.vector.data.entity.Person
import kotlinx.android.synthetic.main.item_person.view.*

class PeopleAdapter(
    private val context : Context,
    private val people : List<Person>,
    private val onClick : (Person) -> Any
    ) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_person, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(view: ViewHolder, position: Int) {
        val person = people[position]

        view.createdAtTxt.text = person.createdAt.toString()
        view.nameTxt.text = person.name
        view.mainView.setOnClickListener { onClick(person) }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nameTxt = view.item_person_name_txt!!
        val createdAtTxt = view.item_person_created_at_txt!!
        val mainView = view.item_person_main_view!!
    }
}