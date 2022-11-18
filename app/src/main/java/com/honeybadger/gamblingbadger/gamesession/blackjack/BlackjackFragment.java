package com.honeybadger.gamblingbadger.gamesession.blackjack;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.honeybadger.gamblingbadger.ImageSpinnerAdapter;
import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.cards.Card;
import com.honeybadger.gamblingbadger.cards.ECardNum;
import com.honeybadger.gamblingbadger.cards.ECardSuit;
import com.honeybadger.gamblingbadger.casinosession.CasinoSessionFragment;
import com.honeybadger.gamblingbadger.databinding.FragmentBlackjackBinding;
import com.honeybadger.gamblingbadger.gamesession.StartGameFragment;
import com.honeybadger.gamblingbadger.tips.BJTipFrag;

import java.util.LinkedList;
import java.util.List;

public class BlackjackFragment extends Fragment {

    // CONSTANTS:

    // VARIABLES:

    private FragmentBlackjackBinding binding;

    private AlertDialog addCardDialog;

    private AlertDialog changeNumberOfDecksDialog;

    private static final Integer[] imageIconDatabase = {R.drawable.ace_of_spades,
            R.drawable._2_of_hearts, R.drawable._3_of_clubs, R.drawable._4_of_diamonds,
            R.drawable._5_of_spades, R.drawable._6_of_hearts, R.drawable._7_of_clubs,
            R.drawable._8_of_diamonds, R.drawable._9_of_spades, R.drawable._10_of_hearts,
            R.drawable.jack_of_clubs2, R.drawable.queen_of_diamonds2, R.drawable.king_of_spades2};

    private Card dealerCard;
    private List<Card> userHand;

    public static @Nullable BlackjackSession blackjackSession;
    public static int softNumberOfDecks = BlackjackSession.DEFAULT_NUMBER_OF_DECKS;

    // FRAGMENT EVENTS:

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Options menu
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
        initTextViewNumberOfDecks();
        initButtonGetOdds();
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
        if (id == android.R.id.home) {
            actionEndSession();
            return true; // must return true to consume it here
        }
        if (id == R.id.menu_blackjack_item_changeNumberOfDecks) {
            actionShowChangeNumberOfDecksAlert();
            return true;
        }
        if (id == R.id.menu_blackjack_item_setBet) {
            actionSetBet();
            return true;
        }
        if (id == R.id.menu_blackjack_item_tips) {
            actionGoToTips();
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

    private void initTextViewNumberOfDecks() {
        updateNumberOfDecks(softNumberOfDecks);
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
        addCardDialog = dialogBuilder.create();
        addCardDialog.show();

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.A));
                addCardDialog.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_2));
                addCardDialog.dismiss();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_3));
                addCardDialog.dismiss();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_4));
                addCardDialog.dismiss();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_5));
                addCardDialog.dismiss();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_6));
                addCardDialog.dismiss();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_7));
                addCardDialog.dismiss();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_8));
                addCardDialog.dismiss();
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_9));
                addCardDialog.dismiss();
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_10));
                addCardDialog.dismiss();
            }
        });
        buttonJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.J));
                addCardDialog.dismiss();
            }
        });
        buttonQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.Q));
                addCardDialog.dismiss();
            }
        });
        buttonK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.K));
                addCardDialog.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCardDialog.dismiss();
            }
        });
    }

    private void actionShowChangeNumberOfDecksAlert() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        View popupView = getLayoutInflater().inflate(R.layout.popup_blackjack_change_number_of_decks, null);

        TextView instructionsView = (TextView) popupView.findViewById(R.id.blackjackChangeNumberOfDecksPopup_textView_instructions);
        NumberPicker numberPicker = (NumberPicker) popupView.findViewById(R.id.blackjackChangeNumberOfDecksPopup_numPicker_number);
        Button applyButton = (Button) popupView.findViewById(R.id.blackjackChangeNumberOfDecksPopup_button_apply);

        dialogBuilder.setView(popupView);
        changeNumberOfDecksDialog = dialogBuilder.create();
        changeNumberOfDecksDialog.show();

        numberPicker.setMinValue(BlackjackSession.MINIMUM_NUMBER_OF_DECKS);
        numberPicker.setMaxValue(BlackjackSession.MAXIMUM_NUMBER_OF_DECKS);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setValue(blackjackSession.getNumberOfDecks());

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNumberOfDecks(numberPicker.getValue());
                changeNumberOfDecksDialog.dismiss();
            }
        });
    }

    private void updateNumberOfDecks(int numberOfDecks) {
        softNumberOfDecks = numberOfDecks;
        blackjackSession.setNumberOfDecks(numberOfDecks);
        TextView numberDisplay = (TextView) getActivity().findViewById(R.id.blackjackFrag_textView_numberOfDecks_display);
        numberDisplay.setText(String.valueOf(blackjackSession.getNumberOfDecks()));
    }

    private void actionAddCardToUserHand(Card card) {
        userHand.add(card);
    }

    private void actionGoToTips() {
        BJTipFrag.callingFragmentReturnActionID = R.id.action_bjtipsFragment_to_blackjackFragment;
        NavHostFragment.findNavController(this).navigate(R.id.action_blackjackFragment_to_bjtipsFragment);
    }

    private void actionSetBet() {
        // TODO
    }

    private void initButtonGetOdds() {
        binding.GetOddsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGetOdds();
            }
        });
    }

    private void actionGetOdds() {
        // TODO
        TextView oddsDisplay = (TextView) getActivity().findViewById(R.id.oddstextview);
        oddsDisplay.setText("HELLO");
    }

    private void actionWinGame() {
        // TODO
    }

    private void actionLoseGame() {
        // TODO
    }

    public void actionEndSession() {
        blackjackSession.closeSession();
        NavHostFragment.findNavController(this).navigate(R.id.action_blackjackFragment_to_casinoSessionFragment);
    }

}