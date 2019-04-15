package com.example.inclass08;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExpenseAppFragment.ExpenseAppFragmentInterface, AddExpenseFragment.AddExpenseFragmentInterface
{

    static final ArrayList<Expense> expenseList = new ArrayList<>();
    final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference("expenses");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Expense App");

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_container, new ExpenseAppFragment() ,"ExpenseAppFragment")
                .commit();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot expensesnap: dataSnapshot.getChildren()){

                    Expense expense = expensesnap.getValue(Expense.class);
                    Log.d("demo","expense from main"+expense.toString());
                    expenseList.add(expense);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        System.out.println(expenseList.size() + "expenses");
    }

    @Override
    public void gotoAddExpenseFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, AddExpenseFragment.setData(expenseList), "AddExpenses").commit();
    }

   @Override
    public void AddButtonClicked(Expense expense) {
       ;
       expenseList.add(expense);
       Log.d("demo", "test1 :" + expense.toString());
       getSupportFragmentManager()
               .beginTransaction()
               .replace(R.id.layout_container, ExpenseAppFragment.setData(expenseList), "AddExpenses")
               .commit();

       String key = databaseReference.push().getKey();
       expense.setKey(key);

       databaseReference.child(key).setValue(expense);
    }

}
