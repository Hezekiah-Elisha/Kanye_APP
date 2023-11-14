import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olefaent.kanyeapp.ui.screens.KanyeScreen
import com.olefaent.kanyeapp.ui.screens.KanyeViewModel

@Composable
fun KanyeApp() {
    val uiState: KanyeViewModel = viewModel(factory = KanyeViewModel.Factory)
    KanyeScreen(
        uiState = uiState.uiState,
        retryAction = uiState::getQuote
    )

    Log.d("kanye_log", "KanyeApp: ${uiState.uiState}")
}