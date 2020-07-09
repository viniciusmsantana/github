package com.fromthebasement.githubrepos.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fromthebasement.githubrepos.R
import com.fromthebasement.githubrepos.base.BaseBindingFragment
import com.fromthebasement.githubrepos.databinding.FragmentRepoListBinding
import com.fromthebasement.githubrepos.ui.adapter.RepoListAdapter
import com.fromthebasement.githubrepos.ui.adapter.RepoListAdapterOnClick
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Shows a list of Github Repositories
 * Clicking on a Repository navigates to its Details screen
 */
@AndroidEntryPoint
class RepoListFragment : BaseBindingFragment<FragmentRepoListBinding>(), RepoListAdapterOnClick {

    override val layoutResource = R.layout.fragment_repo_list

    private val viewModel: RepoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        binding.repoRecyclerview.adapter = RepoListAdapter(this)

        return view
    }

    //Function to be implemented for adapter click interface
    override fun onClick(repo: Pair<String, String>) {
        val (repoName, repoAuthor) = repo
        val action =
            RepoListFragmentDirections.toOrderDetailsFragment(repoName, repoAuthor)
        findNavController().navigate(action)
    }
}