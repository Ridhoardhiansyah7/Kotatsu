package org.koitharu.kotatsu.list.ui.filter

import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_checkable_single.*
import org.koitharu.kotatsu.R
import org.koitharu.kotatsu.base.ui.list.BaseViewHolder
import org.koitharu.kotatsu.core.model.MangaTag

class FilterTagHolder(parent: ViewGroup) :
	BaseViewHolder<MangaTag?, Boolean>(parent, R.layout.item_checkable_single) {

	override fun onBind(data: MangaTag?, extra: Boolean) {
		radio.text = data?.title ?: context.getString(R.string.all)
		radio.isChecked = extra
	}
}