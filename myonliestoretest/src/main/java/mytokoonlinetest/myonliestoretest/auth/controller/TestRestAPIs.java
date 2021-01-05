package mytokoonlinetest.myonliestoretest.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestAPIs {
    @GetMapping("/api/test/seller")
    @PreAuthorize("hasRole('USER') or hasRole('SELLER')")
    public String userAccess() {
        return ">>> Seller Contents!";
    }

    @GetMapping("/api/test/buyer")
    @PreAuthorize("hasRole('USER') or hasRole('BUYER')")
    public String projectManagementAccess() {
        return ">>> Buyer Content";
    }

    @GetMapping("/api/test/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return ">>> Admin Contents";
    }

}
