package com.example.HomePS.controller;

import com.example.HomePS.dto.BillRequest;
import com.example.HomePS.dto.OrderServiceDto;
import com.example.HomePS.model.Bill;
import com.example.HomePS.model.OrderService;
import com.example.HomePS.service.BillService;
import com.example.HomePS.service.ESService;
import com.example.HomePS.service.OrderESService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bills")
@AllArgsConstructor
public class BillController {

    private final BillService billService;
    private final OrderESService orderESService;
    private final ESService esService;

    @PostMapping
    public void createNewBill(@RequestBody BillRequest billRequest){
        billService.create(billRequest);
    }

    @PutMapping("/endbill/{billId}")
    public void finishBill(@PathVariable Long billId){
        billService.endBill(billId);
    }

    @PutMapping("/{billId}")
    public Bill addExtraService(@RequestBody OrderForm form, @PathVariable Long billId){
        List<OrderServiceDto> formDtos = form.getOrderServiceDtos();
        Bill bill = billService.getBill(billId);
        if(!bill.isPaid()){
            List<OrderService> orderServices = new ArrayList<>();
            for(OrderServiceDto dto: formDtos){
                orderServices.add(orderESService.create(new OrderService(bill, esService.getService(dto.getExtraService().getServiceId()), dto.getQuantity())));
            }
            bill.setOrderServices(orderServices);

            return billService.update(bill);
        }
        return bill;
    }

    @GetMapping
    public Iterable<Bill> getAllBills(
            @RequestParam(required = false, defaultValue = "full") String status,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "billId") String sortBy
    ){
        switch (status) {
            case "paid": return billService.getAllPaidBill(page - 1, size, sortBy);
            case "unpaid": return billService.getAllUnpaidBill(page - 1, size, sortBy);
            default: return billService.getAllBill(page - 1, size, sortBy);
        }
    }

    @GetMapping("/{id}")
    public Bill getBill(@PathVariable Long id){
        return billService.getBill(id);
    }

    public static class OrderForm{
        private List<OrderServiceDto> orderServiceDtos ;

        public List<OrderServiceDto> getOrderServiceDtos() {
            return orderServiceDtos;
        }

        public void setOrderServiceDtos(List<OrderServiceDto> orderServiceDtos) {
            this.orderServiceDtos = orderServiceDtos;
        }
    }
}
