package am.andranik.simplelist.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import am.andranik.simplelist.R;
import am.andranik.simplelist.models.Person;

/**
 * Created by andranik on 9/18/17.
 */

public class PersonsListAdapter extends RecyclerView.Adapter<PersonsListAdapter.PersonsListViewHolder>{

    private List<Person> data;

    public PersonsListAdapter() {
        this.data = new ArrayList<>();
    }

    @Override
    public PersonsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonsListViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(PersonsListViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Person> persons){
        data.clear();
        data.addAll(persons);
        notifyDataSetChanged();
    }

    public void addItem(Person person){
        data.add(person);
        notifyItemChanged(data.size() - 1);
    }

    static class PersonsListViewHolder extends RecyclerView.ViewHolder{

        private TextView nameTv;

        PersonsListViewHolder(View itemView) {
            super(itemView);

            nameTv = (TextView) itemView.findViewById(R.id.item_person_name_tv);
        }

        void bind(Person person){
            nameTv.setText(person.getFormattedName());
        }
    }
}
