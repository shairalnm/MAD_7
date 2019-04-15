package com.example.inclass08;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExpenseAppFragment extends Fragment {

   /* private Button buttonAdd;
    private TextView textviewExpenseName, textViewAmount, textViewCategory;
    ListView listView;
    ExpenseAdapter adapter = null;
    ArrayList<Expense> data = new ArrayList<>();*/
   private ExpenseAppFragment.ExpenseAppFragmentInterface mListener;
    private ListView myListView;
    //private ExpenseAdapter expenseAdapter;
    private TextView textView;
    private ArrayList<Expense> expenseNew = new ArrayList<>();
    static  String ARG_PARAM="ItemList";
    private View view;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public ExpenseAppFragment() {
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
    public static ExpenseAppFragment newInstance(String param1, String param2) {
        ExpenseAppFragment fragment = new ExpenseAppFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static ExpenseAppFragment setData(ArrayList<Expense> expense) {
        ExpenseAppFragment fragment = new ExpenseAppFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM,expense);
        fragment.setArguments(args);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       /* buttonAdd = getActivity().findViewById(R.id.addButton);
        textviewExpenseName = getActivity().findViewById(R.id.textViewExpenseName);
        textViewAmount = getActivity().findViewById(R.id.textViewExpenseAmount);
        textViewCategory = getActivity().findViewById(R.id.textViewExpenseCategory);
        listView =  getActivity().findViewById(R.id.expenselistView);*/

        /*if(adapter == null) {
            //progressBar.setVisibility(View.INVISIBLE);
            adapter = new ExpenseAdapter(ExpenseAppFragment.this, R.layout.expense_list, data);
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }*/


       /* buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("demo","expense in Expense App fragment");

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.layout_container, new AddExpenseFragment(), "Display_fragment")
                        .addToBackStack(null)
                        .commit();
            }
        });*/

        getActivity().findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoAddExpenseFragment();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ExpenseAppFragmentInterface) {
            mListener = (ExpenseAppFragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        if(view==null) {
            view=inflater.inflate(R.layout.fragment_expense_app, container,false);
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }
        myListView = view.findViewById(R.id.expenselistView);
        //myListView.setAdapter(expenseAdapter);
        myListView.setVisibility(View.VISIBLE);
        textView = view.findViewById(R.id.noExpensetextView);
        textView.setVisibility(View.INVISIBLE);
        return view;
        /*return inflater.inflate(R.layout.fragment_expense_app, container, false);*/
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Expense expense) {

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onStart() {

        super.onStart();
        getActivity().setTitle("Expense App");
        if (getArguments() != null) {
            expenseNew = (ArrayList<Expense>) getArguments().getSerializable(ARG_PARAM);
            final ExpenseAdapter expenseAdapter = new ExpenseAdapter(view.getContext(), R.layout.expense_list, expenseNew);
            myListView.setAdapter(expenseAdapter);
            myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Expense expenseClick = expenseAdapter.getItem(position);
                    expenseNew.remove(expenseClick);
                    if(expenseNew.isEmpty()){
                        myListView.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.VISIBLE);
                    }else {
                        myListView.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                    }
                    expenseAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "Expense Deleted.", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Expense expenseClick = expenseAdapter.getItem(position);
                    getFragmentManager().beginTransaction().replace(R.id.layout_container, ShowExpenseFragment.setData(expenseClick,expenseNew), "ShowExpenses").commit();
                }
            });
        }
        if(expenseNew.isEmpty()){
            myListView.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
        }else {
            myListView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.INVISIBLE);
        }
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
    public interface ExpenseAppFragmentInterface {
        // TODO: Update argument type and name
        void gotoAddExpenseFragment();
    }
}
