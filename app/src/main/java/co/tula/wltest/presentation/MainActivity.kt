package co.tula.wltest.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import co.tula.wltest.R
import co.tula.wltest.domain.AuthStorage
import com.google.android.material.snackbar.Snackbar
import com.hannesdorfmann.adapterdelegates4.paging.PagedListDelegationAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val adapter = PagedListDelegationAdapter(
        PagedListItemCallback(),
        streamAdapterDelegate ({ shareStream(it) }) {
            Snackbar.make(refreshLayout, "Comment button clicked", Snackbar.LENGTH_SHORT).show()
        })

    private val presenter = MainPresenter().apply {
        showError = { Snackbar.make(refreshLayout, it, Snackbar.LENGTH_SHORT).show() }
        initAdapter = ::initAdapter
        afterSignInUpdate = { signInButton.visibility = View.GONE}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = getString(R.string.community)
        refreshLayout.setOnRefreshListener { initAdapter() }
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        signInButton.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(AuthStorage.authUrl)
            )
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onGetData(intent)
    }

    private fun initAdapter() {
        adapter.submitList(PagedList.Builder(
            StreamsDataSource(presenter),
            PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(20).build()
        )
            .setFetchExecutor { it.run() }
            .setNotifyExecutor {
                refreshLayout.isRefreshing = false
                it.run()
            }
            .build())
    }

    private fun shareStream(url: String) {
        startActivity(Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        }, null))
    }
}