package com.novadev.webviewsteamid


import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.novadev.webviewsteamid.Constants.MY_PREFERENCES
import com.novadev.webviewsteamid.Constants.USER_ID
import kotlinx.android.synthetic.main.fragment_next.*


class NextFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_next, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textview.text = getPreferences()?.getString(USER_ID, "No userid defined")
    }


    private fun getPreferences(): SharedPreferences? =
        activity?.getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE)


}
