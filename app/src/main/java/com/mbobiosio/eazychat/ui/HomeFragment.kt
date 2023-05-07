package com.mbobiosio.eazychat.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mbobiosio.eazychat.App
import com.mbobiosio.eazychat.R
import com.mbobiosio.eazychat.databinding.FragmentHomeBinding
import com.mbobiosio.eazychat.util.WhatsAppPackages.fmWWhatsapp
import com.mbobiosio.eazychat.util.WhatsAppPackages.gbWhatsapp
import com.mbobiosio.eazychat.util.WhatsAppPackages.whatsApp
import com.mbobiosio.eazychat.util.WhatsAppPackages.whatsAppBusiness
import com.mbobiosio.eazychat.util.WhatsAppPackages.yoWhatsapp
import com.mbobiosio.eazychat.util.hideKeyboard
import com.mbobiosio.eazychat.util.isAppInstalled
import com.mbobiosio.eazychat.util.showToast
import com.mbobiosio.eazychat.util.whatsappUri
import www.sanju.motiontoast.MotionToastStyle

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        validateInputs()

        binding.apply {
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

    private fun validateInputs() {
        binding.apply {

            countryCodePicker.registerCarrierNumberEditText(phoneEditText)

            countryCodePicker.setPhoneNumberValidityChangeListener {
                whatsAppBtn.isEnabled = it
                whatsAppBusinessBtn.isEnabled = it
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

        App.trackSendMessageEvent(id = "send", name = "Button", type = packageName)
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
