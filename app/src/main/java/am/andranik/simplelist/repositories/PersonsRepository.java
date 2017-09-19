package am.andranik.simplelist.repositories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import am.andranik.simplelist.listeners.DataUpdatedListener;
import am.andranik.simplelist.models.Person;
import am.andranik.simplelist.utils.JsonUtils;

/**
 * Created by andranik on 9/18/17.
 */

public class PersonsRepository {

    private static final String KEY_FNAME = "first_name";
    private static final String KEY_LNAME = "last_name";

    private List<Person> persons;

    public void getPersons(DataUpdatedListener<List<Person>> listener) {
        if(persons != null){
            listener.onDataUpdated(persons);
            return;
        }

        String personsJson = JsonUtils.read("persons.json");
        persons = parseUsersJson(personsJson);
        listener.onDataUpdated(persons);
    }

    public void addPerson(Person person, DataUpdatedListener<Person> listener){
        if(persons == null){
            persons = new ArrayList<>();
        }

        persons.add(person);
        listener.onDataUpdated(person);
    }

    private List<Person> parseUsersJson(String json) {
        List<Person> persons = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String firstName = jsonObject.getString(KEY_FNAME);
                String lastName = jsonObject.getString(KEY_LNAME);
                persons.add(Person.create(firstName, lastName));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return persons;
    }
}
