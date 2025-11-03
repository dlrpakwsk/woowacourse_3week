package lotto.controller;

import lotto.Lotto;
import lotto.domain.*;
import lotto.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LottoController {

    public void run() {
        int purchaseAmount = readPurchaseAmount();
        LottoMachine machine = new LottoMachine();
        List<Lotto> purchasedLotto = machine.generateLotto(purchaseAmount);

        View.printPurchasedLottos(purchasedLotto.stream()
                .map(Lotto::getNumbers)
                .toList());

        WinningLotto winningLotto = readWinningLotto();
        Result result = new Result(purchasedLotto, winningLotto, purchaseAmount);

        View.printStatisticsHeader();
        View.printResult(formatResults(result.getResults()), result.getProfitRate());
    }

    // 구입 금액 입력 처리
    private int readPurchaseAmount() {
        try {
            return View.readPurchaseAmount();
        } catch (IllegalArgumentException e) {
            View.printError(e.getMessage());
            return readPurchaseAmount();
        }
    }

    // 당첨 번호 입력 처리
    private WinningLotto readWinningLotto() {
        try {
            String winningInput = View.readWinningNumbers();
            List<Integer> winningNumbers = Arrays.stream(winningInput.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();

            int bonusNumber = View.readBonusNumber();
            return new WinningLotto(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            View.printError(e.getMessage());
            return readWinningLotto();
        }
    }
    
    // 출력 형식 맞추기
    private List<String> formatResults(Map<Rank, Integer> results) {
        List<Rank> orderedRanks = List.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST);
        List<String> lines = new ArrayList<>();
        for (Rank rank : orderedRanks) {
            if (rank == Rank.NONE) continue;
            lines.add(String.format("%s - %d개", rank.toString(), results.getOrDefault(rank, 0)));
        }
        return lines;
    }
}
