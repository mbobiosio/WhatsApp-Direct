package com.mbobiosio.eazychat.util

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
const val CACHE_INTERVAL = 3000L

object WhatsAppPackages {
    const val whatsApp = "com.whatsapp"
    const val yoWhatsapp = "com.yowhatsapp"
    const val gbWhatsapp = "com.gbwhatsapp"
    const val fmWWhatsapp = "com.fmwhatsapp"
    const val whatsAppBusiness = "com.whatsapp.w4b"
}

object DefaultConfigs {
    fun getDefaultParams(): Map<String, Any> {
        return hashMapOf(
            ConfigKeys.FORCE_UPDATE to false,
            ConfigKeys.DESCRIPTION to "Check update"
        )
    }

    object ConfigKeys {
        const val FORCE_UPDATE = "force_update"
        const val DESCRIPTION = "message"
    }
}
