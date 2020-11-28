package org.koitharu.kotatsu.tracker.ui.model

import org.koitharu.kotatsu.core.model.Manga

data class FeedItem(
	val id: Long,
	val imageUrl: String,
	val title: String,
	val subtitle: String,
	val chapters: CharSequence,
	val manga: Manga
)