package com.honeybadger.gamblingbadger.gamesession.texasholdem;

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
import com.honeybadger.gamblingbadger.cards.ECardNum;
import com.honeybadger.gamblingbadger.casinosession.CasinoSessionFragment;
import com.honeybadger.gamblingbadger.databinding.FragmentTexasHoldemBinding;
import com.honeybadger.gamblingbadger.gamesession.poker.PokerRound;
import com.honeybadger.gamblingbadger.gamesession.poker.PokerSession;
import com.honeybadger.gamblingbadger.tips.TXTipFrag;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class TexasHoldemFragment extends Fragment {

    // CONSTANTS:

    // VARIABLES:

    private FragmentTexasHoldemBinding binding;

    private AlertDialog setBetDialog;

    private BigDecimal bet = BigDecimal.ZERO;

    private static final Integer[] imageIconDatabase = {R.drawable.ace_of_spades,
            R.drawable._2_of_hearts, R.drawable._3_of_clubs, R.drawable._4_of_diamonds,
            R.drawable._5_of_spades, R.drawable._6_of_hearts, R.drawable._7_of_clubs,
            R.drawable._8_of_diamonds, R.drawable._9_of_spades, R.drawable._10_of_hearts,
            R.drawable.jack_of_clubs2, R.drawable.queen_of_diamonds2, R.drawable.king_of_spades2};

    public static @Nullable TexasHoldemSession texasHoldemSession = null;

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

        if (texasHoldemSession == null)
            texasHoldemSession = new TexasHoldemSession(CasinoSessionFragment.casinoSession);

        binding = FragmentTexasHoldemBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init Interface
        initSpinnerDealerCard();
        initWinButton();
        initLoseButton();
        initCalculateButton();
        initUserHandCards();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();

        inflater.inflate(R.menu.menu_texas_holdem, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == android.R.id.home) {
            actionEndSession();
            return true; // must return true to consume it here
        }
        if (id == R.id.menu_texasHoldem_item_setBet) {
            actionSetBet();
            return true;
        }
        if (id == R.id.menu_texasHoldem_item_tips) {
            actionGoToTexasHoldemTips();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // INTERFACE:

    private void initSpinnerDealerCard() {
        Spinner spinnerDealerCard1 = getActivity().findViewById(R.id.texasholdem_spinner_dealerCard1);
        Spinner spinnerDealerCard2 = getActivity().findViewById(R.id.texasholdem_spinner_dealerCard2);
        Spinner spinnerDealerCard3 = getActivity().findViewById(R.id.texasholdem_spinner_dealerCard3);
        Spinner spinnerDealerCard4 = getActivity().findViewById(R.id.texasholdem_spinner_dealerCard4);
        Spinner spinnerDealerCard5 = getActivity().findViewById(R.id.texasholdem_spinner_dealerCard5);

        ImageSpinnerAdapter adapter = new ImageSpinnerAdapter(getActivity(), imageIconDatabase);

        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.blackjack_card_list, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerDealerCard1.setAdapter(adapter);
        spinnerDealerCard2.setAdapter(adapter);
        spinnerDealerCard3.setAdapter(adapter);
        spinnerDealerCard4.setAdapter(adapter);
        spinnerDealerCard5.setAdapter(adapter);

        binding.texasholdemSpinnerDealerCard1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.texasholdemSpinnerDealerCard2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.texasholdemSpinnerDealerCard3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.texasholdemSpinnerDealerCard4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.texasholdemSpinnerDealerCard5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initUserHandCards() {
        Spinner spinnerUserCard1 = getActivity().findViewById(R.id.texasholdem_spinner_userCard1);
        Spinner spinnerUserCard2 = getActivity().findViewById(R.id.texasholdem_spinner_userCard2);

        ImageSpinnerAdapter adapter = new ImageSpinnerAdapter(getActivity(), imageIconDatabase);

        spinnerUserCard1.setAdapter(adapter);
        spinnerUserCard2.setAdapter(adapter);

        binding.texasholdemSpinnerUserCard1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.texasholdemSpinnerUserCard2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initCalculateButton() {
        binding.CalculateButtonTexasholdem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionUpdateOddsTextview();
                actionUpdateRecommendedActionTextview();
            }
        });
    }

    private void initWinButton() {
        binding.WinButtonTexasholdem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionWinGame();
            }
        });
    }

    private void initLoseButton() {
        binding.LoseButtonTexasholdem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionLoseGame();
            }
        });
    }

    // ACTIONS:

    private void actionGoToTexasHoldemTips() {
        TXTipFrag.callingFragmentReturnActionID = R.id.action_TXTipFrag_to_texasHoldemFragment;
        NavHostFragment.findNavController(this).navigate(R.id.action_texasHoldemFragment_to_TXTipFrag);
    }

    private void actionEndSession() {
        if (texasHoldemSession != null)
            texasHoldemSession.closeSession();
        texasHoldemSession = null;
        NavHostFragment.findNavController(this).navigate(R.id.action_texasHoldemFragment_to_casinoSessionFragment);
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
                    TextView betDisplay = (TextView) getActivity().findViewById(R.id.bet_textview_Texasholdem);

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

    private void actionUpdateOddsTextview(){
        //TODO link whatever method used when calculate button is pressed. Should calculate the odds value
        TextView oddsDisplay = (TextView) getActivity().findViewById(R.id.Odds_textview_TexasHoldem);
        oddsDisplay.setText("N/A"); //placeholder test output
    }

    private void actionUpdateRecommendedActionTextview(){
        //TODO link whatever method used when calculate button is pressed. Should use odds values to return a string for recommended action
        TextView RecommendedActionDisplay = (TextView) getActivity().findViewById(R.id.RecommendedAction_textview_TexasHoldem);
        RecommendedActionDisplay.setText("N/A"); //placeholder test output
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
        TexasHoldemRound texasHoldemRound = new TexasHoldemRound(bet);
        texasHoldemRound.setWin(true);
        actionAddToBalance();
    }

    private void actionLoseGame(){
        //TODO link to when the lose button is pressed. Record the round into the PokerSession as a lose
        TexasHoldemRound texasHoldemRound = new TexasHoldemRound(bet);
        texasHoldemRound.setWin(false);
        actionSubToBalance();
    }

}