package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;
import java.util.ArrayList;
import java.util.List;


public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;

    // 구입 금액에 따른 로또 생성
    public List<Lotto> generateLotto(int purchaseAmount) {
        validateAmount(purchaseAmount);
        int count = purchaseAmount / LOTTO_PRICE;
        List<Lotto> lotto = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lotto.add(generateSingleLotto());
        }
        return lotto;
    }

    //금액 유효성 검사
    private void validateAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException("[ERROR] 최소 구입 금액은 1,000원 이상이어야 합니다.");
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위로 입력해야 합니다.");
        }
    }

    // 로또 한 개 생성
    private Lotto generateSingleLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }
}
