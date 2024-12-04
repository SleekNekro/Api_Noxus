package com.example.api_noxus

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ChampAdapter
    private lateinit var model: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

        adapter = ChampAdapter(
            requireContext(),
            R.layout.lst_item,
            ArrayList()
        )
        binding.itemList.adapter = adapter


        binding.itemList.setOnItemClickListener { _, _, position, _ ->
            val champ = adapter.getItem(position) as Champ
            val args = Bundle().apply {
                putSerializable("item", champ)
            }
            NavHostFragment.findNavController(this@FirstFragment)
                .navigate(R.id.action_FirstFragment_to_fragment_details, args)
        }


        model = ViewModelProvider(this)[ViewModel::class.java]
        observer()
        refresh()
    }

    private fun observer() {
        model.champs.observe(viewLifecycleOwner) { result ->
            Log.d("FUNCIONA", "Datos recibidos: $result")
            updateAdapter(result)
        }
    }

    private fun updateAdapter(result: List<Champ>) {

        val preferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val diff = preferences.getString("Champ", null)?.takeIf { it.isNotBlank() }


        val filteredChamps = if (!diff.isNullOrEmpty()) {
            result.filter { champ ->
                champ.diff.equals(diff, ignoreCase = true)
            }
        } else {
            result
        }

        lifecycleScope.launch(Dispatchers.Main) {
            adapter.clear()
            adapter.addAll(filteredChamps)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun refresh() {
        lifecycleScope.launch {
            model.reload()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(context, SettingsActivity::class.java))
                true
            }
            R.id.action_refresh -> {
                refresh()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
