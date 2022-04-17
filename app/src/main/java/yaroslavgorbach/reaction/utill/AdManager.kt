package yaroslavgorbach.reaction.utill

import android.app.Activity
import android.app.Application
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import javax.inject.Inject


class AdManager @Inject constructor(private val app: Application) {

    companion object {
        private const val REWARD_AD_ID = "ca-app-pub-6043694180023070/6421069001"

        private const val TEST_REWARD_AD_ID = "ca-app-pub-6043694180023070/6421069001"
    }

    private var rewordAd: RewardedAd? = null

    fun loadRewordAd() {
        val adRequest: AdRequest = AdManagerAdRequest.Builder().build()

        RewardedAd.load(app, REWARD_AD_ID,
            adRequest, object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    rewordAd = null
                }

                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    rewordAd = rewardedAd
                }
            })
    }

    fun showRewardAd(activity: Activity, onReword: () -> Unit) {

        if (rewordAd != null) {
            rewordAd!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdShowedFullScreenContent() {
                    loadRewordAd()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    rewordAd = null
                    loadRewordAd()
                }

                override fun onAdDismissedFullScreenContent() {
                    rewordAd = null
                    loadRewordAd()
                }
            }

            rewordAd!!.show(activity) { rewardItem ->
                onReword()
            }
        }
    }
}