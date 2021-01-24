package com.diazzerss.stocks.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.diazzerss.stocks.R
import com.diazzerss.stocks.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {

    @Inject
    lateinit var newsAdapter: NewsAdapter

    private val vm: NewsViewModel by viewModels()

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNewsArticles.apply {
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

        binding.newsRefreshContainer.apply {
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
            binding.newsContent.isVisible = !it
            binding.newsRefreshContainer.isRefreshing = it

        })
    }
}
