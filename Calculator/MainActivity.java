package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> insets);

        TextView tvResult = findViewById(R.id.tvResult);
        int[] numberBtnIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot};
        int[] opBtnIds = {R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv};
        Button btnEq = findViewById(R.id.btnEq);
        Button btnClear = findViewById(R.id.btnClear);

        StringBuilder input = new StringBuilder();

        View.OnClickListener numberListener = v -> {
            Button b = (Button) v;
            input.append(b.getText());
            tvResult.setText(input.toString());
        };
        for (int id : numberBtnIds) {
            findViewById(id).setOnClickListener(numberListener);
        }

        View.OnClickListener opListener = v -> {
            if (input.length() == 0) return;
            char last = input.charAt(input.length() - 1);
            if ("+-×÷".indexOf(last) != -1) {
                input.setCharAt(input.length() - 1, ((Button) v).getText().charAt(0));
            } else {
                input.append(((Button) v).getText());
            }
            tvResult.setText(input.toString());
        };
        for (int id : opBtnIds) {
            findViewById(id).setOnClickListener(opListener);
        }

        btnClear.setOnClickListener(v -> {
            input.setLength(0);
            tvResult.setText("0");
        });

        btnEq.setOnClickListener(v -> {
            try {
                String expr = input.toString().replace('×', '*').replace('÷', '/');
                double result = eval(expr);
                if (result == (long) result) {
                    tvResult.setText(String.valueOf((long) result));
                } else {
                    tvResult.setText(String.valueOf(result));
                }
                input.setLength(0);
            } catch (Exception e) {
                tvResult.setText("Error");
                input.setLength(0);
            }
        });
    }

    // Simple eval for +, -, *, /
    private double eval(String expr) {
        return new Object() {
            int pos = -1, ch;
            void nextChar() { ch = (++pos < expr.length()) ? expr.charAt(pos) : -1; }
            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) { nextChar(); return true; }
                return false;
            }
            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expr.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }
            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }
            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();
                double x;
                int startPos = this.pos;
                if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expr.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }
                return x;
            }
        }.parse();
    }
}