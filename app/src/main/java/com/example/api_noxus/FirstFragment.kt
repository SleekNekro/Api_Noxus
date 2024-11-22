package com.example.api_noxus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.api_noxus.databinding.FragmentFirstBinding
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retrofit = RetrofitServiceFactory.makeRetrofitService()

        lifecycleScope.launch {
            val champ: ArrayList<Champ> = retrofit.listChamps("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InF4dXVvZ2JpY3J1dmJhdGdxam91Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE1MDc5NjgsImV4cCI6MjA0NzA4Mzk2OH0.HwsEVV1d6b_0pMHBTyYt88cgEd7MUyb9XrFNexqJBEY")
            val adapter = Champ_Adapter(
                requireContext(),
                R.layout.lst_item,
                champ
            )
            binding.itemList.adapter = adapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}