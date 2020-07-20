package com.diazzerss.stocks.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.diazzerss.stocks.BaseFragment
import com.diazzerss.stocks.databinding.FragmentNewsBinding
import com.diazzerss.stocks.utils.getViewModel
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : BaseFragment() {

    private val vm by lazy { getViewModel<NewsViewModel>() }

    private var articleAdapter: ArticleAdapter = ArticleAdapter()

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
            adapter = articleAdapter
        }

        vm.getArticles()
        vm.article.observe(viewLifecycleOwner, Observer {
            articleAdapter.addData(it)
        })

    }

    override fun showContent() {
        super.showContent()

    }

    override fun showProgress(progress: Boolean) {
        super.showProgress(progress)
    }

    override fun showError(error: Boolean) {
        super.showError(error)
    }
}
