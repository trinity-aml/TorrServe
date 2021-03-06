package ru.yourok.torrserve.utils

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import ru.yourok.torrserve.app.App


class Player(var Name: String, var Package: String) {
    override fun toString(): String {
        return Name
    }
}

object Players {
    fun getList(): MutableList<Player> {
        val list = mutableListOf<Player>()
        list.addAll(getList("video/*"))
        list.addAll(getList("audio/*"))
        return list.distinctBy { it.Package }.sortedBy { it.Name }.toMutableList()
    }

    private fun getList(mime: String): List<Player> {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.parse("http://127.0.0.1:8091/torrent/file.mp4"), mime)
        val apps = App.getContext().packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        val list = mutableListOf<Player>()
        for (a in apps) {
            val name = a.loadLabel(App.getContext().packageManager)?.toString() ?: a.activityInfo.packageName
            list.add(Player(name, a.activityInfo.packageName))
        }
        return list
    }
}