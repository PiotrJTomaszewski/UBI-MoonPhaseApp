package pl.pjt.ubi_zad1

class MoonImage(var hemisphere: Char, var moonPhase: Double) {
    var imgName: Int = 0

    init {
        val convPhase: Double = moonPhase / 2
        val isFirstHalf: Boolean = (moonPhase <= 50)

        imgName = if (hemisphere == 'n') {
            if (isFirstHalf) {
                when {
                    convPhase <= 0.1 ->  R.drawable.n0_1p
                    convPhase <= 1.2 -> R.drawable.n1_2p
                    convPhase <= 4.5 -> R.drawable.n4_5p
                    convPhase <= 10 -> R.drawable.n10p
                    convPhase <= 17.5 -> R.drawable.n17_5p
                    convPhase <= 26.8 -> R.drawable.n26_8p
                    convPhase <= 37.3 -> R.drawable.n37_3p
                    convPhase <= 48.6 -> R.drawable.n48_6p
                    convPhase <= 60 -> R.drawable.n60p
                    convPhase <= 71 -> R.drawable.n71p
                    convPhase <= 80.8 -> R.drawable.n80_8p
                    convPhase <= 89 -> R.drawable.n89p
                    convPhase <= 95 -> R.drawable.n95p
                    convPhase <= 98.7 -> R.drawable.n98_7p
                    else -> R.drawable.n99_9p
                }
            } else {
                when {
                    convPhase <= 0.4 -> R.drawable.n0_4p_
                    convPhase <= 2.8 -> R.drawable.n2_8p_
                    convPhase <= 7.3 -> R.drawable.n7_3p_
                    convPhase <= 13.5 -> R.drawable.n13_5p_
                    convPhase <= 21 -> R.drawable.n21p_
                    convPhase <= 29.5 -> R.drawable.n29_5p_
                    convPhase <= 38.6 -> R.drawable.n38_6p_
                    convPhase <= 48 -> R.drawable.n48p_
                    convPhase <= 57.4 -> R.drawable.n57_4p_
                    convPhase <= 66.7 -> R.drawable.n66_7p_
                    convPhase <= 75.4 -> R.drawable.n75_4p_
                    convPhase <= 83.3 -> R.drawable.n83_3p_
                    convPhase <= 90 -> R.drawable.n90p_
                    convPhase <= 95.3 -> R.drawable.n95_3p_
                    else -> R.drawable.n98_7p_
                }
            }
        } else { // Southern hemisphere
            if (isFirstHalf) {
                when {
                    convPhase <= 0.1 -> R.drawable.s0_1p
                    convPhase <= 0.2 -> R.drawable.s0_2p
                    convPhase <= 0.4 -> R.drawable.s0_4p
                    convPhase <= 3 -> R.drawable.s3p
                    convPhase <= 8.3 -> R.drawable.s8_3p
                    convPhase <= 15.9 -> R.drawable.s15_9p
                    convPhase <= 25.4 -> R.drawable.s25_4p
                    convPhase <= 36.1 -> R.drawable.s36_1p
                    convPhase <= 47.4 -> R.drawable.s47_4p
                    convPhase <= 58.5 -> R.drawable.s58_5p
                    convPhase <= 69.1 -> R.drawable.s69_1p
                    convPhase <= 78.5 -> R.drawable.s78_5p
                    convPhase <= 86.4 -> R.drawable.s86_4p
                    convPhase <= 92.7 -> R.drawable.s92_7p
                    convPhase <= 97 -> R.drawable.s97p
                    else -> R.drawable.s99_4p
                }
            } else {
                when {
                    convPhase <= 0.1 -> R.drawable.s0_1p_
                    convPhase <= 1.4 -> R.drawable.s1_4p_
                    convPhase <= 5.4 -> R.drawable.s5_4p_
                    convPhase <= 11.7 -> R.drawable.s11_7p_
                    convPhase <= 19.7 -> R.drawable.s19_7p_
                    convPhase <= 28.9 -> R.drawable.s28_9p_
                    convPhase <= 38.7 -> R.drawable.s38_7p_
                    convPhase <= 48.7 -> R.drawable.s48_7p_
                    convPhase <= 58.5 -> R.drawable.s58_5p_
                    convPhase <= 67.7 -> R.drawable.s67_7p_
                    convPhase <= 76.2 -> R.drawable.s76_2p_
                    convPhase <= 83.3 -> R.drawable.s83_8p_
                    convPhase <= 90.1 -> R.drawable.s90_1p_
                    convPhase <= 95 -> R.drawable.s95p_
                    convPhase <= 98.3 -> R.drawable.s98_3p_
                    else -> R.drawable.s99_8p_
                }
            }
        }
    }
}