package ihm;

import java.awt.Component;

import javax.swing.JPanel;

import player.Player;

public class ClickableCardFactory {

	public static Component getClickableCard(Player p,int cardId, JPanel panel) {
		if(cardId == p.getSelectedCardId()){
			return new SelectedClickableCard(p,cardId,panel);
		}else{
			return new ClickableCard(p,cardId,panel);
		}
	}

}
