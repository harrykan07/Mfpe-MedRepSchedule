package com.cognizantmfpe.repservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * The Interface MedicineStockFeignClient.
 */
@FeignClient(name = "medicine-stock", url = "http://65.0.251.158:8080")
public interface MedicineStockFeignClient {

	/**
	 * Gets the medicineList by ailment.
	 *
	 * @param ailment the ailment
	 * @return the medicineList by ailment
	 */
	@GetMapping("/medicine/getByAilment/{ailment}")
	public String[] getMedicineByAilment(@RequestHeader("Authorization") String token, @PathVariable String ailment);
}
