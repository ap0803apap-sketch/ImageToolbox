/*
 * ImageToolbox is an image editor for android
 * Copyright (c) 2026 T8RIN (Malik Mukhametzyanov)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.t8rin.imagetoolbox.feature.pdf_tools.presentation.ai_tools.screenLogic

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.BaseComponent
import com.t8rin.imagetoolbox.core.ui.utils.state.update
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AiPdfToolComponent @AssistedInject internal constructor(
    @Assisted val type: Type,
    @Assisted initialUri: Uri?,
    @Assisted val onGoBack: () -> Unit,
    @Assisted componentContext: ComponentContext,
    dispatchersHolder: DispatchersHolder
) : BaseComponent(dispatchersHolder, componentContext) {

    private val _uri: MutableState<Uri?> = mutableStateOf(initialUri)
    val uri by _uri

    val titleRes: Int = type.titleRes
    val subtitleRes: Int = type.subtitleRes
    val headlineRes: Int = type.headlineRes
    val workflowSteps: List<Int> = type.workflowSteps
    val whyItMattersRes: Int = type.whyItMattersRes
    val nextStepRes: Int = type.nextStepRes

    fun setUri(uri: Uri?) {
        _uri.update { uri }
    }

    enum class Type(
        val titleRes: Int,
        val subtitleRes: Int,
        val headlineRes: Int,
        val workflowSteps: List<Int>,
        val whyItMattersRes: Int,
        val nextStepRes: Int
    ) {
        Summary(
            titleRes = R.string.pdf_ai_summary,
            subtitleRes = R.string.pdf_ai_summary_sub,
            headlineRes = R.string.pdf_ai_summary_headline,
            workflowSteps = listOf(
                R.string.pdf_ai_step_extract_text,
                R.string.pdf_ai_step_chunk_locally,
                R.string.pdf_ai_step_generate_summary
            ),
            whyItMattersRes = R.string.pdf_ai_summary_why,
            nextStepRes = R.string.pdf_ai_summary_next
        ),
        Chat(
            titleRes = R.string.pdf_ai_chat,
            subtitleRes = R.string.pdf_ai_chat_sub,
            headlineRes = R.string.pdf_ai_chat_headline,
            workflowSteps = listOf(
                R.string.pdf_ai_step_extract_text,
                R.string.pdf_ai_step_build_local_context,
                R.string.pdf_ai_step_answer_questions
            ),
            whyItMattersRes = R.string.pdf_ai_chat_why,
            nextStepRes = R.string.pdf_ai_chat_next
        ),
        KeyPoints(
            titleRes = R.string.pdf_ai_key_points,
            subtitleRes = R.string.pdf_ai_key_points_sub,
            headlineRes = R.string.pdf_ai_key_points_headline,
            workflowSteps = listOf(
                R.string.pdf_ai_step_extract_text,
                R.string.pdf_ai_step_detect_entities,
                R.string.pdf_ai_step_group_highlights
            ),
            whyItMattersRes = R.string.pdf_ai_key_points_why,
            nextStepRes = R.string.pdf_ai_key_points_next
        ),
        StudyNotes(
            titleRes = R.string.pdf_ai_study_notes,
            subtitleRes = R.string.pdf_ai_study_notes_sub,
            headlineRes = R.string.pdf_ai_study_notes_headline,
            workflowSteps = listOf(
                R.string.pdf_ai_step_extract_text,
                R.string.pdf_ai_step_create_notes,
                R.string.pdf_ai_step_export_flashcards
            ),
            whyItMattersRes = R.string.pdf_ai_study_notes_why,
            nextStepRes = R.string.pdf_ai_study_notes_next
        ),
        ChapterSummary(
            titleRes = R.string.pdf_ai_chapter_summary,
            subtitleRes = R.string.pdf_ai_chapter_summary_sub,
            headlineRes = R.string.pdf_ai_chapter_summary_headline,
            workflowSteps = listOf(
                R.string.pdf_ai_step_extract_text,
                R.string.pdf_ai_step_detect_sections,
                R.string.pdf_ai_step_summarize_per_chapter
            ),
            whyItMattersRes = R.string.pdf_ai_chapter_summary_why,
            nextStepRes = R.string.pdf_ai_chapter_summary_next
        ),
        ExplainPage(
            titleRes = R.string.pdf_ai_explain_page,
            subtitleRes = R.string.pdf_ai_explain_page_sub,
            headlineRes = R.string.pdf_ai_explain_page_headline,
            workflowSteps = listOf(
                R.string.pdf_ai_step_extract_current_page,
                R.string.pdf_ai_step_simplify_language,
                R.string.pdf_ai_step_show_answer
            ),
            whyItMattersRes = R.string.pdf_ai_explain_page_why,
            nextStepRes = R.string.pdf_ai_explain_page_next
        ),
        Classify(
            titleRes = R.string.pdf_ai_classify,
            subtitleRes = R.string.pdf_ai_classify_sub,
            headlineRes = R.string.pdf_ai_classify_headline,
            workflowSteps = listOf(
                R.string.pdf_ai_step_extract_text,
                R.string.pdf_ai_step_detect_document_type,
                R.string.pdf_ai_step_suggest_next_action
            ),
            whyItMattersRes = R.string.pdf_ai_classify_why,
            nextStepRes = R.string.pdf_ai_classify_next
        )
    }

    @AssistedFactory
    fun interface Factory {
        operator fun invoke(
            type: Type,
            initialUri: Uri?,
            onGoBack: () -> Unit,
            componentContext: ComponentContext
        ): AiPdfToolComponent
    }
}
