package tsp.projetInfoGroupe2.rubikscubeprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Classe représentant la deuxième page de l'application. Celle de l'historique des rotations effectuées
 * @author Aydin Abiar
 */
public class ListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Création et attribution de the list of rotations
        final ListView rotationsDoneListView = (ListView) findViewById(R.id.rotationsDoneListView);
        ArrayAdapter<String> rotationsDoneArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MainActivity.cube.historicOfRotations);
        rotationsDoneListView.setAdapter(rotationsDoneArrayAdapter);

        // Retour à la page en cliquant sur le bouton correspondant ("Back to main page")
        rotationsDoneListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (rotationsDoneListView.getItemAtPosition(i).equals("Back to main page")) {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }

            }
        });

    }
}
