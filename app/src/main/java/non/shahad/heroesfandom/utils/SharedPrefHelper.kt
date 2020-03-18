package non.shahad.heroesfandom.utils

import android.content.Context
import android.content.SharedPreferences
import non.shahad.heroesfandom.core.Constants
import java.sql.Time
import java.util.*

class SharedPrefHelper(context: Context) {
    private val pref = context.getSharedPreferences(Constants.Persistence.APP_PREF_NAME,Context.MODE_PRIVATE)
    private val editor = pref.edit()

    fun putTimeoutForMovies(timeOutSeconds : Int){
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.SECOND,timeOutSeconds)
        val timeOutDate = calendar.time
        editor.putLong("timeout",timeOutDate.time)
    }

    fun getTimeoutDate() : Date{
        return Date(pref.getLong("timeout",0))
    }

    fun hasTimeoutExpired() : Boolean{
        val now = Date()
        return (now.after(getTimeoutDate()))
    }

}