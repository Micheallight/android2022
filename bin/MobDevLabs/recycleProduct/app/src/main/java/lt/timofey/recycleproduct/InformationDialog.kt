package lt.timofey.recycleproduct

//import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class InformationDialog: DialogFragment() {

     fun onCreateDialog(savedInstanceState: Bundle?, name: String, description: String): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(name)
                .setMessage("Hello, ${description}")
                .setPositiveButton("ОК") {
                        dialog, id ->  dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}