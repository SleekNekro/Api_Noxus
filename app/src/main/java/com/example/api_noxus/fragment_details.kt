package com.example.api_noxus

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.api_noxus.databinding.FragmentDetailsBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var _binding : FragmentDetailsBinding? =null
private  val binding get() = _binding!!

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_details.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_details : Fragment() {
    // TODO: Rename and change types of parameters
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
        _binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_details.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_details().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args : Bundle? = arguments

       args.let {
           val role = it?.getSerializable("item") as Role

           role.let {
               updateUi(it)
           }
       }
    }

    private fun updateUi(role: Role) {
        Log.d("MOVIE", role.toString())

        binding.nameRole.text = role.name.replaceFirst(role.name[0].toString() , role.name[0].toString().uppercase())
        binding.description.text = role.desc.replaceFirst(role.desc[0].toString() , role.desc[0].toString().uppercase())


        Glide.with(requireContext())
            .load(role.img)
            .into(binding.Roleimg)
    }

}