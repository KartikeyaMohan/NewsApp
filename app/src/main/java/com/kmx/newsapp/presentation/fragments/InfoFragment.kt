package com.kmx.newsapp.presentation.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.kmx.newsapp.R
import com.kmx.newsapp.databinding.FragmentInfoBinding
import com.kmx.newsapp.presentation.MainActivity
import com.kmx.newsapp.presentation.viewModel.NewsViewModel

class InfoFragment : Fragment() {

    private lateinit var fragmentInfoBinding: FragmentInfoBinding
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentInfoBinding = FragmentInfoBinding.bind(view)
        val args: InfoFragmentArgs by navArgs()
        val article = args.selectedArticle
        viewModel = (activity as MainActivity).viewModel
        fragmentInfoBinding.webView.apply {
            webViewClient = WebViewClient()
            if (!TextUtils.isEmpty(article.url)) {
                loadUrl(article.url!!)
            }
        }
        fragmentInfoBinding.saveButton.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Saved successfully!", Snackbar.LENGTH_LONG).show()
        }
    }
}