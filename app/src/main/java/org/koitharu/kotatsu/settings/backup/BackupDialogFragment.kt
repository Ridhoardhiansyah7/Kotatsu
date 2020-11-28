package org.koitharu.kotatsu.settings.backup

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.dialog_progress.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koitharu.kotatsu.R
import org.koitharu.kotatsu.base.ui.AlertDialogFragment
import org.koitharu.kotatsu.utils.ShareHelper
import org.koitharu.kotatsu.utils.ext.getDisplayMessage
import org.koitharu.kotatsu.utils.progress.Progress
import java.io.File

class BackupDialogFragment : AlertDialogFragment(R.layout.dialog_progress) {

	private val viewModel by viewModel<BackupViewModel>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		textView_title.setText(R.string.create_backup)
		textView_subtitle.setText(R.string.processing_)

		viewModel.progress.observe(viewLifecycleOwner, this::onProgressChanged)
		viewModel.onBackupDone.observe(viewLifecycleOwner, this::onBackupDone)
		viewModel.onError.observe(viewLifecycleOwner, this::onError)
	}

	override fun onBuildDialog(builder: AlertDialog.Builder) {
		builder.setCancelable(false)
			.setNegativeButton(android.R.string.cancel, null)
	}

	private fun onError(e: Throwable) {
		AlertDialog.Builder(context ?: return)
			.setNegativeButton(R.string.close, null)
			.setTitle(R.string.error)
			.setMessage(e.getDisplayMessage(resources))
			.show()
		dismiss()
	}

	private fun onProgressChanged(progress: Progress?) {
		with(progressBar) {
			isVisible = true
			isIndeterminate = progress == null
			if (progress != null) {
				this.max = progress.total
				this.progress = progress.value
			}
		}
	}

	private fun onBackupDone(file: File) {
		ShareHelper.shareBackup(context ?: return, file)
		dismiss()
	}

	companion object {

		const val TAG = "BackupDialogFragment"
	}
}