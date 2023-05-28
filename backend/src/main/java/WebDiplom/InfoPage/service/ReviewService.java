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
import java.util.stream.Collectors;

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

    public List<ReviewRequest> findReviewById(Long id) {
        InfoShop infoShop = IInfoShopRepository.findById(id).orElseThrow(() -> new RuntimeException("Магази не знайдено"));
        return IReviewRepository.findAllByShopId(infoShop).stream()
                .map(this::mapReviewEntityToReviewRequest)
                .collect(Collectors.toList());
    }

    private ReviewRequest mapReviewEntityToReviewRequest(Review entity) {
        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setId(entity.getId());
        reviewRequest.setId_shop(entity.getId_shop().getId());
        reviewRequest.setText(entity.getText());
        reviewRequest.setBall(entity.getBall());
        reviewRequest.setUsername(entity.getId_user().getUserName());
        reviewRequest.setLogo(entity.getId_user().getLogo());
        reviewRequest.setName(entity.getId_user().getName());
        reviewRequest.setHeader(entity.getHeader());
        reviewRequest.setSurname(entity.getId_user().getSurname());
        reviewRequest.setData(entity.getData());
        return reviewRequest;
    }

}
