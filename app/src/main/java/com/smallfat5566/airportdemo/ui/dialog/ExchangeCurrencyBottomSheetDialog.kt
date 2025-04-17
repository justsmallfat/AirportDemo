package com.smallfat5566.airportdemo.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smallfat5566.airportdemo.R
import com.smallfat5566.airportdemo.databinding.FragmentDialogExchangeCurrencyBottomSheetBinding

/**
 * TODO: document your custom view class.
 */
class ExchangeCurrencyBottomSheetDialog(): BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDialogExchangeCurrencyBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        // 初始化 ViewBinding
        binding = FragmentDialogExchangeCurrencyBottomSheetBinding.inflate(layoutInflater)

        // 示例：設置按鈕點擊事件
        binding.closeButton.setOnClickListener {
            dismiss() // 點擊按鈕關閉對話框
        }

        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.BottomSheetFullHeightDialog
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): BottomSheetDialog {
        return BottomSheetDialog(requireContext(), theme)
    }



}