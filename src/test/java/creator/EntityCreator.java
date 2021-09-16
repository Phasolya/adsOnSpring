package creator;

import com.entity.*;

import java.time.LocalDate;

public class EntityCreator {

    public static User getUser(int id){

        Phone phone = Phone.builder()
                .id(id)
                .phoneNumber("phone" + id)
                .build();

        Email email = Email.builder()
                .id(id)
                .mailAddress("mail" + id)
                .build();

        Address address = Address.builder()
                .id(id)
                .street("street" + id)
                .region("region" + id)
                .building("building" + id)
                .city("city" + id)
                .build();

        return User.builder()
                .id(id)
                .login("login"+id)
                .password("pass"+id)
                .registration(LocalDate.now())
                .firstName("firstName"+id)
                .lastName("lastName"+id)
                .role(Role.USER)
                .phone(phone)
                .email(email)
                .address(address)
                .build();

    }

}
