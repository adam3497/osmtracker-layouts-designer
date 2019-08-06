package net.osmtracker.layoutsdesigner.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.osmtracker.layoutsdesigner.OsmtrackerLayoutsDesigner;
import net.osmtracker.layoutsdesigner.R;
import net.osmtracker.layoutsdesigner.utils.GridViewLayoutManager;
import net.osmtracker.layoutsdesigner.utils.ItemListMain;
import net.osmtracker.layoutsdesigner.utils.xmlutils.ReadXmlTask;
import net.osmtracker.layoutsdesigner.utils.xmlutils.RowXmlObject;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class EditLayout extends AppCompatActivity {

    private static final String TAG = OsmtrackerLayoutsDesigner.Preferences.TAG + ".EditLayout";
    private ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadingProgressBar = findViewById(R.id.progressbar_layout_loading);

        Bundle extras = getIntent().getExtras();
        ItemListMain item;
        if(extras !=  null){
            item = (ItemListMain) extras.getSerializable("item");
            if(item != null){
                getSupportActionBar().setTitle(item.getLayoutCreatedName());
                loadingProgressBar.setVisibility(View.VISIBLE);
                loadLayoutXmlFile(item);
            }
            else{
                Toast.makeText(this, "Error in receiving extras, item is null", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this, "Error: bundle is null", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void loadLayoutXmlFile(ItemListMain item) {
        new ReadXmlTask(){
            @Override
            protected void onPostExecute(Hashtable<String, ArrayList<RowXmlObject>> hashLayoutParsed) {
                if(hashLayoutParsed != null){
                    printHashInfo(hashLayoutParsed);
                }
                else{
                    Toast.makeText(EditLayout.this, "Error trying parsing the layout file", Toast.LENGTH_LONG).show();
                }
            }
        }.execute(OsmtrackerLayoutsDesigner.Preferences.LAYOUTS_DIR_PATH + item.getLayoutFileName());
    }

    private void printHashInfo(Hashtable<String, ArrayList<RowXmlObject>> hash){
        Enumeration<String> hashKeys = hash.keys();
        while(hashKeys.hasMoreElements()){
            Log.i(TAG, "printHashInfo: current key: " + hashKeys.nextElement() + "\n");
        }
        GridViewLayoutManager manager = new GridViewLayoutManager(this);
        manager.showGridView(hash.get("root"));
        //loadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
