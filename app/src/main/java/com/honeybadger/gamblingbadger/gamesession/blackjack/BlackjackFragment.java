package com.honeybadger.gamblingbadger.gamesession.blackjack;

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
    private List<Card> userCards;

    // FRAGMENT EVENTS:

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        dealerCard = new Card();
        userCards = new LinkedList<>();

        binding = FragmentBlackjackBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init Interface

        initSpinnerDealerCard();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // INTERFACE:

    private void initSpinnerDealerCard() {
        Spinner spinnerDealerCard = getActivity().findViewById(R.id.blackjackFrag_spinner_dealerCard);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.blackjackFrag_spinner_dealerCard_content, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerDealerCard.setAdapter(adapter);

        binding.blackjackFragSpinnerDealerCard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
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
        });
    }

    // ACTIONS:

    private void actionDealerCardSelect(Card card) {
        dealerCard = card;
    }

}