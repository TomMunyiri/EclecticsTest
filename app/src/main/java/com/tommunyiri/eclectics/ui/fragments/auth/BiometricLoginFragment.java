package com.tommunyiri.eclectics.ui.fragments.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tommunyiri.eclectics.databinding.BiometricLoginFragmentBinding;
import com.tommunyiri.eclectics.ui.activities.MainActivity;

public class BiometricLoginFragment extends Fragment {

    private BiometricLoginViewModel mViewModel;
    private BiometricLoginFragmentBinding binding;
    private String TAG="BiometricLoginFrag";

    public static BiometricLoginFragment newInstance() {
        return new BiometricLoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=BiometricLoginFragmentBinding.inflate(inflater,container,false);
        View view=binding.getRoot();
        Log.d(TAG, "onCreateView: clicked");
        binding.ivFingerPrint.setOnClickListener(v->{
            Log.d(TAG, "onCreateView: clicked");
            startActivity(new Intent(requireContext(), MainActivity.class));
            requireActivity().finish();
        });
        binding.btnSkip.setOnClickListener(v->{
            Log.d(TAG, "onCreateView: clicked");
            startActivity(new Intent(requireContext(),MainActivity.class));
            requireActivity().finish();
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BiometricLoginViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}