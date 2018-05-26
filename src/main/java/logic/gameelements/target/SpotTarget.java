package logic.gameelements.target;

public class SpotTarget extends AbstractTarget {
    public SpotTarget() {
        super(0);
    }

    @Override
    public int hit() {
        notifyObservers("triggerJackPotBonus");
        deactivate();
        notifyObservers("droppedSpotTarget");
        return getScore();
    }

    @Override
    public void getTargetType() {
        notifyObservers("SpotTarget");
    }
}
