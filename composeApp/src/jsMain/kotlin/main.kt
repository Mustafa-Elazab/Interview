import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.roqay.mostafa.App
import com.roqay.mostafa.di.initKoin
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin {}
    onWasmReady {
        CanvasBasedWindow("Interview") {
            App()
        }
    }
}
