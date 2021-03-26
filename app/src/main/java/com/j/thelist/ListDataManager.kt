package com.j.thelist

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


class ListDataManager(private val context: Context)
{
	fun saveList(list: TaskList){
		val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context).edit()
		sharedPrefs.putStringSet(list.name, list.tasks.toHashSet())
		sharedPrefs.apply()
	}

	fun readLists(): ArrayList<TaskList>{
		val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
		val contents = sharedPreferences.all
		val taskLists = ArrayList<TaskList>()

		for(taskList in contents){
			val taskItems = ArrayList(taskList.value as HashSet<String>)
			val list = TaskList(taskList.key, taskItems)
			taskLists.add(list)
		}

		return taskLists

	}
}