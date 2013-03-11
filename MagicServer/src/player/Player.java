package player;

import java.rmi.RemoteException;

import server.DraftThread;

import binder.ImplementedBooster;
import binder.ImplementedDeck;
import binder.interfaces.Booster;
import binder.interfaces.Card;
import binder.interfaces.Deck;

public abstract class Player {
	protected int chair;
	protected Booster currentBooster;
	public int id=-1;

	protected DraftThread inProgressDraft;

	private String name;

	protected Deck pick;

	protected boolean ready;

	private Card selectedCard;

	public Player(Player p){
		this.setName(p.name);
		chair = p.chair;
		pick = p.pick;
		currentBooster = p.currentBooster;
		ready = p.ready;
		id = p.id;
		inProgressDraft = p.inProgressDraft;
	}

	public Player(String name) throws RemoteException {
		this.setName(name);
		chair = -1;
		pick = new ImplementedDeck();
		currentBooster = new ImplementedBooster();
		ready = false;
		this.setId(-(int)(Math.random()*99999999));
		this.inProgressDraft = null;
	}
	public boolean equals(Player p){
		return this.getId() == p.getId();
	}
	public int getChair() {
		return chair;
	}

	public Booster getCurrentBooster() {
		return currentBooster;
	}

	public int getId() {
		return id;
	}
	
	public DraftThread getInProgressDraft() {
		return this.inProgressDraft;
	}

	public String getName() {
		return name;
	}

	public Deck getPick() {
		return pick;
	}

	public Card getSelectedCard() throws RemoteException {
		if(selectedCard == null)return currentBooster.get(0);
		return selectedCard;
	}

	public int getSelectedCardId() throws RemoteException {
		if(selectedCard == null)return 999;
		for(int i = 0 ; i < currentBooster.size();i++){
			if(currentBooster.get(i).equals(selectedCard))return i;
		}
		return 999;
	}

	public boolean isReady() {
		return ready;
	}

	public boolean ping() throws RemoteException{
		return true;
	}

	public void refresh() throws RemoteException{
	}

	public void setChair(int i) {
		chair = i;
	}
	public void setCurrentBooster(Booster currentBooster) throws RemoteException {
		this.currentBooster = currentBooster;
		this.selectedCard = null;
		this.setReady(false);
	}

	public void setId(int id) {
		this.id=id;
	}

	public void setInProgressDraft(DraftThread draftThread) {
		this.inProgressDraft = draftThread;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPick(Deck pick) {
		this.pick = pick;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	
	public void setSelectedCard(Card card) {
		this.selectedCard = card;
		this.setReady(true);
	}
	
	public void startDeckListScreen(Deck deck) throws RemoteException{}

	public void startDraftScreen() throws RemoteException{}

}
