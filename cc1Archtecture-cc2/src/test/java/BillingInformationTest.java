
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ecommercGesture.domain.objects.BillingInformation;
import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.repositories.BillingInformationRepository;
import ecommercGesture.domain.services.BillingInformationService;
import ecommercGesture.infrastructure.defaultRepositoryImplementation.InMemoryBillingInformationRepository;


public class BillingInformationTest {

	private static BillingInformationRepository billingInformationRepository;
	private static BillingInformationService billingInformationService;
	static Id firstId;
	static BillingInformation first;
	
	@BeforeClass
	public static void setup() {
		billingInformationRepository = new InMemoryBillingInformationRepository();
		billingInformationService = new BillingInformationService(billingInformationRepository);
		firstId = billingInformationService.getNextId();
		first = BillingInformation.of(firstId, Id.of(1), "first", "first", "first", "password");
		billingInformationService.addBillingInformation(first);
	}

	@Test
	public void getNextBillingInformationId() {
		Id id1 = billingInformationService.getNextId();
		Id id2 = billingInformationService.getNextId();
		assertEquals(true, id2.getId() == id1.getId() + 1);
	}
	
	@Test
	public void getBillingInformation() {
		BillingInformation billingInformation = billingInformationService.getbillingInformationById(firstId);
		assertEquals(true, billingInformation != null);
	}
	
	@Test
	public void getAllBillingInformation() {
		Id userId = billingInformationService.getNextId();
		BillingInformation newBillingInformation = BillingInformation.of(userId,Id.of(1), "test", "test", "test", "password");
		billingInformationService.addBillingInformation(newBillingInformation);
		List<BillingInformation> BillingInformationList = new ArrayList<BillingInformation>();
		BillingInformationList.add(first);
		BillingInformationList.add(newBillingInformation);
		List<BillingInformation> result = billingInformationService.getAll();
		assertEquals(true, result.get(0).equals(first));
	}
	
	@Test
	public void addBillingInformation() {
		Id BillingInformationId = billingInformationService.getNextId();
		BillingInformation newBillingInformation = BillingInformation.of(BillingInformationId,Id.of(1), "test", "test", "test", "password");
		BillingInformation result = billingInformationService.addBillingInformation(newBillingInformation);
		assertEquals(true, newBillingInformation.equals(result));
	}
	
	
	@Test
	public void billingInformationExist() {
		boolean exist = billingInformationService.billingInformationExist(firstId);
		assertEquals(true, exist);
	}

	@Test
	public void billingInformationNotExist() {
		boolean exist = billingInformationService.billingInformationExist(Id.of(100000));
		assertEquals(false, exist);
	}


}
