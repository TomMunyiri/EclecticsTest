package com.tommunyiri.eclectics.ui.fragments.auth;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tommunyiri.eclectics.R;
import com.tommunyiri.eclectics.databinding.LoginHomeFragmentBinding;
import com.tommunyiri.eclectics.ui.activities.MainActivity;

public class LoginHomeFragment extends Fragment {

    private LoginHomeViewModel mViewModel;
    private LoginHomeFragmentBinding binding;

    public static LoginHomeFragment newInstance() {
        return new LoginHomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=LoginHomeFragmentBinding.inflate(inflater,container,false);
        View view=binding.getRoot();
        binding.btnBiometricLogin.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_loginHomeFragment_to_biometricLoginFragment);
            //startActivity(new Intent(requireContext(), MainActivity.class));
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginHomeViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}