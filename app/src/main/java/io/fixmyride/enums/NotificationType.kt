package io.fixmyride.enums

enum class NotificationType {
    TPL_EXPIRY,
    CI_EXPIRY,
    UPCOMING_INSPECTION;

    companion object {
        fun from(value: String): NotificationType {
            return when (value) {
                "tpl_expiry" -> TPL_EXPIRY
                "ci_expiry" -> CI_EXPIRY
                else -> UPCOMING_INSPECTION
            }
        }
    }
}