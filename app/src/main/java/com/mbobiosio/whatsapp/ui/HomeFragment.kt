package com.mbobiosio.whatsapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mbobiosio.whatsapp.databinding.FragmentHomeBinding
import com.mbobiosio.whatsapp.util.WhatsAppPackages.whatsApp
import com.mbobiosio.whatsapp.util.WhatsAppPackages.whatsAppBusiness
import com.mbobiosio.whatsapp.util.WhatsAppPackages.yoWhatsapp
import com.mbobiosio.whatsapp.util.isAppInstalled
import com.mbobiosio.whatsapp.util.isValidPhone
import com.mbobiosio.whatsapp.util.logd
import com.mbobiosio.whatsapp.util.textChangeListener
import com.mbobiosio.whatsapp.util.whatsappUri

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
                whatsApp()
            }
            whatsAppBusinessBtn.setOnClickListener {
                whatsAppBusiness()
            }
        }
    }

    private fun validateInputs() {
        binding.apply {
            phoneEditText.textChangeListener(
                afterTextChanged = {
                    whatsAppBtn.isEnabled =
                        it.toString().isNotEmpty() and it.toString().isValidPhone()
                }
            )
            phoneEditText.textChangeListener {
                whatsAppBusinessBtn.isEnabled =
                    it.toString().isNotEmpty() and it.toString().isValidPhone()
            }
        }
    }

    private fun getData(): Uri {
        binding.apply {
            val countryCode = countrySelection.selectedCountryCode.toString()
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
    }

    private fun whatsApp() {
        activity?.let {
            when {
                it.isAppInstalled(whatsApp) -> openTarget(whatsApp)
                it.isAppInstalled(yoWhatsapp) -> openTarget(yoWhatsapp)
                else -> logd("No whatsApp installed")
            }
        }
    }

    private fun whatsAppBusiness() {
        activity?.let {
            if (it.isAppInstalled(whatsAppBusiness)) {
                openTarget(whatsAppBusiness)
            } else {
                logd("")
            }
        }
    }

}