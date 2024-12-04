package com.example.api_noxus

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.api_noxus.databinding.FragmentDetailsBinding
import java.util.Locale

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var _binding: FragmentDetailsBinding? = null
private val binding get() = _binding!!

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_details.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_details : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_details().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: Bundle? = arguments
        val adapter: MutableList<Role> = mutableListOf()


        val champs = args?.getSerializable("item") as Champ
        Log.i("role", champs.toString())


        val model = ViewModelProvider(this)[ViewModel::class.java]
        model.rols.observe(viewLifecycleOwner) { result ->
            Log.i("result", result.toString())
            adapter.addAll(result)
            Log.i("ADA1", adapter.toString())


            for (i: Role in adapter) {
                if (i.id == champs.id) {
                    Log.i("ADA", i.toString())
                    updateUi(i)
                    break
                }
            }
        }
    }

    private fun updateUi(role: Role) {
        Log.d("MOVIE", role.toString())


        binding.nameRole.text = role.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }

        binding.description.text = role.desc.replaceFirst(role.desc[0].toString(), role.desc[0].toString().uppercase())


        Glide.with(requireContext())
            .load(role.img)
            .into(binding.Roleimg)
    }
}
