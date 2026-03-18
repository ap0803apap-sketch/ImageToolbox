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

package com.t8rin.imagetoolbox.feature.pdf_tools.presentation.ai_tools

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.FileOpen
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.model.MimeType
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.rememberFilePicker
import com.t8rin.imagetoolbox.core.ui.widget.AdaptiveLayoutScreen
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedButton
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItem
import com.t8rin.imagetoolbox.core.ui.widget.text.TitleItem
import com.t8rin.imagetoolbox.core.utils.filename
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.ai_tools.screenLogic.AiPdfToolComponent

@Composable
fun AiPdfToolContent(
    component: AiPdfToolComponent
) {
    val picker = rememberFilePicker(
        mimeType = MimeType.Pdf,
        onSuccess = { uri: Uri -> component.setUri(uri) }
    )

    AdaptiveLayoutScreen(
        title = {
            TitleItem(
                text = stringResource(component.titleRes),
                icon = Icons.Outlined.AutoAwesome,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        },
        onGoBack = component.onGoBack,
        shouldDisableBackHandler = true,
        controls = {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    PreferenceItem(
                        title = stringResource(R.string.pdf_ai_offline_ready),
                        subtitle = stringResource(R.string.pdf_ai_local_first),
                        startIcon = Icons.Outlined.CheckCircle,
                        onClick = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    PreferenceItem(
                        title = stringResource(R.string.pdf_ai_pick_pdf),
                        subtitle = component.uri?.filename() ?: stringResource(R.string.pdf_ai_no_pdf_selected),
                        startIcon = Icons.Outlined.FileOpen,
                        onClick = picker::pickFile,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    Column(
                        modifier = Modifier.padding(top = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = stringResource(component.headlineRes),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = stringResource(component.subtitleRes),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                item {
                    Text(
                        text = stringResource(R.string.pdf_ai_workflow),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                itemsIndexed(component.workflowSteps) { index, stepRes ->
                    PreferenceItem(
                        title = "${index + 1}. ${stringResource(stepRes)}",
                        subtitle = null,
                        startIcon = Icons.Outlined.AutoAwesome,
                        onClick = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    PreferenceItem(
                        title = stringResource(R.string.pdf_ai_why_it_matters),
                        subtitle = stringResource(component.whyItMattersRes),
                        startIcon = Icons.Outlined.AutoAwesome,
                        onClick = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    PreferenceItem(
                        title = stringResource(R.string.pdf_ai_next_step),
                        subtitle = stringResource(component.nextStepRes),
                        startIcon = Icons.Outlined.AutoAwesome,
                        onClick = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    EnhancedButton(
                        onClick = picker::pickFile,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = if (component.uri == null) {
                                stringResource(R.string.pdf_ai_pick_pdf)
                            } else {
                                stringResource(R.string.pick_file)
                            }
                        )
                    }
                }
            }
        },
        imagePreview = {},
        showImagePreviewAsStickyHeader = false,
        placeImagePreview = false,
        noDataControls = {},
        actions = {},
        buttons = {}
    )
}
