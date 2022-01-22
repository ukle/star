package me.star;

import lombok.RequiredArgsConstructor;
import me.star.system.service.UserService;
import me.star.zheshan.service.ZheshanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/22
 */
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final ZheshanService zheshanService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> info() {

//        int userTotal = userService.countAllEnableUser();
        int onSaleCount = zheshanService.countBySaleStatusIn(List.of("0", "1"));
        int saleOutCount = zheshanService.countBySaleStatusIn(List.of("3"));
        int saleInTotal = zheshanService.sumBySaleIn();
        int saleOutTotal = zheshanService.sumBySaleOut();
        Map<String, Integer> data = new HashMap<>();
//        data.put("userTotal", userTotal);
        data.put("onSaleCount", onSaleCount);
        data.put("saleOutCount", saleOutCount);
        data.put("saleInTotal", saleInTotal);
        data.put("saleOutTotal", saleOutTotal);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
