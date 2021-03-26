package com.j.thelist

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

class MainActivity : AppCompatActivity()
{

	private lateinit var toDoListRecyclerView: RecyclerView
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setSupportActionBar(findViewById(R.id.toolbar))

		toDoListRecyclerView = findViewById<RecyclerView>(R.id.list_recyclerView)
		toDoListRecyclerView.layoutManager = LinearLayoutManager(this)
		toDoListRecyclerView.adapter = ToDoListAdapter()

		findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
			showCreateTodListDialog()
		}
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.menu_main, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return when (item.itemId)
		{
			R.id.action_settings -> true
			else -> super.onOptionsItemSelected(item)
		}
	}

	private fun showCreateTodListDialog(){
		val dialogTitle = getString(R.string.name_of_list)
		val positiveButtonTitle = getString(R.string.create)
		val myDialog = AlertDialog.Builder(this)
		val toDoListEditText = EditText(this)
		toDoListEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
		myDialog.setTitle(dialogTitle)
		myDialog.setView(toDoListEditText)

		myDialog.setPositiveButton(positiveButtonTitle){
			dialog, _ ->
			val adapter = toDoListRecyclerView.adapter as ToDoListAdapter
			adapter.addNewItem(toDoListEditText.text.toString())
			dialog.dismiss()
		}
		myDialog.create().show()

	}
}