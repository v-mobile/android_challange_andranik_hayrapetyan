package am.andranik.simplelist.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import am.andranik.simplelist.dialogs.AddPersonDialog;
import am.andranik.simplelist.listeners.DataUpdatedListener;
import am.andranik.simplelist.view_models.MainActivityViewModel;
import am.andranik.simplelist.models.Person;
import am.andranik.simplelist.adapters.PersonsListAdapter;
import am.andranik.simplelist.R;

public class MainActivity extends AppCompatActivity implements AddPersonDialog.AddPersonDialogListener {

    private RecyclerView rv;
    private PersonsListAdapter adapter;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.activity_main_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PersonsListAdapter();
        rv.setAdapter(adapter);

        viewModel = MainActivityViewModel.getViewModel();

        viewModel.getPersons(new DataUpdatedListener<List<Person>>() {
            @Override
            public void onDataUpdated(List<Person> data) {
                adapter.setData(data);
            }
        });
    }

    public void onFabClick(View view) {
        AddPersonDialog.show(getSupportFragmentManager());
    }

    @Override
    public void onPersonAdded(final Person person) {
        viewModel.addPerson(person, new DataUpdatedListener<Person>() {
            @Override
            public void onDataUpdated(Person data) {
                adapter.addItem(person);
            }
        });
    }

    @Override
    protected void onDestroy() {
        if(isChangingConfigurations()){
            viewModel.persistViewModel();
        } else {
            MainActivityViewModel.clearViewModel();
        }
        super.onDestroy();
    }
}
