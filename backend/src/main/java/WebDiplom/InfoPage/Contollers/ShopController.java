package WebDiplom.InfoPage.Contollers;

import WebDiplom.InfoPage.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import WebDiplom.InfoPage.Service.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    UserService userService;

    @GetMapping("/allShop")
    public ResponseEntity getallShop(){
        return new ResponseEntity(userService.selectAllShop(), HttpStatus.OK);
    }

    @PostMapping("/addimg/{id}")
    public ResponseEntity addImg(@RequestParam("imageFile") MultipartFile file, @PathVariable("id") Long id){
        userService.saveImageShop(file,id);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/kategory")
    public ResponseEntity getallKategory(){
        return new ResponseEntity(userService.selectallKategory(),HttpStatus.OK);
    }

    @PostMapping("/favourite/{username}/{id}")
    public ResponseEntity favourite(@PathVariable("username") String username, @PathVariable("id") Long id)
    { userService.favourite(username,id);
        return  new ResponseEntity(HttpStatus.OK ); }

    @GetMapping("/find/{id}")
    public ResponseEntity findshop(@PathVariable("id") Long id){
        return  new ResponseEntity(userService.findShop(id),HttpStatus.OK);
    }

    @GetMapping("/kategory/find/{id}")
    public ResponseEntity findkategory(@PathVariable("id") Long id){
        return  new ResponseEntity( userService.findByKategory(id),HttpStatus.OK);
    }
}
