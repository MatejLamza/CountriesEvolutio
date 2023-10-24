package matej.lamza.countries.utils.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri


fun Context.openMaps(locationURI: String) {
    val gmmIntentUri = Uri.parse(locationURI)
    startActivity(Intent(Intent.ACTION_VIEW, gmmIntentUri))
}
