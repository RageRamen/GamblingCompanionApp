package com.honeybadger.gamblingbadger.gamesession.poker;

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
import com.honeybadger.gamblingbadger.databinding.FragmentPokerBinding;
import com.honeybadger.gamblingbadger.tips.PokerTipFrag;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

public class PokerFragment extends Fragment {

    // CONSTANTS:

    // VARIABLES:

    private FragmentPokerBinding binding;

    private AlertDialog setBetDialog;

    private Card[] userHand = new Card[5];
    private BigDecimal bet = BigDecimal.ZERO;

    public static @Nullable PokerSession pokerSession = null;


    private static final Integer[] imageIconDatabase = {R.drawable.ace_of_spades,
            R.drawable._2_of_hearts, R.drawable._3_of_clubs, R.drawable._4_of_diamonds,
            R.drawable._5_of_spades, R.drawable._6_of_hearts, R.drawable._7_of_clubs,
            R.drawable._8_of_diamonds, R.drawable._9_of_spades, R.drawable._10_of_hearts,
            R.drawable.jack_of_clubs2, R.drawable.queen_of_diamonds2, R.drawable.king_of_spades2};

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
        //default dealer cards is all aces, just as a default card setup
        setDefaultUserCards();

        if (pokerSession == null)
            pokerSession = new PokerSession(CasinoSessionFragment.casinoSession);

        binding = FragmentPokerBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init Interface
        initSpinnerDealerCard();
        initCalculateButton();
        initWinButton();
        initLoseButton();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();

