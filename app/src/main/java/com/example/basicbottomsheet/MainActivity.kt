package com.example.basicbottomsheet

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
        _binding.imageView.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                bindingBeforeDelay()
                delay(5000L)
                bindingAfterDelay()
                openBottomSheet()
            }
        }

    }

    fun bindingMainScreen(){
        _binding.textView.visibility = View.VISIBLE
        _binding.imageView.visibility = View.VISIBLE
    }

    fun bindingBeforeDelay(){
        _binding.textView.visibility = View.GONE
        _binding.imageView.visibility = View.GONE
        _binding.ivDelayAnimate.visibility = View.VISIBLE
        _binding.textView2.visibility = View.VISIBLE
    }

    fun bindingAfterDelay(){
        _binding.ivDelayAnimate.visibility = View.GONE
        _binding.textView2.visibility = View.GONE
    }

    fun openBottomSheet() {
        val bottomSheet = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet, null)
        val btnClose = view.findViewById<ImageView>(R.id.ivCloseBottomSheet)

        btnClose.setOnClickListener {
            bottomSheet.dismiss()
            bindingMainScreen()
        }

        bottomSheet.setCancelable(false)
        bottomSheet.setContentView(view)
        bottomSheet.show()
    }

}
