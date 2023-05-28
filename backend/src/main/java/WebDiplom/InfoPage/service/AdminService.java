package WebDiplom.InfoPage.service;

import WebDiplom.InfoPage.models.*;
import WebDiplom.InfoPage.repository.*;
import WebDiplom.InfoPage.dto.RoleDto;
import WebDiplom.InfoPage.dto.*;
import org.hibernate.criterion.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    IUserRepository IUserRepository;

    @Autowired
    IRoleRepository IRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    ICategoryRepository ICategoryRepository;
    @Autowired
    IInfoShopRepository IInfoShopRepository;
    @Autowired
    IReviewRepository IReviewRepository;

    public List<Kategory> selectallKategory() {
        return ICategoryRepository.findAll();
        /**Old code**/
//        Kategory[] kategories = ICategoryRepository.findAll().toArray(new Kategory[0]);
//        KategoryDto[] kategoryDtos = new KategoryDto[kategories.length];
//        if(kategories.length>0){
//            for (int i = 0; i < kategories.length; i++) {
//                KategoryDto kategoryDto = new KategoryDto();
//                kategoryDto.setId_kategory(kategories[i].getId_kategory());
//                kategoryDto.setName_kategory(kategories[i].getName_kategory());
//                kategoryDtos[i] = kategoryDto;
//            }
//
//        }
//        return Arrays.asList(kategoryDtos);
    }

    public List<InfoShopRequest> selectAllShop() {
        int sum = 0, count = 0;
        InfoShop[] infoShops = IInfoShopRepository.findAll().toArray(new InfoShop[0]);
        InfoShopRequest[] infoShopRequests = new InfoShopRequest[infoShops.length];
        for (int i = 0; i < infoShops.length; i++) {
            InfoShopRequest infoShopRequest = new InfoShopRequest();
            Review[] reviewShop = IReviewRepository.findAllById(infoShops[i]).toArray(new Review[0]);
            if(reviewShop.length>0){
                for (Review entity : reviewShop) {
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


    public List<Review> selectallreview(){
        return IReviewRepository.findAll();
        /**Old code**/
//        Review[] reviewEntities = IReviewRepository.findAll().toArray(new Review[0]);
//        ReviewRequest[] reviewRequests = new ReviewRequest[reviewEntities.length];
//        for(int i=0;i<reviewEntities.length;i++){
//            ReviewRequest reviewRequest= new ReviewRequest();
//            reviewRequest.setId(reviewEntities[i].getId());
//            reviewRequest.setId_shop(reviewEntities[i].getId_shop().getId());
//            reviewRequest.setText(reviewEntities[i].getText());
//            reviewRequest.setBall(reviewEntities[i].getBall());
//            reviewRequest.setUsername(reviewEntities[i].getId_user().getUserName());
//            reviewRequest.setLogo(reviewEntities[i].getId_user().getLogo());
//            reviewRequest.setName(reviewEntities[i].getId_user().getName());
//            reviewRequest.setHeader(reviewEntities[i].getHeader());
//            reviewRequest.setSurname(reviewEntities[i].getId_user().getSurname());
//            reviewRequest.setData(reviewEntities[i].getData());
//            reviewRequests[i]=reviewRequest;
//        }
//        return Arrays.asList(reviewRequests);
    }

    public List<UserFullInfoDto> selectalluser() {
        return IUserRepository.findAll().stream().map(user -> {
            UserFullInfoDto dto = new UserFullInfoDto();
            dto.setLogin(user.getUserName());
            dto.setEmail(user.getEmail());
            dto.setLastname(user.getLastname());
            dto.setName(user.getName());
            dto.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes(StandardCharsets.UTF_8)));
            dto.setSurname(user.getSurname());
            dto.setPhone(user.getPhone());
            dto.setLogo(user.getLogo());
            return dto;
        }).collect(Collectors.toList());
    }

    public RoleDto roleEntityToDto(Role roleEntity){
        RoleDto role = new RoleDto();
//        role.setName(roleEntity.getName());
        role.setId(roleEntity.getId());
        return role;
    }

    public void AddShop( ShopDto shopDto){
        Kategory kategory = ICategoryRepository.findById(shopDto.getKategory()).orElseThrow(() ->
                new RuntimeException("Категорія не знайдено"));
        InfoShop infoShop = new InfoShop();
        infoShop.setName_shop(shopDto.getName());
        infoShop.setEmail(shopDto.getEmail());
        infoShop.setContacts_people(shopDto.getContact_people());
        infoShop.setPhone(shopDto.getPhone());
        infoShop.setWebsite(shopDto.getWebsite());
        infoShop.setId_Kategory(kategory);
        IInfoShopRepository.save(infoShop);
    }

    public void AddKategory(String kategory){
        Kategory kategory1 =  new Kategory();
        kategory1.setName_kategory(kategory);
        ICategoryRepository.save(kategory1);
    }

    public void UpdateShop(Long id,ShopDto shopDto){
        InfoShop infoShop = IInfoShopRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Магазин не знайдено"));
        Kategory kategory = ICategoryRepository.findById(shopDto.getKategory()).orElseThrow(() ->
                new RuntimeException("Категорія не знайдено"));
        infoShop.setName_shop(shopDto.getName());
        infoShop.setEmail(shopDto.getEmail());
        infoShop.setContacts_people(shopDto.getContact_people());
        infoShop.setPhone(shopDto.getPhone());
        infoShop.setWebsite(shopDto.getWebsite());
        infoShop.setId_Kategory(kategory);
        IInfoShopRepository.save(infoShop);
    }

    public KategoryDto Findkategory(Long id){
        Kategory kategory = ICategoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Магазин не знайдено"));
        KategoryDto kategoryDto = new KategoryDto();
        kategoryDto.setId_kategory(kategory.getId_kategory());
        kategoryDto.setName_kategory(kategory.getName_kategory());
        return kategoryDto;
    }

    public void UpdateKategory(Long id, String kategory){
        Kategory kategory1 = ICategoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Магазин не знайдено"));
        kategory1.setName_kategory(kategory);
        ICategoryRepository.save(kategory1);
    }

    public void DeleteShop(Long id ){
        IInfoShopRepository.delete(IInfoShopRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Магазин не знайдено")));
    }

    public void DeleteReview(Long id){
        IReviewRepository.delete(IReviewRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Відгук не знайдено")));
    }

    public void DeleteKategory(Long id){
        ICategoryRepository.delete(ICategoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Категорія не знайдено")));
    }

    public void DeleteUser(Long id){
        IUserRepository.delete(IUserRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Категорія не знайдено")));
    }

    public StatsDTO getCountStats(){
     return  new StatsDTO((int)IUserRepository.count(),(int)IReviewRepository.count(),(int)IInfoShopRepository.count());
    }


    public List<Object[]> getCountAndDate() {
        List<Object[]> result = IReviewRepository.getDataWithCount();
        result = result.subList(0, Math.min(result.size(), 1000)); // Limit the number of results to 1000

        for (Object[] row : result) {
            Long count = (Long) row[0];
            String data = (String) row[1];
            // Do something with the count and data
        }
        return result;
    }

}
