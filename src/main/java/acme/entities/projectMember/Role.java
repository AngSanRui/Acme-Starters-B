package acme.entities.projectMember;
import acme.client.components.basis.AbstractRole;
import acme.realms.campaign.Spokesperson;
import acme.realms.inventor.Inventor;
import acme.realms.strategy.Fundraiser;

public enum Role {

	INVENTOR(Inventor.class), FUNDRAISER(Fundraiser.class), SPOKESPERSON(Spokesperson.class);


	private final Class<? extends AbstractRole> realmClass;


	Role(final Class<? extends AbstractRole> realmClass) {
		this.realmClass = realmClass;
	}

	public Class<? extends AbstractRole> getRealmClass() {
		return this.realmClass;
	}
}
