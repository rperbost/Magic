package player;

import java.rmi.RemoteException;

import server.DraftThread;

import binder.Booster;
import binder.Deck;
import binder.IBooster;
import binder.ICard;
import binder.IDeck;

public abstract class IPlayer {
	protected int chair;
	protected IBooster currentBooster;
	public int id=-1;

	protected DraftThread inProgressDraft;

	private String name;

	protected IDeck pick;

	protected boolean ready;

	private ICard selectedCard;

	public IPlayer(IPlayer p){
		this.setName(p.name);
		chair = p.chair;
		pick = p.pick;
		currentBooster = p.currentBooster;
		ready = p.ready;
		id = p.id;
		inProgressDraft = p.inProgressDraft;
	}

	public IPlayer(String name) throws RemoteException {
		this.setName(name);
		chair = -1;
		pick = new Deck();
		currentBooster = new Booster();
		ready = false;
		this.setId(-(int)(Math.random()*99999999));
		this.inProgressDraft = null;
	}
	public boolean equals(IPlayer p){
		return this.getId() == p.getId();
	}
	public int getChair() {
		return chair;
	}

	public IBooster getCurrentBooster() {
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

	public IDeck getPick() {
		return pick;
	}

	public ICard getSelectedCard() throws RemoteException {
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
	public void setCurrentBooster(IBooster currentBooster) throws RemoteException {
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

	public void setPick(IDeck pick) {
		this.pick = pick;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	
	public void setSelectedCard(ICard card) {
		this.selectedCard = card;
		this.setReady(true);
	}
	
	public void startDeckListScreen(IDeck deck) throws RemoteException{}

	public void startDraftScreen() throws RemoteException{}

}
