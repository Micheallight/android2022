package com.example.exchangerates.db

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.exchangerates.MainActivity


class DeleteDataDialogFragment(private val listener: OnDismissDialogListener): DialogFragment() {

    interface OnDismissDialogListener {
        fun onDismiss()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val exchangeDao = arguments?.getSerializable("exchangeDao")!! as ExchangeDao

        return AlertDialog.Builder(activity)
            .setTitle("Are you sure you want to delete all data?")
            .setPositiveButton("Yes") { _, _ -> exchangeDao.deleteAll(); listener.onDismiss() }
            .setNegativeButton("No", null)
            .create()
    }
}