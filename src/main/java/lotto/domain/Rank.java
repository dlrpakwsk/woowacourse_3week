package lotto.domain;

import java.util.Arrays;

public enum  Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean bonusMatch;
    private final int prize;

    Rank(int matchCount, boolean bonusMatch, int prize) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.prize = prize;
    }

    public int getPrize() {
        return prize;
    }

    public static Rank valueOf(int matchCount, boolean bonusMatch) {
        if (matchCount < 3) {
            return NONE;
        }
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount && rank.bonusMatch == bonusMatch)
                .findFirst()
                .orElseGet(() ->
                        Arrays.stream(values())
                                .filter(rank -> rank.matchCount == matchCount && !rank.bonusMatch)
                                .findFirst()
                                .orElse(NONE)
                );
    }

    @Override
    public String toString() {
        return switch (this) {
            case FIRST -> "6개 일치 (2,000,000,000원)";
            case SECOND -> "5개 일치, 보너스 볼 일치 (30,000,000원)";
            case THIRD -> "5개 일치 (1,500,000원)";
            case FOURTH -> "4개 일치 (50,000원)";
            case FIFTH -> "3개 일치 (5,000원)";
            default -> "";
        };
    }
}
