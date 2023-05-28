package WebDiplom.InfoPage.service;

import WebDiplom.InfoPage.models.*;
import WebDiplom.InfoPage.repository.*;
import WebDiplom.InfoPage.dto.*;
import WebDiplom.InfoPage.models.InfoShop;
import WebDiplom.InfoPage.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
        User user = IUserRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("Користувача не знайдено"));
        Favourite[] favourites = IFavouriteRepository.findAllByUserId(user).toArray(new Favourite[0]);

        List<InfoShopRequest> infoShopRequests = new ArrayList<>();
        for (Favourite favourite : favourites) {
            InfoShop infoShop = favourite.getId_shop();
            Review[] reviewShop = IReviewRepository.findAllById(infoShop).toArray(new Review[0]);
            InfoShopRequest infoShopRequest = infoShopRequest(infoShop);
            if (reviewShop.length > 0) {
                int sum = 0, count = 0;
                for (Review entity : reviewShop) {
                    count++;
                    sum = sum + entity.getBall();
                }
                infoShopRequest.setBall(sum / count);
                infoShopRequest.setResponse(count);
            } else {
                infoShopRequest.setBall(0);
                infoShopRequest.setResponse(0);
            }
            infoShopRequest.setSubscribe(true);
            infoShopRequest.setSubscribeID(favourite.getId());
            infoShopRequests.add(infoShopRequest);
        }
        return infoShopRequests;
    }

    public void deleteFavourite(Long id) {
        IFavouriteRepository.delete(IFavouriteRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("Улюблені не знайдено")));
    }

    public List<InfoShopRequest> selectAllShop() {
        List<InfoShop> infoShops = IInfoShopRepository.findAll();
        return infoShops.stream().map(infoShop -> {
            InfoShopRequest infoShopRequest = new InfoShopRequest();
            Review[] reviewShop = IReviewRepository.findAllById(infoShop).toArray(new Review[0]);
            int count = reviewShop.length;
            int sum = Arrays.stream(reviewShop).mapToInt(Review::getBall).sum();
            double ball = count > 0 ? (double) sum / count : 0;
            infoShopRequest.setId(infoShop.getId());
            infoShopRequest.setId_Kategory(infoShop.getId_Kategory());
            infoShopRequest.setWebsite(infoShop.getWebsite());
            infoShopRequest.setName_shop(infoShop.getName_shop());
            infoShopRequest.setContacts_people(infoShop.getContacts_people());
            infoShopRequest.setEmail(infoShop.getEmail());
            infoShopRequest.setPhone(infoShop.getPhone());
            infoShopRequest.setLogo(infoShop.getLogo());
            infoShopRequest.setBall((int)ball);
            infoShopRequest.setResponse(count);
            return infoShopRequest;
        }).collect(Collectors.toList());
    }

    public List<Kategory> selectallKategory() {
       return ICategoryRepository.findAll();
    }

    public InfoShopRequest findShop(Long id) {
        InfoShop infoShop = IInfoShopRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Магазин не знайдено"));
        Review[] reviewShop = IReviewRepository.findAllById(infoShop).toArray(new Review[0]);

        int count = reviewShop.length;
        int sum = Arrays.stream(reviewShop).mapToInt(Review::getBall).sum();

        InfoShopRequest infoShopRequest = new InfoShopRequest();
        infoShopRequest.setBall(count > 0 ? sum / count : 0);
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

    public InfoShopRequest findShopForUser(Long id, String username) {
        InfoShop infoShop = IInfoShopRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Магазин не знайдено"));

        User user = IUserRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Користувача не знайдено"));

        Review[] reviewShop = IReviewRepository.findAllById(infoShop).toArray(new Review[0]);
        Favourite[] favourites = IFavouriteRepository.findAllByUserIdAndIdShop(user, infoShop).toArray(new Favourite[0]);

        InfoShopRequest infoShopRequest = new InfoShopRequest();
        infoShopRequest.setId(infoShop.getId());
        infoShopRequest.setName_shop(infoShop.getName_shop());
        infoShopRequest.setLogo(infoShop.getLogo());
        infoShopRequest.setPhone(infoShop.getPhone());
        infoShopRequest.setEmail(infoShop.getEmail());
        infoShopRequest.setContacts_people(infoShop.getContacts_people());
        infoShopRequest.setWebsite(infoShop.getWebsite());
        infoShopRequest.setId_Kategory(infoShop.getId_Kategory());

        int sum = 0, count = 0;
        for (Review entity : reviewShop) {
            count++;
            sum += entity.getBall();
        }
        infoShopRequest.setBall(count == 0 ? 0 : sum / count);
        infoShopRequest.setResponse(count);

        for (Favourite favourite : favourites) {
            if (favourite.getId_shop().getId().equals(id)) {
                infoShopRequest.setSubscribe(true);
                infoShopRequest.setSubscribeID(id);
                break;
            }
        }

        return infoShopRequest;
    }

    public List<InfoShopRequest> findByKategory(Long id){
        Kategory kategory = ICategoryRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Магазин не знайдено"));
        InfoShop[] infoShops = IInfoShopRepository.findAllByKategoryName(kategory)
                .toArray(new InfoShop[0]);

        return Arrays.stream(infoShops)
                .map(infoShop -> {
                    InfoShopRequest infoShopRequest = new InfoShopRequest();
                    Review[] reviewShop = IReviewRepository.findAllById(infoShop).toArray(new Review[0]);
                    int count = reviewShop.length;
                    int sum = Arrays.stream(reviewShop).mapToInt(Review::getBall).sum();
                    int avgRating = count > 0 ? sum / count : 0;

                    infoShopRequest.setId(infoShop.getId());
                    infoShopRequest.setId_Kategory(infoShop.getId_Kategory());
                    infoShopRequest.setContacts_people(infoShop.getContacts_people());
                    infoShopRequest.setEmail(infoShop.getEmail());
                    infoShopRequest.setLogo(infoShop.getLogo());
                    infoShopRequest.setName_shop(infoShop.getName_shop());
                    infoShopRequest.setPhone(infoShop.getPhone());
                    infoShopRequest.setWebsite(infoShop.getWebsite());
                    infoShopRequest.setBall(avgRating);
                    infoShopRequest.setResponse(count);

                    return infoShopRequest;
                })
                .collect(Collectors.toList());
    }

    public List<InfoShopRequest> findByKategoryForUser(Long id, String username) {
        Kategory kategory = ICategoryRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Магазин не знайдено"));

        User user = IUserRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Користувача не знайдено"));

        List<Favourite> favourites = IFavouriteRepository.findAllByUserId(user);
        List<InfoShop> infoShops = IInfoShopRepository.findAllByKategoryName(kategory);
        List<InfoShopRequest> infoShopRequests = new ArrayList<>();

        for (InfoShop infoShop : infoShops) {
            InfoShopRequest infoShopRequest = new InfoShopRequest();
            int sum = 0, count = 0;

            for (Favourite favourite : favourites) {
                if (favourite.getId_shop().getId().equals(infoShop.getId())) {
                    infoShopRequest.setSubscribeID(infoShop.getId());
                    infoShopRequest.setSubscribe(true);
                    break;
                }
            }

            Review[] reviewShop = IReviewRepository.findAllById(infoShop).toArray(new Review[0]);

            if (reviewShop.length > 0) {
                for (Review entity : reviewShop) {
                    count++;
                    sum = sum + entity.getBall();
                }

                infoShopRequest.setBall(sum / count);
                infoShopRequest.setResponse(count);
            } else {
                infoShopRequest.setBall(0);
                infoShopRequest.setResponse(0);
            }

            infoShopRequest.setId(infoShop.getId());
            infoShopRequest.setId_Kategory(infoShop.getId_Kategory());
            infoShopRequest.setContacts_people(infoShop.getContacts_people());
            infoShopRequest.setEmail(infoShop.getEmail());
            infoShopRequest.setLogo(infoShop.getLogo());
            infoShopRequest.setName_shop(infoShop.getName_shop());
            infoShopRequest.setPhone(infoShop.getPhone());
            infoShopRequest.setWebsite(infoShop.getWebsite());

            infoShopRequests.add(infoShopRequest);
        }

        return infoShopRequests;
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

    public InfoShopRequest[] getAllShops(String username) {
        List<InfoShopRequest> subscribesList = getFavourite(username);
        Set<Long> subscribedIds = subscribesList.stream().map(InfoShopRequest::getId).collect(Collectors.toSet());
        List<InfoShopRequest> allshops = selectAllShop();
        List<InfoShopRequest> result = allshops.stream()
                .filter(s -> !subscribedIds.contains(s.getId()))
                .collect(Collectors.toList());
        return result.toArray(new InfoShopRequest[0]);
    }


}