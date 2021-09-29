package id.smartech.lovecalculator.ui.result

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import id.smartech.lovecalculator.R
import id.smartech.lovecalculator.base.BaseActivity
import id.smartech.lovecalculator.databinding.ActivityResultBinding

class ResultActivity : BaseActivity<ActivityResultBinding>() {
    private lateinit var viewModel: ResultViewModel
    private var fname: String? = null
    private var sname: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setLayout = R.layout.activity_result
        super.onCreate(savedInstanceState)

        fname = intent.getStringExtra("f_name").toString()
        sname = intent.getStringExtra("s_name").toString()

        setViewModel(sname!!,fname!!)
        subscribeLiveData()
        setStatusBar()
        setData()

    }

    private fun setViewModel(sname: String, fname: String) {
        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)
        viewModel.setService(createApi(this))
        viewModel.getPercentageResult(sname = sname, fname = fname)
        Log.d("viewmodel", "setViewModel")
    }

    private fun loading() {
        bind.containerName.visibility = View.GONE
        bind.percentage.visibility = View.GONE
        bind.lottie2.visibility = View.GONE
        bind.result.visibility = View.GONE
        bind.progressBar.visibility = View.VISIBLE
    }

    private fun notLoading() {
        bind.containerName.visibility = View.VISIBLE
        bind.percentage.visibility = View.VISIBLE
        bind.lottie2.visibility = View.VISIBLE
        bind.result.visibility = View.VISIBLE
        bind.progressBar.visibility = View.GONE
    }

    private fun subscribeLiveData() {
        this.let {
            viewModel.isLoadingLiveData.observe(it) { isLoading ->
                if (isLoading) {
                    loading()
                } else {
                    notLoading()
                }
            }
        }

        this.let {
            viewModel.onSuccessLiveData.observe(it) { result ->
                bind.percentage.text = "${result.percentage}%"
                bind.result.text = result.result
            }
        }

        this.let {
            viewModel.onFailLiveData.observe(it) { message ->
                Log.d("onFail", message)
            }
        }
    }

    private fun setData() {
        bind.fName.text = fname
        bind.sName.text = sname
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