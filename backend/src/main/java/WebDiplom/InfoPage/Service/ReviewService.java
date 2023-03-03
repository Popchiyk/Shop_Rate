package WebDiplom.InfoPage.service;

import WebDiplom.InfoPage.models.InfoShop;
import WebDiplom.InfoPage.models.Review;
import WebDiplom.InfoPage.models.User;
import WebDiplom.InfoPage.repository.IInfoShopRepository;
import WebDiplom.InfoPage.repository.IReviewRepository;

import WebDiplom.InfoPage.repository.IUserRepository;
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
    IReviewRepository IReviewRepository;
    @Autowired
    IInfoShopRepository IInfoShopRepository;
    @Autowired
    IUserRepository IUserRepository;

    public void addReview(Long id, String username, ReviewDto reviewDto){
        User user =   IUserRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("Користувача не знайдено"));
        InfoShop infoShop = IInfoShopRepository.findById(id).orElseThrow(() -> new RuntimeException("Магази не знайдено"));
            Review review = new Review();
            review.setId_shop(infoShop);
            review.setId_user(user);
            review.setText(reviewDto.getText());
            review.setHeader(reviewDto.getHeader());
            review.setBall(reviewDto.getBall());
            review.setData(reviewDto.getData());
            IReviewRepository.save(review);
    }

        public List<ReviewRequest>  findReviewById(Long id){
            InfoShop infoShop = IInfoShopRepository.findById(id).orElseThrow(() -> new RuntimeException("Магази не знайдено"));
            Review[] reviewEntities = IReviewRepository.findAllByShopId(infoShop).toArray(new Review[0]);
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
