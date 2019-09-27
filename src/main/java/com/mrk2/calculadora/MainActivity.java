package com.mrk2.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {
    String oper = "", resul = "";
    TextView tvOper, tvResul;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnPunto;
    Button btnSobre, btnPor, btnMenos, btnMas, btnIgual;
    ImageButton btnBorr;
    //Botones Extras
    boolean euler, pi;
    Button btnINV, btnRAD, btnMOD, btnSENO, btnCOS, btnTAN, btnLn, btnLOG, btnRaiz, btnPi, btnE, btnPot, btnParIz, btnParDer, btnFact;
    boolean sum, res, mul, div, puntito;
    double result, op1, op2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            cargarControles();
            cargarEventos();

        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }

    }

    private void cargarEventos() {

        btnBorr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tvOper.getText().toString().isEmpty()) {
                    String cadena = tvOper.getText().toString();
                    cadena = cadena.replaceFirst(".$", "");
                    tvOper.setText(cadena);
                } else {
                    op1 = 0;
                    op2 = 0;
                    mul = false;
                    sum = false;
                }
                if (!tvResul.getText().toString().isEmpty()) {
                    String cadena2 = tvResul.getText().toString();
                    cadena2 = cadena2.replaceFirst(".$", "");
                    tvResul.setText(cadena2);
                } else {
                    op1 = 0;
                    op2 = 0;
                    mul = false;
                    sum = false;
                }
                if (tvResul.getText().toString().isEmpty() || tvOper.getText().toString().isEmpty()) {
                    mul = false;
                    sum = false;
                    euler = false;
                    puntito = false;
                    pi = false;
                }
            }
        });
         btnBorr.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tvResul.setText("");
                tvOper.setText("");
                op1 = 0;
                op2 = 0;
                mul = false;
                sum = false;
                euler = false;
                puntito = false;
                pi = false;
                return false;
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarCadena("1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarCadena("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarCadena("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarCadena("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarCadena("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarCadena("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarCadena("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarCadena("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarCadena("9");
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarCadena("0");
            }
        });
        btnPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!puntito) {
                    String cadena = tvOper.getText().toString();
                    if (cadena.isEmpty()
                            || cadena.charAt(cadena.length() - 1) == '+'
                            || cadena.charAt(cadena.length() - 1) == '*'
                            || cadena.charAt(cadena.length() - 1) == '/'
                            || cadena.charAt(cadena.length() - 1) == '-') {
                        agregarCadena("0.");
                    } else {
                        agregarCadena(".");
                    }
                    puntito = true;
                }
            }
        });
        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!div && !mul && !div && !sum) {
                    tvOper.setText(tvOper.getText().toString() + "+");
                    sum = true;
                    puntito = false;
                    euler = false;
                }

            }
        });
        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //operaciones("-");
                if (!div && !mul && !div && !sum) {
                    tvOper.setText(tvOper.getText().toString() + "-");
                    res = true;
                    puntito = false;
                    euler = false;
                }
            }
        });
        btnPor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //operaciones("*");
                if (!div && !mul && !div && !sum) {
                    tvOper.setText(tvOper.getText().toString() + "*");
                    mul = true;
                    puntito = false;
                    euler = false;
                }
            }
        });
        btnSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!div && !mul && !div && !sum) {
                    tvOper.setText(tvOper.getText().toString() + "÷");
                    div = true;
                    puntito = false;
                    euler = false;
                }
            }
        });
        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fina = tvResul.getText().toString();
                tvOper.setText(fina);
                tvResul.setText("");
                puntito = false;
            }
        });


        int currentScreenOrientation = getResources().getConfiguration().orientation;

        if (currentScreenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "( horizontal )", Toast.LENGTH_LONG).show();
            tvOper.setText(oper);
            tvResul.setText(resul);
            //CARGAR EVENTOS CUANDO TAS ACOSTAO'
            btnINV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Compra la versión PRO", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            btnRAD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Compra la versión PRO", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            btnMOD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!tvResul.getText().toString().isEmpty()) {
                        funcionesExtras("%", 0);
                    }
                }
            });
            btnSENO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!tvResul.getText().toString().isEmpty()) {
                        //funcionesExtras("sin",);
                    }
                }
            });
            btnCOS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            btnTAN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            btnLn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            btnLOG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            btnRaiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tvOperaciones = tvOper.getText().toString();
                    if (!tvResul.getText().toString().isEmpty()) {
                        tvOper.setText(Math.sqrt(Double.parseDouble(tvResul.getText().toString())) + "");
                        tvResul.setText("");
                    } else if (!tvOperaciones.equals("") && (!tvOperaciones.contains("+") || !tvOperaciones.contains("-") || !tvOperaciones.contains("÷") || !tvOperaciones.contains("*"))) {
                        tvOper.setText(Math.sqrt(Double.parseDouble(tvOperaciones)) + "");
                        tvResul.setText("");
                    }
                }
            });
            btnPi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!pi && !euler) {
                        agregarCadena("3.141592");
                        pi = true;
                        puntito = true;
                        euler = true;
                    }

                }
            });
            btnE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!euler && !pi) {
                        agregarCadena("2.718281828459");
                        euler = true;
                        puntito = true;
                    }
                }
            });
            btnPot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tvOperaciones = tvOper.getText().toString();
                    if (!tvResul.getText().toString().isEmpty()) {
                        tvOper.setText(Math.pow(Double.parseDouble(tvResul.getText().toString()), 2) + "");
                        tvResul.setText("");
                    } else if (!tvOperaciones.equals("") && (!tvOperaciones.contains("+") || !tvOperaciones.contains("-") || !tvOperaciones.contains("÷") || !tvOperaciones.contains("*"))) {
                        tvOper.setText(Math.pow(Double.parseDouble(tvOperaciones), 2) + "");
                        tvResul.setText("");
                    }
                }
            });
            btnParDer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            btnFact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String tvOperaciones = tvOper.getText().toString();
                        if (!tvResul.getText().toString().isEmpty()) {
                            double a1 = Double.parseDouble(tvResul.getText().toString());
                            int a2 = (int) a1;
                            BigInteger fact = BigInteger.valueOf(1);
                            for (int i = 1; i <= a2; i++) {
                                fact = fact.multiply(BigInteger.valueOf(i));

                            }
                            tvOper.setText(fact + "");
                            tvResul.setText("");
                        } else if (!tvOperaciones.equals("") && (!tvOperaciones.contains("+") || !tvOperaciones.contains("-") || !tvOperaciones.contains("÷") || !tvOperaciones.contains("*"))) {
                            double a1 = Double.parseDouble(tvOperaciones);
                            int a2 = (int) a1;
                            BigInteger fact = BigInteger.valueOf(1);
                            for (int i = 1; i <= a2; i++) {
                                fact = fact.multiply(BigInteger.valueOf(i));
                            }
                            tvOper.setText(fact + "");
                            tvResul.setText("");
                        }
                    } catch (NumberFormatException e) {
                        //Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                        // toast.show();
                    }
                }
            });
        } else if (currentScreenOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            //Toast.makeText(this, "( vertical )", Toast.LENGTH_LONG).show();
        }


    }

    private void funcionesExtras(String s, int valor2) {
        Double gg = Double.parseDouble(tvResul.getText().toString());

        switch (s) {
            case "%":
                gg = gg / 100;
                break;
            case "sin":
                //gg=gg
                break;

        }

        tvOper.setText(gg + "");
        tvResul.setText("");

    }

    private void agregarCadena(String s) {
        tvOper.setText(tvOper.getText().toString() + s);
        sum = false;
        mul = false;
        div = false;
        res = false;
        calcular(tvOper.getText().toString());


    }

    private void calcular(String cadenaFinal) {
        String[] result = cadenaFinal.split("(?<=[^\\d.])(?=\\d)|(?<=\\d)(?=[^\\d.])");
        String op = null;
        double res = 0;
        try {
            for (String s : result) {
                if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("÷")) {
                    op = s;
                } else {
                    if (op == null) {
                        res = Double.parseDouble(s);
                    } else {
                        if (op.equals("+")) {
                            res += Double.parseDouble(s);
                        } else if (op.equals("-")) {
                            res -= Double.parseDouble(s);
                        } else if (op.equals("*")) {
                            res *= Double.parseDouble(s);
                        } else if (op.equals("÷")) {
                            res /= Double.parseDouble(s);
                        }
                    }
                }
            }

            tvResul.setText(res + "");
            oper = cadenaFinal;
            resul = res + "";
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private void cargarControles() {
        tvOper = findViewById(R.id.tv_operacion);
        tvResul = findViewById(R.id.tv_resul);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnPunto = findViewById(R.id.btnPunto);
        btnSobre = findViewById(R.id.btnSobre);
        btnPor = findViewById(R.id.btnPor);
        btnMenos = findViewById(R.id.btnMenos);
        btnMas = findViewById(R.id.btnMas);
        btnBorr = findViewById(R.id.btnCE);
        btnIgual = findViewById(R.id.btnIgual);
        // scientific buttons
        btnINV = findViewById(R.id.btnINV);
        btnRAD = findViewById(R.id.btnRAD);
        btnMOD = findViewById(R.id.btnMOD);
        btnSENO = findViewById(R.id.btnSENO);
        btnCOS = findViewById(R.id.btnCOS);
        btnTAN = findViewById(R.id.btnTAN);
        btnLn = findViewById(R.id.btnLn);
        btnLOG = findViewById(R.id.btnLOG);
        btnRaiz = findViewById(R.id.btnRaiz);
        btnPi = findViewById(R.id.btnPi);
        btnE = findViewById(R.id.btnE);
        btnPot = findViewById(R.id.btnPot);
        btnParIz = findViewById(R.id.btnParIz);
        btnParDer = findViewById(R.id.btnParDer);
        btnFact = findViewById(R.id.btnFact);

    }
}
