package io.fixmyride.enums

enum class NotificationType {
    TPL_EXPIRY,
    CI_EXPIRY;

    companion object {
        fun from(value: String): NotificationType {
            return when (value) {
                "tpl_expiry" -> TPL_EXPIRY
                else -> CI_EXPIRY
            }
        }
    }
}