package com.example.minibuscaminas_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btnResolver, btnNuevo;
    private Button[][] btns = new Button[5][5];
    private TextView lblPuntos;
    private int puntos = -2;
    int row1, col1, row2, col2, row3, col3 = -1, num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btns[0][0] = findViewById(R.id.btn00);
        btns[0][1] = findViewById(R.id.btn01);
        btns[0][2] = findViewById(R.id.btn02);
        btns[0][3] = findViewById(R.id.btn03);
        btns[0][4] = findViewById(R.id.btn04);
        btns[1][0] = findViewById(R.id.btn10);
        btns[1][1] = findViewById(R.id.btn11);
        btns[1][2] = findViewById(R.id.btn12);
        btns[1][3] = findViewById(R.id.btn13);
        btns[1][4] = findViewById(R.id.btn14);
        btns[2][0] = findViewById(R.id.btn20);
        btns[2][1] = findViewById(R.id.btn21);
        btns[2][2] = findViewById(R.id.btn22);
        btns[2][3] = findViewById(R.id.btn23);
        btns[2][4] = findViewById(R.id.btn24);
        btns[3][0] = findViewById(R.id.btn30);
        btns[3][1] = findViewById(R.id.btn31);
        btns[3][2] = findViewById(R.id.btn32);
        btns[3][3] = findViewById(R.id.btn33);
        btns[3][4] = findViewById(R.id.btn34);
        btns[4][0] = findViewById(R.id.btn40);
        btns[4][1] = findViewById(R.id.btn41);
        btns[4][2] = findViewById(R.id.btn42);
        btns[4][3] = findViewById(R.id.btn43);
        btns[4][4] = findViewById(R.id.btn44);
        btnResolver = findViewById(R.id.btnResolver);
        btnNuevo = findViewById(R.id.btnNuevo);
        lblPuntos = findViewById(R.id.lblPuntos);

        lblPuntos.setText("(Insert Coin)");

        for(int i=0 ; i< btns.length ; i++)
        {
            for(int j=0 ; j<btns[i].length ; j++)
            {
                final int row = i;
                final int col = j;

                btns[i][j].setOnClickListener(view -> accionBtn(row, col));
            }
        }

        btnResolver.setOnClickListener(view -> accionResolver());
        btnNuevo.setOnClickListener(view -> accionNuevo());
    }

    public void accionBtn(int row, int col)
    {
        if(row == row1 && col == col1 || row == row2 && col == col2 || row == row3 && col == col3)
        {
            btns[row][col].setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.rojo)));
            lblPuntos.setText("WASTED...");
            puntos = -1;
            Toast.makeText(this, "BOOOOOM !!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(row+1 == row1 || row-1 == row1 || row == row1)
            {
                if(col+1 == col1 || col-1 == col1 || col == col1)
                {
                    num = num + 1;
                    btns[row][col].setText("" + num);
                    if(puntos != -1)
                        puntos = puntos + 10;
                }
            }

            if(row+1 == row2 || row-1 == row2 || row == row2)
            {
                if(col+1 == col2 || col-1 == col2 || col == col2)
                {
                    num = num + 1;
                    btns[row][col].setText("" + num);
                    if(puntos != -1)
                        puntos = puntos + 10;
                }
            }

            if(row+1 == row3 || row-1 == row3 || row == row3)
            {
                if(col+1 == col3 || col-1 == col3 || col == col3)
                {
                    num = num + 1;
                    btns[row][col].setText("" + num);
                    if(puntos != -1)
                        puntos = puntos + 10;
                }
            }

            if(num == 0)
            {
                btns[row][col].setVisibility(View.INVISIBLE);
                if (puntos != -1)
                    puntos = puntos + 5;
            }

            num = 0;
        }

        if(puntos != -1)
            lblPuntos.setText("Puntos: " + puntos);
    }

    public void accionNuevo()
    {
        insertarMinas();

        puntos = 0;

        lblPuntos.setText("Puntos: " + puntos);

        for(int i=0 ; i< btns.length ; i++)
        {
            for(int j=0 ; j<btns[i].length ; j++)
            {
                btns[i][j].setVisibility(View.VISIBLE);
                btns[i][j].setText("");
                btns[i][j].setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.azul)));
            }
        }

        Log.i("Boton Nuevo", "Juego nuevo comenzado");
    }

    public void accionResolver()
    {
        if(puntos != -2)
        {
            btns[row1][col1].setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.rojo)));
            btns[row2][col2].setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.rojo)));
            btns[row3][col3].setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.rojo)));

            puntos = -1;

            lblPuntos.setText("WASTED...");

            Log.i("Boton Resolver", "Juego resuelto");
            Toast.makeText(this, "Juego resuelto", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Log.i("Boton Resolver", "Juego sin iniciar");
            Toast.makeText(this, "Juegos sin iniciar", Toast.LENGTH_SHORT).show();
        }
    }

    public void insertarMinas()
    {
        Random random = new Random();

        row1 = random.nextInt(5);
        col1 = random.nextInt(5);
        row2 = random.nextInt(5);
        col2 = random.nextInt(5);
        row3 = random.nextInt(5);
        col3 = random.nextInt(5);

        if((row1 == row2) && (col1 == col2))
        {
            do {
                col2 = random.nextInt(5);
            }while(col2 == col1);
        }

        if((row2 == row3) && (col2 == col3))
        {
            do {
                col3 = random.nextInt(5);
            }while(col3 == col2);
        }

        if((row1 == row3) && (col1 == col3))
        {
            do {
                col3 = random.nextInt(5);
            }while(col3 == col1);
        }

        Log.i("Minas Insertadas", "Mina1 " + row1 + "-" + col1 + " | Mina2 " + row2 + "-" + col2 + " | Mina3 " + row3 + "-" + col3);
        Toast.makeText(this, "Minas insertadas", Toast.LENGTH_SHORT).show();
    }
}