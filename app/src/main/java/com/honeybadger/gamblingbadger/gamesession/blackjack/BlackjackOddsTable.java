package com.honeybadger.gamblingbadger.gamesession.blackjack;

import com.honeybadger.gamblingbadger.cards.Card;
import com.honeybadger.gamblingbadger.cards.ECardNum;

import java.util.ArrayList;
import java.util.HashMap;

// ONLY SUPPORTS 6 DECKS RIGHT NOW

public class BlackjackOddsTable {

    private static final String SOFT = "soft";
    private static final String HARD = "hard";

    private static final HashMap<ECardNum, ArrayList<HashMap<String, Float>>> table = new HashMap<ECardNum, ArrayList<HashMap<String, Float>>>() {
        {
            put(ECardNum.A, new ArrayList<HashMap<String, Float>>() {
                {
                    add(new HashMap<String, Float>() {  // Value 4
                        {
                            put(HARD, -0.292f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 5
                        {
                            put(HARD, -0.317f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 6
                        {
                            put(HARD, -0.344f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 7
                        {
                            put(HARD, -0.351f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 8
                        {
                            put(HARD, -0.265f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 9
                        {
                            put(HARD, -0.126f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 10
                        {
                            put(HARD, 0.033f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 11
                        {
                            put(HARD, 0.115f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 12
                        {
                            put(SOFT, 0.521f);
                            put(HARD, -0.383f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 13
                        {
                            put(SOFT, -0.100f);
                            put(HARD, -0.427f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 14
                        {
                            put(SOFT, -0.0135f);
                            put(HARD, -0.468f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 15
                        {
                            put(SOFT, -0.172f);
                            put(HARD, -0.509f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 16
                        {
                            put(SOFT, -0.209f);
                            put(HARD, -0.540f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 17
                        {
                            put(SOFT, -0.221f);
                            put(HARD, -0.513f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 18
                        {
                            put(SOFT, -0.160f);
                            put(HARD, -0.223f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 19
                        {
                            put(SOFT, 0.189f);
                            put(HARD, 0.191f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 20
                        {
                            put(SOFT, 0.605f);
                            put(HARD, 0.600f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 21
                        {
                            put(SOFT, 1.0000f);
                            put(HARD, 1.0000f);
                        }
                    });
                }
            });
            put(ECardNum.N_2, new ArrayList<HashMap<String, Float>>() {
                {
                    add(new HashMap<String, Float>() {  // Value 4
                        {
                            put(HARD, -0.082f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 5
                        {
                            put(HARD, -0.126f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 6
                        {
                            put(HARD, -0.139f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 7
                        {
                            put(HARD, -0.107f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 8
                        {
                            put(HARD, -0.022f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 9
                        {
                            put(HARD, 0.073f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 10
                        {
                            put(HARD, 0.185f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 11
                        {
                            put(HARD, 0.241f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 12
                        {
                            put(SOFT, 0.000f);
                            put(HARD, -0.252f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 13
                        {
                            put(SOFT, 0.045f);
                            put(HARD, -0.289f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 14
                        {
                            put(SOFT, 0.022f);
                            put(HARD, -0.289f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 15
                        {
                            put(SOFT, -0.001f);
                            put(HARD, -0.286f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 16
                        {
                            put(SOFT, -0.021f);
                            put(HARD, -0.287f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 17
                        {
                            put(SOFT, 0.000f);
                            put(HARD, -0.157f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 18
                        {
                            put(SOFT, 0.113f);
                            put(HARD, 0.109f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 19
                        {
                            put(SOFT, 0.380f);
                            put(HARD, 0.377f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 20
                        {
                            put(SOFT, 0.637f);
                            put(HARD, 0.632f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 21
                        {
                            put(SOFT, 1.0000f);
                            put(HARD, 1.0000f);
                        }
                    });
                }
            });
            put(ECardNum.N_3, new ArrayList<HashMap<String, Float>>() {
                {
                    add(new HashMap<String, Float>() {  // Value 4
                        {
                            put(HARD, -0.080f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 5
                        {
                            put(HARD, -0.093f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 6
                        {
                            put(HARD, -0.104f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 7
                        {
                            put(HARD, -0.074f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 8
                        {
                            put(HARD, 0.008f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 9
                        {
                            put(HARD, 0.104f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 10
                        {
                            put(HARD, 0.210f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 11
                        {
                            put(HARD, 0.261f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 12
                        {
                            put(SOFT, 0.104f);
                            put(HARD, -0.231f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 13
                        {
                            put(SOFT, 0.073f);
                            put(HARD, -0.248f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 14
                        {
                            put(SOFT, 0.050f);
                            put(HARD, -0.246f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 15
                        {
                            put(SOFT, 0.029f);
                            put(HARD, -0.245f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 16
                        {
                            put(SOFT, 0.008f);
                            put(HARD, -0.246f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 17
                        {
                            put(SOFT, 0.029f);
                            put(HARD, -0.120f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 18
                        {
                            put(SOFT, 0.014f);
                            put(HARD, 0.137f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 19
                        {
                            put(SOFT, 0.399f);
                            put(HARD, 0.394f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 20
                        {
                            put(SOFT, 0.645f);
                            put(HARD, 0.643f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 21
                        {
                            put(SOFT, 1.0000f);
                            put(HARD, 1.0000f);
                        }
                    });
                }
            });
            put(ECardNum.N_4, new ArrayList<HashMap<String, Float>>() {
                {
                    add(new HashMap<String, Float>() {  // Value 4
                        {
                            put(HARD, -0.044f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 5
                        {
                            put(HARD, -0.055f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 6
                        {
                            put(HARD, -0.067f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 7
                        {
                            put(HARD, -0.037f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 8
                        {
                            put(HARD, 0.043f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 9
                        {
                            put(HARD, 0.133f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 10
                        {
                            put(HARD, 0.233f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 11
                        {
                            put(HARD, 0.285f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 12
                        {
                            put(SOFT, 0.000f);
                            put(HARD, -0.205f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 13
                        {
                            put(SOFT, 0.104f);
                            put(HARD, -0.203f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 14
                        {
                            put(SOFT, 0.082f);
                            put(HARD, -0.202f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 15
                        {
                            put(SOFT, 0.060f);
                            put(HARD, -0.202f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 16
                        {
                            put(SOFT, 0.040f);
                            put(HARD, -0.203f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 17
                        {
                            put(SOFT, 0.061f);
                            put(HARD, -0.080f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 18
                        {
                            put(SOFT, 0.171f);
                            put(HARD, 0.164f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 19
                        {
                            put(SOFT, 0.415f);
                            put(HARD, 0.413f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 20
                        {
                            put(SOFT, 0.655f);
                            put(HARD, 0.654f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 21
                        {
                            put(SOFT, 1.0000f);
                            put(HARD, 1.0000f);
                        }
                    });
                }
            });
            put(ECardNum.N_5, new ArrayList<HashMap<String, Float>>() {
                {
                    add(new HashMap<String, Float>() {  // Value 4
                        {
                            put(HARD, -0.003f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 5
                        {
                            put(HARD, -0.015f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 6
                        {
                            put(HARD, -0.026f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 7
                        {
                            put(HARD, 0.002f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 8
                        {
                            put(HARD, 0.079f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 9
                        {
                            put(HARD, 0.163f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 10
                        {
                            put(HARD, 0.262f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 11
                        {
                            put(HARD, 0.312f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 12
                        {
                            put(SOFT, 0.160f);
                            put(HARD, -0.161f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 13
                        {
                            put(SOFT, 0.137f);
                            put(HARD, -0.160f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 14
                        {
                            put(SOFT, 0.116f);
                            put(HARD, -0.160f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 15
                        {
                            put(SOFT, 0.094f);
                            put(HARD, -0.159f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 16
                        {
                            put(SOFT, 0.075f);
                            put(HARD, -0.160f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 17
                        {
                            put(SOFT, 0.098f);
                            put(HARD, -0.045f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 18
                        {
                            put(SOFT, 0.198f);
                            put(HARD, 0.195f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 19
                        {
                            put(SOFT, 0.439f);
                            put(HARD, 0.437f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 20
                        {
                            put(SOFT, 0.670f);
                            put(HARD, 0.668f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 21
                        {
                            put(SOFT, 1.0000f);
                            put(HARD, 1.0000f);
                        }
                    });
                }
            });
            put(ECardNum.N_6, new ArrayList<HashMap<String, Float>>() {
                {
                    add(new HashMap<String, Float>() {  // Value 4
                        {
                            put(HARD, 0.030f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 5
                        {
                            put(HARD, 0.019f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 6
                        {
                            put(HARD, 0.009f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 7
                        {
                            put(HARD, 0.037f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 8
                        {
                            put(HARD, 0.106f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 9
                        {
                            put(HARD, 0.189f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 10
                        {
                            put(HARD, 0.285f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 11
                        {
                            put(HARD, 0.334f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 12
                        {
                            put(SOFT, 0.189f);
                            put(HARD, -0.121f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 13
                        {
                            put(SOFT, 0.166f);
                            put(HARD, -0.121f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 14
                        {
                            put(SOFT, 0.146f);
                            put(HARD, -0.120f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 15
                        {
                            put(SOFT, 0.126f);
                            put(HARD, -0.120f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 16
                        {
                            put(SOFT, 0.110f);
                            put(HARD, -0.124f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 17
                        {
                            put(SOFT, 0.126f);
                            put(HARD, -0.009f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 18
                        {
                            put(SOFT, 0.222f);
                            put(HARD, 0.220f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 19
                        {
                            put(SOFT, 0.452f);
                            put(HARD, 0.450f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 20
                        {
                            put(SOFT, 0.677f);
                            put(HARD, 0.676f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 21
                        {
                            put(SOFT, 1.0000f);
                            put(HARD, 1.0000f);
                        }
                    });
                }
            });
            put(ECardNum.N_7, new ArrayList<HashMap<String, Float>>() {
                {
                    add(new HashMap<String, Float>() {  // Value 4
                        {
                            put(HARD, -0.088f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 5
                        {
                            put(HARD, -0.119f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 6
                        {
                            put(HARD, -0.153f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 7
                        {
                            put(HARD, -0.068f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 8
                        {
                            put(HARD, -0.083f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 9
                        {
                            put(HARD, 0.174f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 10
                        {
                            put(HARD, 0.258f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 11
                        {
                            put(HARD, 0.291f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 12
                        {
                            put(SOFT, 0.164f);
                            put(HARD, -0.212f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 13
                        {
                            put(SOFT, 0.120f);
                            put(HARD, -0.269f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 14
                        {
                            put(SOFT, 0.076f);
                            put(HARD, -0.324f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 15
                        {
                            put(SOFT, 0.036f);
                            put(HARD, -0.368f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 16
                        {
                            put(SOFT, 0.007f);
                            put(HARD, -0.408f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 17
                        {
                            put(SOFT, 0.054f);
                            put(HARD, -0.108f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 18
                        {
                            put(SOFT, 0.401f);
                            put(HARD, 0.397f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 19
                        {
                            put(SOFT, 0.615f);
                            put(HARD, 0.615f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 20
                        {
                            put(SOFT, 0.773f);
                            put(HARD, 0.772f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 21
                        {
                            put(SOFT, 1.0000f);
                            put(HARD, 1.0000f);
                        }
                    });
                }
            });
            put(ECardNum.N_8, new ArrayList<HashMap<String, Float>>() {
                {
                    add(new HashMap<String, Float>() {  // Value 4
                        {
                            put(HARD, -0.156f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 5
                        {
                            put(HARD, -0.187f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 6
                        {
                            put(HARD, -0.219f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 7
                        {
                            put(HARD, -0.211f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 8
                        {
                            put(HARD, -0.059f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 9
                        {
                            put(HARD, -0.099f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 10
                        {
                            put(HARD, 0.199f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 11
                        {
                            put(HARD, 0.227f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 12
                        {
                            put(SOFT, 0.094f);
                            put(HARD, -0.272f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 13
                        {
                            put(SOFT, 0.051f);
                            put(HARD, -0.324f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 14
                        {
                            put(SOFT, 0.016f);
                            put(HARD, -0.369f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 15
                        {
                            put(SOFT, 0.028f);
                            put(HARD, -0.416f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 16
                        {
                            put(SOFT, -0.069f);
                            put(HARD, -0.453f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 17
                        {
                            put(SOFT, -0.071f);
                            put(HARD, -0.348f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 18
                        {
                            put(SOFT, 0.108f);
                            put(HARD, 0.104f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 19
                        {
                            put(SOFT, 0.595f);
                            put(HARD, 0.591f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 20
                        {
                            put(SOFT, 0.790f);
                            put(HARD, 0.790f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 21
                        {
                            put(SOFT, 1.0000f);
                            put(HARD, 1.0000f);
                        }
                    });
                }
            });
            put(ECardNum.N_9, new ArrayList<HashMap<String, Float>>() {
                {
                    add(new HashMap<String, Float>() {  // Value 4
                        {
                            put(HARD, -0.237f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 5
                        {
                            put(HARD, -0.265f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 6
                        {
                            put(HARD, -0.294f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 7
                        {
                            put(HARD, -0.285f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 8
                        {
                            put(HARD, -0.209f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 9
                        {
                            put(HARD, -0.052f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 10
                        {
                            put(HARD, 0.116f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 11
                        {
                            put(HARD, 0.155f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 12
                        {
                            put(SOFT, -0.001f);
                            put(HARD, -0.340f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 13
                        {
                            put(SOFT, -0.034f);
                            put(HARD, -0.382f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 14
                        {
                            put(SOFT, -0.072f);
                            put(HARD, -0.428f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 15
                        {
                            put(SOFT, -0.112f);
                            put(HARD, -0.472f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 16
                        {
                            put(SOFT, -0.151f);
                            put(HARD, -0.504f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 17
                        {
                            put(SOFT, -0.147f);
                            put(HARD, -0.422f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 18
                        {
                            put(SOFT, -0.098f);
                            put(HARD, -0.185f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 19
                        {
                            put(SOFT, 0.287f);
                            put(HARD, 0.283f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 20
                        {
                            put(SOFT, 0.759f);
                            put(HARD, 0.756f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 21
                        {
                            put(SOFT, 1.0000f);
                            put(HARD, 1.0000f);
                        }
                    });
                }
            });
            put(ECardNum.N_10, new ArrayList<HashMap<String, Float>>() {
                {
                    add(new HashMap<String, Float>() {  // Value 4
                        {
                            put(HARD, -0.287f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 5
                        {
                            put(HARD, -0.312f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 6
                        {
                            put(HARD, -0.339f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 7
                        {
                            put(HARD, -0.319f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 8
                        {
                            put(HARD, -0.249f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 9
                        {
                            put(HARD, -0.153f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 10
                        {
                            put(HARD, 0.026f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 11
                        {
                            put(HARD, 0.117f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 12
                        {
                            put(SOFT, -0.066f);
                            put(HARD, -0.375f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 13
                        {
                            put(SOFT, -0.102f);
                            put(HARD, -0.420f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 14
                        {
                            put(SOFT, -0.137f);
                            put(HARD, -0.463f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 15
                        {
                            put(SOFT, -0.173f);
                            put(HARD, -0.503f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 16
                        {
                            put(SOFT, -0.210f);
                            put(HARD, -0.534f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 17
                        {
                            put(SOFT, -0.195f);
                            put(HARD, -0.418f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 18
                        {
                            put(SOFT, -0.142f);
                            put(HARD, -0.174f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 19
                        {
                            put(SOFT, 0.063f);
                            put(HARD, 0.069f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 20
                        {
                            put(SOFT, 0.554f);
                            put(HARD, 0.559f);
                        }
                    });
                    add(new HashMap<String, Float>() {  // Value 21
                        {
                            put(SOFT, 1.0000f);
                            put(HARD, 1.0000f);
                        }
                    });
                }
            });
            put(ECardNum.J, get(ECardNum.N_10));
            put(ECardNum.Q, get(ECardNum.N_10));
            put(ECardNum.K, get(ECardNum.N_10));
        }
    };

    public static float getValue(Card dealerCard, int hardValue, int softValue) {
        float exp_mod = -1.0f;
        String HardSoft;

        if (hardValue >= 4) {
            if (softValue == hardValue) HardSoft = HARD;
            else HardSoft = SOFT;

            if (softValue <= 21) {
                exp_mod = table.get(dealerCard.num).get(softValue).get(HardSoft);
            }
            else if (hardValue <= 21) {
                exp_mod = table.get(dealerCard.num).get(hardValue).get(HARD);
            }
        }

        return (exp_mod + 1f) / 2f;
    }
}
