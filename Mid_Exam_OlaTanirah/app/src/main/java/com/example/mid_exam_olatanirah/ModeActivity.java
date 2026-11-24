package com.example.mid_exam_olatanirah;

import android.os.Bundle;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.activity.OnBackPressedCallback;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ModeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    Button btnSetMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        btnSetMode = findViewById(R.id.btnSetMode);

        // Setup ViewPager Adapter
        ViewPagerAdapter adapter =
                new ViewPagerAdapter(this);

        viewPager.setAdapter(adapter);

        // Connect Tabs with ViewPager
        new TabLayoutMediator(
                tabLayout,
                viewPager,
                (tab, position) -> {

                    switch (position) {
                        case 0:
                            tab.setText("First");
                            break;

                        case 1:
                            tab.setText("Second");
                            break;

                        case 2:
                            tab.setText("Third");
                            break;
                    }
                }
        ).attach();

        // Popup Menu Button
        btnSetMode.setOnClickListener(v -> showModeMenu());

        // Enable Up Navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Custom Back Navigation
        getOnBackPressedDispatcher().addCallback(
                this,
                new OnBackPressedCallback(true) {

                    @Override
                    public void handleOnBackPressed() {

                        if (viewPager.getCurrentItem() != 0) {

                            viewPager.setCurrentItem(0, true);

                        } else {

                            setEnabled(false);
                            getOnBackPressedDispatcher().onBackPressed();

                        }
                    }
                }
        );
    }

    // Show Popup Menu
    private void showModeMenu() {

        PopupMenu popupMenu =
                new PopupMenu(this, btnSetMode);

        popupMenu.getMenu().add("Light");
        popupMenu.getMenu().add("Night");
        popupMenu.getMenu().add("System");

        popupMenu.setOnMenuItemClickListener(item -> {

            String mode = item.getTitle().toString();

            if (mode.equals("Light")) {

                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO);

            } else if (mode.equals("Night")) {

                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES);

            } else if (mode.equals("System")) {

                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

            }

            return true;
        });

        popupMenu.show();
    }

    // Up Button Navigation
    @Override
    public boolean onSupportNavigateUp() {

        finish();
        return true;
    }
}
