package com.muhammet.service;

import com.muhammet.repository.IAuthRepository;
import com.muhammet.repository.entity.Auth;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
// @ActiveProfiles("test") - Aktif profile ı değiştirmek için
// yani application-test.yml i kullanmak için yazabilirsiniz.
@ExtendWith(MockitoExtension.class)
public class OrnekTest {

    /**
     * @Autowired ile kullanımda gerçek nesne kullanılır.
     * @InjectMocks ile kullanımda mock nesne kullanılır.
     */
    @InjectMocks
    AuthService authService;
    @Mock
    IAuthRepository authRepository;

    @Test
    public void test1(){
        /**
         * authService -> mock haline getirilmiş sanal bir nesnedir.
         ****** DİKKAT!!!! mock, verify gibi kullanımlar için  *****
         * import static org.mockito.Mockito.*; eklenmelidir.
         */
        AuthService authService = mock(AuthService.class);
        Auth auth = Auth.builder().id(1L).username("muhammet").build();
        Auth auth1 = Auth.builder().id(2L).username("canan").build();
        Auth auth2 = Auth.builder().id(3L).username("temel").build();
        authService.save(auth);
        authService.save(auth2);
        authService.save(auth1);

        /**
         * authServisin yapmış olduğu işlemler mock nesnesi tarafından işlenmektedir.
         * bu işlemlerin doğruluğunu bunun üzerinden kontrol edebilirsiniz.
         */
         //verify(authService).save(auth1);
         //verify(authService).save(auth);

        /**
         * times -> bir method un belli bir dğeişken ile kaç kez çağırıldığını kontrol eder.
         * eğer methodun kaç kez çağırıldığı kontrol edilecek ise, anyObject denebebilir.
         * never -> mehod hiç çağırılmadığını kontrol eder.
         */
        //verify(authService,times(2)).save(auth1);
        //verify(authService,times(5)).save(any(Auth.class));
        //verify(authService,times(2)).save(auth2);
        //verify(authService,never()).update(any());
        /**
         * enaz bir kere çağırıldı mı?
         */
        // verify(authService,atLeast(1)).update(any());
       // InOrder inorder = inOrder(authService);
      //  inorder.verify(authService).save(auth);
     //   inorder.verify(authService).save(auth2);
     //   inorder.verify(authService).save(auth1);


    }

    @Test
    public void test2(){
        AuthService authService = mock(AuthService.class);
        when(authService.findById(1L)).thenReturn(
                Auth.builder()
                        .password("123")
                        .username("muhammet")
                        .id(1L)
                        .build()
        );
        when(authService.findById(3L)).thenReturn(
                Auth.builder()
                        .password("312232")
                        .username("canan")
                        .id(3L)
                        .build()
        );
        Auth auth3 =  authService.findById(3L);

        System.out.println(auth3.toString());
    }

    @Test
    public void saveTest2(){
        /**
         * Auth Service kayıt işlemi için ->
         * injection -> nesne yaratılması gerekli.
         * Auth servicenin içermesi gereken bağımlılıklar inject edilir.
         */
        when(authService.save(any())).thenReturn(
                Auth.builder()
                        .id(1L)
                        .username("muhammet")
                        .password("123")
                        .build()
        );
      Auth auth =  authService.save(Auth.builder().id(1014L).username("canan").build());
        System.out.println(auth.toString());
    }

}
