package game.player;

import game.Manager;
import game.Player;
import game.Poker;

import java.util.Collections;
import java.util.List;

public class GaoYuanPlayer implements Player {
    @Override
    public String getName() {
        return "GaoYuan";
    }

    @Override
    public String getStuNum() {
        return "2019211507";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        Collections.sort(pokers);
        if (p(pokers))
            return (int) 3.5 * moneyOnDesk;
        if (o(pokers))
            return (int) 2.8 * moneyOnDesk;
        if (x(pokers))
            return (int) (2.4 * moneyYouNeedToPayLeast) < 2.5 * moneyOnDesk ? (int) (3 * moneyYouNeedToPayLeast) : 3 * moneyOnDesk;
        if (y(pokers))
            return (int) (1.5 * moneyYouNeedToPayLeast) < moneyOnDesk ? (int) moneyYouNeedToPayLeast : 2 * moneyOnDesk;
        if (z(pokers))
            return (int) (2.1 * moneyYouNeedToPayLeast) < 2.5 * moneyOnDesk ? (int) (2.1 * moneyYouNeedToPayLeast) : 3 * moneyOnDesk;
        for (Poker p : pokers) {
            if (p.getPoint().getNum() >= 13)
                return (int) moneyYouNeedToPayLeast;
        }
        return 0;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }

    private boolean x(List<Poker> pokers) {
        return pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
                pokers.get(1).getSuit() == pokers.get(2).getSuit();//金花
    }

    private boolean y(List<Poker> pokers) {
        return pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum()
                || pokers.get(1).getPoint().getNum() == pokers.get(2).getPoint().getNum()
                || pokers.get(0).getPoint().getNum() == pokers.get(2).getPoint().getNum();//對子
    }

    private boolean z(List<Poker> pokers) {
        Collections.sort(pokers);
        return Math.abs(pokers.get(0).getPoint().getNum() - pokers.get(1).getPoint().getNum()) == 1
                && Math.abs(pokers.get(1).getPoint().getNum() - pokers.get(2).getPoint().getNum()) == 1;//順子
    }

    private boolean o(List<Poker> handCards) {
        return x(handCards) && z(handCards);//同花順
    }

    private boolean p(List<Poker> handCards) {
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();//豹子
    }

}
