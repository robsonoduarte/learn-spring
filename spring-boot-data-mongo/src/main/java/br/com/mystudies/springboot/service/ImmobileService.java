package br.com.mystudies.springboot.service;

import static org.apache.commons.lang3.StringUtils.upperCase;
import static org.springframework.data.domain.Page.empty;
import static org.springframework.data.domain.PageRequest.of;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.mystudies.springboot.domain.Immobile;
import br.com.mystudies.springboot.repository.ImmobileRepository;

@Service
public class ImmobileService {

	private static final int PAGE_SIZE = 20;
	private static final String PORTAL_ZAP = "ZAP";
	private static final String PORTAL_VIVALREAL = "VIVAREAL";


	@Autowired
	private ImmobileRepository immobileRepository;


	@Autowired
	private BoundingBox boundingBoxGrupoZapService;




	public Page<Immobile> getPropertiesToRental(ImmobileParameters parameters) {

		validateParameter(parameters);

		if(PORTAL_ZAP.equals(upperCase(parameters.getPortal()))) {
			return immobileRepository.findAllImmobileToRentalForZap(pageable(parameters));
		}

		if(PORTAL_VIVALREAL.equals(upperCase(parameters.getPortal()))) {
			Page<Immobile> page = immobileRepository.findAllImmobileToRentalForVivaReal(pageable(parameters));

			List<Immobile> content = new ArrayList<>();

			page.forEach( immobile -> {
				if(isInBoundingBox(immobile)) {
					content.add(immobile.increaseRentalTotalPrice(50));
				}else {
					content.add(immobile);
				}
			});

			return newPage(page, content);
		}

		return empty();
	}







	public Page<Immobile> getPropertiesToSale(ImmobileParameters parameters) {

		validateParameter(parameters);

		if(PORTAL_ZAP.equals(upperCase(parameters.getPortal()))) {

			Page<Immobile> page = immobileRepository.findAllImmobileToSaleForZap(pageable(parameters));

			List<Immobile> content = new ArrayList<>();

			page.forEach( immobile -> {
				if(isInBoundingBox(immobile)) {
					content.add(immobile.decreasePrice(10));
				}else {
					content.add(immobile);
				}
			});

			return newPage(page, content);
		}

		if(PORTAL_VIVALREAL.equals(upperCase(parameters.getPortal()))) {
			return immobileRepository.findAllImmobileToSaleForVivaReal(pageable(parameters));
		}

		return empty();
	}







	private void validateParameter(ImmobileParameters parameters) {
		if(parameters == null) {
			throw new IllegalArgumentException("parameters is null");
		}
	}

	private Pageable pageable(ImmobileParameters parameters) {
		return of(parameters.getPage(), PAGE_SIZE);
	}

	private boolean isInBoundingBox(Immobile immobile) {
		return boundingBoxGrupoZapService.isBoundingbox(immobile.location());
	}

	private PageImpl<Immobile> newPage(Page<Immobile> page, List<Immobile> content) {
		return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
	}
}
