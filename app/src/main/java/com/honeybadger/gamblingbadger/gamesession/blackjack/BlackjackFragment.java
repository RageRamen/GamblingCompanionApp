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
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.honeybadger.gamblingbadger.ImageSpinnerAdapter;
import com.honeybadger.gamblingbadger.R;
import com.honeybadger.gamblingbadger.balance.Balance;
import com.honeybadger.gamblingbadger.cards.Card;
import com.honeybadger.gamblingbadger.cards.ECardNum;
import com.honeybadger.gamblingbadger.cards.ECardSuit;
import com.honeybadger.gamblingbadger.casinosession.CasinoSessionFragment;
import com.honeybadger.gamblingbadger.databinding.FragmentBlackjackBinding;
import com.honeybadger.gamblingbadger.tips.BJTipFrag;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

public class BlackjackFragment extends Fragment {

    // CONSTANTS:

    // VARIABLES:

    private FragmentBlackjackBinding binding;

    private AlertDialog addCardDialog;
    private AlertDialog setBetDialog; //added this for setting bets
    private AlertDialog changeNumberOfDecksDialog;

    private static final Integer[] imageIconDatabase = {R.drawable.ace_of_spades,
            R.drawable._2_of_hearts, R.drawable._3_of_clubs, R.drawable._4_of_diamonds,
            R.drawable._5_of_spades, R.drawable._6_of_hearts, R.drawable._7_of_clubs,
            R.drawable._8_of_diamonds, R.drawable._9_of_spades, R.drawable._10_of_hearts,
            R.drawable.jack_of_clubs2, R.drawable.queen_of_diamonds2, R.drawable.king_of_spades2};

    private static Card dealerCard = new Card(ECardSuit.ANY, ECardNum.A);
    private static List<Card> userHand = new LinkedList<>();
    private static BigDecimal bet = BigDecimal.ZERO;

    public static @Nullable BlackjackSession blackjackSession = null;
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
        if (blackjackSession == null)
            blackjackSession = new BlackjackSession(CasinoSessionFragment.casinoSession);

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
        initButtonWinGame();
        initButtonLoseGame();

