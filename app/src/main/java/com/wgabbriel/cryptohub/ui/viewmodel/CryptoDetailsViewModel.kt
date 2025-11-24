package com.wgabbriel.cryptohub.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.wgabbriel.cryptohub.model.Crypto

class CryptoDetailsViewModel : ViewModel() {

    private var _crypto by mutableStateOf<Crypto?>(null)
    val crypto: Crypto?
        get() = _crypto

    fun updateCrypto(crypto: Crypto) {
        _crypto = crypto
    }
}