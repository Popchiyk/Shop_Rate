package WebDiplom.InfoPage.service;

import WebDiplom.InfoPage.models.*;
import WebDiplom.InfoPage.repository.*;
import WebDiplom.InfoPage.dto.*;
import WebDiplom.InfoPage.models.InfoShop;
import WebDiplom.InfoPage.repository.ICategoryRepository;
import WebDiplom.InfoPage.dto.KategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepository IUserRepository;
    @Autowired
    AdminService adminService;
    @Autowired
    IInfoShopRepository IInfoShopRepository;
    @Autowired
    ICategoryRepository ICategoryRepository;
    @Autowired
    IFavouriteRepository IFavouriteRepository;
    @Autowired
    IReviewRepository IReviewRepository;


    public void saveImageShop(MultipartFile file, Long id) {
        InfoShop infoShop = IInfoShopRepository.findById(id).orElseThrow(() -> new RuntimeException("Магази не знайдено"));
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (filename.contains("..")) {
            new Exception("Error");
        }
        try {
            infoShop.setLogo(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        IInfoShopRepository.save(infoShop);
    }

    public void saveImage(MultipartFile file, String username) {
        User user = IUserRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("Користувача не знайдено"));
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (filename.contains("..")) {
            new Exception("Error");
        }
        try {
            user.setLogo(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        IUserRepository.save(user);
    }

    public void setInfo(String username, UserRequestUpdateData userRequestUpdateData) {
        User user = IUserRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("Користувача не знайдено"));
        user.setName(userRequestUpdateData.getName());
        user.setLastname(userRequestUpdateData.getLastname());
        user.setSurname(userRequestUpdateData.getSurname());
        user.setLogo(userRequestUpdateData.getLogo());
        user.setPhone(userRequestUpdateData.getPhone());
        IUserRepository.save(user);
    }


    public void setInfoLoginAndPassword(String username, UserRequestLoginAndPassword userRequestLoginAndPassword) {
        User user = IUserRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("Користувача не знайдено"));
        user.setUserName(userRequestLoginAndPassword.getLogin());
        user.setPassword(userRequestLoginAndPassword.getPassword());
        IUserRepository.save(user);
    }

    public void deleteImg(String username) {
        User user = IUserRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("Користувача не знайдено"));
        user.setLogo(null);
        IUserRepository.save(user);
    }

    public UserRequestUpdateData getUser(String username) {
        User user = IUserRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("Користувача не знайдено"));
        UserRequestUpdateData userRequestUpdateData = new UserRequestUpdateData();
        userRequestUpdateData.setName(user.getName());
        userRequestUpdateData.setLogo(user.getLogo());
        userRequestUpdateData.setSurname(user.getSurname());
        userRequestUpdateData.setLastname(user.getLastname());
        userRequestUpdateData.setPhone(user.getPhone());
        return userRequestUpdateData;
    }

    public InfoShopRequest infoShopRequest(InfoShop infoShop) {
        InfoShopRequest infoShopRequest = new InfoShopRequest();
        infoShopRequest.setId(infoShop.getId());
        infoShopRequest.setName_shop(infoShop.getName_shop());
        infoShopRequest.setId_Kategory(infoShop.getId_Kategory());
        infoShopRequest.setWebsite(infoShop.getWebsite());
        infoShopRequest.setEmail(infoShop.getEmail());
        infoShopRequest.setContacts_people(infoShop.getContacts_people());
        infoShopRequest.setPhone(infoShop.getPhone());
        infoShopRequest.setLogo(infoShop.getLogo());
        return infoShopRequest;
    }

    public List<InfoShopRequest> getFavourite(String username) {
        int sum = 0, count = 0;
        User user = IUserRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("Користувача не знайдено"));
        Favourite[] favourites = IFavouriteRepository.findAllByUserId(user).toArray(new Favourite[0]);

        InfoShop[] infoShops = new InfoShop[favourites.length];
        for (int i = 0; i < favourites.length; i++) {
            infoShops[i] = favourites[i].getId_shop();

        }
        InfoShopRequest[] infoShopRequests = new InfoShopRequest[favourites.length];
        for (int i = 0; i < infoShops.length; i++) {
            Review[] reviewShop = IReviewRepository.findAllById(infoShops[i]).toArray(new Review[0]);
            infoShopRequests[i] = infoShopRequest(infoShops[i]);
            if(reviewShop.length>0){
                for (Review entity : reviewShop) {
                    count++;
                    sum = sum + entity.getBall();
                }
                infoShopRequests[i].setBall(sum/count);
                infoShopRequests[i].setResponse(count);
                infoShopRequests[i].setSubscribe(true);
                infoShopRequests[i].setSubscribeID(favourites[i].getId());
                count=0;
                sum=0;
            }else{
                infoShopRequests[i].setBall(0);
                infoShopRequests[i].setResponse(0);
                infoShopRequests[i].setSubscribe(true);
                infoShopRequests[i].setSubscribeID(favourites[i].getId());
            }

        }
        return Arrays.asList(infoShopRequests.clone());
    }

    public void deleteFavourite(Long id) {
        IFavouriteRepository.delete(IFavouriteRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("Улюблені не знайдено")));
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

    public List<KategoryDto> selectallKategory() {
        Kategory[] kategories = ICategoryRepository.findAll().toArray(new Kategory[0]);
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

    public InfoShopRequest findShop(Long id) {
        int sum = 0, count = 0;
        InfoShop infoShop = IInfoShopRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("Магазин не знайдено"));
        Review[] reviewShop = IReviewRepository.findAllById(infoShop).toArray(new Review[0]);
        if(reviewShop.length>0){
            for (Review entity : reviewShop) {
                    count++;
                    sum = sum + entity.getBall();
            }
            InfoShopRequest infoShopRequest = new InfoShopRequest();
            infoShopRequest.setBall(sum / count);
            infoShopRequest.setResponse(count);
            infoShopRequest.setId(infoShop.getId());
            infoShopRequest.setName_shop(infoShop.getName_shop());
            infoShopRequest.setLogo(infoShop.getLogo());
            infoShopRequest.setPhone(infoShop.getPhone());
            infoShopRequest.setEmail(infoShop.getEmail());
            infoShopRequest.setContacts_people(infoShop.getContacts_people());
            infoShopRequest.setWebsite(infoShop.getWebsite());
            infoShopRequest.setId_Kategory(infoShop.getId_Kategory());
            return infoShopRequest;

        }
        else {
            InfoShopRequest infoShopRequest = new InfoShopRequest();
            infoShopRequest.setBall(0);
            infoShopRequest.setResponse(0);
            infoShopRequest.setId(infoShop.getId());
            infoShopRequest.setName_shop(infoShop.getName_shop());
            infoShopRequest.setLogo(infoShop.getLogo());
            infoShopRequest.setPhone(infoShop.getPhone());
            infoShopRequest.setEmail(infoShop.getEmail());
            infoShopRequest.setContacts_people(infoShop.getContacts_people());
            infoShopRequest.setWebsite(infoShop.getWebsite());
            infoShopRequest.setId_Kategory(infoShop.getId_Kategory());
            return infoShopRequest;
        }

    }

    public InfoShopRequest findShopForUser(Long id,String username) {
        int sum = 0, count = 0;
        InfoShop infoShop = IInfoShopRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("Магазин не знайдено"));
        User user = IUserRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("Користувача не знайдено"));
        Review[] reviewShop = IReviewRepository.findAllById(infoShop).toArray(new Review[0]);
        Favourite [] favourites= IFavouriteRepository.findAllByUserId(user).toArray(new Favourite[0]);
        if(reviewShop.length>0){
            for (Review entity : reviewShop) {
                count++;
                sum = sum + entity.getBall();
            }

            InfoShopRequest infoShopRequest = new InfoShopRequest();
            infoShopRequest.setBall(sum / count);
            infoShopRequest.setResponse(count);
            for (Favourite favourite : favourites) {
                if (favourite.getId_shop().getId().equals(id)) {
                infoShopRequest.setSubscribe(true);
                infoShopRequest.setSubscribeID(id);
                }
            }
            infoShopRequest.setId(infoShop.getId());
            infoShopRequest.setName_shop(infoShop.getName_shop());
            infoShopRequest.setLogo(infoShop.getLogo());
            infoShopRequest.setPhone(infoShop.getPhone());
            infoShopRequest.setEmail(infoShop.getEmail());
            infoShopRequest.setContacts_people(infoShop.getContacts_people());
            infoShopRequest.setWebsite(infoShop.getWebsite());
            infoShopRequest.setId_Kategory(infoShop.getId_Kategory());
            return infoShopRequest;

        }
        else {
            InfoShopRequest infoShopRequest = new InfoShopRequest();
            infoShopRequest.setBall(0);
            infoShopRequest.setResponse(0);
            infoShopRequest.setId(infoShop.getId());
            infoShopRequest.setName_shop(infoShop.getName_shop());
            infoShopRequest.setLogo(infoShop.getLogo());
            infoShopRequest.setPhone(infoShop.getPhone());
            infoShopRequest.setEmail(infoShop.getEmail());
            infoShopRequest.setContacts_people(infoShop.getContacts_people());
            infoShopRequest.setWebsite(infoShop.getWebsite());
            infoShopRequest.setId_Kategory(infoShop.getId_Kategory());
            return infoShopRequest;
        }

    }

    public List<InfoShopRequest> findByKategory(Long id){
        Kategory kategory= ICategoryRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("Магазин не знайдено"));
        InfoShop[] infoShops= IInfoShopRepository.findAllByKategoryName(kategory).toArray(new InfoShop[0]);
        InfoShopRequest[] infoShopRequests = new InfoShopRequest[infoShops.length];
        int sum = 0, count = 0;

        for(int i=0;i<infoShops.length;i++){
            InfoShopRequest infoShopRequest =  new InfoShopRequest();
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
                infoShopRequest.setContacts_people(infoShops[i].getContacts_people());
                infoShopRequest.setEmail(infoShops[i].getEmail());
                infoShopRequest.setLogo(infoShops[i].getLogo());
                infoShopRequest.setName_shop(infoShops[i].getName_shop());
                infoShopRequest.setPhone(infoShops[i].getPhone());
                infoShopRequest.setWebsite(infoShops[i].getWebsite());
                infoShopRequests[i]=infoShopRequest;
                count=0;
                sum=0;
            }
            else {
                infoShopRequest.setBall(0);
                infoShopRequest.setResponse(0);
                infoShopRequest.setId(infoShops[i].getId());
                infoShopRequest.setId_Kategory(infoShops[i].getId_Kategory());
                infoShopRequest.setContacts_people(infoShops[i].getContacts_people());
                infoShopRequest.setEmail(infoShops[i].getEmail());
                infoShopRequest.setLogo(infoShops[i].getLogo());
                infoShopRequest.setName_shop(infoShops[i].getName_shop());
                infoShopRequest.setPhone(infoShops[i].getPhone());
                infoShopRequest.setWebsite(infoShops[i].getWebsite());
                infoShopRequests[i]=infoShopRequest;
            }

        }
        return Arrays.asList(infoShopRequests);
    }

    public List<InfoShopRequest> findByKategoryForUser(Long id,String username){
        Kategory kategory= ICategoryRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("Магазин не знайдено"));
        User user = IUserRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("Користувача не знайдено"));
        InfoShop[] infoShops= IInfoShopRepository.findAllByKategoryName(kategory).toArray(new InfoShop[0]);
        InfoShopRequest[] infoShopRequests = new InfoShopRequest[infoShops.length];
        int sum = 0, count = 0;

        for(int i=0;i<infoShops.length;i++){
            Favourite [] favourites= IFavouriteRepository.findAllByUserId(user).toArray(new Favourite[0]);
            InfoShopRequest infoShopRequest =  new InfoShopRequest();
            Review[] reviewShop = IReviewRepository.findAllById(infoShops[i]).toArray(new Review[0]);
            if(reviewShop.length>0){
                for (Review entity : reviewShop) {
                    count++;
                    sum = sum + entity.getBall();
                }
                for(Favourite favourite:favourites){
                    if(favourite.getId_shop().getId().equals(infoShops[i].getId())){
                        infoShopRequest.setSubscribeID(infoShops[i].getId());
                        infoShopRequest.setSubscribe(true);
                    }
                }
                infoShopRequest.setBall(sum/count);
                infoShopRequest.setResponse(count);
                infoShopRequest.setId(infoShops[i].getId());
                infoShopRequest.setId_Kategory(infoShops[i].getId_Kategory());
                infoShopRequest.setContacts_people(infoShops[i].getContacts_people());
                infoShopRequest.setEmail(infoShops[i].getEmail());
                infoShopRequest.setLogo(infoShops[i].getLogo());
                infoShopRequest.setName_shop(infoShops[i].getName_shop());
                infoShopRequest.setPhone(infoShops[i].getPhone());
                infoShopRequest.setWebsite(infoShops[i].getWebsite());
                infoShopRequests[i]=infoShopRequest;
                count=0;
                sum=0;
            }
            else {
                infoShopRequest.setBall(0);
                infoShopRequest.setResponse(0);
                infoShopRequest.setId(infoShops[i].getId());
                infoShopRequest.setId_Kategory(infoShops[i].getId_Kategory());
                infoShopRequest.setContacts_people(infoShops[i].getContacts_people());
                infoShopRequest.setEmail(infoShops[i].getEmail());
                infoShopRequest.setLogo(infoShops[i].getLogo());
                infoShopRequest.setName_shop(infoShops[i].getName_shop());
                infoShopRequest.setPhone(infoShops[i].getPhone());
                infoShopRequest.setWebsite(infoShops[i].getWebsite());
                infoShopRequests[i]=infoShopRequest;
            }

        }
        return Arrays.asList(infoShopRequests);
    }

    public void favourite(String username, Long id) {
        User user = IUserRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("Користувача не знайдено"));
        InfoShop infoShop = IInfoShopRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("Магазин не знайдено"));
        Favourite favourite = new Favourite();
        favourite.setId_shop(infoShop);
        favourite.setId_users(user);
        IFavouriteRepository.save(favourite);
    }

    public InfoShopRequest[] getallFacult(String username){
        List<InfoShopRequest> subscribesList =  getFavourite(username);
        InfoShopRequest[] subscribesArray = subscribesList.toArray(new InfoShopRequest[0]);
        InfoShopRequest[] allshops = selectAllShop().toArray(new InfoShopRequest[0]);
        if(subscribesArray.length>0) {
            int ij=0;
            boolean z=true;
            InfoShopRequest[] tmp = new InfoShopRequest[allshops.length-subscribesArray.length];
            for (InfoShopRequest allshop : allshops) {
                for (InfoShopRequest infoShopRequest : subscribesArray) {
                    if (!allshop.getId().equals(infoShopRequest.getId())) {
                        z = true;
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) tmp[ij++] = allshop;
                z = true;
            }

            return tmp;
        }
        else return allshops;
    }


}