package ru.yourok.torrserve.ui.fragments.main.torrents

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.yourok.torrserve.R
import ru.yourok.torrserve.app.App
import ru.yourok.torrserve.server.models.torrent.Torrent
import ru.yourok.torrserve.ui.activities.play.PlayActivity
import ru.yourok.torrserve.ui.fragments.TSFragment


class TorrentsFragment : TSFragment() {

    private var torrentAdapter: TorrentsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val vi = inflater.inflate(R.layout.main_fragment, container, false)
        torrentAdapter = TorrentsAdapter(requireActivity())
        vi.findViewById<ListView>(R.id.lvTorrents)?.let { lvTorrents ->
            lvTorrents.adapter = torrentAdapter
            lvTorrents.setOnItemClickListener { _, _, i, _ ->
                val torr = torrentAdapter?.getItem(i) as Torrent
                val vintent = Intent(App.context, PlayActivity::class.java)
                vintent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                vintent.action = Intent.ACTION_VIEW
                vintent.putExtra("hash", torr.hash)
                vintent.putExtra("title", torr.title)
                vintent.putExtra("poster", torr.poster)
                vintent.putExtra("action", "play")
                App.context.startActivity(vintent)
            }
        }
        return vi
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            start()
        }
    }


    suspend fun start() = withContext(Dispatchers.Main) {
        viewModel = ViewModelProvider(this@TorrentsFragment).get(TorrentsViewModel::class.java)
        val data = (viewModel as TorrentsViewModel).getData()
        data.observe(this@TorrentsFragment) {
            torrentAdapter?.update(it)
        }
    }
}