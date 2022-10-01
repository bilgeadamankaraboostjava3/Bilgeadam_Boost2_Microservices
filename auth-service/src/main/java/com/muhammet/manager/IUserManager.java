package com.muhammet.manager;

import com.muhammet.dto.request.NewUserCreateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Başka bir microservisteki end pointleri çağırmak için kullanılır.
 * Ulaşmak istediğiniz controller ın url bilgisini vermelisiniz.
 * Tüm feign işlemlerine benzersiz adlandırmalar yapılmalıdır.
 */
@FeignClient(url = "${raceapplication.url.user}v1/api/user",name = "user-service-userprofile",decode404 = true)
public interface IUserManager {
    @PostMapping("/newcreateuser")
    ResponseEntity<Boolean> NewUserCreate(NewUserCreateDto dto);
}
