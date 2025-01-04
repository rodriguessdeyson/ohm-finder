package com.rad.app;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;

import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.rad.database.DBContext;
import com.rad.models.types.ResistorType;
import com.rad.resistorcolorcode.R;
import com.rad.resistorcolorcode.databinding.LayoutMainMenuBinding;
import com.rad.services.HelpBottomSheetDialog;
import com.rad.services.HistoryBottomSheetDialog;

public class MainActivity extends AppCompatActivity
{
    private AppBarConfiguration appBarConfiguration;
    private LayoutMainMenuBinding binding;
    
    private HelpBottomSheetDialog helpBottomSheet;
    private HistoryBottomSheetDialog historyBottomSheet;

    private ResistorType resistorType = ResistorType.NONE;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (historyBottomSheet != null && historyBottomSheet.isVisible())
        {
            outState.putBoolean("isBottomSheetShown", true);
            outState.putInt("resistorType", resistorType.ordinal());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = LayoutMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitleEnabled(true);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        navController.addOnDestinationChangedListener((navController1, navDestination, bundle) ->
        {
            binding.toolbar.setTitle(navDestination.getLabel());
            collapsingToolbarLayout.setTitle(navDestination.getLabel());

            NavArgument nArg = navDestination.getArguments().get("bandType");
            if (nArg != null && nArg.getDefaultValue() != null)
                resistorType = ResistorType.values()[Integer.parseInt(nArg.getDefaultValue().toString())];
            else
                resistorType = ResistorType.NONE;
        });

        initializeDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int itemId = item.getItemId();
        
        Bundle args = new Bundle();
        args.putInt("resistorType", resistorType.ordinal());
        
        if (itemId == R.id.action_help)
        {
            helpBottomSheet = new HelpBottomSheetDialog();
            helpBottomSheet.setArguments(args);
            helpBottomSheet.show(getSupportFragmentManager(), "BottomSheetDialog");
            return true;
        }
        else if (itemId == R.id.action_history)
        {
            historyBottomSheet = new HistoryBottomSheetDialog();
            historyBottomSheet.setArguments(args);
            historyBottomSheet.show(getSupportFragmentManager(), "BottomSheetDialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**
     * Initializes the database.
     */
    private void initializeDatabase()
    {
        DBContext dbContext = new DBContext(this);
        dbContext.Create();
    }
}