package ihm.card;

@SuppressWarnings("serial")
public class CardPositionned extends CardDecorator{

	public CardPositionned(int x,int y,CardDrawable parent) {
		super(parent);
		super.setLocation(x, y);
	}

}
