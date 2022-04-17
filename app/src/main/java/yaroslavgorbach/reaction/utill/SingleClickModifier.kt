package yaroslavgorbach.reaction.utill

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

object DebounceClick {
    private val debounceState = MutableStateFlow {}
    private var timeout: Long = 250

    init {
        GlobalScope.launch(Dispatchers.Main) {
            debounceState
                .debounce(timeout)
                .collect { onClick ->
                    onClick.invoke()
                }
        }
    }

    fun debounceClicks(timeout: Long, onClick: () -> Unit) {
        this.timeout = timeout
        debounceState.value = onClick
    }
}

fun Modifier.singleClickable(
    timeout: Long = 250,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {

    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = {
            DebounceClick.debounceClicks(timeout) {
                onClick.invoke()
            }
        },
        role = role,
        indication = LocalIndication.current,
        interactionSource = remember { MutableInteractionSource() }
    )
}