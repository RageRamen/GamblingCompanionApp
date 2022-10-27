package com.honeybadger.gamblingbadger.gamesession.blackjack;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.cards.Card;
import com.honeybadger.gamblingbadger.cards.ECardNum;
import com.honeybadger.gamblingbadger.cards.ECardSuit;
import com.honeybadger.gamblingbadger.databinding.FragmentBlackjackBinding;

import java.util.LinkedList;
import java.util.List;

public class BlackjackFragment extends Fragment {

    // CONSTANTS:

    // VARIABLES:

    private FragmentBlackjackBinding binding;

    private Card dealerCard;
    private List<Card> userHand;

    // FRAGMENT EVENTS:

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

    // INTERFACE:

    private void initSpinnerDealerCard() {
        Spinner spinnerDealerCard = getActivity().findViewById(R.id.blackjackFrag_spinner_dealerCard);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.blackjack_card_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerDealerCard.setAdapter(adapter);

        binding.blackjackFragSpinnerDealerCard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ECardNum number;
                switch ((int)l) {
                    default: number = ECardNum.ANY; break;
                    case 0: number = ECardNum.ACE; break;
                    case 1: number = ECardNum.N_2; break;
                    case 2: number = ECardNum.N_3; break;
                    case 3: number = ECardNum.N_4; break;
                    case 4: number = ECardNum.N_5; break;
                    case 5: number = ECardNum.N_6; break;
                    case 6: number = ECardNum.N_7; break;
                    case 7: number = ECardNum.N_8; break;
                    case 8: number = ECardNum.N_9; break;
                    case 9: number = ECardNum.N_10; break;
                    case 10: number = ECardNum.JACK; break;
                    case 11: number = ECardNum.QUEEN; break;
                    case 12: number = ECardNum.KING; break;
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
        AlertDialog.Builder dialogueBuilder = new AlertDialog.Builder(getContext());
        final View blackjackAddCardPopup = getLayoutInflater().inflate(R.layout.blackjack_add_card_popup, null);


        View button_A = blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_ace);
        View button_2 = blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_2);
        View button_3 = blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_3);
        View button_4 = blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_4);
        View button_5 = blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_5);
        View button_6 = blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_6);
        View button_7 = blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_7);
        View button_8 = blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_8);
        View button_9 = blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_9);
        View button_10 = blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_10);
        View button_J = blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_jack);
        View button_Q = blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_queen);
        View button_K = blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_king);
        View button_cancel = blackjackAddCardPopup.findViewById(R.id.blackjackAddCardPopup_button_cancel);

        dialogueBuilder.setView(blackjackAddCardPopup);
        AlertDialog dialog = dialogueBuilder.create();
        dialog.show();

        button_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.ACE));
                dialog.dismiss();
            }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_2));
                dialog.dismiss();
            }
        });
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_3));
                dialog.dismiss();
            }
        });
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_4));
                dialog.dismiss();
            }
        });
        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_5));
                dialog.dismiss();
            }
        });
        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_6));
                dialog.dismiss();
            }
        });
        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_7));
                dialog.dismiss();
            }
        });
        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_8));
                dialog.dismiss();
            }
        });
        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_9));
                dialog.dismiss();
            }
        });
        button_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_10));
                dialog.dismiss();
            }
        });
        button_J.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.JACK));
                dialog.dismiss();
            }
        });
        button_Q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.QUEEN));
                dialog.dismiss();
            }
        });
        button_K.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.KING));
                dialog.dismiss();
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
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