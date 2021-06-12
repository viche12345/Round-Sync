package ca.pkay.rcloneexplorer.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ca.pkay.rcloneexplorer.Database.DatabaseHandler;
import ca.pkay.rcloneexplorer.Database.json.Exporter;
import ca.pkay.rcloneexplorer.Database.json.Importer;
import ca.pkay.rcloneexplorer.R;
import ca.pkay.rcloneexplorer.RecyclerViewAdapters.TriggerRecyclerViewAdapter;
import ca.pkay.rcloneexplorer.TriggerActivity;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

public class TriggerFragment extends Fragment {

    private TriggerRecyclerViewAdapter recyclerViewAdapter;
    private View fragmentView;


    public TriggerFragment() {
        // Required empty public constructor
    }

    public static TriggerFragment newInstance() {
        return new TriggerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((FragmentActivity) getContext()).setTitle(R.string.trigger);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trigger, container, false);
        fragmentView = view;
        populateTriggerList(fragmentView);

        view.findViewById(R.id.newTrigger).setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), TriggerActivity.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        populateTriggerList(fragmentView);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tasks_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void populateTriggerList(View v){
        Context c = v.getContext();
        DatabaseHandler dbHandler = new DatabaseHandler(c);

        RecyclerView recyclerView =  v.findViewById(R.id.trigger_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(c));
        recyclerView.setItemAnimator(new LandingAnimator());

        recyclerViewAdapter = new TriggerRecyclerViewAdapter(dbHandler.getAllTrigger(), c);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
