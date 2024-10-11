package gaurink.marcella.galeriapublica;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final MainViewModel vm = new ViewModelProvider(this).get(MainViewModel.class);



        bottomNavigationView = findViewById(R.id.btNav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationOpSelected(@NonNull MenuItem item) {
                vm.setNavigationOpSelected(item.getItemId());
                switch (item.getItemId()) {
                    case R.id.gridViewOp:
                        GridViewFragment.gridViewFragment = GridViewFragment.newInstance();
                        setFragment(gridViewFragment);
                        break;
                    case R.id.listViewOp:
                        ListViewFragment listViewFragment = ListViewFragment.newInstance();
                        setFragment(listViewFragment);
                        break;
                }
                return true;
            }
        });
    }

    void setFragment(Fragment fragment) {
        FragmentTransaction.fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragContainer,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        checkForPermissions(permissions);
    }

    private void checkForPermissions(List<String> permissions) {
        List<String> permissionsNotGranted = new ArrayList<>();

        if(permissionsNotGranted.size() > 0) {

        }
        else {
            MainViewModel vm = new ViewModelProvider(this).get(MainViewModel.class);
            int navigationOpSelected = vm.navigationOpSelected;
            bottomNavigationView.setSelectedItemId(navigationOpSelected);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,);


        if(permissionsRejected.size() > 0) {

        }
        else {
            MainViewModel vm = new ViewModelProvider(this).get(MainViewModel.class);
            int navigationOpSelected = vm.getNavigationOpSelected();
            bottomNavigationView.setSelectedItemId(navigationOpSelected);
        }
    }
}