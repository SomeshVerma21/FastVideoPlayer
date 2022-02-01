package com.vermaji.fastvideoplayer.fragments.videoListFragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.ChipGroup
import com.vermaji.fastvideoplayer.PlayerActivity
import com.vermaji.fastvideoplayer.R
import com.vermaji.fastvideoplayer.databinding.FragmentVideoListBinding
import com.vermaji.fastvideoplayer.fragments.adapters.MediaClickListener
import com.vermaji.fastvideoplayer.fragments.adapters.VideoItemAdapter

class VideoListFragment : Fragment() {
    private lateinit var binding:FragmentVideoListBinding
    private val REQUEST_CODE =1001
    private val PERMISSIONS = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    private lateinit var viewModel: VideoListModel
    private lateinit var filterChipGroup:ChipGroup

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentVideoListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filterChipGroup = view.findViewById(R.id.idMediaFilterChipGroup)
        filterChipGroup.setOnCheckedChangeListener(ChipGroup.OnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.idFilterAllMedia ->{
                    Toast.makeText(requireContext(),"All Media",Toast.LENGTH_SHORT).show()
                    viewModel.getData("all")
                }
                R.id.idFilterByDuration ->{
                    Toast.makeText(requireContext(),"By Duration",Toast.LENGTH_SHORT).show()
                    viewModel.getData("duration")
                }
                R.id.idFilterByName ->{
                    Toast.makeText(requireContext(),"By Name",Toast.LENGTH_SHORT).show()
                    viewModel.getData("name")
                }
                R.id.idFilterLatestAdded ->{
                    Toast.makeText(requireContext(),"Latest Added",Toast.LENGTH_SHORT).show()
                    viewModel.getData("latest")
                }
                R.id.idFilterBySize ->{
                    Toast.makeText(requireContext(),"By Size",Toast.LENGTH_SHORT).show()
                    viewModel.getData("size")
                }
            }
        })
        val viewModelFactory = ViewModelFactory(activity?.contentResolver)
        viewModel = ViewModelProvider(this,viewModelFactory).get(VideoListModel::class.java)
        viewModel.videoList.observe(viewLifecycleOwner, Observer {
            val mAdapter = viewModel.videoList.value?.let { it1 -> VideoItemAdapter(it1,
                MediaClickListener { itemUri ->
                    val intent = Intent(activity,PlayerActivity::class.java)
                    intent.putExtra("mediaUri",itemUri)
                    startActivity(intent)
                }) }
            binding.idVideoRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.idVideoRecyclerView.adapter = mAdapter
        })
        binding.idVideoRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun checkPermission()
    {
        if (allPermissionGranted())
            loadMedia()
        else{
            requestPermissions(PERMISSIONS,REQUEST_CODE)
        }
    }
    private fun loadMedia(){
        viewModel.getData("All")
    }

    override fun onStart() {
        super.onStart()
        checkPermission()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==REQUEST_CODE){
            if (allPermissionGranted()){
                loadMedia()
            }else{
                Toast.makeText(requireContext(),"Permission Required",Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", requireContext().packageName, null)
                intent.data = uri
                startActivity(intent)
            }
        }
    }

    private fun allPermissionGranted() = PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(),it)==PackageManager.PERMISSION_GRANTED
    }
}