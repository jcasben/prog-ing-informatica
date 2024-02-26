package com.jcasben.efficientsorting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textViewRand = findViewById(R.id.textViewRandArray);
        TextView textViewOrd = findViewById(R.id.textViewOrdArray);
        textViewRand.setMovementMethod(new ScrollingMovementMethod());
        textViewOrd.setMovementMethod(new ScrollingMovementMethod());
    }

    private int[] generateRandomArray(int n) {
        int [] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        return array;
    }

    public void appAction(View v) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                EditText editText = findViewById(R.id.editTextLength);
                int n = Integer.parseInt(editText.getText().toString());
                int [] array = generateRandomArray(n);
                String randomArray = printArray(array);
                long start = System.currentTimeMillis();
                selectionSort(array);
                long totalTime = System.currentTimeMillis() - start;
                String orderedArray = printArray(array);
                runOnUiThread(() -> {
                    TextView textView = findViewById(R.id.textViewRandArray);
                    textView.setText(String.format("%s%s", "Random Array: ", randomArray));
                    TextView textView2 = findViewById(R.id.textViewOrdArray);
                    textView2.setText(String.format("%s%s", "Ordered Array: ", orderedArray));
                    TextView textViewTime = findViewById(R.id.textViewTime);
                    textViewTime.setText("Time: " + totalTime + " ms");
                });
            }
        };
        thread.start();
    }

    private String printArray(int [] array) {
        StringBuilder arrayString = new StringBuilder();
        for (int a : array) {
            arrayString.append(a);
            arrayString.append(", ");
        }

        return arrayString.toString();
    }

    private void selectionSort(int [] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }
}