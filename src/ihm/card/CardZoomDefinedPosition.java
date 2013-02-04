package ihm.card;

@SuppressWarnings("serial")
public class CardZoomDefinedPosition extends CardZoom{

	public CardZoomDefinedPosition(int x, int y,CardDrawable parent) {
		super(parent);
		zoom.setLocation(x,y);
	}
	
}
