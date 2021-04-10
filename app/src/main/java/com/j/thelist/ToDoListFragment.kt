package com.j.thelist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ToDoListFragment : Fragment(), ToDoListAdapter.TodoListClickListener {

	private var listener: OnFragmentInteractionListener? = null
	private lateinit var todoListRecyclerView: RecyclerView
	private lateinit var listDataManager: ListDataManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_to_do_list, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val lists = listDataManager.readLists()
		todoListRecyclerView = view.findViewById(R.id.list_recyclerView)
		todoListRecyclerView.layoutManager = LinearLayoutManager(activity)
		todoListRecyclerView.adapter = ToDoListAdapter(lists, this)

	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		if (context is OnFragmentInteractionListener) {
			listener = context
			listDataManager = ListDataManager(context)
		}
	}

	override fun onDetach() {
		super.onDetach()
		listener = null
	}

	interface OnFragmentInteractionListener {
		fun onTodoListClicked(list: TaskList)
	}

	companion object {
		fun newInstance(): ToDoListFragment {
			return ToDoListFragment()
		}
	}

	override fun listItemClicked(list: TaskList) {
		listener?.onTodoListClicked(list)
	}

	fun addList(list: TaskList) {
		listDataManager.saveList(list)
		val todoAdapter = todoListRecyclerView.adapter as ToDoListAdapter
		todoAdapter.addList(list)
	}

	fun saveList(list: TaskList) {
		listDataManager.saveList(list)
		updateLists()
	}

	private fun updateLists() {
		val lists = listDataManager.readLists()
		todoListRecyclerView.adapter = ToDoListAdapter(lists, this)
	}
}