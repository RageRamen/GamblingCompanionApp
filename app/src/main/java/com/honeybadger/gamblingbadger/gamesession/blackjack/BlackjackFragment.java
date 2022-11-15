package com.honeybadger.gamblingbadger.gamesession.blackjack;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.honeybadger.gamblingbadger.ImageSpinnerAdapter;
import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.cards.Card;
import com.honeybadger.gamblingbadger.cards.ECardNum;
import com.honeybadger.gamblingbadger.cards.ECardSuit;
import com.honeybadger.gamblingbadger.databinding.FragmentBlackjackBinding;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BlackjackFragment extends Fragment {

    // CONSTANTS:

    // VARIABLES:

    private FragmentBlackjackBinding binding;

    private AlertDialog dialog;

    private static final Integer[] imageIconDatabase = {R.drawable.ace_of_spades,
            R.drawable._2_of_hearts, R.drawable._3_of_clubs, R.drawable._4_of_diamonds,
            R.drawable._5_of_spades, R.drawable._6_of_hearts, R.drawable._7_of_clubs,
            R.drawable._8_of_diamonds, R.drawable._9_of_spades, R.drawable._10_of_hearts,
            R.drawable.jack_of_clubs2, R.drawable.queen_of_diamonds2, R.drawable.king_of_spades2};

    private Card dealerCard;
    private List<Card> userHand;

    // FRAGMENT EVENTS:

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        dealerCard = new Card();
        userHand = new LinkedList<>();

        binding = FragmentBlackjackBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init Interface

        initSpinnerDealerCard();
        initButtonAddUserCard();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();

        inflater.inflate(R.menu.menu_blackjack, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_blackjack_item_changeNumberOfDecks) {


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // INTERFACE:

    private void initSpinnerDealerCard() {
        Spinner spinnerDealerCard = getActivity().findViewById(R.id.blackjackFrag_spinner_dealerCard);

        ImageSpinnerAdapter adapter = new ImageSpinnerAdapter(getActivity(), imageIconDatabase);

        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.blackjack_card_list, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerDealerCard.setAdapter(adapter);

        binding.blackjackFragSpinnerDealerCard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ECardNum number;
                switch ((int)l) {
                    default: number = ECardNum.ANY; break;
                    case 0: number = ECardNum.A; break;
                    case 1: number = ECardNum.N_2; break;
                    case 2: number = ECardNum.N_3; break;
                    case 3: number = ECardNum.N_4; break;
                    case 4: number = ECardNum.N_5; break;
                    case 5: number = ECardNum.N_6; break;
                    case 6: number = ECardNum.N_7; break;
                    case 7: number = ECardNum.N_8; break;
                    case 8: number = ECardNum.N_9; break;
                    case 9: number = ECardNum.N_10; break;
                    case 10: number = ECardNum.J; break;
                    case 11: number = ECardNum.Q; break;
                    case 12: number = ECardNum.K; break;
                }
                actionDealerCardSelect(new Card(ECardSuit.ANY, number));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initButtonAddUserCard() {
        binding.blackjackFragButtonAddUserCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionShowUserAddCardAlert();
            }
        });
    }

    // ACTIONS:

    private void actionDealerCardSelect(Card card) {
        dealerCard = card;
    }

    private void actionShowUserAddCardAlert() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        View blackjackAddCardPopup = getLayoutInflater().inflate(R.layout.blackjack_add_card_popup, null);

        Button buttonA = (Button) blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_ace);
        Button button2 = (Button) blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_2);
        Button button3 = (Button) blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_3);
        Button button4 = (Button) blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_4);
        Button button5 = (Button) blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_5);
        Button button6 = (Button) blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_6);
        Button button7 = (Button) blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_7);
        Button button8 = (Button) blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_8);
        Button button9 = (Button) blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_9);
        Button button10 = (Button) blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_10);
        Button buttonJ = (Button) blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_jack);
        Button buttonQ = (Button) blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_queen);
        Button buttonK = (Button) blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_king);
        Button buttonCancel = (Button) blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_cancel);

        dialogBuilder.setView(blackjackAddCardPopup);
        dialog = dialogBuilder.create();
        dialog.show();

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.A));
                dialog.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_2));
                dialog.dismiss();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_3));
                dialog.dismiss();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_4));
                dialog.dismiss();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_5));
                dialog.dismiss();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_6));
                dialog.dismiss();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_7));
                dialog.dismiss();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_8));
                dialog.dismiss();
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_9));
                dialog.dismiss();
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_10));
                dialog.dismiss();
            }
        });
        buttonJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.J));
                dialog.dismiss();
            }
        });
        buttonQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.Q));
                dialog.dismiss();
            }
        });
        buttonK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.K));
                dialog.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void actionAddCardToUserHand(Card card) {
        userHand.add(card);
    }

}