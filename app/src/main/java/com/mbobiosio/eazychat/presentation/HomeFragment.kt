package com.mbobiosio.eazychat.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.firebase.analytics.FirebaseAnalytics
import com.mbobiosio.eazychat.BuildConfig
import com.mbobiosio.eazychat.R
import com.mbobiosio.eazychat.data.model.RemoteConfigs
import com.mbobiosio.eazychat.databinding.FragmentHomeBinding
import com.mbobiosio.eazychat.util.WhatsAppPackages.fmWWhatsapp
import com.mbobiosio.eazychat.util.WhatsAppPackages.gbWhatsapp
import com.mbobiosio.eazychat.util.WhatsAppPackages.whatsApp
import com.mbobiosio.eazychat.util.WhatsAppPackages.whatsAppBusiness
import com.mbobiosio.eazychat.util.WhatsAppPackages.yoWhatsapp
import com.mbobiosio.eazychat.util.alertDialog
import com.mbobiosio.eazychat.util.hideKeyboard
import com.mbobiosio.eazychat.util.isAppInstalled
import com.mbobiosio.eazychat.util.logEngagement
import com.mbobiosio.eazychat.util.negativeButton
import com.mbobiosio.eazychat.util.openAppInGooglePlay
import com.mbobiosio.eazychat.util.positiveButton
import com.mbobiosio.eazychat.util.showToast
import com.mbobiosio.eazychat.util.whatsappUri
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle
import javax.inject.Inject

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<RemoteConfigViewModel>()

    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

        observeRemoteConfig()
    }

    private fun setupViews() {
        binding.apply {

            countryCodePicker.registerCarrierNumberEditText(phoneEditText)

            countryCodePicker.setPhoneNumberValidityChangeListener {
                whatsAppBtn.isEnabled = it
                whatsAppBusinessBtn.isEnabled = it
            }

            whatsAppBtn.setOnClickListener {
                it.hideKeyboard()
                whatsApp()
            }
            whatsAppBusinessBtn.setOnClickListener {
                it.hideKeyboard()
                whatsAppBusiness()
            }
        }
    }

    private fun observeRemoteConfig() {
        lifecycleScope.launchWhenStarted {
            viewModel.remoteConfigStateFlow.collect {
                updateUI(it)
            }
        }
    }

    private fun updateUI(remoteConfigs: RemoteConfigs) {
        val versionCode = remoteConfigs.versionCode
        val forceUpdate = remoteConfigs.forceUpdate
        val title = getString(R.string.app_update_default_title)
        val description = remoteConfigs.message
        when {
            versionCode > BuildConfig.VERSION_CODE -> {
                activity?.alertDialog(style = R.style.ThemeOverlay_App_MaterialAlertDialog) {
                    setTitle(title)
                    setMessage(description)
                    setIcon(R.mipmap.ic_launcher)
                    positiveButton(
                        text = getString(R.string.app_update_positive_text)
                    ) {
                        activity?.openAppInGooglePlay()
                    }
                    if (forceUpdate.not()) {
                        negativeButton(
                            text = getString(R.string.app_update_cancel)
                        ) {
                            return@negativeButton
                        }
                    }
                }
            }

            else -> {
                //Timber.d("You have the current version")
            }
        }
    }

    private fun getData(): Uri {
        binding.apply {
            val countryCode = countryCodePicker.selectedCountryCode.toString()
            val number = phoneEditText.text
            val phone = countryCode.plus(number)

            val messageText = messageInput.text.toString()
            return whatsappUri(phoneNumber = phone, message = messageText)
        }
    }

    private fun openTarget(packageName: String) {
        val uri = getData()
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setPackage(packageName)
        intent.data = uri
        startActivity(intent)

        firebaseAnalytics.logEngagement(id = "send", name = "Button", type = packageName)
    }

    private fun whatsApp() {
        activity?.let {
            when {
                it.isAppInstalled(whatsApp) -> openTarget(whatsApp)
                it.isAppInstalled(yoWhatsapp) -> openTarget(yoWhatsapp)
                it.isAppInstalled(fmWWhatsapp) -> openTarget(fmWWhatsapp)
                it.isAppInstalled(gbWhatsapp) -> openTarget(gbWhatsapp)
                else -> it.showToast(
                    getString(R.string.no_whatsapp_installed),
                    MotionToastStyle.ERROR
                )
            }
        }
    }

    private fun whatsAppBusiness() {
        activity?.let {
            if (it.isAppInstalled(whatsAppBusiness)) {
                openTarget(whatsAppBusiness)
            } else {
                it.showToast(getString(R.string.no_wa_business_installed), MotionToastStyle.ERROR)
            }
        }
    }
}
