package io.fixmyride.data.enums

object NotificationType {
    const val NOT_SET = 0

    const val TPL_ABOUT_TO_EXPIRE = 1
    const val TPL_EXPIRED = -1

    const val CI_ABOUT_TO_EXPIRE = 2
    const val CI_EXPIRED = -2

    const val INSPECTION_UPCOMING = 3
    const val INSPECTION_EXPIRED = -3
}