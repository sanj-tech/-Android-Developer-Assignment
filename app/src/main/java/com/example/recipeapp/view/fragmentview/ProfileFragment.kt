package com.example.recipeapp.view.fragmentview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.R
import com.example.recipeapp.data.db.RecipeDB
import com.example.recipeapp.data.db.UserProfileViewModelFactory
import com.example.recipeapp.data.model.UserProfile
import com.example.recipeapp.databinding.FragmentProfileBinding
import com.example.recipeapp.mvvm.UserProfileViewModel


class ProfileFragment : Fragment() {
    private lateinit var viewModel: UserProfileViewModel
    lateinit var binding:FragmentProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val view = inflater.inflate(R.layout.fragment_profile, container, false)
//
//        val database = RecipeDB.getInstance(requireContext())
//        val userProfileDao = database.userProfileDao()

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val database = RecipeDB.getInstance(requireContext())
        val userProfileDao = database.userProfileDao()

        val viewModelFactory = UserProfileViewModelFactory(userProfileDao)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(UserProfileViewModel::class.java)

        //viewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        val firstNameEditText = view.findViewById<EditText>(R.id.tvName)
        val lastNameEditText = view.findViewById<EditText>(R.id.tvAddress)

        val saveButton = view.findViewById<Button>(R.id.btnSave)

        saveButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()


            val userProfile = UserProfile(0, firstName, lastName)
            viewModel.saveUserProfile(userProfile)

            Toast.makeText(requireContext(),"${userProfile}",Toast.LENGTH_LONG).show()
        }




        return view
    }
    }





