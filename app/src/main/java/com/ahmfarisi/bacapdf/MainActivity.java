package com.ahmfarisi.bacapdf;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class MainActivity extends AppCompatActivity {
    private PDFView pdfViewer;
    private String file_name = "knowledge_management.pdf";
    private Button btnBukaHalaman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pdfViewer = findViewById(R.id.pdf_viewer);
        pdfViewer.fromAsset(file_name).load();

        btnBukaHalaman = findViewById(R.id.btn_buka_halaman);
        btnBukaHalaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Perhatian");
                dialog.setMessage("Halaman Berapa yang Ingin Anda Buka?");

                EditText inputHal = new EditText(MainActivity.this);
                inputHal.setHeight(100);
                inputHal.setWidth(340);
                inputHal.setInputType(InputType.TYPE_CLASS_NUMBER);
                inputHal.setGravity(Gravity.CENTER_HORIZONTAL);
                inputHal.setImeOptions(EditorInfo.IME_ACTION_DONE);
                dialog.setView(inputHal);

                dialog.setPositiveButton("Buka", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int halaman = Integer.parseInt(inputHal.getText().toString());
                        //Toast.makeText(MainActivity.this, "Halaman "+halaman, Toast.LENGTH_SHORT).show();

                        pdfViewer.fromAsset(file_name)
                                .defaultPage(halaman+16)
                                .load();
                    }
                });

                dialog.show();

            }
        });
    }
}