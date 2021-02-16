package vi.sukhov.scanner.core.common

sealed class UiStates {
    object Loading : UiStates()
    object Success : UiStates()
    data class Error(var exception: String) : UiStates()
    object EmptyState : UiStates()
}