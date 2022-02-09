package ecommercGesture.infrastructure.defaultRepositoryImplementation;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import ecommercGesture.domain.objects.BillingInformation;
import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.repositories.BillingInformationRepository;
import ecommercGesture.infrastructure.exception.NoSuchBillingIformationException;
import kernel.NoSuchEntityException;

public class InMemoryBillingInformationRepository implements BillingInformationRepository{

	private final AtomicInteger count = new AtomicInteger(0);
	private final Map<Id, BillingInformation> data = new ConcurrentHashMap<>();

	@Override
	public Id getNextId() {
		return Id.of(count.incrementAndGet());
	}

	@Override
	public BillingInformation getBillingInformationById(Id id) {
		BillingInformation billingInformation = data.get(id);
		if (billingInformation == null) {
			throw NoSuchEntityException.withIdAndElem(id,"billing information");
		}
		return billingInformation;
	}

	@Override
	public List<BillingInformation> getAll() {
		List<BillingInformation> result = data.values().stream().collect(Collectors.toList());
		return  result;
	}

	@Override
	public BillingInformation saveBillingInformation(BillingInformation billingInformation) {
		data.put(billingInformation.getId(), billingInformation);
		return billingInformation;
	}

	@Override
	public BillingInformation getBillingInformationByUserId(Id userId) {
		for (Map.Entry<Id, BillingInformation> entry : data.entrySet()) {
			if(entry.getValue().getUserId().equals(userId)) {
				return entry.getValue();
			}
		}
		throw NoSuchBillingIformationException.withId(userId);
	}

}
