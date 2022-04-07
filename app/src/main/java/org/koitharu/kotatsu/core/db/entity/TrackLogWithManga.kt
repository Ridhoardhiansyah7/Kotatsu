package org.koitharu.kotatsu.core.db.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import java.util.*
import org.koitharu.kotatsu.core.model.TrackingLogItem
import org.koitharu.kotatsu.parsers.util.mapToSet
import org.koitharu.kotatsu.utils.ext.mapToSet

class TrackLogWithManga(
	@Embedded val trackLog: TrackLogEntity,
	@Relation(
		parentColumn = "manga_id",
		entityColumn = "manga_id"
	)
	val manga: MangaEntity,
	@Relation(
		parentColumn = "manga_id",
		entityColumn = "tag_id",
		associateBy = Junction(MangaTagsEntity::class)
	)
	val tags: List<TagEntity>
) {

	fun toTrackingLogItem() = TrackingLogItem(
		id = trackLog.id,
		chapters = trackLog.chapters.split('\n').filterNot { x -> x.isEmpty() },
		manga = manga.toManga(tags.mapToSet { x -> x.toMangaTag() }),
		createdAt = Date(trackLog.createdAt)
	)
}