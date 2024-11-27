package com.example.api_noxus

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.PreferenceManager
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

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val retrofit = RetrofitServiceFactory.makeRetrofitService()

            val champ: ArrayList<Champ> = ArrayList()
            val adapter = ChampAdapter(
                requireContext(),
                R.layout.lst_item,
                champ
            )
            binding.itemList.adapter = adapter

        binding.itemList.setOnItemClickListener{ adapter, _, position, _ ->
            val champ = adapter.getItemAtPosition(position) as Champ
            val args = Bundle().apply {
                putSerializable("item", champ)
            }
            NavHostFragment.findNavController(this@FirstFragment)
                .navigate(R.id.action_FirstFragment_to_fragment_details, args)
        }
        val preferences = PreferenceManager.getDefaultSharedPreferences(context as Context)
        val rol: String? = preferences.getString("Champs","")
        val model = ViewModelProvider(this)[ViewModel::class.java]
        model.champs.observe(viewLifecycleOwner) { result ->
            Log.d("FUNCIONA", model.champs.toString())
            Log.d("resultXXX", result.toString())
            adapter.clear()

            val filteredChamps = result.filter { champ ->
                champ.diff.lowercase().contains(rol?.lowercase() ?: "")
            }
            adapter.addAll(filteredChamps)
        }

        super.onViewCreated(view, savedInstanceState)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id==R.id.action_settings){
            val i : Intent = Intent(this.context, SettingsActivity::class.java)
            startActivity(i)
            return true
        }
        if (id == R.id.action_refresh){
            lifecycleScope.launch {
                val viewModel = ViewModel(app = Application())
                viewModel.reload() }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}