        updateUserCardDisplay();
        updateBetDisplay();
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
            actionGoToBlackjackTips();
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
        updateDealerCardDisplay();

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
        actionSetNumberOfDecks(softNumberOfDecks);
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
                updateUserCardDisplay();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_2));
                addCardDialog.dismiss();
                updateUserCardDisplay();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_3));
                addCardDialog.dismiss();
                updateUserCardDisplay();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_4));
                addCardDialog.dismiss();
                updateUserCardDisplay();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_5));
                addCardDialog.dismiss();
                updateUserCardDisplay();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_6));
                addCardDialog.dismiss();
                updateUserCardDisplay();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_7));
                addCardDialog.dismiss();
                updateUserCardDisplay();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_8));
                addCardDialog.dismiss();
                updateUserCardDisplay();
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_9));
                addCardDialog.dismiss();
                updateUserCardDisplay();
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.N_10));
                addCardDialog.dismiss();
                updateUserCardDisplay();
            }
        });
        buttonJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.J));
                addCardDialog.dismiss();
                updateUserCardDisplay();
            }
        });
        buttonQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.Q));
                addCardDialog.dismiss();
                updateUserCardDisplay();
            }
        });
        buttonK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAddCardToUserHand(new Card(ECardSuit.ANY, ECardNum.K));
                addCardDialog.dismiss();
                updateUserCardDisplay();
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
                actionSetNumberOfDecks(numberPicker.getValue());
                changeNumberOfDecksDialog.dismiss();
            }
        });
    }

    private void actionSetNumberOfDecks(int numberOfDecks) {
        softNumberOfDecks = numberOfDecks;
        blackjackSession.setNumberOfDecks(numberOfDecks);
        updateNumberOfDecksDisplay();
    }

    private void actionAddCardToUserHand(Card card) {
        userHand.add(card);
    }

    private void actionGoToBlackjackTips() {
        BJTipFrag.callingFragmentReturnActionID = R.id.action_bjtipsFragment_to_blackjackFragment;
        NavHostFragment.findNavController(this).navigate(R.id.action_blackjackFragment_to_bjtipsFragment);
    }

    private void actionSetBet() {
        // TODO
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        View blackjackSetBetPopup = getLayoutInflater().inflate(R.layout.blackjack_set_bet_popup, null);

        Button ConfirmButton = (Button) blackjackSetBetPopup.findViewById(R.id.ConfirmButton);
        Button CancelButton = (Button) blackjackSetBetPopup.findViewById(R.id.CancelButton);
        EditText SetBetInput = (EditText) blackjackSetBetPopup.findViewById(R.id.TextInputSetBet);

        dialogBuilder.setView(blackjackSetBetPopup);
        setBetDialog = dialogBuilder.create();

        //bet = new BigDecimal(SetBetInput.getText().toString());

        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    bet = new BigDecimal(SetBetInput.getText().toString());
                    updateBetDisplay();
                }
                catch (NumberFormatException e) {
                    //catch prevents bet from getting an invalid number. Also prevent crash if bet is an invalid value
                }
                setBetDialog.dismiss();
            }
        });

        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBetDialog.dismiss();
            }
        });

        setBetDialog.show();
    }

    private void initButtonGetOdds() {
        binding.GetOddsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGetOdds();
                actionGetRecommendedAction();
            }
        });
    }

    private void actionGetOdds() {
        TextView oddsDisplay = (TextView) getActivity().findViewById(R.id.oddsTextview);
        BlackjackRound blackjackround = new BlackjackRound(bet, softNumberOfDecks, userHand, dealerCard);
        String odds = String.format("%.2f", 100 * blackjackround.getOdds());
        oddsDisplay.setText(odds + "%");
    }

    private void actionGetRecommendedAction(){
        TextView RecommendedActionDisplay = (TextView) getActivity().findViewById(R.id.RecommendedAction_textview_Blackjack);
        BlackjackRound round = new BlackjackRound(bet, softNumberOfDecks, userHand, dealerCard);
        String action = "";
        switch (round.getRecommendedAction(getContext())) {
            case BlackjackRound.HIT: action = "Hit"; break;
            case BlackjackRound.STAND: action = "Stand"; break;
            case BlackjackRound.BUST: action = "Busted"; break;
            case BlackjackRound.DOUBLE_DOWN_ELSE_STAND: action = "Double down, else stand"; break;
            case BlackjackRound.DOUBLE_DOWN_ELSE_HIT: action = "Double down, else hit"; break;
            case BlackjackRound.SPLIT: action = "Split"; break;
            case BlackjackRound.SPLIT_IF_DD_AFTER_SPLITS_ELSE_HIT: action = "Split if DD after splits, else hit"; break;
            case BlackjackRound.SPLIT_IF_DD_AFTER_SPLITS_ELSE_STAND: action = "Split if DD after splits, else stand"; break;
            case BlackjackRound.SPLIT_IF_DD_AFTER_SPLITS_ELSE_DH: action = "Split if DD after splits, else DD, else hit"; break;
            case BlackjackRound.SURRENDER_ELSE_HIT: action = "Surrender, else hit"; break;
            case BlackjackRound.SURRENDER_ELSE_STAND: action = "Surrender, else stand"; break;
            case BlackjackRound.SURRENDER_ELSE_SPLIT: action = "Surrender, else split"; break;
            default:
                break;
        }
        RecommendedActionDisplay.setText(action);
    }

    private void initButtonWinGame() {
        binding.WinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionWinGame();
            }
        });
    }

    private void initButtonLoseGame() {
        binding.LoseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionLoseGame();
            }
        });
    }


    private void updateBetDisplay() {
        TextView betDisplay = (TextView) getActivity().findViewById(R.id.betTextview);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        betDisplay.setText(formatter.format(bet)); //displays the bet input
    }

    private void updateDealerCardDisplay() {
        Spinner display = getActivity().findViewById(R.id.blackjackFrag_spinner_dealerCard);
        switch (dealerCard.num) {
            case A:     display.setSelection(0);    break;
            case N_2:   display.setSelection(1);    break;
            case N_3:   display.setSelection(2);    break;
            case N_4:   display.setSelection(3);    break;
            case N_5:   display.setSelection(4);    break;
            case N_6:   display.setSelection(5);    break;
            case N_7:   display.setSelection(6);    break;
            case N_8:   display.setSelection(7);    break;
            case N_9:   display.setSelection(8);    break;
            case N_10:  display.setSelection(9);    break;
            case J:     display.setSelection(10);   break;
            case Q:     display.setSelection(11);   break;
            case K:     display.setSelection(12);   break;
            default:
                break;
        }
    }

    private void updateUserCardDisplay(){
        TextView UserCardAmountTextView = (TextView) getActivity().findViewById(R.id.UserCardAmount);
        String tempcardview = "";
        for(int x = 0; x < userHand.size(); x++){
            tempcardview += userHand.get(x).getECardNum() + ", ";
        }
        UserCardAmountTextView.setText(tempcardview);
    }

    private void updateNumberOfDecksDisplay() {
        TextView numberDisplay = (TextView) getActivity().findViewById(R.id.blackjackFrag_textView_numberOfDecks_display);
        numberDisplay.setText(String.valueOf(blackjackSession.getNumberOfDecks()));
    }


    private void actionWinGame() {
        // TODO
        BlackjackRound blackjackround = new BlackjackRound(bet, softNumberOfDecks, userHand, dealerCard);
        blackjackround.setWin(true);
        blackjackSession.applyRound(blackjackround); //adds the round info into sesssion even if you win or lose since winning or losing means you're done with that single round
        userHand.clear();
        updateUserCardDisplay();
        actionAddToBalance();
    }

    private void actionLoseGame() {
        // TODO
        BlackjackRound blackjackround = new BlackjackRound(bet, softNumberOfDecks, userHand, dealerCard);
        blackjackround.setWin(false);
        blackjackSession.applyRound(blackjackround);
        userHand.clear();
        updateUserCardDisplay();
        actionSubToBalance();
    }

    private void actionAddToBalance() {

        if(bet != null){
            assert CasinoSessionFragment.casinoSession != null;
            //Get the existing balance in a casino session by calling getBalance()
            // the Balance returned by getBalance() is the balance of the casino session itself
            Balance casinoSessionBalance = CasinoSessionFragment.casinoSession.getBalance();
            casinoSessionBalance.addToBalance(bet, getActivity()); //test
        }
    }

    private void actionSubToBalance() {
        if(bet != null){
            assert CasinoSessionFragment.casinoSession != null;
            //Get the existing balance in a casino session by calling getBalance()
            // the Balance returned by getBalance() is the balance of the casino session itself
            Balance casinoSessionBalance = CasinoSessionFragment.casinoSession.getBalance();
            casinoSessionBalance.subFromBalance(bet, getActivity()); //test
        }

    }

    public void actionEndSession() {
        blackjackSession.closeSession();
        NavHostFragment.findNavController(this).navigate(R.id.action_blackjackFragment_to_casinoSessionFragment);
    }

}