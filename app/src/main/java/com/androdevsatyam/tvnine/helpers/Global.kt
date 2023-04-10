package com.androdevsatyam.tvnine.helpers

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class Global {

    companion object {
        fun logs(logtype: String, bundle: Bundle,analytics:FirebaseAnalytics) {
            analytics.logEvent(logtype, bundle)
        }
    }
}