package com.app.foodie.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app.foodie.R
import com.google.android.material.textview.MaterialTextView

class MyProfile : Fragment() {

    lateinit var name : MaterialTextView
    lateinit var phone : MaterialTextView
    lateinit var email : MaterialTextView
    lateinit var address : MaterialTextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_my_profile, container, false)

        name = view.findViewById(R.id.name)
        phone = view.findViewById(R.id.phone)
        email = view.findViewById(R.id.email)
        address = view.findViewById(R.id.address)

        val bundle = arguments

        name.text = bundle?.getString("name")
        phone.text = bundle?.getString("phone")
        email.text = bundle?.getString("email")
        address.text = bundle?.getString("address")

        return view
    }

}
