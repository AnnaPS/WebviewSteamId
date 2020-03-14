package com.novadev.webviewsteamid


import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.novadev.webviewsteamid.Constants.MY_PREFERENCES
import com.novadev.webviewsteamid.Constants.REALM_PARAM
import com.novadev.webviewsteamid.Constants.URL
import com.novadev.webviewsteamid.Constants.USER_ID
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {
    private var userId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setWebView()
    }


    private fun putSharedPreferences(){
        val editor: SharedPreferences.Editor = activity?.getSharedPreferences(
            MY_PREFERENCES,
            MODE_PRIVATE
        )!!.edit()
        editor.putString(USER_ID, userId)
        editor.apply()
    }


    private fun setWebView(){
        webView.settings.javaScriptEnabled = true

        webView.loadUrl(URL)

        webView.webViewClient = object:  WebViewClient(){
            override fun onPageStarted(viewW: WebView?, url: String?, favicon: Bitmap?) {
                activity?.title = "Steam Login"
                val _url: Uri = Uri.parse(url)
                if(_url.authority.equals(REALM_PARAM.toLowerCase())){
                    // That means that authentication is finished and the url contains user's id.
                    webView.stopLoading()

                    // Extracts user id.
                    var  userAccountUrl = Uri.parse(_url.getQueryParameter("openid.identity"));
                    userId = userAccountUrl.lastPathSegment.toString()

                    // Do whatever you want with the user's steam id
                    putSharedPreferences()
                    findNavController().navigate(R.id.action_mainFragment_to_nextActivity)
                    webView.destroy()
                    activity?.finish()

                }

            }

        }
    }
}
