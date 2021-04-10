package com.j.thelist

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ToDoListFragment.OnFragmentInteractionListener {

	private var todoListFragment = ToDoListFragment.newInstance()

	companion object {
		const val INTENT_LIST_KEY = "list"
		const val LIST_DETAIL_REQUEST_CODE = 123
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
			showCreateTodoListDialog()
		}
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.menu_main, menu)
		return true
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (requestCode == LIST_DETAIL_REQUEST_CODE) {
			data?.let {
				val list = data.getParcelableExtra<TaskList>(INTENT_LIST_KEY)!!
				todoListFragment.saveList(list)
			}
		}
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return when (item.itemId) {
			R.id.action_settings -> true
			else -> super.onOptionsItemSelected(item)
		}
	}

	private fun showCreateTodoListDialog() {

		val dialogTitle = getString(R.string.name_of_list)
		val positiveButtonTitle = getString(R.string.create)
		val myDialog = AlertDialog.Builder(this)
		val todoTitleEditText = EditText(this)
		todoTitleEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

		myDialog.setTitle(dialogTitle)
		myDialog.setView(todoTitleEditText)

		myDialog.setPositiveButton(positiveButtonTitle) {
				dialog, _ ->
			val list = TaskList(todoTitleEditText.text.toString())
			todoListFragment.addList(list)
			dialog.dismiss()
			showTaskListItems(list)
		}
		myDialog.create().show()
	}

	private fun showTaskListItems(list: TaskList) {
		val taskListItem = Intent(this, DetailActivity::class.java)
		taskListItem.putExtra(INTENT_LIST_KEY, list)
		startActivityForResult(taskListItem, LIST_DETAIL_REQUEST_CODE)
	}

	override fun onTodoListClicked(list: TaskList) {
		showTaskListItems(list)
	}

}