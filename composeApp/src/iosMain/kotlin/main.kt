import androidx.compose.ui.window.ComposeUIViewController
import com.roqay.mostafa.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
