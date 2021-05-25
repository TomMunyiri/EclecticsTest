package com.tommunyiri.eclectics.ui.fragments.auth;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tommunyiri.eclectics.R;
import com.tommunyiri.eclectics.databinding.PinLoginFragmentBinding;
import com.tommunyiri.eclectics.ui.activities.MainActivity;

public class PinLoginFragment extends Fragment {

    private PinLoginViewModel mViewModel;
    private PinLoginFragmentBinding binding;

    public static PinLoginFragment newInstance() {
        return new PinLoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=PinLoginFragmentBinding.inflate(inflater,container,false);
        View view =binding.getRoot();
        binding.ivBiometricLogin.setOnClickListener(v->{
            startActivity(new Intent(requireContext(), MainActivity.class));
            requireActivity().finish();
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PinLoginViewModel.class);
        // TODO: Use the ViewModel
    }

}