package com.honeybadger.gamblingbadger.gamesession.blackjack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

        binding.blackjackFragSpinnerDealerCard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ECardNum number;
                switch ((int)l) {
                    default: number = ECardNum.CN_ANY; break;
                    case 0: number = ECardNum.CN_A; break;
                    case 1: number = ECardNum.CN_2; break;
                    case 2: number = ECardNum.CN_3; break;
                    case 3: number = ECardNum.CN_4; break;
                    case 4: number = ECardNum.CN_5; break;
                    case 5: number = ECardNum.CN_6; break;
                    case 6: number = ECardNum.CN_7; break;
                    case 7: number = ECardNum.CN_8; break;
                    case 8: number = ECardNum.CN_9; break;
                    case 9: number = ECardNum.CN_10; break;
                    case 10: number = ECardNum.CN_J; break;
                    case 11: number = ECardNum.CN_Q; break;
                    case 12: number = ECardNum.CN_K; break;
                }
                actionDealerCardSelect(new Card(ECardSuit.CS_ANY, number));
            }
        });
    }

    // ACTIONS:

    private void actionDealerCardSelect(Card card) {
        dealerCard = card;
    }

}