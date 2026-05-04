
package acme.features.manager.member;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.realms.managers.Manager;
import acme.realms.members.Member;

@Service
public class ManagaMemberCreateService extends AbstractService<Manager, Member> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerMemberRepository	repository;

	private Collection<Member>		members;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		// TODO Auto-generated method stub
		super.authorise();
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		super.load();
	}

	@Override
	public void unbind() {
		// TODO Auto-generated method stub
		super.unbind();
	}

}
