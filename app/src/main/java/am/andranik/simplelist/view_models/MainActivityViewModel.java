package am.andranik.simplelist.view_models;

import java.util.List;

import am.andranik.simplelist.listeners.DataUpdatedListener;
import am.andranik.simplelist.models.Person;
import am.andranik.simplelist.repositories.PersonsRepository;

/**
 * Created by andranik on 9/18/17.
 */

public class MainActivityViewModel {

    private PersonsRepository repository;

    private MainActivityViewModel() {
        repository = new PersonsRepository();
    }

    public void getPersons(DataUpdatedListener<List<Person>> listener){
        repository.getPersons(listener);
    }

    public void addPerson(Person person, DataUpdatedListener<Person> listener){
        repository.addPerson(person, listener);
    }

    public void persistViewModel(){
        MainActivityViewModelHolder.setData(this);
    }

    public static void clearViewModel(){
        MainActivityViewModelHolder.cleanData();
    }

    public static MainActivityViewModel getViewModel(){
        MainActivityViewModel viewModel = MainActivityViewModelHolder.getData();

        if(viewModel == null){
            viewModel = new MainActivityViewModel();
        }

        return viewModel;
    }

    private enum MainActivityViewModelHolder {
        INSTANCE;

        private MainActivityViewModel data;

        public static boolean hasData() {
            return INSTANCE.data != null;
        }

        public static MainActivityViewModel getData() {
            final MainActivityViewModel data = INSTANCE.data;
            INSTANCE.data = null;

            return data;
        }

        public static void setData(final MainActivityViewModel data) {
            INSTANCE.data = data;
        }

        public static void cleanData(){
            INSTANCE.data = null;
        }
    }
}
