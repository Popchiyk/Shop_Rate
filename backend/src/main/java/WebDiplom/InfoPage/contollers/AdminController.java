package WebDiplom.InfoPage.contollers;


import WebDiplom.InfoPage.service.AdminService;
import WebDiplom.InfoPage.service.UserService;
import WebDiplom.InfoPage.dto.ShopDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity getallusers() {
        return new ResponseEntity(adminService.selectalluser(), HttpStatus.OK);
    }

    @GetMapping("/kategorys")
    public ResponseEntity getallkategorys() {
        return new ResponseEntity(adminService.selectallKategory(), HttpStatus.OK);
    }

    @GetMapping("/shops")
    public ResponseEntity getallshops() {
        return new ResponseEntity(adminService.selectAllShop(), HttpStatus.OK);
    }

    @GetMapping("/reviews")
    public ResponseEntity getallreviews() {
        return new ResponseEntity(adminService.selectallreview(), HttpStatus.OK);
    }

    @PostMapping("/add/shop")
    public ResponseEntity addshop(@RequestBody ShopDto shopDto) {
        adminService.AddShop(shopDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/add/kategory")
    public ResponseEntity addkategory(@RequestBody String kategory) {
        adminService.AddKategory(kategory);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/update/shop/{id}")
    public ResponseEntity updateshop(@RequestBody ShopDto shopDto,@PathVariable Long id){
        adminService.UpdateShop(id,shopDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/find/kategory/{id}")
    public ResponseEntity findkategory(@PathVariable Long id){
    return  new ResponseEntity(adminService.Findkategory(id),HttpStatus.OK);
    }

    @PutMapping("/update/kategory/{id}")
    public ResponseEntity updatekategory(@PathVariable Long id, @RequestBody  String kategory)
    {
        adminService.UpdateKategory(id,kategory);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/kategory/{id}")
    public  ResponseEntity deletekategory(@PathVariable Long id){
        adminService.DeleteKategory(id);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/shop/{id}")
    public  ResponseEntity deleteshop(@PathVariable Long id){
        adminService.DeleteShop(id);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/user/{id}")
    public  ResponseEntity deleteuser(@PathVariable Long id){
        adminService.DeleteUser(id);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/review/{id}")
    public  ResponseEntity deletereview(@PathVariable Long id){
        adminService.DeleteReview(id);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/stats")
    public  ResponseEntity getCountStats(){
        return  new ResponseEntity(  adminService.getCountStats(),HttpStatus.OK);
    }

    @GetMapping("/chart")
    public  ResponseEntity getDateAndCountReview(){
        return  new ResponseEntity(  adminService.getCountAndDate(),HttpStatus.OK);
    }
}
