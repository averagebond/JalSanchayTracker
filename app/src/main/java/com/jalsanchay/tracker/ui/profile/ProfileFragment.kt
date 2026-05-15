package com.jalsanchay.tracker.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.jalsanchay.tracker.R
import com.jalsanchay.tracker.RainfallViewModel
import com.jalsanchay.tracker.databinding.FragmentProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val vm: RainfallViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userPrefs = requireActivity().getSharedPreferences("user", 0)
        val isLoggedIn = userPrefs.getBoolean("is_logged_in", false)

        if (!isLoggedIn) {
            showLoginView()
        } else {
            showProfileView()
        }
    }

    private fun showLoginView() {
        binding.profileContent.visibility = View.GONE
        binding.loginForm.visibility = View.VISIBLE

        binding.btnLogin.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userPrefs = requireActivity().getSharedPreferences("user", 0)
            val savedPassword = userPrefs.getString("pass_$email", null)

            if (savedPassword != null && savedPassword != password) {
                Toast.makeText(requireContext(), "Incorrect password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            userPrefs.edit().apply {
                putString("name", name)
                putString("email", email)
                putString("pass_$email", password)
                putBoolean("is_logged_in", true)
                apply()
            }
            
            showProfileView()
            Toast.makeText(requireContext(), "Logged in successfully", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showProfileView() {
        binding.loginForm.visibility = View.GONE
        binding.profileContent.visibility = View.VISIBLE

        val userPrefs = requireActivity().getSharedPreferences("user", 0)
        binding.tvUserName.text = userPrefs.getString("name", "User")
        binding.tvUserEmail.text = userPrefs.getString("email", "")

        vm.totalLitres.observe(viewLifecycleOwner) { total ->
            binding.tvProfileTotal.text = String.format("%.0f L", total ?: 0.0)
        }

        vm.entryCount.observe(viewLifecycleOwner) { count ->
            binding.tvProfileEntries.text = (count ?: 0).toString()
        }

        // BUG 2 FIX: Use selectedItemId on BottomNavigationView to switch tabs correctly
        binding.btnLogRain.setOnClickListener {
            requireActivity()
                .findViewById<BottomNavigationView>(R.id.bottom_navigation)
                .selectedItemId = R.id.historyFragment
        }

        binding.btnLogout.setOnClickListener {
            userPrefs.edit().putBoolean("is_logged_in", false).apply()
            showLoginView()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