        inflater.inflate(R.menu.menu_poker, menu);

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
        if (id == R.id.menu_poker_item_setBet) {
            actionSetBet();
            return true;
        }
        if (id == R.id.menu_poker_item_tips) {
            actionGoToPokerTips();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void actionEndSession() {
        if (pokerSession != null)
            pokerSession.closeSession();
        pokerSession = null;
        NavHostFragment.findNavController(this).navigate(R.id.action_pokerFragment_to_casinoSessionFragment);
    }

    // INTERFACE:

    private void setDefaultUserCards(){
        userHand[0] = new Card(ECardSuit.ANY, ECardNum.A);
        userHand[1] = new Card(ECardSuit.ANY, ECardNum.A);
        userHand[2] = new Card(ECardSuit.ANY, ECardNum.A);
        userHand[3] = new Card(ECardSuit.ANY, ECardNum.A);
        userHand[4] = new Card(ECardSuit.ANY, ECardNum.A);
    }

    private void initSpinnerDealerCard() {
        Spinner spinnerDealerCard1 = getActivity().findViewById(R.id.poker_spinner_dealerCard1);
        Spinner spinnerDealerCard2 = getActivity().findViewById(R.id.poker_spinner_dealerCard2);
        Spinner spinnerDealerCard3 = getActivity().findViewById(R.id.poker_spinner_dealerCard3);
        Spinner spinnerDealerCard4 = getActivity().findViewById(R.id.poker_spinner_dealerCard4);
        Spinner spinnerDealerCard5 = getActivity().findViewById(R.id.poker_spinner_dealerCard5);

        ImageSpinnerAdapter adapter = new ImageSpinnerAdapter(getActivity(), imageIconDatabase);

        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.blackjack_card_list, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerDealerCard1.setAdapter(adapter);
        spinnerDealerCard2.setAdapter(adapter);
        spinnerDealerCard3.setAdapter(adapter);
        spinnerDealerCard4.setAdapter(adapter);
        spinnerDealerCard5.setAdapter(adapter);

        binding.pokerSpinnerDealerCard1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ECardNum number;
                switch ((int)i) {
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
                userHand[0] = new Card(ECardSuit.ANY, number);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.pokerSpinnerDealerCard2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ECardNum number;
                switch ((int)i) {
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
                userHand[1] = new Card(ECardSuit.ANY, number);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.pokerSpinnerDealerCard3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ECardNum number;
                switch ((int)i) {
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
                userHand[2] = new Card(ECardSuit.ANY, number);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.pokerSpinnerDealerCard4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ECardNum number;
                switch ((int)i) {
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
                userHand[3] = new Card(ECardSuit.ANY, number);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.pokerSpinnerDealerCard5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ECardNum number;
                switch ((int)i) {
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
                userHand[4] = new Card(ECardSuit.ANY, number);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initCalculateButton() {
        binding.CalculateButtonPoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionUpdateOddsTextview();
                actionUpdateRecommendedActionTextview();
            }
        });
    }

    private void initWinButton() {
        binding.WinButtonPoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO link the method in PokerSession that does this
                actionWinGame();
            }
        });
    }

    private void initLoseButton() {
        binding.LoseButtonPoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO link the method in PokerSession that does this
                actionLoseGame();
            }
        });
    }

    // ACTIONS:

    private void actionGoToPokerTips() {
        PokerTipFrag.callingFragmentReturnActionID = R.id.action_pokerTipFrag_to_pokerFragment;
        NavHostFragment.findNavController(this).navigate(R.id.action_pokerFragment_to_pokerTipFrag);
    }


    private void actionUpdateOddsTextview(){
        //TODO link whatever method used when calculate button is pressed. Should calculate the odds value
        TextView oddsDisplay = (TextView) getActivity().findViewById(R.id.Odds_textview);
        PokerRound round = new PokerRound(bet, userHand);
        String odds = String.format("%.2f", 100 * round.getOdds());
        oddsDisplay.setText(odds + "%");
    }

    private void actionSetBet() { //NOTE: Ignore the naming that 'blackjack_set_bet_popup' is here. Realized the popup is the same for all.
        // TODO
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        View blackjackSetBetPopup = getLayoutInflater().inflate(R.layout.blackjack_set_bet_popup, null);

        Button ConfirmButton = (Button) blackjackSetBetPopup.findViewById(R.id.ConfirmButton);
        Button CancelButton = (Button) blackjackSetBetPopup.findViewById(R.id.CancelButton);
        EditText SetBetInput = (EditText) blackjackSetBetPopup.findViewById(R.id.TextInputSetBet);

        dialogBuilder.setView(blackjackSetBetPopup);
        setBetDialog = dialogBuilder.create();
        setBetDialog.show();

        //bet = new BigDecimal(SetBetInput.getText().toString()); //DONT USE THIS, CRASHES APP. Keeping this here just for now

        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    bet = new BigDecimal(SetBetInput.getText().toString());
                    TextView betDisplay = (TextView) getActivity().findViewById(R.id.Bet_textview_Poker);

                    NumberFormat formatter = NumberFormat.getCurrencyInstance();
                    betDisplay.setText(formatter.format(bet)); //displays the bet input
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


        //TextView betDisplay = (TextView) getActivity().findViewById(R.id.betTextview);
        //betDisplay.setText("HELLO"); //temporary test in place of setBet method
    }

    private void actionUpdateRecommendedActionTextview(){
        //TODO link whatever method used when calculate button is pressed. Should use odds values to return a string for recommended action
        TextView RecommendedActionDisplay = (TextView) getActivity().findViewById(R.id.recommendedAction_textview);

        PokerRound pokerRound = new PokerRound(bet, userHand);
        List<Card> discardHands = new LinkedList<>();
        discardHands = pokerRound.getRecommendedDiscards();

        String tempcardview = "";
        for(int x = 0; x < discardHands.size(); x++){
            tempcardview += discardHands.get(x).getECardNum() + ":" + discardHands.get(x).getECardSuit() + ", ";
        }
        RecommendedActionDisplay.setText(tempcardview); //placeholder test output
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

    private void actionWinGame(){
        //TODO link to when the win button is pressed. Record the round into the PokerSession as a win
        PokerRound pokerRound = new PokerRound(bet);
        pokerRound.setWin(true);
        pokerSession.applyRound(pokerRound);
        actionAddToBalance();
    }

    private void actionLoseGame(){
        //TODO link to when the lose button is pressed. Record the round into the PokerSession as a lose
        PokerRound pokerRound = new PokerRound(bet);
        pokerRound.setWin(false);
        pokerSession.applyRound(pokerRound);
        actionSubToBalance();
    }

}