package com.diazzerss.stocks.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.diazzerss.stocks.R
import com.diazzerss.stocks.databinding.FragmentNewsBinding
import com.diazzerss.stocks.utils.getViewModel
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    private val vm by lazy { getViewModel<NewsViewModel>() }

    private var newsAdapter: NewsAdapter = NewsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentNewsBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_news_articles.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }

        newsAdapter.onItemClick = {
            Navigation
                .findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(
                    R.id.navigation_article, bundleOf("url" to it.url)
                )
        }

        news_refreshContainer.apply {
            setColorSchemeResources(R.color.colorPrimary)
            setOnRefreshListener {
                vm.getArticles()

            }


        }
        bindViewModel()

    }

    private fun bindViewModel() {
        vm.article.observe(viewLifecycleOwner, Observer {
            newsAdapter.addData(it)
        })
        vm.progress.observe(viewLifecycleOwner, Observer {
            news_content.isVisible = !it
            news_refreshContainer.isRefreshing = it

        })
    }
}
