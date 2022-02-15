package br.com.intelbras.view.base

import android.app.AlertDialog
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.intelbras.R
import com.google.android.material.snackbar.Snackbar
import com.kaopiz.kprogresshud.KProgressHUD

open class BaseActivity : AppCompatActivity(){
    private var hud: KProgressHUD? = null

    interface OnCompleteListener {
        fun onComplete()
    }

    public override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    fun openLoading() {
        hud = KProgressHUD.create(this)
            .setLabel(getString(R.string.please_wait))
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
        hud!!.show()

    }

    fun closeLoading() {
        if (hud != null && hud!!.isShowing) hud!!.dismiss()
    }

    fun alert(msg: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton(getString(android.R.string.ok)) { dialog, id -> }
        builder.setMessage(msg)
        val dialog = builder.create()
        dialog.show()
    }

    fun alert(msg: String?, completeListener: OnCompleteListener) {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton(getString(android.R.string.ok)) { dialog, id -> completeListener.onComplete() }
        builder.setMessage(msg)
        val dialog = builder.create()
        dialog.show()
    }

    fun error(view: View?, msg: String?) {
        val snackbar = Snackbar.make(view!!, msg!!, Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.holo_red_dark))
        snackbar.show()
    }
}