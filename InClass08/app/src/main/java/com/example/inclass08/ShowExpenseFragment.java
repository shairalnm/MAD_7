package com.example.inclass08;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ShowExpenseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View view;
    private TextView tvExpenseName, tvCategory, tvAmount, tvDate;
    private Expense expense;
    private Button closeButton;
    private ArrayList<Expense> expenseNew;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /*private ShowExpenseFragmentInterface mListener;*/

    public ShowExpenseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowExpenseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowExpenseFragment newInstance(String param1, String param2) {
        ShowExpenseFragment fragment = new ShowExpenseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment setData(Expense expenseClick, ArrayList<Expense> expense) {
        ShowExpenseFragment fragment = new ShowExpenseFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1,expenseClick);
        args.putSerializable(ARG_PARAM2,expense);
        fragment.setArguments(args);
        Log.d("demo", "setData" );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view==null)
        {
            view=inflater.inflate(R.layout.fragment_show_expense, container,false);
        }
        else
        {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }

        tvAmount = view.findViewById(R.id.tvAmount);
        tvCategory = view.findViewById(R.id.tvCategory);
        tvExpenseName = view.findViewById(R.id.tvExpenseName);
        tvDate = view.findViewById(R.id.tvExpenseDate);
        Button edit = view.findViewById(R.id.btnedit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddExpenseFragment appFragment = new AddExpenseFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("expense",expense);
                appFragment.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.layout_container, appFragment ,"AddExpenseFragment")
                        .commit();
            }
        });
        return view;
        /*return inflater.inflate(R.layout.fragment_show_expense, container, false);*/
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof ShowExpenseFragmentInterface) {
            mListener = (ShowExpenseFragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        /*mListener = null;*/
    }
    public void onStart() {
        super.onStart();
        getActivity().setTitle("Show Expense");
        if (getArguments() != null) {
            expense = (Expense) getArguments().getSerializable(ARG_PARAM1);
            expenseNew = (ArrayList<Expense>) getArguments().getSerializable(ARG_PARAM2);
            tvExpenseName.setText(expense.getExpenseName());
            tvCategory.setText(expense.getCategory());
            tvDate.setText(expense.getDate().toString());
            tvAmount.setText("$"+expense.getAmount());
        }
        getActivity().findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.layout_container, ExpenseAppFragment.setData(expenseNew), "AddExpenses").commit();
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*public interface ShowExpenseFragmentInterface {
        // TODO: Update argument type and name
        void showExpenseFragment(Expense expense);
    }*/
}
