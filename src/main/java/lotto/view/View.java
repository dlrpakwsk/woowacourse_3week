package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class View {
    public static int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();
        return Integer.parseInt(input.trim());
    }

    public static String readWinningNumbers() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
        return Console.readLine().trim();
    }

    public static int readBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        return Integer.parseInt(Console.readLine().trim());
    }

    public static void printPurchasedLottos(List<List<Integer>> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (List<Integer> lotto : lottos) {
            System.out.println(lotto);
        }
        System.out.println();
    }

    public static void printStatisticsHeader() {
        System.out.println("당첨 통계\n---");
    }

    public static void printResult(List<String> resultLines, double profitRate) {
        resultLines.forEach(System.out::println);
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }

    public static void printError(String message) {
        System.out.println("[ERROR] " + message);
    }
}
