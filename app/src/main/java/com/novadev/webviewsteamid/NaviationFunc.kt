package com.novadev.webviewsteamid

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

fun Fragment.findNavController(): NavController =
    NavHostFragment.findNavController(this)