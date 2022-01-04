package com.example.basicbottomsheet

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.basicbottomsheet.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        bindingMainScreen()
        coroutineWithClickListener()
    }

    fun coroutineWithClickListener() {
        _binding.imageView.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                bindingBeforeDelay()
                delay(5000L)
                bindingAfterDelay()
                openBottomSheet()
            }
        }
    }

    fun bindingMainScreen() {
        with(_binding) {
            tvClickMessage.visibility = View.VISIBLE
            imageView.visibility = View.VISIBLE
        }
    }

    fun bindingBeforeDelay() {
        with(_binding) {
            tvClickMessage.visibility = View.GONE
            imageView.visibility = View.GONE
            ivDelayAnimate.visibility = View.VISIBLE
            tvBottomSheetMessage.visibility = View.VISIBLE
        }
    }

    fun bindingAfterDelay() {
        with(_binding) {
            ivDelayAnimate.visibility = View.GONE
            tvBottomSheetMessage.visibility = View.GONE
        }
    }

    @SuppressLint("InflateParams")
    fun openBottomSheet() {
        val bottomSheet = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet, null)
        val btnClose = view.findViewById<ImageView>(R.id.ivCloseBottomSheet)

        btnClose.setOnClickListener {
            bottomSheet.dismiss()
            bindingMainScreen()
        }

        with(bottomSheet) {
            setCancelable(false)
            setContentView(view)
            show()
        }

    }

}
