package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import ru.skillbranch.devintensive.R

//скрыть клавиатуру
fun Activity.hideKeyboard(){
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = this.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

//Клавиатура открыта?
fun Activity.isKeyboardOpen():Boolean{
    val r = Rect()
    val view = this.findViewById<View>(R.id.main)
    view.getWindowVisibleDisplayFrame(r)
    Log.i("keyboard", "${r.bottom}")
    Log.i("keyboard", "${r.top}")
    Log.i("keyboard", "${view.height}")
    val heightDiff = view.height - (r.bottom - r.top)
    if (heightDiff > 100) {
        return true
    }
    return false
}

//Клава закрыта?
fun Activity.isKeyboardClosed():Boolean{
    val r = Rect()
    val view = this.findViewById<View>(R.id.main)
    view.getWindowVisibleDisplayFrame(r)
    Log.i("keyboard", "${r.bottom}")
    Log.i("keyboard", "${r.top}")
    Log.i("keyboard", "${view.height}")
    val heightDiff = view.height - (r.bottom - r.top)
    if (heightDiff > 100) {
        return false
    }
    return true


}


