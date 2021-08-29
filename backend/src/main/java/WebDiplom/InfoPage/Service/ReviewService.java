package WebDiplom.InfoPage.Service;

import WebDiplom.InfoPage.Models.InfoShop;
import WebDiplom.InfoPage.Models.ReviewEntity;
import WebDiplom.InfoPage.Models.UserEntity;
import WebDiplom.InfoPage.Repository.InfoShopRepository;
import WebDiplom.InfoPage.Repository.ReviewRepository;

import WebDiplom.InfoPage.Repository.UserRepository;
import WebDiplom.InfoPage.dto.ReviewDto;
import WebDiplom.InfoPage.dto.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    InfoShopRepository infoShopRepository;
    @Autowired
    UserRepository userRepository;

    public void addReview(Long id, String username, ReviewDto reviewDto){
        UserEntity userEntity =   userRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("Користувача не знайдено"));
        InfoShop infoShop = infoShopRepository.findById(id).orElseThrow(() -> new RuntimeException("Магази не знайдено"));
            ReviewEntity reviewEntity = new ReviewEntity();
            reviewEntity.setId_shop(infoShop);
            reviewEntity.setId_user(userEntity);
            reviewEntity.setText(reviewDto.getText());
            reviewEntity.setHeader(reviewDto.getHeader());
            reviewEntity.setBall(reviewDto.getBall());
            reviewEntity.setData(reviewDto.getData());
            reviewRepository.save(reviewEntity);
    }

        public List<ReviewRequest>  findReviewById(Long id){
            InfoShop infoShop = infoShopRepository.findById(id).orElseThrow(() -> new RuntimeException("Магази не знайдено"));
            ReviewEntity[] reviewEntities = reviewRepository.findAllByShopId(infoShop).toArray(new ReviewEntity[0]);
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

}
