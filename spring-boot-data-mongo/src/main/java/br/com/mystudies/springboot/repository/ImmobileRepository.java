package br.com.mystudies.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.mystudies.springboot.domain.Immobile;

public interface ImmobileRepository extends MongoRepository<Immobile, String> {

	@Query("{ $and: [{\"pricingInfos.businessType\" : \"RENTAL\"},{ \"pricingInfos.rentalTotalPrice\": { $lte: 4000 } },{ $where: \"this.pricingInfos.monthlyCondoFee  <= this.pricingInfos.rentalTotalPrice * 0.3\" },{\"address.geoLocation.precision\": {$ne : \"NO_GEOCODE\"}} ] }")
	Page<Immobile> findAllImmobileToRentalForVivaReal(Pageable pageable);

	@Query("{ $and: [{\"pricingInfos.businessType\" : \"SALE\"},{ \"pricingInfos.price\": { $lte: 700000 } },{\"address.geoLocation.precision\": {$ne : \"NO_GEOCODE\"}} ] }")
	Page<Immobile> findAllImmobileToSaleForVivaReal(Pageable pageable);

	@Query("{ $and: [{\"pricingInfos.businessType\" : \"RENTAL\"},{ \"pricingInfos.rentalTotalPrice\": { $gte: 3500 } },{\"address.geoLocation.precision\": {$ne : \"NO_GEOCODE\"}} ] }")
	Page<Immobile> findAllImmobileToRentalForZap(Pageable pageable);

	@Query("{ $and: [{\"pricingInfos.businessType\" : \"SALE\"},{ \"pricingInfos.price\": { $gte: 600000 } },{ \"usableAreas\": { $gt: 3500 } },{\"address.geoLocation.precision\": {$ne : \"NO_GEOCODE\"}} ] }")
	Page<Immobile> findAllImmobileToSaleForZap(Pageable pageable);

}
