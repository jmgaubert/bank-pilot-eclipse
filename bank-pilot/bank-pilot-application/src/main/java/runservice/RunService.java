package runservice;

import fr.training.samples.bankpilot.application.holder.ServiceHolder;
import fr.training.samples.bankpilot.application.holder.ServiceHolderImpl;
import fr.training.samples.bankpilot.domain.Holder;
import fr.training.samples.bankpilot.infrastructure.DaoHolder;
import fr.training.samples.bankpilot.infrastructure.DaoHolderImpl;

public class RunService {

	public static void main(String[] args) {

		//déclaration des interfaces DAO --> on est on main donc pas de AutoWired, on est obligé de faire des new sur Impl
		ServiceHolder serviceHolder = new ServiceHolderImpl();
		DaoHolder daoHolder = new DaoHolderImpl();
		
	    System.out.println("------------------------TEST SUR HOLDER APPLICATION-------------------------------------------------------");
		
		Holder holderS1 = new Holder();
		holderS1.setName("DARWIN");
		holderS1.setForname("Charles");
		holderS1.setMailAddress("charles.darwin@orange.fr");
		System.out.println(holderS1);
        serviceHolder.createHolder(holderS1);


	}
}

