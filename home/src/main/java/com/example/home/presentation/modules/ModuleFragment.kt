package com.example.home.presentation.modules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.launch

class ModuleFragment : Fragment() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val modalSheetState =
                    rememberModalBottomSheetState(
                        initialValue = ModalBottomSheetValue.HalfExpanded
                    )
                val coroutineScope = rememberCoroutineScope()
                val list = arrayListOf<Int>()
                repeat(200) { list += it }

                // Take action based on hidden state
                LaunchedEffect(modalSheetState.currentValue) {
                    if (modalSheetState.currentValue == ModalBottomSheetValue.Hidden) {
                        modalSheetState.show()
                    }
                }

                ModalBottomSheetLayout(
                    sheetContent = {
                        LazyColumn(
                            Modifier.statusBarsPadding()
                        ) {
                            itemsIndexed(list) { index, launch ->
                                Text(text = "Bottom Sheet Content")
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    },
                    sheetState = modalSheetState,
                    sheetElevation = 0.dp,
                    sheetShape = RoundedCornerShape(10.dp),
                    sheetBackgroundColor = ModalBottomSheetDefaults.scrimColor,
                    scrimColor = Color.Transparent
                ) {
                    Column(
                        Modifier
                            .statusBarsPadding()
                            .padding(bottom = 68.dp)
                    ) {
                        Text(text = "modal bottom sheet screen")
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { coroutineScope.launch { modalSheetState.show() } }) {
                            Text(
                                text = "Modal sheet "
                            )
                        }
                    }
                }
            }
        }
    }
}