package pl.pjt.ubi_zad1

class MoonImage(var hemisphere: Char, var moonPercent: Double, var isFirstHalf: Boolean) {
    var imgName: Int = 0

    init {
        imgName = if (hemisphere == 'n') {
            if (isFirstHalf) {
                when {
                    moonPercent <= 0.1 ->  R.drawable.n0_1p
                    moonPercent <= 1.2 -> R.drawable.n1_2p
                    moonPercent <= 4.5 -> R.drawable.n4_5p
                    moonPercent <= 10 -> R.drawable.n10p
                    moonPercent <= 17.5 -> R.drawable.n17_5p
                    moonPercent <= 26.8 -> R.drawable.n26_8p
                    moonPercent <= 37.3 -> R.drawable.n37_3p
                    moonPercent <= 48.6 -> R.drawable.n48_6p
                    moonPercent <= 60 -> R.drawable.n60p
                    moonPercent <= 71 -> R.drawable.n71p
                    moonPercent <= 80.8 -> R.drawable.n80_8p
                    moonPercent <= 89 -> R.drawable.n89p
                    moonPercent <= 95 -> R.drawable.n95p
                    moonPercent <= 98.7 -> R.drawable.n98_7p
                    else -> R.drawable.n99_9p
                }
            } else {
                when {
                    moonPercent <= 0.4 -> R.drawable.n0_4p_
                    moonPercent <= 2.8 -> R.drawable.n2_8p_
                    moonPercent <= 7.3 -> R.drawable.n7_3p_
                    moonPercent <= 13.5 -> R.drawable.n13_5p_
                    moonPercent <= 21 -> R.drawable.n21p_
                    moonPercent <= 29.5 -> R.drawable.n29_5p_
                    moonPercent <= 38.6 -> R.drawable.n38_6p_
                    moonPercent <= 48 -> R.drawable.n48p_
                    moonPercent <= 57.4 -> R.drawable.n57_4p_
                    moonPercent <= 66.7 -> R.drawable.n66_7p_
                    moonPercent <= 75.4 -> R.drawable.n75_4p_
                    moonPercent <= 83.3 -> R.drawable.n83_3p_
                    moonPercent <= 90 -> R.drawable.n90p_
                    moonPercent <= 95.3 -> R.drawable.n95_3p_
                    else -> R.drawable.n98_7p_
                }
            }
        } else { // Southern hemisphere
            if (isFirstHalf) {
                when {
                    moonPercent <= 0.1 -> R.drawable.s0_1p
                    moonPercent <= 0.2 -> R.drawable.s0_2p
                    moonPercent <= 0.4 -> R.drawable.s0_4p
                    moonPercent <= 3 -> R.drawable.s3p
                    moonPercent <= 8.3 -> R.drawable.s8_3p
                    moonPercent <= 15.9 -> R.drawable.s15_9p
                    moonPercent <= 25.4 -> R.drawable.s25_4p
                    moonPercent <= 36.1 -> R.drawable.s36_1p
                    moonPercent <= 47.4 -> R.drawable.s47_4p
                    moonPercent <= 58.5 -> R.drawable.s58_5p
                    moonPercent <= 69.1 -> R.drawable.s69_1p
                    moonPercent <= 78.5 -> R.drawable.s78_5p
                    moonPercent <= 86.4 -> R.drawable.s86_4p
                    moonPercent <= 92.7 -> R.drawable.s92_7p
                    moonPercent <= 97 -> R.drawable.s97p
                    else -> R.drawable.s99_4p
                }
            } else {
                when {
                    moonPercent <= 0.1 -> R.drawable.s0_1p_
                    moonPercent <= 1.4 -> R.drawable.s1_4p_
                    moonPercent <= 5.4 -> R.drawable.s5_4p_
                    moonPercent <= 11.7 -> R.drawable.s11_7p_
                    moonPercent <= 19.7 -> R.drawable.s19_7p_
                    moonPercent <= 28.9 -> R.drawable.s28_9p_
                    moonPercent <= 38.7 -> R.drawable.s38_7p_
                    moonPercent <= 48.7 -> R.drawable.s48_7p_
                    moonPercent <= 58.5 -> R.drawable.s58_5p_
                    moonPercent <= 67.7 -> R.drawable.s67_7p_
                    moonPercent <= 76.2 -> R.drawable.s76_2p_
                    moonPercent <= 83.3 -> R.drawable.s83_8p_
                    moonPercent <= 90.1 -> R.drawable.s90_1p_
                    moonPercent <= 95 -> R.drawable.s95p_
                    moonPercent <= 98.3 -> R.drawable.s98_3p_
                    else -> R.drawable.s99_8p_
                }
            }
        }
    }
}