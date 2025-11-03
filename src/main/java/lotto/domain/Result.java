package lotto.domain;

import lotto.Lotto;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Result {
    private final Map<Rank, Integer> results = new EnumMap<>(Rank.class);
    private final double profitRate;

    public Result(List<Lotto> lottos, WinningLotto winningLotto, int purchaseAmount) {
        calculateResults(lottos, winningLotto);
        this.profitRate = calculateProfitRate(purchaseAmount);
    }

    // 당첨 결과 계산
    private void calculateResults(List<Lotto> lottos, WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            int matchCount = countMatches(lotto, winningLotto.getWinningNumbers());
            boolean bonusMatch = lotto.getNumbers().contains(winningLotto.getBonusNumber());
            Rank rank = Rank.valueOf(matchCount, bonusMatch);
            results.put(rank, results.getOrDefault(rank, 0) + 1);
        }
    }

    // 맞은 번호 개수 계산
    private int countMatches(Lotto lotto, List<Integer> winningNumbers) {
        return (int) lotto.getNumbers().stream()
                .filter(winningNumbers::contains)
                .count();
    }

    // 수익률 계산
    private double calculateProfitRate(int purchaseAmount) {
        int totalPrize = results.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
        return ((double) totalPrize / purchaseAmount) * 100;
    }

    public Map<Rank, Integer> getResults() {
        return results;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
