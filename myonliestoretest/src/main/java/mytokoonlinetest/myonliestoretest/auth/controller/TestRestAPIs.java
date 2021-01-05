package mytokoonlinetest.myonliestoretest.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestAPIs {

    @GetMapping("/api/test/seller")
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    public String sellerAccess() {
        return ">>>  Access Seller Contents!";
    }

    @GetMapping("/api/test/buyer")
    @PreAuthorize("hasRole('BUYER') or hasRole('ADMIN')")
    public String buyerAccess() {
        return ">>> Access Buyer Content";
    }

    @GetMapping("/api/test/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return ">>> Access Admin Contents";
    }
}
