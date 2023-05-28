package WebDiplom.InfoPage.contollers;

import WebDiplom.InfoPage.service.ReviewService;
import WebDiplom.InfoPage.dto.ReviewDto;
import WebDiplom.InfoPage.dto.UserRequestLoginAndPassword;
import WebDiplom.InfoPage.dto.UserRequestUpdateData;
import WebDiplom.InfoPage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ReviewService  reviewService;

    @PostMapping("/setImg/{username}")
    public ResponseEntity setinfo(@RequestParam("imageFile") MultipartFile file, @PathVariable("username") String username){
        userService.saveImage(file,username);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getInfoUser/{username}")
    public ResponseEntity findUser(@PathVariable("username") String username) {
        return new ResponseEntity(userService.getUser(username), HttpStatus.OK);
    }

    @PostMapping("/deleteImg/{username}")
    public ResponseEntity deleteimg(@PathVariable("username") String username){
        userService.deleteImg(username);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/setInfo/{username}")
    public ResponseEntity setinfo(@PathVariable("username") String username,@RequestBody UserRequestUpdateData userRequestUpdateData) {
        userService.setInfo(username, userRequestUpdateData);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getfavourite/{username}")
    public ResponseEntity getfavourite(@PathVariable("username") String username){
        return new ResponseEntity(userService.getFavourite(username),HttpStatus.OK);
    }

    @DeleteMapping("/delete/favourite/{id}")
    public ResponseEntity deleteFavourite(@PathVariable("id") Long id){
        userService.deleteFavourite(id);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/review/allpost/{id}")
    public ResponseEntity getllreview(@PathVariable("id")Long id){
        return  new ResponseEntity(reviewService.findReviewById(id),HttpStatus.OK);
    }

    @PostMapping("/add/review/{id}/{username}")
    public ResponseEntity postreview(@PathVariable("id") Long id, @PathVariable("username") String username, @RequestBody ReviewDto reviewDto){
        reviewService.addReview(id,username,reviewDto);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/setinfo/loginandpassword/{username}")
    public ResponseEntity setInfo(@PathVariable("username") String username, @RequestBody UserRequestLoginAndPassword userRequestLoginAndPassword){
        userService.setInfoLoginAndPassword(username,userRequestLoginAndPassword);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getall/shop/{username}")
    public ResponseEntity getall(@PathVariable ("username") String username){
    return  new ResponseEntity(userService.getAllShops(username),HttpStatus.OK);
    }

    @GetMapping("/find/user/{username}/{id}")
    public ResponseEntity findusershops(@PathVariable("username") String username,@PathVariable("id") Long id){
        return  new ResponseEntity(userService.findShopForUser(id,username),HttpStatus.OK);
    }

    @GetMapping("/find/kategory/{username}/{id}")
    public ResponseEntity findbykategory(@PathVariable("username") String username,@PathVariable("id") Long id){
        return  new ResponseEntity(userService.findByKategoryForUser(id,username),HttpStatus.OK);
    }
}
