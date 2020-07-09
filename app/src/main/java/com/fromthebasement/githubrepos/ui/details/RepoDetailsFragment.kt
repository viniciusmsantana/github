package com.fromthebasement.githubrepos.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.fromthebasement.githubrepos.R
import com.fromthebasement.githubrepos.base.BaseBindingFragment
import com.fromthebasement.githubrepos.databinding.FragmentRepoDetailsBinding
import com.fromthebasement.githubrepos.ui.adapter.PullRequestAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Shows all Pull Requests of the selected Github Repository
 */
@AndroidEntryPoint
class RepoDetailsFragment : BaseBindingFragment<FragmentRepoDetailsBinding>() {

    override val layoutResource = R.layout.fragment_repo_details

    private val viewModel: RepoDetailsViewModel by viewModels()

    private val args: RepoDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.fetchData(args.repoName, args.repoAuthor)
        binding.repoRecyclerview.adapter = PullRequestAdapter()
        return view
    }
}