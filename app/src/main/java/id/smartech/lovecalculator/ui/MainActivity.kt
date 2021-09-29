package id.smartech.lovecalculator.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import id.smartech.lovecalculator.R
import id.smartech.lovecalculator.base.BaseActivity
import id.smartech.lovecalculator.databinding.ActivityMainBinding
import id.smartech.lovecalculator.ui.result.ResultActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setLayout = R.layout.activity_main
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setOnClick()
        setStatusBar()
    }

    private fun setOnClick(){
        bind.btnCalculate.setOnClickListener {
            if(bind.fName.text.isNullOrEmpty() || bind.sName.text.isNullOrEmpty()) {
                noticeToast("Your name & your partner name can't be empty")
            } else {
                val sname = bind.sName.text.toString()
                val fname = bind.fName.text.toString()

                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("s_name", sname)
                intent.putExtra("f_name", fname)
                startActivity(intent)
            }

        }
    }

    private fun setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }
}