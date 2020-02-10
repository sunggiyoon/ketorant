package com.giyoon.ketorant;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.giyoon.ketorant.fragment.FavoriteFragment;
import com.giyoon.ketorant.fragment.HomeFragment;
import com.giyoon.ketorant.fragment.MapFragment;
import com.giyoon.ketorant.fragment.RecipeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.giyoon.ketorant.util.StatusCode.FRAGMENT_ARG;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    FirebaseAuth mAuth;
    FirebaseUser mCurrentUser;

    private BottomNavigationView bottomNavigationView;
    private Fragment homeFragment;
    private Fragment favoriteFragment;
    private Fragment mapFragment;
    private Fragment recipeFragment;

    private FragmentManager fm = getSupportFragmentManager();
    private FragmentTransaction ts = getSupportFragmentManager().beginTransaction();

    private static final int RC_SIGN_IN = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.action_home);


        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.action_home :

                Fragment homeFragment = new HomeFragment();

                Bundle bundle_0 = new Bundle();
                bundle_0.putInt(FRAGMENT_ARG, 0);

                homeFragment.setArguments(bundle_0);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment, homeFragment)
                        .commit();

                return true;

            case R.id.action_map :

                Fragment mapFragment = new MapFragment(this);

                Bundle bundle_1 = new Bundle();
                bundle_1.putInt(FRAGMENT_ARG, 1);

                mapFragment.setArguments(bundle_1);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment, mapFragment)
                        .commit();

                return true;

            case R.id.action_recipe :

                Fragment recipeFragment = new RecipeFragment();

                Bundle bundle_2 = new Bundle();
                bundle_2.putInt(FRAGMENT_ARG, 2);

                recipeFragment.setArguments(bundle_2);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment, recipeFragment)
                        .commit();

                return true;

            case R.id.action_favorite :

                Fragment favoriteFragment = new FavoriteFragment();

                Bundle bundle_3 = new Bundle();
                bundle_3.putInt(FRAGMENT_ARG, 3);

                favoriteFragment.setArguments(bundle_3);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment, favoriteFragment)
                        .commit();

                return true;
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_fragment);
        int fragmentNum = fragment.getArguments().getInt(FRAGMENT_ARG, 0);

        // TODO : Refactoring 필요
        if (fragmentNum == 5) {
            bottomNavigationView.setSelectedItemId(R.id.action_home);
        } else {
            super.onBackPressed();
        }
    }

}
