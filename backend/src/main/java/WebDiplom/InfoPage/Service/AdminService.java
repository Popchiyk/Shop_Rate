package WebDiplom.InfoPage.Service;

import WebDiplom.InfoPage.Models.*;
import WebDiplom.InfoPage.Repository.*;
import WebDiplom.InfoPage.dto.RoleDto;
import WebDiplom.InfoPage.dto.*;
    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    KategoryRepository kategoryRepository;
    @Autowired
    InfoShopRepository infoShopRepository;
    @Autowired
    ReviewRepository reviewRepository;

    public List<KategoryDto> selectallKategory() {
        Kategory[] kategories = kategoryRepository.findAll().toArray(new Kategory[0]);
        KategoryDto[] kategoryDtos = new KategoryDto[kategories.length];
        if(kategories.length>0){
            for (int i = 0; i < kategories.length; i++) {
                KategoryDto kategoryDto = new KategoryDto();
                kategoryDto.setId_kategory(kategories[i].getId_kategory());
                kategoryDto.setName_kategory(kategories[i].getName_kategory());
                kategoryDtos[i] = kategoryDto;
            }

        }
        return Arrays.asList(kategoryDtos);
    }

    public List<InfoShopRequest> selectAllShop() {
        int sum = 0, count = 0;
        InfoShop[] infoShops = infoShopRepository.findAll().toArray(new InfoShop[0]);
        InfoShopRequest[] infoShopRequests = new InfoShopRequest[infoShops.length];
        for (int i = 0; i < infoShops.length; i++) {
            InfoShopRequest infoShopRequest = new InfoShopRequest();
            ReviewEntity[] reviewEntityShop = reviewRepository.findAllById(infoShops[i]).toArray(new ReviewEntity[0]);
            if(reviewEntityShop.length>0){
                for (ReviewEntity entity : reviewEntityShop) {
                    count++;
                    sum = sum + entity.getBall();
                }
                infoShopRequest.setBall(sum/count);
                infoShopRequest.setResponse(count);
                infoShopRequest.setId(infoShops[i].getId());
                infoShopRequest.setId_Kategory(infoShops[i].getId_Kategory());
                infoShopRequest.setWebsite(infoShops[i].getWebsite());
                infoShopRequest.setName_shop(infoShops[i].getName_shop());
                infoShopRequest.setContacts_people(infoShops[i].getContacts_people());
                infoShopRequest.setEmail(infoShops[i].getEmail());
                infoShopRequest.setPhone(infoShops[i].getPhone());
                infoShopRequest.setLogo(infoShops[i].getLogo());
                infoShopRequests[i] = infoShopRequest;
                count = 0;
                sum = 0;
            }
            else{
                infoShopRequest.setBall(0);
                infoShopRequest.setResponse(0);
                infoShopRequest.setId(infoShops[i].getId());
                infoShopRequest.setId_Kategory(infoShops[i].getId_Kategory());
                infoShopRequest.setWebsite(infoShops[i].getWebsite());
                infoShopRequest.setName_shop(infoShops[i].getName_shop());
                infoShopRequest.setContacts_people(infoShops[i].getContacts_people());
                infoShopRequest.setEmail(infoShops[i].getEmail());
                infoShopRequest.setPhone(infoShops[i].getPhone());
                infoShopRequest.setLogo(infoShops[i].getLogo());
                infoShopRequests[i] = infoShopRequest;
            }
        }

        return Arrays.asList(infoShopRequests);
    }


    public List<ReviewRequest> selectallreview(){
        ReviewEntity[] reviewEntities = reviewRepository.findAll().toArray(new ReviewEntity[0]);
        ReviewRequest[] reviewRequests = new ReviewRequest[reviewEntities.length];
        for(int i=0;i<reviewEntities.length;i++){
            ReviewRequest reviewRequest= new ReviewRequest();
            reviewRequest.setId(reviewEntities[i].getId());
            reviewRequest.setId_shop(reviewEntities[i].getId_shop().getId());
            reviewRequest.setText(reviewEntities[i].getText());
            reviewRequest.setBall(reviewEntities[i].getBall());
            reviewRequest.setUsername(reviewEntities[i].getId_user().getUserName());
            reviewRequest.setLogo(reviewEntities[i].getId_user().getLogo());
            reviewRequest.setName(reviewEntities[i].getId_user().getName());
            reviewRequest.setHeader(reviewEntities[i].getHeader());
            reviewRequest.setSurname(reviewEntities[i].getId_user().getSurname());
            reviewRequest.setData(reviewEntities[i].getData());
            reviewRequests[i]=reviewRequest;
        }
        return Arrays.asList(reviewRequests);
    }

    public List<UserFullInfoDto> selectalluser(){
        UserEntity[] userEntities = userRepository.findAll().toArray(new UserEntity[0]);
        UserFullInfoDto[] userFullInfoDto = new UserFullInfoDto[userEntities.length];
        for(int i=0;i<userEntities.length;i++){
            UserFullInfoDto userFullInfoDto1 = new UserFullInfoDto();
            userFullInfoDto1.setId(userEntities[i].getId());
            userFullInfoDto1.setLogin(userEntities[i].getUserName());
            userFullInfoDto1.setEmail(userEntities[i].getEmail());
            userFullInfoDto1.setLastname(userEntities[i].getLastname());
            userFullInfoDto1.setName(userEntities[i].getName());
            byte[] bytesEncoded = Base64.getEncoder().encode(userEntities[i].getPassword().getBytes(StandardCharsets.UTF_8));
            byte [] barr = Base64.getDecoder().decode(bytesEncoded);
            userFullInfoDto1.setPassword(userEntities[i].getPassword());
            userFullInfoDto1.setSurname(userEntities[i].getSurname());
            userFullInfoDto1.setPhone(userEntities[i].getPhone());
            userFullInfoDto1.setLogo(userEntities[i].getLogo());
            userFullInfoDto[i]=userFullInfoDto1;
        }
        return Arrays.asList(userFullInfoDto);
    }

    public RoleDto roleEntityToDto(RoleEntity roleEntity){
        RoleDto role = new RoleDto();
        role.setName(roleEntity.getName());
        role.setId(roleEntity.getId());
        return role;
    }

    public void AddShop( ShopDto shopDto){
        Kategory kategory = kategoryRepository.findById(shopDto.getKategory()).orElseThrow(() ->
                new RuntimeException("Категорія не знайдено"));
        InfoShop infoShop = new InfoShop();
        infoShop.setName_shop(shopDto.getName());
        infoShop.setEmail(shopDto.getEmail());
        infoShop.setContacts_people(shopDto.getContact_people());
        infoShop.setPhone(shopDto.getPhone());
        infoShop.setWebsite(shopDto.getWebsite());
        infoShop.setId_Kategory(kategory);
        infoShopRepository.save(infoShop);
    }

    public void AddKategory(String kategory){
        Kategory kategory1 =  new Kategory();
        kategory1.setName_kategory(kategory);
        kategoryRepository.save(kategory1);
    }

    public void UpdateShop(Long id,ShopDto shopDto){
        InfoShop infoShop = infoShopRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Магазин не знайдено"));
        Kategory kategory = kategoryRepository.findById(shopDto.getKategory()).orElseThrow(() ->
                new RuntimeException("Категорія не знайдено"));
        infoShop.setName_shop(shopDto.getName());
        infoShop.setEmail(shopDto.getEmail());
        infoShop.setContacts_people(shopDto.getContact_people());
        infoShop.setPhone(shopDto.getPhone());
        infoShop.setWebsite(shopDto.getWebsite());
        infoShop.setId_Kategory(kategory);
        infoShopRepository.save(infoShop);
    }

    public KategoryDto Findkategory(Long id){
        Kategory kategory = kategoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Магазин не знайдено"));
        KategoryDto kategoryDto = new KategoryDto();
        kategoryDto.setId_kategory(kategory.getId_kategory());
        kategoryDto.setName_kategory(kategory.getName_kategory());
        return kategoryDto;
    }

    public void UpdateKategory(Long id, String kategory){
        Kategory kategory1 = kategoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Магазин не знайдено"));
        kategory1.setName_kategory(kategory);
        kategoryRepository.save(kategory1);
    }

    public void DeleteShop(Long id ){
        infoShopRepository.delete(infoShopRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Магазин не знайдено")));
    }

    public void DeleteReview(Long id){
        reviewRepository.delete(reviewRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Відгук не знайдено")));
    }

    public void DeleteKategory(Long id){
        kategoryRepository.delete(kategoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Категорія не знайдено")));
    }

    public void DeleteUser(Long id){
        userRepository.delete(userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Категорія не знайдено")));
    }

}
